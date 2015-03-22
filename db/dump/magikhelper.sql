-- MySQL dump 10.13  Distrib 5.6.10, for osx10.7 (x86_64)
--
-- Host: localhost    Database: magikhelper
-- ------------------------------------------------------
-- Server version	5.6.10

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
-- Table structure for table `application_properties`
--

DROP TABLE IF EXISTS `application_properties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_properties` (
  `property_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(250) CHARACTER SET latin1 NOT NULL,
  `name` varchar(250) CHARACTER SET latin1 NOT NULL,
  `value` varchar(2000) CHARACTER SET latin1 DEFAULT NULL,
  `sort_order` int(10) unsigned NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(50) CHARACTER SET latin1 DEFAULT NULL,
  `is_active` varchar(1) CHARACTER SET latin1 DEFAULT '1',
  PRIMARY KEY (`property_id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1308 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_properties`
--

LOCK TABLES `application_properties` WRITE;
/*!40000 ALTER TABLE `application_properties` DISABLE KEYS */;
INSERT INTO `application_properties` VALUES (1,'SERVICES','Furniture Assembly','Furniture Assembly',1,'2015-03-21 19:17:08','System','2015-03-21 19:17:08','System','1'),(2,'SERVICES','Cleaning Help','Cleaning Help',2,'2015-03-21 19:17:08','System','2015-03-21 19:17:08','System','1'),(3,'SERVICES','Moving Help','Moving Help',3,'2015-03-21 19:17:08','System','2015-03-21 19:17:08','System','1'),(4,'SERVICES','Gardening Help','Gardening Help',4,'2015-03-21 19:17:08','System','2015-03-21 19:17:08','System','1'),(5,'SERVICES','General Labor Help','General Labor Help',5,'2015-03-21 19:17:08','System','2015-03-21 19:17:08','System','1'),(6,'BOOKING_STATUS','Active','Active',1,'2015-03-21 19:17:08','System','2015-03-21 19:17:08','System','1'),(7,'BOOKING_STATUS','Completed','Completed',2,'2015-03-21 19:17:08','System','2015-03-21 19:17:08','System','1'),(8,'BOOKING_STATUS','Cancelled','Cancelled',3,'2015-03-21 19:17:08','System','2015-03-21 19:17:08','System','1'),(1001,'COUNTRIES','Afghanistan','Afghanistan',1,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1002,'COUNTRIES','Aringland Islands','Aringland Islands',2,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1003,'COUNTRIES','Albania','Albania',3,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1004,'COUNTRIES','Algeria','Algeria',4,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1005,'COUNTRIES','American Samoa','American Samoa',5,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1006,'COUNTRIES','Andorra','Andorra',6,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1007,'COUNTRIES','Angola','Angola',7,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1008,'COUNTRIES','Anguilla','Anguilla',8,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1009,'COUNTRIES','Antarctica','Antarctica',9,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1010,'COUNTRIES','Antigua and Barbuda','Antigua and Barbuda',10,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1011,'COUNTRIES','Argentina','Argentina',11,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1012,'COUNTRIES','Armenia','Armenia',12,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1013,'COUNTRIES','Aruba','Aruba',13,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1014,'COUNTRIES','Australia','Australia',14,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1015,'COUNTRIES','Austria','Austria',15,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1016,'COUNTRIES','Azerbaijan','Azerbaijan',16,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1017,'COUNTRIES','Bahamas','Bahamas',17,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1018,'COUNTRIES','Bahrain','Bahrain',18,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1019,'COUNTRIES','Bangladesh','Bangladesh',19,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1020,'COUNTRIES','Barbados','Barbados',20,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1021,'COUNTRIES','Belgium','Belgium',21,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1022,'COUNTRIES','Belize','Belize',22,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1023,'COUNTRIES','Benin','Benin',23,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1024,'COUNTRIES','Bermuda','Bermuda',24,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1025,'COUNTRIES','Bhutan','Bhutan',25,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1026,'COUNTRIES','Bolivia','Bolivia',26,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1027,'COUNTRIES','Bosnia and Herzegovina','Bosnia and Herzegovina',27,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1028,'COUNTRIES','Botswana','Botswana',28,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1029,'COUNTRIES','Bouvet Island','Bouvet Island',29,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1030,'COUNTRIES','Brazil','Brazil',30,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1031,'COUNTRIES','British Indian Ocean territory','British Indian Ocean territory',31,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1032,'COUNTRIES','Brunei Darussalam','Brunei Darussalam',32,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1033,'COUNTRIES','Bulgaria','Bulgaria',33,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1034,'COUNTRIES','Burkina Faso','Burkina Faso',34,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1035,'COUNTRIES','Burundi','Burundi',35,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1036,'COUNTRIES','Cambodia','Cambodia',36,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1037,'COUNTRIES','Cameroon','Cameroon',37,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1038,'COUNTRIES','Canada','Canada',38,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1039,'COUNTRIES','Cape Verde','Cape Verde',39,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1040,'COUNTRIES','Cayman Islands','Cayman Islands',40,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1041,'COUNTRIES','Central African Republic','Central African Republic',41,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1042,'COUNTRIES','Chad','Chad',42,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1043,'COUNTRIES','Chile','Chile',43,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1044,'COUNTRIES','China','China',44,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1045,'COUNTRIES','Christmas Island','Christmas Island',45,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1046,'COUNTRIES','Cocos (Keeling) Islands','Cocos (Keeling) Islands',46,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1047,'COUNTRIES','Colombia','Colombia',47,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1048,'COUNTRIES','Comoros','Comoros',48,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1049,'COUNTRIES','Congo','Congo',49,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1050,'COUNTRIES','Cook Islands','Cook Islands',50,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1051,'COUNTRIES','Costa Rica','Costa Rica',51,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1052,'COUNTRIES','Croatia (Hrvatska)','Croatia (Hrvatska)',52,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1053,'COUNTRIES','Cyprus','Cyprus',53,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1054,'COUNTRIES','Czech Republic','Czech Republic',54,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1055,'COUNTRIES','Denmark','Denmark',55,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1056,'COUNTRIES','Djibouti','Djibouti',56,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1057,'COUNTRIES','Dominica','Dominica',57,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1058,'COUNTRIES','Dominican Republic','Dominican Republic',58,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1059,'COUNTRIES','East Timor','East Timor',59,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1060,'COUNTRIES','Ecuador','Ecuador',60,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1061,'COUNTRIES','Egypt','Egypt',61,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1062,'COUNTRIES','El Salvador','El Salvador',62,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1063,'COUNTRIES','Equatorial Guinea','Equatorial Guinea',63,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1064,'COUNTRIES','Eritrea','Eritrea',64,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1065,'COUNTRIES','Estonia','Estonia',65,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1066,'COUNTRIES','Ethiopia','Ethiopia',66,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1067,'COUNTRIES','Falkland Islands','Falkland Islands',67,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1068,'COUNTRIES','Faroe Islands','Faroe Islands',68,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1069,'COUNTRIES','Fiji','Fiji',69,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1070,'COUNTRIES','Finland','Finland',70,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1071,'COUNTRIES','France','France',71,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1072,'COUNTRIES','French Guiana','French Guiana',72,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1073,'COUNTRIES','French Polynesia','French Polynesia',73,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1074,'COUNTRIES','French Southern Territories','French Southern Territories',74,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1075,'COUNTRIES','Gabon','Gabon',75,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1076,'COUNTRIES','Gambia','Gambia',76,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1077,'COUNTRIES','Georgia','Georgia',77,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1078,'COUNTRIES','Germany','Germany',78,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1079,'COUNTRIES','Ghana','Ghana',79,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1080,'COUNTRIES','Gibraltar','Gibraltar',80,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1081,'COUNTRIES','Greece','Greece',81,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1082,'COUNTRIES','Greenland','Greenland',82,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1083,'COUNTRIES','Grenada','Grenada',83,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1084,'COUNTRIES','Guadeloupe','Guadeloupe',84,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1085,'COUNTRIES','Guam','Guam',85,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1086,'COUNTRIES','Guatemala','Guatemala',86,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1087,'COUNTRIES','Guinea','Guinea',87,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1088,'COUNTRIES','Guinea-Bissau','Guinea-Bissau',88,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1089,'COUNTRIES','Guyana','Guyana',89,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1090,'COUNTRIES','Haiti','Haiti',90,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1091,'COUNTRIES','Heard and McDonald Islands','Heard and McDonald Islands',91,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1092,'COUNTRIES','Honduras','Honduras',92,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1093,'COUNTRIES','Hong Kong','Hong Kong',93,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1094,'COUNTRIES','Hungary','Hungary',94,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1095,'COUNTRIES','Iceland','Iceland',95,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1096,'COUNTRIES','India','India',96,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1097,'COUNTRIES','Indonesia','Indonesia',97,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1098,'COUNTRIES','Ireland','Ireland',98,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1099,'COUNTRIES','Israel','Israel',99,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1100,'COUNTRIES','Italy','Italy',100,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1101,'COUNTRIES','Jamaica','Jamaica',101,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1102,'COUNTRIES','Japan','Japan',102,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1103,'COUNTRIES','Jordan','Jordan',103,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1104,'COUNTRIES','Kazakhstan','Kazakhstan',104,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1105,'COUNTRIES','Kenya','Kenya',105,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1106,'COUNTRIES','Kiribati','Kiribati',106,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1107,'COUNTRIES','Korea (south)','Korea (south)',107,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1108,'COUNTRIES','Kuwait','Kuwait',108,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1109,'COUNTRIES','Kyrgyzstan','Kyrgyzstan',109,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1110,'COUNTRIES','Lao People\'s Democratic Republic','Lao People\'s Democratic Republic',110,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1111,'COUNTRIES','Latvia','Latvia',111,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1112,'COUNTRIES','Lebanon','Lebanon',112,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1113,'COUNTRIES','Lesotho','Lesotho',113,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1114,'COUNTRIES','Libyan Arab Jamahiriya','Libyan Arab Jamahiriya',114,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1115,'COUNTRIES','Liechtenstein','Liechtenstein',115,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1116,'COUNTRIES','Lithuania','Lithuania',116,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1117,'COUNTRIES','Luxembourg','Luxembourg',117,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1118,'COUNTRIES','Macao','Macao',118,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1119,'COUNTRIES','Macedonia','Macedonia',119,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1120,'COUNTRIES','Madagascar','Madagascar',120,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1121,'COUNTRIES','Malawi','Malawi',121,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1122,'COUNTRIES','Malaysia','Malaysia',122,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1123,'COUNTRIES','Maldives','Maldives',123,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1124,'COUNTRIES','Mali','Mali',124,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1125,'COUNTRIES','Malta','Malta',125,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1126,'COUNTRIES','Marshall Islands','Marshall Islands',126,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1127,'COUNTRIES','Martinique','Martinique',127,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1128,'COUNTRIES','Mauritania','Mauritania',128,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1129,'COUNTRIES','Mauritius','Mauritius',129,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1130,'COUNTRIES','Mayotte','Mayotte',130,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1131,'COUNTRIES','Mexico','Mexico',131,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1132,'COUNTRIES','Micronesia','Micronesia',132,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1133,'COUNTRIES','Moldova','Moldova',133,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1134,'COUNTRIES','Monaco','Monaco',134,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1135,'COUNTRIES','Mongolia','Mongolia',135,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1136,'COUNTRIES','Montenegro','Montenegro',136,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1137,'COUNTRIES','Montserrat','Montserrat',137,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1138,'COUNTRIES','Morocco','Morocco',138,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1139,'COUNTRIES','Mozambique','Mozambique',139,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1140,'COUNTRIES','Myanmar','Myanmar',140,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1141,'COUNTRIES','Namibia','Namibia',141,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1142,'COUNTRIES','Nauru','Nauru',142,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1143,'COUNTRIES','Nepal','Nepal',143,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1144,'COUNTRIES','Netherlands','Netherlands',144,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1145,'COUNTRIES','Netherlands Antilles','Netherlands Antilles',145,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1146,'COUNTRIES','New Caledonia','New Caledonia',146,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1147,'COUNTRIES','New Zealand','New Zealand',147,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1148,'COUNTRIES','Nicaragua','Nicaragua',148,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1149,'COUNTRIES','Niger','Niger',149,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1150,'COUNTRIES','Nigeria','Nigeria',150,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1151,'COUNTRIES','Niue','Niue',151,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1152,'COUNTRIES','Norfolk Island','Norfolk Island',152,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1153,'COUNTRIES','Northern Mariana Islands','Northern Mariana Islands',153,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1154,'COUNTRIES','Norway','Norway',154,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1155,'COUNTRIES','Oman','Oman',155,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1156,'COUNTRIES','Pakistan','Pakistan',156,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1157,'COUNTRIES','Palau','Palau',157,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1158,'COUNTRIES','Palestinian Territories','Palestinian Territories',158,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1159,'COUNTRIES','Panama','Panama',159,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1160,'COUNTRIES','Papua New Guinea','Papua New Guinea',160,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1161,'COUNTRIES','Paraguay','Paraguay',161,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1162,'COUNTRIES','Peru','Peru',162,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1163,'COUNTRIES','Philippines','Philippines',163,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1164,'COUNTRIES','Pitcairn','Pitcairn',164,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1165,'COUNTRIES','Poland','Poland',165,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1166,'COUNTRIES','Portugal','Portugal',166,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1167,'COUNTRIES','Puerto Rico','Puerto Rico',167,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1168,'COUNTRIES','Qatar','Qatar',168,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1169,'COUNTRIES','Runion','Runion',169,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1170,'COUNTRIES','Romania','Romania',170,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1171,'COUNTRIES','Russian Federation','Russian Federation',171,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1172,'COUNTRIES','Rwanda','Rwanda',172,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1173,'COUNTRIES','Saint Helena','Saint Helena',173,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1174,'COUNTRIES','Saint Kitts and Nevis','Saint Kitts and Nevis',174,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1175,'COUNTRIES','Saint Lucia','Saint Lucia',175,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1176,'COUNTRIES','Saint Pierre and Miquelon','Saint Pierre and Miquelon',176,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1177,'COUNTRIES','Saint Vincent and the Grenadines','Saint Vincent and the Grenadines',177,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1178,'COUNTRIES','Samoa','Samoa',178,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1179,'COUNTRIES','San Marino','San Marino',179,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1180,'COUNTRIES','Sao Tome and Principe','Sao Tome and Principe',180,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1181,'COUNTRIES','Saudi Arabia','Saudi Arabia',181,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1182,'COUNTRIES','Senegal','Senegal',182,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1183,'COUNTRIES','Serbia','Serbia',183,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1184,'COUNTRIES','Seychelles','Seychelles',184,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1185,'COUNTRIES','Sierra Leone','Sierra Leone',185,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1186,'COUNTRIES','Singapore','Singapore',186,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1187,'COUNTRIES','Slovakia','Slovakia',187,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1188,'COUNTRIES','Slovenia','Slovenia',188,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1189,'COUNTRIES','Solomon Islands','Solomon Islands',189,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1190,'COUNTRIES','Somalia','Somalia',190,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1191,'COUNTRIES','South Africa','South Africa',191,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1192,'COUNTRIES','South Georgia and the South Sandwich Islands','South Georgia and the South Sandwich Islands',192,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1193,'COUNTRIES','Spain','Spain',193,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1194,'COUNTRIES','Sri Lanka','Sri Lanka',194,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1195,'COUNTRIES','Suriname','Suriname',195,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1196,'COUNTRIES','Svalbard and Jan Mayen Islands','Svalbard and Jan Mayen Islands',196,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1197,'COUNTRIES','Swaziland','Swaziland',197,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1198,'COUNTRIES','Sweden','Sweden',198,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1199,'COUNTRIES','Switzerland','Switzerland',199,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1200,'COUNTRIES','Taiwan','Taiwan',200,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1201,'COUNTRIES','Tajikistan','Tajikistan',201,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1202,'COUNTRIES','Tanzania','Tanzania',202,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1203,'COUNTRIES','Thailand','Thailand',203,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1204,'COUNTRIES','Togo','Togo',204,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1205,'COUNTRIES','Tokelau','Tokelau',205,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1206,'COUNTRIES','Tonga','Tonga',206,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1207,'COUNTRIES','Trinidad and Tobago','Trinidad and Tobago',207,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1208,'COUNTRIES','Tunisia','Tunisia',208,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1209,'COUNTRIES','Turkey','Turkey',209,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1210,'COUNTRIES','Turkmenistan','Turkmenistan',210,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1211,'COUNTRIES','Turks and Caicos Islands','Turks and Caicos Islands',211,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1212,'COUNTRIES','Tuvalu','Tuvalu',212,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1213,'COUNTRIES','Uganda','Uganda',213,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1214,'COUNTRIES','Ukraine','Ukraine',214,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1215,'COUNTRIES','United Arab Emirates','United Arab Emirates',215,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1216,'COUNTRIES','United Kingdom','United Kingdom',216,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1217,'COUNTRIES','United States of America','United States of America',217,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1218,'COUNTRIES','Uruguay','Uruguay',218,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1219,'COUNTRIES','Uzbekistan','Uzbekistan',219,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1220,'COUNTRIES','Vanuatu','Vanuatu',220,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1221,'COUNTRIES','Vatican City','Vatican City',221,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1222,'COUNTRIES','Venezuela','Venezuela',222,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1223,'COUNTRIES','Vietnam','Vietnam',223,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1224,'COUNTRIES','Virgin Islands (British)','Virgin Islands (British)',224,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1225,'COUNTRIES','Virgin Islands (US)','Virgin Islands (US)',225,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1226,'COUNTRIES','Wallis and Futuna Islands','Wallis and Futuna Islands',226,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1227,'COUNTRIES','Western Sahara','Western Sahara',227,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1228,'COUNTRIES','Yemen','Yemen',228,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1229,'COUNTRIES','Zaire','Zaire',229,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1'),(1230,'COUNTRIES','Zambia','Zambia',230,'2012-12-05 00:00:00','SYSTEM','2012-12-05 00:00:00','SYSTEM','1');
/*!40000 ALTER TABLE `application_properties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking` (
  `row_id` int(11) NOT NULL AUTO_INCREMENT,
  `booked_datetime` datetime DEFAULT NULL,
  `duration` int(11) DEFAULT NULL,
  `start_datetime` datetime DEFAULT NULL,
  `finish_datetime` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `is_active` varchar(1) DEFAULT NULL,
  `client_id` int(10) unsigned NOT NULL,
  `status_desc` varchar(1000) DEFAULT NULL,
  `status_id` int(10) unsigned NOT NULL,
  `service_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`row_id`),
  KEY `fk_booking_user1_idx` (`client_id`),
  KEY `fk_booking_application_properties1_idx` (`status_id`),
  KEY `fk_booking_application_properties2_idx` (`service_id`),
  CONSTRAINT `fk_booking_user1` FOREIGN KEY (`client_id`) REFERENCES `user` (`row_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_booking_application_properties1` FOREIGN KEY (`status_id`) REFERENCES `application_properties` (`property_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_booking_application_properties2` FOREIGN KEY (`service_id`) REFERENCES `application_properties` (`property_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booking_assignment`
--

DROP TABLE IF EXISTS `booking_assignment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `booking_assignment` (
  `create_time` timestamp NULL DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT NULL,
  `is_active` varchar(1) DEFAULT NULL,
  `row_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `booking_id` int(11) NOT NULL,
  `vendor_id` int(10) unsigned NOT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`row_id`),
  KEY `fk_booking_assignment_booking1_idx` (`booking_id`),
  KEY `fk_booking_assignment_user1_idx` (`vendor_id`),
  CONSTRAINT `fk_booking_assignment_booking1` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`row_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_booking_assignment_user1` FOREIGN KEY (`vendor_id`) REFERENCES `user` (`row_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking_assignment`
--

LOCK TABLES `booking_assignment` WRITE;
/*!40000 ALTER TABLE `booking_assignment` DISABLE KEYS */;
/*!40000 ALTER TABLE `booking_assignment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact` (
  `row_id` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(45) DEFAULT NULL,
  `additional` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `is_active` varchar(1) DEFAULT NULL,
  `mobile_phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`row_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
INSERT INTO `contact` VALUES (1,'Street-1','Additional-1','City-1','State-1','Zip-1','Country-1','2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1','Mobile-1'),(2,'Street-2','Additional-2','City-2','State-2','Zip-2','Country-2','2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1','Mobile-2'),(3,'Street-3','Additional-3','City-3','State-3','Zip-3','Country-3','2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1','Mobile-3'),(4,'Street-4','Additional-4','City-4','State-4','Zip-4','Country-4','2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1','Mobile-4');
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_privilege`
--

DROP TABLE IF EXISTS `role_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_privilege` (
  `role_privilege_id` int(10) unsigned NOT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_active` varchar(1) DEFAULT NULL,
  `privilege_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`role_privilege_id`),
  KEY `fk_role_privilege_sys_privilege1_idx` (`privilege_id`),
  KEY `fk_role_privilege_system_role1_idx` (`role_id`),
  CONSTRAINT `fk_role_privilege_sys_privilege1` FOREIGN KEY (`privilege_id`) REFERENCES `sys_privilege` (`privilege_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_privilege_system_role1` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_privilege`
--

LOCK TABLES `role_privilege` WRITE;
/*!40000 ALTER TABLE `role_privilege` DISABLE KEYS */;
/*!40000 ALTER TABLE `role_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_tokens`
--

DROP TABLE IF EXISTS `security_tokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_tokens` (
  `row_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `is_active` varchar(1) DEFAULT NULL,
  `device_id` varchar(225) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`row_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_tokens`
--

LOCK TABLES `security_tokens` WRITE;
/*!40000 ALTER TABLE `security_tokens` DISABLE KEYS */;
/*!40000 ALTER TABLE `security_tokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `services` (
  `row_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `service_id` int(10) unsigned NOT NULL,
  `rate` decimal(10,2) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `is_active` varchar(1) DEFAULT NULL,
  `zipcode` int(11) DEFAULT NULL,
  PRIMARY KEY (`row_id`),
  KEY `fk_services_rates_application_properties1_idx` (`service_id`),
  CONSTRAINT `fk_services_rates_application_properties1` FOREIGN KEY (`service_id`) REFERENCES `application_properties` (`property_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (1,1,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75254),(2,2,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75254),(3,3,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75254),(4,4,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75254),(5,5,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75254),(6,1,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75240),(7,2,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75240),(8,3,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75240),(9,4,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75240),(10,5,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75240),(11,1,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75001),(12,2,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75001),(13,3,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75001),(14,4,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75001),(15,5,20.00,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',75001);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_privilege`
--

DROP TABLE IF EXISTS `sys_privilege`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_privilege` (
  `privilege_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_active` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`privilege_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_privilege`
--

LOCK TABLES `sys_privilege` WRITE;
/*!40000 ALTER TABLE `sys_privilege` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_privilege` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_role`
--

DROP TABLE IF EXISTS `system_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_role` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `is_active` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_role`
--

LOCK TABLES `system_role` WRITE;
/*!40000 ALTER TABLE `system_role` DISABLE KEYS */;
INSERT INTO `system_role` VALUES (1,'ADMIN_ROLE','Role for admins','2015-03-21 19:17:23','SYSTEM','2015-03-21 19:17:23','SYSTEM','1'),(2,'CLIENT_ROLE','Role for clients','2015-03-21 19:17:23','SYSTEM','2015-03-21 19:17:23','SYSTEM','1'),(3,'VENDOR_ROLE','Role for vendors','2015-03-21 19:17:23','SYSTEM','2015-03-21 19:17:23','SYSTEM','1');
/*!40000 ALTER TABLE `system_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `row_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(32) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `is_active` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`row_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `address_id_idx` (`address_id`),
  CONSTRAINT `address_id` FOREIGN KEY (`address_id`) REFERENCES `contact` (`row_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'leo_adnan@hotmail.com','Test123','Adnan','Ahmed',1,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1'),(2,'alphanzox@hotmail.com','Test123','Umair','Raza',2,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1'),(3,'client1@hotmail.com','Test123','Client','One',3,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1'),(4,'client2@hotmail.com','Test123','Client','Two',3,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1'),(5,'vendor1@hotmail.com','Test123','Vendor','One',4,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1'),(6,'vendor2@hotmail.com','Test123','Vendor','Two',4,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `is_active` varchar(1) DEFAULT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`user_role_id`),
  KEY `fk_user_role_user1_idx` (`user_id`),
  KEY `fk_user_role_system_role1_idx` (`role_id`),
  CONSTRAINT `fk_user_role_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`row_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_role_system_role1` FOREIGN KEY (`role_id`) REFERENCES `system_role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,'2015-03-21 19:17:23','SYSTEM','2015-03-21 19:17:23','SYSTEM','1',1,1),(2,'2015-03-21 19:17:23','SYSTEM','2015-03-21 19:17:23','SYSTEM','1',2,1),(3,'2015-03-21 19:17:23','SYSTEM','2015-03-21 19:17:23','SYSTEM','1',3,2),(4,'2015-03-21 19:17:23','SYSTEM','2015-03-21 19:17:23','SYSTEM','1',4,2),(5,'2015-03-21 19:17:23','SYSTEM','2015-03-21 19:17:23','SYSTEM','1',5,3),(6,'2015-03-21 19:17:23','SYSTEM','2015-03-21 19:17:23','SYSTEM','1',6,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor_skill`
--

DROP TABLE IF EXISTS `vendor_skill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vendor_skill` (
  `row_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `skill_id` int(10) unsigned NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_by` varchar(100) DEFAULT NULL,
  `update_by` varchar(100) DEFAULT NULL,
  `is_active` varchar(1) DEFAULT NULL,
  `rates` decimal(10,2) DEFAULT NULL,
  `vendor_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`row_id`),
  KEY `fk_vendor_skill_application_properties1_idx` (`skill_id`),
  KEY `fk_vendor_skill_user1_idx` (`vendor_id`),
  CONSTRAINT `fk_vendor_skill_application_properties1` FOREIGN KEY (`skill_id`) REFERENCES `application_properties` (`property_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_vendor_skill_user1` FOREIGN KEY (`vendor_id`) REFERENCES `user` (`row_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor_skill`
--

LOCK TABLES `vendor_skill` WRITE;
/*!40000 ALTER TABLE `vendor_skill` DISABLE KEYS */;
INSERT INTO `vendor_skill` VALUES (1,1,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',20.00,5),(2,2,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',20.00,5),(3,3,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',20.00,5),(4,4,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',20.00,5),(5,5,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',20.00,5),(6,1,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',20.00,6),(7,2,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',20.00,6),(8,3,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',20.00,6),(9,4,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',20.00,6),(10,5,'2015-03-21 19:17:23','2015-03-21 19:17:23','SYSTEM','SYSTEM','1',20.00,6);
/*!40000 ALTER TABLE `vendor_skill` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-03-21 19:18:21
