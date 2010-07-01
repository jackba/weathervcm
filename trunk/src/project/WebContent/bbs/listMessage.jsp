<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/xtheme-shallowgray.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css">
<script type="text/javascript" src="resources/js/ext-base.js"></script>
<script type="text/javascript" src="resources/js/ext-all.js"></script>
<script type="text/javascript" src="resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/calendar/WdatePicker.js"></script>
</head>
<body>
<script language="javascript">
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	window.parent.contentPanel.getActiveTab().setTitle("留言列表");
});
<s:if test='#request.msg!=null'>
Ext.Msg.alert("提示","<s:property value='#request.msg'/>");
</s:if>
function deleteMessage(messageId, personal){
	Ext.MessageBox.confirm('提示', '确定要删除该条留言吗?', function(button) {
		if (button == 'yes'){
			location.href = '<%=request.getContextPath()%>/bbs_delete.do?messageId='+messageId+'&personal='+personal;
		}
	});
}
function editMessage(messageId, personal, title, content){
	if(title==null || title==""){
	var win = new Ext.Window({
		title: '修改留言',
		width: 500,
		height: 350,
		items: [{
			xtype: 'form',
			url: '<%=request.getContextPath()%>/bbs_update.do',
			id: 'editform',
			title: '',
			labelWidth: 100,
			labelAlign: 'left',
			layout: 'form',
			autoHeight: true,
			items:[{
				xtype: 'hidden',
				name: 'messageBoard.messageId',
				value: messageId
			},{
				xtype: 'textarea',
				fieldLabel: '内容',
				anchor: '100%',
				name: 'messageBoard.content',
				height: 200,
				value: content
			}],
			buttons: [{
				text: '确认',
				xtype: 'button',
				handler: function(thisBtn, thisEvent){
					Ext.getCmp('editform').getForm().submit({
						success:function(form,action){
							Ext.Msg.alert('成功',action.result.msg,function(button){
								win.close();
								document.form1.submit();
							});
						},
						failure:function(form,action){
							switch (action.failureType) {
							case Ext.form.Action.CLIENT_INVALID:
                				Ext.Msg.alert('失败', 'Form fields may not be submitted with invalid values',function(btn){
									win.close();
								});
                				break;
            				case Ext.form.Action.CONNECT_FAILURE:
                				Ext.Msg.alert('失败', 'Ajax communication failed',function(btn){
									win.close();
								});
                				break;
            				case Ext.form.Action.SERVER_INVALID:
               					Ext.Msg.alert('失败', action.result.msg,function(btn){
									win.close();
								});
							}
						}
					});
				}
			},{
				text: '取消',
				xtype: 'button',
				handler: function(b, e){
					win.close();
				}
			}]
		}]
	});
	win.show(this);
	}else{
	var win = new Ext.Window({
		title: '修改留言',
		width: 500,
		height: 350,
		items: [{
			xtype: 'form',
			url: '<%=request.getContextPath()%>/bbs_update.do',
			id: 'editform',
			title: '',
			labelWidth: 100,
			labelAlign: 'left',
			layout: 'form',
			autoHeight: true,
			items:[{
				xtype: 'hidden',
				name: 'messageBoard.messageId',
				value: messageId
			},{
				xtype: 'textfield',
				fieldLabel: '标题',
				anchor: '100%',
				name: 'messageBoard.title',
				value: title
			},{
				xtype: 'textarea',
				fieldLabel: '内容',
				anchor: '100%',
				name: 'messageBoard.content',
				height: 200,
				value: content
			}],
			buttons: [{
				text: '确认',
				xtype: 'button',
				handler: function(thisBtn, thisEvent){
					Ext.getCmp('editform').getForm().submit({
						success:function(form,action){
							Ext.Msg.alert('成功',action.result.msg,function(button){
								win.close();
								document.form1.submit();
							});
						},
						failure:function(form,action){
							switch (action.failureType) {
							case Ext.form.Action.CLIENT_INVALID:
                				Ext.Msg.alert('失败', 'Form fields may not be submitted with invalid values',function(btn){
									win.close();
								});
                				break;
            				case Ext.form.Action.CONNECT_FAILURE:
                				Ext.Msg.alert('失败', 'Ajax communication failed',function(btn){
									win.close();
								});
                				break;
            				case Ext.form.Action.SERVER_INVALID:
               					Ext.Msg.alert('失败', action.result.msg,function(btn){
									win.close();
								});
							}
						}
					});
				}
			},{
				text: '取消',
				xtype: 'button',
				handler: function(thisBtn, thisEvent){
					win.close();
				}
			}]
		}]
	});
	win.show(this);
	}
}
function replyMessage(messageId, personal){
	var win = new Ext.Window({
		title: '修改留言',
		width: 500,
		height: 350,
		items: [{
			xtype: 'form',
			url: '<%=request.getContextPath()%>/bbs_reply.do',
			id: 'replyform',
			title: '',
			labelWidth: 100,
			labelAlign: 'left',
			layout: 'form',
			autoHeight: true,
			items:[{
				xtype: 'hidden',
				name: 'messageBoard.omsgId',
				value: messageId
			},{
				xtype: 'textarea',
				fieldLabel: '内容',
				anchor: '100%',
				name: 'messageBoard.content',
				height: 200
			}],
			buttons: [{
				text: '确认',
				xtype: 'button',
				handler: function(thisBtn, thisEvent){
					Ext.getCmp('replyform').getForm().submit({
						success:function(form,action){
							Ext.Msg.alert('成功',action.result.msg,function(button){
								win.close();
								document.form1.submit();
							});
						},
						failure:function(form,action){
							switch (action.failureType) {
							case Ext.form.Action.CLIENT_INVALID:
                				Ext.Msg.alert('失败', 'Form fields may not be submitted with invalid values',function(btn){
									win.close();
								});
                				break;
            				case Ext.form.Action.CONNECT_FAILURE:
                				Ext.Msg.alert('失败', 'Ajax communication failed',function(btn){
									win.close();
								});
                				break;
            				case Ext.form.Action.SERVER_INVALID:
               					Ext.Msg.alert('失败', action.result.msg,function(btn){
									win.close();
								});
							}
						}
					});
				}
			},{
				text: '取消',
				xtype: 'button',
				handler: function(){
					win.close();
				}
			}]
		}]
	});
	win.show(this);
}
</script>
<h1>留言板&nbsp;&gt;&nbsp;<span class="position_current"><s:if test='#request.personal=="true"'>查看个人留言</s:if><s:else>查看全部留言</s:else></span></h1>
<div class="wrap">
<div class="search">
<form name="form1" action="<%=request.getContextPath()%>/bbs_search.do" method="post">
<input type="hidden" name="personal" value="<s:property value='#request.personal'/>"/>
<table width="100%" cellspacing="10px"><tr><td>

