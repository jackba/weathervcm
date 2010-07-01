<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议模板详情</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/xtheme-shallowgray.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/resources/css/content.css">
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/dwr/engine.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/dwr/util.js"></script>

<style type="text/css">
body {
	font-size: 12px;
}
</style>
</head>
<body style="overflow: scroll; overflow-x: hidden">
<div class="wrap">
<h1>当前位置：会议与资源&nbsp;&gt;&nbsp;<span class="position_current">会议模板详情</span></h1>
<div class="search"><br />
<table class="query">
	<tr class="t_title">
		<th colspan="2" class="t">会议模板基本信息</th>
	</tr>
	<tr>
		<th width="20%">会议模板名称：</th>
		<td><s:property value='serviceTemplate.serviceTemplateName' />&nbsp;</td>
	</tr>
	<tr>
		<th width="20%">会议模板前缀：</th>
		<td><s:property value='serviceTemplate.servicePrefix' />&nbsp;</td>
	</tr>
	<tr>
		<th width="20%">会议模板描述：</th>
		<td><s:property value='serviceTemplate.serviceTemplateDesc' />&nbsp;</td>
	</tr>

	<tr>
		<th width="20%">会议模板带宽：</th>
		<td><s:property value='serviceTemplate.matchingRate' />&nbsp;</td>
	</tr>

	<tr>
		<th width="20%">builtInToken：</th>
		<td><s:property value="serviceTemplate.builtInToken" />&nbsp;</td>
	</tr>
	<tr>
		<th width="20%">mcuService：</th>
		<td><s:property value="serviceTemplate.serviceType" />&nbsp;</td>
	</tr>
	<tr>
		<th width="20%">switchingMode：</th>
		<td><s:property value="serviceTemplate.switchingMode" />&nbsp;</td>
	</tr>
</table>
<div class="dotLine"></div>
<div class="query_btn"><input name="button" type="button"
	onclick="window.parent.closePanel('serviceDetail_<s:property value="serviceTemplate.serviceTemplateId" />')" class="butt_bg1"
	onMouseOver="this.className='butt_bg1_over'"
	onMouseOut="this.className='butt_bg1'" value="关闭" />
</div>
</div><!--end of search--> 
<br />
</div><!--end of wrap-->
</body>
</html>