-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: miaomiaohelp
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `comment_id` int NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `post_id` bigint NOT NULL COMMENT '关联的帖子id(外键)',
  `user_id` int NOT NULL COMMENT '发表评论的用户ID（外键）',
  `comment_text` text NOT NULL COMMENT '评论内容',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论时间 ',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间 ',
  PRIMARY KEY (`comment_id`),
  KEY `post_id` (`post_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `like_id` int NOT NULL AUTO_INCREMENT COMMENT '点赞记录ID ',
  `post_id` bigint NOT NULL COMMENT '关联的帖子ID (外键) ',
  `user_id` int NOT NULL COMMENT ' 点赞用户ID (外键)',
  `liked_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '点赞时间 ',
  PRIMARY KEY (`like_id`),
  UNIQUE KEY `post_id` (`post_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`),
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_assignments`
--

DROP TABLE IF EXISTS `order_assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_assignments` (
  `assignment_id` int NOT NULL AUTO_INCREMENT COMMENT '接单ID ',
  `order_id` int DEFAULT NULL COMMENT '订单ID (外键) ',
  `runner_id` int DEFAULT NULL COMMENT '跑腿者用户ID (外键) ',
  `assigned_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '接单时间 ',
  `completed_at` timestamp NULL DEFAULT NULL COMMENT '完成时间 ',
  `status` varchar(50) DEFAULT 'assigned' COMMENT '订单状态（已接单/进行中/已完成）',
  PRIMARY KEY (`assignment_id`),
  KEY `order_id` (`order_id`),
  KEY `runner_id` (`runner_id`),
  CONSTRAINT `order_assignments_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `order_assignments_ibfk_2` FOREIGN KEY (`runner_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_assignments`
--

LOCK TABLES `order_assignments` WRITE;
/*!40000 ALTER TABLE `order_assignments` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单ID ',
  `user_id` int DEFAULT NULL COMMENT '发布者用户ID (外键) ',
  `item_name` varchar(100) DEFAULT NULL COMMENT '物品名称 ',
  `item_image_url` varchar(255) DEFAULT NULL COMMENT '物品图片链接 ',
  `start_location` varchar(255) DEFAULT NULL COMMENT '起始位置 ',
  `end_location` varchar(255) DEFAULT NULL COMMENT '目的地位置 ',
  `price` decimal(10,2) DEFAULT NULL COMMENT '订单价格',
  `phone_number` varchar(15) DEFAULT NULL COMMENT '联系电话（接单后显示给跑腿者） ',
  `status` enum('pending','completed','cancelled') DEFAULT 'pending' COMMENT '订单状态 ',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布订单时间 ',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间 ',
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单ID ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_changes`
--

DROP TABLE IF EXISTS `password_changes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `password_changes` (
  `change_id` int NOT NULL AUTO_INCREMENT COMMENT '修改记录ID ',
  `user_id` int DEFAULT NULL COMMENT '用户ID (外键) ',
  `old_password` varchar(255) DEFAULT NULL COMMENT '旧密码 ',
  `new_password` varchar(255) DEFAULT NULL COMMENT '新密码 ',
  `changed_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT ' 修改时间\n',
  PRIMARY KEY (`change_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `password_changes_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_changes`
--

LOCK TABLES `password_changes` WRITE;
/*!40000 ALTER TABLE `password_changes` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_changes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '帖子ID ',
  `user_id` int NOT NULL COMMENT '用户ID (外键)',
  `text_content` varchar(255) DEFAULT NULL COMMENT '文字内容 ',
  `image_url` varchar(255) DEFAULT NULL COMMENT '图片链接 ',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间 ',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间 ',
  PRIMARY KEY (`post_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,1,'我发送的第一条测试帖子',NULL,'2024-12-13 13:50:17','2024-12-13 13:50:17'),(2,1,'上传帖子测试',NULL,'2024-12-13 14:02:21','2024-12-13 14:02:21'),(3,1,'测试帖子','/uploads/1734103134949_1.jpg','2024-12-13 15:18:55','2024-12-13 15:18:55');
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_addresses`
--

DROP TABLE IF EXISTS `user_addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_addresses` (
  `address_id` int NOT NULL AUTO_INCREMENT COMMENT '地址id(主键)',
  `user_id` int NOT NULL COMMENT '用户id(外键，关联users表)',
  `address_name` varchar(100) DEFAULT NULL COMMENT '地址名称',
  `address_line2` varchar(255) DEFAULT NULL COMMENT '地址额外信息（可选，地址详细信息）',
  `is_default` tinyint(1) DEFAULT '0' COMMENT '是否为默认地址',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '地址创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '地址更新时间',
  PRIMARY KEY (`address_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_addresses_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_addresses`
--

LOCK TABLES `user_addresses` WRITE;
/*!40000 ALTER TABLE `user_addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `account` varchar(50) NOT NULL COMMENT '账号',
  `username` varchar(50) NOT NULL COMMENT '用户名 ',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone_number` varchar(15) DEFAULT NULL COMMENT '手机号码 ',
  `avatar_url` varchar(255) DEFAULT NULL COMMENT '头像链接 ',
  `user_role` enum('publisher','runner') DEFAULT 'publisher' COMMENT '用户身份（发布者/跑腿者） ',
  `status` enum('active','inactive') DEFAULT 'active' COMMENT '用户状态（激活/未激活） ',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间 ',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'1981027129','bill','1981027129','19200957731',NULL,'publisher','active','2024-12-13 13:49:22','2024-12-13 13:49:22');
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

-- Dump completed on 2024-12-27  7:45:53
