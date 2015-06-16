/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50618
Source Host           : 127.0.0.1:3306
Source Database       : gms

Target Server Type    : MYSQL
Target Server Version : 50618
File Encoding         : 65001

Date: 2015-06-15 11:13:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BILL_SEQUENCE` varchar(255) DEFAULT NULL,
  `PAYER_PHONE` varchar(20) DEFAULT NULL,
  `SHD_PAY_MONEY` double DEFAULT NULL,
  `HANDLER_ID` bigint(20) DEFAULT NULL,
  `HANDLER_NAME` varchar(45) DEFAULT NULL,
  `STATUS` smallint(6) DEFAULT NULL,
  `GAS_USE_AMOUNT` double DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `MODIFY_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES ('1', '2015060900000001', '13102116528', '1000', '1', '林健', '1', '250', '2015-06-09 22:52:19', '2015-06-09 22:52:19');
INSERT INTO `bill` VALUES ('2', '2015061000000002', '13102116528', '160', '1', '林健', '1', '20', '2015-06-10 09:04:08', '2015-06-10 09:04:08');

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEVICE_NAME` varchar(45) DEFAULT NULL,
  `PA_VAL` double DEFAULT NULL,
  `PA_MIN_VAL` double DEFAULT NULL,
  `PA_MAX_VAL` double DEFAULT NULL,
  `TEMP_VAL` double DEFAULT NULL,
  `TEMP_MIN_VAL` double DEFAULT NULL,
  `TEMP_MAX_VAL` double DEFAULT NULL,
  `WATER_LEVEL_VAL` double DEFAULT NULL,
  `WL_MIN_VAL` double DEFAULT NULL,
  `WL_MAX_VAL` double DEFAULT NULL,
  `IS_FAILED` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('1', '电阻器', '9.5', '2', '10', '26', '3', '50', '6.4', '1', '10', '0');
INSERT INTO `device` VALUES ('2', '继电器', '4.6', '3', '7', '5', '5', '20', '7.2', '4', '30', '1');
INSERT INTO `device` VALUES ('3', '变频器', '24', '14', '28', '24.3', '2', '30', '5.9', '6', '15', '1');
INSERT INTO `device` VALUES ('4', '传感器', '9', '3', '9', '4.9', '3', '6', '7.5', '5', '14', '1');
INSERT INTO `device` VALUES ('5', '振动器', '21.2', '6', '25', '8.9', '4', '16', '4.3', '2', '16', '0');
INSERT INTO `device` VALUES ('6', '设备6', '9.1', '5', '9', '5.4', '2', '6', '3.1', '2', '7', '1');
INSERT INTO `device` VALUES ('7', '设备7', '10.6', '1', '15', '13.2', '3', '16', '16.1', '2', '18', '0');
INSERT INTO `device` VALUES ('8', '设备8', '12.9', '6', '13', '5.4', '2', '16', '8.7', '2', '17', '0');
INSERT INTO `device` VALUES ('9', '设备9', '9.3', '6', '16', '49.6', '10', '60', '12', '6', '16', '0');
INSERT INTO `device` VALUES ('10', '设备10', '24.3', '2', '26', '3.5', '1', '13', '6.9', '2', '13', '0');
INSERT INTO `device` VALUES ('11', '设备', '9.8', '1', '12', '2', '2', '23', '3.3', '2', '12', '1');

-- ----------------------------
-- Table structure for environmental_param
-- ----------------------------
DROP TABLE IF EXISTS `environmental_param`;
CREATE TABLE `environmental_param` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TEMP_VAL` double DEFAULT NULL,
  `FEED_WATER_TEMP_VAL` double DEFAULT NULL,
  `OUT_WATER_TEMP_VAL` double DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of environmental_param
