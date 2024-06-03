--liquibase formatted sql

--changeset Aleksey Fedorov:1000000000002-1 splitStatements:false
CREATE TABLE IF NOT EXISTS SR_SYS_USER_ROLE (
	ID BIGINT NOT NULL,
	USER_ID BIGINT NOT NULL,
	ROLE_ID BIGINT NOT NULL,
    PRIMARY KEY (ID)
);
