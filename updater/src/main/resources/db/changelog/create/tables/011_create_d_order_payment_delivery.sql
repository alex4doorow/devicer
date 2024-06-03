--liquibase formatted sql

--changeset Aleksey Fedorov:1000000000001-1 splitStatements:false
CREATE TABLE D_ORDER_PAYMENT_DELIVERY (
	ID BIGINT NOT NULL, /* ИДЕНТИФИКАТОР */
	ANNOTATION VARCHAR(255) NOT NULL /* ОПИСАНИЕ */,
	PRIMARY KEY(ID)
);