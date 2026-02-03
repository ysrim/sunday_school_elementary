$(function () {
	setTimeout(_setAlertMsg, 200);
});

// WAS정보 요청후 행위결과에 대한 알림을 위한 얼럿 함수
function _setAlertMsg() {
	let alertMsg = Cookies.get('_alertMsg');
	if (alertMsg) {
		_showAlert(alertMsg.replace(/\+/g, ' '));
		Cookies.remove('_alertMsg', {path: '/'});
	}
}

// 페이지 이동 처리 (Object Map 패턴 적용)
function _naviPage(menu) {
	const menuUrls = {
		'STD_HOME': '/std/home.pg', 'STD_ATND': '/std/atnd.pg', 'STD_GILD': '/std/gild.pg', 'STD_QEST': '/std/qest.pg', 'STD_OPTS': '/std/opts.pg'
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

// 토스트 생성 함수
function _showToast(id, title, msg, type = 'info') {
	const icons = {
		SUCCESS: '✅', REWARD: '💰', WARNING: '⚠️', INFO: '📢'
	};
	const icon = icons[type] || '🔔';
	const toastHtml = `
            <div class="toast-item ${type}" id="toast-${id}">
                <div style="font-size:1.5rem;">${icon}</div>
                <div class="flex-col">
                    <span class="text-bold" style="font-size:0.95rem; color:#333;">${title}</span>
                    <span class="text-sub">${msg}</span>
                </div>
                <button class="toast-close" onclick="_removeToast(${id})">&times;</button>
            </div>`;
	$('#toast-container').append(toastHtml);
}

// 토스트 삭제 함수
function _removeToast(id) {
	const $target = $(`#toast-${id}`);
	if ($target.length) {
		$target.css('animation', 'toastFadeOut 0.3s forwards');
		setTimeout(() => $target.remove(), 300);
		$.ajax({
			type: 'GET', url: '/std/home/removeToast.ax', data: {toastSn: id}, success: (data) => {
			}, error: (e) => {
				console.error(e);
			}
		});
	}
}