<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统配置</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validate.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
<style type="text/css">
body{font-size:12px;}
.style1 {color: #FF0000}
</style>
</head>
<body>
<div class="wrap">
<h1>当前位置：操作员管理&nbsp;&gt;&nbsp;<span class="position_current"> 系统配置</span></h1>
<div class="search">
  <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/config_modifyConfiguration.do">
	
	<table class="query" width="100%" border="0" align="center" cellpadding="3" cellspacing="1">
	 
	  <tr class="t_title">
		<th colspan="2" class="t">系统配置</th>
	  </tr>
	  
	  <tr>
	    <th class="row1">默认会议模板：</th>
	    <td class="row2"><label>
	    	<div id="service_template"></div>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">平台地址：</th>
	    <td class="row2"><label>
	      <input type="text" name="config.icmHost" class="put200" value="<s:property value='config.icmHost'/>"/>
	    </label></td>
	  </tr>
	  <tr>
	    <th class="row1">平台端口：</th>
	    <td class="row2"><label>
	      <input type="text" name="config.icmPort" class="put200" value="<s:property value='config.icmPort'/>"/>
	    </label></td>
	  </tr>
	  	 
	</table>
	  <br/>
	  <div class="query_btn">
			<input type="button" class="butt_bg1" value="保存" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="submitForm1()" name="post"/>
	  </div>
  </form>
</div>
</div>
<script language="javascript">
Ext.onReady(function(){
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
				serviceComboWithTooltip.setValue("<s:property value='config.defaultServiceTemplateId'/>");
			}
		}
	});
	var serviceComboWithTooltip = new Ext.form.ComboBox({
		store: serviceds,
		//value: "<s:property value='config.defaultServiceTemplateId'/>",
		hiddenId: 'serviceTemplateId',
        hiddenName: 'config.defaultServiceTemplateId',
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
})

function submitForm1(){
	if(checkForm()){
	/*beforChangePasw();*/
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
						}
					});
				} else {
					Ext.Msg.alert('失败',resp.msg, function(button){
						if(button == 'ok'){
							//location.href= '<%=request.getContextPath() %>/user_list.do' ;
							//history.go(-1);
						}
					});
				}
		    },
		    failure:function(result,request){
				Ext.Msg.alert('失败',result.responseText);
		    }
		});
	}
}
function checkForm(){
	return true;
}
</script>
</body>
</html>