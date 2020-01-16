<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();    //콘텍스트명 얻어오기.
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mycipe - update</title>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/navbar.css">
<style>
	body{
		overflow:scroll;
	}
</style>
</head>
<body>

	<jsp:include page="navbar.jsp"/>
	
	<div style="position:relative; margin-left:240px; padding:10px; overflow-y:auto;">
			<div style="background-color:#DCDCDC; padding:20px;">
				<form name="frm" action="mypostupdate" method="post" enctype="multipart/form-data">
					<table border='3px solid black' width=1300px style="margin:auto; background-color:white; text-align:center;">
						<tr style="display:none;">
						    <td><input type="text" name="bno" value="${vo.bno}"></td>
							<td><input type="text" name="uno" value="${vo.uno}"></td>
							<td><input type="text" name="thumbnail" value="${vo.thumbnail}"></td>
							<td><input type="file" name="file" id="file" style="display:none; visibility: hidden" accept="image/*"/></td> 
						</tr>
						<tr>
							<td rowspan=3 width=300px height=300px;><img src="display?fileName=${vo.thumbnail}" style="margin:auto; padding:0; width:300px; height: 300px;" id="thumbnail"></td>
							<th width=55px height=50px>제목</th>
							<td colspan=6><input type="text" style="border:0; height:60px; font-size:24px; width:900px; padding:1px;" name="btitle" value="${vo.btitle}"></td>
						</tr>
						<tr>
							<th width=50px height=50px>게시판</th>
							<th width=50px>국가</th>
							<td width=233px>
								<select style="width:273px; height:40px; border:0; font-size:18px;" name="bnational" class="bnational" onchange="change();">
									<option value="korean" <c:out value="${vo.bnational=='korean'?'selected':''}"/>>Korean</option>
									<option value="western" <c:out value="${vo.bnational=='western'?'selected':''}"/>>Western</option>
									<option value="chinese" <c:out value="${vo.bnational=='chinese'?'selected':''}"/>>Chinese</option>
									<option value="japanese" <c:out value="${vo.bnational=='japanese'?'selected':''}"/>>Japanese</option>
									<option value="other" <c:out value="${vo.bnational=='other'?'selected':''}"/>>Other</option>
								</select>
							</td>
							<th width=50px>시간</th>
							<td width=233px>
								<select style="width:273px; height:40px; border:0; font-size:18px;" name="btime" id="btime">
									<option value="q-hour" <c:out value="${vo.btime=='q-hour'?'selected':''}"/>>15min</option>
									<option value="h-hour" <c:out value="${vo.btime=='h-hour'?'selected':''}"/>>30min</option>
									<option value="o-hour" <c:out value="${vo.btime=='o-hour'?'selected':''}"/>>1Hour</option>
									<option value="over" <c:out value="${vo.btime=='over'?'selected':''}"/>>Over</option>
								</select>
							</td>
							<th width=50px>유형</th>
							<td width=233px>
								<select style="width:273px; height:40px; border:0; font-size:18px;" name="bsort" id="bsort">
									<option value="solo" <c:out value="${vo.bsort=='solo'?'selected':''}"/>>Solo</option>
									<option value="family" <c:out value="${vo.bsort=='family'?'selected':''}"/>>Family</option>
									<option value="healthy" <c:out value="${vo.bsort=='healthy'?'selected':''}"/>>Healthy</option>
									<option value="dessert" <c:out value="${vo.bsort=='dessert'?'selected':''}"/>>Dessert</option>
								</select>
							</td>
						</tr>
						<tr>
							<th height=220px>재료</th>
							<td colspan=6><textarea style="width:100%; height:220px; border:0; resize:none;" name="bingredient">${vo.bingredient}</textarea></td>
						</tr>
						<tr>
							<td colspan=8 height=300px;>
								<textarea cols="200" rows="19" name="bcontent" style="resize:none;">${vo.bcontent}</textarea>
							</td>
						</tr>
					</table>
					<br>
					<input type="submit" id="update-submit" style="display:none;">
				</form>
				<div style="width:120px; margin:0px auto;">
					<a class="btn-update" href="#"><img src="resources/image/check1.png" alt="Save" style="width:40px; margin-right:10px;"></a>
					<a class="btn-Close" href="#"><img src="resources/image/close1.png" alt="Close" style="width:40px; margin-left:10px;" onClick="location.href='myPost'"></a>
				</div>
			</div>
		</div>
</body>

<script type="text/javascript">
   
	$(".btn-update").click(function(){
		if(!confirm("수정하시겠습니까?")) return;
		$("#update-submit").trigger("click")
	});
	
	$('#thumbnail').click(function() {
		$('#file').trigger('click');
	});
	

	
	
	//이미지 미리보기

	$("#file").on("change", function(e) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#thumbnail").attr("src", e.target.result);
		}

		reader.readAsDataURL(this.files[0]);

	});

</script>
</html>