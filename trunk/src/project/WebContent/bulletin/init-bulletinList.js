var ds;// 数据源
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
			url : 'bulletin_search.do'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'totalProperty',
			root : 'root'
		}, [{
			name : 'bulletinId'
		}, {
			name : 'userId'
		},{
			name : 'loginId'
		}, {
			name : 'title'
		},{
			name : 'effectiveTime',
			type : 'date',
			mapping : 'effectiveTime.time',
			dateFormat : 'time'
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
		dataIndex : 'bulletinId',
		hidden : true
	},{
		dataIndex : "userId",
		hidden : true
	},{
		header : "标题",
		width: Ext.get("searchArea").getWidth()*0.33,
		sortable : true,
		dataIndex : 'title',
		renderer : function(value, p, record){
			//return String.format('<a href="bulletin_detail.do?bulletinId={0}" target="_blank">{1}</a>',record.data.bulletinId,value);
			var action = "window.parent.createNewPanel('bulletinDetail_{0}','bulletinManage','公告详情','bulletin_detail.do?bulletinId={1}');";
			return String.format('<a href="#" onclick='+action+'>{2}</a>',record.data.bulletinId,record.data.bulletinId,value);
		}
	}, {
		header : "操作员名称",
		width: Ext.get("searchArea").getWidth()*0.33,
		sortable : true,
		dataIndex : 'loginId',
		renderer : function(value, p , record){
			return String.format('<a href="user_detail.do?userId={0}" target="_blank">{1}</a>',record.data.userId,value);
		}
	}, {
		header : "发布时间",
		width: Ext.get("searchArea").getWidth()*0.33,
		sortable : false,
		dataIndex : 'effectiveTime',
		renderer: Ext.util.Format.dateRenderer("Y-m-d H:i:s")
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
		tbar : ['<b>&nbsp;&nbsp;&nbsp;&nbsp;<font color=#990000>公告列表</font></b>','->', {
			id : 'btnQuery',
			text : '查询',
			pressed : true,
			tooltip : '查询公告',
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
		}, {
			id : 'btnAdd',
			text : '添加',
			pressed : true,
			tooltip : '添加新公告',
			iconCls : 'add16',
			onClick : function() {
//				location.href = "bulletin_add.do";
				window.parent.createNewPanel('bulletinIssue','bulletinManage','添加新公告','bulletin_add.do');
			}
		}, {
			id : 'btnEdit',
			text : '修改',
			pressed : true,
			tooltip : '修改公告',
			//iconCls : 'edit',
			iconCls : 'edit16',
			onClick : function() {
				if (sm.getCount() == 1) {
				//location.href = "user_modify.do?userId="+list[0].data["id"];
					edit();
				} else {
					Ext.MessageBox.alert('提示', "请选择一条记录!");
				}
			}
		}, {
			id : 'btnDel',
			text : '删除',
			pressed : true,
			tooltip : '删除公告',
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
		var list = sm.getSelections();
		var id = list[0].data["bulletinId"];
//		location.href = "bulletin_modify.do?bulletinId="+id;
		window.parent.createNewPanel('bulletinModify_'+id,'bulletinManage','修改公告','bulletin_modify.do?bulletinId='+id);
	}
	
	function del() {
		Ext.MessageBox.confirm('提示', '确定要删除公告吗?', function(button) {
			if (button == 'yes') {
				var list = sm.getSelections();
				var ids = [];
				for (var i = 0; i < list.length; i++) {
					ids[i] = list[i].data["bulletinId"];
				}
				bulletinService.deleteBulletins(ids, function(data) {
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
//	alert(Ext.get('status').dom.value);
	ds.load({
		params : {
			start : start,
			limit : ptb.getPageSize(),
			'title' : Ext.get('title').dom.value,
			'content' :     Ext.get('content').dom.value,
			'startTime' :   Ext.get('startTime').dom.value,
			'endTime' : Ext.get('endTime').dom.value,
			'totalProperty' : ds.getTotalCount()
		}
	});
}
function reset() {
	Ext.get("form1").reset();
}