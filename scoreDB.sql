/*
 Navicat Premium Data Transfer

 Source Server         : alibaba
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : 59.110.125.219:3306
 Source Schema         : scoreDB

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 25/07/2020 16:30:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `exam_method` varchar(255) DEFAULT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `course_number` varchar(255) DEFAULT NULL,
  `total_credits` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  `institute_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbnefdeo2rp4e3aflyp2rsw1nb` (`category_id`),
  KEY `FKbyyciv7mbmmkp7e6t9db0m9w4` (`institute_id`),
  CONSTRAINT `FKbnefdeo2rp4e3aflyp2rsw1nb` FOREIGN KEY (`category_id`) REFERENCES `course_category` (`id`),
  CONSTRAINT `FKbyyciv7mbmmkp7e6t9db0m9w4` FOREIGN KEY (`institute_id`) REFERENCES `institute` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
BEGIN;
INSERT INTO `course` VALUES (1, '2020-07-08 21-14-20', '2020-07-08 22-28-38', '闭卷', 'Java', '10001', '3.6', 1, 1);
INSERT INTO `course` VALUES (3, '2020-07-08 21-45-47', '2020-07-08 21-45-47', '闭卷', 'C++', '10002', '3', 1, 1);
INSERT INTO `course` VALUES (4, '2020-07-08 21-46-38', '2020-07-08 21-46-38', '闭卷', '数据库', '10003', '3', 1, 1);
INSERT INTO `course` VALUES (5, '2020-07-08 21-47-14', '2020-07-08 21-47-14', '大作业', 'Python', '10004', '3', 1, 1);
INSERT INTO `course` VALUES (6, '2020-07-08 21-48-57', '2020-07-08 21-48-57', '上机', '算法程序与设计', '10005', '3.5', 1, 1);
INSERT INTO `course` VALUES (7, '2020-07-08 21-49-32', '2020-07-08 21-49-32', '闭卷', '编译原理', '10006', '3', 1, 1);
INSERT INTO `course` VALUES (8, '2020-07-08 21-50-13', '2020-07-08 21-50-13', '闭卷', '计算机网络', '10007', '3.5', 1, 1);
INSERT INTO `course` VALUES (9, '2020-07-08 21-50-41', '2020-07-08 21-50-41', '闭卷', '软件工程', '10008', '3', 1, 1);
INSERT INTO `course` VALUES (10, '2020-07-08 21-51-42', '2020-07-08 21-51-42', '三步上篮', '篮球', '10009', '1.5', 2, 5);
INSERT INTO `course` VALUES (11, '2020-07-08 21-52-34', '2020-07-08 21-52-34', '闭卷', '英语读写', '10010', '3', 1, 6);
INSERT INTO `course` VALUES (12, '2020-07-08 21-53-10', '2020-07-08 21-53-10', '闭卷', '英语听说', '10010', '3', 1, 6);
COMMIT;

-- ----------------------------
-- Table structure for course_category
-- ----------------------------
DROP TABLE IF EXISTS `course_category`;
CREATE TABLE `course_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `course_category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_category
-- ----------------------------
BEGIN;
INSERT INTO `course_category` VALUES (1, '2020-07-08 21-12-11', '2020-07-08 21-12-11', '学科基础课程');
INSERT INTO `course_category` VALUES (2, '2020-07-08 21-31-03', '2020-07-08 21-31-03', '通识教育课程');
COMMIT;

-- ----------------------------
-- Table structure for course_mark
-- ----------------------------
DROP TABLE IF EXISTS `course_mark`;
CREATE TABLE `course_mark` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `course_mark_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_mark
-- ----------------------------
BEGIN;
INSERT INTO `course_mark` VALUES (1, '2020-07-08 21-12-18', '2020-07-08 21-12-18', '主修');
INSERT INTO `course_mark` VALUES (2, '2020-07-08 21-31-07', '2020-07-08 21-31-07', '辅修');
COMMIT;

-- ----------------------------
-- Table structure for course_nature
-- ----------------------------
DROP TABLE IF EXISTS `course_nature`;
CREATE TABLE `course_nature` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `nature_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_nature
-- ----------------------------
BEGIN;
INSERT INTO `course_nature` VALUES (1, '2020-07-08 21-11-56', '2020-07-08 21-11-56', '必修');
INSERT INTO `course_nature` VALUES (2, '2020-07-08 21-29-48', '2020-07-08 21-29-48', '选修');
INSERT INTO `course_nature` VALUES (3, '2020-07-08 21-30-02', '2020-07-08 21-30-02', '实践环节');
COMMIT;

-- ----------------------------
-- Table structure for course_token
-- ----------------------------
DROP TABLE IF EXISTS `course_token`;
CREATE TABLE `course_token` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `credits` varchar(255) DEFAULT NULL,
  `grade_point` varchar(255) DEFAULT NULL,
  `invalid` varchar(255) DEFAULT NULL,
  `school_year` varchar(255) DEFAULT NULL,
  `score` varchar(255) DEFAULT NULL,
  `semester` varchar(255) DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `course_mark_id` int DEFAULT NULL,
  `course_nature_id` int DEFAULT NULL,
  `score_nature_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdnks6jarifto8fwq55posm3gk` (`course_id`),
  KEY `FKjvnjylgirvf431g8m18d6bcah` (`course_mark_id`),
  KEY `FKijy0apwhckxe0miigjhlr1qtl` (`course_nature_id`),
  KEY `FKf6x87tuveitswa1fs3awdvnmg` (`score_nature_id`),
  KEY `FKb5w4m6ddwcrc22dfrnndgq3sp` (`student_id`),
  CONSTRAINT `FKb5w4m6ddwcrc22dfrnndgq3sp` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `FKdnks6jarifto8fwq55posm3gk` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `FKf6x87tuveitswa1fs3awdvnmg` FOREIGN KEY (`score_nature_id`) REFERENCES `score_nature` (`id`),
  CONSTRAINT `FKijy0apwhckxe0miigjhlr1qtl` FOREIGN KEY (`course_nature_id`) REFERENCES `course_nature` (`id`),
  CONSTRAINT `FKjvnjylgirvf431g8m18d6bcah` FOREIGN KEY (`course_mark_id`) REFERENCES `course_mark` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course_token
-- ----------------------------
BEGIN;
INSERT INTO `course_token` VALUES (2, '2020-07-08 22-13-45', '2020-07-08 22-13-45', '3.5', '3.03', '否', '1', '86.7', '1', 1, 1, 1, 1, 5);
INSERT INTO `course_token` VALUES (3, '2020-07-08 22-13-52', '2020-07-08 22-13-52', '3', '2.60', '否', '1', '86.7', '1', 3, 1, 1, 1, 5);
INSERT INTO `course_token` VALUES (4, '2020-07-08 22-14-04', '2020-07-08 22-14-04', '3', '2.46', '否', '1', '82.1', '1', 4, 1, 1, 1, 5);
INSERT INTO `course_token` VALUES (7, '2020-07-08 22-15-10', '2020-07-08 22-15-10', '3', '2.69', '否', '1', '89.7', '1', 5, 1, 1, 1, 5);
INSERT INTO `course_token` VALUES (12, '2020-07-08 22-16-32', '2020-07-08 22-16-32', '0', '0', '否', '1', '11', '1', 9, 1, 1, 3, 10);
INSERT INTO `course_token` VALUES (13, '2020-07-08 22-16-48', '2020-07-08 22-16-48', '3', '1.96', '否', '1', '65.2', '1', 9, 1, 1, 3, 11);
INSERT INTO `course_token` VALUES (14, '2020-07-08 22-17-44', '2020-07-08 22-17-44', '1.5', '1.46', '否', '1', '97.1', '1', 10, 1, 2, 1, 9);
INSERT INTO `course_token` VALUES (15, '2020-07-08 22-18-02', '2020-07-08 22-18-02', '3', '2.74', '否', '1', '91.4', '1', 11, 1, 2, 1, 9);
INSERT INTO `course_token` VALUES (16, '2020-07-08 22-18-10', '2020-07-08 22-18-10', '3', '3.00', '否', '1', '100', '1', 12, 1, 2, 1, 9);
INSERT INTO `course_token` VALUES (17, '2020-07-08 22-18-17', '2020-07-08 22-18-17', '3.5', '3.22', '否', '1', '91.9', '1', 8, 1, 2, 1, 12);
INSERT INTO `course_token` VALUES (18, '2020-07-08 22-18-26', '2020-07-08 22-18-26', '3', '2.50', '否', '1', '83.3', '1', 7, 1, 2, 1, 12);
INSERT INTO `course_token` VALUES (19, '2020-07-08 22-18-43', '2020-07-08 22-18-43', '3', '2.99', '否', '1', '99.5', '1', 9, 1, 2, 1, 12);
INSERT INTO `course_token` VALUES (20, '2020-07-08 22-29-48', '2020-07-08 22-29-48', '3.5', '2.54', '否', '1', '72.7', '1', 6, 1, 2, 1, 6);
COMMIT;

-- ----------------------------
-- Table structure for education
-- ----------------------------
DROP TABLE IF EXISTS `education`;
CREATE TABLE `education` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `study_year` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of education
-- ----------------------------
BEGIN;
INSERT INTO `education` VALUES (1, '2020-07-08 21-12-29', '2020-07-08 21-12-29', '本科', '4年');
INSERT INTO `education` VALUES (2, '2020-07-08 21-31-16', '2020-07-08 21-31-16', '硕士', '3年');
COMMIT;

-- ----------------------------
-- Table structure for institute
-- ----------------------------
DROP TABLE IF EXISTS `institute`;
CREATE TABLE `institute` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `institute_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of institute
-- ----------------------------
BEGIN;
INSERT INTO `institute` VALUES (1, '2020-07-08 21-13-12', '2020-07-08 21-13-12', '计算机课程与技术学院');
INSERT INTO `institute` VALUES (2, '2020-07-08 21-33-32', '2020-07-08 21-33-32', '机械学院');
INSERT INTO `institute` VALUES (3, '2020-07-08 21-33-44', '2020-07-08 21-33-44', '美术学院');
INSERT INTO `institute` VALUES (4, '2020-07-08 21-33-56', '2020-07-08 21-33-56', '音乐学院');
INSERT INTO `institute` VALUES (5, '2020-07-08 21-34-08', '2020-07-08 21-34-08', '体育学院');
INSERT INTO `institute` VALUES (6, '2020-07-08 21-34-24', '2020-07-08 21-34-24', '外国语学院');
INSERT INTO `institute` VALUES (7, '2020-07-08 21-34-56', '2020-07-08 21-34-56', '数学与统计学院');
INSERT INTO `institute` VALUES (8, '2020-07-08 21-35-46', '2020-07-08 21-35-46', '法学院');
INSERT INTO `institute` VALUES (9, '2020-07-08 21-36-03', '2020-07-08 21-36-03', '车辆工程学院');
INSERT INTO `institute` VALUES (10, '2020-07-08 21-36-46', '2020-07-08 21-36-46', '理学院');
INSERT INTO `institute` VALUES (11, '2020-07-08 21-37-09', '2020-07-08 21-37-09', '管理学院');
INSERT INTO `institute` VALUES (12, '2020-07-08 21-37-28', '2020-07-08 21-37-28', '化学化工学院');
COMMIT;

-- ----------------------------
-- Table structure for profession
-- ----------------------------
DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `profession_name` varchar(255) DEFAULT NULL,
  `institute_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe6qwjrrpv50wfbxn9im1m1yhe` (`institute_id`),
  CONSTRAINT `FKe6qwjrrpv50wfbxn9im1m1yhe` FOREIGN KEY (`institute_id`) REFERENCES `institute` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of profession
-- ----------------------------
BEGIN;
INSERT INTO `profession` VALUES (1, '2020-07-08 21-13-12', '2020-07-08 21-13-12', '软件', 1);
INSERT INTO `profession` VALUES (2, '2020-07-08 21-13-12', '2020-07-08 21-13-12', '计科', 1);
INSERT INTO `profession` VALUES (3, '2020-07-08 21-13-12', '2020-07-08 21-13-12', '通信', 1);
INSERT INTO `profession` VALUES (4, '2020-07-08 21-13-12', '2020-07-08 21-13-12', '大数据', 1);
INSERT INTO `profession` VALUES (5, '2020-07-08 21-33-32', '2020-07-08 21-33-32', '专业A', 2);
INSERT INTO `profession` VALUES (6, '2020-07-08 21-33-32', '2020-07-08 21-33-32', '专业B', 2);
INSERT INTO `profession` VALUES (7, '2020-07-08 21-33-32', '2020-07-08 21-33-32', '专业C', 2);
INSERT INTO `profession` VALUES (8, '2020-07-08 21-33-32', '2020-07-08 21-33-32', '专业D', 2);
INSERT INTO `profession` VALUES (9, '2020-07-08 21-33-32', '2020-07-08 21-33-32', '专业E', 2);
INSERT INTO `profession` VALUES (10, '2020-07-08 21-33-44', '2020-07-08 21-33-44', '专业A', 3);
INSERT INTO `profession` VALUES (11, '2020-07-08 21-33-44', '2020-07-08 21-33-44', '专业B', 3);
INSERT INTO `profession` VALUES (12, '2020-07-08 21-33-44', '2020-07-08 21-33-44', '专业C', 3);
INSERT INTO `profession` VALUES (13, '2020-07-08 21-33-44', '2020-07-08 21-33-44', '专业D', 3);
INSERT INTO `profession` VALUES (14, '2020-07-08 21-33-45', '2020-07-08 21-33-45', '专业E', 3);
INSERT INTO `profession` VALUES (15, '2020-07-08 21-33-56', '2020-07-08 21-33-56', '专业A', 4);
INSERT INTO `profession` VALUES (16, '2020-07-08 21-33-56', '2020-07-08 21-33-56', '专业B', 4);
INSERT INTO `profession` VALUES (17, '2020-07-08 21-33-56', '2020-07-08 21-33-56', '专业C', 4);
INSERT INTO `profession` VALUES (18, '2020-07-08 21-33-56', '2020-07-08 21-33-56', '专业D', 4);
INSERT INTO `profession` VALUES (19, '2020-07-08 21-33-56', '2020-07-08 21-33-56', '专业E', 4);
INSERT INTO `profession` VALUES (20, '2020-07-08 21-34-09', '2020-07-08 21-34-09', '专业A', 5);
INSERT INTO `profession` VALUES (21, '2020-07-08 21-34-09', '2020-07-08 21-34-09', '专业B', 5);
INSERT INTO `profession` VALUES (22, '2020-07-08 21-34-09', '2020-07-08 21-34-09', '专业C', 5);
INSERT INTO `profession` VALUES (23, '2020-07-08 21-34-09', '2020-07-08 21-34-09', '专业D', 5);
INSERT INTO `profession` VALUES (24, '2020-07-08 21-34-09', '2020-07-08 21-34-09', '专业E', 5);
INSERT INTO `profession` VALUES (25, '2020-07-08 21-34-24', '2020-07-08 21-34-24', '专业A', 6);
INSERT INTO `profession` VALUES (26, '2020-07-08 21-34-24', '2020-07-08 21-34-24', '专业B', 6);
INSERT INTO `profession` VALUES (27, '2020-07-08 21-34-24', '2020-07-08 21-34-24', '专业C', 6);
INSERT INTO `profession` VALUES (28, '2020-07-08 21-34-24', '2020-07-08 21-34-24', '专业D', 6);
INSERT INTO `profession` VALUES (29, '2020-07-08 21-34-24', '2020-07-08 21-34-24', '专业E', 6);
INSERT INTO `profession` VALUES (30, '2020-07-08 21-34-56', '2020-07-08 21-34-56', '专业A', 7);
INSERT INTO `profession` VALUES (31, '2020-07-08 21-34-56', '2020-07-08 21-34-56', '专业B', 7);
INSERT INTO `profession` VALUES (32, '2020-07-08 21-34-56', '2020-07-08 21-34-56', '专业C', 7);
INSERT INTO `profession` VALUES (33, '2020-07-08 21-34-56', '2020-07-08 21-34-56', '专业D', 7);
INSERT INTO `profession` VALUES (34, '2020-07-08 21-34-56', '2020-07-08 21-34-56', '专业E', 7);
INSERT INTO `profession` VALUES (35, '2020-07-08 21-35-46', '2020-07-08 21-35-46', '专业A', 8);
INSERT INTO `profession` VALUES (36, '2020-07-08 21-35-46', '2020-07-08 21-35-46', '专业B', 8);
INSERT INTO `profession` VALUES (37, '2020-07-08 21-35-46', '2020-07-08 21-35-46', '专业C', 8);
INSERT INTO `profession` VALUES (38, '2020-07-08 21-35-46', '2020-07-08 21-35-46', '专业D', 8);
INSERT INTO `profession` VALUES (39, '2020-07-08 21-35-46', '2020-07-08 21-35-46', '专业E', 8);
INSERT INTO `profession` VALUES (40, '2020-07-08 21-36-03', '2020-07-08 21-36-03', '专业A', 9);
INSERT INTO `profession` VALUES (41, '2020-07-08 21-36-03', '2020-07-08 21-36-03', '专业B', 9);
INSERT INTO `profession` VALUES (42, '2020-07-08 21-36-03', '2020-07-08 21-36-03', '专业C', 9);
INSERT INTO `profession` VALUES (43, '2020-07-08 21-36-03', '2020-07-08 21-36-03', '专业D', 9);
INSERT INTO `profession` VALUES (44, '2020-07-08 21-36-03', '2020-07-08 21-36-03', '专业E', 9);
INSERT INTO `profession` VALUES (45, '2020-07-08 21-36-46', '2020-07-08 21-36-46', '专业A', 10);
INSERT INTO `profession` VALUES (46, '2020-07-08 21-36-46', '2020-07-08 21-36-46', '专业B', 10);
INSERT INTO `profession` VALUES (47, '2020-07-08 21-36-46', '2020-07-08 21-36-46', '专业C', 10);
INSERT INTO `profession` VALUES (48, '2020-07-08 21-36-46', '2020-07-08 21-36-46', '专业D', 10);
INSERT INTO `profession` VALUES (49, '2020-07-08 21-36-46', '2020-07-08 21-36-46', '专业E', 10);
INSERT INTO `profession` VALUES (50, '2020-07-08 21-37-09', '2020-07-08 21-37-09', '专业A', 11);
INSERT INTO `profession` VALUES (51, '2020-07-08 21-37-09', '2020-07-08 21-37-09', '专业B', 11);
INSERT INTO `profession` VALUES (52, '2020-07-08 21-37-09', '2020-07-08 21-37-09', '专业C', 11);
INSERT INTO `profession` VALUES (53, '2020-07-08 21-37-09', '2020-07-08 21-37-09', '专业D', 11);
INSERT INTO `profession` VALUES (54, '2020-07-08 21-37-09', '2020-07-08 21-37-09', '专业E', 11);
INSERT INTO `profession` VALUES (55, '2020-07-08 21-37-28', '2020-07-08 21-37-28', '专业A', 12);
INSERT INTO `profession` VALUES (56, '2020-07-08 21-37-28', '2020-07-08 21-37-28', '专业B', 12);
INSERT INTO `profession` VALUES (57, '2020-07-08 21-37-28', '2020-07-08 21-37-28', '专业C', 12);
INSERT INTO `profession` VALUES (58, '2020-07-08 21-37-28', '2020-07-08 21-37-28', '专业D', 12);
INSERT INTO `profession` VALUES (59, '2020-07-08 21-37-28', '2020-07-08 21-37-28', '专业E', 12);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (2, '2020-07-08 12-11-15', '2020-07-08 12-11-15', 'ADMIN');
INSERT INTO `role` VALUES (3, '2020-07-08 12-13-43', '2020-07-08 12-13-43', 'USER');
COMMIT;

-- ----------------------------
-- Table structure for score_nature
-- ----------------------------
DROP TABLE IF EXISTS `score_nature`;
CREATE TABLE `score_nature` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `score_nature_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score_nature
-- ----------------------------
BEGIN;
INSERT INTO `score_nature` VALUES (1, '2020-07-08 21-12-03', '2020-07-08 21-12-03', '正常考试');
INSERT INTO `score_nature` VALUES (2, '2020-07-08 21-30-33', '2020-07-08 21-30-33', '正常重修');
INSERT INTO `score_nature` VALUES (3, '2020-07-08 21-30-42', '2020-07-08 21-30-42', '重修考试');
INSERT INTO `score_nature` VALUES (4, '2020-07-08 21-30-51', '2020-07-08 21-30-51', '重修缓考');
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthplace` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `hometown` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `student_number` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `admission_time` varchar(255) DEFAULT NULL,
  `institute_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhahtmjbbf09kwtrgd150xop46` (`institute_id`),
  CONSTRAINT `FKhahtmjbbf09kwtrgd150xop46` FOREIGN KEY (`institute_id`) REFERENCES `institute` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (5, '2020-07-08 21-57-34', '2020-07-08 21-57-34', '山东淄博', '山东', '2020-07-14', '山东淄博', '张三', '汉', '10003', '男', '2020-07-13', 2);
INSERT INTO `student` VALUES (6, '2020-07-08 21-58-10', '2020-07-08 21-58-10', '山东淄博 ', '山东', '2020-07-14', '山东青岛', '李四', '汉', '10004', '男', '2020-07-12', 3);
INSERT INTO `student` VALUES (7, '2020-07-08 21-58-49', '2020-07-08 21-58-49', '山东淄博', '山东', '2020-07-14', '山东青岛', '王五', '回', '10005', '女', '2020-07-13', 4);
INSERT INTO `student` VALUES (8, '2020-07-08 22-00-17', '2020-07-08 22-00-17', '山东淄博', '山东', '2020-07-19', '山东青岛', '王五二', '回', '10006', '男', '2020-07-19', 3);
INSERT INTO `student` VALUES (9, '2020-07-08 22-03-14', '2020-07-08 22-03-14', '山东淄博', '河北', '2020-07-13', '河北石家庄', '赵云', '汉', '10007', '男', '2020-07-19', 5);
INSERT INTO `student` VALUES (10, '2020-07-08 22-04-58', '2020-07-08 22-04-58', '山东淄博', '山东', '2020-07-12', '山东潍坊', '张飞', '满', '10008', '男', '2020-07-26', 5);
INSERT INTO `student` VALUES (11, '2020-07-08 22-05-59', '2020-07-08 22-05-59', '山东淄博', '山东', '2020-07-25', '山东菏泽', '关羽', '汉', '10009', '男', '2020-07-18', 10);
INSERT INTO `student` VALUES (12, '2020-07-08 22-06-57', '2020-07-08 22-06-57', '山东淄博', '山东', '2020-07-12', '山东淄博', '刘备', '汉', '10010', '男', '2020-07-26', 7);
INSERT INTO `student` VALUES (13, '2020-07-08 22-07-45', '2020-07-08 22-07-45', '山东淄博', '山东', '2020-07-19', '山东潍坊', '曹操', '汉', '10011', '男', '2020-07-11', 12);
INSERT INTO `student` VALUES (14, '2020-07-08 22-08-44', '2020-07-08 22-08-44', '山东淄博', '山东', '2020-07-19', '山东临沂', '貂蝉', '汉', '10012', '女', '2020-07-19', 9);
INSERT INTO `student` VALUES (15, '2020-07-08 22-11-05', '2020-07-08 22-11-05', '山东淄博', '北京', '2020-07-18', '北京', '甄姬', '汉', '10013', '女', '2020-07-19', 2);
COMMIT;

-- ----------------------------
-- Table structure for sys_student_education
-- ----------------------------
DROP TABLE IF EXISTS `sys_student_education`;
CREATE TABLE `sys_student_education` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `education_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi9noquwjej04pga2tehl5opp7` (`education_id`),
  KEY `FKem61otp16393l8109ifj5mwm0` (`student_id`),
  CONSTRAINT `FKem61otp16393l8109ifj5mwm0` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `FKi9noquwjej04pga2tehl5opp7` FOREIGN KEY (`education_id`) REFERENCES `education` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_student_education
-- ----------------------------
BEGIN;
INSERT INTO `sys_student_education` VALUES (5, '2020-07-08 21-57-34', '2020-07-08 21-57-34', 1, 5);
INSERT INTO `sys_student_education` VALUES (6, '2020-07-08 21-58-10', '2020-07-08 21-58-10', 1, 6);
INSERT INTO `sys_student_education` VALUES (7, '2020-07-08 21-58-49', '2020-07-08 21-58-49', 1, 7);
INSERT INTO `sys_student_education` VALUES (8, '2020-07-08 22-00-17', '2020-07-08 22-00-17', 1, 8);
INSERT INTO `sys_student_education` VALUES (9, '2020-07-08 22-03-14', '2020-07-08 22-03-14', 1, 9);
INSERT INTO `sys_student_education` VALUES (10, '2020-07-08 22-04-58', '2020-07-08 22-04-58', 2, 10);
INSERT INTO `sys_student_education` VALUES (11, '2020-07-08 22-05-59', '2020-07-08 22-05-59', 2, 11);
INSERT INTO `sys_student_education` VALUES (12, '2020-07-08 22-06-57', '2020-07-08 22-06-57', 2, 12);
INSERT INTO `sys_student_education` VALUES (13, '2020-07-08 22-07-45', '2020-07-08 22-07-45', 2, 13);
INSERT INTO `sys_student_education` VALUES (14, '2020-07-08 22-08-44', '2020-07-08 22-08-44', 1, 14);
INSERT INTO `sys_student_education` VALUES (15, '2020-07-08 22-11-05', '2020-07-08 22-11-05', 2, 15);
COMMIT;

-- ----------------------------
-- Table structure for sys_teacher_course
-- ----------------------------
DROP TABLE IF EXISTS `sys_teacher_course`;
CREATE TABLE `sys_teacher_course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  `teacher_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcegnwlmhggudwtku0vpdvw173` (`course_id`),
  KEY `FK5t7e81lw7wynqshsy4krl94gl` (`teacher_id`),
  CONSTRAINT `FK5t7e81lw7wynqshsy4krl94gl` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`),
  CONSTRAINT `FKcegnwlmhggudwtku0vpdvw173` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_teacher_course
-- ----------------------------
BEGIN;
INSERT INTO `sys_teacher_course` VALUES (3, '2020-07-08 21-45-47', '2020-07-08 21-45-47', 3, 2);
INSERT INTO `sys_teacher_course` VALUES (5, '2020-07-08 21-47-14', '2020-07-08 21-47-14', 5, 4);
INSERT INTO `sys_teacher_course` VALUES (6, '2020-07-08 21-47-43', '2020-07-08 21-47-43', 4, 3);
INSERT INTO `sys_teacher_course` VALUES (7, '2020-07-08 21-48-57', '2020-07-08 21-48-57', 6, 5);
INSERT INTO `sys_teacher_course` VALUES (8, '2020-07-08 21-49-32', '2020-07-08 21-49-32', 7, 6);
INSERT INTO `sys_teacher_course` VALUES (9, '2020-07-08 21-50-13', '2020-07-08 21-50-13', 8, 7);
INSERT INTO `sys_teacher_course` VALUES (10, '2020-07-08 21-50-41', '2020-07-08 21-50-41', 9, 8);
INSERT INTO `sys_teacher_course` VALUES (11, '2020-07-08 21-51-42', '2020-07-08 21-51-42', 10, 9);
INSERT INTO `sys_teacher_course` VALUES (12, '2020-07-08 21-52-34', '2020-07-08 21-52-34', 11, 10);
INSERT INTO `sys_teacher_course` VALUES (13, '2020-07-08 21-53-10', '2020-07-08 21-53-10', 12, 11);
INSERT INTO `sys_teacher_course` VALUES (14, '2020-07-08 22-28-38', '2020-07-08 22-28-38', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt086m3oo91mwo102fl45gg3mr` (`role_id`),
  KEY `FKjyhkvwgvnte8yr010vwsey66h` (`user_id`),
  CONSTRAINT `FKjyhkvwgvnte8yr010vwsey66h` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKt086m3oo91mwo102fl45gg3mr` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (4, 3, 3);
INSERT INTO `sys_user_role` VALUES (5, 2, 3);
INSERT INTO `sys_user_role` VALUES (6, 3, 4);
INSERT INTO `sys_user_role` VALUES (7, 3, 5);
INSERT INTO `sys_user_role` VALUES (8, 3, 6);
COMMIT;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `institute_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhxlfncm4hxuwkb2k3cvybau9a` (`institute_id`),
  CONSTRAINT `FKhxlfncm4hxuwkb2k3cvybau9a` FOREIGN KEY (`institute_id`) REFERENCES `institute` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
BEGIN;
INSERT INTO `teacher` VALUES (1, '2020-07-08 21-13-47', '2020-07-08 21-13-47', '2020-07-07', '1001', 'teacher1', '10001', '男', '2020-07-21', 1);
INSERT INTO `teacher` VALUES (2, '2020-07-08 21-38-31', '2020-07-08 21-38-31', '2020-07-13', '10000', '教师2', '10002', '男', '2020-07-19', 7);
INSERT INTO `teacher` VALUES (3, '2020-07-08 21-38-52', '2020-07-08 21-38-52', '2020-07-13', '10000', '教师3', '10003', '男', '2020-07-19', 8);
INSERT INTO `teacher` VALUES (4, '2020-07-08 21-39-21', '2020-07-08 21-39-21', '2020-07-13', '10000', '教师4', '10004', '女', '2020-07-19', 3);
INSERT INTO `teacher` VALUES (5, '2020-07-08 21-39-39', '2020-07-08 21-39-39', '2020-07-13', '10000', '教师5', '10005', '女', '2020-07-19', 2);
INSERT INTO `teacher` VALUES (6, '2020-07-08 21-39-53', '2020-07-08 21-39-53', '2020-07-13', '10000', '教师6', '10006', '女', '2020-07-19', 4);
INSERT INTO `teacher` VALUES (7, '2020-07-08 21-40-09', '2020-07-08 21-40-09', '2020-07-13', '10000', '教师7', '10007', '男', '2020-07-19', 5);
INSERT INTO `teacher` VALUES (8, '2020-07-08 21-40-28', '2020-07-08 21-40-28', '2020-07-13', '10000', '教师8', '10008', '男', '2020-07-19', 6);
INSERT INTO `teacher` VALUES (9, '2020-07-08 21-40-49', '2020-07-08 21-40-49', '2020-07-13', '10000', '教师9', '10009', '女', '2020-07-19', 1);
INSERT INTO `teacher` VALUES (10, '2020-07-08 21-41-17', '2020-07-08 21-41-17', '2020-07-13', '10000', '教师10', '10010', '女', '2020-07-19', 3);
INSERT INTO `teacher` VALUES (11, '2020-07-08 21-41-37', '2020-07-08 21-41-37', '2020-07-13', '10000', '教师11', '10011', '男', '2020-07-19', 4);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` varchar(255) DEFAULT NULL,
  `update_date` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_names` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (3, '2020-07-08 12-14-18', '2020-07-25 07-46-51', '000000000@163.com', 'admin', '$2a$10$Z8jFkWRGmSg5k8uX8Cyb6.HxirEVMO0KeBLAKujLmKKoD0uCWZPTK', '-USER-ADMIN', 'admin');
INSERT INTO `user` VALUES (4, '2020-07-08 21-28-44', '2020-07-08 21-28-44', '1818181818@qq.com', 'user1', '$2a$10$.KVhqgBO18MvU6nMFbJs8eHB2XcMZHVLXUnRuEuTj1QUys4elvIm.', '-USER', 'user1');
INSERT INTO `user` VALUES (5, '2020-07-08 21-29-00', '2020-07-08 21-29-00', '1871717171@qq.com', 'user2', '$2a$10$lqy5mNwH0jbwDRbyHPqbReRkxoLFdcyaGQ8OKgT/0bfu2jUdK3gey', '-USER', 'user2');
INSERT INTO `user` VALUES (6, '2020-07-08 21-29-17', '2020-07-08 21-29-17', '1717171771@qq.com', 'user3', '$2a$10$lol1WSacryt6YbZdd6bAV.5oZc8IPDUxJM/.SFeNt8K53LKgFRP9K', '-USER', 'user3');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
