--用户数据
INSERT INTO `user` VALUES ('1','super',NULL,'超级管理员',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,0,'2009-09-01 04:47:21','2009-09-01 04:47:21','111111');
--权限数据
INSERT INTO `privilege` (`privilege_id`,`name`,`url`,`description`) VALUES (1,'预约会议','action:reserveConf','预约会议');
INSERT INTO `privilege` (`privilege_id`,`name`,`url`,`description`) VALUES (2,'审核会议','action:auditConf','审核会议');
INSERT INTO `privilege` (`privilege_id`,`name`,`url`,`description`) VALUES (3,'管理留言板','action:manageBBS','管理留言板');
INSERT INTO `privilege` (`privilege_id`,`name`,`url`,`description`) VALUES (4,'管理公告栏','action:manageBulletinBoard','管理公告栏');
INSERT INTO `privilege` (`privilege_id`,`name`,`url`,`description`) VALUES (5,'查看资源占用','action:viewResourceOccupy','查看资源占用');
INSERT INTO `privilege` (`privilege_id`,`name`,`url`,`description`) VALUES (6,'查看可用资源','action:viewResourceAvailable','查看可用资源');
INSERT INTO `privilege` (`privilege_id`,`name`,`url`,`description`) VALUES (7,'查看正在召开的会议','action:viewConfOnline','查看正在召开的会议');
INSERT INTO `privilege` (`privilege_id`,`name`,`url`,`description`) VALUES (8,'查看预约会议','action:viewConfReserved','查看预约会议');
INSERT INTO `privilege` (`privilege_id`,`name`,`url`,`description`) VALUES (9,'查看会议历史','action:viewConfHistory','查看会议历史');
INSERT INTO `privilege` (`privilege_id`,`name`,`url`,`description`) VALUES (10,'会议控制','action:controlConf','会议控制');
INSERT INTO `privilege` (`privilege_id`,`name`,`url`,`description`) VALUES (11,'查看用户排行','action:viewUserOrder','查看用户排行');
INSERT INTO `privilege` (`privilege_id`,`name`,`url`,`description`) VALUES (12,'查看会议排行','action:viewConfOrder','查看会议排行');