<%@ page contentType="text/html;charset=UTF-8" errorPage="/error.jsp"%>
<%@ page import="com.cma.intervideo.util.PageHolder"%>

<%
	PageHolder pageHolder = (PageHolder) request.getAttribute("pageHolder");
	int totalPage = pageHolder.getTotalPage();
	int currentPage = pageHolder.getCurrentPage();
%>

<script language="javascript">
	function toPage(page)
	{
		document.form1.jumpPage.value = page;
		document.form1.submit();
	}
	function setPageSize(size)
	{
		document.form1.pageSize.value = size;
		toPage(1);
	}
	 function fun3890308916()
     {
		   if(document.getElementById("topage").value == "")
		   {
			   	alert("请输入您要跳转到的页码!");
			   	return false;
		   	}
     	var  newPar=/^\d+(\.\d+)?$/;
		   	if(newPar.test(document.getElementById("topage").value) == false)
		   	{
			   	alert("请输入正确的页码!");
			   	return false;
		   	}
     	if(parseInt(document.getElementById("topage").value)> <%=totalPage%>)
     	{
			   	alert("您输入的页码超过记录的总页数!");
			   	return false;
     	}
		toPage(document.getElementById("topage").value);
     }	   
</script>


<table width="100%" border="0" cellpadding="0" cellspacing="0">
	<input type="hidden" name="currPage" value="<%=currentPage%>" />
	<input type="hidden" name="pageSize" value="<%=pageHolder.getPageSize()%>" />
	<input type="hidden" name="jumpPage" value="" />
	<input type="hidden" name="totalPage" value="<%=totalPage%>" />
  <tr>
    <td>每页显示数量 &nbsp;
    <a href="#"	onclick="setPageSize(5)"> 
				<%if (pageHolder.getPageSize() == 5) {%><FONT color=red><strong>5</strong></FONT> 
				<%} else {%>5 
				<%}%> 
			</a>  
			<a href="#"	onclick="setPageSize(10)">  
				<%if (pageHolder.getPageSize() == 10) {%> <FONT color=red><strong>10</strong></FONT> 
				<%} else {%>10 
				<%}%> 
			</a> 
			<a href="#"	onclick="setPageSize(20)">
				<%if (pageHolder.getPageSize() == 20) {%><FONT color=red><strong>20</strong></FONT> 
				<%} else {%>20 
				<%}%> 
			</a> 
			<a href="#"	onclick="setPageSize(50)"> 
				<%if (pageHolder.getPageSize() == 50) {%> <FONT color=red><strong>50</strong></FONT> 
				<%} else {%> 50 
				<%}%> 
			</a> 
    </td>
    <td>到第
      <INPUT name="topage" id="topage" type="text" class="lin001 decorate" size="4" onkeydown="if(event.keyCode==13) return fun3890308916()">
      页      </td>
    <td><input name="submit2" type="submit" class="decorate" value="GO"  onclick="return fun3890308916()"/></td>
    <td>第<%=currentPage%>页 &nbsp;&nbsp;共<%=totalPage%>页</td>
    <td><input name="submit22" type="submit" class="decorate" value="首页"  onclick="toPage(1)" /></td>
    <td><input name="submit23" type="submit" class="decorate" value="上一页" onclick="toPage(<%=(currentPage - 1) < 1 ? 1 : (currentPage - 1)%>)" /></td>
    <td><input name="submit24" type="submit" class="decorate" value="下一页" onclick="toPage(<%=(currentPage + 1) > totalPage ? totalPage: (currentPage + 1)%>)"/></td>
    <td><input name="submit25" type="submit" class="decorate" value="尾页" onclick="toPage(<%=totalPage%>)"/></td>
    </tr>
</table>
