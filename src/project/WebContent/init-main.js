var viewport;
var ds;// 数据源
var serviceds; // 会议模板数据源
var mychart; //图表
var day;
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
	
	var menu = new Ext.menu.Menu({
		id:'basicMenu',
		items:[{
			text:'系统管理'
		},{
			text:'测试菜单2'
		}]
	});
	var northToolbar = new Ext.Toolbar({
		id:'tb',
		height:30,
		items:[{
			text:"欢迎"+loginId+"进入全国天气预报电视会商系统"
		},{
			xtype:'tbfill'
		},{
			text:"导航菜单",
			menu:menu
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
				title:'系统管理',
				html:Ext.getDom('systemMenus').innerHTML,
				autoScroll:true,
				border:false
			},{
				title:'系统资源',
				html:Ext.getDom('resourceMenus').innerHTML,
				autoScroll:true,
				border:false
			},{
				title:'会议管理',
				html:Ext.getDom('confMenus').innerHTML,
				autoScroll:true,
				border:false
			},{
				title:'基本信息',
				html:Ext.getDom('personalMenus').innerHTML,
				autoScroll:true,
				border:false
			},{
				title:'统计分析',
				html:Ext.getDom('statisticsMenus').innerHTML,
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
});
function onClickMenuItem(node){
	//var n = contentPanel.getComponent(node.id);
	//if(!n){
		n = contentPanel.add({
			//'id':node.id,
			'title':node.innerHTML,
			closable:true,
			autoLoad:{
				url:'tabFrame.jsp?url='+node.attributes.href.value,
				callback:this.initSearch,
				scope:this,
				scripts:true
			}
		});
	//}
	contentPanel.setActiveTab(n);
}
function query() {
	loadStore();
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