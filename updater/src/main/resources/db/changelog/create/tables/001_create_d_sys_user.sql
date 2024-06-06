--liquibase formatted sql

--changeset Aleksey Fedorov:1000000000001-1 splitStatements:false
CREATE TABLE IF NOT EXISTS D_SYS_USER (
    ID BIGINT PRIMARY KEY NOT NULL,
    NAME VARCHAR(64) NOT NULL,
    PASSWORD VARCHAR(100) NOT NULL,
    EMAIL VARCHAR(100) NOT NULL,
    ENABLED INT NOT NULL DEFAULT 1,
    LAST_LOGIN TIMESTAMP NULL,
    CONSTRAINT D_SYS_USER_UQ UNIQUE (NAME)
);

CREATE UNIQUE INDEX IF NOT EXISTS D_SYS_USER_NAME_UNIQ ON D_SYS_USER(NAME);

COMMENT ON TABLE D_SYS_USER IS 'Users';
COMMENT ON COLUMN D_SYS_USER.ID IS 'primary key';
COMMENT ON COLUMN D_SYS_USER.NAME IS 'user name';
COMMENT ON COLUMN D_SYS_USER.PASSWORD IS 'user password';
COMMENT ON COLUMN D_SYS_USER.EMAIL IS 'email';
COMMENT ON COLUMN D_SYS_USER.ENABLED IS 'enabled';
COMMENT ON COLUMN D_SYS_USER.LAST_LOGIN IS 'last login';