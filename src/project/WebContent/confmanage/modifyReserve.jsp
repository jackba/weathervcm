<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改预约会议</title>
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
<h1>当前位置：会议管理&nbsp;&gt;&nbsp;会议预约管理&nbsp;&gt;&nbsp;<span class="position_current">修改预约会议</span></h1>
	<div class="search">
	<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/conf_update.do">
	<input type="hidden" name="conf.conferenceId" value="<s:property value='conf.conferenceId'/>"/>
	<input type="hidden" name="conf.radConferenceId" value="<s:property value='conf.radConferenceId'/>"/>
	<br/>
	<table class="query">
	  <tr class="t_title">
		<th colspan="2" class="t">修改预约会议</th>
	  </tr>
	  <tr>
	    <th width="20%"><font color="red">&nbsp;*</font>名称：</th>
	    <td><label>
	      <input name="conf.subject" value="<s:property value='conf.subject'/>" id="subject" type="text" class="put200" maxlength="40">
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
		  <input type="text" class="Wdate" id="startTime" name="startTime" value="<s:property value='#request.startTime'/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
		</td>
	  </tr>
	  <tr>
	  	<th width="20%"><font color="red">&nbsp;*</font>时长：</th>
		<td>
		  <input name="conf.timeLong" value="<s:property value='conf.timeLong'/>"  id="timeLong" type="text" class="put200" maxlength="10"/>分钟
		</td>
	  </tr>
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
	  <tr>
	  	<th class="row1"><input type="button" value="生成预约码"/></th>
		<td class="row2">
		  <input name="conf.reserveCode" id="conf.reserveCode" type="text" class="put200" maxlength="10"/>
		</td>
	  </tr>
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
	    <th width="20%" class="row1"><font color="red">&nbsp;*</font>会议模板：</th>
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
		<input type="reset"  class="butt_bg1"  onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" value="重置"/>
	</div>
  		
</form>
</div><!--end of search-->
<br/>
</div><!--end of wrap-->
<script language="javascript">
var formItemSelector;
var unitComboWithTooltip;
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
	window.parent.contentPanel.getActiveTab().setTitle("修改预约会议");
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
		}])
	});
	serviceds.load();
	var serviceComboWithTooltip = new Ext.form.ComboBox({
		store: serviceds,
		value: "<s:property value='conf.serviceTemplateId'/>",
		hiddenId: 'serviceTemplate',
        hiddenName: 'conf.serviceTemplate',
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
		}])
	});
	unitds.load();
	unitComboWithTooltip = new Ext.form.ComboBox({
		store: unitds,
		value: "<s:property value='conf.mainUnit'/>",
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
	var fds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'conf_getUnitsByConfId.do?conferenceId=<%=request.getParameter("conferenceId")%>&selected=false'
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
	fds.load();
	var tds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'conf_getUnitsByConfId.do?conferenceId=<%=request.getParameter("conferenceId")%>&selected=true'
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
	tds.load();
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
		fromStore:fds,
		toStore:tds,
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
							location.href = '<%=request.getContextPath() %>/conf_listReserve.do' ;
							window.parent.closeAndRefreshPanel("confModifyReserve_<s:property value='conf.conferenceId'/>");
							</s:if>
							<s:else>
							location.href = '<%=request.getContextPath()%>/conf_manageReserve.do';
							window.parent.closeAndRefreshPanel("confModifyReserve_<s:property value='conf.conferenceId'/>");
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
			&& validateRequired('serviceTemplate','会议类型')
			) {		
		return true;
	}else {
		return false;
	}		
}
function loadvmex(){
	var roomId = Ext.get('roomId').dom.value;
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
</script>
</body>
</html>