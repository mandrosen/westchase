-- MySQL dump 10.13  Distrib 5.1.40, for Win64 (unknown)
--
-- Host: localhost    Database: westchase
-- ------------------------------------------------------
-- Server version	5.1.40-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `audit`
--

DROP TABLE IF EXISTS `audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `employee` int(10) unsigned NOT NULL,
  `object` varchar(255) NOT NULL,
  `object_id` bigint(20) unsigned NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_description` longtext NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_audit_employee` (`employee`),
  CONSTRAINT `FK_audit_employee` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6486 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `audit_company`
--

DROP TABLE IF EXISTS `audit_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit_company` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `company` int(10) NOT NULL,
  `employee` int(10) unsigned NOT NULL,
  `update_description` longtext NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_audit_company_company` (`company`),
  KEY `FK_audit_company_employee` (`employee`),
  CONSTRAINT `FK_audit_company_employee` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5127 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `audit_employee`
--

DROP TABLE IF EXISTS `audit_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit_employee` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `employee_edited` int(10) unsigned NOT NULL,
  `employee` int(10) unsigned NOT NULL,
  `update_description` longtext NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_audit_employee_employee` (`employee`),
  CONSTRAINT `FK_audit_employee_employee` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `audit_phone_book`
--

DROP TABLE IF EXISTS `audit_phone_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit_phone_book` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `phone_book` int(10) NOT NULL,
  `employee` int(10) unsigned NOT NULL,
  `update_description` longtext NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_audit_phone_book_pb` (`phone_book`),
  KEY `FK_audit_phone_book_employee` (`employee`),
  CONSTRAINT `FK_audit_phone_book_employee` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11363 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `audit_property`
--

DROP TABLE IF EXISTS `audit_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit_property` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `property` int(10) NOT NULL,
  `employee` int(10) unsigned NOT NULL,
  `update_description` longtext NOT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_audit_property_prop` (`property`),
  KEY `FK_audit_property_emp` (`employee`),
  CONSTRAINT `FK_audit_property_emp` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1025 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `CategoryCode` varchar(2) NOT NULL,
  `CategoryDesc` varchar(40) DEFAULT NULL,
  `InputDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CategoryCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `citizen`
--

DROP TABLE IF EXISTS `citizen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `citizen` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `tx_dl` int(11) DEFAULT NULL,
  `organization` varchar(255) DEFAULT NULL,
  `street_address` varchar(255) DEFAULT NULL,
  `zip_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmu_apartment`
--

DROP TABLE IF EXISTS `cmu_apartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmu_apartment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `quarter` int(10) unsigned NOT NULL,
  `property` int(10) NOT NULL,
  `completed_by` varchar(255) DEFAULT NULL,
  `occupancy_rate` double DEFAULT NULL,
  `community_mgr` varchar(255) DEFAULT NULL,
  `community_mgr_email` varchar(255) DEFAULT NULL,
  `community_mgr_phone` varchar(20) DEFAULT NULL,
  `community_mgr_fax` varchar(20) DEFAULT NULL,
  `mgmt_company` varchar(255) DEFAULT NULL,
  `mgmt_company_addr` varchar(255) DEFAULT NULL,
  `mgmt_company_phone` varchar(20) DEFAULT NULL,
  `mgmt_company_fax` varchar(20) DEFAULT NULL,
  `supervisor` varchar(255) DEFAULT NULL,
  `supervisor_email` varchar(255) DEFAULT NULL,
  `supervisor_phone` varchar(20) DEFAULT NULL,
  `supervisor_fax` varchar(20) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `owner_address` varchar(255) DEFAULT NULL,
  `owner_phone` varchar(20) DEFAULT NULL,
  `owner_fax` varchar(20) DEFAULT NULL,
  `comments` longtext,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `verified` datetime DEFAULT NULL,
  `static_info_correct` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_cmu_apartment_quarter` (`quarter`),
  CONSTRAINT `FK_cmu_apartment_quarter` FOREIGN KEY (`quarter`) REFERENCES `cmu_quarter` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=792 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmu_devsite`
--

DROP TABLE IF EXISTS `cmu_devsite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmu_devsite` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `quarter` int(10) unsigned NOT NULL,
  `property` int(10) NOT NULL,
  `completed_by` varchar(255) DEFAULT NULL,
  `site_size` double DEFAULT NULL,
  `frontage` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `company` varchar(255) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `divide` tinyint(1) NOT NULL DEFAULT '0',
  `price_sq_ft` varchar(20) DEFAULT NULL,
  `restrictions` longtext,
  `comments` longtext,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `verified` datetime DEFAULT NULL,
  `static_info_correct` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_cmu_devsite_quarter` (`quarter`),
  CONSTRAINT `FK_cmu_devsite_quarter` FOREIGN KEY (`quarter`) REFERENCES `cmu_quarter` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmu_hotel`
--

