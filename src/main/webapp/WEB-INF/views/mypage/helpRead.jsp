<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>문의 게시글</title>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<style>
#darken-background {
	position: absolute;
	top: 0px;
	left: 0px;
	right: 0px;
	height: 100%;
	width: 100%;
	background: rgba(0, 0, 0, 0.5);
	z-index: 1000;
	overflow-y: scroll;
	display: none;
}

#lightbox {
	width: 800px;
	margin: 20px auto;
	padding: 15px;
	border: 1px solid #333333;
	border-radius: 5px;
	background: white;
	box-shadow: 0px 5px 5px rgba(34, 25, 25, 0.4);
	text-align: center;
}

#body-content {
	margin: 0px;
	margin-left: 240px;
	padding: 0px;
}

#head-title {
	height: 80px;
	font-size: 48px;
	font-weight: bold;
	text-align: center;
	padding-bottom: 16px;
	vertical-align: center;
	background-color: #FAFAD2;
	color: #32CD32;
	margin: 10px;
}

#board-body {
	margin: 10px;
	text-align: center;
	background-color: #DCDCDC;
	padding:10px;
}

#board-hidden {
	margin: 10px auto;
	background-color: #7B68EE;
}

#content-image {
	margin: 10px;
	background-color: white;
}

#content-title {
	font-size: 24px;
	margin: 10px;
}

#content-content {
	font-size: 16px;
	margin: 10px;
}

#btn-box {
	padding: 10px;
}
</style>

</head>
<body>
	<jsp:include page="../navbar.jsp" />
	<div id="body-content">
		<div id="head-title">문의 게시글 읽기</div>
		<div id="board-body">
			<form name="frm" action="helpWritePost" method="post" enctype="multipart/form-data">
				<table border='3px solid black' width=1300px style="margin:auto; background-color:white">
					<tr style="display: none;">
						<td><input type="text" name="uno" value="${param.uno}"></td>
					</tr>
					<tr style="text-align:center;">
						<td height=320px; style="text-align: center; background-color:#DCDCDC;">
							<img src="display?fileName=${read.image}" style="margin: auto; padding: 0; width:480px; height:320px;" id="image">
						</td>
					</tr>
					<tr>
						<td>
							<input type="text" style="border: 0; height: 60px; font-size: 24px; width:100%; padding: 1px;" name="QnAtitle" value="${read.qnAtitle}" readonly>
						</td>
					</tr>
					<tr>
						<td height=300px;>
							<textarea cols="200" rows="19" name="QnAcontent"  style="padding:10px; resize:none; font-size:16px;" readonly>${read.qnAcontent}</textarea>
						</td>
					</tr>
				</table>
			</form>
			<br>
			<jsp:include page="../qnareply.jsp"/>
			
			<div id="btn-box">
                  <input type="button" id='list-btn' value="목록으로" class="btn btn-primary" onClick="location.href='help'">
        	</div>
			
		</div>
	</div>
	
	
	
	<!-- 라이트박스 -->
	<div id="darken-background">
		<div id="lightbox">
			<img src="display?fileName=${read.image}" width=780px> <br> 
			<a id="btnClose" href="#">닫기</a>
		</div>
	</div>
</body>
<script>
	$("#image").on("click", function(){
		$("#darken-background").show();
	})
	
	$("#btnClose").on("click", function(){
		$("#darken-background").hide();
	})
</script>
</html>