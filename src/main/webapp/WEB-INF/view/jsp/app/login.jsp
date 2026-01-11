<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>봉동중앙교회 초등부 RPG - 로그인</title>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" href="<c:url value='/files/css/login_style.css'/>">

</head>
<script>
	$(function () {

		function showPopup(msg, icon = '✨', title = '알림', callback) {
			$('#alert-message').html(msg);
			$('#alert-icon').html(icon);
			$('#alert-title').text(title);
			$('#custom-alert').addClass('active');
			$('.custom-alert-confirm').on('click', function () {
				$('#custom-alert').removeClass('active');
				if (callback && typeof callback === 'function') {
					callback();
				}
			});
		}

		$('.btn-main-login').on('click', function () {
			var mberId = $('#mberId').val().trim();
			var pwd = $('#pwd').val();
			console.log('mberId: ' + mberId);
			console.log('pwd: ' + pwd);
			if (mberId == '') {
				showPopup('아이디를 입력해주세요!', '⚠️', '아이디 입력', function () {
					console.log("트리거 작동: 저장 후 페이지 이동 로직 실행");
					$('#mberId').focus();
				});
				return false;
			}
			if (pwd == '') {
				showPopup('비밀번호를 입력해주세요!', '🔑', '비밀번호 입력', function () {
					$('#pwd').focus();
				});
				return false;
			}
			$.ajax({
				type: "POST",
				url: "<c:url value="/idx/login.ax"/>",
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify({
					mberId: mberId,
					pwd: pwd
				}),
				success: function (data) {
					if (data.rtnCd == '001') {
						showPopup(data.rtnMsg, '✨', '인증 완료', function () {
							location.href = "<c:url value='/std/home.pg'/>";
						});
					} else {
						showPopup(data.rtnMsg, '❌', '인증 실패', function () {
						});
						return false;
					}
				},
				error: function (e) {
					console.log(JSON.stringify(e));
				}
			});
			return false;
		});
	});

</script>
<body>
<div class="app-container">
	<div class="page active">
		<div class="sub-header">
			<div class="sub-header-left">
				<h2>봉동중앙교회 초등부 RPG - 로그인</h2>
			</div>
		</div>
		<h3>반가워요! 믿음의 용사님</h3>
		<div class="card">
			<input type="text" id="mberId" placeholder="이름을 입력하세요">
			<input type="password" id="pwd" placeholder="비밀번호">
			<div class="login-options">
				<label class="checkbox-label">
					<input type="checkbox" id="save-id"> 아이디 저장
				</label>
				<label class="checkbox-label">
					<input type="checkbox" id="auto-login"> 자동 로그인
				</label>
			</div>
			<button class="btn btn-main btn-main-login">로그인</button>
			<button class="btn btn-sub" onclick="location.href='<c:url value="/idx/join.pg"/>'">처음 왔어요 (회원가입)</button>
		</div>
	</div>
	<div id="custom-alert"
	     style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0,0,0,0.5); z-index:9999; align-items:center; justify-content:center;">
		<div class="card"
		     style="max-width:320px; text-align:center; padding:30px; transform: scale(0.9); transition: transform 0.2s;">
			<div id="alert-icon" style="font-size: 3rem; margin-bottom: 15px;">✨</div>
			<h4 id="alert-title" style="margin-bottom: 10px; color: var(--secondary);">알림</h4>
			<p id="alert-message"
			   style="font-size: 0.95rem; color: #666; margin-bottom: 25px; line-height: 1.5; word-break: keep-all;"></p>
			<button class="btn btn-main custom-alert-confirm" style="margin-top:0;">확인</button>
		</div>
	</div>
</div>
</body>
</html>