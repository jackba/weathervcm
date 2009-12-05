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
			url : 'conftemplate_search.do'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'totalProperty',
			root : 'root'
		}, [{
			name : 'confTemplateId'
		}, {
			name : 'confTemplateName'
		}, {
			name : 'subject'
		}, {
			name : 'createTime',
			type : 'date',
			mapping : 'createTime.time',
			dateFormat : 'time'
		}, {
			name : 'serviceTemplateDesc'
		}])
	});
	ds.load({
		params : {
			start : 0,
			limit : limit,
		}
	});
	initGrid();
}
// 初始化显示表格
function initGrid() {
	Ext.QuickTips.init();
	var sm = new Ext.grid.CheckboxSelectionModel();

	var cm = new Ext.grid.ColumnModel([sm, {
		dataIndex : 'confTemplateId',
		hidden : true
	},{
		header : "模板名称",
		width: Ext.get("searchArea").getWidth()*0.25,
		sortable : true,
		dataIndex : 'confTemplateName',
		renderer : function(value, p , record){
			//return String.format('<a href="conf_reserveDetail.do?personal=false&conferenceId={0}" target="_blank">{1}</a>',record.data.conferenceId,value);
			var action = "window.parent.createNewPanel('confTemplateDetail_{0}','confTemplate','模板详情','conftemplate_detail.do?confTemplateId={1}');";
			return String.format('<a href="#" onclick='+action+'>{2}</a>',record.data.confTemplateId,record.data.confTemplateId,value);
		}
	}, {
		header : "会议号",
		width: Ext.get("searchArea").getWidth()*0.25,
		sortable : true,
		dataIndex : 'virtualConfId'
	}, {
		header : "会议类型",
		width: Ext.get("searchArea").getWidth()*0.25,
		sortable : true,
		dataIndex : 'serviceTemplateId'
	}, {
		header : "组织单位",
		width : Ext.get("searchArea").getWidth()*0.25,
		sortable : true,
		dataIndex : 'initUnit'
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
		tbar : ['<b>&nbsp;&nbsp;&nbsp;&nbsp;<font color=#990000>表单模板列表</font></b>','->',{
			id : 'btnAdd',
			text : '添加',
			pressed : true,
			tooltip : '添加新虚拟房间',
			iconCls : 'add16',
			onClick : function() {
				//location.href = "conftemplate_add.do";
				window.parent.createNewPanel('conftemplateAdd','confTemplate','添加新表单模板','conftemplate_add.do');
			}
		}, {
			id : 'btnEdit',
			text : '修改',
			pressed : true,
			tooltip : '修改表单模板',
			//iconCls : 'edit',
			iconCls : 'edit16',
			onClick : function() {
				if (sm.getCount() == 1) {
					edit();
				} else {
					Ext.MessageBox.alert('提示', "请选择一条记录!");
				}
			}
		}, {
			id : 'btnDel',
			text : '删除',
			pressed : true,
			tooltip : '删除表单模板',
			iconCls : 'delete16',
			onClick : function() {
				if (sm.hasSelection()) {
					del();
				} else {
					Ext.MessageBox.alert('提示', "请至少选择一条记录!");
				}
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
//	alert(Ext.get('status').dom.value);
	ds.load({
		params : {
			start : start,
			limit : ptb.getPageSize(),
			'totalProperty' : ds.getTotalCount(),
			'confTemplateName' : Ext.get("confTemplateName").dom.value,
			'serviceTemplateId' : Ext.get("serviceTemplateId").dom.value
		}
	});
}
function reset() {
	Ext.get("form1").reset();
}