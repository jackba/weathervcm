<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/xtheme-shallowgray.css">

<script type="text/javascript" src="resources/js/ext-base.js"></script>
<script type="text/javascript" src="resources/js/ext-all.js"></script>
<script type="text/javascript" src="resources/js/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/calendar/WdatePicker.js"></script>
<link href="<%=request.getContextPath()%>/resources/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/nicejforms.js"></script>
<style type="text/css" media="screen">@import url(<%=request.getContextPath()%>/resources/css/niceforms-default.css);</style>

<style type="text/css">
h1{float:left; height:20px; line-height:20px; padding-left:32px;color:#000; font-weight:bold; background:url(<%=request.getContextPath()%>/resources/img/ico_2.gif) 11px 1px no-repeat; font-size:12px; font-weight:normal }
</style>

</head>
<body>
<script language="javascript">
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL="resources/images/default/s.gif";
	window.parent.contentPanel.getActiveTab().setTitle("留言列表");
	$(document).ready(function(){$.NiceJForms.build({imagesPath:"<%=request.getContextPath()%>/resources/css/images/default/",formclass:'decorate'})});
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
		title: '回复留言',
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

<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td> 
           </td>
        </tr>
      </table>
<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
<form name="form1" id="form1" action="<%=request.getContextPath()%>/bbs_search.do" method="post" class="niceform">
<input type="hidden" name="personal" value="<s:property value='#request.personal'/>"/>
        <tr>
          <td><div style="background-image:url(images/main_z.gif);background-repeat:no-repeat;background-position: 0px; position:absolute; width:13px; height:98px;"></div></td>
        </tr>
        <tr>
          <td></td>
        </tr>
        <tr>
          <td width="8" height="8" style="background-image: url(images/main_p.gif); background-position: left top;"></td>
          <td style="background-image: url(images/main_h.gif); background-position: bottom;"></td>
          <td width="8" height="8"  style="background-image: url(images/main_p.gif); background-position: right top;"></td>
        </tr>
        <tr>
          <td style="background-image: url(images/main_s.gif); background-position: right; background-repeat:repeat-y;"></td>
          <td ><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="30" align="center" bgcolor="#fbfbfb" ><table border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td>标题：
                      <input type="text" name="title" size="12" class="decorate" value="<s:property value='#request.title'/>"/></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;内容：
                      <input type="text" name="content" size="12" class="decorate" value="<s:property value='#request.content'/>"/></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;时间：
                      <input type="text" size="22" class="Wdate" id="startTime" name="startTime" value="<s:property value='#request.startTime'/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/> - 
						 <input type="text" size="22" class="Wdate" id="endTime" name="endTime" value="<s:property value='#request.endTime'/>" onFocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;
                      <input type="button" value="查询" id="btnQuery"	class="decorate" onClick="document.form1.submit();" /></td></tr>
                </table></td>
                </tr>
              <tr>
                <td height="3" background="images/xuxian.gif"></td>
              </tr>
              <tr>
                <td height="0" bgcolor="#FFFFFF" align="center">
                  <br />
                  <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#ededed">
                    <tr>
                      <td><br />

<table width="100%" border="0" cellspacing="0" style="text-align:left">
<s:iterator value='#request.messageList'>
<tr>
  <td height="8" colspan="2"></td>
</tr>

<s:if test='messageId == omsgId'>
<tr><td colspan="2"><table width="100%" style="border:solid 1px #cccccc" cellpadding="0" cellspacing="0">
<tr>
  <td height="7" colspan="2" style="background-image:url(images/message.gif); background-repeat:repeat-x"></td>
</tr>
<tr bgcolor="#ffffff">
<td colspan="2"><s:property value="title"/></td>
</tr>
</s:if>
<tr bgcolor="#ffffff"><s:if test='messageId==omsgId'><td colspan="2"></s:if><s:else><td bgcolor="#ededed" valign="top"><img src="images/message_2.gif"/></td><td><table width="100%" style="border:solid 1px #cccccc" cellpadding="0" cellspacing="0"><tr><td height="7" style="background-image:url(images/message_1.gif); background-repeat:repeat-x"></td></tr><tr bgcolor="#ffffff"><td></s:else><span><s:property value="content"/></span><br/></td></tr>
<tr bgcolor="#ffffff"><s:if test='messageId==omsgId'><td colspan="2"></s:if><s:else><td></s:else>发帖人： <s:property value='userName'/> | 发表于： <s:date name='createTime' format='yyyy-MM-dd HH:mm:ss'/> | 最后修改： <s:date name='updateTime' format='yyyy-MM-dd HH:mm:ss'/> <s:if test='#session.userPrivilege.hasCodePrivilege("0037") || #session.userPrivilege.userId==userId'>| <a href="#" onClick="deleteMessage('<s:property value="messageId"/>','<s:property value="#request.personal"/>')">删除</a> | <a href="#" onClick="editMessage('<s:property value="messageId"/>','<s:property value="#request.personal"/>','<s:property value="title"/>','<s:property value="content"/>')">修改</a> </s:if>| <a href="#" onClick="replyMessage('<s:property value="omsgId"/>','<s:property value="#request.personal"/>')">回复</a></td></tr>

</table></td></tr>

</s:iterator>
</table>

<br /></td>
                    </tr>
                  </table>
                  <br />


<jsp:include page="../common/page.jsp"/>
</td>
              </tr>
              <tr>
                <td bgcolor="#fbfbfb">&nbsp;</td>
              </tr>
          </table></td>
          <td style="background-image: url(images/main_s.gif); background-position: left; background-repeat:repeat-y;"></td>
        </tr>
        <tr>
          <td width="8" height="8" style="background-image: url(images/main_p.gif); background-position: left bottom;"></td>
          <td style="background-image: url(images/main_h.gif); background-position: top;"></td>
          <td width="8" height="8" style="background-image: url(images/main_p.gif); background-position: right bottom;"></td>
        </tr>
		</form>
      </table>
      <br />
      <br />
</body>
</html>