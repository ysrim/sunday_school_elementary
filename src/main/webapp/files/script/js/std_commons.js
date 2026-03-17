$(function () {
	setTimeout(_setAlertMsg, 200);
});

/**
 * WAS(서버)에서 전달된 알림 메시지 처리
 * 쿠키에 저장된 메시지가 있으면 알림창을 띄우고 쿠키 삭제
 */
function _setAlertMsg() {
	const alertMsg = Cookies.get('_alertMsg');
	if (alertMsg) {
		_showAlert(alertMsg.replace(/\+/g, ' '));
		Cookies.remove('_alertMsg', {path: '/'});
	}
}

/**
 * 페이지 이동 처리 (매핑 방식)
 * @param {string} menu - 이동할 메뉴 코드 (예: STD_HOME)
 */
function _naviPage(menu) {
	const menuUrls = {
		'STD_HOME': '/std/home.pg', 'STD_ATND': '/std/atnd.pg', 'STD_GILD': '/std/gild.pg', 'STD_QEST': '/std/qest.pg', 'STD_OPTS': '/std/opts.pg'
	};
	const targetUrl = menuUrls[menu];
	if (targetUrl) {
		location.href = targetUrl;
	} else {
		console.warn(`[Navigation] 정의되지 않은 메뉴 코드입니다: ${menu}`);
	}
}

/**
 * 로그아웃
 */
function _logOut() {
	if (confirm("로그아웃 하시겠습니까? 로그아웃 하면 자동로그인 기능이 해제됩니다.")) {
		location.href = '/std/idx/logOut.pg';
	}
	return false;
}

/* -------------------------------------------------------------------------- */
/* UI 컴포넌트                                 */

/* -------------------------------------------------------------------------- */

/**
 * 커스텀 알림창 띄우기 (애니메이션 적용)
 * @param {string} message - 알림 내용 (HTML 허용)
 * @param {string} icon - 상단 아이콘 (기본값: ✨)
 * @param {string} title - 알림 제목 (기본값: 알림)
 */
function _showAlert(message, icon = '✨', title = '알림') {
	const $alert = $('#custom-alert');

	// 내용 설정 (jQuery 체이닝 활용)
	$alert.find('#alert-title').text(title);
	$alert.find('#alert-message').html(message);
	$alert.find('#alert-icon').html(icon);

	// 1. 먼저 요소를 보이게 설정 (display: flex)
	$alert.css('display', 'flex');

	// 2. 브라우저 렌더링 후 클래스 추가하여 애니메이션(scale/opacity) 실행
	setTimeout(() => {
		$alert.addClass('active');
	}, 10);
}

/**
 * 커스텀 알림창 닫기
 */
function _closeAlert() {
	const $alert = $('#custom-alert');

	// 1. active 클래스 제거 (사라지는 애니메이션 시작)
	$alert.removeClass('active');

	// 2. CSS transition 시간(0.3s) 후 display: none 처리
	setTimeout(() => {
		$alert.hide(); // .css('display', 'none')과 동일
	}, 300);
}

/**
 * 토스트 메시지 생성 및 표시
 * @param {number|string} id - 토스트 고유 ID
 * @param {string} title - 제목
 * @param {string} msg - 내용
 * @param {string} type - 타입 (SUCCESS, REWARD, WARNING, INFO)
 */
function _showToast(id, title, msg, type = 'INFO') {
	const icons = {
		SUCCESS: '✅', REWARD: '💰', WARNING: '⚠️', INFO: '📢'
	};
	const iconChar = icons[type] || '🔔';

	// HTML 템플릿 생성
	const toastHtml = `
        <div class="toast-item ${type}" id="toast-${id}">
            <div style="font-size:1.5rem;">${iconChar}</div>
            <div class="flex-col">
                <span class="text-bold" style="font-size:0.95rem; color:#333;">${title}</span>
                <span class="text-sub">${msg}</span>
            </div>
            <button class="toast-close" onclick="_removeToast('${id}')">&times;</button>
        </div>`;

	// 1. 컨테이너에 요소 추가 (jQuery 객체로 변환하여 제어)
	const $toast = $(toastHtml).appendTo('#toast-container');

	// 2. 자연스러운 등장을 위해 약간의 지연 후 'show' 클래스 추가 (CSS 필요)
	// *CSS에 .toast-item { opacity: 0; transform: translateY(20px); transition: ... }
	// * .toast-item.show { opacity: 1; transform: translateY(0); } 가 있어야 함
	setTimeout(() => {
		$toast.addClass('show');
	}, 10);

}

/**
 * 토스트 메시지 삭제
 * @param {number|string} id - 삭제할 토스트 ID
 */
function _removeToast(id) {
	const $target = $(`#toast-${id}`);

	if ($target.length) {
		// 1. 사라지는 애니메이션 (show 클래스 제거 or CSS animation 적용)
		$target.removeClass('show').css('animation', 'toastFadeOut 0.3s forwards');

		// 2. 애니메이션 완료 후 DOM 제거
		setTimeout(() => {
			$target.remove();
		}, 300);

		// 3. 서버에 확인 요청 (비동기, 결과 무시)
		$.ajax({
			type: 'GET', url: '/std/home/removeToast.ax', data: {toastSn: id}, error: (e) => console.error('Toast Check Error:', e)
		});
	}
}