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
						<th>会议类型：</th>
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
var ds;// 数据源
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
	ds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'res_searchAvailable.do'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'totalProperty',
			root : 'root'
		}, [{
			name : 'hourMinutes'
		}, {
			name : 'occupyNum'
		}, {
			name : 'availableNum'
		}])
	});
	
	
	serviceds = new Ext.data.Store({
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
		hiddenId: 'serviceTemplate',
        hiddenName: 'serviceTemplate',
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
	
});
function query() {
	loadStore();
}
function loadStore(){
//	alert(Ext.get('status').dom.value);
	if (validateRequired('serviceTemplate','会议类型')
			&& validateRequired('day','日期')
			) {
		
	}else{
		return;
	}
	if(mychart==null || mychart==undefined){
		mychart = new Ext.Panel({
        title: '可用资源情况',
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
            }
        }],
        items: {
            xtype: 'columnchart',
            store: ds,
            xField: 'hourMinutes',
            yAxis: new Ext.chart.NumericAxis({
                title: '数量'
            }),
			xAxis: new Ext.chart.CategoryAxis({
				title: "时间"
			}),
            tipRenderer : function(chart, record, index, series){
                if(series.yField == 'availableNum'){
                    return record.data.availableNum + ' 点数空闲，在 ' + record.data.hourMinutes;
                }else{
                    return record.data.occupyNum + ' 点数占用在 ' + record.data.hourMinutes;
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
	ds.load({
		params : {
			'serviceTemplate' : Ext.get("serviceTemplate").dom.value,
			'day' : Ext.get("day").dom.value,
			'interval' : Ext.get("interval").dom.value
		}
	});
}
function reset() {
	Ext.get("form1").reset();
}
</script>
</body>
</html>