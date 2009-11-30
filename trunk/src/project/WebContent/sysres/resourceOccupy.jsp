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
var ds;// 数据源
var mychart; //图表
Ext.onReady(function() {
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	Ext.FlashComponent.EXPRESS_INSTALL_URL = "resources/images/default/expressInstall.swf";
	Ext.chart.Chart.CHART_URL = "resources/images/default/charts.swf";
	ds = new Ext.data.Store({
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
	});
	loadStore();
});
function loadStore(){
	if(mychart==null || mychart==undefined){
		mychart = new Ext.Panel({
        title: '资源占用情况',
        frame:true,
        renderTo: 'searchArea',
        width: Ext.get("searchArea").getWidth()*0.98,
		//autoWidth : true,
		//height:Ext.get("searchArea").getHeight()*0.99,
		//autoHeight : true,
		height: 400,
		layout:'fit',
		tbar: [{
			xtype:'tbfill'
		},{
            text: '重新载入',
            handler: function(){
                loadStore();
            }
        }],
        items: {
            xtype: 'columnchart',
            store: ds,
            xField: 'serviceTemplateName',
            yAxis: new Ext.chart.NumericAxis({
                title: '数量'
            }),
			xAxis: new Ext.chart.CategoryAxis({
				title: "会议类型"
			}),
            tipRenderer : function(chart, record, index, series){
                if(series.yField == 'availableNum'){
                    return record.data.availableNum + ' 点数空闲，' + record.data.serviceTemplateName;
                }else{
                    return record.data.occupyNum + ' 点数占用在 ' + record.data.serviceTemplateName;
                }
            },
            chartStyle: {
                padding: 10,
                animationEnabled: true,
                font: {
                    name: 'Tahoma',
                    color: 0x444444,
                    size: 11
                },
                dataTip: {
                    padding: 5,
                    border: {
                        color: 0x99bbe8,
                        size:1
                    },
                    background: {
                        color: 0xDAE7F6,
                        alpha: .9
                    },
                    font: {
                        name: 'Tahoma',
                        color: 0x15428B,
                        size: 10,
                        bold: true
                    }
                }
            },
            series: [{
                type: 'column',
                displayName: '占用',
                yField: 'occupyNum',
                style: {
					labelPosition: 'outside',
                    //image:'bar.gif',
                    mode: 'stretch',
                    //color:0x99BBE8
					color:0xFF0000
                }
            },{
                type:'column',
                displayName: '空闲',
                yField: 'availableNum',
                style: {
					labelPosition: 'outside',
					//image:'bar.gif',
                    mode: 'stretch',
                    //color:0x99BBE8
                    //color: 0x15428B
					color:0x0000FF
                }
            }]
        }
    });
	}		
	ds.load();
}
</script>
</body>
</html>