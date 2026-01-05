<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>봉동중앙교회 초등부 RPG - 로그인</title>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<style>
        :root {
            --primary: #FFD700;
            --secondary: #4A90E2;
            --bg: #E3F2FD;
            --text: #333;
            --white: #ffffff;
            --gray: #999;
        }

        /* 기본 초기화 */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: var(--bg);
            background-image: url('https://www.transparenttextures.com/patterns/clouds.png');
            color: var(--text);
            line-height: 1.6;
        }

        /* 앱 레이아웃 */
        .app-container {
            max-width: 480px;
            margin: 0 auto;
            min-height: 100vh;
            background: #ffffff;
            position: relative;
            display: flex;
            flex-direction: column;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        /* 페이지 애니메이션 */
        .page {
            width: 100%;
            animation: fadeIn 0.3s ease;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(10px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        /* 헤더 스타일 */
        .sub-header {
            padding: 20px;
            background: linear-gradient(135deg, #4A90E2 0%, #357ABD 100%);
            display: flex;
            align-items: center;
            justify-content: center; /* 로그인 타이틀 중앙 정렬 */
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            color: white;
            border-radius: 0 0 20px 20px;
            margin-bottom: 20px;
        }

        .sub-header h2 {
            font-size: 1.2rem;
            font-weight: 700;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
        }

        /* 카드 스타일 */
        .card {
            margin: 0 20px;
            background: var(--white);
            border-radius: 25px;
            padding: 30px 20px;
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.05);
            border: 2px solid #e1f5fe;
        }

        /* 입력 폼 스타일 */
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 14px;
            margin-bottom: 12px;
            border: 1px solid #ddd;
            border-radius: 12px;
            font-size: 1rem;
            outline: none;
            transition: border-color 0.2s;
        }

        input[type="text"]:focus, input[type="password"]:focus {
            border-color: var(--secondary);
        }

        /* 체크박스 옵션 영역 */
        .login-options {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin: 5px 5px 20px 5px;
            font-size: 0.85rem;
            color: #666;
        }

        .checkbox-label {
            display: flex;
            align-items: center;
            cursor: pointer;
            user-select: none;
        }

        .checkbox-label input {
            width: 18px;
            height: 18px;
            margin-right: 6px;
            accent-color: var(--secondary);
        }

        /* 버튼 스타일 */
        .btn {
            width: 100%;
            padding: 16px;
            border-radius: 50px;
            border: none;
            font-weight: bold;
            font-size: 1rem;
            cursor: pointer;
            transition: transform 0.1s;
        }

        .btn:active {
            transform: scale(0.98);
        }

        .btn-main {
            background: var(--primary);
            color: #444;
            box-shadow: 0 4px 15px rgba(255, 215, 0, 0.3);
            margin-bottom: 10px;
        }

        .btn-sub {
            background: #f0f2f5;
            color: #666;
        }
	</style>
</head>
<body>
<div class="app-container">
	<div id="page-login" class="page">
		<div class="sub-header">
			<h2>로그인</h2>
		</div>
		<div class="card">
			<input type="text" id="login-id" placeholder="이름을 입력하세요">
			<input type="password" id="login-pw" placeholder="비밀번호">
			<div class="login-options">
				<label class="checkbox-label">
					<input type="checkbox" id="save-id"> 아이디 저장
				</label>
				<label class="checkbox-label">
					<input type="checkbox" id="auto-login"> 자동 로그인
				</label>
			</div>
			<button class="btn btn-main" onclick="location.href='<c:url value="/login.ax"/>'">로그인</button>
			<button class="btn btn-sub" onclick="location.href='<c:url value="/join.pg"/>'">처음 왔어요 (회원가입)</button>
		</div>
	</div>
</div>
</body>
</html>