<table width="100%"><tr><td>标题：</td><td><input type="text" name="title" class="putin200" value="<s:property value='#request.title'/>"/></td>
<td>内容：</td><td><input type="text" name="content" class="putin200" value="<s:property value='#request.content'/>"/></td><td>时间：</td><td>
<input type="text" class="Wdate" id="startTime" name="startTime" value="<s:property value='#request.startTime'/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/> - 
						 <input type="text" class="Wdate" id="endTime" name="endTime" value="<s:property value='#request.endTime'/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
</td><td><input type="button" value="查询" id="btnQuery"	class="butt_bg1" onMouseOver="this.className='butt_bg1_over'" onMouseOut="this.className='butt_bg1'" onClick="document.form1.submit();" /></td>
</tr></table>

</td></tr><tr><td><table width="100%" border="0">
<s:iterator value='#request.messageList'>
<s:if test='messageId == omsgId'>
<tr bgcolor="#cccccc">
<td colspan="2"><s:property value="title"/></td>
</tr>
</s:if>
<tr><s:if test='messageId==omsgId'><td colspan="2"></s:if><s:else><td rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;</td><td></s:else><span><s:property value="content"/></span><br/></td></tr>
<tr bgcolor="#e1e2e2"><s:if test='messageId==omsgId'><td colspan="2"></s:if><s:else><td></s:else>作者： <s:property value='userName'/> | 发表于： <s:date name='createTime' format='yyyy-MM-dd HH:mm:ss'/> | 最后修改： <s:date name='updateTime' format='yyyy-MM-dd HH:mm:ss'/> <s:if test='#session.userPrivilege.hasCodePrivilege("0037") || #session.userPrivilege.userId==userId'>| <a href="#" onClick="deleteMessage('<s:property value="messageId"/>','<s:property value="#request.personal"/>')">删除</a> | <a href="#" onClick="editMessage('<s:property value="messageId"/>','<s:property value="#request.personal"/>','<s:property value="title"/>','<s:property value="content"/>')">修改</a> </s:if>| <a href="#" onClick="replyMessage('<s:property value="omsgId"/>','<s:property value="#request.personal"/>')">回复</a></td></tr>
</s:iterator>
</table></td></tr></table>
<jsp:include page="../common/page.jsp"/>
</form>
</div>
</div>
</body>
</html>