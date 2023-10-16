<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>** Hello Dynamic Web_Project **</h2>
<br>
<%-- 
** 아래 JSTL 과 비교
<%
	if( session.getAttribute("loginName") != null ){ %>
		<%=session.getAttribute("loginName") %> 님 안녕하세요 ~~ <br>
<%	}else { %>
	로그인 후 이용하세요 <br>
<% }%> 
--%>

<c:if test="${not empty sessionScope.loginName}">
		${sessionScope.loginName} 님 안녕하세요 ~~ <br>
</c:if>
<c:if test="${empty sessionScope.loginName}">
	로그인 후 이용하세요. <br>
</c:if>

<%--
!= 보다는 empty 사용을 권장함
empty 는 값의 유무와 글자수가 0보다 큰지를 한번에 할 수 있음
<c:if test="${sessionScope.loginName != null}">
</c:if>
--%>

<hr>
<img src="./images/a2.png" width ="400" height="300">
<hr>
<c:if test="${not empty sessionScope.loginName }">
	&nbsp;<a href="/Web01/logout">Logout</a> &nbsp;
	&nbsp;<a href="/Web01/detail">MyInfo</a> <br>
</c:if>
<c:if test="${empty sessionScope.loginName }">
	&nbsp;<a href="/Web01/servletTestForm/flowEx04_LoginForm.jsp">Login Form</a>&nbsp;
	&nbsp;<a href="/Web01/jsp99_mvcTest/mvc2_sJoin.jsp">Join</a> <br>
</c:if>

&nbsp; <a href="/Web01/helloS">HelloServelt</a> <br>
&nbsp; <a href="/Web01/lifecycle">ServletLF</a>
&nbsp;<a href="/Web01/helloS">Hello Servlet</a>
&nbsp;<a href="/Web01/lifeCycle">Servlet LifeCycle</a>
&nbsp;<a href="/Web01/flow01">Flow01</a>
&nbsp;<a href="/Web01/servletTestForm/flow02_TestForm.jsp">Flow02_Form</a><br>
&nbsp;<a href="/Web01/sessioni">SessionInfo</a>
&nbsp;<a href="/Web01/01set">Scope Test</a>
&nbsp;<a href="/Web01/list2">List2</a>
</body>
</html>