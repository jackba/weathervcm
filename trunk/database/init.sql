INSERT INTO `user` VALUES ('1','super',NULL,'��������Ա',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,0,'2009-09-01 04:47:21','2009-09-01 04:47:21','111111',-1);
insert into user(user_id,login_id,user_name,sex,status,create_time,update_time,password,default_unit_id) values('2','guest','guest',1,0,now(),now(),'111111',-1);

insert into privilege (name,url,description,code) values("�����û�","action:UserAction.add;json:UserAction:save","�����û�","0000");
insert into privilege (name,url,description,code) values("�޸��û�","action:UserAction.modify;json:UserAction.update;dwr:UserServiceImpl.stopUser;dwr:UserServiceImpl.resumeUser;action:UserAction.beforResetPassword;action:UserAction.resetPassword","�޸��û�","0001");
insert into privilege (name,url,description,code) values("ɾ���û�","dwr:UserServiceImpl.deleteUsers","ɾ���û�","0002");
insert into privilege (name,url,description,code) values("�鿴�û�","action:UserAction.list;json:UserAction.search;action:UserAction.detail","�鿴�û�","0003");

insert into privilege (name,url,description,code) values("���ӽ�ɫ","action:RoleAction.add;json:UserAction.save","���ӽ�ɫ","0004");
insert into privilege (name,url,description,code) values("�޸Ľ�ɫ","action:RoleAction.modify;json:RoleAction.update","�޸Ľ�ɫ","0005");
insert into privilege (name,url,description,code) values("ɾ����ɫ","dwr:RoleServiceImpl.deleteRoles","ɾ����ɫ","0006");
insert into privilege (name,url,description,code) values("�鿴��ɫ","action:RoleAction.list;json:RoleAction.search;action:RoleAction.detail","�鿴��ɫ","0007");

insert into privilege (name,url,description,code) values("�鿴��־","action:LogAction.list;json:LogAction.search","�鿴��־","0008");

insert into privilege (name,url,description,code) values("�鿴����ģ��","action:ServiceAction.list;json:ServiceAction.search;action:ServiceAction.detail","�鿴����ģ��","0009");
insert into privilege (name,url,description,code) values("���»���ģ��","action:ServiceAction.update","���»���ģ��","0010");

insert into privilege (name,url,description,code) values("�鿴��Դռ�����","action:ResourceAction.occupy;json:ResourceAction.searchOccupy;action:ResourceAction.occupywave;json:ResourceAction.searchOccupyWave","�鿴��Դռ�����","0011");
insert into privilege (name,url,description,code) values("�鿴������Դ","action.ResourceAction.available;json:ResourceAction.searchAvailable","�鿴������Դ","0012");

insert into privilege (name,url,description,code) values("����ԤԼ����","action:ConfAction.manageReserve;json:ConfAction.searchReserves;action:ConfAction.reserveDetail","�鿴ԤԼ����","0013");
insert into privilege (name,url,description,code) values("�޸�ԤԼ����","action:ConfAction.modifyReserve;json:ConfAction.update","�޸�ԤԼ����","0014");
insert into privilege (name,url,description,code) values("ɾ��ԤԼ����","dwr:ConfServiceImpl.deleteReserves","ɾ��ԤԼ����","0015");
insert into privilege (name,url,description,code) values("ԤԼ����","action:ConfAction.reserveConf;json:ConfAction.save;action:ConfAction.listReserve;json:ConfAction.searchReserves;action:ConfAction.reserveDetail;action:ConfAction.modifyReserve;json:ConfAction.update;dwr:ConfServiceImpl.deleteReserves","ԤԼ����","0016");

insert into privilege (name,url,description,code) values("�鿴�����ٿ��Ļ���","action:ConfAction.listRunning;json:ConfAction.searchRunnings;action:ConfAction.reserveDetail","�鿴�����ٿ��Ļ���","0017");
insert into privilege (name,url,description,code) values("�鿴���ջ��鰲��","action:ConfAction.listCurrentDay;json:ConfAction.searchCurrentDays;action:ConfAction.reserveDetail","�鿴���ջ��鰲��","0018");
insert into privilege (name,url,description,code) values("�鿴���ܻ��鰲��","action:ConfAction.listCurrentWeek;json:ConfAction.searchCurrentWeeks;action:ConfAction.reserveDetail","�鿴���ܻ��鰲��","0019");
insert into privilege (name,url,description,code) values("�鿴���»��鰲��","action:ConfAction.listCurrentMonth;json:ConfAction.searchCurrentMonths;action:ConfAction.reserveDetail","�鿴���»��鰲��","0020");
insert into privilege (name,url,description,code) values("�鿴���л��鰲��","action:ConfAction.listAll;json:ConfAction.searchAlls;action:ConfAction.reserveDetail","�鿴���л��鰲��","0021");


