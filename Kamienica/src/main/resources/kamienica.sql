-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: kamienica
-- ------------------------------------------------------
-- Server version	5.7.12-log

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
-- Table structure for table `apartment`
--

DROP TABLE IF EXISTS `apartment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apartment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apartmentNumber` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `intercom` varchar(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_3ivm5ayi9khyj2vkjjr9y6rib` (`apartmentNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apartment`
--

LOCK TABLES `apartment` WRITE;
/*!40000 ALTER TABLE `apartment` DISABLE KEYS */;
INSERT INTO `apartment` VALUES (1,0,'Część Wpólna','0000'),(2,1,'Piwnica','1111'),(3,2,'Parter','2222'),(4,3,'I piętro','0000');
/*!40000 ALTER TABLE `apartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `division`
--

DROP TABLE IF EXISTS `division`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `division` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `divisionValue` double NOT NULL,
  `apartment_id` int(11) DEFAULT NULL,
  `tenant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_964trt9yvo2le8ctstywh84uc` (`apartment_id`),
  KEY `FK_h53eoy0r5oklnxy3k9pnrmvvy` (`tenant_id`),
  CONSTRAINT `FK_964trt9yvo2le8ctstywh84uc` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`),
  CONSTRAINT `FK_h53eoy0r5oklnxy3k9pnrmvvy` FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `division`
--

LOCK TABLES `division` WRITE;
/*!40000 ALTER TABLE `division` DISABLE KEYS */;
INSERT INTO `division` VALUES (1,'2016-06-09',0.5,1,1),(2,'2016-06-09',1,2,1),(3,'2016-06-09',0,3,1),(4,'2016-06-09',1,4,1),(5,'2016-06-09',0.5,1,2),(6,'2016-06-09',0,2,2),(7,'2016-06-09',1,3,2),(8,'2016-06-09',0,4,2);
/*!40000 ALTER TABLE `division` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoiceenergy`
--

DROP TABLE IF EXISTS `invoiceenergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoiceenergy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) NOT NULL,
  `totalAmount` double NOT NULL,
  `baseReading_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ca6fh3j57ubt3kxh5kjpyq31l` (`date`),
  UNIQUE KEY `UK_ora9muw4gt5b8r4h0piebsxa7` (`serialNumber`),
  KEY `FK_dcf0sxkwqjeke2p7lmxp6xr3c` (`baseReading_id`),
  CONSTRAINT `FK_dcf0sxkwqjeke2p7lmxp6xr3c` FOREIGN KEY (`baseReading_id`) REFERENCES `readingenergy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoiceenergy`
--

LOCK TABLES `invoiceenergy` WRITE;
/*!40000 ALTER TABLE `invoiceenergy` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoiceenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoicegas`
--

DROP TABLE IF EXISTS `invoicegas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoicegas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) NOT NULL,
  `totalAmount` double NOT NULL,
  `baseReading_id` int(11) DEFAULT NULL,
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
INSERT INTO `invoicegas` VALUES (1,'2016-06-09','Faktura Za Gaz','1',100,12);
/*!40000 ALTER TABLE `invoicegas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoicewater`
--

DROP TABLE IF EXISTS `invoicewater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoicewater` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `serialNumber` varchar(255) NOT NULL,
  `totalAmount` double NOT NULL,
  `baseReading_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_mq3ho02pvwakfnr3fmc6q01c` (`date`),
  UNIQUE KEY `UK_tpyvx1uhpb1kinmsoyw453hwf` (`serialNumber`),
  KEY `FK_4i8nw17uh0d40eyu02r8xme07` (`baseReading_id`),
  CONSTRAINT `FK_4i8nw17uh0d40eyu02r8xme07` FOREIGN KEY (`baseReading_id`) REFERENCES `readingwater` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoicewater`
--

LOCK TABLES `invoicewater` WRITE;
/*!40000 ALTER TABLE `invoicewater` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoicewater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meterenergy`
--

DROP TABLE IF EXISTS `meterenergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meterenergy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `serialNumber` varchar(255) NOT NULL,
  `unit` varchar(255) NOT NULL,
  `apartment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
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
INSERT INTO `meterenergy` VALUES (1,'Główny','12','kWh',NULL),(2,'Część Wpólna','123','kWh',1),(3,'I piętro','2165','kWh',4),(4,'Piwnica','213454','kWh',2),(5,'Parter','3453','kWh',3);
/*!40000 ALTER TABLE `meterenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metergas`
--

DROP TABLE IF EXISTS `metergas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `metergas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `serialNumber` varchar(255) NOT NULL,
  `unit` varchar(255) NOT NULL,
  `cwu` bit(1) NOT NULL,
  `apartment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
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
INSERT INTO `metergas` VALUES (1,'Główny','23','m3','\0',NULL),(2,'Piwnica','232355','m3','\0',2),(3,'Parter','2131454','m3','\0',3),(4,'I piętro','12312321','m3','\0',4),(5,'Wspólny','213144','m3','\0',1),(6,'CWU','123154','m3','\0',1);
/*!40000 ALTER TABLE `metergas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `meterwater`
--

DROP TABLE IF EXISTS `meterwater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `meterwater` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  `serialNumber` varchar(255) NOT NULL,
  `unit` varchar(255) NOT NULL,
  `isWarmWater` bit(1) NOT NULL,
  `apartment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_52pne9to4thhtwbsmnflrqoj3` (`serialNumber`),
  KEY `FK_ki9mhcg39a5lwwlcv9c0gout0` (`apartment_id`),
  CONSTRAINT `FK_ki9mhcg39a5lwwlcv9c0gout0` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meterwater`
--

LOCK TABLES `meterwater` WRITE;
/*!40000 ALTER TABLE `meterwater` DISABLE KEYS */;
INSERT INTO `meterwater` VALUES (1,'Licznik Główny','0000','m3','\0',NULL),(2,'Piwnica','0','m3','\0',2),(3,'Parter','12','m3','\0',3),(4,'I piętro','23556','m3','\0',4);
/*!40000 ALTER TABLE `meterwater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentenergy`
--

DROP TABLE IF EXISTS `paymentenergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentenergy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paymentAmount` double DEFAULT NULL,
  `paymentDate` date DEFAULT NULL,
  `tenant_id` int(11) DEFAULT NULL,
  `invoice_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_1xpyywhi7gj8d7pnd7blrvhhl` (`tenant_id`),
  KEY `FK_fpm0dw53us0br9jqpof1v4c75` (`invoice_id`),
  CONSTRAINT `FK_1xpyywhi7gj8d7pnd7blrvhhl` FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`),
  CONSTRAINT `FK_fpm0dw53us0br9jqpof1v4c75` FOREIGN KEY (`invoice_id`) REFERENCES `invoiceenergy` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentenergy`
--

LOCK TABLES `paymentenergy` WRITE;
/*!40000 ALTER TABLE `paymentenergy` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymentenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentgas`
--

DROP TABLE IF EXISTS `paymentgas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentgas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paymentAmount` double DEFAULT NULL,
  `paymentDate` date DEFAULT NULL,
  `tenant_id` int(11) DEFAULT NULL,
  `invoice_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_t52uvln6opgagcneoifa6ehpt` (`tenant_id`),
  KEY `FK_nbhx8srtnswhv81fur44ylnja` (`invoice_id`),
  CONSTRAINT `FK_nbhx8srtnswhv81fur44ylnja` FOREIGN KEY (`invoice_id`) REFERENCES `invoicegas` (`id`),
  CONSTRAINT `FK_t52uvln6opgagcneoifa6ehpt` FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentgas`
--

LOCK TABLES `paymentgas` WRITE;
/*!40000 ALTER TABLE `paymentgas` DISABLE KEYS */;
INSERT INTO `paymentgas` VALUES (1,61.11,'2016-06-09',1,1),(2,38.89,'2016-06-09',2,1);
/*!40000 ALTER TABLE `paymentgas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentwater`
--

DROP TABLE IF EXISTS `paymentwater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentwater` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paymentAmount` double DEFAULT NULL,
  `paymentDate` date DEFAULT NULL,
  `tenant_id` int(11) DEFAULT NULL,
  `invoice_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_p8fuu7ahqolhwfhwbsga1lilw` (`tenant_id`),
  KEY `FK_rvwuf4cauk2l96g9do0u3x4iv` (`invoice_id`),
  CONSTRAINT `FK_p8fuu7ahqolhwfhwbsga1lilw` FOREIGN KEY (`tenant_id`) REFERENCES `tenant` (`id`),
  CONSTRAINT `FK_rvwuf4cauk2l96g9do0u3x4iv` FOREIGN KEY (`invoice_id`) REFERENCES `invoicewater` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentwater`
--

LOCK TABLES `paymentwater` WRITE;
/*!40000 ALTER TABLE `paymentwater` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymentwater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readingenergy`
--

DROP TABLE IF EXISTS `readingenergy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `readingenergy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `readingDate` date NOT NULL,
  `resolved` bit(1) DEFAULT NULL,
  `value` double NOT NULL,
  `unit` varchar(255) NOT NULL,
  `meter_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5knt9b445xrgemgapg5jesbja` (`meter_id`),
  CONSTRAINT `FK_5knt9b445xrgemgapg5jesbja` FOREIGN KEY (`meter_id`) REFERENCES `meterenergy` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readingenergy`
--

LOCK TABLES `readingenergy` WRITE;
/*!40000 ALTER TABLE `readingenergy` DISABLE KEYS */;
INSERT INTO `readingenergy` VALUES (1,'2016-01-01','\0',0,'kWh',2),(2,'2016-01-01','\0',0,'kWh',4),(3,'2016-01-01','\0',0,'kWh',5),(4,'2016-01-01','\0',0,'kWh',3),(5,'2016-01-01','\0',0,'kWh',1),(6,'2016-02-01','\0',3,'kWh',2),(7,'2016-02-01','\0',5,'kWh',4),(8,'2016-02-01','\0',5,'kWh',5),(9,'2016-02-01','\0',5,'kWh',3),(10,'2016-02-01','\0',18,'kWh',1);
/*!40000 ALTER TABLE `readingenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readinggas`
--

DROP TABLE IF EXISTS `readinggas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `readinggas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `readingDate` date NOT NULL,
  `resolved` bit(1) DEFAULT NULL,
  `value` double NOT NULL,
  `unit` varchar(255) NOT NULL,
  `meter_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fm64ubuqi3kaa8bvr23u73nwh` (`meter_id`),
  CONSTRAINT `FK_fm64ubuqi3kaa8bvr23u73nwh` FOREIGN KEY (`meter_id`) REFERENCES `metergas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readinggas`
--

LOCK TABLES `readinggas` WRITE;
/*!40000 ALTER TABLE `readinggas` DISABLE KEYS */;
INSERT INTO `readinggas` VALUES (1,'2016-01-01','\0',0,'m3',5),(2,'2016-01-01','\0',0,'m3',6),(3,'2016-01-01','\0',0,'m3',2),(4,'2016-01-01','\0',0,'m3',3),(5,'2016-01-01','\0',0,'m3',4),(6,'2016-01-01','\0',0,'m3',1),(7,'2016-02-01','',1,'m3',5),(8,'2016-02-01','',2,'m3',6),(9,'2016-02-01','',3,'m3',2),(10,'2016-02-01','',2,'m3',3),(11,'2016-02-01','',1,'m3',4),(12,'2016-02-01','',9,'m3',1);
/*!40000 ALTER TABLE `readinggas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `readingwater`
--

DROP TABLE IF EXISTS `readingwater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `readingwater` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `readingDate` date NOT NULL,
  `resolved` bit(1) DEFAULT NULL,
  `value` double NOT NULL,
  `unit` varchar(255) NOT NULL,
  `meter_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_l5pfio0fjd6qw0699nkdt6hvw` (`meter_id`),
  CONSTRAINT `FK_l5pfio0fjd6qw0699nkdt6hvw` FOREIGN KEY (`meter_id`) REFERENCES `meterwater` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `readingwater`
--

LOCK TABLES `readingwater` WRITE;
/*!40000 ALTER TABLE `readingwater` DISABLE KEYS */;
INSERT INTO `readingwater` VALUES (1,'2016-01-01','\0',0,'m3',2),(2,'2016-01-01','\0',0,'m3',3),(3,'2016-01-01','\0',0,'m3',4),(4,'2016-01-01','\0',0,'m3',1),(5,'2016-02-01','\0',1,'m3',2),(6,'2016-02-01','\0',3,'m3',3),(7,'2016-02-01','\0',1,'m3',4),(8,'2016-02-01','\0',5,'m3',1);
/*!40000 ALTER TABLE `readingwater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tenant`
--

DROP TABLE IF EXISTS `tenant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tenant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `movementDate` date NOT NULL,
  `password` varchar(5) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `apartment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1wuu4ilo8ya2tm94iswtp6ev1` (`email`),
  KEY `FK_gpvh1vgwvwdh30p9ppff6udto` (`apartment_id`),
  CONSTRAINT `FK_gpvh1vgwvwdh30p9ppff6udto` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tenant`
--

LOCK TABLES `tenant` WRITE;
/*!40000 ALTER TABLE `tenant` DISABLE KEYS */;
INSERT INTO `tenant` VALUES (1,'folik@wp.pl','Maciej','Folik','2016-06-09','witaj','530081187','ADMIN','AKTYWNY',2),(2,'anna@wp.pl','Anna','Kowalska','2016-06-09','witaj','233453463','USER','AKTYWNY',3),(3,'kowalski@wp.pl','piotr','kowalski','2015-06-09','witaj','4544454545','USER','NIEAKTYWNY',3),(4,'pati@wp.pl','Patrycja','Bork','2014-06-09','witaj','7878787878','USER','NIEAKTYWNY',4);
/*!40000 ALTER TABLE `tenant` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-09 12:49:13
