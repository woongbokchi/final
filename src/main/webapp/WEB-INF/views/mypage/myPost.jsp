<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/manage.css">
<title>Mycipe - MyPage</title>
</head>
<body>
	<jsp:include page="../navbar.jsp"></jsp:include>
	<div id="body-content">
		<img src="resources/image/head-title/mypage3.jpg" style = "margin:auto; width:1625px; height:120px;">
		<div id="head-move">
			<a href="myPage">My Profile</a>
			<a href="myPost">My Post</a>
			<a href="help">My QnA</a>
		</div>
		<div id="post-body">
			<div style="text-align: center;">
				<table border=1
					style="margin: 0px auto; background-color: white; border: 3px solid black;">
					<tr height=30px; style="border-bottom:3px solid black;">
						<td width=650px>제목</td>
						<td width=200px>작성일</td>
						<td width=75px>조회수</td>
						<td width=75px>추천</td>
					</tr>
					<c:forEach items="${list}" var="vo">

						<!--controller에서 모델에담은 list를 가져옴.  -->
						<tr height=30px;>
							<td width=650px><a href="#"
								onClick="location.href='read?bno=${vo.bno}'">${vo.btitle}</a></td>
							<td width=200px><f:formatDate value="${vo.bwdate}"
									pattern="yyyy-MM-dd" /></td>
							<td width=75px>${vo.bviewcnt}</td>
							<td width=75px>${vo.blike}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
<script>
	
</script>
</html>


