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
			url : 'user_search.do'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'totalProperty',
			root : 'root'
		}, [{
			name : 'userId'
		}, {
			name : 'loginId'
		}, {
			name : 'userName'
		}, {
			name : 'status'
		}, {
			name : 'description'
		}])
	});
	ds.load({
		params : {
			start : 0,
			limit : limit,
			status : ""
		}
	});
	initGrid();
}

// 初始化显示表格
function initGrid() {
	Ext.QuickTips.init();
	var sm = new Ext.grid.CheckboxSelectionModel();

	var cm = new Ext.grid.ColumnModel([
       sm, {
    	   dataIndex : 'userId',
    	   hidden : true
       }, {
    	   header : "操作员名称",
    	   width: Ext.get("searchArea").getWidth()*0.25,
    	   sortable : true,
    	   dataIndex : 'loginId',
    	   renderer : function(value, p , record){
//    	   		return String.format('<a href="user_detail.do?userId={0}" target="_blank">{1}</a>',record.data.userId,value);
    	   		var action = "window.parent.createNewPanel('userDetail_{0}','user','用户详情','user_detail.do?userId={1}');"
    	   		return String.format('<a href="#" onclick='+action+'>{2}</a>',record.data.userId,record.data.userId,value);
    	   }
       }, {
    	   header : "操作员姓名",
    	   width: Ext.get("searchArea").getWidth()*0.25,
    	   sortable : true,
    	   dataIndex : 'userName'
       }, {
    	   header : "操作员状态",
    	   width: Ext.get("searchArea").getWidth()*0.25,
    	   sortable : true,
    	   dataIndex : 'status',
    	   renderer : function(value, p, record){
    	   		if(record.data.status == '0'){
    	   			return "<span style='color:blue;'>" + "启用" + "</span>";
    	   		}else if(record.data.status == '1'){
    	   			return "<span style='color:green;'>" + "禁用" + "</span>";
    	   		}else{
    	   			return "<span style='color:red;'>" + "无效" + "</span>";
    	   		}
		   }
       }, {
    	   header : "备注",
    	   width: Ext.get("searchArea").getWidth()*0.25,
    	   sortable : false,
    	   dataIndex : 'description'
       }
    ]);
	
	// 工具栏对象
	ptb = new Ext.PagingToolbar({
		plugins : new Ext.ux.Andrie.pPageSize(),
		pageSize : limit,
		store : ds,
		displayInfo : true,
		displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
		emptyMsg : "对不起，查询记录为空!",
		doLoad : loadStore,
		items : ['-', { 
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
		tbar : ['<b>&nbsp;&nbsp;&nbsp;&nbsp;<font color=#990000>操作员列表</font></b>','->',{
			id : 'btnAdd',
			text : '添加',
			pressed : true,
			tooltip : '添加新操作员',
			iconCls : 'add16',
			onClick : function() {
				window.parent.createNewPanel('userAdd','user','增加用户','user_add.do');
//				location.href = "user_add.do";
			}
		}, {
			id : 'btnEdit',
			text : '修改',
			pressed : true,
			tooltip : '修改操作员',
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
			tooltip : '删除操作员',
			iconCls : 'delete16',
			onClick : function() {
				if (sm.hasSelection()) {
					del();
				} else {
					Ext.MessageBox.alert('提示', "请至少选择一条记录!");
				}
			}
		}/*,{
			id : 'btnDetail',
			text : '详情',
			pressed : true,
			tooltip : '查看操作员详情信息',
			iconCls : 'detail16',
			onClick : function() {
				if (sm.getCount() == 1) {
					var id = sm.getSelected().data['id'];
					location.href = 'user_detail.do?userId=' + id;
				} else {
					Ext.MessageBox.alert('提示', "请选择一条记录!");
				}
			}
		}*/,{
			id : 'btnReset',
			text : '重置密码',
			pressed : true,
			tooltip : '重置密码',
			iconCls : 'resetPassword16',
			onClick : function(){
				if (sm.getCount()==1){
					resetPassword();
				}else{
					Ext.MessageBox.alert('提示',"请选择一条记录!");
				}
			}
		},{
			id : 'btnResume',
			text : '启用',
			pressed : true,
			iconCls : 'resume16',
			tooltip : '用户启用',
			onClick : function(){
				if (sm.getCount()==1){
					resume();
				}else{
					Ext.MessageBox.alert('提示',"请选择一条记录!");
				}
			}
		},{
			id : 'btnStop',
			text : '禁用',
			pressed : true,
			iconCls : 'stop16',
			tooltip : '用户禁用',
			onClick : function(){
				if (sm.getCount() == 1){
					stop();
				}else{
					Ext.MessageBox.alert('提示',"请选择一条记录!");
				}
			}
		}

		],
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
		var id = list[0].data["userId"];
		//location.href = "user_modify.do?userId="+id;
		window.parent.createNewPanel('userModify_'+id,'user','修改用户',"user_modify.do?userId="+id);
	}
	
	function resetPassword(){
		Ext.MessageBox.confirm('提示', '确定重设操作员密码？', function(btn){
			if(btn == 'yes'){
				var list = sm.getSelections();
				location.href = "user_beforResetPassword.do?userId="+list[0].data["userId"];
			}
		});
	}
	
	function resume(){
		var list = sm.getSelections();
		var id = list[0].data["userId"];
		userService.updateStatus(id,0,function(){
			Ext.MessageBox.alert('提示','修改用户状态成功！');
			loadStore(ptb.cursor);
		});
	}
	
	function stop(){
		var list = sm.getSelections();
		var id = list[0].data["userId"];
		userService.updateStatus(id,1,function(){
			Ext.MessageBox.alert('提示','修改用户状态成功！');
			loadStore(ptb.cursor);
		});
	}
	
	function del() {
		Ext.MessageBox.confirm('提示', '确定要删除用户吗?', function(button) {
			if (button == 'yes') {
				var list = sm.getSelections();
				var ids = [];
				var status = [];//
				var totalStatus = 1;
				for (var i = 0; i < list.length; i++) {
					ids[i] = list[i].data["userId"];
					status[i] = list[i].data["status"];
					totalStatus = totalStatus*status[i];
				}
				if(totalStatus==0){
					Ext.MessageBox.alert("提示","启用的操作员不能删除!");
						return;
				}
				userService.deleteUsers(ids, function(data) {
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
	// alert(Ext.get('status').dom.value);
	ds.load({
		params : {
			start : start,
			limit : ptb.getPageSize(),
			'username' : Ext.get('userName').dom.value,
			'name' :     Ext.get('name').dom.value,
			'status' :   Ext.get('status').dom.value,
			'totalProperty' : ds.getTotalCount()
		}
	});
}

function reset() {
	Ext.get("form1").reset();
}