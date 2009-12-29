<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源占用情况</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/edgrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/validate.js"></script>
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
	<h1>当前位置：系统资源&nbsp;&gt;&nbsp;<span class="position_current">资源占用情况</span></h1>
		<div id="searchArea" class="search">
			<p>正在召开的会议数: <span id="totalConfs"></span></p><br/>
		</div><!--end of searchArea-->
	</div><!--end of wrap-->
<script language="javascript">
var mychart; //图表
Ext.onReady(function() {
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	Ext.FlashComponent.EXPRESS_INSTALL_URL = "resources/images/default/expressInstall.swf";
	Ext.chart.Chart.CHART_URL = "resources/images/default/charts.swf";
	/*ds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'res_searchOccupy.do'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'totalProperty',
			root : 'root'
		}, [{
			name : 'serviceTemplateName'
		}, {
			name : 'occupyNum'
		}, {
			name : 'availableNum'
		}]),
		listeners : {
			load : function(thisStore, records, options){
				Ext.getDom("totalConfs").innerHTML=""+thisStore.reader.jsonData.totalConfs;
			}
		}
	});*/
	loadStore();
});
var chartEvents = {
           //'mousemove':function(){console.log(['mousemove',arguments])}
          };
function getTotalConfs(){
	Ext.Ajax.request({
		url: 'res_getTotalConfs.do',
		success: function(result,request){
			Ext.getDom("totalConfs").innerHTML = ""+result.responseText;
		},
		failure: function(result,request){
			Ext.getDom("totalConfs").innerHTML = "-1";
		}
	});
}
function loadStore(){
	if(mychart==null || mychart==undefined){
		mychart = new Ext.ux.Chart.Fusion.Panel({
       				title       : '资源占用情况',
       				//collapsible : true,
       				renderTo    : 'searchArea',
       				floating:false,
       				fusionCfg   :{ id   : 'chart1'
                     				,listeners: chartEvents  //DOM listeners for the chart Object itself
                  				},
       				autoScroll : true,
       				id       : 'chartpanel',
       				chartURL : 'FusionCharts/MSColumn3D.swf',
       				dataURL  : 'res_searchOccupy.do',
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
				getTotalConfs();
	}else{
		mychart.setChartDataURL('res_searchOccupy.do',true);
		getTotalConfs();
		//mychart.refreshMedia();
	}
}
</script>
</body>
</html>