DROP TABLE IF EXISTS `cmu_hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmu_hotel` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `quarter` int(10) unsigned NOT NULL,
  `property` int(10) NOT NULL,
  `completed_by` varchar(255) DEFAULT NULL,
  `general_mgr` varchar(255) DEFAULT NULL,
  `general_mgr_email` varchar(255) DEFAULT NULL,
  `general_mgr_phone` varchar(20) DEFAULT NULL,
  `occupancy` double DEFAULT NULL,
  `comments` longtext,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `verified` datetime DEFAULT NULL,
  `static_info_correct` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_cmu_hotel_quarter` (`quarter`),
  CONSTRAINT `FK_cmu_hotel_quarter` FOREIGN KEY (`quarter`) REFERENCES `cmu_quarter` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=280 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmu_lease`
--

DROP TABLE IF EXISTS `cmu_lease`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmu_lease` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `quarter` int(10) unsigned NOT NULL,
  `property` int(10) NOT NULL,
  `tenant_name` varchar(255) DEFAULT NULL,
  `sq_ft` double DEFAULT NULL,
  `lease_trans_type` tinyint(1) unsigned NOT NULL,
  `owners_rep` varchar(255) DEFAULT NULL,
  `tenants_rep` varchar(255) DEFAULT NULL,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `verified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cmu_lease_transtype` (`lease_trans_type`),
  KEY `FK_cmu_lease_quarter` (`quarter`),
  KEY `FK_cmu_lease_property` (`property`),
  CONSTRAINT `FK_cmu_lease_quarter` FOREIGN KEY (`quarter`) REFERENCES `cmu_quarter` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_cmu_lease_transtype` FOREIGN KEY (`lease_trans_type`) REFERENCES `cmu_transaction_type` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=441 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmu_office_retail_svc`
--

DROP TABLE IF EXISTS `cmu_office_retail_svc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmu_office_retail_svc` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `quarter` int(10) unsigned NOT NULL,
  `property` int(10) NOT NULL,
  `completed_by` varchar(255) DEFAULT NULL,
  `for_sale` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `for_sale_contact` varchar(255) DEFAULT NULL,
  `for_sale_phone` varchar(20) DEFAULT NULL,
  `sq_ft_for_lease` double DEFAULT NULL,
  `occupancy` double DEFAULT NULL,
  `occupied` double DEFAULT NULL,
  `largest_space` double DEFAULT NULL,
  `largest_space_6mths` double DEFAULT NULL,
  `largest_space_12mths` double DEFAULT NULL,
  `property_mgr` varchar(255) DEFAULT NULL,
  `property_mgr_phone` varchar(20) DEFAULT NULL,
  `property_mgr_fax` varchar(20) DEFAULT NULL,
  `property_mgr_email` varchar(255) DEFAULT NULL,
  `mgmt_company` varchar(255) DEFAULT NULL,
  `mgmt_company_addr` varchar(255) DEFAULT NULL,
  `leasing_company` varchar(255) DEFAULT NULL,
  `leasing_company_addr` varchar(255) DEFAULT NULL,
  `leasing_agent` varchar(255) DEFAULT NULL,
  `leasing_agent_phone` varchar(20) DEFAULT NULL,
  `leasing_agent_fax` varchar(20) DEFAULT NULL,
  `leasing_agent_email` varchar(255) DEFAULT NULL,
  `comments` longtext,
  `updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `verified` datetime DEFAULT NULL,
  `static_info_correct` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_cmu_office_quarter` (`quarter`),
  CONSTRAINT `FK_cmu_office_quarter` FOREIGN KEY (`quarter`) REFERENCES `cmu_quarter` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2410 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmu_quarter`
--

DROP TABLE IF EXISTS `cmu_quarter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmu_quarter` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(45) DEFAULT NULL,
  `year` int(4) unsigned NOT NULL,
  `quarter_num` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cmu_transaction_type`
--

DROP TABLE IF EXISTS `cmu_transaction_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmu_transaction_type` (
  `id` tinyint(1) unsigned NOT NULL AUTO_INCREMENT,
  `Description` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Company` varchar(100) NOT NULL,
  `StNumber` varchar(50) DEFAULT NULL,
  `StAddress` varchar(150) NOT NULL,
  `RoomNo` varchar(50) DEFAULT NULL,
  `City` varchar(100) NOT NULL,
  `State` varchar(2) NOT NULL,
  `ZipCode` varchar(10) DEFAULT NULL,
  `WkPhone` varchar(15) NOT NULL,
  `FaxPhone` varchar(15) DEFAULT NULL,
  `Latitude` varchar(20) DEFAULT NULL,
  `Longitude` varchar(20) DEFAULT NULL,
  `CompanyType` int(10) unsigned DEFAULT NULL,
  `NoEmployees` int(11) DEFAULT '0',
  `Classification` varchar(50) NOT NULL,
  `SubClassification` varchar(50) DEFAULT NULL,
  `NAICS` varchar(5) NOT NULL,
  `HCAD` varchar(50) DEFAULT NULL,
  `MapNo` varchar(50) DEFAULT NULL,
  `Other` varchar(250) DEFAULT NULL,
  `Owner` varchar(50) DEFAULT NULL,
  `Vendor` tinyint(1) DEFAULT '0',
  `Center` varchar(50) DEFAULT NULL,
  `SquareFeet` int(11) DEFAULT '0',
  `InputDate` datetime DEFAULT NULL,
  `LastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Website` text,
  `Notes` text,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `CompanyId` (`id`) USING BTREE,
  KEY `FK_company_companytype` (`CompanyType`),
  CONSTRAINT `FK_company_companytype` FOREIGN KEY (`CompanyType`) REFERENCES `company_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9954 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company_mapno`
