--liquibase formatted sql

--changeset Aleksey Fedorov:1000000000001-1 splitStatements:false
CREATE TABLE IF NOT EXISTS D_CDEK_PVZ (
    ID BIGINT PRIMARY KEY NOT NULL,
    CODE VARCHAR(30) NOT NULL /* КОД ПВЗ */,
    CITY VARCHAR(255) NOT NULL /* ГОРОД */,
    ADDRESS VARCHAR(255) NOT NULL /* АДРЕС */,
    PHONES VARCHAR(255) NULL /* ТЕЛЕФОНЫ */,
    SCHEDULE_WORK VARCHAR(255) NULL /* РАСПИСАНИЕ РАБОТЫ */,
    POST_CODE VARCHAR(10) NULL /* ИНДЕКС */,
    CONSTRAINT D_CDEK_PVZ_UQ UNIQUE (CODE)
);