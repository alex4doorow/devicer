CREATE TABLE IF NOT EXISTS D_USER_QUERY (
	ID BIGINT PRIMARY KEY NOT NULL,
    USER_ID BIGINT NOT NULL,
    SHELF VARCHAR(30) NOT NULL,
	CODE VARCHAR(255) NOT NULL,
	VAL VARCHAR(255) NULL,
    CONSTRAINT D_USER_QUERY_UQ UNIQUE (USER_ID, SHELF, CODE)
);

CREATE TABLE IF NOT EXISTS D_SYS_CONFIG (
    ID BIGINT PRIMARY KEY NOT NULL,
	CODE VARCHAR(255) NOT NULL,
	VAL VARCHAR(255) NOT NULL,
	ANNOTATION VARCHAR(255) NOT NULL,
    REC_STATUS CHAR(1) NOT NULL,
    USER_ADDED BIGINT DEFAULT 1 NOT NULL,
	DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS D_SYS_USER (
    ID BIGINT PRIMARY KEY NOT NULL,
    NAME VARCHAR(64) NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    ENABLED INT NOT NULL DEFAULT 1,
    LAST_LOGIN TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS D_SYS_ROLE (
    ID BIGINT PRIMARY KEY NOT NULL,
    NAME VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS D_SYS_USER_ROLE (
	ID BIGINT PRIMARY KEY NOT NULL,
	USER_ID BIGINT NOT NULL,
	ROLE_ID BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS D_SYS_STORE (
  ID BIGINT PRIMARY KEY NOT NULL,
  CODE VARCHAR(255) NOT NULL,
  ANNOTATION VARCHAR(255) NOT NULL,
  URL VARCHAR(255) NOT NULL,
  REC_STATUS CHAR(1) NOT NULL,
  USER_ADDED BIGINT DEFAULT 1 NOT NULL,
  DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS D_CATEGORY_PRODUCT (
  ID BIGINT PRIMARY KEY NOT NULL,
  TYPE_GROUP VARCHAR(255) NOT NULL,
  ANNOTATION VARCHAR(255) NOT NULL,
  REC_STATUS CHAR(1) NOT NULL,
  USER_ADDED BIGINT DEFAULT 1 NOT NULL,
  DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS D_ORDER_ADVERT (
  ID BIGINT PRIMARY KEY NOT NULL,
  ANNOTATION VARCHAR(255) NOT NULL,
  REC_STATUS CHAR(1) NOT NULL,
  USER_ADDED BIGINT DEFAULT 1 NOT NULL,
  DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS D_ORDER_PAYMENT (
  ID BIGINT PRIMARY KEY NOT NULL,
  ANNOTATION VARCHAR(255) NOT NULL,
  REC_STATUS CHAR(1) NOT NULL,
  USER_ADDED BIGINT DEFAULT 1 NOT NULL,
  DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS D_ORDER_PAYMENT_DELIVERY (
  ID BIGINT PRIMARY KEY NOT NULL,
  ANNOTATION VARCHAR(255) NOT NULL,
  REC_STATUS CHAR(1) NOT NULL,
  USER_ADDED BIGINT DEFAULT 1 NOT NULL,
  DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS D_ORDER_SOURCE (
  ID BIGINT PRIMARY KEY NOT NULL,
  ANNOTATION VARCHAR(255) NOT NULL,
  REC_STATUS CHAR(1) NOT NULL,
  USER_ADDED BIGINT DEFAULT 1 NOT NULL,
  DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS D_ORDER_TYPE (
  ID BIGINT PRIMARY KEY NOT NULL,
  ANNOTATION VARCHAR(255) NOT NULL,
  REC_STATUS CHAR(1) NOT NULL,
  USER_ADDED BIGINT DEFAULT 1 NOT NULL,
  DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS D_ORDER_EMAIL_STATUS (
  ID BIGINT PRIMARY KEY NOT NULL,
  ANNOTATION VARCHAR(255) NOT NULL,
  REC_STATUS CHAR(1) NOT NULL,
  USER_ADDED BIGINT DEFAULT 1 NOT NULL,
  DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS D_ORDER_STATUS (
  ID BIGINT PRIMARY KEY NOT NULL,
  ANNOTATION VARCHAR(255) NOT NULL,
  REC_STATUS CHAR(1) NOT NULL,
  USER_ADDED BIGINT DEFAULT 1 NOT NULL,
  DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS D_ADDRESS_TYPE (
  ID INT PRIMARY KEY NOT NULL,
  ANNOTATION VARCHAR(255) NOT NULL,
  REC_STATUS CHAR(1) NOT NULL,
  USER_ADDED BIGINT DEFAULT 1 NOT NULL,
  DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS BP_PERSON (
	ID BIGINT PRIMARY KEY NOT NULL,
    FIRST_NAME VARCHAR(255) NOT NULL,
    LAST_NAME VARCHAR(255) NULL,
    MIDDLE_NAME VARCHAR(255) NULL,
    COUNTRY_ISO_CODE_2 CHAR(2) DEFAULT 'RU' NOT NULL,
	PHONE_NUMBER VARCHAR(30) NOT NULL,
    EMAIL VARCHAR(255) NULL,
    REC_STATUS CHAR(1) NOT NULL,
    USER_ADDED BIGINT DEFAULT 1 NOT NULL,
    DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    DATE_MODIFIED TIMESTAMP NULL,
    CONSTRAINT D_PERSON_UQ UNIQUE (COUNTRY_ISO_CODE_2, PHONE_NUMBER)
);

CREATE TABLE IF NOT EXISTS BP_ADDRESS (
  ID BIGINT PRIMARY KEY NOT NULL,
  TYPE INT DEFAULT 1 NOT NULL, /* ТИП */
  COUNTRY_ISO_CODE_2 CHAR(2) DEFAULT 'RU' NOT NULL,
  POST_CODE VARCHAR(6) NULL,
  STREET VARCHAR(255) NULL,
  HOUSE VARCHAR(255) NULL,
  FLAT VARCHAR(255) NULL,
  ADDRESS VARCHAR(255) NULL,
  ANNOTATION VARCHAR(255) NULL,
  REC_STATUS CHAR(1) NOT NULL,
  USER_ADDED BIGINT DEFAULT 1 NOT NULL,
  DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS BP_CUSTOMER (
	ID BIGINT PRIMARY KEY NOT NULL,
    TYPE INT DEFAULT 1 NOT NULL,
	PERSON_ID BIGINT NULL,
    STATUS INT DEFAULT 1 NOT NULL,
    REC_STATUS CHAR(1) NOT NULL,
    USER_ADDED BIGINT DEFAULT 1 NOT NULL,
    DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE BP_CUSTOMER_ADDRESS (
	ADDRESS_ID BIGINT NOT NULL,
    CUSTOMER_ID BIGINT NOT NULL,
    CONSTRAINT BP_CUSTOMER_ADDRESS_UQ UNIQUE (CUSTOMER_ID, ADDRESS_ID)
);

CREATE TABLE BP_CUSTOMER_COMPANY (
	ID BIGINT PRIMARY KEY NOT NULL,
    CUSTOMER_ID BIGINT NOT NULL,
    COUNTRY_ISO_CODE_2 CHAR(2) DEFAULT 'RU' NOT NULL,
    INN VARCHAR(30) NULL,
    SHORT_NAME VARCHAR(255) NOT NULL,
    LONG_NAME VARCHAR(255) NULL,
    REC_STATUS CHAR(1) NOT NULL,
    USER_ADDED BIGINT DEFAULT 1 NOT NULL,
    DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    DATE_MODIFIED TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS BP_ORDER (
	ID BIGINT PRIMARY KEY NOT NULL,
    ORDER_NUM BIGINT NOT NULL,
    ORDER_DATE TIMESTAMP NOT NULL,
    ORDER_TYPE INT DEFAULT 1 NOT NULL,
    SOURCE_TYPE INT DEFAULT 4 NOT NULL,
	ADVERT_TYPE INT DEFAULT 1 NOT NULL,
    PAYMENT_TYPE INT DEFAULT 5 NOT NULL,
    STORE_ID INT DEFAULT 1 NOT NULL,
    CATEGORY_PRODUCT_ID BIGINT DEFAULT 0 NOT NULL,
    CUSTOMER_ID BIGINT NOT NULL,
    AMOUNT_TOTAL NUMERIC(15,4) DEFAULT 0 NOT NULL,
	AMOUNT_TOTAL_WITH_DELIVERY NUMERIC(15,4) DEFAULT 0 NOT NULL,
	AMOUNT_BILL NUMERIC(15,4) DEFAULT 0 NOT NULL,
	AMOUNT_SUPPLIER NUMERIC(15,4) DEFAULT 0 NOT NULL,
	AMOUNT_MARGIN NUMERIC(15,4) DEFAULT 0 NOT NULL,
	AMOUNT_POSTPAY NUMERIC(15,4) DEFAULT 0 NOT NULL,
	ANNOTATION VARCHAR(255) NULL,
    STATUS INT DEFAULT 1 NOT NULL,
    STATUS_EMAIL INT DEFAULT 0 NOT NULL,
    OFFER_COUNT_DAY INT DEFAULT 0 NOT NULL,
    REC_STATUS CHAR(1) NOT NULL,
    USER_ADDED BIGINT DEFAULT 1 NOT NULL,
	DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    DATE_MODIFIED TIMESTAMP NULL,
    CONSTRAINT BP_ORDER_UQ UNIQUE (ORDER_NUM)
);
