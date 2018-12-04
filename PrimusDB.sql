SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `PrimusDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `PrimusDB` ;

-- -----------------------------------------------------
-- Table `PrimusDB`.`Orte`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PrimusDB`.`Orte` ;

CREATE  TABLE IF NOT EXISTS `PrimusDB`.`Orte` (
  `idOrte` INT NOT NULL AUTO_INCREMENT ,
  `PLZ` VARCHAR(5) NULL ,
  `Ort` INT NULL ,
  PRIMARY KEY (`idOrte`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PrimusDB`.`Shop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PrimusDB`.`Shop` ;

CREATE  TABLE IF NOT EXISTS `PrimusDB`.`Shop` (
  `idShop` INT NOT NULL AUTO_INCREMENT ,
  `Orte_idOrte` INT NOT NULL ,
  `Benutzername` VARCHAR(45) NULL ,
  `Passwort` VARCHAR(45) NULL ,
  `ShopName` VARCHAR(45) NULL ,
  `Strasse` VARCHAR(45) NULL ,
  PRIMARY KEY (`idShop`) ,
  INDEX `fk_Shop_Orte1_idx` (`Orte_idOrte` ASC) ,
  CONSTRAINT `fk_Shop_Orte1`
    FOREIGN KEY (`Orte_idOrte` )
    REFERENCES `PrimusDB`.`Orte` (`idOrte` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PrimusDB`.`Spedition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PrimusDB`.`Spedition` ;

CREATE  TABLE IF NOT EXISTS `PrimusDB`.`Spedition` (
  `idSpedition` INT NOT NULL AUTO_INCREMENT ,
  `Orte_idOrte` INT NOT NULL ,
  `Benutzername` VARCHAR(45) NULL ,
  `Passwort` VARCHAR(45) NULL ,
  `SpeditionsName` VARCHAR(45) NULL ,
  `Strasse` VARCHAR(45) NULL ,
  PRIMARY KEY (`idSpedition`) ,
  INDEX `fk_Spedition_Orte1_idx` (`Orte_idOrte` ASC) ,
  CONSTRAINT `fk_Spedition_Orte1`
    FOREIGN KEY (`Orte_idOrte` )
    REFERENCES `PrimusDB`.`Orte` (`idOrte` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PrimusDB`.`Kunde`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PrimusDB`.`Kunde` ;

CREATE  TABLE IF NOT EXISTS `PrimusDB`.`Kunde` (
  `idKunde` INT NOT NULL AUTO_INCREMENT ,
  `Orte_idOrte` INT NOT NULL ,
  `Name` VARCHAR(45) NULL ,
  `Nachname` VARCHAR(45) NULL ,
  `Strasse` VARCHAR(45) NULL ,
  PRIMARY KEY (`idKunde`) ,
  INDEX `fk_Kunde_Orte1_idx` (`Orte_idOrte` ASC) ,
  CONSTRAINT `fk_Kunde_Orte1`
    FOREIGN KEY (`Orte_idOrte` )
    REFERENCES `PrimusDB`.`Orte` (`idOrte` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PrimusDB`.`Pakete`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PrimusDB`.`Pakete` ;

CREATE  TABLE IF NOT EXISTS `PrimusDB`.`Pakete` (
  `idPakete` INT NOT NULL AUTO_INCREMENT ,
  `Kunde_idKunde` INT NOT NULL ,
  `Gewicht` VARCHAR(45) NULL ,
  `Groesse` VARCHAR(45) NULL ,
  `Versicherung` TINYINT(1) NULL ,
  PRIMARY KEY (`idPakete`) ,
  INDEX `fk_Pakete_Kunde1_idx` (`Kunde_idKunde` ASC) ,
  CONSTRAINT `fk_Pakete_Kunde1`
    FOREIGN KEY (`Kunde_idKunde` )
    REFERENCES `PrimusDB`.`Kunde` (`idKunde` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PrimusDB`.`ShopPaketZuweisung`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PrimusDB`.`ShopPaketZuweisung` ;

CREATE  TABLE IF NOT EXISTS `PrimusDB`.`ShopPaketZuweisung` (
  `idShopPaketZuweisung` INT NOT NULL AUTO_INCREMENT ,
  `Shop_idShop` INT NOT NULL ,
  `Shop_Central_idCentral` INT NOT NULL ,
  `Pakete_idPakete` INT NOT NULL ,
  PRIMARY KEY (`idShopPaketZuweisung`) ,
  INDEX `fk_ShopPaketZuweisung_Shop1_idx` (`Shop_idShop` ASC, `Shop_Central_idCentral` ASC) ,
  INDEX `fk_ShopPaketZuweisung_Pakete1_idx` (`Pakete_idPakete` ASC) ,
  CONSTRAINT `fk_ShopPaketZuweisung_Shop1`
    FOREIGN KEY (`Shop_idShop` )
    REFERENCES `PrimusDB`.`Shop` (`idShop` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ShopPaketZuweisung_Pakete1`
    FOREIGN KEY (`Pakete_idPakete` )
    REFERENCES `PrimusDB`.`Pakete` (`idPakete` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PrimusDB`.`SpeditionPaketZuweisung`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PrimusDB`.`SpeditionPaketZuweisung` ;

CREATE  TABLE IF NOT EXISTS `PrimusDB`.`SpeditionPaketZuweisung` (
  `idSpeditionPaketZuweisung` INT NOT NULL AUTO_INCREMENT ,
  `Spedition_idSpedition` INT NOT NULL ,
  `Pakete_idPakete` INT NOT NULL ,
  PRIMARY KEY (`idSpeditionPaketZuweisung`) ,
  INDEX `fk_SpeditionPaketZuweisung_Spedition1_idx` (`Spedition_idSpedition` ASC) ,
  INDEX `fk_SpeditionPaketZuweisung_Pakete1_idx` (`Pakete_idPakete` ASC) ,
  CONSTRAINT `fk_SpeditionPaketZuweisung_Spedition1`
    FOREIGN KEY (`Spedition_idSpedition` )
    REFERENCES `PrimusDB`.`Spedition` (`idSpedition` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_SpeditionPaketZuweisung_Pakete1`
    FOREIGN KEY (`Pakete_idPakete` )
    REFERENCES `PrimusDB`.`Pakete` (`idPakete` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `PrimusDB`.`PaketVerfolgung`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `PrimusDB`.`PaketVerfolgung` ;

CREATE  TABLE IF NOT EXISTS `PrimusDB`.`PaketVerfolgung` (
  `idPaketVerfolgung` INT NOT NULL AUTO_INCREMENT ,
  `Pakete_idPakete` INT NOT NULL ,
  `Status` VARCHAR(45) NULL ,
  `Datum` DATE NULL ,
  `Uhrzeit` TIME NULL ,
  PRIMARY KEY (`idPaketVerfolgung`) ,
  INDEX `fk_PaketVerfolgung_Pakete1_idx` (`Pakete_idPakete` ASC) ,
  CONSTRAINT `fk_PaketVerfolgung_Pakete1`
    FOREIGN KEY (`Pakete_idPakete` )
    REFERENCES `PrimusDB`.`Pakete` (`idPakete` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `PrimusDB` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
