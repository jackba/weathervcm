<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>虚拟房间详情</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>

<style type="text/css">
body{font-size:12px;}
</style>
</head>
<body style="overflow:scroll;overflow-x:hidden">
<div class="wrap">
<h1>当前位置：个人信息管理&nbsp;&gt;&nbsp;会议模板设置&nbsp;&gt;&nbsp;<span class="position_current"> 会议模板详情</span></h1>
	<div class="search">
	<br/>
	<table class="query">
	  <tr class="t_title">
		<th colspan="2" class="t">会议模板详情</th>
	  </tr>
	  <tr>
	    <th width="20%"><font color="red">&nbsp;*</font>模板名称：</th>
	    <td><label>
	      <s:property value='room.templateName'/>
	    </label></td>
  	  </tr>
  	  <tr>
	    <th class="row1"><font color="red">&nbsp;*</font>会议类型：</th>
	    <td class="row2"><label>
	      <s:property value='room.serviceTemplate'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1"><font color="red">&nbsp;*</font>会议主题：</th>
	    <td class="row2"><label>
	      <s:property value='room.subject'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">会议开始时间：</th>
	    <td class="row2"><label>
	      <s:property value='#request.startTime'/>
	    </label></td>
	  </tr>
	  <tr>
	  	<th class="row1">会议结束时间：</th>
		<td class="row2"><label>
	      <s:property value='#request.endTime'/></td>
	  </tr>
	  <tr>
	    <th class="row1">会议密码：</th>
	    <td class="row2"><label>
	      <s:property value='room.password'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">控制口令：</th>
	    <td class="row2"><label>
	      <s:property value='room.controlPin'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">备注：</th>
	    <td class="row2"><label>
	      <s:property value='room.description'/>
	    </label></td>
	  </tr>
  </table>
	  
  <br/>
</div><!--end of search-->
<br/>
</div><!--end of wrap-->
<script language="javascript">
var formItemSelector;
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	window.parent.contentPanel.getActiveTab().setTitle("会议模板详情");
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
});
</script>
</body>
</html>