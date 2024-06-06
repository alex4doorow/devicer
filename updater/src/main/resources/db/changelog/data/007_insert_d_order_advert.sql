--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100007-1
INSERT INTO D_ORDER_ADVERT (ID, ANNOTATION, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
 (1, 'реклама', 'A', 1, localtimestamp, localtimestamp)
,(2, 'поиск', 'A', 1, localtimestamp, localtimestamp)
,(3, 'youtube', 'A', 1, localtimestamp, localtimestamp)
,(4, 'сарафанное радио', 'A', 1, localtimestamp, localtimestamp)
,(5, 'повторное обращение', 'A', 1, localtimestamp, localtimestamp)
,(6, 'холодный звонок', 'A', 1, localtimestamp, localtimestamp)
,(8, 'тендер', 'A', 1, localtimestamp, localtimestamp);