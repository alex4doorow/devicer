--liquibase formatted sql

--changeset Aleksey Fedorov:1000000000013-1 splitStatements:false
CREATE TABLE IF NOT EXISTS D_ORDER_STATUS (
  ID BIGINT PRIMARY KEY NOT NULL,
  ANNOTATION VARCHAR(255) NOT NULL,
  REC_STATUS CHAR(1) NOT NULL,
  USER_ADDED BIGINT DEFAULT 1 NOT NULL,
  DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATE_MODIFIED TIMESTAMP NULL
);

COMMENT ON TABLE D_ORDER_STATUS IS 'Order statuses';
COMMENT ON COLUMN D_ORDER_STATUS.ID IS 'primary key';
COMMENT ON COLUMN D_ORDER_STATUS.ANNOTATION IS 'annotation';
COMMENT ON COLUMN D_ORDER_STATUS.REC_STATUS IS 'A – active, D - deleted';
COMMENT ON COLUMN D_ORDER_STATUS.USER_ADDED IS 'modified user';
COMMENT ON COLUMN D_ORDER_STATUS.DATE_ADDED IS 'creation date';
COMMENT ON COLUMN D_ORDER_STATUS.DATE_MODIFIED IS 'modification date';

--CHECK CONSTRAINTS
ALTER TABLE D_ORDER_STATUS ADD CONSTRAINT CC_D_ORDER_STATUS_REC_STATUS CHECK (REC_STATUS IN ('A', 'D'));