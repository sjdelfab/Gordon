CREATE SCHEMA IF NOT EXISTS GORDON
/
CREATE USER IF NOT EXISTS GORDON PASSWORD 'abc'
/
ALTER USER GORDON ADMIN TRUE
/
DROP TABLE IF EXISTS GORDON.REGISTRY
/
DROP TABLE IF EXISTS GORDON.EXCHANGE
/
DROP TABLE IF EXISTS GORDON.STOCK
/
DROP TABLE IF EXISTS GORDON.UI_TAB
/
DROP TABLE IF EXISTS GORDON.UI_GROUP
/
DROP TABLE IF EXISTS GORDON.STOCK_DAY_TRADE
/
DROP TABLE IF EXISTS GORDON.TABULAR_DATASET_DEFINITION
/
DROP TABLE IF EXISTS GORDON.COLUMN_DEFINITION
/
DROP TABLE IF EXISTS GORDON.TABULAR_DATASET
/
DROP TABLE IF EXISTS GORDON.UI_TABULAR_DGM
/
DROP TABLE IF EXISTS GORDON.UNITARY_PROPERTY_DEFINITION
/
DROP TABLE IF EXISTS GORDON.UI_UNITARY_DGM
/
DROP TABLE IF EXISTS GORDON.UNITARY_VALUE
/
DROP TABLE IF EXISTS GORDON.AUTHENTICATION_USER
/
CREATE TABLE GORDON.EXCHANGE(ID INT AUTO_INCREMENT(1,1) PRIMARY KEY, VERSION INT, CODE VARCHAR(8), NAME VARCHAR(40),
                             ACTIVE BOOLEAN)
/
CREATE TABLE GORDON.STOCK(ID BIGINT AUTO_INCREMENT(1,1) PRIMARY KEY, VERSION INT, EXCHANGE_ID INT,CODE VARCHAR(30), NAME VARCHAR(50),
                            ACTIVE BOOLEAN,LIST_DATE DATE,LAST_TRADE_DATE DATE,
                            FOREIGN KEY(EXCHANGE_ID) REFERENCES EXCHANGE(ID))
/
ALTER TABLE GORDON.STOCK ADD CONSTRAINT UNIQUE_STOCK_CODE UNIQUE(CODE)
/
ALTER TABLE GORDON.STOCK ADD CONSTRAINT UNIQUE_STOCK_NAME UNIQUE(NAME)
/
CREATE TABLE GORDON.UI_TAB(ID INT AUTO_INCREMENT(1,1) PRIMARY KEY, VERSION INT, NAME VARCHAR(50), TAB_ORDER INT, ACTIVE BOOLEAN)
/
ALTER TABLE GORDON.UI_TAB ADD CONSTRAINT UNIQUE_TAB_NAME UNIQUE(NAME)
/
CREATE TABLE GORDON.UI_GROUP(ID INT AUTO_INCREMENT(1,1) PRIMARY KEY, VERSION INT, NAME VARCHAR(50), TAB_ID INT, ACTIVE BOOLEAN,
                             FOREIGN KEY(TAB_ID) REFERENCES UI_TAB(ID))
/
ALTER TABLE GORDON.UI_GROUP ADD CONSTRAINT UNIQUE_UI_GROUP_NAME UNIQUE(NAME)
/
CREATE TABLE GORDON.STOCK_DAY_TRADE(ID BIGINT AUTO_INCREMENT(1,1) PRIMARY KEY, STOCK_ID BIGINT, "DATE" DATE, OPEN_PRICE DOUBLE, 
                                    HIGH_PRICE DOUBLE, LOW_PRICE DOUBLE, CLOSE_PRICE DOUBLE, VOLUME BIGINT,
                                    FOREIGN KEY(STOCK_ID) REFERENCES STOCK(ID))
/
CREATE TABLE GORDON.TABULAR_DATASET_DEFINITION(ID INT AUTO_INCREMENT(1,1) PRIMARY KEY, VERSION INT, NAME VARCHAR(30),
                   DESCRIPTION VARCHAR(100))
