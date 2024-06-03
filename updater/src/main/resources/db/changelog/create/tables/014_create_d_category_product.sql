--liquibase formatted sql

--changeset Aleksey Fedorov:1000000000001-1 splitStatements:false
CREATE TABLE SR_WIKI_CATEGORY_PRODUCT (
	ID BIGINT NOT NULL, /* ИДЕНТИФИКАТОР */
	TYPE_GROUP VARCHAR(255) NOT NULL, /* ГРУППА НИШ */
	ANNOTATION VARCHAR(255) NOT NULL /* ОПИСАНИЕ */,
	PRIMARY KEY(ID)
);