
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ctx = request.getContextPath(); //콘텍스트명 얻어오기.
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mycipe - Write</title>

<style>
body {
	overflow: scroll;
}

textarea {
	resize: none;
}
</style>
</head>
<body>

	<jsp:include page="navbar.jsp"></jsp:include>

	<div
		style="position: relative; margin-left: 240px; padding: 10px; overflow-y: auto;">
		<div style="background-color: #DCDCDC; padding: 20px;">
			<form name="frm" action="writer" method="post"
				enctype="multipart/form-data">
				<table border='3px solid black' width=1300px
					style="margin: auto; background-color: white">
					<tr style="display: none;">
						<td><input type="text" name="uno" value="${vo.uno}"></td>
						<td><input type="text" name="unick" value="${vo.unick}"></td>
						<td><input type="file" id="imgupload" name="file" style="visibility:hidden" accept="image/*"></td>
					</tr>
					<tr>
						<td rowspan=3 width=300px height=300px; style="text-align: center;"><img
							src="resources/image/plus.jpg"
							style="margin: auto; padding: 0; width:300px; height:300px;" id="thumbnail"></td>
						<th width=55px height=50px>제목</th>
						<td colspan=6><input type="text"
							style="border: 0; height: 60px; font-size: 24px; width: 900px; padding: 1px;"
							name="btitle" placeholder="제목을 입력해주세요."></td>
					</tr>
					<tr>
						<th width=50px heigth=50px>분류</th>
						<th width=50px>국가</th>
						<td width=233px><select
							style="width: 273px; height: 40px; border: 0; font-size: 18px;"
							name="bnational">
								<option value="korean">Korean</option>
								<option value="western">Western</option>
								<option value="chinese">Chinese</option>
								<option value="japanese">Japanese</option>
								<option value="other">Other</option>
						</select></td>
						<th width=50px>시간</th>
						<td width=233px><select
							style="width: 273px; height: 40px; border: 0; font-size: 18px;"
							name="btime">
								<option value="q-hour">15min</option>
								<option value="h-hour">30min</option>
								<option value="o-hour">1Hour</option>
								<option value="over">Over</option>
						</select></td>
						<th width=50px>유형</th>
						<td width=233px><select
							style="width: 273px; height: 40px; border: 0; font-size: 18px;"
							name="bsort">
								<option value="solo">Solo</option>
								<option value="family">Family</option>
								<option value="healthy">Healthy</option>
								<option value="dessert">Dessert</option>
								<option value="etc">etc</option>
						</select></td>
					</tr>
					<tr>
						<th height=220px>재료</th>
						<td colspan=6><textarea
								style="width: 100%; height: 220px; border: 0;"
								placeholder="재료를 이곳에 적어주십시오." name="bingredient"></textarea></td>
					</tr>
					<tr>
						<td colspan=8 height=300px;><textarea cols="200" rows="19"
								name="bcontent"></textarea></td>
					</tr>
				</table>
				<br> <input type="submit" id="write-submit"
					style="display: none;">
			</form>
			<div style="width: 120px; margin: 0px auto;">
				<a class="btn-Save" href="#"><img
					src="resources/image/checkbtn.png" alt="Save"
					style="width: 40px; margin-right: 10px;"></a> <a
					class="btn-Close" href="#"><img
					src="resources/image/closebtn.png" alt="Close"
					style="width: 40px; margin-left: 10px;"
					onClick="location.href='main'"></a>
			</div>
		</div>
	</div>
</body>

<script>
	$(".btn-Save").click(function() {
		$("#write-submit").trigger("click")
	});

	$('#thumbnail').click(function() {
		$('#imgupload').trigger('click');
	});

	//이미지 미리보기
	$("#imgupload").on("change", function(e) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$("#thumbnail").attr("src", e.target.result);
		}
		reader.readAsDataURL(this.files[0]);
	});
</script>

</html>