-- ----------------------------
INSERT INTO `environmental_param` VALUES ('1', '13.1', '6.9', '10.7', '2015-06-09 22:03:45');
INSERT INTO `environmental_param` VALUES ('2', '14.6', '2.8', '0.9', '2015-06-09 22:04:15');
INSERT INTO `environmental_param` VALUES ('3', '1.4', '0.2', '11.6', '2015-06-09 22:04:45');
INSERT INTO `environmental_param` VALUES ('4', '0.1', '2.5', '6', '2015-06-09 22:05:15');
INSERT INTO `environmental_param` VALUES ('5', '2.7', '10.9', '4.3', '2015-06-09 22:05:45');
INSERT INTO `environmental_param` VALUES ('6', '0.4', '1.5', '10.5', '2015-06-09 22:06:15');
INSERT INTO `environmental_param` VALUES ('7', '14.9', '0.3', '2.9', '2015-06-09 22:06:45');
INSERT INTO `environmental_param` VALUES ('8', '12', '14.9', '10.4', '2015-06-09 22:07:15');
INSERT INTO `environmental_param` VALUES ('9', '1.3', '8.8', '1', '2015-06-09 22:07:45');
INSERT INTO `environmental_param` VALUES ('10', '3.5', '8.2', '4.6', '2015-06-09 22:08:15');
INSERT INTO `environmental_param` VALUES ('11', '9.5', '0.9', '13.1', '2015-06-09 22:08:45');
INSERT INTO `environmental_param` VALUES ('12', '1.4', '12.1', '2.1', '2015-06-09 22:09:15');
INSERT INTO `environmental_param` VALUES ('13', '1.2', '5.4', '1.5', '2015-06-09 22:09:45');
INSERT INTO `environmental_param` VALUES ('14', '6.8', '5.8', '2.7', '2015-06-10 08:27:45');
INSERT INTO `environmental_param` VALUES ('15', '7.8', '2.1', '6.4', '2015-06-10 09:05:17');
INSERT INTO `environmental_param` VALUES ('16', '2', '0.6', '1.9', '2015-06-10 09:05:47');
INSERT INTO `environmental_param` VALUES ('17', '2.2', '1.6', '0.2', '2015-06-10 09:06:17');
INSERT INTO `environmental_param` VALUES ('18', '3.9', '0.3', '4.3', '2015-06-10 09:06:47');
INSERT INTO `environmental_param` VALUES ('19', '6.7', '11.8', '9', '2015-06-10 09:07:17');
INSERT INTO `environmental_param` VALUES ('20', '18.8', '5.3', '2.3', '2015-06-10 09:07:47');
INSERT INTO `environmental_param` VALUES ('21', '0.4', '11.4', '2.6', '2015-06-10 09:08:17');
INSERT INTO `environmental_param` VALUES ('22', '2.8', '1.2', '7.5', '2015-06-10 09:08:47');
INSERT INTO `environmental_param` VALUES ('23', '4.4', '8.4', '5.1', '2015-06-10 09:09:17');

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
INSERT INTO `finance` VALUES ('1', '1000', '用户缴费', '2015-06-09 22:52:19');
INSERT INTO `finance` VALUES ('2', '160', '用户缴费', '2015-06-10 09:04:08');

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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menus
-- ----------------------------
INSERT INTO `menus` VALUES ('1', '1', '1', '管理员管理模块', '', '0', '1');
INSERT INTO `menus` VALUES ('2', '1', '1', '系统管理模块', '', '0', '1');
INSERT INTO `menus` VALUES ('3', '1', '1', '设备管理模块', '', '0', '1');
INSERT INTO `menus` VALUES ('4', '1', '1', '环境管理模块', ' ', '0', '1');
INSERT INTO `menus` VALUES ('5', '1', '1', '数据管理模块', ' ', '0', '1');
INSERT INTO `menus` VALUES ('7', '2', '1', '系统管理模块', ' ', '0', '1');
INSERT INTO `menus` VALUES ('8', '2', '1', '设备管理模块', ' ', '0', '1');
INSERT INTO `menus` VALUES ('9', '2', '1', '环境管理模块', ' ', '0', '1');
INSERT INTO `menus` VALUES ('10', '2', '1', '数据管理模块', ' ', '0', '1');
INSERT INTO `menus` VALUES ('13', '1', '2', '权限分配', 'authDistribute', '1', '1');
INSERT INTO `menus` VALUES ('14', '1', '2', '用户添加', 'userAdd', '1', '1');
INSERT INTO `menus` VALUES ('15', '1', '2', '环境监控', 'environmentMonitor', '4', '1');
INSERT INTO `menus` VALUES ('16', '1', '2', '环境图表显示', 'environmentChart', '4', '1');
INSERT INTO `menus` VALUES ('17', '2', '2', '环境监控', 'environmentMonitor', '9', '1');
INSERT INTO `menus` VALUES ('18', '2', '2', '环境图表显示', 'environmentChart', '9', '1');
INSERT INTO `menus` VALUES ('19', '1', '2', '设备运行数据', 'deviceDataExport', '2', '1');
INSERT INTO `menus` VALUES ('20', '1', '2', '账户管理', 'accountManage', '2', '1');
INSERT INTO `menus` VALUES ('21', '2', '2', '设备运行数据', 'deviceDataExport', '7', '1');
INSERT INTO `menus` VALUES ('22', '2', '2', '账户管理', 'accountManage', '7', '1');
INSERT INTO `menus` VALUES ('23', '1', '2', '设备监控', 'deviceMonitor', '3', '1');
INSERT INTO `menus` VALUES ('24', '2', '2', '设备监控', 'deviceMonitor', '8', '1');
INSERT INTO `menus` VALUES ('25', '1', '2', '设备数据采集', 'deviceDataCollection', '5', '1');
INSERT INTO `menus` VALUES ('26', '2', '2', '设备数据采集', 'deviceDataCollection', '10', '1');
INSERT INTO `menus` VALUES ('27', '1', '2', '设备维修记录', 'deviceRepairedRecord', '5', '1');
INSERT INTO `menus` VALUES ('28', '2', '2', '设备维修记录', 'deviceRepairedRecord', '10', '1');
INSERT INTO `menus` VALUES ('31', '1', '2', '用户删除', 'userDel', '1', '1');
INSERT INTO `menus` VALUES ('32', '1', '2', '设备管理', 'deviceManage', '3', '1');
INSERT INTO `menus` VALUES ('33', '2', '2', '设备管理', 'deviceManage', '8', '1');
INSERT INTO `menus` VALUES ('34', '1', '2', '缴费管理', 'payManagement', '2', '1');
INSERT INTO `menus` VALUES ('35', '2', '2', '缴费管理', 'payManagement', '7', '1');

