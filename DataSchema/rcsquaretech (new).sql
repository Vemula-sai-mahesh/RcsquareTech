-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: rcsquaretech
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `blog_tags`
--

DROP TABLE IF EXISTS `blog_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blog_tags` (
  `blog_id` binary(16) NOT NULL,
  `tags` varchar(255) DEFAULT NULL,
  KEY `FKknhfjqf24lyrdbfobo3qm09e6` (`blog_id`),
  CONSTRAINT `FKknhfjqf24lyrdbfobo3qm09e6` FOREIGN KEY (`blog_id`) REFERENCES `blogs` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blog_tags`
--

LOCK TABLES `blog_tags` WRITE;
/*!40000 ALTER TABLE `blog_tags` DISABLE KEYS */;
INSERT INTO `blog_tags` VALUES (_binary '\Zt\œHOC⁄í\≈–èZ⁄ß','javascript'),(_binary '\Zt\œHOC⁄í\≈–èZ⁄ß','work'),(_binary '\Zt\œHOC⁄í\≈–èZ⁄ß','yes');
/*!40000 ALTER TABLE `blog_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `blogs`
--

DROP TABLE IF EXISTS `blogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blogs` (
  `id` binary(16) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `content` longtext,
  `cover_image` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `is_draft` bit(1) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blogs`
--

LOCK TABLES `blogs` WRITE;
/*!40000 ALTER TABLE `blogs` DISABLE KEYS */;
INSERT INTO `blogs` VALUES (_binary '\Zt\œHOC⁄í\≈–èZ⁄ß','development','<p>Founded in 2006 by Yasser Ali and Nayyer Hussain, Amore Gelato brings a freshly crafted range of delicious Italian gelatos to India! With the mission to make premium gelato affordable for everyone, the brand has flourished over the years. Once a 100 square feet outlet in Bandra, Mumbai, it has now expanded to 30+ outlets across key Indian cities like Mumbai, Hyderabad, and Pune.</p><p><img src=\"http://localhost:8080/image/image_1735966500666.png\"></p><p><br></p><p>Yasser always loved ice creams! When he went to university in America, he ended up working at a local ice cream parlor and quickly gained a reputation for his sundaes and milkshakes. It was a passion he carried with him, which would eventually lead to the creation of Amore Gelato.</p><p>During his college time, Yasser visited India, where the roots of his family belonged. Trekking through the Himalayas, he realised that he wanted to return home. Yasser adds,&nbsp;<strong><em>‚ÄúI got a job at a bar in Santa Barbara and did everything from unclogging toilets to taking out the garbage to save enough money for the big move.‚Äù</em></strong></p><p>Yasser finally made his way back to India in 2002. He started an NGO called RISE, focused on rebuilding schools and advancing education throughout the country. During this period, he chose to live in an ashram in Rishikesh for a couple of months and devoted his time to meditation.</p><p><img src=\"http://localhost:8080/image/image_1736057121294.png\"></p>','http://localhost:8080/image/Resize-1-1_1736057109273.jpg','2025-01-04 05:35:40.227000',_binary '\0','The Big Brand Theory | From Trekking Himalayan Slopes to Raising Rs. 0.75 Crores at Shark Tank',NULL);
/*!40000 ALTER TABLE `blogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'$2a$10$1eVOyhJ6IzaDcqP9QmQ9i.uOBHq4pR4kcG2VgIsr8SKYdwIUeWWxq','admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-08 11:38:54
