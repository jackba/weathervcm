--�û�����
INSERT INTO `user` VALUES ('1','super',NULL,'��������Ա',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,0,'2009-09-01 04:47:21','2009-09-01 04:47:21','111111');
--Ȩ������
insert into privilege (name,url,description,code) values("�����û�","action:UserAction.add;json:UserAction:save","�����û�","0000");
insert into privilege (name,url,description,code) values("�޸��û�","action:UserAction.modify;json:UserAction.update","�޸��û�","0001");
insert into privilege (name,url,description,code) values("ɾ���û�","dwr:UserServiceImpl.deleteUsers","ɾ���û�","0002");
insert into privilege (name,url,description,code) values("�鿴�û�","action:UserAction.list;json:UserAction.search","�鿴�û�","0003");