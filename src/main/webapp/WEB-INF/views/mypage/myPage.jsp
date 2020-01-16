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
<style>
   
   table{
      border:1px solid black;
      border-collapse:collapse;
   }
   
   .profile-table{
      width:1000px;
      border:3px double black;
      margin:0px auto;
      background-color:white;
   }
   
   .profile-table th{
      text-align:center;
      font-size:26px;
   }
   
   .profile-table td{
      padding:10px;
   }
   
   .profile-btn{
      width:1000px;
      margin:10px auto;
      text-align:center;
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
			<a href="help">My QnA</a>			
		</div>
		<div id="profile-body" style="text-align:center;">
			<table class="profile-table" border=1>
				<tr>
					<td rowspan=4 style="width:300px; height:300px; padding:10px;"><img src="display?fileName=${vo.profileimage}" width="280px;" height="280px;"/></td>
					<th width=150px style="font-size:30px; height:40px;">닉네임</th>
					<td>${vo.unick}</td>
				</tr>
				<tr>
					<th style="font-size:30px; height:40px;">이메일</th>
					<td>${vo.uemail}</td>
				</tr>
				<tr>
					<th style="font-size:30px; height:40px;">가입일</th>
					<td><f:formatDate value="${vo.uregdate}" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<th>자기소개</th>
					<td>${vo.uint}</td>
				</tr>
			</table>
			<div class="profile-btn">
				<button class="btn-update">수정하기</button>
			</div>
		</div>
	</div>
	
	<div id="profile-back">
		<div id="profile-box">
			<form name="frm" action = "myupdate" method = "post" enctype="multipart/form-data">
				<input type = "file" name = "file" id = "file" accept = "image/*" style="visibility:hidden;">
				<input type="hidden" name="profileimage" value="${vo.profileimage}"/>
				<table class="profile-table" border=1 style="margin:30px auto;">
					<tr>
						<td rowspan=4 style="width:300px; height:300px; padding:10px;"><img id = "profileimage" src="display?fileName=${vo.profileimage}" name = "profileimage" width="280px;" height="280px;"></td>
						<th width=150px>닉네임</th>
						<td><input type="text" style="height:30px; width:100%; font-size:22px; border:0;" name = "unick" value = "${vo.unick}"></td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><input type="text" readonly="readonly" style="height:30px; width:100%; font-size:22px; border:0;" name = "uemail" value = "${vo.uemail}"></td>
					</tr>
					<tr>
						<th>가입일</th>
						<td><input type="text" readonly="readonly" style="height:30px; width:100%; font-size:22px; border:0; hint:regdate값" value = '<f:formatDate value="${vo.uregdate}"/>'></td>
					</tr>
					<tr>
						<th>자기소개</th>
						<td><textarea cols=70 rows=6 name = "uint">${vo.uint}</textarea></td>
					</tr>
				</table>
				<div class="profile-btn">
					<input type="submit" value="수정하기">
					<input type="reset" value="취소">
					<input type="button" value="닫기" class="btn-close">
				</div>
			</form>
		</div>
	</div>
</body>

<style>
	#profile-back{
		position:absolute;
		top:0;
		left:0;
		right:0;
		height:100%;
		width:100%;
		z-index:10;
		overflow-y:scroll;
		display:none;
		background:rgba(0, 0, 0, 0.5);
	}
	
	#profile-box{
		width:1200px;
		margin:20px auto;
		margin-top:280px;
		padding:10px;
		border: 1px solid #333333;
		border-radius:5px;
		background:white;
		box-shadow:0px 5px 5px rgba(34, 25, 25, 0.4);
		text-align:center;
	}
</style>

<script>

	$(".btn-update").on("click", function(){
		$("#profile-back").show();
	});
	
	$(".btn-close").on("click", function(){
		$("#profile-back").hide();
	});

	$("#profileimage").on("click", function(){
		$("#file").click();
	});
	
	$("#file").on("change", function(e){
		var reader = new FileReader();
		reader.onload = function(e){
			$("#profileimage").attr("src", e.target.result);
		}
		reader.readAsDataURL(this.files[0]);
	});
</script>

</html>