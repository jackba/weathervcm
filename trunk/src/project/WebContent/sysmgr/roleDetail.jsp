<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色信息详情</title>
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
<!--<script language="javascript">
	function show(){
		if ( <s:property value="role.status"/> == 0 ){
			document.write("有效");
		} else {
			document.write("无效");
		}
	}
</script>-->
<div class="wrap">
<h1>当前位置：系统管理&nbsp;&gt;&nbsp;角色管理&nbsp;&gt;&nbsp;<span class="position_current"> 角色信息详情</span></h1>
	<div class="search">

<br/>
		<table class="query">
			<tr class="t_title">
				<th colspan="2" class="t">角色基本信息</th>
			</tr>
			<tr>
				<th width="20%">角色名称：</th>
				<td>
					<s:property value='role.roleName'/>&nbsp;
				</td>
			</tr>
			<tr>
				<th width="20%">角色描述：</th>
				<td>
					<s:property value='role.description'/>&nbsp;
				</td>
			</tr>
			<tr>
				<th width="20%">权限：</th>
				<td>
					<div id="privilegesDiv"></div>
				</td>
			</tr> 
	</table>
	<div class="dotLine"></div>
	<div class="query_btn">
		<input name="button" type="button"  onclick="window.parent.closePanel('roleDetail_<s:property value="role.roleId"/>');" class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" value="关闭"/>
	</div>
</div><!--end of search-->
<br/>

</div><!--end of wrap-->
<script language="javascript">
var formItemSelector;
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	//window.parent.contentPanel.getActiveTab().setTitle("角色信息详情");
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var ds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'role_getAllPrivilegesExclud.do?roleId=<%=request.getParameter("roleId")%>'
		}),
		reader : new Ext.data.JsonReader({
			root : 'root'
		}, [{
			name : 'privilegeId'
		}, {
			name : 'name'
		}, {
			name : 'url'
		}, {
		    name : 'description'
		}
		])
	});
	ds.load();
	/*
	 * get role mapping privileges.
	 */
	var dsr = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'role_getPrivilegesByRoleId.do?roleId=<%=request.getParameter("roleId")%>'
		}),
		reader : new Ext.data.JsonReader({
			root : 'root'
		}, [{
			name : 'privilegeId'
		}, {
			name : 'name'
		}, {
			name : 'url'
		}, {
		    name : 'description'
		}
		])
	});
	dsr.load();
	/*
	 * Ext.ux.ItemSelector Example Code
	 */
	
			formItemSelector = new Ext.ux.ItemSelector({
				//labelWidth: 75,
				width:700,
				renderTo:'privilegesDiv',
				name:"privileges",
				fieldLabel:"ItemSelector",
				hideLabel:false,
				dataFields:["privilegeId", "name"],
				fromStore:ds,
				toStore:dsr,
				toData:[],
				msWidth:250,
				msHeight:200,
				valueField:"privilegeId",
				displayField:"name",
				imagePath:"resources/js/ItemSelector",
				//switchToFrom:true,
				toLegend:"已选权限",
				fromLegend:"可选权限"
			});
	
});
</script>
</body>
</html>