SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `EPMComboEntityVO`;
CREATE TABLE `EPMComboEntityVO` (

  `id` bigint(20) NOT NULL,
  `Label` varchar(255) NOT NULL,
  `Value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `WorkflowCountEntityVO`;
CREATE TABLE `WorkflowCountEntityVO` (

  `id` bigint(20) NOT NULL,
  `cntDirect` bigint(20) DEFAULT NULL,
  `cntRole` bigint(20) NOT NULL,
  `processDescription` varchar(255) NOT NULL,
  `processId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `digital_manuscript`;
CREATE TABLE `digital_manuscript` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `document_type` int(11) NOT NULL,
  `subject` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mxm6dv1dvt4eg0ogwa55hb8by` (`document_type`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `epmcomboentityvo`;
CREATE TABLE `epmcomboentityvo` (

  `id` bigint(20) NOT NULL,
  `Label` varchar(255) NOT NULL,
  `Value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `mdr_article_details`;
CREATE TABLE `mdr_article_details` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abstractOfArticle` varchar(255) DEFAULT NULL,
  `abstractOfMagazine` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `isPrinted` int(11) DEFAULT NULL,
  `issnNo` varchar(255) DEFAULT NULL,
  `journalOthrDtls` varchar(255) DEFAULT NULL,
  `magazineIssnNo` varchar(255) DEFAULT NULL,
  `nameOfEditor` varchar(255) DEFAULT NULL,
  `nameOfMagazine` varchar(255) DEFAULT NULL,
  `noOfPages` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `yearOfPublication` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_accesscontrol`;
CREATE TABLE `omds_accesscontrol` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `MenuMasterFkId` bigint(20) DEFAULT NULL,
  `RoleMasterFkId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_bundle`;
CREATE TABLE `omds_bundle` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bundle_number` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_category`;
CREATE TABLE `omds_category` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT NULL,
  `parentfkid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA02ECE104454CC43` (`parentfkid`),
  CONSTRAINT `FKA02ECE104454CC43` FOREIGN KEY (`parentfkid`) REFERENCES `omds_category` (`id`) ON DELETE SET NULL ON UPDATE SET NULL

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_commoncodesmetadata`;
CREATE TABLE `omds_commoncodesmetadata` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_conditionofmanuscript`;
CREATE TABLE `omds_conditionofmanuscript` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_employeemaster`;
CREATE TABLE `omds_employeemaster` (

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

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_language`;
CREATE TABLE `omds_language` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `UNICODE_POINT` varchar(255) DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_manuscript_specificcategorymapper`;
CREATE TABLE `omds_manuscript_specificcategorymapper` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `manuscriptfkid` bigint(20) DEFAULT NULL,
  `specificcategoryfkid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_material`;
CREATE TABLE `omds_material` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_menumaster`;
CREATE TABLE `omds_menumaster` (

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

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_nmm_details`;
CREATE TABLE `omds_nmm_details` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cameraMake` varchar(255) DEFAULT NULL,
  `cameraModel` varchar(255) DEFAULT NULL,
  `createdDate` varchar(255) DEFAULT NULL,
  `digitisedDate` varchar(255) DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL,
  `width` varchar(255) DEFAULT NULL,
  `xResolution` varchar(255) DEFAULT NULL,
  `yResolution` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_organisation`;
CREATE TABLE `omds_organisation` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `type` smallint(6) DEFAULT NULL,
  `phoneNumber` varchar(255) DEFAULT NULL,
  `acronym` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_patha`;
CREATE TABLE `omds_patha` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isdeleted` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `unicode_point` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_person`;
CREATE TABLE `omds_person` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `LIFE_HISTORY` varchar(1500) DEFAULT NULL,
  `NAME` varchar(1000) NOT NULL,
  `regional_name` varchar(255) DEFAULT NULL,
  `diacritical_name` varchar(255) DEFAULT NULL,
  `PERIOD` varchar(255) DEFAULT NULL,
  `type` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_publisher`;
CREATE TABLE `omds_publisher` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_rolemaster`;
CREATE TABLE `omds_rolemaster` (

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

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_script`;
CREATE TABLE `omds_script` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `UNICODE_POINT` varchar(255) DEFAULT NULL,
  `isdeleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_specificcategory`;
CREATE TABLE `omds_specificcategory` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `isdeleted` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_subject1`;
CREATE TABLE `omds_subject1` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isdeleted` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `unicode_point` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_tags`;
CREATE TABLE `omds_tags` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isdeleted` bit(1) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_userlogindetails`;
CREATE TABLE `omds_userlogindetails` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CreatedBy` varchar(255) DEFAULT NULL,
  `CreatedDate` datetime DEFAULT NULL,
  `ModifiedBy` varchar(255) DEFAULT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  `RowVersion` int(11) DEFAULT NULL,
  `GenratedResetPasswordDate` datetime DEFAULT NULL,
  `isDeleted` tinyint(4) DEFAULT NULL,
  `LoginId` varchar(255) NOT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `RefrenceFkId` bigint(20) DEFAULT NULL,
  `ResetPasswordId` varchar(255) DEFAULT NULL,
  `Status` smallint(6) NOT NULL,
  `Type` smallint(6) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_userroledetails`;
CREATE TABLE `omds_userroledetails` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `RoleMasterFkId` bigint(20) DEFAULT NULL,
  `UserLoginDetailsFkId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `registration_code`;
CREATE TABLE `registration_code` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime NOT NULL,
  `username` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  `last_updated_by` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `role_name` varchar(100) DEFAULT NULL,
  `last_updated` datetime DEFAULT NULL,
  `authority` varchar(255) NOT NULL,
  `is_enabled` bit(1) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r261muslviw4d89p3xlvagqof` (`authority`),
  KEY `roles_dateCreated_idx` (`date_created`),
  KEY `roles_roleName_idx` (`role_name`),
  KEY `roles_lastUpdated_idx` (`last_updated`),
  KEY `roles_authority_idx` (`authority`),
  KEY `roles_createdBy_idx` (`created_by`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `last_updated_by` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `password_expired` bit(1) NOT NULL,
  `last_updated` datetime DEFAULT NULL,
  `account_expired` bit(1) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `prev_login_date` bigint(20) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_currentprocessbranchdetails`;
CREATE TABLE `wfl_currentprocessbranchdetails` (

  `Id` int(11) NOT NULL,
  `CurrentProcessDetailsFkId` int(11) NOT NULL,
  `PreviousFkId` int(11) DEFAULT NULL,
  `NextFkId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_currentprocessccdetails`;
CREATE TABLE `wfl_currentprocessccdetails` (

  `Id` int(11) NOT NULL,
  `CurrentProcessDetailsFkId` int(11) NOT NULL,
  `UserInfoFkId` int(11) NOT NULL,
  `Status` tinyint(4) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_currentprocesschecklistanswer`;
CREATE TABLE `wfl_currentprocesschecklistanswer` (

  `Id` int(11) NOT NULL,
  `ChecklistItemFkId` int(11) NOT NULL,
  `ChecklistItemTypeDetailsFkId` int(11) NOT NULL,
  `HighValue` int(11) NOT NULL,
  `LowValue` int(11) DEFAULT NULL,
  `CurrentProcessDetailsFkId` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `CreatedBy` varchar(50) NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  `ModifiedBy` varchar(50) NOT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_currentprocesschecklistdetails`;
CREATE TABLE `wfl_currentprocesschecklistdetails` (

  `Id` int(11) NOT NULL,
  `CurrentProcessDetailsFkId` int(11) NOT NULL,
  `CheckListMasterFkId` int(11) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_currentprocessdetails`;
CREATE TABLE `wfl_currentprocessdetails` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CurrentProcessMasterFkId` int(11) NOT NULL,
  `Level` int(11) NOT NULL,
  `LocationUserRoleFkId` int(11) NOT NULL,
  `Url` varchar(250) NOT NULL,
  `Status` tinyint(4) NOT NULL,
  `StartedOn` datetime DEFAULT NULL,
  `CompletedOn` datetime DEFAULT NULL,
  `ScreenName` varchar(250) NOT NULL,
  `ProcessTimeOut` int(11) NOT NULL,
  `IsUserRoleId` tinyint(4) NOT NULL,
  `IsReturnButton` tinyint(4) NOT NULL,
  `IsTerminateButton` tinyint(4) NOT NULL,
  `IsSaveButton` tinyint(4) NOT NULL,
  `IsAuthorizeButton` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_currentprocessmaster`;
CREATE TABLE `wfl_currentprocessmaster` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ProcessMasterFkId` int(11) NOT NULL,
  `LocationMasterFkId` int(11) NOT NULL,
  `ReferenceFkId` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_historyprocessbranchdetails`;
CREATE TABLE `wfl_historyprocessbranchdetails` (

  `Id` int(11) NOT NULL,
  `HistoryProcessDetailsFkId` int(11) NOT NULL,
  `PreviousFkId` int(11) DEFAULT NULL,
  `NextFkId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_historyprocessccdetails`;
CREATE TABLE `wfl_historyprocessccdetails` (

  `Id` int(11) NOT NULL,
  `HistoryProcessDetailsFkId` int(11) NOT NULL,
  `UserInfoFkId` int(11) NOT NULL,
  `Status` tinyint(4) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_historyprocesschecklistanswer`;
CREATE TABLE `wfl_historyprocesschecklistanswer` (

  `Id` int(11) NOT NULL,
  `ChecklistItemFkId` int(11) NOT NULL,
  `ChecklistItemTypeDetailsFkId` int(11) NOT NULL,
  `HighValue` int(11) NOT NULL,
  `LowValue` int(11) DEFAULT NULL,
  `HistoryProcessDetailsFkId` int(11) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `CreatedBy` varchar(50) NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  `ModifiedBy` varchar(50) NOT NULL,
  `RowVersion` int(11) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_historyprocesschecklistdetails`;
CREATE TABLE `wfl_historyprocesschecklistdetails` (

  `Id` int(11) NOT NULL,
  `HistoryProcessDetailsFkId` int(11) NOT NULL,
  `CheckListMasterFkId` int(11) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_historyprocessdetails`;
CREATE TABLE `wfl_historyprocessdetails` (

  `Id` int(11) NOT NULL,
  `HistoryProcessMasterFkId` int(11) NOT NULL,
  `Level` int(11) NOT NULL,
  `LocationUserRoleFkId` int(11) NOT NULL,
  `Url` varchar(250) NOT NULL,
  `Status` tinyint(4) NOT NULL,
  `ScreenName` varchar(250) NOT NULL,
  `ProcessTimeOut` int(11) NOT NULL,
  `IsUserRoleId` tinyint(4) NOT NULL,
  `IsReturnButton` tinyint(4) NOT NULL,
  `IsTerminateButton` tinyint(4) NOT NULL,
  `IsSaveButton` tinyint(4) NOT NULL,
  `IsAuthorizeButton` tinyint(4) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_historyprocessmaster`;
CREATE TABLE `wfl_historyprocessmaster` (

  `Id` int(11) NOT NULL,
  `ProcessMasterFkId` int(11) NOT NULL,
  `LocationMasterFkId` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_locationlevelmaster`;
CREATE TABLE `wfl_locationlevelmaster` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `Description` varchar(255) DEFAULT NULL,
  `IconImageName` varchar(255) DEFAULT NULL,
  `LevelNumber` bigint(20) NOT NULL,
  `Name` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_locationprocessbranchdetails`;
CREATE TABLE `wfl_locationprocessbranchdetails` (

  `Id` int(11) NOT NULL,
  `LocationProcessDetailsFkId` int(11) NOT NULL,
  `PreviousFkId` int(11) DEFAULT NULL,
  `NextFkId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_locationprocesschecklistdetails`;
CREATE TABLE `wfl_locationprocesschecklistdetails` (

  `Id` int(11) NOT NULL,
  `LocationProcessDetailsFkId` int(11) NOT NULL,
  `CheckListMasterFkId` int(11) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_locationprocessdetails`;
CREATE TABLE `wfl_locationprocessdetails` (

  `Id` int(11) NOT NULL,
  `LocationProcessMasterFkId` int(11) NOT NULL,
  `Level` int(11) NOT NULL,
  `RoleMasterFkId` int(11) NOT NULL,
  `Url` varchar(250) NOT NULL,
  `Status` tinyint(4) NOT NULL,
  `ScreenName` varchar(250) NOT NULL,
  `ProcessTimeOut` int(11) NOT NULL,
  `IsReturnButton` tinyint(4) NOT NULL,
  `IsTerminateButton` tinyint(4) NOT NULL,
  `IsSaveButton` tinyint(4) NOT NULL,
  `IsAuthorizeButton` tinyint(4) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_locationprocessmaster`;
CREATE TABLE `wfl_locationprocessmaster` (

  `Id` int(11) NOT NULL,
  `ProcessMasterFkId` int(11) NOT NULL,
  `LocationMasterFkId` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_processbranchdetails`;
CREATE TABLE `wfl_processbranchdetails` (

  `Id` int(11) NOT NULL,
  `ProcessDetailsFkId` int(11) NOT NULL,
  `PreviousFkId` int(11) DEFAULT NULL,
  `NextFkId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_processchecklistdetails`;
CREATE TABLE `wfl_processchecklistdetails` (

  `Id` int(11) NOT NULL,
  `ProcessDetailsFkId` int(11) NOT NULL,
  `CheckListMasterFkId` int(11) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_processdetails`;
CREATE TABLE `wfl_processdetails` (

  `Id` int(11) NOT NULL,
  `ProcessMasterFkId` int(11) NOT NULL,
  `Level` int(11) NOT NULL,
  `RoleMasterFkId` int(11) NOT NULL,
  `Url` varchar(250) NOT NULL,
  `Status` tinyint(4) NOT NULL,
  `ScreenName` varchar(250) NOT NULL,
  `ProcessTimeOut` int(11) NOT NULL,
  `IsReturnButton` tinyint(4) NOT NULL,
  `IsTerminateButton` tinyint(4) NOT NULL,
  `IsSaveButton` tinyint(4) NOT NULL,
  `IsAuthorizeButton` tinyint(4) NOT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_processmaster`;
CREATE TABLE `wfl_processmaster` (

  `Id` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `workflowcountentityvo`;
CREATE TABLE `workflowcountentityvo` (

  `id` bigint(20) NOT NULL,
  `cntDirect` bigint(20) DEFAULT NULL,
  `cntRole` bigint(20) NOT NULL,
  `processDescription` varchar(255) NOT NULL,
  `processId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_commoncodes`;
CREATE TABLE `omds_commoncodes` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `CommonCodesMetadataFkId` bigint(20) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKF4ED7B06E7570A44` (`CommonCodesMetadataFkId`),
  KEY `FKD892DEC9E7570A44` (`CommonCodesMetadataFkId`),
  CONSTRAINT `FKD892DEC9E7570A44` FOREIGN KEY (`CommonCodesMetadataFkId`) REFERENCES `omds_commoncodesmetadata` (`Id`),
  CONSTRAINT `FKF4ED7B06E7570A44` FOREIGN KEY (`CommonCodesMetadataFkId`) REFERENCES `omds_commoncodesmetadata` (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_publication`;
CREATE TABLE `omds_publication` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ISAVAILABLE` smallint(6) DEFAULT NULL,
  `NO_OF_PAGES` varchar(255) DEFAULT NULL,
  `PRICE` varchar(255) DEFAULT NULL,
  `REMARK` varchar(255) DEFAULT NULL,
  `YEAR_OF_PUBLICATION` varchar(255) DEFAULT NULL,
  `AuthorFkId` bigint(20) DEFAULT NULL,
  `PublisherFkId` bigint(20) DEFAULT NULL,
  `isDeleted` smallint(6) DEFAULT NULL,
  `editorfkid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK28EFAC3790670BC0` (`PublisherFkId`),
  KEY `FK28EFAC37FAFF4C2E` (`AuthorFkId`),
  KEY `FKAD21FDA23F83B9D` (`PublisherFkId`),
  KEY `FKAD21FDA63FE7171` (`AuthorFkId`),
  KEY `FKAD21FDA60B6B053` (`editorfkid`),
  CONSTRAINT `FK28EFAC3790670BC0` FOREIGN KEY (`PublisherFkId`) REFERENCES `omds_publisher` (`Id`),
  CONSTRAINT `FK28EFAC37FAFF4C2E` FOREIGN KEY (`AuthorFkId`) REFERENCES `omds_person` (`Id`),
  CONSTRAINT `FKAD21FDA23F83B9D` FOREIGN KEY (`PublisherFkId`) REFERENCES `omds_publisher` (`Id`),
  CONSTRAINT `FKAD21FDA60B6B053` FOREIGN KEY (`editorfkid`) REFERENCES `omds_person` (`Id`),
  CONSTRAINT `FKAD21FDA63FE7171` FOREIGN KEY (`AuthorFkId`) REFERENCES `omds_person` (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_locationuserroledetails`;
CREATE TABLE `wfl_locationuserroledetails` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `locationmasterfkid` bigint(20) NOT NULL,
  `rolemasterfkid` bigint(20) DEFAULT NULL,
  `userlogindetailsfkid` bigint(20) DEFAULT NULL,
  `UserInfoFkId` bigint(20) NOT NULL,
  `ValidFromDate` datetime DEFAULT '1900-01-01 00:00:00',
  `ValidToDate` datetime DEFAULT '2199-12-12 00:00:00',
  PRIMARY KEY (`id`),
  KEY `FKD3EF414AD375457C` (`rolemasterfkid`),
  CONSTRAINT `FKD3EF414AD375457C` FOREIGN KEY (`rolemasterfkid`) REFERENCES `omds_rolemaster` (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_manuscript_tagmapper`;
CREATE TABLE `omds_manuscript_tagmapper` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `manuscriptfkid` bigint(20) DEFAULT NULL,
  `tagsfkid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK22986254AD4DBFDA` (`tagsfkid`),
  CONSTRAINT `FK22986254AD4DBFDA` FOREIGN KEY (`tagsfkid`) REFERENCES `omds_tags` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (

  `users_id` bigint(20) NOT NULL,
  `roles_id` bigint(20) NOT NULL,
  PRIMARY KEY (`users_id`,`roles_id`),
  KEY `FKa62j07k5mhgifpp955h37ponj` (`roles_id`),
  CONSTRAINT `FKa62j07k5mhgifpp955h37ponj` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKml90kef4w2jy7oxyqv742tsfc` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `wfl_locationmaster`;
CREATE TABLE `wfl_locationmaster` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `rowversion` tinyint(4) DEFAULT NULL,
  `Description` varchar(255) NOT NULL,
  `IsDeleted` tinyint(1) NOT NULL,
  `IsWorkflowEnabled` tinyint(1) DEFAULT NULL,
  `LevelFkId` bigint(20) DEFAULT NULL,
  `Name` varchar(255) NOT NULL,
  `ParentFkId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK24A607593E3492B1` (`ParentFkId`),
  KEY `FK24A6075995D301E5` (`LevelFkId`),
  CONSTRAINT `FK24A607593E3492B1` FOREIGN KEY (`ParentFkId`) REFERENCES `wfl_locationmaster` (`id`),
  CONSTRAINT `FK24A6075995D301E5` FOREIGN KEY (`LevelFkId`) REFERENCES `wfl_locationlevelmaster` (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_digital_manuscript`;
CREATE TABLE `omds_digital_manuscript` (

  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `AuthorFkId` bigint(20) DEFAULT NULL,
  `CONTRIBUTION_TO_AYURVEDA` varchar(1500) DEFAULT NULL,
  `MaterialFkId` bigint(20) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `regional_name` varchar(255) DEFAULT NULL,
  `diacritical_name` varchar(255) DEFAULT NULL,
  `OrganisationFkId` bigint(20) DEFAULT NULL,
  `REMARKS` varchar(255) DEFAULT NULL,
  `SUMMARY` text,
  `TYPE_OF_WORK` int(11) DEFAULT NULL,
  `UNIQUENESS_OF_WORK` text,
  `languageFkId` bigint(20) DEFAULT NULL,
  `isDeleted` smallint(6) DEFAULT NULL,
  `PublicationFkId` bigint(20) DEFAULT NULL,
  `categoryFkId` bigint(20) DEFAULT NULL,
  `scriptFkId` bigint(20) DEFAULT NULL,
  `specificCategoryFkId` bigint(20) DEFAULT NULL,
  `documentType` bigint(20) DEFAULT NULL,
  `any_other_details` text,
  `table_of_contents` varchar(4000) DEFAULT NULL,
  `parentfkid` bigint(20) DEFAULT NULL,
  `manuscript_id` varchar(255) DEFAULT NULL,
  `scribefkid` bigint(20) DEFAULT NULL,
  `acc_no` varchar(255) DEFAULT NULL,
  `beginning_line` varchar(1000) DEFAULT NULL,
  `bundleMasterfkid` bigint(20) DEFAULT NULL,
  `cataloguedetails` varchar(255) DEFAULT NULL,
  `catalogue_no` varchar(255) DEFAULT NULL,
  `colophon` varchar(1000) DEFAULT NULL,
  `commentatorfkid` bigint(20) DEFAULT NULL,
  `condition_of_manuscript` varchar(100) DEFAULT NULL,
  `digitized_by` varchar(255) DEFAULT NULL,
  `documentation_of_manuscript` int(11) DEFAULT NULL,
  `ending_line` varchar(1000) DEFAULT NULL,
  `isbound` smallint(6) DEFAULT NULL,
  `nature_of_collection` smallint(6) DEFAULT NULL,
  `nmmDetailsFkId` bigint(20) DEFAULT NULL,
  `source_of_catalogue` int(11) DEFAULT NULL,
  `subCommentatorfkid` bigint(20) DEFAULT NULL,
  `total_no_of_folios` bigint(20) DEFAULT NULL,
  `total_no_of_maps` bigint(20) DEFAULT NULL,
  `translatorfkid` bigint(20) DEFAULT NULL,
  `digitizerid` bigint(20) DEFAULT NULL,
  `recordstatus` tinyint(4) DEFAULT NULL,
  `manuscripttype` tinyint(4) DEFAULT NULL,
  `transLanguage` varchar(255) DEFAULT NULL,
  `articleDetailFkId` bigint(20) DEFAULT NULL,
  `articleLaguage` int(11) DEFAULT NULL,
  `fieldsCovered` varchar(255) DEFAULT NULL,
  `redDigits` varchar(5) DEFAULT NULL,
  `redDigitsText` varchar(20) DEFAULT NULL,
  `redLetters` varchar(5) DEFAULT NULL,
  `redLettersText` varchar(20) DEFAULT NULL,
  `redLines` varchar(5) DEFAULT NULL,
  `redLinesText` varchar(20) DEFAULT NULL,
  `redMarked` varchar(5) DEFAULT NULL,
  `redMarkedText` varchar(20) DEFAULT NULL,
  `decorated` smallint(6) DEFAULT NULL,
  `decoratedRemarks` text,
  `edited` smallint(6) DEFAULT '0',
  `editedTypeRemarks` text,
  `charactersPerLine` varchar(20) DEFAULT NULL,
  `editedType` varchar(100) DEFAULT NULL,
  `illustrations` smallint(6) DEFAULT NULL,
  `illustrationsOthers` varchar(255) DEFAULT NULL,
  `illustrationsType` varchar(100) DEFAULT NULL,
  `inkPigment` varchar(100) DEFAULT NULL,
  `inkPigmentOthers` varchar(255) DEFAULT NULL,
  `linesPerPage` varchar(20) DEFAULT NULL,
  `miscellaneousRemarks` longtext,
  `patha` varchar(2) DEFAULT NULL,
  `subject1` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKECCFAB02FAFF4C2E` (`AuthorFkId`),
  KEY `FKECCFAB0217842D66` (`MaterialFkId`),
  KEY `FKECCFAB02330DB6FC` (`OrganisationFkId`),
  KEY `FKECCFAB021A5988C8` (`languageFkId`),
  KEY `FKECCFAB023ED96620` (`PublicationFkId`),
  KEY `FK3C7EF1FF63FE7171` (`AuthorFkId`),
  KEY `FK3C7EF1FFC9678C1B` (`specificCategoryFkId`),
  KEY `FK3C7EF1FF6C523697` (`categoryFkId`),
  KEY `FK3C7EF1FFB29C8FF1` (`scriptFkId`),
  KEY `FK3C7EF1FF3D4F0DE9` (`MaterialFkId`),
  KEY `FK3C7EF1FF4024694B` (`languageFkId`),
  KEY `FK3C7EF1FF58D8977F` (`OrganisationFkId`),
  KEY `FK3C7EF1FF32DE12BD` (`PublicationFkId`),
  KEY `FK3C7EF1FFD21066D` (`parentfkid`),
  KEY `FK3C7EF1FF72964770` (`scribefkid`),
  KEY `FK3C7EF1FF3E356B1B` (`commentatorfkid`),
  KEY `FK3C7EF1FF9341AA80` (`translatorfkid`),
  KEY `FK3C7EF1FF51310BA3` (`nmmDetailsFkId`),
  KEY `FK3C7EF1FFC33764DB` (`subCommentatorfkid`),
  KEY `FK3C7EF1FF1796EE0E` (`articleDetailFkId`),
  CONSTRAINT `FK3C7EF1FF1796EE0E` FOREIGN KEY (`articleDetailFkId`) REFERENCES `mdr_article_details` (`id`),
  CONSTRAINT `FK3C7EF1FF32DE12BD` FOREIGN KEY (`PublicationFkId`) REFERENCES `omds_publication` (`Id`),
  CONSTRAINT `FK3C7EF1FF3D4F0DE9` FOREIGN KEY (`MaterialFkId`) REFERENCES `omds_material` (`Id`),
  CONSTRAINT `FK3C7EF1FF3E356B1B` FOREIGN KEY (`commentatorfkid`) REFERENCES `omds_person` (`Id`),
  CONSTRAINT `FK3C7EF1FF4024694B` FOREIGN KEY (`languageFkId`) REFERENCES `omds_language` (`Id`),
  CONSTRAINT `FK3C7EF1FF51310BA3` FOREIGN KEY (`nmmDetailsFkId`) REFERENCES `omds_nmm_details` (`id`),
  CONSTRAINT `FK3C7EF1FF58D8977F` FOREIGN KEY (`OrganisationFkId`) REFERENCES `omds_organisation` (`Id`),
  CONSTRAINT `FK3C7EF1FF63FE7171` FOREIGN KEY (`AuthorFkId`) REFERENCES `omds_person` (`Id`),
  CONSTRAINT `FK3C7EF1FF6C523697` FOREIGN KEY (`categoryFkId`) REFERENCES `omds_category` (`id`),
  CONSTRAINT `FK3C7EF1FF72964770` FOREIGN KEY (`scribefkid`) REFERENCES `omds_person` (`Id`),
  CONSTRAINT `FK3C7EF1FF9341AA80` FOREIGN KEY (`translatorfkid`) REFERENCES `omds_person` (`Id`),
  CONSTRAINT `FK3C7EF1FFB29C8FF1` FOREIGN KEY (`scriptFkId`) REFERENCES `omds_script` (`Id`),
  CONSTRAINT `FK3C7EF1FFC33764DB` FOREIGN KEY (`subCommentatorfkid`) REFERENCES `omds_person` (`Id`),
  CONSTRAINT `FK3C7EF1FFC9678C1B` FOREIGN KEY (`specificCategoryFkId`) REFERENCES `omds_specificcategory` (`id`),
  CONSTRAINT `FK3C7EF1FFD21066D` FOREIGN KEY (`parentfkid`) REFERENCES `omds_digital_manuscript` (`Id`),
  CONSTRAINT `FKECCFAB0217842D66` FOREIGN KEY (`MaterialFkId`) REFERENCES `omds_material` (`Id`),
  CONSTRAINT `FKECCFAB021A5988C8` FOREIGN KEY (`languageFkId`) REFERENCES `omds_language` (`Id`),
  CONSTRAINT `FKECCFAB02330DB6FC` FOREIGN KEY (`OrganisationFkId`) REFERENCES `omds_organisation` (`Id`),
  CONSTRAINT `FKECCFAB023ED96620` FOREIGN KEY (`PublicationFkId`) REFERENCES `omds_publication` (`Id`),
  CONSTRAINT `FKECCFAB02FAFF4C2E` FOREIGN KEY (`AuthorFkId`) REFERENCES `omds_person` (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_digital_manuscript_frame`;
CREATE TABLE `omds_digital_manuscript_frame` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `digitalDocumentBean` tinyblob,
  `digitalManuscriptFkId` bigint(20) NOT NULL,
  `filePath` varchar(255) NOT NULL,
  `islast` tinyint(4) DEFAULT '0',
  `frame_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7D0A432D9CA4AC1` (`digitalManuscriptFkId`),
  CONSTRAINT `FK7D0A432D9CA4AC1` FOREIGN KEY (`digitalManuscriptFkId`) REFERENCES `omds_digital_manuscript` (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_manuscript_authormapper`;
CREATE TABLE `omds_manuscript_authormapper` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `manuscriptfkid` bigint(20) DEFAULT NULL,
  `authorfkid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK87F49CF363FE7171` (`authorfkid`),
  KEY `FK87F49CF3CF77E6A9` (`manuscriptfkid`),
  CONSTRAINT `FK87F49CF363FE7171` FOREIGN KEY (`authorfkid`) REFERENCES `omds_person` (`Id`),
  CONSTRAINT `FK87F49CF3CF77E6A9` FOREIGN KEY (`manuscriptfkid`) REFERENCES `omds_digital_manuscript` (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_digitaldocument`;
CREATE TABLE `omds_digitaldocument` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `digitalManuscriptFkId` bigint(20) DEFAULT NULL,
  `digitalManuscriptFrameFkId` bigint(20) DEFAULT NULL,
  `languageFkId` bigint(20) DEFAULT NULL,
  `scriptFkId` bigint(20) DEFAULT NULL,
  `workType` int(11) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `recordtype` smallint(6) DEFAULT NULL,
  `script` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5F161DE1B29C8FF1` (`scriptFkId`),
  KEY `FK5F161DE14024694B` (`languageFkId`),
  KEY `FK5F161DE19CA4AC1` (`digitalManuscriptFkId`),
  KEY `FK5F161DE168BE33A9` (`digitalManuscriptFrameFkId`),
  CONSTRAINT `FK5F161DE14024694B` FOREIGN KEY (`languageFkId`) REFERENCES `omds_language` (`Id`),
  CONSTRAINT `FK5F161DE168BE33A9` FOREIGN KEY (`digitalManuscriptFrameFkId`) REFERENCES `omds_digital_manuscript_frame` (`id`),
  CONSTRAINT `FK5F161DE19CA4AC1` FOREIGN KEY (`digitalManuscriptFkId`) REFERENCES `omds_digital_manuscript` (`Id`),
  CONSTRAINT `FK5F161DE1B29C8FF1` FOREIGN KEY (`scriptFkId`) REFERENCES `omds_script` (`Id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_document_comment`;
CREATE TABLE `omds_document_comment` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` text,
  `commentedby` varchar(255) DEFAULT NULL,
  `commentedon` varchar(255) DEFAULT NULL,
  `framefkid` bigint(20) DEFAULT NULL,
  `digitalmanuscriptid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7C3FA4D7AE2CCE7` (`framefkid`),
  CONSTRAINT `FK7C3FA4D7AE2CCE7` FOREIGN KEY (`framefkid`) REFERENCES `omds_digital_manuscript_frame` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `omds_digitaldocument_details`;
CREATE TABLE `omds_digitaldocument_details` (

  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `createdby` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `rowversion` int(11) DEFAULT NULL,
  `attachmentFilePath` varchar(255) DEFAULT NULL,
  `digitalDocumentFkId` bigint(20) NOT NULL,
  `TEXT` longtext,
  `userRoleDetailsFkId` bigint(20) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `ismax` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKE33745E4E5F0A136` (`userRoleDetailsFkId`),
  KEY `FKE33745E4E420B76B` (`digitalDocumentFkId`),
  CONSTRAINT `FKE33745E4E420B76B` FOREIGN KEY (`digitalDocumentFkId`) REFERENCES `omds_digitaldocument` (`id`),
  CONSTRAINT `FKE33745E4E5F0A136` FOREIGN KEY (`userRoleDetailsFkId`) REFERENCES `wfl_locationuserroledetails` (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS=1;
