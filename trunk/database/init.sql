--用户数据
INSERT INTO `user` VALUES ('1','super',NULL,'超级管理员',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,0,'2009-09-01 04:47:21','2009-09-01 04:47:21','111111');
--权限数据
insert into privilege (name,url,description,code) values("增加用户","action:UserAction.add;json:UserAction:save","增加用户","0000");
insert into privilege (name,url,description,code) values("修改用户","action:UserAction.modify;json:UserAction.update","修改用户","0001");
insert into privilege (name,url,description,code) values("删除用户","dwr:UserServiceImpl.deleteUsers","删除用户","0002");
insert into privilege (name,url,description,code) values("查看用户","action:UserAction.list;json:UserAction.search","查看用户","0003");