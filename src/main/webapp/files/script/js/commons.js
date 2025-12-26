$(function() {
	setTimeout("_setAlertMsg()", "200");
});

/**
 * WAS정보 요청후 행위결과에 대한 알림을 위한 얼럿 함수
 *
 * @returns
 */
function _setAlertMsg() {
	if ($.cookie('_alertMsg') != null && $.cookie('_alertMsg') != 'null' && $.cookie('_alertMsg') != '') {
		alert($.cookie('_alertMsg').replace(/\+/g, ' '));
		$.cookie('_alertMsg', '', {
			path : '/'
		});
	}
}