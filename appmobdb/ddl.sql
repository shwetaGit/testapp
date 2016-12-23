DROP TABLE IF EXISTS `zen_health`;

CREATE TABLE `zen_health` (  `monitorId` varchar(64) NOT NULL,  `classUnloaded` int(11) NOT NULL,  `gcCollectionTime` int(11) NOT NULL,  `nonHeapCommitted` bigint(20) NOT NULL,  `startTime` datetime NOT NULL,  `cpuTime` double NOT NULL,  `cpuLoadTime` double NOT NULL DEFAULT '0',  `nonHeapUsed` bigint(20) NOT NULL,  `upTime` int(11) NOT NULL,  `freePhysicalSize` bigint(20) NOT NULL,  `totalThread` double NOT NULL,  `heapCommitted` bigint(20) NOT NULL,  `totalPhysicalMemorySize` bigint(20) NOT NULL,  `threadPeakCount` double NOT NULL,  `scheduledDateTime` datetime NOT NULL,  `maxMemory` bigint(20) NOT NULL,  `cpuLoad` double NOT NULL DEFAULT '0',  `threadStartedCount` double NOT NULL,  `threadDaemonCount` double NOT NULL,  `serverInstanceId` int(32) NOT NULL,  `totalClass` bigint(20) NOT NULL,  `freeMemory` bigint(20) NOT NULL,  `freeSwapSize` bigint(20) NOT NULL,  `serverIpAddress` varchar(45) NOT NULL,  `classLoaded` bigint(20) NOT NULL,  `heapUsed` bigint(20) NOT NULL,  `systemLoadAverage` double NOT NULL,  `threadCpuTime` double NOT NULL,  `totalMemory` bigint(20) NOT NULL,  `totalSwapSize` bigint(20) NOT NULL,  `availableProcessor` double NOT NULL,  `gcMemoryName` varchar(256) NOT NULL,  `threadUserTime` varchar(45) DEFAULT NULL,  `usedMemory` bigint(20) DEFAULT NULL,  `usedSwapSize` bigint(20) DEFAULT NULL,  `usedPhysicalMemory` bigint(20) DEFAULT NULL,  PRIMARY KEY (`monitorId`));

DROP TABLE IF EXISTS `zen_health_counter`;

CREATE TABLE `zen_health_counter` (  `counterId` varchar(64) NOT NULL ,  `serverInstanceId` int(11) NOT NULL,  `httpStatus` varchar(256) NOT NULL,  `serverIpAddress` varchar(256) NOT NULL,  `serviceName` varchar(32) NOT NULL,  `scheduledDateTime` datetime NOT NULL,  `statusCount` int(2) NOT NULL,  `methodName` varchar(100) NOT NULL,  `counterType` int(4) NOT NULL,  PRIMARY KEY (`counterId`)) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `zen_health_gauge`;

CREATE TABLE `zen_health_gauge` (  `gaugeId` varchar(64) NOT NULL ,  `scheduledDateTime` datetime NOT NULL,  `methodHitCount` double NOT NULL,  `serverInstanceId` int(11) NOT NULL,  `methodHitTime` double NOT NULL,  `serverIpAddress` varchar(256) NOT NULL,  `serviceName` varchar(256) NOT NULL,  `methodName` varchar(32) NOT NULL,  PRIMARY KEY (`gaugeId`));

DROP TABLE IF EXISTS `zen_health_status`;

CREATE TABLE `zen_health_status` (  `healthId` varchar(64) NOT NULL ,  `scheduledDateTime` datetime NOT NULL,  `freeSpace` double NOT NULL,  `status` varchar(256) NOT NULL,  `serverInstanceId` int(3) NOT NULL,  `usedSpace` double NOT NULL,  `serverIpAddress` varchar(30) NOT NULL,  `totalSpace` double NOT NULL,  `errorDescription` varchar(300), PRIMARY KEY (`healthId`)) ;

DROP TABLE IF EXISTS `zen_request_details`;

