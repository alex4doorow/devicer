--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100011-1
INSERT INTO D_ORDER_PAYMENT_DELIVERY (ID, ANNOTATION, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
 (1, 'оплачивает доставку покупатель', 'A', 1, localtimestamp, localtimestamp)
,(2, 'оплачивает доставку продавец', 'A', 1, localtimestamp, localtimestamp);
