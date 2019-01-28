-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sbs
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `node_resource`
--

DROP TABLE IF EXISTS `node_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `node_resource` (
  `node_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  KEY `FKl4vbs2joiwp96mk7o6ygld97h` (`resource_id`),
  KEY `FKkswnpw5dnk1h99gneqi24yel4` (`node_id`),
  CONSTRAINT `FKkswnpw5dnk1h99gneqi24yel4` FOREIGN KEY (`node_id`) REFERENCES `t_node` (`id`),
  CONSTRAINT `FKl4vbs2joiwp96mk7o6ygld97h` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `node_resource`
--

LOCK TABLES `node_resource` WRITE;
/*!40000 ALTER TABLE `node_resource` DISABLE KEYS */;
INSERT INTO `node_resource` VALUES (6,13),(6,17),(6,18),(6,25),(6,38),(6,42),(6,43),(6,44),(7,9),(7,19),(7,13),(7,20),(7,26),(3,6),(3,7),(3,8),(3,9),(3,22),(3,28),(3,30),(3,31),(4,10),(4,11),(4,12),(4,13),(4,23),(4,36),(4,37),(8,14),(8,15),(8,16),(8,40),(8,41),(8,45),(8,46),(2,3),(2,5),(2,1),(2,4),(2,21),(2,27),(2,32),(2,33),(2,38),(11,46),(11,38),(11,50),(11,49),(11,47),(11,48),(11,51);
/*!40000 ALTER TABLE `node_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_node`
--

DROP TABLE IF EXISTS `role_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role_node` (
  `role_id` int(11) NOT NULL,
  `node_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`node_id`),
  KEY `FKsytjunjpdjywu1jqum3bi5u1t` (`node_id`),
  CONSTRAINT `FK9txd1kpxo4g5xjs9l1fregrv6` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FKsytjunjpdjywu1jqum3bi5u1t` FOREIGN KEY (`node_id`) REFERENCES `t_node` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_node`
--

LOCK TABLES `role_node` WRITE;
/*!40000 ALTER TABLE `role_node` DISABLE KEYS */;
INSERT INTO `role_node` VALUES (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4),(1,5),(1,6),(1,7),(1,8),(1,11);
/*!40000 ALTER TABLE `role_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_resource`
--

DROP TABLE IF EXISTS `role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `role_resource` (
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  KEY `FKar4g14kkyfk56pvv6gpwa177d` (`resource_id`),
  KEY `FK2v4tr27ijtejkp2t65j3850in` (`role_id`),
  CONSTRAINT `FK2v4tr27ijtejkp2t65j3850in` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FKar4g14kkyfk56pvv6gpwa177d` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_resource`
--

LOCK TABLES `role_resource` WRITE;
/*!40000 ALTER TABLE `role_resource` DISABLE KEYS */;
INSERT INTO `role_resource` VALUES (1,3),(1,5),(1,1),(1,4),(1,21),(1,27),(1,32),(1,33),(1,38),(1,6),(1,7),(1,8),(1,9),(1,22),(1,28),(1,30),(1,31),(1,10),(1,11),(1,12),(1,13),(1,23),(1,36),(1,37),(1,14),(1,15),(1,16),(1,40),(1,41),(1,45),(1,46),(1,13),(1,17),(1,18),(1,25),(1,38),(1,42),(1,43),(1,44),(1,9),(1,19),(1,13),(1,20),(1,26),(1,46),(1,38),(1,50),(1,49),(1,47),(1,51),(2,3),(2,5),(2,1),(2,4),(2,21),(2,27),(2,32),(2,33),(2,38),(2,6),(2,7),(2,9),(2,22),(2,28),(2,30),(2,31),(2,10),(2,11),(2,12),(2,13),(2,23),(2,36),(2,37),(2,13),(2,38),(2,9),(2,13),(2,38);
/*!40000 ALTER TABLE `role_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_node`
--

DROP TABLE IF EXISTS `t_node`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7hfllck3b10nyhosdvdc4haqy` (`parent_id`),
  CONSTRAINT `FK7hfllck3b10nyhosdvdc4haqy` FOREIGN KEY (`parent_id`) REFERENCES `t_node` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_node`
--

LOCK TABLES `t_node` WRITE;
/*!40000 ALTER TABLE `t_node` DISABLE KEYS */;
INSERT INTO `t_node` VALUES (1,'基础资料','',NULL),(2,'菜单管理','/node/toMenu',1),(3,'用户管理','/user/toList',1),(4,'角色管理','/role/toList',1),(5,'权限管理','',NULL),(6,'角色授权限','/permission/toRoleNodes',5),(7,'用户授角色','/permission/toUserRole',5),(8,'资源管理','/resource/toListAll',1),(11,'节点授资源 ','/permission/toNodeGrantRes',5);
/*!40000 ALTER TABLE `t_node` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_resource`
--

DROP TABLE IF EXISTS `t_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_resource`
--

LOCK TABLES `t_resource` WRITE;
/*!40000 ALTER TABLE `t_resource` DISABLE KEYS */;
INSERT INTO `t_resource` VALUES (1,'','跳转到增加根节点页面','node:addRoot1','/node/toAddRoot'),(3,'','修改节点','node:update2','/node/addNode'),(4,'','删除节点','node:del','/node/delNode'),(5,'','跳转到修改节点页面','node:update1','/node/toUpdateNode'),(6,'','跳转到新增用户页面','user:add1','/user/toAdd'),(7,'','跳转到修改用户页面','user:update1','/user/toAdd'),(8,'','删除员工','user:del','/user/delByIds'),(9,'','用户列表','user:list2','/user/findUsers'),(10,'','跳转到角色新增页','role:add1','/role/toAdd'),(11,'','跳转到角色修改页','role:update1','/role/toAdd'),(12,'','删除角色','role:del','/role/delByIds'),(13,'','查询角色','role:list2','/role/findRoles'),(14,'','跳转到新增资源页面','resource:add1','/resource/toAdd'),(15,'','修改资源','resource:update2','/resource/toAdd'),(16,'','删除资源','resource:del','/resource/delById'),(17,'','跳转到分配节点页面','permission:toRoleGrantNode1','/permission/multiSelectTree'),(18,'','给角色分配菜单节点','permission:toRoleGrantNode2','/permission/grantNodes'),(19,'','用户选择角色','permission:toUserGrantRole2','/permission/toSelectRole'),(20,'','给用户授角色','permission:toUserGrantRole3','/permission/grantRoles'),(21,'','菜单列表','node:list','/node/toMenu'),(22,'','跳转到用户列表页','user:list1','/user/toList'),(23,'','跳转到角色列表','role:list1','/role/toList'),(25,'','显示角色权限','permission:list','/permission/toRoleNodes'),(26,'','跳转到给用户授角色页面','permission:toUserGrantRole1','/permission/toUserRole'),(27,'','跳转到增加子节点页面','node:addNode1','/node/toAddNode'),(28,'','新增用户','user:add2','/user/add'),(30,'','修改用户回显','user:update2','/user/findUserById/*'),(31,'','修改用户','user:update3','/user/add'),(32,'','增加根节点','node:addRoot2','/node/addNode'),(33,'','增加子节点','node:addNode2','/node/addNode'),(36,'','角色新增','role:add2','/role/add'),(37,'','角色修改','role:update2','/role/add'),(38,'','显示菜单树','node:listTree','/node/findLeftTree'),(40,'','新增资源','resource:add2','/resource/add'),(41,'','跳转到修改资源页面','resource:update1','/resource/add'),(42,'','给角色分配资源1','permission:toRoleGrantRes1','/permission/toRoleRes'),(43,'','给角色分配资源2','permission:toRoleGrantRes2','/permission/findReses'),(44,'','给角色分配资源3','permission:toRoleGrantRes3','/permission/grantReses'),(45,'','跳转到资源列表','resource:list1','/resource/toListAll'),(46,'','查询资源','resource:list2','/resource/findReses'),(47,'','获取节点已有资源','permission:viewNodeRes','/node/getResIdes'),(48,'','节点授资源','permission:toNodeGrantRes','/node/grantReses'),(49,'','跳转到给节点授资源页面','permission:toNodeGrantRes','/permission/toNodeGrantRes'),(50,'','查询节点拥有资源','permission:nodeHasRes','/permission/findResByNode'),(51,'','节点选择资源','permission:nodeSelectRes','/permission/toSelectReses');
/*!40000 ALTER TABLE `t_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `short_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'系统管理员','admin'),(2,'普通用户','c1');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jhib4legehrm4yscx9t3lirqi` (`username`),
  KEY `FK76cs7cu4h4y8vc1vd4qyw36rt` (`role_id`),
  CONSTRAINT `FK76cs7cu4h4y8vc1vd4qyw36rt` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'tom','93c3ef4bc1b01b865028192eeda1a41d','tom',1),(2,'mike','9d6c82f7650bdf2dd881134db45d1b41','mike',2);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-28 11:57:02
