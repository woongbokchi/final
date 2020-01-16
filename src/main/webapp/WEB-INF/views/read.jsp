<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctx = request.getContextPath(); //콘텍스트명 얻어오기.
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mycipe - Read</title>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<style>
body {
	overflow: scroll;
}
</style>
<link rel="stylesheet" type="text/css" href="resources/css/navbar.css">
</head>
<body>

	<jsp:include page="navbar.jsp" />

	<div
		style="position: relative; margin-left: 240px; padding: 10px; overflow-y: auto;">
		<div style="background-color: #DCDCDC; padding: 20px;">
			<input type="text" name="bno" value="${param.bno}"
				style="display: none; visibility: hidden">
			<form name="frm" action="read" method="post"
				enctype="multipart/form-data">
				<table border='3px solid black' width=1300px
					style="margin: auto; background-color: white; text-align: center;">
					<tr style="display: none;">
						<td><input type="text" name="uno" value="${vo.uno}"></td>
						<td><input type="text" name="bno" id="searchbno" value="${vo.bno}"></td>
						<td><input type="file" id="imgupload"
							style="display: none; visibility: hidden" accept="image/*" /></td>
					</tr>
					<tr>
						<td rowspan=3 width=300px height=300px;><img
							src="display?fileName=${vo.thumbnail}"
							style="margin: auto; padding: 0; width: 300px; height: 300px;"
							id="thumbnail"></td>
						<th width=55px height=50px>제목</th>
						<td colspan=4><input type="text"
							style="border: 0; height: 60px; font-size: 24px; width: 400px; padding: 1px;"
							name="btitle" value="${vo.btitle}" readonly></td>
						<td colspan=2>추천수 : <span id = "blike"></span></td>
					</tr>
					<tr>
						<th width=50px height=50px>분류</th>
						<th width=50px>국가</th>
						<td width=233px><select
							style="width: 273px; height: 40px; border: 0; font-size: 18px;"
							name="bnational" disabled>
								<option value="korean"
									<c:out value="${vo.bnational=='korean'? 'selected':''}"/>>Korean</option>
								<option value="western"
									<c:out value="${vo.bnational=='western'? 'selected':''}"/>>Western</option>
								<option value="chinese"
									<c:out value="${vo.bnational=='chinese'? 'selected':''}"/>>Chinese</option>
								<option value="japanese"
									<c:out value="${vo.bnational=='japanese'? 'selected':''}"/>>Japanese</option>
								<option value="other"
									<c:out value="${vo.bnational=='other'? 'selected':''}"/>>Other</option>
						</select></td>
						<th width=50px>시간</th>
						<td width=233px><select
							style="width: 273px; height: 40px; border: 0; font-size: 18px;"
							name="btime" disabled>
								<option value="q-hour"
									<c:out value="${vo.btime=='q-hour'? 'selected':''}"/>>15min</option>
								<option value="h-hour"
									<c:out value="${vo.btime=='h-hour'? 'selected':''}"/>>30min</option>
								<option value="o-hour"
									<c:out value="${vo.btime=='o-hour'? 'selected':''}"/>>1Hour</option>
								<option value="over"
									<c:out value="${vo.btime=='over'? 'selected':''}"/>>Over</option>
						</select></td>
						<th width=50px>유형</th>
						<td width=233px><select
							style="width: 273px; height: 40px; border: 0; font-size: 18px;"
							name="bsort" disabled>
								<option value="solo"
									<c:out value="${vo.bsort=='solo'? 'selected':''}"/>>Solo</option>
								<option value="family"
									<c:out value="${vo.bsort=='family'? 'selected':''}"/>>Family</option>
								<option value="healthy"
									<c:out value="${vo.bsort=='healthy'? 'selected':''}"/>>Healthy</option>
								<option value="dessert"
									<c:out value="${vo.bsort=='dessert'? 'selected':''}"/>>Dessert</option>
						</select></td>
					</tr>
					<tr>
						<th height=220px>재료</th>
						<td colspan=6><textarea
								style="width: 100%; height: 220px; border: 0; resize: none;"
								name="bingredient" readonly>${vo.bingredient}</textarea></td>
					</tr>
					<tr>
						<td colspan=8 height=300px;><textarea cols="200" rows="19"
								name="bcontent" style="resize: none;" readonly>${vo.bcontent}</textarea>
						</td>
					</tr>
				</table>
				<br>
			</form>
			
			<jsp:include page="reply.jsp"></jsp:include>
			<br>
			<!-- <input type="submit" id="read-submit" style="display: none;"> -->
			
			<div style="width: 280px; margin:0px auto; text-align:center;">
			
				<c:if test="${uno != vo.uno}">
				<a class="btn-Blikeup" id="bu" href="#">
					<img src="resources/image/blikedown.png" alt="likeup" style="width: 40px; marign-left:auto; margin-right: 10px;">
				</a> 
				<a class="btn-Blikedown" id="bd" href="#" style="display: none"> 
					<img src="resources/image/blikeup.png" alt="likedown" style="width: 40px; margin-right: 10px;">
				</a>
				<a class="btn-Close" href="#">
					<img src="resources/image/close1.png" alt="Close" style="width: 40px; margin-left: 10px; margin-right:auto;" onClick="history.back();">
				</a>
				</c:if>
				
				<c:if test="${uno == vo.uno}">
				<a class="btn-Update" href="#">
					<img src="resources/image/writeupdate.png" alt="Update"	style="width: 40px; marign-left:auto; margin-right: 10px;"
						onClick="location.href='update?bno=${vo.bno}'">
				</a>
				
				<a class="btn-Delete" href="#">
					<img src="resources/image/delete.png" alt="Delete" style="width: 40px;">
				</a>
					
				<a class="btn-Close" href="#">
					<img src="resources/image/close1.png" alt="Close" style="width: 40px; margin-left: 10px; margin-right:auto;"
						onClick="history.back();">
				</a>
				</c:if>
			</div>
		</div>
	</div>
</body>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<script>
	var bno = $("#searchbno").val();

	var blike = ${vo.blike};
	
	$("#blike").html(blike);
	
	$(".btn-Delete").click(function(){
		if(!confirm("삭제하시겠습니까?")) return;
		frm.action = "delete";
		frm.submit();
	})
	
	$(".btn-Blikeup").click(function() {
		$.ajax({
			type : "get",
			url : "blikeupJSON",
			data : {
				"bno" : bno
			},
			success : function(data) {
				blike = blike + 1;
				$("#blike").html(blike);
				var x = document.getElementById("bu");
				x.style.display = "none";
				var y = document.getElementById("bd");
				y.style.display = "inline";
			}
		});
	});

	$(".btn-Blikedown").click(function() {
		$.ajax({
			type : "get",
			url : "blikedownJSON",
			data : {
				"bno" : bno
			},
			success : function(data) {
				blike = blike - 1;
				$("#blike").html(blike);
				var b = document.getElementById("bu");
				b.style.display = "inline";
				var a = document.getElementById("bd");
				a.style.display = "none";

			}
		});
	});
</script>
</html>