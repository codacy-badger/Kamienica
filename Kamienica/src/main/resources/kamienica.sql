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
-- Dumping data for table `apartment`
--

LOCK TABLES `apartment` WRITE;
/*!40000 ALTER TABLE `apartment` DISABLE KEYS */;
INSERT INTO `apartment` VALUES (1,0,'Część Wpólna','0000'),(2,1,'Parter','0000');
/*!40000 ALTER TABLE `apartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `division`
--

LOCK TABLES `division` WRITE;
/*!40000 ALTER TABLE `division` DISABLE KEYS */;
INSERT INTO `division` VALUES (1,'2016-06-28',1,1,1),(2,'2016-06-28',1,2,1);
/*!40000 ALTER TABLE `division` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `invoiceenergy`
--

LOCK TABLES `invoiceenergy` WRITE;
/*!40000 ALTER TABLE `invoiceenergy` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoiceenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `invoicegas`
--

LOCK TABLES `invoicegas` WRITE;
/*!40000 ALTER TABLE `invoicegas` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoicegas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `invoicewater`
--

LOCK TABLES `invoicewater` WRITE;
/*!40000 ALTER TABLE `invoicewater` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoicewater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `meterenergy`
--

LOCK TABLES `meterenergy` WRITE;
/*!40000 ALTER TABLE `meterenergy` DISABLE KEYS */;
INSERT INTO `meterenergy` VALUES (2,'2600-01-01','Wspólny',0,'2','kWh',1),(3,'2600-01-01','Parter',0,'3','kWh',2),(4,'2600-01-01','Główny',0,'123','kWh',1);
/*!40000 ALTER TABLE `meterenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `metergas`
--

LOCK TABLES `metergas` WRITE;
/*!40000 ALTER TABLE `metergas` DISABLE KEYS */;
/*!40000 ALTER TABLE `metergas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `meterwater`
--

LOCK TABLES `meterwater` WRITE;
/*!40000 ALTER TABLE `meterwater` DISABLE KEYS */;
INSERT INTO `meterwater` VALUES (1,'2600-01-01','Częsć Wpólna',0,'2','m3','\0',1);
/*!40000 ALTER TABLE `meterwater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `paymentenergy`
--

LOCK TABLES `paymentenergy` WRITE;
/*!40000 ALTER TABLE `paymentenergy` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymentenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `paymentgas`
--

LOCK TABLES `paymentgas` WRITE;
/*!40000 ALTER TABLE `paymentgas` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymentgas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `paymentwater`
--

LOCK TABLES `paymentwater` WRITE;
/*!40000 ALTER TABLE `paymentwater` DISABLE KEYS */;
/*!40000 ALTER TABLE `paymentwater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `readingenergy`
--

LOCK TABLES `readingenergy` WRITE;
/*!40000 ALTER TABLE `readingenergy` DISABLE KEYS */;
/*!40000 ALTER TABLE `readingenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `readinggas`
--

LOCK TABLES `readinggas` WRITE;
/*!40000 ALTER TABLE `readinggas` DISABLE KEYS */;
/*!40000 ALTER TABLE `readinggas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `readingwater`
--

LOCK TABLES `readingwater` WRITE;
/*!40000 ALTER TABLE `readingwater` DISABLE KEYS */;
/*!40000 ALTER TABLE `readingwater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tenant`
--

LOCK TABLES `tenant` WRITE;
/*!40000 ALTER TABLE `tenant` DISABLE KEYS */;
INSERT INTO `tenant` VALUES (1,'folik@wp.pl','Maciej','Folik','2016-06-28','witaj','123456779','ADMIN','AKTYWNY',2);
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

-- Dump completed on 2016-06-28 11:15:56
