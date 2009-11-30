<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改公告</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validate.js"></script>
<style type="text/css">
body{font-size:12px;}
</style>
</head>
<body>
  <div class="wrap">
  <h1>当前位置：公告管理&nbsp;&gt;&nbsp;<span class="position_current"> 修改公告信息</span></h1>
	<div class="search">
    <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/bulletin_update.do">
		<input type="hidden" name="bulletinBoard.bulletinId" value="<s:property value='bulletinBoard.bulletinId'/>"/>
		<input type="hidden" name="bulletinBoard.status" value="<s:property value='bulletinBoard.status'/>"/>
		<table class="query">
			<tr class="t_title">
				<th colspan="2" class="t">公告信息</th>
			</tr>
		  	<tr>
				<th width="20%">标题：</th>
				<td>
					<input type="text" maxlength="100" size="100" name="bulletinBoard.title" id="title" value="<s:property value='bulletinBoard.title'/>"/>
				</td>
			</tr>
			<tr>
				<th width="20%">内容：</th>
				<td>
					<textarea name="bulletinBoard.content" id="content" rows="10" cols="100" style="width:auto"><s:property value='bulletinBoard.content'/></textarea>
				</td>
			</tr>
			
		</table>
		
		<br/>
		<div class="query_btn">
			<input type="button" class="butt_bg1" value="提交" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="submitForm()" />
		</div>
		
		</form>
  <br/>
  </div>
</div>
<script language="javascript">
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	//window.parent.contentPanel.getActiveTab().setTitle("公告修改");
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
							window.parent.closeAndRefreshPanel('bulletinModify_<s:property value='bulletinBoard.bulletinId'/>');
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
<!--</div>end of wrap-->
</body>
</html>