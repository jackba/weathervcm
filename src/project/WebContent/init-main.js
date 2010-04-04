var viewport;
var serviceds; // 会议模板数据源
var mychart; //图表
var day;
var ds1;// 数据源
var grid1;// 数据显示表格
var limit = 5;// 每页显示的记录数
var ptb1;// 分页控件
var weekPanel;
var dayGrid;
var win;
var win2;
var contentPanel;
function getConnectStatus(){
	Ext.Ajax.request({
		url: 'conf_getConnectStatus.do',
		success: function(result,request){
			Ext.getCmp('connectStatus').setText(result.responseText);
		},
		failure: function(result,request){
			Ext.getCmp('connectStatus').setText("连接iVIEW错误");
		}
	});
}
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	Ext.FlashComponent.EXPRESS_INSTALL_URL = "resources/images/default/expressInstall.swf";
	Ext.chart.Chart.CHART_URL = "resources/images/default/charts.swf";
	var tabs = new Ext.TabPanel({
		id:"tabs",
		renderTo:"center",
	    //width:Ext.get("searchArea").getWidth(),
	    activeTab: 0,
	    frame:true,
	    defaults:{autoHeight: true},
	    items:[
	        {contentEl:'searchArea', title: 'Resource'},
	        {contentEl:'runningArea', title: 'Running'},
	        {contentEl:'agendaArea', title: 'Agenda'}
	    ]
	});
	contentPanel = new Ext.TabPanel({
		region:'center',
		id:'tabPanel',
		enableTabScroll:true,
		resizeTabs:true,
		deferredRender:false,
		minTabWidth:115,
		activeTab:0,
		items:[{
			contentEl:"tabs",
			title:'主页',
			autoScroll:true
		}]
	});
	var northToolbar = new Ext.Toolbar({
		id:'tb',
		height:30,
		items:[{
			text:"欢迎"+loginId+"进入全国天气预报电视会商系统"
		},{
			xtype:'tbseparator'
		},{
			id: 'connectStatus',
					xtype: 'tbtext',
					text: '...',
					listeners: {
						'afterrender': {
							fn: function(){
								Ext.fly(Ext.getCmp('connectStatus').getEl()).parent().addClass('custom-status-text-panel');
								getConnectStatus();
								setInterval("getConnectStatus()",30*1000);
							}
						}
					}	
		},{
			xtype:'tbfill'
		},{
			xtype:'tbseparator'
		},{
			text:"刷新",
			handler:function(){
				refreshActiveTab();
			}
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
				border:false,
				hidden:isHidden('resourceMenus')
			},{
				title:'个人会议预约',
				html:Ext.getDom('scheduleConfMenus').innerHTML,
				autoScroll:true,
				border:false,
				hidden:isHidden('scheduleConfMenus')
			},{
				title:'预约审核及变更',
				html:Ext.getDom('moidfyConfMenus').innerHTML,
				autoScroll:true,
				border:false,
				hidden:isHidden('moidfyConfMenus')
			},{
				title:'个人会议管理',
				html:Ext.getDom('confMgmtMenus').innerHTML,
				autoScroll:true,
				border:false,
				hidden:isHidden('confMgmtMenus')
			},{
				title:'用户个人信息管理',
				html:Ext.getDom('personalMenus').innerHTML,
				autoScroll:true,
				border:false,
				hidden:isHidden('personalMenus')
			},{
				title:'系统参数管理',
				html:Ext.getDom('systemConfigMenus').innerHTML,
				autoScroll:true,
				border:false,
				hidden:isHidden('systemConfigMenus')
			},{
				title:'公告栏',
				html:Ext.getDom('bulletinMenus').innerHTML,
				autoScroll:true,
				border:false,
				hidden:isHidden('bulletinMenus')
			},{
				title:'留言板',
				html:Ext.getDom('bbsMenus').innerHTML,
				autoScroll:true,
				border:false,
				hidden:isHidden('bbsMenus')
			},{
				title:'帮助',
				html:Ext.getDom('helpMenus').innerHTML,
				autoScroll:true,
				border:false,
				hidden:isHidden('helpMenus')
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
	day.setValue(new Date());
//	serviceds = new Ext.data.Store({
//		proxy : new Ext.data.HttpProxy({
//			url : 'service_search.do'
//		}),
//		reader : new Ext.data.JsonReader({
//			root : 'root'
//		}, [{
//			name : 'serviceTemplateId'
//		}, {
//			name : 'serviceTemplateName'
//		}, {
//			name : 'serviceTemplateDesc'
//		}])
//	});
//	serviceds.load();
//	var serviceComboWithTooltip = new Ext.form.ComboBox({
//		store: serviceds,
//		hiddenId: 'serviceTemplate',
//        hiddenName: 'serviceTemplate',
//        valueField: 'serviceTemplateId',
//        displayField: 'serviceTemplateDesc',
//        typeAhead: true,
//        forceSelection: false,
//        mode: 'local',
//        triggerAction: 'all',
//        emptyText: '请选择会议模板...',
//        selectOnFocus: true,
//        renderTo: 'service_template'
//    });
	loadStore();
    var iTop = (window.screen.availHeight-30-400)/2;
    var iLeft = (window.screen.availWidth-10-800)/2;
    window.open('bulletin_list.do','最新公告','height=400,width=800,status=no,menubar=no,location=no,resizable=yes,scrollbars=yes,toolbar=no,top='+iTop+',left='+iLeft);
    initData();
   
    var cday = new Date();
    Ext.getDom('day2').value = cday.format('Y-m-d');
    createWeekPanel();
	setInterval("refresh()", 30*1000)
});
function isHidden(divId){
	var childMenus = Ext.DomQuery.select("ul/li",Ext.getDom(divId));
	if(childMenus==null || childMenus==undefined || childMenus.length==0)
		return true;
	else
		return false;
}
function refresh(){
	loadStore();
	loadStore1(ptb1.cursor);
	weekPanel.getActiveTab().getBottomToolbar().doLoad(weekPanel.getActiveTab().getBottomToolbar().cursor);
}
function createDayGrid(){
	dayGrid = getDayConf(Ext.getDom('day2').value);
	
	dayGrid.render('agendaArea');
}
function destroyDayGrid(){
	dayGrid.destroy();
}
function createWeekPanel(){
	var cday = Date.parseDate(Ext.getDom('day2').value,'Y-m-d');
	var cdayofweek = cday.getDay();
    if(cdayofweek==0){
    	cdayofweek = 7;
    }
    var sday = cday.add(Date.DAY,1-cdayofweek);
//	var title = "星期一"
//	if(cdayofweek == 7){
//		title = "星期日"
//	}else if(cdayofweek == 1){
//		title = "星期一";
//	}else if(cdayofweek == 2){
//		title = "星期二";
//	}else if(cdayofweek == 3){
//		title = "星期三";
//	}else if(cdayofweek == 4){
//		title = "星期四";
//	}else if(cdayofweek == 5){
//		title = "星期五";
//	}else if(cdayofweek == 6){
//		title = "星期六";
//	}
    weekPanel = new Ext.TabPanel({
		renderTo:'agendaArea',
		id:'weekPanel',
		enableTabScroll:true,
		resizeTabs:true,
		deferredRender:false,
		minTabWidth:115,
		activeTab:cdayofweek-1,
		items:[Ext.applyIf(getDayConf(sday.format('Y-m-d')),{title:'星期一'}),
			Ext.applyIf(getDayConf(sday.add(Date.DAY,1).format('Y-m-d')),{title:'星期二'}),
			Ext.applyIf(getDayConf(sday.add(Date.DAY,2).format('Y-m-d')),{title:'星期三'}),
			Ext.applyIf(getDayConf(sday.add(Date.DAY,3).format('Y-m-d')),{title:'星期四'}),
			Ext.applyIf(getDayConf(sday.add(Date.DAY,4).format('Y-m-d')),{title:'星期五'}),
			Ext.applyIf(getDayConf(sday.add(Date.DAY,5).format('Y-m-d')),{title:'星期六'}),
			Ext.applyIf(getDayConf(sday.add(Date.DAY,6).format('Y-m-d')),{title:'星期日'})
		]
	});
}
function destroyWeekPanel(){
	weekPanel.destroy();
}
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
			name : 'virtualConfId'
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
		width: Ext.get("runningArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'subject',
		renderer : function(value, p , record){
			//return String.format('<a href="conf_reserveDetail.do?personal=false&conferenceId={0}" target="_blank">{1}</a>',record.data.conferenceId,value);
			var action = "window.parent.createNewPanel('confReserveDetail_{0}','currentConf','会议详情','conf_reserveDetail.do?personal=false%26conferenceId={1}');";
			return String.format('<a href="#" onclick='+action+'>{2}</a>',record.data.conferenceId,record.data.conferenceId,value);
		}
	}, {
		header : "会议号",
		width: Ext.get("runningArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'virtualConfId'
	}, {
		header : "组织单位",
		width : Ext.get("runningArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'initUnit'
	}, {
		header : "起始时间",
		width : Ext.get("runningArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'startTime',
		renderer : function(value, p, record){
			return new Date(value).format('Y-m-d H:i:s');
		}
	}, {
		header : "时长(分钟)",
		width : Ext.get("runningArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'timeLong'
	}, {
		header : "状态",
		width: Ext.get("runningArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'statusStr'
	}, {
		header : "会议类型",
		width : Ext.get("runningArea").getWidth()*0.14,
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
		width: Ext.get("runningArea").getWidth()*0.98,
		height:Ext.get("runningArea").getHeight()*0.98,
		autoHeight : true,
		//cls:vline-on,
		//title : '用户列表',
		//iconCls : 'icon-grid',
		renderTo : 'runningArea'
	});
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
function refreshActiveTab() {
	var n = contentPanel.getActiveTab();
	n.load(n.autoLoad);
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
	win.hide();
}
function query2(){
	var type = Ext.getDom('displayType').options[Ext.getDom('displayType').selectedIndex].value;
	if(type=='2'){
		if(weekPanel!=null){
			destroyWeekPanel();
		}
		if(dayGrid!=null){
			dayGrid.destroy();
		}
		createDayGrid();
	}else{
		if(dayGrid!=null){
			destroyDayGrid();
		}
		if(weekPanel!=null){
			destroyWeekPanel();
		}
		createWeekPanel();
	}
	win2.hide();
}
function loadStore1(start){
//	alert(Ext.get('status').dom.value);
	ds1.load({
		params : {
			start : start,
			limit : ptb1.getPageSize(),
			'totalProperty' : ds1.getTotalCount(),
			'listType' : 'running'
		}
	});
}
var chartEvents = {
           //'mousemove':function(){console.log(['mousemove',arguments])}
          };
function loadStore(){
//	alert(Ext.get('status').dom.value);
	if (/*validateRequired('serviceTemplate','会议类型')
			&& */validateRequired('day','日期')
			) {
		
	}else{
		return;
	}
	if(mychart==null || mychart==undefined){
		mychart = new Ext.ux.Chart.Fusion.Panel({
       				title       : '可用资源情况',
       				//collapsible : true,
       				renderTo    : 'searchArea',
       				floating:false,
       				fusionCfg   :{ id   : 'chart1'
                     				,listeners: chartEvents  //DOM listeners for the chart Object itself
                  				},
       				autoScroll : true,
       				id       : 'chartpanel',
       				chartURL : 'FusionCharts/StackedColumn3D.swf',
       				dataURL  : 'res_searchAvailable1.do?serviceTemplate='+Ext.get("serviceTemplate").dom.value+'&day='+Ext.get("day").dom.value+'&interval='+Ext.get('interval').dom.value,
       				mediaMask  : {msg:'正在装载报表'},
       				autoMask  : true,
       				width     : Ext.get("searchArea").getWidth()*0.98,
       				height    : 500,
       				listeners :{
           				show  : function(p){
               				if(p.floating)p.setPosition(p.x||10,p.y||10);
               				}
           				//,chartload : function(p,obj){
               			//	p.setTitle(p.title.split(":")[0]+': Loaded.');
               			//	}
           				//,chartrender : function(p,obj){
               			//	p.setTitle(p.title.split(":")[0]+': Rendered.');
               			//	}
       				},
					tbar: [{
						xtype:'tbfill'
						},{
			text: '查询',
			handler: function(){
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
			xtype: 'tbseparator'
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
                			//mychart.refreshMedia();
            			}
        			}]
  				});
				mychart.show();
		
	}else{
		mychart.setChartDataURL('res_searchAvailable1.do?serviceTemplate='+Ext.get("serviceTemplate").dom.value+'&day='+Ext.get("day").dom.value+'&interval='+Ext.get('interval').dom.value,true);
		//mychart.refreshMedia();
	}
}
function reset() {
	Ext.get("form1").reset();
}
function reset2(){
	Ext.get("form2").reset();
}
function getDayConf(day){
	var ds2 = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'conf_searchCurrentDays.do'
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
			name : 'virtualConfId'
		}])
	});
	ds2.load({
		params : {
			start : 0,
			limit : limit,
			'listType' : 'currentDay',
			day : day
		}
	});
	Ext.QuickTips.init();
	var sm2 = new Ext.grid.CheckboxSelectionModel();

	var cm2 = new Ext.grid.ColumnModel([sm2, {
		dataIndex : 'conferenceId',
		hidden : true
	},{
		dataIndex : 'radConferenceId',
		hidden : true
	},{
		header : "主题",
		width: Ext.get("agendaArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'subject',
		renderer : function(value, p , record){
			//return String.format('<a href="conf_reserveDetail.do?personal=false&conferenceId={0}" target="_blank">{1}</a>',record.data.conferenceId,value);
			var action = "window.parent.createNewPanel('confReserveDetail_{0}','currentDayConf','会议详情','conf_reserveDetail.do?personal=false%26conferenceId={1}');";
			return String.format('<a href="#" onclick='+action+'>{2}</a>',record.data.conferenceId,record.data.conferenceId,value);
		}
	}, {
		header : "会议号",
		width: Ext.get("agendaArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'virtualConfId'
	}, {
		header : "组织单位",
		width : Ext.get("agendaArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'initUnit'
	}, {
		header : "起始时间",
		width : Ext.get("agendaArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'startTime',
		renderer : function(value, p, record){
			return new Date(value).format('Y-m-d H:i:s');
		}
	}, {
		header : "时长(分钟)",
		width : Ext.get("agendaArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'timeLong'
	}, {
		header : "状态",
		width: Ext.get("agendaArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'statusStr'
	}, {
		header : "会议类型",
		width : Ext.get("agendaArea").getWidth()*0.14,
		sortable : true,
		dataIndex : 'serviceTemplateDesc'
	}]);
	// 工具栏对象
	var ptb2 = new Ext.PagingToolbar({
		plugins : new Ext.ux.Andrie.pPageSize(),
		pageSize : limit,
		store : ds2,
		displayInfo : true,
		displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
		emptyMsg : "对不起，查询记录为空!",
		doLoad : loadStore2
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
	var grid2 = new Ext.grid.GridPanel({
		//id : 'button-grid',
		border : false,
		buttonAlign : 'right',
		ds : ds2,
		cm : cm2,
		sm : sm2,
		stripeRows : true,
		forceFit:true,
		loadMask : true,
		//viewConfig : {forceFit : true},
		tbar : ['<b>&nbsp;&nbsp;&nbsp;&nbsp;<font color=#990000>当日会议安排</font></b>','->',
				{
			xtype:'tbfill'
		},{
			text:'查询',
			handler:function(){
				if(!win2){
					win2 = new Ext.Window({
						title: '查询',
						closable:true,
						closeAction:'hide',
						width:800,
						height:150,
						items:[{
							xtype:"panel",
							title:"",
							contentEl:"agendaQueryArea"
							//html:Ext.getDom("queryArea").innerHTML
						}]
					});
				}
				win2.show(this);
				Ext.getDom("agendaQueryArea").style.display="block";
			}
		}],
		bbar : ptb2,
		width: Ext.get("agendaArea").getWidth()*0.98,
		height:Ext.get("agendaArea").getHeight()*0.98,
		autoHeight : true
		//cls:vline-on,
		//title : title
		//iconCls : 'icon-grid',
		//renderTo : 'agendaArea'
	});
	function loadStore2(start){
		ds2.load({
			params : {
				start : start,
				limit : ptb2.getPageSize(),
				'totalProperty' : ds2.getTotalCount(),
				'listType' : 'currentDay',
				'day' : day
			}
		});
	}
	return grid2;
}