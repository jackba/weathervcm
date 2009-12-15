<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议次数统计表</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/css/content.css"/>
</head>
<body>
<br/>
	<h1>当前位置：系统资源浏览&nbsp;&gt;&nbsp;<span class="position_current"> 会议次数统计表</span></h1>
<br/><br/><br/>
<TABLE class="bm-tb" cellSpacing=0 cellPadding=0 width="98%" align="center"
border=0><!--class="bm-tb"控制着表格样式-->
  <TBODY>
  <TR class=b-tb>
    <TH>会议类型</TH>
    <TH>主会场</TH>
    <TH>召开次数</TH>
    </TR>
	<s:iterator value='#request.statList'>
  <tr>
  <s:if test='columnSpan>0'>
  <td rowspan="<s:property value='columnSpan'/>">
  <s:property value='confType'/>
  </td>
  </s:if>
  
  <td>
  <s:property value='mainUnit'/>
  </td>
  <td>
  <s:property value='num'/>
  </td>
  </tr>
  </s:iterator>
  </TBODY></TABLE>
</body>
</html>