<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:if test="#request.recurrence=='true'">例会预约详情</s:if><s:else>会议预约详情</s:else></title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/xtheme-shallowgray.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>

<style type="text/css">
body{font-size:12px;}
</style>
</head>
<body style="overflow:scroll;overflow-x:hidden">
<div class="wrap">
<h1>当前位置：会议管理&nbsp;&gt;&nbsp;预约会议&nbsp;&gt;&nbsp;<span class="position_current"> <s:if test="#request.recurrence=='true'">例会预约详情</s:if><s:else>会议预约详情</s:else></span></h1>
	<div class="search">
	<br/>
	<table class="query">
	  <tr class="t_title">
		<th colspan="2" class="t">会议预约详情</th>
	  </tr>
	  <tr>
	    <th width="20%">名称：</th>
	    <td><label>
	      <s:property value='conf.subject'/>
	    </label></td>
  	  </tr>
  	  <tr>
	    <th width="20%">会议号：</th>
	    <td><label>
	      <s:property value='conf.virtualConfId'/>
	    </label></td>
  	  </tr>
  	  <tr>
	    <th class="row1">组织单位：</th>
	    <td class="row2"><label>
	      <s:property value='conf.initUnit'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">开始时间：</th>
	    <td class="row2"><label>
	      <s:property value='#request.startTime'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">时长：</th>
	    <td class="row2"><label>
	      <s:property value='conf.timeLong'/>
	    </label></td>
	  </tr>
	  <s:if test="#request.recurrence=='true'">
	  <input id="recurrence.recurrenceType" type="hidden" name="recurrence.recurrenceType" value="recurrence.recurrenceType"/>
	  <input id="recurrence.dayInterval" type="hidden" name="dayInterval" value="recurrence.dayInterval"/>
	  <input id="recurrence.weekInterval" type="hidden" name="weekInterval" value="recurrence.weekInterval"/>
	  <input id="recurrence.weekDay" type="hidden" name="weekDay" value="recurrence.weekDay"/>
	  <input id="recurrence.monthInterval" type="hidden" name="monthInterval" value="recurrence.monthInterval"/>
	  <input id="recurrence.monthDay" type="hidden" name="monthDay" value="recurrence.monthDay"/>
	  <input id="recurrence.endType" type="hidden" name="recurrence.endType" value="recurrence.endType"/>
	  <input id="recurrence.endDate" type="hidden" name="endDate" value="#request.endDate"/>
	  <input id="recurrence.endAfterNumber" type="hidden" name="endAfterNumber" value="recurrence.endAfterNumber"/>
	  <tr>
	  	<th width="20%"><font color="red">&nbsp;*</font>例会设置:</th>
	  	<td><input id="recurrenceBtn" value="设置" type="button" onClick="setupRecurrence()"/></td>
	  </tr>
	  </s:if>
	  <tr>
	    <th  class="row1">主会场：</th>
	    <td class="row2"><label>
	      <s:property value='conf.mainUnitName'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">主持人：</th>
	    <td class="row2"><label>
	      <s:property value='conf.presider'/>
	    </label></td>
	  </tr>
	  
	  <tr>
	    <th class="row1">会议类型：</th>
	    <td class="row2"><label>
	      <s:property value='conf.confTypeDesc'/>
	    </label></td>
	  </tr>
	  
	  <tr>
	    <th  class="row1">会议负责人：</th>
	    <td class="row2"><label>
	      <s:property value='conf.principal'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">负责人手机：</th>
	    <td class="row2"><label>
	      <s:property value='conf.principalMobile'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">联系方式：</th>
	    <td class="row2"><label>
	      <s:property value='conf.contactMethod'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">参加单位：</th>
	    <td class="row2"><label>
	      <s:property value='conf.confUnitNames'/>
	    </label></td>
	  </tr>
	  
	  <tr>
	    <th  class="row1">主要议题：</th>
	    <td class="row2"><label>
	      <s:property value='conf.description'/>
	    </label></td>
	  </tr>
	  <tr>
	  	<th class="row1">是否需要打开卫星单向广播</th>
		<td class="row2">
		<input id="isBroadcast" type="checkbox" name="conf.isBroadcast" value="1"/>是
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">是否需要主站技术支持</th>
		<td class="row2">
		<input id="isSupport" type="checkbox" name="conf.isSupport" value="1"/>是
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">是否需要主站进行录像</th>
		<td class="row2">
		<input id="isRecord" type="checkbox" name="conf.isRecord" value="1"/>是
		</td>
	  </tr>
	  <tr>
	  	<th class="row1">高级选项：</th>
		<td class="row2"><input name="advance" id="advance" type="checkbox" onClick="showAdv()"/>显示高级会议设置选项</td>
	  </tr>
	  <tr><td colspan="2"><table class="query" id="adv" width="100%" cellpadding="0" cellspacing="0" style="display:none">
	  	<tr>
	    <th width="20%" class="row1">会议模板：</th>
	    <td class="row2"><label>
	      <s:property value='conf.serviceTemplateDesc'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">会议密码：</th>
	    <td class="row2"><label>
	      <s:property value='conf.password'/>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">控制口令：</th>
	    <td class="row2"><label>
	      <s:property value='conf.controlPin'/>
	    </label></td>
	  </tr>
	  </table></td></tr>
  </table>
	  
  <br/>
</div><!--end of search-->
<br/>
</div><!--end of wrap-->
<script language="javascript">
var formItemSelector;
function showAdv(){
	var adv = document.getElementById('adv');
	if(adv.style.display == 'none'){
		adv.style.display = "block";
	}else{
		adv.style.display = "none";
	}
}
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	// window.parent.contentPanel.getActiveTab().setTitle("预约会议详情");
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
	var isBroadcast = document.getElementById('isBroadcast');
	var isSupport = document.getElementById('isSupport');
	var isRecord = document.getElementById('isRecord');
	<s:if test='conf.isBroadcast==1'>
		isBroadcast.checked = 'true';
	</s:if>
	<s:if test='conf.isSupport==1'>
		isSupport.checked = 'true';
	</s:if>
	<s:if test='conf.isRecord==1'>
		isRecord.checked = 'true';
	</s:if>
});
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
						Ext.getDom('recurrence.dayInterval').value = '0';
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
			if(dayInterval=='0'){
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
			Ext.getCmp('weekDay_'+weekDay).setValue(true);
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