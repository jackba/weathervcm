<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增表单模板</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/js/ItemSelector/Multiselect.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ItemSelector/Multiselect.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ItemSelector/DDView.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/calendar/WdatePicker.js"></script>

<style type="text/css">
body{font-size:12px;}
</style>
</head>
<body style="overflow:scroll;overflow-x:hidden">
<div class="wrap">
<h1>当前位置：用户个人信息管理&nbsp;&gt;&nbsp;表单模板设置&nbsp;&gt;&nbsp;<span class="position_current"> 新增表单模板</span></h1>
	<div class="search">
	<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/conftemplate_save.do">
	<input type="hidden" name="personal" value="<s:property value='#request.personal'/>"/>
	<br/>
	<table class="query">
	  <tr class="t_title">
		<th colspan="2" class="t">表单模板</th>
	  </tr>
	  <tr>
	    <th width="20%"><font color="red">&nbsp;*</font>模板名称：</th>
	    <td><label>
	      <input name="confTemplate.confTemplateName" value="<s:property value='confTemplate.confTemplateName'/>" id="subject" type="text" class="put200" maxlength="40"></input>
	    </label></td>
  	  </tr>
	  <tr>
	    <th width="20%"><font color="red">&nbsp;*</font>会议号：</th>
	    <td><label>
	      <input name="confTemplate.virtualConfId" value="<s:property value='confTemplate.virtualConfId'/>" id="virtualConfId" type="text" class="put200" maxlength="40"></input>
	    </label></td>
  	  </tr>
  	  <tr>
	    <th width="20%">会议主题：</th>
	    <td><label>
	      <input name="confTemplate.subject" value="<s:property value='confTemplate.subject'/>" id="subject" type="text" class="put200" maxlength="40"></input>
	    </label></td>
  	  </tr>
  	  
  	  <tr>
	    <th width="20%">组织单位：</th>
	    <td><label>
	      <input name="confTemplate.initUnit" value="<s:property value='confTemplate.initUnit'/>"  id="initUnit" type="text" class="put200" maxlength="200"/>
	    </label></td>
  	  </tr>
	  <tr>
	  	<th width="20%">时长：</th>
		<td>
		  <input name="confTemplate.timeLong" value="<s:property value='confTemplate.timeLong'/>"  id="timeLong" type="text" class="put200" maxlength="10"/>分钟
		</td>
	  </tr>
	  <tr>
	  	<th width="20%">主会场：</th>
		<td>
		<div id="main_unit"></div>
		</td>
	  </tr>
	  <tr>
	  	<th width="20%">主持人：</th>
		<td>
		  <input name="confTemplate.presider" value="<s:property value='confTemplate.presider'/>" id="presider" type="text" class="put200" maxlength="40"/>
		</td>
	  </tr>
  	  <tr>
	    <th class="row1">会议类型：</th>
	    <td class="row2"><label>
	    	<div id="service_template"></div>
	    </label></td>
	  </tr>
	  <tr>
	  	<th class="row1">会议负责人：</th>
		<td>
		  <input name="confTemplate.principal" value="<s:property value='confTemplate.principal'/>" id="principal" type="text" class="put200" maxlength="40"/>
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">负责人手机：</th>
		<td class="row2">
		  <input name="confTemplate.principalMobile" value="<s:property value='confTemplate.principalMobile'/>"  id="principalMobile" type="text" class="put200" maxlength="15"/>
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">联系方式：</th>
		<td class="row2">
		  <input name="confTemplate.contactMethod" value="<s:property value='confTemplate.contactMethod'/>" id="contactMethod" type="text" class="put200" maxlength="200"/>
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">参加单位：</th>
		<td class="row2">
		  <div id="conf_unit"></div>
		</td>
	  </tr>
	  <tr>
	    <th class="row1">会议密码：</th>
	    <td class="row2"><label>
	      <input name="confTemplate.password" value="<s:property value='confTemplate.password'/>" type="password" id="password" class="put200" maxlength="8">
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">控制口令：</th>
	    <td class="row2"><label>
	      <input name="confTemplate.controlPin" value="<s:property value='confTemplate.controlPin'/>" type="password" id="controlPin" class="put200" maxlength="8">
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">主要议题：</th>
	    <td class="row2"><label>
	      <textarea name="confTemplate.description" cols="40" rows="5" id="description" class="w600" style="width: 450px;"><s:property value='confTemplate.description'/></textarea>
	    </label></td>
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
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	window.parent.contentPanel.getActiveTab().setTitle("新建表单模板");
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    var serviceds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'service_search.do'
		}),
		reader : new Ext.data.JsonReader({
			root : 'root'
		}, [{
			name : 'serviceTemplateId'
		}, {
			name : 'serviceTemplateName'
		}, {
			name : 'serviceTemplateDesc'
		}])
	});
	serviceds.load();
	var serviceComboWithTooltip = new Ext.form.ComboBox({
		store: serviceds,
		value: "<s:property value='confTemplate.serviceTemplateDesc'/>",
		hiddenId: 'serviceTemplateId',
        hiddenName: 'confTemplate.serviceTemplateId',
        valueField: 'serviceTemplateId',
        displayField: 'serviceTemplateDesc',
        typeAhead: true,
        forceSelection: false,
        mode: 'local',
        triggerAction: 'all',
        emptyText: '请选择会议模板...',
        selectOnFocus: true,
        renderTo: 'service_template'
    });
    unitds = new Ext.data.Store({
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
		}])
	});
	unitds.load();
	var unitComboWithTooltip = new Ext.form.ComboBox({
		store: unitds,
		value: "<s:property value='confTemplate.mainUnitName'/>",
		hiddenId: 'mainUnit',
        hiddenName: 'confTemplate.mainUnit',
        valueField: 'unitId',
        displayField: 'unitName',
        typeAhead: true,
        forceSelection: false,
        mode: 'local',
        triggerAction: 'all',
        emptyText: '请选择单位...',
        selectOnFocus: true,
        renderTo: 'main_unit'
    });
	/*
	 * Ext.ux.ItemSelector Example Code
	 */
			formItemSelector = new Ext.ux.ItemSelector({
				//labelWidth: 75,
				width:650,
				renderTo:'conf_unit',
				name:"confUnits",
				fieldLabel:"ItemSelector",
				hideLabel:true,
				dataFields:["unitId", "unitName"],
				fromStore:unitds,
				toData:[],
				msWidth:250,
				msHeight:200,
				valueField:"unitId",
				displayField:"unitName",
				imagePath:"resources/js/ItemSelector",
				//switchToFrom:true,
				toLegend:"已选单位",
				fromLegend:"可选单位"
			});
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
							<s:if test="#request.personal == 'true'">
							//location.href = '<%=request.getContextPath() %>/conf_listReserve.do' ;
							window.parent.closeAndRefreshPanel('conftemplateAdd');
							</s:if>
							<s:else>
							//location.href = '<%=request.getContextPath()%>/conf_manageReserve.do';
							window.parent.closeAndRefreshPanel('conftemplateAdd');
							</s:else>
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
	if (validateRequired('subject','名称')
			&& validateRequired('virtualConfId','会议号')) {		
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