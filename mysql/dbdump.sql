CREATE DATABASE  IF NOT EXISTS `facultyresearch` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `facultyresearch`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: facultyresearch
-- ------------------------------------------------------
-- Server version	8.0.24

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
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `facultyID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `school` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `pwhash` varchar(500) DEFAULT NULL,
  `salt` varchar(500) DEFAULT NULL,
  `facultyAbstract` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`facultyID`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Steve','Smith','RIT','stevesmith@rit.edu','fvXX38CFm65cZSw5pLT/Sa+eQTsBLglxfJMYmipnOT2obSuqtCXdXiOsLswAVYJzpFYbXrxbkhwnimYZtp8=','t7npLksG7sJWhen1pVMc45fyZLZ/YGTUA6vgwwUdPmw=','Steve Smith is really into Java. He has written multiple user interfaces in Java. This includes sign up systems, database information viewers, and web applications. He now teaches multiple Java classes at RIT.'),(2,'George','Preston','RIT','georgepreston@rit.edu','fEmpqoBUTnPeKmOhnHP5bDzX4lIbxr997fAqjIoI+5rT9lnYI3wmIzUlx40rcIBaus+9irVuUrqhK5boxls=','Ug0doNQxEpCCeKW6MIlEoMyWHZwjFM9hKNPdqsBfa/A=','George Preston is a big supporter of object-oriented programming. Through his computer science classes at RIT, he teaches students the theory behing object-oriented programming and how it maps to real life problems.'),(3,'Karen','Brown','RIT','karenbrown@rit.edu','bVbUKw6A7+I9EHorDlkfFsEUBF7VlaLnLeXQKR88Vvta0VOCZOpWd9oerpbmsq7Ev/GWkvZfzTJhbaf4etQ=','BjSoXk6JHDqiCrkBhhGka754GIeOHu2VY0AMlT8o6Zo=','Karen Brown is a computing security professor at RIT. She has written multiple books on the subject and teaches her classes using the language know as Python. She also likes to teach her students about the ethics that come with gaining knowledge that allows the breaching of private data in computers.'),(4,'Terry','Murray','RIT','terrymurray@rit.edu','vihbxVu6/VciglttbsdPndRlUgAWuxk5oeYEruj5q2ZFNSMTtPImeoYUKotR8MOBGl28vta9PoUJPZyVl3g=','n+Bzxw4gwieBTNpwXSoYL2zFAp/yuFbSdQ0+2Zt3UVw=','Terry Murray has given multiple speeches about project management when it comes to creating applications. He stresses in all of his classes at RIT that through strict organizational skills and teamwork, the completion of a project is well under way.'),(5,'Greg','West','RIT','gregwest@rit.edu','EsM/yw56DK0sRkGZRukJzUGB413R8/6GAyor785dRHKEZl6cWhtnYoWDelZ4dCwCVIipzeoHH2+hhuNYOnQ=','x6LCySiTUnGebqKSSWuhSL1O8Uql0gQIVAcepibafxo=','Greg West is a mobile application development guru and professor at RIT. By teaching his students the basic rules of design, color, proximity, etc. when it comes to mobile design, he is then able to teach his students Swift code, allowing them to create good-looking user interfaces in mobile environments.'),(6,'Madison','Mann','RIT','madisonmann@rit.edu','Rc+W2vpf88WjHMtSkNK/RNhtcz9ZEalK1iQqm7BNquwkSoIhOaatofo9peW9Zo9jS6d0vHeQK3kWrIPxq8U=','F4RnjbtVgzsRkNOwiwvHtqARlcwClpFgij4MOfVi8S4=','Madison Mann is a former web designer at multiple big companies. Through her knowledge gained from her past experiences and general knowledge about web development she has gained over the years, she is able to teach her students how to create websites that will make users want to stick around.'),(7,'Brady','Fleming','RIT','bradyfleming@rit.edu','Dwu6/UNFEMWJVTXZd7unlpTxXVdzsMrQvjDjk42wJJfiDFQo0FOXTxhwNcPzz49uIaio86mXzoMpGqU8G1U=','B6WxDKPfO5jq0VJUVoGkr9hFglmal3/sgW99fsBE9so=','Brady Fleming loves to teach the hard subject of assembly language. With a large amount of experience with early game design for early handheld systems, Brady teaches his students how to write in languages very close to their machine\'s hardware.'),(8,'Osman','Welch','RIT','osmanwelch@rit.edu','t7GgQ4XyYq684eGuKCsJT4R3EYH0q0VutH94vma7Ky+65DdwaZGkvXLLSx7HLVCAOCKxhQCGNA+buRoRXz4=','q4ZnirA5SaGkbHpVwouWSBbxxR6v7Bq4FT44lldjbhk=','Osman Welch has written multiple books on JavaScript. He has always been good at object-oriented programming, so being able to write code for the web and combine his object-oriented skills is a plus. He stresses the importance of JavaScript when it comes to web development to his students at RIT and makes sure they are well-versed in the sometimes difficult language.'),(9,'Cassidy','Ferry','RIT','cassidyferry@rit.edu','uJHLBLxfjyTr7D4rKjJBs/Q8+IB8LOFJepXgroWLk2u+bArqNz6x6pHaYVO1hUzGM1M0Peo9PHjNyfiCFqQ=','cNmfS7aZSc4cOtNp3BTDbCtS/+ooCgjVU4f8y7wttZg=','Cassidy Ferry has researched and developed with functional programming since a young age. She went to a very prestigeous school with a major in computer science and has since become a professor at RIT. Through her career, she is able to spread her knowledge about the importance of functional programming skills to her students.'),(10,'Heather','Brewer','RIT','heatherbrewer@rit.edu','t4tJn1d4LJMSD1e5/tfodLA14bTl5gDaLuI9I0L2HMiFD+J4MQPm7Jv2KS4ifMe8yX8GPcThkcyJ2CdhIig=','Ertm9JvQU4NVRBVNJuFyvJitfUASmypLGW6nalGWJzw=','Heather Brewer is a very skilled level designer when it comes to video games. She has a YouTube channel in which she uploads tutorials on the subject and has even gotten a job as a professor at RIT to teach students the basics of level design in video games. Although it can be a very tedious and tiresome job, she stresses the importance of level designers in the video game design market.'),(11,'Danny','Forest','RIT','dannyforest@rit.edu','igeoxRbgBVlUsATik9rpfbXdzEDY35W+BNxuafOfAbKBA0mPfQcd6jEluKhJCRdVNsOTS9i9YDUFskmd538=','tsay+IZiDvlW6dOj46yR/0Wkejb7asA9gDEpN+yCko8=','Danny forest has written one top selling book on C++ and has been a professor at RIT for ten years. Through his knowledge on C++, he was able to pick up scripting skills for video games. Now teaching a game design scripting course at RIT, he teaches the importance of the technical side of video games and the work that goes into it.'),(12,'Juniper','Sims','RIT','junipersims@rit.edu','Q2iZqbIrzpy4rFy9nETunbVmc2qkzrkNHSHbhDmumLLE4LMHsz0au/pC/Qwm7dOHw5ty2JM9nDbkQnHjU+Y=','BkWV2VpXT865JcRPq/KW9SNnok0V8bPW7HdG1Pn+5KY=','Juniper Sims has been researching software engineering topics for decades. She has brought forth her experience and decided to become a professor at RIT to teach exactly that. Through her expertise, she is able to influence her students to design and develop applications for end users.');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facultykeywords`
