<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="css/bootstrap.css">
<style>
	.container{
		position: relative;
        top: 200px;
        text-align: center;
	}
	
</style>
</head>
<body>
	<nav class="navbar navbar-dark bg-dark">
		<ul class="nav justify-content-end">
			<li class="nav-item">
			  <a class="btn btn-dark" aria-current="page" href="login.html">로그인</a>
			</li>
			<li class="nav-item">
			  <a class="btn btn-dark" href="join.html">회원가입</a>
			</li>
			<li class="nav-item">
				<a class="btn btn-dark" href="notice">게시판</a>
			</li>
		  </ul>
	</nav>
	
	<!-- Detail 페이지 -->
	<div class="container">
        <table class="table table-striped" style="text-align: center;">
			<thead>
				<tr>
					<th colspan="4">${n.title }</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th class="col-1">${n.boardnum }</th>
					<th class="col-10"><fmt:formatDate pattern="yyyy-MM-dd" value="${n.regDate }" /></th>
					<th class="col-1">${n.userId }</th>
					<th class="col-1">${n.hit }</th>
				</tr>
				<tr>
					<td colspan="4">${n.content }</td>
				</tr>
			</tbody>
		</table>
		
		<span><a class="btn btn-light" href="detailmodify?id=${n.boardnum }">수정</a></span>
		<span><a class="btn btn-light" href="notice">목록</a></span>
		<span><a class="btn btn-light" href="noticedelete?id=${n.boardnum }">삭제</a></span>
		
    </div>
    
	
	
	<script>
	</script>
	<script src="../java/js/bootstrap.js"></script>
</body>
</html>