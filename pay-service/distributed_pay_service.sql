# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 47.106.251.47 (MySQL 5.7.23)
# Database: distributed_pay_service
# Generation Time: 2018-12-14 07:36:28 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table pay_record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `pay_record`;

CREATE TABLE `pay_record` (
  `id` bigint(22) NOT NULL AUTO_INCREMENT COMMENT '流水ID',
  `app_id` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '第三方分配的第三方ID',
  `company_id` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '公司ID',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户ID',
  `order_type` tinyint(4) DEFAULT NULL COMMENT '订单类型 1，2油卡业务 3电子码，4汽车保养',
  `order_id` bigint(11) DEFAULT NULL COMMENT '订单ID',
  `out_trade_no` varchar(45) CHARACTER SET utf8 DEFAULT NULL COMMENT '订单编号',
  `trade_no` varchar(64) CHARACTER SET utf8 DEFAULT NULL COMMENT '第三方交易号',
  `total_amount` decimal(11,2) DEFAULT NULL COMMENT '订单金额',
  `receipt_amount` decimal(11,2) DEFAULT NULL COMMENT '买方支付金额',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '支付类型，1 钱包，2支付宝App 3 微信App',
  `trade_state` tinyint(4) DEFAULT '0' COMMENT '支付状态, 0:待支付；1:支付完成，2：取消支付，3：支付逾期，4：申请退款，5：退款成功 ',
  `create_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
  `pay_at` bigint(10) DEFAULT NULL COMMENT '支付时间',
  `refund_at` bigint(10) DEFAULT NULL COMMENT '退款时间',
  `closed_at` bigint(10) DEFAULT NULL COMMENT '关闭时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户支付流水表';



# Dump of table transaction
# ------------------------------------------------------------

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `id` bigint(22) unsigned NOT NULL,
  `out_trade_no` varchar(36) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单编号',
  `pay_type` tinyint(1) unsigned DEFAULT NULL COMMENT '支付类型1钱包2支付宝3微信',
  `status` tinyint(1) DEFAULT NULL COMMENT '事务状态0待处理1已处理',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='事务表';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
