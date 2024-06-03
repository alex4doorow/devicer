--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100018-1 splitStatements:false
INSERT INTO D_COUNTRY (ID, NAME, ISO_CODE_2, ISO_CODE_3, ADDRESS_FORMAT, POSTCODE_REQUIRED, STATUS, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
(1, 'Russia', 'RU', 'RUR', '*', 0, 1, 'A', 1, localtimestamp, localtimestamp);
