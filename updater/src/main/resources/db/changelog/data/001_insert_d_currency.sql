--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100001-1
INSERT INTO D_CURRENCIES(ID, CODE, NAME, AMOUNT_SCALE, REC_STATUS, CR_DT, UPD_DT) VALUES
 (1, 'MUR', 'Mauritius rupee', 2, 'A', localtimestamp, localtimestamp)
,(2, 'USD', 'US dollar', 2, 'A', localtimestamp, localtimestamp)
,(3, 'EUR', 'Euro', 2, 'A', localtimestamp, localtimestamp);