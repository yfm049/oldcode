/*
Navicat MySQL Data Transfer

Source Server         : 54
Source Server Version : 50520
Source Host           : 10.120.148.54:3306
Source Database       : jxc

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2012-12-06 17:34:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `out_data`
-- ----------------------------
DROP TABLE IF EXISTS `out_data`;
CREATE TABLE `out_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `oid` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `pinpai` varchar(50) DEFAULT NULL,
  `guige` varchar(50) DEFAULT NULL,
  `danwei` varchar(20) DEFAULT NULL,
  `shuliang` int(20) DEFAULT NULL,
  `mishu` double(20,3) DEFAULT NULL,
  `danjia` double(20,2) DEFAULT NULL,
  `money` double(20,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of out_data
-- ----------------------------
INSERT INTO `out_data` VALUES ('58', '21', 'sdfgsfdg', 'dfgfd', 'B4643', '条', '6', '27.857', '3.00', '83.57');
INSERT INTO `out_data` VALUES ('59', '21', 'ads', 'asdf', 'B7533', '条', '7', '52.731', '2.00', '105.46');
INSERT INTO `out_data` VALUES ('60', '20', '千万', 'qwejgj', 'B3456', '条', '43', '148.608', '32.00', '4755.45');
INSERT INTO `out_data` VALUES ('61', '20', '安慰', 'ADSAFugu', 'B4522', '条', '6', '27.132', '3.00', '81.39');
INSERT INTO `out_data` VALUES ('62', '20', 'SEFS', 'ASDFjhg', 'B6428', '条', '2', '12.856', '4.00', '51.42');
INSERT INTO `out_data` VALUES ('63', '20', '4323', '234234', 'B4325', '条', '7', '30.275', '2.00', '60.55');

-- ----------------------------
-- Table structure for `out_list`
-- ----------------------------
DROP TABLE IF EXISTS `out_list`;
CREATE TABLE `out_list` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `danhao` varchar(50) DEFAULT NULL,
  `phonenum` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `price` double(20,2) DEFAULT NULL,
  `dxprice` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of out_list
-- ----------------------------
INSERT INTO `out_list` VALUES ('20', '维玩儿', '1354781453281', '23423545435', '2012-12-06 16:10:41', '4948.81', '肆千玖百肆拾捌元捌角壹分');
INSERT INTO `out_list` VALUES ('21', 'easdf', '1354784999218', 'adsf', '2012-12-06 17:09:56', '189.03', '壹百捌拾玖元零叁分');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `pass` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin');
