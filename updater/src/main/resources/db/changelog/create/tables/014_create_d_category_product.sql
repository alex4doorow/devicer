--liquibase formatted sql

--changeset Aleksey Fedorov:1000000000014-1 splitStatements:false
CREATE TABLE IF NOT EXISTS D_CATEGORY_PRODUCT (
  ID BIGINT PRIMARY KEY NOT NULL,
  TYPE_GROUP VARCHAR(255) NOT NULL,
  ANNOTATION VARCHAR(255) NOT NULL,
  REC_STATUS CHAR(1) NOT NULL,
  USER_ADDED BIGINT DEFAULT 1 NOT NULL,
  DATE_ADDED TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
  DATE_MODIFIED TIMESTAMP NULL
);

COMMENT ON TABLE D_CATEGORY_PRODUCT IS 'Category products';
COMMENT ON COLUMN D_CATEGORY_PRODUCT.ID IS 'primary key';
COMMENT ON COLUMN D_CATEGORY_PRODUCT.TYPE_GROUP IS 'type group';
COMMENT ON COLUMN D_CATEGORY_PRODUCT.ANNOTATION IS 'annotation';
COMMENT ON COLUMN D_CATEGORY_PRODUCT.REC_STATUS IS 'A – active, D - deleted';
COMMENT ON COLUMN D_CATEGORY_PRODUCT.USER_ADDED IS 'modified user';
COMMENT ON COLUMN D_CATEGORY_PRODUCT.DATE_ADDED IS 'creation date';
COMMENT ON COLUMN D_CATEGORY_PRODUCT.DATE_MODIFIED IS 'modification date';

--CHECK CONSTRAINTS
ALTER TABLE D_CATEGORY_PRODUCT ADD CONSTRAINT CC_D_CATEGORY_PRODUCT_REC_STATUS CHECK (REC_STATUS IN ('A', 'D'));