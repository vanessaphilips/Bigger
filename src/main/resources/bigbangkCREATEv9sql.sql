-- MySQL Script generated by MySQL Workbench
-- Thu Dec 16 14:46:28 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bigbangk
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bigbangk
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bigbangk` DEFAULT CHARACTER SET utf8 ;
USE `bigbangk` ;

-- -----------------------------------------------------
-- Table `bigbangk`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bigbangk`.`address` (
  `postalcode` VARCHAR(10) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `number` INT NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`postalcode`, `number`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bigbangk`.`asset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bigbangk`.`asset` (
  `code` VARCHAR(5) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bigbangk`.`wallet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bigbangk`.`wallet` (
  `IBAN` VARCHAR(45) NOT NULL,
  `balance` DECIMAL(25,2) NOT NULL,
  PRIMARY KEY (`IBAN`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bigbangk`.`bank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bigbangk`.`bank` (
  `code` VARCHAR(45) NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  `IBAN` VARCHAR(45) NULL,
  `startingcapital` DECIMAL(10,3) NULL,
  `transactioncosts` DECIMAL(5,5) NULL,
  PRIMARY KEY (`code`),
  INDEX `verzinzelf6_idx` (`IBAN` ASC) VISIBLE,
  CONSTRAINT `verzinzelf6`
    FOREIGN KEY (`IBAN`)
    REFERENCES `bigbangk`.`wallet` (`IBAN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bigbangk`.`pricehistory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bigbangk`.`pricehistory` (
  `datetime` TIMESTAMP NOT NULL,
  `code` VARCHAR(5) NOT NULL,
  `price` DOUBLE NOT NULL,
  PRIMARY KEY (`datetime`, `code`),
  INDEX `fk_Price_Coin1_idx` (`code` ASC) VISIBLE,
  CONSTRAINT `fk_Price_Coin1`
    FOREIGN KEY (`code`)
    REFERENCES `bigbangk`.`asset` (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bigbangk`.`wallet_has_asset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bigbangk`.`wallet_has_asset` (
  `code` VARCHAR(5) NOT NULL,
  `amount` DECIMAL(40,30) NOT NULL,
  `IBAN` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`code`, `IBAN`),
  INDEX `fk_Coin_has_Wallet_Coin1_idx` (`code` ASC) VISIBLE,
  INDEX `verzinzelf3_idx` (`IBAN` ASC) VISIBLE,
  CONSTRAINT `fk_Coin_has_Wallet_Coin1`
    FOREIGN KEY (`code`)
    REFERENCES `bigbangk`.`asset` (`code`),
  CONSTRAINT `verzinzelf3`
    FOREIGN KEY (`IBAN`)
    REFERENCES `bigbangk`.`wallet` (`IBAN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bigbangk`.`client`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bigbangk`.`client` (
  `email` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `insertion` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `dateofbirth` DATE NOT NULL,
  `BSN` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `postalcode` VARCHAR(10) NULL,
  `number` INT NULL,
  `IBAN` VARCHAR(45) NULL,
  PRIMARY KEY (`email`),
  INDEX `verzinzelf1_idx` (`postalcode` ASC, `number` ASC) VISIBLE,
  INDEX `verzinzelf4_idx` (`IBAN` ASC) VISIBLE,
  CONSTRAINT `verzinzelf1`
    FOREIGN KEY (`postalcode` , `number`)
    REFERENCES `bigbangk`.`address` (`postalcode` , `number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `verzinzelf4`
    FOREIGN KEY (`IBAN`)
    REFERENCES `bigbangk`.`wallet` (`IBAN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bigbangk`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bigbangk`.`order` (
  `orderId` INT NOT NULL,
  `buyer` VARCHAR(45) NULL,
  `seller` VARCHAR(45) NULL,
  `code` VARCHAR(5) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `orderlimit` DOUBLE NULL,
  `amount` DOUBLE NULL,
  `date` TIMESTAMP NOT NULL,
  `fee` DOUBLE NULL,
  `amount` DOUBLE NULL,
  `totalprice` DOUBLE NULL,
  PRIMARY KEY (`orderId`),
  INDEX `verzinzelf2_idx` (`buyer` ASC) VISIBLE,
  INDEX `verzinzelf5_idx` (`seller` ASC) VISIBLE,
  INDEX `verzinzelf7_idx` (`code` ASC) VISIBLE,
  CONSTRAINT `verzinzelf2`
    FOREIGN KEY (`buyer`)
    REFERENCES `bigbangk`.`wallet` (`IBAN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `verzinzelf5`
    FOREIGN KEY (`seller`)
    REFERENCES `bigbangk`.`wallet` (`IBAN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `verzinzelf7`
    FOREIGN KEY (`code`)
    REFERENCES `bigbangk`.`asset` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

CREATE USER 'userBigbangk'@'localhost' IDENTIFIED BY 'userBigbangkPW';
GRANT ALL PRIVILEGES ON bigbangk . * TO 'userBigbangk' @'localhost';