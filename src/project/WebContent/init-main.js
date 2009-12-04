var viewport;
var ds;// 数据源
var serviceds; // 会议模板数据源
var mychart; //图表
var day;
var ds1;// 数据源
var grid1;// 数据显示表格
var limit = 5;// 每页显示的记录数
var ptb1;// 分页控件
var contentPanel=new Ext.TabPanel({
	region:'center',
	id:'tabPanel',
	enableTabScroll:true,
	resizeTabs:true,
	deferredRender:false,
	minTabWidth:115,
	activeTab:0,
	items:[{
		contentEl:"center",
		title:'主页',
		autoScroll:true
	}]
});
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	Ext.FlashComponent.EXPRESS_INSTALL_URL = "resources/images/default/expressInstall.swf";
	Ext.chart.Chart.CHART_URL = "resources/images/default/charts.swf";
	var northToolbar = new Ext.Toolbar({
		id:'tb',
		height:30,
		items:[{
			text:"欢迎"+loginId+"进入全国天气预报电视会商系统"
		},{
			xtype:'tbfill'
		},{
			xtype:'tbseparator'
		},{
			text:"帮助",
			handler:function(){
				Ext.Msg.alert('帮助','help!');
			}
		},{
			xtype:'tbseparator'
		},{
			text:"安全退出",
			handler:function(){
				Ext.MessageBox.confirm('提示','您确定要退出登录么？',function(button){
						if(button=="yes"){
							window.location="user_logout.do";
						}
					}
				)
			}
		}]
	});
	viewport = new Ext.Viewport({
		layout:'border',
		items:[{
			region:'north',
			id:'north',
			html:Ext.getDom("north").innerHTML,
			split:true,
			height:106,
			//autoHeight:true,
			margin:'0 0 0 0',
			bbar:northToolbar
		},{
			region:'west',
			id:'west',
			title:'菜单栏',
			split:true,
			width:200,
			minSize:175,
			maxSize:400,
			collapsible:true,
			margins:'0 0 5 5',
			cmargins:'0 5 5 5',
			layout:'accordion',
			layoutConfig:{
				animate:true
			},
			items:[{
				title:'系统资源浏览',
				html:Ext.getDom('resourceMenus').innerHTML,
				autoScroll:true,
				border:false
			},{
				title:'个人会议预约',
				html:Ext.getDom('scheduleConfMenus').innerHTML,
				autoScroll:true,
				border:false
			},{
				title:'预约审核及变更',
				html:Ext.getDom('moidfyConfMenus').innerHTML,
				autoScroll:true,
				border:false
			},{
				title:'个人会议管理',
				html:Ext.getDom('confMgmtMenus').innerHTML,
				autoScroll:true,
				border:false
			},{
				title:'用户个人信息管理',
				html:Ext.getDom('personalMenus').innerHTML,
				autoScroll:true,
				border:false
			},{
				title:'系统参数管理',
				html:Ext.getDom('systemConfigMenus').innerHTML,
				autoScroll:true,
				border:false
			},{
				title:'公告栏',
				html:Ext.getDom('bulletinMenus').innerHTML,
				autoScroll:true,
				border:false
			},{
				title:'留言板',
				html:Ext.getDom('bbsMenus').innerHTML,
				autoScroll:true,
				border:false
			},{
				title:'帮助',
				html:Ext.getDom('helpMenus').innerHTML,
				autoScroll:true,
				border:false
			}]
		},contentPanel]
	});
	day = new Ext.form.DateField({
		name:'day',
		id:'day',
		readOnly:true,
		format:'Y\-m\-d',
		renderTo:'dayDiv'
	});
	ds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'res_searchAvailable.do'
		}),
		reader : new Ext.data.JsonReader({
			totalProperty : 'totalProperty',
			root : 'root'
		}, [{
			name : 'hourMinutes'
		}, {
			name : 'occupyNum'
		}, {
			name : 'availableNum'
		}])
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
    var iTop = (window.screen.availHeight-30-400)/2;
    var iLeft = (window.screen.availWidth-10-800)/2;
    window.open('bulletin_list.do','最新公告','height=400,width=800,status=no,menubar=no,location=no,toolbar=no,top='+iTop+',left='+iLeft);
    initData();
});
// 初始化数据
function initData() {
	ds1 = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'conf_searchRunnings.do'
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
		}, {
			name : 'serviceTemplateDesc'
		}, {
			name : 'dialableNumber'
		}])
	});
	ds1.load({
		params : {
			start : 0,
			limit : limit,
			'listType' : 'running'
		}
	});
	initGrid();
}
// 初始化显示表格
function initGrid() {
	Ext.QuickTips.init();
	var sm1 = new Ext.grid.CheckboxSelectionModel();

	var cm1 = new Ext.grid.ColumnModel([sm1, {
		dataIndex : 'conferenceId',
		hidden : true
	},{
		dataIndex : 'radConferenceId',
		hidden : true
	},{
		header : "主题",
		width: Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'subject',
		renderer : function(value, p , record){
			//return String.format('<a href="conf_reserveDetail.do?personal=false&conferenceId={0}" target="_blank">{1}</a>',record.data.conferenceId,value);
			var action = "window.parent.createNewPanel('confReserveDetail_{0}','currentConf','会议详情','conf_reserveDetail.do?personal=false%26conferenceId={1}');";
			return String.format('<a href="#" onclick='+action+'>{2}</a>',record.data.conferenceId,record.data.conferenceId,value);
		}
	}, {
		header : "会议号",
		width: Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'dialableNumber'
	}, {
		header : "组织单位",
		width : Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'initUnit'
	}, {
		header : "起始时间",
		width : Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'startTime',
		renderer : function(value, p, record){
			return new Date(value).format('Y-m-d H:i:s');
		}
	}, {
		header : "时长(分钟)",
		width : Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'timeLong'
	}, {
		header : "状态",
		width: Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'statusStr'
	}, {
		header : "会议类型",
		width : Ext.get("searchArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'serviceTemplateDesc'
	}]);
	// 工具栏对象
	ptb1 = new Ext.PagingToolbar({
		plugins : new Ext.ux.Andrie.pPageSize(),
		pageSize : limit,
		store : ds1,
		displayInfo : true,
		displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
		emptyMsg : "对不起，查询记录为空!",
		doLoad : loadStore1
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
		ds : ds1,
		cm : cm1,
		sm : sm1,
		stripeRows : true,
		forceFit:true,
		loadMask : true,
		//viewConfig : {forceFit : true},
		tbar : ['<b>&nbsp;&nbsp;&nbsp;&nbsp;<font color=#990000>正在召开的会议</font></b>','->'],
		bbar : ptb1,
		width: Ext.get("searchArea").getWidth()*0.98,
		height:Ext.get("searchArea").getHeight()*0.98,
		autoHeight : true,
		//cls:vline-on,
		//title : '用户列表',
		//iconCls : 'icon-grid',
		renderTo : 'searchArea'
	});

	function makeParams(){
		var params = "start="+ptb.cursor;
		params += "&limit="+ptb.getPageSize();
		params += "&totalProperty="+ds.getTotalCount();
		params += "&lisType=running";
		return params;
	}
}
function onClickMenuItem(node){
	var n = contentPanel.getComponent(node.id);
	if(!n){
		n = contentPanel.add({
			'id':node.id,
			'title':node.innerHTML,
			closable:true,
			autoLoad:{
				url:'tabFrame.jsp?url='+node.attributes.href.value,
				//url: node.attributes.href.value,
				//callback:this.initSearch,
				scope:this,
				scripts:true
			}
		});
	}
	contentPanel.setActiveTab(n);
}
function createNewPanel(id,initId,title,href){
	var n = contentPanel.getComponent(id);
	if(!n){
		n = contentPanel.add({
			'id' : id,
			'title' : title,
			'initId' : initId,
			closable:true,
			autoLoad:{
				url:'tabFrame.jsp?url='+href,
				scope:this,
				scripts:true
			}
		});
	}
	contentPanel.setActiveTab(n);
}
function closePanel(id){
	var n = contentPanel.getComponent(id);
	if(n){
		var initId = n.initId;
		contentPanel.remove(n);
		n.destroy();
		if(initId){
			initN = contentPanel.getComponent(initId);
			contentPanel.setActiveTab(initN);
			//initN.load(initN.autoLoad);
		}
	}
}
function closeActivePanel(){
	var n = contentPanel.getActiveTab();
	contentPanel.remove();
	n.destroy();
}
function closeAndRefreshPanel(id){
	var n = contentPanel.getComponent(id);
	if(n){
		var initId = n.initId;
		contentPanel.remove(n);
		n.destroy();
		if(initId){
			initN = contentPanel.getComponent(initId);
			contentPanel.setActiveTab(initN);
			initN.load(initN.autoLoad);
		}
	}
}
function closeAndCreatePanel(oldId,newId){
	var n = contentPanel.getComponent(oldId);
	if(n){
		contentPanel.remove(n);
		n.destroy();
		var nn = contentPanel.getComponent(newId);
		if(nn){
			contentPanel.setActiveTab(nn);
			nn.load(nn.autoLoad);
		}else{
			onClickMenuItem(Ext.getDom(newId));
		}
	}
}
function query() {
	loadStore();
}
function loadStore1(start){
//	alert(Ext.get('status').dom.value);
	ds.load({
		params : {
			start : start,
			limit : ptb1.getPageSize(),
			'totalProperty' : ds1.getTotalCount(),
			'listType' : 'running'
		}
	});
}
function loadStore(){
//	alert(Ext.get('status').dom.value);
	if (validateRequired('serviceTemplate','会议类型')
			&& validateRequired('day','日期')
			) {
		
	}else{
		return;
	}
	if(mychart==null || mychart==undefined){
		mychart = new Ext.Panel({
        title: '可用资源情况',
        frame:true,
        renderTo: 'searchArea',
        width: Ext.get("searchArea").getWidth()*0.98,
		//autoWidth : true,
		//height:Ext.get("searchArea").getHeight()*0.99,
		//autoHeight : true,
		height: 400,
		layout:'fit',
		tbar: [{
			xtype:'tbfill'
		},{
			text: '上一天',
			handler: function(){
				if(day.getValue()!=null && day.getValue()!=undefined && day.getValue()!="")
					day.setValue(day.getValue().add(Date.DAY,-1));
				loadStore();
			}
		},{
			xtype: 'tbseparator'
		},{
			text: '下一天',
			handler: function(){
				if(day.getValue()!=null && day.getValue()!=undefined && day.getValue()!="")
					day.setValue(day.getValue().add(Date.DAY,1));
				loadStore();
			}
		},{
			xtype: 'tbseparator'
		},{
            text: '重新载入',
            handler: function(){
                loadStore();
            }
        }],
        items: {
            xtype: 'columnchart',
            store: ds,
            xField: 'hourMinutes',
            yAxis: new Ext.chart.NumericAxis({
                title: '数量'
            }),
			xAxis: new Ext.chart.CategoryAxis({
				title: "时间"
			}),
            tipRenderer : function(chart, record, index, series){
                if(series.yField == 'availableNum'){
                    return record.data.availableNum + ' 点数空闲，在 ' + record.data.hourMinutes;
                }else{
                    return record.data.occupyNum + ' 点数占用在 ' + record.data.hourMinutes;
                }
            },
            chartStyle: {
                padding: 10,
                animationEnabled: true,
                font: {
                    name: 'Tahoma',
                    color: 0x444444,
                    size: 11
                },
                dataTip: {
                    padding: 5,
                    border: {
                        color: 0x99bbe8,
                        size:1
                    },
                    background: {
                        color: 0xDAE7F6,
                        alpha: .9
                    },
                    font: {
                        name: 'Tahoma',
                        color: 0x15428B,
                        size: 10,
                        bold: true
                    }
                }
            },
            series: [{
                type: 'column',
                displayName: '占用',
                yField: 'occupyNum',
                style: {
                	labelPosition: 'outside',
                    //image:'bar.gif',
                    mode: 'stretch',
                    //color:0x99BBE8
					color:0xFF0000
                }
            },{
                type:'column',
                displayName: '空闲',
                yField: 'availableNum',
                style: {
                	labelPosition: 'outside',
					//image:'bar.gif',
                    mode: 'stretch',
                    //color:0x99BBE8
                    //color: 0x15428B
					color:0x0000FF
                }
            }]
        }
    });
	}		
	ds.load({
		params : {
			'serviceTemplate' : Ext.get("serviceTemplate").dom.value,
			'day' : Ext.get("day").dom.value,
			'interval' : Ext.get("interval").dom.value
		}
	});
}
function reset() {
	Ext.get("form1").reset();
}