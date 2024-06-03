--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100009-1
INSERT INTO D_CONTACT_TYPE (ID, ANNOTATION, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
 (1, 'главный', 'A', 1, localtimestamp, localtimestamp)
,(2, 'дополнительный', 'A', 1, localtimestamp, localtimestamp);
