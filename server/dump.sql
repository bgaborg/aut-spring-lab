SET DATABASE UNIQUE NAME HSQLDB49386D9E9B
SET DATABASE GC 0
SET DATABASE DEFAULT RESULT MEMORY ROWS 0
SET DATABASE EVENT LOG LEVEL 0
SET DATABASE TRANSACTION CONTROL LOCKS
SET DATABASE DEFAULT ISOLATION LEVEL READ COMMITTED
SET DATABASE TRANSACTION ROLLBACK ON CONFLICT TRUE
SET DATABASE TEXT TABLE DEFAULTS ''
SET DATABASE SQL NAMES FALSE
SET DATABASE SQL REFERENCES FALSE
SET DATABASE SQL SIZE TRUE
SET DATABASE SQL TYPES FALSE
SET DATABASE SQL TDC DELETE TRUE
SET DATABASE SQL TDC UPDATE TRUE
SET DATABASE SQL TRANSLATE TTI TYPES TRUE
SET DATABASE SQL CONCAT NULLS TRUE
SET DATABASE SQL UNIQUE NULLS TRUE
SET DATABASE SQL CONVERT TRUNCATE TRUE
SET DATABASE SQL AVG SCALE 0
SET DATABASE SQL DOUBLE NAN TRUE
SET FILES WRITE DELAY 500 MILLIS
SET FILES BACKUP INCREMENT TRUE
SET FILES CACHE SIZE 10000
SET FILES CACHE ROWS 50000
SET FILES SCALE 32
SET FILES LOB SCALE 32
SET FILES DEFRAG 0
SET FILES NIO TRUE
SET FILES NIO SIZE 256
SET FILES LOG TRUE
SET FILES LOG SIZE 50
CREATE USER SA PASSWORD DIGEST 'd41d8cd98f00b204e9800998ecf8427e'
ALTER USER SA SET LOCAL TRUE
CREATE SCHEMA PUBLIC AUTHORIZATION DBA
SET SCHEMA PUBLIC
CREATE MEMORY TABLE PUBLIC.AUTHORITIES(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,AUTHORITY VARCHAR(255),USERNAME VARCHAR(255))
ALTER TABLE PUBLIC.AUTHORITIES ALTER COLUMN ID RESTART WITH 3
CREATE MEMORY TABLE PUBLIC.MEASUREMENT(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,DATE TIMESTAMP,GPS_ACCURACY DOUBLE NOT NULL,SIGNAL_STRENGTH DOUBLE NOT NULL)
ALTER TABLE PUBLIC.MEASUREMENT ALTER COLUMN ID RESTART WITH 1
CREATE MEMORY TABLE PUBLIC.PHONE(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,API_KEY VARCHAR(255),NICKNAME VARCHAR(255),CONSTRAINT UK_D7XHBBEDE3872KIKFT6W0T5CC UNIQUE(API_KEY))
ALTER TABLE PUBLIC.PHONE ALTER COLUMN ID RESTART WITH 1
CREATE MEMORY TABLE PUBLIC.USERS(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,ENABLED BOOLEAN,PASSWORD VARCHAR(255),USERNAME VARCHAR(255),CONSTRAINT UK_R43AF9AP4EDM43MMTQ01ODDJ6 UNIQUE(USERNAME))
ALTER TABLE PUBLIC.USERS ALTER COLUMN ID RESTART WITH 3
ALTER SEQUENCE SYSTEM_LOBS.LOB_ID RESTART WITH 1
SET DATABASE DEFAULT INITIAL SCHEMA PUBLIC
GRANT USAGE ON DOMAIN INFORMATION_SCHEMA.SQL_IDENTIFIER TO PUBLIC
GRANT USAGE ON DOMAIN INFORMATION_SCHEMA.YES_OR_NO TO PUBLIC
GRANT USAGE ON DOMAIN INFORMATION_SCHEMA.TIME_STAMP TO PUBLIC
GRANT USAGE ON DOMAIN INFORMATION_SCHEMA.CARDINAL_NUMBER TO PUBLIC
GRANT USAGE ON DOMAIN INFORMATION_SCHEMA.CHARACTER_DATA TO PUBLIC
GRANT DBA TO SA
SET SCHEMA SYSTEM_LOBS
INSERT INTO BLOCKS VALUES(0,2147483647,0)
SET SCHEMA PUBLIC
INSERT INTO AUTHORITIES VALUES(1,'ROLE_ADMIN','user')
INSERT INTO AUTHORITIES VALUES(2,'ROLE_USER','user2')
INSERT INTO USERS VALUES(1,TRUE,'04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb','user')
INSERT INTO USERS VALUES(2,TRUE,'04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb','user2')
