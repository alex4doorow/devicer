--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100017-1 splitStatements:false
INSERT INTO D_ADDRESS_TYPE (ID, ANNOTATION, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
 (1, 'главный', 'A', 1, localtimestamp, localtimestamp)
,(2, 'дополнительный', 'A', 1, localtimestamp, localtimestamp)
,(3, 'пункт выдачи', 'A', 1, localtimestamp, localtimestamp)
,(4, 'терминал', 'A', 1, localtimestamp, localtimestamp)
,(5, 'самовывоз', 'A', 1, localtimestamp, localtimestamp);
