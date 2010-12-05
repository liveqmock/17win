/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : windb
Target Host     : localhost:3306
Target Database : windb
Date: 2010-12-06 22:42:03
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for tb_property
-- ----------------------------
DROP TABLE IF EXISTS `tb_property`;
CREATE TABLE `tb_property` (
  `id_` bigint(20) NOT NULL AUTO_INCREMENT,
  `name_` varchar(255) NOT NULL,
  `numberValue_` double DEFAULT NULL,
  `stringValue_` varchar(255) DEFAULT NULL,
  `desc_` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of tb_property
-- ----------------------------
INSERT INTO `tb_property` VALUES ('1', 'fabudianPrice', '0.5', null, '发布点价格');
INSERT INTO `tb_property` VALUES ('2', 'huangguanPrice', '5000', null, '皇冠卡价格');
INSERT INTO `tb_property` VALUES ('3', 'shuangzuanPrice', '275', null, '双钻卡价格');
INSERT INTO `tb_property` VALUES ('4', 'zuanshikaPrice', '135', null, '钻石卡价格');
INSERT INTO `tb_property` VALUES ('5', 'huangguanCount', '10001', null, '皇冠卡发布点数量');
INSERT INTO `tb_property` VALUES ('6', 'shuangzuanCount', '550', null, '双钻发布点数量');
INSERT INTO `tb_property` VALUES ('7', 'zuanshiCount', '270', null, '钻石卡发布点数量');
INSERT INTO `tb_property` VALUES ('8', 'vipPrice', '20', null, 'vip 20块钱每月');
INSERT INTO `tb_property` VALUES ('9', 'vipYearRebate', '0.8', null, 'vip一年的折扣');
INSERT INTO `tb_property` VALUES ('10', 'receieveTaskDotRate', '0.8', null, '一心以上接任务扣发布点比率');
INSERT INTO `tb_property` VALUES ('11', 'logisticsDotCount', '0.2', null, '物流发布点个数');
INSERT INTO `tb_property` VALUES ('12', 'creditValueLimit', '250', null, '信誉值上限');
INSERT INTO `tb_property` VALUES ('13', 'buyReleaseDotRebateToRefree', '0.01', null, '当购买发布点的时候，推广人所得金额为，他推荐人买的发布点的金额的0.01');
INSERT INTO `tb_property` VALUES ('14', 'releaseDotChangeMoney', '0.4', null, '发布点兑换成金额');
INSERT INTO `tb_property` VALUES ('15', 'scoreChangeReleaseDot', '200', null, '积分兑换发布点');
INSERT INTO `tb_property` VALUES ('16', 'loginScore', '5', null, '登录时的积分，非会员，不能为小数');
INSERT INTO `tb_property` VALUES ('17', 'refreeByVipReleaseDot', '10', null, '通过你的宣传链接注册的会员购买VIP，获得10个发布点');
