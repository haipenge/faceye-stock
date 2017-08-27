CREATE DATABASE  IF NOT EXISTS `faceye_root` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `faceye_root`;
-- MySQL dump 10.13  Distrib 5.6.13, for osx10.6 (i386)
--
-- Host: 127.0.0.1    Database: faceye_root
-- ------------------------------------------------------
-- Server version	5.6.14

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
-- Table structure for table `security_user_role`
--

DROP TABLE IF EXISTS `security_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_user_role` (
  `role_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_f56d9g77c0nbc6kyu2h9v7tix` (`user_id`),
  KEY `FK_qftn6eesvr93j7uavpecptmkr` (`role_id`),
  CONSTRAINT `FK_f56d9g77c0nbc6kyu2h9v7tix` FOREIGN KEY (`user_id`) REFERENCES `security_user` (`id`),
  CONSTRAINT `FK_qftn6eesvr93j7uavpecptmkr` FOREIGN KEY (`role_id`) REFERENCES `security_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user_role`
--

LOCK TABLES `security_user_role` WRITE;
/*!40000 ALTER TABLE `security_user_role` DISABLE KEYS */;
INSERT  IGNORE INTO `security_user_role` (`role_id`, `user_id`) VALUES (1,209),(2,210);
/*!40000 ALTER TABLE `security_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_menu_role`
--

DROP TABLE IF EXISTS `security_menu_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_menu_role` (
  `menu_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK_1lv42xx9ruwf84kdkwv6sifpq` (`role_id`),
  KEY `FK_nx2oahgulctm5g61bassx95fa` (`menu_id`),
  CONSTRAINT `FK_1lv42xx9ruwf84kdkwv6sifpq` FOREIGN KEY (`role_id`) REFERENCES `security_role` (`id`),
  CONSTRAINT `FK_nx2oahgulctm5g61bassx95fa` FOREIGN KEY (`menu_id`) REFERENCES `security_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_menu_role`
--

LOCK TABLES `security_menu_role` WRITE;
/*!40000 ALTER TABLE `security_menu_role` DISABLE KEYS */;
INSERT  IGNORE INTO `security_menu_role` (`menu_id`, `role_id`) VALUES (7,1),(8,1),(9,1),(10,1),(11,1);
/*!40000 ALTER TABLE `security_menu_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_menu`
--

DROP TABLE IF EXISTS `security_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `resource_id` bigint(20) NOT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_50t5t0q11h5j9wdec8qk57f2u` (`resource_id`),
  KEY `FK_50t5t0q11h5j9wdec8qk57f2u` (`resource_id`),
  KEY `FK_er03g9dnoydqnir77091ukden` (`parent_id`),
  CONSTRAINT `FK_50t5t0q11h5j9wdec8qk57f2u` FOREIGN KEY (`resource_id`) REFERENCES `security_resource` (`id`),
  CONSTRAINT `FK_er03g9dnoydqnir77091ukden` FOREIGN KEY (`parent_id`) REFERENCES `security_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_menu`
--

LOCK TABLES `security_menu` WRITE;
/*!40000 ALTER TABLE `security_menu` DISABLE KEYS */;
INSERT  IGNORE INTO `security_menu` (`id`, `name`, `type`, `resource_id`, `parent_id`) VALUES (7,'系统管理',1,8,NULL),(8,'用户管理',1,9,7),(9,'角色管理',1,10,7),(10,'资源管理',1,11,7),(11,'菜单管理',1,12,7);
/*!40000 ALTER TABLE `security_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_role`
--

DROP TABLE IF EXISTS `security_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_role`
--

LOCK TABLES `security_role` WRITE;
/*!40000 ALTER TABLE `security_role` DISABLE KEYS */;
INSERT  IGNORE INTO `security_role` (`id`, `name`) VALUES (1,'管理员'),(2,'用户');
/*!40000 ALTER TABLE `security_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_role_resource`
--

DROP TABLE IF EXISTS `security_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_role_resource` (
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  PRIMARY KEY (`resource_id`,`role_id`),
  KEY `FK_gccnse2a6193nkat9fhkhk7lw` (`resource_id`),
  KEY `FK_rhpl71m7fgrapqw0ufc8xrkas` (`role_id`),
  CONSTRAINT `FK_gccnse2a6193nkat9fhkhk7lw` FOREIGN KEY (`resource_id`) REFERENCES `security_resource` (`id`),
  CONSTRAINT `FK_rhpl71m7fgrapqw0ufc8xrkas` FOREIGN KEY (`role_id`) REFERENCES `security_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_role_resource`
--

LOCK TABLES `security_role_resource` WRITE;
/*!40000 ALTER TABLE `security_role_resource` DISABLE KEYS */;
INSERT  IGNORE INTO `security_role_resource` (`role_id`, `resource_id`) VALUES (1,8),(1,9),(1,10),(1,11),(1,12);
/*!40000 ALTER TABLE `security_role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_resource`
--

DROP TABLE IF EXISTS `security_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_resource`
--

LOCK TABLES `security_resource` WRITE;
/*!40000 ALTER TABLE `security_resource` DISABLE KEYS */;
INSERT  IGNORE INTO `security_resource` (`id`, `name`, `url`) VALUES (4,'测试','/ajax/register'),(5,'第一个','/ajax/test'),(6,'test-1','ajax/test'),(7,'tet','ss'),(8,'系统管理',''),(9,'用户管理','/security/user/home'),(10,'角色管理','/security/role/home'),(11,'资源管理','/security/resource/home'),(12,'菜单管理','/security/menu/home');
/*!40000 ALTER TABLE `security_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_user`
--

DROP TABLE IF EXISTS `security_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_expired` tinyint(1) DEFAULT NULL,
  `account_locked` tinyint(1) DEFAULT NULL,
  `credentials_expired` tinyint(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user`
--

LOCK TABLES `security_user` WRITE;
/*!40000 ALTER TABLE `security_user` DISABLE KEYS */;
INSERT  IGNORE INTO `security_user` (`id`, `account_expired`, `account_locked`, `credentials_expired`, `email`, `enabled`, `password`, `name`) VALUES (209,0,0,0,'haipenge@gmail.com',1,'21232f297a57a5a743894a0e4a801fc3','admin'),(210,0,0,0,'test@sohu.com',1,'098f6bcd4621d373cade4e832627b4f6','test');
/*!40000 ALTER TABLE `security_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-16 20:52:45
