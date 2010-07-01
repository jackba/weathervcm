<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单位详情</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/xtheme-shallowgray.css">
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
<h1>当前位置：基本信息&nbsp;&gt;&nbsp;参会单位nbsp;&gt;&nbsp;<span class="position_current"> 单位详情</span></h1>
	<div class="search">
	<br/>
	<table class="query">
	  <tr class="t_title">
		<th colspan="2" class="t">单位详情</th>
	  </tr>
	  <tr>
	    <th width="20%">单位名称：</th>
	    <td><label>
	      <s:property value='unit.unitName'/>
	    </label></td>
  	  </tr>
  	  <tr>
	    <th width="20%">终端：</th>
	    <td><label>
	      <s:property value='unit.partyName'/>
	    </label></td>
  	  </tr>
  	  <tr>
	    <th  class="row1">描述：</th>
	    <td class="row2"><label>
	      <s:property value='unit.description'/>
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
	window.parent.contentPanel.getActiveTab().setTitle("单位详情");
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
});
</script>
</body>
</html>