<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Loop(forEach) Test01</title>
<%
	String [] menu = {"짜장면", "짬뽕", "스파게티", "칼국수", "냉면"};
	pageContext.setAttribute("menuList", menu);
%>
</head>
<body>
<h2>JSTL Loop(forEach) Test01</h2>
1) forEach 기본형식
=> Java 의 forEach 와 유사
   for (String s:students) {  out.print(s); }
   
2) varStatus 속성 활용
=> index, count, first, last    

3) varStatus 속성 연습
=> first, last : boolean Type
=> for, if(또는 choose) 구문 모두 중첩 가능 
=> 과제
   . first 는 굵은 파랑으로 출력
   . ul li 를 이용해서 출력하면서 menu 뒤에 ',' 표시
   . 단, 마지막에는 마침표를 표시하세요 ~~ 
=> 결과
</pre><hr>
<b>
Test 1) <br>
<c:forEach var="menu" items="${menuList}">
	${menu}, &nbsp;
</c:forEach>
<hr>
Test 2) varStatus 속성 활용
<table border="1" style="text-align:center; width:90%;">
	<tr style="background-color:orange;">
		<th>menu</th>
		<th>index</th>
		<th>count</th>
		<th>first</th>
		<th>last</th>
	</tr>
	<c:forEach var="menu" items="${menuList}" varStatus="vs">
		<tr>
			<td>${menu}</td>
			<td>${vs.index}</td>
			<td>${vs.count}</td>
			<td>${vs.first}</td>
			<td>${vs.last}</td>
		</tr>
	</c:forEach>
</table>
<hr>
Test 3) 과제
<!-- 현주누나 코드 -->
<ul>
<c:forEach var="menu" items="${menuList}" varStatus="vs">
   <li 
   		<c:if test="${vs.index == 0 }">style="color:blue"
   			</c:if>>
   				${menu}
   			<c:if test="${vs.last == false}"> ,
   			</c:if><c:if test="${vs.last == true}"> .
   		</c:if>
   </li>
 </c:forEach>
</ul>

<hr>

<!-- 선생님 코드 -->
<ul>
<c:forEach var="menu" items="${menuList}" varStatus="vs">
   <c:choose>
      <c:when test="${vs.first}">
         <li style="font-weight:bold; color:blue;">${menu}, </li>
      </c:when>
      <c:when test="${vs.last}">
         <li>${menu}. </li>
      </c:when>
      <c:otherwise>
         <li>${menu}, </li>
      </c:otherwise>
   </c:choose>
</c:forEach>
</ul>
<hr>
</body>
</html>