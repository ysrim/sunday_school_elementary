<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
	<title>봉동중앙교회 초등부 RPG</title>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700;800&display=swap" rel="stylesheet">
	<style>
        :root {
            --primary: #FFD700;
            --secondary: #4A90E2;
            --bg: #E3F2FD;
            --text: #333;
        }

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: 'Noto Sans KR', sans-serif;
            background: var(--bg);
        }

        .app-container {
            max-width: 480px;
            margin: 0 auto;
            min-height: 100vh;
            background: #fff;
            overflow: hidden;
        }

        /* ================= 인트로 페이지 ================= */
        #page-intro {
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .intro-hero {
            position: relative;
            height: 58vh;
            overflow: hidden;
            border-radius: 0 0 40px 40px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.08);
        }

        .intro-hero img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            animation: zoomEffect 15s infinite alternate ease-in-out;
        }

        @keyframes zoomEffect {
            from {
                transform: scale(1);
            }
            to {
                transform: scale(1.15);
            }
        }

        .intro-overlay {
            position: absolute;
            inset: 0;
            background: linear-gradient(to bottom, rgba(0, 0, 0, 0) 55%, #fff 100%);
        }

        .intro-content {
            flex: 1;
            padding: 24px;
            text-align: center;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        @keyframes floating {
            0% {
                transform: translateY(0);
            }
            50% {
                transform: translateY(-12px);
            }
            100% {
                transform: translateY(0);
            }
        }

        .intro-badge {
            background: #E3F2FD;
            color: #1976D2;
            padding: 6px 14px;
            border-radius: 20px;
            font-size: 0.8rem;
            font-weight: 700;
            margin-bottom: 14px;
        }

        .intro-title {
            font-size: 2rem;
            font-weight: 800;
            line-height: 1.3;
            letter-spacing: -1px;
            color: #1A237E;
            margin-bottom: 12px;
            animation: fadeUp 0.6s ease-out 0.2s both;
        }

        .intro-title span {
            color: var(--secondary);
        }

        .intro-subtitle {
            font-size: 1rem;
            color: #78909C;
            line-height: 1.6;
            animation: fadeUp 0.6s ease-out 0.4s both;
        }

        @keyframes fadeUp {
            from {
                opacity: 0;
                transform: translateY(15px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .intro-footer {
            padding: 0 24px 40px;
        }

        .btn-start {
            /* [레이아웃] 가운데 정렬 */
            display: block;
            margin: 0 auto;

            /* [크기] 적당한 크기로 조절 */
            width: 85%;
            height: 52px;

            /* [디자인 - 플랫 & 하모니] */
            background-color: #FFD700; /* 단색 배경 (그라데이션 제거) */
            color: #1A237E; /* 타이틀과 동일한 남색 (통일감) */
            border: none; /* 테두리 제거 */
            border-radius: 14px; /* 부드러운 라운드 처리 */

            /* [타이포그래피] */
            font-size: 1rem;
            font-weight: 800; /* 두꺼운 폰트로 가독성 높임 */
            letter-spacing: -0.5px; /* 자간을 좁혀 단단한 느낌 */

            /* [감성 한 스푼 - 부드러운 그림자] */
            /* 검은 그림자 대신 노란빛 그림자를 사용하여 화사하게 표현 */
            box-shadow: 0 8px 20px rgba(255, 215, 0, 0.25);

            cursor: pointer;
            transition: all 0.2s ease; /* 부드러운 전환 효과 */

            /* [등장 애니메이션 유지] */
            animation: popIn 0.6s ease-out;
        }

        /* 마우스 오버 시 (PC 환경 고려) */
        .btn-start:hover {
            background-color: #FFC107; /* 살짝 진해짐 */
            box-shadow: 0 10px 25px rgba(255, 215, 0, 0.35);
            transform: translateY(-2px); /* 살짝 떠오르는 느낌 */
        }

        /* 클릭 시 (모바일 터치감) */
        .btn-start:active {
            transform: scale(0.98); /* 3D 눌림 대신 전체적으로 살짝 작아짐 */
            box-shadow: 0 4px 10px rgba(255, 215, 0, 0.2);
        }

        @keyframes popIn {
            0% {
                transform: scale(0.85);
                opacity: 0;
            }
            100% {
                transform: scale(1);
                opacity: 1;
            }
        }

        .btn-start:active {
            transform: scale(0.97);
        }

        .warrior-count {
            margin-top: 14px;
            font-size: 0.8rem;
            color: #90A4AE;
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 6px;
        }

        .status-dot {
            width: 8px;
            height: 8px;
            border-radius: 50%;
            background: #4CAF50;
            box-shadow: 0 0 8px rgba(76, 175, 80, 0.6);
            animation: pulse 2s infinite;
        }

        @keyframes pulse {
            0% {
                transform: scale(1);
                opacity: 1;
            }
            50% {
                transform: scale(1.4);
                opacity: 0.6;
            }
            100% {
                transform: scale(1);
                opacity: 1;
            }
        }
	</style>
</head>
<body>

<div class="app-container">
	<div id="page-intro">
		<div class="intro-hero">
			<img src="https://cdn.jsdelivr.net/gh/ysrim/bjcc_ss@main/images/intro.png" alt="인트로 배경">
			<div class="intro-overlay"></div>
		</div>
		<div class="intro-content">
			<span class="intro-badge">SEASON 1 · 믿음의 갑주</span>
			<h1 class="intro-title">
				봉동중앙교회<br>
				<span>초등부 RPG</span>
			</h1>
			<p class="intro-subtitle">
				말씀으로 성장하고 ⚔️<br>
				달란트를 모아 레벨업하자!
			</p>
		</div>
		<div class="intro-footer">
			<button class="btn-start" onclick="location.href='<c:url value="/login.pg"/>'">
				모험 시작하기! ⚔
			</button>
			<div class="warrior-count">
				<span class="status-dot"></span>
				이번 주 <b>120명</b>의 용사가 함께 모험 중!
			</div>
		</div>
	</div>
</div>
</body>
</html>