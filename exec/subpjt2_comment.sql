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
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `boardID` int DEFAULT NULL,
  `feedID` int DEFAULT NULL,
  `commentID` int NOT NULL AUTO_INCREMENT,
  `parentComment` int DEFAULT NULL,
  `content` varchar(4000) NOT NULL,
  `userID` varchar(50) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `recommendCnt` int DEFAULT NULL,
  PRIMARY KEY (`commentID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,NULL,1,NULL,'댓글1','test1',NULL,NULL),(1,NULL,2,NULL,'댓글2','테스트',NULL,NULL),(1,NULL,4,2,'대댓2','대댓테스트',NULL,NULL),(1,NULL,5,1,'대댓1','대댓테스트',NULL,NULL),(1,NULL,6,1,'대댓1','대댓테스트',NULL,NULL),(1,NULL,7,1,'대댓1','대댓테스트',NULL,NULL),(1,NULL,8,2,'대댓1','대댓테스트',NULL,NULL),(1,NULL,9,2,'대댓1','대댓테스트',NULL,NULL),(1,NULL,10,2,'대댓1','대댓테스트',NULL,NULL),(1,NULL,11,NULL,'댓글3','test',NULL,NULL),(1,NULL,12,NULL,'댓글3','test',NULL,NULL),(101,NULL,13,0,'댓글등록','1234','2021-08-04 18:49:02',0),(101,NULL,14,0,'댓글등록','1234','2021-08-09 10:46:50',0),(0,NULL,15,0,'되려나?','1234','2021-08-09 11:10:24',0),(101,NULL,17,0,'댓글이 안나와','1234','2021-08-09 11:22:35',0),(101,NULL,18,0,'??뭐지','1234','2021-08-09 11:23:18',0),(NULL,11,31,0,'댓글삭제 테스트','1234','2021-08-09 11:39:28',0),(NULL,11,32,0,'댓글삭제테스트','1234','2021-08-09 11:39:33',0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-19 17:27:37
