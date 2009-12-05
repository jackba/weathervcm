<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表单模板列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/edgrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/pager.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/interface/confTemplateService.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/conftemplate/init-confTemplateList.js" defer="defer"></script>

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
	<h1>当前位置：用户个人信息管理&nbsp;&gt;&nbsp;<span class="position_current"> 表单模板设置</span></h1>
		<div id="searchArea" class="search">
			<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/room_search.do">
				<table border="1" width="800" class="query">
					<tr>
						<th>表单模板名称：</th>
						<td>
							<input name="templateName" type="text" id="confTemplateName" class="put200" maxlength="15"/>
						</td>
						<th>会议类型：</th>
						<td>
							<div id="service_template"></div>
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