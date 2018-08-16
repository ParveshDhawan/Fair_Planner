drop database if exists fairplanner;
create database fairplanner;
use fairplanner;

-- MySQL dump 10.13  Distrib 5.5.27, for Win64 (x86)
--
-- Host: localhost    Database: fairplanner
-- ------------------------------------------------------
-- Server version	5.5.27

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
-- Table structure for table `companyfairparticipation`
--

DROP TABLE IF EXISTS `companyfairparticipation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companyfairparticipation` (
  `Fair_Participation_Id` int(11) NOT NULL,
  `Company_Id` int(11) DEFAULT NULL,
  `Fair_Id` int(11) DEFAULT NULL,
  `Fair_Stall_Id` int(11) DEFAULT NULL,
  `Booking_Date` datetime DEFAULT NULL,
  `No_Of_Stall_Booked` int(11) DEFAULT NULL,
  `Is_Booking_Cancelled` tinyint(1) DEFAULT NULL,
  `Cancellation_Date` datetime DEFAULT NULL,
  PRIMARY KEY (`Fair_Participation_Id`),
  KEY `fk_companyfairparticipation_fairmaster1` (`Fair_Id`),
  KEY `fk_companyfairparticipation_companymaster1` (`Company_Id`),
  CONSTRAINT `fk_companyfairparticipation_companymaster1` FOREIGN KEY (`Company_Id`) REFERENCES `companymaster` (`Company_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_companyfairparticipation_fairmaster1` FOREIGN KEY (`Fair_Id`) REFERENCES `fairmaster` (`Fair_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companyfairparticipation`
--

LOCK TABLES `companyfairparticipation` WRITE;
/*!40000 ALTER TABLE `companyfairparticipation` DISABLE KEYS */;
INSERT INTO `companyfairparticipation` VALUES (1,2,2,2,'2012-06-25 19:03:23',9,0,'2013-09-09 00:00:00'),(2,1,1,1,'2012-06-25 19:03:31',6,NULL,'2013-09-09 00:00:00'),(3,3,5,5,'2012-06-25 19:03:40',156,NULL,'2013-09-09 00:00:00'),(4,4,4,4,'2012-06-25 19:04:34',20,NULL,'2013-09-09 00:00:00'),(5,4,3,3,'2012-06-25 19:04:51',5,NULL,'2013-09-09 00:00:00');
/*!40000 ALTER TABLE `companyfairparticipation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companymaster`
--

DROP TABLE IF EXISTS `companymaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `companymaster` (
  `Company_Id` int(11) NOT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Address` text,
  `City` varchar(45) DEFAULT NULL,
  `State` varchar(45) DEFAULT NULL,
  `Country` varchar(45) DEFAULT NULL,
  `Pin_Code` varchar(10) DEFAULT NULL,
  `Email_Id` varchar(45) DEFAULT NULL,
  `Website_Url` varchar(45) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Mobile` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Company_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companymaster`
--

LOCK TABLES `companymaster` WRITE;
/*!40000 ALTER TABLE `companymaster` DISABLE KEYS */;
INSERT INTO `companymaster` VALUES (1,'Alpha','Chandigarh','Chandigarh','Punjab','India','160022','alphaIT@Alpha.com','www.alpha.com','017236528974','9866857412'),(2,'Punjab Agro','Moga	','Moga','Punjab','India','151002','Punjab_Agro@gmail.com','www.Punjabagro.com','0163636524748','9866857412'),(3,'TCS','Mohali','Mohai','Punjab','India','153624','tcs@gmail.com','www.tcs.com','123664792','9866857412'),(4,'Mahindra','Barnala','Barnala','Punjab','India','1563.246','Mahindera@gmai.com','www.Mahindera.com','12365487','9866857412');
/*!40000 ALTER TABLE `companymaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fairmaster`
--

DROP TABLE IF EXISTS `fairmaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fairmaster` (
  `Fair_Id` int(11) NOT NULL,
  `Fair_Name` varchar(45) DEFAULT NULL,
  `Description` text,
  `Address` text,
  `City` varchar(45) DEFAULT NULL,
  `State` varchar(45) DEFAULT NULL,
  `Country` varchar(45) DEFAULT NULL,
  `Pin_Code` varchar(10) DEFAULT NULL,
  `Fair_Type_Id` int(11) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `Fair_Start_Date` datetime DEFAULT NULL,
  `Fair_End_Date` datetime DEFAULT NULL,
  PRIMARY KEY (`Fair_Id`),
  KEY `fk_fairmaster_fairtype1` (`Fair_Type_Id`),
  CONSTRAINT `fk_fairmaster_fairtype1` FOREIGN KEY (`Fair_Type_Id`) REFERENCES `fairtype` (`Fair_Type_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fairmaster`
--

LOCK TABLES `fairmaster` WRITE;
/*!40000 ALTER TABLE `fairmaster` DISABLE KEYS */;
INSERT INTO `fairmaster` VALUES (1,'Tech World','Technical Fair','Mohali','Mohali','Punjab','India',NULL,1,'Available','2012-02-20 00:00:00','2012-03-20 00:00:00'),(2,'Animal Fair','Fair Of Animals','Moga','Moga','Punjab','India',NULL,2,'Available','2012-02-03 00:00:00','2012-02-04 00:00:00'),(3,'World of Vehicles','Fair Of Automobiles','Bathinda','Bathinda','Punjab','Punjab',NULL,3,'Available','2012-01-01 00:00:00','2012-01-03 00:00:00'),(4,'Petsss','Fair Of Pets','Bathinda','Bathinda','Punjab','India',NULL,4,'Available','2013-02-01 00:00:00','2013-02-03 00:00:00'),(5,'Punjabi Rocks','Fair of cultural activities ','Bathinda	','Bathinda	','Punjab','India',NULL,5,'Available','2012-03-03 00:00:00','2012-03-05 00:00:00');
/*!40000 ALTER TABLE `fairmaster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fairstalls`
--

DROP TABLE IF EXISTS `fairstalls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fairstalls` (
  `Fair_Stall_Id` int(11) NOT NULL,
  `Fair_Stall` varchar(45) DEFAULT NULL,
  `Size` varchar(100) DEFAULT NULL,
  `Price` double DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `No_Of_Stalls` int(11) DEFAULT NULL,
  `Fair_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Fair_Stall_Id`),
  KEY `fk_fairstalls_fairmaster1` (`Fair_Id`),
  CONSTRAINT `fk_fairstalls_fairmaster1` FOREIGN KEY (`Fair_Id`) REFERENCES `fairmaster` (`Fair_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fairstalls`
--

LOCK TABLES `fairstalls` WRITE;
/*!40000 ALTER TABLE `fairstalls` DISABLE KEYS */;
INSERT INTO `fairstalls` VALUES (1,'50','10',5000,'Available',6,1),(2,'60','8',5000,'Available',9,2),(3,'25','10',10000,'Available',5,3),(4,'58','25',5000,'Available',20,4),(5,'75','6',10000,'Available',156,5);
/*!40000 ALTER TABLE `fairstalls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fairtype`
--

DROP TABLE IF EXISTS `fairtype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fairtype` (
  `Fair_Type_Id` int(11) NOT NULL,
  `Fair_Type` varchar(45) DEFAULT NULL,
  `Description` text,
  `Status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Fair_Type_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fairtype`
--

LOCK TABLES `fairtype` WRITE;
/*!40000 ALTER TABLE `fairtype` DISABLE KEYS */;
INSERT INTO `fairtype` VALUES (1,'Tech Fair','Fair of Technical Events','Available'),(2,'Animal Fair','Fair of Animals','Available'),(3,'Automobile Fair','Fair Of Automobiles','Available'),(4,'Pet Fairs','Fair of Pets','Available'),(5,'Cultural Fair','Fair of Cultural Events','Available');
/*!40000 ALTER TABLE `fairtype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usermaster`
--

DROP TABLE IF EXISTS `usermaster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usermaster` (
  `User_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(45) DEFAULT NULL,
  `Password` varchar(45) DEFAULT NULL,
  `User_Type` varchar(20) DEFAULT NULL,
  `User_Status` varchar(20) DEFAULT NULL,
  `Name` varchar(50) NOT NULL,
  `Contact_Number` varchar(20) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usermaster`
--

LOCK TABLES `usermaster` WRITE;
/*!40000 ALTER TABLE `usermaster` DISABLE KEYS */;
INSERT INTO `usermaster` VALUES (1,'admin','admin','Administrator','Active','Rohan','01724565654','admin@gmail.com'),(2,'employee','employee','Employee','Active','Aakash','98888989898','employee@gmail.com'),(3,'User','user','Employee','Active','Rajpreet','9899988888','user@gmail.com');
/*!40000 ALTER TABLE `usermaster` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-05 18:23:47
