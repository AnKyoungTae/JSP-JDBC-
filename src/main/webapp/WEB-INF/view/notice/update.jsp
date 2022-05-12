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
		<form action="datailUpdate?boardnum=${n.boardnum }" method="post">
			<div class="container">
		        <table class="table table-striped" style="text-align: center;">
					<thead>
						<tr>
							<th colspan="4">제목 : <input type="text" name="title" value="${n.title }"></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th class="col-1">${n.boardnum }</th>
							<th class="col-10">${n.regDate }</th>
							<th class="col-1">${n.userId }</th>
							<th class="col-1">${n.hit }</th>
						</tr>
						<tr>
							<td colspan="4"><textarea cols="100" rows="10" name="content">${n.content }</textarea></td>
						</tr>
					</tbody>
				</table>
		    </div>
		
			<div class="container">
			<input type="submit" class="btn btn-light" value="수정">
			<a class="btn btn-light" href="notice">취소</a>
			</div>
		</form>
		
	
	
	<script>
	</script>
	<script src="../java/js/bootstrap.js"></script>
</body>
</html>