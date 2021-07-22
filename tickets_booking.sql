-- MySQL dump 10.13  Distrib 5.6.51, for Win64 (x86_64)
--
-- Host: localhost    Database: tickets_booking
-- ------------------------------------------------------
-- Server version	5.6.51-log

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
-- Table structure for table `booking_transaction`
--

DROP TABLE IF EXISTS `booking_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking_transaction` (
  `BOOK_TRNX` bigint(20) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_ID` bigint(20) NOT NULL,
  `SHOW_ID` bigint(20) NOT NULL,
  `NO_OF_SEAT` int(11) NOT NULL,
  `TOTAL_AMOUNT` double NOT NULL,
  `BOOKED_STATUS` tinyint(4) NOT NULL,
  `PAYMENT_STATUS` tinyint(4) NOT NULL,
  `CREATE_TIME` bigint(20) NOT NULL,
  `UPDATE_TIME` bigint(20) NOT NULL,
  PRIMARY KEY (`BOOK_TRNX`),
  KEY `booking_transaction_FK1` (`SHOW_ID`),
  KEY `booking_transaction_FK2` (`CUSTOMER_ID`),
  CONSTRAINT `booking_transaction_FK1` FOREIGN KEY (`SHOW_ID`) REFERENCES `show_information` (`SHOW_ID`),
  CONSTRAINT `booking_transaction_FK2` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer_contacts` (`CUSTOMER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_transaction`
--

LOCK TABLES `booking_transaction` WRITE;
/*!40000 ALTER TABLE `booking_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_contacts`
--

DROP TABLE IF EXISTS `customer_contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_contacts` (
  `CUSTOMER_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FNAME` varchar(200) NOT NULL,
  `LNAME` varchar(200) DEFAULT NULL,
  `EMAIL` varchar(200) NOT NULL,
  `PHONE` varchar(200) NOT NULL,
  `ADDRESS_LINE_1` varchar(200) DEFAULT NULL,
  `ADDRESS_LINE_2` varchar(200) DEFAULT NULL,
  `CITY` varchar(100) NOT NULL,
  `STATE` varchar(200) NOT NULL,
  `ZIPCODE` int(11) NOT NULL,
  `CREATED_TIME` bigint(20) NOT NULL,
  `UPDATED_TIME` bigint(20) NOT NULL,
  PRIMARY KEY (`CUSTOMER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_contacts`
--

LOCK TABLES `customer_contacts` WRITE;
/*!40000 ALTER TABLE `customer_contacts` DISABLE KEYS */;
INSERT INTO `customer_contacts` VALUES (1,'Dhana','pal','dhana@test.com','2345678',NULL,NULL,'CITY','STATE',56789,234567890,123456789);
/*!40000 ALTER TABLE `customer_contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hall_screen_information`
--

DROP TABLE IF EXISTS `hall_screen_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hall_screen_information` (
  `hall_id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(200) NOT NULL,
  `SEAT` int(11) NOT NULL,
  `DESCRIPTION` varchar(250) NOT NULL,
  `ADDRESS` varchar(250) NOT NULL,
  `PHONE` varchar(50) NOT NULL,
  `STATUS` tinyint(4) NOT NULL,
  `CREATED_TIME` bigint(20) NOT NULL,
  `UPDATE_TIME` bigint(20) NOT NULL,
  PRIMARY KEY (`hall_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hall_screen_information`
--

LOCK TABLES `hall_screen_information` WRITE;
/*!40000 ALTER TABLE `hall_screen_information` DISABLE KEYS */;
INSERT INTO `hall_screen_information` VALUES (1,'Palace Hall',100,'Palace Hall AC','ABC road, ABC Street, Chennai','+91 999999999',1,1234567890,1234567890),(2,'Moon Star Hall',100,'Moon Star AC','xyz road, ABC Street, Chennai','+91 999900999',1,1234567890,1234567890),(3,'Sun Set Mall',100,'Sun Set AC','xyz road, ABC Street, Chennai','+91 9999009123',1,1234567890,1234567890);
/*!40000 ALTER TABLE `hall_screen_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_information`
--

DROP TABLE IF EXISTS `movie_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_information` (
  `MOVIE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MOVIE_NAME` varchar(250) NOT NULL,
  `DESCRIPTION` varchar(250) NOT NULL,
  `DURATION` int(11) NOT NULL,
  `PRICE` float NOT NULL,
  `STATUS` tinyint(4) NOT NULL,
  `CREATED_TIME` bigint(20) NOT NULL,
  `UPDATED_TIME` bigint(20) NOT NULL,
  PRIMARY KEY (`MOVIE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_information`
--

LOCK TABLES `movie_information` WRITE;
/*!40000 ALTER TABLE `movie_information` DISABLE KEYS */;
INSERT INTO `movie_information` VALUES (1,'ABC Movie','Directed By: 123, Family and Comedy Drama',160,0,1,1234567890,1234567890),(2,'Action Movie','Directed By: ABC, Action Movie',150,0,1,1234567890,1234567890),(3,'Comedy Movie','Directed By: XYZ, Comedy entertiner',140,0,1,1234567890,1234567890);
/*!40000 ALTER TABLE `movie_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat_hold_transaction`
--

DROP TABLE IF EXISTS `seat_hold_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seat_hold_transaction` (
  `SEAT_HOLD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `HASH` varchar(250) NOT NULL,
  `ROW_ID` varchar(20) NOT NULL,
  `SEAT_NO` int(11) NOT NULL,
  `HOLDING_DURATION` int(11) NOT NULL,
  `CREATED_TIME` bigint(20) NOT NULL,
  `UPDATED_TIME` bigint(20) NOT NULL,
  PRIMARY KEY (`SEAT_HOLD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat_hold_transaction`
--

LOCK TABLES `seat_hold_transaction` WRITE;
/*!40000 ALTER TABLE `seat_hold_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `seat_hold_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seat_transaction`
--

DROP TABLE IF EXISTS `seat_transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seat_transaction` (
  `SEAT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BOOK_TRNX` bigint(20) NOT NULL,
  `ROW_ID` varchar(100) NOT NULL,
  `SEAT_NUMBER` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SEAT_ID`),
  KEY `seat_transaction_FK1` (`BOOK_TRNX`),
  CONSTRAINT `seat_transaction_FK1` FOREIGN KEY (`BOOK_TRNX`) REFERENCES `booking_transaction` (`BOOK_TRNX`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seat_transaction`
--

LOCK TABLES `seat_transaction` WRITE;
/*!40000 ALTER TABLE `seat_transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `seat_transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `show_information`
--

DROP TABLE IF EXISTS `show_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `show_information` (
  `SHOW_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `MOVIE_ID` int(11) NOT NULL,
  `HALL_ID` int(11) NOT NULL,
  `SHOW_START_DATE` datetime NOT NULL,
  `SHOW_START_TIME` varchar(50) NOT NULL,
  `STATUS` tinyint(4) NOT NULL,
  `CREAT_TIME` bigint(20) NOT NULL,
  `UPDATE_TIME` bigint(20) NOT NULL,
  PRIMARY KEY (`SHOW_ID`),
  KEY `show_information_fk1` (`MOVIE_ID`),
  KEY `show_information_fk2` (`HALL_ID`),
  CONSTRAINT `show_information_fk1` FOREIGN KEY (`MOVIE_ID`) REFERENCES `movie_information` (`MOVIE_ID`),
  CONSTRAINT `show_information_fk2` FOREIGN KEY (`HALL_ID`) REFERENCES `hall_screen_information` (`hall_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `show_information`
--

LOCK TABLES `show_information` WRITE;
/*!40000 ALTER TABLE `show_information` DISABLE KEYS */;
INSERT INTO `show_information` VALUES (1,1,1,'2021-06-20 00:00:00','9:30',1,1234567890,1234567890),(2,1,1,'2021-06-20 00:00:00','1:00',1,1234567890,1234567890),(3,1,1,'2021-06-20 00:00:00','4:30',1,1234567890,1234567890),(4,1,2,'2021-06-20 00:00:00','4:30',1,1234567890,1234567890),(5,1,2,'2021-06-20 00:00:00','1:00',1,1234567890,1234567890),(6,2,2,'2021-06-20 00:00:00','9:30',1,1234567890,1234567890);
/*!40000 ALTER TABLE `show_information` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-22 20:06:40
