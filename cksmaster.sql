/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.28 : Database - cksmaster
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cksmaster` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cksmaster`;

/*Table structure for table `code_message` */

DROP TABLE IF EXISTS `code_message`;

CREATE TABLE `code_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` int(11) NOT NULL COMMENT '值',
  `msg` varchar(64) NOT NULL COMMENT '消息内容',
  `memo` varchar(64) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  `status` int(1) NOT NULL COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `code_message` */

insert  into `code_message`(`id`,`key`,`msg`,`memo`,`create_time`,`last_update_time`,`status`) values (1,1001,'密码不可有空格','密码不可有空格','2017-08-14 19:46:30',NULL,1),(2,1002,'手机号不正确','手机号不正确','2017-08-14 19:47:35',NULL,1),(3,1003,'该手机号尚未注册，请先注册','该手机号尚未注册，请先注册','2017-08-15 10:18:52',NULL,1),(4,1004,'手机号不存在','手机号不存在','2017-08-15 10:19:12',NULL,1),(5,1005,'密码错误','密码错误','2017-08-15 10:19:24',NULL,1),(6,1006,'手机号已存在，请重新输入','手机号已存在，请重新输入','2017-08-15 10:24:29',NULL,1),(7,1007,'该手机号已被注册','该手机号已被注册','2017-08-15 10:26:49',NULL,1),(8,1008,'用户名不能为空','用户名不能为空','2017-08-15 10:27:30',NULL,1),(9,1009,'用户名不存在','用户名不存在','2017-08-15 10:27:46',NULL,1),(10,10010,'帐号或者密码错误','帐号或者密码错误','2017-08-15 10:28:16',NULL,1),(11,10011,'验证码过期','验证码过期','2017-08-15 10:28:43',NULL,1);

/*Table structure for table `server_exception_log` */

DROP TABLE IF EXISTS `server_exception_log`;

CREATE TABLE `server_exception_log` (
  `id` int(11) NOT NULL,
  `exception` varchar(6000) DEFAULT NULL COMMENT '异常',
  `matched_path` varchar(64) DEFAULT NULL COMMENT '匹配地址',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型',
  `user_id` int(11) NOT NULL COMMENT '用户Id',
  `sys` int(11) DEFAULT NULL COMMENT '系统类型',
  `request_uri` varchar(64) DEFAULT NULL COMMENT '请求地址',
  `request_method` varchar(64) DEFAULT NULL COMMENT '请求方法',
  `request_params` varchar(64) DEFAULT NULL COMMENT '请求参数',
  `ua` varchar(64) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL COMMENT '记录时间',
  `status` int(11) DEFAULT NULL COMMENT '处理状态',
  `send_mail_status` int(11) DEFAULT NULL COMMENT '邮件发送状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `server_exception_log` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(12) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(32) NOT NULL COMMENT '邮箱',
  `address` varchar(32) NOT NULL COMMENT '地址',
  `nick_name` varchar(32) NOT NULL COMMENT '昵称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_last_time` datetime NOT NULL COMMENT '最后一次修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`user_name`,`password`,`email`,`address`,`nick_name`,`create_time`,`update_last_time`) values (1,'cks','123456','123456','上海','cks','2017-07-20 00:00:00','2017-07-20 00:00:00'),(2,'kcs','123456','123456','上海','cks','2017-07-20 10:49:39','2017-07-20 10:49:39'),(3,'kcs','123456','123456','上海','cks','2017-07-20 16:24:03','2017-07-20 16:24:03'),(4,'kcs','123456','123456','上海','cks','2017-07-20 17:45:14','2017-07-20 17:45:14'),(5,'kcs','123456','123456','上海','cks','2017-07-20 17:45:28','2017-07-20 17:45:28'),(6,'kcs','123456','123456','上海','cks','2017-07-20 18:02:01','2017-07-20 18:02:01'),(7,'kcs','123456','123456','上海','cks','2017-07-20 18:03:52','2017-07-20 18:03:52');

/*Table structure for table `user_token` */

DROP TABLE IF EXISTS `user_token`;

CREATE TABLE `user_token` (
  `token` varchar(64) NOT NULL,
  `uid` int(11) NOT NULL COMMENT '用户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` tinyint(1) NOT NULL COMMENT '状态',
  `expiry_time` datetime NOT NULL COMMENT '失效时间',
  `system_type` int(11) NOT NULL COMMENT '系统类型',
  `expiry_type` int(11) DEFAULT NULL COMMENT '失效类型',
  PRIMARY KEY (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_token` */

insert  into `user_token`(`token`,`uid`,`create_time`,`status`,`expiry_time`,`system_type`,`expiry_type`) values ('4897d5e1d0e2455395e32f6038d7db08',1,'2017-08-22 15:17:51',1,'2017-08-22 15:47:51',1,NULL),('596f6b3f090940a0a99e9626d66b1b71',10001,'2017-08-16 10:17:05',1,'2017-08-16 10:17:05',1,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
