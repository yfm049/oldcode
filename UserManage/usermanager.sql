/*
Navicat MySQL Data Transfer

Source Server         : 54
Source Server Version : 50520
Source Host           : 10.120.148.54:3306
Source Database       : usermanager

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2012-12-14 18:10:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `pass` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '234');

-- ----------------------------
-- Table structure for `auth`
-- ----------------------------
DROP TABLE IF EXISTS `auth`;
CREATE TABLE `auth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `IMEI` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `zt` varchar(10) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth
-- ----------------------------

-- ----------------------------
-- Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `logintime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log
-- ----------------------------
INSERT INTO `log` VALUES ('1', '4234', '2012-12-14 16:29:32');
INSERT INTO `log` VALUES ('2', null, '2012-12-14 17:36:20');
INSERT INTO `log` VALUES ('3', '123', '2012-12-14 17:39:32');

-- ----------------------------
-- Table structure for `manager`
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `realname` varchar(100) DEFAULT NULL,
  `phonenum` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `imei` varchar(100) DEFAULT NULL,
  `state` int(11) DEFAULT '0' COMMENT '状态 0,未审核，1已审核，2审核不通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('2', '345', '345', '234上大富大贵', '345234234', '1sf@123.com', '864046001245479', '1');
INSERT INTO `manager` VALUES ('3', '4234', '234234', '2342444', '23423', '4234234', '123123213', '0');
INSERT INTO `manager` VALUES ('4', '24131', '234', '34534', '231', '313123', '1232321321', '0');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `realname` varchar(100) DEFAULT NULL,
  `phonenum` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `imei` varchar(100) DEFAULT NULL,
  `state` int(11) DEFAULT '0' COMMENT '状态 0,未审核，1已审核，2审核不通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '345', '345', '234上大富大贵', '345234234', '1sf@123.com', '864046001245479', '0');
INSERT INTO `user` VALUES ('3', '123', '123', '2342444', '23423', '4234234', '864046001245479', '1');
INSERT INTO `user` VALUES ('4', '24131', '234', '34534', '231', '313123', '1232321321', '1');
