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
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `boardID` int NOT NULL AUTO_INCREMENT,
  `title` varchar(500) NOT NULL,
  `content` varchar(4000) NOT NULL,
  `writer` varchar(50) NOT NULL,
  `date` datetime NOT NULL,
  `photoPath1` varchar(255) DEFAULT NULL,
  `photoPath2` varchar(255) DEFAULT NULL,
  `photoPath3` varchar(255) DEFAULT NULL,
  `photoPath4` varchar(255) DEFAULT NULL,
  `recommend` int DEFAULT NULL,
  `viewCount` int DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`boardID`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'제목1','내용1','작성자1','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'제목2','내용2','작성자2','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'제목3','내용3','작성자3','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'제목4','내용4','작성자4','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'제목5','내용5','작성자5','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'제목6','내용6','작성자6','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,'제목7','내용7','작성자7','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'제목8','내용8','작성자8','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,'제목9','내용9','작성자9','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'제목10','내용10','작성자10','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'제목11','내용11','작성자11','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'제목12','내용12','작성자12','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,'제목13','내용13','작성자13','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,0,NULL),(14,'제목14','내용14','작성자14','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(15,'제목15','내용15','작성자15','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(16,'제목16','내용16','작성자16','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(17,'제목17','내용17','작성자17','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(18,'제목18','내용18','작성자18','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(19,'제목19','내용19','작성자19','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(20,'제목20','내용20','작성자20','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(21,'제목21','내용21','작성자21','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(22,'제목22','내용22','작성자22','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(23,'제목23','내용23','작성자23','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(24,'제목24','내용24','작성자24','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(25,'제목25','내용25','작성자25','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(26,'제목26','내용26','작성자26','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(27,'제목27','내용27','작성자27','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(28,'제목28','내용28','작성자28','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(29,'제목29','내용29','작성자29','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,'제목30','내용30','작성자30','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(31,'제목31','내용31','작성자31','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,'제목32','내용32','작성자32','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(33,'제목33','내용33','작성자33','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(34,'제목34','내용34','작성자34','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(35,'제목35','내용35','작성자35','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(36,'제목36','내용36','작성자36','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(37,'제목37','내용37','작성자37','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(38,'제목38','내용38','작성자38','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(39,'제목39','내용39','작성자39','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,0,NULL),(40,'제목40','내용40','작성자40','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(41,'제목41','내용41','작성자41','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(42,'제목42','내용42','작성자42','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(43,'제목43','내용43','작성자43','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(44,'제목44','내용44','작성자44','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(45,'제목45','내용45','작성자45','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(46,'제목46','내용46','작성자46','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(47,'제목47','내용47','작성자47','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(48,'제목48','내용48','작성자48','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(49,'제목49','내용49','작성자49','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(50,'제목50','내용50','작성자50','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(51,'제목51','내용51','작성자51','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(52,'제목52','내용52','작성자52','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(53,'제목53','내용53','작성자53','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(54,'제목54','내용54','작성자54','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(55,'제목55','내용55','작성자55','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(56,'제목56','내용56','작성자56','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(57,'제목57','내용57','작성자57','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(58,'제목58','내용58','작성자58','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(59,'제목59','내용59','작성자59','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(60,'제목60','내용60','작성자60','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(61,'제목61','내용61','작성자61','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(62,'제목62','내용62','작성자62','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(63,'제목63','내용63','작성자63','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(64,'제목64','내용64','작성자64','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(65,'제목65','내용65','작성자65','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(66,'제목66','내용66','작성자66','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(67,'제목67','내용67','작성자67','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(68,'제목68','내용68','작성자68','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(69,'제목69','내용69','작성자69','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(70,'제목70','내용70','작성자70','2021-07-26 15:24:22',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(71,'제목71','내용71','작성자71','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(72,'제목72','내용72','작성자72','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(73,'제목73','내용73','작성자73','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(74,'제목74','내용74','작성자74','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(75,'제목75','내용75','작성자75','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(76,'제목76','내용76','작성자76','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,0,NULL),(77,'제목77','내용77','작성자77','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,1,NULL),(78,'제목78','내용78','작성자78','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,1,NULL),(79,'제목79','내용79','작성자79','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80,'제목80','내용80','작성자80','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,1,NULL),(81,'제목81','내용81','작성자81','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(82,'제목82','내용82','작성자82','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(83,'제목83','내용83','작성자83','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(84,'제목84','내용84','작성자84','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(85,'제목85','내용85','작성자85','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(86,'제목86','내용86','작성자86','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(87,'제목87','내용87','작성자87','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(88,'제목88','내용88','작성자88','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(89,'제목89','내용89','작성자89','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(90,'제목90','내용90','작성자90','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(91,'제목91','내용91','작성자91','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(92,'제목92','내용92','작성자92','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(93,'제목93','내용93','작성자93','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(94,'제목94','내용94','작성자94','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(95,'제목95','내용95asdasda수정 testasdad','작성자95','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,0,NULL),(100,'제목100','내용100','작성자100','2021-07-26 15:24:23',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(101,'asd','adsasd','asdsa','2021-07-27 11:23:59',NULL,NULL,NULL,NULL,0,5,NULL),(102,'test','test','test','2021-08-13 09:35:38',NULL,NULL,NULL,NULL,0,2,'일반');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
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
