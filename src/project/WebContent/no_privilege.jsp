<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>没有权限</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/ext-all.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-base.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-all.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/ext-lang-zh_CN.js"></script>
</head>
<body>
<script language="javascript">
Ext.Msg.confirm("警告","您没有该操作的权限，是否重新登陆",function(btn){
	if(btn=='yes'){
		window.top.location.href="<%=request.getContextPath()%>/user_logout.do";
	}else{
		//history.go(-1);
		window.parent.closeActivePanel();
	}
});
</script>
</body>
</html>