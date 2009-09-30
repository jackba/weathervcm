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
			name : 'privilegeId'
		}, {
			name : 'name'
		}, {
			name : 'url'
		}, {
		    name : 'description'
		}
		])
	});
	ds.load();
	/*
	 * Ext.ux.ItemSelector Example Code
	 */
	
			formItemSelector = new Ext.ux.ItemSelector({
				//labelWidth: 75,
				width:650,
				renderTo:'roleDiv',
				name:"privileges",
				fieldLabel:"ItemSelector",
				hideLabel:true,
				dataFields:["privilegeId", "name"],
				fromStore:ds,
				toData:[],
				msWidth:250,
				msHeight:200,
				valueField:"privilegeId",
				displayField:"name",
				imagePath:"resources/js/ItemSelector",
				//switchToFrom:true,
				toLegend:"已选权限",
				fromLegend:"可选权限"
			});
	
});
function submitForm1(){
	Ext.Ajax.request({
		form:'form1',
		success:function(result,request){
			var o = Ext.util.JSON.decode(result.responseText);
			Ext.Msg.alert('Success',o.msg);
			reload();
		},
		failure:function(result,request){
			Ext.Msg.alert('Warning',result.responseText);
		}
	});
	return false;
}