CREATE TABLE `zen_request_details` ( `requestId` varchar(46) NOT NULL,  `className` varchar(150) DEFAULT NULL,  `ipAddress` varchar(45) DEFAULT NULL,  `requestTime` datetime DEFAULT NULL,   `requestMethod` varchar(45) DEFAULT NULL,  `httpStatus` varchar(45) DEFAULT NULL,  `returnStatus` varchar(45) DEFAULT NULL,  `executionTime` double DEFAULT NULL,   `methodName` varchar(45) DEFAULT NULL,   `startTime` DATETIME DEFAULT NULL,   `endTime` datetime NOT NULL,  `typeofClass` varchar(11) DEFAULT NULL,   `callSeqId` int(11) DEFAULT NULL,   `exceptionId` int(11) DEFAULT NULL,   `userId` varchar(45) DEFAULT NULL,   `appSessionId` varchar(45) DEFAULT NULL ,`customerId` varchar(45) DEFAULT NULL);

DROP TABLE IF EXISTS `art_user_last_status`;

CREATE TABLE `art_user_last_status` (
  `id` VARCHAR(64) NOT NULL,
  `user_id` VARCHAR(64) DEFAULT NULL,
  `menu_id` VARCHAR(64) DEFAULT NULL,
  `json` TEXT,

  `project_id` VARCHAR(256) DEFAULT NULL,

  `project_version_id` VARCHAR(256) DEFAULT NULL,

  `updated_by` INT(11) DEFAULT NULL,

  `updated_date` DATETIME DEFAULT NULL,

  `created_by` INT(11) DEFAULT NULL,

  `created_date` DATETIME DEFAULT NULL,

  `version_id` INT(11) DEFAULT NULL,

  `active_status` TINYINT(1) DEFAULT NULL,

  `app_creator_id` VARCHAR(256) DEFAULT NULL,

  PRIMARY KEY (`id`)

) ;

DROP TABLE IF EXISTS `art_password_algorithm`;

CREATE TABLE `art_password_algorithm` ( `algoId` varchar(256) NOT NULL, `algorithm` varchar(256) NOT NULL, `algoName` varchar(256) NOT NULL, `algoDescription` varchar(256) DEFAULT NULL, `active_status` int(1) DEFAULT '1', `created_date` datetime DEFAULT '1900-01-01 00:00:00', `updated_by` int(11) DEFAULT '-1', `version_id` int(11) DEFAULT '-1', `created_by` int(11) DEFAULT '-1', `updated_date` datetime DEFAULT '1900-01-01 00:00:00', PRIMARY KEY (`algoId`) );

DROP TABLE IF EXISTS `art_password_policy`;

CREATE TABLE `art_password_policy` ( `policyId` VARCHAR (256) NOT NULL, `policyName` VARCHAR (256) NOT NULL, `policyDescription` VARCHAR (512) NOT NULL, `minPwdLength` INT (11) NOT NULL, `maxPwdLength` INT (11) NOT NULL, `minCapitalLetters` INT (11) NOT NULL, `minSmallLetters` INT (11) NOT NULL, `minNumericValues` INT (11) NOT NULL, `minSpecialLetters` INT (11) NOT NULL, `allowedSpecialLetters` VARCHAR (256) DEFAULT NULL, `active_status` INT (1) DEFAULT '1', `version_id` INT (11) DEFAULT '-1', `updated_date` DATETIME DEFAULT '1900-01-01 00:00:00', `updated_by` INT (11) DEFAULT '-1', `created_date` DATETIME DEFAULT '1900-01-01 00:00:00', `created_by` INT (11) DEFAULT '-1', PRIMARY KEY (`policyId`) ) ;

DROP TABLE IF EXISTS `cloud_drive_file_content_type`;

CREATE TABLE `cloud_drive_file_content_type` ( `id` int(11) NOT NULL AUTO_INCREMENT, `application_type` varchar(300) NOT NULL, `file_mime_type` varchar(300) DEFAULT NULL, `extension` varchar(25) DEFAULT NULL, `image` varchar(25) DEFAULT NULL, PRIMARY KEY (`id`) );

DROP TABLE IF EXISTS `cloud_drive_tags`;

