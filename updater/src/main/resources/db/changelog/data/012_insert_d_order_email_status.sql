--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100012-1
INSERT INTO D_ORDER_EMAIL_STATUS (ID, ANNOTATION, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
 (0, 'неопределен', 'A', 1, localtimestamp, localtimestamp)
,(1, 'запрос отзыва', 'A', 1, localtimestamp, localtimestamp)
,(2, 'запрос на актуальность', 'A', 1, localtimestamp, localtimestamp);
