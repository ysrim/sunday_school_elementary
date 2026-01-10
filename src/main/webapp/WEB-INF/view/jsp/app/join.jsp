<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>봉동중앙교회 초등부 RPG - 회원가입</title>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link rel="stylesheet" href="<c:url value='/files/css/join_style.css'/>">

</head>
<body>
<div class="app-container">
	<div class="page active">
		<div class="sub-header">
			<div class="sub-header-left">
				<h2>봉동중앙교회 초등부 RPG - 회원가입</h2>
			</div>
		</div>
		<h3>믿음의 용사가 되어볼까요?</h3>
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