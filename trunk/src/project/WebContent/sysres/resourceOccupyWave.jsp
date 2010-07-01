<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源占用波动图</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/xtheme-shallowgray.css">
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
	<h1>当前位置：系统资源&nbsp;&gt;&nbsp;<span class="position_current"> 资源占用波动图</span></h1>
		<div id="searchArea" class="search">
			<div id="queryArea" style="display:none">
			<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/res_searchOccupyWave.do">
			<input type="hidden" id="serviceTemplate" name="serviceTemplate" value="10002"/>
				<table border="1" class="query">
					<tr>
						<th>时间范围：</th>
						<td>
						<select name="range" id="range">
						<option value="1" selected="true">一天</option>
						<option value="2">一周</option>
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
			</div>
		</div><!--end of searchArea-->
	</div><!--end of wrap-->
<script language="javascript">
var mychart; //图表
var win;
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
	day.setValue(new Date());
	loadStore();
});
function query() {
	loadStore();
	win.hide();
}
var chartEvents = {
           //'mousemove':function(){console.log(['mousemove',arguments])}
          };
function loadStore(){
//	alert(Ext.get('status').dom.value);
	if (validateRequired('day','日期')) {
		
	}else{
		return;
	}
	if(mychart==null || mychart==undefined){
		mychart = new Ext.ux.Chart.Fusion.Panel({
       				title       : '资源占用波动图',
       				//collapsible : true,
       				renderTo    : 'searchArea',
       				floating:false,
       				fusionCfg   :{ id   : 'chart1'
                     				,listeners: chartEvents  //DOM listeners for the chart Object itself
                  				},
       				autoScroll : true,
       				id       : 'chartpanel',
       				chartURL : 'FusionCharts/Line.swf',
       				dataURL  : 'res_searchOccupyWave.do?serviceTemplate='+Ext.get("serviceTemplate").dom.value+'&day='+Ext.get("day").dom.value+'&range='+Ext.get("range").dom.value,
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
							text: '查询',
							handler: function(){
								win = new Ext.Window({
									title: '查询',
									closable:true,
									closeAction:'hide',
									width:800,
									height:150,
									items:[{
										xtype:"panel",
										title:"",
										contentEl:"queryArea"
										//html:Ext.getDom("queryArea").innerHTML
									}]
								});
								win.show(this);
								Ext.getDom("queryArea").style.display="block";
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
		mychart.setChartDataURL('res_searchOccupyWave.do?serviceTemplate='+Ext.get("serviceTemplate").dom.value+'&day='+Ext.get("day").dom.value+'&range='+Ext.get("range").dom.value,true);
		//mychart.refreshMedia();
	}
}
function reset() {
	Ext.get("form1").reset();
}
</script>
</body>
</html>