/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.1.51-community : Database - life
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`life` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `life`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `balance` int(11) DEFAULT '0',
  `pay_password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account` */

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_name` varchar(50) NOT NULL,
  `ad_password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

/*Table structure for table `advertisement` */

DROP TABLE IF EXISTS `advertisement`;

CREATE TABLE `advertisement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img_name` varchar(20) NOT NULL,
  `img_url` varchar(100) NOT NULL,
  `img_connect` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `advertisement` */

/*Table structure for table `apply_friend` */

DROP TABLE IF EXISTS `apply_friend`;

CREATE TABLE `apply_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `apply_id` int(11) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL,
  `reply_time` datetime DEFAULT NULL,
  `result` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `apply_id` (`apply_id`),
  CONSTRAINT `apply_friend_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `apply_friend_ibfk_2` FOREIGN KEY (`apply_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `apply_friend` */

/*Table structure for table `buyer_info` */

DROP TABLE IF EXISTS `buyer_info`;

CREATE TABLE `buyer_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '外键',
  `nickname` varchar(50) DEFAULT NULL,
  `sex` char(10) DEFAULT NULL,
  `age` char(4) DEFAULT NULL,
  `star` varchar(20) DEFAULT NULL,
  `hometown` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `autograph` varchar(200) DEFAULT NULL,
  `image` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `buyer_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `buyer_info` */

insert  into `buyer_info`(`id`,`user_id`,`nickname`,`sex`,`age`,`star`,`hometown`,`address`,`autograph`,`image`) values (1,201503,'宝贝宝贝宝贝','男','3','天秤座','山西省-长治市-长治县','福建省-福州市-仓山区','','/image/buyerhd/201503/18c6adb491de749f809f1a1188040679.png'),(2,201504,'遇见你的城',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,201505,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,201506,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,201507,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,201508,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,201512,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,201509,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,201510,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,201511,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `classify` */

DROP TABLE IF EXISTS `classify`;

CREATE TABLE `classify` (
  `id` int(11) NOT NULL,
  `classify_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `classify` */

insert  into `classify`(`id`,`classify_name`) values (1,'中国菜'),(2,'越南菜');

/*Table structure for table `collection` */

DROP TABLE IF EXISTS `collection`;

CREATE TABLE `collection` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `title` varchar(100) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `goods_id` (`seller_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `collection_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `collection_ibfk_3` FOREIGN KEY (`seller_id`) REFERENCES `seller_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `collection` */

/*Table structure for table `comments` */

DROP TABLE IF EXISTS `comments`;

CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `grade` double(2,1) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `time` varchar(30) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `seller_id` (`seller_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`seller_id`) REFERENCES `seller_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comments_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `comments` */

insert  into `comments`(`id`,`user_id`,`grade`,`comment`,`time`,`seller_id`) values (1,201504,4.5,'挺棒的。就是人少 开始以为是餐厅了 结果还是吃了饺子','2015年9月16日16:35:11',1),(2,201503,4.0,'可能是刚开店的原因 人不是很多 和朋友去吃的 两个人吃不完四人餐 分量很足 口味也很特别。服务挺好的。给个赞。','2015年9月17日19:38:45',1),(3,201504,5.0,'就是饺子不能用团购券，饺子的味道也一般般','2015年9月16日21:15:35',1),(4,201504,5.0,'就是饺子不能用团购券，饺子的味道也一般般','2015年9月16日21:15:35',1),(5,201504,5.0,'就是饺子不能用团购券，饺子的味道也一般般','2015年9月16日21:15:35',1),(6,201503,5.0,'就是饺子不能用团购券，饺子的味道也一般般','2015年9月16日21:15:35',1),(7,201504,5.0,'就是饺子不能用团购券，饺子的味道也一般般','2015年9月16日21:15:35',1),(8,201504,5.0,'就是饺子不能用团购券，饺子的味道也一般般','2015年9月16日21:15:35',1),(9,201503,5.0,'就是饺子不能用团购券，饺子的味道也一般般','2015年9月16日21:15:35',1),(10,201503,5.0,'就是饺子不能用团购券，饺子的味道也一般般','2015年9月16日21:15:35',1),(11,201504,1.1,'就是饺子不能用团购券，饺子的味道也一般般','2015年9月16日22:09:30',1),(12,201503,1.2,'就是饺子不能用团购券，饺子的味道也一般般','2015年9月16日21:15:35',1),(13,201503,1.3,'就是饺子不能用团购券，饺子的味道也一般般','2015年9月16日21:15:35',1);

/*Table structure for table `coupon` */

DROP TABLE IF EXISTS `coupon`;

CREATE TABLE `coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `original_price` int(11) NOT NULL,
  `current_price` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `public_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `seller_counts` int(11) DEFAULT '0',
  `state` int(5) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `seller_id` (`seller_id`),
  CONSTRAINT `coupon_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `seller_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `coupon` */

insert  into `coupon`(`id`,`original_price`,`current_price`,`seller_id`,`description`,`public_time`,`update_time`,`seller_counts`,`state`) values (2,40,20,1,'真便宜','2015-09-15 20:07:54','2015-09-15 20:08:21',0,0),(3,10,15,1,'北京方便面专用!','2015-09-15 20:22:30','2015-09-15 20:30:19',0,0);

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seller_id` int(11) DEFAULT NULL,
  `good_name` varchar(20) NOT NULL,
  `real_price` double(11,2) NOT NULL,
  `seller_counts` int(11) DEFAULT NULL,
  `introduction` text,
  `img` varchar(50) DEFAULT NULL,
  `recommend_num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `goods_ibfk_1` (`seller_id`),
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `seller_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`id`,`seller_id`,`good_name`,`real_price`,`seller_counts`,`introduction`,`img`,`recommend_num`) values (1,1,'青椒肉丝',10.00,2,'我的青椒肉丝，好吃，好吃，最好吃！！！',NULL,50),(2,1,'鱼香肉丝',12.00,2,'鱼香肉丝，萃取天然植物精油，大伙吃了都说好！',NULL,100);

