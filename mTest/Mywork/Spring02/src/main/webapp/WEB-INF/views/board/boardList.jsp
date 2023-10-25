<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link rel="stylesheet" type="text/css" href="/green/resources/myLib/myStyle.css">
<title>Spring_MVC2 BoardList</title>
</head>
<body>
<h2>Spring_MVC2 BoardList</h2>

<!-- MVC2_sDelete에 만든 message 부분 구현하기 -->
<hr>
<c:if test="${not empty requestScope.message}">
   ${requestScope.message} <br><hr>
</c:if>

<table border="1" style="width:90%">
   <tr bgcolor="orange">
      <th>Seq</th>
      <th>Title</th>
      <th>ID</th>
      <th>RegDate</th>
      <th>조회수</th>
   </tr>
   <c:if test="${not empty requestScope.banana}">
      <c:forEach var="s" items="${requestScope.banana}">
         <tr>
            <td>${s.seq}</td>
            <!-- Title
                 => 로그인 한 경우에만 글내용을 볼 수 있도록 Link -->
            <td>
            	<c:if test="${not empty loginID}">
	            	<a href="bdetail?seq=${s.seq}"> ${s.title}</a>
            	</c:if>
            	<c:if test="${empty loginID}">
	            	${s.title}
	         	</c:if>
            </td>
            <td>${s.id}</td>
            <td>${s.regdate}</td>
            <td>${s.cnt}</td>
         </tr>
      </c:forEach>
   </c:if>
   <c:if test="${empty requestScope.banana}">
      <tr>
         <td colspan="5">출력할 Data 가 1건도 없습니다.</td>
      </tr>
   </c:if>
</table>

<hr>
<!-- 로그인 한 경우에만 새글등록 가능 -->
<c:if test="${not empty loginID}">
	&nbsp; <a href="boardInsert">새글등록</a> <br>
</c:if>
&nbsp; <a href="/green/home">Home</a> <br>

</body>
</html>