--

DROP TABLE IF EXISTS `facultykeywords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facultykeywords` (
  `facultyID` int DEFAULT NULL,
  `keywords` varchar(50) DEFAULT NULL,
  KEY `facultyKeywords_faculty_fk` (`facultyID`),
  CONSTRAINT `facultyKeywords_faculty_fk` FOREIGN KEY (`facultyID`) REFERENCES `faculty` (`facultyID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facultykeywords`
--

LOCK TABLES `facultykeywords` WRITE;
/*!40000 ALTER TABLE `facultykeywords` DISABLE KEYS */;
INSERT INTO `facultykeywords` VALUES (1,'Java'),(1,'Systems'),(1,'Database'),(1,'Information'),(1,'Web'),(1,'Application'),(1,'RIT'),(1,'Interface'),(1,'Web application'),(2,'Object'),(2,'Object-oriented'),(2,'Programming'),(2,'Computer science'),(2,'Computer'),(2,'RIT'),(2,'Theory'),(3,'Computing'),(3,'Security'),(3,'Professor'),(3,'Computing security'),(3,'RIT'),(3,'Book'),(3,'Books'),(3,'Language'),(3,'Python'),(3,'Data'),(3,'Computer'),(4,'Speech'),(4,'Project'),(4,'Application'),(4,'Applications'),(4,'RIT'),(4,'Project'),(5,'Mobile'),(5,'Application'),(5,'Develop'),(5,'Professor'),(5,'RIT'),(5,'Design'),(5,'Swift'),(5,'Code'),(5,'Create'),(5,'Interface'),(6,'Web'),(6,'Design'),(6,'Web design'),(6,'Knowledge'),(6,'Develop'),(6,'Create'),(6,'Website'),(6,'User'),(7,'Hard'),(7,'Assembly'),(7,'Language'),(7,'Experience'),(7,'Game'),(7,'Design'),(7,'System'),(7,'Write'),(7,'Machine'),(7,'Hardware'),(8,'Book'),(8,'JavaScript'),(8,'Object'),(8,'Object-oriented'),(8,'Write'),(8,'Code'),(8,'Web'),(8,'Skill'),(8,'Develop'),(8,'RIT'),(8,'Language'),(9,'Research'),(9,'Develop'),(9,'Function'),(9,'Functional'),(9,'Functional programming'),(9,'Programming'),(9,'School'),(9,'Major'),(9,'Computer'),(9,'Computer science'),(9,'Professor'),(9,'RIT'),(9,'Career'),(9,'Knowledge'),(9,'Skill'),(10,'Skill'),(10,'Level'),(10,'Design'),(10,'Video game'),(10,'Game'),(10,'Tutorial'),(10,'Professor'),(10,'RIT'),(11,'Book'),(11,'C++'),(11,'Professor'),(11,'RIT'),(11,'Knowledge'),(11,'Script'),(11,'Skill'),(11,'Game'),(11,'Video game'),(11,'Design'),(12,'Research'),(12,'Software'),(12,'Engineer'),(12,'Software engineering'),(12,'Experience'),(12,'Professor'),(12,'RIT'),(12,'Design'),(12,'Develop'),(12,'Application');
/*!40000 ALTER TABLE `facultykeywords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `skills` (
  `skillID` int NOT NULL,
  `skill` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`skillID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
INSERT INTO `skills` VALUES (1,'C++'),(2,'C'),(3,'C#'),(4,'Objective-C'),(5,'Java'),(6,'Python'),(7,'Perl'),(8,'Go'),(9,'HTML'),(10,'CSS'),(11,'JavaScript'),(12,'MySQL'),(13,'PHP'),(14,'Swift');
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `studentID` int NOT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `school` varchar(100) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `pwhash` varchar(500) DEFAULT NULL,
  `salt` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`studentID`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'Steve','Roberts','RIT','steveroberts@rit.edu','k7ykP+csWopHxokdiY5rDcxvhYrclFYh4MpNW/HEXuWJhSvxCgHZ0B2x7RSdyCAO/06Inyy7v9Cgjz0lfwk=','iuac20OTClEJeibNBhMNrOfhTTVtqJTHVc812UcQ3SI='),(2,'Mason','Large','RIT','masonlarge@rit.edu','13jDTlQrH5QMo9QpmYO5H4Dq6fxn7hav+mxRpKkOkeNVe6LlB3OgG2VV2oqMz37hUPNGXYwLbgm89FPS05k=','9V0JIAQ8LX/hjTi7pEBrrNKgGRK6DNrhtVW9j+Osrvw='),(3,'Henry','Green','RIT','henrygreen@rit.edu','7ByzbBdvcKA+JIKSEOsIG4rZ4D5u/en1qwMpD9DyNguHDuLcKnMUximrOFCsQZ1YlpLzzDONjSAXQ0kmswE=','tjDKieOsYeMn15H+6N/LU5KlQ4UAmkIcfcxNO4WlprI='),(4,'Chris','Blue','RIT','chrisblue@rit.edu','bxVGCRdkMHnb0o4878sx2lS6Z9KxINsvCCUrx6YJ94oTImbyDRpCkh4xh5qLXZIO012KAk/8Rwp5hPsF4MU=','bZTo68XMCdeAUaS4ZR9Xyj8nCEzwWnRbntPCBU6gWJU='),(5,'Sarah','Jones','RIT','sarahjones@rit.edu','XyhyWM9jUJZ8hAbBY+p65FPctRCuDEaQCmntt0w9ep/9ad4/5/ClxY6ZYj/KeFWtCFmcs33Dqoav3dzlurw=','WAQrk0tsUjI58rNdVYAIEz2RO4YrwZHsm5QVhZzHLFQ='),(6,'Conor','Keegan','RIT','conorkeegan@rit.edu','A21B4QSnWmaz/Z94NdofaNUxUOTevF/Wa5a4rcDUpjhQrkrjoc0lAFwak7+A36AUorGRSonFucMNy0eVgvE=','jjfuVdOC2bZzsm+8FCuvag/HxVcZLW3Ni0UXFAOBkR4='),(7,'Sam','Romero','RIT','samromero@rit.edu','gHGY63KIhMS2xokiETgRPgzwB/BPakzFnxCq9RJ2AEIoX9jXiSRpwjm4epPybyQywtRdICS8iXXHp456kpI=','5iuUAXOdh9IEBAX1cRkhpUVh/ws9JpG0sVeEenbMxNw='),(8,'Camran','Bridge','RIT','camranbridge@rit.edu','au95sFqdrmY+4FFnh7EytUDXbQR/6QOfebbG0ut7zbjxoUqTPEHUb9a2iW0HYQge8K3I6Ztzw0Ge9xNYxCw=','NdhL+8/yDzo+H5AllXfaXYlb+EMrzfevDXf9dLkRjbU='),(9,'Clarise','Turner','RIT','clariseturner@rit.edu','ToTKA9CyQUDlX8BxoLeNjItypCQASPGt5KQF2vYap3A9BqEN86slZIumylyDzEDX5aRXrrOoC/XOCuRCc5M=','gWXXQmG7+lmo42mASyOubhIjCJ2YqJrwK6eWcnRdRBI='),(10,'Ronald','Williams','RIT','ronaldwilliams@rit.edu','jot9W4nnGmlFxvE1fRW1m25o8/Fc77uPpDoG17CaUv5ClflD1t9D4/7GqAquXalnHbq9c29CbSE+/srSFr4=','euHYzsr3JzksssDBHqoO9qp+flB7kvajbm6eaSgD11Q='),(11,'Bailey','Anthony','RIT','baileyanthony@rit.edu','fQBdvNbtBDYmS93sV3EI9vO+0wg1M3MetjNuKrjEW/roE6IKUDu8C9jI+JM0qJVJEsvwPF0iKjkpEwZ+qZM=','nUDv2BJNj8gos2zX3DPnT8V5GTjCHIps1YQ2KEh2hzE='),(12,'Courtney','Tanner','RIT','courtneytanner@rit.edu','PhwNRuIp09pFuTDWiWenvIoJtmTwu+9vSGMsVgxtMoH1fDTRrlaP9OKzZPB5JhfWfgXoPi3MjeKC2ffYdjo=','g9tz3uAw6MoO3rkeTDevqY8jJ6vjKwm1Ws6h9sHJlug='),(13,'Claude','Shelton','RIT','claudeshelton@rit.edu','MOnQ67TvH9jQZi1991UBsLnQyqzDRXxJ4+n/DJzsCFrxXQ06urecrQIyEapTjR5bOLEZdIfzTabCtEOKyqs=','H1eC0CpRnNftHrRYtOVVFsSKL0oQShQrX7Li/gGmpqI='),(14,'Hudson','Dudley','RIT','hudsondudley@rit.edu','20xiCs+Y4Z5hCngtkeoksvooyu5MeLmGBFEcUG4+o/S6VBpZYB2LAzgQB4Nx+Kp/YCdkapRm8Ce8CisJdsQ=','J55bhtFqDqY+rDeV+iXukSRUZ3psNy95lMOsBTd9LcI='),(15,'Tyler','Gibson','RIT','tylergibson@rit.edu','jOAdMcbp9sq6bDLjQo4sQWt2F+pFge++PvPCC1E3tjqJil9oxdtHT/jshU7FWRPvYJJYMLfqAtEWsNyqKHE=','6EXgDVUc2wtPPAjUVxTZV6SJzDM0JdFoIr44G+o4eME='),(16,'Savannah','Montes','RIT','savannahmontes@rit.edu','jzlKoPTmP+PYc5zJmitRasJeW+PYknAYsclcRyINvtfmT0hwAOHgUJJTojQdOApU/ehw8cNqnNJA0wUpv0g=','yOryFYSwPs+GUk7mQayEEeGkFu1huerwdLo5YZ4YSeY='),(17,'Abbey','Henderson','RIT','abbeyhenderson@rit.edu','7b3l1EqN0I4V1y1pVdpwvNU4BwQYfLe+AGp3vie1OHDBcH2kV44xrgOKXi7Z/fGXOmJnpR1HfpxwUT1oY1k=','YBoFzSa2DWP5qTJuzECuBAaRpmT4tnhaIJ2JXNkTCAs='),(18,'Hetty','Alexander','RIT','hettyalexander@rit.edu','rhFMr6890w8QH1aJrgQXvynElmmDm42nac05KeGO2TawlLicw6hr1lTqTQ4J4RIo/BCxfApmzB3Np7zr6u4=','YaOiqLV8S0h8cEzA6BAKWP9g7PVxgw2xNXppHDD5ihQ=');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentskill`
--

DROP TABLE IF EXISTS `studentskill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentskill` (
  `studentID` int NOT NULL,
  `skillID` int NOT NULL,
  PRIMARY KEY (`studentID`,`skillID`),
  KEY `studentSkill_skill_fk` (`skillID`),
  CONSTRAINT `studentSkill_skill_fk` FOREIGN KEY (`skillID`) REFERENCES `skills` (`skillID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `studentSkill_student_fk` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentskill`
--

LOCK TABLES `studentskill` WRITE;
/*!40000 ALTER TABLE `studentskill` DISABLE KEYS */;
INSERT INTO `studentskill` VALUES (1,1),(5,1),(6,1),(7,1),(14,1),(17,1),(6,2),(9,2),(12,2),(13,2),(14,2),(1,3),(6,3),(7,3),(14,3),(1,4),(12,4),(5,5),(7,5),(8,5),(12,5),(14,5),(4,6),(8,6),(9,6),(13,6),(4,7),(10,7),(11,7),(15,7),(16,7),(4,8),(10,8),(14,8),(15,8),(16,8),(2,9),(7,9),(11,9),(2,10),(11,10),(2,11),(3,11),(9,11),(3,12),(4,12),(8,12),(9,12),(13,12),(15,12),(18,12),(3,13),(13,13),(15,13),(18,13),(3,14),(7,14),(12,14),(18,14);
/*!40000 ALTER TABLE `studentskill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'facultyresearch'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-28 23:29:23
