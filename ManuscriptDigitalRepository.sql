/*
SQLyog Community Edition- MySQL GUI v8.17 
MySQL - 5.5.27 : Database - omdp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`omdp` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `omdp`;

/*Table structure for table `omdp_accesscontrol` */

DROP TABLE IF EXISTS `omdp_accesscontrol`;

CREATE TABLE `omdp_accesscontrol` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `MenuMasterFkId` bigint(20) DEFAULT NULL,
  `RoleMasterFkId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

/*Data for the table `omdp_accesscontrol` */

insert  into `omdp_accesscontrol`(`Id`,`MenuMasterFkId`,`RoleMasterFkId`) values (58,101,1),(59,105,1),(60,10501,1),(61,10502,1),(62,102,1),(63,10201,1),(64,10202,1),(65,103,1),(66,10301,1),(67,10302,1),(68,104,1),(69,10401,1);

/*Table structure for table `omdp_author` */

DROP TABLE IF EXISTS `omdp_author`;

CREATE TABLE `omdp_author` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `LIFE_HISTORY` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `PERIOD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `omdp_author` */

insert  into `omdp_author`(`Id`,`LIFE_HISTORY`,`NAME`,`PERIOD`) values (30,'Author belonging to. Born in Varanasi.','Upadhyay Sri Madhava','1700AD');

/*Table structure for table `omdp_commoncodes` */

DROP TABLE IF EXISTS `omdp_commoncodes`;

CREATE TABLE `omdp_commoncodes` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CommonCodesMetadataFkId` bigint(20) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKF4ED7B06E7570A44` (`CommonCodesMetadataFkId`),
  CONSTRAINT `FKF4ED7B06E7570A44` FOREIGN KEY (`CommonCodesMetadataFkId`) REFERENCES `omdp_commoncodesmetadata` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `omdp_commoncodes` */

/*Table structure for table `omdp_commoncodesmetadata` */

DROP TABLE IF EXISTS `omdp_commoncodesmetadata`;

CREATE TABLE `omdp_commoncodesmetadata` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `omdp_commoncodesmetadata` */

/*Table structure for table `omdp_digital_manuscript` */

DROP TABLE IF EXISTS `omdp_digital_manuscript`;

CREATE TABLE `omdp_digital_manuscript` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `AuthorFkId` bigint(20) DEFAULT NULL,
  `CONTRIBUTION_TO_AYURVEDA` varchar(255) DEFAULT NULL,
  `MaterialFkId` bigint(20) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `OrganisationFkId` bigint(20) DEFAULT NULL,
  `REMARKS` varchar(255) DEFAULT NULL,
  `SUMMARY` varchar(255) DEFAULT NULL,
  `TYPE_OF_WORK` int(11) DEFAULT NULL,
  `UNIQUENESS_OF_WORK` varchar(255) DEFAULT NULL,
  `languageFkId` bigint(20) DEFAULT NULL,
  `isDeleted` smallint(6) DEFAULT NULL,
  `PublicationFkId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKECCFAB02FAFF4C2E` (`AuthorFkId`),
  KEY `FKECCFAB0217842D66` (`MaterialFkId`),
  KEY `FKECCFAB02330DB6FC` (`OrganisationFkId`),
  KEY `FKECCFAB021A5988C8` (`languageFkId`),
  KEY `FKECCFAB023ED96620` (`PublicationFkId`),
  CONSTRAINT `FKECCFAB0217842D66` FOREIGN KEY (`MaterialFkId`) REFERENCES `omdp_material` (`Id`),
  CONSTRAINT `FKECCFAB021A5988C8` FOREIGN KEY (`languageFkId`) REFERENCES `omdp_language` (`Id`),
  CONSTRAINT `FKECCFAB02330DB6FC` FOREIGN KEY (`OrganisationFkId`) REFERENCES `omdp_organisation` (`Id`),
  CONSTRAINT `FKECCFAB023ED96620` FOREIGN KEY (`PublicationFkId`) REFERENCES `omdp_publication` (`Id`),
  CONSTRAINT `FKECCFAB02FAFF4C2E` FOREIGN KEY (`AuthorFkId`) REFERENCES `omdp_author` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `omdp_digital_manuscript` */

/*Table structure for table `omdp_employeemaster` */

DROP TABLE IF EXISTS `omdp_employeemaster`;

