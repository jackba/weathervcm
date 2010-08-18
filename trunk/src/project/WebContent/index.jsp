<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="com.cma.intervideo.util.UserPrivilege" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自主会商平台</title>
<link rel="stylesheet" type="text/css" href="resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/xtheme-shallowgray.css">
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
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/uxmediapak.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/uxflashpak.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/uxfusionpak.js"></script>
<script type="text/javascript" src="init-main.js"></script>

<style type="text/css">
body{font-size:14px;font-family:"Times New Roman", Times, "宋体", serif;margin:0px; padding:0px;text-align:center;background:#626670;}
a:link,a:visited{font-size:12px;text-decoration:none;}
a:hover{}
#north{margin:0;padding:0;border:0px;}
#center{margin:0; padding:0}
.bulletin {
    background-image: url(images/top_button_2_1.gif) !important;
}
.bulletinOver {
	background-image: url(images/top_button_2_2.gif) !important;
}
.menu-icon{
	background-image: url(images/menu-icon.jpg) no-repeat 5px 4px !important;
	z-index:999;
}
.help{
	background-image: url(images/top_button_3_1.gif) !important;
}
.helpOver{
	background-image: url(images/top_button_3_2.gif) !important;
}
.logout{
	background-image: url(images/top_button_4_1.gif) !important;
}
.logoutOver{
	background-image: url(images/top_button_4_2.gif) !important;
}
.refresh{
	background-image: url(images/top_button_9_1.gif) !important;
}
.refreshOver{
	background-image: url(images/top_button_9_2.gif) !important;
}
.notconnect{
	background-image: url(images/top_button_8_1.gif) !important;
}
.connect{
	background-image: url(images/top_button_1_1.gif) !important;
}
/*#left{ position:absolute; height:100%;top:0px; left:0px; margin:0px;padding:0px; width:70%; text-align:left; border:0;}
#right{ position:absolute; height:100%;top:0px; right:0px; margin:0px;padding:0px; width:30%; background: #FFFFCC; border:1px solid; border-color:#6593cf; text-align:left;}
.bulletinBoardTitle{ color:#FF0000;}*/
.menuList {
	list-style: square;
	padding-left: 40px;
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
.custom-status-text-panel{
	border-top:1px solid #99BBE8;
	border-right:1px solid #fff;
	border-bottom:1px solid #fff;
	border-left:1px solid #99BBE8;
	padding:1px 2px 2px 1px;
}

#tabPanel ul.x-tab-strip-top{
    background-color:#626670;
	background-image: url(images/main_top_bg.gif);
	border-bottom-color:#626670;
}
#tabPanel .x-tab-panel-header,#tabPanel .x-tab-panel-footer {
	background-color: #626670;
	border-color:#626670;
    overflow:hidden;
    zoom:1;
}

#tabPanel .x-tab-strip-top .x-tab-right, #tabPanel .x-tab-strip-top .x-tab-left,#tabPanel .x-tab-strip-top .x-tab-strip-inner{
	background-image: url(images/tabs-sprite.gif);
}
#tabPanel .x-tab-strip span.x-tab-strip-text {
	font:normal 12px tahoma,arial,helvetica;
	color:#C0C1C5;
}

#tabPanel .x-tab-strip-over span.x-tab-strip-text {
	color:#C0C1C5;
	font-weight:bold;
}

#tabPanel .x-tab-strip-active span.x-tab-strip-text {
	color:#C5FD88;
    font-weight:bold;
}

#tabs .x-tab-strip-top .x-tab-right, #tabs .x-tab-strip-top .x-tab-left,#tabs .x-tab-strip-top .x-tab-strip-inner{
	background-image: url(resources/images/shallowgraytheme/tabs/tabs-sprite.gif);
}
#tabs .x-tab-strip span.x-tab-strip-text {
	font:normal 11px tahoma,arial,helvetica;
	color:#8f8f8f;
}

#tabs .x-tab-strip-over span.x-tab-strip-text {
	color:#6f6f67;
}

#tabs .x-tab-strip-active span.x-tab-strip-text {
	color:#6f6f67;
    font-weight:bold;
}
#tabs .x-tab-strip-wrap UL{
	width:100%;
	padding-right:10px;
}
#tabs .x-tab-strip-wrap UL LI{
	float:right;
}

#tabs ul.x-tab-strip-top{
    background-color:#fcfcfc;
	background-image: url(../images/shallowgraytheme/tabs/tab-strip-bg.gif);
	border-bottom-color:#d4d4d4;
}
#tabs .x-tab-panel-header,#tabs .x-tab-panel-footer {
	background-color: #FFFFFF;
	border-color:#d4d4d4;
    overflow:hidden;
    zoom:1;
}
#weekPanel .x-tab-strip-wrap UL{
	width:auto;
	padding-right:inherit;
}
#weekPanel .x-tab-strip-wrap UL LI{
	float:left;
}
.mainIcon1{
	background:url(images/main_icon_1.gif) -5px -7px;
}
.mainIcon2{
	background-image:url(images/main_icon_2.gif);
}
.mainIcon3{
	background-image:url(images/main_icon_3.gif);
}
.accordion_child_top{
	background-image:url(images/left_child_top_bg.gif);
	height: 24px;
}

