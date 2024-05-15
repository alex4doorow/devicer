--liquibase formatted sql

--changeset Aleksey Fedorov:1000001000001-1 splitStatements:false
INSERT INTO D_SYS_USER(ID, NAME, PASSWORD, EMAIL, ENABLED, LAST_LOGIN) VALUES
 (1, 'al', '1', 'al@gmail.com', 1, localtimestamp);