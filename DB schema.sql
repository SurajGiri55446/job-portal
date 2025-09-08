-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: job_portal
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `application` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `applied_time` time(6) DEFAULT NULL,
  `cover_letter` varchar(1000) DEFAULT NULL,
  `resume_path` varchar(255) DEFAULT NULL,
  `status` enum('APPLIED','REJECTED','SHORTLISTED','UNDER_REVIEW') DEFAULT NULL,
  `job_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKq55fxm9py10j6a4w5e8ohhi1d` (`job_id`,`user_id`),
  KEY `FKawte0mbtubellxed1dvpoxhdj` (`user_id`),
  CONSTRAINT `FKawte0mbtubellxed1dvpoxhdj` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKls6sryk64ga8o5t4bym8qu3vm` FOREIGN KEY (`job_id`) REFERENCES `job` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1,'20:54:21.855000','Dear Hiring Manager,\r\n\r\nI am writing to express my interest in the job opportunity at your esteemed organization. With a strong passion for learning, problem-solving, and contributing to impactful projects, I believe I would be a valuable addition to your team.\r\n\r\nI have recently been working on a Job Portal System project using technologies such as Java, Spring Boot, MySQL, and Thymeleaf, which has helped me understand full-stack web development, database design, and user role management. I am confident that my technical skills and willingness to learn will allow me to contribute effectively to your projects.\r\n\r\nI am particularly drawn to your company because of its focus on innovation and quality. I would welcome the opportunity to work in a dynamic environment where I can continue to grow and take on new challenges.\r\n\r\nThank you for considering my application. I look forward to the opportunity to discuss how I can contribute to your team.\r\n\r\nSincerely,  \r\nABC','uploads\\resumes\\1757085861864_tcs_nqt_verbal_ability_sample_questions_set_2.pdf','SHORTLISTED',2,1),(2,'20:54:56.522000','Dear Hiring Manager,\r\n\r\nI am writing to express my interest in the job opportunity at your esteemed organization. With a strong passion for learning, problem-solving, and contributing to impactful projects, I believe I would be a valuable addition to your team.\r\n\r\nI have recently been working on a Job Portal System project using technologies such as Java, Spring Boot, MySQL, and Thymeleaf, which has helped me understand full-stack web development, database design, and user role management. I am confident that my technical skills and willingness to learn will allow me to contribute effectively to your projects.\r\n\r\nI am particularly drawn to your company because of its focus on innovation and quality. I would welcome the opportunity to work in a dynamic environment where I can continue to grow and take on new challenges.\r\n\r\nThank you for considering my application. I look forward to the opportunity to discuss how I can contribute to your team.\r\n\r\n','uploads\\resumes\\1757085896522_tcs_nqt_verbal_ability_sample_questions_set_2.pdf','UNDER_REVIEW',3,1);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education`
--

