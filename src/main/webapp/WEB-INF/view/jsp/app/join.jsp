<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>봉동중앙교회 초등부 RPG - 회원가입</title>
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
            --light-gray: #f0f0f0;
            --success: #4CAF50;
            --error: #FF5252;
        }

        /* 기본 초기화 및 컨테이너 */
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

        .app-container {
            max-width: 480px;
            margin: 0 auto;
            min-height: 100vh;
            background: #ffffff;
            position: relative;
            padding-bottom: 60px;
            overflow-x: hidden;
            display: flex;
            flex-direction: column;
        }

        /* 페이지 설정 */
        .page {
            display: none;
            padding: 0 0 20px 0;
            animation: fadeIn 0.3s ease;
            width: 100%;
        }

        .page.active {
            display: block;
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

        /* 서브페이지 헤더 */
        .sub-header {
            padding: 15px 20px;
            background: linear-gradient(135deg, #4A90E2 0%, #357ABD 100%);
            display: flex;
            align-items: center;
            justify-content: space-between;
            position: sticky;
            top: 0;
            z-index: 100;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            color: white;
        }

        .sub-header-left {
            display: flex;
            align-items: center;
            gap: 12px;
        }

        .sub-header h2 {
            font-size: 1.1rem;
            font-weight: 700;
            margin: 0;
            text-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2);
        }

        h3 {
            color: #1976D2;
            margin: 20px 0 15px;
            font-size: 1.3rem;
            text-align: center;
        }

        /* 카드 스타일 */
        .card {
            margin: 15px 20px;
            width: calc(100% - 40px);
            background: var(--white);
            border-radius: 25px;
            padding: 25px 20px;
            box-shadow: 0 6px 15px rgba(0, 0, 0, 0.05);
            border: 2px solid #e1f5fe;
        }

        /* 폼 요소 스타일 */
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            font-size: 0.9rem;
            color: #555;
            margin-top: 10px;
        }

        label:first-child {
            margin-top: 0;
        }

        input[type="text"], input[type="password"], select {
            width: 100%;
            padding: 12px;
            margin-bottom: 5px; /* 메시지 공간 확보를 위해 줄임 */
            border: 1px solid #ddd;
            border-radius: 10px;
            font-size: 1rem;
            transition: border-color 0.2s;
        }

        input:focus, select:focus {
            outline: none;
            border-color: var(--secondary);
        }

        /* 아이디 중복확인 레이아웃 */
        .input-group {
            display: flex;
            gap: 8px;
            margin-bottom: 5px;
        }

        .input-group input {
            margin-bottom: 0;
            flex: 1;
        }

        .btn-check {
            white-space: nowrap;
            background: #81D4FA;
            color: #01579B;
            border: none;
            border-radius: 10px;
            padding: 0 15px;
            font-weight: bold;
            font-size: 0.85rem;
            cursor: pointer;
            height: 46px; /* 인풋 높이와 맞춤 (padding+border 고려) */
            transition: background 0.2s;
        }

        .btn-check:hover {
            background: #4FC3F7;
        }

        /* 유효성 메시지 */
        .validation-msg {
            font-size: 0.8rem;
            min-height: 18px;
            margin-bottom: 10px;
            padding-left: 5px;
        }

        .msg-success {
            color: var(--success);
        }

        .msg-error {
            color: var(--error);
        }

        /* 아바타 선택창 */
        .avatar-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 10px;
            margin: 15px 0 20px;
        }

        .avatar-item {
            border: 3px solid transparent;
            border-radius: 15px;
            overflow: hidden;
            cursor: pointer;
            transition: 0.2s;
            background: #f8f9fa;
        }

        .avatar-item img {
            width: 100%;
            display: block;
            aspect-ratio: 1/1;
            object-fit: cover;
        }

        .avatar-item span {
            display: block;
            font-size: 0.7rem;
            text-align: center;
            padding: 5px 0;
            background: rgba(255, 255, 255, 0.8);
            font-weight: bold;
        }

        .avatar-item.selected {
            border-color: var(--secondary);
            background: #e3f2fd;
            transform: scale(1.05);
            box-shadow: 0 4px 10px rgba(74, 144, 226, 0.3);
        }

        /* 메인 버튼 */
        .btn {
            width: 100%;
            padding: 15px;
            border-radius: 50px;
            border: none;
            font-weight: bold;
            font-size: 1rem;
            cursor: pointer;
            transition: 0.2s;
            margin-top: 20px;
        }

        .btn-main {
            background: var(--primary);
            color: #444;
            box-shadow: 0 4px 15px rgba(255, 215, 0, 0.3);
        }

        .btn-main:active {
            transform: scale(0.98);
        }
	</style>
