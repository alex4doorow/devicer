--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100002-1 splitStatements:false
INSERT INTO D_SYS_USER(ID, NAME, PASSWORD, EMAIL, ENABLED, LAST_LOGIN) VALUES
 (1, 'al', '1', 'al@gmail.com', 1, localtimestamp);