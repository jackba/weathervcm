<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改单位</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/calendar/WdatePicker.js"></script>

<style type="text/css">
body{font-size:12px;}
</style>
</head>
<body style="overflow:scroll;overflow-x:hidden">
<div class="wrap">
<h1>当前位置：基本信息&nbsp;&gt;&nbsp;参会单位&nbsp;&gt;&nbsp;<span class="position_current">修改单位</span></h1>
	<div class="search">
		
	<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/unit_update.do">
	<input type="hidden" name="unit.unitId" value="<s:property value='unit.unitId'/>"/>
	<br/>
	<table class="query">
	  <tr class="t_title">
		<th colspan="2" class="t">修改单位</th>
	  </tr>
	  <tr>
	    <th width="20%"><font color="red">&nbsp;*</font>单位名称：</th>
	    <td><label>
	      <input name="unit.unitName" value="<s:property value='unit.unitName'/>" id="unitName" type="text" class="put200" maxlength="80">
	    </label></td>
  	  </tr>
  	  <tr>
	    <th class="row1">终端：</th>
	    <td class="row2"><label>
	    	<div id="unit_terminal"></div>
	    </label></td>
	  </tr>
	  <tr>
	    <th  class="row1">描述：</th>
	    <td class="row2"><label>
	      <textarea name="unit.description" cols="40" rows="5" id="description" class="w600" style="width: 450px;"><s:property value='unit.description'/></textarea>
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
var terminalComboWithTooltip;
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	//window.parent.contentPanel.getActiveTab().setTitle("修改虚拟房间");
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';

    tds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'terminal_searchAll.do'
		}),
		reader : new Ext.data.JsonReader({
			root : 'root'
		}, [{
			name : 'terminalId'
		}, {
			name : 'terminalName'
		}, {
			name : 'dialString'
		}]),
		listeners : {
			load : function(thisObject, records, options){
    			terminalComboWithTooltip.setValue("<s:property value='unit.partyId'/>");
			}
		}
	});
	terminalComboWithTooltip = new Ext.form.ComboBox({
		store: tds,
		//value: "<s:property value='unit.partyId'/>",
		hiddenId: 'partyId',
        hiddenName: 'unit.partyId',
        valueField: 'terminalId',
        displayField: 'terminalName',
        typeAhead: true,
        forceSelection: false,
        mode: 'local',
        triggerAction: 'all',
        emptyText: '请选择终端...',
        selectOnFocus: true,
        renderTo: 'unit_terminal'
    });
	tds.load();
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
							//location.href= '<%=request.getContextPath() %>/unit_list.do' ;
							window.parent.closeAndRefreshPanel("unitModify_<s:property value='unit.unitId'/>");
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
	if (validateRequired('unitName','单位名称')) {		
		return true;
	}else {
		return false;
	}
}
</script>
</body>
</html>