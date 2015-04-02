/*
Navicat MySQL Data Transfer

Source Server         : 54
Source Server Version : 50520
Source Host           : 10.120.148.54:3306
Source Database       : user

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2012-12-14 18:10:28
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', '345', '345', '234上大富大贵', '345234234', '1sf@123.com', '123213123123', '0');
INSERT INTO `user` VALUES ('3', '4234', '234234', '2342444', '23423', '4234234', '123123213', '1');
INSERT INTO `user` VALUES ('4', '24131', '234', '34534', '231', '313123', '1232321321', '0');
INSERT INTO `user` VALUES ('5', '345', '345', '234上大富大贵', '345234234', '1sf@123.com', '123213123123', '0');
INSERT INTO `user` VALUES ('6', '4234', '234234', '2342444', '23423', '4234234', '123123213', '1');
INSERT INTO `user` VALUES ('7', '24131', '234', '34534', '231', '313123', '1232321321', '0');