CREATE TABLE `cloud_drive_tags` ( `tag_id` varchar(64) NOT NULL, `tag_hierachy` VARCHAR(100) DEFAULT NULL, `tag_name` VARCHAR(50) DEFAULT NULL, `tag_image` VARCHAR(50) DEFAULT NULL, `created_by` VARCHAR(64) DEFAULT NULL, `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, `updated_by` VARCHAR(64) DEFAULT NULL, `updated_date` TIMESTAMP NULL DEFAULT NULL, `version_id` INT(11) DEFAULT NULL, `active_status` INT(1) DEFAULT NULL, `app_creator_id` VARCHAR(64) DEFAULT NULL, KEY `tag_id` (`tag_id`) );

DROP TABLE IF EXISTS `cloud_drive_files`;

CREATE TABLE `cloud_drive_files` ( `file_id` varchar(64) NOT NULL, `file_name` VARCHAR(50) DEFAULT NULL, `file_extension` VARCHAR(100) DEFAULT NULL, `file_application_type` VARCHAR(300) DEFAULT NULL, `file_mime_type` VARCHAR(300) DEFAULT NULL, `system_name` VARCHAR(100) DEFAULT NULL, `system_path` VARCHAR(500) DEFAULT NULL, `size1` DECIMAL(10,0) DEFAULT NULL, `file_scope` CHAR(1) DEFAULT NULL, `created_by` VARCHAR(64) DEFAULT NULL, `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, `updated_by` VARCHAR(64) DEFAULT NULL, `updated_date` TIMESTAMP NULL DEFAULT NULL, `version_id` VARCHAR(11) DEFAULT NULL, `app_creator_id` VARCHAR(64) DEFAULT NULL, `active_status` INT(1) DEFAULT NULL, PRIMARY KEY (`file_id`), KEY `file_id` (`file_id`), KEY `fk_file_content_id` (`file_mime_type`) );

DROP TABLE IF EXISTS `cloud_drive_tags_file`;

CREATE TABLE `cloud_drive_tags_file` ( `id` int(11) NOT NULL AUTO_INCREMENT, `file_id` varchar(64) DEFAULT NULL, `tag_id` varchar(64) DEFAULT NULL, PRIMARY KEY (`id`), KEY `fk_file_id` (`file_id`), CONSTRAINT `fk_file_id` FOREIGN KEY (`file_id`) REFERENCES `cloud_drive_files` (`file_id`) );

DROP TABLE IF EXISTS `cloud_drive_file_versions`;

CREATE TABLE `cloud_drive_file_versions` ( `base_file_Id` varchar(64) DEFAULT NULL, `file_id` varchar(64) NOT NULL, `file_version_id` INT(5) NOT NULL, `created_by` VARCHAR(64) DEFAULT NULL, `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, `updated_by` VARCHAR(64) DEFAULT NULL, `updated_date` TIMESTAMP NULL DEFAULT NULL, `version_id` INT(5) DEFAULT NULL, `active_status` INT(1) DEFAULT NULL, PRIMARY KEY (`file_id`,`file_version_id`), CONSTRAINT `fk_file_version_id` FOREIGN KEY (`file_id`) REFERENCES `cloud_drive_files` (`file_id`) );

DROP TABLE IF EXISTS `cloud_drive_tag_shared`;

CREATE TABLE `cloud_drive_tag_shared` ( `tag_id` int(11) DEFAULT NULL, `shared_user_id` varchar(64) DEFAULT NULL, `updated_by` varchar(64) DEFAULT NULL, `updated_date` timestamp NULL DEFAULT NULL, `created_by` varchar(64) DEFAULT NULL, `created_date` timestamp NULL DEFAULT  NULL, `version_id` int(11) DEFAULT NULL, `active_status` int(1) DEFAULT NULL, `id` varchar(64) NOT NULL, PRIMARY KEY (`id`) );

DROP TABLE IF EXISTS `cloud_drive_file_shared`;

CREATE TABLE `cloud_drive_file_shared` ( `file_id` varchar(64) DEFAULT NULL, `shared_user_id` varchar(64) DEFAULT NULL, `updated_by` varchar(64) DEFAULT NULL, `updated_date` TIMESTAMP NULL DEFAULT NULL, `created_by` varchar(64) DEFAULT NULL, `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP, `version_id` int(11) DEFAULT NULL, `active_status` int(1) DEFAULT NULL, `id` varchar(64) NOT NULL, PRIMARY KEY (`id`) );

DROP TABLE IF EXISTS `art_query`;

