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

