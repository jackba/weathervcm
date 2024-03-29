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
			url : 'role_search.do',
			failure : function(response, options){
				Ext.Msg.confirm("警告",response.responseText,function(btn){
					if(btn=='yes'){
						window.top.location.href="login.jsp";
					}
				});
			}
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'totalProperty',
			root : 'root'
		}, [{
			name : 'id'
		}, {
			name : 'name'
		}, {
			name : 'remark'
		}, {
			name : 'status'
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
		dataIndex : 'id',
		hidden : true
	},{
		header : "角色名称",
		width : 20,
		sortable : true,
		dataIndex : 'name',
		renderer : function(value, p , record){
			//return String.format('<a href="role_detail.do?roleId={0}" target="_blank">{1}</a>',record.data.id,value);
			var action = "window.parent.createNewPanel('roleDetail_{0}','角色详情','role_detail.do?roleId={1}');";
			return String.format('<a href="#" onclick='+action+'>{2}</a>',record.data.id,record.data.id,value);
		}
	}, {
		header : "角色描述",
		width : 40,
		sortable : false,
		dataIndex : 'remark'
	}, {
		header : "角色状态",
		width : 15,
		sortable : true,
		dataIndex : 'status',
		renderer : function(value, p, record){
			if(record.data.status == '0'){
				return "有效";
			}else{
				return "无效";
			}
		}
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
//		doLoad : function(start) {
//			var o = {}, pn = this.paramNames;
//			o[pn.start] = start;
//			o[pn.limit] = this.pageSize;
//			Ext.apply(o, {
//				'taskName' : Ext.get('taskName').dom.value
//			});
//			Ext.apply(o, {
//				'adName' : Ext.get('adName').dom.value
//			});
//			Ext.apply(o, {
//				'orderName' : Ext.get('orderName').dom.value
//			});
//			Ext.apply(o, {
//				'positionName' : Ext.get('positionName').dom.value
//			});
//			Ext.apply(o, {
//				'orderName' : Ext.get('orderName').dom.value
//			});
//			Ext.apply(o, {
//				'startTimeDate' : Ext.get('startTimeDate').dom.value
//			});
//			Ext.apply(o, {
//				'endTimeDate' : Ext.get('endTimeDate').dom.value
//			});
//			Ext.apply(o, {
//				'customerName' : Ext.get('customerName').dom.value
//			});
//			Ext.apply(o, {
//				'status' : Ext.get('status').dom.value
//			});
//			if (this.fireEvent('beforechange', this, o) !== false) {
//				this.store.load({
//					params : o
//				});
//			}
//		}
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
		loadMask : true,
		viewConfig : {
			forceFit : true
		},
		tbar : ['<b>&nbsp;&nbsp;&nbsp;&nbsp;<font color=#990000>角色信息列表</font></b>','->',{
			id : 'btnQuery',
			text : '查询',
			pressed : true,
			tooltip : '查询角色信息',
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
			tooltip : '添加新角色',
			iconCls : 'add16',
			onClick : function() {
//				location.href = "role_add.do";
				window.parent.createNewPanel('roleAdd','增加角色','role_add.do');
			}
		}, {
			id : 'btnEdit',
			text : '修改',
			pressed : true,
			tooltip : '修改角色',
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
			tooltip : '删除角色',
			iconCls : 'delete16',
			onClick : function() {
				if (sm.hasSelection()) {
					del();
				} else {
					Ext.MessageBox.alert('提示', "请至少选择一条记录!");
				}
			}
		}
		],
		bbar : ptb,
		width : 800,
		autoHeight : true,
		//title : '角色列表',
		//iconCls : 'icon-grid',
		renderTo : 'searchArea'
	});

	function edit() {
		Ext.MessageBox.confirm('提示', '修改角色信息?', function(button) {
			if (button == 'yes') {
				var list = sm.getSelections();
//				location.href = "role_modify.do?roleId="+list[0].data["id"];
				window.parent.createNewPanel('modifyRole_'+list[0].data["id"],'修改角色','role_modify.do?roleId='+list[0].data["id"]);
			}
		});
		
	}
//	function resetPassword(){
//		var list = sm.getSelections();
//		var id = list[0].data["id"];
//		var userName = list[0].data["userName"];
//		window.open('privilege/resetPassword.jsp?userId='+id+'&userName='+userName,'resetUserPassword','height=400,width=800,status=no,titlebar=no,toolbar=no,menubar=no,location=no,top=200,left=200');
//	}
	function del() {
		Ext.MessageBox.confirm('提示', '确定要删除这些角色吗?', function(button) {
			if (button == 'yes') {
				var list = sm.getSelections();
				var ids = [];
				for (var i = 0; i < list.length; i++) {
					ids[i] = list[i].data["id"];
				}
				roleService.deleteRoles(ids, {
					callback: function(data) {
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
					},
					errorHandler: function(errorStr,exception){
						Ext.Msg.confirm("警告",errorStr,function(btn){
							if(btn=='yes'){
								window.top.location.href="login.jsp";
							}
						});
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
//	alert(Ext.get('name').dom.value);
	ds.load({
		params : {
			start : start,
			limit : ptb.getPageSize(),
			'totalProperty' : ds.totalProperty,
			'name' : Ext.get('name').dom.value,
			'remark' : Ext.get('remark').dom.value,
			'status' : Ext.get('status').dom.value
		}
	});
}
function reset() {
	Ext.get("form1").reset();
}