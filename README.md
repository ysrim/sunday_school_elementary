# ⚔️ 봉동중앙교회 초등부 RPG (Sunday School RPG)

> **"말씀으로 성장하고, 경험치를 모아 레벨업하자! 달란트는 덤~ "**
>
> 봉동중앙교회 초등부 학생들을 위한 신앙 성장 게이미피케이션 플랫폼입니다.

## 📖 프로젝트 소개
이 프로젝트는 초등부 학생들이 주일학교 활동(출석, 말씀 읽기, 퀘스트 수행)을 통해 **RPG 게임처럼 경험치(EXP)와 달란트**를 획득하며 즐겁게 신앙생활을 할 수 있도록 돕는 웹 애플리케이션입니다.

학생들은 자신만의 캐릭터(전사, 마법사 등)를 선택하고, 반별 길드 활동을 통해 소속감을 느낄 수 있습니다.

## ✨ 주요 기능

| 카테고리 | 기능 설명 |
|:---:|:---|
| **👤 회원/캐릭터** | • 회원가입 시 6가지 직업(전사, 성기사, 궁수, 치유사, 마법사, 수호자) 선택<br>• Jsoup 기반 XSS 방지 및 유효성 검사 적용 |
| **🏠 마이페이지** | • 레벨, 경험치(EXP), 보유 달란트 실시간 확인<br>• 경험치 바 애니메이션 및 성장 시각화 |
| **📅 출석 체크** | • 매일/주일 출석 스탬프 시스템<br>• 출석 시 자동 달란트 지급 및 애니메이션 효과 |
| **🛡️ 길드 시스템** | • 반(Class)별 자동 길드 매칭<br>• 길드원 목록 및 길드 레벨/공동 목표 달성 현황 확인 |
| **📜 퀘스트** | • 주간 미션 (말씀 읽기, 기도하기 등) 수행 체크<br>• 퀘스트 완료 시 보상 지급 |

## 🛠️ 기술 스택 (Tech Stack)

### Backend
![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Framework](https://img.shields.io/badge/Spring_Legacy-6.2.15-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MyBatis](https://img.shields.io/badge/MyBatis-3.5.19-black?style=for-the-badge)
![MariaDB](https://img.shields.io/badge/MariaDB-10.x-003545?style=for-the-badge&logo=mariadb&logoColor=white)

### Frontend
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![jQuery](https://img.shields.io/badge/jQuery-3.6.0-0769AD?style=for-the-badge&logo=jquery&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Legacy_JSP_Mixed-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)

### Infrastructure & Tools
![Maven](https://img.shields.io/badge/Maven-3.8-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Tomcat](https://img.shields.io/badge/Tomcat-9.0-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

## 📂 프로젝트 구조

```bash
src
├── main
│   ├── java
│   │   ├── app          # 비즈니스 로직 (Controller, Service, VO)
│   │   │   ├── idx      # 인덱스/로그인/회원가입 (Intro, Login, Join)
│   │   │   └── psn      # 개인화 서비스 (Student)
│   │   ├── com          # 공통 설정 및 유틸리티
│   │   │   ├── config   # Spring Java Config (Web, DB, Thymeleaf)
│   │   │   ├── base     # 공통 모듈 (Interceptor, Security, Utils)
│   │   │   └── init     # WebAppInitializer
│   └── resources
│       ├── spring
│       │   ├── prop     # 환경 설정 파일 (db.properties 등)
│       │   └── sqlmap   # MyBatis Mapper XML
│       └── logback.xml  # 로깅 설정
└── webapp
    ├── WEB-INF
    │   └── view         # JSP & Thymeleaf Templates
    └── files            # 정적 리소스 (CSS, JS, Images)
