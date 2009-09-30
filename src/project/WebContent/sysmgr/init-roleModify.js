var formItemSelector;
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var ds = new Ext.data.Store({
		proxy : new Ext.data.HttpProxy({
			url : 'role_getAllPrivilege.do'
		}),
		reader : new Ext.data.JsonReader({
			root : 'root'
		}, [{
			name : 'id'
		}, {
			name : 'name'
		}, {
			name : 'remark'
		}, {
		    name : 'status'
		}
		])
	});
	ds.load();
	/*
	 * Ext.ux.ItemSelector Example Code
	 */
	
			formItemSelector = new Ext.ux.ItemSelector({
				//labelWidth: 75,
				width:700,
				renderTo:'roleDiv',
				name:"privileges",
				fieldLabel:"ItemSelector",
				hideLabel:true,
				dataFields:["id", "name"],
				fromStore:ds,
				toData:[],
				msWidth:250,
				msHeight:200,
				valueField:"id",
				displayField:"name",
				imagePath:"resources/js/ItemSelector",
				//switchToFrom:true,
				toLegend:"已选权限",
				fromLegend:"可选权限",
				toTBar:[{
					text:"清空",
					handler:function(){
						formItemSelector.reset.call(formItemSelector);
					}
				}]
			});
	
});
