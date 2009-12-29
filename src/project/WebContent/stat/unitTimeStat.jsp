<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>主会场会议时长统计表</title>
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
	<h1>当前位置：系统资源浏览&nbsp;&gt;&nbsp;<span class="position_current"> 主会场会议时长统计</span></h1>
		<div id="searchArea" class="search">
			
			<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/stat_unitTimeStat.do">
				<table border="1" class="query">
					<tr>
						<th>起始日期：</th>
						<td>
						<input type="text" class="Wdate" id="startDate" name="startDate" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
						</td>
						<th>结束日期：</th>
						<td>
						<input type="text" class="Wdate" id="endDate" name="endDate" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
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
var mychart; //图表
Ext.onReady(function() {
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	Ext.FlashComponent.EXPRESS_INSTALL_URL = "resources/images/default/expressInstall.swf";
	Ext.chart.Chart.CHART_URL = "resources/images/default/charts.swf";
	var today = new Date();
	Ext.getDom('startDate').value = today.format('Y-m-d');
	Ext.getDom('endDate').value = today.format('Y-m-d');
	loadStore();
});
function query() {
	loadStore();
}
var chartEvents = {
           //'mousemove':function(){console.log(['mousemove',arguments])}
          };
function loadStore(){
//	alert(Ext.get('status').dom.value);
	if (validateRequired('startDate','起始日期')
	&& validateRequired('endDate','结束日期')) {
		
	}else{
		return;
	}
	if(mychart==null || mychart==undefined){
		mychart = new Ext.ux.Chart.Fusion.Panel({
       				title       : '主会场会议时长统计',
       				//collapsible : true,
       				renderTo    : 'searchArea',
       				floating:false,
       				fusionCfg   :{ id   : 'chart1'
                     				,listeners: chartEvents  //DOM listeners for the chart Object itself
                  				},
       				autoScroll : true,
       				id       : 'chartpanel',
       				chartURL : '<%=request.getContextPath()%>/FusionCharts/Column3D.swf',
       				dataURL  : '<%=request.getContextPath()%>/stat_searchUnitTimeStat.do?startDate='+Ext.get("startDate").dom.value+'&endDate='+Ext.get("endDate").dom.value,
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
            			text: '重新载入',
            			handler: function(){
							loadStore();
                			//mychart.refreshMedia();
            			}
        			}]
  				});
				mychart.show();
		
	}else{
		mychart.setChartDataURL('<%=request.getContextPath()%>/stat_searchUnitTimeStat.do?startDate='+Ext.get("startDate").dom.value+'&endDate='+Ext.get("endDate").dom.value,true);
		//mychart.refreshMedia();
	}
}
function reset() {
	Ext.get("form1").reset();
}
</script>
</body>
</html>