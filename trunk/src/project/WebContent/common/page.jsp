<%@ page contentType="text/html;charset=UTF-8" errorPage="/error.jsp"%>
<%@ page import="com.cma.intervideo.util.PageHolder"%>

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
</script>

<%
	PageHolder pageHolder = (PageHolder) request.getAttribute("pageHolder");
%>
<table width="95%">
	<%
		int totalPage = pageHolder.getTotalPage();
		int currentPage = pageHolder.getCurrentPage();
	%>
	<input type="hidden" name="currPage" value="<%=currentPage%>" />
	<input type="hidden" name="pageSize" value="<%=pageHolder.getPageSize()%>" />
	<input type="hidden" name="jumpPage" value="" />
	<input type="hidden" name="totalPage" value="<%=totalPage%>" />

	<tr height="30" align="right">
		<td class="newPagetd">每页显示数量： 
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
			&nbsp;&nbsp; 
			到 <INPUT name="topage" id="topage" class="lin001" size="4" onkeydown="if(event.keyCode==13) return fun3890308916()"> 页&nbsp; 
			<input type="image" src="<%=request.getContextPath()%>/resources/images/go.gif" align="middle" onclick="return fun3890308916()"> &nbsp;&nbsp;&nbsp; 
			第&nbsp;<strong><%=currentPage%></strong>&nbsp;页&nbsp;&nbsp;
			共&nbsp;<strong><%=totalPage%></strong>&nbsp;页 
			<SCRIPT	type=text/javascript>
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
				   	toPage(parseInt(document.getElementById("topage").value));
	            }
	        </SCRIPT> 
	        <input type="image" src="<%=request.getContextPath()%>/resources/images/newPage_01.gif" alt="首页" onclick="toPage(1)" width="49" align="middle"> &nbsp; 
	        <input type="image" src="<%=request.getContextPath()%>/resources/images/newPage_02.gif" alt="上一页"	onclick="toPage(<%=(currentPage - 1) < 1 ? 1 : (currentPage - 1)%>)" width="49" align="middle"> &nbsp; 
	        <input type="image"	src="<%=request.getContextPath()%>/resources/images/newPage_03.gif" alt="下一页"	onclick="toPage(<%=(currentPage + 1) > totalPage ? totalPage: (currentPage + 1)%>)"	width="49" align="middle"> &nbsp; 
	        <input type="image"	src="<%=request.getContextPath()%>/resources/images/newPage_04.gif" alt="尾页" onclick="toPage(<%=totalPage%>)"	width="49" align="middle">
        </td>
	</tr>
</table>

