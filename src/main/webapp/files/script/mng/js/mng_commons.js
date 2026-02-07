$(function () {
    // 페이지 로드 후 0.2초 뒤 알림 체크
    setTimeout(_setAlertMsg, 200);
});

/**
 * 쿠키에 저장된 알림 메시지 세팅 및 표시
 */
function _setAlertMsg() {
    const alertMsg = Cookies.get('_alertMsg');
    if (alertMsg) {
        // '+' 기호를 공백으로 치환하여 표시
        _showToast(alertMsg.replace(/\+/g, ' '));
        // 확인 후 쿠키 삭제
        Cookies.remove('_alertMsg', {path: '/'});
    }
}

/**
 * 페이지 이동 처리 (매핑 방식)
 * @param {string} menu - 이동할 메뉴 코드 (예: STD_HOME)
 */
function _naviPage(menu) {
    const menuUrls = {
        'MNG_HOME': '/mng/home.pg',
        'MNG_MBER': '/mng/mber.pg',
        'MNG_FEED': '/mng/feed.pg',
        'MNG_REWD': '/mng/rewd.pg'
    };
    const targetUrl = menuUrls[menu];
    if (targetUrl) {
        location.href = targetUrl;
    } else {
        console.warn(`[Navigation] 정의되지 않은 메뉴 코드입니다: ${menu}`);
    }
}