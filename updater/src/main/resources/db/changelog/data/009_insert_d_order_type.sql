--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100009-1
INSERT INTO D_ORDER_TYPE (ID, ANNOTATION, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
 (1, 'заказ', 'A', 1, localtimestamp, localtimestamp)
,(2, 'счет на оплату', 'A', 1, localtimestamp, localtimestamp)
,(3, 'коммерческое предложение', 'A', 1, localtimestamp, localtimestamp)
,(4, 'консультация', 'A', 1, localtimestamp, localtimestamp)
,(11, 'замена', 'A', 1, localtimestamp, localtimestamp)
,(12, 'возврат', 'A', 1, localtimestamp, localtimestamp)
,(13, 'подарок', 'A', 1, localtimestamp, localtimestamp)
,(14, 'тест-драйв', 'A', 1, localtimestamp, localtimestamp)
,(15, 'ремонт', 'A', 1, localtimestamp, localtimestamp);
