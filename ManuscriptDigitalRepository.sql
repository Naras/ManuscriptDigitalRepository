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
  `UnicodeBlock` MEDIUMINT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
COMMENT = 'Langauges for the manuscript and transcrriptions, commentaries etc';


-- -----------------------------------------------------
-- Table `DigitalRepository`.`Script`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Script` (
  `id` CHAR(10) NOT NULL ,
  `Name` VARCHAR(100) NULL ,
  `UnicodeBlock` MEDIUMINT NULL ,
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
-- Table `DigitalRepository`.`Role`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `DigitalRepository`.`Role` (
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
  INDEX `fk_Person_has_Role_Person` (`Person` ASC) ,
  INDEX `fk_Person_has_Role_Role` (`Role` ASC) ,
  CONSTRAINT `fk_Person_has_Role_Person`
    FOREIGN KEY (`Person` )
    REFERENCES `DigitalRepository`.`Person` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Person_has_Role_Role`
    FOREIGN KEY (`Role` )
    REFERENCES `DigitalRepository`.`Role` (`id` )
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


/*Data for the table `material` */

insert  into `material`(`id`,`Name`,`Description`) values ('palm','Palmleaf','Palm leaf'),('birch','Birch','Birch'),('wood','Wood','Wood');

/*Data for the table `condition` */

insert  into `condition`(`id`,`Name`,`Description`) values ('bad','Bad','Bad'),('good','Good','Good');

/*Data for the table `equipment` */

insert  into `equipment`(`id`,`Name`) values ('scanner','Scanner');

/*Data for the table `make` */

insert  into `make`(`id`,`Name`,`Equipment`) values ('Amul','Amul','Scanner'),('Nirma','Nirma','Scanner'),('Samsung','Samsung','Scanner'),('Sony','Sony','Scanner');

/*Data for the table `imagingsoftware` */

insert  into `imagingsoftware`(`id`,`Name`) values ('Better','Behethar Hai - sirf kehene ke liye'),('Good','goody goody - this is a test'),('Worst','Kharaab Hai - pasand nahi');

/*Data for the table `catalogsource` */

insert  into `catalogsource`(`id`,`Name`) values ('melkote','Melkote');


/*Data for the table `organization` */

insert  into `organization`(`id`,`Name`) values ('FRLHT','Foundation for Rejuvenation of Local Health Traditions');

/*Data for the table `person` */

insert  into `person`(`id`,`First`,`Middle`,`Last`,`Initials`,`Nick`,`Other`) values ('ama','Ananth',NULL,'M.A.','AMA',NULL,NULL),('hem','Hemanth',NULL,NULL,'H',NULL,NULL),('maa','Alwar',NULL,'M.A.','MAA',NULL,NULL),('mal','Lakshmitathachar',NULL,'M.A.','MAL',NULL,NULL),('ND','Nikhil','','Desale','ND','',''),('nmg','Narasimhan',NULL,'M.G.','NMG','Naras',NULL),('pa','Pavitha',NULL,'A.','PA',NULL,NULL),('sd','Sindhu',NULL,'D.','SD',NULL,NULL),('skv','Surekha',NULL,'K.V.','SKV',NULL,NULL),('vr','Vinay',NULL,'R.','VR',NULL,NULL),('vs','Vijay',NULL,'Srinivas','VS',NULL,NULL);

/*Data for the table `Role` */

insert  into `Role`(`id`,`Name`) values ('Auth','Author'),('Edt','Editor'),('Mgr','Manager'),('Mtd','Metadata Manager'),('pub','Publisher'),('Rev','Reviewer'),('Sch','Scholar'),('Scr','Scribe');

/*Data for the table `person_has_role` */

insert  into `person_has_role`(`Person`,`Role`) values ('ama','rev'),('ama','sch'),('hem','sch'),('maa','mtd'),('maa','pub'),('maa','rev'),('maa','sch'),('mal','edt'),('mal','mgr'),('mal','mtd'),('mal','pub'),('mal','rev'),('nmg','mgr'),('nmg','scr'),('pa','scr'),('sd','rev'),('skv','edt'),('vr','auth'),('vs','mtd');

