--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100008-1
INSERT INTO D_ORDER_SOURCE (ID, ANNOTATION, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
 (1, 'звонок', 'A', 1, localtimestamp, localtimestamp)
,(2, 'чат', 'A', 1, localtimestamp, localtimestamp)
,(3, 'письмо', 'A', 1, localtimestamp, localtimestamp)
,(4, 'лид', 'A', 1, localtimestamp, localtimestamp)
,(5, 'быстрый заказ', 'A', 1, localtimestamp, localtimestamp)
,(6, 'обратный звонок', 'A', 1, localtimestamp, localtimestamp)
,(8, 'тендер', 'A', 1, localtimestamp, localtimestamp);