/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : windb
Target Host     : localhost:3306
Target Database : windb
Date: 2010-12-06 23:31:28
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_vip
-- ----------------------------
DROP TABLE IF EXISTS `tb_vip`;
CREATE TABLE `tb_vip` (
  `ID_` bigint(20) NOT NULL AUTO_INCREMENT,
  `Login_GrowValue` int(11) NOT NULL,
  `Login_Score` int(11) NOT NULL,
  `Phone_Msg_` int(11) NOT NULL,
  `Receieve_GrowValue_` int(11) NOT NULL,
  `Receieve_Score_` int(11) NOT NULL,
  `Release_GrowValue_` int(11) NOT NULL,
  `Release_Score_` int(11) NOT NULL,
  `Seller_Count_` int(11) NOT NULL,
  `Type_` char(1) NOT NULL,
  PRIMARY KEY (`ID_`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_vip
-- ----------------------------
INSERT INTO `tb_vip` VALUES ('1', '5', '10', '5', '2', '3', '1', '2', '3', '1');
INSERT INTO `tb_vip` VALUES ('2', '10', '20', '10', '3', '4', '2', '3', '4', '2');
INSERT INTO `tb_vip` VALUES ('3', '15', '30', '20', '4', '5', '3', '4', '-1', '3');