CREATE TABLE `art_query` (
`query_id` varchar(64) NOT NULL ,
`jpql_query` text,
`query_type` tinyint(1) DEFAULT NULL,
`query_json` text,
`name` varchar(256) DEFAULT NULL,
`hidden_name` varchar(256) DEFAULT NULL,
`app_creator_id` varchar(256) DEFAULT NULL,
`project_id` varchar(256) DEFAULT NULL,
`project_version_id` varchar(256) DEFAULT NULL,
`created_by` int(11) DEFAULT NULL,
`created_date` datetime DEFAULT NULL,
`updated_by` int(11) DEFAULT NULL,
`updated_date` datetime DEFAULT NULL,
`version_id` int(11) DEFAULT NULL,
`active_status` tinyint(1) DEFAULT NULL,
`sql_query` text,
`user_access` tinyint(1) DEFAULT '0',
PRIMARY KEY (`query_id`)
);

DROP TABLE IF EXISTS `art_log_alarm`;

CREATE TABLE `art_log_alarm` (
 `loggerPkId` varchar(256) NOT NULL,
  `alarmType` int(10) NOT NULL,
  `alarmData` longtext,
  `alarmVersion` int(10) NOT NULL,
  `created_by` varchar(64) DEFAULT '-1',
  `created_date` datetime DEFAULT '1900-01-01 00:00:00',
  `updated_by` varchar(64) DEFAULT '-1',
  `updated_date` datetime DEFAULT '1900-01-01 00:00:00',
  `version_id` int(11) DEFAULT '-1',
  `active_status` int(1) DEFAULT '1',
  `app_creator_id` varchar(64) NOT NULL,
  PRIMARY KEY (`loggerPkId`)
);

DROP TABLE IF EXISTS `Timezone`;

CREATE TABLE `Timezone` ( `timeZoneId` VARCHAR(64) NOT NULL, `utcdifference` INT(11) NOT NULL, `gmtLabel` VARCHAR(256) NULL DEFAULT NULL, `timeZoneLabel` VARCHAR(256) NULL DEFAULT NULL, `country` VARCHAR(256) NULL DEFAULT NULL, `cities` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`timeZoneId`));

DROP TABLE IF EXISTS `Language`;

CREATE TABLE `Language` ( `languageId` VARCHAR(64) NOT NULL, `language` VARCHAR(256) NOT NULL, `languageType` VARCHAR(32) NULL DEFAULT NULL, `languageDescription` VARCHAR(256) NULL DEFAULT NULL, `languageIcon` VARCHAR(128) NULL DEFAULT NULL, `alpha2` VARCHAR(2) NULL DEFAULT NULL, `alpha3` VARCHAR(3) NULL DEFAULT NULL, `alpha4` VARCHAR(4) NULL DEFAULT NULL, `alpha4parentid` INT(11) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`languageId`));

DROP TABLE IF EXISTS `Country`;

CREATE TABLE `Country` ( `countryId` VARCHAR(64) NOT NULL, `countryName` VARCHAR(128) NOT NULL, `countryCode1` VARCHAR(3) NULL DEFAULT NULL, `countryCode2` VARCHAR(3) NULL DEFAULT NULL, `countryFlag` VARCHAR(64) NULL DEFAULT NULL, `capital` VARCHAR(32) NULL DEFAULT NULL, `currencyCode` VARCHAR(3) NULL DEFAULT NULL, `currencyName` VARCHAR(128) NULL DEFAULT NULL, `currencySymbol` VARCHAR(32) NULL DEFAULT NULL, `capitalLatitude` INT(11) NULL DEFAULT NULL, `capitalLongitude` INT(11) NULL DEFAULT NULL, `isoNumeric` INT(11) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`countryId`));

DROP TABLE IF EXISTS `State`;

CREATE TABLE `State` ( `stateId` VARCHAR(64) NOT NULL, `countryId` VARCHAR(64) NOT NULL, `stateName` VARCHAR(256) NOT NULL, `stateCode` INT(2) NULL DEFAULT NULL, `stateCodeChar2` VARCHAR(32) NOT NULL, `stateCodeChar3` VARCHAR(32) NULL DEFAULT NULL, `stateDescription` VARCHAR(256) NULL DEFAULT NULL, `stateFlag` VARCHAR(128) NULL DEFAULT NULL, `stateCapital` VARCHAR(128) NULL DEFAULT NULL, `stateCapitalLatitude` INT(11) NULL DEFAULT NULL, `stateCapitalLongitude` INT(11) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`stateId`));

DROP TABLE IF EXISTS `City`;

