<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>방명록 목록</title>
</head>
<body>

<h1>게시물</h1>
<br>
게시물 전체 수 : ${count }, 방문한 수 : ${cookieCount }<br><br>


<c:forEach items="${list}" var="guestbook">	
${guestbook.postId }<br>
${guestbook.title }<br>
${guestbook.content }<br>
${guestbook.date }<br>
<img src="<spring:url value='/resources/img/test.jpg'/>"/>

<c:if test="${sessionScope.isAdmin == 'true'}"><a href="delete?id=${guestbook.postId}">삭제</a><br><br></c:if>
</c:forEach>
<br>

<c:forEach items="${pageStartList}" var="pageIndex" varStatus="status">
<a href="list?start=${pageIndex}">${status.index +1 }</a>&nbsp; &nbsp;
</c:forEach>

<br><br>
<form method="post" action="write">
title : <input type="text" name="title"><br>
<textarea name="content" cols="60" rows="6"></textarea><br>
<input type="submit" value="등록">
</form>
</body>
</html>