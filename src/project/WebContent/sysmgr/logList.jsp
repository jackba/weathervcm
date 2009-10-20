<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志查询</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/edgrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/pager.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sysmgr/init-logList.js" defer="defer"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/calendar/WdatePicker.js"></script>

<style type="text/css">
.STYLE3 {
	font-size: 12px
}

#button-grid .x-panel-body {
	border: 1px solid #99bbe8;
	border-top: 0 none;
}
</style>
</head>
<body>
<div class="wrap">
	<h1>当前位置：系统管理&nbsp;&gt;&nbsp;<span class="position_current">日志管理</span></h1>
		<div id="searchArea" class="search">
			
			<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/log_search.do">
				<table border="1" width="800" class="query">
					<tr>
						<th>用户姓名：</th>
						<td>
							<input name="userName" type="text" id="userName" class="put200" maxlength="40"/>
						</td>
						<th>日志类型：</th>
						<td>
							<select name="logType" id="logType">
							<option value="-1">请选择</option>
							<option value="1">用户登录</option>
							<option value="2">用户退出</option>
							<option value="3">预约会议</option>
							<option value="4">修改会议</option>
							<option value="5">删除会议</option>
							<option value="6">创建用户</option>
							<option value="7">修改用户</option>
							<option value="8">删除用户</option>
							</select>
						</td>
					</tr>
					<tr>
						<th>起始时间：</th>
						<td>
						<input type="text" class="Wdate" id="startTime" name="startTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
						</td>
						</td>
						<th>结束时间：</th>
						<td>
						<input type="text" class="Wdate" id="endTime" name="endTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
						</td>
					</tr>
				</table>
				<div align="center" class="query_btn">
					<td colspan="2">
						<input type="button" value="查询" id="btnQuery"	class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="query()" /> &nbsp;&nbsp;
						<input type="button" value="重置" id="btnReset"	class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="reset()" /></td>
				</div>
			</form>
		</div><!--end of searchArea-->
	</div><!--end of wrap-->
</body>
</html>