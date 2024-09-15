SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `DigitalRepository` ;
CREATE SCHEMA IF NOT EXISTS `DigitalRepository` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `DigitalRepository`;

-- -----------------------------------------------------
-- Table `DigitalRepository`.`Person`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Person` (
  `id` CHAR(10) NOT NULL ,
  `First` CHAR(30) NOT NULL ,
  `Middle` CHAR(30) NULL ,
  `Last` CHAR(30) NULL ,
  `Initials` CHAR(10) NULL ,
  `Nick` CHAR(20) NULL ,
  `Other` CHAR(30) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
COMMENT = 'Persons';


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Country`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Country` (
  `id` CHAR(10) NOT NULL ,
  `Name` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DigitalRepository`.`State`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`State` (
  `id` CHAR(10) NOT NULL ,
  `Country_id` CHAR(10) NOT NULL ,
  `Name` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`, `Country_id`) ,
  INDEX `fk_State_Country` (`Country_id` ASC) ,
  CONSTRAINT `fk_State_Country`
    FOREIGN KEY (`Country_id` )
    REFERENCES `DigitalRepository`.`Country` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DigitalRepository`.`City`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`City` (
  `id` CHAR(10) NOT NULL ,
  `State_id` CHAR(10) NOT NULL ,
  `Country_id` CHAR(10) NOT NULL ,
  `Name` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`, `State_id`, `Country_id`) ,
  INDEX `fk_City_State` (`State_id` ASC, `Country_id` ASC) ,
  CONSTRAINT `fk_City_State`
    FOREIGN KEY (`State_id` , `Country_id` )
    REFERENCES `DigitalRepository`.`State` (`id` , `Country_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DigitalRepository`.`District`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`District` (
  `id` CHAR(10) NOT NULL ,
  `State_id` CHAR(10) NOT NULL ,
  `Country_id` CHAR(10) NOT NULL ,
  `Name` CHAR(30) NULL ,
  PRIMARY KEY (`id`, `State_id`, `Country_id`) ,
  INDEX `fk_District_State` (`State_id` ASC, `Country_id` ASC) ,
  CONSTRAINT `fk_District_State`
    FOREIGN KEY (`State_id` , `Country_id` )
    REFERENCES `DigitalRepository`.`State` (`id` , `Country_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Address`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Address` (
  `id` CHAR(10) NOT NULL ,
  `Value` VARCHAR(255) NULL ,
  `HouseNumber` CHAR(30) NULL ,
  `Street` CHAR(30) NULL ,
  `Area` CHAR(30) NULL ,
  `City_id` CHAR(10) NULL ,
  `City_State_id` CHAR(10) NULL ,
  `City_Country_id` CHAR(10) NULL ,
  `District_id` CHAR(10) NULL ,
  `District_State_id` CHAR(10) NULL ,
  `District_Country_id` CHAR(10) NULL ,
  `Person_id` CHAR(10) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Address_City` (`City_id` ASC, `City_State_id` ASC, `City_Country_id` ASC) ,
  INDEX `fk_Address_District` (`District_id` ASC, `District_State_id` ASC, `District_Country_id` ASC) ,
  INDEX `fk_Address_Person` (`Person_id` ASC) ,
  CONSTRAINT `fk_Address_City`
    FOREIGN KEY (`City_id` , `City_State_id` , `City_Country_id` )
    REFERENCES `DigitalRepository`.`City` (`id` , `State_id` , `Country_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Address_District`
    FOREIGN KEY (`District_id` , `District_State_id` , `District_Country_id` )
    REFERENCES `DigitalRepository`.`District` (`id` , `State_id` , `Country_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Address_Person`
    FOREIGN KEY (`Person_id` )
    REFERENCES `DigitalRepository`.`Person` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Organization`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Organization` (
  `id` CHAR(10) NOT NULL ,
  `Name` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Bundle`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Bundle` (
  `id` MEDIUMINT NOT NULL ,
  `Name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Folio`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Folio` (
  `id` MEDIUMINT NOT NULL ,
  `Name` VARCHAR(45) NULL ,
  `Bundle_id` MEDIUMINT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Folio_Bundle` (`Bundle_id` ASC) ,
  CONSTRAINT `fk_Folio_Bundle`
    FOREIGN KEY (`Bundle_id` )
    REFERENCES `DigitalRepository`.`Bundle` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Language`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Language` (
  `id` CHAR(10) NOT NULL ,
  `Name` VARCHAR(100) NULL ,
  `UnicodePoint` MEDIUMINT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
COMMENT = 'Langauges for the manuscript and transcrriptions, commentaries etc';


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Script`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Script` (
  `id` CHAR(10) NOT NULL ,
  `Name` VARCHAR(100) NULL ,
  `UnicodePoint` MEDIUMINT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
COMMENT = 'Scripts for the manuscript and transcrriptions, commentaries etc';


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Material`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Material` (
  `id` CHAR(10) NOT NULL ,
  `Name` CHAR(30) NULL ,
  `Description` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Condition`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Condition` (
  `id` CHAR(10) NOT NULL ,
  `Name` CHAR(30) NULL ,
  `Description` VARCHAR(100) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DigitalRepository`.`CatalogSource`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`CatalogSource` (
  `id` CHAR(10) NOT NULL ,
  `Name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DigitalRepository`.`ColorProfile`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`ColorProfile` (
  `id` CHAR(10) NOT NULL ,
  `Name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
COMMENT = 'ICC Color Profile';


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Equipment`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Equipment` (
  `id` CHAR(10) NOT NULL ,
  `Name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
COMMENT = 'Hardware used';


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Make`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Make` (
  `id` CHAR(10) NOT NULL ,
  `Name` VARCHAR(45) NULL ,
  `Equipment` CHAR(10) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Make_Equipment` (`Equipment` ASC) ,
  CONSTRAINT `fk_Make_Equipment`
    FOREIGN KEY (`Equipment` )
    REFERENCES `DigitalRepository`.`Equipment` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Scanner Software';


-- -----------------------------------------------------
-- Table `DigitalRepository`.`ImagingSoftware`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`ImagingSoftware` (
  `id` CHAR(10) NOT NULL ,
  `Name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DigitalRepository`.`DigitalManuscript`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`DigitalManuscript` (
  `id` CHAR(20) NOT NULL ,
  `MaterialNumber` CHAR(30) NULL ,
  `Title` VARCHAR(255) NULL ,
  `OtherTitle` VARCHAR(255) NULL ,
  `Complete` BOOLEAN NULL DEFAULT TRUE COMMENT 'whether the mansucript is complete' ,
  `Organization` CHAR(10) NULL ,
  `Folio` MEDIUMINT NULL ,
  `Language` CHAR(10) NULL ,
  `Script` CHAR(10) NULL ,
  `Pages` SMALLINT NULL ,
  `Material` CHAR(10) NULL ,
  `Condition` CHAR(10) NULL ,
  `ManuscriptDate` DATE NULL ,
  `Length` SMALLINT NULL ,
  `Width` SMALLINT NULL ,
  `CreatedOn` DATE NULL ,
  `ModifiedOn` DATE NULL ,
  `CatalogSource` CHAR(10) NULL ,
  `Remarks` VARCHAR(255) NULL ,
  `PixelHeight` MEDIUMINT NULL ,
  `PixelWidth` MEDIUMINT NULL ,
  `Resolution` SMALLINT NULL COMMENT 'Pixels per inch' ,
  `ColorMode` SMALLINT NULL ,
  `ColorProfile` CHAR(10) NULL ,
  `Make` CHAR(10) NULL ,
  `XResolution` MEDIUMINT NULL ,
  `YResoluton` MEDIUMINT NULL ,
  `ImagingSoftware` CHAR(10) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_DigitalManuscript_Organization` (`Organization` ASC) ,
  INDEX `fk_DigitalManuscript_Folio` (`Folio` ASC) ,
  INDEX `fk_DigitalManuscript_Language` (`Language` ASC) ,
  INDEX `fk_DigitalManuscript_Script` (`Script` ASC) ,
  INDEX `fk_DigitalManuscript_Material` (`Material` ASC) ,
  INDEX `fk_DigitalManuscript_Condition` (`Condition` ASC) ,
  INDEX `fk_DigitalManuscript_CatalogSource` (`CatalogSource` ASC) ,
  INDEX `fk_DigitalManuscript_ColorProfile` (`ColorProfile` ASC) ,
  INDEX `fk_DigitalManuscript_Make` (`Make` ASC) ,
  INDEX `fk_DigitalManuscript_ImagingSoftware` (`ImagingSoftware` ASC) ,
  CONSTRAINT `fk_DigitalManuscript_Organization`
    FOREIGN KEY (`Organization` )
    REFERENCES `DigitalRepository`.`Organization` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DigitalManuscript_Folio`
    FOREIGN KEY (`Folio` )
    REFERENCES `DigitalRepository`.`Folio` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DigitalManuscript_Language`
    FOREIGN KEY (`Language` )
    REFERENCES `DigitalRepository`.`Language` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DigitalManuscript_Script`
    FOREIGN KEY (`Script` )
    REFERENCES `DigitalRepository`.`Script` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DigitalManuscript_Material`
    FOREIGN KEY (`Material` )
    REFERENCES `DigitalRepository`.`Material` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DigitalManuscript_Condition`
    FOREIGN KEY (`Condition` )
    REFERENCES `DigitalRepository`.`Condition` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DigitalManuscript_CatalogSource`
    FOREIGN KEY (`CatalogSource` )
    REFERENCES `DigitalRepository`.`CatalogSource` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DigitalManuscript_ColorProfile`
    FOREIGN KEY (`ColorProfile` )
    REFERENCES `DigitalRepository`.`ColorProfile` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DigitalManuscript_Make`
    FOREIGN KEY (`Make` )
    REFERENCES `DigitalRepository`.`Make` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DigitalManuscript_ImagingSoftware`
    FOREIGN KEY (`ImagingSoftware` )
    REFERENCES `DigitalRepository`.`ImagingSoftware` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'A copy of a physical manuscript. The central entity in the database. This is like a book in a library. Frames are the pages.';


-- -----------------------------------------------------
-- Table `DigitalRepository`.`PersonRole`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`PersonRole` (
  `id` CHAR(10) NOT NULL ,
  `Name` CHAR(30) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
COMMENT = 'A person can be an Author, Scholar, Reviewer etc';


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Person_has_Role`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Person_has_Role` (
  `Person` CHAR(10) NOT NULL ,
  `Role` CHAR(10) NOT NULL ,
  PRIMARY KEY (`Person`, `Role`) ,
  INDEX `fk_Person_has_PersonRole_Person` (`Person` ASC) ,
  INDEX `fk_Person_has_PersonRole_PersonRole` (`Role` ASC) ,
  CONSTRAINT `fk_Person_has_PersonRole_Person`
    FOREIGN KEY (`Person` )
    REFERENCES `DigitalRepository`.`Person` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Person_has_PersonRole_PersonRole`
    FOREIGN KEY (`Role` )
    REFERENCES `DigitalRepository`.`PersonRole` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `DigitalRepository`.`DocumentType`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`DocumentType` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT ,
  `Name` CHAR(30) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
COMMENT = 'Transcription, Commentary or Annotation';


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Document`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Document` (
  `DigitalManuscript` CHAR(20) NOT NULL ,
  `id` CHAR(10) NOT NULL ,
  `Path` VARCHAR(255) NULL ,
  `DocumentType` SMALLINT NULL ,
  PRIMARY KEY (`id`, `DigitalManuscript`) ,
  INDEX `fk_Document_DocumentType` (`DocumentType` ASC) ,
  INDEX `fk_Document_DigitalManuscript` (`DigitalManuscript` ASC) ,
  CONSTRAINT `fk_Document_DocumentType`
    FOREIGN KEY (`DocumentType` )
    REFERENCES `DigitalRepository`.`DocumentType` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Document_DigitalManuscript`
    FOREIGN KEY (`DigitalManuscript` )
    REFERENCES `DigitalRepository`.`DigitalManuscript` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'A document is about a digital manuscript. It can be a Transcripion, Commentary or an Annotation';


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Subject`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Subject` (
  `id` CHAR(10) NOT NULL ,
  `Name` CHAR(30) NOT NULL ,
  `Description` VARCHAR(100) NULL ,
  `BelongsUnder` CHAR(10) NULL COMMENT 'A subject can belong under other subjects. A tree-structure with one root node and other subdivisions can be built' ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Subject_Subject` (`BelongsUnder` ASC) ,
  CONSTRAINT `fk_Subject_Subject`
    FOREIGN KEY (`BelongsUnder` )
    REFERENCES `DigitalRepository`.`Subject` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DigitalRepository`.`DigitalManuscript_has_Subject`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`DigitalManuscript_has_Subject` (
  `DigitalManuscript` CHAR(20) NOT NULL ,
  `Subject` CHAR(10) NOT NULL ,
  INDEX `fk_DigitalManuscript_has_Subject_DigitalManuscript` (`DigitalManuscript` ASC) ,
  INDEX `fk_DigitalManuscript_has_Subject_Subject` (`Subject` ASC) ,
  PRIMARY KEY (`Subject`, `DigitalManuscript`) ,
  CONSTRAINT `fk_DigitalManuscript_has_Subject_DigitalManuscript`
    FOREIGN KEY (`DigitalManuscript` )
    REFERENCES `DigitalRepository`.`DigitalManuscript` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DigitalManuscript_has_Subject_Subject`
    FOREIGN KEY (`Subject` )
    REFERENCES `DigitalRepository`.`Subject` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Tag`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Tag` (
  `id` CHAR(10) NOT NULL ,
  `Name` CHAR(30) NULL ,
  `BelongsTo` CHAR(10) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Tag_Tag` (`BelongsTo` ASC) ,
  CONSTRAINT `fk_Tag_Tag`
    FOREIGN KEY (`BelongsTo` )
    REFERENCES `DigitalRepository`.`Tag` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Tags are additional metadata. They can be used in search and filter for digital manuscripts. They can be managed by a Metadata Manager';


-- -----------------------------------------------------
-- Table `DigitalRepository`.`DigitalManuscript_has_Tag`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`DigitalManuscript_has_Tag` (
  `DigitalManuscript` CHAR(20) NOT NULL ,
  `Tag` CHAR(10) NOT NULL ,
  PRIMARY KEY (`DigitalManuscript`, `Tag`) ,
  INDEX `fk_DigitalManuscript_has_Tag_DigitalManuscript` (`DigitalManuscript` ASC) ,
  INDEX `fk_DigitalManuscript_has_Tag_Tag` (`Tag` ASC) ,
  CONSTRAINT `fk_DigitalManuscript_has_Tag_DigitalManuscript`
    FOREIGN KEY (`DigitalManuscript` )
    REFERENCES `DigitalRepository`.`DigitalManuscript` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_DigitalManuscript_has_Tag_Tag`
    FOREIGN KEY (`Tag` )
    REFERENCES `DigitalRepository`.`Tag` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `DigitalRepository`.`DigitalManuscriptFrame`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`DigitalManuscriptFrame` (
  `DigitalManuscript_id` CHAR(20) NOT NULL ,
  `Frame_id` INT NOT NULL AUTO_INCREMENT ,
  `Location_URL` VARCHAR(255) NOT NULL COMMENT 'The Document Management System will contain the digital image file (JEPG/TIFF). The URL will be linked in this field.' ,
  PRIMARY KEY (`Frame_id`, `DigitalManuscript_id`) ,
  INDEX `fk_DigitalManuscriptFrame_DigitalManuscript` (`DigitalManuscript_id` ASC) ,
  CONSTRAINT `fk_DigitalManuscriptFrame_DigitalManuscript`
    FOREIGN KEY (`DigitalManuscript_id` )
    REFERENCES `DigitalRepository`.`DigitalManuscript` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'Each scanned image will be a frame. These frames will make up a Digital Manuscript (a book). A DigitalManuscript is like a book, and frames are pages in the book.';


-- -----------------------------------------------------
-- Table `DigitalRepository`.`PersonLife`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`PersonLife` (
  `id` CHAR(10) NOT NULL ,
  `Living` BOOLEAN NULL DEFAULT TRUE ,
  `Birth` DATE NULL ,
  `Death` DATE NULL ,
  `Period` CHAR(25) NULL ,
  `History` VARCHAR(255) NULL ,
  `Life` VARCHAR(255) NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_PersonLife_Person` (`id` ASC) ,
  CONSTRAINT `fk_PersonLife_Person`
    FOREIGN KEY (`id` )
    REFERENCES `DigitalRepository`.`Person` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = 'The life of a person. May be a historical, fictional or even a mytholgical person';

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
