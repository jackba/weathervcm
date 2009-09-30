<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改会议模板</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/calendar/WdatePicker.js"></script>

<style type="text/css">
body{font-size:12px;}
</style>
</head>
<body style="overflow:scroll;overflow-x:hidden">
<div class="wrap">
<h1>当前位置：个人信息管理&nbsp;&gt;&nbsp;会议模板设置&nbsp;&gt;&nbsp;<span class="position_current">修改会议模板</span></h1>
	<div class="search">
		
	<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/room_update.do">
	<input type="hidden" name="room.roomId" value="<s:property value='room.roomId'/>"/>
	<br/>
	<table class="query">
	  <tr class="t_title">
		<th colspan="2" class="t">修改会议模板</th>
	  </tr>
	  <tr>
	    <th width="20%"><font color="red">&nbsp;*</font>模板名称：</th>
	    <td><label>
	      <input name="room.templateName" value="<s:property value='room.templateName'/>" id="templateName" type="text" class="put200" maxlength="80">
	    </label></td>
  	  </tr>
  	  <tr>
	    <th class="row1"><font color="red">&nbsp;*</font>会议类型：</th>
	    <td class="row2"><label>
	      <select name="room.serviceTemplate" id="serviceTemplate">
		  <option value="-1">请选择</option>
		  </select>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1"><font color="red">&nbsp;*</font>会议主题：</th>
	    <td class="row2"><label>
	      <input name="room.subject" value="<s:property value='room.subject'/>" type="text" id="subject" class="put200" maxlength="80">
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">会议开始时间：</th>
	    <td class="row2"><label>
	      <input type="text" value="<s:property value='#request.startTime'/>" class="Wdate" id="startTime" name="startTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
	    </label></td>
	  </tr>
	  <tr>
	  	<th class="row1">会议结束时间：</th>
		<td class="row2"><label>
	      <input type="text" value="<s:property value='#request.endTime'/>" class="Wdate" id="endTime" name="endTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
	  </tr>
	  <tr>
	    <th class="row1">会议密码：</th>
	    <td class="row2"><label>
	      <input name="room.password" value="<s:property value='room.password'/>" type="password" id="password" class="put200" maxlength="8">
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">控制口令：</th>
	    <td class="row2"><label>
	      <input name="room.controlPin" value="<s:property value='room.controlPin'/>" type="password" id="controlPin" class="put200" maxlength="8">
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">备注：</th>
	    <td class="row2"><label>
	      <textarea name="room.description" cols="40" rows="5" id="description" class="w600" style="width: 450px;">
		  <s:property value='room.description'/>
		  </textarea>
	    </label></td>
	  </tr>
  </table>
	  
  <br/>
	  
	<div class="query_btn">
		<input type="button" class="butt_bg1"  onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" value="提交" onClick="submitForm()" />
		<input type="reset"  class="butt_bg1"  onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" value="重置"/>
		<input type="button" class="butt_bg1"  onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" value="返回" onClick="history.go(-1)"  />
	</div>
  		
</form>
</div><!--end of search-->
<br/>
</div><!--end of wrap-->
<script language="javascript">
var formItemSelector;
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	window.parent.contentPanel.getActiveTab().setTitle("修改会议模板");
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
});
function submitForm(){
if ( checkForm()){
	var ajax_loading_callback = function()
		{
		    Ext.MessageBox.show({
		       title: '提示',
		       progressText: '用户数据校验，请稍等……',
		       width:300,
		       progress:true,
		       closable:false,
		       animEl: 'body'
		   });
		}
	var ajax_loaded_callback = function()
		{
		    Ext.MessageBox.hide();
		}
	
	Ext.Ajax.on('beforerequest', ajax_loading_callback, this);
	Ext.Ajax.on('requestcomplete', ajax_loaded_callback, this);
	
	//定义ajax的调用过程
		Ext.Ajax.request({
			form:'form1',
			success:function(result,request){
				var resp = Ext.util.JSON.decode(result.responseText);
				if(resp.success == true){	
					Ext.Msg.alert('成功',resp.msg, function(button){
						if(button == 'ok'){
							location.href= '<%=request.getContextPath() %>/room_list.do' ;
						}
					});
				} else {
					Ext.Msg.alert('失败',resp.msg);
				}
		    },
		    failure:function(result,request){
				Ext.Msg.alert('失败',result.responseText);
		    }
		});
	}
}
function checkForm(){
	if(validateRequired('templateName','会议模板名称')&&validateRequired('subject','会议主题')){		
		return true;
	}else {
		return false;
	}		
}
function validatePasswordRule(elementId, elementName) {
	var pass=Ext.get(elementId).dom.value;
	if (/\W/.test(pass)){
		Ext.Msg.alert('提示',  '\" ' + elementName + " \" 输入项有非法字符，请重新输入!",
				function(button) {
					clear(button, elementId);
				});
		return false;
	}
				if (pass.length<6) {
					Ext.Msg.alert('提示', '\" ' + elementName + " \" 输入项长度不能少于6，请重新输入!",
				function(button) {
					clear(button, elementId);
				});
		return false;
				}
	return true;
}
</script>
</body>
</html>