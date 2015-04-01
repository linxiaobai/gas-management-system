/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50618
Source Host           : localhost:3306
Source Database       : gms

Target Server Type    : MYSQL
Target Server Version : 50618
File Encoding         : 65001

Date: 2015-04-01 14:28:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `ID` int(11) NOT NULL,
  `PARAM_NAME` varchar(45) DEFAULT NULL,
  `PARAM_VALUE` double DEFAULT NULL,
  `MIN_VALUE` double DEFAULT NULL,
  `MAX_VALUE` double DEFAULT NULL,
  `IS_FAILED` smallint(6) DEFAULT NULL,
  `REPAIR_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------

-- ----------------------------
-- Table structure for finance
-- ----------------------------
DROP TABLE IF EXISTS `finance`;
CREATE TABLE `finance` (
  `ID` int(11) NOT NULL,
  `FEE` double DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of finance
-- ----------------------------
INSERT INTO `finance` VALUES ('1', '10', '2015-04-01 09:44:36');
INSERT INTO `finance` VALUES ('2', '-20', '2015-04-01 09:44:36');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `ID` int(11) NOT NULL,
  `USER_TYPE` smallint(1) DEFAULT NULL,
  `MENU_LEVEL` smallint(1) DEFAULT NULL,
  `MENU_NAME` varchar(50) DEFAULT NULL,
  `MENU_MSG` varchar(45) DEFAULT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `IS_VALID` smallint(1) DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `ID` int(11) NOT NULL,
  `PAYER_PHONE` varchar(20) DEFAULT NULL,
  `PAY_MONEY` double DEFAULT NULL,
  `HANDLER_ID` bigint(20) DEFAULT NULL,
  `HANDLER_NAME` varchar(45) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of payment
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL,
  `USERNAME` varchar(45) DEFAULT NULL,
  `PASSWD` varchar(45) DEFAULT NULL,
  `REAL_NAME` varchar(45) DEFAULT NULL,
  `USER_TYPE` smallint(1) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `MODIFY_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'linjian', '123', '林健', '1', '2015-03-31 10:49:41', '2015-03-31 10:49:46');
INSERT INTO `user` VALUES ('2', 'xiaobai', '123456', null, null, null, null);
INSERT INTO `user` VALUES ('3', 'haohao', '123456', '小小', '2', '2015-04-01 09:09:30', '2015-04-01 09:09:30');
