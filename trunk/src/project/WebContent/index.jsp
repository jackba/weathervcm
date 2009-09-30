<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自主会商平台</title>
<link rel="stylesheet" type="text/css" href="resources/css/ext-all.css">
<script language="javascript">
var loginId = "<s:property value='#session.userPrivilege.userName'/>";
</script>
<script type="text/javascript" src="resources/js/ext-base.js"></script>
<script type="text/javascript" src="resources/js/ext-all.js"></script>
<script type="text/javascript" src="resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="init-main.js"></script>
<style type="text/css">
body{font:12px Tahoma;margin:0px;text-align:center;background:#FF;}
a:link,a:visited{font-size:12px;text-decoration:none;}
a:hover{}
#center{margin:0;}
#left{ position:absolute; height:100%;top:0px; left:0px; margin:0px;padding:10px;width:70%; text-align:left;}
#right{ position:absolute; height:100%;top:0px; right:0px; margin:0px;padding:10px;width:30%; background: #FFFFCC; border:1px solid; border-color:#6593cf; text-align:left;}
#bulletinBoardTitle{ color:#FF0000;}
.menuList {
	list-style: square;
	padding-left: 30px;
	margin-top: 10px;
	color: #000000;
	font-size: 12px;
	text-decoration: underline;
	cursor: pointer;
}
.menuList span:hover {
	text-decoration: underline;
	color: blue;
}

</style>
</head>
<body>
<div id="north" style="display:none">
<table border="0" width="100%" cellpadding="0" cellspacing="0"><tr><td height="76" width="777"><img src="images/head1_01.jpg"/></td><td background="images/head1_02.jpg">&nbsp;</td><td width="233"><img src="images/head1_04.jpg"/></td></tr></table>
</div>
<div id="menus" style="display:none">
	<div id="systemMenus">
		<ul class="menuList">
			<li>
				<span id="user" href="<%=request.getContextPath()%>/user_list.do" onClick="onClickMenuItem(this)">用户管理</span>
			</li>
			<li>
				<span id="role" href="<%=request.getContextPath()%>/role_list.do" onClick="onClickMenuItem(this)">角色管理</span>
			</li>
			<li>
				<span id="log" href="#">日志管理</span>
			</li>
		</ul>
	</div>
	<div id="reserveMenus">
		<ul class="menuList">
			<li>
				<span id="reserveConf" href="#">预约会议</span>
			</li>
			<li>
				<span id="modifyReserve" href="#">变更预约申请</span>
			</li>
			<li>
				<span id="auditReserve" href="#">预约会议审批</span>
			</li>
		</ul>
	</div>
	<div id="resourceMenus">
		<ul class="menuList">
			<li>
				<span id="role" href="<%=request.getContextPath()%>/service_list.do" onClick="onClickMenuItem(this)">会议模板</span>
			</li>
			<li>
				<span id="occupation" href="#">资源占用情况</span>
			</li>
			<li>
				<span id="available" href="#">可用资源</span>
			</li>
			<li>
				<span id="currentConf" href="#">正在召开的会议</span>
			</li>
			<li>
				<span id="currentDayConf" href="#">当日会议安排</span>
			</li>
			<li>
				<span id="currentWeekConf" href="#">本周会议安排</span>
			</li>
			<li>
				<span id="currentMonthConf" href="#">本月会议安排</span>
			</li>
			<li>
				<span id="allConf" href="#">所有会议安排</span>
			</li>
		</ul>
	</div>
	
	<div id="personalMenus">
		<ul class="menuList">
			<li>
				<span id="baseInfo" href="<%=request.getContextPath()%>/user_personalModify.do" onClick="onClickMenuItem(this)">基本信息管理</span>
			</li>
			<li>
				<span id="updatePassword" href="<%=request.getContextPath()%>/user_beforChangePassword.do" onClick="onClickMenuItem(this)">修改密码</span>
			</li>
			<li>
				<span id="virtualRoom" href="<%=request.getContextPath()%>/room_list.do" onClick="onClickMenuItem(this)">虚拟房间设置</span>
			</li>
		</ul>
	</div>
	<div id="statisticsMenus">
		<ul class="menuList">
			<li>
				<span id="userSort" href="#">用户使用次数排行</span>
			</li>
			<li>
				<span id="confSort" href="#">用户会议召开次数排行</span>
			</li>
			<li>
				<span id="dayConfSort" href="#">用户单日会议召开次数排行</span>
			</li>
		</ul>
	</div>
	
	<div id="bulletinMenus">
		<ul class="menuList">
			<li>
				<span id="bulletinManage" href="<%=request.getContextPath()%>/bulletin_manage.do" onClick="onClickMenuItem(this)">公告管理</span>
			</li>
			<li>
				<span id="bulletinIssue" href="<%=request.getContextPath()%>/bulletin_add.do" onClick="onClickMenuItem(this)">公告发布</span>
			</li>
		</ul>
	</div>	
	<div id="bbsMenus">
		<ul class="menuList">
			<li>
				<span id="bbsManage" href="<%=request.getContextPath()%>/bbs_listAll.do" onClick="onClickMenuItem(this)">查看全部留言</span>
			</li>
			<li>
				<span id="messageList" href="<%=request.getContextPath()%>/bbs_listPersonal.do" onClick="onClickMenuItem(this)">查看个人留言</span>
			</li>
			<li>
				<span id="messageAdd" href="<%=request.getContextPath()%>/bbs_add.do" onClick="onClickMenuItem(this)">留言</span>
			</li>
		</ul>
	</div>
</div>
<div id="center">
<div id="left">
The date tag will allow you to format a Date in a quick and easy way. You can specify a custom format (eg. "dd/MM/yyyy hh:mm"), you can generate easy readable notations (like "in 2 hours, 14 minutes"), or you can just fall back on a predefined format with key 'struts.date.format' in your properties file.
</div>
<div id="right">
<h2 id="bulletinBoardTitle">>>最新公告</h2>
<br/>
<s:iterator value='#request.bulletinList'>
<ul>
<a href="<%=request.getContextPath()%>/bulletin_detail.do?bulletinId=<s:property value='bulletinId'/>" target="_blank"><s:property value='title'/>(<s:date name='effectiveTime' format='yyyy-MM-dd'/>)</a>
</ul>
</s:iterator>
<p align="right"><a href="<%=request.getContextPath()%>/bulletin_list.do" target="_blank">>>more</a></p>
</div>
</div>
</body>
</html>