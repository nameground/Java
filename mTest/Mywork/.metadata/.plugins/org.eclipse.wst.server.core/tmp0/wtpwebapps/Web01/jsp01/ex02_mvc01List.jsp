<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="mvcTest.StudentService, mvcTest.StudentDTO" %> 
<%@ page import="java.util.List" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JSP Student List_MVC01 **</title>
</head>
<body>
<h2>** JSP Student List_MVC01 **</h2>
<pre>
=> Service 실행 -> 결과 -> 출력
</pre>
<%
	StudentService service = new StudentService();
	List<StudentDTO> list = service.selectList();	
%>
<table border="1" style="width:90%">
	<tr bgcolor="Lime">
		<th>sno</th><th>name</th><th>age</th><th>jno</th><th>info</th><th>point</th><th>birthday</th>
	</tr>
<% //list 에서 출력
	if ( list!=null ) {
		for ( StudentDTO s:list ) { %>
		<tr>
			<td><%=s.getSno()%></td>
			<td><%=s.getName()%></td>
			<td><%=s.getAge()%></td>
			<td><%=s.getJno()%></td>
			<td><%=s.getInfo()%></td>
			<td><%=s.getPoint()%></td>
			<td><%=s.getBirthday()%></td>
		</tr>
<%		} //for
	}else { %>
		<h2> 출력할 Data 가 없습니다 ~~ </h2>");
<%	} %>	
</table>

</body>
</html>