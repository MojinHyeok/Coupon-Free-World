-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: subpjt2
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `feed`
--

DROP TABLE IF EXISTS `feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feed` (
  `feedId` int NOT NULL AUTO_INCREMENT,
  `userID` varchar(45) NOT NULL,
  `photoPath` varchar(5000) DEFAULT NULL,
  `content` varchar(4000) DEFAULT NULL,
  `date` datetime NOT NULL,
  `likeCnt` int NOT NULL,
  PRIMARY KEY (`feedId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed`
--

LOCK TABLES `feed` WRITE;
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
INSERT INTO `feed` VALUES (3,'1234','https://subpjt.s3.ap-northeast-2.amazonaws.com/feed/3.PNG','12313','2021-08-03 19:28:07',0),(4,'1234','https://subpjt.s3.ap-northeast-2.amazonaws.com/feed/3.PNG','ㅁㄴㅇ','2021-08-03 19:40:17',1),(6,'1234','https://subpjt.s3.ap-northeast-2.amazonaws.com/feed/3.PNG','ㅁㄴㅇ','2021-08-03 19:46:55',1),(8,'test0','https://subpjt.s3.ap-northeast-2.amazonaws.com/feed/1.PNG','내용TEST','2021-08-04 19:02:46',0),(9,'test0','https://subpjt.s3.ap-northeast-2.amazonaws.com/feed/1.PNG','내용TEST123123','2021-08-04 19:03:44',0),(10,'test0','https://subpjt.s3.ap-northeast-2.amazonaws.com/feed/1.PNG','TEST12312312','2021-08-04 19:04:07',1),(11,'1234','https://subpjt.s3.ap-northeast-2.amazonaws.com/feed/sample_images_01.png|https://subpjt.s3.ap-northeast-2.amazonaws.com/feed/sample_images_08.png|https://subpjt.s3.ap-northeast-2.amazonaws.com/feed/sample_images_09.png','내요으 테스트입니다,~!','2021-08-05 11:42:21',1);
/*!40000 ALTER TABLE `feed` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-19 17:27:38
