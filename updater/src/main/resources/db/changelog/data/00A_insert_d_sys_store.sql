--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100007-1
INSERT INTO D_SYS_STORE (ID, CODE, ANNOTATION, URL, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
(1, 'priborMaster', 'priborMaster store', 'https://pribormaster.ru', 'A', 1, localtimestamp, localtimestamp);