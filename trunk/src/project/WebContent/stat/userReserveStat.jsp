<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户使用次数排行</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css"/>
</head>
<body>
	<h1>当前位置：统计分析&nbsp;&gt;&nbsp;<span class="position_current"> 用户使用次数排行</span></h1>
<TABLE class="bm-tb" cellSpacing=0 cellPadding=0 width="98%" align="center"
border=0><!--class="bm-tb"控制着表格样式-->
  <TBODY>
  <TR class=b-tb>
    <TH>排名</TH>
    <TH>用户</TH>
    <TH>使用次数</TH>
    <TH></TH></TR>
	<s:iterator value='#request.statList'>
  <tr>
  <td>
  <s:property value='index'/>
  </td>
  <td>
  <s:property value='userName'/>
  </td>
  <td>
  <s:property value='number'/>
  </td>
  <td align="left" valign="middle" width="410px" >
  <div style="float:left; width:<s:property value='length'/>px; margin:2px; background-color:#0000FF; "></div>
  </td>
  </tr>
  </s:iterator>
  </TBODY></TABLE>
</body>
</html>