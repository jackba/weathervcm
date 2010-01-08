INSERT INTO `user` VALUES ('1','super',NULL,'超级管理员',NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,0,'2009-09-01 04:47:21','2009-09-01 04:47:21','111111',-1);
insert into user(user_id,login_id,user_name,sex,status,create_time,update_time,password,default_unit_id) values('2','guest','guest',1,0,now(),now(),'111111',-1);

insert into privilege (name,url,description,code) values("增加用户","action:UserAction.add;json:UserAction:save","增加用户","0000");
insert into privilege (name,url,description,code) values("修改用户","action:UserAction.modify;json:UserAction.update;dwr:UserServiceImpl.stopUser;dwr:UserServiceImpl.resumeUser;action:UserAction.beforResetPassword;action:UserAction.resetPassword","修改用户","0001");
insert into privilege (name,url,description,code) values("删除用户","dwr:UserServiceImpl.deleteUsers","删除用户","0002");
insert into privilege (name,url,description,code) values("查看用户","action:UserAction.list;json:UserAction.search;action:UserAction.detail","查看用户","0003");

insert into privilege (name,url,description,code) values("增加角色","action:RoleAction.add;json:UserAction.save","增加角色","0004");
insert into privilege (name,url,description,code) values("修改角色","action:RoleAction.modify;json:RoleAction.update","修改角色","0005");
insert into privilege (name,url,description,code) values("删除角色","dwr:RoleServiceImpl.deleteRoles","删除角色","0006");
insert into privilege (name,url,description,code) values("查看角色","action:RoleAction.list;json:RoleAction.search;action:RoleAction.detail","查看角色","0007");

insert into privilege (name,url,description,code) values("查看日志","action:LogAction.list;json:LogAction.search","查看日志","0008");

insert into privilege (name,url,description,code) values("查看会议模板","action:ServiceAction.list;json:ServiceAction.search;action:ServiceAction.detail","查看会议模板","0009");
insert into privilege (name,url,description,code) values("更新会议模板","action:ServiceAction.update","更新会议模板","0010");

insert into privilege (name,url,description,code) values("查看资源占用情况","action:ResourceAction.occupy;json:ResourceAction.searchOccupy;action:ResourceAction.occupywave;json:ResourceAction.searchOccupyWave","查看资源占用情况","0011");
insert into privilege (name,url,description,code) values("查看可用资源","action.ResourceAction.available;json:ResourceAction.searchAvailable","查看可用资源","0012");

insert into privilege (name,url,description,code) values("管理预约会议","action:ConfAction.manageReserve;json:ConfAction.searchReserves;action:ConfAction.reserveDetail","查看预约会议","0013");
insert into privilege (name,url,description,code) values("修改预约会议","action:ConfAction.modifyReserve;json:ConfAction.update","修改预约会议","0014");
insert into privilege (name,url,description,code) values("删除预约会议","dwr:ConfServiceImpl.deleteReserves","删除预约会议","0015");
insert into privilege (name,url,description,code) values("预约会议","action:ConfAction.reserveConf;json:ConfAction.save;action:ConfAction.listReserve;json:ConfAction.searchReserves;action:ConfAction.reserveDetail;action:ConfAction.modifyReserve;json:ConfAction.update;dwr:ConfServiceImpl.deleteReserves","预约会议","0016");

insert into privilege (name,url,description,code) values("查看正在召开的会议","action:ConfAction.listRunning;json:ConfAction.searchRunnings;action:ConfAction.reserveDetail","查看正在召开的会议","0017");
insert into privilege (name,url,description,code) values("查看当日会议安排","action:ConfAction.listCurrentDay;json:ConfAction.searchCurrentDays;action:ConfAction.reserveDetail","查看当日会议安排","0018");
insert into privilege (name,url,description,code) values("查看本周会议安排","action:ConfAction.listCurrentWeek;json:ConfAction.searchCurrentWeeks;action:ConfAction.reserveDetail","查看本周会议安排","0019");
insert into privilege (name,url,description,code) values("查看本月会议安排","action:ConfAction.listCurrentMonth;json:ConfAction.searchCurrentMonths;action:ConfAction.reserveDetail","查看本月会议安排","0020");
insert into privilege (name,url,description,code) values("查看所有会议安排","action:ConfAction.listAll;json:ConfAction.searchAlls;action:ConfAction.reserveDetail","查看所有会议安排","0021");