--

DROP TABLE IF EXISTS `company_mapno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_mapno` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `CompanyID` int(10) NOT NULL,
  `primary` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `MapNo` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `COMPANYID` (`CompanyID`),
  KEY `FK51FF59DB2AF68FE` (`CompanyID`),
  KEY `FK_company_mapno_property` (`MapNo`),
  CONSTRAINT `FK_company_mapno_company` FOREIGN KEY (`CompanyID`) REFERENCES `company` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_company_mapno_property` FOREIGN KEY (`MapNo`) REFERENCES `property` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1716 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `company_type`
--

DROP TABLE IF EXISTS `company_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `email_template`
--

DROP TABLE IF EXISTS `email_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_template` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `employee` int(10) unsigned NOT NULL,
  `subject` varchar(1000) NOT NULL,
  `message` longtext NOT NULL,
  `attachment` longblob,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `attachment_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_email_template_employee` (`employee`),
  CONSTRAINT `FK_email_template_employee` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `ext` varchar(4) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `flag_size`
--

DROP TABLE IF EXISTS `flag_size`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flag_size` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hcad`
--

DROP TABLE IF EXISTS `hcad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hcad` (
  `TaxYear` int(11) DEFAULT NULL,
  `HCADAccount` varchar(13) NOT NULL,
  `OwnerName` varchar(26) DEFAULT NULL,
  `OwnerAddress1` varchar(26) DEFAULT NULL,
  `OwnerAddress2` varchar(26) DEFAULT NULL,
  `OwnerAddress3` varchar(26) DEFAULT NULL,
  `CityState` varchar(26) DEFAULT NULL,
  `OwnerZip` varchar(9) DEFAULT NULL,
  `PropIdentifier` varchar(20) DEFAULT NULL,
  `PropDescription1` varchar(30) DEFAULT NULL,
  `PropDescription2` varchar(30) DEFAULT NULL,
  `PropDescription3` varchar(30) DEFAULT NULL,
  `PropDescription4` varchar(30) DEFAULT NULL,
  `Acres` decimal(18,4) DEFAULT '0.0000',
  `SquareFeet` int(11) DEFAULT NULL,
  `LocStreetNum` varchar(5) DEFAULT NULL,
  `LocStreetName` varchar(30) DEFAULT NULL,
  `LocBuildingCode` varchar(7) DEFAULT NULL,
  `LocSuiteCode` varchar(6) DEFAULT NULL,
  `LocZip` varchar(50) DEFAULT NULL,
  `JurCode1` varchar(3) DEFAULT NULL,
  `JurCode2` varchar(3) DEFAULT NULL,
  `JurCode3` varchar(3) DEFAULT NULL,
  `JurCode4` varchar(3) DEFAULT NULL,
  `JurCode5` varchar(3) DEFAULT NULL,
  `JurCode6` varchar(3) DEFAULT NULL,
  `JurCode7` varchar(3) DEFAULT NULL,
  `JurCode8` varchar(3) DEFAULT NULL,
  `JurCode9` varchar(3) DEFAULT NULL,
  `JurCode10` varchar(3) DEFAULT NULL,
  `NoticeFlag` varchar(1) DEFAULT NULL,
  `NoticeDate` varchar(8) DEFAULT NULL,
  `ARBDate` varchar(8) DEFAULT NULL,
  `HitsDate` varchar(8) DEFAULT NULL,
  `HitsValue` int(11) DEFAULT NULL,
  `MultiAgentFlag` varchar(1) DEFAULT NULL,
  `ParcelNumber` varchar(23) DEFAULT NULL,
  `StateCatCode` varchar(2) DEFAULT NULL,
  `PPTCode` varchar(2) DEFAULT NULL,
  `SICCode` varchar(5) DEFAULT NULL,
  `ICCode` varchar(1) DEFAULT NULL,
  `RegionCenter` varchar(2) DEFAULT NULL,
  `MapFacet` varchar(7) DEFAULT NULL,
  `PersPropValue` int(11) DEFAULT NULL,
  `LandValue` int(11) DEFAULT NULL,
  `ImprovementsValue` int(11) DEFAULT NULL,
  `ProductivityValue` int(11) DEFAULT NULL,
  `ProductivityType` varchar(1) DEFAULT NULL,
  `SpecialCode` varchar(1) DEFAULT NULL,
  `HMSTDXMPTCode` varchar(1) DEFAULT NULL,
  `PctHMSTDValue` int(11) DEFAULT NULL,
  `VetPCT1` int(11) DEFAULT NULL,
  `VetPCT2` int(11) DEFAULT NULL,
  `SPCLXMPTCode` varchar(1) DEFAULT NULL,
  `SPCLXMPTValue` int(11) DEFAULT '0',
  `SPCLXMPTPCT` varchar(3) DEFAULT NULL,
  `HTSFlag` varchar(1) DEFAULT NULL,
  `PTSFlag` varchar(1) DEFAULT NULL,
  `BPSFlag` varchar(1) DEFAULT NULL,
  `RealPropRef` varchar(13) DEFAULT NULL,
  `ExemptValue` int(11) DEFAULT NULL,
  `TaxableValue` int(11) DEFAULT NULL,
  `ADJCADCode` varchar(3) DEFAULT NULL,
  `ADJCADAcct` varchar(10) DEFAULT NULL,
  `ADJCADLandValue` int(11) DEFAULT NULL,
  `ADJCADIMPRValue` int(11) DEFAULT NULL,
  `ADJCADPersValue` int(11) DEFAULT NULL,
  `NBHD` varchar(5) DEFAULT NULL,
  `LUC` varchar(3) DEFAULT NULL,
  `AgentNum` varchar(5) DEFAULT NULL,
  `AgentAddr1` varchar(26) DEFAULT NULL,
  `MKTLandValue` int(11) DEFAULT NULL,
  `MKTIMPRValue` int(11) DEFAULT NULL,
  `RendFlag` varchar(1) DEFAULT NULL,
  `CAMAMktValue` int(11) DEFAULT NULL,
  `LSTVisitDate` varchar(8) DEFAULT NULL,
  `ActualPURDate` varchar(8) DEFAULT NULL,
  `EffectPURDate` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`HCADAccount`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hpd_news_release`
--

DROP TABLE IF EXISTS `hpd_news_release`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hpd_news_release` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `release_date` date NOT NULL,
  `title` varchar(500) NOT NULL,
  `found_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `url` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3001 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mapno`
--

DROP TABLE IF EXISTS `mapno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mapno` (
  `PersonId` int(11) NOT NULL DEFAULT '0',
  `MapNo` varchar(50) NOT NULL,
  `HCAD` varchar(50) DEFAULT NULL,
  `InputDate` datetime DEFAULT NULL,
  KEY `HCAD` (`HCAD`),
  KEY `MapNo` (`MapNo`),
  KEY `Person Id` (`PersonId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `employee` int(10) unsigned NOT NULL,
  `due_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `expire_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `text` longtext NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_message_employee` (`employee`),
  CONSTRAINT `FK_message_employee` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `naics`
--

DROP TABLE IF EXISTS `naics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `naics` (
  `Code` varchar(5) NOT NULL,
  `Description` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`Code`),
  KEY `Code` (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `officer`
--

DROP TABLE IF EXISTS `officer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `officer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `badge` int(11) DEFAULT NULL,
  `cell_phone` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patrol_activity`
--

DROP TABLE IF EXISTS `patrol_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrol_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `officer_id` int(11) DEFAULT NULL,
  `patrol_officer_count` int(10) unsigned NOT NULL DEFAULT '1',
  `patrol_type_id` int(11) NOT NULL,
  `patrol_type_desc` varchar(255) DEFAULT NULL,
  `patrol_phone_id` int(11) DEFAULT NULL,
  `patrol_shop_id` int(11) NOT NULL,
  `patrol_shop_comments` varchar(255) DEFAULT NULL,
  `start_date_time` datetime NOT NULL,
  `end_date_time` datetime NOT NULL,
  `start_miles` int(11) NOT NULL,
  `end_miles` int(11) NOT NULL,
  `crime_arrests_felony` int(11) NOT NULL DEFAULT '0',
  `crime_arrests_class_ab_misdemeanor` int(11) NOT NULL DEFAULT '0',
  `crime_arrests_class_c_ticket` int(11) NOT NULL DEFAULT '0',
  `crime_arrests_traffic_drt` int(11) NOT NULL DEFAULT '0',
  `warrants_city` int(11) NOT NULL DEFAULT '0',
  `warrants_felony` int(11) NOT NULL DEFAULT '0',
  `warrants_misdemeanor` int(11) NOT NULL DEFAULT '0',
  `warrants_setcic` int(11) NOT NULL DEFAULT '0',
  `drt_investigations_warnings` int(11) NOT NULL DEFAULT '0',
  `drt_investigations_abatements` int(11) NOT NULL DEFAULT '0',
  `drt_investigations_tickets` int(11) NOT NULL DEFAULT '0',
  `drt_investigations_offense_reports` int(11) NOT NULL DEFAULT '0',
  `field_parking` int(11) NOT NULL DEFAULT '0',
  `field_charges_filed` int(11) NOT NULL DEFAULT '0',
  `field_suspects_in_jail` int(11) NOT NULL DEFAULT '0',
  `field_holds` int(11) NOT NULL DEFAULT '0',
  `field_traffic_stops` int(11) NOT NULL DEFAULT '0',
  `traffic_moving` int(11) NOT NULL DEFAULT '0',
  `traffic_non_moving` int(11) NOT NULL DEFAULT '0',
  `hike_patrolled_date_time_start1` datetime DEFAULT NULL,
  `hike_patrolled_date_time_end1` datetime DEFAULT NULL,
  `hike_patrolled_date_time_start2` datetime DEFAULT NULL,
  `hike_patrolled_date_time_end2` datetime DEFAULT NULL,
  `hike_patrolled_date_time_start3` datetime DEFAULT NULL,
  `hike_patrolled_date_time_end3` datetime DEFAULT NULL,
  `primary_calls` int(11) NOT NULL DEFAULT '0',
  `secondary_calls` int(11) NOT NULL DEFAULT '0',
  `on_views_flagged_down` int(11) NOT NULL DEFAULT '0',
  `incident_reports` int(11) NOT NULL DEFAULT '0',
  `accident_reports` int(11) NOT NULL DEFAULT '0',
  `supplement_reports` int(11) NOT NULL DEFAULT '0',
  `crime_initiatives` int(11) NOT NULL DEFAULT '0',
  `crime_initiatives_in_wc_vehicle` int(11) NOT NULL DEFAULT '0',
  `admin_assignments` int(11) NOT NULL DEFAULT '0',
  `am_checklist_completed` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `business_checks_completed_east` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `business_checks_completed_west` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `community_apartment_liaison_meetings` int(11) NOT NULL DEFAULT '0',
  `community_hotel_liaison_meetings` int(11) NOT NULL DEFAULT '0',
  `community_retail_liaison_meetings` int(11) NOT NULL DEFAULT '0',
  `community_office_building_liason_meetings` int(11) NOT NULL DEFAULT '0',
  `community_citizen_contacts` int(11) NOT NULL DEFAULT '0',
  `community_crime_prevention_pamphlets` int(11) NOT NULL DEFAULT '0',
  `community_events` int(11) NOT NULL DEFAULT '0',
  `community_cpted_inspections` int(11) NOT NULL DEFAULT '0',
  `community_crime_prevention_seminars` int(11) NOT NULL DEFAULT '0',
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_officer_activity_officer_idx` (`officer_id`),
  KEY `fk_officer_activity_patrol_type_idx` (`patrol_type_id`),
  KEY `fk_officer_patrol_phone_idx` (`patrol_phone_id`),
  KEY `fk_officer_patrol_shop_idx` (`patrol_shop_id`),
  CONSTRAINT `fk_officer_patrol_officer` FOREIGN KEY (`officer_id`) REFERENCES `officer` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_officer_patrol_patrol_type` FOREIGN KEY (`patrol_type_id`) REFERENCES `patrol_type` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_officer_patrol_phone` FOREIGN KEY (`patrol_phone_id`) REFERENCES `patrol_phone` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_officer_patrol_shop` FOREIGN KEY (`patrol_shop_id`) REFERENCES `patrol_shop` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4208 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patrol_activity_detail`
--

DROP TABLE IF EXISTS `patrol_activity_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrol_activity_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `patrol_activity_id` bigint(20) NOT NULL,
  `patrol_detail_category_id` int(11) NOT NULL,
  `received_time` time DEFAULT NULL,
  `arrived_time` time DEFAULT NULL,
  `cleared_time` time DEFAULT NULL,
  `received_date_time` datetime DEFAULT NULL,
  `arrived_date_time` datetime DEFAULT NULL,
  `cleared_date_time` datetime DEFAULT NULL,
  `officer_role` enum('Primary','Secondary') DEFAULT NULL,
  `patrol_detail_type_id` int(11) NOT NULL,
  `disposition` varchar(255) DEFAULT NULL,
  `incident_id` varchar(255) DEFAULT NULL,
  `location_property_id` int(10) DEFAULT NULL,
  `location_desc` varchar(255) DEFAULT NULL,
  `comments` longtext NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_officer_patrol_activity_partrol_idx` (`patrol_activity_id`),
  KEY `fk_patrol_detail_category_idx` (`patrol_detail_category_id`),
  KEY `fk_patrol_detail_type_idx` (`patrol_detail_type_id`),
  KEY `fk_patrol_detail_property_idx` (`location_property_id`),
  CONSTRAINT `fk_officer_patrol_activity_partrol` FOREIGN KEY (`patrol_activity_id`) REFERENCES `patrol_activity` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_patrol_detail_category` FOREIGN KEY (`patrol_detail_category_id`) REFERENCES `patrol_detail_category` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_patrol_detail_location` FOREIGN KEY (`location_property_id`) REFERENCES `property` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_patrol_detail_type` FOREIGN KEY (`patrol_detail_type_id`) REFERENCES `patrol_detail_type` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6796 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patrol_activity_detail_citizen`
--

DROP TABLE IF EXISTS `patrol_activity_detail_citizen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrol_activity_detail_citizen` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `patrol_activity_detail_id` bigint(20) NOT NULL,
  `citizen_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_patrol_detail_detail_idx` (`patrol_activity_detail_id`),
  KEY `fk_partol_detail_citizen_idx` (`citizen_id`),
  CONSTRAINT `fk_partol_detail_citizen` FOREIGN KEY (`citizen_id`) REFERENCES `citizen` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_patrol_detail_detail` FOREIGN KEY (`patrol_activity_detail_id`) REFERENCES `patrol_activity_detail` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patrol_activity_hotspot`
--

DROP TABLE IF EXISTS `patrol_activity_hotspot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrol_activity_hotspot` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `patrol_activity_id` bigint(20) NOT NULL,
  `hotspot_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_patrol_activity_hotspot_activity` (`patrol_activity_id`),
  KEY `FK_patrol_activity_hotspot_hotspot` (`hotspot_id`),
  CONSTRAINT `FK_patrol_activity_hotspot_activity` FOREIGN KEY (`patrol_activity_id`) REFERENCES `patrol_activity` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_patrol_activity_hotspot_hotspot` FOREIGN KEY (`hotspot_id`) REFERENCES `patrol_hotspot` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patrol_activity_officer`
--

DROP TABLE IF EXISTS `patrol_activity_officer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrol_activity_officer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `patrol_activity_id` bigint(20) NOT NULL,
  `officer_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_patrol_activity_officer_patrol` (`patrol_activity_id`),
  KEY `fk_patrol_activity_officer_officer` (`officer_id`),
  CONSTRAINT `fk_patrol_activity_officer_patrol` FOREIGN KEY (`patrol_activity_id`) REFERENCES `patrol_activity` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_patrol_activity_officer_officer` FOREIGN KEY (`officer_id`) REFERENCES `officer` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8195 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patrol_detail_category`
--

DROP TABLE IF EXISTS `patrol_detail_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrol_detail_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patrol_detail_type`
--

DROP TABLE IF EXISTS `patrol_detail_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrol_detail_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patrol_hotspot`
--

DROP TABLE IF EXISTS `patrol_hotspot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrol_hotspot` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mapno` varchar(45) NOT NULL,
  `name` varchar(255) NOT NULL,
  `east_west` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patrol_phone`
--

DROP TABLE IF EXISTS `patrol_phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrol_phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patrol_shop`
--

DROP TABLE IF EXISTS `patrol_shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrol_shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `patrol_type`
--

DROP TABLE IF EXISTS `patrol_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patrol_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `order_val` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phone_book`
--

DROP TABLE IF EXISTS `phone_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(50) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `MiddleInitial` varchar(1) DEFAULT NULL,
  `LastName` varchar(50) NOT NULL,
  `Salutation` varchar(50) DEFAULT NULL,
  `Suffix` varchar(10) DEFAULT NULL,
  `JobTitle` varchar(50) DEFAULT NULL,
  `Company` varchar(255) DEFAULT NULL,
  `Department` varchar(75) DEFAULT NULL,
  `WkPhone` varchar(50) NOT NULL,
  `Wkext` varchar(50) DEFAULT NULL,
  `DirectPhone` varchar(50) DEFAULT NULL,
  `FaxPhone` varchar(50) DEFAULT NULL,
  `MobilePhone` varchar(50) DEFAULT NULL,
  `Gender` varchar(7) NOT NULL DEFAULT 'Unsure',
  `Email` varchar(255) DEFAULT NULL,
  `AltEmail` varchar(255) DEFAULT NULL,
  `DontEmail` tinyint(1) DEFAULT '0',
  `HomeAddress` varchar(255) DEFAULT NULL,
  `HomePhone` varchar(50) DEFAULT NULL,
  `HomeFax` varchar(50) DEFAULT NULL,
  `Investor` tinyint(1) DEFAULT '0',
  `WestchaseToday` tinyint(1) DEFAULT '0',
  `Other` varchar(255) DEFAULT NULL,
  `Notes` text,
  `CompanyId` int(11) DEFAULT '0',
  `InputDate` datetime DEFAULT NULL,
  `LastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `CompanyId` (`CompanyId`),
  KEY `Company` (`Company`),
  CONSTRAINT `FK_phone_book_company` FOREIGN KEY (`CompanyId`) REFERENCES `company` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19077 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phone_book_category`
--

DROP TABLE IF EXISTS `phone_book_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_book_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `CategoryCode` varchar(2) NOT NULL,
  `PhoneBookId` int(10) DEFAULT NULL,
  `InputDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `CategoryCode` (`CategoryCode`),
  KEY `FKA6C542A8C5F52FE0` (`CategoryCode`),
  KEY `PersonId` (`PhoneBookId`) USING BTREE,
  KEY `FKA6C542A88570CB3C` (`PhoneBookId`) USING BTREE,
  CONSTRAINT `FK_phone_book_category_category` FOREIGN KEY (`CategoryCode`) REFERENCES `category` (`CategoryCode`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_phone_book_category_phone_book` FOREIGN KEY (`PhoneBookId`) REFERENCES `phone_book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26361 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phone_book_monument_sign_number`
--

DROP TABLE IF EXISTS `phone_book_monument_sign_number`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_book_monument_sign_number` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `phone_book_id` int(11) NOT NULL,
  `monument_sign` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_phone_book_monument_sign_number_pb` (`phone_book_id`),
  CONSTRAINT `FK_phone_book_monument_sign_number_pb` FOREIGN KEY (`phone_book_id`) REFERENCES `phone_book` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phone_book_property`
--

DROP TABLE IF EXISTS `phone_book_property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_book_property` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `phone_book` int(10) NOT NULL,
  `property` int(10) NOT NULL,
  `suite_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_phone_book_property_property` (`property`),
  KEY `FK_phone_book_property_phonebook` (`phone_book`),
  CONSTRAINT `FK_phone_book_property_phonebook` FOREIGN KEY (`phone_book`) REFERENCES `phone_book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_phone_book_property_property` FOREIGN KEY (`property`) REFERENCES `property` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8362 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phone_book_relation`
--

DROP TABLE IF EXISTS `phone_book_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_book_relation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `phone_book` int(11) NOT NULL,
  `property` int(10) NOT NULL,
  `relation_type` tinyint(2) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_phone_book_relation_phone_book` (`phone_book`),
  KEY `FK_phone_book_relation_property` (`property`),
  KEY `FK_phone_book_relation_relation_type` (`relation_type`),
  CONSTRAINT `FK_phone_book_relation_phone_book` FOREIGN KEY (`phone_book`) REFERENCES `phone_book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_phone_book_relation_property` FOREIGN KEY (`property`) REFERENCES `property` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_phone_book_relation_relation_type` FOREIGN KEY (`relation_type`) REFERENCES `phone_book_relation_type` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2159 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `phone_book_relation_type`
--

DROP TABLE IF EXISTS `phone_book_relation_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phone_book_relation_type` (
  `id` tinyint(2) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `BuildingName` varchar(50) NOT NULL,
  `GeoNumber` varchar(50) DEFAULT NULL,
  `GeoAddress` varchar(30) NOT NULL,
  `GeoCity` varchar(25) NOT NULL,
  `GeoState` varchar(6) NOT NULL,
  `GeoZipCode` varchar(10) NOT NULL,
  `Latitude` varchar(20) DEFAULT NULL,
  `Longitude` varchar(20) DEFAULT NULL,
  `PropertyType` int(10) unsigned DEFAULT NULL,
  `BuildingSize` int(11) DEFAULT '0',
  `AvailableForLease` int(11) DEFAULT '0',
  `OccupiedSqFt` int(11) DEFAULT '0',
  `OccupancyRate` float DEFAULT '0',
  `Prior_Q1_Occupied_Rate` float DEFAULT '0',
  `Prior_Q2_Occupied_Rate` float DEFAULT '0',
  `Prior_Q3_Occupied_Rate` float DEFAULT '0',
  `Prior_Q4_Occupied_Rate` float DEFAULT '0',
  `LargestVacancy` int(11) DEFAULT '0',
  `NoUnits` int(11) DEFAULT '0',
  `VacantUnits` int(11) DEFAULT '0',
  `PersonId` int(11) DEFAULT '0',
  `BusinessType` varchar(50) NOT NULL,
  `CommercialSpcFore` int(11) DEFAULT '0',
  `CommSpcFore1yr` varchar(10) DEFAULT NULL,
  `ForSale` tinyint(1) DEFAULT '0',
  `SingleTenant` tinyint(1) DEFAULT '0',
  `WillDivide` tinyint(1) DEFAULT '0',
  `TrpassAfdvt` tinyint(1) DEFAULT '0',
  `TrspassDate` datetime DEFAULT NULL,
  `FlagPole` tinyint(1) DEFAULT '0',
  `flag_size_id` int(10) unsigned DEFAULT NULL,
  `Notes` text,
  `Acreage` double DEFAULT '0',
  `HCad` varchar(50) DEFAULT NULL,
  `Owner` varchar(50) NOT NULL,
  `MetaOwner` varchar(50) DEFAULT NULL,
  `Center` varchar(50) DEFAULT NULL,
  `YearBuilt` varchar(5) DEFAULT NULL,
  `Facet` varchar(50) DEFAULT NULL,
  `Frontage` varchar(50) DEFAULT NULL,
  `PriceSqFt` varchar(50) DEFAULT '0',
  `Restrictions` varchar(50) DEFAULT NULL,
  `InputDate` datetime DEFAULT NULL,
  `LastUpdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` tinyint(1) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `MapNo` (`id`) USING BTREE,
  KEY `HCad` (`HCad`),
  KEY `FK_property_propertytype` (`PropertyType`),
  KEY `fk_property_flag_size` (`flag_size_id`),
  CONSTRAINT `fk_property_flag_size` FOREIGN KEY (`flag_size_id`) REFERENCES `flag_size` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_property_propertytype` FOREIGN KEY (`PropertyType`) REFERENCES `property_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9000 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_hcad`
--

DROP TABLE IF EXISTS `property_hcad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_hcad` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `property_id` int(11) NOT NULL,
  `hcad` varchar(50) NOT NULL,
  `notes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_property_hcad_hcad` (`hcad`),
  KEY `fk_property_hcad_property` (`property_id`),
  CONSTRAINT `fk_property_hcad_property` FOREIGN KEY (`property_id`) REFERENCES `property` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=421 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property_type`
--

DROP TABLE IF EXISTS `property_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sent_email`
--

DROP TABLE IF EXISTS `sent_email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sent_email` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `message_template` int(10) unsigned DEFAULT NULL,
  `phone_book` int(11) DEFAULT NULL,
  `from_address` varchar(255) NOT NULL,
  `to_address` varchar(255) NOT NULL,
  `subject` varchar(1000) NOT NULL,
  `message` longtext NOT NULL,
  `message_id` varchar(100) NOT NULL,
  `sent_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `attachment` longblob,
  `employee` int(10) unsigned NOT NULL,
  `attachment_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_sent_email_phonebook` (`phone_book`),
  KEY `FK_sent_email_template` (`message_template`),
  KEY `FK_sent_email_employee` (`employee`),
  CONSTRAINT `FK_sent_email_employee` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_sent_email_phonebook` FOREIGN KEY (`phone_book`) REFERENCES `phone_book` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_sent_email_template` FOREIGN KEY (`message_template`) REFERENCES `email_template` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1338 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(2) NOT NULL,
  `name` varchar(155) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `street`
--

DROP TABLE IF EXISTS `street`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `street` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `todo`
--

DROP TABLE IF EXISTS `todo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `todo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `employee` int(10) unsigned NOT NULL,
  `due_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `expire_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `text` longtext NOT NULL,
  `complete_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_todo_employee` (`employee`),
  CONSTRAINT `FK_todo_employee` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user` int(10) unsigned NOT NULL,
  `role` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_role_user` (`user`),
  KEY `FK_user_role_role` (`role`),
  CONSTRAINT `FK_user_role_role` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_user_role_user` FOREIGN KEY (`user`) REFERENCES `wcuser` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wcuser`
--

DROP TABLE IF EXISTS `wcuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wcuser` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` longtext NOT NULL,
  `disabled` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `employee` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNQ_wcuser_username` (`username`),
  KEY `FK_wcuser_employee` (`employee`),
  CONSTRAINT `FK_wcuser_employee` FOREIGN KEY (`employee`) REFERENCES `employee` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'westchase'
--
/*!50003 DROP FUNCTION IF EXISTS `func_format_phone_number` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 FUNCTION `func_format_phone_number`(str varchar(50)) RETURNS varchar(15) CHARSET utf8
BEGIN

declare restmp varchar(15);
declare res varchar(15);

set restmp = replace(replace(replace(replace(replace(str, '.', ''), '-', ''), ' ', ''), ')', ''), '(', '');






set res = concat("(", substr(restmp, 1, 3), ") ", substr(restmp,4, 3), "-", substr(restmp,7, 4));

return res;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `proc_format_phone_numbers` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`root`@`localhost`*/ /*!50003 PROCEDURE `proc_format_phone_numbers`()
BEGIN

    
    
    
    
    
    
update phone_book set HomePhone = func_format_phone_number(HomePhone) where HomePhone is not null and HomePhone <> '' and char_length(HomePhone) < 7;
update phone_book set HomeFax = func_format_phone_number(HomeFax) where HomeFax is not null and HomeFax <> '' and char_length(HomeFax) < 7;
update phone_book set FaxPhone = func_format_phone_number(FaxPhone) where FaxPhone is not null and FaxPhone <> '' and char_length(FaxPhone) < 7;
update phone_book set WkPhone = func_format_phone_number(WkPhone) where WkPhone is not null and WkPhone <> '' and char_length(WkPhone) < 7;
update phone_book set MobilePhone = func_format_phone_number(MobilePhone) where MobilePhone is not null and MobilePhone <> '' and char_length(MobilePhone) < 7;

    
    
    
update company set WkPhone = func_format_phone_number(WkPhone) where WkPhone is not null and WkPhone <> '' and char_length(WkPhone) < 7;
update company set FaxPhone = func_format_phone_number(FaxPhone) where FaxPhone is not null and FaxPhone <> '' and char_length(FaxPhone) < 7;



END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-22 15:11:38
