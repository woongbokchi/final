<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="resources/css/manage.css">
<title>Management</title>
</head>
<body>
	<jsp:include page="../navbar.jsp"></jsp:include>
	<div id="body-content">
		<div id="head-title">Management</div>
		<div id="head-move">
			<a href="userlist">유저관리</a> <a href="help">문의글관리</a>
		</div>
		<div id="list-body">
			<div style="text-align: center;">
				<div style="width:1000px; margin:0px auto; background-color:#FFFAFA; color:black;">
					<p>
						선택된 회원을 일괄
						<button id="ulockadd" value="{{uno}}">경고</button>
						<button id="userblack" value="{{uno}}">차단</button>
						<button id="unblack" value="{{uno}}">차단해제</button>
						합니다.
					</p>
				<!-- 검색 form -->

				<select id="searchType"
					style="width: 120px; height: 30px; border: 0; padding: 0;">
					<option value="uno">아이디</option>
					<option value="unick">닉네임</option>
					<option value="ulock">경고횟수</option>
					<option value="uauth">ban</option>
				</select> 
				<input type="text" id="keyword1" style="height: 30px; width: 350px; border:1px solid black;"> 
				<input type="button" id="search" value="검  색"> <br> 
				
				<div>
                  <b>유저 수:<span id="mytotal"> </span>명</b>
                </div>
                  <br>
				</div>

				<table id="user" border=1
					style="width: 1000px; margin: 0px auto; background-color: white; border: 3px solid black;">
					<tr height=50px;>
						<td width=50px style="text-align: center;"><input
							type="checkbox" id="checkAll" name="box" onClick="check()"></td>
						<td width=150px>아이디</td>
						<td width=200px>닉네임</td>
						<td width=200px>이메일</td>
						<td width=200px>가입일</td>
						<td width=50px>경고</td>
						<td width=50px>Ban</td>
					</tr>
				</table>

				<table border=1 id="atbl"
					style="width: 1000px; margin: 0px auto; background-color: white; border: 3px solid black; border-top:0;"></table>
				<script id="temp" type="text/x-handlebars-template">
               {{#each list}}
                  <tr height=40px>
                     <td width=50px style="text-align:center;"><input type="checkbox" name="box"></td>
                     <td width=150px>{{uno}}</td>
                     <td width=200px>{{unick}}</td>
                     <td width=200px>{{uemail}}</td>
                     <td width=200px>{{uregdate}}</td>
                     <td width=50px>{{ulock}}</td>
                	 <td width=50px>{{uauth}}</td>
                  </tr>
               {{/each}}
            </script>
            <div id="pagination"></div>
			</div>
		</div>
	</div>
</body>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script>
	var total;
	var page = 1;
	var searchType = $("#searchType").val();
	var keyword1 = $("#keyword1").val();

	getlist();

	// 검색
	$("#search").on("click", function() {
		searchType = $("#searchType").val();
		keyword1 = $("#keyword1").val();
		/* alert(searchType);
		alert(ha); */
		page = 1;
		getlist();
	});

	$("#keyword1").keydown(function(key) {
		if (key.keyCode == 13) {
			keyword1 = $("#keyword1").val();
			getlist();
		}
	});

	$("#pagination").on("click", "a", function(event) {
		event.preventDefault();
		page = $(this).attr("href");
		getlist();
	});

	function getlist() {
		$.ajax({
			url : "userlist.json",
			type : "post",
			dataType : "json",
			data : {
				"page" : page,
				"searchType" : searchType,
				"keyword" : keyword1
			},
			success : function(data) {
				var temp = Handlebars.compile($("#temp").html());
				$("#atbl").html(temp(data));
				var str = "";

				if (data.pm.prev) {
					str += "<a href='" + (data.pm.startPage - 1) + "'> ◁ </a>";
				}

				for (var i = data.pm.startPage; i <= data.pm.endPage; i++) {

					str += "<a href='" + i + "'>[" + i + "]</a>";

				}

				if (data.pm.next) {
					str += "<a href='" + (data.pm.endPage + 1) + "'>▷</a>";
				}

				$("#pagination").html(str);
				$("#mytotal").html(data.pm.totalCount);
			}
		});
	}

	// checkbox All선택 및 해제
	function check() {
		if ($("#checkAll").is(':checked')) {
			$("input[name=box]:checkbox").prop("checked", true);
		} else {
			$("input[name=box]:checkbox").prop("checked", false);
		}
	}

	// 버튼 클릭시 체크된 경고
	$("#ulockadd").click(function() {
		var checkbox = $("input[name=box]:checked");
		// 체크된 체크박스 값을 가져온다
		checkbox.each(function(i) {
			// checkbox.parent() : checkbox의 부모는 <td>이다.
			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();

			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var uno = td.eq(1).text();

			$.ajax({
				type : "post",
				url : "ulockupdate",
				data : {
					"uno" : uno
				},
				success : function() {
					alert(uno + "경고");
					getlist();
				}

			});

		});

	});

	// 버튼 클릭시 체크된 유저 블랙
	$("#userblack").click(function() {
		var checkbox = $("input[name=box]:checked");
		// 체크된 체크박스 값을 가져온다
		checkbox.each(function(i) {
			// checkbox.parent() : checkbox의 부모는 <td>이다.
			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();

			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var uno = td.eq(1).text();

			$.ajax({
				type : "post",
				url : "userblack",
				data : {
					"uno" : uno
				},
				success : function() {
					alert(uno + "블랙");
					getlist();
				}

			});

		});

	});

	// 버튼 클릭시 체크된유저 블랙 해방
	$("#unblack").click(function() {
		var checkbox = $("input[name=box]:checked");
		// 체크된 체크박스 값을 가져온다
		checkbox.each(function(i) {
			// checkbox.parent() : checkbox의 부모는 <td>이다.
			// checkbox.parent().parent() : <td>의 부모이므로 <tr>이다.
			var tr = checkbox.parent().parent().eq(i);
			var td = tr.children();

			// td.eq(0)은 체크박스 이므로  td.eq(1)의 값부터 가져온다.
			var uno = td.eq(1).text();

			$.ajax({
				type : "post",
				url : "unblack",
				data : {
					"uno" : uno
				},
				success : function() {
					alert(uno + "해제");
					getlist();
				}

			});

		});

	});
</script>

</html>