-- ----------------------------
-- Table structure for repaired_record
-- ----------------------------
DROP TABLE IF EXISTS `repaired_record`;
CREATE TABLE `repaired_record` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEVICE_ID` int(11) DEFAULT NULL,
  `USER_NAME` varchar(20) DEFAULT NULL,
  `REPAIRED_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repaired_record
-- ----------------------------
INSERT INTO `repaired_record` VALUES ('1', '1', '林健', '2015-06-09 22:07:14');
INSERT INTO `repaired_record` VALUES ('2', '1', '林健', '2015-06-09 22:09:21');
INSERT INTO `repaired_record` VALUES ('3', '2', '林健', '2015-06-09 22:59:13');
INSERT INTO `repaired_record` VALUES ('4', '3', '林健', '2015-06-09 22:59:21');
INSERT INTO `repaired_record` VALUES ('5', '4', '林健', '2015-06-09 22:59:26');
INSERT INTO `repaired_record` VALUES ('6', '5', '林健', '2015-06-10 09:05:00');

-- ----------------------------
-- Table structure for runtime_device
-- ----------------------------
DROP TABLE IF EXISTS `runtime_device`;
CREATE TABLE `runtime_device` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEVICE_ID` int(11) DEFAULT NULL,
  `PA_VAL` double DEFAULT NULL,
  `TEMP_VAL` double DEFAULT NULL,
  `WATER_LEVEL_VAL` double DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of runtime_device
-- ----------------------------
INSERT INTO `runtime_device` VALUES ('1', '1', '3', '46', '9.3', '2015-06-09 22:03:45');
INSERT INTO `runtime_device` VALUES ('2', '2', '3.3', '5.4', '20.7', '2015-06-09 22:03:45');
INSERT INTO `runtime_device` VALUES ('3', '3', '21.5', '11.6', '14.9', '2015-06-09 22:03:45');
INSERT INTO `runtime_device` VALUES ('4', '4', '7.6', '3.1', '12.1', '2015-06-09 22:03:45');
INSERT INTO `runtime_device` VALUES ('5', '5', '14.2', '13.6', '3.5', '2015-06-09 22:03:45');
INSERT INTO `runtime_device` VALUES ('6', '1', '2.8', '48', '3.5', '2015-06-09 22:04:15');
INSERT INTO `runtime_device` VALUES ('7', '2', '4.7', '19.5', '13.5', '2015-06-09 22:04:15');
INSERT INTO `runtime_device` VALUES ('8', '3', '21.6', '11.7', '14.1', '2015-06-09 22:04:15');
INSERT INTO `runtime_device` VALUES ('9', '4', '4', '5.2', '5.1', '2015-06-09 22:04:15');
INSERT INTO `runtime_device` VALUES ('10', '5', '19.4', '8.5', '7.6', '2015-06-09 22:04:15');
INSERT INTO `runtime_device` VALUES ('11', '1', '7.1', '46.8', '3.1', '2015-06-09 22:04:45');
INSERT INTO `runtime_device` VALUES ('12', '2', '3.8', '8.7', '25.6', '2015-06-09 22:04:45');
INSERT INTO `runtime_device` VALUES ('13', '3', '19.9', '23.6', '14', '2015-06-09 22:04:45');
INSERT INTO `runtime_device` VALUES ('14', '4', '5.1', '4.7', '12.6', '2015-06-09 22:04:45');
INSERT INTO `runtime_device` VALUES ('15', '5', '12.3', '15.1', '12.7', '2015-06-09 22:04:45');
INSERT INTO `runtime_device` VALUES ('16', '1', '9.6', '40.7', '9.8', '2015-06-09 22:05:15');
INSERT INTO `runtime_device` VALUES ('17', '2', '4.3', '7.4', '7.4', '2015-06-09 22:05:15');
INSERT INTO `runtime_device` VALUES ('18', '3', '21.8', '20.2', '14.4', '2015-06-09 22:05:15');
INSERT INTO `runtime_device` VALUES ('19', '4', '3.6', '3.4', '9.5', '2015-06-09 22:05:15');
INSERT INTO `runtime_device` VALUES ('20', '5', '23.9', '6.3', '2.1', '2015-06-09 22:05:15');
INSERT INTO `runtime_device` VALUES ('21', '1', '9.1', '41.9', '5.8', '2015-06-09 22:05:45');
INSERT INTO `runtime_device` VALUES ('22', '2', '6.3', '5.3', '29.5', '2015-06-09 22:05:45');
INSERT INTO `runtime_device` VALUES ('23', '3', '14.2', '7.2', '6.8', '2015-06-09 22:05:45');
INSERT INTO `runtime_device` VALUES ('24', '4', '3.8', '4.5', '11.6', '2015-06-09 22:05:45');
INSERT INTO `runtime_device` VALUES ('25', '5', '7.8', '14.4', '11.3', '2015-06-09 22:05:45');
INSERT INTO `runtime_device` VALUES ('26', '1', '7.3', '41.4', '2.7', '2015-06-09 22:06:15');
INSERT INTO `runtime_device` VALUES ('27', '2', '4.6', '14.5', '9.4', '2015-06-09 22:06:15');
INSERT INTO `runtime_device` VALUES ('28', '3', '20.4', '16.6', '10.2', '2015-06-09 22:06:15');
INSERT INTO `runtime_device` VALUES ('29', '4', '6.3', '4.7', '8.5', '2015-06-09 22:06:15');
INSERT INTO `runtime_device` VALUES ('30', '5', '15.4', '11.3', '5.6', '2015-06-09 22:06:15');
INSERT INTO `runtime_device` VALUES ('31', '1', '2', '3.5', '10', '2015-06-09 22:06:45');
INSERT INTO `runtime_device` VALUES ('32', '2', '5.8', '11.1', '29.5', '2015-06-09 22:06:45');
INSERT INTO `runtime_device` VALUES ('33', '3', '27.3', '21.2', '15', '2015-06-09 22:06:45');
INSERT INTO `runtime_device` VALUES ('34', '4', '7.2', '3.8', '11.1', '2015-06-09 22:06:45');
INSERT INTO `runtime_device` VALUES ('35', '5', '7.8', '10.2', '3.2', '2015-06-09 22:06:45');
INSERT INTO `runtime_device` VALUES ('36', '1', '8.5', '8.3', '6.1', '2015-06-09 22:07:15');
INSERT INTO `runtime_device` VALUES ('37', '2', '4.1', '9.5', '27.4', '2015-06-09 22:07:15');
INSERT INTO `runtime_device` VALUES ('38', '3', '14.1', '20.3', '7.4', '2015-06-09 22:07:15');
INSERT INTO `runtime_device` VALUES ('39', '4', '5.9', '5.6', '12.3', '2015-06-09 22:07:15');
INSERT INTO `runtime_device` VALUES ('40', '5', '18.5', '14.6', '3.7', '2015-06-09 22:07:15');
INSERT INTO `runtime_device` VALUES ('41', '1', '3.5', '3.1', '3.8', '2015-06-09 22:07:45');
INSERT INTO `runtime_device` VALUES ('42', '2', '6.5', '7.1', '10.3', '2015-06-09 22:07:45');
INSERT INTO `runtime_device` VALUES ('43', '3', '14.7', '26.1', '13.5', '2015-06-09 22:07:45');
INSERT INTO `runtime_device` VALUES ('44', '4', '3.8', '3.5', '9.5', '2015-06-09 22:07:45');
INSERT INTO `runtime_device` VALUES ('45', '5', '18.4', '12.8', '8', '2015-06-09 22:07:45');
INSERT INTO `runtime_device` VALUES ('46', '1', '2.9', '37.8', '8.9', '2015-06-09 22:08:15');
INSERT INTO `runtime_device` VALUES ('47', '2', '6.5', '16.2', '11.8', '2015-06-09 22:08:15');
INSERT INTO `runtime_device` VALUES ('48', '3', '17.6', '26.5', '8.7', '2015-06-09 22:08:15');
INSERT INTO `runtime_device` VALUES ('49', '4', '3.6', '3.8', '12.4', '2015-06-09 22:08:15');
INSERT INTO `runtime_device` VALUES ('50', '5', '14.9', '11.7', '15.8', '2015-06-09 22:08:15');
INSERT INTO `runtime_device` VALUES ('51', '6', '7.8', '2.6', '5', '2015-06-09 22:08:15');
INSERT INTO `runtime_device` VALUES ('52', '1', '6.7', '17.5', '10', '2015-06-09 22:08:45');
INSERT INTO `runtime_device` VALUES ('53', '2', '4.4', '16.5', '24.2', '2015-06-09 22:08:45');
INSERT INTO `runtime_device` VALUES ('54', '3', '15.5', '27.8', '9.2', '2015-06-09 22:08:45');
INSERT INTO `runtime_device` VALUES ('55', '4', '5.5', '4.2', '8.7', '2015-06-09 22:08:45');
INSERT INTO `runtime_device` VALUES ('56', '5', '23.2', '5.2', '14.3', '2015-06-09 22:08:45');
INSERT INTO `runtime_device` VALUES ('57', '6', '9', '4.2', '6', '2015-06-09 22:08:45');
INSERT INTO `runtime_device` VALUES ('58', '7', '8.6', '8.5', '7.9', '2015-06-09 22:08:45');
INSERT INTO `runtime_device` VALUES ('59', '8', '7.4', '7.4', '16.8', '2015-06-09 22:08:45');
INSERT INTO `runtime_device` VALUES ('60', '2', '6.6', '12.9', '12.4', '2015-06-09 22:09:15');
INSERT INTO `runtime_device` VALUES ('61', '3', '26.3', '15.3', '7.7', '2015-06-09 22:09:15');
INSERT INTO `runtime_device` VALUES ('62', '4', '4.4', '5.6', '13.6', '2015-06-09 22:09:15');
INSERT INTO `runtime_device` VALUES ('63', '5', '23.1', '10.4', '8.5', '2015-06-09 22:09:15');
INSERT INTO `runtime_device` VALUES ('64', '6', '8.9', '3.6', '5.5', '2015-06-09 22:09:15');
INSERT INTO `runtime_device` VALUES ('65', '7', '2.2', '4.5', '3', '2015-06-09 22:09:15');
INSERT INTO `runtime_device` VALUES ('66', '8', '12.6', '12.7', '16.7', '2015-06-09 22:09:15');
INSERT INTO `runtime_device` VALUES ('67', '9', '12.4', '32.7', '11.2', '2015-06-09 22:09:15');
INSERT INTO `runtime_device` VALUES ('68', '1', '4.3', '17.9', '2', '2015-06-09 22:09:45');
INSERT INTO `runtime_device` VALUES ('69', '2', '5.9', '7.5', '24.5', '2015-06-09 22:09:45');
INSERT INTO `runtime_device` VALUES ('70', '3', '21.8', '25.6', '11.1', '2015-06-09 22:09:45');
INSERT INTO `runtime_device` VALUES ('71', '4', '5.6', '5.3', '13.9', '2015-06-09 22:09:45');
INSERT INTO `runtime_device` VALUES ('72', '5', '6.8', '14.5', '12.7', '2015-06-09 22:09:45');
INSERT INTO `runtime_device` VALUES ('73', '6', '5.5', '5.3', '3.7', '2015-06-09 22:09:45');
INSERT INTO `runtime_device` VALUES ('74', '7', '12.3', '14.3', '11.4', '2015-06-09 22:09:45');
INSERT INTO `runtime_device` VALUES ('75', '8', '8.6', '6.3', '2', '2015-06-09 22:09:45');
INSERT INTO `runtime_device` VALUES ('76', '9', '7.4', '14.1', '15.4', '2015-06-09 22:09:45');
INSERT INTO `runtime_device` VALUES ('77', '1', '9.1', '30.6', '5.3', '2015-06-10 08:27:45');
INSERT INTO `runtime_device` VALUES ('78', '2', '4.2', '10.4', '5.1', '2015-06-10 08:27:45');
INSERT INTO `runtime_device` VALUES ('79', '3', '18.1', '21.8', '9.2', '2015-06-10 08:27:45');
INSERT INTO `runtime_device` VALUES ('80', '4', '5', '5.7', '7.8', '2015-06-10 08:27:45');
INSERT INTO `runtime_device` VALUES ('81', '5', '12.1', '5.4', '16', '2015-06-10 08:27:45');
INSERT INTO `runtime_device` VALUES ('82', '6', '6.3', '3', '6.2', '2015-06-10 08:27:45');
INSERT INTO `runtime_device` VALUES ('83', '7', '5.4', '14.1', '13', '2015-06-10 08:27:45');
INSERT INTO `runtime_device` VALUES ('84', '8', '11.4', '14.3', '8.7', '2015-06-10 08:27:45');
INSERT INTO `runtime_device` VALUES ('85', '9', '15.8', '32.5', '11.8', '2015-06-10 08:27:45');
INSERT INTO `runtime_device` VALUES ('86', '10', '8.4', '3.1', '12.3', '2015-06-10 08:27:45');
INSERT INTO `runtime_device` VALUES ('87', '1', '6.1', '17.8', '4.4', '2015-06-10 09:05:17');
INSERT INTO `runtime_device` VALUES ('88', '2', '5.8', '19.3', '6.1', '2015-06-10 09:05:17');
INSERT INTO `runtime_device` VALUES ('89', '3', '27.3', '19.8', '13.1', '2015-06-10 09:05:17');
INSERT INTO `runtime_device` VALUES ('90', '4', '5.7', '5.4', '7.1', '2015-06-10 09:05:17');
INSERT INTO `runtime_device` VALUES ('91', '5', '23.6', '14.1', '5', '2015-06-10 09:05:17');
INSERT INTO `runtime_device` VALUES ('92', '6', '6.5', '4.5', '6', '2015-06-10 09:05:17');
INSERT INTO `runtime_device` VALUES ('93', '7', '12.2', '9.2', '5.7', '2015-06-10 09:05:17');
INSERT INTO `runtime_device` VALUES ('94', '8', '10.3', '3.4', '12.7', '2015-06-10 09:05:17');
INSERT INTO `runtime_device` VALUES ('95', '9', '6.6', '57.5', '6.3', '2015-06-10 09:05:17');
INSERT INTO `runtime_device` VALUES ('96', '10', '3.7', '9.5', '3.5', '2015-06-10 09:05:17');
INSERT INTO `runtime_device` VALUES ('97', '1', '7.7', '38.5', '2.2', '2015-06-10 09:05:47');
INSERT INTO `runtime_device` VALUES ('98', '2', '5.2', '19.1', '20.9', '2015-06-10 09:05:47');
INSERT INTO `runtime_device` VALUES ('99', '3', '24', '24.3', '5.9', '2015-06-10 09:05:47');
INSERT INTO `runtime_device` VALUES ('100', '4', '8.5', '3.8', '10.9', '2015-06-10 09:05:47');
INSERT INTO `runtime_device` VALUES ('101', '5', '12.4', '14.4', '15.7', '2015-06-10 09:05:47');
INSERT INTO `runtime_device` VALUES ('102', '6', '5.4', '4.3', '4.1', '2015-06-10 09:05:47');
INSERT INTO `runtime_device` VALUES ('103', '7', '13.5', '12.1', '2.9', '2015-06-10 09:05:47');
INSERT INTO `runtime_device` VALUES ('104', '8', '11.6', '3.7', '3.2', '2015-06-10 09:05:47');
INSERT INTO `runtime_device` VALUES ('105', '9', '13.5', '17.8', '13.3', '2015-06-10 09:05:47');
INSERT INTO `runtime_device` VALUES ('106', '10', '11.7', '2.2', '6.6', '2015-06-10 09:05:47');
INSERT INTO `runtime_device` VALUES ('107', '1', '4.5', '41', '1.3', '2015-06-10 09:06:17');
INSERT INTO `runtime_device` VALUES ('108', '2', '5.1', '17.4', '28.6', '2015-06-10 09:06:17');
INSERT INTO `runtime_device` VALUES ('109', '4', '8.9', '3.9', '12.9', '2015-06-10 09:06:17');
INSERT INTO `runtime_device` VALUES ('110', '5', '10.2', '8.8', '13', '2015-06-10 09:06:17');
INSERT INTO `runtime_device` VALUES ('111', '6', '6.3', '2.8', '3.2', '2015-06-10 09:06:17');
INSERT INTO `runtime_device` VALUES ('112', '7', '10.4', '8.9', '16.7', '2015-06-10 09:06:17');
INSERT INTO `runtime_device` VALUES ('113', '8', '8.7', '8.4', '14.1', '2015-06-10 09:06:17');
INSERT INTO `runtime_device` VALUES ('114', '9', '7.5', '48.2', '7', '2015-06-10 09:06:17');
INSERT INTO `runtime_device` VALUES ('115', '10', '11.1', '2.7', '6', '2015-06-10 09:06:17');
INSERT INTO `runtime_device` VALUES ('116', '11', '9.8', '9.3', '6.3', '2015-06-10 09:06:17');
INSERT INTO `runtime_device` VALUES ('117', '1', '2.1', '6.9', '8.2', '2015-06-10 09:06:47');
INSERT INTO `runtime_device` VALUES ('118', '2', '4.6', '5.9', '7.8', '2015-06-10 09:06:47');
INSERT INTO `runtime_device` VALUES ('119', '4', '3.1', '3.5', '6', '2015-06-10 09:06:47');
INSERT INTO `runtime_device` VALUES ('120', '5', '9.7', '8.7', '11.8', '2015-06-10 09:06:47');
INSERT INTO `runtime_device` VALUES ('121', '6', '9.1', '5.4', '3.1', '2015-06-10 09:06:47');
INSERT INTO `runtime_device` VALUES ('122', '7', '10', '10.3', '6.1', '2015-06-10 09:06:47');
INSERT INTO `runtime_device` VALUES ('123', '8', '6.2', '4.6', '6.9', '2015-06-10 09:06:47');
INSERT INTO `runtime_device` VALUES ('124', '9', '12.4', '12.5', '10.3', '2015-06-10 09:06:47');
INSERT INTO `runtime_device` VALUES ('125', '10', '6.6', '5.4', '12.3', '2015-06-10 09:06:47');
INSERT INTO `runtime_device` VALUES ('126', '11', '1.7', '19.6', '11.1', '2015-06-10 09:06:47');
INSERT INTO `runtime_device` VALUES ('127', '1', '9.1', '34.2', '5.1', '2015-06-10 09:07:17');
INSERT INTO `runtime_device` VALUES ('128', '2', '6.6', '16.5', '4.4', '2015-06-10 09:07:17');
INSERT INTO `runtime_device` VALUES ('129', '4', '6.9', '4.3', '8.9', '2015-06-10 09:07:17');
INSERT INTO `runtime_device` VALUES ('130', '5', '8.8', '5.4', '12.8', '2015-06-10 09:07:17');
INSERT INTO `runtime_device` VALUES ('131', '7', '9.5', '8.9', '10.4', '2015-06-10 09:07:17');
INSERT INTO `runtime_device` VALUES ('132', '8', '11', '15.3', '14', '2015-06-10 09:07:17');
INSERT INTO `runtime_device` VALUES ('133', '9', '13.7', '31.1', '12.3', '2015-06-10 09:07:17');
INSERT INTO `runtime_device` VALUES ('134', '10', '13.5', '2.5', '8', '2015-06-10 09:07:17');
INSERT INTO `runtime_device` VALUES ('135', '11', '4.2', '12', '3.3', '2015-06-10 09:07:17');
INSERT INTO `runtime_device` VALUES ('136', '1', '5.9', '44.4', '9.1', '2015-06-10 09:07:47');
INSERT INTO `runtime_device` VALUES ('137', '2', '4.8', '19.7', '13.2', '2015-06-10 09:07:47');
INSERT INTO `runtime_device` VALUES ('138', '4', '6.6', '5.1', '11.6', '2015-06-10 09:07:47');
INSERT INTO `runtime_device` VALUES ('139', '5', '11', '13.2', '9.7', '2015-06-10 09:07:47');
INSERT INTO `runtime_device` VALUES ('140', '7', '9.6', '14.2', '10.2', '2015-06-10 09:07:47');
INSERT INTO `runtime_device` VALUES ('141', '8', '9', '11.8', '7', '2015-06-10 09:07:47');
INSERT INTO `runtime_device` VALUES ('142', '9', '6.7', '51.6', '9.6', '2015-06-10 09:07:47');
INSERT INTO `runtime_device` VALUES ('143', '10', '8.3', '11.6', '4.5', '2015-06-10 09:07:47');
INSERT INTO `runtime_device` VALUES ('144', '11', '5.1', '16', '10', '2015-06-10 09:07:47');
INSERT INTO `runtime_device` VALUES ('145', '1', '3', '31.5', '2.1', '2015-06-10 09:08:17');
INSERT INTO `runtime_device` VALUES ('146', '2', '4.6', '5', '7.2', '2015-06-10 09:08:17');
INSERT INTO `runtime_device` VALUES ('147', '4', '9', '4.9', '7.5', '2015-06-10 09:08:17');
INSERT INTO `runtime_device` VALUES ('148', '5', '24.2', '7.8', '5.5', '2015-06-10 09:08:17');
INSERT INTO `runtime_device` VALUES ('149', '7', '9.8', '9.3', '6.3', '2015-06-10 09:08:17');
INSERT INTO `runtime_device` VALUES ('150', '8', '13', '11', '10.2', '2015-06-10 09:08:17');
INSERT INTO `runtime_device` VALUES ('151', '9', '11.7', '32.3', '6', '2015-06-10 09:08:17');
INSERT INTO `runtime_device` VALUES ('152', '10', '4.1', '4.2', '7.7', '2015-06-10 09:08:17');
INSERT INTO `runtime_device` VALUES ('153', '11', '7.6', '17.3', '11', '2015-06-10 09:08:17');
INSERT INTO `runtime_device` VALUES ('154', '1', '6.9', '30.4', '3.9', '2015-06-10 09:08:47');
INSERT INTO `runtime_device` VALUES ('155', '5', '20.9', '10.1', '5.4', '2015-06-10 09:08:47');
INSERT INTO `runtime_device` VALUES ('156', '7', '10.1', '4.4', '15.5', '2015-06-10 09:08:47');
INSERT INTO `runtime_device` VALUES ('157', '8', '6', '9.8', '7', '2015-06-10 09:08:47');
INSERT INTO `runtime_device` VALUES ('158', '9', '8.7', '36.7', '10.1', '2015-06-10 09:08:47');
INSERT INTO `runtime_device` VALUES ('159', '10', '18.3', '8.4', '9.7', '2015-06-10 09:08:47');
INSERT INTO `runtime_device` VALUES ('160', '11', '7', '13.1', '8.5', '2015-06-10 09:08:47');
INSERT INTO `runtime_device` VALUES ('161', '1', '9.5', '26', '6.4', '2015-06-10 09:09:17');
INSERT INTO `runtime_device` VALUES ('162', '5', '21.2', '8.9', '4.3', '2015-06-10 09:09:17');
INSERT INTO `runtime_device` VALUES ('163', '7', '10.6', '13.2', '16.1', '2015-06-10 09:09:17');
INSERT INTO `runtime_device` VALUES ('164', '8', '12.9', '5.4', '8.7', '2015-06-10 09:09:17');
INSERT INTO `runtime_device` VALUES ('165', '9', '9.3', '49.6', '12', '2015-06-10 09:09:17');
INSERT INTO `runtime_device` VALUES ('166', '10', '24.3', '3.5', '6.9', '2015-06-10 09:09:17');
INSERT INTO `runtime_device` VALUES ('167', '11', '9.8', '2', '3.3', '2015-06-10 09:09:17');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_param
-- ----------------------------
INSERT INTO `system_param` VALUES ('1', 'orderSequence', '2', '1', '2015-06-10 09:20:45');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(45) DEFAULT NULL,
  `PASSWD` varchar(45) DEFAULT NULL,
  `REAL_NAME` varchar(45) DEFAULT NULL,
  `MOBILE_PHONE` varchar(45) DEFAULT NULL,
  `USER_TYPE` smallint(1) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `MODIFY_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'linjian', 'e10adc3949ba59abbe56e057f20f883e', '林健', '13102116528', '1', '2015-06-09 21:56:32', '2015-06-09 21:56:41');
