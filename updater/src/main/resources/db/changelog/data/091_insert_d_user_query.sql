--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100091-1 splitStatements:false
INSERT INTO D_USER_QUERY (ID, USER_ID, SHELF, CODE, VAL) VALUES
 (1, 1, 'orderConditionsShelf', 'periodType', '1')
,(2, 1, 'orderConditionsShelf', 'periodExist', '1')
,(3, 1, 'orderConditionsShelf', 'periodMonth', '5')
,(4, 1, 'orderConditionsShelf', 'periodYear', '2024')
,(5, 1, 'orderConditionsShelf', 'num', '')
,(6, 1, 'orderConditionsShelf', 'trackCode', '')
,(7, 1, 'orderConditionsShelf', 'deliveryAddress', '')
,(8, 1, 'orderConditionsShelf', 'product', '0')
,(9, 1, 'orderConditionsShelf', 'customerConditions.person.phoneNumber', '')
,(10, 1, 'orderConditionsShelf', 'customerConditions.person.email', '')
,(11, 1, 'orderConditionsShelf', 'customerConditions.person.lastName', '')
,(12, 1, 'orderConditionsShelf', 'customerConditions.company.inn', '')
,(13, 1, 'orderConditionsShelf', 'customerConditions.company.shortName', '')
,(14, 1, 'orderConditionsShelf', 'period.start', '01.05.2024')
,(15, 1, 'orderConditionsShelf', 'period.end', '31.05.2024')
,(16, 1, 'orderConditionsShelf', 'statuses', '')
,(17, 1, 'orderConditionsShelf', 'orderTypes', '')
,(18, 1, 'orderConditionsShelf', 'deliveryTypes', '')
,(19, 1, 'orderConditionsShelf', 'customerTypes', '')
,(20, 1, 'orderConditionsShelf', 'paymentTypes', '')
,(21, 1, 'orderConditionsShelf', 'advertTypes', '')
;