insert into privilege (name,url,description,code) values("增加虚拟房间","action:RoomAction.add;json:RoomAction.save","增加虚拟房间","0022");
insert into privilege (name,url,description,code) values("修改虚拟房间","action:RoomAction.modify;json:RoomAction.update","修改虚拟房间","0023");
insert into privilege (name,url,description,code) values("删除虚拟房间","dwr:RoomServiceImpl.deleteRooms","删除虚拟房间","0024");
insert into privilege (name,url,description,code) values("查看虚拟房间","action:RoomAction.list;json:RoomAction.search;action:RoomAction.detail","查看虚拟房间","0025");

insert into privilege (name,url,description,code) values("查看终端列表","action:TerminalAction.list;json:TerminalAction.search;action:TerminalAction.detail","查看终端列表","0026");
insert into privilege (name,url,description,code) values("更新终端列表","action:TerminalAction.update;action:TerminalAction.list;json:TerminalAction.search;action:TerminalAction.detail","更新终端列表","0027");

insert into privilege (name,url,description,code) values("增加参会单位","action:UnitAction.add;json:UnitAction.save","增加参会单位","0028");
insert into privilege (name,url,description,code) values("修改参会单位","action:UnitAction.modify;json:UnitAction.update","修改参会单位","0029");
insert into privilege (name,url,description,code) values("删除参会单位","dwr:UnitServiceImpl.deleteUnits","删除参会单位","0030");
insert into privilege (name,url,description,code) values("查看参会单位","action:UnitAction.list;json:UnitAction.search;action:UnitAction.detail","查看参会单位","0031");

insert into privilege (name,url,description,code) values("用户统计","action:StatAction.userReserveStat;action:StatAction.userDayReserveStat","用户统计","0032");

insert into privilege (name,url,description,code) values("公告发布","action:BulletinAction.add;json:BulletinAction.save","公告发布","0033");
insert into privilege (name,url,description,code) values("公告修改","action:BulletinAction.modify;json:BulletinAction.update","公告修改","0034");
insert into privilege (name,url,description,code) values("公告删除","dwr:BulletinServiceImpl.deleteBulletins","公告删除","0035");
insert into privilege (name,url,description,code) values("公告管理","action:BulletinAction.manage;json:BulletinAction.search;action:BulletinAction.detail","公告管理","0036");

insert into privilege (name,url,description,code) values("查看全部留言","action:BBSAction.listAll;action:BBSAction.search","查看全部留言","0037");
insert into privilege (name,url,description,code) values("删除留言","action:BBSAction.delete","删除留言","0038");

insert into privilege (name,url,description,code) values("系统配置","action:SysConfigAction.configModify;json:SysConfigAction.modifyConfiguration","系统配置","0039");

insert into privilege (name,url,description,code) values("会议次数统计","action:StatAction.confNumStat","会议次数统计","0040");

insert into privilege (name,url,description,code) values("会议时长统计","action:StatAction.confTypeTimeStat;json:StatAction.searchConfTypeTimeStat;action:StatAction.unitTimeStat;json:StatAction.searchUnitTimeStat;action:StatAction.confTimeStat;json:StatAction.searchConfTimeStat","会议时长统计","0041");

insert into field_desc (table_name,field_name,field_value,field_desc) values ('conference','conf_type',1,'定期会商');
insert into field_desc (table_name,field_name,field_value,field_desc) values ('conference','conf_type',2,'区域流域');
insert into field_desc (table_name,field_name,field_value,field_desc) values ('conference','conf_type',3,'临时会商');
insert into field_desc (table_name,field_name,field_value,field_desc) values ('conference','conf_type',4,'灾害会商');
insert into field_desc (table_name,field_name,field_value,field_desc) values ('conference','conf_type',5,'会议');
insert into field_desc (table_name,field_name,field_value,field_desc) values ('conference','conf_type',6,'其他');
