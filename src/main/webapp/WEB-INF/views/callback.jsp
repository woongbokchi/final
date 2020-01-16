<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="kr">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>NaverLoginSDK</title>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>

<body>
<script type="text/javascript">
		var naver_id_login = new naver_id_login("xutQP3xAJRfccCu5hhFo",
				"http://192.168.0.139:8088/final/callback"); 
		/* var naver_id_login = new naver_id_login("xutQP3xAJRfccCu5hhFo",
				"http://localhost:8088/web/callback");  */
		// 접근 토큰 값 출력
		//alert(naver_id_login.oauthParams.access_token);
		// 네이버 사용자 프로필 조회
		naver_id_login.get_naver_userprofile("naverSignInCallback()");
		// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
		function naverSignInCallback() {
			var uno = naver_id_login.getProfileData('id');
			var uemail = naver_id_login.getProfileData('email');
			var unick = naver_id_login.getProfileData('nickname');
			location.href = "navbar?uemail=" + uemail + "&unick=" + unick
					+ "&uno=" + uno;

		}
</script>
</body>
</html>