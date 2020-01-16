<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>문의 게시글</title>
<link href="resources/style.css" rel="stylesheet">
<script src = "http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<style>
   #body-content{
      margin:0px;
      margin-left:240px;
      padding:0px;
   }
   
   #head-title{
      height:80px;
      font-size: 48px;
      font-weight:bold;
      text-align:center;
      padding-bottom:16px;
      vertical-align:center;
      background-color: #FAFAD2;
	color: #32CD32;
      margin:10px;
   }
   
   #board-body{
      margin:10px;
      text-align:center;
      background-color:#3CB371;
   }
   
   #board-hidden{
      margin:10px auto;
      background-color:#7B68EE;
   }
   
   #content-image{
      margin:10px;
      background-color:white;
   }
   
   #content-title{
      font-size:24px;
      margin:10px;
   }
   
   #content-content{
      font-size:16px;
      margin:10px;
   }
   
   #btn-box{
      padding:10px;
   }
   
</style>
</head>
<body>
   <jsp:include page="navbar.jsp"/>
   
   <div	id="body-content" style="position: relative; overflow-y: auto;"> 
      <div id="head-title">문의 게시글 쓰기</div>
		<div id="board-body" style="background-color: #DCDCDC; padding: 20px">
			<form name="frm" action="helpWritePost" method="post" enctype="multipart/form-data">
				<table border='3px solid black' width=1300px
					style="margin: auto; background-color: white">
					<tr style="display: none;">
						<td><input type="text" name="uno" value="${vo.uno}"></td>
						<td><input type="text" name="unick" value="${vo.unick}"></td>
						<td><input type="file" id="file" name="file" accept="image/*"></td>
					</tr>
					<tr style="text-align:center;">
						<td height=320px; style="text-align: center; background-color:#DCDCDC;"><img
							src="resources/image/plus.jpg"
							style="margin: auto; padding: 0; width:480px; height:320px;" id="image"></td>
					</tr>
					<tr>
						<td><input type="text"
							style="border: 0; height: 60px; font-size: 24px; width:100%; padding: 1px;"
							name="QnAtitle" placeholder="제목을 입력해주세요."></td>
					</tr>
					<tr>
						<td height=300px;><textarea cols="200" rows="19"
								name="QnAcontent" placeholder="문의 내용을 입력하십시오." style="padding:10px; resize:none"></textarea></td>
					</tr>
				</table>
			<div id="btn-box">
                  <input type="submit" id='save-btn' value="등록하기" class="btn btn-success">
                  <input type="button" id='list-btn' value="목록으로" class="btn btn-primary" onClick="location.href='help'">
            </div>
			</form>
		</div>
	</div>
</body>
<script>

   $("#image").on("click", function(){
      $("#file").click();
   });
   
   //이미지 미리보기
   $("#file").on("change", function(e) {
      var reader = new FileReader();
      reader.onload = function(e) {
         $("#image").attr("src", e.target.result);
      }
      reader.readAsDataURL(this.files[0]);
   });
</script>
</html>