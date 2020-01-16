<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mycipe Main</title>
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<div
		style="position: relative; margin-left: 240px; padding: 10px; overflow-y: auto;">
		<div style="background-color: #F0FFF0; padding: 10px;">
			<div id="main-best-title" style="background-color: white;">Best Recipe</div>
				
			<div id="main-best-content"
				style="margin-top: 10px; height: 320px; display: flex;"></div>
			<script id="temp" type="text/x-handlebars-template"> 
				{{#each blist}} 
					<div class="main-best-item" style="background-color:#800000; position:relative; border:1px solid black; overflow:hidden;">
						<img src= display?fileName={{thumbnail}} width="420px;" height="300px;" onClick = "location.href = 'read?bno={{bno}}'">
						<div class="main-best-item-text">{{btitle}}</div>
					</div>
				{{/each}}
			</script>
		</div>
	</div>
	<hr>
	<div
		style="position: relative; margin-left: 240px; padding: 10px; overflow-y: auto; overflow: scroll;">
		<div
			style="width: 100%; padding: 10px; display: flex; border:3px double green;">
			<div style="width: 100%; display: inline-block; margin: 0px auto;" id="main-recipe-content"></div>
			<!-- 아래 box 5개 반복 -->
			<script id="temp1" type="text/x-handlebars-template">
				{{#each rlist}}
				<div style="background-color:#F0FFF0; width:20%; float:left; padding:10px; margin:auto;">
            		<div style="width:240px; height:360px; margin:0px auto; background-color:white; border:1px solid black">
            			 	<img src = display?fileName={{thumbnail}} style="margin:10px; width:220px; height:280px;" onClick = "location.href = 'read?bno={{bno}}'">
            			    <div style="padding:10px; background-color:#E6E6FA; height:50px; text-overflow:ellipsis; font-size:16px; font-weight:bold; overflow:hidden;">
             			 	{{btitle}}
             		 		</div>
              		</div>
				</div>	
				{{/each}}
			</script>
		</div>
	</div>
</body>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script>
	getbest();
	getlist();

	function getbest() {
		$.ajax({
			type : "get",
			url : "best.json",
			success : function(data) {
				var temp = Handlebars.compile($("#temp").html());
				$("#main-best-content").html(temp(data));
			}
		});
	}

	function getlist() {
		$.ajax({
			type : "get",
			url : "recipe.json",
			//data:{"page":page, "size":"10"},
			success : function(data) {
				var temp = Handlebars.compile($("#temp1").html());
				$("#main-recipe-content").append(temp(data));
				
				alert(data.meta.total_count);
				alert(data);
				//$("#total").html(data.meta.total_count);
			}
		});
	}

	
</script>
</html>