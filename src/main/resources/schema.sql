CREATE TABLE `client`
(
    `email`       VARCHAR(45)  NOT NULL,
    `firstname`   VARCHAR(45)  NOT NULL,
    `insertion`   VARCHAR(45) NULL,
    `lastname`    VARCHAR(45)  NOT NULL,
    `dateofbirth` DATE         NOT NULL,
    `BSN`         VARCHAR(45)  NOT NULL,
    `password`    VARCHAR(100) NOT NULL,
    `postalcode`  VARCHAR(10) NULL,
    `number`      INT NULL,
    `IBAN`        VARCHAR(45) NULL,
    PRIMARY KEY (`email`)
);
-- CONSTRAINT `verzinzelf1`
-- FOREIGN KEY (`postalcode` , `number`)
-- REFERENCES `bigbangk`.`address` (`postalcode` , `number`)
-- CONSTRAINT `verzinzelf4`
-- FOREIGN KEY (`IBAN`)
-- REFERENCES `bigbangk`.`wallet` (`IBAN`)

CREATE TABLE `bank` (
    `code` VARCHAR(45) NOT NULL,
    `name` VARCHAR(64) NOT NULL,
    `IBAN` VARCHAR(45) NULL,
    `startingcapital` DECIMAL(10,3) NULL,
    `transactioncosts` DECIMAL(10,3) NULL,
    PRIMARY KEY (`code`)
);

CREATE TABLE `PriceHistory`
(
    `dateTime` TIMESTAMP   NOT NULL,
    `code`     VARCHAR(45) NOT NULL,
    `price`    DOUBLE      NOT NULL,
    PRIMARY KEY (`code`, `dateTime`)
);

CREATE TABLE `Asset`
(
    `name` VARCHAR(45) NOT NULL,
    `code` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`code`)
);

CREATE TABLE `wallet`
(
    `IBAN` VARCHAR(45) NOT NULL,
    `balance` DECIMAL(25,2) NOT NULL,
    PRIMARY KEY (`IBAN`)
);