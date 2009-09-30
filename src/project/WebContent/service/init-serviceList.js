var ds;// 数据源
var grid;// 数据显示表格
var searchForm;// 查询表单
var limit = 10;// 每页显示的记录数
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
			url : 'service_search.do'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'totalProperty',
			root : 'root'
		}, [{
			name : 'serviceTemplateId'
		}, {
			name : 'servicePrefix'
		}, {
			name : 'serviceTemplateName'
		}, {
			name : 'serviceTemplateDesc'
		}, {
			name : 'matchingRate'
		}])
	});
	ds.load({
		params : {
			start : 0,
			limit : limit
		}
	});
	initGrid();
}
// 初始化显示表格
function initGrid() {
	Ext.QuickTips.init();
	var sm = new Ext.grid.CheckboxSelectionModel();

	var cm = new Ext.grid.ColumnModel([sm, {
		dataIndex : 'serviceTemplateId',
		hidden : true
	},{
		header : "模板名称",
		width: Ext.get("searchArea").getWidth() * 0.25,
		sortable : true,
		dataIndex : 'serviceTemplateName',
		renderer : function(value, p , record){
			return String.format('<a href="service_detail.do?serviceTemplateId={0}" target="_blank">{1}</a>',record.data.serviceTemplateId,value);
		}
	}, {
		header : "模板前缀",
		width: Ext.get("searchArea").getWidth() * 0.25,
		sortable : true,
		dataIndex : 'servicePrefix'
	}, {
		header : "模板描述",
		width: Ext.get("searchArea").getWidth() * 0.25,
		sortable : false,
		dataIndex : 'serviceTemplateDesc'
	}, {
		header : "带宽",
		width: Ext.get("searchArea").getWidth() * 0.25,
		sortable : true,
		dataIndex : 'matchingRate'
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
		tbar : ['<b>&nbsp;&nbsp;&nbsp;&nbsp;<font color=#990000>会议模板列表</font></b>','->',{
			id : 'btpdate',
			text : '更新',
			pressed : true,
			tooltip : '更新会议模板列表',
			iconCls : 'add16',
			onClick : function() {
				location.href = "service_update.do";
			}
		}],
		bbar : ptb,
		width: Ext.get("searchArea").getWidth()*0.99,
		height:Ext.get("searchArea").getHeight()*0.99,
		autoHeight : true,
		renderTo : 'searchArea'
	});
}

function query() {
	loadStore(0);
}
function loadStore(start){
	ds.load({
		params : {
			start : start,
			limit : ptb.getPageSize(),
			'totalProperty' : ds.getTotalCount()
		}
	});
}