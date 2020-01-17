<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>desert</title>
</head>
<body>
   <jsp:include page="../../navbar.jsp"></jsp:include>
   <div id="body-field">
      <img src="resources/image/head-title/dessert.jpg" style = "margin:auto; width:1625px; height:120px;">
      <div id="head-move">
         <a href="solo">Solo</a>
         <a href="family">Family</a>
         <a href="dessert">Dessert</a>
         <a href="healthy">Healthy</a>
         <a href="etc">etc</a>
      </div>
      
      <select id="orderType" style="width: 120px; height: 30px; border: 0; padding: 0;">
               <option value="bwdate">날짜순</option>
               <option value="blike">추천순</option>
     </select>
     
      <div id="body-content"></div>
      <script id="temp" type="text/x-handlebars-template">
	{{#each dlist}}
         <div style="width:1300px; margin:0px auto;">
            <table class="body-table" style="width:1200px; margin:10px auto; background-color:white;">
               <tr height=40px>
                  <td rowspan=3 width=400px><img src = display?fileName={{thumbnail}} style="margin:10px; width:320px; height:180px;" onClick = "location.href = 'read?bno={{bno}}'"></td>
                  <th colspan=3 width=680px>{{btitle}}</th>
               </tr>
               <tr height=20px;>
                  <td class="unick" width=380px>{{unick}}</td>
                  <td class="bwdate" width=200px>{{bwdate}}</td>
                  <td class="blike">추천수 : {{blike}}</td>
               </tr>
               <tr>
                  <td colspan=3><textarea rows=4 cols=108 readonly class="bingredient" style="resize:none;">{{bingredient}}</textarea></td>
               </tr>
            </table>
         </div>
	{{/each}}
      </script>
      
      
      </div>
      <div id="more-btn-back">
         <div id="more-btn">
             <div id="pagination" style="text-align:center;"></div>
         </div>
      </div>
  

   
</body>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>   <%--  jquery를 사용하기위해 라이브러리를 추가해줘야함. --%>	
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script>
var orderType = $("#orderType").val();

var page = 1;


$("#orderType").on("change", function(){
	   orderType = $("#orderType").val();
	   page=1;
	   getlist();
	});

getlist();

$("#pagination").on("click", "a", function(event) {
	event.preventDefault();
	page = $(this).attr("href");
	getlist();
});


function getlist() {
	$.ajax({
		type : "get",
		url : "dessertJSON",
		data : {"page":page, "orderType":orderType},
		success : function(data) {
			var temp = Handlebars.compile($("#temp").html());
			$("#body-content").html(temp(data));
			var str = "";
			if (data.pm.prev) {
				str += "<a href='" + (data.pm.startPage - 1) + "'>◁ </a>";
			}

			for (var i = data.pm.startPage; i <= data.pm.endPage; i++) {

				str += "<a href='" + i + "'>[" + i + "]</a>";

			}

			if (data.pm.next) {
				str += "<a href='" + (data.pm.endPage + 1) + "'>▷</a>";
			}
			$("#pagination").html(str);
			$("#total").html(data.pm.totalCount);
		}
	});
}

</script>
</html>