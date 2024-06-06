--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100010-1
INSERT INTO D_ORDER_PAYMENT (ID, ANNOTATION, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
 (1, 'предоплата', 'A', 1, localtimestamp, localtimestamp)
,(2, 'постоплата', 'A', 1, localtimestamp, localtimestamp)
,(5, 'наличными курьеру', 'A', 1, localtimestamp, localtimestamp)
,(6, 'банковской картой', 'A', 1, localtimestamp, localtimestamp)
,(7, 'Apple Pay', 'A', 1, localtimestamp, localtimestamp)
,(8, 'Google Pay', 'A', 1, localtimestamp, localtimestamp)
,(9, 'в кредит', 'A', 1, localtimestamp, localtimestamp);
