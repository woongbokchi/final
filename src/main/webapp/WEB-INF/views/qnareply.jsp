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

	#tbl{
		width:1400px;
		border:3px double black;
		background-color:white;
		margin-left:auto;
		margin-right:auto;
	}

	.QnAreno{
		width:100px;
	}
	
	.QnAnick{
		width:150px;
		font-weight:bold;
	}
	
	.QnAcontent{
		width:1070px;
		text-align:left;
		padding-left:10px;
	}
	
	.rdelete{
		width:100%;
	}

</style>
</head>
<body>
   <table id = "tbl"  border='1px solid black' width="1400"></table>
	<br>
   <div style="border: 3px double black; width: 1400px; padding: 5px; text-align: center; margin-left: auto; margin-right: auto; background-color:white;">
      <form name="rform" action="rinsert" method="get">
         <input type="hidden" name="QnAno" value="${param.QnAno}">
         <input type="hidden" name="wuno" value="${wuno}">
         <input type="hidden" name="unick" readonly size="20" maxlength="20" value="${lo.unick}">
         <textarea name="QnAcontent" class="QnAcontent1" style="resize: none;" rows="1" cols="190" maxlength="150" placeholder="댓글을 달아주세요."></textarea>
         <input type = "button" value = " 등록 " id="rinsert">
      </form>
   </div>
   
</body>
<script>
   var QnAno = ${param.QnAno};
   var wuno = ${wuno};
   var uauth = ${lo.uauth};
   
   
   getlist();
   
   $("#rinsert").click(function() {
      var QnAcontent = $(".QnAcontent1").val();
     var unick = "${lo.unick}";
   $.ajax({
      url : "qnarinsert",
      type : "get",
      data : {"QnAno":QnAno,"uno":wuno,"unick":unick,"QnAcontent":QnAcontent},
      success : function() {
         alert("댓글이 등록되었습니다");
         getlist();
      }
      });
   });
   
   $("#tbl").on("click","tr .rdelete", function() {
      var row = $(this).parent().parent();
      var QnAreno = row.find(".QnAreno").html();
 
      if(confirm("삭제 하시겠습니까?")) {
         $.ajax({
            url : "qnardelete",
            type : "get",
            contentType:false, 
            data : {"QnAreno":QnAreno},
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
      url : "qnareply",
      type : "get",
      dataType : "json",
      data : {"QnAno":QnAno},
      success : function(data) {
         var str="";
         $(data.rlist).each(function(){
            str +="<tr>";
            str +="<td class='QnAreno'>" + this.qnAreno + "</td>";
            str +="<td class-'QnAnick'>" + this.unick + "</td>";
            str +="<td class='QnAcontent'>" + this.qnAcontent + "</td>";
            if(uauth == 1){
            str += "<td><button class='rdelete'>삭제</button></td>";
         }else if(this.uno == wuno){
            str += "<td><button class='rdelete'>삭제</button></td>";         
   
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