CREATE TABLE `City` ( `cityId` VARCHAR(64) NOT NULL, `countryId` VARCHAR(64) NOT NULL, `stateId` VARCHAR(64) NOT NULL, `cityName` VARCHAR(256) NOT NULL, `cityCodeChar2` VARCHAR(32) NOT NULL, `cityCode` INT(3) NOT NULL, `cityDescription` VARCHAR(128) NULL DEFAULT NULL, `cityFlag` VARCHAR(128) NULL DEFAULT NULL, `cityLatitude` INT(11) NULL DEFAULT NULL, `cityLongitude` INT(11) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`cityId`));

DROP TABLE IF EXISTS `AddressType`;

CREATE TABLE `AddressType` ( `addressTypeId` VARCHAR(64) NOT NULL, `addressType` VARCHAR(128) NOT NULL, `addressTypeDesc` VARCHAR(256) NULL DEFAULT NULL, `addressTypeIcon` VARCHAR(128) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`addressTypeId`));

DROP TABLE IF EXISTS `Address`;

CREATE TABLE `Address` ( `addressId` VARCHAR(64) NOT NULL, `addressTypeId` VARCHAR(64) NOT NULL, `addressLabel` VARCHAR(11) NULL DEFAULT NULL, `address1` VARCHAR(256) NULL DEFAULT NULL, `address2` VARCHAR(256) NULL DEFAULT NULL, `address3` VARCHAR(256) NULL DEFAULT NULL, `countryId` VARCHAR(64) NOT NULL, `stateId` VARCHAR(64) NOT NULL, `cityId` VARCHAR(64) NOT NULL, `zipcode` VARCHAR(6) NOT NULL, `latitude` VARCHAR(64) NULL DEFAULT NULL, `longitude` VARCHAR(64) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`addressId`));

DROP TABLE IF EXISTS `ContactType`;

CREATE TABLE `ContactType` ( `contactTypeId` VARCHAR(64) NOT NULL, `contactType` VARCHAR(128) NOT NULL, `contactTypeDesc` VARCHAR(256) NULL DEFAULT NULL, `contactTypeIcon` VARCHAR(128) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`contactTypeId`));

DROP TABLE IF EXISTS `Gender`;

CREATE TABLE `Gender` ( `genderId` VARCHAR(64) NOT NULL, `gender` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`genderId`));

DROP TABLE IF EXISTS `Title`;

CREATE TABLE `Title` ( `titleId` VARCHAR(64) NOT NULL, `titles` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`titleId`));

DROP TABLE IF EXISTS `CoreContacts`;

CREATE TABLE `CoreContacts` ( `contactId` VARCHAR(64) NOT NULL, `titleId` VARCHAR(64) NOT NULL, `firstName` VARCHAR(256) NOT NULL, `middleName` VARCHAR(256) NULL DEFAULT NULL, `lastName` VARCHAR(256) NOT NULL, `nativeLanguageCode` VARCHAR(64) NULL DEFAULT NULL, `nativeTitle` VARCHAR(128) NULL DEFAULT NULL, `nativeFirstName` VARCHAR(256) NULL DEFAULT NULL, `nativeMiddleName` VARCHAR(256) NULL DEFAULT NULL, `nativeLastName` VARCHAR(256) NULL DEFAULT NULL, `genderId` VARCHAR(64) NOT NULL, `dateofbirth` TIMESTAMP NULL DEFAULT NULL, `age` INT(11) NULL DEFAULT NULL, `approximateDOB` TIMESTAMP NULL DEFAULT NULL, `emailId` VARCHAR(256) NOT NULL, `phoneNumber` VARCHAR(20) NOT NULL, `timeZoneId` VARCHAR(64) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`contactId`));

DROP TABLE IF EXISTS `AddressMap`;

CREATE TABLE `AddressMap` ( `addMapId` INT(11) NOT NULL AUTO_INCREMENT, `contactId` VARCHAR(64) NOT NULL, `addressId` VARCHAR(64) NOT NULL, PRIMARY KEY (`addMapId`));

DROP TABLE IF EXISTS `PasswordAlgo`;

CREATE TABLE `PasswordAlgo` ( `algoId` VARCHAR(64) NOT NULL, `algorithm` INT(11) NOT NULL, `algoName` VARCHAR(256) NOT NULL, `algoDescription` VARCHAR(256) NULL DEFAULT NULL, `algoIcon` VARCHAR(64) NULL DEFAULT NULL, `algoHelp` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`algoId`));

DROP TABLE IF EXISTS `PasswordPolicy`;

