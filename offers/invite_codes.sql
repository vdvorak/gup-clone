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


CREATE OR REPLACE FUNCTION fn_generate_invite_codes("count" INTEGER, "amount" AMOUNT) RETURNS TEXT AS
  $BODY$
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
    RETURN 'SUCCESS';
  END;
  $BODY$
LANGUAGE plpgsql;

SELECT fn_generate_invite_codes(1000, '50') AS answer;
SELECT fn_generate_invite_codes(1000, '100') AS answer;
SELECT fn_generate_invite_codes(1000, '500') AS answer;

INSERT INTO bobus(user_id) VALUES
  ('57a1f7c72a6a188051a3ba94')
  ,('578f34854c8e46b32797d92b');
UPDATE bobus SET amount=100 WHERE user_id='57a1f7c72a6a188051a3ba94';
SELECT * FROM bobus;

UPDATE invite_codes SET user_id='57a1f7c72a6a188051a3ba94', activate_date=CURRENT_DATE WHERE code='BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZOxg';
UPDATE invite_codes SET user_id='578f34854c8e46b32797d92b', activate_date=CURRENT_DATE WHERE code='6l4zoQn7AJqV3qjEGN5RgHSIqH6NQm1F8QJqLHecSNsq6WRrKR';
SELECT * FROM invite_codes;


CREATE OR REPLACE FUNCTION fn_refill_invite_code(IN "check_user" TEXT, IN "check_code" TEXT, OUT "status" character varying, OUT "answer" character varying) RETURNS SETOF record AS
  $BODY$
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

    IF (active_code) = 0 THEN -- если код не найден
      status := 'ERROR';
      answer := 'инвайт-код не найден';
    ELSE
      IF (is_user_code) = 0 THEN -- если не дублируется
        IF (is_user) = 1 THEN -- если юзера еще нет
          IF (active_code) = 1 THEN -- если код свободен
            status := 'SUCCESS';
            answer := 'инвайт-код прийнят';
          ELSE
            status := 'ERROR';
            answer := 'инвайт-код зайнят';
          END IF;
        ELSE
          status := 'ERROR';
          answer := 'пользователю больше одного раза нельзя';
        END IF;
      ELSE
        status := 'ERROR';
        answer := 'дублирует инвайт-код';
      END IF;
    END IF;
    RETURN NEXT;
  END;
  $BODY$
LANGUAGE plpgsql;

SELECT fn_refill_invite_code('57a1f7c72a6a188051a3ba94', 'BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZOxg') AS answer;
SELECT fn_refill_invite_code('57a1f7c72a6a188051a3ba94', 'BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZO') AS answer;
SELECT fn_refill_invite_code('1a2a3a4a5a6a7a8a9a0abccd', '2gRc5Cb2aVJy2D64RG2rdwGXyHOqUgcSN1xanoIbwuabsIIHXO') AS answer;
SELECT fn_refill_invite_code('578f34854c8e46b32797d92b', 'BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZOxg') AS answer;
SELECT fn_refill_invite_code('578f34854c8e46b32797d92b', 'BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZO') AS answer;
SELECT fn_refill_invite_code('1a2a3a4a5a6a7a8a9a0abccd', 'BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZO') AS answer;
SELECT fn_refill_invite_code('1a2a3a4a5a6a7a8a9a0abccd', '2gRc5Cb2aVJy2D64RG2rdwGXyHOqUgcSN1xanoIbwuabsIIHXO') AS answer;


DROP FUNCTION fn_generate_invite_codes(length INTEGER, amount AMOUNT);
DROP FUNCTION fn_refill_invite_code(check_user TEXT, check_code TEXT);
DROP TABLE bobus;
DROP TABLE invite_codes;
DROP TYPE AMOUNT;
