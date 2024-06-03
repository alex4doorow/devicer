--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100016-1
INSERT INTO D_CUSTOMER_TYPE (ID, ANNOTATION, REC_STATUS, USER_ADDED, DATE_ADDED, DATE_MODIFIED) VALUES
 (1, 'физическое лицо', 'A', 1, localtimestamp, localtimestamp)
,(2, 'юридическое лицо', 'A', 1, localtimestamp, localtimestamp)
,(3, 'индивидуальный предприниматель', 'A', 1, localtimestamp, localtimestamp)
,(4, 'нерезидент, физическое лицо', 'A', 1, localtimestamp, localtimestamp)
,(5, 'нерезидент, юридическое лицо', 'A', 1, localtimestamp, localtimestamp)
,(6, 'неопределен', 'A', 1, localtimestamp, localtimestamp);
