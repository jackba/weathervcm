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
			url : 'log_search.do'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'totalProperty',
			root : 'root'
		}, [{
			name : 'logId'
		}, {
			name : 'userId'
		}, {
			name : 'loginId'
		}, {
			name : 'userName'
		}, {
			name : 'createTime',
			type : 'date',
			mapping : 'createTime.time',
			dateFormat : 'time'
		}, {
			name : 'logType'
		}, {
			name : 'description'
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
		dataIndex : 'logId',
		hidden : true
	},{
		dataIndex : 'userId',
		hidden : true
	},{
		header : "用户姓名",
		width: Ext.get("searchArea").getWidth()*0.25,
		sortable : true,
		dataIndex : 'userName',
		renderer : function(value, p , record){
			//return String.format('<a href="user_detail.do?userId={0}" target="_blank">{1}</a>',record.data.userId,value);
			var action = "window.parent.createNewPanel('userDetail_{0}','log','用户详情','user_detail.do?userId={1}');";
			return String.format('<a href="#" onclick='+action+'>{2}</a>',record.data.userId,record.data.userId,value);
		}
	}, {
		header : "创建时间",
		width: Ext.get("searchArea").getWidth()*0.25,
		sortable : true,
		dataIndex : 'createTime',
		renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')
	}, {
		header : "日志类型",
		width: Ext.get("searchArea").getWidth()*0.25,
		sortable : true,
		dataIndex : 'logType',
		renderer : function(value, p, record){
    	   		if(record.data.logType == 1){
    	   			return "用户登录";
    	   		}else if(record.data.logType == 2){
    	   			return "用户注销";
    	   		}else if(record.data.logType == 3){
    	   			return "预约会议";
    	   		}
    	   		else if(record.data.logType == 4){
    	   			return "修改会议";
    	   		}
    	   		else if(record.data.logType == 5){
    	   			return "删除会议";
    	   		}
    	   		else if(record.data.logType == 6){
    	   			return "创建用户";
    	   		}
    	   		else if(record.data.logType == 7){
    	   			return "修改用户";
    	   		}
    	   		else if(record.data.logType == 8){
    	   			return "删除用户";
    	   		}
		   }
	}, {
		header : "日志描述",
		width : Ext.get("searchArea").getWidth()*0.25,
		sortable : true,
		dataIndex : 'description'
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
		tbar : ['<b>&nbsp;&nbsp;&nbsp;&nbsp;<font color=#990000>日志列表</font></b>','->'],
		bbar : ptb,
		width: Ext.get("searchArea").getWidth()*0.99,
		height:Ext.get("searchArea").getHeight()*0.99,
		autoHeight : true,
		//cls:vline-on,
		//title : '用户列表',
		//iconCls : 'icon-grid',
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
			'userName' : Ext.get("userName").dom.value,
			'logType' : Ext.get("logType").dom.value,
			'startTime' : Ext.get("startTime").dom.value,
			'endTime' : Ext.get("endTime").dom.value
		}
	});
}
function reset() {
	Ext.get("form1").reset();
}