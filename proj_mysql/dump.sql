CREATE DATABASE  IF NOT EXISTS `DB_Project` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `DB_Project`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: DB_Project
-- ------------------------------------------------------
-- Server version	5.7.17

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
-- Table structure for table `Activity`
--

DROP TABLE IF EXISTS `Activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Activity` (
  `aid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `aname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `fromd` date DEFAULT NULL,
  `tod` date DEFAULT NULL,
  `organizer` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Activity`
--

LOCK TABLES `Activity` WRITE;
/*!40000 ALTER TABLE `Activity` DISABLE KEYS */;
INSERT INTO `Activity` VALUES ('0000000001','Final Exam','2017-05-11','2017-05-18','Chulalongkorn University');
/*!40000 ALTER TABLE `Activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Advisor`
--

DROP TABLE IF EXISTS `Advisor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Advisor` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `pab` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `fromd` date NOT NULL,
  `tod` date DEFAULT NULL,
  PRIMARY KEY (`sid`,`pab`),
  KEY `pab` (`pab`),
  CONSTRAINT `advisor_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`),
  CONSTRAINT `advisor_ibfk_2` FOREIGN KEY (`pab`) REFERENCES `Professor` (`pab`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Advisor`
--

LOCK TABLES `Advisor` WRITE;
/*!40000 ALTER TABLE `Advisor` DISABLE KEYS */;
INSERT INTO `Advisor` VALUES ('5630152623','PFS','2015-01-01',NULL),('5730196321','PFS','2015-01-01',NULL),('5731045421','PFS','2015-01-01',NULL);
/*!40000 ALTER TABLE `Advisor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Attend`
--

DROP TABLE IF EXISTS `Attend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Attend` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `cid` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `eyear` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `semester` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `section` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `adate` date NOT NULL,
  PRIMARY KEY (`sid`,`cid`,`eyear`,`semester`,`section`,`adate`),
  KEY `cid` (`cid`),
  CONSTRAINT `attend_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`),
  CONSTRAINT `attend_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `Course` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Attend`
--

LOCK TABLES `Attend` WRITE;
/*!40000 ALTER TABLE `Attend` DISABLE KEYS */;
/*!40000 ALTER TABLE `Attend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Company`
--

DROP TABLE IF EXISTS `Company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Company` (
  `coid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `cname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `houseno` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `road` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subdistrict` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `district` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`coid`),
  KEY `district` (`district`),
  CONSTRAINT `company_ibfk_1` FOREIGN KEY (`district`) REFERENCES `Postalcode` (`district`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Company`
--

LOCK TABLES `Company` WRITE;
/*!40000 ALTER TABLE `Company` DISABLE KEYS */;
INSERT INTO `Company` VALUES ('0000000001','Line Thailand Company','99/12','Browny Rd','Puttaram','Pathumwan','Bangkok','TH','0865557777');
/*!40000 ALTER TABLE `Company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Control_Project`
--

DROP TABLE IF EXISTS `Control_Project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Control_Project` (
  `pab` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `pjid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`pab`,`pjid`),
  KEY `pjid` (`pjid`),
  CONSTRAINT `control_project_ibfk_1` FOREIGN KEY (`pab`) REFERENCES `Professor` (`pab`),
  CONSTRAINT `control_project_ibfk_2` FOREIGN KEY (`pjid`) REFERENCES `Project` (`pjid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Control_Project`
--

LOCK TABLES `Control_Project` WRITE;
/*!40000 ALTER TABLE `Control_Project` DISABLE KEYS */;
INSERT INTO `Control_Project` VALUES ('PFS','1014562'),('PFS','1102011');
/*!40000 ALTER TABLE `Control_Project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Course`
--

DROP TABLE IF EXISTS `Course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Course` (
  `cid` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `cname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `credit` int(1) NOT NULL,
  `did` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `fid` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`cid`),
  KEY `did` (`did`),
  KEY `fid` (`fid`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`did`) REFERENCES `Department` (`did`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`fid`) REFERENCES `Faculty` (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Course`
--

LOCK TABLES `Course` WRITE;
/*!40000 ALTER TABLE `Course` DISABLE KEYS */;
INSERT INTO `Course` VALUES ('2110101','COMPROG',3,'10','21'),('2110318','DIS SYS ESSEN',3,'10','21'),('2110422','DB MGT SYS DESIGN',3,'10','21'),('2110478','COMP/COMM',3,'10','21'),('2221485','BUDDHIST CIV',3,'00','23'),('2304107','GEN PHYS I',3,'00','23'),('2313213','DIGITAL PHOTO',3,'00','23'),('2603284',' STAT PHYS SCIENCE',3,'03','21'),('5500111','EXP ENG I',3,'10','21'),('800250','HUMAN RELATIONS',3,'10','21');
/*!40000 ALTER TABLE `Course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Department`
--

DROP TABLE IF EXISTS `Department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Department` (
  `did` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `fid` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `dname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`fid`,`did`),
  KEY `did` (`did`),
  CONSTRAINT `department_ibfk_1` FOREIGN KEY (`fid`) REFERENCES `Faculty` (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Department`
--

LOCK TABLES `Department` WRITE;
/*!40000 ALTER TABLE `Department` DISABLE KEYS */;
INSERT INTO `Department` VALUES ('03','21','Chemical',NULL),('10','21','Computer',NULL),('00','23','Normal',NULL);
/*!40000 ALTER TABLE `Department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Department_Leader`
--

DROP TABLE IF EXISTS `Department_Leader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Department_Leader` (
  `pab` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `did` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `fid` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `fromd` date NOT NULL,
  `tod` date DEFAULT NULL,
  PRIMARY KEY (`pab`,`did`,`fid`,`fromd`),
  KEY `did` (`did`),
  KEY `fid` (`fid`),
  CONSTRAINT `department_leader_ibfk_1` FOREIGN KEY (`pab`) REFERENCES `Professor` (`pab`),
  CONSTRAINT `department_leader_ibfk_2` FOREIGN KEY (`did`) REFERENCES `Department` (`did`),
  CONSTRAINT `department_leader_ibfk_3` FOREIGN KEY (`fid`) REFERENCES `Faculty` (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Department_Leader`
--

LOCK TABLES `Department_Leader` WRITE;
/*!40000 ALTER TABLE `Department_Leader` DISABLE KEYS */;
INSERT INTO `Department_Leader` VALUES ('BPF','10','21','2014-01-01','2018-01-01');
/*!40000 ALTER TABLE `Department_Leader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Earn_Reward`
--

DROP TABLE IF EXISTS `Earn_Reward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Earn_Reward` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `rid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`sid`,`rid`),
  KEY `rid` (`rid`),
  CONSTRAINT `earn_reward_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`),
  CONSTRAINT `earn_reward_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `Reward` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Earn_Reward`
--

LOCK TABLES `Earn_Reward` WRITE;
/*!40000 ALTER TABLE `Earn_Reward` DISABLE KEYS */;
/*!40000 ALTER TABLE `Earn_Reward` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Enroll`
--

DROP TABLE IF EXISTS `Enroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Enroll` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `cid` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `eyear` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `semester` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `section` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `grade` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`sid`,`cid`,`eyear`,`semester`,`section`),
  KEY `cid` (`cid`),
  CONSTRAINT `enroll_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`),
  CONSTRAINT `enroll_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `Course` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Enroll`
--

LOCK TABLES `Enroll` WRITE;
/*!40000 ALTER TABLE `Enroll` DISABLE KEYS */;
INSERT INTO `Enroll` VALUES ('5431055521','2110422','2015','2','1','F'),('5630152623','2110318','2015','1','2','X'),('5630152623','2304107','2014','1','3','X'),('5630152623','2313213','2015','2','1','X'),('5630152623','800250','2014','2','1','X'),('5730196321','2110318','2015','1','2','X'),('5730196321','2110422','2016','2','1','X'),('5730196321','2304107','2014','1','3','X'),('5730196321','2313213','2015','2','1','X'),('5730196321','800250','2014','2','1','X'),('5731045421','2110318','2015','1','2','X'),('5731045421','2304107','2014','1','3','X'),('5731045421','2313213','2015','2','1','X'),('5731045421','800250','2014','2','1','X');
/*!40000 ALTER TABLE `Enroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Faculty`
--

DROP TABLE IF EXISTS `Faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Faculty` (
  `fid` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `houseno` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `road` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subdistrict` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `district` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`fid`),
  KEY `district` (`district`),
  CONSTRAINT `faculty_ibfk_1` FOREIGN KEY (`district`) REFERENCES `Postalcode` (`district`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Faculty`
--

LOCK TABLES `Faculty` WRITE;
/*!40000 ALTER TABLE `Faculty` DISABLE KEYS */;
INSERT INTO `Faculty` VALUES ('21','Engineering',NULL,NULL,NULL,'Bangrak',NULL,NULL),('23','Science',NULL,NULL,NULL,'Bangrak',NULL,NULL);
/*!40000 ALTER TABLE `Faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Faculty_Head`
--

DROP TABLE IF EXISTS `Faculty_Head`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Faculty_Head` (
  `pab` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `fid` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `fromd` date NOT NULL,
  `tod` date DEFAULT NULL,
  PRIMARY KEY (`pab`,`fid`,`fromd`),
  KEY `fid` (`fid`),
  CONSTRAINT `faculty_head_ibfk_1` FOREIGN KEY (`pab`) REFERENCES `Professor` (`pab`),
  CONSTRAINT `faculty_head_ibfk_2` FOREIGN KEY (`fid`) REFERENCES `Faculty` (`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Faculty_Head`
--

LOCK TABLES `Faculty_Head` WRITE;
/*!40000 ALTER TABLE `Faculty_Head` DISABLE KEYS */;
/*!40000 ALTER TABLE `Faculty_Head` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Graduate`
--

DROP TABLE IF EXISTS `Graduate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Graduate` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `ssn` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `sname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `houseno` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `road` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subdistrict` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `district` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `bdate` date NOT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `syear` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `eyear` varchar(4) COLLATE utf8_unicode_ci DEFAULT NULL,
  `did` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `fid` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `degree` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `uid` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gyear` varchar(4) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`sid`),
  KEY `did` (`did`),
  KEY `fid` (`fid`),
  KEY `uid` (`uid`),
  KEY `district` (`district`),
  CONSTRAINT `graduate_ibfk_1` FOREIGN KEY (`did`) REFERENCES `Department` (`did`),
  CONSTRAINT `graduate_ibfk_2` FOREIGN KEY (`fid`) REFERENCES `Faculty` (`fid`),
  CONSTRAINT `graduate_ibfk_3` FOREIGN KEY (`uid`) REFERENCES `University` (`uid`),
  CONSTRAINT `graduate_ibfk_4` FOREIGN KEY (`district`) REFERENCES `Postalcode` (`district`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Graduate`
--

LOCK TABLES `Graduate` WRITE;
/*!40000 ALTER TABLE `Graduate` DISABLE KEYS */;
/*!40000 ALTER TABLE `Graduate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Graduate_Status`
--

DROP TABLE IF EXISTS `Graduate_Status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Graduate_Status` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `probation` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `studyabroad` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `leaving` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`sid`),
  CONSTRAINT `graduate_status_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Graduate` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Graduate_Status`
--

LOCK TABLES `Graduate_Status` WRITE;
/*!40000 ALTER TABLE `Graduate_Status` DISABLE KEYS */;
/*!40000 ALTER TABLE `Graduate_Status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Intern`
--

DROP TABLE IF EXISTS `Intern`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Intern` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `coid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `istatus` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `fromd` date DEFAULT NULL,
  `tod` date DEFAULT NULL,
  `cfname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `csname` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cposition` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`sid`,`coid`),
  KEY `coid` (`coid`),
  CONSTRAINT `intern_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`),
  CONSTRAINT `intern_ibfk_2` FOREIGN KEY (`coid`) REFERENCES `Company` (`coid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Intern`
--

LOCK TABLES `Intern` WRITE;
/*!40000 ALTER TABLE `Intern` DISABLE KEYS */;
INSERT INTO `Intern` VALUES ('5730196321','0000000001','W','2017-05-28','2017-07-30','Cony','Rabbit','Human Resource','cony@line.me','0855555555');
/*!40000 ALTER TABLE `Intern` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Intern_Lab`
--

DROP TABLE IF EXISTS `Intern_Lab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Intern_Lab` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `uid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `istatus` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `fromd` date DEFAULT NULL,
  `tod` date DEFAULT NULL,
  PRIMARY KEY (`sid`,`uid`),
  KEY `uid` (`uid`),
  CONSTRAINT `intern_lab_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`),
  CONSTRAINT `intern_lab_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `University` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Intern_Lab`
--

LOCK TABLES `Intern_Lab` WRITE;
/*!40000 ALTER TABLE `Intern_Lab` DISABLE KEYS */;
INSERT INTO `Intern_Lab` VALUES ('5730197421','0000000001','W','2017-05-28','2017-07-30');
/*!40000 ALTER TABLE `Intern_Lab` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Join_Activity`
--

DROP TABLE IF EXISTS `Join_Activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Join_Activity` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `aid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`sid`,`aid`),
  KEY `aid` (`aid`),
  CONSTRAINT `join_activity_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`),
  CONSTRAINT `join_activity_ibfk_2` FOREIGN KEY (`aid`) REFERENCES `Activity` (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Join_Activity`
--

LOCK TABLES `Join_Activity` WRITE;
/*!40000 ALTER TABLE `Join_Activity` DISABLE KEYS */;
INSERT INTO `Join_Activity` VALUES ('5730196321','0000000001');
/*!40000 ALTER TABLE `Join_Activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Own_Research`
--

DROP TABLE IF EXISTS `Own_Research`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Own_Research` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `reid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`sid`,`reid`),
  KEY `reid` (`reid`),
  CONSTRAINT `own_research_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`),
  CONSTRAINT `own_research_ibfk_2` FOREIGN KEY (`reid`) REFERENCES `Research` (`reid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Own_Research`
--

LOCK TABLES `Own_Research` WRITE;
/*!40000 ALTER TABLE `Own_Research` DISABLE KEYS */;
/*!40000 ALTER TABLE `Own_Research` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Parent`
--

DROP TABLE IF EXISTS `Parent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Parent` (
  `ssn` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `sname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `houseno` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `road` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subdistrict` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `district` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `bdate` date NOT NULL,
  `role` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ssn`),
  KEY `district` (`district`),
  CONSTRAINT `parent_ibfk_1` FOREIGN KEY (`district`) REFERENCES `Postalcode` (`district`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Parent`
--

LOCK TABLES `Parent` WRITE;
/*!40000 ALTER TABLE `Parent` DISABLE KEYS */;
INSERT INTO `Parent` VALUES ('0000000000555','Lazy','Girl','1/23','Falling Star Rd','Puttaram','Pathumwan','Bangkok','M','1985-12-05','Grandmother','superjanitorza@chula.ac.th','0812345678');
/*!40000 ALTER TABLE `Parent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Postalcode`
--

DROP TABLE IF EXISTS `Postalcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Postalcode` (
  `district` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `pcode` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`district`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Postalcode`
--

LOCK TABLES `Postalcode` WRITE;
/*!40000 ALTER TABLE `Postalcode` DISABLE KEYS */;
INSERT INTO `Postalcode` VALUES ('Bangbon','10150'),('Bangkae','10160'),('Bangna','10260'),('Bangrak','10500'),('Kaerai','74110'),('Meuang Chonburi','20130'),('Pathumwan','10330');
/*!40000 ALTER TABLE `Postalcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Professor`
--

DROP TABLE IF EXISTS `Professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Professor` (
  `pab` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `ssn` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `sname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `houseno` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `road` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subdistrict` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `district` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `bdate` date NOT NULL,
  `advanced` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `since` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `did` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `fid` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`pab`),
  KEY `did` (`did`),
  KEY `fid` (`fid`),
  KEY `district` (`district`),
  CONSTRAINT `professor_ibfk_1` FOREIGN KEY (`did`) REFERENCES `Department` (`did`),
  CONSTRAINT `professor_ibfk_2` FOREIGN KEY (`fid`) REFERENCES `Faculty` (`fid`),
  CONSTRAINT `professor_ibfk_3` FOREIGN KEY (`district`) REFERENCES `Postalcode` (`district`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Professor`
--

LOCK TABLES `Professor` WRITE;
/*!40000 ALTER TABLE `Professor` DISABLE KEYS */;
INSERT INTO `Professor` VALUES ('BML','1230052012','Bob','Marley','654','BangnamJud','Trenchtown','Bangna','Bangkok','M','1945-02-06','Reggae Song','1998','Bob.M@chula.ac.th','0889162456','03','21'),('BPF','4444444444444','Big','Professor','12/112','Jupiter Rd','Puttaram','Pathumwan','Bangkok','M','1980-01-30','Security','2015','BIGprofessor@chula.ac.th','0911111111','10','21'),('CNL','4123001520122','Cony','Lingkon','654','BangnamJud','Mern','Kaerai','Samutsakorn','M','1978-11-30','Web application','2001','piya.S@chula.ac.th','0897812202','10','21'),('NDJ','1842419283725','Nadet','Jaruwan','25','Nathong4','Dindang','Pathumwan','Bangkok','M','1960-12-01','Computer Engineering','1997','nadet.J@chula.ac.th','0812556969','10','21'),('PFS','3333333333333','Dummy','ProfessorX','12/112','Jupiter Rd','Puttaram','Pathumwan','Bangkok','M','1980-01-30','Security','1999','professorX@chula.ac.th','0911111111','10','21'),('PKS','3104120153403','Piashare','Kong','51/7','Nameuang','Nameuang','Meuang Chonburi','Chonburi','M','1975-11-30','SoftwareEngineering','1996','pishare.K@chula.ac.th','0815645811','10','21'),('PYS','3104250155103','Piya','Satilapasuksakul','51/7','BangnamJud','Mern','Kaerai','Samutsakorn','M','1978-11-30','Chemical Engineering','2001','piya.S@chula.ac.th','0814165811','03','21'),('SHH','2541973665508','Sudyod','Hahaha','117/92','Ladprao80','Wangthonglang','Bangrak','Bangkok','M','1980-02-01','Marine Scientist','2000','sudyod.h@chula.ac.th','0901422356','00','23'),('TKN','4123001520122','Taken','Nitro','501','Bangna','Bangna','Bangna','Bangkok','M','1980-07-01','Network','2001','taken.N@chula.ac.th','0812050210','10','21'),('TML','3012488706050','Thomas','Muller','999','Thonglor18','Khrongtannuea','Pathumwan','Bangkok','M','1989-09-13','Soccer','2000','thomas.M@chula.ac.th','0853214651','00','23'),('TNN','1234001000122','Take','Niro','51','Bangna67','Bangna','Bangna','Bangkok','M','1979-04-01','Hardware','2000','taken.N@chula.ac.th','0812050210','10','21');
/*!40000 ALTER TABLE `Professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Project`
--

DROP TABLE IF EXISTS `Project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Project` (
  `pjid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `pname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `field` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`pjid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Project`
--

LOCK TABLES `Project` WRITE;
/*!40000 ALTER TABLE `Project` DISABLE KEYS */;
INSERT INTO `Project` VALUES ('1010456','GameofNinja','Game in 4D','Game'),('1014562','Punycode','Impoved from phishing','Security'),('1102011','SecureBit v2','Impoved from SecureBit v1','Security'),('1240589','Alarm from STM32 board','Detecting burglar','Computer Hardware');
/*!40000 ALTER TABLE `Project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Research`
--

DROP TABLE IF EXISTS `Research`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Research` (
  `reid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `rname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `pubdate` date DEFAULT NULL,
  `field` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`reid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Research`
--

LOCK TABLES `Research` WRITE;
/*!40000 ALTER TABLE `Research` DISABLE KEYS */;
/*!40000 ALTER TABLE `Research` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Reward`
--

DROP TABLE IF EXISTS `Reward`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Reward` (
  `rid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `rname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `adate` date DEFAULT NULL,
  `prize` int(10) DEFAULT NULL,
  `organizer` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Reward`
--

LOCK TABLES `Reward` WRITE;
/*!40000 ALTER TABLE `Reward` DISABLE KEYS */;
/*!40000 ALTER TABLE `Reward` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Rule`
--

DROP TABLE IF EXISTS `Rule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Rule` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `ssn` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`sid`,`ssn`),
  KEY `ssn` (`ssn`),
  CONSTRAINT `rule_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`),
  CONSTRAINT `rule_ibfk_2` FOREIGN KEY (`ssn`) REFERENCES `Parent` (`ssn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Rule`
--

LOCK TABLES `Rule` WRITE;
/*!40000 ALTER TABLE `Rule` DISABLE KEYS */;
INSERT INTO `Rule` VALUES ('5730196321','0000000000555');
/*!40000 ALTER TABLE `Rule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Staff`
--

DROP TABLE IF EXISTS `Staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Staff` (
  `ssn` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `sname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `houseno` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `road` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subdistrict` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `district` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `bdate` date NOT NULL,
  `role` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `since` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `did` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `fid` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ssn`),
  KEY `did` (`did`),
  KEY `fid` (`fid`),
  KEY `district` (`district`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`did`) REFERENCES `Department` (`did`),
  CONSTRAINT `staff_ibfk_2` FOREIGN KEY (`fid`) REFERENCES `Faculty` (`fid`),
  CONSTRAINT `staff_ibfk_3` FOREIGN KEY (`district`) REFERENCES `Postalcode` (`district`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Staff`
--

LOCK TABLES `Staff` WRITE;
/*!40000 ALTER TABLE `Staff` DISABLE KEYS */;
INSERT INTO `Staff` VALUES ('0000000000007','Lazy','Boy','1/23','Falling Star Rd','Puttaram','Pathumwan','Bangkok','M','1985-12-05','Janitor','1996','superjanitorza@chula.ac.th','0812345678','10','21');
/*!40000 ALTER TABLE `Staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Student_Status`
--

DROP TABLE IF EXISTS `Student_Status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Student_Status` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `probation` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `studyabroad` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `leaving` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`sid`),
  CONSTRAINT `student_status_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Student` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Student_Status`
--

LOCK TABLES `Student_Status` WRITE;
/*!40000 ALTER TABLE `Student_Status` DISABLE KEYS */;
INSERT INTO `Student_Status` VALUES ('5431055521','Y','Y','N'),('5630152623','N','N','N'),('5730196321','N','N','Y'),('5730197421','N','Y','N'),('5731045421','N','N','N');
/*!40000 ALTER TABLE `Student_Status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Teach`
--

DROP TABLE IF EXISTS `Teach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Teach` (
  `pab` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `cid` varchar(7) COLLATE utf8_unicode_ci NOT NULL,
  `eyear` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `semester` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `section` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`pab`,`cid`,`eyear`,`semester`,`section`),
  KEY `cid` (`cid`),
  CONSTRAINT `teach_ibfk_1` FOREIGN KEY (`pab`) REFERENCES `Professor` (`pab`),
  CONSTRAINT `teach_ibfk_2` FOREIGN KEY (`cid`) REFERENCES `Course` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Teach`
--

LOCK TABLES `Teach` WRITE;
/*!40000 ALTER TABLE `Teach` DISABLE KEYS */;
INSERT INTO `Teach` VALUES ('PFS','2110101','2015','1','3'),('PFS','2110318','2015','2','1'),('PFS','2110422','2016','2','1'),('PFS','2110478','2014','1','1');
/*!40000 ALTER TABLE `Teach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Undergraduate`
--

DROP TABLE IF EXISTS `Undergraduate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Undergraduate` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `ssn` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `sname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `houseno` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `road` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subdistrict` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `district` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `bdate` date NOT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `syear` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `eyear` varchar(4) COLLATE utf8_unicode_ci DEFAULT NULL,
  `did` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `fid` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `pjid` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`sid`),
  KEY `did` (`did`),
  KEY `fid` (`fid`),
  KEY `pjid` (`pjid`),
  KEY `district` (`district`),
  CONSTRAINT `undergraduate_ibfk_1` FOREIGN KEY (`did`) REFERENCES `Department` (`did`),
  CONSTRAINT `undergraduate_ibfk_2` FOREIGN KEY (`fid`) REFERENCES `Faculty` (`fid`),
  CONSTRAINT `undergraduate_ibfk_3` FOREIGN KEY (`pjid`) REFERENCES `Project` (`pjid`),
  CONSTRAINT `undergraduate_ibfk_4` FOREIGN KEY (`district`) REFERENCES `Postalcode` (`district`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Undergraduate`
--

LOCK TABLES `Undergraduate` WRITE;
/*!40000 ALTER TABLE `Undergraduate` DISABLE KEYS */;
INSERT INTO `Undergraduate` VALUES ('5730196321','1111111111111','Danupat','Khamnuansin','113/14','Nares','4 Phaya','Bangrak','Bangkok','M','1996-04-30','jrkns1996@gmail.com','0892668633','2014','','10','21','1102011'),('5730197421','222222222222','Brown','Bigbear','15/14','Line Rd','4 Phaya','Bangrak','Bangkok','M','1996-05-15','brown@line.me','0899999999','2014','','10','21','1102011');
/*!40000 ALTER TABLE `Undergraduate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Undergraduate_Status`
--

DROP TABLE IF EXISTS `Undergraduate_Status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Undergraduate_Status` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `probation` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `studyabroad` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `leaving` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`sid`),
  CONSTRAINT `undergraduate_status_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `Undergraduate` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Undergraduate_Status`
--

LOCK TABLES `Undergraduate_Status` WRITE;
/*!40000 ALTER TABLE `Undergraduate_Status` DISABLE KEYS */;
/*!40000 ALTER TABLE `Undergraduate_Status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `University`
--

DROP TABLE IF EXISTS `University`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `University` (
  `uid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `uname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `houseno` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `road` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subdistrict` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `district` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `country` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `district` (`district`),
  CONSTRAINT `university_ibfk_1` FOREIGN KEY (`district`) REFERENCES `Postalcode` (`district`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `University`
--

LOCK TABLES `University` WRITE;
/*!40000 ALTER TABLE `University` DISABLE KEYS */;
INSERT INTO `University` VALUES ('0000000001','Chulalongkorn University','1/12','Phayathai','Puttaram','Pathumwan','Bangkok','TH','022312345');
/*!40000 ALTER TABLE `University` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'PFS','$2a$10$S00IW5kixh7CMTmKAM1Rv.hl93cYeR5AtuCeJmcuhprGueDgiMgyW','2017-04-25 04:05:20','2017-04-25 04:05:20'),(2,'BPF','$2a$10$KYEQ3g7ABA4wFisHkIneG.yR6BRF0Nub.0u83xkD.j/3nZWQImuaG','2017-04-25 04:05:20','2017-04-25 04:05:20'),(3,'0000000000007','$2a$10$vdA5.a6EnphKrnOeM5QyDeljJJG6Bier/Ro2SfEbYDPxKwojhkyC6','2017-04-25 04:05:21','2017-04-25 04:05:21');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sessions`
--

DROP TABLE IF EXISTS `sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sessions` (
  `session_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `expires` int(11) unsigned NOT NULL,
  `data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  PRIMARY KEY (`session_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sessions`
--

LOCK TABLES `sessions` WRITE;
/*!40000 ALTER TABLE `sessions` DISABLE KEYS */;
/*!40000 ALTER TABLE `sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `sid` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `ssn` varchar(13) COLLATE utf8_unicode_ci NOT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `sname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `houseno` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `road` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subdistrict` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `district` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `bdate` date NOT NULL,
  `email` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `syear` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `eyear` varchar(4) COLLATE utf8_unicode_ci DEFAULT NULL,
  `did` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  `fid` varchar(2) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`sid`),
  UNIQUE KEY `sid_index` (`sid`) USING BTREE,
  KEY `fid` (`fid`),
  KEY `district` (`district`),
  KEY `start_year_index` (`syear`),
  KEY `department_index` (`did`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`did`) REFERENCES `Department` (`did`),
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`fid`) REFERENCES `Faculty` (`fid`),
  CONSTRAINT `student_ibfk_3` FOREIGN KEY (`district`) REFERENCES `Postalcode` (`district`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('5431055521','1101900159555','Loving','StudyLong','50/496','Bangbon3','Bangbon','Bangbon','Bangkok','M','1996-08-12','lovingstudy@gmail.com','0897813023','2012','2018','10','21'),('5630152623','3548962015485','Chaiwat','Uthaitip','113','Ruangrat','Bangsaphan','Pathumwan','Bangkok','M','1995-04-23','chaiwattip@gmail.com','0942543651','2013','2017','00','23'),('5630521422','1802410256358','Thip','Ruangrit','88','','Sukhothai','Pathumwan','Bangkok','F','1995-01-15','thrt@gmail.com','0834512553','2013','2017','10','21'),('5730196321','1111111111111','Danupat','Khamnuansin','113/14','Nares','4 Phaya','Bangrak','Bangkok','M','1996-04-30','jrkns1996@gmail.com','0892668633','2014','','10','21'),('5730197421','222222222222','Brown','Bigbear','15/14','Line Rd','4 Phaya','Bangrak','Bangkok','M','1996-05-15','brown@line.me','0899999999','2014','','10','21'),('5731045421','1101900159077','Thanaphat','Areechitsakul','50/496','Bangbon3','Bangbon','Bangbon','Bangkok','M','1996-08-12','thanaphata79@gmail.com','0897813023','2014','2018','10','21'),('5765435621','1245678965124','Force','Army','129/370','Bangbon3','Ruk2','Bangkae','Bangkok','M','1996-08-12','Force2@gmail.com','0891555553','2014','2018','10','21'),('5830458521','1850124258167','Rach','Pianpai','11/111','','Phaholyothin','Bangrak','Bangkok','M','1997-04-13','rachlnwza@gmail.com','0845201624','2015','2019','03','21'),('5831005221','1104250120122','Pattarawadee','Yingsakul','129/370','Bangbon3','Ruk2','Bangkae','Bangkok','M','1996-08-12','tthanaphata79@gmail.com','0897813023','2014','2018','10','21'),('5885945621','1225445465124','Than','Areful','52/49','Bangsan2','Bangsan','Meuang Chonburi','Chonburi','M','1996-08-13','than@gmail.com','0891588423','2015','2019','10','21'),('5935120221','3102150110153','Napat','Yenrudee','40/598','Akachai76','Bangbon','Bangbon','Bangkok','M','1998-01-21','NapatY@gmail.com','0841337435','2016','','10','21'),('5985945621','1222224445124','Kanika','God','52/49','Bangsan2','Bangsan','Meuang Chonburi','Chonburi','M','1996-04-13','kanikaGodmoon@gmail.com','0891588888','2016','2020','10','21');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'DB_Project'
--

--
-- Dumping routines for database 'DB_Project'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-02  2:52:36
