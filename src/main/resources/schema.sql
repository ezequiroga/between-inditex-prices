/*Table of Brands*/
CREATE TABLE IF NOT EXISTS INDITEX.BRAND (
    ID INT NOT NULL,
    DESCRIPTION VARCHAR(100) NOT NULL,
    PRIMARY KEY (ID)
);
GRANT INSERT, SELECT, UPDATE, DELETE ON INDITEX.BRAND TO SA;

/*Table of prices*/
/*ID = PRICE_LIST*/
CREATE TABLE IF NOT EXISTS INDITEX.PRICES (
    ID INT NOT NULL,
    BRAND_ID INT NOT NULL,
    START_DATE BIGINT NOT NULL,
    END_DATE BIGINT NOT NULL,
    PRODUCT_ID INT NOT NULL,
    PRIORITY SMALLINT NOT NULL,
    PRICE DECIMAL(6,2) NOT NULL,
    CURR VARCHAR(3) NOT NULL,
    PRIMARY KEY (ID),
    CONSTRAINT FK_BRAND FOREIGN KEY(BRAND_ID) REFERENCES INDITEX.BRAND(ID)
);
GRANT INSERT, SELECT, UPDATE, DELETE ON INDITEX.PRICES TO SA;