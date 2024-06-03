--liquibase formatted sql

--changeset Aleksey Fedorov:1000000000002-1 splitStatements:false
CREATE TABLE IF NOT EXISTS SR_SYS_ROLE (
	ID BIGINT NOT NULL,
	NAME VARCHAR(50) NOT NULL,
    PRIMARY KEY (ID)
);