<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>角色查询</title>
	<link href="<%=request.getContextPath() %>/resources/css/edgrid.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/pager.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/engine.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/util.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/dwr/interface/roleService.js"></script>
<style type="text/css">
.STYLE3 {
	font-size: 12px
}

#button-grid .x-panel-body {
	border: 1px solid #99bbe8;
	border-top: 0 none;
}
</style>
</head>
<body style="overflow:scroll;overflow-x:hidden">
<div class="wrap">
	<h1>当前位置：系统管理&nbsp;&gt;&nbsp;角色管理&nbsp;&gt;&nbsp;<span class="position_current">角色查询</span></h1>
	<div class="search" id="searchArea">
		<form name="form1" id="form1" method="post" action="<%=request.getContextPath()%>/role_search.do">
			<table class="query">
				<tr>
					<th>角色名称：</th>
					<td><input type="text" id="name" class="put200" /></td>
					<th>角色描述：</th>
					<td><input type="text" id="remark" class="put200" /></td>
					<input type="hidden" id="status" value="0" />
					<!-- <th>角色状态：</th>
					<td><select  id="status" class="put200">
						<option value="">不限</option>
						<option value="0">有效</option>
						<option value="1">无效</option>
	                </select></td> -->
	            </tr>
			</table>
			<div class="query_btn">
				  <input id="btnQuery"  type="button" class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'"  value="查询" onClick="query()"/>
				   &nbsp;&nbsp;
				  <input id="btnReset"  type="button" class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'"  value="重置" onClick="reset()"/>
			</div>
		</form>
	</div><!-- end searchArea div -->
</div><!-- end wrap div -->
<script type="text/javascript">
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
			url : 'role_search.do'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'totalProperty',
			root : 'root'
		}, [{
			name : 'roleId'
		}, {
			name : 'roleName'
		}, {
			name : 'description'
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
		dataIndex : 'roleId',
		hidden : true
	},{
		header : "角色名称",
		//width : 20,
		width: Ext.get("searchArea").getWidth()*0.33,
		sortable : true,
		dataIndex : 'roleName',
		renderer : function(value, p , record){
			//return String.format('<a href="role_detail.do?roleId={0}" target="_blank">{1}</a>',record.data.roleId,value);
			var action = "window.parent.createNewPanel('roleDetail_{0}','role','角色详情','role_detail.do?roleId={1}');";
			return String.format('<a href="#" onclick='+action+'>{2}</a>',record.data.roleId,record.data.roleId,value);
		}
	}, {
		header : "角色描述",
		//width : 40,
		width: Ext.get("searchArea").getWidth()*0.33,
		sortable : false,
		dataIndex : 'description'
	}, {
		header : "角色状态",
		width: Ext.get("searchArea").getWidth()*0.33,
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
		loadMask : true,
		//viewConfig : {forceFit : true},
		tbar : ['<b>&nbsp;&nbsp;&nbsp;&nbsp;<font color=#990000>角色信息列表</font></b>','->',{
			id : 'btnAdd',
			text : '添加',
			pressed : true,
			tooltip : '添加新角色',
			iconCls : 'add16',
			onClick : function() {
				//location.href = "role_add.do";
				window.parent.createNewPanel('roleAdd','role','增加角色','role_add.do');
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
		//width : 800,
		width: Ext.get("searchArea").getWidth()*0.99,
		autoHeight : true,
		//title : '角色列表',
		//iconCls : 'icon-grid',
		renderTo : 'searchArea'
	});

	function edit() {
		var list = sm.getSelections();
		//location.href = "role_modify.do?roleId="+list[0].data["roleId"];
		window.parent.createNewPanel('roleModify_'+list[0].data["roleId"],'role','修改角色','role_modify.do?roleId='+list[0].data["roleId"]);
	}

	function resume(){
		var list = sm.getSelections();
		var id = list[0].data["roleId"];
		roleService.updateStatus(id,0,function(){
			Ext.MessageBox.alert('提示','修改角色状态成功！');
		});
		loadStore(ptb.cursor);
	}
	function stop(){
		var list = sm.getSelections();
		var id = list[0].data["roleId"];
		roleService.updateStatus(id,2,function(){
			Ext.MessageBox.alert('提示','修改角色状态成功！');
		});
		loadStore(ptb.cursor);
	}
	function del() {
		Ext.MessageBox.confirm('提示', '确定要删除这些角色吗?', function(button) {
			if (button == 'yes') {
				var list = sm.getSelections();
				var ids = [];
				for (var i = 0; i < list.length; i++) {
					ids[i] = list[i].data["roleId"];
				}
				
				fullId = ids.join(",");

				//location.href = "privilege_delete.do?privilegeIdList="+fullId;
				var ajax_loading_callback = function(){
					Ext.MessageBox.show({
						title: '提示',
						progressText: '正在提交数据，请稍等……',
						width:300,
						progress:true,
						closable:false,
						animEl: 'body'
					});
				};
				
				//定义回调函数的弹出效果，存入变量ajax_loaded_callback中
				var ajax_loaded_callback = function(){
					Ext.MessageBox.hide();
				};
				
				//将上面两变量注册到ajax中
				Ext.Ajax.on('beforerequest', ajax_loading_callback, this);
				Ext.Ajax.on('requestcomplete', ajax_loaded_callback, this);
				
				//定义ajax的调用过程
				Ext.Ajax.request({
					url: 'role_delete.do?roleIdList='+fullId,
					success:function(result,request){
						var resp = Ext.util.JSON.decode(result.responseText);
						if(resp.success == true){	
						
							Ext.Msg.alert('成功',resp.msg, function(button){
								if(button == 'ok'){
									location.href= '<%=request.getContextPath() %>/role_list.do' ;
								}
							});
						} else {
							Ext.Msg.alert('失败',resp.msg);
						}
				    },
				    failure:function(result,request){
						Ext.Msg.alert('失败',result.responseText);
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
//	alert(Ext.get('name').dom.value);
	ds.load({
		params : {
			start : start,
			limit : ptb.getPageSize(),
			'name' : Ext.get('name').dom.value,
			'remark' : Ext.get('remark').dom.value,
			'totalProperty' : ds.getTotalCount()
		}
	});
}
function reset() {
	Ext.get("form1").reset();
}
</script>
</body>
</html>