--liquibase formatted sql

--changeset Aleksey Fedorov:1000000000023-1 splitStatements:false
CREATE TABLE BP_CUSTOMER_ADDRESS (
	ADDRESS_ID BIGINT NOT NULL,
    CUSTOMER_ID BIGINT NOT NULL,
    CONSTRAINT BP_CUSTOMER_ADDRESS_UQ UNIQUE (CUSTOMER_ID, ADDRESS_ID)
);

COMMENT ON TABLE BP_CUSTOMER_ADDRESS IS 'Customers & addresses';
COMMENT ON COLUMN BP_CUSTOMER_ADDRESS.ADDRESS_ID IS 'address id';
COMMENT ON COLUMN BP_CUSTOMER_ADDRESS.CUSTOMER_ID IS 'customer id';

-- FOREIGN KEY
ALTER TABLE BP_CUSTOMER_ADDRESS
    ADD CONSTRAINT FK_BP_CUSTOMER_ADDRESS_ADDRESS_ID FOREIGN KEY (ADDRESS_ID)
    REFERENCES BP_ADDRESS (ID);

ALTER TABLE BP_CUSTOMER_ADDRESS
    ADD CONSTRAINT FK_BP_CUSTOMER_ADDRESS_CUSTOMER_ID FOREIGN KEY (CUSTOMER_ID)
    REFERENCES BP_CUSTOMER (ID);

-- INDEX
CREATE INDEX BP_CUSTOMER_ADDRESS_CUSTOMER_ID_INDEX ON BP_CUSTOMER_ADDRESS (CUSTOMER_ID);