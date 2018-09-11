/*
 Navicat Premium Data Transfer

 Source Server         : 8月6服务器
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : 139.196.123.112
 Source Database       : Truck0326

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : utf-8

 Date: 08/08/2018 13:10:35 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `truck_admin`
-- ----------------------------
DROP TABLE IF EXISTS `truck_admin`;
CREATE TABLE `truck_admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_name` varchar(50) NOT NULL COMMENT '管理员名',
  `password` varchar(50) NOT NULL COMMENT '管理员密码，MD5加密',
  `email` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `shop_name` varchar(100) DEFAULT NULL,
  `role` int(4) NOT NULL COMMENT '角色0-管理员,1-超级管理员',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `user_name_unique` (`admin_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_admin`
-- ----------------------------
BEGIN;
INSERT INTO `truck_admin` VALUES ('1', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '12345744489@qq.com', '1234568998', 'question', 'answer', null, '1', '2018-03-15 10:04:56', '2018-03-15 10:43:43'), ('2', 'admin234', 'E10ADC3949BA59ABBE56E057F20F883E', '12345674489@qq.com', '1234567998', 'question', 'answer', null, '0', '2018-03-15 10:04:56', '2018-03-15 10:43:43'), ('3', 'admin345', 'E10ADC3949BA59ABBE56E057F20F883E', '12356744489@qq.com', '1234567898', 'question', 'answer', null, '0', '2018-03-15 10:04:56', '2018-03-15 10:43:43'), ('4', 'maxiaojun', '202CB962AC59075B964B07152D234B70', 'maxiaojun', null, null, null, null, '0', '2018-05-10 09:34:50', '2018-05-10 09:34:50'), ('5', 'IEC', 'FEF28193E66A52432FBB91B767F8290B', 'IEC', null, null, null, null, '0', '2018-05-18 16:42:48', '2018-05-18 16:42:48'), ('6', 'KST', '2B75D61C4D4E844423945C34846AAE35', 'KST', null, null, null, null, '0', '2018-05-18 18:13:53', '2018-05-18 18:13:53');
COMMIT;

-- ----------------------------
--  Table structure for `truck_admin_shop`
-- ----------------------------
DROP TABLE IF EXISTS `truck_admin_shop`;
CREATE TABLE `truck_admin_shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `admin_id` int(11) NOT NULL,
  `shop_name` varchar(100) DEFAULT NULL,
  `shop_desc` varchar(50) DEFAULT NULL,
  `shop_email` varchar(50) DEFAULT NULL,
  `shop_phone` varchar(20) DEFAULT NULL,
  `shop_address` varchar(100) DEFAULT NULL,
  `shop_headImg` varchar(255) DEFAULT NULL,
  `shop_firstImg` mediumtext,
  `shop_subImg` mediumtext,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `shop_account` text,
  `shop_tax_card` text,
  `shop_sppkp` text,
  `shop_licence` text,
  `shop_proxy_certificate` text,
  `shop_status` int(11) NOT NULL COMMENT '0审核中，1审核通过，2审核不通过',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_admin_shop`
-- ----------------------------
BEGIN;
INSERT INTO `truck_admin_shop` VALUES ('2', '2', '良品2566', '', '', '', '', 'http://cdn.ayotrust.com/upload/894f5fba-3446-4cc1-93ad-74adca296f1e.jpg', null, null, '2018-04-13 10:55:19', '2018-08-06 17:11:57', '1', '1', '1', '1', '1', '1'), ('3', '3', '良品255', null, null, null, null, 'http://cdn.ayotrust.com/upload/894f5fba-3446-4cc1-93ad-74adca296f1e.jpg', null, null, '2018-04-08 12:08:05', '2018-04-13 11:44:54', null, null, null, null, null, '1'), ('4', '5', 'IEC', 'IEC', 'IEC', 'IEC', 'IEC', 'http://cdn.ayotrust.com/upload/b455839e-e7af-4fea-941b-2bee696d138b.png', null, null, '2018-05-18 16:42:59', '2018-05-18 18:48:41', 'http://cdn.ayotrust.com/upload/2f75cb3f-af80-4a1d-b628-2d62b5eecfd5.png', 'http://cdn.ayotrust.com/upload/3aef66a6-cc94-4789-a761-ddcfae5aa0ff.png', 'http://cdn.ayotrust.com/upload/91548160-1234-4ba0-b186-5d42efc76e8d.png', 'http://cdn.ayotrust.com/upload/5f974500-8ebd-4369-811d-c54240bc82b5.png', 'http://cdn.ayotrust.com/upload/915e1c68-9cc1-4940-a31e-dc69959a7f0b.png', '1'), ('5', '6', 'PT KINGSUM TECH INDONESIA', 'XCMG LONKING HANGCHA HBXG', 'kst', 'kst', 'Jl. Margomulyo 51 Tambak Sarioso Asem Rowo Surabaya Jawa Timur Indonesia', 'http://cdn.ayotrust.com/upload/63970c53-47b6-4748-bce8-bbb7598bc5e0.png', null, null, '2018-05-18 18:14:07', '2018-05-18 18:34:32', 'http://cdn.ayotrust.com/upload/69acd80c-2af5-4ba6-949d-ab59f8275a10.png', 'http://cdn.ayotrust.com/upload/b4b7df30-62b3-45f6-8157-d6397532366f.png', 'http://cdn.ayotrust.com/upload/459d07c9-8282-4a17-b41f-75cb7908068d.png', 'http://cdn.ayotrust.com/upload/167132b4-3b38-444b-a0da-3a486d9d0997.png', 'http://cdn.ayotrust.com/upload/04a60b5c-6433-4ac1-bb02-08ccf3a071a5.png', '1');
COMMIT;

-- ----------------------------
--  Table structure for `truck_balance_alteration`
-- ----------------------------
DROP TABLE IF EXISTS `truck_balance_alteration`;
CREATE TABLE `truck_balance_alteration` (
  `balance_alteration_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_info_id` int(11) DEFAULT NULL,
  `alteration_money` decimal(20,2) DEFAULT NULL,
  `alteration_status` int(11) DEFAULT NULL COMMENT '交易状态  收入还是支出',
  `alteration_reason` int(11) DEFAULT NULL COMMENT '变动原因(充值  下单  退单)',
  `order_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`balance_alteration_id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_balance_alteration`
-- ----------------------------
BEGIN;
INSERT INTO `truck_balance_alteration` VALUES ('1', '4', '5000.00', '0', '0', null, '2018-05-10 15:18:36', '2018-05-10 15:18:36'), ('2', '4', '2500.00', '0', '0', null, '2018-05-10 15:19:24', '2018-05-10 15:19:24'), ('3', '4', '9660.00', '0', '0', null, '2018-05-10 15:19:35', '2018-05-10 15:19:35'), ('4', '4', '123.00', '1', '1', '30', '2018-05-10 15:20:51', '2018-05-10 15:20:51'), ('5', '4', '0.01', '0', '0', null, '2018-05-10 15:21:25', '2018-05-10 15:21:25'), ('6', '4', '123.00', '0', '2', '30', '2018-05-10 15:21:39', '2018-05-10 15:21:39'), ('7', '4', '10000000000000000.00', '0', '0', null, '2018-05-10 15:30:28', '2018-05-10 15:30:28'), ('8', '3', '100000000000000001.00', '0', '0', null, '2018-05-10 15:31:22', '2018-05-10 15:31:22'), ('9', '4', '60000000.00', '1', '1', '31', '2018-05-10 15:33:06', '2018-05-10 15:33:06'), ('10', '3', '1.00', '0', '0', null, '2018-05-10 15:45:22', '2018-05-10 15:45:22'), ('11', '3', '1.00', '0', '0', null, '2018-05-10 15:45:28', '2018-05-10 15:45:28'), ('12', '4', '1.00', '0', '0', null, '2018-05-10 15:45:40', '2018-05-10 15:45:40'), ('13', '4', '1.00', '0', '0', null, '2018-05-10 15:46:06', '2018-05-10 15:46:06'), ('14', '4', '1.00', '0', '0', null, '2018-05-10 15:46:09', '2018-05-10 15:46:09'), ('15', '4', '1000.00', '0', '0', null, '2018-05-10 15:46:15', '2018-05-10 15:46:15'), ('16', '4', '1000.00', '0', '0', null, '2018-05-10 15:46:20', '2018-05-10 15:46:20'), ('17', '4', '1.00', '0', '0', null, '2018-05-10 15:46:25', '2018-05-10 15:46:25'), ('18', '4', '2.00', '0', '0', null, '2018-05-10 15:46:28', '2018-05-10 15:46:28'), ('19', '4', '1.00', '0', '0', null, '2018-05-10 15:46:31', '2018-05-10 15:46:31'), ('20', '4', '1.00', '0', '0', null, '2018-05-10 15:46:34', '2018-05-10 15:46:34'), ('21', '4', '1.00', '0', '0', null, '2018-05-10 15:46:38', '2018-05-10 15:46:38'), ('22', '4', '1.00', '0', '0', null, '2018-05-10 15:46:42', '2018-05-10 15:46:42'), ('23', '4', '1.00', '0', '0', null, '2018-05-10 15:46:44', '2018-05-10 15:46:44'), ('24', '4', '1.00', '0', '0', null, '2018-05-10 15:46:47', '2018-05-10 15:46:47'), ('25', '4', '1.00', '0', '0', null, '2018-05-10 15:46:49', '2018-05-10 15:46:49'), ('26', '4', '2.00', '0', '0', null, '2018-05-10 15:46:52', '2018-05-10 15:46:52'), ('27', '4', '2.00', '0', '0', null, '2018-05-10 15:46:54', '2018-05-10 15:46:54'), ('28', '4', '0.50', '0', '0', null, '2018-05-10 15:47:03', '2018-05-10 15:47:03'), ('29', '4', '2.00', '0', '0', null, '2018-05-10 15:48:39', '2018-05-10 15:48:39'), ('30', '4', '2.00', '0', '0', null, '2018-05-10 15:48:41', '2018-05-10 15:48:41'), ('31', '4', '2.00', '0', '0', null, '2018-05-10 15:48:44', '2018-05-10 15:48:44'), ('32', '4', '2.00', '0', '0', null, '2018-05-10 15:48:46', '2018-05-10 15:48:46'), ('33', '4', '2.00', '0', '0', null, '2018-05-10 15:48:47', '2018-05-10 15:48:47'), ('34', '4', '3.60', '0', '0', null, '2018-05-10 15:49:10', '2018-05-10 15:49:10'), ('35', '4', '3.60', '0', '0', null, '2018-05-10 15:49:13', '2018-05-10 15:49:13'), ('36', '4', '1.00', '0', '0', null, '2018-05-10 15:49:22', '2018-05-10 15:49:22'), ('37', '4', '1.00', '0', '0', null, '2018-05-10 15:49:24', '2018-05-10 15:49:24'), ('38', '4', '1.00', '0', '0', null, '2018-05-10 15:49:26', '2018-05-10 15:49:26'), ('39', '4', '1.00', '0', '0', null, '2018-05-10 15:49:29', '2018-05-10 15:49:29'), ('40', '4', '1.00', '0', '0', null, '2018-05-10 15:49:32', '2018-05-10 15:49:32'), ('41', '4', '1.00', '0', '0', null, '2018-05-10 15:49:34', '2018-05-10 15:49:34'), ('42', '4', '1.00', '0', '0', null, '2018-05-10 15:49:37', '2018-05-10 15:49:37'), ('43', '4', '1.00', '0', '0', null, '2018-05-10 15:49:39', '2018-05-10 15:49:39'), ('44', '4', '1.00', '0', '0', null, '2018-05-10 15:49:43', '2018-05-10 15:49:43'), ('45', '3', '2.00', '0', '0', null, '2018-05-10 15:51:05', '2018-05-10 15:51:05'), ('46', '3', '23.00', '0', '0', null, '2018-05-10 15:51:10', '2018-05-10 15:51:10'), ('47', '3', '23.00', '0', '0', null, '2018-05-10 15:51:13', '2018-05-10 15:51:13'), ('48', '3', '23.00', '0', '0', null, '2018-05-10 15:51:15', '2018-05-10 15:51:15'), ('49', '3', '23.00', '0', '0', null, '2018-05-10 15:51:18', '2018-05-10 15:51:18'), ('50', '4', '1.00', '0', '0', null, '2018-05-10 15:53:42', '2018-05-10 15:53:42'), ('51', '4', '2.00', '0', '0', null, '2018-05-10 15:54:25', '2018-05-10 15:54:25'), ('52', '4', '1.00', '0', '0', null, '2018-05-10 15:55:08', '2018-05-10 15:55:08'), ('53', '4', '1.00', '0', '0', null, '2018-05-10 15:55:32', '2018-05-10 15:55:32'), ('54', '4', '1.00', '0', '0', null, '2018-05-10 15:56:56', '2018-05-10 15:56:56'), ('55', '4', '1.00', '0', '0', null, '2018-05-10 15:56:58', '2018-05-10 15:56:58'), ('56', '4', '1.00', '0', '0', null, '2018-05-10 15:57:01', '2018-05-10 15:57:01'), ('57', '4', '1.00', '0', '0', null, '2018-05-10 15:57:03', '2018-05-10 15:57:03'), ('58', '4', '0.01', '0', '0', null, '2018-05-10 15:57:10', '2018-05-10 15:57:10'), ('59', '4', '0.01', '0', '0', null, '2018-05-10 15:57:12', '2018-05-10 15:57:12'), ('60', '4', '800000000.00', '1', '1', '32', '2018-05-10 16:00:04', '2018-05-10 16:00:04'), ('61', '4', '10000000.00', '1', '1', '33', '2018-05-10 16:00:21', '2018-05-10 16:00:21'), ('62', '4', '130000000.00', '1', '1', '34', '2018-05-10 16:00:39', '2018-05-10 16:00:39'), ('63', '4', '20.00', '0', '0', null, '2018-05-10 16:06:31', '2018-05-10 16:06:31'), ('64', '6', '1000.00', '0', '0', null, '2018-08-06 17:53:53', '2018-08-06 17:53:53');
COMMIT;

-- ----------------------------
--  Table structure for `truck_bank`
-- ----------------------------
DROP TABLE IF EXISTS `truck_bank`;
CREATE TABLE `truck_bank` (
  `bank_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `bank_name` varchar(20) NOT NULL,
  `bank_address` varchar(200) DEFAULT NULL,
  `bank_account` varchar(20) NOT NULL,
  `bank_user_name` varchar(20) NOT NULL,
  `contact_number` varchar(20) NOT NULL,
  `present_address` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `truck_buy_status`
-- ----------------------------
DROP TABLE IF EXISTS `truck_buy_status`;
CREATE TABLE `truck_buy_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(50) DEFAULT NULL COMMENT '订单状态',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `truck_buy_status`
-- ----------------------------
BEGIN;
INSERT INTO `truck_buy_status` VALUES ('1', '客户已下单', '2018-08-08 09:23:50', '2018-08-08 09:23:50'), ('2', '完成配货', '2018-08-08 09:24:13', '2018-08-08 09:24:13'), ('3', '填写物流单', '2018-08-08 09:24:21', '2018-08-08 09:24:21'), ('4', '出库', '2018-08-08 09:24:28', '2018-08-08 09:24:28'), ('5', '配送中', '2018-08-08 09:24:35', '2018-08-08 09:24:35'), ('6', '已送达', '2018-08-08 09:24:41', '2018-08-08 09:24:41');
COMMIT;

-- ----------------------------
--  Table structure for `truck_cart`
-- ----------------------------
DROP TABLE IF EXISTS `truck_cart`;
CREATE TABLE `truck_cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '购物车id',
  `cart_user_id` int(11) NOT NULL COMMENT '用户id',
  `cart_product_id` int(11) NOT NULL COMMENT '产品id',
  `amount` int(11) NOT NULL COMMENT '数量',
  `checked` int(11) NOT NULL COMMENT '是否被选中（0否，1是）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `shop_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_cart`
-- ----------------------------
BEGIN;
INSERT INTO `truck_cart` VALUES ('22', '26', '1048', '1', '1', '2018-05-10 19:17:24', '2018-05-10 19:17:36', '2'), ('30', '24', '1046', '2', '1', '2018-08-06 15:12:29', '2018-08-06 17:14:18', '2'), ('31', '24', '1047', '1', '1', '2018-08-06 15:12:30', '2018-08-06 15:12:30', '3'), ('32', '24', '1048', '1', '1', '2018-08-06 15:12:31', '2018-08-06 15:12:31', '2'), ('33', '24', '1049', '1', '1', '2018-08-06 15:12:35', '2018-08-06 15:12:35', '2'), ('34', '24', '1052', '0', '1', '2018-08-06 15:12:37', '2018-08-06 15:12:37', '4'), ('35', '24', '1054', '0', '1', '2018-08-06 15:12:38', '2018-08-06 15:12:38', '4'), ('39', '30', '1046', '2', '1', '2018-08-06 17:51:00', '2018-08-06 17:54:20', '2'), ('40', '25', '1046', '2', '1', '2018-08-07 09:05:11', '2018-08-07 09:05:16', '2'), ('41', '25', '1047', '1', '1', '2018-08-07 09:05:18', '2018-08-07 09:05:18', '3'), ('42', '25', '1048', '1', '1', '2018-08-07 09:05:19', '2018-08-07 09:05:19', '2'), ('43', '25', '1057', '0', '1', '2018-08-07 09:05:21', '2018-08-07 09:05:21', '5');
COMMIT;

-- ----------------------------
--  Table structure for `truck_category`
-- ----------------------------
DROP TABLE IF EXISTS `truck_category`;
CREATE TABLE `truck_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
  `admin_id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `Product_Status` tinyint(1) DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_category`
-- ----------------------------
BEGIN;
INSERT INTO `truck_category` VALUES ('1', '2', '0', '一级', '0', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('2', '2', '1', '二级', '1', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('3', '2', '2', '三级', '1', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('4', '2', '3', '四级', '1', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('5', '2', '1', '二级', '1', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('6', '2', '2', '三级', '1', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('7', '2', '3', '四级', '1', null, '2018-04-02 13:36:39', '2018-04-02 13:36:41'), ('8', '1', '0', 'hh', '1', null, '2018-04-09 16:32:32', '2018-04-09 16:32:32'), ('9', '5', '0', '0', '1', null, '2018-05-18 16:57:00', '2018-05-18 16:57:00'), ('10', '5', '0', '0', '1', null, '2018-05-18 16:57:10', '2018-05-18 16:57:10'), ('11', '5', '0', 'SHANCMAN', '1', null, '2018-05-18 16:57:49', '2018-05-18 16:57:49'), ('12', '5', '9', '0', '1', null, '2018-05-18 16:58:27', '2018-05-18 16:58:27'), ('13', '5', '12', '0', '1', null, '2018-05-18 16:58:39', '2018-05-18 16:58:39'), ('14', '5', '10', '0', '1', null, '2018-05-18 16:59:02', '2018-05-18 16:59:02'), ('15', '5', '14', '0', '1', null, '2018-05-18 16:59:11', '2018-05-18 16:59:11'), ('16', '5', '9', '0', '1', null, '2018-05-18 16:59:28', '2018-05-18 16:59:28'), ('17', '5', '11', 'DUMP TRUCK', '1', null, '2018-05-18 17:00:21', '2018-05-18 17:00:21'), ('18', '5', '11', 'LORRY TRUCK', '1', null, '2018-05-18 17:04:22', '2018-05-18 17:04:22'), ('19', '5', '17', '6X4 HP340 SX3255DR384R', '1', null, '2018-05-18 17:59:42', '2018-05-18 17:59:42'), ('20', '5', '18', '6X4 HP340 SX1255DR464R', '1', null, '2018-05-18 18:00:15', '2018-05-18 18:00:15'), ('21', '5', '17', '8X4 HP380', '1', null, '2018-05-18 18:04:31', '2018-05-18 18:04:31'), ('22', '5', '0', '0', '1', null, '2018-05-18 18:32:33', '2018-05-18 18:32:33'), ('23', '6', '0', 'LONKING', '1', null, '2018-05-18 18:34:52', '2018-05-18 18:34:52'), ('24', '6', '0', 'XCMG', '1', null, '2018-05-18 18:35:09', '2018-05-18 18:35:09'), ('25', '6', '0', 'HBXG', '1', null, '2018-05-18 18:35:17', '2018-05-18 18:35:17'), ('26', '6', '23', '装载机', '1', null, '2018-05-18 18:35:30', '2018-05-18 18:35:30'), ('27', '6', '26', 'LG855', '1', null, '2018-05-18 18:35:49', '2018-05-18 18:35:49'), ('28', '6', '24', '挖机', '1', null, '2018-05-18 18:36:06', '2018-05-18 18:36:06'), ('29', '6', '28', 'XE215', '1', null, '2018-05-18 18:36:15', '2018-05-18 18:36:15'), ('30', '6', '25', '推土机', '1', null, '2018-05-18 18:36:30', '2018-05-18 18:36:30'), ('31', '6', '30', 'SD7N', '1', null, '2018-05-18 18:36:48', '2018-05-18 18:36:48');
COMMIT;

-- ----------------------------
--  Table structure for `truck_company_address`
-- ----------------------------
DROP TABLE IF EXISTS `truck_company_address`;
CREATE TABLE `truck_company_address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `company_id` int(11) NOT NULL,
  `address_name` varchar(100) NOT NULL,
  `address_desc` varchar(100) NOT NULL,
  `address_phone` varchar(50) DEFAULT NULL,
  `address_email` varchar(50) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_company_address`
-- ----------------------------
BEGIN;
INSERT INTO `truck_company_address` VALUES ('1', '28', 'Test', 'Test', '67564535', 'Test', '2018-08-06 18:39:13', '2018-08-06 18:39:13');
COMMIT;

-- ----------------------------
--  Table structure for `truck_company_contact`
-- ----------------------------
DROP TABLE IF EXISTS `truck_company_contact`;
CREATE TABLE `truck_company_contact` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '联系人id',
  `company_id` int(11) NOT NULL,
  `contact_name` varchar(100) NOT NULL,
  `contact_position` varchar(50) DEFAULT NULL,
  `contact_email` varchar(50) DEFAULT NULL,
  `contact_phone` varchar(20) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_company_contact`
-- ----------------------------
BEGIN;
INSERT INTO `truck_company_contact` VALUES ('1', '26', '1', '1', '1', '1', '2018-05-10 09:58:57', '2018-05-10 09:58:57'), ('2', '26', '2', '2', '2', '2', '2018-05-10 09:59:29', '2018-05-10 09:59:29'), ('3', '26', '3', '3', '3', '3', '2018-05-10 09:59:36', '2018-05-10 09:59:36'), ('4', '25', 'halfgod', 'halfgod', '234234', '234234', '2018-08-06 17:55:03', '2018-08-06 17:55:03');
COMMIT;

-- ----------------------------
--  Table structure for `truck_lis_info`
-- ----------------------------
DROP TABLE IF EXISTS `truck_lis_info`;
CREATE TABLE `truck_lis_info` (
  `logistics_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `custom_id` int(11) DEFAULT NULL,
  `staff_id` varchar(50) DEFAULT '',
  `delivery_time` datetime DEFAULT NULL,
  `destination` varchar(200) DEFAULT NULL,
  `delivery_warehouse` varchar(20) DEFAULT NULL,
  `shipping_director` varchar(20) DEFAULT NULL,
  `logistics_no` varchar(50) NOT NULL,
  `order_detail_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`logistics_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_lis_info`
-- ----------------------------
BEGIN;
INSERT INTO `truck_lis_info` VALUES ('7', '2', null, null, '123', '2018-04-13 15:19:28', '123', '123', '123213', '1523603968876', '16', '2018-04-13 15:19:28', '2018-04-13 15:19:28'), ('11', '2', null, null, null, '2018-04-12 15:44:35', null, null, null, '1525778028267', '19', '2018-05-08 19:13:48', '2018-05-08 19:13:48'), ('12', '2', null, null, null, '2018-04-12 15:44:35', null, null, null, '1525832972382', '23', '2018-05-09 10:29:32', '2018-05-09 10:29:32'), ('13', '2', null, null, null, '2018-04-12 15:44:35', null, null, null, '1525833002066', '24', '2018-05-09 10:30:01', '2018-05-09 10:30:01'), ('14', '2', null, null, '2323', '2018-05-10 11:59:21', '32', '123', '123', '1525924761304', '26', '2018-05-10 11:59:21', '2018-05-10 11:59:21'), ('15', '2', null, null, '1', '2018-05-10 12:03:11', '1', '1', '1', '1525924991586', '30', '2018-05-10 12:03:11', '2018-05-10 12:03:11'), ('16', '2', null, null, '123', '2018-05-10 16:04:17', '1', '1', '1', '1525939457703', '34', '2018-05-10 16:04:17', '2018-05-10 16:04:17'), ('17', '2', null, null, '1', '2018-05-10 17:48:36', '1', '1', '1', '1525945717017', '35', '2018-05-10 17:48:36', '2018-05-10 17:48:36'), ('18', '2', null, null, '123', '2018-05-17 16:59:11', '11', '11', '11', '1526547551837', '33', '2018-05-17 16:59:11', '2018-05-17 16:59:11'), ('19', '2', null, null, '1001', '2018-06-01 17:28:50', 'shanghai', 'north', '2001', '1527845330910', '40', '2018-06-01 17:28:50', '2018-06-01 17:31:26'), ('20', '2', null, null, '1eew', '2018-08-06 16:19:24', 'dfs', 'few', 'efwew', '1533543564508', '45', '2018-08-06 16:19:24', '2018-08-06 16:28:33'), ('21', '2', null, null, '111', '2018-08-06 17:13:41', '111', '111', '111', '1533546821182', '46', '2018-08-06 17:13:41', '2018-08-06 17:13:41'), ('22', '2', null, null, 'halfgod', '2018-08-07 09:44:59', 'halfgod', 'halfgod', 'halfgod', '1533606299788', '41', '2018-08-07 09:44:59', '2018-08-07 09:44:59'), ('23', '2', null, null, '123213', '2018-08-07 09:46:18', '123213', '123', '123', '1533546821182', '46', '2018-08-07 09:46:18', '2018-08-07 09:46:18'), ('24', '2', null, null, '234', '2018-08-07 17:26:51', '234', '234', 'halfgod', '1533546821182', '46', '2018-08-07 17:26:51', '2018-08-07 17:26:51'), ('25', '2', null, null, 'rewr', '2018-08-08 11:06:33', 'ew', 're', 'rer', '1533546821182', '46', '2018-08-08 11:06:33', '2018-08-08 11:06:33');
COMMIT;

-- ----------------------------
--  Table structure for `truck_lis_location`
-- ----------------------------
DROP TABLE IF EXISTS `truck_lis_location`;
CREATE TABLE `truck_lis_location` (
  `logistics_id` int(11) NOT NULL,
  `lng` double(10,6) DEFAULT NULL,
  `lat` double(10,6) DEFAULT NULL,
  `current_times` datetime DEFAULT NULL,
  `is_transfer_station` int(4) DEFAULT NULL COMMENT '0-不是，1是',
  `transfer_station_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_lis_location`
-- ----------------------------
BEGIN;
INSERT INTO `truck_lis_location` VALUES ('1', '121.200000', '223.300000', '2018-03-19 12:41:52', '1', '1'), ('1', '121.200000', '223.300000', '2018-03-19 12:42:55', '1', '1'), ('1', '121.200000', '223.300000', '2018-03-19 12:42:57', '1', '1'), ('1', '121.200000', '223.300000', '2018-03-19 12:42:58', '1', '1'), ('1', '121.200000', '223.300000', '2018-03-19 12:43:00', '1', '1'), ('5', null, null, '2018-04-02 14:14:16', '1', '1'), ('5', null, null, '2018-04-11 14:00:15', null, null), ('19', '100.000000', '200.000000', '2018-06-01 17:32:33', null, null), ('19', '200.000000', '200.000000', '2018-06-01 17:32:53', null, null), ('19', '106.894557', '-6.158496', '2018-06-01 17:38:01', null, null), ('19', '90.894557', '6.158496', '2018-06-01 17:38:22', null, null);
COMMIT;

-- ----------------------------
--  Table structure for `truck_lis_status`
-- ----------------------------
DROP TABLE IF EXISTS `truck_lis_status`;
CREATE TABLE `truck_lis_status` (
  `logistics_id` int(11) NOT NULL,
  `logistics_status` int(11) DEFAULT NULL,
  `status_change_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
--  Records of `truck_lis_status`
-- ----------------------------
BEGIN;
INSERT INTO `truck_lis_status` VALUES ('1', '0', '2018-03-13 18:24:36'), ('7', '1', '2018-03-15 09:42:43'), ('1', '1', '2018-03-19 12:17:07'), ('1', '1', '2018-03-19 12:17:29'), ('1', '1', '2018-03-19 12:17:49'), ('1', '2', '2018-03-19 01:26:48'), ('1', '2', '2018-03-19 01:26:57'), ('1', '0', '2018-03-19 01:27:08'), ('5', '2', '2018-04-11 12:33:43'), ('5', '2', '2018-04-11 13:33:41'), ('5', '2', null), ('5', '2', null), ('5', '2', '2018-04-02 14:14:16'), ('5', '2', '2018-04-11 13:50:28'), ('19', '1', '2018-06-01 17:33:53'), ('19', '2', '2018-06-01 17:34:02'), ('7', null, '2018-08-06 17:49:16'), ('7', null, '2018-08-06 17:49:20');
COMMIT;

-- ----------------------------
--  Table structure for `truck_order`
-- ----------------------------
DROP TABLE IF EXISTS `truck_order`;
CREATE TABLE `truck_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `shop_id` int(11) DEFAULT NULL,
  `order_price` decimal(20,2) DEFAULT NULL COMMENT '订单金额',
  `payment_price` decimal(20,2) DEFAULT NULL COMMENT '实付金额',
  `payment_type` int(11) DEFAULT NULL COMMENT '支付类型',
  `freight` decimal(20,2) DEFAULT NULL COMMENT '运费',
  `order_status` int(11) DEFAULT NULL COMMENT '订单状态',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_order`
-- ----------------------------
BEGIN;
INSERT INTO `truck_order` VALUES ('13', '1522649656308', '24', null, '2.00', null, '0', null, '40', null, '2018-04-02 14:14:16', '2018-04-13 18:12:50'), ('14', '1522649656309', '24', null, '2.00', null, '0', null, '10', null, '2018-04-02 14:14:16', '2018-04-02 14:14:16'), ('15', '1525773689435', '25', '2', '6.00', '6.00', '0', null, '10', null, '2018-05-08 18:01:29', '2018-05-08 18:01:29'), ('16', '1525774140327', '25', '2', '6.00', '6.00', '0', null, '10', null, '2018-05-08 18:09:00', '2018-05-08 18:09:00'), ('17', '1525774239486', '25', '2', '6.00', '6.00', '0', null, '10', null, '2018-05-08 18:10:39', '2018-05-08 18:10:39'), ('18', '1525774338372', '25', '2', '6.00', '6.00', '0', null, '10', null, '2018-05-08 18:12:18', '2018-05-08 18:12:18'), ('19', '1525774427412', '25', '2', '6.00', '6.00', '0', null, '40', null, '2018-05-08 18:13:47', '2018-05-08 19:13:48'), ('20', '1525778175443', '25', '2', '5.00', '5.00', '0', null, '10', null, '2018-05-08 19:16:15', '2018-05-08 19:16:15'), ('21', '1525778175640', '25', '3', '2.00', '2.00', '0', null, '10', null, '2018-05-08 19:16:15', '2018-05-08 19:16:15'), ('22', '1525831247483', '25', '3', '2.00', '2.00', '0', null, '10', null, '2018-05-09 10:00:47', '2018-05-09 10:00:47'), ('23', '1525831247756', '25', '2', '4.00', '4.00', '0', null, '40', null, '2018-05-09 10:00:47', '2018-05-09 10:30:02'), ('24', '1525917826087', '26', '2', '2.00', '2.00', '0', null, '10', null, '2018-05-10 10:03:46', '2018-05-10 10:03:46'), ('25', '1525919449632', '26', '2', '2.00', '2.00', '0', null, '40', null, '2018-05-10 10:30:49', '2018-05-10 11:59:21'), ('26', '1525919449714', '26', '3', '2.00', '2.00', '0', null, '10', null, '2018-05-10 10:30:49', '2018-05-10 10:30:49'), ('27', '1525922208562', '26', '2', '2.00', '0.00', '0', null, '0', null, '2018-05-10 11:16:48', '2018-05-10 11:21:33'), ('28', '1525924951501', '26', '2', '5.00', '5.00', '0', null, '40', null, '2018-05-10 12:02:31', '2018-05-10 12:03:11'), ('29', '1525925720438', '26', '2', '2.00', '2.00', '0', null, '10', null, '2018-05-10 12:15:20', '2018-05-10 12:15:20'), ('30', '1525936851694', '26', '2', '123.00', '0.00', '1', null, '0', '2018-05-10 15:20:52', '2018-05-10 15:20:51', '2018-05-10 15:21:39'), ('31', '1525937586857', '26', '2', '60000000.00', '60000000.00', '1', null, '40', '2018-05-10 15:33:07', '2018-05-10 15:33:06', '2018-05-17 16:59:11'), ('32', '1525939204623', '26', '2', '800000000.00', '800000000.00', '1', null, '40', '2018-05-10 16:00:05', '2018-05-10 16:00:04', '2018-05-10 16:04:17'), ('33', '1525939221128', '26', '2', '10000000.00', '10000000.00', '1', null, '40', '2018-05-10 16:00:21', '2018-05-10 16:00:21', '2018-05-10 17:48:36'), ('34', '1525939239205', '26', '2', '130000000.00', '130000000.00', '1', null, '20', '2018-05-10 16:00:39', '2018-05-10 16:00:39', '2018-05-10 16:00:39'), ('35', '1525951037537', '26', '2', '10000000.00', '0.00', '0', null, '0', null, '2018-05-10 19:17:17', '2018-05-10 19:17:21'), ('36', '1526640757242', '27', '5', '0.00', '0.00', '0', null, '10', null, '2018-05-18 18:52:37', '2018-05-18 18:52:37'), ('37', '1526640925536', '27', '5', '199998.00', '199998.00', '0', null, '10', null, '2018-05-18 18:55:25', '2018-05-18 18:55:25'), ('38', '1527845101916', '25', '2', '24.00', '24.00', '0', null, '40', null, '2018-06-01 17:25:01', '2018-06-01 17:28:50'), ('39', '1528948002464', '25', '2', '9.00', '9.00', '0', null, '40', null, '2018-06-14 11:46:42', '2018-08-07 09:44:59'), ('40', '1533535463063', '30', '4', '0.00', '0.00', '0', null, '10', null, '2018-08-06 14:04:23', '2018-08-06 14:04:23'), ('41', '1533535549246', '30', '3', '1.00', '1.00', '0', null, '10', null, '2018-08-06 14:05:49', '2018-08-06 14:05:49'), ('42', '1533537103204', '30', '3', '1.00', '0.00', '0', null, '0', null, '2018-08-06 14:31:43', '2018-08-06 17:29:21'), ('43', '1533543298860', '25', '2', '1.00', '1.00', '0', null, '40', null, '2018-08-06 16:14:58', '2018-08-06 16:19:24'), ('44', '1533546712726', '30', '2', '2.00', '2.00', '0', null, '40', null, '2018-08-06 17:11:52', '2018-08-06 17:13:41');
COMMIT;

-- ----------------------------
--  Table structure for `truck_order_buy_status`
-- ----------------------------
DROP TABLE IF EXISTS `truck_order_buy_status`;
CREATE TABLE `truck_order_buy_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `buy_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `truck_order_buy_status`
-- ----------------------------
BEGIN;
INSERT INTO `truck_order_buy_status` VALUES ('1', '44', '5', '2018-08-08 11:51:17', '2018-08-08 11:51:17');
COMMIT;

-- ----------------------------
--  Table structure for `truck_order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `truck_order_detail`;
CREATE TABLE `truck_order_detail` (
  `order_detail_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单详情id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `order_id` int(11) NOT NULL COMMENT '订单id',
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `product_no` varchar(50) DEFAULT NULL COMMENT '商品编号',
  `product_name` varchar(200) NOT NULL COMMENT '商品名称',
  `quantity` int(11) NOT NULL COMMENT '商品数量',
  `total_price` decimal(20,2) NOT NULL COMMENT '商品总价',
  `product_image` varchar(255) DEFAULT NULL COMMENT '商品图片地址',
  `current_unit_price` decimal(20,2) NOT NULL COMMENT '生成订单时的商品单价',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `shipping_id` int(11) DEFAULT NULL,
  `order_detail_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_order_detail`
-- ----------------------------
BEGIN;
INSERT INTO `truck_order_detail` VALUES ('14', '24', '13', '1046', null, 'hh', '80', '2.00', null, '1.00', '2018-04-02 14:14:16', '2018-04-02 14:14:16', '2', null), ('15', '24', '14', '1046', null, 'hh', '2', '2.00', null, '1.00', '2018-04-02 14:14:16', '2018-04-02 14:14:16', '3', null), ('16', '24', '13', '1047', null, 'hh', '80', '2.00', null, '1.00', '2018-04-02 14:14:16', '2018-04-02 14:14:16', '2', null), ('19', '25', '19', '1046', null, 'hh', '6', '6.00', 'www', '1.00', '2018-05-08 18:13:47', '2018-05-08 19:13:48', '1', '40'), ('20', '25', '20', '1046', null, 'hh', '5', '5.00', 'www', '1.00', '2018-05-08 19:16:15', '2018-05-08 19:16:15', '1', '20'), ('21', '25', '21', '1047', null, 'hh', '2', '2.00', 'hh', '1.00', '2018-05-08 19:16:15', '2018-05-08 19:16:15', '1', '20'), ('22', '25', '22', '1047', null, 'hh', '2', '2.00', 'hh', '1.00', '2018-05-09 10:00:47', '2018-05-09 10:21:49', '1', '30'), ('23', '25', '23', '1046', null, 'hh', '2', '2.00', 'www', '1.00', '2018-05-09 10:00:47', '2018-05-09 10:29:32', '1', '40'), ('24', '25', '23', '1048', null, 'wwwww', '2', '2.00', 'wwwwww', '1.00', '2018-05-09 10:00:47', '2018-05-09 10:30:01', '1', '40'), ('25', '26', '24', '1048', null, 'wwwww', '2', '2.00', 'wwwwww', '1.00', '2018-05-10 10:03:46', '2018-05-10 11:59:10', '2', '30'), ('26', '26', '25', '1048', null, 'wwwww', '2', '2.00', 'wwwwww', '1.00', '2018-05-10 10:30:49', '2018-05-10 11:59:21', '2', '40'), ('27', '26', '26', '1047', null, 'hh', '2', '2.00', 'hh', '1.00', '2018-05-10 10:30:49', '2018-05-10 10:30:49', '2', '20'), ('28', '26', '27', '1046', null, 'hh', '1', '1.00', 'www', '1.00', '2018-05-10 11:16:48', '2018-05-10 11:20:36', '2', '0'), ('29', '26', '27', '1048', null, 'wwwww', '1', '1.00', 'wwwwww', '1.00', '2018-05-10 11:16:48', '2018-05-10 11:21:33', '2', '0'), ('30', '26', '28', '1048', null, 'wwwww', '5', '5.00', 'http://cdn.ayotrust.com/upload/704746a7-8199-4c8f-9f82-649448642a43.jpg', '1.00', '2018-05-10 12:02:31', '2018-05-10 12:03:11', '2', '40'), ('31', '26', '29', '1048', null, 'wwwww', '2', '2.00', 'http://cdn.ayotrust.com/upload/704746a7-8199-4c8f-9f82-649448642a43.jpg', '1.00', '2018-05-10 12:15:20', '2018-05-10 12:15:20', '2', '20'), ('32', '26', '30', '1049', null, '123', '1', '123.00', 'http://cdn.ayotrust.com/upload/66eff950-d33e-4950-970f-afe89243db3e.jpg', '123.00', '2018-05-10 15:20:51', '2018-05-10 15:21:39', '2', '0'), ('33', '26', '31', '1048', null, 'wwwww', '6', '60000000.00', 'http://cdn.ayotrust.com/upload/704746a7-8199-4c8f-9f82-649448642a43.jpg', '10000000.00', '2018-05-10 15:33:06', '2018-05-17 16:59:11', '2', '40'), ('34', '26', '32', '1048', null, 'wwwww', '80', '800000000.00', 'http://cdn.ayotrust.com/upload/704746a7-8199-4c8f-9f82-649448642a43.jpg', '10000000.00', '2018-05-10 16:00:04', '2018-05-10 16:04:17', '2', '40'), ('35', '26', '33', '1048', null, 'wwwww', '1', '10000000.00', 'http://cdn.ayotrust.com/upload/704746a7-8199-4c8f-9f82-649448642a43.jpg', '10000000.00', '2018-05-10 16:00:21', '2018-05-10 17:48:36', '2', '40'), ('36', '26', '34', '1048', null, 'wwwww', '13', '130000000.00', 'http://cdn.ayotrust.com/upload/704746a7-8199-4c8f-9f82-649448642a43.jpg', '10000000.00', '2018-05-10 16:00:39', '2018-08-07 16:38:31', '2', '30'), ('37', '26', '35', '1048', null, 'wwwww', '1', '10000000.00', 'http://cdn.ayotrust.com/upload/704746a7-8199-4c8f-9f82-649448642a43.jpg', '10000000.00', '2018-05-10 19:17:17', '2018-05-10 19:17:21', '2', '0'), ('38', '27', '36', '1055', null, 'WHEEL LOADER', '0', '0.00', null, '66666.00', '2018-05-18 18:52:37', '2018-05-18 18:52:37', null, '20'), ('39', '27', '37', '1055', null, 'WHEEL LOADER', '3', '199998.00', null, '66666.00', '2018-05-18 18:55:25', '2018-05-18 18:55:43', null, '30'), ('40', '25', '38', '1058', null, '测试产品', '2', '24.00', null, '12.00', '2018-06-01 17:25:01', '2018-06-01 17:28:50', '1', '40'), ('41', '25', '39', '1046', null, 'hh', '9', '9.00', 'http://cdn.ayotrust.com/upload/913fd67e-c00f-486d-94c4-76d86cde8f86.jpg', '1.00', '2018-06-14 11:46:42', '2018-08-07 09:44:59', '1', '40'), ('42', '30', '40', '1054', null, 'LORRY TRUCK', '0', '0.00', null, '66666.00', '2018-08-06 14:04:23', '2018-08-06 14:04:23', null, '20'), ('43', '30', '41', '1047', null, 'hh', '1', '1.00', 'hh', '1.00', '2018-08-06 14:05:49', '2018-08-06 14:05:49', null, '20'), ('44', '30', '42', '1047', null, 'hh', '1', '1.00', 'hh', '1.00', '2018-08-06 14:31:43', '2018-08-06 17:29:21', null, '0'), ('45', '25', '43', '1046', null, 'hh', '1', '1.00', 'http://cdn.ayotrust.com/upload/913fd67e-c00f-486d-94c4-76d86cde8f86.jpg', '1.00', '2018-08-06 16:14:58', '2018-08-06 16:19:24', '1', '40'), ('46', '30', '44', '1046', null, 'hhee', '2', '2.00', 'http://cdn.ayotrust.com/upload/913fd67e-c00f-486d-94c4-76d86cde8f86.jpg', '1.00', '2018-08-06 17:11:52', '2018-08-06 17:13:41', null, '40');
COMMIT;

-- ----------------------------
--  Table structure for `truck_order_pay_status`
-- ----------------------------
DROP TABLE IF EXISTS `truck_order_pay_status`;
CREATE TABLE `truck_order_pay_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  `pay_id` int(11) DEFAULT NULL COMMENT '订单状态',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `truck_order_pay_status`
-- ----------------------------
BEGIN;
INSERT INTO `truck_order_pay_status` VALUES ('1', '44', '2', '2018-08-08 11:49:44', '2018-08-08 11:49:44');
COMMIT;

-- ----------------------------
--  Table structure for `truck_pay_status`
-- ----------------------------
DROP TABLE IF EXISTS `truck_pay_status`;
CREATE TABLE `truck_pay_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(50) DEFAULT NULL COMMENT '订单状态',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `truck_pay_status`
-- ----------------------------
BEGIN;
INSERT INTO `truck_pay_status` VALUES ('1', '货到付款(未付款)', '2018-08-08 09:22:48', '2018-08-08 09:22:48'), ('2', '货到付款(已付款)', '2018-08-08 09:23:03', '2018-08-08 09:23:03'), ('3', '在线支付(已付款)', '2018-08-08 09:23:21', '2018-08-08 09:23:21');
COMMIT;

-- ----------------------------
--  Table structure for `truck_product`
-- ----------------------------
DROP TABLE IF EXISTS `truck_product`;
CREATE TABLE `truck_product` (
  `Product_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `admin_id` int(11) NOT NULL,
  `Product_CategoryID` int(11) DEFAULT NULL COMMENT '分类ID',
  `Product_Title` varchar(100) NOT NULL DEFAULT '' COMMENT '产品标题',
  `Product_SubTitle` varchar(100) DEFAULT NULL COMMENT '产品次标题',
  `Product_Promotion` varchar(50) DEFAULT NULL COMMENT '产品促销',
  `Product_Desc` mediumtext COMMENT '产品描述',
  `Product_Weight` decimal(20,2) NOT NULL DEFAULT '1.00' COMMENT '产品重量,吨为单位',
  `Product_Size` varchar(50) NOT NULL DEFAULT '' COMMENT '产品尺寸',
  `Product_Price` decimal(20,2) NOT NULL DEFAULT '1.00',
  `Product_Status` int(2) NOT NULL DEFAULT '1',
  `Product_Stock` int(20) DEFAULT NULL,
  `Product_FirstImg` varchar(255) DEFAULT NULL,
  `Product_SubImg` mediumtext,
  `CreateTime` datetime DEFAULT NULL COMMENT '开始时间',
  `EndTime` datetime DEFAULT NULL COMMENT '结束时间',
  `stock_status` int(50) DEFAULT '2' COMMENT '0库存为0，1警戒，2正常',
  `picket_line` int(20) DEFAULT '0' COMMENT '警戒线，警戒数量',
  PRIMARY KEY (`Product_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1059 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_product`
-- ----------------------------
BEGIN;
INSERT INTO `truck_product` VALUES ('1046', '2', '4', 'hhee', '', '', '<p><br></p>', '2.00', '1,2,3', '1.00', '1', '761', 'http://cdn.ayotrust.com/upload/913fd67e-c00f-486d-94c4-76d86cde8f86.jpg', 'http://cdn.ayotrust.com/upload/913fd67e-c00f-486d-94c4-76d86cde8f86.jpg', null, '2018-08-07 17:00:54', '2', '50'), ('1047', '3', '4', 'hh', null, null, null, '2.00', '1,2,3', '1.00', '1', '24', 'http://cdn.ayotrust.com/upload/66eff950-d33e-4950-970f-afe89243db3e.jpg', 'http://cdn.ayotrust.com/upload/66eff950-d33e-4950-970f-afe89243db3e.jpg', '2018-05-08 17:39:20', '2018-08-06 17:29:21', '1', '50'), ('1048', '2', '4', 'wwwww', '', '', '<p><br></p>', '2.00', '1,1,1', '10000000.00', '1', '279', 'http://cdn.ayotrust.com/upload/6e9be5d2-bf21-4cf0-adfb-f3d30d9d5ead.jpg', 'http://cdn.ayotrust.com/upload/6e9be5d2-bf21-4cf0-adfb-f3d30d9d5ead.jpg', null, '2018-08-06 16:36:00', '2', '10'), ('1049', '2', '3', '123', '1', '1', '<p>1</p>', '1.00', '1,1,1', '123.00', '1', '200', 'http://cdn.ayotrust.com/upload/66eff950-d33e-4950-970f-afe89243db3e.jpg', 'http://cdn.ayotrust.com/upload/66eff950-d33e-4950-970f-afe89243db3e.jpg,http://cdn.ayotrust.com/upload/4615c9b6-5f2f-4de9-8240-1a5536de15c1.jpg', '2018-05-09 16:10:17', '2018-05-10 15:21:39', '2', '50'), ('1052', '5', '19', 'DUMP TRUCK 6X4 HP340', 'SHANCMAN', '30%', '<p><br></p>', '6.00', '6,6,6', '66666.00', '1', '0', 'http://cdn.ayotrust.com/upload/46796fd2-8d20-4e9a-a4b1-7e31f7fd7b08.jpg', 'http://cdn.ayotrust.com/upload/46796fd2-8d20-4e9a-a4b1-7e31f7fd7b08.jpg', null, '2018-05-18 18:06:23', '2', '50'), ('1054', '5', '20', 'LORRY TRUCK', 'SHANCMAN', '30%', '<p><br></p>', '6.00', '6,6,6', '66666.00', '1', '0', 'http://cdn.ayotrust.com/upload/46796fd2-8d20-4e9a-a4b1-7e31f7fd7b08.jpg', 'http://cdn.ayotrust.com/upload/46796fd2-8d20-4e9a-a4b1-7e31f7fd7b08.jpg', null, '2018-08-06 14:04:23', '2', '50'), ('1055', '6', '15', 'WHEEL LOADER', 'LONKING', '30%', '<p><br></p>', '100.00', '6,6,6', '66666.00', '1', '97', 'http://cdn.ayotrust.com/upload/46796fd2-8d20-4e9a-a4b1-7e31f7fd7b08.jpg', 'http://cdn.ayotrust.com/upload/46796fd2-8d20-4e9a-a4b1-7e31f7fd7b08.jpg', '2018-05-18 18:37:57', '2018-05-18 18:55:25', '2', '50'), ('1056', '6', '29', 'EXCUVTOR', 'XCMG', '30%', '<p><br></p>', '100.00', '6,6,6', '66666.00', '1', '0', 'http://cdn.ayotrust.com/upload/46796fd2-8d20-4e9a-a4b1-7e31f7fd7b08.jpg', 'http://cdn.ayotrust.com/upload/46796fd2-8d20-4e9a-a4b1-7e31f7fd7b08.jpg', '2018-05-18 18:38:57', '2018-05-18 18:38:57', '2', '50'), ('1057', '6', '31', 'BUDOLZER', 'HBXG', '30%', '<p><br></p>', '100.00', '6,6,6', '66666.00', '1', '0', 'http://cdn.ayotrust.com/upload/46796fd2-8d20-4e9a-a4b1-7e31f7fd7b08.jpg', 'http://cdn.ayotrust.com/upload/46796fd2-8d20-4e9a-a4b1-7e31f7fd7b08.jpg', '2018-05-18 18:40:32', '2018-05-18 18:40:32', '2', '50'), ('1058', '2', '4', '测试产品', '啊啊啊', '啊啊啊', '<p><br></p>', '1.00', '2,2,2', '12.00', '1', '98', 'http://cdn.ayotrust.com/upload/46796fd2-8d20-4e9a-a4b1-7e31f7fd7b08.jpg', 'http://cdn.ayotrust.com/upload/46796fd2-8d20-4e9a-a4b1-7e31f7fd7b08.jpg', '2018-08-06 17:19:39', '2018-08-06 16:36:02', '2', '50');
COMMIT;

-- ----------------------------
--  Table structure for `truck_product_component`
-- ----------------------------
DROP TABLE IF EXISTS `truck_product_component`;
CREATE TABLE `truck_product_component` (
  `component_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`component_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_product_component`
-- ----------------------------
BEGIN;
INSERT INTO `truck_product_component` VALUES ('1', '1050', 'Engine', '2018-05-18 17:12:05', '2018-05-18 17:12:05');
COMMIT;

-- ----------------------------
--  Table structure for `truck_product_conf`
-- ----------------------------
DROP TABLE IF EXISTS `truck_product_conf`;
CREATE TABLE `truck_product_conf` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `product_id` int(11) NOT NULL,
  `component_id` int(11) DEFAULT NULL,
  `param_name` varchar(200) NOT NULL COMMENT '商品名称',
  `param_value` varchar(200) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_product_conf`
-- ----------------------------
BEGIN;
INSERT INTO `truck_product_conf` VALUES ('1', '1050', '1', 'model', '6bg1', '2018-05-18 17:12:37', '2018-05-18 17:12:37'), ('2', '1050', '1', 'displacement', '15', '2018-05-18 17:12:55', '2018-05-18 17:12:55');
COMMIT;

-- ----------------------------
--  Table structure for `truck_product_stock`
-- ----------------------------
DROP TABLE IF EXISTS `truck_product_stock`;
CREATE TABLE `truck_product_stock` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `product_id` int(11) NOT NULL COMMENT '产品ID',
  `admin_id` int(11) NOT NULL,
  `warehouse_id` int(11) NOT NULL COMMENT '产品标题',
  `shop_id` int(11) DEFAULT NULL COMMENT '产品次标题',
  `stock_num` int(50) DEFAULT NULL COMMENT '产品促销',
  `stock_limit_num` int(50) DEFAULT NULL COMMENT '产品描述',
  `stock_status` int(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '开始时间',
  `update_time` datetime DEFAULT NULL COMMENT '结束时间',
  `stock_category_id` int(11) DEFAULT NULL,
  `in_stock_price` decimal(20,2) DEFAULT NULL COMMENT '入库价格',
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_product_stock`
-- ----------------------------
BEGIN;
INSERT INTO `truck_product_stock` VALUES ('1', '1046', '2', '1', '2', '480', '10', '1', '2018-04-12 12:26:35', '2018-08-06 17:57:05', '46', '100.00'), ('13', '1047', '2', '1', '2', '29', '111', '0', '2018-04-13 15:20:36', '2018-05-09 10:21:49', '46', '11.00'), ('20', '1048', '2', '1', '2', '10', '20', '1', '2018-05-09 09:57:13', '2018-08-07 16:38:31', '44', '8.00'), ('21', '1049', '2', '1', '2', '200', '15', '1', '2018-05-10 11:24:48', '2018-05-10 11:24:48', '45', '122.00'), ('22', '1048', '2', '2', '2', '245', '234', '1', '2018-05-10 11:51:31', '2018-08-07 16:26:01', '44', '234.00'), ('23', '1046', '2', '1', '2', '25', '43', '1', '2018-05-10 12:05:37', '2018-08-07 09:44:47', '45', '34.00'), ('24', '1046', '2', '2', '2', '45', '25', '1', '2018-05-10 12:06:49', '2018-05-10 12:06:49', '49', '235.00'), ('25', '1046', '2', '2', '2', '123', '11', '1', '2018-05-10 17:33:23', '2018-05-10 17:33:23', '45', '1.00'), ('26', '1046', '2', '1', '2', '1', '1', '1', '2018-05-10 17:47:46', '2018-05-10 17:47:46', '45', '1.00'), ('27', '1055', '6', '3', '5', '97', '5', '1', '2018-05-18 18:54:53', '2018-05-18 18:55:43', '45', '100.00'), ('28', '1046', '2', '4', '2', '16', '13', '1', '2018-06-01 17:17:51', '2018-06-01 17:17:51', '54', '2.00'), ('29', '1058', '2', '4', '2', '98', '20', '1', '2018-06-01 17:20:17', '2018-06-01 17:25:55', '54', '21.00'), ('30', '1046', '2', '4', '2', '10', '1', '1', '2018-08-06 17:10:25', '2018-08-06 17:10:25', '45', '12.00'), ('31', '1046', '2', '4', '2', '10', '1', '1', '2018-08-06 17:10:31', '2018-08-06 17:10:31', '45', '12.00'), ('32', '1046', '2', '1', '2', '66', '9', '1', '2018-08-06 17:42:28', '2018-08-06 17:42:28', '45', '88.00');
COMMIT;

-- ----------------------------
--  Table structure for `truck_shipping`
-- ----------------------------
DROP TABLE IF EXISTS `truck_shipping`;
CREATE TABLE `truck_shipping` (
  `shipping_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收货地址id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `receiver_name` varchar(20) NOT NULL COMMENT '收货姓名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收货固定电话',
  `receiver_mobile` varchar(20) DEFAULT NULL COMMENT '收货移动电话',
  `receiver_province` varchar(20) DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(20) DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `receiver_zip` varchar(11) DEFAULT NULL COMMENT '邮编',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`shipping_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_shipping`
-- ----------------------------
BEGIN;
INSERT INTO `truck_shipping` VALUES ('1', '25', 'hh', '123', '123', '上海', '上海', '上海', '123', '123123', null, '2018-08-07 09:31:45'), ('2', '26', 'mxj', '123123123', '123123123', 'shanghai', 'shanghai', 'pudong', 'naxian road', '123123', '2018-05-10 10:02:16', '2018-05-10 10:02:16'), ('3', '26', 'Dr.strange', '68500042', '135951225', 'USA', 'NewYork', 'Queen', 'holyHall', '123123', '2018-05-10 16:20:14', '2018-05-10 16:20:14'), ('4', '30', '1', '1', '1', '1', '1', '1', '1', '1', '2018-08-06 17:51:55', '2018-08-06 17:51:55'), ('5', '30', '1', '1', '1', '1', '1', '1', '1', '1', '2018-08-06 17:52:06', '2018-08-06 17:52:06'), ('6', '30', 'd d d', 'ddd', 'ddd', 'ddd', 'ddd', 'ddd', 'ddd', 'ddd', '2018-08-07 16:59:06', '2018-08-07 16:59:06');
COMMIT;

-- ----------------------------
--  Table structure for `truck_shop_contact`
-- ----------------------------
DROP TABLE IF EXISTS `truck_shop_contact`;
CREATE TABLE `truck_shop_contact` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '联系人id',
  `shop_id` int(11) NOT NULL,
  `contact_name` varchar(100) NOT NULL,
  `contact_position` varchar(50) DEFAULT NULL,
  `contact_email` varchar(50) DEFAULT NULL,
  `contact_phone` varchar(20) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_shop_contact`
-- ----------------------------
BEGIN;
INSERT INTO `truck_shop_contact` VALUES ('1', '2', 'lili', '老总', '12456@163.com', '1358752', '2018-04-12 12:28:15', '2018-04-12 12:28:17');
COMMIT;

-- ----------------------------
--  Table structure for `truck_shop_warehouse`
-- ----------------------------
DROP TABLE IF EXISTS `truck_shop_warehouse`;
CREATE TABLE `truck_shop_warehouse` (
  `warehouse_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `shop_id` int(11) NOT NULL,
  `warehouse_name` varchar(100) NOT NULL,
  `warehouse_desc` varchar(100) NOT NULL,
  `warehouse_phone` varchar(50) DEFAULT NULL,
  `warehouse_email` varchar(50) DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`warehouse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_shop_warehouse`
-- ----------------------------
BEGIN;
INSERT INTO `truck_shop_warehouse` VALUES ('1', '2', 'hh', 'tt', null, null, '2018-04-12 12:26:02', '2018-04-12 12:26:05'), ('2', '2', '仓库二', '河南郑州几号', null, null, '2018-04-13 16:24:43', '2018-04-13 16:24:43'), ('3', '5', '1', '1', '1', '1', '2018-05-18 18:54:14', '2018-05-18 18:54:14'), ('4', '2', '雅加达', '雅加达', '111', '111', '2018-06-01 17:14:36', '2018-06-01 17:14:36'), ('5', '2', '东北仓', '哈尔滨', '5858232323', 'hhhh@163.com', '2018-08-07 11:57:53', '2018-08-07 11:57:53');
COMMIT;

-- ----------------------------
--  Table structure for `truck_stock_alteration`
-- ----------------------------
DROP TABLE IF EXISTS `truck_stock_alteration`;
CREATE TABLE `truck_stock_alteration` (
  `alteration_id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_id` int(11) DEFAULT NULL,
  `order_detail_id` int(11) DEFAULT NULL,
  `alteration_num` int(11) DEFAULT NULL,
  `alteration_status` int(11) DEFAULT NULL COMMENT '交易状态  收入还是支出',
  `alteration_reason` int(11) DEFAULT NULL COMMENT '变动原因(充值  下单  退单)',
  `alteration_product_price` decimal(20,2) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`alteration_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Records of `truck_stock_alteration`
-- ----------------------------
BEGIN;
INSERT INTO `truck_stock_alteration` VALUES ('1', '25', null, '123', '0', '0', '1.00', '2018-05-10 17:33:23', '2018-05-10 17:33:23'), ('2', '26', null, '1', '0', '0', '1.00', '2018-05-10 17:47:46', '2018-05-10 17:47:46'), ('3', '20', '35', '1', '1', '1', '10000000.00', '2018-05-10 17:48:27', '2018-05-10 17:48:27'), ('4', '22', '33', '6', '1', '1', '10000000.00', '2018-05-17 16:58:58', '2018-05-17 16:58:58'), ('5', '27', null, '100', '0', '0', '100.00', '2018-05-18 18:54:53', '2018-05-18 18:54:53'), ('6', '27', '39', '3', '1', '1', '66666.00', '2018-05-18 18:55:43', '2018-05-18 18:55:43'), ('7', '28', null, '16', '0', '0', '2.00', '2018-06-01 17:17:51', '2018-06-01 17:17:51'), ('8', '29', null, '100', '0', '0', '21.00', '2018-06-01 17:20:17', '2018-06-01 17:20:17'), ('9', '29', '40', '2', '1', '1', '12.00', '2018-06-01 17:25:55', '2018-06-01 17:25:55'), ('10', '1', '45', '1', '1', '1', '1.00', '2018-08-06 16:18:21', '2018-08-06 16:18:21'), ('11', '30', null, '10', '0', '0', '12.00', '2018-08-06 17:10:25', '2018-08-06 17:10:25'), ('12', '31', null, '10', '0', '0', '12.00', '2018-08-06 17:10:31', '2018-08-06 17:10:31'), ('13', '1', '46', '2', '1', '1', '1.00', '2018-08-06 17:13:04', '2018-08-06 17:13:04'), ('14', '32', null, '66', '0', '0', '88.00', '2018-08-06 17:42:28', '2018-08-06 17:42:28'), ('15', '1', null, '421', '0', '0', '100.00', '2018-08-06 17:56:43', '2018-08-06 17:56:43'), ('16', '1', null, '476', '0', '0', '100.00', '2018-08-06 17:56:47', '2018-08-06 17:56:47'), ('17', '1', null, '480', '0', '0', '100.00', '2018-08-06 17:57:05', '2018-08-06 17:57:05'), ('18', '23', '41', '9', '1', '1', '1.00', '2018-08-07 09:44:47', '2018-08-07 09:44:47'), ('19', '20', '36', '12', '1', '1', '10000000.00', '2018-08-07 16:17:36', '2018-08-07 16:17:36'), ('20', '20', '36', '12', '1', '1', '10000000.00', '2018-08-07 16:26:01', '2018-08-07 16:26:01'), ('21', '20', '36', '13', '1', '1', '10000000.00', '2018-08-07 16:38:31', '2018-08-07 16:38:31');
COMMIT;

-- ----------------------------
--  Table structure for `truck_stock_category`
-- ----------------------------
DROP TABLE IF EXISTS `truck_stock_category`;
CREATE TABLE `truck_stock_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
  `admin_id` int(11) NOT NULL,
  `parent_id` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `status` tinyint(1) DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_stock_category`
-- ----------------------------
BEGIN;
INSERT INTO `truck_stock_category` VALUES ('41', '2', '0', '上海仓', '1', null, '2018-04-13 15:28:33', '2018-04-13 15:28:33'), ('42', '2', '41', '浦东1号仓库', '1', null, '2018-04-13 15:29:01', '2018-04-13 15:29:01'), ('43', '2', '42', 'a区', '1', null, '2018-04-13 15:29:26', '2018-04-13 15:29:26'), ('44', '2', '43', '11号货架', '1', null, '2018-04-13 15:29:41', '2018-04-13 15:29:41'), ('45', '2', '44', '3层', '1', null, '2018-04-13 15:29:57', '2018-04-13 15:29:57'), ('46', '2', '45', '编号111', '1', null, '2018-04-13 15:30:27', '2018-04-13 15:30:27'), ('47', '2', '41', '浦东二号仓库', '1', null, '2018-05-10 12:06:01', '2018-05-10 12:06:01'), ('48', '2', '47', 'b区', '1', null, '2018-05-10 12:06:13', '2018-05-10 12:06:13'), ('49', '2', '48', '13号', '1', null, '2018-05-10 12:06:28', '2018-05-10 12:06:28'), ('51', '2', '0', '雅加达', '1', null, '2018-06-01 17:16:18', '2018-06-01 17:16:18'), ('52', '2', '51', '北方', '1', null, '2018-06-01 17:16:28', '2018-06-01 17:16:28'), ('53', '2', '52', 'A区', '1', null, '2018-06-01 17:16:38', '2018-06-01 17:16:38'), ('54', '2', '53', '12货架', '1', null, '2018-06-01 17:16:54', '2018-06-01 17:16:54'), ('55', '2', '0', '东北仓', '1', null, '2018-08-07 11:57:54', '2018-08-07 11:57:54');
COMMIT;

-- ----------------------------
--  Table structure for `truck_user`
-- ----------------------------
DROP TABLE IF EXISTS `truck_user`;
CREATE TABLE `truck_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `email` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `role` int(4) NOT NULL COMMENT '角色0-普通用户,1-管理员，2-VIP用户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_unique` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_user`
-- ----------------------------
BEGIN;
INSERT INTO `truck_user` VALUES ('24', 'admin', '25F9E794323B453885F5181F1B624D0B', '22222@qq.com', '12345678998', '问题', '问题的答案', '1', '2018-03-14 18:03:17', '2018-03-14 18:22:05'), ('25', 'admin234', '25F9E794323B453885F5181F1B624D0B', '2222@qq.com', '12345678998', '问题', '问题的答案', '0', '2018-03-14 18:03:17', '2018-03-14 18:22:05'), ('26', 'maxiaojun', '202CB962AC59075B964B07152D234B70', 'maxiaojun', '0', null, null, '0', '2018-05-10 09:50:41', '2018-05-10 09:50:41'), ('27', 'TID', '703FB2898407D085357B23BDB7F1B113', 'TID', '0', null, null, '0', '2018-05-18 17:14:24', '2018-05-18 17:14:24'), ('28', '8080', 'D4A973E303EC37692CC8923E3148EEF7', '8080@163.com', '0', null, null, '0', '2018-05-24 16:39:17', '2018-05-24 16:39:17'), ('29', '111', '698D51A19D8A121CE581499D7B701668', '111', '0', null, null, '0', '2018-06-06 14:07:41', '2018-06-06 14:07:41'), ('30', 'sunhe', '202CB962AC59075B964B07152D234B70', 'sunhe', '0', null, null, '0', '2018-08-06 14:02:16', '2018-08-06 14:02:16');
COMMIT;

-- ----------------------------
--  Table structure for `truck_user_company`
-- ----------------------------
DROP TABLE IF EXISTS `truck_user_company`;
CREATE TABLE `truck_user_company` (
  `company_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `user_id` int(11) NOT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `company_desc` varchar(50) DEFAULT NULL,
  `company_email` varchar(50) DEFAULT NULL,
  `company_phone` varchar(20) DEFAULT NULL,
  `company_address` varchar(100) DEFAULT NULL,
  `company_headImg` varchar(255) DEFAULT NULL,
  `company_firstImg` mediumtext,
  `company_subImg` mediumtext,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  `company_account` text,
  `company_tax_card` text,
  `company_sppkp` text,
  `company_licence` text,
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_user_company`
-- ----------------------------
BEGIN;
INSERT INTO `truck_user_company` VALUES ('2', '2', '良品2553', null, null, null, null, null, null, null, '2018-04-08 13:14:09', '2018-04-08 13:14:09', '1', '', '1', '1'), ('25', '25', '良品255', '啊啊啊', '1', '1', '1', '', null, null, '2018-04-08 12:08:05', '2018-06-01 17:24:29', 'http://cdn.ayotrust.com/upload/92dda09d-f3b9-4d13-a33e-067a5d18d528.jpg', 'http://cdn.ayotrust.com/upload/78f7b76c-857d-4303-88aa-de0d3e0cbe1c.jpg', 'http://cdn.ayotrust.com/upload/fb237c98-d0ea-4cb2-963b-0ecd26a03b03.jpeg', 'http://cdn.ayotrust.com/upload/4a5ca4e5-252f-473e-b649-115d91ac7e76.jpeg'), ('26', '26', '上海剑展网络有限公司', '上海剑展网络有限公司', 'abc@jianzhan.com', '15901452245', '浦东上海', '', null, null, '2018-05-10 09:52:38', '2018-05-10 14:59:24', 'http://cdn.ayotrust.com/upload/2193ab53-b256-4131-a6d1-b32146d518d8.jpg', 'http://cdn.ayotrust.com/upload/b6e1db74-ec8e-493b-b5d0-efe98f225581.jpg', 'http://cdn.ayotrust.com/upload/13e40d98-60d0-464b-aa90-22c53b21c788.jpg', 'http://cdn.ayotrust.com/upload/0f8aee17-1d2e-41cd-b3ff-fbd32aad1d7f.jpg'), ('27', '27', 'TID', 'TID', 'TID', 'TID', 'TID', null, null, null, '2018-05-18 17:24:50', '2018-05-18 17:24:50', 'http://cdn.ayotrust.com/upload/d7a94ca5-81bb-42e4-9976-0d85a588d782.jpeg', 'http://cdn.ayotrust.com/upload/1daa339c-a47d-4703-a070-467a3308cb51.jpeg', 'http://cdn.ayotrust.com/upload/0de5f583-64d6-4a12-8483-983ee3769b69.jpeg', 'http://cdn.ayotrust.com/upload/d5877285-b8a4-4177-a6ba-42b9910dfeae.jpeg'), ('28', '30', 'sun', 'sun', '111', '111', '111', '', null, null, '2018-08-06 14:03:20', '2018-08-06 18:39:30', 'http://cdn.ayotrust.com/upload/59f5b37a-b427-49a1-8c68-aa164d229454.jpeg', 'http://cdn.ayotrust.com/upload/97d9c6d3-18d1-49aa-afd9-b8482c263abd.jpeg', 'http://cdn.ayotrust.com/upload/e290c49a-1731-4aa6-b241-1d977af9bcf3.jpeg', 'http://cdn.ayotrust.com/upload/dcde1464-a72c-450b-bd1d-47b05c03c57f.jpeg');
COMMIT;

-- ----------------------------
--  Table structure for `truck_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `truck_user_info`;
CREATE TABLE `truck_user_info` (
  `customer_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '客户编号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `company_name` varchar(100) NOT NULL COMMENT '客户公司名称',
  `duty_id` varchar(20) DEFAULT NULL COMMENT '税号',
  `cumulative_purchase` decimal(20,2) DEFAULT NULL COMMENT '累计采购额',
  `register_address` varchar(200) DEFAULT NULL COMMENT '注册地址',
  `office_address` varchar(200) DEFAULT NULL COMMENT '办公地址',
  `company_phone` varchar(20) DEFAULT NULL COMMENT '公司联系电话',
  `company_email` varchar(50) DEFAULT NULL COMMENT '公司联系邮箱',
  `company_fax` varchar(50) DEFAULT NULL COMMENT '公司传真',
  `company_contactor` varchar(20) DEFAULT NULL COMMENT '公司联系人',
  `member_level` varchar(20) DEFAULT NULL COMMENT '会员等级',
  `account_balance` decimal(20,2) DEFAULT NULL COMMENT '账户余额',
  `service_balance` decimal(20,2) DEFAULT NULL COMMENT '服务费账户余额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `company_name_unique` (`company_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `truck_user_info`
-- ----------------------------
BEGIN;
INSERT INTO `truck_user_info` VALUES ('3', '25', '上海剑展', '2500314000', '1.00', '上海', '上海浦东新区', '0221456320', 'jianhe@happy.com', '022103', '孙先生', '100', '100000000000000071.00', '0.00', '2018-03-26 17:52:00', '2018-08-06 16:14:58'), ('4', '26', '上海剑展网络有限公司', '123', '1000000013.00', '浦东上海', '123', '15901452245', 'abc@jianzhan.com', '123', '123', null, '19217.02', '0.00', '2018-05-10 14:51:48', '2018-05-10 19:17:21'), ('5', '27', 'TID', 'TID', '199998.00', 'TID', 'TID', 'TID', 'TID', 'TID', 'TID', '0', '0.00', '0.00', '2018-05-18 17:24:50', '2018-05-18 18:55:25'), ('6', '30', 'sun', '111', '3.00', '111', '111', '111', '111', '111', '111', '0', '1000.00', '0.00', '2018-08-06 14:03:20', '2018-08-06 18:39:30');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
