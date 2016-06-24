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
/*
 * Tabela po zmmianach (dodanie m.in. kolumny main dla liczników
 * 
 * 
 * */
LOCK TABLES `apartment` WRITE;
/*!40000 ALTER TABLE `apartment` DISABLE KEYS */;
INSERT INTO `apartment` VALUES (1,0,'Część Wpólna','0000'),(2,1,'Piwnica','1111'),(3,2,'Parter','2222'),(4,3,'I piętro','0000');
/*!40000 ALTER TABLE `apartment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `division`
--

LOCK TABLES `division` WRITE;
/*!40000 ALTER TABLE `division` DISABLE KEYS */;
INSERT INTO `division` VALUES (17,'2016-06-23',0.33,1,1),(18,'2016-06-23',1,2,1),(19,'2016-06-23',0,3,1),(20,'2016-06-23',0,4,1),(21,'2016-06-23',0.33,1,5),(22,'2016-06-23',0,2,5),(23,'2016-06-23',1,3,5),(24,'2016-06-23',0,4,5),(25,'2016-06-23',0.33,1,7),(26,'2016-06-23',0,2,7),(27,'2016-06-23',0,3,7),(28,'2016-06-23',1,4,7);
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
INSERT INTO `invoicegas` VALUES (1,'2016-06-23','Faktura Za Gaz','4565466',200,6);
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
INSERT INTO `meterenergy` VALUES (1,'Główny','12','kWh',NULL,0,'�\�\0sr\0org.joda.time.LocalDate���\�\�\�\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0\�\�D\0sr\0\'org.joda.time.chrono.ISOChronology$Stub�\�fq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub�/�|2\Z\�\0\0xpw\0UTCxx'),(3,'I piętro','2165','kWh',4,0,NULL),(5,'Parter','3453','kWh',3,0,NULL),(8,'546','45','kWh',1,0,'�\�\0sr\0org.joda.time.LocalDate���\�\�\�\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0\�\�D\0sr\0\'org.joda.time.chrono.ISOChronology$Stub�\�fq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub�/�|2\Z\�\0\0xpw\0UTCxx'),(11,'vbn','vbn','kWh',NULL,1,'�\�\0sr\0org.joda.time.LocalDate���\�\�\�\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0\�\�D\0sr\0\'org.joda.time.chrono.ISOChronology$Stub�\�fq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub�/�|2\Z\�\0\0xpw\0UTCxx');
/*!40000 ALTER TABLE `meterenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `metergas`
--

LOCK TABLES `metergas` WRITE;
/*!40000 ALTER TABLE `metergas` DISABLE KEYS */;
INSERT INTO `metergas` VALUES (2,'Piwnica','232355','m3','\0',2,0,NULL),(3,'Parter','2131454','m3','\0',3,0,NULL),(4,'I piętro','12312321','m3','\0',4,0,NULL),(5,'Wspólny','213144','m3','\0',1,0,NULL),(6,'CWU','123154','m3','\0',1,0,NULL),(8,'Główny','4234234','m3','\0',NULL,1,'�\�\0sr\0org.joda.time.LocalDate���\�\�\�\0J\0iLocalMillisL\0iChronologyt\0\ZLorg/joda/time/Chronology;xp\0\0\�\�D\0sr\0\'org.joda.time.chrono.ISOChronology$Stub�\�fq7P\'\0\0xpsr\0org.joda.time.DateTimeZone$Stub�/�|2\Z\�\0\0xpw\0UTCxx');
/*!40000 ALTER TABLE `metergas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `meterwater`
--

LOCK TABLES `meterwater` WRITE;
/*!40000 ALTER TABLE `meterwater` DISABLE KEYS */;
INSERT INTO `meterwater` VALUES (1,'Licznik Główny','0000','m3','\0',NULL,1,NULL),(2,'Piwnica','0','m3','\0',2,0,NULL),(3,'Parter','12','m3','\0',3,0,NULL),(4,'I piętro','23556','m3','\0',4,0,NULL);
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
INSERT INTO `paymentgas` VALUES (1,20.11,'2016-06-23',1,1),(2,23.68,'2016-06-23',5,1),(3,155.82,'2016-06-23',7,1);
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
INSERT INTO `readingenergy` VALUES (52,'2016-06-21','\0',2,'kWh',1,NULL),(53,'2016-06-21','\0',2,'kWh',3,NULL),(54,'2016-06-21','\0',3,'kWh',5,NULL),(55,'2016-06-21','\0',5,'kWh',8,NULL);
/*!40000 ALTER TABLE `readingenergy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `readinggas`
--

LOCK TABLES `readinggas` WRITE;
/*!40000 ALTER TABLE `readinggas` DISABLE KEYS */;
INSERT INTO `readinggas` VALUES (1,'2016-06-23','',2,'m3',2,NULL),(2,'2016-06-23','',3,'m3',3,NULL),(3,'2016-06-23','',40,'m3',4,NULL),(4,'2016-06-23','',5,'m3',5,NULL),(5,'2016-06-23','',6,'m3',6,NULL),(6,'2016-06-23','',7,'m3',8,NULL);
/*!40000 ALTER TABLE `readinggas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `readingwater`
--

LOCK TABLES `readingwater` WRITE;
/*!40000 ALTER TABLE `readingwater` DISABLE KEYS */;
INSERT INTO `readingwater` VALUES (1,'2016-06-23','\0',4,'m3',1,NULL),(2,'2016-06-23','\0',5,'m3',2,NULL),(3,'2016-06-23','\0',6,'m3',3,NULL),(4,'2016-06-23','\0',7,'m3',4,NULL);
/*!40000 ALTER TABLE `readingwater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tenant`
--

LOCK TABLES `tenant` WRITE;
/*!40000 ALTER TABLE `tenant` DISABLE KEYS */;
INSERT INTO `tenant` VALUES (1,'folik@wp.pl','Maciej','Folik','2016-06-09','witaj','530081187','ADMIN','AKTYWNY',2),(2,'anna@wp.pl','Anna','Kowalska','2016-06-09','witaj','233453463','USER','NIEAKTYWNY',3),(3,'kowalski@wp.pl','piotr','kowalski','2015-06-09','witaj','4544454545','USER','NIEAKTYWNY',3),(4,'pati@wp.pl','Patrycja','Bork','2014-06-09','witaj','7878787878','USER','NIEAKTYWNY',4),(5,'sdf@wpp.l','And','ljk','2016-06-16','witaj','345367777','USER','AKTYWNY',3),(6,'z@zz','zzzzzzzz','zzzzzzzz','2016-06-17','witaj','213425667777','USER','NIEAKTYWNY',4),(7,'wof@wp.pl','Andrzej','Wolf','2016-06-20','witaj','23778976554','USER','AKTYWNY',4),(8,'nieaktywny@wp.pl','powinienem','byc','2016-06-16','witaj','2353464654','USER','NIEAKTYWNY',4);
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

-- Dump completed on 2016-06-24 15:11:46
