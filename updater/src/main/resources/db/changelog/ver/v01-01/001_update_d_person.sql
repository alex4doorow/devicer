--liquibase formatted sql

--changeset Aleksey Fedorov:10001
alter table D_PERSON add column INFO VARCHAR(250);