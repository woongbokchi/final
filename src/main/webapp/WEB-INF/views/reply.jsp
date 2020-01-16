<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<title>Insert title here</title>
<style>
	.rno{
		display: none;
	}
	
	#tbl{
		width:1400px;
		border:3px double black;
		background-color:white;
		margin-left:auto;
		margin-right:auto;
	}
	
	#replydiv{
		border:3px double black;
		width: 1400px;
		margin-left:auto;
		margin-right:auto;
		text-align:center;
		padding:5px;
		background-color:white;
	}
	
	.replynick{
		width:150px;
		text-align:center;
		font-weight:bold;
	}
	
	.replycontent{
		width:1020px;
		padding-left:10px;
	}
	
	.replywdate{
		width:150px;
		text-align:center;
	}
	
	.rdelete{
		width:100%;
		background-color:rgba(0, 0, 0, 0);
		border:0;
		text-align:center;
		margin-left:auto;
		margin-right:auto;
	}
	
	.replydel{
		text-align:center;
		align:center;
	}
	
	
</style>
</head>
<body>
   <table id ="tbl" border='1px solid black'></table>
	<br>
   <div id="replydiv">
      <form name="rform" action="rinsert" method="get">
         <input type="hidden" name="bno" value="${vo.bno}">
         <input type="hidden" name="uno" value="${wuno}">
         <input type="hidden" name="unick" readonly size="20" maxlength="20" value="${lo.unick}">
         <textarea name="rcontent" class="rcontent" style="resize: none; top:0px; left:0px;" rows="1" cols="190" 
            maxlength="150" placeholder="댓글을 입력해주세요."></textarea>
         <img src="resources/image/check1.png" style="width:30px;" id="rinsert">
      </form>
   </div>
   
</body>
<script>
   var bno = ${vo.bno};
   var wuno = ${wuno};
   var uauth = ${lo.uauth};
   getlist();
   
   $("#rinsert").click(function() {
      var rcontent = $(".rcontent").val();
   //   var unick = $(".unick").val();
      var unick = "${lo.unick}";
   $.ajax({
      url : "rinsert",
      type : "get",
      data : {"bno":bno,"uno":wuno,"unick":unick,"rcontent":rcontent},
      success : function() {
         alert("댓글이 등록되었습니다");
         getlist();
      }
      });
   });
   
   $("#tbl").on("click","tr .rdelete", function() {
      var row = $(this).parent().parent();
      var rno = row.find(".rno").html();
      if(confirm("삭제 하시겠습니까?")) {
         $.ajax({
            url : "rdelete",
            type : "get",
            contentType:false,
            data : {"rno":rno},
            success : function() {
               alert("댓글이 삭제되었습니다");
               getlist();
               return;
            }
            
            });
         } else {
         return false;
      }
      });
      
   
   
   function getlist() {
   $.ajax({
      url : "reply",
      type : "get",
      dataType : "json",
      data : {"bno":bno},
      success : function(data) {
         var str="";
         $(data.rlist).each(function(){
            str +="<tr style='height:50px;'>";
            str +="<td class='rno'>" + this.rno + "</td>";
            str +="<td class='replynick'>" + this.unick + "</td>";
            str +="<td class='replycontent'>" + this.rcontent + "</td>";
            str +="<td class='replywdate'>" + this.rwdate + "</td>";
            if(uauth == 1){
               str += "<td class='replydel'><img src='resources/image/del-btn.png' style='width:30px; margin:0px auto;' class='rdelete'></td>";
            }else if(this.uno == wuno){
               str += "<td class='replydel'><img src='resources/image/del-btn.png' style='width:30px;' class='rdelete'></td>";         
      
            }else{
               // str += "<td></td>";         
            }
            str +="</tr>";
         });
         $("#tbl").html(str);
      }
      });
   }
</script>




</html>