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
	<form action="">
		<div id="body-content">
			<img src="resources/image/head-title/mypage3.jpg" style = "margin:auto; width:1625px; height:120px;">
			<div id="head-move">
				<c:if test="${vo.uauth!=2}">
					<a href="myPage">My Profile</a>
					<a href="myPost">My Post</a>
					<a href="help">My QnA</a>
				</c:if>
				<c:if test="${vo.uauth==2}">
					<a href="help">My QnA</a>
				</c:if>
			</div>
			<div id="post-body">
				<div style="text-align: center;">
					<table border=1 id="helptbl"
						style="margin: 0px auto; background-color: white; border: 3px solid black;"></table>
					<script id="temp" type="text/x-handlebars-template">
							<tr height=50px;>
								<th width=650px style='background-color:black; color:white;'>제목</th>
								<th width=175px style='background-color:black; color:white;'>작성자</th>
								<th width=200px style='background-color:black; color:white;'>작성일</th>
							</tr>
							{{#each qlist}}
								<tr height=40px;>
									<td width=650px style='font-weight:bold;'><a href = "helpRead?QnAno={{qnAno}}&uno={{uno}}" style='text-decoration:none;'>{{qnAtitle}}</a></td>
									<td width=175px>{{unick}}</td>
									<td width=200px class="qnaDate">{{qnAdate}}</td>	
								</tr>
							{{/each}}
						</script>
					<!-- 페이지 작업 -->
					<div id="pagination"></div>
					<input type="button" value=" 문의글 작성 "
						onClick="location.href = 'helpWrite'" style="margin-top: 10px;" />
				</div>
			</div>
		</div>
	</form>
</body>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script>
	var page = 1;

	getqlist();

	$("#pagination").on("click", "a", function(event) {
		event.preventDefault();
		page = $(this).attr("href");
		getqlist();
	});

	function getqlist() {
		$.ajax({
			type : "get",
			url : "help.json",
			data : {
				"page" : page
			},
			success : function(data) {
				//alert("??????????");
				var temp = Handlebars.compile($("#temp").html());
				$("#helptbl").html(temp(data));

				var str = "";

				if (data.pm.prev) {
					str += "<a href='" + (data.pm.startPage - 1) + "'>◁</a>";
				}

				for (var i = data.pm.startPage; i <= data.pm.endPage; i++) {
					str += "<a href='" + i + "'>[" + i + "]</a>";
				}

				if (data.pm.next) {
					str += "<a href='" + (data.pm.endPage + 1) + "'>▷</a>";
				}

				$("#pagination").html(str);
			}
		});
	}
</script>
</html>