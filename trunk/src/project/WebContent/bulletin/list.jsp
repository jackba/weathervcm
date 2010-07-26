<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>公告列表</title>
<link href="<%=request.getContextPath()%>/resources/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/nicejforms.js"></script>
<style type="text/css" media="screen">@import url(<%=request.getContextPath()%>/resources/css/niceforms-default.css);</style>
</head>
<body>
      <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td> 
           </td>
        </tr>
      </table>
	  
	 
	  
      <table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
	  <form name="form1" method="post" action="<%=request.getContextPath()%>/bulletin_list.do" class="niceform">
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
                <td height="30" align="left" class="heida" bgcolor="#fbfbfb" >最新公告</td>
              </tr>
              <tr>
                <td height="3" background="images/xuxian.gif"></td>
              </tr>
              <tr>
                <td height="0" align="center" bgcolor="#FFFFFF">
                  

<br />
<table width="90%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#666666">
                    
                    <tr>
                      <td width="50" height="25" align="center" bgcolor="#FFFFFF">序号</td>
                      <td align="center" bgcolor="#FFFFFF">标题</td>
                      <td width="80" align="center" bgcolor="#FFFFFF">时间</td>
                    </tr>
					<s:iterator value='#request.bulletinList' status="rowStatus">
                    <tr>
                      <td width="50" height="25" align="center" bgcolor="#FFFFFF"><s:property value='#rowStatus.index+1+#request.pageHolder.pageSize*(#request.pageHolder.currentPage-1)'/></td>
                      <td bgcolor="#FFFFFF"><a href="<%=request.getContextPath()%>/bulletin_detail.do?bulletinId=<s:property value='bulletinId'/>"><s:property value='title'/></td>
                      <td align="center" bgcolor="#FFFFFF"><s:date name='effectiveTime' format='yyyy-MM-dd'/></td>
                    </tr>
                    </s:iterator>
                   
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
        </tr> </form>
      </table>
	   <script type="text/javascript">
$(document).ready(function(){$.NiceJForms.build({imagesPath:"<%=request.getContextPath()%>/resources/css/images/default/"})});
</script>
</body>
</html>