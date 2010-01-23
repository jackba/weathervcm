<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#request.recurrence=='true'">预约例会</s:if><s:else>预约会议</s:else></title>
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
<h1>当前位置：会议管理&nbsp;&gt;&nbsp;会议预约管理&nbsp;&gt;&nbsp;<span class="position_current"> <s:if test="#request.recurrence=='true'">预约例会</s:if><s:else>预约会议</s:else></span></h1>
	<div class="search">
	<s:if test="#request.recurrence=='true'">
	<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/conf_saveRecurrence.do">
	</s:if>
	<s:else>
	<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/conf_save.do">
	</s:else>
	<input type="hidden" name="personal" value="<s:property value='#request.personal'/>"/>
	<br/>
	<table class="query">
	  <tr class="t_title">
		<th colspan="2" class="t"><s:if test="#request.recurrence=='true'">预约例会</s:if><s:else>预约会议</s:else></th>
	  </tr>
	  <tr>
	  	<th width="20%">表单模板：</th>
		<td><div id="conf_template"/></td>
	  </tr>
	  <tr>
	    <th width="20%"><font color="red">&nbsp;*</font>名称：</th>
	    <td><label>
	      <input name="conf.subject" value="<s:property value='conf.subject'/>" id="subject" type="text" class="put200" maxlength="40"></input>
	    </label></td>
  	  </tr>
  	  <tr>
	    <th width="20%"><font color="red">&nbsp;*</font>组织单位：</th>
	    <td><label>
	      <input name="conf.initUnit" value="<s:property value='conf.initUnit'/>"  id="initUnit" type="text" class="put200" maxlength="200"/>
	    </label></td>
  	  </tr>
	  <tr>
	  	<th width="20%"><font color="red">&nbsp;*</font>开始时间：</th>
		<td>
		  <input type="text" class="Wdate" id="startTime" name="startTime" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
		</td>
	  </tr>
	  <tr>
	  	<th width="20%"><font color="red">&nbsp;*</font>时长：</th>
		<td>
		  <input name="conf.timeLong" value="<s:property value='conf.timeLong'/>"  id="timeLong" type="text" class="put200" maxlength="10"/>分钟
		</td>
	  </tr>
	  <s:if test="#request.recurrence=='true'">
	  <input id="recurrence.recurrenceType" type="hidden" name="recurrence.recurrenceType"/>
	  <input id="recurrence.dayInterval" type="hidden" name="recurrence.dayInterval"/>
	  <input id="recurrence.weekInterval" type="hidden" name="recurrence.weekInterval"/>
	  <input id="recurrence.weekDay" type="hidden" name="recurrence.weekDay"/>
	  <input id="recurrence.monthInterval" type="hidden" name="recurrence.monthInterval"/>
	  <input id="recurrence.monthDay" type="hidden" name="recurrence.monthDay"/>
	  <input id="recurrence.endType" type="hidden" name="recurrence.endType"/>
	  <input id="recurrence.endDate" type="hidden" name="recurrence.endDate"/>
	  <input id="recurrence.endAfterNumber" type="hidden" name="recurrence.endAfterNumber"/>
	  <tr>
	  	<th width="20%"><font color="red">&nbsp;*</font>例会设置:</th>
	  	<td><input id="recurrenceBtn" value="设置" type="button" onClick="setupRecurrence()"/></td>
	  </tr>
	  </s:if>
	  <tr>
	  	<th width="20%"><font color="red">&nbsp;*</font>主会场：</th>
		<td>
		<div id="main_unit"></div>
		</td>
	  </tr>
	  <tr>
	  	<th width="20%">主持人：</th>
		<td>
		  <input name="conf.presider" value="<s:property value='conf.presider'/>" id="presider" type="text" class="put200" maxlength="40"/>
		</td>
	  </tr>
	  <tr>
	  	<th class="row1"><font color="red">&nbsp;*</font>会议类型:</th>
		<td class="row2"><label>
			<div id="conf_type"></div>
		</label>
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">会议负责人：</th>
		<td>
		  <input name="conf.principal" value="<s:property value='conf.principal'/>" id="principal" type="text" class="put200" maxlength="40"/>
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">负责人手机：</th>
		<td class="row2">
		  <input name="conf.principalMobile" value="<s:property value='conf.principalMobile'/>"  id="principalMobile" type="text" class="put200" maxlength="15"/>
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">联系方式：</th>
		<td class="row2">
		  <input name="conf.contactMethod" value="<s:property value='conf.contactMethod'/>" id="contactMethod" type="text" class="put200" maxlength="200"/>
		</td>
	  </tr>
	  <!--
	  <tr>
	  	<th class="row1"><input type="button" value="生成预约码"/></th>
		<td class="row2">
		  <input name="conf.reserveCode" id="conf.reserveCode" type="text" class="put200" maxlength="10"/>
		</td>
	  </tr>
	  -->
	  <tr>
	  	<th class="row1">参加单位：</th>
		<td class="row2">
		  <div id="conf_unit"></div>
		</td>
	  </tr>
	  
	  <tr>
	    <th  class="row1">主要议题：</th>
	    <td class="row2"><label>
	      <textarea name="conf.description" cols="40" rows="5" id="description" class="w600" style="width: 450px;"><s:property value='conf.description'/></textarea>
	    </label></td>
	  </tr>
	  <tr>
	  	<th class="row1">是否需要打开卫星单向广播</th>
		<td class="row2">
		<input type="checkbox" name="conf.isBroadcast" value="1"/>是
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">是否需要主站技术支持</th>
		<td class="row2">
		<input type="checkbox" name="conf.isSupport" value="1"/>是
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">是否需要主站进行录像</th>
		<td class="row2">
		<input type="checkbox" name="conf.isRecord" value="1"/>是
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">高级选项：</th>
		<td class="row2"><input name="advance" id="advance" type="checkbox" onClick="showAdv()"/>显示高级会议设置选项</td>
	  </tr>
	  <tr><td colspan="2"><table class="query" id="adv" width="100%" cellpadding="0" cellspacing="0" style="display:none">
	  	<tr>
	    <th width="20%" class="row1"><font color="red">&nbsp;*</font>会议摸板：</th>
	    <td class="row2"><label>
	    	<div id="service_template"></div>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">会议密码：</th>
	    <td class="row2"><label>
	      <input name="conf.password" value="<s:property value='conf.password'/>" type="password" id="password" class="put200" maxlength="8">
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">控制口令：</th>
	    <td class="row2"><label>
	      <input name="conf.controlPin" value="<s:property value='conf.controlPin'/>" type="password" id="controlPin" class="put200" maxlength="8">
	    </label></td>
	  </tr>
	  </table></td></tr>
	  
  </table>
	  
  <br/>
	  
	<div class="query_btn">
		<input type="button" class="butt_bg1"  onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" value="提交" onClick="submitForm()" />
		<input type="button"  class="butt_bg1"  onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" value="重置" onClick="resetForm()"/>
	</div>
  		
