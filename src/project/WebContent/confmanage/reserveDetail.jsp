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
	      <s:property value='#request.startTime'/>
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
	      <s:property value='conf.mainUnitName'/>
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
	      <s:property value='conf.confTypeDesc'/>
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
	      <s:property value='conf.confUnitNames'/>
	    </label></td>
	  </tr>
	  
	  <tr>
	    <th  class="row1">主要议题：</th>
	    <td class="row2"><label>
	      <s:property value='conf.description'/>
	    </label></td>
	  </tr>
	  <tr>
	  	<th class="row1">是否需要打开卫星单向广播</th>
		<td class="row2">
		<input id="isBroadcast" type="checkbox" name="conf.isBroadcast" value="1"/>是
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">是否需要主站技术支持</th>
		<td class="row2">
		<input id="isSupport" type="checkbox" name="conf.isSupport" value="1"/>是
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">是否需要主站进行录像</th>
		<td class="row2">
		<input id="isRecord" type="checkbox" name="conf.isRecord" value="1"/>是
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">高级选项：</th>
		<td class="row2"><input name="advance" id="advance" type="checkbox" onClick="showAdv()"/>显示高级会议设置选项</td>
	  </tr>
	  <tr><td colspan="2"><table class="query" id="adv" width="100%" cellpadding="0" cellspacing="0" style="display:none">
	  	<tr>
	    <th width="20%" class="row1">会议模板：</th>
	    <td class="row2"><label>
	      <s:property value='conf.serviceTemplateDesc'/>
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
	  </table></td></tr>
  </table>
	  
  <br/>
</div><!--end of search-->
<br/>
</div><!--end of wrap-->
<script language="javascript">
var formItemSelector;
function showAdv(){
	var adv = document.getElementById('adv');
	if(adv.style.display == 'none'){
		adv.style.display = "block";
	}else{
		adv.style.display = "none";
	}
}
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	// window.parent.contentPanel.getActiveTab().setTitle("预约会议详情");
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
	var isBroadcast = document.getElementById('isBroadcast');
	var isSupport = document.getElementById('isSupport');
	var isRecord = document.getElementById('isRecord');
	<s:if test='conf.isBroadcast==1'>
		isBroadcast.checked = 'true';
	</s:if>
	<s:if test='conf.isSupport==1'>
		isSupport.checked = 'true';
	</s:if>
	<s:if test='conf.isRecord==1'>
		isRecord.checked = 'true';
	</s:if>
});
</script>
</body>
</html>