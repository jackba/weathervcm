<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重置操作员密码</title>
<link rel="stylesheet" type="text/css" href="resources/css/ext-all.css">
<script type="text/javascript" src="resources/js/ext-base.js"></script>
<script type="text/javascript" src="resources/js/ext-all.js"></script>
<script type="text/javascript" src="resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/util.js"></script>
<script type="text/javascript" src="resources/js/DWRTreeLoader.js"></script>
<script type="text/javascript" src="resources/js/TreeCheckNodeUI.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validate.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
<style type="text/css">
body{font-size:12px;}
.style1 {color: #FF0000}
</style>
</head>
<body>

<div class="wrap">
<h1>当前位置：操作员管理&nbsp;&gt;&nbsp;<span class="position_current"> 重置操作员密码</span></h1>
<div class="search">
  <form id="form1" name="form1" method="post"   action="<%=request.getContextPath()%>/user_resetPassword.do">

	<input type="hidden" name="user.userId"	value="<s:property value='user.userId'/>"/>
	
	<table class="query" width="100%" border="0" align="center" cellpadding="3" cellspacing="1">
	 
	  <tr class="t_title">
		<th colspan="2" class="t">重置操作员密码</th>
	  </tr>
	  <tr>
	    <th class="row1">操作员名称：</th>
	    <td class="row2">
	    	<input type="text" name="user.loginId" readonly class="put200" value="<s:property value='user.loginId'/>"/>
	    </td>
	  </tr>
	 
	  <tr>
	    <th class="row1"><span class="style1">*</span>新密码：</th>
	    <td class="row2"><label>
	      <input name="user.password" type="password" id="password" class="put200"/>&nbsp;
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1"><span class="style1">*</span>重复新密码：</th>
	    <td class="row2"><label>
	      <input name="password2" type="password" id="password2" maxlength="8" class="put200">&nbsp;
	    </label></td>
	  </tr>
	  	 
	</table>
	  <br/>
	  <div class="query_btn">
			<input type="button" class="butt_bg1" value="提交" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="submitForm1()" name="post"/>
			<input type="reset"  class="butt_bg1" value="返回" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="history.go(-1)"/>
	  </div>
  </form>
</div>
</div>
<script language="javascript">
function submitForm1(){
	if(checkForm()){
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
	
		Ext.Ajax.request({
			form:'form1',
			success:function(result,request){
			
				var resp = Ext.util.JSON.decode(result.responseText);
				if(resp.success == true){	
				
					Ext.Msg.alert('成功',resp.msg, function(button){
						if(button == 'ok'){
							location.href= '<%=request.getContextPath() %>/user_list.do' ;
						}
					});
				} else {alert("failert");
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
	if(validateRequired('password','密码') && validatePassword('password','password2') 
	&& validateMinLength('password','密码','6') && validateAlphanum('password','密码')
	){		
		return true;
	}else {
		return false;
	}		
}
</script>
</body>
</html>