insert into privilege (name,url,description,code) values("�������ⷿ��","action:RoomAction.add;json:RoomAction.save","�������ⷿ��","0022");
insert into privilege (name,url,description,code) values("�޸����ⷿ��","action:RoomAction.modify;json:RoomAction.update","�޸����ⷿ��","0023");
insert into privilege (name,url,description,code) values("ɾ�����ⷿ��","dwr:RoomServiceImpl.deleteRooms","ɾ�����ⷿ��","0024");
insert into privilege (name,url,description,code) values("�鿴���ⷿ��","action:RoomAction.list;json:RoomAction.search;action:RoomAction.detail","�鿴���ⷿ��","0025");

insert into privilege (name,url,description,code) values("�鿴�ն��б�","action:TerminalAction.list;json:TerminalAction.search;action:TerminalAction.detail","�鿴�ն��б�","0026");
insert into privilege (name,url,description,code) values("�����ն��б�","action:TerminalAction.update;action:TerminalAction.list;json:TerminalAction.search;action:TerminalAction.detail","�����ն��б�","0027");

insert into privilege (name,url,description,code) values("���Ӳλᵥλ","action:UnitAction.add;json:UnitAction.save","���Ӳλᵥλ","0028");
insert into privilege (name,url,description,code) values("�޸Ĳλᵥλ","action:UnitAction.modify;json:UnitAction.update","�޸Ĳλᵥλ","0029");
insert into privilege (name,url,description,code) values("ɾ���λᵥλ","dwr:UnitServiceImpl.deleteUnits","ɾ���λᵥλ","0030");
insert into privilege (name,url,description,code) values("�鿴�λᵥλ","action:UnitAction.list;json:UnitAction.search;action:UnitAction.detail","�鿴�λᵥλ","0031");

insert into privilege (name,url,description,code) values("�û�ͳ��","action:StatAction.userReserveStat;action:StatAction.userDayReserveStat","�û�ͳ��","0032");

insert into privilege (name,url,description,code) values("���淢��","action:BulletinAction.add;json:BulletinAction.save","���淢��","0033");
insert into privilege (name,url,description,code) values("�����޸�","action:BulletinAction.modify;json:BulletinAction.update","�����޸�","0034");
insert into privilege (name,url,description,code) values("����ɾ��","dwr:BulletinServiceImpl.deleteBulletins","����ɾ��","0035");
insert into privilege (name,url,description,code) values("�������","action:BulletinAction.manage;json:BulletinAction.search;action:BulletinAction.detail","�������","0036");

insert into privilege (name,url,description,code) values("�鿴ȫ������","action:BBSAction.listAll;action:BBSAction.search","�鿴ȫ������","0037");
insert into privilege (name,url,description,code) values("ɾ������","action:BBSAction.delete","ɾ������","0038");

insert into privilege (name,url,description,code) values("ϵͳ����","action:SysConfigAction.configModify;json:SysConfigAction.modifyConfiguration","ϵͳ����","0039");

insert into privilege (name,url,description,code) values("�������ͳ��","action:StatAction.confNumStat","�������ͳ��","0040");

insert into privilege (name,url,description,code) values("����ʱ��ͳ��","action:StatAction.confTypeTimeStat;json:StatAction.searchConfTypeTimeStat;action:StatAction.unitTimeStat;json:StatAction.searchUnitTimeStat;action:StatAction.confTimeStat;json:StatAction.searchConfTimeStat","����ʱ��ͳ��","0041");

insert into field_desc (table_name,field_name,field_value,field_desc) values ('conference','conf_type',1,'���ڻ���');
insert into field_desc (table_name,field_name,field_value,field_desc) values ('conference','conf_type',2,'��������');
insert into field_desc (table_name,field_name,field_value,field_desc) values ('conference','conf_type',3,'��ʱ����');
insert into field_desc (table_name,field_name,field_value,field_desc) values ('conference','conf_type',4,'�ֺ�����');
insert into field_desc (table_name,field_name,field_value,field_desc) values ('conference','conf_type',5,'����');
insert into field_desc (table_name,field_name,field_value,field_desc) values ('conference','conf_type',6,'����');
