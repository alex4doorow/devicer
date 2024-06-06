--liquibase formatted sql

--changeset Aleksey Fedorov:100000000001A-1 splitStatements:false
CREATE TABLE D_ORDER_COMMENT_TYPE (
	ID BIGINT NOT NULL, /* ИДЕНТИФИКАТОР */
	ANNOTATION VARCHAR(255) NOT NULL /* ОПИСАНИЕ */,
	PRIMARY KEY(ID)
);