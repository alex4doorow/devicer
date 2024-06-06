--liquibase formatted sql

--changeset Aleksey Fedorov:1000000100002-1 splitStatements:false
INSERT INTO D_SYS_ROLE(ID, NAME) VALUES
 (1, 'ROLE_USER')
,(2, 'ROLE_ADMIN');

INSERT INTO D_SYS_USER_ROLE(ID, USER_ID, ROLE_ID) VALUES
 (1, 1, 1)
,(2, 1, 2);
