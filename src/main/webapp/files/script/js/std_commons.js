$(function () {
	setTimeout(_setAlertMsg, 200);
});

/**
 * WAS정보 요청후 행위결과에 대한 알림을 위한 얼럿 함수
 */
function _setAlertMsg() {
	// 1. 쿠키 읽기 (js-cookie는 기본적으로 decodeURIComponent를 내부에서 수행합니다)
	let alertMsg = Cookies.get('_alertMsg');

	if (alertMsg) {
		// 2. Java URLEncoder 특성상 공백이 '+'로 올 수 있으므로 치환
		// (js-cookie가 %XX는 자동으로 디코딩해주므로 decodeURIComponent 호출 불필요)
		alert(alertMsg.replace(/\+/g, ' '));

		// 3. 쿠키 삭제 (깔끔하게 remove 사용)
		// 단, 생성할 때 path를 지정했다면 삭제할 때도 path를 반드시 명시해야 함
		Cookies.remove('_alertMsg', { path: '/' });
	}
}

/**
 * 페이지 이동 처리 (Object Map 패턴 적용)
 */
function _naviPage(menu) {
	const menuUrls = {
		'STD_HOME': '/std/home.pg',
		'STD_ATND': '/std/atnd.pg',
		'STD_GILD': '/std/gild.pg',
		'STD_QEST': '/std/qest.pg',
		'STD_OPTS': '/std/opts.pg'
	};
	const targetUrl = menuUrls[menu];
	if (targetUrl) {
		location.href = targetUrl;
	} else {
		console.warn('정의되지 않은 메뉴 코드입니다:', menu);
	}
}

// 알림창 띄우기 함수 (메시지, 아이콘, 제목)
function _showAlert(msg, icon = '✨', title = '알림') {
	$('#alert-message').html(msg);
	$('#alert-icon').html(icon);
	$('#alert-title').text(title);
	$('#custom-alert').addClass('active');
}

// 알림창 닫기 함수
function _closeAlert() {
	$('#custom-alert').removeClass('active');
}