INSERT INTO `user` VALUES ('2', 'cw1', 'e10adc3949ba59abbe56e057f20f883e', '财务1', '13102116528', '2', '2015-06-09 22:27:36', '2015-06-09 22:27:36');
INSERT INTO `user` VALUES ('3', 'cw2', 'e10adc3949ba59abbe56e057f20f883e', '财务2', '13102165245', '2', '2015-06-09 22:27:35', '2015-06-09 22:27:35');
INSERT INTO `user` VALUES ('4', 'zyj', 'e10adc3949ba59abbe56e057f20f883e', '赵一建', '13123231232', '2', '2015-06-09 22:27:34', '2015-06-09 22:27:34');
INSERT INTO `user` VALUES ('5', 'hxb', 'e10adc3949ba59abbe56e057f20f883e', '韩旭斌', '13165254612', '2', '2015-06-09 22:27:32', '2015-06-09 22:27:32');
INSERT INTO `user` VALUES ('6', 'ldq', 'e10adc3949ba59abbe56e057f20f883e', '刘德强', '13122312312', '2', '2015-06-09 22:27:21', '2015-06-09 22:27:21');
INSERT INTO `user` VALUES ('8', 'ceshi2', 'e10adc3949ba59abbe56e057f20f883e', '测试', '13102116528', '2', '2015-06-10 09:02:36', '2015-06-10 09:02:36');
