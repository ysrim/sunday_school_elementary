<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="<c:url value='/files/script/libs/jquery-3.1.1.min.js'/>"></script>
<style>
* {
	box-sizing: border-box;
	font-family: system-ui;
}

body {
	margin: 0;
	background: #eef2f7;
	display: flex;
	justify-content: center;
	align-items: flex-start;
	min-height: 100vh;
	padding: 20px;
}

/* 🔥 세로 배치 */
.wrap {
	width: 100%;
	max-width: 500px;
	display: flex;
	flex-direction: column; /* 세로 배치 */
	align-items: center;
	gap: 24px;
}

/* 포스터 */
.poster {
	background: white;
	border-radius: 16px;
	padding: 16px;
	box-shadow: 0 4px 16px rgba(0, 0, 0, .12);
	width: 100%;
}

.poster img {
	width: 100%;
	display: block;
	border-radius: 12px;
}

/* 로그인 카드 */
.card {
	background: white;
	border-radius: 16px;
	padding: 24px 20px;
	box-shadow: 0 4px 16px rgba(0, 0, 0, .12);
	width: 100%;
	margin-top: 30px; /* 추가 */
}

.card h2 {
	text-align: center;
	margin-top: 0;
	margin-bottom: 18px;
}

input {
	width: 100%;
	padding: 12px;
	margin: 8px 0;
	border-radius: 8px;
	border: 1px solid #ccc;
	font-size: 16px; /* 글씨 조금 키움 */
}

button {
	width: 100%;
	padding: 13px;
	border: none;
	border-radius: 10px;
	font-size: 15px;
	cursor: pointer;
}

.btn-login {
	background: #4caf50;
	color: white;
	margin-top: 12px;
}

.btn-join {
	background: #1976d2;
	color: white;
	margin-top: 12px;
}
</style>
<script>
	$(function() {
		$(".poster").click(function() {
			location.href = "<c:url value='/login.do'/>";
		});
	});
</script>
</head>
<body>
	<div class="wrap">
		<div class="poster">
			<img src="/files/assets/login_banner.png" alt="말씀의 용사 퀘스트">
		</div>
	</div>
</body>
</html>