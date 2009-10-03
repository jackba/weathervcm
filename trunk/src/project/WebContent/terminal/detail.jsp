<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>终端详情</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/resources/css/ext-all.css">
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
<h1>当前位置：基本信息&nbsp;&gt;&nbsp;<span class="position_current">终端详情</span></h1>
<div class="search"><br />
<table class="query">
	<tr class="t_title">
		<th colspan="2" class="t">终端基本信息</th>
	</tr>
	<tr>
		<th width="20%">终端名称：</th>
		<td><s:property value='terminal.terminalName' />&nbsp;</td>
	</tr>
	<tr>
		<th width="20%">终端类型：</th>
		<td><s:property value='terminal.protocolDesc' />&nbsp;</td>
	</tr>
		<tr>
		<th width="20%">终端号码：</th>
		<td><s:property value='terminal.dialString' />&nbsp;</td>
	</tr>
	<tr>
		<th width="20%">带宽：</th>
		<td><s:property value='terminal.maxBandwidth' />&nbsp;</td>
	</tr>
</table>
<div class="dotLine"></div>
<div class="query_btn"><input name="button" type="button"
	onclick="window.close()" class="butt_bg1"
	onMouseOver="this.className='butt_bg1_over'"
	onMouseOut="this.className='butt_bg1'" value="返回" />
</div>
</div><!--end of search--> 
<br />
</div><!--end of wrap-->
</body>
</html>