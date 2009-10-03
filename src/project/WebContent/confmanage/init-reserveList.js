var ds;// 数据源
var serviceds; // 会议模板数据源
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
			url : 'conf_searchReserves.do'
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
		}])
	});
	ds.load({
		params : {
			start : 0,
			limit : limit
		}
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
		header : "名称",
		width: Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'subject',
		renderer : function(value, p , record){
			return String.format('<a href="conf_reserveDetail.do?conferenceId={0}" target="_blank">{1}</a>',record.data.conferenceId,value);
		}
	}, {
		header : "预约时间",
		width: Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'createTime',
		renderer: Ext.util.Format.dateRenderer('Y-m-d H:i:s')
	}, {
		header : "状态",
		width: Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'statusStr'
	}, {
		header : "起始时间",
		width : Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'startTime'
	}, {
		header : "时长(分钟)",
		width : Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'timeLong'
	}, {
		header : "组织单位",
		width : Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'initUnit'
	}, {
		header : "会议类型",
		width : Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'serviceTemplate'
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
		tbar : ['<b>&nbsp;&nbsp;&nbsp;&nbsp;<font color=#990000>预约会议列表</font></b>','->',{
			id : 'btnAdd',
			text : '添加',
			pressed : true,
			tooltip : '预约会议',
			iconCls : 'add16',
			onClick : function() {
				location.href = "conf_reserveConf.do";
			}
		}, {
			id : 'btnEdit',
			text : '修改',
			pressed : true,
			tooltip : '修改会议预约',
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
			tooltip : '删除会议预约',
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
		//cls:vline-on,
		//title : '用户列表',
		//iconCls : 'icon-grid',
		renderTo : 'searchArea'
	});

	function edit() {
		/*Ext.MessageBox.confirm('提示', '修改操作员基本信息', function(btn){
			if(btn == 'yes'){
				var list = sm.getSelections();
				location.href = "user_modify.do?userId="+list[0].data["id"];
			}
		});*/
		var list = sm.getSelections();
		var id = list[0].data["conferenceId"];
		location.href = "conf_modifyReserve.do?conferenceId="+id;
	}
	
	function del() {
		Ext.MessageBox.confirm('提示', '确定要删除会议预约吗?', function(button) {
			if (button == 'yes') {
				var list = sm.getSelections();
				var ids = [];
				for (var i = 0; i < list.length; i++) {
					ids[i] = list[i].data["conferenceId"]+","+list[i].data["radConferenceId"];
				}
				confService.deleteReserves(ids, function(data) {
					if (data > 0) {
						Ext.MessageBox.alert('提示', "删除" + data + '条数据成功!');
						// 如果把当页数据删除完毕，则跳转到上一页！
						if (data == ptb.store.getTotalCount() - ptb.cursor) {
							if(ptb.cursor!=0){
								ptb.cursor = ptb.cursor - ptb.pageSize;
							}
						}
						loadStore(ptb.cursor);
					} else {
						Ext.MessageBox.alert('提示', "删除数据失败!");
					}
				});
			}
		});
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
			'serviceTemplate' : Ext.get("serviceTemplate").dom.value
		}
	});
}
function reset() {
	Ext.get("form1").reset();
}