CREATE TABLE `omdp_employeemaster` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CreatedBy` varchar(255) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `ModifiedBy` varchar(255) DEFAULT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  `RowVersion` int(11) DEFAULT NULL,
  `Address1` varchar(255) NOT NULL,
  `Dob` datetime NOT NULL,
  `departmentMasterFkId` bigint(20) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Type` smallint(6) NOT NULL,
  `FirstName` varchar(255) NOT NULL,
  `Gender` smallint(6) NOT NULL,
  `isDeleted` tinyint(1) DEFAULT NULL,
  `LastName` varchar(255) NOT NULL,
  `PhoneNumber` varchar(255) NOT NULL,
  `smsDetailFkId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `omdp_employeemaster` */

insert  into `omdp_employeemaster`(`Id`,`CreatedBy`,`CreatedDate`,`ModifiedBy`,`ModifiedDate`,`RowVersion`,`Address1`,`Dob`,`departmentMasterFkId`,`Email`,`Type`,`FirstName`,`Gender`,`isDeleted`,`LastName`,`PhoneNumber`,`smsDetailFkId`) values (1,NULL,NULL,'System','2014-01-17 23:52:43',4,'asdfadfsdafdafasd','2014-01-17 23:52:43',1,'admin@omdp.com',1,'admin',1,0,'admin','35343',3);

/*Table structure for table `omdp_language` */

DROP TABLE IF EXISTS `omdp_language`;

CREATE TABLE `omdp_language` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `UNICODE_POINT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `omdp_language` */

insert  into `omdp_language`(`Id`,`NAME`,`UNICODE_POINT`) values (1,'Sanskrit','1'),(2,'Tamil',NULL),(3,'Hindi',NULL),(4,'English',NULL),(5,'Kannada',NULL),(6,'Telegu',NULL),(7,'Other Indian Languages',NULL),(8,'Other Foreign Languages',NULL);

/*Table structure for table `omdp_material` */

DROP TABLE IF EXISTS `omdp_material`;

CREATE TABLE `omdp_material` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `omdp_material` */

insert  into `omdp_material`(`Id`,`NAME`) values (-1,0),(1,2),(2,3),(3,1);

/*Table structure for table `omdp_menumaster` */

DROP TABLE IF EXISTS `omdp_menumaster`;

CREATE TABLE `omdp_menumaster` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `DefaultStatus` varchar(255) DEFAULT NULL,
  `LeftPanelLink` varchar(255) DEFAULT NULL,
  `MenuLevel` int(11) DEFAULT NULL,
  `MenuLink` varchar(255) DEFAULT NULL,
  `MenuName` varchar(255) DEFAULT NULL,
  `MenuOrder` int(11) DEFAULT NULL,
  `ParentId` bigint(20) DEFAULT NULL,
  `RequestId` varchar(255) DEFAULT NULL,
  `ShortKey` varchar(255) DEFAULT NULL,
  `StatusMsg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10404 DEFAULT CHARSET=utf8;

/*Data for the table `omdp_menumaster` */

insert  into `omdp_menumaster`(`Id`,`DefaultStatus`,`LeftPanelLink`,`MenuLevel`,`MenuLink`,`MenuName`,`MenuOrder`,`ParentId`,`RequestId`,`ShortKey`,`StatusMsg`) values (1,'1','Root',0,'Root','ROOT',0,NULL,'0',NULL,'WASP Configuration'),(101,'1','widget.leftprofile|widget.mybookmarks',0,'/homePageAction.action','Home',0,1,'1',NULL,'Home'),(102,'0','',0,'','Role',3,1,'102',NULL,'Role Management'),(103,'0','',0,'/createSearchFormAction.action?vo=EmployeeMaster','User',2,1,'103',NULL,'User Management'),(104,'1','',0,'','My Account',4,1,'4',NULL,'My Account'),(105,'0',NULL,0,'/createSearchFormAction.action?vo=DigitalManuscript','Manuscript',1,1,'105',NULL,NULL),(10201,'0','',1,'/displayMenuInfo.action','Create Role',0,102,'10201',NULL,'Create Role'),(10202,'0',NULL,1,'/showroleaction.action','Update Role',1,102,'10202',NULL,'Update Role'),(10401,'1','widget.leftprofile|widget.mybookmarks',1,'/logoutaction.action','Sign out',1,104,'10401',NULL,'Sign out');

/*Table structure for table `omdp_organisation` */

DROP TABLE IF EXISTS `omdp_organisation`;

CREATE TABLE `omdp_organisation` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `omdp_organisation` */

insert  into `omdp_organisation`(`Id`,`ADDRESS`,`NAME`) values (1,'Bangalore','FRLHT'),(2,NULL,'Other');

/*Table structure for table `omdp_publication` */

DROP TABLE IF EXISTS `omdp_publication`;

CREATE TABLE `omdp_publication` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ISAVAILABLE` smallint(6) DEFAULT NULL,
  `NO_OF_PAGES` varchar(255) DEFAULT NULL,
  `PRICE` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `YEAR_OF_PUBLICATION` varchar(255) DEFAULT NULL,
  `AuthorFkId` bigint(20) DEFAULT NULL,
  `PublisherFkId` bigint(20) DEFAULT NULL,
  `isDeleted` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK28EFAC3790670BC0` (`PublisherFkId`),
  KEY `FK28EFAC37FAFF4C2E` (`AuthorFkId`),
  CONSTRAINT `FK28EFAC3790670BC0` FOREIGN KEY (`PublisherFkId`) REFERENCES `omdp_publisher` (`Id`),
  CONSTRAINT `FK28EFAC37FAFF4C2E` FOREIGN KEY (`AuthorFkId`) REFERENCES `omdp_author` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `omdp_publication` */

/*Table structure for table `omdp_publisher` */

DROP TABLE IF EXISTS `omdp_publisher`;

CREATE TABLE `omdp_publisher` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `omdp_publisher` */

/*Table structure for table `omdp_rolemaster` */

DROP TABLE IF EXISTS `omdp_rolemaster`;

CREATE TABLE `omdp_rolemaster` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CreatedBy` varchar(255) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `ModifiedBy` varchar(255) DEFAULT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  `RowVersion` int(11) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `IsDeleted` tinyint(1) DEFAULT NULL,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `omdp_rolemaster` */

