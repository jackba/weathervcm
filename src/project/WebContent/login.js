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
		frame : true,
		id : 'login',
		bodyStyle : 'padding:5px 5px 0;',
		width : 800,
		monitorValid : true,
		keys : {
			key : 13,
			scope : this,
			fn : doSubmit
		},
		layout : 'column',
		items : [{
					columnWidth:.25,
					layout: 'form',
					items: [{
						xtype : 'textfield',
						id : 'username',
						name : 'username',
						fieldLabel : '用户名',
						allowBlank : false,
						blankText : '账号不能为空'
					}]
				}, {
					columnWidth:.25,
					layout: 'form',
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
					columnWidth:.2,
					layout: 'form',
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
					columnWidth:.3,
					layout: 'column',
					items: [{
						columnWidth:.3,
						items: [{
							html : '&nbsp;&nbsp;<img id="photo" src="user_code.do" onclick="changeImg(this)"/>'
						}]
					},{
						columnWidth:.2,
						items: [{
							xtype : 'button',
							text : '登录',
							type : 'submit',
							formBind : true,
							handler : doSubmit
						}]
					},{
						columnWidth:.3,
						items: [{
							xtype : 'button',
							text : '匿名登录',
							type : 'button',
							handler : doAnonymousSubmit
						}]
					},{
						columnWidth:.2,
						items: [{
							xtype : 'button',
							text : '取消',
							type : 'reset',
							handler : function() {
								loginForm.form.reset();
							}
						}]
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