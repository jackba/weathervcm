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
	var loginForm = new Ext.FormPanel({
		labelWidth : 70,
		frame : true,
		id : 'login',
		bodyStyle : 'padding:5px 5px 0',
		width : 300,
		monitorValid : true,
		keys : {
			key : 13,
			scope : this,
			fn : doSubmit
		},
		items : [{
			layout : 'form',
			items : [{
						xtype : 'textfield',
						name : 'username',
						fieldLabel : '用户名',
						allowBlank : false,
						blankText : '账号不能为空'
					}, {
						xtype : 'textfield',
						name : 'password',
						fieldLabel : '密码',
						allowBlank : false,
						blankText : '密码不能为空',
						inputType : 'password'
					}, {
						items : [{
							layout : 'column',
							items : [{
								layout : 'form',
								items : [{
											name : 'validateCode',
											xtype : 'textfield',
											fieldLabel : '验证码',
											regex : /^[0-9]{4}$/,
											regexText : '图片不清楚么？请点击图片进行刷新，验证码为4位数字！',
											allowBlank : false,
											blankText : '验证码不能为空！'
										}]
							}, {
								html : '&nbsp;&nbsp;<img id="photo" src="user_code.do" onclick="changeImg(this)"/>'
							}]
						}]
					}]
		}],
		buttons : [{
					text : '登录',
					type : 'submit',
					formBind : true,
					handler : doSubmit
				}, {
					text : '取消',
					handler : function() {
						loginForm.form.reset();
					}
				}]
	});

	win = new Ext.Window({
				title : "登录自主会商平台",
				width : 300,
				resizable : false,
				autoHeight : true,
				layout : 'column',
				collasible : false,
				draggable : false,
				defaults : {
					border : false
				},
				items : {
					columnWidth : 1,
					items : loginForm
				}
			});
	win.show();
});
function changeImg(obj) {
	obj.src = "user_code.do?date=" + new Date();
}