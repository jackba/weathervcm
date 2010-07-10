Ext.onReady(function() {
	Ext.QuickTips.init();
	Ext.form.Field.prototype.msgTarget = 'side';
	var doSubmit = function() {
		if (loginForm.getForm().isValid()) {
			loginForm.form.doAction('submit', {
						url : 'user_login.do',
						method : 'post',
						params : '',
						success : function(form, action) {
							if (action.result.msg == 'ok') {
								window.location = 'user_main.do';
							} else {
								Ext.Msg.alert('登录失败', action.result.msg);
							}
						},
						failure : function() {
							Ext.Msg.alert('错误', '服务器出现错误请稍后再试！');
						}
					})
		}
	}
	var doAnonymousSubmit = function(){
		Ext.getDom('username').value = 'guest';
		Ext.getDom('password').value = '111111';
		Ext.getCmp('validateCode').allowBlank = true;
		doSubmit();
	}
	var loginForm = new Ext.FormPanel({
		renderTo : 'loginFormDiv',
		labelWidth : 60,
		//frame : true,
		id : 'login',
		style: 'margin:0px; padding:0px; border:0px;',
		//bodyStyle : 'padding:5px 5px 0;',
		width : 512,
		height: 80,
		unstyled: true,
		monitorValid : true,
//		keys : {
//			key : 13,
//			scope : this,
//			fn : doSubmit
//		},
		layout : 'table',
		layoutConfig:{
			columns:3
		},
		items : [{
					xtype:"container",
					layout: 'hbox',
					width:184,
					height:30,
					defaultMargins:{
						left:0
					},
					items: [{
						xtype : 'textfield',
						id : 'username',
						name : 'username',
						fieldLabel : '用户名',
						allowBlank : false,
						blankText : '账号不能为空'
					}]
				}, {
					xtype:'container',
					layout: 'hbox',
					width:90,
					height:30,
					items: [{
						id : 'validateCode',
						name : 'validateCode',
						xtype : 'textfield',
						fieldLabel : '验证码',
						regex : /^[0-9]{4}$/,
						width : 80,
						regexText : '图片不清楚么？请点击图片进行刷新，验证码为4位数字！',
						allowBlank : false,
						blankText : '验证码不能为空！'
					}]
				}, {
					xtype:'container',
					layout: 'hbox',
					width:330,
					height:30,
					items: [{
						width:60,
						height:20,
						html : '<img id="photo" src="user_code.do" onclick="changeImg(this)"/>'
					}]
				},{
					xtype:"container",
					layout: 'hbox',
					width:184,
					height:67,
					items: [{
						xtype : 'textfield',
						id : 'password',
						name : 'password',
						fieldLabel : '密码',
						allowBlank : false,
						blankText : '密码不能为空',
						inputType : 'password'
					}]
				}, {
					xtype:'container',
					layout:'hbox',
					colspan:2,
					width:420,
					height:67,
					items: [{
						xtype : 'button',
						cls:"login",
						type : 'submit',
						width: 110,
						height: 29,
						formBind : true,
						handler : doSubmit
					},{
						xtype: 'box',
						width: 10,
						height:29
					},{
						xtype : 'button',
						cls:"anonymouslogin",
						type : 'button',
						width:110,
						height:29,
						handler : doAnonymousSubmit
					}]
				}]

	});

		// win = new Ext.Window({
		// title : "登录自主会商平台",
		// width : 300,
		// resizable : false,
		// autoHeight : true,
		// layout : 'column',
		// collasible : false,
		// draggable : false,
		// defaults : {
		// border : false
		// },
		// items : {
		// columnWidth : 1,
		// items : loginForm
		// }
		// });
		// win.show();
});
function changeImg(obj) {
	obj.src = "user_code.do?date=" + new Date();
}