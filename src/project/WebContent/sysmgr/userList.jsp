<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作员列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/xtheme-shallowgray.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/edgrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/pager.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/interface/userService.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/sysmgr/init-userList.js" defer="defer"></script>

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
<body >
	
<div class="wrap">
	<h1>当前位置：系统管理&nbsp;&gt;&nbsp;<span class="position_current"> 操作员管理</span></h1>
		<div id="searchArea" class="search">
			<div id="queryArea" style="display:none">			
			<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/user_search.do">
				<table border="1" width="800" class="query">
					<tr>
						<th>操作员名称：</th>
						<td>
							<input name="userName1" type="text" id="userName" class="put200" maxlength="15"/>
						</td>
						<th>操作员姓名：</th>
						<td>
							<input name="name1" type="text" id="name" class="put200" maxlength="20"/>
						</td>
						<th>操作员状态：</th>
						<td><select name="status" id="status" class="put200">
							<option value="" selected="selected">请选择</option>
							<option value="0">启用</option>
							<option value="2">禁用</option>
							<!--<option value="1">无效</option>-->
							<!--<option value="2">禁用</option>-->
							
						</select></td>
					</tr>
				</table>
				<div align="center" class="query_btn">
					<td colspan="4">
						<input type="button" value="查询" id="btnQuery"	class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="query()" /> &nbsp;&nbsp;
						<input type="button" value="重置" id="btnReset"	class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="reset()" /></td>
				</div>
			</form>
			</div>
		</div><!--end of searchArea-->
	</div><!--end of wrap-->
</body>
</html>