<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="resources/css/navbar.css">

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>

</head>

<body>
	<div id="navbar-back">
		<div id="navbar-content">
			<div id="navbar-mainicon">
				<a href="main"><img src="resources/image/logo2.png"
					onmouseover="this.src='resources/image/logo1.png'"
					onmouseout="this.src='resources/image/logo2.png'"
					style="width: 200px;"></a>
			</div>
			<div id="navbar-loginapi">
				<br>
				<c:if test="${uemail == null}">
					<div id="naver_id_login"></div>
				</c:if>
				<c:if test="${uemail != null}">
					<!-- 	<h1>${unick}님 환영합니다</h1> -->
					<div id="naver_id_login" style="display: none"></div>
					<button type="button" class="btn btn-primary btn-lg btn-block"
						onClick="location.href='logout'">Logout</button>
				</c:if>
				<!--  
			-->
			</div>
			<c:if test="${vo.uauth == 1}">
				<div id="navbar-icon-manager">
					<div class="icons" style="margin-left: 6px; margin-right: 4px;">
						<a href="write"><img src="resources/image/write1.jpg"
							style="width: 60px;" id="icon-write"></a>
					</div>
					<div class="icons">
						<a href="myPage"><img src="resources/image/mypage1.jpg"
							style="width: 60px;"></a>
					</div>
					<div class="icons" style="margin-left: 4px; margin-right: 6px;">
						<a href="userlist"><img src="resources/image/update1.jpg"
							style="width: 60px;"></a>
					</div>
				</div>
			</c:if>
			<c:if test="${vo.uauth == 0}">
				<div id="navbar-icon-user">
					<div class="icons" style="margin-left: 35px; margin-right: 5px;">
						<a href="write"><img src="resources/image/write1.jpg"
							style="width: 60px;" id="icon-write"></a>
					</div>
					<div class="icons" style="margin-left: 5px; margin-right: 35px;">
						<a href="myPage"><img src="resources/image/mypage1.jpg"
							style="width: 60px;" id="icon-mypage"></a>
					</div>
				</div>
			</c:if>
			<div id="navbar-drop">
				<div class="dropdown">
					<button class="dropbtn">National</button>
					<div class="dropdown-content">
						<a href="korean">Korean</a> 
						<a href="western">Western</a>
						<a href="chinese">Chinese</a> 
						<a href="japanese">Japanese</a> 
						<a href="other">Other</a>
					</div>
				</div>

				<div class="dropdown">
					<button class="dropbtn">Time</button>
					<div class="dropdown-content">
						<a href="q-hour">15min</a> <a href="h-hour">30min</a> <a href="o-hour">1Hour</a>
						<a href="over">Over</a>
					</div>
				</div>

				<div class="dropdown">
					<button class="dropbtn" id="sort">Sort</button>
					<div class="dropdown-content" id="sort-sub">
						<a href="solo">Solo</a> <a href="family">Family</a> <a href="dessert">Dessert</a>
						<a href="healthy">Healthy</a> <a href="etc">etc</a>
					</div>
				</div>
			</div>
			<!-- Navbar - dropdown End -->
			<div id="navbar-search">
				<form action="search">
					<p>
						<input type="text" id="keyword" name="keyword"
							placeholder="검색어를 입력하세요"> <input
							type="image" src="resources/image/search.jpg"
							style="width: 40px; vertical-align: middle; margin: 2px; padding: 0; float: right;"
							name="Submit" value="Submit">
					</p>
				</form>
			</div>
			<div id="navbar-top">
				<a href="#"><img src="resources/image/to_top.png" width=50px; align="center";></a>
			</div>
			<div id="navbar-help">
				<a href="help">HELP</a>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	var naver_id_login = new naver_id_login("xutQP3xAJRfccCu5hhFo",
			"http://192.168.0.139:8088/final/callback");
	/* var naver_id_login = new naver_id_login("xutQP3xAJRfccCu5hhFo",
			"http://localhost:8088/web/callback"); */ 
	var state = naver_id_login.getUniqState();

	naver_id_login.setButton("green", 3, 40);
	naver_id_login.setDomain("http://192.168.0.139:8088/final/main");
	//naver_id_login.setDomain("http://localhost:8088/web/navbar");
	naver_id_login.setState(state);
	naver_id_login.init_naver_id_login();
</script>


</html>