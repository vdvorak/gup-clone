CREATE TYPE AMOUNT AS ENUM('50','100','500');

CREATE TABLE invite_codes(
  id  SERIAL PRIMARY KEY,
  code           TEXT,
  amount         AMOUNT,
  user_id        CHAR(50) NULL,
  date           DATE NOT NULL DEFAULT CURRENT_DATE,
  current_amount INT NULL
);

CREATE OR REPLACE FUNCTION generate_invite_codes(count INTEGER, amount AMOUNT) RETURNS TEXT AS
  $$
  DECLARE
    chars TEXT[] := '{0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z}';
    code  TEXT   := '';
    i  INTEGER   := 0;

  BEGIN
    FOR c IN 1 .. count LOOP
      code := '';
      FOR i IN 1..50 LOOP
        code := code || chars[1+random()*(array_length(chars, 1)-1)];
      END LOOP;
      INSERT INTO invite_codes (code, amount) VALUES (code, amount);
    END LOOP;
    RETURN 'Success';
  END;
  $$
LANGUAGE plpgsql;

SELECT generate_invite_codes(1000, '50') AS answer;
SELECT generate_invite_codes(1000, '100') AS answer;
SELECT generate_invite_codes(1000, '500') AS answer;
SELECT * FROM invite_codes;

DROP FUNCTION generate_invite_codes(length INTEGER, amount AMOUNT);
DROP TABLE invite_codes;
DROP TYPE AMOUNT;

-- ----------------------------------------------------------------------------

CREATE TYPE AMOUNT AS ENUM('50','100','500');


CREATE TABLE bobus(
  user_id CHAR(50) PRIMARY KEY,
  amount  INT DEFAULT 0
);

CREATE TABLE invite_codes(
  id             SERIAL PRIMARY KEY,
  code           TEXT,
  amount         AMOUNT,
  user_id        CHAR(50) REFERENCES bobus(user_id),
  create_date    DATE NOT NULL DEFAULT CURRENT_DATE,
  activate_date  DATE DEFAULT NULL
);


CREATE OR REPLACE FUNCTION generate_invite_codes(count INTEGER, amount AMOUNT) RETURNS TEXT AS
  $$
  DECLARE
    chars TEXT[] := '{0,1,2,3,4,5,6,7,8,9,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z}';
    code  TEXT   := '';
    i  INTEGER   := 0;

  BEGIN
    FOR c IN 1 .. count LOOP
      code := '';
      FOR i IN 1..50 LOOP
        code := code || chars[1+random()*(array_length(chars, 1)-1)];
      END LOOP;
      INSERT INTO invite_codes (code, amount) VALUES (code, amount);
    END LOOP;
    RETURN 'Success';
  END;
  $$
LANGUAGE plpgsql;

SELECT generate_invite_codes(1000, '50') AS answer;
SELECT generate_invite_codes(1000, '100') AS answer;
SELECT generate_invite_codes(1000, '500') AS answer;

INSERT INTO bobus(user_id) VALUES
  ('57a1f7c72a6a188051a3ba94')
  ,('578f34854c8e46b32797d92b');
UPDATE bobus SET amount=100 WHERE user_id='57a1f7c72a6a188051a3ba94';
SELECT * FROM bobus;

UPDATE invite_codes SET user_id='57a1f7c72a6a188051a3ba94', activate_date=CURRENT_DATE WHERE code='BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZOxg';
UPDATE invite_codes SET user_id='578f34854c8e46b32797d92b', activate_date=CURRENT_DATE WHERE code='6l4zoQn7AJqV3qjEGN5RgHSIqH6NQm1F8QJqLHecSNsq6WRrKR';
SELECT * FROM invite_codes;


CREATE OR REPLACE FUNCTION refill_invite_code(check_user TEXT, check_code TEXT) RETURNS TEXT AS
  $$
  DECLARE
    active_code  INTEGER := 0; -- CODE: NOT FOUND
    is_user      INTEGER := 0; -- USER: NOT FOUND
    is_user_code INTEGER := 0; -- NOT MATCH
    result          TEXT := '';

  BEGIN
    IF (SELECT count(*) FROM invite_codes WHERE user_id=check_user AND code=check_code) > 0 THEN
      is_user_code := -1; -- IS MATCH;
    END IF;

    IF (SELECT count(*) FROM invite_codes WHERE user_id=check_user) > 0 THEN
      is_user := -1; -- USER: IS USED;
    ELSE
      is_user := 1; -- USER: FREE;
    END IF;

    IF (SELECT count(*) FROM invite_codes WHERE code=check_code) > 0 THEN
      IF (SELECT user_id FROM invite_codes WHERE code=check_code) ISNULL THEN
        active_code := 1; -- CODE: FREE;
      ELSE
        active_code := -1; -- CODE: IS USED;
      END IF;
    END IF;

    IF (is_user_code) = 0 THEN -- если не дублируется
      IF (is_user) = 1 THEN -- если юзера еще нет
        IF (active_code) = 1 THEN -- если код свободен
          result := 'SUCCESS';
        ELSE -- если зайнят #2
          result := 'ERROR: зайнят';
        END IF;
      ELSE -- больше одного раза нельзя #1
        result := 'ERROR: больше одного раза нельзя';
      END IF;
    ELSE -- дублирует инвайт-код
      result := 'ERROR: дублирует инвайт-код';
    END IF;
    RETURN result;
  END;
  $$
LANGUAGE plpgsql;

SELECT refill_invite_code('57a1f7c72a6a188051a3ba94', 'PxnMc3V659T6XJMl4kSRpbJHYHkTvptrzPfEFRvZjZCcsszmii') AS answer;
SELECT refill_invite_code('57a1f7c72a6a188051a3ba94', 'PxnMc3V659T6XJMl4kSRpbJHYHkTvptrzPfEFRvZjZCcsszm') AS answer;
SELECT refill_invite_code('57a1f7c72a6a188051a3ba94', 'bgihaPwlzZ8MFUDiQ1btG9u8YYpXbdAkVcyLFJBH3r8YqesvwD') AS answer;
SELECT refill_invite_code('57a1f7c72a6a188051a3ba', 'PxnMc3V659T6XJMl4kSRpbJHYHkTvptrzPfEFRvZjZCcsszmii') AS answer;
SELECT refill_invite_code('57a1f7c72a6a188051a3ba', 'PxnMc3V659T6XJMl4kSRpbJHYHkTvptrzPfEFRvZjZCcsszm') AS answer;
SELECT refill_invite_code('57a1f7c72a6a188051a3ba', 'bgihaPwlzZ8MFUDiQ1btG9u8YYpXbdAkVcyLFJBH3r8YqesvwD') AS answer;


DROP FUNCTION generate_invite_codes(length INTEGER, amount AMOUNT);
DROP FUNCTION refill_invite_code(check_user TEXT, check_code TEXT);
DROP TABLE bobus;
DROP TABLE invite_codes;
DROP TYPE AMOUNT;

