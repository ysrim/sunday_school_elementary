// 1. 캐시 설정 (기존 코드 유지)
const CACHE_NAME = 'sunday-school-v1';
const ASSETS_TO_CACHE = [
	'/',
	'/manifest.json',
	'https://cdn.jsdelivr.net/gh/ysrim/bjcc_ss@main/images/pwa/icon-192.png' // 푸시 알림에 사용할 아이콘도 캐싱 권장
];

// 서비스 워커 설치
self.addEventListener('install', (event) => {
	self.skipWaiting(); // 새로운 서비스 워커가 즉시 제어권을 갖도록 함
	event.waitUntil(
		caches.open(CACHE_NAME).then((cache) => {
			console.log('캐시 저장 완료');
			return cache.addAll(ASSETS_TO_CACHE);
		})
	);
});

// 2. 네트워크 요청 가로채기 (기존 코드 유지)
self.addEventListener('fetch', (event) => {
	event.respondWith(
		caches.match(event.request).then((response) => {
			return response || fetch(event.request);
		})
	);
});

// -------------------------------------------------------------
// 3. 푸시 알림 수신 이벤트 (추가됨)
// -------------------------------------------------------------
self.addEventListener('push', (event) => {
	if (!event.data) {
		console.log('푸시 데이터가 없습니다.');
		return;
	}

	let data = {};
	try {
		// 서버에서 보낸 JSON 데이터를 파싱
		data = event.data.json();
	} catch (e) {
		// 텍스트로 올 경우를 대비한 예외 처리
		data = {title: '알림', body: event.data.text()};
	}

	const options = {
		body: data.body || '새로운 알림이 있습니다.',
		icon: 'https://cdn.jsdelivr.net/gh/ysrim/bjcc_ss@main/images/pwa/icon-192.png',      // 앱 아이콘 경로
		badge: 'https://cdn.jsdelivr.net/gh/ysrim/bjcc_ss@main/images/pwa/icon-192.png',     // iOS/Android 상태표시줄 아이콘
		vibrate: [100, 50, 100],
		data: {
			click_url: data.url || '/'       // 클릭 시 이동할 URL (없으면 메인으로)
		}
	};

	event.waitUntil(
		self.registration.showNotification(data.title || 'Sunday School', options)
	);
});

// -------------------------------------------------------------
// 4. 알림 클릭 이벤트 (추가됨)
// -------------------------------------------------------------
self.addEventListener('notificationclick', (event) => {
	event.notification.close(); // 알림창 닫기

	const targetUrl = event.notification.data.click_url;

	event.waitUntil(
		clients.matchAll({type: 'window', includeUncontrolled: true}).then((windowClients) => {
			// 이미 앱이 열려있다면 해당 창으로 포커스 이동
			for (let client of windowClients) {
				if (client.url.includes(targetUrl) && 'focus' in client) {
					return client.focus();
				}
			}
			// 앱이 닫혀있다면 새로운 창(PWA 모드)으로 열기
			if (clients.openWindow) {
				return clients.openWindow(targetUrl);
			}
		})
	);
});