<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>留言列表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
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
</script>
<h1>留言板&nbsp;&gt;&nbsp;<span class="position_current">查看个人留言</span></h1>
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
<tr bgcolor="#cccccc">
<td><s:property value="title"/></td>
</tr>
<tr><td><span><s:property value="content"/></span><br/></td></tr>
<tr bgcolor="#e1e2e2"><td>作者： <s:property value='userName'/> | 发表于： <s:date name='createTime' format='yyyy-MM-dd HH:mm:ss'/> | <a href="#" onClick="deleteMessage('<s:property value="messageId"/>','<s:property value="#request.personal"/>')">删除该条留言</a></td></tr>
</s:iterator>
</table></td></tr></table>
<jsp:include page="../common/page.jsp"/>
</form>
</body>
</html>