</form>
</div><!--end of search-->
<br/>
</div><!--end of wrap-->
<script language="javascript">
var formItemSelector;
var serviceComboWithTooltip;
var unitComboWithTooltip;
function showAdv(){
	var adv = document.getElementById('adv');
	if(adv.style.display == 'none'){
		adv.style.display = "block";
	}else{
		adv.style.display = "none";
	}
}
function resetForm(){
	formItemSelector.reset();
	document.getElementById('adv').style.display = "none";
	document.form1.reset();
	serviceComboWithTooltip.setValue("<s:property value='#request.defaultServiceTemplateId'/>");
	unitComboWithTooltip.setValue("<s:property value='#request.defaultUnitId'/>");
}
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	//window.parent.contentPanel.getActiveTab().setTitle("预约会议");
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
	var ctds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'conftemplate_getConfTemplatesByUser.do'
		}),
		reader : new Ext.data.JsonReader({
			root : 'root'
		}, [{
			name : 'confTemplateId'
		}, {
			name : 'confTemplateName'
		}])
	});
	ctds.load();
	var ctComboWithTooltip = new Ext.form.ComboBox({
		store: ctds,
		hiddenId: 'confTemplateId',
        hiddenName: 'conf.confTemplateId',
        valueField: 'confTemplateId',
        displayField: 'confTemplateName',
        typeAhead: true,
        forceSelection: false,
        mode: 'local',
        triggerAction: 'all',
        emptyText: '请选择表单模板...',
        selectOnFocus: true,
        renderTo: 'conf_template',
		listeners : {
			select : function(thisObject, record, index){
				Ext.Ajax.request({
					url : 'conftemplate_getConfTemplateById.do',
					success:function(result,request){
						var resp = Ext.util.JSON.decode(result.responseText);
						Ext.getDom('subject').value = resp.subject;
						Ext.getDom('initUnit').value = resp.initUnit;
						Ext.getDom('timeLong').value = resp.timeLong;
						unitComboWithTooltip.setValue(resp.mainUnit);
						Ext.getDom('presider').value = resp.presider;
						serviceComboWithTooltip.setValue(resp.serviceTemplateId);
						Ext.getDom('principal').value = resp.principal;
						Ext.getDom('principalMobile').value = resp.principalMobile;
						Ext.getDom('contactMethod').value = resp.contactMethod;
						Ext.getDom('password').value = resp.password;
						Ext.getDom('controlPin').value = resp.controlPin;
						Ext.getDom('description').value = resp.description;
						var otherUnits = new Ext.data.JsonStore({
							fields: ['unitId','unitName','description'],
							data : resp.otherUnits
						});
						var units = new Ext.data.JsonStore({
							fields: ['unitId','unitName','description'],
							data : resp.units
						});
						//formItemSelector.fromData = resp.otherUnits;
						//formItemSelector.toData = resp.units;
						formItemSelector.destroy();
						formItemSelector = new Ext.ux.ItemSelector({
							//labelWidth: 75,
							width:650,
							renderTo:'conf_unit',
							name:"confUnits",
							fieldLabel:"ItemSelector",
							hideLabel:true,
							dataFields:["unitId", "unitName"],
							fromStore:otherUnits,
							toStore:units,
							msWidth:250,
							msHeight:200,
							valueField:"unitId",
							displayField:"unitName",
							imagePath:"resources/js/ItemSelector",
							//switchToFrom:true,
							toLegend:"已选单位",
							fromLegend:"可选单位"
						});
		    		},
		    		failure:function(result,request){
						Ext.Msg.alert('失败',result.responseText);
		    		},
					params : {confTemplateId : record.data.confTemplateId}
				});
			}
		}
	});
    /*
    var vmds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'room_getRoomsByUser.do'
		}),
		reader : new Ext.data.JsonReader({
			root : 'root'
		}, [{
			name : 'roomId'
		}, {
			name : 'templateName'
		}])
	});
	vmds.load();
	var roomComboWithTooltip = new Ext.form.ComboBox({
		store: vmds,
		hiddenId: 'roomId',
        hiddenName: 'conf.roomId',
        valueField: 'roomId',
        displayField: 'templateName',
        typeAhead: true,
        forceSelection: false,
        mode: 'local',
        triggerAction: 'all',
        emptyText: '请选择虚拟房间...',
        selectOnFocus: true,
        renderTo: 'virtual_room'
    });
    */
    var conftypeds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'conf_searchConfType.do'
		}),
		reader : new Ext.data.JsonReader({
			root : 'root'
		}, [{
			name : 'fieldValue'
		}, {
			name : 'fieldDesc'
		}])
	});
	conftypeds.load();
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
		}]),
		listeners : {
			load : function(thisObject, records, options){
				serviceComboWithTooltip.setValue("<s:property value='#request.defaultServiceTemplateId'/>");
			}
		}
	});
	
	serviceComboWithTooltip = new Ext.form.ComboBox({
		store: serviceds,
		//value: "<s:property value='#request.defaultServiceTemplateId'/>",
		hiddenId: 'serviceTemplateId',
        hiddenName: 'conf.serviceTemplateId',
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
	serviceds.load();
	var confTypeComboWithTooltip = new Ext.form.ComboBox({
		store: conftypeds,
		value: "<s:property value='conf.confType'/>",
		hiddenId: 'confType',
        hiddenName: 'conf.confType',
        valueField: 'fieldValue',
        displayField: 'fieldDesc',
        typeAhead: true,
        forceSelection: false,
        mode: 'local',
        triggerAction: 'all',
        emptyText: '请选择会议类型...',
        selectOnFocus: true,
        renderTo: 'conf_type'
    });
	
    unitds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'user_getUnitsByUserId.do'
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
				unitComboWithTooltip.setValue("<s:property value='#request.defaultUnitId'/>");
			}
		}
	});
	
	unitComboWithTooltip = new Ext.form.ComboBox({
		store: unitds,
		//value: "<s:property value='#request.defaultUnitId'/>",
		hiddenId: 'mainUnit',
        hiddenName: 'conf.mainUnit',
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
	unitds.load();
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
							window.parent.closeAndRefreshPanel('confReserve');
							</s:if>
							<s:else>
							//location.href = '<%=request.getContextPath()%>/conf_manageReserve.do';
							window.parent.closeAndRefreshPanel('confReserve');
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
			&& validateRequired('initUnit','组织单位')
			&& validateRequired('startTime','开始时间')
			&& validateRequired('timeLong','时长')
			&& validateRequired('mainUnit','主会场')
			&& validateRequired('serviceTemplateId','会议模板')
			&& validateRequired('confType','会议类型')
			) {		
		return true;
	}else {
		return false;
	}		
}
function loadvm(){
	var ajax_loading_callback = function()
	{
	    Ext.MessageBox.show({
	       title: '提示',
	       progressText: '加载虚拟房间，请稍等……',
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
		url:'<%=request.getContextPath()%>/conf_loadvm.do',
		method: 'post',
		params: {
			roomId: Ext.get('roomId').dom.value
		},
		success:function(result,request){
			var resp = Ext.util.JSON.decode(result.responseText);
			if(resp.success == true){	
			} else {
				Ext.Msg.alert('加载失败',resp.msg);
			}
	    },
	    failure:function(result,request){
			Ext.Msg.alert('加载失败',result.responseText);
	    }
	});
}
function loadvmex(){
	var roomId = Ext.get('roomId').dom.value;
	if (roomId == null || roomId=="") {
		Ext.Msg.alert('提示', '请选择要加载的虚拟房间!');
		return;
	}
	document.form1.action="<%=request.getContextPath()%>/conf_loadvm.do?roomId="+roomId;
	document.form1.submit();
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
var win;
function setupRecurrence(){
	win = new Ext.Window({
		title: '例会设置',
		closable: false,
		width: 500,
		height: 400,
		//border: false,
		plain: true,
		layout: 'fit',
		items: [new Ext.FormPanel({
			labelWidth: 75,
			bodyStyle: 'padding:5px 5px 0',
			items: [{
				xtype: 'fieldset',
				title: '例会模式',
				items: [{
					layout: 'column',
					border: true,
					items: [{
						columnWidth: 0.3,
						autoHeight: true,
						border: false,
						items: [{
							xtype: 'radiogroup',
							//itemCls: 'x-check-group-alt',
							columns: 1,
							id: 'mode',
							items: [{
								boxLabel: '日例会',
								name: 'recurrenceType',
								inputValue: 1,
								//checked: true,
								id:'mode_1'
							}, {
								boxLabel: '周例会',
								name: 'recurrenceType',
								inputValue: 2,
								id:'mode_2'
							}, {
								boxLabel: '月例会',
								name: 'recurrenceType',
								inputValue: 3,
								id:'mode_3'
							}]
						}]
					}, {
						columnWidth: 0.7,
						layout: 'form',
						border: false,
						items: [{
							xtype: 'fieldset',
							header: false,
							border: false,
							hidden: false,
							id: 'dayMode',
							items: [{
								layout: 'column',
								border: false,
								items: [{
									xtype: 'radio',
									id: 'dayIntervalRadio',
									fieldLabel: '',
									name: 'workday',
									labelSeparator: '',
									boxLabel: '每'
								}, {
									xtype: 'textfield',
									id: 'dayInterval',
									width: 60
								}, {
									xtype: 'label',
									text: '天.'
								}]
							}, {
								layout: 'column',
								border: false,
								items: [{
									xtype: 'radio',
									id: 'workdayRadio',
									name: 'workday',
									fieldLabel: '',
									labelSeparator: '',
									boxLabel: '每个工作日'
								}]
							}]
						}, {
							xtype: 'fieldset',
							border: false,
							header: false,
							hidden: true,
							id: 'weekMode',
							items: [{
								layout: 'column',
								border: false,
								items: [{
									xtype: 'label',
									text: '每'
								}, {
									xtype: 'textfield',
									width: 60,
									id: 'weekInterval'
								}, {
									xtype: 'label',
									text: '周：'
								}]
							}, {
								layout: 'column',
								border: false,
								items: [{
									xtype: 'radiogroup',
									//itemCls: 'x-check-group-alt',
									columns: [70, 70, 70, 70],
									id: 'weekDayGroup',
									items: [{
										boxLabel: '星期日',
										inputValue: 1,
										id: 'weekDay_1',
										name: 'weekDay'
									}, {
										boxLabel: '星期一',
										inputValue: 2,
										id: 'weekDay_2',
										name: 'weekDay'
									}, {
										boxLabel: '星期二',
										inputValue: 3,
										id: 'weekDay_3',
										name: 'weekDay'
									}, {
										boxLabel: '星期三',
										inputValue: 4,
										id: 'weekDay_4',
										name: 'weekDay'
									}, {
										boxLabel: '星期四',
										inputValue: 5,
										id: 'weekDay_5',
										name: 'weekDay'
									}, {
										boxLabel: '星期五',
										inputValue: 6,
										id: 'weekDay_6',
										name: 'weekDay'
									}, {
										boxLabel: '星期六',
										inputValue: 7,
										id: 'weekDay_7',
										name: 'weekDay'
									}]
								}]
							}]
						}, {
							xtype: 'fieldset',
							border: false,
							header: false,
							hidden: true,
							id: 'monthMode',
							items: [{
								layout: 'column',
								border: false,
								items: [{
									xtype: 'label',
									text: '每'
								}, {
									xtype: 'textfield',
									width: 60,
									id: 'monthInterval'
								}, {
									xtype: 'label',
									text: '月：'
								}]
							}, {
								layout: 'column',
								border: false,
								items: [{
									xtype: 'combo',
									id: 'countType',
									mode: 'local',
									emptyText: '请选择',
									width: 80,
									store: new Ext.data.ArrayStore({
										id: 0,
										fields: ['countType', 'countTypeText'],
										data: [[1, '正数'], [2, '倒数']]
									}),
									valueField: 'countType',
									displayField: 'countTypeText',
									triggerAction: 'all',
									value: 1
								}, {
									xtype: 'label',
									text: '第'
								}, {
									id: 'positive',
									xtype: 'combo',
									mode: 'local',
									emptyText: '请选择',
									triggerAction: 'all',
									width: 80,
									store: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31]
								}, {
									id: 'negative',
									xtype: 'combo',
									emptyText: '请选择',
									width: 80,
									mode: 'local',
									hidden: true,
									triggerAction: 'all',
									store: [1, 2, 3]
								}, {
									xtype: 'label',
									text: '天'
								}]
							}]
						}]
					}]
				}]
			}, {
				xtype: 'fieldset',
				title: '例会范围',
				items: [{
					layout: 'column',
					border: true,
					items: [{
						layout: 'form',
						hideLabels: true,
						border: false,
						columnWidth: 0.3,
						xtype: 'radiogroup',
						columns: 1,
						id: 'endType',
						items: [{
							//xtype: 'radio',
							fieldLabel: '',
							labelSeparator: '',
							name: 'endType',
							boxLabel: '循环:',
							inputValue: 2,
							id: 'endType_2'
						}, {
							//xtype: 'radio',
							fieldLabel: '',
							//checked: true,
							labelSeparator: '',
							name: 'endType',
							boxLabel: '截止至:',
							inputValue: 1,
							id: 'endType_1'
						}]
					}, {
						layout: 'form',
						columnWidth: 0.7,
						border: false,
						hideLabels: true,
						items: [{
							layout: 'column',
							border: false,
							items: [{
								xtype: 'textfield',
								width: 60,
								id: 'endAfterNumber'
							}, {
								xtype: 'label',
								text: '次后结束'
							}]
						}, {
							layout: 'column',
							border: false,
							items: [{
								xtype: 'datefield',
								format: 'Y-m-d',
								id: 'endDay'
							}, {
								xtype: 'timefield',
								format: 'H:i',
								increment: 1,
								id: 'endMinute'
							}]
						}]
					}]
				}]
			}],
			listeners:{
				'render':function(){
					Ext.getCmp('mode').addListener('change', function(thidObj, checkedRadio){
						if (checkedRadio.inputValue == 1) {
							Ext.getCmp('dayMode').show();
							Ext.getCmp('weekMode').hide();
							Ext.getCmp('monthMode').hide();
						}
						else 
							if (checkedRadio.inputValue == 2) {
								Ext.getCmp('dayMode').hide();
								Ext.getCmp('weekMode').show();
								Ext.getCmp('monthMode').hide();
							}
							else 
								if (checkedRadio.inputValue == 3) {
									Ext.getCmp('dayMode').hide();
									Ext.getCmp('weekMode').hide();
									Ext.getCmp('monthMode').show();
								}
					});
					Ext.getCmp('countType').addListener('select', function(thisField, record, index){
						if (index == 0) {
							Ext.getCmp('positive').show();
							Ext.getCmp('negative').hide();
						}
						else {
							Ext.getCmp('positive').hide();
							Ext.getCmp('negative').show();
						}
					});
				}
			}
		})],
		buttons: [{
			text: '确认',
			handler: function(){
				var recurrenceType = Ext.getCmp('mode').getValue().inputValue;
				Ext.getDom('recurrence.recurrenceType').value = recurrenceType;
				if (recurrenceType == 1) {
					if (Ext.getCmp('dayIntervalRadio').checked) {
						Ext.getDom('recurrence.dayInterval').value = Ext.getCmp('dayInterval').getValue();
					}
					else {
						Ext.getDom('recurrence.dayInterval').value = "0";
					}
				}
				else 
					if (recurrenceType == 2) {
						Ext.getDom('recurrence.weekInterval').value = Ext.getCmp('weekInterval').getValue();
						Ext.getDom('recurrence.weekDay').value = Ext.getCmp('weekDayGroup').getValue().inputValue; 
					}
					else {
						Ext.getDom('recurrence.monthInterval').value = Ext.getCmp('monthInterval').getValue();
						if(Ext.getCmp('countType').getValue()==1){
							Ext.getDom('recurrence.monthDay').value = Ext.getCmp('positive').getValue();
						}else{
							Ext.getDom('recurrence.monthDay').value = '-'+Ext.getCmp('negative').getValue();
						}
					}
				var endType = Ext.getCmp('endType').getValue().inputValue;
				Ext.getDom('recurrence.endType').value = endType;
				if (endType == 1) {
					//日期
					Ext.getDom('recurrence.endDate').value = Ext.getCmp('endDay').getRawValue() + ' ' + Ext.getCmp('endMinute').getRawValue();
				}
				else {
					//次数
					Ext.getDom('recurrence.endAfterNumber').value = Ext.getCmp('endAfterNumber').getValue();
				}
				win.close();
			}
		}, {
			text: '取消',
			handler: function(){
				win.close();
			}
		}]
	});
	win.show(Ext.getDom("recurrenceBtn"),function(){
		initWin();
	});
}
function initWin(){
	//初始化工作
	var recurrenceType = Ext.getDom('recurrence.recurrenceType').value;
	var dayInterval = Ext.getDom('recurrence.dayInterval').value;
	var weekInterval = Ext.getDom('recurrence.weekInterval').value;
	var weekDay = Ext.getDom('recurrence.weekDay').value;
	var monthInterval = Ext.getDom('recurrence.monthInterval').value;
	var monthDay = Ext.getDom('recurrence.monthDay').value;
	var endType = Ext.getDom('recurrence.endType').value;
	var endDate = Ext.getDom('recurrence.endDate').value;
	var endAfterNumber = Ext.getDom('recurrence.endAfterNumber').value;
	if(recurrenceType!=null && recurrenceType!=''){
		if(recurrenceType=='1'){
			//日例会
			Ext.getCmp('mode_1').setValue(true);
			Ext.getCmp('dayMode').show();
			Ext.getCmp('weekMode').hide();
			Ext.getCmp('monthMode').hide();
			if(dayInterval=="0"){
				Ext.getCmp('workdayRadio').setValue(true);
			}else{
				Ext.getCmp('dayIntervalRadio').setValue(true);
				Ext.getCmp('dayInterval').setValue(dayInterval);
			}
		}else if(recurrenceType=='2'){
			//周例会
			Ext.getCmp('mode_2').setValue(true);
			Ext.getCmp('dayMode').hide();
			Ext.getCmp('weekMode').show();
			Ext.getCmp('monthMode').hide();
			Ext.getCmp('weekInterval').setValue(weekInterval);
			Ext.getCmp('weekDayGroup').setValue(Ext.getCmp('weekDay_'+weekDay),true);
		}else{
			//月例会
			Ext.getCmp('mode_3').setValue(true);
			Ext.getCmp('dayMode').hide();
			Ext.getCmp('weekMode').hide();
			Ext.getCmp('monthMode').show();
			Ext.getCmp('monthInterval').setValue(monthInterval);
			if(monthDay.charAt(0)=='-'){
				Ext.getCmp('positive').hide();
				Ext.getCmp('negative').show();
				Ext.getCmp('countType').setValue(2);
				Ext.getCmp('negative').setValue(monthDay.charAt(1));
			}else{
				Ext.getCmp('countType').setValue(1);
				Ext.getCmp('positive').setValue(monthDay);
			}
		}
		Ext.getCmp('endType_'+endType).setValue(true);
		if(endType=='1'){
			//日期
			Ext.getCmp('endDay').setValue(endDate.substr(0,10));
			Ext.getCmp('endMinute').setValue(endDate.substr(11,5));
		}else{
			//次数
			Ext.getCmp('endAfterNumber').setValue(endAfterNumber);
		}
	}else{
		Ext.getCmp('mode_1').setValue(true);
		Ext.getCmp('endType_1').setValue(true);
	}
}
</script>
</body>
</html>