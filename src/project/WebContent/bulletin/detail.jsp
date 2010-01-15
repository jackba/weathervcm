<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告详情</title>
</head>
<body>
<a href="#" onClick="history.go(-1)"><< 返回</a>  
<h1 align="center"></h1>
<h1 align="center"><s:property value='bulletinBoard.title'/></h1>
<p align="center"><s:date name='bulletinBoard.effectiveTime' format='yyyy-MM-dd'/></p>
<div align="left"><s:property value="bulletinBoard.content"/></div>
<p align="center"><input type="button" value="返回" onClick="history.go(-1)"/></p>
</body>
</html>