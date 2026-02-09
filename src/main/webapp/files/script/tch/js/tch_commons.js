$(function () {
	// 페이지 로드 후 0.2초 뒤 알림 체크
	setTimeout(_setAlertMsg, 200);
	switchTab('home');
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
		'TCH_HOME': '/tch/home.pg',
		'TCH_ATND': '/tch/atnd.pg',
		'TCH_GILD': '/tch/gild.pg',
		'TCH_QEST': '/tch/qest.pg'
	};
	const targetUrl = menuUrls[menu];
	if (targetUrl) {
		location.href = targetUrl;
	} else {
		console.warn(`[Navigation] 정의되지 않은 메뉴 코드입니다: ${menu}`);
	}
}

/**
 * 토스트 알림 표시
 */
function _showToast(message) {
	const $toast = $('#toast');
	$toast.text(message).addClass('show');
	setTimeout(() => {
		$toast.removeClass('show');
	}, 3000);
}


// 4. 퀘스트 필터링
$('#quest-filter .chip').on('click', function () {
	const filter = $(this).data('filter');
	let count = 0;
	$('.quest-item').each(function () {
		if (filter === 'all' || $(this).data('type') === filter) {
			$(this).show();
			count++;
		} else {
			$(this).hide();
		}
	});
	$('#quest-empty').toggleClass('hidden', count > 0);
});

// 5. 공통 액션 (승인/반려 애니메이션)
function handleAction(btn, type) {
	const card = $(btn).closest('.card-box, .quest-item');
	if (type === 'approve') {
		$(btn).html('<i class="fas fa-spinner fa-spin"></i>');
		setTimeout(() => {
			card.css({transform: 'scale(0.9)', opacity: '0', transition: '0.3s'});
			setTimeout(() => card.slideUp(300, function () {
				$(this).remove()
			}), 300);
			showToast('처리가 완료되었습니다! ✅');
		}, 500);
	} else if (confirm('반려하시겠습니까?')) {
		card.css('opacity', '0.5');
	}
}

function updatePreview(el) {
	$('#preview-text').text($(el).val() || "미리보기가 표시됩니다.");
}

function postComment() {
	const val = $('#comment-input').val();
	if (!val) return;
	const html = `<div class="p-4 flex gap-3"><div class="w-10 h-10 rounded-2xl bg-blue-600 flex items-center justify-center text-white"><i class="fas fa-user-tie"></i></div><div class="flex-1"><span class="text-sm font-bold text-blue-600">관리자</span><p class="text-sm text-slate-700 mt-1">${val}</p></div></div>`;
	$('#comment-list').prepend(html);
	$('#comment-input').val('');
}

function deleteComment(btn) {
	if (confirm("삭제하시겠습니까?")) $(btn).closest('.p-4').remove();
}

// 1. 모든 버튼 공통 ON/OFF 제어 (칩, 주차, 서브탭 통합)
$(document).on('click', '.chip, .week-btn, .sub-tab', function () {
	$(this).addClass('active').siblings().removeClass('active');
});