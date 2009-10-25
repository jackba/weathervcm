var ds;// 数据源
var grid;// 数据显示表格
var searchForm;// 查询表单
var limit = 5;// 每页显示的记录数
var ptb;// 分页控件

// 页面加载后执行的代码
Ext.onReady(function() {
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	initData();
});

// 初始化数据
function initData() {
	ds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'conf_searchRunnings.do'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'totalProperty',
			root : 'root'
		}, [{
			name : 'conferenceId'
		}, {
			name : 'radConferenceId'
		}, {
			name : 'subject'
		}, {
			name : 'createTime',
			type : 'date',
			mapping : 'createTime.time',
			dateFormat : 'time'
		}, {
			name : 'status'
		}, {
			name : 'statusStr'
		}, {
			name : 'startTime'
		}, {
			name : 'timeLong'
		}, {
			name : 'initUnit'
		}, {
			name : 'serviceTemplate'
		}, {
			name : 'serviceTemplateDesc'
		}, {
			name : 'dialableNumber'
		}])
	});
	ds.load({
		params : {
			start : 0,
			limit : limit,
			'listType' : 'running'
		}
	});
	initGrid();
}
// 初始化显示表格
function initGrid() {
	Ext.QuickTips.init();
	var sm = new Ext.grid.CheckboxSelectionModel();

	var cm = new Ext.grid.ColumnModel([sm, {
		dataIndex : 'conferenceId',
		hidden : true
	},{
		dataIndex : 'radConferenceId',
		hidden : true
	},{
		header : "主题",
		width: Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'subject',
		renderer : function(value, p , record){
			//return String.format('<a href="conf_reserveDetail.do?personal=false&conferenceId={0}" target="_blank">{1}</a>',record.data.conferenceId,value);
			var action = "window.parent.createNewPanel('confReserveDetail_{0}','currentConf','会议详情','conf_reserveDetail.do?personal=false%26conferenceId={1}');";
			return String.format('<a href="#" onclick='+action+'>{2}</a>',record.data.conferenceId,record.data.conferenceId,value);
		}
	}, {
		header : "会议号",
		width: Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'dialableNumber'
	}, {
		header : "组织单位",
		width : Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'initUnit'
	}, {
		header : "起始时间",
		width : Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'startTime',
		renderer : function(value, p, record){
			return new Date(value).format('Y-m-d H:i:s');
		}
	}, {
		header : "时长(分钟)",
		width : Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'timeLong'
	}, {
		header : "状态",
		width: Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'statusStr'
	}, {
		header : "会议类型",
		width : Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'serviceTemplateDesc'
	}]);
	// 工具栏对象
	ptb = new Ext.PagingToolbar({
		plugins : new Ext.ux.Andrie.pPageSize(),
		pageSize : limit,
		store : ds,
		displayInfo : true,
		displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
		emptyMsg : "对不起，查询记录为空!",
		doLoad : loadStore
		,items:['-', { 
					pressed: true, 
					enableToggle:true, 
					text:'重置列表宽度 ', 
					tooltip : '重置列表宽度',
					iconCls : 'resetWidth16',
					onClick : function() {
						window.location.reload(); 
					} 
				}] 

	});
	// 表格对象
	grid = new Ext.grid.GridPanel({
		id : 'button-grid',
		border : false,
		buttonAlign : 'right',
		ds : ds,
		cm : cm,
		sm : sm,
		stripeRows : true,
		forceFit:true,
		loadMask : true,
		//viewConfig : {forceFit : true},
		tbar : ['<b>&nbsp;&nbsp;&nbsp;&nbsp;<font color=#990000>正在召开的会议</font></b>','->',{
			id : 'btnMonitor',
			text : '监控',
			pressed : true,
			tooltip : '会议监控',
			iconCls : 'edit16',
			onClick : function() {
				if (sm.getCount() == 1) {
					var list = sm.getSelections();
					var id = list[0].data["radConferenceId"];
					openMonitorWin(id);
				} else {
					Ext.MessageBox.alert('提示', "请选择一条记录!");
				}
			}
		}, {
			id : 'btnPdf',
			text : '生成PDF报表',
			pressed : true,
			tooltip : '生成PDF报表',
			//iconCls : 'edit',
			iconCls : 'edit16',
			onClick : function() {
				generatePDF();
			}
		}, {
			id : 'btnExcel',
			text : '生成Excel报表',
			pressed : true,
			tooltip : '生成Excel报表',
			iconCls : 'edit16',
			onClick : function() {
				generateExcel();
			}
		}],
		bbar : ptb,
		width: Ext.get("searchArea").getWidth()*0.98,
		height:Ext.get("searchArea").getHeight()*0.98,
		autoHeight : true,
		//cls:vline-on,
		//title : '用户列表',
		//iconCls : 'icon-grid',
		renderTo : 'searchArea'
	});

	function generatePDF(){
		window.open("conf_generatePDF.do?"+makeParams(),"download");
		//location.href = "conf_generatePDF.do?conferenceId="+id+makeParams();
	}
	function generateExcel(){
		window.open("conf_generateExcel.do?"+makeParams(),"download")
		//location.href = "conf_generateExcel.do?conferenceId="+id+makeParams();
	}
	function makeParams(){
		var params = "start="+ptb.cursor;
		params += "&limit="+ptb.getPageSize();
		params += "&totalProperty="+ds.getTotalCount();
		params += "&subject="+Ext.get("subject").dom.value;
		params += "&dialableNumber="+Ext.get("dialableNumber").dom.value;
		params += "&lisType=running";
		return params;
	}
}

function query() {
	loadStore(0);
}
function loadStore(start){
//	alert(Ext.get('status').dom.value);
	ds.load({
		params : {
			start : start,
			limit : ptb.getPageSize(),
			'totalProperty' : ds.getTotalCount(),
			'subject' : Ext.get("subject").dom.value,
			'dialableNumber' : Ext.get("dialableNumber").dom.value,
			'listType' : 'running'
		}
	});
}
function reset() {
	Ext.get("form1").reset();
}
function openMonitorWin(confId) { 
    	window.open(monitorUrl+"?ConfId="+confId,"newwindow", "fullscreen=yes,toolbar=no, menubar=no, scrollbars=no, location=no, status=no");
}