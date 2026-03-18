// 캐시 이름 지정 (업데이트 시 버전을 올려주세요)
const CACHE_NAME = 'sunday-school-v1';

// 오프라인에서도 보여줄 기본 파일 목록 (추후 필요한 파일 경로를 더 추가하세요)
const ASSETS_TO_CACHE = [
	'/',
	'/manifest.json'
];

// 1. 서비스 워커 설치 및 리소스 캐싱
self.addEventListener('install', (event) => {
	event.waitUntil(
		caches.open(CACHE_NAME).then((cache) => {
			console.log('캐시 저장 완료');
			return cache.addAll(ASSETS_TO_CACHE);
		})
	);
});

// 2. 네트워크 요청 가로채기 (캐시에 있으면 캐시 반환, 없으면 서버 요청)
self.addEventListener('fetch', (event) => {
	event.respondWith(
		caches.match(event.request).then((response) => {
			return response || fetch(event.request);
		})
	);
});