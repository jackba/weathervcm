var ds;// 数据源
var grid;// 数据显示表格

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
			'update' : 'true'
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
			//return String.format('<a href="service_detail.do?serviceTemplateId={0}" target="_blank">{1}</a>',record.data.serviceTemplateId,value);
			var action = "window.parent.createNewPanel('serviceDetail_{0}','service','会议模板详情','service_detail.do?serviceTemplateId={1}');";
			return String.format('<a href="#" onclick='+action+'>{2}</a>',record.data.serviceTemplateId,record.data.serviceTemplateId,value);
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
//				location.href = "service_update.do";
				update();
			}
		}],
		width: Ext.get("searchArea").getWidth()*0.99,
		height:Ext.get("searchArea").getHeight()*0.99,
		autoHeight : true,
		renderTo : 'searchArea'
	});
}
function update() {
	ds.load({
		params : {
			'update' : 'true'
		}
	});	
}