/*Table structure for table `message` */

DROP TABLE IF EXISTS `message`;

CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `send_id` int(11) NOT NULL COMMENT '发送者',
  `reciver_id` int(11) NOT NULL COMMENT '接收者',
  `message` text NOT NULL,
  `datetime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `send_id` (`send_id`),
  KEY `reciver_id` (`reciver_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`send_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`reciver_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message` */

/*Table structure for table `opinion` */

DROP TABLE IF EXISTS `opinion`;

CREATE TABLE `opinion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `content` text,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `opinion_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `opinion` */

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL,
  `seller_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `coupon_id` int(11) DEFAULT NULL,
  `order_time` datetime DEFAULT NULL,
  `balance` int(11) DEFAULT '0',
  PRIMARY KEY (`order_id`),
  KEY `seller_id` (`seller_id`),
  KEY `user_id` (`user_id`),
  KEY `coupon_id` (`coupon_id`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `seller_info` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `order` */

/*Table structure for table `person_coupon` */

DROP TABLE IF EXISTS `person_coupon`;

CREATE TABLE `person_coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `coupon_id` int(11) NOT NULL,
  `coupon_num` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `coupon_id` (`coupon_id`),
  CONSTRAINT `person_coupon_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `person_coupon_ibfk_2` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `person_coupon` */

/*Table structure for table `refund` */

DROP TABLE IF EXISTS `refund`;

CREATE TABLE `refund` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `refund_ammount` int(11) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL,
  `return_time` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '0 表示没退 1表示已退',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `refund` */

/*Table structure for table `seller_info` */

DROP TABLE IF EXISTS `seller_info`;

CREATE TABLE `seller_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `seller_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `seller_phone` char(12) COLLATE utf8_unicode_ci DEFAULT NULL,
  `city` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `arer` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `street` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `longitude` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `latitude` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rank` double(6,1) DEFAULT '0.0',
  `classify_id` int(11) DEFAULT NULL,
  `img` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `classify_id` (`classify_id`),
  KEY `seller_id` (`user_id`),
  CONSTRAINT `seller_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `seller_info_ibfk_2` FOREIGN KEY (`classify_id`) REFERENCES `classify` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `seller_info` */

insert  into `seller_info`(`id`,`user_id`,`seller_name`,`province`,`seller_phone`,`city`,`arer`,`street`,`longitude`,`latitude`,`rank`,`classify_id`,`img`) values (1,201501,'有家酸菜鱼','江苏省','15737954116','苏州市','吴中区','仁爱路1号有家酸菜鱼','120.745715','31.278872',NULL,1,'/image/shopimage/0b465072fdba2334249759af9e6b9d6d/20151016180751.png'),(2,201502,NULL,'江苏省',NULL,'苏州市','吴中区','仁爱路2号','120.741938','31.276019',0.0,1,NULL);

/*Table structure for table `share` */

DROP TABLE IF EXISTS `share`;

CREATE TABLE `share` (
  `user_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `share_url` varchar(100) NOT NULL,
  `share_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `share_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `share` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `phone` char(12) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `type` char(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=201513 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

insert  into `user`(`u_id`,`username`,`email`,`phone`,`password`,`type`) values (201501,'debughao1','258418411@qq.com','15737954118','df10ef8509dc176d733d59549e7dbfaf','1'),(201502,'debughao2','863260364@qq.com','15737954350','df10ef8509dc176d733d59549e7dbfaf','1'),(201503,'debguhao3','1056934272@qq.com','15737954106','df10ef8509dc176d733d59549e7dbfaf','2'),(201504,'debguhao4','86325@qq.com','15737954113','df10ef8509dc176d733d59549e7dbfaf','2'),(201505,'debguhao5','86326@qq.com','15737954114','df10ef8509dc176d733d59549e7dbfaf','2'),(201506,'debguhao6','86327@qq.com','15737954115','df10ef8509dc176d733d59549e7dbfaf','2'),(201507,'debguhao7','86328@qq.com','15737954116','df10ef8509dc176d733d59549e7dbfaf','2'),(201508,'debguhao8','866231@qq.com','15737954120','df10ef8509dc176d733d59549e7dbfaf','2'),(201509,'denguaa','323232@qq.com','15784552221','df10ef8509dc176d733d59549e7dbfaf','2'),(201510,'ss312313','sfs@qq.com','15737954119','df10ef8509dc176d733d59549e7dbfaf','2'),(201511,'ss121','623@qq.com','15737954185','df10ef8509dc176d733d59549e7dbfaf','2'),(201512,'debnughao55','86326548@qq.com','15784851154','df10ef8509dc176d733d59549e7dbfaf','2');

/*Table structure for table `user_goods` */

DROP TABLE IF EXISTS `user_goods`;

CREATE TABLE `user_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `sell_time` datetime DEFAULT NULL,
  `number` int(5) NOT NULL,
  `amount` int(10) NOT NULL,
  `discount_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `user_goods_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_goods_ibfk_2` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_goods` */

/*Table structure for table `user_oauth` */

DROP TABLE IF EXISTS `user_oauth`;

CREATE TABLE `user_oauth` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `oauth_name` varchar(20) NOT NULL,
  `oauth_access_token` varchar(100) NOT NULL,
  `oauth_openid` varchar(100) NOT NULL,
  `add_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `u_id` (`user_id`),
  CONSTRAINT `user_oauth_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_oauth` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
