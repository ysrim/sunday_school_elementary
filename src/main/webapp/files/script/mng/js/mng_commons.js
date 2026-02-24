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
		Toast.success(alertMsg.replace(/\+/g, ' '));
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
		'MNG_REWD': '/mng/rewd.pg',
		'MNG_QEST': '/mng/qest.pg'
	};
	const targetUrl = menuUrls[menu];
	if (targetUrl) {
		location.href = targetUrl;
	} else {
		console.warn(`[Navigation] 정의되지 않은 메뉴 코드입니다: ${menu}`);
	}
}

/**
 * commons.js
 * 전역 공통 유틸리티 모음
 */

const Toast = {
	// 설정
	containerId: 'global-toast-container',
	displayDuration: 3000, // 3초

	/**
	 * Toast 컨테이너가 없으면 생성 (상단 중앙 위치)
	 */
	ensureContainer: function () {
		let container = document.getElementById(this.containerId);
		if (!container) {
			container = document.createElement('div');
			container.id = this.containerId;
			// [위치 변경] fixed top-6 left-1/2 (중앙 정렬을 위한 translate-x-1/2)
			container.className = 'fixed top-6 left-1/2 transform -translate-x-1/2 z-[100] flex flex-col items-center gap-3 pointer-events-none w-full max-w-md';
			document.body.appendChild(container);
		}
		return container;
	},

	/**
	 * 메인 실행 함수
	 */
	fire: function (type, message) {
		const container = this.ensureContainer();
		const id = 'toast-' + Date.now() + '-' + Math.random().toString(36).substr(2, 9);

		// 디자인 테마 설정 (아이콘 + 배경색 + 텍스트색)
		const config = {
			success: {
				icon: '<div class="w-8 h-8 rounded-full bg-green-100 flex items-center justify-center text-green-600"><i class="fas fa-check"></i></div>',
				border: 'border-green-200'
			},
			error: {
				icon: '<div class="w-8 h-8 rounded-full bg-red-100 flex items-center justify-center text-red-600"><i class="fas fa-exclamation"></i></div>',
				border: 'border-red-200'
			},
			warning: {
				icon: '<div class="w-8 h-8 rounded-full bg-amber-100 flex items-center justify-center text-amber-600"><i class="fas fa-bolt"></i></div>',
				border: 'border-amber-200'
			},
			info: {
				icon: '<div class="w-8 h-8 rounded-full bg-indigo-100 flex items-center justify-center text-indigo-600"><i class="fas fa-bell"></i></div>',
				border: 'border-indigo-200'
			}
		};

		const theme = config[type] || config.info;

		// Toast 요소 생성
		const toastEl = document.createElement('div');
		toastEl.id = id;

		// [디자인 포인트]
		// 1. backdrop-blur-md: 배경이 살짝 비치는 유리 효과
		// 2. rounded-2xl: 알약처럼 둥근 모서리
		// 3. shadow-2xl: 깊이감 있는 그림자
		// 4. animate-bounce-custom: 커스텀 애니메이션 (CSS로 구현 필요 없게 tailwind 유틸리티 조합)
		toastEl.className = `
            pointer-events-auto 
            bg-white/95 backdrop-blur-md 
            border ${theme.border} 
            flex items-center gap-4 
            px-4 py-3 
            rounded-2xl 
            shadow-[0_10px_40px_-10px_rgba(0,0,0,0.15)] 
            transform transition-all duration-500 ease-[cubic-bezier(0.175,0.885,0.32,1.275)]
            -translate-y-[150%] opacity-0 scale-95
        `;

		// 내용 주입
		toastEl.innerHTML = `
            <div class="flex-shrink-0">${theme.icon}</div>
            <div class="flex-1 min-w-[200px]">
                <p class="text-[13px] font-bold text-slate-700 leading-snug">${message}</p>
            </div>
            <button onclick="document.getElementById('${id}').remove()" class="w-6 h-6 flex items-center justify-center rounded-full text-slate-300 hover:bg-slate-100 hover:text-slate-500 transition ml-2">
                <i class="fas fa-times text-xs"></i>
            </button>
        `;

		// 컨테이너에 추가 (prepend를 사용하여 최신 메시지가 위나 아래로 쌓이게 조절 가능, 여기선 append)
		container.appendChild(toastEl);

		// 애니메이션: 위에서 아래로 툭 떨어지는 효과 (Bounce)
		requestAnimationFrame(() => {
			setTimeout(() => {
				toastEl.classList.remove('-translate-y-[150%]', 'opacity-0', 'scale-95');
			}, 10);
		});

		// 자동 삭제 타이머
		setTimeout(() => {
			// 사라지는 애니메이션 (위로 다시 올라가면서 사라짐)
			toastEl.classList.add('-translate-y-[150%]', 'opacity-0', 'scale-95');

			setTimeout(() => {
				if (toastEl.parentNode) {
					toastEl.parentNode.removeChild(toastEl);
				}
			}, 500);
		}, this.displayDuration);
	},

	// 단축 메서드
	success: function (msg) {
		this.fire('success', msg);
	},
	error: function (msg) {
		this.fire('error', msg);
	},
	info: function (msg) {
		this.fire('info', msg);
	},
	warning: function (msg) {
		this.fire('warning', msg);
	}
};