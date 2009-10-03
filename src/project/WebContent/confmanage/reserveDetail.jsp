<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议预约详情</title>
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
<h1>当前位置：会议管理&nbsp;&gt;&nbsp;预约会议&nbsp;&gt;&nbsp;<span class="position_current"> 会议预约详情</span></h1>
	<div class="search">
	<br/>
	<table class="query">
	  <tr class="t_title">
		<th colspan="2" class="t">会议预约详情</th>
	  </tr>
	  <tr>
	    <th width="20%">名称：</th>
	    <td><label>
	      <s:property value='conf.subject'/>
	    </label></td>
  	  </tr>
  	  <tr>
	    <th width="20%">会议号：</th>
	    <td><label>
	      <s:property value='conf.virtualConfId'/>
	    </label></td>
  	  </tr>
  	  <tr>
	    <th class="row1">组织单位：</th>
	    <td class="row2"><label>
	      <s:property value='conf.initUnit'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">开始时间：</th>
	    <td class="row2"><label>
	      <s:property value='conf.startTime'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">时长：</th>
	    <td class="row2"><label>
	      <s:property value='conf.timeLong'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">主会场：</th>
	    <td class="row2"><label>
	      <s:property value='conf.mainUnit'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">主持人：</th>
	    <td class="row2"><label>
	      <s:property value='conf.presider'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">会议类型：</th>
	    <td class="row2"><label>
	      <s:property value='conf.serviceTemplate'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">会议负责人：</th>
	    <td class="row2"><label>
	      <s:property value='conf.principal'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">负责人手机：</th>
	    <td class="row2"><label>
	      <s:property value='conf.principalMobile'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">联系方式：</th>
	    <td class="row2"><label>
	      <s:property value='conf.contactMethod'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">参加单位：</th>
	    <td class="row2"><label>
	      <s:property value='conf.confUnit'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">会议密码：</th>
	    <td class="row2"><label>
	      <s:property value='conf.password'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">控制口令：</th>
	    <td class="row2"><label>
	      <s:property value='conf.controlPin'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">主要议题：</th>
	    <td class="row2"><label>
	      <s:property value='conf.description'/>
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
	window.parent.contentPanel.getActiveTab().setTitle("预约会议详情");
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
});
</script>
</body>
</html>