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


///


/**
 * 출석 리스트 탭 전환 (승인 대기 / 완료됨)
 */
function switchAttList(type) {
    const $btnPending = $('#btn-att-pending');
    const $btnComplete = $('#btn-att-complete');
    const $listPending = $('#list-att-pending');
    const $listComplete = $('#list-att-complete');

    // 공통 스타일 클래스 정의
    const activeClass = "text-lg font-bold text-slate-800 border-b-2 border-slate-800 pb-1 transition-all";
    const inactiveClass = "text-lg font-bold text-slate-300 pb-1 hover:text-slate-500 transition-all";

    if (type === 'pending') {
        $btnPending.removeClass().addClass(activeClass).html('승인 대기 <span class="text-blue-500 text-sm align-top">2</span>');
        $btnComplete.removeClass().addClass(inactiveClass);

        $listPending.removeClass('hidden');
        $listComplete.addClass('hidden');
    } else {
        $btnComplete.removeClass().addClass(activeClass);
        $btnPending.removeClass().addClass(inactiveClass).text('승인 대기'); // 숫자 제거

        $listComplete.removeClass('hidden');
        $listPending.addClass('hidden');
    }
}

/**
 * 퀘스트 필터링 (전체 / 일일 / 주일 / 특별)
 */
function filterQuest(type) {
    // 칩 버튼 활성화 상태 변경
    $('#tab-quest .chip').removeClass('active');
    $('#btn-' + type).addClass('active');

    const $items = $('.quest-item');
    let visibleCount = 0;

    $items.each(function () {
        const $item = $(this);
        // data-type 속성 확인
        if (type === 'all' || $item.data('type') === type) {
            $item.removeClass('hidden');
            visibleCount++;
        } else {
            $item.addClass('hidden');
        }
    });

    // 리스트가 비었는지 확인하여 안내 메시지 표시
    if (visibleCount > 0) {
        $('#quest-list').removeClass('hidden');
        $('#quest-empty').addClass('hidden');
    } else {
        $('#quest-list').addClass('hidden');
        $('#quest-empty').removeClass('hidden');
    }
}

/**
 * 채팅 메시지 전송
 */
function sendMessage() {
    const $input = $('#chat-input');
    const messageText = $input.val().trim();

    if (!messageText) return;

    const msgHtml = `
        <div class="flex gap-3 items-end flex-row-reverse animate-enter">
            <div class="w-8 h-8 rounded-xl bg-slate-800 flex items-center justify-center text-white text-xs shrink-0"><i class="fas fa-user-tie"></i></div>
            <div class="flex-1 text-right">
                <div class="bg-blue-600 text-white px-4 py-3 rounded-2xl rounded-br-none text-sm text-left shadow-md inline-block max-w-[90%]">
                    ${messageText}
                </div>
                <div class="text-[10px] text-slate-300 mt-1 mr-1">방금 전</div>
            </div>
        </div>
    `;

    const $chatList = $('#chat-list');
    $chatList.append(msgHtml);
    $input.val(''); // 입력창 초기화

    // 스크롤 최하단으로 이동
    $chatList.scrollTop($chatList[0].scrollHeight);
}

/**
 * 액션 처리 (승인/반려)
 */
function handleAction(btn, type) {
    const $btn = $(btn);
    const $row = $btn.closest('.row-item, .quest-item');

    if (type === 'approve') {
        // 로딩 스피너 표시
        $btn.html('<i class="fas fa-spinner fa-spin"></i>');

        setTimeout(() => {
            $row.css({
                'transition': 'all 0.3s',
                'opacity': '0',
                'transform': 'scale(0.9)'
            });

            setTimeout(() => {
                $row.remove();
                // 여기에 리스트가 비었는지 다시 체크하는 로직(filterQuest 등)을 호출할 수도 있습니다.
            }, 300);
        }, 500);
    } else {
        if (confirm('정말 반려하시겠습니까?')) {
            $row.css('opacity', '0.5'); // 반려 시 흐리게 처리
        }
    }
}

/**
 * 월 선택 칩 버튼 활성화
 */
function selectMonth(btn) {
    $('.chip').removeClass('active');
    $(btn).addClass('active');
}