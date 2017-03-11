-- MySQL dump 10.13  Distrib 5.7.16, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: kamienica
-- ------------------------------------------------------
-- Server version	5.7.16-0ubuntu0.16.04.1

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
-- Table structure for table `History`
--

DROP TABLE IF EXISTS `History`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `History` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `ocurrence` tinyblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `History`
--

LOCK TABLES `History` WRITE;
/*!40000 ALTER TABLE `History` DISABLE KEYS */;
/*!40000 ALTER TABLE `History` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apartment`
--

DROP TABLE IF EXISTS `apartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apartment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apartmentNumber` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `intercom` varchar(4) DEFAULT NULL,
  `residence_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3ivm5ayi9khyj2vkjjr9y6rib` (`apartmentNumber`),
  KEY `FK_ilkik9ihdi11jt0ckhr7ashbu` (`residence_id`),
  CONSTRAINT `FK_ilkik9ihdi11jt0ckhr7ashbu` FOREIGN KEY (`residence_id`) REFERENCES `residence` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment`
--

LOCK TABLES `apartment` WRITE;
/*!40000 ALTER TABLE `apartment` DISABLE KEYS */;
INSERT INTO `apartment` VALUES (1,0,'Część Wspólna','6666',NULL),(2,1,'Piwnica','6666',NULL),(3,2,'Parter','6666',NULL),(4,3,'I Pietro','6666',NULL);
/*!40000 ALTER TABLE `apartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `division`
--

DROP TABLE IF EXISTS `division`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `division` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `divisionValue` double NOT NULL,
  `apartment_id` bigint(20) DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_964trt9yvo2le8ctstywh84uc` (`apartment_id`),
  KEY `FK_h53eoy0r5oklnxy3k9pnrmvvy` (`tenant_id`),
  CONSTRAINT `FK_964trt9yvo2le8ctstywh84uc` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`),
  CONSTRAINT `FK_h53eoy0r5oklnxy3k9pnrmvvy` FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `division`
--

LOCK TABLES `division` WRITE;
/*!40000 ALTER TABLE `division` DISABLE KEYS */;
INSERT INTO `division` VALUES (154,'2016-12-30',0.33,1,1),(155,'2016-12-30',1,2,1),(156,'2016-12-30',0,3,1),(157,'2016-12-30',0,4,1),(158,'2016-12-30',0.33,1,3),(159,'2016-12-30',0,2,3),(160,'2016-12-30',0,3,3),(161,'2016-12-30',1,4,3),(162,'2016-12-30',0.33,1,4),(163,'2016-12-30',0,2,4),(164,'2016-12-30',1,3,4),(165,'2016-12-30',0,4,4);
/*!40000 ALTER TABLE `division` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoiceenergy`
--

DROP TABLE IF EXISTS `invoiceenergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoiceenergy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) NOT NULL,
  `totalAmount` double NOT NULL,
  `baseReading_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ca6fh3j57ubt3kxh5kjpyq31l` (`date`),
  UNIQUE KEY `UK_ora9muw4gt5b8r4h0piebsxa7` (`serialNumber`),
  KEY `FK_dcf0sxkwqjeke2p7lmxp6xr3c` (`baseReading_id`),
  CONSTRAINT `FK_dcf0sxkwqjeke2p7lmxp6xr3c` FOREIGN KEY (`baseReading_id`) REFERENCES `readingenergy` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoiceenergy`
--

LOCK TABLES `invoiceenergy` WRITE;
/*!40000 ALTER TABLE `invoiceenergy` DISABLE KEYS */;
INSERT INTO `invoiceenergy` VALUES (1,'2016-08-01','Faktura Za Energi?','1',200,6),(2,'2016-08-04','Faktura Za Energi?','1231',200,11),(3,'2016-08-05','Faktura Za Energi?','21414124',100,1);
/*!40000 ALTER TABLE `invoiceenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoicegas`
--

DROP TABLE IF EXISTS `invoicegas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoicegas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) NOT NULL,
  `totalAmount` double NOT NULL,
  `baseReading_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rxpl6snjw63wmqu3l6ms46v9j` (`date`),
  UNIQUE KEY `UK_d5jc25vusi96db0n5dsbf4x32` (`serialNumber`),
  KEY `FK_nitee28a0ncesqk3lci3au4xh` (`baseReading_id`),
  CONSTRAINT `FK_nitee28a0ncesqk3lci3au4xh` FOREIGN KEY (`baseReading_id`) REFERENCES `readinggas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoicegas`
--

LOCK TABLES `invoicegas` WRITE;
/*!40000 ALTER TABLE `invoicegas` DISABLE KEYS */;
INSERT INTO `invoicegas` VALUES (1,'2016-09-01','Faktura Za Gaz','123',150,7);
/*!40000 ALTER TABLE `invoicegas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoicewater`
--

DROP TABLE IF EXISTS `invoicewater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoicewater` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) NOT NULL,
  `totalAmount` double NOT NULL,
  `baseReading_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mq3ho02pvwakfnr3fmc6q01c` (`date`),
  UNIQUE KEY `UK_tpyvx1uhpb1kinmsoyw453hwf` (`serialNumber`),
  KEY `FK_4i8nw17uh0d40eyu02r8xme07` (`baseReading_id`),
  CONSTRAINT `FK_4i8nw17uh0d40eyu02r8xme07` FOREIGN KEY (`baseReading_id`) REFERENCES `readingwater` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoicewater`
--

LOCK TABLES `invoicewater` WRITE;
/*!40000 ALTER TABLE `invoicewater` DISABLE KEYS */;
INSERT INTO `invoicewater` VALUES (1,'2016-08-01','Faktura Za Wode','123',150,8);
/*!40000 ALTER TABLE `invoicewater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meterenergy`
--

DROP TABLE IF EXISTS `meterenergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meterenergy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deactivation` date DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `main` tinyint(1) DEFAULT NULL,
  `serialNumber` varchar(255) NOT NULL,
  `unit` varchar(255) NOT NULL,
  `apartment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sej6avygl7sas1dmnw1ecxvvs` (`description`),
  UNIQUE KEY `UK_4ejivveyqm3cwgjvhi1lo7iml` (`serialNumber`),
  KEY `FK_jns6xh8nk3c4u60bn8ubfhl4d` (`apartment_id`),
  CONSTRAINT `FK_jns6xh8nk3c4u60bn8ubfhl4d` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meterenergy`
--

LOCK TABLES `meterenergy` WRITE;
/*!40000 ALTER TABLE `meterenergy` DISABLE KEYS */;
INSERT INTO `meterenergy` VALUES (1,'2600-01-01','G?ówny',1,'0','kWh',NULL),(2,'2600-01-01','Czesc Wspolna',0,'00','kWh',1),(3,'2016-08-10','Piwnica (NIEAKTYWNY)',0,'1','kWh',2),(4,'2600-01-01','Parter',0,'2','kWh',3),(5,'2600-01-01','I pietro',0,'3','kWh',4);
/*!40000 ALTER TABLE `meterenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metergas`
--

DROP TABLE IF EXISTS `metergas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `metergas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deactivation` date DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `main` tinyint(1) DEFAULT NULL,
  `serialNumber` varchar(255) NOT NULL,
  `unit` varchar(255) NOT NULL,
  `cwu` bit(1) NOT NULL,
  `apartment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6n8o8ntmdxf8mh19gnw4389d7` (`description`),
  UNIQUE KEY `UK_6drvuiflso7cylvcx4oyu8spc` (`serialNumber`),
  KEY `FK_oucqhmyvsm6mqbp441uvn7ydo` (`apartment_id`),
  CONSTRAINT `FK_oucqhmyvsm6mqbp441uvn7ydo` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metergas`
--

LOCK TABLES `metergas` WRITE;
/*!40000 ALTER TABLE `metergas` DISABLE KEYS */;
INSERT INTO `metergas` VALUES (1,'2600-01-01','G?ówny',1,'00','m3','\0',NULL),(2,'2600-01-01','Ogrzewanie',0,'0000','m3','\0',1),(3,'2600-01-01','Piwnica',0,'1','m3','\0',2),(4,'2600-01-01','Parter',0,'2','m3','\0',3),(5,'2600-01-01','I pietro',0,'3','m3','\0',4),(6,'2600-01-01','CWU',0,'000000','m3','',1);
/*!40000 ALTER TABLE `metergas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meterwater`
--

DROP TABLE IF EXISTS `meterwater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meterwater` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deactivation` date DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `main` tinyint(1) DEFAULT NULL,
  `serialNumber` varchar(255) NOT NULL,
  `unit` varchar(255) NOT NULL,
  `isWarmWater` bit(1) NOT NULL,
  `apartment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_p4pvpa9p082jgvmv6cl8o0xha` (`description`),
  UNIQUE KEY `UK_52pne9to4thhtwbsmnflrqoj3` (`serialNumber`),
  KEY `FK_ki9mhcg39a5lwwlcv9c0gout0` (`apartment_id`),
  CONSTRAINT `FK_ki9mhcg39a5lwwlcv9c0gout0` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meterwater`
--

LOCK TABLES `meterwater` WRITE;
/*!40000 ALTER TABLE `meterwater` DISABLE KEYS */;
INSERT INTO `meterwater` VALUES (1,'2600-01-01','G?ówny',1,'00','m3','\0',NULL),(3,'2600-01-01','Piwnica',0,'1','m3','\0',2),(9,'2600-01-01','Piwnica ciepla',0,'11','m3','',2),(10,'2600-01-01','Parter zimna',0,'22','m3','\0',3),(11,'2600-01-01','Parter ciepla',0,'1111','m3','',3),(12,'2600-01-01','I pietro zimna',0,'3','m3','\0',4),(13,'2600-01-01','I pietro ciepla',0,'34','m3','',4);
/*!40000 ALTER TABLE `meterwater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentenergy`
--

DROP TABLE IF EXISTS `paymentenergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentenergy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `paymentAmount` double DEFAULT NULL,
  `paymentDate` date DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  `invoice_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1xpyywhi7gj8d7pnd7blrvhhl` (`tenant_id`),
  KEY `FK_fpm0dw53us0br9jqpof1v4c75` (`invoice_id`),
  CONSTRAINT `FK_1xpyywhi7gj8d7pnd7blrvhhl` FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`),
  CONSTRAINT `FK_fpm0dw53us0br9jqpof1v4c75` FOREIGN KEY (`invoice_id`) REFERENCES `invoiceenergy` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentenergy`
--

LOCK TABLES `paymentenergy` WRITE;
/*!40000 ALTER TABLE `paymentenergy` DISABLE KEYS */;
INSERT INTO `paymentenergy` VALUES (1,88.67,'2016-07-29',1,1),(2,66.44,'2016-07-29',3,1),(3,44.22,'2016-07-29',4,1),(4,30.18,'2016-08-04',1,2),(5,48.36,'2016-08-04',3,2),(6,121.09,'2016-08-04',4,2),(7,24.09,'2016-08-05',1,3),(8,42.27,'2016-08-05',3,3),(9,33.18,'2016-08-05',4,3);
/*!40000 ALTER TABLE `paymentenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentgas`
--

DROP TABLE IF EXISTS `paymentgas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentgas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `paymentAmount` double DEFAULT NULL,
  `paymentDate` date DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  `invoice_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t52uvln6opgagcneoifa6ehpt` (`tenant_id`),
  KEY `FK_nbhx8srtnswhv81fur44ylnja` (`invoice_id`),
  CONSTRAINT `FK_nbhx8srtnswhv81fur44ylnja` FOREIGN KEY (`invoice_id`) REFERENCES `invoicegas` (`id`),
  CONSTRAINT `FK_t52uvln6opgagcneoifa6ehpt` FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentgas`
--

LOCK TABLES `paymentgas` WRITE;
/*!40000 ALTER TABLE `paymentgas` DISABLE KEYS */;
INSERT INTO `paymentgas` VALUES (1,38.63,'2016-07-29',1,1),(2,40,'2016-07-29',3,1),(3,71.36,'2016-07-29',4,1);
/*!40000 ALTER TABLE `paymentgas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentwater`
--

DROP TABLE IF EXISTS `paymentwater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentwater` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `paymentAmount` double DEFAULT NULL,
  `paymentDate` date DEFAULT NULL,
  `tenant_id` bigint(20) DEFAULT NULL,
  `invoice_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_p8fuu7ahqolhwfhwbsga1lilw` (`tenant_id`),
  KEY `FK_rvwuf4cauk2l96g9do0u3x4iv` (`invoice_id`),
  CONSTRAINT `FK_p8fuu7ahqolhwfhwbsga1lilw` FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`),
  CONSTRAINT `FK_rvwuf4cauk2l96g9do0u3x4iv` FOREIGN KEY (`invoice_id`) REFERENCES `invoicewater` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentwater`
--

LOCK TABLES `paymentwater` WRITE;
/*!40000 ALTER TABLE `paymentwater` DISABLE KEYS */;
INSERT INTO `paymentwater` VALUES (1,27.27,'2016-07-29',1,1),(2,68.18,'2016-07-29',3,1),(3,54.55,'2016-07-29',4,1);
/*!40000 ALTER TABLE `paymentwater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readingenergy`
--

DROP TABLE IF EXISTS `readingenergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `readingenergy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `readingDate` date NOT NULL,
  `resolvement` bit(1) DEFAULT NULL,
  `value` double NOT NULL,
  `unit` varchar(255) NOT NULL,
  `meter_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5knt9b445xrgemgapg5jesbja` (`meter_id`),
  CONSTRAINT `FK_5knt9b445xrgemgapg5jesbja` FOREIGN KEY (`meter_id`) REFERENCES `meterenergy` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readingenergy`
--

LOCK TABLES `readingenergy` WRITE;
/*!40000 ALTER TABLE `readingenergy` DISABLE KEYS */;
INSERT INTO `readingenergy` VALUES (1,'2016-07-01','',11,'kWh',1),(2,'2016-07-01','',5,'kWh',2),(3,'2016-07-01','',1,'kWh',3),(4,'2016-07-01','',2,'kWh',4),(5,'2016-07-01','',3,'kWh',5),(6,'2016-08-01','',20,'kWh',1),(7,'2016-08-01','',8,'kWh',2),(8,'2016-08-01','',4,'kWh',3),(9,'2016-08-01','',3,'kWh',4),(10,'2016-08-01','',5,'kWh',5),(11,'2016-09-01','',31,'kWh',1),(12,'2016-09-01','',10,'kWh',2),(13,'2016-09-01','',5,'kWh',3),(14,'2016-09-01','',9,'kWh',4),(15,'2016-09-01','',7,'kWh',5),(16,'2018-08-02','\0',32,'kWh',1),(17,'2018-08-02','\0',11,'kWh',2),(18,'2018-08-02','\0',6,'kWh',3),(19,'2018-08-02','\0',10,'kWh',4),(20,'2018-08-02','\0',8,'kWh',5);
/*!40000 ALTER TABLE `readingenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readinggas`
--

DROP TABLE IF EXISTS `readinggas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `readinggas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `readingDate` date NOT NULL,
  `resolvement` bit(1) DEFAULT NULL,
  `value` double NOT NULL,
  `unit` varchar(255) NOT NULL,
  `meter_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fm64ubuqi3kaa8bvr23u73nwh` (`meter_id`),
  CONSTRAINT `FK_fm64ubuqi3kaa8bvr23u73nwh` FOREIGN KEY (`meter_id`) REFERENCES `metergas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readinggas`
--

LOCK TABLES `readinggas` WRITE;
/*!40000 ALTER TABLE `readinggas` DISABLE KEYS */;
INSERT INTO `readinggas` VALUES (1,'2016-07-29','\0',114,'m3',1),(2,'2016-07-29','\0',1.5,'m3',2),(3,'2016-07-29','\0',4.5,'m3',3),(4,'2016-07-29','\0',3,'m3',4),(5,'2016-07-29','\0',5,'m3',5),(6,'2016-07-29','\0',100,'m3',6),(7,'2016-09-01','',169,'m3',1),(8,'2016-09-01','',2,'m3',2),(9,'2016-09-01','',6,'m3',3),(10,'2016-09-01','',4,'m3',4),(11,'2016-09-01','',7,'m3',5),(12,'2016-09-01','',150,'m3',6),(13,'2016-10-01','\0',196,'m3',1),(14,'2016-10-01','\0',2,'m3',2),(15,'2016-10-01','\0',9,'m3',3),(16,'2016-10-01','\0',5,'m3',4),(17,'2016-10-01','\0',10,'m3',5),(18,'2016-10-01','\0',170,'m3',6);
/*!40000 ALTER TABLE `readinggas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readingwater`
--

DROP TABLE IF EXISTS `readingwater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `readingwater` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `readingDate` date NOT NULL,
  `resolvement` bit(1) DEFAULT NULL,
  `value` double NOT NULL,
  `unit` varchar(255) NOT NULL,
  `meter_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_l5pfio0fjd6qw0699nkdt6hvw` (`meter_id`),
  CONSTRAINT `FK_l5pfio0fjd6qw0699nkdt6hvw` FOREIGN KEY (`meter_id`) REFERENCES `meterwater` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readingwater`
--

LOCK TABLES `readingwater` WRITE;
/*!40000 ALTER TABLE `readingwater` DISABLE KEYS */;
INSERT INTO `readingwater` VALUES (1,'2016-07-01','\0',33,'m3',1),(2,'2016-07-01','\0',4,'m3',3),(3,'2016-07-01','\0',2,'m3',9),(4,'2016-07-01','\0',5,'m3',10),(5,'2016-07-01','\0',10,'m3',11),(6,'2016-07-01','\0',6,'m3',12),(7,'2016-07-01','\0',6,'m3',13),(8,'2016-08-01','',44,'m3',1),(9,'2016-08-01','',5,'m3',3),(10,'2016-08-01','',3,'m3',9),(11,'2016-08-01','',7,'m3',10),(12,'2016-08-01','',12,'m3',11),(13,'2016-08-01','',10,'m3',12),(14,'2016-08-01','',7,'m3',13),(15,'2016-09-01','\0',56,'m3',1),(16,'2016-09-01','\0',6,'m3',3),(17,'2016-09-01','\0',4,'m3',9),(18,'2016-09-01','\0',10,'m3',10),(19,'2016-09-01','\0',16,'m3',11),(20,'2016-09-01','\0',11,'m3',12),(21,'2016-09-01','\0',9,'m3',13);
/*!40000 ALTER TABLE `readingwater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `residence`
--

DROP TABLE IF EXISTS `residence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `residence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6ds0xfk6hmf1s7rmnwxwojdi2` (`street`,`number`,`city`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `residence`
--

LOCK TABLES `residence` WRITE;
/*!40000 ALTER TABLE `residence` DISABLE KEYS */;
INSERT INTO `residence` VALUES (2,'Gdynia','45','Pusta'),(1,'Gdynia','45','Świętojańska');
/*!40000 ALTER TABLE `residence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `settings`
--

DROP TABLE IF EXISTS `settings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `settings` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `correctDivision` bit(1) NOT NULL,
  `garbage` bit(1) NOT NULL,
  `IPaymentDao` bit(1) NOT NULL,
  `internet` bit(1) NOT NULL,
  `waterHeatingSystem` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `settings`
--

LOCK TABLES `settings` WRITE;
/*!40000 ALTER TABLE `settings` DISABLE KEYS */;
INSERT INTO `settings` VALUES (1,'','\0','','\0','SHARED_GAS');
/*!40000 ALTER TABLE `settings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant`
--

DROP TABLE IF EXISTS `tenant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `movementDate` date NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `apartment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1wuu4ilo8ya2tm94iswtp6ev1` (`email`),
  KEY `FK_gpvh1vgwvwdh30p9ppff6udto` (`apartment_id`),
  CONSTRAINT `FK_gpvh1vgwvwdh30p9ppff6udto` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant`
--

LOCK TABLES `tenant` WRITE;
/*!40000 ALTER TABLE `tenant` DISABLE KEYS */;
INSERT INTO `tenant` VALUES (1,'folik@wp.pl','Maciej','Folik','2016-07-25','witaj','530081187','ADMIN','ACTIVE',2),(2,'kow@wp.pl','Andrzej','Kowalski','2014-07-01','witaj','4456','TENANT','INACTIVE',3),(3,'par@wp.pl','Kasia','Para','2015-07-26','witaj','23636','TENANT','ACTIVE',4),(4,'kasia@wp.pl','Kasia','Kowalska','2016-07-29','witaj','3456775','TENANT','ACTIVE',3),(5,'klej@wp.pl','Piotr','Kulej','2010-07-29','witaj','23526564','TENANT','INACTIVE',2);
/*!40000 ALTER TABLE `tenant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant_residence`
--

DROP TABLE IF EXISTS `tenant_residence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant_residence` (
  `tenant_id` bigint(20) NOT NULL,
  `residence_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_ia1bk63djrwlw6libxjsaaf6e` (`residence_id`),
  KEY `FK_sma8cunsiakjgvi87rldv3ks` (`tenant_id`),
  CONSTRAINT `FK_ia1bk63djrwlw6libxjsaaf6e` FOREIGN KEY (`residence_id`) REFERENCES `residence` (`id`),
  CONSTRAINT `FK_sma8cunsiakjgvi87rldv3ks` FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant_residence`
--

LOCK TABLES `tenant_residence` WRITE;
/*!40000 ALTER TABLE `tenant_residence` DISABLE KEYS */;
INSERT INTO `tenant_residence` VALUES (1,1);
/*!40000 ALTER TABLE `tenant_residence` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-30 19:08:09
