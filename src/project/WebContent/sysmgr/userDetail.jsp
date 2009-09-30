<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作员信息详情</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/ItemSelector/Multiselect.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/engine.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/util.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/DWRTreeLoader.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/TreeCheckNodeUI.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ItemSelector/MultiselectT.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ItemSelector/DDView.js"></script>

<style type="text/css">
body{font-size:12px;}
</style>
</head>
<body style="overflow:scroll;overflow-x:hidden">
<div class="wrap">
<h1>当前位置：系统管理&nbsp;&gt;&nbsp;<span class="position_current"> 操作员信息详情</span></h1>
	<div class="search">

<br/>
		<table class="query">
			<tr class="t_title">
				<th colspan="2" class="t">操作员基本信息</th>
			</tr>
			<tr>
				<th width="20%">操作员名称：</th>
				<td>
					<s:property value='user.loginId'/>&nbsp;
				</td>
			</tr>
			<tr>
				<th width="20%">操作员姓名：</th>
				<td>
					<s:property value='user.userName'/>&nbsp;
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
				<th width="20%">操作员手机：</th>
				<td>
					<s:property value="user.mobile"/>&nbsp;
				</td>
			</tr>
			<tr>
				<th width="20%">电子邮件：</th>
				<td>
					<s:property value="user.email"/>&nbsp;
				</td>
			</tr>
			<tr>
				<th width="20%">家庭电话：</th>
				<td>
					<s:property value="user.homeTelephone"/>&nbsp;
				</td>
			</tr>
			<tr>
				<th width="20%">办公室电话：</th>
				<td>
					<s:property value="user.officeTelephone"/>&nbsp;
				</td>
			</tr>
			<tr>
				<th width="20%">地址：</th>
				<td>
					<s:property value='user.address'/>&nbsp;
				</td>
			</tr>
			<tr>
				<th width="20%">备注：</th>
				<td>
					<s:property value="user.remark"/>&nbsp;
				</td>
			</tr>      
			<tr>
			  	<th class="row1">角色：</th>
				<td class="row2"><div id="roleDiv">
			</td>
	  </tr>	 
	</table>
	<div class="dotLine"></div>
	<div class="query_btn">
		<input name="button" type="button"  onclick="window.close()" class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" value="返回"/>
	</div>
</div><!--end of search-->
<br/>

</div><!--end of wrap-->
<script language="javascript">
var formItemSelector;
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	//window.parent.contentPanel.getActiveTab().setTitle("操作员信息详情");
	Ext.getDom("sex").selectedIndex = <s:property value="user.sex"/> - 1;
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var ds = new Ext.data.Store({
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
	ds.load();
	var dsAllRoles = new Ext.data.Store({
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
	dsAllRoles.load();
	/*
	 * Ext.ux.ItemSelector Example Code
	 */
	
			formItemSelector = new Ext.ux.ItemSelector({
				//labelWidth: 75,
				width:700,
				renderTo:'roleDiv',
				name:"roles",
				fieldLabel:"ItemSelector",
				hideLabel:true,
				dataFields:["roleId", "roleName"],
				fromStore:dsAllRoles,
				toStore:ds,
				toData:[],
				msWidth:250,
				msHeight:200,
				valueField:"roleId",
				displayField:"roleName",
				imagePath:"resources/js/ItemSelector",
				//switchToFrom:true,
				toLegend:"已选角色",
				fromLegend:"可选角色"/*,
				toTBar:[{
					text:"清空角色",
					handler:function(){
						formItemSelector.reset.call(formItemSelector);
					}
				}]*/
			});
	
});
</script>
</body>
</html>