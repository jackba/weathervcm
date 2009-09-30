<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>公告列表</title>
<style type="text/css">
#bulletinBoardTitle{color:#FF0000;}
</style>
</head>
<body>
<form name="form1" method="post" action="<%=request.getContextPath()%>/bulletin_list.do">
<h2 id="bulletinBoardTitle">>>最新公告</h2>
<br/>
<s:iterator value='#request.bulletinList'>
<ul>
<a href="<%=request.getContextPath()%>/bulletin_detail.do?bulletinId=<s:property value='bulletinId'/>"><s:property value='title'/>(<s:date name='effectiveTime' format='yyyy-MM-dd'/>)</a>
</ul>
</s:iterator>
<jsp:include page="../common/page.jsp"/>
</form>
</body>
</html>