.accordion_child_end{
	background-image:url(images/left_child_end_bg.gif);
	height: 24px;
}
.accordion_child{
	padding-left: 18px;
	background-image: url(images/left_child__top_bg.gif);
}
.accordion_child ul{}
.accordion_child ul li{
	text-align:left;
	font-size: 12px;
	display: block;
	line-height: 24px;
	display:block;
	background-image: url(images/left_child_bg.gif);
	padding-left: 30px;
}
.accordion_child ul li span{
text-decoration: none;
color: #3c505e;
cursor: pointer;
}
.accordion_child ul li span:hover{
color: #990000;
}
</style>
</head>
<body>
<div id="north">
<table border="0" style="margin:0px" width="100%" height="124" cellpadding="0" cellspacing="0" background="images/top_bg.gif">
<tr><td height="11"></td></tr>
<tr><td height="113" align="left"><img src="images/top_main.jpg" width="995" height="113"></td></tr></table>
</div>
<div id="menus" style="display:none">
	<div id="resourceMenus">
		<div class="accordion_child_top" ></div>
    	<div class="accordion_child">  
		<ul>
		<%
			UserPrivilege up = (UserPrivilege)session.getAttribute("userPrivilege");
			if(up.hasCodePrivilege("0031")){
		%>
			<li>
				<span id="siteinfo" href="<%=request.getContextPath()%>/unit_list.do" onClick="onClickMenuItem(this)">参会单位</span>
			</li>
		<%}
			if(up.hasCodePrivilege("0026")){
		%>
			<li>
				<span id="terminalinfo" href="<%=request.getContextPath()%>/terminal_list.do" onClick="onClickMenuItem(this)">终端列表</span>
			</li>
		<%} 
			if(up.hasCodePrivilege("0009")){
		%>	
			<li>
				<span id="service" href="<%=request.getContextPath()%>/service_list.do" onClick="onClickMenuItem(this)">会议模板</span>
			</li>
		<%}
			if(up.hasCodePrivilege("0011")){
		%>
			<li>
				<span id="occupation" href="<%=request.getContextPath()%>/res_occupy.do" onClick="onClickMenuItem(this)">资源占用情况</span>
			</li>
			<li>
				<span id="occupationwave" href="<%=request.getContextPath()%>/res_occupywave.do" onClick="onClickMenuItem(this)">资源占用波动图</span>
			</li>
		<%}
			if(up.hasCodePrivilege("0012")){
		%>
			<li>
				<span id="available" href="<%=request.getContextPath()%>/res_available.do" onClick="onClickMenuItem(this)">可用资源</span>
			</li>
		<%}
			if(up.hasCodePrivilege("0032")){
		%>
			<li>
				<span id="userSort" href="<%=request.getContextPath()%>/stat_userReserveStat.do" onClick="onClickMenuItem(this)">用户使用次数排行</span>
			</li>
			<!--<li>
				<span id="confSort" href="<%=request.getContextPath()%>/stat_userReserveStat.do" onClick="onClickMenuItem(this)">用户会议召开次数排行</span>
			</li>-->
			<li><span id="dayConfSort" href="<%=request.getContextPath()%>/stat_userDayReserveStat.do" onClick="onClickMenuItem(this)">用户单日会议召开次数排行</span></li>
		<%}
			if(up.hasCodePrivilege("0040")){
		%>
			<li>
				<span id="confNumStat" href="<%=request.getContextPath()%>/stat_confNumStat.do" onClick="onClickMenuItem(this)">会议次数统计表</span>
			</li>
		<%}
			if(up.hasCodePrivilege("0041")){
		%>
			<li>
				<span id="confTypeTimeStat" href="<%=request.getContextPath()%>/stat_confTypeTimeStat.do" onClick="onClickMenuItem(this)">会议类型时长统计</span>
			</li>
			<li>
				<span id="unitTimeStat" href="<%=request.getContextPath()%>/stat_unitTimeStat.do" onClick="onClickMenuItem(this)">主会场会议时长统计</span>
			</li>
			<li>
				<span id="confTimeStat" href="<%=request.getContextPath()%>/stat_confTimeStat.do" onClick="onClickMenuItem(this)">会议时长排行榜</span>
			</li>
		<%} %>
		</ul>
		</div> <div class="accordion_child_end" ></div>
	</div>
	
	<div id="scheduleConfMenus">
		<div class="accordion_child_top" ></div>
    	<div class="accordion_child">  
		<ul>
			<%
				if(up.hasCodePrivilege("0016")){
			%>
			<li>
				<span id="scheduleConf" href="<%=request.getContextPath()%>/conf_listReserve.do" onClick="onClickMenuItem(this)">预约会议</span>
			</li>
			<li>
				<span id="scheduleRecurrence" href="<%=request.getContextPath()%>/conf_listRecurrence.do" onClick="onClickMenuItem(this)">预约例会</span>
			</li>
			<%} %>
		</ul>
		</div> <div class="accordion_child_end" ></div>  
	</div>
	
	<div id="moidfyConfMenus">
		<div class="accordion_child_top" ></div>
    	<div class="accordion_child">  
		<ul>
		<%
			if(up.hasCodePrivilege("0013")){
		%>
			<li>
				<span id="manageConf" href="<%=request.getContextPath()%>/conf_manageReserve.do" onClick="onClickMenuItem(this)">预约管理</span>
			</li>
			<li>
				<span id="manageRecurrence" href="<%=request.getContextPath() %>/conf_manageRecurrence.do" onClick="onClickMenuItem(this)">例会管理</span>
			</li>
		<%} %>
		</ul>
		</div> <div class="accordion_child_end" ></div>
	</div>
	
	<div id="confMgmtMenus">
		<div class="accordion_child_top" ></div>
    	<div class="accordion_child">  
		<ul>
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
		</div> <div class="accordion_child_end" ></div>
	</div>
	
	<div id="personalMenus">
		<div class="accordion_child_top" ></div>
    	<div class="accordion_child">  
		<ul>
		<%
			if(!up.getLoginId().equals("guest")){
		%>
			<li>
				<span id="baseInfo" href="<%=request.getContextPath()%>/user_personalModify.do" onClick="onClickMenuItem(this)">个人设置</span>
			</li>
			<li>
				<span id="updatePassword" href="<%=request.getContextPath()%>/user_beforChangePassword.do" onClick="onClickMenuItem(this)">修改密码</span>
			</li>
			<li>
				<span id="conftemplate" href="<%=request.getContextPath()%>/conftemplate_list.do" onClick="onClickMenuItem(this)">表单模板</span>
			</li>
		<%} %>
		</ul>
		</div> <div class="accordion_child_end" ></div>
	</div>
	
	<div id="systemConfigMenus">
		<div class="accordion_child_top" ></div>
    	<div class="accordion_child">  
		<ul>
			<%
			if(up.hasCodePrivilege("0003")){
			%>
			<li>
				<span id="user" href="<%=request.getContextPath()%>/user_list.do" onClick="onClickMenuItem(this)">用户管理</span>
			</li>
			<%} 
			if(up.hasCodePrivilege("0007")){
			%>
			<li>
				<span id="role" href="<%=request.getContextPath()%>/role_list.do" onClick="onClickMenuItem(this)">角色管理</span>
			</li>
			<%}
			if(up.hasCodePrivilege("0008")){
			%>
			<li>
				<span id="log" href="<%=request.getContextPath()%>/log_list.do" onClick="onClickMenuItem(this)">日志管理</span>
			</li>
			<%}
			if(up.hasCodePrivilege("0039")){
			%>
			<li>
				<span id="config" href="<%=request.getContextPath()%>/config_configModify.do" onClick="onClickMenuItem(this)">系统配置</span>
			</li>
			<%} %>
		</ul>
		</div> <div class="accordion_child_end" ></div>
	</div>
	
	<div id="bulletinMenus">
		<div class="accordion_child_top" ></div>
    	<div class="accordion_child">  
		<ul>
		<!--  
			<li>
				<span id="bulletinList" href="<%=request.getContextPath()%>/bulletin_list.do" onClick="onClickMenuItem(this)">最新公告</span>
			</li>
		-->
			<%
			if(up.hasCodePrivilege("0036")){
			%>
			<li>
				<span id="bulletinManage" href="<%=request.getContextPath()%>/bulletin_manage.do" onClick="onClickMenuItem(this)">公告管理</span>
			</li>
			<%}
			if(up.hasCodePrivilege("0033")){
			%>
			<li>
				<span id="bulletinIssue" href="<%=request.getContextPath()%>/bulletin_add.do" onClick="onClickMenuItem(this)">公告发布</span>
			</li>
			<%} %>
		</ul>
		</div> <div class="accordion_child_end" ></div>
	</div>
	
	<div id="bbsMenus">
		<div class="accordion_child_top" ></div>
    	<div class="accordion_child">  
		<ul>
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
		</div> <div class="accordion_child_end" ></div>
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
			<div id="queryArea" style="display:none">
			<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/res_searchAvailable.do">
			<input id="serviceTemplate" type="hidden" name="serviceTemplate" value="10002"/>
				<table border="1" class="query">
					<tr>
						<!--<th>会议类型：</th>
						<td>
							<div id="service_template"></div>
						</td>-->
						<th width="25%" align="right">时间间隔（分钟）：</th>
						<td width="25%">
						<select name="interval" id="interval">
						<option value="60">60</option>
						<option value="30" selected="true">30</option>
						<option value="15">15</option>
						</select>
						</td>
						<th width="25%" align="right">日期：</th>
						<td width="25%">
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
			</div>
		</div><!--end of searchArea-->
		<div id="runningArea" class="search" style="display:none"></div>
		<div id="agendaArea" class="search" style="display:none">
			<div id="agendaQueryArea" style="display:none">
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
<iframe id="bulletin" style="display:none" frameborder="0" width="100%" height="100%" src="<%=request.getContextPath()%>/bulletin_list.do"></iframe>
<div id="newsDiv">......</div>
</body>
</html>