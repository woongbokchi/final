<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mycipe - MyPage</title>
<style>
	#body-content{
		margin:0;
		margin-left:240px;
		padding:0;
	}
	
	#head-title{
		height:80px;
		font-size: 48px;
		font-weight:bold;
		color:#FFA07A;
		text-align:center;
		padding-bottom:16px;
		vertical-align:center;
		background-color:#3CB371;
		margin:10px;
	}
	
	#head-move{
		height:59px;
		text-align:center;
		margin:10px;
		background-color:#2F4F4F;
		padding:10px;
	}
	
	#head-move a{
		text-decoration:none;
		font-size:26px;
		padding:10px;
		color:#F0FFFF;
		border:1px solid white;
		margin:0px 5px 0px 5px;
		font-family:'DOSGothic';
	}
	
	#head-move a:hover{
		color:#FFB6C1;
	}
	
	@font-face{ 
		font-family: 'DOSGothic'; 
		src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_eight@1.0/DOSGothic.woff') format('woff'); 
		font-weight: normal; 
		font-style: normal; 
	}	
</style>
</head>
<body>
	<jsp:include page="../navbar.jsp"></jsp:include>
	<div id="body-content">
		<div id="head-title">My Page</div>
		<div id="head-move">
			<a href="myPage">My Profile</a>
			<a href="myPost">My Post</a>
			<a href="bookmark">BookMark</a>
			<a href="help">My QnA</a>
		</div>
	</div>
</body>
</html>