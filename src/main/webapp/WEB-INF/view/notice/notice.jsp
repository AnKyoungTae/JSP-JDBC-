<%@page import="wep.Notice"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	}
	
	.pageing li{
		display: inline;
		margin: 5px;
	}
	
	a{
		text-decoration: none; text-shadow: 0 0 24px;
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
	
	<div class="container">
		<div style="text-align: center;">
				<c:set var="page" value="${( empty param.p)? 1:param.p }" />
				<c:set var="startNum" value="${page-(page-1)%5 }" />
				<c:set var="endNum" value="${fn:substringBefore(Math.ceil(count/5) , '.')}"/>
						
		</div>
		<div>
			<span style="color: orange">${(empty param.p)?1:param.p}</span>/${endNum} pages
		</div>
		<!-- 검색 -->
		<div style="text-align: right;">
			<form action="notice">
				<select name="t" >
					<option ${(param.t == "title")?"selected" : "" } value="title">제목</option>
					<option ${(param.t == "userId")?"selected" : "" } value="userId">이름</option>
				</select>
				<input type="text" name="q" value="${param.q }">
				<input type="submit" value="검색">
			</form>
		</div>
		<!-- 게시판 리스트 -->
		<table class="table table-striped" style="text-align: center;">
			<thead>
				<tr>
					<th class="col-1">No</th>
					<th class="col-7">제목</th>
					<th class="col-2">작성자</th>
					<th class="col-1">작성일</th>
					<th class="col-1">조회수</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="n" items="${list }">
				<tr>
					<td class="col-1">${n.boardnum }</td>
					<td class="col-7"><a href="detail?id=${n.boardnum }">${n.title }</a></td>
					<td class="col-2">${n.userId }</td>
					<td class="col-1"><fmt:formatDate pattern="yyyy-MM-dd" value="${n.regDate }" /></td>
					<td class="col-1">${n.hit }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<!-- 글쓰기 버튼 -->
		<div>
		<button type="button" class="btn btn-dark" style="float: right; margin: 10px;" onclick="moveWriting()">글쓰기</button>
		</div>
		<!-- 페이징 처리 -->
			
			
			<div style="text-align: center;"  >
				
				<ul class="pageing" style="list-style: none;" >
					<c:if test="${startNum > 1 }">
						<li><a class="btn btn-dark" href="notice?p=${ startNum - 1 }" class="btn btn-next">이전</a></li>
					</c:if>
					<c:if test="${startNum <= 1 }">
						<li><span class="btn btn-dark" onclick="alert('이전 페이지가 없습니다.')">이전</span></li>
					</c:if>
					
					<c:forEach var="i" begin="0" end="4" >
					<c:if test="${(startNum + i) <= endNum }">
						<li><a Style="color: ${( page == (startNum+i))?'orange':'blue'}" href="notice?p=${ startNum + i }&t=${param.t }&q=${param.q }" >${ startNum+i }</a></li>
					</c:if>
					</c:forEach> 
					 
					<c:if test="${startNum+5 <= endNum }">
						<li><a class="btn btn-dark" href="notice?p=${ startNum+5 }" class="btn btn-next">다음</a></li>
					</c:if>
					<c:if test="${startNum+5 > endNum }">
						<li><span class="btn btn-dark" onclick="alert('다음 페이지가 없습니다.')">다음</span></li>
					</c:if>
				</ul>
			</div>
			
	</div>
			
	<script>
		function moveWriting(){
			location.href = "writing.html";
		}
	</script>
	<script src="../java/js/bootstrap.js"></script>
</body>
</html>