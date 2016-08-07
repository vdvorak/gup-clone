CREATE TYPE AMOUNT AS ENUM('50','100','500');


CREATE TABLE bonus(
  user_id CHAR(50) PRIMARY KEY,
  amount  INT DEFAULT 0
);

CREATE TABLE invite_codes(
  id             SERIAL PRIMARY KEY,
  code           TEXT,
  amount         AMOUNT,
  user_id        CHAR(50) REFERENCES bonus(user_id),
  create_date    DATE NOT NULL DEFAULT CURRENT_DATE,
  activate_date  DATE DEFAULT NULL
);


CREATE OR REPLACE FUNCTION fn_generate_invite_code("count" INTEGER, "amount" AMOUNT) RETURNS TEXT AS
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

SELECT fn_generate_invite_code(1000, '50') AS answer;
SELECT fn_generate_invite_code(1000, '100') AS answer;
SELECT fn_generate_invite_code(1000, '500') AS answer;

INSERT INTO bonus(user_id) VALUES
  ('57a1f7c72a6a188051a3ba94')
  ,('578f34854c8e46b32797d92b');
UPDATE bonus SET amount=100 WHERE user_id='57a1f7c72a6a188051a3ba94';
SELECT * FROM bonus;

UPDATE invite_codes SET user_id='57a1f7c72a6a188051a3ba94', activate_date=CURRENT_DATE WHERE code='BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZOxg';
UPDATE invite_codes SET user_id='578f34854c8e46b32797d92b', activate_date=CURRENT_DATE WHERE code='6l4zoQn7AJqV3qjEGN5RgHSIqH6NQm1F8QJqLHecSNsq6WRrKR';
SELECT * FROM invite_codes;


CREATE OR REPLACE FUNCTION fn_activate_invite_code(IN "current_user_id" TEXT, IN "current_code" TEXT, OUT "status" character varying, OUT "details" character varying) RETURNS SETOF record AS
  $BODY$
  DECLARE
    status_invite_code    INTEGER := 0;     -- CODE: NOT FOUND       (-1 | 0 | 1)
    is_double_invite_code BOOLEAN := FALSE; -- USER: NOT DOUBLE CODE (TRUE | FALSE)
    amount_invite_code        INT := 0;
    amount_bonus              INT := 0;

  BEGIN
    IF (SELECT count(*) FROM invite_codes WHERE user_id=current_user_id AND code=current_code) > 0 THEN
      is_double_invite_code := TRUE; -- CODE: IS DOUBLE;
    END IF;
    IF (SELECT count(*) FROM invite_codes WHERE code=current_code) > 0 THEN
      IF (SELECT activate_date FROM invite_codes WHERE code=current_code) ISNULL THEN
        status_invite_code := 1; -- CODE: FREE;
      ELSE
        status_invite_code := -1; -- CODE: ACTIVATED;
      END IF;
    END IF;

    IF (status_invite_code) = 0 THEN
      status  := 'ERROR';
      details := 'инвайт-код не существует';
    ELSE
      IF (is_double_invite_code) THEN
        status  := 'ERROR';
        details := 'клиент дважды активирует инвайт-код';
      ELSE
        IF (status_invite_code) = -1 THEN
          status  := 'ERROR';
          details := 'инвайт-код уже зайнят';
        ELSE
          amount_invite_code := (SELECT amount FROM invite_codes WHERE code=current_code);
          IF (SELECT count(*) FROM bonus WHERE user_id=current_user_id) = 0 THEN -- если в таблицу 'bonus' впервые добавляется инвайт-код
            INSERT INTO bonus(user_id,amount) VALUES (current_user_id,amount_invite_code);
            UPDATE invite_codes SET user_id=current_user_id, activate_date=CURRENT_DATE WHERE code=current_code;
          ELSE
            amount_bonus := (SELECT amount FROM bonus WHERE user_id=current_user_id);
            UPDATE bonus SET amount=(amount_bonus+amount_invite_code) WHERE user_id=current_user_id;
            UPDATE invite_codes SET user_id=current_user_id, activate_date=CURRENT_DATE WHERE code=current_code;
          END IF;
          status  := 'SUCCESS';
          details := 'инвайт-код успешно активирован';
        END IF;
      END IF;
    END IF;
    RETURN NEXT;
  END;
  $BODY$
LANGUAGE plpgsql;

SELECT fn_activate_invite_code('57a1f7c72a6a188051a3ba94', 'BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZO') AS answer;   -- инвайт-код не найден
SELECT fn_activate_invite_code('578f34854c8e46b32797d92b', 'BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZO') AS answer;   -- инвайт-код не найден
SELECT fn_activate_invite_code('1a2a3a4a5a6a7a8a9a0abccd', 'BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZO') AS answer;   -- инвайт-код не найден
SELECT fn_activate_invite_code('57a1f7c72a6a188051a3ba94', 'BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZOxg') AS answer; -- дублирует инвайт-код
SELECT fn_activate_invite_code('578f34854c8e46b32797d92b', 'BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZOxg') AS answer; -- инвайт-код зайнят
SELECT fn_activate_invite_code('1a2a3a4a5a6a7a8a9a0abccd', 'BE1B838URoEOdAoSHmQJ4JoNtqDlNgNWO975HGbnEcO1hHZOxg') AS answer; -- инвайт-код зайнят
SELECT fn_activate_invite_code('1a2a3a4a5a6a7a8a9a0abccd', '2gRc5Cb2aVJy2D64RG2rdwGXyHOqUgcSN1xanoIbwuabsIIHXO') AS answer; -- SUCCESS
SELECT fn_activate_invite_code('1a2a3a4a5a6a7a8a9a0abccd', 'sL2bKTh4vW5igYxkDN8piYXW0bDXP2DQyFnRq1kNX4lWtNKJbR') AS answer; -- SUCCESS
SELECT fn_activate_invite_code('1a2a3a4a5a6a7a8a9a0abccd', 'RMpBbHA1Jstn5A9YURw7oxhCr0Oy518SB64vxxLmTPIAPzwwOM') AS answer; -- SUCCESS


DROP FUNCTION fn_generate_invite_code("length" INTEGER, "amount" AMOUNT);
DROP FUNCTION fn_activate_invite_code("current_user_id" TEXT, "current_code" TEXT);
DROP TABLE bonus;
DROP TABLE invite_codes;
DROP TYPE AMOUNT;
