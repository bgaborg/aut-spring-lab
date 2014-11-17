SET DATABASE UNIQUE NAME HSQLDB49BFE623EC
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
ALTER TABLE PUBLIC.PHONE ALTER COLUMN ID RESTART WITH 2
CREATE MEMORY TABLE PUBLIC.PHROBE_DATA_OBJECT(ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 1) NOT NULL PRIMARY KEY,API_KEY VARCHAR(255),INTERVAL INTEGER NOT NULL,ACCURACY DOUBLE NOT NULL,ALTITUDE DOUBLE NOT NULL,BEARING DOUBLE NOT NULL,ELAPSED_REALTIME_NANOS DOUBLE NOT NULL,LATITUDE DOUBLE NOT NULL,LONGITUDE DOUBLE NOT NULL,PROVIDER VARCHAR(255),SPEED DOUBLE NOT NULL,TIME BIGINT NOT NULL,DBM INTEGER NOT NULL,SIGNAL_STRENGTH INTEGER NOT NULL,TIMESTAMP BIGINT NOT NULL)
ALTER TABLE PUBLIC.PHROBE_DATA_OBJECT ALTER COLUMN ID RESTART WITH 24
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
INSERT INTO PHONE VALUES(1,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8','Samsung Galaxy S IV')
INSERT INTO PHROBE_DATA_OBJECT VALUES(1,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,0.0E0,0.0E0,0.0E0,0.0E0,0.0E0,0.0E0,NULL,0.0E0,0,-120,14,1416263812744)
INSERT INTO PHROBE_DATA_OBJECT VALUES(2,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,168.0E0,181.0E0,0.0E0,9.039293873428E13,47.51556581E0,19.10741857E0,'gps',0.0E0,1416263820000,-120,14,1416263822745)
INSERT INTO PHROBE_DATA_OBJECT VALUES(3,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,119.0E0,181.0E0,0.0E0,9.0405941175686E13,47.51556642E0,19.10741884E0,'gps',0.0E0,1416263833000,-120,14,1416263832756)
INSERT INTO PHROBE_DATA_OBJECT VALUES(4,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,100.0E0,181.0E0,0.0E0,9.0411944074855E13,47.51556642E0,19.10741884E0,'gps',0.0E0,1416263839000,-120,14,1416263842754)
INSERT INTO PHROBE_DATA_OBJECT VALUES(5,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,359.0E0,181.0E0,0.0E0,9.042493507217E13,47.51556521E0,19.10741865E0,'gps',0.0E0,1416263852000,-120,14,1416263852753)
INSERT INTO PHROBE_DATA_OBJECT VALUES(6,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,148.0E0,181.0E0,0.0E0,9.0429935255276E13,47.51556494E0,19.10741904E0,'gps',0.0E0,1416263857000,-120,14,1416263862758)
INSERT INTO PHROBE_DATA_OBJECT VALUES(7,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,333.0E0,181.0E0,0.0E0,9.044494074844E13,47.51556465E0,19.107419E0,'gps',0.0E0,1416263872000,-120,14,1416263872762)
INSERT INTO PHROBE_DATA_OBJECT VALUES(8,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,186.0E0,181.0E0,0.0E0,9.0449945631253E13,47.51556495E0,19.10741881E0,'gps',0.0E0,1416263877000,-120,14,1416263882765)
INSERT INTO PHROBE_DATA_OBJECT VALUES(9,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,191.0E0,181.0E0,0.0E0,9.0464947645411E13,47.51556507E0,19.10741862E0,'gps',0.0E0,1416263892000,-120,12,1416263892769)
INSERT INTO PHROBE_DATA_OBJECT VALUES(10,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,225.0E0,181.0E0,0.0E0,9.0472949720609E13,47.5155641E0,19.10741398E0,'gps',0.0E0,1416263900000,-120,12,1416263902803)
INSERT INTO PHROBE_DATA_OBJECT VALUES(11,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,272.0E0,180.0E0,0.0E0,9.0485942548977E13,47.51556185E0,19.10740529E0,'gps',0.0E0,1416263913000,-120,12,1416263912775)
INSERT INTO PHROBE_DATA_OBJECT VALUES(12,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,227.0E0,180.0E0,0.0E0,9.0492937025293E13,47.51556181E0,19.10740565E0,'gps',0.0E0,1416263920000,-120,12,1416263922779)
INSERT INTO PHROBE_DATA_OBJECT VALUES(13,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,554.0E0,180.0E0,0.0E0,9.050594630264E13,47.51555994E0,19.10740761E0,'gps',0.0E0,1416263933000,-120,12,1416263932779)
INSERT INTO PHROBE_DATA_OBJECT VALUES(14,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,616.0E0,180.0E0,0.0E0,9.0514944959865E13,47.51555785E0,19.10740808E0,'gps',0.0E0,1416263942000,-120,12,1416263942786)
INSERT INTO PHROBE_DATA_OBJECT VALUES(15,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,651.0E0,180.0E0,0.0E0,9.0524948957668E13,47.51555417E0,19.10741037E0,'gps',0.0E0,1416263952000,-120,11,1416263952797)
INSERT INTO PHROBE_DATA_OBJECT VALUES(16,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,519.0E0,180.0E0,0.0E0,9.0534955305324E13,47.51555222E0,19.10741114E0,'gps',0.0E0,1416263962000,-120,11,1416263962794)
INSERT INTO PHROBE_DATA_OBJECT VALUES(17,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,549.0E0,180.0E0,0.0E0,9.0542945142971E13,47.51555048E0,19.10741124E0,'gps',0.0E0,1416263970000,-120,13,1416263972796)
INSERT INTO PHROBE_DATA_OBJECT VALUES(18,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,778.0E0,180.0E0,0.0E0,9.055293833755E13,47.51554673E0,19.10741159E0,'gps',0.0E0,1416263980000,-120,13,1416263982799)
INSERT INTO PHROBE_DATA_OBJECT VALUES(19,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,826.0E0,180.0E0,0.0E0,9.056494816421E13,47.51554449E0,19.10741035E0,'gps',0.0E0,1416263992000,-120,13,1416263992809)
INSERT INTO PHROBE_DATA_OBJECT VALUES(20,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,581.0E0,180.0E0,0.0E0,9.0575955641017E13,47.51554294E0,19.1074098E0,'gps',0.0E0,1416264003000,-120,13,1416264002813)
INSERT INTO PHROBE_DATA_OBJECT VALUES(21,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,456.0E0,180.0E0,0.0E0,9.0583946882474E13,47.5155442E0,19.10740972E0,'gps',0.0E0,1416264011000,-120,14,1416264012816)
INSERT INTO PHROBE_DATA_OBJECT VALUES(22,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,618.0E0,180.0E0,0.0E0,9.0592945417628E13,47.51554596E0,19.10740928E0,'gps',0.0E0,1416264020000,-120,14,1416264022820)
INSERT INTO PHROBE_DATA_OBJECT VALUES(23,'APA91bH29fcvqbBf6OjXEKpvldyaGIwlOW2kbyzjbK_okpkmN9xJnD6pFp6J_rXEPKGLOsM5N7RvV_Mke5Rv2MC9CxOGrqG9WFtODTaYE1HwYApiwAedQoYPVupL3ZcWrVBTwNqj4hrX6NRBhe5JgDXlnGlcuX0lHL_KaPKv9Wf6djONxJ5l1E8',10000,175.0E0,180.0E0,0.0E0,9.0603021162257E13,47.51554627E0,19.10740879E0,'gps',0.0E0,1416264030000,-120,13,1416264032829)
INSERT INTO USERS VALUES(1,TRUE,'04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb','user')
INSERT INTO USERS VALUES(2,TRUE,'04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb','user2')