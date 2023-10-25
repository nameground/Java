<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="/green/resources/myLib/myStyle.css">
<title>** Spring_MVC2 JoDetail **</title>
</head>
<body>
	<h2>** Spring_MVC2 JoDetail **</h2>
	<table>
		<c:if test="${not empty requestScope.apple}">
			<tr height="40">
				<th bgcolor="Plum">jno</th>
				<td>${requestScope.apple.jno}</td>
			</tr>
			<tr height="40">
				<th bgcolor="Plum">Jname</th>
				<td>${requestScope.apple.jname}</td>
			</tr>
			<tr height="40">
				<th bgcolor="Plum">I D</th>
				<td>${requestScope.apple.id}</td>
			</tr>
			<tr height="40">
				<th bgcolor="Plum">Project</th>
				<td>${requestScope.apple.project}</td>
			</tr>
			<tr height="40">
				<th bgcolor="Plum">Slogan</th>
				<td>${requestScope.apple.slogan}</td>
			</tr>
		</c:if>
		<c:if test="${empty requestScope.apple}">
			<tr>
				<td colspan="2">~~출력할 자료가 없습니다.</td>
			</tr>
		</c:if>
	</table>
	<c:if test="${not empty requestScope.message}">
		${requestScope.message}
	</c:if>
	<hr>
	<!-- 로그인 한 경우에는 새글등록, 답글등록 가능해짐 -->
	<!-- 로그인id 와 글쓴이id 가 동일하면 수정, 삭제 가능 -->
		&nbsp;<a href="jdetail?jCode=U&jno=${requestScope.apple.jno}">조수정</a> &nbsp;
		&nbsp;<a href="jdelete?jno=${requestScope.apple.jno}">조삭제</a> &nbsp;
	<hr>
	&nbsp;<a href="javascript:history.go(-1)">이전으로</a> &nbsp;
	<a href="/green/home">Home</a>
	<br>

</body>
</html>