/
ALTER TABLE GORDON.TABULAR_DATASET_DEFINITION ADD CONSTRAINT UNIQUE_TAB_DATASET_DEF_NAME UNIQUE(NAME)
/
CREATE TABLE GORDON.COLUMN_DEFINITION(ID INT AUTO_INCREMENT(1,1) PRIMARY KEY, VERSION INT, DATASET_ID INT, COLUMN_ORDER INT, NAME VARCHAR(30),
                                      DESCRIPTION VARCHAR(100), "TYPE" VARCHAR(30),
                                      FOREIGN KEY(DATASET_ID) REFERENCES TABULAR_DATASET_DEFINITION(ID))
/
CREATE TABLE GORDON.TABULAR_DATASET(ID BIGINT AUTO_INCREMENT(1,1) PRIMARY KEY, VERSION INT, ENTITY_ID BIGINT, COLUMN_DEFINITION_ID BIGINT,
                     ROW_INDEX BIGINT, REAL_NUMBER_VALUE DECIMAL, INTEGER_VALUE INT, LONG_VALUE BIGINT, DATETIME_VALUE TIMESTAMP, DATE_VALUE DATE, 
                     STRING_VALUE VARCHAR(100), ENUM_VALUE VARCHAR(30), ENUMS VARCHAR(200),
                     FOREIGN KEY(ENTITY_ID) REFERENCES STOCK(ID),
                     FOREIGN KEY(COLUMN_DEFINITION_ID) REFERENCES COLUMN_DEFINITION(ID))
/
CREATE TABLE GORDON.UI_TABULAR_DGM(ID INT AUTO_INCREMENT(1,1) PRIMARY KEY, VERSION INT, "ORDER" INT, DATASET_ID INT, GROUP_ID INT,
                     VISUALISATION_TYPE VARCHAR(30),
                     FOREIGN KEY(DATASET_ID) REFERENCES TABULAR_DATASET_DEFINITION(ID),
                     FOREIGN KEY(GROUP_ID) REFERENCES UI_GROUP(ID))
/
CREATE TABLE GORDON.UNITARY_PROPERTY_DEFINITION(ID INT AUTO_INCREMENT(1,1) PRIMARY KEY, VERSION INT, NAME VARCHAR(30),
                                      DESCRIPTION VARCHAR(100), "TYPE" VARCHAR(30))
/
ALTER TABLE GORDON.UNITARY_PROPERTY_DEFINITION ADD CONSTRAINT UNIQUE_UNITARY_PROP_DEF_NAME UNIQUE(NAME)
/
CREATE TABLE GORDON.UI_UNITARY_DGM(ID INT AUTO_INCREMENT(1,1) PRIMARY KEY, VERSION INT, "ORDER" INT, DEFINITION_ID INT, GROUP_ID INT,
                     FOREIGN KEY(DEFINITION_ID) REFERENCES TABULAR_DATASET_DEFINITION(ID),
                     FOREIGN KEY(GROUP_ID) REFERENCES UI_GROUP(ID))
/
CREATE TABLE GORDON.UNITARY_VALUE(ID BIGINT AUTO_INCREMENT(1,1) PRIMARY KEY, VERSION INT, ENTITY_ID BIGINT, COLUMN_DEFINITION_ID BIGINT,
                     ROW_INDEX BIGINT, REAL_NUMBER_VALUE DECIMAL, INTEGER_VALUE INT, LONG_VALUE BIGINT, DATETIME_VALUE TIMESTAMP, DATE_VALUE DATE, 
                     STRING_VALUE VARCHAR(100), ENUM_VALUE VARCHAR(30), ENUMS VARCHAR(200),
                     FOREIGN KEY(ENTITY_ID) REFERENCES STOCK(ID),
                     FOREIGN KEY(COLUMN_DEFINITION_ID) REFERENCES UNITARY_PROPERTY_DEFINITION(ID))
/
CREATE TABLE GORDON.AUTHENTICATION_USER(ID INT AUTO_INCREMENT(1,1) PRIMARY KEY, VERSION INT, FIRST_NAME VARCHAR(30), LAST_NAME VARCHAR(30),
                                      USERNAME VARCHAR(30), PASSWORD VARCHAR(30))
/