CREATE TABLE `PasswordPolicy` ( `policyId` VARCHAR(64) NOT NULL, `policyName` VARCHAR(256) NOT NULL, `policyDescription` VARCHAR(512) NULL DEFAULT NULL, `maxPwdLength` INT(11) NULL DEFAULT NULL, `minPwdLength` INT(11) NOT NULL, `minCapitalLetters` INT(11) NULL DEFAULT NULL, `minSmallLetters` INT(11) NULL DEFAULT NULL, `minNumericValues` INT(11) NULL DEFAULT NULL, `minSpecialLetters` INT(11) NULL DEFAULT NULL, `allowedSpecialLetters` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`policyId`));

DROP TABLE IF EXISTS `Question`;

CREATE TABLE `Question` ( `questionId` VARCHAR(64) NOT NULL, `levelid` INT(11) NOT NULL, `question` VARCHAR(256) NOT NULL, `questionDetails` TEXT NULL DEFAULT NULL, `questionIcon` VARCHAR(64) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`questionId`));

DROP TABLE IF EXISTS `UserAccessLevel`;

CREATE TABLE `UserAccessLevel` ( `userAccessLevelId` VARCHAR(64) NOT NULL, `userAccessLevel` INT(11) NOT NULL, `levelName` VARCHAR(256) NOT NULL, `levelDescription` VARCHAR(256) NOT NULL, `levelHelp` VARCHAR(2048) NULL DEFAULT NULL, `levelIcon` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`userAccessLevelId`), UNIQUE KEY (`userAccessLevel`));

DROP TABLE IF EXISTS `UserAccessDomain`;

CREATE TABLE `UserAccessDomain` ( `userAccessDomainId` VARCHAR(64) NOT NULL, `userAccessDomain` INT(11) NOT NULL, `domainName` VARCHAR(256) NOT NULL, `domainDescription` VARCHAR(256) NOT NULL, `domainHelp` VARCHAR(2048) NULL DEFAULT NULL, `domainIcon` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`userAccessDomainId`), UNIQUE KEY (`userAccessDomain`));

DROP TABLE IF EXISTS `UserDetail`;

CREATE TABLE `UserDetail` ( `userId` VARCHAR(64) NOT NULL, `userAccessCode` INT(11) NULL DEFAULT NULL, `userAccessLevelId` VARCHAR(64) NULL DEFAULT NULL, `userAccessDomainId` VARCHAR(64) NULL DEFAULT NULL, `multiFactorAuthEnabled` INT(1) NULL DEFAULT NULL, `genTempOneTimeAuth` INT(1) NULL DEFAULT NULL, `allowMultipleLogin` INT(1) NULL DEFAULT NULL, `isLocked` INT(1) NULL DEFAULT NULL, `isDeleted` INT(1) NULL DEFAULT NULL, `changeAuthNextLogin` INT(1) NULL DEFAULT NULL, `authExpiryDate` TIMESTAMP NULL DEFAULT NULL, `authAlgo` VARCHAR(64) NULL DEFAULT '1', `lastAuthChangeDate` TIMESTAMP NULL DEFAULT NULL, `sessionTimeout` INT(11) NULL DEFAULT '1800', `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`userId`));

DROP TABLE IF EXISTS `Login`;

CREATE TABLE `Login` ( `loginPk` VARCHAR(64) NOT NULL, `loginId` VARCHAR(200) NOT NULL, `serverAuthImage` VARCHAR(64) NULL DEFAULT NULL, `serverAuthText` VARCHAR(32) NULL DEFAULT NULL, `userId` VARCHAR(64) NOT NULL, `contactId` VARCHAR(64) NOT NULL, `failedLoginAttempts` INT(11) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`loginPk`));

DROP TABLE IF EXISTS `LoginSession`;

CREATE TABLE `LoginSession` ( `AppSessionId` VARCHAR(256) NOT NULL, `userId` VARCHAR(64) NOT NULL, `AppServerSessionId` VARCHAR(256) NOT NULL, `loginTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, `lastAccessTime` TIMESTAMP NULL DEFAULT NULL, `logoutTime` TIMESTAMP NULL, `clientIPAddress` VARCHAR(128) NOT NULL, `clientIPAddressInt` BIGINT NULL DEFAULT NULL, `clientNetworkAddress` INT(11) NULL DEFAULT NULL, `clientBrowser` VARCHAR(256) NOT NULL, `sessionData` TEXT NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`AppSessionId`));