</head>
<body>
<div class="app-container">
	<div id="page-signup" class="page active">
		<div class="sub-header">
			<div class="sub-header-left">
				<h2>봉동중앙교회 초등부 RPG - 회원가입</h2>
			</div>
		</div>
		<h3>반가워요! 믿음의 용사가 되어볼까요?</h3>
		<sf:form id="joinFm" name="joinFm" modelAttribute="joinFm">
			<div class="card">
				<label>아이디</label>
				<div class="input-group">
					<sf:input path="mberId" placeholder="아이디 (영문, 숫자)"/>
					<button type="button" class="btn-check" id="btn-check-id">중복확인</button>
				</div>
				<p id="msg-id" class="validation-msg"></p>
				<label>비밀번호</label>
				<sf:password path="pwd" placeholder="비밀번호를 입력하세요"/>
				<p class="validation-msg" style="margin-bottom:0;"></p> <label>비밀번호 확인</label>
				<input type="password" id="pwd-confirm" placeholder="한 번 더 입력해주세요">
				<p id="msg-pw" class="validation-msg"></p>
				<label>이름</label>
				<sf:input path="mberNm" placeholder="실명을 적어주세요"/>
				<p class="validation-msg"></p>
				<label>캐릭터 이름</label>
				<sf:input path="ncnm" placeholder="멋진 캐릭터이름을 지어주세요"/>
				<p class="validation-msg"></p>
				<label>나의 캐릭터 선택</label>
				<div class="avatar-grid" id="avatar-selector">
					<sf:hidden path="occpCode"/>
					<div class="avatar-item" data-id="010">
						<img src="https://cdn.jsdelivr.net/gh/ysrim/bjcc_ss@main/images/avatar/warrior.png" alt="전사">
						<span>믿음의 전사</span>
					</div>
					<div class="avatar-item" data-id="020">
						<img src="https://cdn.jsdelivr.net/gh/ysrim/bjcc_ss@main/images/avatar/paladin.png" alt="성기사">
						<span>말씀 용사</span>
					</div>
					<div class="avatar-item" data-id="030">
						<img src="https://cdn.jsdelivr.net/gh/ysrim/bjcc_ss@main/images/avatar/archer.png" alt="궁수">
						<span>평화의 궁수</span>
					</div>
					<div class="avatar-item" data-id="040">
						<img src="https://cdn.jsdelivr.net/gh/ysrim/bjcc_ss@main/images/avatar/healer.png" alt="치유사">
						<span>사랑의 약사</span>
					</div>
					<div class="avatar-item" data-id="050">
						<img src="https://cdn.jsdelivr.net/gh/ysrim/bjcc_ss@main/images/avatar/wizard.png" alt="마법사">
						<span>지혜의 술사</span>
					</div>
					<div class="avatar-item" data-id="060">
						<img src="https://cdn.jsdelivr.net/gh/ysrim/bjcc_ss@main/images/avatar/guardian.png" alt="수호자">
						<span>희망 수호자</span>
					</div>
				</div>
				<div style="display: flex; gap: 10px; margin-bottom: 10px;">
					<div style="flex: 1;">
						<label>학년</label>
						<sf:select path="grade">
							<sf:option value="4">4학년</sf:option>
							<sf:option value="5">5학년</sf:option>
							<sf:option value="6">6학년</sf:option>
						</sf:select>
					</div>
					<div style="flex: 1;">
						<label>반</label>
						<sf:select path="cls">
							<sf:option value="1">1반</sf:option>
							<sf:option value="2">2반</sf:option>
							<sf:option value="3">3반</sf:option>
							<sf:option value="4">4반</sf:option>
							<sf:option value="5">5반</sf:option>
							<sf:option value="6">6반</sf:option>
						</sf:select>
						</select>
					</div>
				</div>
				<button class="btn btn-main" id="btn-submit">가입 완료!</button>
			</div>
		</sf:form>
	</div>
