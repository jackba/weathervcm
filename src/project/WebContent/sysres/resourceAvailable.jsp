<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>可用资源</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/edgrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/calendar/WdatePicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/uxmediapak.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/uxflashpak.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/uxfusionpak.js"></script>
<style type="text/css">
.STYLE3 {
	font-size: 12px
}

#button-grid .x-panel-body {
	border: 1px solid #99bbe8;
	border-top: 0 none;
}
</style>
</head>
<body>
<div class="wrap">
	<h1>当前位置：系统资源&nbsp;&gt;&nbsp;<span class="position_current"> 可用资源</span></h1>
		<div id="searchArea" class="search">
			
			<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/res_searchAvailable.do">
				<table border="1" class="query">
					<tr>
						<th>会议模板：</th>
						<td>
							<div id="service_template"></div>
						</td>
						<th>时间间隔（分钟）：</th>
						<td>
						<select name="interval" id="interval">
						<option value="60" selected="selected">60</option>
						<option value="30">30</option>
						<option value="15">15</option>
						</select>
						</td>
						<th>日期：</th>
						<td>
						<!--<input type="text" class="Wdate" id="day" name="day" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>-->
						<div id="dayDiv"></div>
						</td>
					</tr>
				</table>
				<div align="center" class="query_btn">
						<input type="button" value="查询" id="btnQuery"	class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="query()" /> &nbsp;&nbsp;
						<input type="button" value="重置" id="btnReset"	class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="reset()" />
				</div>
			</form>
		</div><!--end of searchArea-->
	</div><!--end of wrap-->
<script language="javascript">
var serviceds; // 会议模板数据源
var mychart; //图表
var day;
Ext.onReady(function() {
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	Ext.FlashComponent.EXPRESS_INSTALL_URL = "resources/images/default/expressInstall.swf";
	Ext.chart.Chart.CHART_URL = "resources/images/default/charts.swf";
	day = new Ext.form.DateField({
		name:'day',
		id:'day',
		readOnly:true,
		format:'Y\-m\-d',
		renderTo:'dayDiv'
	});
	
	
	serviceds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'service_searchex.do'
		}),
		reader : new Ext.data.JsonReader({
			root : 'root'
		}, [{
			name : 'serviceTemplateId'
		}, {
			name : 'serviceTemplateName'
		}, {
			name : 'serviceTemplateDesc'
		}, {
			name : 'serviceTemplateClassification'
		}])
	});
	serviceds.load();
	var serviceComboWithTooltip = new Ext.form.ComboBox({
		store: serviceds,
		hiddenId: 'serviceTemplateId',
        hiddenName: 'serviceTemplateId',
        valueField: 'serviceTemplateId',
        displayField: 'serviceTemplateClassification',
        typeAhead: true,
        forceSelection: false,
        mode: 'local',
        triggerAction: 'all',
        emptyText: '请选择会议模板...',
        selectOnFocus: true,
        renderTo: 'service_template'
    });
	
});
function query() {
	loadStore();
}
var chartEvents = {
           //'mousemove':function(){console.log(['mousemove',arguments])}
          };
function loadStore(){
//	alert(Ext.get('status').dom.value);
	if (validateRequired('serviceTemplateId','会议类型')
			&& validateRequired('day','日期')
			) {
		
	}else{
		return;
	}
	if(mychart==null || mychart==undefined){
		mychart = new Ext.ux.Chart.Fusion.Panel({
       				title       : '可用资源情况',
       				//collapsible : true,
       				renderTo    : 'searchArea',
       				floating:false,
       				fusionCfg   :{ id   : 'chart1'
                     				,listeners: chartEvents  //DOM listeners for the chart Object itself
                  				},
       				autoScroll : true,
       				id       : 'chartpanel',
       				chartURL : 'FusionCharts/StackedColumn3D.swf',
       				dataURL  : 'res_searchAvailable1.do?serviceTemplateId='+Ext.get("serviceTemplateId").dom.value+'&day='+Ext.get("day").dom.value+'&interval='+Ext.get('interval').dom.value,
       				mediaMask  : {msg:'正在装载报表'},
       				autoMask  : true,
       				width     : Ext.get("searchArea").getWidth()*0.98,
       				height    : 500,
       				listeners :{
           				show  : function(p){
               				if(p.floating)p.setPosition(p.x||10,p.y||10);
               				}
           				//,chartload : function(p,obj){
               			//	p.setTitle(p.title.split(":")[0]+': Loaded.');
               			//	}
           				//,chartrender : function(p,obj){
               			//	p.setTitle(p.title.split(":")[0]+': Rendered.');
               			//	}
       				},
					tbar: [{
						xtype:'tbfill'
						},{
			text: '上一天',
			handler: function(){
				if(day.getValue()!=null && day.getValue()!=undefined && day.getValue()!="")
					day.setValue(day.getValue().add(Date.DAY,-1));
				loadStore();
			}
		},{
			xtype: 'tbseparator'
		},{
			text: '下一天',
			handler: function(){
				if(day.getValue()!=null && day.getValue()!=undefined && day.getValue()!="")
					day.setValue(day.getValue().add(Date.DAY,1));
				loadStore();
			}
		},{
			xtype: 'tbseparator'
		},{
            			text: '重新载入',
            			handler: function(){
							loadStore();
                			//mychart.refreshMedia();
            			}
        			}]
  				});
				mychart.show();
		
	}else{
		mychart.setChartDataURL('res_searchAvailable1.do?serviceTemplateId='+Ext.get("serviceTemplateId").dom.value+'&day='+Ext.get("day").dom.value+'&interval='+Ext.get('interval').dom.value,true);
		//mychart.refreshMedia();
	}
}
function reset() {
	Ext.get("form1").reset();
}
</script>
</body>
</html>