<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作员信息修改</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/ItemSelector/Multiselect.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ItemSelector/Multiselect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ItemSelector/DDView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/DWRTreeLoader.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/TreeCheckNodeUI.js"></script>

<style type="text/css">
body{font-size:12px;}
</style>
</head>
  
  <body>
  <div class="wrap">
  <h1>当前位置：操作员管理&nbsp;&gt;&nbsp;<span class="position_current"> 修改操作员信息</span></h1>
	<div class="search">
    <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/user_update.do">
		<input type="hidden" name="user.userId" value="<s:property value='user.userId'/>"/>
		<input type="hidden" name="user.status" value="<s:property value='user.status'/>"/>
		<table class="query">
			<tr class="t_title">
				<th colspan="2" class="t">操作员基本信息</th>
			</tr>
		  	<tr>
				<th width="20%">操作员名称：</th>
				<td>
					<input type="text" name="user.loginId" readonly class="put200" value="<s:property value='user.loginId'/>"/>
				</td>
			</tr>
			<tr>
				<th width="20%">操作员姓名：</th>
				<td>
					<input type="text" name="user.userName" class="put200" value="<s:property value='user.userName'/>"/>
				</td>
			</tr>
			<tr>
				<th width="20%">性别：</th>
				<td>
					<select name="user.sex" id="sex">
					<option value="1">男</option>
					<option value="2">女</option>
					</select>
				</td>
			</tr>
			<tr>
				<th width="20%">地址：</th>
				<td>
					<input type="text" name="user.address" class="put200" value="<s:property value='user.address'/>"/>
				</td>
			</tr>
			<tr>
				<th width="20%">电子邮件：</th>
				<td>
					<input type="text" name="user.email" class="put200" value="<s:property value='user.email'/>"/>
				</td>
			</tr>
			<tr>
				<th width="20%">家庭电话：</th>
				<td>
					<input type="text" name="user.homeTelephone" class="put200" value="<s:property value='user.homeTelephone'/>"/>
				</td>
			</tr>
			<tr>
				<th width="20%">办公室电话：</th>
				<td>
					<input type="text" name="user.officeTelephone" class="put200" value="<s:property value='user.officeTelephone'/>"/>
				</td>
			</tr>
			<tr>
				<th width="20%">手机：</th>
				<td>
					<input type="text" name="user.mobile" class="put200" value="<s:property value='user.mobile'/>">
				</td>
			</tr>
			<tr>
				<th width="20%">备注：</th>
				<td>
					<input type="text" name="user.description" class="put200" value="<s:property value='user.description'/>">
				</td>
			</tr>
			<tr>
			  	<th width="20%" ><font color="red">&nbsp;*</font>角色：</th>
				<td class="row2">
					<div id="roleDiv">
				</td>
			</tr>
			
		    
		</table>
		
		<br/>
		<div class="query_btn">
			<input type="button" class="butt_bg1" value="提交" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="submitForm()" />
			<input type="button" class="butt_bg1" value="返回" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="history.go(-1)"/>
		</div>
		
		</form>
  <br/>
  </div>
</div>
<script language="javascript">
var formItemSelector;
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	// window.parent.contentPanel.getActiveTab().setTitle("修改操作员信息");
	Ext.getDom("sex").selectedIndex = <s:property value="user.sex"/> - 1;
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var ds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'user_getAllRolesExclud.do?userId=<%=request.getParameter("userId")%>'
		}),
		reader : new Ext.data.JsonReader({
			root : 'root'
		}, [{
			name : 'roleId'
		}, {
			name : 'roleName'
		}])
	});
	ds.load();
	var dsr = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'user_getRolesByUserId.do?userId=<%=request.getParameter("userId")%>'
		}),
		reader : new Ext.data.JsonReader({
			root : 'root'
		}, [{
			name : 'roleId'
		}, {
			name : 'roleName'
		}])
	});
	dsr.load();
	/*
	 * Ext.ux.ItemSelector Example Code
	 */
	
			formItemSelector = new Ext.ux.ItemSelector({
				//labelWidth: 75,
				width:650,
				renderTo:'roleDiv',
				name:"roles",
				fieldLabel:"ItemSelector",
				hideLabel:true,
				dataFields:["roleId", "roleName"],
				fromStore:ds,
				toStore:dsr,
				toData:[],
				msWidth:250,
				msHeight:200,
				valueField:"roleId",
				displayField:"roleName",
				imagePath:"resources/js/ItemSelector",
				//switchToFrom:true,
				toLegend:"已选角色",
				fromLegend:"可选角色"
			});
	
});
function submitForm(){

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
							//location.href= '<%=request.getContextPath() %>/user_list.do' ;
							//history.go(-1);
							window.parent.closeAndRefreshPanel("userModify_<s:property value='user.userId'/>");
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
	//return false;
}
</script>
<!--</div>end of wrap-->
</body>
</html>