/*Data for the table `language` */

insert  into `language`(`id`,`Name`,`UnicodeBlock`) values ('aSSAmi','aSSAmi',21),('bengAli','bengAli',21),('English','English',2),('gujarAthi','gujarAthi',23),('hindi','hindi',20),('kannaDa','KannaDa',27),('kashmIri','kashmIri',185),('malayALam','malayALam',28),('mANipuri','mANipuri',136),('marATHi','marATHi',20),('oDiyA','oDiyA',24),('punjAbi','punJabi',22),('Samskrth','Samskrth',20),('tamiL','tamiL',25),('telugu','telugu',26),('urdu','urdu',19);

/*Data for the table `script` */

insert  into `script`(`id`,`Name`,`UnicodeBlock`) values ('bengAli','bengAli',21),('brAhmi','brAhmi',181),('devanAgari','devanAgari',20),('English','English',2),('granTHa','granTHa',NULL),('gujarAthi','gujarAthi',23),('gurmukhi','gurmukhi',22),('kannaDa','kannADa',27),('malayALam','malayALam',28),('oDiyA','oDiyA',24),('shArada','shArada',185),('tamiL','tamiL',25),('telugu','telugu',26);

/*Data for the table `country` */

insert  into `country`(`id`,`Name`) values ('Afghan','Afghanistan'),('Alb','Albania'),('Argent','Argentina'),('Armen','Armenia'),('Azer','Azerbaijan'),('BD','Bangladesh'),('Bolivia','Bolivia'),('Botswana','Botswana'),('Braz','Brazil'),('Chile','Chile'),('China','China'),('Croatia','Croatia'),('Czech','Czech Republic'),('Eritrea','Eritrea'),('Ethio','Ethiopia'),('Gambia','Gambia'),('Georgia','Georgia'),('Haiti','Haiti'),('Hungary','Hungary'),('ind','India'),('Indonesia','Indonesia'),('Iran','Iran'),('Iraq','Iraq'),('Israel','Israel'),('Japan','Japan'),('Kamp','Kampuchea'),('Kazakhstan','Kazakhstan'),('Kenya','Kenya'),('Kurd','Kurdistan'),('Kuwait','Kuwait'),('Lao','Lao'),('Lat','Latvia'),('Lith','Lithuania'),('Malay','Malaysia'),('Mali','Mali'),('Myanmar','Myanmar'),('Namibia','Namibia'),('NKorea','North Korea'),('Oman','Oman'),('Pakistan','Pakistan'),('Peru','Peru'),('Phil','Phillipines'),('Poland','Poland'),('Rumania','Rumania'),('Russia','Russia'),('SAfrica','South Africa'),('Saud','Saudi Arabia'),('Senegal','Senegal'),('Serbia','Serbia'),('Sing','Singapore'),('SKorea','South Korea'),('SL','Srilanka'),('Somalia','Somalia'),('Tadji','Tadjikistan'),('Tanz','Tanzania'),('Thai','Thailand'),('Turkey','Turkey'),('Turkmen','Turkmenistan'),('UAE','Emirates'),('Uganda','Uganda'),('Ukraine','Ukraine'),('Uzbek','Uzbekistan'),('Viet','Vietnam'),('Zimb','Zimbabwe');

/*Data for the table `state` */

insert  into `state`(`id`,`Country_id`,`Name`) values ('AP','ind','Andhra pradesh'),('aru','ind','aruNAchal pradEsh'),('asm','ind','aSSAm'),('ben','ind','paschim bangAl'),('bih','ind','bihAr'),('Col','SL','Colombo'),('guj','ind','gujarAth'),('HP','ind','himAchal pradEsh'),('JK','ind','jammu & kAshmIr'),('kar','ind','karnAtaka'),('ker','ind','kEraLa'),('mani','ind','maNipUr'),('megh','ind','mEghAlay'),('MH','ind','mahA rAshTRa'),('miz','ind','mizOram'),('MP','ind','madhya pradEsh'),('naga','ind','nAgAland'),('ND','ind','New Delhi'),('odi','ind','odiSSa'),('punj','ind','punJab'),('raj','ind','rAjAsTHAn'),('sik','ind','sikkim'),('TN','ind','tamiL NADu'),('tri','ind','tripura'),('UP','ind','utthara pradEsh');

