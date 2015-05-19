/*
Navicat MySQL Data Transfer

Source Server         : bendi
Source Server Version : 50618
Source Host           : localhost:3306
Source Database       : gms

Target Server Type    : MYSQL
Target Server Version : 50618
File Encoding         : 65001

Date: 2015-05-11 21:44:11
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('1', '设备1', '0.8', '1', '10', '9.1', '6', '25', '34', '4', '34', '0');
INSERT INTO `device` VALUES ('2', '设备2', '2.7', '1', '7', '10', '7', '14', '6.1', '3', '8', '0');
INSERT INTO `device` VALUES ('3', '设备3', '14.8', '1', '22', '13.9', '5', '34', '19.8', '3', '22', '0');
INSERT INTO `device` VALUES ('14', '设备4', '4.4', '2', '23', '25.1', '4', '30', '7.2', '4', '15', '0');
INSERT INTO `device` VALUES ('15', '设备5', '7.4', '2', '9', '1.8', '2', '6', '2.9', '2', '7', '0');
INSERT INTO `device` VALUES ('16', '设备6', '16', '11', '22', '6.5', '1', '23', '10.8', '1', '23', '0');
INSERT INTO `device` VALUES ('17', '设备7', '24.5', '10', '30', '6.6', '2', '10', '7.5', '2', '15', '0');
INSERT INTO `device` VALUES ('18', '三星手机', '6.2', '2', '13', '12', '2', '12', '4.4', '3', '18', '1');
INSERT INTO `device` VALUES ('19', '小米手机', '11.9', '2', '12', '21.9', '12', '32', '19.7', '11', '22', '0');
INSERT INTO `device` VALUES ('20', '华为手机', '11.7', '3', '13', '17.6', '2', '33', '122.5', '33', '234', '0');
INSERT INTO `device` VALUES ('21', '李晓的大脑', '5.4', '1', '12', '8.8', '1', '12', '27.1', '1', '34', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of environmental_param
-- ----------------------------
INSERT INTO `environmental_param` VALUES ('1', '2.5', '2.7', '8.4', '2015-05-09 17:48:30');
INSERT INTO `environmental_param` VALUES ('2', '11.7', '9.5', '1.2', '2015-05-09 17:48:12');
INSERT INTO `environmental_param` VALUES ('3', '2.9', '0.7', '2.5', '2015-05-09 17:48:41');
INSERT INTO `environmental_param` VALUES ('4', '4.5', '0.1', '7.9', '2015-05-09 17:49:11');
INSERT INTO `environmental_param` VALUES ('5', '14.2', '8.2', '4.1', '2015-05-09 17:49:41');
INSERT INTO `environmental_param` VALUES ('6', '0.2', '0.1', '17.5', '2015-05-09 17:50:11');
INSERT INTO `environmental_param` VALUES ('7', '1', '2.2', '13.3', '2015-05-09 17:50:41');
INSERT INTO `environmental_param` VALUES ('8', '6.8', '1.7', '7.5', '2015-05-09 17:51:11');
INSERT INTO `environmental_param` VALUES ('9', '10.6', '16.1', '2.2', '2015-05-10 13:43:56');
INSERT INTO `environmental_param` VALUES ('10', '2.4', '8.9', '0.5', '2015-05-10 13:44:26');
INSERT INTO `environmental_param` VALUES ('11', '7.7', '2.9', '0.7', '2015-05-10 13:44:56');
INSERT INTO `environmental_param` VALUES ('12', '12.7', '1.1', '4.2', '2015-05-10 13:45:26');
INSERT INTO `environmental_param` VALUES ('13', '12.9', '6.8', '8.4', '2015-05-10 13:45:56');
INSERT INTO `environmental_param` VALUES ('14', '8.9', '12.9', '6.5', '2015-05-10 21:02:40');
INSERT INTO `environmental_param` VALUES ('15', '9.4', '5.2', '3.5', '2015-05-10 21:03:11');
INSERT INTO `environmental_param` VALUES ('16', '2.9', '1.2', '5.4', '2015-05-10 21:03:41');
INSERT INTO `environmental_param` VALUES ('17', '1.2', '5.4', '10.3', '2015-05-10 21:58:35');
INSERT INTO `environmental_param` VALUES ('18', '17.2', '9.8', '6.3', '2015-05-10 21:59:05');
INSERT INTO `environmental_param` VALUES ('19', '0.1', '12.7', '0', '2015-05-10 21:59:35');
INSERT INTO `environmental_param` VALUES ('20', '9.9', '3.1', '0.4', '2015-05-11 14:53:05');
INSERT INTO `environmental_param` VALUES ('21', '8.3', '4.2', '4.3', '2015-05-11 14:53:35');
INSERT INTO `environmental_param` VALUES ('22', '7.5', '8.9', '7.6', '2015-05-11 14:54:05');
INSERT INTO `environmental_param` VALUES ('23', '3.4', '2.3', '2.7', '2015-05-11 14:54:35');
INSERT INTO `environmental_param` VALUES ('24', '0.1', '11.9', '6.5', '2015-05-11 15:08:56');
INSERT INTO `environmental_param` VALUES ('25', '0.1', '3.6', '3.2', '2015-05-11 15:09:10');
INSERT INTO `environmental_param` VALUES ('26', '0.5', '4.2', '5.1', '2015-05-11 15:19:06');
INSERT INTO `environmental_param` VALUES ('27', '18.4', '1.2', '4.3', '2015-05-11 15:19:06');
INSERT INTO `environmental_param` VALUES ('28', '6.8', '15.7', '3.3', '2015-05-11 15:19:37');
INSERT INTO `environmental_param` VALUES ('29', '1.9', '9.3', '6.6', '2015-05-11 15:21:14');
INSERT INTO `environmental_param` VALUES ('30', '7.2', '0.1', '0.4', '2015-05-11 15:21:55');
INSERT INTO `environmental_param` VALUES ('31', '7.6', '5.7', '10.7', '2015-05-11 15:22:24');
INSERT INTO `environmental_param` VALUES ('32', '3.7', '1.6', '12', '2015-05-11 16:06:00');
INSERT INTO `environmental_param` VALUES ('33', '4.5', '1', '6.6', '2015-05-11 16:06:30');
INSERT INTO `environmental_param` VALUES ('34', '5', '3.8', '14.9', '2015-05-11 16:07:00');
INSERT INTO `environmental_param` VALUES ('35', '12.6', '4.3', '7', '2015-05-11 16:13:15');
INSERT INTO `environmental_param` VALUES ('36', '5.1', '15.7', '6.3', '2015-05-11 16:13:44');
INSERT INTO `environmental_param` VALUES ('37', '4', '10.5', '6.3', '2015-05-11 16:14:15');
INSERT INTO `environmental_param` VALUES ('38', '8.1', '12.5', '6.4', '2015-05-11 16:15:16');
INSERT INTO `environmental_param` VALUES ('39', '1.5', '7', '3', '2015-05-11 16:15:46');
INSERT INTO `environmental_param` VALUES ('40', '10.4', '8.5', '13.7', '2015-05-11 16:16:16');

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
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repaired_record
-- ----------------------------
INSERT INTO `repaired_record` VALUES ('1', '2', '普通用户', '2015-05-10 10:52:46');
INSERT INTO `repaired_record` VALUES ('2', '15', '普通用户', '2015-05-10 13:45:46');
INSERT INTO `repaired_record` VALUES ('3', '17', '林健', '2015-05-10 14:43:09');
INSERT INTO `repaired_record` VALUES ('4', '14', '林健', '2015-05-10 21:03:02');
INSERT INTO `repaired_record` VALUES ('5', '3', '林健', '2015-05-10 21:03:07');
INSERT INTO `repaired_record` VALUES ('6', '16', '林健', '2015-05-10 21:03:14');
INSERT INTO `repaired_record` VALUES ('7', '18', '林健', '2015-05-10 21:03:18');
INSERT INTO `repaired_record` VALUES ('8', '19', '林健', '2015-05-10 21:03:23');
INSERT INTO `repaired_record` VALUES ('9', '20', '林健', '2015-05-10 21:03:28');
INSERT INTO `repaired_record` VALUES ('10', '2', '林健', '2015-05-10 21:45:50');
INSERT INTO `repaired_record` VALUES ('11', '15', '林健', '2015-05-10 21:45:55');
INSERT INTO `repaired_record` VALUES ('12', '15', '林健', '2015-05-10 21:53:37');
INSERT INTO `repaired_record` VALUES ('13', '3', '林健', '2015-05-10 21:58:54');
INSERT INTO `repaired_record` VALUES ('14', '14', '林健', '2015-05-11 15:07:40');
INSERT INTO `repaired_record` VALUES ('15', '19', '林健', '2015-05-11 15:23:39');
INSERT INTO `repaired_record` VALUES ('16', '2', '林健', '2015-05-11 15:23:48');
INSERT INTO `repaired_record` VALUES ('17', '14', '林健', '2015-05-11 15:23:58');
INSERT INTO `repaired_record` VALUES ('18', '17', '林健', '2015-05-11 15:24:11');
INSERT INTO `repaired_record` VALUES ('19', '15', '林健', '2015-05-11 16:16:03');
INSERT INTO `repaired_record` VALUES ('20', '15', '林健', '2015-05-11 18:15:38');
INSERT INTO `repaired_record` VALUES ('21', '1', '林健', '2015-05-11 21:19:14');

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
) ENGINE=InnoDB AUTO_INCREMENT=845 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of runtime_device
-- ----------------------------
INSERT INTO `runtime_device` VALUES ('1', '1', '-1.3', '23.1', '30.3', '2015-05-08 20:45:27');
INSERT INTO `runtime_device` VALUES ('2', '2', '2.1', '8.3', '5.3', '2015-05-08 20:46:00');
INSERT INTO `runtime_device` VALUES ('3', '3', '21.5', '19.3', '22', '2015-05-08 20:46:00');
INSERT INTO `runtime_device` VALUES ('4', '1', '7', '14.7', '26.4', '2015-05-08 20:47:21');
INSERT INTO `runtime_device` VALUES ('5', '2', '7.9', '9.8', '2.6', '2015-05-08 20:47:22');
INSERT INTO `runtime_device` VALUES ('6', '3', '19.7', '18.3', '15.3', '2015-05-08 20:47:22');
INSERT INTO `runtime_device` VALUES ('7', '1', '3.7', '13.5', '28.2', '2015-05-08 20:47:51');
INSERT INTO `runtime_device` VALUES ('8', '3', '19.2', '26.4', '11.4', '2015-05-08 20:47:51');
INSERT INTO `runtime_device` VALUES ('9', '1', '9.6', '21.9', '23.7', '2015-05-08 20:48:21');
INSERT INTO `runtime_device` VALUES ('10', '1', '9.1', '20.6', '24.1', '2015-05-08 20:48:51');
INSERT INTO `runtime_device` VALUES ('11', '14', '19.5', '28.8', '12.7', '2015-05-08 20:48:51');
INSERT INTO `runtime_device` VALUES ('12', '1', '7', '6.1', '24', '2015-05-08 20:49:21');
INSERT INTO `runtime_device` VALUES ('13', '14', '12.4', '27.2', '9.2', '2015-05-08 20:49:21');
INSERT INTO `runtime_device` VALUES ('14', '1', '7.6', '17.1', '31.7', '2015-05-08 20:49:51');
INSERT INTO `runtime_device` VALUES ('15', '1', '3.7', '25.3', '22.4', '2015-05-08 20:51:52');
INSERT INTO `runtime_device` VALUES ('16', '1', '7.1', '9.1', '30.3', '2015-05-08 20:52:22');
INSERT INTO `runtime_device` VALUES ('17', '2', '5.8', '14.8', '3.2', '2015-05-08 20:52:22');
INSERT INTO `runtime_device` VALUES ('18', '3', '18.3', '26.1', '11.3', '2015-05-08 20:52:22');
INSERT INTO `runtime_device` VALUES ('19', '14', '12.2', '13.1', '14.6', '2015-05-08 20:52:22');
INSERT INTO `runtime_device` VALUES ('20', '1', '6.5', '15.5', '30.3', '2015-05-08 20:52:52');
INSERT INTO `runtime_device` VALUES ('21', '1', '10.3', '20.7', '25.7', '2015-05-08 20:53:22');
INSERT INTO `runtime_device` VALUES ('22', '1', '0.3', '16.3', '24', '2015-05-08 20:54:13');
INSERT INTO `runtime_device` VALUES ('23', '2', '0.2', '13.8', '8.1', '2015-05-08 20:54:13');
INSERT INTO `runtime_device` VALUES ('24', '3', '22.9', '27.6', '15.8', '2015-05-08 20:54:13');
INSERT INTO `runtime_device` VALUES ('25', '14', '14.5', '4.2', '13', '2015-05-08 20:54:13');
INSERT INTO `runtime_device` VALUES ('26', '1', '7', '13.8', '6.5', '2015-05-08 21:16:13');
INSERT INTO `runtime_device` VALUES ('27', '2', '7.9', '6.9', '7.6', '2015-05-08 21:16:13');
INSERT INTO `runtime_device` VALUES ('28', '3', '14.6', '15.9', '1.2', '2015-05-08 21:16:13');
INSERT INTO `runtime_device` VALUES ('29', '14', '0.5', '12.9', '6.5', '2015-05-08 21:16:13');
INSERT INTO `runtime_device` VALUES ('30', '1', '0.9', '11.7', '35.8', '2015-05-08 21:16:42');
INSERT INTO `runtime_device` VALUES ('31', '2', '8.9', '15.4', '7.9', '2015-05-08 21:16:43');
INSERT INTO `runtime_device` VALUES ('32', '1', '2.3', '4.4', '22.2', '2015-05-08 21:19:03');
INSERT INTO `runtime_device` VALUES ('33', '2', '6.3', '13.4', '7.2', '2015-05-08 21:19:03');
INSERT INTO `runtime_device` VALUES ('34', '3', '10.4', '3.3', '12.7', '2015-05-08 21:19:03');
INSERT INTO `runtime_device` VALUES ('35', '14', '18.7', '24.6', '11.9', '2015-05-08 21:19:03');
INSERT INTO `runtime_device` VALUES ('36', '2', '-0.4', '10.5', '7', '2015-05-08 21:19:12');
INSERT INTO `runtime_device` VALUES ('37', '14', '2.8', '23.7', '8.9', '2015-05-08 21:19:12');
INSERT INTO `runtime_device` VALUES ('38', '14', '19', '23', '3.7', '2015-05-08 21:19:32');
INSERT INTO `runtime_device` VALUES ('39', '14', '13.3', '26.8', '16', '2015-05-08 21:19:42');
INSERT INTO `runtime_device` VALUES ('40', '1', '2.1', '13.4', '31.5', '2015-05-08 21:21:42');
INSERT INTO `runtime_device` VALUES ('41', '2', '-0.1', '12.6', '1.8', '2015-05-08 21:21:43');
INSERT INTO `runtime_device` VALUES ('42', '3', '11.6', '27.9', '7.8', '2015-05-08 21:21:43');
INSERT INTO `runtime_device` VALUES ('43', '14', '12.8', '2.8', '9.7', '2015-05-08 21:21:43');
INSERT INTO `runtime_device` VALUES ('44', '1', '8.5', '6.8', '33.6', '2015-05-08 21:22:08');
INSERT INTO `runtime_device` VALUES ('45', '3', '3.4', '32.9', '22', '2015-05-08 21:22:08');
INSERT INTO `runtime_device` VALUES ('46', '1', '5.7', '7.5', '12.2', '2015-05-08 21:22:12');
INSERT INTO `runtime_device` VALUES ('47', '3', '6.6', '24', '22.5', '2015-05-08 21:22:13');
INSERT INTO `runtime_device` VALUES ('48', '1', '4.9', '21.6', '14.1', '2015-05-08 21:22:37');
INSERT INTO `runtime_device` VALUES ('49', '3', '18.5', '17.7', '16.3', '2015-05-08 21:22:38');
INSERT INTO `runtime_device` VALUES ('50', '1', '11.6', '6.2', '31.1', '2015-05-08 21:22:42');
INSERT INTO `runtime_device` VALUES ('51', '3', '15.8', '26.8', '14.5', '2015-05-08 21:22:43');
INSERT INTO `runtime_device` VALUES ('52', '1', '2.4', '8.1', '34.1', '2015-05-08 21:23:12');
INSERT INTO `runtime_device` VALUES ('53', '2', '1.1', '14.5', '3.3', '2015-05-08 21:23:13');
INSERT INTO `runtime_device` VALUES ('54', '3', '5.3', '7.5', '7.4', '2015-05-08 21:23:13');
INSERT INTO `runtime_device` VALUES ('55', '14', '4.1', '16.1', '10', '2015-05-08 21:23:13');
INSERT INTO `runtime_device` VALUES ('56', '1', '0.1', '7.4', '23.6', '2015-05-08 21:23:14');
INSERT INTO `runtime_device` VALUES ('57', '2', '4.3', '14.3', '4', '2015-05-08 21:23:14');
INSERT INTO `runtime_device` VALUES ('58', '3', '10.1', '6.3', '14', '2015-05-08 21:23:14');
INSERT INTO `runtime_device` VALUES ('59', '14', '18.2', '13', '11.3', '2015-05-08 21:23:14');
INSERT INTO `runtime_device` VALUES ('60', '1', '3.9', '23.2', '3.5', '2015-05-08 21:23:42');
INSERT INTO `runtime_device` VALUES ('61', '2', '3.8', '5.5', '2.4', '2015-05-08 21:23:43');
INSERT INTO `runtime_device` VALUES ('62', '3', '12.1', '13.7', '3.4', '2015-05-08 21:23:43');
INSERT INTO `runtime_device` VALUES ('63', '14', '10.5', '7.1', '5.5', '2015-05-08 21:23:43');
INSERT INTO `runtime_device` VALUES ('64', '1', '2.2', '12.9', '10.3', '2015-05-08 21:23:43');
INSERT INTO `runtime_device` VALUES ('65', '3', '0.7', '21', '19.6', '2015-05-08 21:23:43');
INSERT INTO `runtime_device` VALUES ('66', '14', '10.3', '5.7', '7', '2015-05-08 21:23:43');
INSERT INTO `runtime_device` VALUES ('67', '1', '7.6', '23.8', '32.6', '2015-05-08 21:24:12');
INSERT INTO `runtime_device` VALUES ('68', '3', '21.8', '17.5', '11.9', '2015-05-08 21:24:13');
INSERT INTO `runtime_device` VALUES ('69', '14', '12.7', '9.9', '2.9', '2015-05-08 21:24:13');
INSERT INTO `runtime_device` VALUES ('70', '1', '10.3', '12.5', '14.6', '2015-05-08 21:24:13');
INSERT INTO `runtime_device` VALUES ('71', '3', '2.2', '7.1', '13', '2015-05-08 21:24:13');
INSERT INTO `runtime_device` VALUES ('72', '1', '2.6', '14.2', '23', '2015-05-08 21:24:42');
INSERT INTO `runtime_device` VALUES ('73', '3', '19.8', '11.3', '15', '2015-05-08 21:24:43');
INSERT INTO `runtime_device` VALUES ('74', '1', '-0.7', '14.9', '27.4', '2015-05-08 21:25:12');
INSERT INTO `runtime_device` VALUES ('75', '3', '3.7', '24.4', '23.8', '2015-05-08 21:25:13');
INSERT INTO `runtime_device` VALUES ('76', '15', '2.1', '0.9', '8.3', '2015-05-08 21:27:12');
INSERT INTO `runtime_device` VALUES ('77', '1', '3.5', '11.5', '33', '2015-05-08 21:28:21');
INSERT INTO `runtime_device` VALUES ('78', '1', '-0.3', '11.9', '19.6', '2015-05-08 21:28:42');
INSERT INTO `runtime_device` VALUES ('79', '2', '5.2', '10.6', '1.3', '2015-05-08 21:28:42');
INSERT INTO `runtime_device` VALUES ('80', '3', '12.6', '12.2', '21.4', '2015-05-08 21:28:43');
INSERT INTO `runtime_device` VALUES ('81', '14', '16.8', '27.9', '14.3', '2015-05-08 21:28:43');
INSERT INTO `runtime_device` VALUES ('82', '15', '6.3', '3', '5.2', '2015-05-08 21:28:43');
INSERT INTO `runtime_device` VALUES ('83', '3', '11.5', '13.3', '17.4', '2015-05-08 21:28:51');
INSERT INTO `runtime_device` VALUES ('84', '14', '17.2', '7.6', '12.5', '2015-05-08 21:28:51');
INSERT INTO `runtime_device` VALUES ('85', '15', '2.7', '4.7', '1.3', '2015-05-08 21:28:51');
INSERT INTO `runtime_device` VALUES ('86', '3', '8.1', '26.1', '11.9', '2015-05-08 21:29:12');
INSERT INTO `runtime_device` VALUES ('87', '14', '15.7', '10', '15.5', '2015-05-08 21:29:12');
INSERT INTO `runtime_device` VALUES ('88', '15', '8.9', '1.1', '4.3', '2015-05-08 21:29:13');
INSERT INTO `runtime_device` VALUES ('89', '3', '6.5', '16.7', '12.5', '2015-05-08 21:29:21');
INSERT INTO `runtime_device` VALUES ('90', '14', '20.1', '22.7', '10.9', '2015-05-08 21:29:21');
INSERT INTO `runtime_device` VALUES ('91', '15', '2.4', '4.2', '6.1', '2015-05-08 21:29:21');
INSERT INTO `runtime_device` VALUES ('92', '3', '-0.3', '30.4', '7.9', '2015-05-08 21:29:42');
INSERT INTO `runtime_device` VALUES ('93', '14', '6.7', '3.1', '15.3', '2015-05-08 21:29:43');
INSERT INTO `runtime_device` VALUES ('94', '15', '8.5', '0.6', '5.5', '2015-05-08 21:29:43');
INSERT INTO `runtime_device` VALUES ('95', '14', '19.9', '10.1', '14.3', '2015-05-08 21:29:51');
INSERT INTO `runtime_device` VALUES ('96', '14', '18.8', '30.4', '2.8', '2015-05-08 21:30:12');
INSERT INTO `runtime_device` VALUES ('97', '1', '7.5', '13.1', '16.3', '2015-05-08 21:37:43');
INSERT INTO `runtime_device` VALUES ('98', '2', '3.3', '7.5', '5.1', '2015-05-08 21:37:43');
INSERT INTO `runtime_device` VALUES ('99', '3', '13.1', '19.9', '13.9', '2015-05-08 21:37:43');
INSERT INTO `runtime_device` VALUES ('100', '14', '23.6', '10.3', '6.5', '2015-05-08 21:37:43');
INSERT INTO `runtime_device` VALUES ('101', '15', '4.4', '5.2', '8.8', '2015-05-08 21:37:43');
INSERT INTO `runtime_device` VALUES ('102', '1', '3.2', '25.4', '4.9', '2015-05-08 21:38:04');
INSERT INTO `runtime_device` VALUES ('103', '2', '3.9', '11.2', '8.2', '2015-05-08 21:38:04');
INSERT INTO `runtime_device` VALUES ('104', '3', '2.8', '15.6', '6.9', '2015-05-08 21:38:04');
INSERT INTO `runtime_device` VALUES ('105', '14', '20.3', '10', '7.7', '2015-05-08 21:38:04');
INSERT INTO `runtime_device` VALUES ('106', '15', '7.5', '4.2', '3.7', '2015-05-08 21:38:04');
INSERT INTO `runtime_device` VALUES ('107', '1', '3.5', '14.3', '25.5', '2015-05-08 21:38:12');
INSERT INTO `runtime_device` VALUES ('108', '2', '3.3', '11.6', '5.4', '2015-05-08 21:38:12');
INSERT INTO `runtime_device` VALUES ('109', '3', '13.3', '11.4', '1.3', '2015-05-08 21:38:13');
INSERT INTO `runtime_device` VALUES ('110', '14', '15.6', '20.9', '12', '2015-05-08 21:38:13');
INSERT INTO `runtime_device` VALUES ('111', '15', '1.2', '1.8', '6.2', '2015-05-08 21:38:13');
INSERT INTO `runtime_device` VALUES ('112', '1', '0.1', '21.5', '25.9', '2015-05-08 21:38:33');
INSERT INTO `runtime_device` VALUES ('113', '2', '6.3', '6.8', '7.2', '2015-05-08 21:38:33');
INSERT INTO `runtime_device` VALUES ('114', '14', '20.9', '30.7', '5.7', '2015-05-08 21:38:33');
INSERT INTO `runtime_device` VALUES ('115', '15', '4.4', '3.7', '4.4', '2015-05-08 21:38:33');
INSERT INTO `runtime_device` VALUES ('116', '1', '10.1', '9.9', '12.7', '2015-05-08 21:38:43');
INSERT INTO `runtime_device` VALUES ('117', '2', '9', '7.7', '6.4', '2015-05-08 21:38:43');
INSERT INTO `runtime_device` VALUES ('118', '14', '17.2', '8.9', '15.9', '2015-05-08 21:38:43');
INSERT INTO `runtime_device` VALUES ('119', '15', '2.8', '2', '4.7', '2015-05-08 21:38:43');
INSERT INTO `runtime_device` VALUES ('120', '1', '2.2', '5.5', '24.4', '2015-05-08 21:39:03');
INSERT INTO `runtime_device` VALUES ('121', '14', '3.2', '4.5', '4.3', '2015-05-08 21:39:03');
INSERT INTO `runtime_device` VALUES ('122', '15', '8.1', '4.3', '5.7', '2015-05-08 21:39:03');
INSERT INTO `runtime_device` VALUES ('123', '1', '11.4', '4.1', '10.8', '2015-05-08 21:39:12');
INSERT INTO `runtime_device` VALUES ('124', '14', '23.2', '17.3', '9.9', '2015-05-08 21:39:13');
INSERT INTO `runtime_device` VALUES ('125', '15', '5.9', '5.2', '4', '2015-05-08 21:39:13');
INSERT INTO `runtime_device` VALUES ('126', '14', '6', '7.1', '5.5', '2015-05-08 21:39:33');
INSERT INTO `runtime_device` VALUES ('127', '15', '2.3', '3.7', '7.6', '2015-05-08 21:39:33');
INSERT INTO `runtime_device` VALUES ('128', '14', '11.5', '3.1', '13.8', '2015-05-08 21:39:43');
INSERT INTO `runtime_device` VALUES ('129', '15', '6.2', '1.4', '1.6', '2015-05-08 21:39:43');
INSERT INTO `runtime_device` VALUES ('130', '14', '20.2', '14.9', '6', '2015-05-08 21:40:03');
INSERT INTO `runtime_device` VALUES ('131', '15', '5.4', '2.4', '3.7', '2015-05-08 21:40:03');
INSERT INTO `runtime_device` VALUES ('132', '14', '24.9', '24.1', '8.1', '2015-05-08 21:40:12');
INSERT INTO `runtime_device` VALUES ('133', '15', '8.3', '5.1', '0.6', '2015-05-08 21:40:13');
INSERT INTO `runtime_device` VALUES ('134', '1', '9.6', '7.4', '10.9', '2015-05-08 22:06:03');
INSERT INTO `runtime_device` VALUES ('135', '2', '1.2', '9.4', '7.5', '2015-05-08 22:06:03');
INSERT INTO `runtime_device` VALUES ('136', '3', '22.9', '28.3', '12.9', '2015-05-08 22:06:03');
INSERT INTO `runtime_device` VALUES ('137', '14', '7.3', '14', '13.7', '2015-05-08 22:06:03');
INSERT INTO `runtime_device` VALUES ('138', '15', '7.6', '4.8', '4.5', '2015-05-08 22:06:03');
INSERT INTO `runtime_device` VALUES ('139', '1', '0.5', '18.7', '21.6', '2015-05-08 22:06:13');
INSERT INTO `runtime_device` VALUES ('140', '2', '4.1', '5.2', '3.3', '2015-05-08 22:06:13');
INSERT INTO `runtime_device` VALUES ('141', '3', '17', '22.4', '10.2', '2015-05-08 22:06:13');
INSERT INTO `runtime_device` VALUES ('142', '14', '9', '21.9', '8.5', '2015-05-08 22:06:13');
INSERT INTO `runtime_device` VALUES ('143', '15', '7', '7.9', '7.3', '2015-05-08 22:06:13');
INSERT INTO `runtime_device` VALUES ('144', '1', '3.8', '22.8', '31.7', '2015-05-08 22:06:32');
INSERT INTO `runtime_device` VALUES ('145', '3', '7.1', '31.7', '6.4', '2015-05-08 22:06:32');
INSERT INTO `runtime_device` VALUES ('146', '14', '15.7', '29.3', '5.3', '2015-05-08 22:06:32');
INSERT INTO `runtime_device` VALUES ('147', '1', '0.3', '21.2', '4.5', '2015-05-08 22:06:43');
INSERT INTO `runtime_device` VALUES ('148', '3', '5.2', '25', '2.9', '2015-05-08 22:06:43');
INSERT INTO `runtime_device` VALUES ('149', '14', '0.7', '13.9', '16', '2015-05-08 22:06:43');
INSERT INTO `runtime_device` VALUES ('150', '1', '8.3', '19.9', '26.8', '2015-05-08 22:07:01');
INSERT INTO `runtime_device` VALUES ('151', '3', '9.8', '20.4', '17.6', '2015-05-08 22:07:01');
INSERT INTO `runtime_device` VALUES ('152', '1', '10.1', '12.5', '17.7', '2015-05-08 22:07:12');
INSERT INTO `runtime_device` VALUES ('153', '3', '7.1', '31.6', '18.6', '2015-05-08 22:07:13');
INSERT INTO `runtime_device` VALUES ('154', '1', '1.2', '17', '33.3', '2015-05-08 22:07:31');
INSERT INTO `runtime_device` VALUES ('155', '3', '20.6', '4.9', '8.6', '2015-05-08 22:07:32');
INSERT INTO `runtime_device` VALUES ('156', '1', '11.3', '7.2', '7.5', '2015-05-08 22:07:43');
INSERT INTO `runtime_device` VALUES ('157', '3', '21.8', '19.9', '14.6', '2015-05-08 22:07:43');
INSERT INTO `runtime_device` VALUES ('158', '1', '3.8', '10.9', '10.1', '2015-05-08 22:08:01');
INSERT INTO `runtime_device` VALUES ('159', '2', '5.7', '7.9', '3.9', '2015-05-08 22:08:02');
INSERT INTO `runtime_device` VALUES ('160', '3', '2.3', '30.4', '4.3', '2015-05-08 22:08:02');
INSERT INTO `runtime_device` VALUES ('161', '14', '7.7', '10.1', '14.3', '2015-05-08 22:08:02');
INSERT INTO `runtime_device` VALUES ('162', '15', '7', '2', '4.9', '2015-05-08 22:08:02');
INSERT INTO `runtime_device` VALUES ('163', '1', '9.4', '6.9', '17.2', '2015-05-08 22:08:13');
INSERT INTO `runtime_device` VALUES ('164', '2', '5.6', '6.7', '1.9', '2015-05-08 22:08:13');
INSERT INTO `runtime_device` VALUES ('165', '3', '20.7', '24.1', '12.1', '2015-05-08 22:08:13');
INSERT INTO `runtime_device` VALUES ('166', '14', '22.6', '18.3', '2.5', '2015-05-08 22:08:13');
INSERT INTO `runtime_device` VALUES ('167', '15', '3.6', '1.8', '7.6', '2015-05-08 22:08:13');
INSERT INTO `runtime_device` VALUES ('168', '1', '1.7', '22', '17.7', '2015-05-08 22:08:31');
INSERT INTO `runtime_device` VALUES ('169', '3', '7.7', '16', '9.8', '2015-05-08 22:08:31');
INSERT INTO `runtime_device` VALUES ('170', '15', '8.3', '5.6', '5', '2015-05-08 22:08:31');
INSERT INTO `runtime_device` VALUES ('171', '1', '7.1', '19.3', '30.2', '2015-05-08 22:08:43');
INSERT INTO `runtime_device` VALUES ('172', '3', '1.3', '23.8', '9.5', '2015-05-08 22:08:43');
INSERT INTO `runtime_device` VALUES ('173', '15', '4.2', '6.3', '3.8', '2015-05-08 22:08:43');
INSERT INTO `runtime_device` VALUES ('174', '1', '7.4', '18.5', '9.4', '2015-05-08 22:09:01');
INSERT INTO `runtime_device` VALUES ('175', '3', '12.6', '34.4', '3.8', '2015-05-08 22:09:02');
INSERT INTO `runtime_device` VALUES ('176', '15', '1.6', '6.2', '5.3', '2015-05-08 22:09:02');
INSERT INTO `runtime_device` VALUES ('177', '1', '4.9', '7.7', '32.9', '2015-05-08 22:09:12');
INSERT INTO `runtime_device` VALUES ('178', '3', '12.9', '13.5', '14.2', '2015-05-08 22:09:13');
INSERT INTO `runtime_device` VALUES ('179', '15', '5.2', '6.8', '7.5', '2015-05-08 22:09:13');
INSERT INTO `runtime_device` VALUES ('180', '1', '6.5', '22.5', '5.5', '2015-05-08 22:09:31');
INSERT INTO `runtime_device` VALUES ('181', '3', '3.6', '29.9', '6.7', '2015-05-08 22:09:32');
INSERT INTO `runtime_device` VALUES ('182', '15', '7.9', '2.8', '4.7', '2015-05-08 22:09:32');
INSERT INTO `runtime_device` VALUES ('183', '1', '0', '4.4', '26.8', '2015-05-08 22:09:43');
INSERT INTO `runtime_device` VALUES ('184', '3', '13.9', '34.5', '13.5', '2015-05-08 22:09:43');
INSERT INTO `runtime_device` VALUES ('185', '15', '5.6', '1', '1.8', '2015-05-08 22:09:43');
INSERT INTO `runtime_device` VALUES ('186', '3', '17.8', '22.7', '15.6', '2015-05-08 22:10:01');
INSERT INTO `runtime_device` VALUES ('187', '3', '17.3', '30.9', '6.9', '2015-05-08 22:10:13');
INSERT INTO `runtime_device` VALUES ('188', '3', '1.9', '13.1', '5.1', '2015-05-08 22:10:31');
INSERT INTO `runtime_device` VALUES ('189', '3', '0.1', '32.9', '14.9', '2015-05-08 22:10:42');
INSERT INTO `runtime_device` VALUES ('190', '3', '13.7', '26.8', '18', '2015-05-08 22:11:01');
INSERT INTO `runtime_device` VALUES ('191', '3', '0.7', '30.6', '7.5', '2015-05-08 22:11:13');
INSERT INTO `runtime_device` VALUES ('192', '3', '14.5', '21.7', '13.6', '2015-05-08 22:11:32');
INSERT INTO `runtime_device` VALUES ('193', '3', '2.3', '31.7', '7.8', '2015-05-08 22:11:43');
INSERT INTO `runtime_device` VALUES ('194', '3', '9.7', '10.2', '4.2', '2015-05-08 22:12:01');
INSERT INTO `runtime_device` VALUES ('195', '3', '13.4', '10', '23.3', '2015-05-08 22:12:13');
INSERT INTO `runtime_device` VALUES ('196', '1', '4.1', '18', '24.2', '2015-05-09 10:38:41');
INSERT INTO `runtime_device` VALUES ('197', '2', '6.3', '7.9', '6.2', '2015-05-09 10:38:41');
INSERT INTO `runtime_device` VALUES ('198', '3', '16.1', '23.8', '11.8', '2015-05-09 10:38:41');
INSERT INTO `runtime_device` VALUES ('199', '14', '17.9', '14.5', '15.1', '2015-05-09 10:38:41');
INSERT INTO `runtime_device` VALUES ('200', '15', '8.9', '2.7', '4.2', '2015-05-09 10:38:41');
INSERT INTO `runtime_device` VALUES ('201', '1', '3.8', '25.2', '22.3', '2015-05-09 10:39:10');
INSERT INTO `runtime_device` VALUES ('202', '2', '2.8', '9.9', '6.8', '2015-05-09 10:39:10');
INSERT INTO `runtime_device` VALUES ('203', '3', '14.8', '11.2', '11.3', '2015-05-09 10:39:10');
INSERT INTO `runtime_device` VALUES ('204', '14', '15.5', '28.4', '10.7', '2015-05-09 10:39:10');
INSERT INTO `runtime_device` VALUES ('205', '15', '8.3', '3.8', '4.7', '2015-05-09 10:39:10');
INSERT INTO `runtime_device` VALUES ('206', '1', '8.1', '7', '30.9', '2015-05-09 10:39:40');
INSERT INTO `runtime_device` VALUES ('207', '2', '7.3', '11.2', '4.9', '2015-05-09 10:39:40');
INSERT INTO `runtime_device` VALUES ('208', '3', '20.8', '26.3', '19.1', '2015-05-09 10:39:40');
INSERT INTO `runtime_device` VALUES ('209', '14', '15.4', '9.5', '5.2', '2015-05-09 10:39:40');
INSERT INTO `runtime_device` VALUES ('210', '15', '3.7', '5.3', '2.1', '2015-05-09 10:39:40');
INSERT INTO `runtime_device` VALUES ('211', '16', '16.5', '8.7', '9.9', '2015-05-09 10:39:40');
INSERT INTO `runtime_device` VALUES ('212', '1', '1.2', '12.8', '24.8', '2015-05-09 10:40:10');
INSERT INTO `runtime_device` VALUES ('213', '2', '0.8', '9.4', '5.9', '2015-05-09 10:40:10');
INSERT INTO `runtime_device` VALUES ('214', '3', '8', '31.5', '12.6', '2015-05-09 10:40:10');
INSERT INTO `runtime_device` VALUES ('215', '14', '12', '16.8', '13.3', '2015-05-09 10:40:10');
INSERT INTO `runtime_device` VALUES ('216', '15', '9.3', '5.1', '1.7', '2015-05-09 10:40:10');
INSERT INTO `runtime_device` VALUES ('217', '16', '21.1', '23.2', '18.8', '2015-05-09 10:40:10');
INSERT INTO `runtime_device` VALUES ('218', '1', '7.2', '17.1', '28.8', '2015-05-09 10:40:40');
INSERT INTO `runtime_device` VALUES ('219', '2', '4.6', '13.9', '7.1', '2015-05-09 10:40:40');
INSERT INTO `runtime_device` VALUES ('220', '3', '6.8', '29.3', '10.7', '2015-05-09 10:40:40');
INSERT INTO `runtime_device` VALUES ('221', '14', '10.8', '21.8', '8.6', '2015-05-09 10:40:40');
INSERT INTO `runtime_device` VALUES ('222', '15', '2.7', '3.2', '3.9', '2015-05-09 10:40:40');
INSERT INTO `runtime_device` VALUES ('223', '16', '19.6', '17.1', '14.2', '2015-05-09 10:40:40');
INSERT INTO `runtime_device` VALUES ('224', '1', '8.3', '19.9', '22.1', '2015-05-09 10:41:10');
INSERT INTO `runtime_device` VALUES ('225', '2', '4.9', '9.3', '4.9', '2015-05-09 10:41:10');
INSERT INTO `runtime_device` VALUES ('226', '3', '3.7', '10', '17.4', '2015-05-09 10:41:10');
INSERT INTO `runtime_device` VALUES ('227', '14', '12.7', '7.4', '9.7', '2015-05-09 10:41:10');
INSERT INTO `runtime_device` VALUES ('228', '15', '5.9', '3.1', '6.7', '2015-05-09 10:41:10');
INSERT INTO `runtime_device` VALUES ('229', '16', '12.3', '16.2', '22.5', '2015-05-09 10:41:10');
INSERT INTO `runtime_device` VALUES ('230', '1', '5.1', '7.3', '4.4', '2015-05-09 10:41:40');
INSERT INTO `runtime_device` VALUES ('231', '2', '2.3', '6.9', '7.2', '2015-05-09 10:41:40');
INSERT INTO `runtime_device` VALUES ('232', '3', '10.1', '15.1', '12.4', '2015-05-09 10:41:40');
INSERT INTO `runtime_device` VALUES ('233', '14', '8.4', '30', '5.2', '2015-05-09 10:41:40');
INSERT INTO `runtime_device` VALUES ('234', '15', '4.4', '3.7', '6.7', '2015-05-09 10:41:40');
INSERT INTO `runtime_device` VALUES ('235', '16', '20.2', '21.3', '14', '2015-05-09 10:41:40');
INSERT INTO `runtime_device` VALUES ('236', '1', '5', '14.5', '12.4', '2015-05-09 10:42:10');
INSERT INTO `runtime_device` VALUES ('237', '2', '1.4', '12.6', '4.1', '2015-05-09 10:42:10');
INSERT INTO `runtime_device` VALUES ('238', '3', '20.8', '27.6', '9.4', '2015-05-09 10:42:10');
INSERT INTO `runtime_device` VALUES ('239', '14', '9.4', '19.1', '14.9', '2015-05-09 10:42:10');
INSERT INTO `runtime_device` VALUES ('240', '15', '3.8', '2.1', '5.7', '2015-05-09 10:42:10');
INSERT INTO `runtime_device` VALUES ('241', '16', '11.9', '6.8', '19.8', '2015-05-09 10:42:10');
INSERT INTO `runtime_device` VALUES ('242', '1', '1.9', '8.9', '30.3', '2015-05-09 10:42:40');
INSERT INTO `runtime_device` VALUES ('243', '2', '3.2', '12.4', '6.8', '2015-05-09 10:42:40');
INSERT INTO `runtime_device` VALUES ('244', '3', '11.1', '34.5', '10', '2015-05-09 10:42:40');
INSERT INTO `runtime_device` VALUES ('245', '14', '14.9', '7.6', '13.7', '2015-05-09 10:42:40');
INSERT INTO `runtime_device` VALUES ('246', '15', '6.9', '4.8', '5.2', '2015-05-09 10:42:40');
INSERT INTO `runtime_device` VALUES ('247', '16', '18', '19', '14.3', '2015-05-09 10:42:40');
INSERT INTO `runtime_device` VALUES ('248', '1', '10', '16.9', '15.3', '2015-05-09 10:43:10');
INSERT INTO `runtime_device` VALUES ('249', '2', '7.1', '12.1', '6.6', '2015-05-09 10:43:10');
INSERT INTO `runtime_device` VALUES ('250', '3', '13.8', '8.4', '3', '2015-05-09 10:43:10');
INSERT INTO `runtime_device` VALUES ('251', '14', '18.4', '13.3', '4.5', '2015-05-09 10:43:10');
INSERT INTO `runtime_device` VALUES ('252', '15', '2.7', '4', '2.3', '2015-05-09 10:43:10');
INSERT INTO `runtime_device` VALUES ('253', '16', '18.2', '20.5', '1.8', '2015-05-09 10:43:11');
INSERT INTO `runtime_device` VALUES ('254', '1', '9', '11.9', '27.9', '2015-05-09 10:43:40');
INSERT INTO `runtime_device` VALUES ('255', '2', '2.7', '9.3', '5.5', '2015-05-09 10:43:40');
INSERT INTO `runtime_device` VALUES ('256', '3', '4.8', '28.2', '12.2', '2015-05-09 10:43:40');
INSERT INTO `runtime_device` VALUES ('257', '14', '18.8', '23.1', '3.8', '2015-05-09 10:43:40');
INSERT INTO `runtime_device` VALUES ('258', '15', '2', '5', '4.6', '2015-05-09 10:43:40');
INSERT INTO `runtime_device` VALUES ('259', '16', '12.1', '2.9', '6.8', '2015-05-09 10:43:40');
INSERT INTO `runtime_device` VALUES ('260', '1', '6.7', '7.4', '14.7', '2015-05-09 10:44:10');
INSERT INTO `runtime_device` VALUES ('261', '2', '1', '13.6', '3.6', '2015-05-09 10:44:10');
INSERT INTO `runtime_device` VALUES ('262', '3', '2.1', '20.7', '21.7', '2015-05-09 10:44:10');
INSERT INTO `runtime_device` VALUES ('263', '14', '11.5', '5.3', '12.5', '2015-05-09 10:44:10');
INSERT INTO `runtime_device` VALUES ('264', '15', '9', '3.4', '5.4', '2015-05-09 10:44:10');
INSERT INTO `runtime_device` VALUES ('265', '16', '17.1', '10.3', '2.6', '2015-05-09 10:44:10');
INSERT INTO `runtime_device` VALUES ('266', '1', '6', '13', '11.7', '2015-05-09 10:44:40');
INSERT INTO `runtime_device` VALUES ('267', '2', '5.9', '14', '5.6', '2015-05-09 10:44:40');
INSERT INTO `runtime_device` VALUES ('268', '3', '5.8', '24.7', '18.5', '2015-05-09 10:44:40');
INSERT INTO `runtime_device` VALUES ('269', '14', '7', '14.7', '11.1', '2015-05-09 10:44:40');
INSERT INTO `runtime_device` VALUES ('270', '15', '8.8', '3.6', '4.3', '2015-05-09 10:44:40');
INSERT INTO `runtime_device` VALUES ('271', '16', '15.8', '1.2', '23.4', '2015-05-09 10:44:40');
INSERT INTO `runtime_device` VALUES ('272', '1', '5.8', '21.4', '16.3', '2015-05-09 10:45:10');
INSERT INTO `runtime_device` VALUES ('273', '2', '1.5', '8', '8.5', '2015-05-09 10:45:10');
INSERT INTO `runtime_device` VALUES ('274', '3', '18', '6.9', '21.6', '2015-05-09 10:45:10');
INSERT INTO `runtime_device` VALUES ('275', '14', '4.8', '13.7', '13.3', '2015-05-09 10:45:10');
INSERT INTO `runtime_device` VALUES ('276', '15', '4.9', '2.8', '2.7', '2015-05-09 10:45:10');
INSERT INTO `runtime_device` VALUES ('277', '16', '14.3', '9', '3.5', '2015-05-09 10:45:10');
INSERT INTO `runtime_device` VALUES ('278', '1', '2', '9', '4.4', '2015-05-09 10:45:40');
INSERT INTO `runtime_device` VALUES ('279', '2', '1', '9.9', '8.2', '2015-05-09 10:45:40');
INSERT INTO `runtime_device` VALUES ('280', '3', '6.3', '32.4', '10', '2015-05-09 10:45:40');
INSERT INTO `runtime_device` VALUES ('281', '14', '14.6', '4.4', '8', '2015-05-09 10:45:40');
INSERT INTO `runtime_device` VALUES ('282', '15', '3.1', '1.9', '3.2', '2015-05-09 10:45:40');
INSERT INTO `runtime_device` VALUES ('283', '16', '13.8', '6.5', '10', '2015-05-09 10:45:40');
INSERT INTO `runtime_device` VALUES ('284', '1', '4.1', '20', '29.8', '2015-05-09 10:46:10');
INSERT INTO `runtime_device` VALUES ('285', '2', '2.5', '7.7', '8.3', '2015-05-09 10:46:10');
INSERT INTO `runtime_device` VALUES ('286', '3', '4.6', '6.6', '19', '2015-05-09 10:46:10');
INSERT INTO `runtime_device` VALUES ('287', '14', '23.3', '29.8', '5.4', '2015-05-09 10:46:10');
INSERT INTO `runtime_device` VALUES ('288', '15', '6.6', '3.3', '7', '2015-05-09 10:46:10');
INSERT INTO `runtime_device` VALUES ('289', '16', '13.1', '15.7', '17.9', '2015-05-09 10:46:10');
INSERT INTO `runtime_device` VALUES ('290', '1', '3.7', '5.9', '11.7', '2015-05-09 10:46:40');
INSERT INTO `runtime_device` VALUES ('291', '2', '4.3', '12.9', '8.2', '2015-05-09 10:46:40');
INSERT INTO `runtime_device` VALUES ('292', '3', '17.4', '18.8', '8.5', '2015-05-09 10:46:40');
INSERT INTO `runtime_device` VALUES ('293', '14', '19.8', '9.7', '10.5', '2015-05-09 10:46:40');
INSERT INTO `runtime_device` VALUES ('294', '15', '3.5', '4.3', '4', '2015-05-09 10:46:40');
INSERT INTO `runtime_device` VALUES ('295', '16', '10.9', '4.8', '14', '2015-05-09 10:46:40');
INSERT INTO `runtime_device` VALUES ('296', '1', '5.8', '13.2', '25.8', '2015-05-09 10:47:10');
INSERT INTO `runtime_device` VALUES ('297', '2', '1.5', '13.5', '3.2', '2015-05-09 10:47:10');
INSERT INTO `runtime_device` VALUES ('298', '3', '0.6', '13.3', '14.9', '2015-05-09 10:47:10');
INSERT INTO `runtime_device` VALUES ('299', '14', '20.9', '20.9', '13.5', '2015-05-09 10:47:10');
INSERT INTO `runtime_device` VALUES ('300', '15', '6.9', '2', '2.1', '2015-05-09 10:47:10');
INSERT INTO `runtime_device` VALUES ('301', '16', '18.1', '12.2', '22.5', '2015-05-09 10:47:10');
INSERT INTO `runtime_device` VALUES ('302', '1', '5.9', '5.7', '16', '2015-05-09 10:47:40');
INSERT INTO `runtime_device` VALUES ('303', '2', '1.1', '12.9', '4.7', '2015-05-09 10:47:40');
INSERT INTO `runtime_device` VALUES ('304', '3', '16.8', '14.7', '18.3', '2015-05-09 10:47:40');
INSERT INTO `runtime_device` VALUES ('305', '14', '11.9', '21.3', '9', '2015-05-09 10:47:40');
INSERT INTO `runtime_device` VALUES ('306', '15', '7.8', '5', '4.4', '2015-05-09 10:47:40');
INSERT INTO `runtime_device` VALUES ('307', '16', '11.4', '9.6', '23.3', '2015-05-09 10:47:40');
INSERT INTO `runtime_device` VALUES ('308', '1', '7.5', '18.4', '23.7', '2015-05-09 10:48:10');
INSERT INTO `runtime_device` VALUES ('309', '2', '6.9', '10.5', '8.3', '2015-05-09 10:48:10');
INSERT INTO `runtime_device` VALUES ('310', '3', '2.1', '13.2', '2.8', '2015-05-09 10:48:10');
INSERT INTO `runtime_device` VALUES ('311', '14', '9.5', '12.4', '8.8', '2015-05-09 10:48:10');
INSERT INTO `runtime_device` VALUES ('312', '15', '1.9', '2.8', '4.8', '2015-05-09 10:48:10');
INSERT INTO `runtime_device` VALUES ('313', '16', '18.6', '12.9', '7.5', '2015-05-09 10:48:10');
INSERT INTO `runtime_device` VALUES ('314', '1', '7.4', '8.7', '21.2', '2015-05-09 10:48:40');
INSERT INTO `runtime_device` VALUES ('315', '2', '1.3', '12.1', '5.1', '2015-05-09 10:48:40');
INSERT INTO `runtime_device` VALUES ('316', '3', '5.3', '16.7', '11.1', '2015-05-09 10:48:40');
INSERT INTO `runtime_device` VALUES ('317', '14', '1.9', '21.3', '3.8', '2015-05-09 10:48:40');
INSERT INTO `runtime_device` VALUES ('318', '15', '4.7', '1.7', '2.6', '2015-05-09 10:48:40');
INSERT INTO `runtime_device` VALUES ('319', '16', '21.1', '19.2', '5.2', '2015-05-09 10:48:40');
INSERT INTO `runtime_device` VALUES ('320', '1', '9.9', '7.3', '8', '2015-05-09 15:23:20');
INSERT INTO `runtime_device` VALUES ('321', '2', '7.4', '12.4', '8.4', '2015-05-09 15:23:20');
INSERT INTO `runtime_device` VALUES ('322', '3', '10', '28.4', '4.4', '2015-05-09 15:23:20');
INSERT INTO `runtime_device` VALUES ('323', '14', '22.6', '10.9', '14.3', '2015-05-09 15:23:20');
INSERT INTO `runtime_device` VALUES ('324', '15', '7.8', '4', '5.9', '2015-05-09 15:23:20');
INSERT INTO `runtime_device` VALUES ('325', '16', '16.2', '22', '10.3', '2015-05-09 15:23:20');
INSERT INTO `runtime_device` VALUES ('326', '1', '8.8', '7.8', '13.7', '2015-05-09 15:24:23');
INSERT INTO `runtime_device` VALUES ('327', '2', '5.7', '10.6', '3.8', '2015-05-09 15:24:23');
INSERT INTO `runtime_device` VALUES ('328', '3', '9.2', '12.1', '14', '2015-05-09 15:24:24');
INSERT INTO `runtime_device` VALUES ('329', '14', '4.9', '25.1', '9', '2015-05-09 15:24:24');
INSERT INTO `runtime_device` VALUES ('330', '15', '5', '5', '5.9', '2015-05-09 15:24:24');
INSERT INTO `runtime_device` VALUES ('331', '16', '17.4', '13.9', '9.7', '2015-05-09 15:24:24');
INSERT INTO `runtime_device` VALUES ('332', '1', '6.9', '18.7', '8.4', '2015-05-09 15:26:38');
INSERT INTO `runtime_device` VALUES ('333', '2', '3.2', '9.1', '8', '2015-05-09 15:26:38');
INSERT INTO `runtime_device` VALUES ('334', '3', '9.4', '12.8', '16.3', '2015-05-09 15:26:38');
INSERT INTO `runtime_device` VALUES ('335', '14', '4.8', '10.6', '6.4', '2015-05-09 15:26:38');
INSERT INTO `runtime_device` VALUES ('336', '15', '2.6', '4.1', '5.9', '2015-05-09 15:26:38');
INSERT INTO `runtime_device` VALUES ('337', '16', '21.1', '15.1', '9.5', '2015-05-09 15:26:38');
INSERT INTO `runtime_device` VALUES ('338', '1', '5.4', '19.5', '29.2', '2015-05-09 15:27:07');
INSERT INTO `runtime_device` VALUES ('339', '2', '0.8', '8.2', '7.4', '2015-05-09 15:27:07');
INSERT INTO `runtime_device` VALUES ('340', '3', '4.6', '5.2', '10.2', '2015-05-09 15:27:07');
INSERT INTO `runtime_device` VALUES ('341', '14', '20.8', '9.5', '8.8', '2015-05-09 15:27:07');
INSERT INTO `runtime_device` VALUES ('342', '15', '8.7', '3', '4', '2015-05-09 15:27:07');
INSERT INTO `runtime_device` VALUES ('343', '16', '22.5', '3.4', '19', '2015-05-09 15:27:07');
INSERT INTO `runtime_device` VALUES ('344', '1', '8.1', '7.2', '5.2', '2015-05-09 15:29:26');
INSERT INTO `runtime_device` VALUES ('345', '2', '0.7', '13.8', '7.2', '2015-05-09 15:29:27');
INSERT INTO `runtime_device` VALUES ('346', '3', '18.9', '5.7', '19.3', '2015-05-09 15:29:27');
INSERT INTO `runtime_device` VALUES ('347', '14', '18.5', '3.6', '12.1', '2015-05-09 15:29:27');
INSERT INTO `runtime_device` VALUES ('348', '15', '3.8', '2.6', '4.1', '2015-05-09 15:29:27');
INSERT INTO `runtime_device` VALUES ('349', '16', '14.6', '17', '15.5', '2015-05-09 15:29:27');
INSERT INTO `runtime_device` VALUES ('350', '1', '3.2', '14.3', '7.4', '2015-05-09 15:29:56');
INSERT INTO `runtime_device` VALUES ('351', '2', '5.1', '10.3', '8.4', '2015-05-09 15:29:56');
INSERT INTO `runtime_device` VALUES ('352', '3', '2', '19.5', '18.7', '2015-05-09 15:29:56');
INSERT INTO `runtime_device` VALUES ('353', '14', '16.2', '24.5', '12.9', '2015-05-09 15:29:56');
INSERT INTO `runtime_device` VALUES ('354', '15', '5.5', '3.7', '2.1', '2015-05-09 15:29:56');
INSERT INTO `runtime_device` VALUES ('355', '16', '21.9', '17.6', '21.5', '2015-05-09 15:29:56');
INSERT INTO `runtime_device` VALUES ('356', '1', '10.1', '9.1', '30.9', '2015-05-09 15:30:50');
INSERT INTO `runtime_device` VALUES ('357', '2', '1.5', '6.8', '8', '2015-05-09 15:30:50');
INSERT INTO `runtime_device` VALUES ('358', '3', '20.4', '5.2', '9.6', '2015-05-09 15:30:50');
INSERT INTO `runtime_device` VALUES ('359', '14', '21.7', '26.4', '5.4', '2015-05-09 15:30:50');
INSERT INTO `runtime_device` VALUES ('360', '15', '2.5', '3.2', '7.4', '2015-05-09 15:30:50');
INSERT INTO `runtime_device` VALUES ('361', '16', '18.6', '5.3', '7.7', '2015-05-09 15:30:50');
INSERT INTO `runtime_device` VALUES ('362', '1', '5.3', '11.8', '29.1', '2015-05-09 15:31:20');
INSERT INTO `runtime_device` VALUES ('363', '2', '4.2', '7.6', '4.7', '2015-05-09 15:31:20');
INSERT INTO `runtime_device` VALUES ('364', '3', '12.9', '29.4', '15.2', '2015-05-09 15:31:20');
INSERT INTO `runtime_device` VALUES ('365', '14', '5.8', '6.2', '13.6', '2015-05-09 15:31:20');
INSERT INTO `runtime_device` VALUES ('366', '15', '2.2', '1.8', '5.5', '2015-05-09 15:31:20');
INSERT INTO `runtime_device` VALUES ('367', '16', '15.1', '14.4', '21.7', '2015-05-09 15:31:21');
INSERT INTO `runtime_device` VALUES ('368', '1', '5.7', '18.5', '24.6', '2015-05-09 15:32:17');
INSERT INTO `runtime_device` VALUES ('369', '2', '1.4', '7.1', '4.7', '2015-05-09 15:32:17');
INSERT INTO `runtime_device` VALUES ('370', '3', '4.6', '28.7', '20.3', '2015-05-09 15:32:17');
INSERT INTO `runtime_device` VALUES ('371', '14', '19.2', '21.9', '7.5', '2015-05-09 15:32:17');
INSERT INTO `runtime_device` VALUES ('372', '15', '2.6', '3.7', '4', '2015-05-09 15:32:17');
INSERT INTO `runtime_device` VALUES ('373', '16', '14.6', '1', '19.7', '2015-05-09 15:32:17');
INSERT INTO `runtime_device` VALUES ('374', '1', '7.1', '6.2', '12.1', '2015-05-09 15:32:18');
INSERT INTO `runtime_device` VALUES ('375', '2', '0.7', '7', '2.9', '2015-05-09 15:32:18');
INSERT INTO `runtime_device` VALUES ('376', '3', '12.4', '16.7', '13.3', '2015-05-09 15:32:18');
INSERT INTO `runtime_device` VALUES ('377', '14', '13.9', '12.6', '12.4', '2015-05-09 15:32:19');
INSERT INTO `runtime_device` VALUES ('378', '15', '2', '4.2', '3.9', '2015-05-09 15:32:19');
INSERT INTO `runtime_device` VALUES ('379', '16', '13.8', '15', '1.8', '2015-05-09 15:32:19');
INSERT INTO `runtime_device` VALUES ('380', '1', '5.4', '24.2', '16.4', '2015-05-09 15:32:52');
INSERT INTO `runtime_device` VALUES ('381', '2', '1.4', '11.1', '7.1', '2015-05-09 15:32:52');
INSERT INTO `runtime_device` VALUES ('382', '3', '16.2', '18', '8.2', '2015-05-09 15:32:52');
INSERT INTO `runtime_device` VALUES ('383', '14', '20.8', '16.8', '10.3', '2015-05-09 15:32:52');
INSERT INTO `runtime_device` VALUES ('384', '15', '9.4', '4', '3.5', '2015-05-09 15:32:52');
INSERT INTO `runtime_device` VALUES ('385', '16', '16.9', '2.5', '1.2', '2015-05-09 15:32:52');
INSERT INTO `runtime_device` VALUES ('386', '1', '4.5', '19', '32.6', '2015-05-09 15:39:57');
INSERT INTO `runtime_device` VALUES ('387', '2', '7.3', '10.4', '5.4', '2015-05-09 15:39:57');
INSERT INTO `runtime_device` VALUES ('388', '3', '6.2', '34.5', '18.5', '2015-05-09 15:39:57');
INSERT INTO `runtime_device` VALUES ('389', '14', '3.9', '4.7', '13.5', '2015-05-09 15:39:57');
INSERT INTO `runtime_device` VALUES ('390', '15', '8.7', '6', '4.9', '2015-05-09 15:39:57');
INSERT INTO `runtime_device` VALUES ('391', '16', '14.4', '2', '4.5', '2015-05-09 15:39:57');
INSERT INTO `runtime_device` VALUES ('392', '1', '10.3', '15', '29.1', '2015-05-09 15:40:27');
INSERT INTO `runtime_device` VALUES ('393', '2', '1.8', '13.1', '6.8', '2015-05-09 15:40:27');
INSERT INTO `runtime_device` VALUES ('394', '3', '17.7', '10.2', '5.5', '2015-05-09 15:40:27');
INSERT INTO `runtime_device` VALUES ('395', '14', '6.9', '22.1', '14.9', '2015-05-09 15:40:27');
INSERT INTO `runtime_device` VALUES ('396', '15', '4.2', '4.3', '2.9', '2015-05-09 15:40:27');
INSERT INTO `runtime_device` VALUES ('397', '16', '21', '6.2', '16.7', '2015-05-09 15:40:27');
INSERT INTO `runtime_device` VALUES ('398', '1', '8.2', '16.9', '18.5', '2015-05-09 16:15:04');
INSERT INTO `runtime_device` VALUES ('399', '2', '4.3', '14.4', '3.5', '2015-05-09 16:15:04');
INSERT INTO `runtime_device` VALUES ('400', '3', '13.3', '31.4', '13.4', '2015-05-09 16:15:05');
INSERT INTO `runtime_device` VALUES ('401', '14', '12.1', '3.1', '7.5', '2015-05-09 16:15:05');
INSERT INTO `runtime_device` VALUES ('402', '15', '3.7', '6.1', '2.3', '2015-05-09 16:15:05');
INSERT INTO `runtime_device` VALUES ('403', '16', '22.5', '13.5', '21.2', '2015-05-09 16:15:05');
INSERT INTO `runtime_device` VALUES ('404', '17', '15.8', '7', '2.1', '2015-05-09 16:15:05');
INSERT INTO `runtime_device` VALUES ('405', '1', '3.2', '19.8', '26.5', '2015-05-09 16:15:34');
INSERT INTO `runtime_device` VALUES ('406', '2', '6.3', '8.4', '8.5', '2015-05-09 16:15:34');
INSERT INTO `runtime_device` VALUES ('407', '3', '11', '21.1', '19.3', '2015-05-09 16:15:35');
INSERT INTO `runtime_device` VALUES ('408', '14', '16.2', '11.4', '11', '2015-05-09 16:15:35');
INSERT INTO `runtime_device` VALUES ('409', '15', '4.9', '2.1', '5.8', '2015-05-09 16:15:35');
INSERT INTO `runtime_device` VALUES ('410', '16', '11.9', '6.8', '14.6', '2015-05-09 16:15:35');
INSERT INTO `runtime_device` VALUES ('411', '17', '17.6', '5.5', '4.4', '2015-05-09 16:15:35');
INSERT INTO `runtime_device` VALUES ('412', '1', '1.5', '9.5', '27', '2015-05-09 16:16:04');
INSERT INTO `runtime_device` VALUES ('413', '2', '4.1', '13.7', '8.6', '2015-05-09 16:16:05');
INSERT INTO `runtime_device` VALUES ('414', '3', '5.2', '15.3', '8', '2015-05-09 16:16:05');
INSERT INTO `runtime_device` VALUES ('415', '14', '18.4', '4.5', '13.4', '2015-05-09 16:16:05');
INSERT INTO `runtime_device` VALUES ('416', '15', '10', '6.3', '6.1', '2015-05-09 16:16:05');
INSERT INTO `runtime_device` VALUES ('417', '16', '11.6', '2.3', '7.8', '2015-05-09 16:16:05');
INSERT INTO `runtime_device` VALUES ('418', '17', '17.4', '5.8', '10.8', '2015-05-09 16:16:05');
INSERT INTO `runtime_device` VALUES ('419', '1', '8.3', '13.9', '20.7', '2015-05-09 16:16:34');
INSERT INTO `runtime_device` VALUES ('420', '2', '1.9', '11.3', '6.8', '2015-05-09 16:16:34');
INSERT INTO `runtime_device` VALUES ('421', '3', '11.8', '11.4', '22.2', '2015-05-09 16:16:34');
INSERT INTO `runtime_device` VALUES ('422', '14', '10.1', '25.9', '6.9', '2015-05-09 16:16:35');
INSERT INTO `runtime_device` VALUES ('423', '15', '5.1', '5.1', '1.4', '2015-05-09 16:16:35');
INSERT INTO `runtime_device` VALUES ('424', '16', '17', '22.4', '6.3', '2015-05-09 16:16:35');
INSERT INTO `runtime_device` VALUES ('425', '17', '14.1', '10.7', '4.5', '2015-05-09 16:16:35');
INSERT INTO `runtime_device` VALUES ('426', '1', '6.7', '5.7', '27.3', '2015-05-09 16:17:04');
INSERT INTO `runtime_device` VALUES ('427', '2', '0.6', '6.1', '3.8', '2015-05-09 16:17:04');
INSERT INTO `runtime_device` VALUES ('428', '3', '11.7', '27.3', '8.4', '2015-05-09 16:17:05');
INSERT INTO `runtime_device` VALUES ('429', '14', '19.2', '27.5', '15.8', '2015-05-09 16:17:05');
INSERT INTO `runtime_device` VALUES ('430', '16', '17.2', '14.7', '23.4', '2015-05-09 16:17:05');
INSERT INTO `runtime_device` VALUES ('431', '17', '14.3', '2', '6', '2015-05-09 16:17:05');
INSERT INTO `runtime_device` VALUES ('432', '1', '5.7', '9.9', '20.6', '2015-05-09 16:17:34');
INSERT INTO `runtime_device` VALUES ('433', '2', '3.8', '10.9', '7', '2015-05-09 16:17:34');
INSERT INTO `runtime_device` VALUES ('434', '3', '13.8', '8.5', '17.1', '2015-05-09 16:17:34');
INSERT INTO `runtime_device` VALUES ('435', '14', '16', '7.7', '13.2', '2015-05-09 16:17:35');
INSERT INTO `runtime_device` VALUES ('436', '16', '14', '13.5', '6.1', '2015-05-09 16:17:35');
INSERT INTO `runtime_device` VALUES ('437', '17', '20.2', '5.3', '14', '2015-05-09 16:17:35');
INSERT INTO `runtime_device` VALUES ('438', '1', '9.1', '21.2', '13.6', '2015-05-09 16:18:04');
INSERT INTO `runtime_device` VALUES ('439', '2', '5.5', '8.2', '7.7', '2015-05-09 16:18:04');
INSERT INTO `runtime_device` VALUES ('440', '3', '3.8', '33.2', '5.6', '2015-05-09 16:18:05');
INSERT INTO `runtime_device` VALUES ('441', '14', '21.7', '14.3', '7.3', '2015-05-09 16:18:05');
INSERT INTO `runtime_device` VALUES ('442', '16', '18.3', '13', '0.9', '2015-05-09 16:18:05');
INSERT INTO `runtime_device` VALUES ('443', '17', '10.4', '2.5', '3.3', '2015-05-09 16:18:05');
INSERT INTO `runtime_device` VALUES ('444', '1', '0.3', '11.3', '6', '2015-05-09 16:18:34');
INSERT INTO `runtime_device` VALUES ('445', '2', '5.9', '14.8', '6.9', '2015-05-09 16:18:34');
INSERT INTO `runtime_device` VALUES ('446', '3', '9.1', '24.2', '12.7', '2015-05-09 16:18:35');
INSERT INTO `runtime_device` VALUES ('447', '14', '9.9', '29.7', '12.1', '2015-05-09 16:18:35');
INSERT INTO `runtime_device` VALUES ('448', '16', '17', '23.1', '0.8', '2015-05-09 16:18:35');
INSERT INTO `runtime_device` VALUES ('449', '17', '18.5', '2', '13.6', '2015-05-09 16:18:35');
INSERT INTO `runtime_device` VALUES ('450', '1', '9.4', '26.2', '33.9', '2015-05-09 16:20:25');
INSERT INTO `runtime_device` VALUES ('451', '2', '5.5', '14.7', '9.5', '2015-05-09 16:20:25');
INSERT INTO `runtime_device` VALUES ('452', '3', '9.4', '9.6', '6.1', '2015-05-09 16:20:25');
INSERT INTO `runtime_device` VALUES ('453', '14', '10.3', '12.5', '12.6', '2015-05-09 16:20:25');
INSERT INTO `runtime_device` VALUES ('454', '16', '14.7', '21.3', '9.4', '2015-05-09 16:20:25');
INSERT INTO `runtime_device` VALUES ('455', '17', '20.9', '6.2', '5.6', '2015-05-09 16:20:26');
INSERT INTO `runtime_device` VALUES ('456', '1', '8', '24', '35.7', '2015-05-09 16:20:55');
INSERT INTO `runtime_device` VALUES ('457', '2', '0.7', '12.9', '9.9', '2015-05-09 16:20:55');
INSERT INTO `runtime_device` VALUES ('458', '3', '18.2', '30.9', '21.8', '2015-05-09 16:20:55');
INSERT INTO `runtime_device` VALUES ('459', '14', '3.2', '7.3', '10.5', '2015-05-09 16:20:55');
INSERT INTO `runtime_device` VALUES ('460', '16', '18.6', '15.3', '20.9', '2015-05-09 16:20:55');
INSERT INTO `runtime_device` VALUES ('461', '17', '27.2', '8.1', '5.2', '2015-05-09 16:20:56');
INSERT INTO `runtime_device` VALUES ('462', '1', '5.9', '17.7', '8.3', '2015-05-09 16:21:25');
INSERT INTO `runtime_device` VALUES ('463', '2', '0.3', '8.4', '4.3', '2015-05-09 16:21:25');
INSERT INTO `runtime_device` VALUES ('464', '3', '9.1', '24', '14.1', '2015-05-09 16:21:25');
INSERT INTO `runtime_device` VALUES ('465', '14', '15.1', '6.8', '16.6', '2015-05-09 16:21:25');
INSERT INTO `runtime_device` VALUES ('466', '16', '18.1', '13.7', '10.6', '2015-05-09 16:21:25');
INSERT INTO `runtime_device` VALUES ('467', '17', '27.6', '4.5', '5.9', '2015-05-09 16:21:26');
INSERT INTO `runtime_device` VALUES ('468', '1', '11.4', '11', '21.5', '2015-05-09 16:21:55');
INSERT INTO `runtime_device` VALUES ('469', '2', '1.2', '6', '4.6', '2015-05-09 16:21:55');
INSERT INTO `runtime_device` VALUES ('470', '3', '16.6', '9.9', '12.9', '2015-05-09 16:21:55');
INSERT INTO `runtime_device` VALUES ('471', '14', '3.5', '17.1', '9.3', '2015-05-09 16:21:55');
INSERT INTO `runtime_device` VALUES ('472', '16', '18.2', '3.1', '19.5', '2015-05-09 16:21:56');
INSERT INTO `runtime_device` VALUES ('473', '17', '24.8', '7.6', '0.4', '2015-05-09 16:21:56');
INSERT INTO `runtime_device` VALUES ('474', '1', '11', '25.9', '11.1', '2015-05-09 16:22:25');
INSERT INTO `runtime_device` VALUES ('475', '2', '0.3', '14.5', '2.4', '2015-05-09 16:22:25');
INSERT INTO `runtime_device` VALUES ('476', '3', '1.3', '35.5', '10.1', '2015-05-09 16:22:25');
INSERT INTO `runtime_device` VALUES ('477', '14', '22.7', '20.3', '2.4', '2015-05-09 16:22:25');
INSERT INTO `runtime_device` VALUES ('478', '16', '13.9', '19.3', '22.8', '2015-05-09 16:22:26');
INSERT INTO `runtime_device` VALUES ('479', '17', '25.7', '6.1', '10.1', '2015-05-09 16:22:26');
INSERT INTO `runtime_device` VALUES ('480', '1', '3.3', '23.6', '26.4', '2015-05-09 16:22:55');
INSERT INTO `runtime_device` VALUES ('481', '2', '0.5', '8.2', '5.9', '2015-05-09 16:22:55');
INSERT INTO `runtime_device` VALUES ('482', '3', '21.1', '27.1', '12.4', '2015-05-09 16:22:55');
INSERT INTO `runtime_device` VALUES ('483', '14', '24.8', '20.1', '2.3', '2015-05-09 16:22:55');
INSERT INTO `runtime_device` VALUES ('484', '16', '12.1', '22.9', '14.8', '2015-05-09 16:22:55');
INSERT INTO `runtime_device` VALUES ('485', '17', '22.9', '1.9', '12.4', '2015-05-09 16:22:55');
INSERT INTO `runtime_device` VALUES ('486', '1', '10.3', '10.7', '32.3', '2015-05-09 16:23:25');
INSERT INTO `runtime_device` VALUES ('487', '2', '-0.6', '9.6', '7.5', '2015-05-09 16:23:25');
INSERT INTO `runtime_device` VALUES ('488', '3', '7.9', '22.3', '11', '2015-05-09 16:23:25');
INSERT INTO `runtime_device` VALUES ('489', '14', '0.7', '22.8', '3.2', '2015-05-09 16:23:26');
INSERT INTO `runtime_device` VALUES ('490', '16', '12.4', '16', '0.6', '2015-05-09 16:23:26');
INSERT INTO `runtime_device` VALUES ('491', '17', '13', '3.1', '2.5', '2015-05-09 16:23:26');
INSERT INTO `runtime_device` VALUES ('492', '1', '7.6', '22', '35.2', '2015-05-09 16:23:55');
INSERT INTO `runtime_device` VALUES ('493', '2', '1.9', '8.4', '5.7', '2015-05-09 16:23:55');
INSERT INTO `runtime_device` VALUES ('494', '3', '14.6', '26', '8.3', '2015-05-09 16:23:55');
INSERT INTO `runtime_device` VALUES ('495', '14', '18.5', '14.8', '15', '2015-05-09 16:23:55');
INSERT INTO `runtime_device` VALUES ('496', '16', '16.5', '15.8', '22.5', '2015-05-09 16:23:56');
INSERT INTO `runtime_device` VALUES ('497', '17', '14.5', '6.9', '12.5', '2015-05-09 16:23:56');
INSERT INTO `runtime_device` VALUES ('498', '1', '1.5', '10.5', '16.5', '2015-05-09 16:24:25');
INSERT INTO `runtime_device` VALUES ('499', '2', '2.8', '6.9', '1.7', '2015-05-09 16:24:25');
INSERT INTO `runtime_device` VALUES ('500', '3', '21.5', '17', '13.5', '2015-05-09 16:24:25');
INSERT INTO `runtime_device` VALUES ('501', '14', '16.7', '3', '16.1', '2015-05-09 16:24:25');
INSERT INTO `runtime_device` VALUES ('502', '16', '20.1', '11.3', '20.7', '2015-05-09 16:24:26');
INSERT INTO `runtime_device` VALUES ('503', '17', '23.1', '11.7', '16.5', '2015-05-09 16:24:26');
INSERT INTO `runtime_device` VALUES ('504', '1', '2.2', '24.7', '13.8', '2015-05-09 16:24:55');
INSERT INTO `runtime_device` VALUES ('505', '2', '6.4', '5.7', '3.8', '2015-05-09 16:24:55');
INSERT INTO `runtime_device` VALUES ('506', '3', '23.6', '6.1', '9.3', '2015-05-09 16:24:55');
INSERT INTO `runtime_device` VALUES ('507', '14', '9.1', '21', '15.9', '2015-05-09 16:24:55');
INSERT INTO `runtime_device` VALUES ('508', '16', '17.5', '3.3', '12.1', '2015-05-09 16:24:55');
INSERT INTO `runtime_device` VALUES ('509', '17', '21.9', '1.3', '9.1', '2015-05-09 16:24:55');
INSERT INTO `runtime_device` VALUES ('510', '1', '0.8', '12.1', '31.8', '2015-05-09 16:25:25');
INSERT INTO `runtime_device` VALUES ('511', '2', '7', '15.9', '4.6', '2015-05-09 16:25:25');
INSERT INTO `runtime_device` VALUES ('512', '3', '7.6', '13.3', '13.3', '2015-05-09 16:25:25');
INSERT INTO `runtime_device` VALUES ('513', '14', '24.7', '22.6', '10', '2015-05-09 16:25:25');
INSERT INTO `runtime_device` VALUES ('514', '16', '21.8', '9.5', '12', '2015-05-09 16:25:25');
INSERT INTO `runtime_device` VALUES ('515', '17', '15', '5.2', '12.1', '2015-05-09 16:25:25');
INSERT INTO `runtime_device` VALUES ('516', '1', '11.9', '26.2', '11', '2015-05-09 16:25:55');
INSERT INTO `runtime_device` VALUES ('517', '2', '1.5', '7.4', '4.7', '2015-05-09 16:25:55');
INSERT INTO `runtime_device` VALUES ('518', '3', '5.5', '10.3', '8.1', '2015-05-09 16:25:55');
INSERT INTO `runtime_device` VALUES ('519', '14', '23.2', '21.8', '11', '2015-05-09 16:25:55');
INSERT INTO `runtime_device` VALUES ('520', '16', '12.5', '1.3', '7', '2015-05-09 16:25:55');
INSERT INTO `runtime_device` VALUES ('521', '17', '10.2', '4.1', '0.1', '2015-05-09 16:25:56');
INSERT INTO `runtime_device` VALUES ('522', '1', '2.1', '11.5', '29.7', '2015-05-09 16:26:25');
INSERT INTO `runtime_device` VALUES ('523', '2', '1.9', '7.7', '1.8', '2015-05-09 16:26:25');
INSERT INTO `runtime_device` VALUES ('524', '3', '19.8', '35.9', '13', '2015-05-09 16:26:25');
INSERT INTO `runtime_device` VALUES ('525', '14', '19.6', '9.4', '13.7', '2015-05-09 16:26:25');
INSERT INTO `runtime_device` VALUES ('526', '16', '14.1', '13.4', '1.9', '2015-05-09 16:26:25');
INSERT INTO `runtime_device` VALUES ('527', '17', '16.5', '8.3', '16', '2015-05-09 16:26:26');
INSERT INTO `runtime_device` VALUES ('528', '1', '11.2', '18.9', '25.3', '2015-05-09 17:47:20');
INSERT INTO `runtime_device` VALUES ('529', '2', '-1', '9.7', '8.5', '2015-05-09 17:47:20');
INSERT INTO `runtime_device` VALUES ('530', '3', '0.5', '8.3', '5', '2015-05-09 17:47:20');
INSERT INTO `runtime_device` VALUES ('531', '14', '13.5', '17.4', '10.3', '2015-05-09 17:47:20');
INSERT INTO `runtime_device` VALUES ('532', '15', '3.1', '5.6', '4.8', '2015-05-09 17:47:20');
INSERT INTO `runtime_device` VALUES ('533', '16', '14.3', '15', '2.4', '2015-05-09 17:47:20');
INSERT INTO `runtime_device` VALUES ('534', '17', '10.5', '11.5', '1.4', '2015-05-09 17:47:20');
INSERT INTO `runtime_device` VALUES ('535', '1', '-0.8', '16.6', '32.9', '2015-05-09 17:48:12');
INSERT INTO `runtime_device` VALUES ('536', '2', '3.3', '9.6', '5', '2015-05-09 17:48:12');
INSERT INTO `runtime_device` VALUES ('537', '3', '16.8', '10.3', '9.3', '2015-05-09 17:48:12');
INSERT INTO `runtime_device` VALUES ('538', '14', '18.6', '7.6', '3.3', '2015-05-09 17:48:12');
INSERT INTO `runtime_device` VALUES ('539', '15', '1.8', '2.3', '3', '2015-05-09 17:48:12');
INSERT INTO `runtime_device` VALUES ('540', '16', '23.2', '-0.8', '-0.3', '2015-05-09 17:48:12');
INSERT INTO `runtime_device` VALUES ('541', '17', '31.9', '2.9', '15.1', '2015-05-09 17:48:12');
INSERT INTO `runtime_device` VALUES ('542', '1', '8.3', '15.2', '2.5', '2015-05-09 17:48:41');
INSERT INTO `runtime_device` VALUES ('543', '2', '4.7', '15.1', '3.1', '2015-05-09 17:48:41');
INSERT INTO `runtime_device` VALUES ('544', '3', '23', '22.9', '4.2', '2015-05-09 17:48:41');
INSERT INTO `runtime_device` VALUES ('545', '14', '14.8', '4.3', '6.4', '2015-05-09 17:48:41');
INSERT INTO `runtime_device` VALUES ('546', '15', '9.3', '2.7', '6.8', '2015-05-09 17:48:41');
INSERT INTO `runtime_device` VALUES ('547', '16', '9.9', '-0.3', '8', '2015-05-09 17:48:41');
INSERT INTO `runtime_device` VALUES ('548', '17', '26.8', '9.6', '6.1', '2015-05-09 17:48:41');
INSERT INTO `runtime_device` VALUES ('549', '1', '7.3', '22.6', '2', '2015-05-09 17:49:11');
INSERT INTO `runtime_device` VALUES ('550', '2', '4', '7.4', '3.3', '2015-05-09 17:49:11');
INSERT INTO `runtime_device` VALUES ('551', '3', '11.2', '7.1', '12.3', '2015-05-09 17:49:11');
INSERT INTO `runtime_device` VALUES ('552', '14', '14.9', '20.2', '9.2', '2015-05-09 17:49:11');
INSERT INTO `runtime_device` VALUES ('553', '15', '2.5', '6.5', '0.4', '2015-05-09 17:49:11');
INSERT INTO `runtime_device` VALUES ('554', '16', '21.5', '9.1', '11.9', '2015-05-09 17:49:11');
INSERT INTO `runtime_device` VALUES ('555', '17', '20.3', '11.2', '7.6', '2015-05-09 17:49:11');
INSERT INTO `runtime_device` VALUES ('556', '1', '2', '14.8', '24.5', '2015-05-09 17:49:41');
INSERT INTO `runtime_device` VALUES ('557', '2', '8.5', '8.9', '7', '2015-05-09 17:49:41');
INSERT INTO `runtime_device` VALUES ('558', '3', '17.9', '6.8', '17.2', '2015-05-09 17:49:41');
INSERT INTO `runtime_device` VALUES ('559', '14', '19.6', '4.4', '3.4', '2015-05-09 17:49:41');
INSERT INTO `runtime_device` VALUES ('560', '15', '6.7', '0.9', '4.7', '2015-05-09 17:49:41');
INSERT INTO `runtime_device` VALUES ('561', '16', '10.8', '12.9', '0.7', '2015-05-09 17:49:41');
INSERT INTO `runtime_device` VALUES ('562', '17', '9.2', '11.7', '1.4', '2015-05-09 17:49:41');
INSERT INTO `runtime_device` VALUES ('563', '1', '5.3', '13.6', '3.7', '2015-05-09 17:50:11');
INSERT INTO `runtime_device` VALUES ('564', '2', '4.4', '13.7', '6.5', '2015-05-09 17:50:11');
INSERT INTO `runtime_device` VALUES ('565', '3', '15.4', '19', '10.7', '2015-05-09 17:50:11');
INSERT INTO `runtime_device` VALUES ('566', '14', '24.8', '31.2', '12.6', '2015-05-09 17:50:11');
INSERT INTO `runtime_device` VALUES ('567', '15', '8', '3.6', '4.2', '2015-05-09 17:50:11');
INSERT INTO `runtime_device` VALUES ('568', '16', '12.8', '19.3', '3.1', '2015-05-09 17:50:11');
INSERT INTO `runtime_device` VALUES ('569', '17', '9.7', '10.8', '15.2', '2015-05-09 17:50:11');
INSERT INTO `runtime_device` VALUES ('570', '1', '2.9', '10.8', '17.8', '2015-05-09 17:50:41');
INSERT INTO `runtime_device` VALUES ('571', '2', '5.2', '14.5', '6.6', '2015-05-09 17:50:41');
INSERT INTO `runtime_device` VALUES ('572', '3', '17.7', '26.9', '5.9', '2015-05-09 17:50:41');
INSERT INTO `runtime_device` VALUES ('573', '14', '15.3', '12.6', '16', '2015-05-09 17:50:41');
INSERT INTO `runtime_device` VALUES ('574', '15', '2.1', '3.1', '4.3', '2015-05-09 17:50:41');
INSERT INTO `runtime_device` VALUES ('575', '16', '12.4', '11.6', '0', '2015-05-09 17:50:41');
INSERT INTO `runtime_device` VALUES ('576', '17', '13.8', '6', '12.7', '2015-05-09 17:50:41');
INSERT INTO `runtime_device` VALUES ('577', '1', '4.3', '6.6', '16', '2015-05-09 17:51:11');
INSERT INTO `runtime_device` VALUES ('578', '2', '2.9', '14.1', '3.2', '2015-05-09 17:51:11');
INSERT INTO `runtime_device` VALUES ('579', '3', '12.8', '21.6', '8.2', '2015-05-09 17:51:11');
INSERT INTO `runtime_device` VALUES ('580', '14', '16.6', '27.4', '3.4', '2015-05-09 17:51:11');
INSERT INTO `runtime_device` VALUES ('581', '15', '2.2', '1.6', '7.2', '2015-05-09 17:51:11');
INSERT INTO `runtime_device` VALUES ('582', '16', '19.9', '10.7', '0.6', '2015-05-09 17:51:11');
INSERT INTO `runtime_device` VALUES ('583', '17', '14', '8.2', '1.4', '2015-05-09 17:51:11');
INSERT INTO `runtime_device` VALUES ('584', '1', '7.5', '22.7', '20.7', '2015-05-10 13:43:56');
INSERT INTO `runtime_device` VALUES ('585', '2', '2.5', '6.8', '9.8', '2015-05-10 13:43:56');
INSERT INTO `runtime_device` VALUES ('586', '1', '1.7', '7.4', '18', '2015-05-10 13:44:26');
INSERT INTO `runtime_device` VALUES ('587', '2', '7.9', '7', '7.9', '2015-05-10 13:44:26');
INSERT INTO `runtime_device` VALUES ('588', '1', '1.1', '10.9', '9.3', '2015-05-10 13:44:56');
INSERT INTO `runtime_device` VALUES ('589', '2', '7', '15.6', '6.6', '2015-05-10 13:44:56');
INSERT INTO `runtime_device` VALUES ('590', '1', '6.5', '19.4', '27.2', '2015-05-10 13:45:26');
INSERT INTO `runtime_device` VALUES ('591', '2', '-0.8', '7.3', '8.1', '2015-05-10 13:45:26');
INSERT INTO `runtime_device` VALUES ('592', '1', '6.2', '12.3', '27.2', '2015-05-10 13:45:56');
INSERT INTO `runtime_device` VALUES ('593', '2', '4.8', '15.8', '6.8', '2015-05-10 13:45:56');
INSERT INTO `runtime_device` VALUES ('594', '15', '5', '4.7', '5.4', '2015-05-10 13:45:56');
INSERT INTO `runtime_device` VALUES ('595', '1', '-0.8', '20.6', '7.4', '2015-05-10 21:02:40');
INSERT INTO `runtime_device` VALUES ('596', '2', '6.3', '10.7', '3.2', '2015-05-10 21:02:40');
INSERT INTO `runtime_device` VALUES ('597', '15', '1.4', '7.3', '3.7', '2015-05-10 21:02:40');
INSERT INTO `runtime_device` VALUES ('598', '17', '28.7', '7', '1.3', '2015-05-10 21:02:40');
INSERT INTO `runtime_device` VALUES ('599', '1', '7.2', '7.2', '12.2', '2015-05-10 21:03:10');
INSERT INTO `runtime_device` VALUES ('600', '2', '2.8', '8.9', '2.9', '2015-05-10 21:03:10');
INSERT INTO `runtime_device` VALUES ('601', '3', '16.5', '34.6', '15.2', '2015-05-10 21:03:10');
INSERT INTO `runtime_device` VALUES ('602', '14', '4.4', '17.5', '16.4', '2015-05-10 21:03:10');
INSERT INTO `runtime_device` VALUES ('603', '15', '3.7', '2.8', '4.4', '2015-05-10 21:03:10');
INSERT INTO `runtime_device` VALUES ('604', '17', '11', '3.1', '1.5', '2015-05-10 21:03:11');
INSERT INTO `runtime_device` VALUES ('605', '1', '6.8', '20.5', '34.1', '2015-05-10 21:03:40');
INSERT INTO `runtime_device` VALUES ('606', '2', '8.5', '13.8', '2.7', '2015-05-10 21:03:40');
INSERT INTO `runtime_device` VALUES ('607', '3', '21.5', '7.7', '11.1', '2015-05-10 21:03:40');
INSERT INTO `runtime_device` VALUES ('608', '14', '7.1', '20.7', '15.4', '2015-05-10 21:03:40');
INSERT INTO `runtime_device` VALUES ('609', '15', '3', '0.5', '4.1', '2015-05-10 21:03:41');
INSERT INTO `runtime_device` VALUES ('610', '16', '14.1', '-0.2', '18.5', '2015-05-10 21:03:41');
INSERT INTO `runtime_device` VALUES ('611', '17', '15.6', '11.1', '3.4', '2015-05-10 21:03:41');
INSERT INTO `runtime_device` VALUES ('612', '18', '9.2', '8.2', '19.1', '2015-05-10 21:03:41');
INSERT INTO `runtime_device` VALUES ('613', '19', '4.4', '16.6', '17.3', '2015-05-10 21:03:41');
INSERT INTO `runtime_device` VALUES ('614', '20', '1.5', '16', '144', '2015-05-10 21:03:41');
INSERT INTO `runtime_device` VALUES ('615', '1', '8.8', '22.6', '3.3', '2015-05-10 21:58:35');
INSERT INTO `runtime_device` VALUES ('616', '2', '2.4', '15.4', '6.5', '2015-05-10 21:58:35');
INSERT INTO `runtime_device` VALUES ('617', '15', '1', '2.6', '5.8', '2015-05-10 21:58:35');
INSERT INTO `runtime_device` VALUES ('618', '16', '22.6', '16.5', '5.8', '2015-05-10 21:58:35');
INSERT INTO `runtime_device` VALUES ('619', '17', '26.7', '8.4', '12.2', '2015-05-10 21:58:35');
INSERT INTO `runtime_device` VALUES ('620', '18', '9.9', '5.1', '19.5', '2015-05-10 21:58:35');
INSERT INTO `runtime_device` VALUES ('621', '19', '5.3', '13.1', '17.3', '2015-05-10 21:58:35');
INSERT INTO `runtime_device` VALUES ('622', '20', '12.5', '12.6', '130.7', '2015-05-10 21:58:35');
INSERT INTO `runtime_device` VALUES ('623', '1', '11.4', '9.4', '4.3', '2015-05-10 21:59:04');
INSERT INTO `runtime_device` VALUES ('624', '2', '8.9', '15', '1.3', '2015-05-10 21:59:05');
INSERT INTO `runtime_device` VALUES ('625', '3', '3.9', '6.8', '5.2', '2015-05-10 21:59:05');
INSERT INTO `runtime_device` VALUES ('626', '15', '0.2', '4.9', '2.6', '2015-05-10 21:59:05');
INSERT INTO `runtime_device` VALUES ('627', '16', '15.9', '20.9', '4.5', '2015-05-10 21:59:05');
INSERT INTO `runtime_device` VALUES ('628', '17', '8.2', '1', '3.4', '2015-05-10 21:59:05');
INSERT INTO `runtime_device` VALUES ('629', '18', '11.4', '0.7', '19', '2015-05-10 21:59:05');
INSERT INTO `runtime_device` VALUES ('630', '19', '7.7', '12.6', '17', '2015-05-10 21:59:05');
INSERT INTO `runtime_device` VALUES ('631', '20', '14', '6.4', '95.1', '2015-05-10 21:59:05');
INSERT INTO `runtime_device` VALUES ('632', '1', '10.7', '20.6', '11.9', '2015-05-10 21:59:35');
INSERT INTO `runtime_device` VALUES ('633', '2', '0.3', '7.8', '4.3', '2015-05-10 21:59:35');
INSERT INTO `runtime_device` VALUES ('634', '3', '6.6', '27.5', '11.5', '2015-05-10 21:59:35');
INSERT INTO `runtime_device` VALUES ('635', '15', '4.9', '6', '3.6', '2015-05-10 21:59:35');
INSERT INTO `runtime_device` VALUES ('636', '16', '17.9', '8.2', '2.9', '2015-05-10 21:59:35');
INSERT INTO `runtime_device` VALUES ('637', '17', '23.7', '7', '1.6', '2015-05-10 21:59:35');
INSERT INTO `runtime_device` VALUES ('638', '18', '13', '7.2', '14.7', '2015-05-10 21:59:35');
INSERT INTO `runtime_device` VALUES ('639', '19', '1.6', '24.8', '22.6', '2015-05-10 21:59:35');
INSERT INTO `runtime_device` VALUES ('640', '20', '11.2', '13.9', '218.5', '2015-05-10 21:59:35');
INSERT INTO `runtime_device` VALUES ('641', '1', '1.2', '7.7', '28.3', '2015-05-11 14:53:05');
INSERT INTO `runtime_device` VALUES ('642', '2', '0.9', '7.9', '9.8', '2015-05-11 14:53:05');
INSERT INTO `runtime_device` VALUES ('643', '3', '4.7', '11.8', '12.6', '2015-05-11 14:53:05');
INSERT INTO `runtime_device` VALUES ('644', '15', '8', '5.3', '8.6', '2015-05-11 14:53:05');
INSERT INTO `runtime_device` VALUES ('645', '16', '18.6', '0.7', '4.5', '2015-05-11 14:53:05');
INSERT INTO `runtime_device` VALUES ('646', '17', '15.2', '10.2', '7.3', '2015-05-11 14:53:05');
INSERT INTO `runtime_device` VALUES ('647', '18', '11.5', '13.2', '8.7', '2015-05-11 14:53:05');
INSERT INTO `runtime_device` VALUES ('648', '19', '8', '14.9', '17.3', '2015-05-11 14:53:05');
INSERT INTO `runtime_device` VALUES ('649', '20', '7.4', '25.1', '37.9', '2015-05-11 14:53:05');
INSERT INTO `runtime_device` VALUES ('650', '1', '-0.2', '26.5', '34', '2015-05-11 14:53:35');
INSERT INTO `runtime_device` VALUES ('651', '2', '6.9', '6.8', '5.8', '2015-05-11 14:53:35');
INSERT INTO `runtime_device` VALUES ('652', '3', '2.8', '21.9', '15.3', '2015-05-11 14:53:35');
INSERT INTO `runtime_device` VALUES ('653', '15', '10', '7.5', '3.5', '2015-05-11 14:53:35');
INSERT INTO `runtime_device` VALUES ('654', '16', '21.9', '23.7', '13.7', '2015-05-11 14:53:35');
INSERT INTO `runtime_device` VALUES ('655', '17', '12.4', '5.8', '1.7', '2015-05-11 14:53:35');
INSERT INTO `runtime_device` VALUES ('656', '18', '9.6', '10.3', '7.5', '2015-05-11 14:53:35');
INSERT INTO `runtime_device` VALUES ('657', '19', '4.2', '16.5', '14.2', '2015-05-11 14:53:35');
INSERT INTO `runtime_device` VALUES ('658', '20', '6.3', '6', '60.4', '2015-05-11 14:53:35');
INSERT INTO `runtime_device` VALUES ('659', '1', '11.5', '23', '22.3', '2015-05-11 14:54:05');
INSERT INTO `runtime_device` VALUES ('660', '2', '4.8', '13', '3.2', '2015-05-11 14:54:05');
INSERT INTO `runtime_device` VALUES ('661', '3', '17.1', '12.3', '5.4', '2015-05-11 14:54:05');
INSERT INTO `runtime_device` VALUES ('662', '15', '0.5', '0.5', '5.4', '2015-05-11 14:54:05');
INSERT INTO `runtime_device` VALUES ('663', '16', '14.3', '17.7', '24.8', '2015-05-11 14:54:05');
INSERT INTO `runtime_device` VALUES ('664', '17', '29', '3.2', '14.9', '2015-05-11 14:54:05');
INSERT INTO `runtime_device` VALUES ('665', '18', '13.7', '5.3', '19.7', '2015-05-11 14:54:05');
INSERT INTO `runtime_device` VALUES ('666', '19', '7', '32.6', '22.6', '2015-05-11 14:54:05');
INSERT INTO `runtime_device` VALUES ('667', '20', '5.1', '25.2', '51.1', '2015-05-11 14:54:05');
INSERT INTO `runtime_device` VALUES ('668', '1', '3.2', '19.5', '5.3', '2015-05-11 14:54:35');
INSERT INTO `runtime_device` VALUES ('669', '2', '5.4', '15.2', '4.5', '2015-05-11 14:54:35');
INSERT INTO `runtime_device` VALUES ('670', '3', '16.3', '19.1', '20.7', '2015-05-11 14:54:35');
INSERT INTO `runtime_device` VALUES ('671', '15', '1.8', '5.3', '5.6', '2015-05-11 14:54:35');
INSERT INTO `runtime_device` VALUES ('672', '16', '14.8', '19.2', '7.5', '2015-05-11 14:54:35');
INSERT INTO `runtime_device` VALUES ('673', '17', '10.8', '6.6', '6.8', '2015-05-11 14:54:35');
INSERT INTO `runtime_device` VALUES ('674', '18', '14.4', '10.4', '18.9', '2015-05-11 14:54:35');
INSERT INTO `runtime_device` VALUES ('675', '19', '8.9', '26', '22.8', '2015-05-11 14:54:35');
INSERT INTO `runtime_device` VALUES ('676', '20', '12.8', '31.6', '197.8', '2015-05-11 14:54:35');
INSERT INTO `runtime_device` VALUES ('677', '1', '6.6', '25', '9.1', '2015-05-11 15:08:53');
INSERT INTO `runtime_device` VALUES ('678', '2', '4.8', '14.2', '8.5', '2015-05-11 15:08:55');
INSERT INTO `runtime_device` VALUES ('679', '3', '10.1', '31.1', '18.1', '2015-05-11 15:08:55');
INSERT INTO `runtime_device` VALUES ('680', '14', '1.4', '18.5', '4.1', '2015-05-11 15:08:55');
INSERT INTO `runtime_device` VALUES ('681', '15', '10.3', '2.5', '5.5', '2015-05-11 15:08:55');
INSERT INTO `runtime_device` VALUES ('682', '16', '16.9', '2.5', '12.5', '2015-05-11 15:08:55');
INSERT INTO `runtime_device` VALUES ('683', '17', '29.4', '7.4', '2.2', '2015-05-11 15:08:55');
INSERT INTO `runtime_device` VALUES ('684', '18', '1.3', '0.5', '16.8', '2015-05-11 15:08:55');
INSERT INTO `runtime_device` VALUES ('685', '19', '0.3', '29.3', '17.2', '2015-05-11 15:08:55');
INSERT INTO `runtime_device` VALUES ('686', '20', '5', '28.4', '192.1', '2015-05-11 15:08:55');
INSERT INTO `runtime_device` VALUES ('687', '21', '0.6', '12.9', '14.3', '2015-05-11 15:08:56');
INSERT INTO `runtime_device` VALUES ('688', '1', '6.8', '26.2', '35.8', '2015-05-11 15:09:09');
INSERT INTO `runtime_device` VALUES ('689', '2', '1.1', '5.6', '8.6', '2015-05-11 15:09:09');
INSERT INTO `runtime_device` VALUES ('690', '3', '12.6', '31.8', '17.5', '2015-05-11 15:09:09');
INSERT INTO `runtime_device` VALUES ('691', '14', '1', '19.4', '14.1', '2015-05-11 15:09:09');
INSERT INTO `runtime_device` VALUES ('692', '15', '1.3', '1.9', '3.5', '2015-05-11 15:09:09');
INSERT INTO `runtime_device` VALUES ('693', '16', '15.3', '22.1', '10.2', '2015-05-11 15:09:09');
INSERT INTO `runtime_device` VALUES ('694', '17', '21.5', '4.4', '0.6', '2015-05-11 15:09:09');
INSERT INTO `runtime_device` VALUES ('695', '18', '1.3', '1.8', '19.8', '2015-05-11 15:09:09');
INSERT INTO `runtime_device` VALUES ('696', '19', '9.3', '13.2', '17.1', '2015-05-11 15:09:10');
INSERT INTO `runtime_device` VALUES ('697', '20', '1.8', '16.6', '64.7', '2015-05-11 15:09:10');
INSERT INTO `runtime_device` VALUES ('698', '21', '5.3', '1.3', '0.2', '2015-05-11 15:09:10');
INSERT INTO `runtime_device` VALUES ('699', '1', '3.8', '24.9', '16.6', '2015-05-11 15:18:47');
INSERT INTO `runtime_device` VALUES ('700', '2', '-0.6', '13.1', '6.2', '2015-05-11 15:19:04');
INSERT INTO `runtime_device` VALUES ('701', '3', '22.9', '7.3', '7.2', '2015-05-11 15:19:05');
INSERT INTO `runtime_device` VALUES ('702', '14', '19', '5.3', '14.6', '2015-05-11 15:19:05');
INSERT INTO `runtime_device` VALUES ('703', '15', '10', '5.7', '6.4', '2015-05-11 15:19:05');
INSERT INTO `runtime_device` VALUES ('704', '16', '18.4', '21.8', '0.4', '2015-05-11 15:19:05');
INSERT INTO `runtime_device` VALUES ('705', '17', '28.3', '3.5', '8.7', '2015-05-11 15:19:05');
INSERT INTO `runtime_device` VALUES ('706', '18', '0.9', '7.3', '5.8', '2015-05-11 15:19:05');
INSERT INTO `runtime_device` VALUES ('707', '19', '1.2', '18', '19.5', '2015-05-11 15:19:05');
INSERT INTO `runtime_device` VALUES ('708', '20', '11.6', '1', '231.5', '2015-05-11 15:19:06');
INSERT INTO `runtime_device` VALUES ('709', '21', '0', '10.5', '17.1', '2015-05-11 15:19:06');
INSERT INTO `runtime_device` VALUES ('710', '1', '2', '8.5', '35.5', '2015-05-11 15:19:06');
INSERT INTO `runtime_device` VALUES ('711', '2', '6.1', '9.3', '6.7', '2015-05-11 15:19:06');
INSERT INTO `runtime_device` VALUES ('712', '3', '3.3', '6.8', '14.8', '2015-05-11 15:19:06');
INSERT INTO `runtime_device` VALUES ('713', '14', '8.1', '31.5', '3.9', '2015-05-11 15:19:06');
INSERT INTO `runtime_device` VALUES ('714', '15', '2.1', '5.5', '0.6', '2015-05-11 15:19:06');
INSERT INTO `runtime_device` VALUES ('715', '16', '12.4', '16', '14.8', '2015-05-11 15:19:06');
INSERT INTO `runtime_device` VALUES ('716', '17', '15.5', '7.6', '9.7', '2015-05-11 15:19:06');
INSERT INTO `runtime_device` VALUES ('717', '18', '11.9', '11.5', '8.4', '2015-05-11 15:19:06');
INSERT INTO `runtime_device` VALUES ('718', '19', '7', '21', '13.4', '2015-05-11 15:19:06');
INSERT INTO `runtime_device` VALUES ('719', '20', '11', '23', '187.9', '2015-05-11 15:19:06');
INSERT INTO `runtime_device` VALUES ('720', '21', '13.6', '13.4', '32.9', '2015-05-11 15:19:06');
INSERT INTO `runtime_device` VALUES ('721', '1', '10.9', '21.4', '22', '2015-05-11 15:19:36');
INSERT INTO `runtime_device` VALUES ('722', '2', '8.7', '10', '4.1', '2015-05-11 15:19:36');
INSERT INTO `runtime_device` VALUES ('723', '3', '-0.5', '20.1', '4', '2015-05-11 15:19:36');
INSERT INTO `runtime_device` VALUES ('724', '14', '20.3', '12', '3.6', '2015-05-11 15:19:36');
INSERT INTO `runtime_device` VALUES ('725', '15', '7.4', '7', '2.5', '2015-05-11 15:19:36');
INSERT INTO `runtime_device` VALUES ('726', '16', '19.1', '12.2', '24', '2015-05-11 15:19:36');
INSERT INTO `runtime_device` VALUES ('727', '17', '28.2', '8', '12.2', '2015-05-11 15:19:36');
INSERT INTO `runtime_device` VALUES ('728', '18', '3.1', '3.8', '11.9', '2015-05-11 15:19:36');
INSERT INTO `runtime_device` VALUES ('729', '19', '9.1', '32.8', '17.3', '2015-05-11 15:19:36');
INSERT INTO `runtime_device` VALUES ('730', '20', '6.1', '24.9', '121.9', '2015-05-11 15:19:36');
INSERT INTO `runtime_device` VALUES ('731', '21', '6', '5.5', '18.9', '2015-05-11 15:19:37');
INSERT INTO `runtime_device` VALUES ('732', '1', '6.8', '8.5', '10.8', '2015-05-11 15:21:13');
INSERT INTO `runtime_device` VALUES ('733', '2', '7.6', '7', '2.6', '2015-05-11 15:21:13');
INSERT INTO `runtime_device` VALUES ('734', '3', '8.9', '30.2', '1.1', '2015-05-11 15:21:13');
INSERT INTO `runtime_device` VALUES ('735', '14', '22.2', '9.3', '3.3', '2015-05-11 15:21:13');
INSERT INTO `runtime_device` VALUES ('736', '15', '3.7', '0.1', '6.2', '2015-05-11 15:21:13');
INSERT INTO `runtime_device` VALUES ('737', '16', '23.1', '0.1', '5.1', '2015-05-11 15:21:13');
INSERT INTO `runtime_device` VALUES ('738', '17', '9.9', '5.4', '1.6', '2015-05-11 15:21:13');
INSERT INTO `runtime_device` VALUES ('739', '18', '3.3', '4.6', '12', '2015-05-11 15:21:13');
INSERT INTO `runtime_device` VALUES ('740', '19', '0.3', '25.5', '23', '2015-05-11 15:21:14');
INSERT INTO `runtime_device` VALUES ('741', '20', '13.6', '33.2', '31.7', '2015-05-11 15:21:14');
INSERT INTO `runtime_device` VALUES ('742', '21', '1.8', '5.6', '23.6', '2015-05-11 15:21:14');
INSERT INTO `runtime_device` VALUES ('743', '1', '1.2', '15.8', '6.4', '2015-05-11 15:21:54');
INSERT INTO `runtime_device` VALUES ('744', '2', '2.5', '6.7', '4', '2015-05-11 15:21:54');
INSERT INTO `runtime_device` VALUES ('745', '3', '15.3', '6.6', '20.9', '2015-05-11 15:21:54');
INSERT INTO `runtime_device` VALUES ('746', '14', '5', '28.7', '11.6', '2015-05-11 15:21:54');
INSERT INTO `runtime_device` VALUES ('747', '15', '8.5', '5.6', '2.2', '2015-05-11 15:21:54');
INSERT INTO `runtime_device` VALUES ('748', '16', '17.2', '13.4', '11.1', '2015-05-11 15:21:55');
INSERT INTO `runtime_device` VALUES ('749', '17', '20.7', '6.3', '10.1', '2015-05-11 15:21:55');
INSERT INTO `runtime_device` VALUES ('750', '18', '4.8', '4.5', '13.2', '2015-05-11 15:21:55');
INSERT INTO `runtime_device` VALUES ('751', '19', '8.1', '22.3', '12.4', '2015-05-11 15:21:55');
INSERT INTO `runtime_device` VALUES ('752', '20', '4.5', '22.2', '83.8', '2015-05-11 15:21:55');
INSERT INTO `runtime_device` VALUES ('753', '21', '6.5', '10.2', '16.9', '2015-05-11 15:21:55');
INSERT INTO `runtime_device` VALUES ('754', '1', '1', '9.8', '31', '2015-05-11 15:22:24');
INSERT INTO `runtime_device` VALUES ('755', '3', '3.1', '24.1', '4.5', '2015-05-11 15:22:24');
INSERT INTO `runtime_device` VALUES ('756', '14', '22.6', '4.8', '15.1', '2015-05-11 15:22:24');
INSERT INTO `runtime_device` VALUES ('757', '15', '8.6', '2.1', '5.7', '2015-05-11 15:22:24');
INSERT INTO `runtime_device` VALUES ('758', '16', '20.5', '17.5', '3.9', '2015-05-11 15:22:24');
INSERT INTO `runtime_device` VALUES ('759', '17', '27.1', '10.5', '10.4', '2015-05-11 15:22:24');
INSERT INTO `runtime_device` VALUES ('760', '18', '10.7', '10.3', '7', '2015-05-11 15:22:24');
INSERT INTO `runtime_device` VALUES ('761', '19', '12.2', '11.8', '12.2', '2015-05-11 15:22:24');
INSERT INTO `runtime_device` VALUES ('762', '20', '7.1', '14.3', '115.8', '2015-05-11 15:22:24');
INSERT INTO `runtime_device` VALUES ('763', '21', '4.3', '4.9', '20.8', '2015-05-11 15:22:24');
INSERT INTO `runtime_device` VALUES ('764', '1', '2.3', '11', '27.8', '2015-05-11 16:05:59');
INSERT INTO `runtime_device` VALUES ('765', '2', '3.9', '8.1', '8.1', '2015-05-11 16:05:59');
INSERT INTO `runtime_device` VALUES ('766', '3', '20.9', '30.4', '12', '2015-05-11 16:05:59');
INSERT INTO `runtime_device` VALUES ('767', '14', '5.5', '11.1', '11.1', '2015-05-11 16:05:59');
INSERT INTO `runtime_device` VALUES ('768', '15', '4.1', '5.4', '3.3', '2015-05-11 16:06:00');
INSERT INTO `runtime_device` VALUES ('769', '16', '20.3', '17.5', '9.3', '2015-05-11 16:06:00');
INSERT INTO `runtime_device` VALUES ('770', '17', '23.4', '9.6', '6.8', '2015-05-11 16:06:00');
INSERT INTO `runtime_device` VALUES ('771', '18', '1.7', '12.5', '7', '2015-05-11 16:06:00');
INSERT INTO `runtime_device` VALUES ('772', '19', '4.1', '18.6', '12.6', '2015-05-11 16:06:00');
INSERT INTO `runtime_device` VALUES ('773', '20', '8.9', '3.3', '108', '2015-05-11 16:06:00');
INSERT INTO `runtime_device` VALUES ('774', '21', '8.6', '6.3', '33.3', '2015-05-11 16:06:00');
INSERT INTO `runtime_device` VALUES ('775', '1', '2.7', '13.8', '22.6', '2015-05-11 16:06:29');
INSERT INTO `runtime_device` VALUES ('776', '3', '7.2', '30.2', '15.4', '2015-05-11 16:06:29');
INSERT INTO `runtime_device` VALUES ('777', '14', '4.4', '30', '12.9', '2015-05-11 16:06:29');
INSERT INTO `runtime_device` VALUES ('778', '15', '3', '5.9', '5.9', '2015-05-11 16:06:29');
INSERT INTO `runtime_device` VALUES ('779', '16', '11.8', '8.7', '16.5', '2015-05-11 16:06:30');
INSERT INTO `runtime_device` VALUES ('780', '17', '14.3', '6.6', '10.1', '2015-05-11 16:06:30');
INSERT INTO `runtime_device` VALUES ('781', '19', '10.1', '26.6', '17.1', '2015-05-11 16:06:30');
INSERT INTO `runtime_device` VALUES ('782', '20', '3.6', '8.7', '214.9', '2015-05-11 16:06:30');
INSERT INTO `runtime_device` VALUES ('783', '21', '9.6', '1.3', '11.8', '2015-05-11 16:06:30');
INSERT INTO `runtime_device` VALUES ('784', '1', '4.9', '23.8', '11.8', '2015-05-11 16:07:00');
INSERT INTO `runtime_device` VALUES ('785', '3', '15.6', '8.9', '9.7', '2015-05-11 16:07:00');
INSERT INTO `runtime_device` VALUES ('786', '15', '4', '1.6', '5.4', '2015-05-11 16:07:00');
INSERT INTO `runtime_device` VALUES ('787', '16', '11.6', '21.8', '16.2', '2015-05-11 16:07:00');
INSERT INTO `runtime_device` VALUES ('788', '17', '26.3', '7', '11.7', '2015-05-11 16:07:00');
INSERT INTO `runtime_device` VALUES ('789', '19', '6.9', '13.9', '18.7', '2015-05-11 16:07:00');
INSERT INTO `runtime_device` VALUES ('790', '20', '9.1', '9.3', '161.5', '2015-05-11 16:07:00');
INSERT INTO `runtime_device` VALUES ('791', '21', '8.7', '12.4', '29.8', '2015-05-11 16:07:00');
INSERT INTO `runtime_device` VALUES ('792', '1', '2.4', '23', '23.7', '2015-05-11 16:13:14');
INSERT INTO `runtime_device` VALUES ('793', '2', '1', '14.4', '3.5', '2015-05-11 16:13:14');
INSERT INTO `runtime_device` VALUES ('794', '3', '19.5', '5.2', '2.6', '2015-05-11 16:13:14');
INSERT INTO `runtime_device` VALUES ('795', '14', '1.8', '8.1', '8.6', '2015-05-11 16:13:14');
INSERT INTO `runtime_device` VALUES ('796', '15', '2.5', '1.8', '4.3', '2015-05-11 16:13:14');
INSERT INTO `runtime_device` VALUES ('797', '16', '11.5', '7.1', '14.6', '2015-05-11 16:13:14');
INSERT INTO `runtime_device` VALUES ('798', '17', '22.3', '2.3', '14', '2015-05-11 16:13:14');
INSERT INTO `runtime_device` VALUES ('799', '18', '10.1', '9.2', '17.6', '2015-05-11 16:13:15');
INSERT INTO `runtime_device` VALUES ('800', '19', '8.9', '23.3', '15.2', '2015-05-11 16:13:15');
INSERT INTO `runtime_device` VALUES ('801', '20', '7.1', '16.8', '46.7', '2015-05-11 16:13:15');
INSERT INTO `runtime_device` VALUES ('802', '21', '1.6', '6', '28.3', '2015-05-11 16:13:15');
INSERT INTO `runtime_device` VALUES ('803', '1', '8.1', '19', '16', '2015-05-11 16:13:44');
INSERT INTO `runtime_device` VALUES ('804', '16', '11.1', '10.9', '21.9', '2015-05-11 16:13:44');
INSERT INTO `runtime_device` VALUES ('805', '17', '23.3', '6.8', '5.4', '2015-05-11 16:13:44');
INSERT INTO `runtime_device` VALUES ('806', '18', '4.1', '7.7', '17.4', '2015-05-11 16:13:44');
INSERT INTO `runtime_device` VALUES ('807', '19', '3.3', '25.9', '21.7', '2015-05-11 16:13:44');
INSERT INTO `runtime_device` VALUES ('808', '20', '5', '23.2', '219.1', '2015-05-11 16:13:44');
INSERT INTO `runtime_device` VALUES ('809', '21', '11.8', '9.7', '28.5', '2015-05-11 16:13:44');
INSERT INTO `runtime_device` VALUES ('810', '1', '5.1', '9.3', '17.7', '2015-05-11 16:14:14');
INSERT INTO `runtime_device` VALUES ('811', '16', '12.7', '1.7', '14.2', '2015-05-11 16:14:14');
INSERT INTO `runtime_device` VALUES ('812', '17', '23.3', '8.3', '13.1', '2015-05-11 16:14:14');
INSERT INTO `runtime_device` VALUES ('813', '18', '6.6', '5.7', '4.8', '2015-05-11 16:14:14');
INSERT INTO `runtime_device` VALUES ('814', '19', '7.9', '28.8', '15.8', '2015-05-11 16:14:15');
INSERT INTO `runtime_device` VALUES ('815', '20', '3.4', '1.9', '178.4', '2015-05-11 16:14:15');
INSERT INTO `runtime_device` VALUES ('816', '21', '8.3', '6.7', '7', '2015-05-11 16:14:15');
INSERT INTO `runtime_device` VALUES ('817', '1', '0.8', '9.1', '34', '2015-05-11 16:15:15');
INSERT INTO `runtime_device` VALUES ('818', '2', '4.4', '11.2', '3.7', '2015-05-11 16:15:15');
INSERT INTO `runtime_device` VALUES ('819', '3', '5.8', '19.1', '5', '2015-05-11 16:15:15');
INSERT INTO `runtime_device` VALUES ('820', '14', '17.3', '26.3', '10.3', '2015-05-11 16:15:15');
INSERT INTO `runtime_device` VALUES ('821', '15', '8.6', '2', '4.2', '2015-05-11 16:15:15');
INSERT INTO `runtime_device` VALUES ('822', '16', '12.3', '21.1', '7.8', '2015-05-11 16:15:15');
INSERT INTO `runtime_device` VALUES ('823', '17', '21.1', '6.4', '11', '2015-05-11 16:15:15');
INSERT INTO `runtime_device` VALUES ('824', '18', '6.2', '12', '4.4', '2015-05-11 16:15:15');
INSERT INTO `runtime_device` VALUES ('825', '19', '7', '19.7', '13.5', '2015-05-11 16:15:16');
INSERT INTO `runtime_device` VALUES ('826', '20', '11.9', '18.3', '124.2', '2015-05-11 16:15:16');
INSERT INTO `runtime_device` VALUES ('827', '21', '7.4', '8.3', '18.7', '2015-05-11 16:15:16');
INSERT INTO `runtime_device` VALUES ('828', '2', '4.5', '12.6', '3.5', '2015-05-11 16:15:45');
INSERT INTO `runtime_device` VALUES ('829', '3', '11.3', '12.7', '8.4', '2015-05-11 16:15:45');
INSERT INTO `runtime_device` VALUES ('830', '14', '6.6', '12.1', '4.9', '2015-05-11 16:15:45');
INSERT INTO `runtime_device` VALUES ('831', '16', '21.6', '9.5', '22.4', '2015-05-11 16:15:45');
INSERT INTO `runtime_device` VALUES ('832', '17', '21', '9.7', '9.8', '2015-05-11 16:15:45');
INSERT INTO `runtime_device` VALUES ('833', '19', '10', '21.7', '15.6', '2015-05-11 16:15:45');
INSERT INTO `runtime_device` VALUES ('834', '20', '3.7', '3.3', '47.4', '2015-05-11 16:15:45');
INSERT INTO `runtime_device` VALUES ('835', '21', '10.9', '9.5', '5', '2015-05-11 16:15:46');
INSERT INTO `runtime_device` VALUES ('836', '2', '2.7', '10', '6.1', '2015-05-11 16:16:15');
INSERT INTO `runtime_device` VALUES ('837', '3', '14.8', '13.9', '19.8', '2015-05-11 16:16:15');
INSERT INTO `runtime_device` VALUES ('838', '14', '4.4', '25.1', '7.2', '2015-05-11 16:16:15');
INSERT INTO `runtime_device` VALUES ('839', '15', '7.4', '1.8', '2.9', '2015-05-11 16:16:15');
INSERT INTO `runtime_device` VALUES ('840', '16', '16', '6.5', '10.8', '2015-05-11 16:16:15');
INSERT INTO `runtime_device` VALUES ('841', '17', '24.5', '6.6', '7.5', '2015-05-11 16:16:15');
INSERT INTO `runtime_device` VALUES ('842', '19', '11.9', '21.9', '19.7', '2015-05-11 16:16:15');
INSERT INTO `runtime_device` VALUES ('843', '20', '11.7', '17.6', '122.5', '2015-05-11 16:16:15');
INSERT INTO `runtime_device` VALUES ('844', '21', '5.4', '8.8', '27.1', '2015-05-11 16:16:16');

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
  `MOBILE_PHONE` varchar(45) DEFAULT NULL,
  `USER_TYPE` smallint(1) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `MODIFY_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'a', '0cc175b9c0f1b6a831c399e269772661', '林健', '13102116528', '1', '2015-05-08 10:15:03', '2015-05-08 10:15:03');
INSERT INTO `user` VALUES ('5', 'zyj', 'e10adc3949ba59abbe56e057f20f883e', '赵一建', '13123213412', '2', '2015-05-11 18:14:56', '2015-05-11 18:14:56');
