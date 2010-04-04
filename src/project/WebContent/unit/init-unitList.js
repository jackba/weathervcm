var ds;// 数据源
var tds; // 终端数据源
var grid;// 数据显示表格
var searchForm;// 查询表单
var limit = 5;// 每页显示的记录数
var ptb;// 分页控件
var win;

// 页面加载后执行的代码
Ext.onReady(function() {
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	initData();
});

// 初始化数据
function initData() {
	ds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'unit_search.do'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'totalProperty',
			root : 'root'
		}, [{
			name : 'unitId'
		}, {
			name : 'unitName'
		}, {
			name : 'partyId'
		}, {
			name : 'partyName'
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
	
	tds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'terminal_searchAll.do'
		}),
		reader : new Ext.data.JsonReader({
			root : 'root'
		}, [{
			name : 'terminalId'
		}, {
			name : 'terminalName'
		}, {
			name : 'dialString'
		}])
	});
	tds.load();
	var terminalComboWithTooltip = new Ext.form.ComboBox({
		store: tds,
		hiddenId: 'partyId',
        hiddenName: 'unit.partyId',
        valueField: 'terminalId',
        displayField: 'terminalName',
        typeAhead: true,
        forceSelection: false,
        mode: 'local',
        triggerAction: 'all',
        emptyText: '请选择终端...',
        selectOnFocus: true,
        renderTo: 'unit_terminal'
    });
	
	initGrid();
}
// 初始化显示表格
function initGrid() {
	Ext.QuickTips.init();
	var sm = new Ext.grid.CheckboxSelectionModel();

	var cm = new Ext.grid.ColumnModel([sm, {
		dataIndex : 'roomId',
		hidden : true
	},{
		header : "单位名称",
		width: Ext.get("searchArea").getWidth()*0.3333,
		sortable : true,
		dataIndex : 'unitName',
		renderer : function(value, p , record){
			//return String.format('<a href="unit_detail.do?unitId={0}" target="_blank">{1}</a>',record.data.unitId,value);
			var action="window.parent.createNewPanel('unitDetail_{0}','siteinfo','单位详情','unit_detail.do?unitId={1}');";
			return String.format('<a href="#" onclick='+action+'>{2}</a>',record.data.unitId,record.data.unitId,value);
		}
	}, {
		header : "终端",
		width: Ext.get("searchArea").getWidth()*0.3333,
		sortable : true,
		dataIndex : 'partyName'
	}, {
		header : "描述",
		width: Ext.get("searchArea").getWidth()*0.3333,
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
		tbar : ['<b>&nbsp;&nbsp;&nbsp;&nbsp;<font color=#990000>单位列表</font></b>','->',{
			id : 'btnQuery',
			text : '查询',
			pressed : true,
			tooltip : '查询单位',
			iconCls : 'add16',
			onClick : function(){
				win = new Ext.Window({
					title: '查询',
					closable:true,
					closeAction:'hide',
					width:800,
					height:150,
					items:[{
						xtype:"panel",
						title:"",
						contentEl:"queryArea"
						//html:Ext.getDom("queryArea").innerHTML
					}]
				});
				win.show(this);
				Ext.getDom("queryArea").style.display="block";
			}
		},{
			id : 'btnAdd',
			text : '添加',
			pressed : true,
			tooltip : '添加新单位',
			iconCls : 'add16',
			onClick : function() {
				//location.href = "unit_add.do";
				window.parent.createNewPanel('unitAdd','siteinfo','添加新单位','unit_add.do');
			}
		}, {
			id : 'btnEdit',
			text : '修改',
			pressed : true,
			tooltip : '修改单位',
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
			tooltip : '删除单位',
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

	function edit() {
		var list = sm.getSelections();
		var id = list[0].data["unitId"];
		//location.href = "unit_modify.do?unitId="+id;
		window.parent.createNewPanel('unitModify_'+id,'siteinfo','修改单位','unit_modify.do?unitId='+id);
	}
	
	function del() {
		Ext.MessageBox.confirm('提示', '确定要删除单位吗?', function(button) {
			if (button == 'yes') {
				var list = sm.getSelections();
				var ids = [];
				for (var i = 0; i < list.length; i++) {
					ids[i] = list[i].data["unitId"];
				}
				unitService.deleteUnits(ids, function(data) {
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
	win.hide();
}
function loadStore(start){
	ds.load({
		params : {
			start : start,
			limit : ptb.getPageSize(),
			'totalProperty' : ds.getTotalCount(),
			'unitName' : Ext.get("unitName").dom.value,
			'partyId' : Ext.get("partyId").dom.value
		}
	});
}
function reset() {
	Ext.get("form1").reset();
}