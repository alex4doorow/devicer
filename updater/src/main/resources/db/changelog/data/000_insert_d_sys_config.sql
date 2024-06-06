--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100000-1 splitStatements:false
INSERT INTO D_SYS_CONFIG(ID, CODE, VAL, ANNOTATION, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
 (1, 'company_j_name', 'ИП Федоров Алексей Анатольевич', 'наименование организации', 'A', 1, localtimestamp, localtimestamp)
,(2, 'company_j_inn', '771872248140', 'ИНН организации', 'A', 1, localtimestamp, localtimestamp)
,(3, 'company_j_address', '107241, г.Москва, Щелковское Шоссе д.29', 'адрес организации', 'A', 1, localtimestamp, localtimestamp)
,(4, 'company_shop_address', '107241, г.Москва, Щелковское Шоссе д.29', 'точка самовывоза', 'A', 1, localtimestamp, localtimestamp)
,(5, 'company_shop_name_short', 'ПРИБОРМАСТЕР', 'наименование интернет-магазина', 'A', 1, localtimestamp, localtimestamp)
,(6, 'company_shop_name_long', 'ИНТЕРНЕТ-КОМПАНИЯ ПРИБОРМАСТЕР', 'наименование интернет-магазина', 'A', 1, localtimestamp, localtimestamp)
,(7, 'order_no_current', '4285', 'текущий номер заказа', 'A', 1, localtimestamp, localtimestamp)
,(8, 'ozon_enabled', 'false', 'озон включен (да/нет)', 'A', 1, localtimestamp, localtimestamp);