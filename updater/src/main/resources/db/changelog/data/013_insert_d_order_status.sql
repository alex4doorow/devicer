--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100013-1
INSERT INTO D_ORDER_STATUS (ID, ANNOTATION, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
 (1, 'заявка', 'A', 1, localtimestamp, localtimestamp)
,(2, 'подтвержден', 'A', 1, localtimestamp, localtimestamp)
,(3, 'ожидаем оплату', 'A', 1, localtimestamp, localtimestamp)
,(4, 'оплата получена', 'A', 1, localtimestamp, localtimestamp)
,(5, 'доставляется', 'A', 1, localtimestamp, localtimestamp)
,(7, 'прибыл', 'A', 1, localtimestamp, localtimestamp)
,(8, 'завершен', 'A', 1, localtimestamp, localtimestamp)
,(9, 'отказ от вручения, возврат', 'A', 1, localtimestamp, localtimestamp)
,(10, 'получен', 'A', 1, localtimestamp, localtimestamp)
,(11, 'отсутствуют документы', 'A', 1, localtimestamp, localtimestamp)
,(12, 'заканчивается срок хранения', 'A', 1, localtimestamp, localtimestamp)
,(13, 'отменен', 'A', 1, localtimestamp, localtimestamp)
,(15, 'возврат получен', 'A', 1, localtimestamp, localtimestamp)
,(16, 'утерян', 'A', 1, localtimestamp, localtimestamp)
,(21, 'удален', 'A', 1, localtimestamp, localtimestamp);