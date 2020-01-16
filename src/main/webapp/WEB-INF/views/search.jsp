<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>검색 결과</title>
</head>
<body>
	<jsp:include page="navbar.jsp"></jsp:include>
	<div id="body-field">
		<div id="head-title">Search Result</div>
		<div id="head-search">
			<form name="frm">
				<input type="text" name="keyword" id="search-key"
					value="${param.keyword}"> <input type="submit" value="검색"
					id="search-btn"> <input type="hidden" value="${pm.cri.page}"
					name="page"><span id="total" style = "color: white;">검색결과 : ${pm.totalCount}건</span>
			</form>

		</div>
		<div id="body-content">
			<div style="width: 1300px; margin: 0px auto;">
				<c:forEach var="map" items="${list}">
					<table class="body-table"
						style="width: 1200px; margin: 10px auto; background-color: white;">
						<tr height=40px>
							<td rowspan=3 width=400px style="text-align: center;"><img
								src="display?fileName=${map.thumbnail}" onClick = "location.href = 'read?bno=${map.bno}'"
								style="width: 320px; height: 180px;"></td>
							<th colspan=3 width=680px><c:out value="${map.btitle}" /></th>
						</tr>
						<tr height=20px;>
							<td class="bwriter" width=380px><c:out value="${map.unick}" /></td>
							<td class="bwdate" width=200px><f:formatDate value="${map.bwdate}" pattern="yyyy-MM-dd kk:mm"/> </td>
							<td class="blike">추천수 : <c:out value="${map.blike}"/></td>
						</tr>
						<tr>
							<td colspan=3><textarea rows=4 cols=108 readonly
									class="bingredient" style="resize: none; border:0;"><c:out
										value="${map.bingredient}" /></textarea></td>
						</tr>
					</table>
				</c:forEach>
			</div>
		</div>
		<div id="pagination" style = "text-align: center;">
			<c:if test="${pm.prev}">
				<a href="${pm.startPage-1}">◀</a>
			</c:if>
			<c:forEach begin="${pm.startPage}" end="${pm.endPage}" var="i">
				<c:if test="${i == pm.cri.page}">
            [<a href="${i}" class="active">${i}</a>]
         </c:if>
				<c:if test="${i != pm.cri.page}">
            [<a href="${i}">${i}</a>]
         </c:if>
			</c:forEach>
			<c:if test="${pm.next}">
				<a href="${pm.endPage+1}">▶</a>
			</c:if>
		</div>
	</div>
</body>
<script>
	var page = 1;
	var keyword = $("#search-key").val();

	$("#pagination").on("click", "a", function(event) {
		event.preventDefault();
		page = $(this).attr("href");
		//alert(page);
		$(frm.page).val(page);
		frm.submit();
	});
</script>
</html>