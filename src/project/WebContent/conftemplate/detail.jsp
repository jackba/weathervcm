<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>表单模板详情</title>
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
<h1>当前位置：用户个人信息管理&nbsp;&gt;&nbsp;表单模板设置&nbsp;&gt;&nbsp;<span class="position_current"> 表单模板详情</span></h1>
	<div class="search">
	<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/conftemplate_save.do">
	<input type="hidden" name="personal" value="<s:property value='#request.personal'/>"/>
	<br/>
	<table class="query">
	  <tr class="t_title">
		<th colspan="2" class="t">表单模板详情</th>
	  </tr>
	  <tr>
	    <th width="20%">模板名称：</th>
	    <td><label>
	      <s:property value='confTemplate.confTemplateName'/>
	    </label></td>
  	  </tr>
	  <tr>
	    <th width="20%">会议主题：</th>
	    <td><label>
	      <s:property value='confTemplate.subject'/>
	    </label></td>
  	  </tr>  	  
  	  <tr>
	    <th width="20%">组织单位：</th>
	    <td><label>
	      <s:property value='confTemplate.initUnit'/>
	    </label></td>
  	  </tr>
	  <tr>
	  	<th width="20%">时长：</th>
	  	<td><label>
	      <s:property value='confTemplate.timeLong'/>
	    </label></td>
	  </tr>
	  <tr>
	  	<th width="20%">主会场：</th>
	  	<td><label>
	      <s:property value='confTemplate.mainUnitName'/>
	    </label></td>
	  </tr>
	  <tr>
	  	<th width="20%">主持人：</th>
	  	<td><label>
	      <s:property value='confTemplate.presider'/>
	    </label></td>
	  </tr>
  	  <tr>
	    <th class="row1">会议类型：</th>
	    <td><label>
	      <s:property value='confTemplate.serviceTemplateDesc'/>
	    </label></td>
	  </tr>
	  <tr>
	  	<th class="row1">会议负责人：</th>
		<td><label>
	      <s:property value='confTemplate.principal'/>
	    </label></td>
	  </tr>
	  <tr>
	  	<th class="row1">负责人手机：</th>
		<td><label>
	      <s:property value='confTemplate.principalMobile'/>
	    </label></td>
	  </tr>
	  <tr>
	  	<th class="row1">联系方式：</th>
		<td><label>
	      <s:property value='confTemplate.contactMethod'/>
	    </label></td>
	  </tr>
	  <tr>
	  	<th class="row1">参加单位：</th>
		<td><label>
	      <s:property value='confTemplate.confUnitNames'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">会议密码：</th>
	    <td><label>
	      <s:property value='confTemplate.password'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">控制口令：</th>
	    <td><label>
	      <s:property value='confTemplate.controlPin'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">会议描述：</th>
	    <td><label>
	      <s:property value='confTemplate.description'/>
	    </label></td>
	  </tr>
  </table>
	  
  <br/>
  
</form>
</div><!--end of search-->
<br/>
</div><!--end of wrap-->
<script language="javascript">
var formItemSelector;
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	// window.parent.contentPanel.getActiveTab().setTitle("表单模板详情");
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
}
</script>
</body>
</html>