DROP TABLE IF EXISTS `PassRecovery`;

CREATE TABLE `PassRecovery` ( `passRecoveryId` VARCHAR(64) NOT NULL, `userId` VARCHAR(64) NOT NULL, `questionId` VARCHAR(64) NOT NULL, `answer` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`passRecoveryId`));

DROP TABLE IF EXISTS `UserData`;

CREATE TABLE `UserData` ( `userDataId` VARCHAR(64) NOT NULL, `userId` VARCHAR(64) NOT NULL, `password` VARCHAR(512) NOT NULL, `oneTimePassword` VARCHAR(32) NULL DEFAULT NULL, `oneTimePasswordExpiry` INT(11) NULL DEFAULT NULL, `oneTimePasswordGenDate` TIMESTAMP NULL DEFAULT NULL, `last5Passwords` VARCHAR(500) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`userDataId`));

DROP TABLE IF EXISTS `SessionData`;

CREATE TABLE `SessionData` ( `dataId` VARCHAR(256) NOT NULL, `customerId` VARCHAR(64) NULL DEFAULT NULL, `userId` VARCHAR(64) NOT NULL, `sessionKey` VARCHAR(64) NOT NULL, `dataType` INT(10) NOT NULL, `numberValue` INT(10) NULL DEFAULT NULL, `dateTimeValue` TIMESTAMP NULL DEFAULT NULL, `stringValue` VARCHAR(2000) NULL DEFAULT NULL, `booleanValue` TINYINT(1) NULL DEFAULT NULL, `jsonValue` TEXT NULL DEFAULT NULL, `appSessionId` VARCHAR(256) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`dataId`));

DROP TABLE IF EXISTS `Roles`;

CREATE TABLE `Roles` ( `roleId` VARCHAR(64) NOT NULL, `RoleName` VARCHAR(256) NOT NULL, `RoleDescription` VARCHAR(256) NOT NULL, `RoleIcon` VARCHAR(64) NULL DEFAULT NULL, `roleHelp` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`roleId`));

DROP TABLE IF EXISTS `AppMenus`;

CREATE TABLE `AppMenus` ( `menuId` VARCHAR(64) NOT NULL, `menuTreeId` VARCHAR(256) NOT NULL, `menuIcon` VARCHAR(256) NULL DEFAULT NULL, `menuAction` VARCHAR(256) NULL DEFAULT NULL, `menuCommands` VARCHAR(64) NULL DEFAULT NULL, `menuDisplay` TINYINT(1) NOT NULL, `menuHead` TINYINT(1) NOT NULL, `menuLabel` VARCHAR(256) NOT NULL, `uiType` VARCHAR(3) NULL DEFAULT NULL, `RefObjectId` VARCHAR(256) NULL DEFAULT NULL, `menuAccessRights` INT(11) NOT NULL, `appType` INT(1) NULL DEFAULT NULL, `appId` VARCHAR(256) NULL DEFAULT NULL, `autoSave` TINYINT(1) NOT NULL, `startDate` TIMESTAMP NULL DEFAULT NULL, `expiryDate` TIMESTAMP NULL DEFAULT NULL, `goLiveDate` TIMESTAMP NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`menuId`));

DROP TABLE IF EXISTS `RoleMenuBridge`;

CREATE TABLE `RoleMenuBridge` ( `roleMenuMapId` VARCHAR(64) NOT NULL, `roleId` VARCHAR(64) NOT NULL, `menuId` VARCHAR(64) NOT NULL, `isRead` TINYINT(1) NOT NULL, `isWrite` TINYINT(1) NOT NULL, `isExecute` TINYINT(1) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`roleMenuMapId`));

DROP TABLE IF EXISTS `UserRoleBridge`;

CREATE TABLE `UserRoleBridge` ( `roleUserMapId` VARCHAR(64) NOT NULL, `roleId` VARCHAR(64) NOT NULL, `userId` VARCHAR(64) NOT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`roleUserMapId`));

DROP TABLE IF EXISTS `PublicApi`;