insert  into `omdp_rolemaster`(`Id`,`CreatedBy`,`CreatedDate`,`ModifiedBy`,`ModifiedDate`,`RowVersion`,`Description`,`IsDeleted`,`Name`) values (1,NULL,NULL,'System','2014-01-17 17:54:03',14,'administrator',0,'administrator');

/*Table structure for table `omdp_script` */

DROP TABLE IF EXISTS `omdp_script`;

CREATE TABLE `omdp_script` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `UNICODE_POINT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `omdp_script` */

/*Table structure for table `omdp_userlogindetails` */

DROP TABLE IF EXISTS `omdp_userlogindetails`;

CREATE TABLE `omdp_userlogindetails` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CreatedBy` varchar(255) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `ModifiedBy` varchar(255) DEFAULT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  `RowVersion` int(11) DEFAULT NULL,
  `GenratedResetPasswordDate` datetime DEFAULT NULL,
  `isDeleted` tinyint(11) DEFAULT NULL,
  `LoginId` varchar(255) NOT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `RefrenceFkId` bigint(20) DEFAULT NULL,
  `ResetPasswordId` varchar(255) DEFAULT NULL,
  `Status` smallint(6) NOT NULL,
  `Type` smallint(6) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `omdp_userlogindetails` */

insert  into `omdp_userlogindetails`(`Id`,`CreatedBy`,`CreatedDate`,`ModifiedBy`,`ModifiedDate`,`RowVersion`,`GenratedResetPasswordDate`,`isDeleted`,`LoginId`,`Password`,`RefrenceFkId`,`ResetPasswordId`,`Status`,`Type`) values (1,NULL,NULL,NULL,NULL,1,NULL,0,'admin','$2a$12$SVmmpTNl8oD6Rn7AS6L0LuUMbNPmUOisTCoqmN.HCv3h7YKJyoAsG',1,NULL,0,2);

/*Table structure for table `omdp_userroledetails` */

DROP TABLE IF EXISTS `omdp_userroledetails`;

CREATE TABLE `omdp_userroledetails` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `RoleMasterFkId` bigint(20) DEFAULT NULL,
  `UserLoginDetailsFkId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `omdp_userroledetails` */

insert  into `omdp_userroledetails`(`Id`,`RoleMasterFkId`,`UserLoginDetailsFkId`) values (1,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