/*Data for the table `district` */

insert  into `district`(`id`,`State_id`,`Country_id`,`Name`) values ('Gul','kar','ind','Gulbarga'),('mandya','kar','ind','Mandya'),('tumkur','kar','ind','Tumkur');

/*Data for the table `city` */

insert  into `city`(`id`,`State_id`,`Country_id`,`Name`) values ('ahmed','guj','ind','Ahmedabad'),('allah','UP','ind','Allahabad'),('bho','MP','ind','Bhopal'),('bhuv','odi','ind','Bhuvaneshwar'),('bidar','kar','ind','Bidar'),('blr','kar','ind','Bangalore'),('che','TN','ind','Chennai'),('hyd','AP','ind','Hyderabad'),('jai','raj','ind','Jaipur'),('kol','ben','ind','Kolkata'),('luck','UP','ind','Lucknow'),('mumb','MH','ind','Mumbai'),('mys','kar','ind','Mysore'),('nd','ND','ind','New Delhi'),('pat','bih','ind','Patna'),('pune','MH','ind','Pune'),('puri','odi','ind','Puri'),('sim','HP','ind','Simla'),('Tanj','TN','ind','thanjAvUr'),('uday','raj','ind','Udaipur'),('vizag','AP','ind','Vishakapatnam');


/*Data for the table `documenttype` */

insert  into `documenttype`(`id`,`Name`) values (1,'Transcription'),(2,'Commentary'),(3,'Annotation');


/*Data for the table `bundle` */

insert  into `bundle`(`id`,`Name`) values (0,'Bundle1');

/*Data for the table `folio` */

insert  into `folio`(`id`,`Name`,`Bundle_id`) values (0,'Yog Shatak',0);

/*Data for the table `digitalmanuscript` */