</div>

<script>
	$(function () {

		let isIdChecked = false; // 아이디 중복 확인 여부

		// 1. 아바타 선택 로직
		$('.avatar-item').on('click', function () {
			$('.avatar-item').removeClass('selected'); // 기존 선택 제거
			$(this).addClass('selected'); // 현재 선택 활성화
			$('#occpCode').val($(this).data('id'));
		});

		// 2. 아이디 중복 확인 로직
		$('#btn-check-id').on('click', function () {
			const inputId = $('#mberId').val().trim();
			const $msg = $('#msg-id');

			if (inputId.length < 5) {
				$msg.text('아이디는 5글자 이상이어야 해요.').removeClass('msg-success').addClass('msg-error');
				isIdChecked = false;
				return;
			}

			$.ajax({
				type: "GET",
				url: "<c:url value='/idDupleChk.ax'/>",
				data: $("#joinFm").serialize(),
				success: function (data) {

					if (data.rtnCd == '001') {
						$msg.text('사용 가능한 아이디입니다!').removeClass('msg-error').addClass('msg-success');
						isIdChecked = true;
					} else {
						$msg.text('이미 사용 중인 아이디입니다.').removeClass('msg-success').addClass('msg-error');
						isIdChecked = false;
					}
				},
				error: function (e) {
					console.log(JSON.stringify(e));
				}
			});

		});

		// 아이디 입력값이 바뀌면 중복확인 다시 하도록 리셋
		$('#mberId').on('input', function () {
			isIdChecked = false;
			$('#msg-id').text('');
		});

		// 3. 비밀번호 일치 확인 로직 (실시간)
		$('#pwd, #pwd-confirm').on('input', function () {
			const pw = $('#pwd').val();
			const pwConfirm = $('#pwd-confirm').val();
			const $msg = $('#msg-pw');

			if (pw === '' && pwConfirm === '') {
				$msg.text('');
				return;
			}

			if (pw === pwConfirm) {
				$msg.text('비밀번호가 일치합니다.').removeClass('msg-error').addClass('msg-success');
			} else {
				$msg.text('비밀번호가 서로 달라요.').removeClass('msg-success').addClass('msg-error');
			}
		});

		// 4. 가입 완료 버튼 클릭 시 유효성 검사
		$('#btn-submit').on('click', function () {
			const name = $('#mberNm').val().trim();
			const pw = $('#pwd').val();
			const pwConfirm = $('#pwd-confirm').val();
			const ncnm = $('#ncnm').val();
			if (!isIdChecked) {
				alert('아이디 중복확인을 해주세요!');
				$('#mberId').focus();
				return false;
			}
			if (pw.length < 4) {
				alert('비밀번호를 4자리 이상 입력해주세요.');
				$('#pwd').focus();
				return false;
			}
			if (pw !== pwConfirm) {
				alert('비밀번호가 일치하지 않습니다.');
				$('#pwd-confirm').focus();
				return false;
			}
			if (name === '') {
				alert('이름을 입력해주세요!');
				$('#mberNm').focus();
				return false;
			}
			if (ncnm === '') {
				alert('캐릭터 이름을 입력해주세요!');
				$('#ncnm').focus();
				return false;
			}
			if ($('#occpCode').val() === '') {
				alert('캐릭터를 선택해주세요!');
				return false;
			}

			$.ajax({
				type: "GET",
				url: "<c:url value='/join.ax'/>",
				data: $("#joinFm").serialize(),
				success: function (data) {
					if (data.rtnCd == '001') {
						alert('가입을 축하합니다! ' + name + ' 용사님!');
						location.href = "<c:url value='/login.pg'/>";
					} else {
						alert(data.rtnMsg);
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

	// 학년 선택 변경 시 반 목록 동적 생성
	$('#grade').on('change', function () {
		$('#cls').empty();
		let maxClass = $(this).val() === '5' ? 4 : 6;
		for (let i = 1; i <= maxClass; i++) {
			$('#cls').append('<option value="' + i + '">' + i + '반</option>');
		}
	});

</script>
</body>
</html>