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