<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="/Spring02/resources/myLib/myStyle.css">
<title>** member Password Test **</title>
</head>
<body>

	<h2>** Spring_MVC2 member Password Test **</h2>
	<form action="pUpdate" method="post">
		<table>
			<tr height="40">
				<td><label for="password">새로운비밀번호</label></td>
				<td><input type="password" id="password" name="password"></td>
			</tr>
			<tr height="40">
				<td><label for="password">비밀번호 확인</label></td>
				<td><input type="password" id="password" name="password"></td>
			</tr>
			<tr height="0">
				<td></td>
				<td><input type="submit" value="수정">&nbsp;&nbsp; <input
					type="reset" value="취소"></td>
			</tr>
		</table>
	</form>
	<hr>

	<c:if test="${not empty requestScope.message}">
	message : ${requestScope.message}
	</c:if>
	<hr>

	&nbsp;
	<a href="/Spring02/home">Home</a>&nbsp;

</body>
</html>