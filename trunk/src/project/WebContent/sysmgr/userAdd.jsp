<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加用户</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/ItemSelector/Multiselect.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ItemSelector/Multiselect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ItemSelector/DDView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validate.js"></script>
<style type="text/css">
body{font-size:12px;}
</style>
</head>
<body style="overflow:scroll;overflow-x:hidden">
<div class="wrap">
	
	<h1>当前位置：系统管理&nbsp;&gt;&nbsp;操作员管理&nbsp;&gt;&nbsp;<span class="position_current"> 新增操作员</span></h1>
	
	<div class="search">	
	<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/user_save.do">
	
	<input type=hidden name="flag" id="flag"  value="<s:property value='#request.action'/>"/>
	
	<br/>
	
	<table class="query">
	  <tr class="t_title">
		<th colspan="2" class="t">新增操作员</th>
	  </tr>
	  <tr>
	    <th width="20%"><font color="red">&nbsp;*</font>登录名：</th>
	    <td><label>
	      <input name="user.loginId" id="loginId" type="text" class="put200" maxlength="15">
	    </label></td>
  	  </tr>
  	  <tr>
	    <th class="row1"><font color="red">&nbsp;*</font>操作员姓名：</th>
	    <td class="row2"><label>
	      <input name="user.userName" type="text" id="userName" class="put200" maxlength="20">
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1"><font color="red">&nbsp;*</font>密码：</th>
	    <td class="row2"><label>
	      <input name="user.password" type="password" id="password" class="put200" maxlength="8">
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1"><font color="red">&nbsp;*</font>确认密码：</th>
	    <td class="row2"><label>
	      <input name="password2" type="password" id="password2" class="put200" maxlength="8">
	    </label></td>
	  </tr>
	  <tr>
	  	<th class="row1">性别：</th>
		<td class="row2">
		<select name="user.sex">
		<option value="1" selected="selected">男</option>
		<option value="2">女</option>
		</select>
		</td>
	  </tr>
	  <tr>
	    <th class="row1">操作员手机：</th>
	    <td class="row2"><label>
	      <input name="user.mobile" type="text" id="mobile" class="put200" maxlength="15">
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">电子邮件：</th>
	    <td class="row2"><label>
	      <input name="user.email" type="text" id="email" style="width: 300px;" maxlength="30">
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">家庭电话：</th>
	    <td class="row2"><label>
	      <input name="user.homeTelephone" type="text" id="telephone" class="put200" maxlength="15">
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">办公室电话：</th>
	    <td class="row2"><label>
	      <input name="user.officeTelephone" type="text" id="telephone" class="put200" maxlength="15">
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">地址：</th>
	    <td class="row2"><label>
	      <input name="user.address" type="text" id="address" style="width: 450px;" size="40" maxlength="50">
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">备注：</th>
	    <td class="row2"><label>
	      <textarea name="user.description" cols="40" rows="5" id="remark" class="w600" style="width: 450px;"></textarea>
	    </label></td>
	  </tr>
	  <tr>
	  	<th class="row1"><font color="red">&nbsp;*</font>操作员角色：</th>
		<td class="row2" id="role"><div id="roleDiv"/>
		</td>
	  </tr>
	  <tr>
	  	<th class="row1"><font color="red">&nbsp;*</font>默认主会场：</th>
		<td class="row2">
		<div id="main_unit"></div>
		</td>
	  </tr>
	  <tr>
	  	<th class="row1"><font color="red">&nbsp;*</font>可选主会场：</th>
		<td class="row2" id="role"><div id="unitDiv"/>
		</td>
	  </tr>
    </table>
	  
    <br/>
	  
	<div class="query_btn">
		<input type="button" class="butt_bg1"  onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" value="提交" onClick="submitForm()" />
		<input type="reset"  class="butt_bg1"  onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" value="重置"/>
	</div>	
    
    </form>
    </div><!--end of search-->
    
    <br/>
</div><!--end of wrap-->

<script language="javascript">
var formItemSelector;
Ext.onReady(
	function(){
		Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
		//window.parent.contentPanel.getActiveTab().setTitle("增加用户");
	    Ext.QuickTips.init();
	    Ext.form.Field.prototype.msgTarget = 'side';
	    var ds = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
				url : 'user_getAllRoles.do'
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
		var unitds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'unit_getAll.do'
		}),
		reader : new Ext.data.JsonReader({
			root : 'root'
		}, [{
			name : 'unitId'
		}, {
			name : 'unitName'
		}, {
			name : 'description'
		}]),
			listeners : {
				load : function(thisObject, records, options){
					unitComboWithTooltip.setValue("<s:property value='user.defaultUnitId'/>");
				}
			}
	});
	
	var unitComboWithTooltip = new Ext.form.ComboBox({
		store: unitds,
		//value: "<s:property value='user.defaultUnitId'/>",
		hiddenId: 'defaultUnitId',
        hiddenName: 'user.defaultUnitId',
        valueField: 'unitId',
        displayField: 'unitName',
        typeAhead: true,
        forceSelection: false,
        mode: 'local',
        triggerAction: 'all',
        emptyText: '请选择默认主会场...',
        selectOnFocus: true,
        renderTo: 'main_unit'
    });
	unitds.load();
	var urds = new Ext.data.Store({
			proxy : new Ext.data.HttpProxy({
				url : 'user_getAllUnits.do'
			}),
			reader : new Ext.data.JsonReader({
				root : 'root'
			}, [{
				name : 'unitId'
			}, {
				name : 'unitName'
			}])
		});
		urds.load();
	
		/*
		 * Ext.ux.ItemSelector Example Code
		*/
		var urItemSelector = new Ext.ux.ItemSelector({
			//labelWidth: 75,
			width:650,
			renderTo:'unitDiv',
			name:"units",
			fieldLabel:"ItemSelector",
			hideLabel:true,
			dataFields:["unitId", "unitName"],
			fromStore:urds,
			toData:[],
			msWidth:250,
			msHeight:200,
			valueField:"unitId",
			displayField:"unitName",
			imagePath:"resources/js/ItemSelector",
			//switchToFrom:true,
			toLegend:"已选会场",
			fromLegend:"可选会场"
		});	
	
	}
);

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
							//location.href= '<%=request.getContextPath() %>/user_list.do' ;
							window.parent.closeAndRefreshPanel('userAdd');
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
	if(validateRequired('loginId','操作员名称') && validateRequired('userName','操作员姓名') 
		&& validateRequired('password','密码') && validatePassword('password','password2') 
		&& validateMinLength('password','密码','6') && validateAlphanum('password','密码')
		&& validateRequired('defaultUnitId','默认主会场'))
	{		
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
			}
		);
		return false;
	}
	return true;
}

</script>
</body>
</html>