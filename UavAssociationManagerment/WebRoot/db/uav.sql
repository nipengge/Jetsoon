/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.5.39 : Database - uavassociationmanagerment
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`uavassociationmanagerment` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `uavassociationmanagerment`;

/*Table structure for table `backstage_user` */

DROP TABLE IF EXISTS `backstage_user`;

CREATE TABLE `backstage_user` (
  `buId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `userPassword` varchar(20) DEFAULT NULL,
  `bindAddressIp` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`buId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `backstage_user` */

insert  into `backstage_user`(`buId`,`userName`,`userPassword`,`bindAddressIp`) values (1,'szuavsys','szuavsys','127.0.0.1');

/*Table structure for table `company_information` */

DROP TABLE IF EXISTS `company_information`;

CREATE TABLE `company_information` (
  `eiId` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(30) DEFAULT NULL,
  `companyAddress` varchar(30) DEFAULT NULL,
  `companytel` varchar(20) DEFAULT NULL,
  `subordinateIndustry` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`eiId`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `company_information` */

insert  into `company_information`(`eiId`,`companyName`,`companyAddress`,`companytel`,`subordinateIndustry`) values (1,'广州激速智能航空科技有限公司','广州市天河区棠安苑6栋1701','02085662368','研发/生产/销售'),(2,'深圳市彩虹鹰无人机有限公司','深圳市福田区沙头街道深南中路6025号英龙大厦4楼405室','075588309107','研发/生产/销售'),(3,'解析设置','解析设置','0757-123456789','解析设置'),(4,'解析设置','解析设置','0757-123456789','解析设置'),(5,'解析设置','解析设置','0757-123456789','解析设置'),(6,'test','test','020-123456789','sss'),(7,'全球鹰(深圳）无人机有限公司','深圳市龙岗区龙岗大道8288号大运软件小镇60栋2楼','0755-28603099',''),(8,'深圳智航无人机有限公司','深圳市宝安区石岩街道塘头第三工业区富青山工业园3楼','18970718330','工业');

/*Table structure for table `drone_driver_info` */

DROP TABLE IF EXISTS `drone_driver_info`;

CREATE TABLE `drone_driver_info` (
  `droneDriverInfoId` int(11) NOT NULL AUTO_INCREMENT,
  `driverName` varchar(10) DEFAULT NULL,
  `idCard` varchar(18) DEFAULT NULL,
  `droneIdCard` varchar(25) DEFAULT NULL,
  `issuingUnit` varchar(30) DEFAULT NULL,
  `CompanyId` int(11) DEFAULT NULL,
  PRIMARY KEY (`droneDriverInfoId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `drone_driver_info` */

insert  into `drone_driver_info`(`droneDriverInfoId`,`driverName`,`idCard`,`droneIdCard`,`issuingUnit`,`CompanyId`) values (1,'胡争博','440115519925091663','gd66235562016081254132','AOPA-C',1),(2,'万蒙少','440225198907048567','gd66284562016081288579','AOPA-C',1),(3,'乐慈堂','440115119994091552','gd66235562016081258632','AOPA-C ',2),(4,'左朝剑','440105199007042618','gd66235562016081288562','AOPA-C',2),(5,'丁冬旭','440125198608097856','gd65634262016081285284','AOPA-C',1);

/*Table structure for table `drone_info` */

DROP TABLE IF EXISTS `drone_info`;

CREATE TABLE `drone_info` (
  `diId` int(11) NOT NULL AUTO_INCREMENT,
  `droneId` varchar(30) DEFAULT NULL,
  `droneBrand` varchar(10) DEFAULT NULL,
  `droneModel` varchar(20) DEFAULT NULL,
  `droneLoad` float DEFAULT NULL,
  `dateManufacture` date DEFAULT NULL,
  `productionLot` varchar(10) DEFAULT NULL,
  `useCompanyName` varchar(30) DEFAULT NULL,
  `industryUse` varchar(20) DEFAULT NULL,
  `operationDate` datetime DEFAULT NULL,
  `dronedriverinfoId` int(11) DEFAULT NULL,
  `droneCompanyId` int(11) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `onLine` int(11) DEFAULT NULL,
  PRIMARY KEY (`diId`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `drone_info` */

insert  into `drone_info`(`diId`,`droneId`,`droneBrand`,`droneModel`,`droneLoad`,`dateManufacture`,`productionLot`,`useCompanyName`,`industryUse`,`operationDate`,`dronedriverinfoId`,`droneCompanyId`,`latitude`,`longitude`,`onLine`) values (1,'86ae2eg0fab00f41er22385c','彩虹鹰','前拉单翼无人机',35,'2015-06-22','2015060303','湛江军分区霞山武装部','安防','2017-02-22 00:00:00',3,2,23.136285,113.375949,0),(2,'96sd2830fab00f41fb48724b','广州激速智能','JS-1601X',3,'2016-08-12','2016081204','湛江军分区霞山武装部','安防','2017-02-22 00:00:00',5,1,23.136285,113.375949,0),(3,'52fs2825df01f41sf487281e','广州激速智能','JS-1604MX',5,'2016-06-23','2016062302','湛江军分区霞山武装部','安防','2017-02-22 00:00:00',1,1,23.136285,113.375949,0),(4,'73sf8210tab55f32de58690t','广州激速智能','JS-1601TX',4.5,'2016-07-22','2016072203','湛江军分区霞山武装部','安防','2017-02-22 00:00:00',2,1,23.136285,113.375949,0),(5,'58ad2830fab00f41dd48724b','彩虹鹰','前拉单翼无人机',4.5,'2016-06-15','2016061503','南沙区环保水务局','环保','2017-02-22 00:00:00',4,2,23.136285,113.375949,0),(6,'45et7sd8gad87f41er85462e','广州激速智能','JS-1601TX',4.5,'2016-06-15','2016061502','湛江军分区霞山武装部','安防','2017-02-24 00:00:00',1,1,23.136285,113.375949,0),(7,'75ss7856abd85t53re15435t ','广州激速智能','JS-1604MX ',5,'2016-05-24','2016052401','湛江军分区霞山武装部','安防','2017-02-24 00:00:00',5,1,23.136285,113.375949,0),(8,'15df5312list854d65hf42314','广州激速智能','JS-1604MX',5,'2016-05-24','2016051204','湛江军分区霞山武装部','安防','2017-02-24 00:00:00',1,1,23.136285,113.375949,0),(9,'53es7513ils123t54rf36060y','广州激速智能','JS-1604MX',5,'2016-05-24','2016051204','南沙区环保水务局','环保','2017-02-24 00:00:00',1,1,23.136285,113.375949,0),(10,'65er3214oirs14e54rt42314 ','广州激速智能','JS-1601X',3,'2016-06-18','2016061801','南沙区环保水务局','环保','2017-02-24 00:00:00',1,1,23.136285,113.375949,0),(11,'85ts7523ta05e51sg25401g','彩虹鹰','双尾撑布局后推式无人机',25,'2015-06-21','2015062103','湛江军分区霞山武装部','安防','2017-02-24 00:00:00',4,2,23.136285,113.375949,0),(12,'72er3251er55t53sa54213e','彩虹鹰','双尾撑布局后推式无人机',25,'2015-06-21','2015062102','湛江军分区霞山武装部','安防','2017-02-24 00:00:00',3,2,23.136285,113.375949,0);

/*Table structure for table `enterprise_user` */

DROP TABLE IF EXISTS `enterprise_user`;

CREATE TABLE `enterprise_user` (
  `euId` int(11) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(20) DEFAULT NULL,
  `euPassword` varchar(20) DEFAULT NULL,
  `statu` int(11) DEFAULT NULL,
  `registerDateTime` datetime DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `enterpriseInformationId` int(11) DEFAULT NULL,
  PRIMARY KEY (`euId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `enterprise_user` */

insert  into `enterprise_user`(`euId`,`accountName`,`euPassword`,`statu`,`registerDateTime`,`role`,`enterpriseInformationId`) values (1,'jetsoon','jetsoon.cn',2,'2016-08-15 00:00:00',1,1),(2,'chyuav','chyuav.com',2,'2017-02-22 15:45:43',0,2),(3,'test','test',1,'2017-03-10 15:37:11',0,6),(4,'gluav','4006632580',2,'2017-03-10 15:41:47',0,7),(5,'smduav','smd2017',2,'2017-03-10 16:06:02',0,8);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