insert  into `digitalmanuscript`(`id`,`MaterialNumber`,`Title`,`OtherTitle`,`Complete`,`Organization`,`Folio`,`Language`,`Script`,`Pages`,`Material`,`Condition`,`ManuscriptDate`,`Length`,`Width`,`CreatedOn`,`ModifiedOn`,`CatalogSource`,`Remarks`,`PixelHeight`,`PixelWidth`,`Resolution`,`ColorMode`,`ColorProfile`,`Make`,`XResolution`,`YResoluton`,`ImagingSoftware`) values ('KSP 1','K460',' ?','',0,NULL,NULL,NULL,'kannaDa',28,NULL,'good',NULL,NULL,NULL,'2013-09-11',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('KSP 10','K358','Ayurveda Mahashastra','',0,NULL,NULL,'kannaDa','kannaDa',162,NULL,NULL,NULL,NULL,NULL,'2013-09-16',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('KSP 11','K83','?','',0,NULL,NULL,'kannaDa','kannaDa',144,NULL,NULL,NULL,46,4,'2013-09-17',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('KSP 2','K455','Vaidya Pustaka ','',0,NULL,NULL,'kannaDa','kannaDa',63,NULL,'good',NULL,23,4,'2013-09-11',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('KSP 3','K465/3a','Vaidya  ','',0,NULL,NULL,'kannaDa','kannaDa',14,NULL,'good',NULL,23,NULL,'2013-09-13',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('KSP 4','K465/3b','Vaidya ','',0,NULL,NULL,'kannaDa','kannaDa',9,NULL,'good',NULL,22,NULL,'2013-09-13',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('KSP 5','K465/3c','Vaidya','',0,NULL,NULL,'kannaDa','kannaDa',2,NULL,'good',NULL,19,4,'2013-09-13',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('KSP 6','K471','Vaidya Chintamani','',0,NULL,NULL,'kannaDa','kannaDa',55,NULL,'good',NULL,23,NULL,'2013-09-13',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('KSP 7',' K473','Vaidya Chintamani ','',0,NULL,NULL,'kannaDa','kannaDa',27,NULL,'good',NULL,NULL,NULL,'2013-09-13',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('KSP 8','K126','Dhanwantari Nighantu','',0,NULL,NULL,'kannaDa','kannaDa',84,NULL,'good',NULL,37,4,'2013-09-13',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('KSP 9','K298',' Vaidya Grantha    ','',0,NULL,NULL,'kannaDa','kannaDa',213,NULL,NULL,NULL,45,NULL,'2013-09-13',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('UTC 1','43','?','',0,NULL,NULL,'kannaDa','kannaDa',242,NULL,'good',NULL,47,NULL,'2013-09-19',NULL,NULL,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('ys000','ys000','Yog Shatak 0',NULL,1,'FRLHT',0,'English','English',1,'wood','good','2013-06-18',5,4,'2013-08-16',NULL,NULL,'Yog Shatak Logo',2,2,864,NULL,NULL,'Amul',3,3,'Good');


/*Data for the table `subject` */

insert  into `subject`(`id`,`Name`,`Description`,`BelongsUnder`) values ('Aitreya','Aitreya','Aitreyopanishad  \"The Microcosm of Man\"','Upanishad'),('AraNyaka','AraNyaka',' \"wilderness texts\" or \"forest treaties\"','Samhita'),('AV','aTHarvaNa Veda','aTHarvaNa Veda','Veda'),('Ayurveda','Ayurveda','Indian Medical Systems','Upaveda'),('BrahadAraN','BrahadAraNyaka','the big forest treatise','Upanishad'),('BrahmaNa','BrahmaNa','Prose commentaries on Samhitas','Samhita'),('Chandas','Chandas','Metre','Vedanga'),('Chandogya','Chandogya','Chandogyopanishad \"Song and Sacrifice\"','Upanishad'),('Dhanurveda','Dhanurveda','Archery','Upaveda'),('Gandharvav','Gandharva veda','Sacred Music and Dance',NULL),('Isa','Isa','Isopanishad \"The Inner Ruler\"','Upanishad'),('Jyotisha','Jyotisha','Astronomy/Astrology/Cosmology','Vedanga'),('Kalpa','Kalpa','Ritual','Vedanga'),('Katha','Katha','Kathopanishad \"Death as Teacher\"','Upanishad'),('Kena','Kena','Kenopanishad \"Who moves the world?\"','Upanishad'),('mAndukya','mAndukya','mAndukyopanishad \"Consciousness and its phases\"','Upanishad'),('MrigAyur','Mrigaayurveda','Ayurveda for Animals','Ayurveda'),('Mundaka','Mundaka','Mundakopanoishad  \"Two modes of Knowing\"','Upanishad'),('Nirukta','Nirukta','Etymology','Vedanga'),('Prasna','Prasna','Prasnopanishad  \"The Breath of Life\"','Upanishad'),('RV','Rk Veda','Rk Veda','Veda'),('Samhita','Samhita','Collection of metric texts','Veda'),('ShastrashA','ShastrashAstra','Military Knowledge','Upaveda'),('Shiksha','Shiksha','Phonetics','Vedanga'),('ShilpashAs','Shilpa shAstra',NULL,'Upaveda'),('Sthapatyav','Sthapatyaveda','Architecture (temples)','Upaveda'),('SV','sAma Veda','sAma Veda','Veda'),('TaittirIya','TaittirIya','Taittiriyopanishad  \"From Food to Joy\"','Upanishad'),('Upanishad','Upanishad','Fundamental philosophy','Vedantha'),('Upaveda','Upaveda','Subsidiary vedas \"applied knowledge\"','Veda'),('Veda','Veda','Veda',NULL),('Vedanga','Vedanga','Angas (limbs) of Vedas','Veda'),('Vedantha','Vedantha','\"End of the vedas\" - sum of all knowledge','Veda'),('VyAkaraNa','VyAkaraNa','Grammar','Vedanga'),('YV','Yajur Veda','Yajur Veda','Veda');


COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