CREATE TABLE `PublicApi` ( `apiId` VARCHAR(256) NOT NULL, `apiData` VARCHAR(256) NOT NULL, `schedulerDetails` VARCHAR(256) NULL DEFAULT NULL, `createdBy` VARCHAR(64) NULL DEFAULT '-1', `createdDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `updatedBy` VARCHAR(64) NULL DEFAULT '-1', `updatedDate` TIMESTAMP NULL DEFAULT '2000-01-01 10:10:10', `versionId` INT(10) NULL DEFAULT '-1', `activeStatus` INT(1) NULL DEFAULT '1', `txnAccessCode` INT(10) NULL DEFAULT NULL, PRIMARY KEY (`apiId`));

ALTER TABLE `State` ADD CONSTRAINT FOREIGN KEY (`countryId`) REFERENCES `Country`(`countryId`);

ALTER TABLE `City` ADD CONSTRAINT FOREIGN KEY (`stateId`) REFERENCES `State`(`stateId`);

ALTER TABLE `City` ADD CONSTRAINT FOREIGN KEY (`countryId`) REFERENCES `Country`(`countryId`);

ALTER TABLE `Address` ADD CONSTRAINT FOREIGN KEY (`stateId`) REFERENCES `State`(`stateId`);

ALTER TABLE `Address` ADD CONSTRAINT FOREIGN KEY (`countryId`) REFERENCES `Country`(`countryId`);

ALTER TABLE `Address` ADD CONSTRAINT FOREIGN KEY (`cityId`) REFERENCES `City`(`cityId`);

ALTER TABLE `Address` ADD CONSTRAINT FOREIGN KEY (`addressTypeId`) REFERENCES `AddressType`(`addressTypeId`);

ALTER TABLE `CoreContacts` ADD CONSTRAINT FOREIGN KEY (`timeZoneId`) REFERENCES `Timezone`(`timeZoneId`);

ALTER TABLE `CoreContacts` ADD CONSTRAINT FOREIGN KEY (`nativeLanguageCode`) REFERENCES `Language`(`languageId`);

ALTER TABLE `CoreContacts` ADD CONSTRAINT FOREIGN KEY (`titleId`) REFERENCES `Title`(`titleId`);

ALTER TABLE `CoreContacts` ADD CONSTRAINT FOREIGN KEY (`genderId`) REFERENCES `Gender`(`genderId`);

ALTER TABLE `AddressMap` ADD CONSTRAINT FOREIGN KEY (`contactId`) REFERENCES `CoreContacts`(`contactId`);

ALTER TABLE `AddressMap` ADD CONSTRAINT FOREIGN KEY (`addressId`) REFERENCES `Address`(`addressId`);

ALTER TABLE `UserDetail` ADD CONSTRAINT FOREIGN KEY (`userAccessLevelId`) REFERENCES `UserAccessLevel`(`userAccessLevelId`);

ALTER TABLE `UserDetail` ADD CONSTRAINT FOREIGN KEY (`userAccessDomainId`) REFERENCES `UserAccessDomain`(`userAccessDomainId`);

ALTER TABLE `Login` ADD CONSTRAINT FOREIGN KEY (`userId`) REFERENCES `UserDetail`(`userId`);

ALTER TABLE `Login` ADD CONSTRAINT FOREIGN KEY (`contactId`) REFERENCES `CoreContacts`(`contactId`);

ALTER TABLE `LoginSession` ADD CONSTRAINT FOREIGN KEY (`userId`) REFERENCES `UserDetail`(`userId`);

ALTER TABLE `PassRecovery` ADD CONSTRAINT FOREIGN KEY (`userId`) REFERENCES `UserDetail`(`userId`);

ALTER TABLE `PassRecovery` ADD CONSTRAINT FOREIGN KEY (`questionId`) REFERENCES `Question`(`questionId`);

ALTER TABLE `UserData` ADD CONSTRAINT FOREIGN KEY (`userId`) REFERENCES `UserDetail`(`userId`);

ALTER TABLE `RoleMenuBridge` ADD CONSTRAINT FOREIGN KEY (`menuId`) REFERENCES `AppMenus`(`menuId`);

ALTER TABLE `RoleMenuBridge` ADD CONSTRAINT FOREIGN KEY (`roleId`) REFERENCES `Roles`(`roleId`);

ALTER TABLE `UserRoleBridge` ADD CONSTRAINT FOREIGN KEY (`userId`) REFERENCES `UserDetail`(`userId`);

ALTER TABLE `UserRoleBridge` ADD CONSTRAINT FOREIGN KEY (`roleId`) REFERENCES `Roles`(`roleId`);

