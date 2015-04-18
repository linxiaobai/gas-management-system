/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50618
Source Host           : localhost:3306
Source Database       : gms

Target Server Type    : MYSQL
Target Server Version : 50618
File Encoding         : 65001

Date: 2015-04-18 16:28:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BILL_SEQUENCE` varchar(255) DEFAULT NULL,
  `PAYER_PHONE` varchar(20) DEFAULT NULL,
  `SHD_PAY_MONEY` double DEFAULT NULL,
  `HANDLER_ID` bigint(20) DEFAULT NULL,
  `HANDLER_NAME` varchar(45) DEFAULT NULL,
  `LATE_FEE` double DEFAULT NULL,
  `ACT_PAY_MONYE` double DEFAULT NULL,
  `SHD_PAY_DATE` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `ACT_PAY_DATE` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `STATUS` smallint(6) DEFAULT NULL,
  `GAS_USE_AMOUNT` double DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `MODIFY_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bill
-- ----------------------------

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
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
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FEE` double DEFAULT NULL,
  `DETAIL` varchar(255) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of finance
-- ----------------------------
INSERT INTO `finance` VALUES ('1', '10', null, '2015-04-01 09:44:36');
INSERT INTO `finance` VALUES ('2', '-20', null, '2015-04-01 09:44:36');

-- ----------------------------
-- Table structure for menus
-- ----------------------------
DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_TYPE` smallint(1) DEFAULT NULL,
  `MENU_LEVEL` smallint(1) DEFAULT NULL,
  `MENU_NAME` varchar(50) DEFAULT NULL,
  `MENU_MSG` varchar(45) DEFAULT NULL,
  `PARENT_ID` int(11) DEFAULT NULL,
  `IS_VALID` smallint(1) DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menus
-- ----------------------------
INSERT INTO `menus` VALUES ('1', '1', '1', '管理员管理模块', '', '0', '1');
INSERT INTO `menus` VALUES ('2', '1', '1', '系统管理模块', null, '0', '1');
INSERT INTO `menus` VALUES ('3', '1', '1', '设备管理模块', null, '0', '1');
INSERT INTO `menus` VALUES ('4', '1', '1', '环境管理模块', null, '0', '1');
INSERT INTO `menus` VALUES ('5', '1', '1', '数据管理模块', null, '0', '1');
INSERT INTO `menus` VALUES ('6', '1', '1', '报警管理模块', null, '0', '1');
INSERT INTO `menus` VALUES ('7', '2', '1', '系统管理模块', null, '0', '1');
INSERT INTO `menus` VALUES ('8', '2', '1', '设备管理模块', null, '0', '1');
INSERT INTO `menus` VALUES ('9', '2', '1', '环境管理模块', null, '0', '1');
INSERT INTO `menus` VALUES ('10', '2', '1', '数据管理模块', null, '0', '1');
INSERT INTO `menus` VALUES ('11', '2', '1', '报警管理模块', null, '0', '1');

-- ----------------------------
-- Table structure for system_param
-- ----------------------------
DROP TABLE IF EXISTS `system_param`;
CREATE TABLE `system_param` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PARAM_NAME` varchar(45) DEFAULT NULL,
  `PARAM_VALUE` varchar(45) DEFAULT NULL,
  `STATUS` smallint(6) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_param
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) DEFAULT NULL,
  `PASSWD` varchar(45) DEFAULT NULL,
  `REAL_NAME` varchar(45) DEFAULT NULL,
  `USER_TYPE` smallint(1) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `MODIFY_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'linjian', 'e10adc3949ba59abbe56e057f20f883e', '林健', '1', '2015-04-18 09:19:08', '2015-04-18 09:19:08');
INSERT INTO `user` VALUES ('2', 'xiaobai', '123456', null, null, null, null);
INSERT INTO `user` VALUES ('3', 'haohao', '123456', '小小', '2', '2015-04-01 09:09:30', '2015-04-01 09:09:30');
