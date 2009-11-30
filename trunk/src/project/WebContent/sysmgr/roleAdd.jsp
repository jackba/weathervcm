<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加角色</title>
	<link href="<%=request.getContextPath() %>/resources/css/edgrid.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/ItemSelector/Multiselect.css">

	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/pager.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/interface/roleService.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/sysmgr/init-roleAdd.js" defer="defer"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ItemSelector/Multiselect.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ItemSelector/DDView.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validate.js"></script>
</head>
<body style="overflow:scroll;overflow-x:hidden">
	<div class="wrap">
		<h1>当前位置：系统管理&nbsp;&gt;&nbsp;角色管理&nbsp;&gt;&nbsp;<span class="position_current"> 增加角色</span></h1>
		<div class="search">
			<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/role_save.do">
				<table class="query">
					  <tr class="t_title">
					  	<th colspan="2" class="t">添加角色</th>
					  </tr>
					  <tr>
						<th width="20%"><font color="red">&nbsp;*</font>角色名称：</th>
						<td><input type="text" class="put200" id="name" name="role.roleName"></td>
					  </tr>
					  <tr>
						<th width="20%">角色描述：</th>
						<td>
							<span class="gen">
								<textarea class="w600" style="width: 450px;" tabindex="14" name="role.description" rows="8" cols="35" ><s:property value='#request.c.remark' /></textarea>
				  			</span>
						</td>
					  </tr>

					  <tr>
					  	<th width="20%"><font color="red">&nbsp;*</font>角色权限：</th>
						<td class="row2"><div id="roleDiv"></div>
						</td>
					  </tr>
			  </table>
				<div class="query_btn">
					<input type="button" value="保存" class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="submitForm();"/>&nbsp;&nbsp;
					<input type="reset"  value="重置" class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" tabindex="15"  >			
				</div>
			</form>
		</div><!--end of search-->
	</div><!-- end of wrap -->
<script language="javascript">
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	//window.parent.contentPanel.getActiveTab().setTitle("增加角色");
});
function reload(){
	location.href = "<%=request.getContextPath()%>/role_list.do";
}
function checkForm(){
	if(validateRequired('name','角色名称')){
		return true;
	}else {
		return false;
	}				
}
function submitForm(){
	if(checkForm()){
		//定义导入时的效果，存入变量ajax_loading_callback中
		var ajax_loading_callback = function(){
			Ext.MessageBox.show({
				title: '提示',
				progressText: '正在提交数据，请稍等……',
				width:300,
				progress:true,
				closable:false,
				animEl: 'body'
			});
		};
		
		//定义回调函数的弹出效果，存入变量ajax_loaded_callback中
		var ajax_loaded_callback = function(){
			Ext.MessageBox.hide();
		};
		
		//将上面两变量注册到ajax中
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
							//location.href= '<%=request.getContextPath() %>/role_list.do' ;
							window.parent.closeAndRefreshPanel('roleAdd');
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
</script>
</body>
</html>
	