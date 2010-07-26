<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告详情</title>
<link href="<%=request.getContextPath()%>/resources/css/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/nicejforms.js"></script>
<style type="text/css" media="screen">@import url(<%=request.getContextPath()%>/resources/css/niceforms-default.css);</style>
</head>
<body>
<br />
<table width="600"  border="0" align="center" cellpadding="0" cellspacing="0" style="margin:0 auto">
  <form action="#" method="post" class="niceform">
    <tr>
      <td><div style="background-image:url(<%=request.getContextPath()%>/images/main_z.gif);background-repeat:no-repeat;background-position: 0px; position:absolute; width:13px; height:98px;"></div></td>
    </tr>
    <tr>
      <td></td>
    </tr>
    <tr>
      <td width="8" height="8" style="background-image: url(<%=request.getContextPath()%>/images/main_p.gif); background-position: left top;"></td>
      <td style="background-image: url(<%=request.getContextPath()%>/images/main_h.gif); background-position: bottom;"></td>
      <td width="8" height="8"  style="background-image: url(<%=request.getContextPath()%>/images/main_p.gif); background-position: right top;"></td>
    </tr>
    <tr>
      <td style="background-image: url(<%=request.getContextPath()%>/images/main_s.gif); background-position: right; background-repeat:repeat-y;"></td>
      <td ><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td height="30" colspan="2" align="left" class="heida" bgcolor="#fbfbfb" ><a href="#" onClick="history.go(-1);return false;" style="font-size:12px; color: #999999;">&lt;&lt;返回</a></td>
        </tr>
        <tr>
          <td height="3" colspan="2" background="<%=request.getContextPath()%>/images/xuxian.gif"></td>
        </tr>
        <tr>
          <td height="0" colspan="2" bgcolor="#FFFFFF"><br />
            <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
            <tr>
              <td  class="heida"><div align="center"><s:property value='bulletinBoard.title'/></div></td>
            </tr>
            <tr>
              <td height="30"> <div align="center"><s:date name='bulletinBoard.effectiveTime' format='yyyy-MM-dd'/></div></td>
            </tr>
          
            <tr>
              <td height="3" background="<%=request.getContextPath()%>/images/xuxian.gif"></td>
            </tr>
            <tr>
              <td height="10"></td>
            </tr>
            <tr>
              <td style="text-indent:24px">
                <p><s:property value="bulletinBoard.content"/></p></td>
            </tr>
            <tr>
              <td align="center"><br />
			  <input type="button" value="返回" onClick="history.go(-1)" />
&nbsp;</td>
            </tr>
          </table>
            <br />            <br /></td>
        </tr>
        <tr>
          <td colspan="2" bgcolor="#fbfbfb">&nbsp;</td>
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
<script type="text/javascript">
$(document).ready(function(){$.NiceJForms.build({imagesPath:"<%=request.getContextPath()%>/resources/css/images/default/"})});
</script>
</body>

</html>