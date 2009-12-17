<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自主会商平台</title>
<link rel="stylesheet" type="text/css" href="resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/edgrid.css"/>
<script language="javascript">
var loginId = "<s:property value='#session.userPrivilege.loginId'/>";
</script>
<script type="text/javascript" src="resources/js/ext-base.js"></script>
<script type="text/javascript" src="resources/js/ext-all.js"></script>
<script type="text/javascript" src="resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/pager.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="init-main.js"></script>
<style type="text/css">
body{font:12px Tahoma;margin:0px; padding:0px;text-align:center;background:#FF;}
a:link,a:visited{font-size:12px;text-decoration:none;}
a:hover{}
#north{margin:0;padding:0;}
#center{margin:0; padding:0}
/*#left{ position:absolute; height:100%;top:0px; left:0px; margin:0px;padding:0px; width:70%; text-align:left; border:0;}
#right{ position:absolute; height:100%;top:0px; right:0px; margin:0px;padding:0px; width:30%; background: #FFFFCC; border:1px solid; border-color:#6593cf; text-align:left;}
.bulletinBoardTitle{ color:#FF0000;}*/
.menuList {
	list-style: square;
	padding-left: 30px;
	margin-top: 10px;
	color: #000000;
	font-size: 12px;
	text-decoration: underline;
	cursor: pointer;
	text-align:left;
}
.menuList span:hover {
	text-decoration: underline;
	color: blue;
}

</style>
</head>
<body>
<div id="north">
<table border="0" width="100%" cellpadding="0" cellspacing="0"><tr><td background="images/head1_01.jpg" height="76" width="777">&nbsp;</td><td background="images/head1_02.jpg">&nbsp;</td><td background="images/head1_04.jpg" width="233">&nbsp;</td></tr></table>
</div>
<div id="menus" style="display:none">
	<div id="resourceMenus">
		<ul class="menuList">
			<li>
				<span id="siteinfo" href="<%=request.getContextPath()%>/unit_list.do" onClick="onClickMenuItem(this)">参会单位</span>
			</li>
			<li>
				<span id="terminalinfo" href="<%=request.getContextPath()%>/terminal_list.do" onClick="onClickMenuItem(this)">终端列表</span>
			</li>
			
			<li>
				<span id="service" href="<%=request.getContextPath()%>/service_list.do" onClick="onClickMenuItem(this)">会议模板</span>
			</li>
			<li>
				<span id="occupation" href="<%=request.getContextPath()%>/res_occupy.do" onClick="onClickMenuItem(this)">资源占用情况</span>
			</li>
			<li>
				<span id="occupationwave" href="<%=request.getContextPath()%>/res_occupywave.do" onClick="onClickMenuItem(this)">资源占用波动图</span>
			</li>
			<li>
				<span id="available" href="<%=request.getContextPath()%>/res_available.do" onClick="onClickMenuItem(this)">可用资源</span>
			</li>
			
			<li>
				<span id="userSort" href="<%=request.getContextPath()%>/stat_userReserveStat.do" onClick="onClickMenuItem(this)">用户使用次数排行</span>
			</li>
			<li>
				<span id="confSort" href="<%=request.getContextPath()%>/stat_userReserveStat.do" onClick="onClickMenuItem(this)">用户会议召开次数排行</span>
			</li>
			<li>
				<span id="dayConfSort" href="<%=request.getContextPath()%>/stat_userDayReserveStat.do" onClick="onClickMenuItem(this)">用户单日会议召开次数排行</span>
			</li>
			<li>
				<span id="confNumStat" href="<%=request.getContextPath()%>/stat_confNumStat.do" onClick="onClickMenuItem(this)">会议次数统计表</span>
			</li>
			<li>
				<span id="confTypeTimeStat" href="<%=request.getContextPath()%>/stat_confTypeTimeStat.do" onClick="onClickMenuItem(this)">会议类型时长统计</span>
			</li>
			<li>
				<span id="unitTimeStat" href="<%=request.getContextPath()%>/stat_unitTimeStat.do" onClick="onClickMenuItem(this)">主会场会议时长统计</span>
			</li>
		</ul>
	</div>
	
	<div id="scheduleConfMenus">
		<ul class="menuList">
			<li>
				<span id="scheduleConf" href="<%=request.getContextPath()%>/conf_listReserve.do" onClick="onClickMenuItem(this)">预约会议</span>
			</li>
		</ul>
	</div>
	
	<div id="moidfyConfMenus">
		<ul class="menuList">
			<li>
				<span id="manageConf" href="<%=request.getContextPath()%>/conf_manageReserve.do" onClick="onClickMenuItem(this)">预约管理</span>
			</li>
		</ul>
	</div>
	
	<div id="confMgmtMenus">
		<ul class="menuList">
			<li>
				<span id="currentConf" href="<%=request.getContextPath()%>/conf_listRunning.do" onClick="onClickMenuItem(this)">正在召开的会议</span>
			</li>
			<li>
				<span id="currentDayConf" href="<%=request.getContextPath()%>/conf_listCurrentDay.do" onClick="onClickMenuItem(this)">当日会议安排</span>
			</li>
			<li>
				<span id="currentWeekConf" href="<%=request.getContextPath()%>/conf_listCurrentWeek.do" onClick="onClickMenuItem(this)">本周会议安排</span>
			</li>
			<li>
				<span id="currentMonthConf" href="<%=request.getContextPath()%>/conf_listCurrentMonth.do" onClick="onClickMenuItem(this)">本月会议安排</span>
			</li>
			<li>
				<span id="allConf" href="<%=request.getContextPath()%>/conf_listAll.do" onClick="onClickMenuItem(this)">所有会议安排</span>
			</li>
		</ul>
	</div>
	
	<div id="personalMenus">
		<ul class="menuList">
			<li>
				<span id="baseInfo" href="<%=request.getContextPath()%>/user_personalModify.do" onClick="onClickMenuItem(this)">个人设置</span>
			</li>
			<li>
				<span id="updatePassword" href="<%=request.getContextPath()%>/user_beforChangePassword.do" onClick="onClickMenuItem(this)">修改密码</span>
			</li>
			<li>
				<span id="conftemplate" href="<%=request.getContextPath()%>/conftemplate_list.do" onClick="onClickMenuItem(this)">表单模板</span>
			</li>
		</ul>
	</div>
	
	<div id="systemConfigMenus">
		<ul class="menuList">
			<li>
				<span id="user" href="<%=request.getContextPath()%>/user_list.do" onClick="onClickMenuItem(this)">用户管理</span>
			</li>
			<li>
				<span id="role" href="<%=request.getContextPath()%>/role_list.do" onClick="onClickMenuItem(this)">角色管理</span>
			</li>
			<li>
				<span id="log" href="<%=request.getContextPath()%>/log_list.do" onClick="onClickMenuItem(this)">日志管理</span>
			</li>
			<li>
				<span id="config" href="<%=request.getContextPath()%>/config_configModify.do" onClick="onClickMenuItem(this)">系统配置</span>
			</li>
		</ul>
	</div>
	
	<div id="bulletinMenus">
		<ul class="menuList">
			<li>
				<span id="bulletinList" href="<%=request.getContextPath()%>/bulletin_list.do" onClick="onClickMenuItem(this)">最新公告</span>
			</li>
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
	
	<div id="helpMenus">
		<ul class="menuList">
	
		</ul>
	</div>
</div>
<div id="center">
<!--div id="left"-->
<div class="wrap">
		<div id="searchArea" class="search">
			
			<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/res_searchAvailable.do">
			<input id="serviceTemplate" type="hidden" name="serviceTemplate" value="10002"/>
				<table border="1" class="query">
					<tr>
						<!--<th>会议类型：</th>
						<td>
							<div id="service_template"></div>
						</td>-->
						<th>时间间隔（分钟）：</th>
						<td>
						<select name="interval" id="interval">
						<option value="60">60</option>
						<option value="30" selected="true">30</option>
						<option value="15">15</option>
						</select>
						</td>
						<th>日期：</th>
						<td>
						<!--<input type="text" class="Wdate" id="day" name="day" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>-->
						<div id="dayDiv"></div>
						</td>
					</tr>
				</table>
				<div align="center" class="query_btn">
						<input type="button" value="查询" id="btnQuery"	class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="query()" /> &nbsp;&nbsp;
						<input type="button" value="重置" id="btnReset"	class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="reset()" />
				</div>
			</form>
		</div><!--end of searchArea-->
		<div id="runningArea" class="search"></div>
		<div id="agendaArea" class="search">
			<form name="form2" id="form2" method="post" action="#">
				<table border="1" class="query">
					<tr>
						<th>日期：</th>
						<td>
						<input type="text" class="Wdate" id="day2" name="day2" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
						</td>
						<th>显示方式：</th>
						<td>
						<select name="displayType" id="displayType">
						<option value="1" selected="selected">按周显示</option>
						<option value="2">按天显示</option>
						</select>
						</td>
					</tr>
				</table>
				<div align="center" class="query_btn">
						<input type="button" value="查询" id="btnQuery"	class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="query2()" /> &nbsp;&nbsp;
						<input type="button" value="重置" id="btnReset"	class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="reset2()" />
				</div>
			</form>
		</div>
	</div><!--end of wrap-->
<!--/div-->
<!--
<div id="right">
<h1 class="bulletinBoardTitle">>>最新公告</h1>
<div class="search">
<s:iterator value='#request.bulletinList'>
<ul>
<a href="<%=request.getContextPath()%>/bulletin_detail.do?bulletinId=<s:property value='bulletinId'/>" target="_blank"><s:property value='title'/>(<s:date name='effectiveTime' format='yyyy-MM-dd'/>)</a>
</ul>
</s:iterator>
<p align="right"><a href="<%=request.getContextPath()%>/bulletin_list.do" target="_blank">>>more</a></p>
</div>
</div>
-->
</div>
</body>
</html>