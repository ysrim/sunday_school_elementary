<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@page isErrorPage="true" %>
<%
	response.setStatus(200);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>페이지를 찾을 수 없음 - 봉동중앙교회 초등부 RPG</title>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
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
            height: 100vh;
            overflow: hidden; /* 스크롤 방지 */
        }

        .app-container {
            max-width: 480px;
            margin: 0 auto;
            height: 100%;
            background: #ffffff;
            position: relative;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }

        /* 카드 스타일 (기존 코드 계승) */
        .card {
            width: 100%;
            background: var(--white);
            border-radius: 25px;
            padding: 40px 20px;
            box-shadow: 0 10px 25px rgba(74, 144, 226, 0.15);
            border: 2px solid #e1f5fe;
            text-align: center;
            position: relative;
            z-index: 2;
        }

        /* 404 전용 이미지 스타일 */
        .error-image {
            width: 180px;
            height: 180px;
            margin-bottom: 20px;
            animation: floating 3s ease-in-out infinite;
        }

        /* 둥둥 떠다니는 애니메이션 (기존 코드 계승) */
        @keyframes floating {
            0% {
                transform: translateY(0px);
            }
            50% {
                transform: translateY(-15px);
            }
            100% {
                transform: translateY(0px);
            }
        }

        h1 {
            font-size: 3rem;
            color: var(--secondary);
            font-weight: 800;
            margin-bottom: 5px;
            letter-spacing: -1px;
        }

        h3 {
            font-size: 1.3rem;
            color: #333;
            margin-bottom: 15px;
        }

        p {
            color: #888;
            font-size: 0.95rem;
            margin-bottom: 30px;
            word-break: keep-all;
        }

        /* 버튼 스타일 (기존 코드 계승) */
        .btn {
            width: 100%;
            padding: 15px;
            border-radius: 50px;
            border: none;
            font-weight: bold;
            font-size: 1rem;
            cursor: pointer;
            transition: 0.2s;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
        }

        .btn-main {
            background: var(--primary);
            color: #444;
            box-shadow: 0 4px 15px rgba(255, 215, 0, 0.4);
            position: relative;
            overflow: hidden;
        }

        .btn-main:active {
            transform: scale(0.98);
        }

        /* 반짝임 효과 (기존 코드 계승) */
        .btn-shine::after {
            content: '';
            position: absolute;
            top: -50%;
            left: -60%;
            width: 20%;
            height: 200%;
            background: rgba(255, 255, 255, 0.4);
            transform: rotate(30deg);
            animation: shine-effect 3s infinite;
        }

        @keyframes shine-effect {
            0% {
                left: -60%;
            }
            20% {
                left: 120%;
            }
            100% {
                left: 120%;
            }
        }

        /* 배경 장식 요소 */
        .bg-deco {
            position: absolute;
            opacity: 0.1;
            z-index: 1;
        }
	</style>
</head>
<body>

<div class="app-container">

	<img src="https://cdn-icons-png.flaticon.com/512/744/744922.png" class="bg-deco"
		 style="top: 10%; left: -20px; width: 100px; transform: rotate(-15deg);">
	<img src="https://cdn-icons-png.flaticon.com/512/2530/2530062.png" class="bg-deco"
		 style="bottom: 10%; right: -20px; width: 120px; transform: rotate(15deg);">

	<div class="card">
		<img src="https://cdn-icons-png.flaticon.com/512/235/235861.png" alt="길을 잃음" class="error-image">

		<h1>404</h1>
		<h3>길을 잃은 용사님!</h3>

		<p>
			찾으시는 페이지가 삭제되었거나<br>
			주소가 잘못된 것 같아요.<br>
			다시 안전한 마을로 돌아갈까요?<br>
			error: ${exception}
		</p>

		<button class="btn btn-main btn-shine" onclick="location.href='<c:url value="/idx/intro.pg"/>'">
			<img src="https://cdn.jsdelivr.net/gh/ysrim/bjcc_ss@main/images/navi/home.png"
				 style="width: 20px; height: 20px; opacity: 0.6;">
			메인으로 돌아가기
		</button>
	</div>

</div>

</body>
</html>