DROP TABLE IF EXISTS `education`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `education` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `degree` varchar(255) DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc8qg4a1sd3texfadb2d7eyhgx` (`user_id`),
  CONSTRAINT `FKc8qg4a1sd3texfadb2d7eyhgx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education`
--

LOCK TABLES `education` WRITE;
/*!40000 ALTER TABLE `education` DISABLE KEYS */;
INSERT INTO `education` VALUES (1,NULL,NULL,NULL,1),(2,'B.Tech','ABC','2022-2026',1),(3,NULL,NULL,NULL,2),(4,'BBA','ABC','2022-2025',2),(5,'MBA','ABC','2025-2027',2);
/*!40000 ALTER TABLE `education` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `company` varchar(255) NOT NULL,
  `description` varchar(4000) NOT NULL,
  `location` varchar(255) DEFAULT NULL,
  `posted_date` datetime(6) DEFAULT NULL,
  `salary` double NOT NULL,
  `title` varchar(255) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpjwg3kcmu25r91o9x9nm6ha56` (`user_id`),
  CONSTRAINT `FKpjwg3kcmu25r91o9x9nm6ha56` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'IBM','Job description\r\nPassionate about technology and emerging technology trends. Manage an Applications Development team of professionals to accomplish established goals and conduct personnel duties for team (e.g. performance evaluations, training and development, hiring and disciplinary actions)\r\nQualification : B.E (CS, IT and EC) , BCA , MCA , BSC( with Computer Science), M.TEC (CS, IT and EC) ( with Computer Science)\r\n\r\nWe are looking for Experienced people from Java / Core Java / J2EE Development / Customization area.\r\nJob Responsibilities:\r\nGood knowledge/experience in Java and J2EE background.\r\nExperience with Spring Boot must.\r\nGood knowledge/experience in Multi-Threading, Exception Management and Use of Collections.\r\nGood knowledge of working with application aspects i.e. Caching, Asynchronous APIs, Logging etc.\r\nExperience with user interface design, database structures, and statistical analyses.\r\nAnalytical mindset and good problem-solving skills.\r\nExcellent written and verbal communication.\r\nGood organizational skills.\r\nAbility to work as part of a team.\r\nAttention to detail.\r\nSkills required\r\nCore Java\r\nSpring\r\nREST API development\r\nData Structures\r\nMySQL\r\nRole: Software Development - Other\r\nIndustry Type: IT Services & Consulting\r\nDepartment: Engineering - Software & QA\r\nEmployment Type: Full Time, Permanent\r\nRole Category: Software Development','Pune','2025-09-05 20:40:06.374163',100000,'Java Developer',2),(2,' Infosys Limited','Responsibilities :\r\nCollaborate with sales teams to identify customer requirements and pain points through active listening and insightful questioning.\r\nDesign and present compelling network solutions that address customer challenges and business objectives, utilizing clear and concise communication.\r\nDevelop and deliver impactful presentations and demonstrations to showcase our network solutions, leveraging your presentation skills.\r\nWork closely with internal teams (engineering, marketing) to develop proposal responses/solution documents.\r\nStay up to date on the latest network technologies and industry trends through continuous learning.\r\nParticipate in industry events and conferences to build relationships and generate new leads, utilizing your strong interpersonal skills and client relationship building abilities.','BANGALORE','2025-09-05 20:43:13.105674',20000,'Client Solution Manager- RCLSSP',2),(3,'TCS','Job description\r\nPassionate about technology and emerging technology trends. Manage an Applications Development team of professionals to accomplish established goals and conduct personnel duties for team (e.g. performance evaluations, training and development, hiring and disciplinary actions)\r\nQualification : B.E (CS, IT and EC) , BCA , MCA , BSC( with Computer Science), M.TEC (CS, IT and EC) ( with Computer Science)\r\n\r\nWe are looking for Experienced people from Java / Core Java / J2EE Development / Customization area.\r\nJob Responsibilities:\r\nGood knowledge/experience in Java and J2EE background.\r\nExperience with Spring Boot must.\r\nGood knowledge/experience in Multi-Threading, Exception Management and Use of Collections.\r\nGood knowledge of working with application aspects i.e. Caching, Asynchronous APIs, Logging etc.\r\nExperience with user interface design, database structures, and statistical analyses.\r\nAnalytical mindset and good problem-solving skills.\r\nExcellent written and verbal communication.\r\nGood organizational skills.\r\nAbility to work as part of a team.\r\nAttention to detail.\r\nSkills required\r\nCore Java\r\nSpring\r\nREST API development\r\nData Structures\r\nMySQL','Remote','2025-09-05 20:48:27.469497',300000,'Java Developer',2),(4,'Shashwath Solution','MANDATORY SKILLS : Python, AWS\r\n\r\nPREFERRED SKILLS\r\nGood to have knowledge of ADAS Automative\r\n\r\nShould have hands-on AWS experience or Migration with AWS Knowledge and cloud-based applications and pipelines. Python, AWS, DynamoDB, Opensearch Terraform (Good to have), ReactJS, Javascript, Typescript, HTML, CSS, Agile, Git, CI CD, Docker, Linux (Good to have), Good to have knowledge of ADAS Automative Good to have AWS certification\r\nRole: Search Engineer\r\nIndustry Type: IT Services & Consulting\r\nDepartment: Engineering - Software & QA\r\nEmployment Type: Full Time, Permanent\r\nRole Category: Software Development\r\nEducation\r\nUG: Any Graduate\r\nPG: Any Postgraduate\r\nDoctorate: Doctorate Not Required','Bengaluru','2025-09-05 20:51:25.557078',4545234,' Python AWS Full Stack developer',2);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_portfolio_links`
--

DROP TABLE IF EXISTS `user_portfolio_links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_portfolio_links` (
  `user_id` bigint NOT NULL,
  `portfolio_link` varchar(255) DEFAULT NULL,
  KEY `FK2pu5t7oeywifw0j4d2fvoaq6t` (`user_id`),
  CONSTRAINT `FK2pu5t7oeywifw0j4d2fvoaq6t` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_portfolio_links`
--

LOCK TABLES `user_portfolio_links` WRITE;
/*!40000 ALTER TABLE `user_portfolio_links` DISABLE KEYS */;
INSERT INTO `user_portfolio_links` VALUES (1,'https://start.spring.io/index.html'),(2,'https://en.wikipedia.org/wiki/Java_(programming_language)');
/*!40000 ALTER TABLE `user_portfolio_links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bio` varchar(2000) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `join_date` date DEFAULT NULL,
  `notify_application_updates` bit(1) NOT NULL,
  `notify_job_recommendations` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `profile_public` bit(1) NOT NULL,
  `resume_path` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','APPLICANT','EMPLOYER') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'hello i am applicant','applicant@123gmail.com','application A','2025-09-05',_binary '',_binary '','$2a$10$b4Q.FzD7SHzGZ7oMlZtdRuQK8aubvlvlub9AXqD59b9BER1oxvDh6','1234567890',_binary '',NULL,'APPLICANT'),(2,'hello i am employer','employer@123gmail.com','employer A','2025-09-05',_binary '\0',_binary '\0','$2a$10$VBJasDwwrsGTZjypfz30wuMPYe0E4QnL0tHFycKFuOtrNiD5jQtNy','01234567890',_binary '\0',NULL,'EMPLOYER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_experience`
--

DROP TABLE IF EXISTS `work_experience`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_experience` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `company` varchar(255) DEFAULT NULL,
  `duration` varchar(255) DEFAULT NULL,
  `job_title` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm7a8aj1sa7ec50xjqfbmp6jka` (`user_id`),
  CONSTRAINT `FKm7a8aj1sa7ec50xjqfbmp6jka` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_experience`
--

LOCK TABLES `work_experience` WRITE;
/*!40000 ALTER TABLE `work_experience` DISABLE KEYS */;
INSERT INTO `work_experience` VALUES (1,NULL,NULL,NULL,1),(2,'','','',1),(3,NULL,NULL,NULL,2),(4,'TCS','2027-persent','Manager',2);
/*!40000 ALTER TABLE `work_experience` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-09-05 21:00:27
