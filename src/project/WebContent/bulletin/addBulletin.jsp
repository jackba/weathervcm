<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告发布</title>
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
<h1>公告栏&nbsp;&gt;&nbsp;<span class="position_current">公告发布</span></h1>
	<div class="search">
		
	<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/bulletin_save.do">
	<br/>
	<table class="query">
	  <tr class="t_title">
		<th colspan="2" class="t">发布新公告</th>
	  </tr>
	  <tr>
	    <th width="20%"><font color="red">&nbsp;*</font>公告标题</th>
	    <td><label>
		  <input type="text" maxlength="100" size="100" name="bulletinBoard.title" id="title"/>
	    </label></td>
  	  </tr>
  	  <tr>
	    <th class="row1"><font color="red">&nbsp;*</font>公告内容</th>
	    <td class="row2">
		  <textarea name="bulletinBoard.content" id="content" rows="10" cols="100" style="width:auto;"></textarea></td>
	  </tr>
	  <tr>
	    <th class="row1">生效时间</th>
	    <td class="row2"><label>
		  <input type="text" class="Wdate" id="effectiveTime" name="bulletinBoard.effectiveTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">失效时间</th>
	    <td class="row2"><label>
		  <input type="text" class="Wdate" id="expiredTime" name="bulletinBoard.expiredTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
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
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	window.parent.contentPanel.getActiveTab().setTitle("公告发布");
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
});
function submitForm(){
if ( checkForm()){
	
	//ajaxĵù
		Ext.Ajax.request({
			form:'form1',
			success:function(result,request){
				var resp = Ext.util.JSON.decode(result.responseText);
				if(resp.success == true){	
					Ext.Msg.alert('成功',resp.msg, function(button){
						if(button == 'ok'){
							//location.href= '<%=request.getContextPath() %>/bulletin_manage.do' ;
							window.parent.closeAndCreatePanel('bulletinIssue','bulletinManage');
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
	if(validateRequired('title','公告标题') && validateRequired('content','公告内容') 
	){		
		return true;
	}else {
		return false;
	}		
}
</script>
</body>
</html>