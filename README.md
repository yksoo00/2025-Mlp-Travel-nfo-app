# 2025 Mlp Travel Info App

자바 콘솔 기반의 **여행 정보 관리 애플리케이션**입니다.  
사용자와 관리자가 각각 로그인하여 관광지 정보, 리뷰, 좋아요, 즐겨찾기 기능 등을 사용할 수 있습니다.

---

## 🚀 주요 기능

### 👤 사용자 기능
- 회원 가입 / 로그인 / 로그아웃
- 관광지 목록 조회
  - 전체 / 제목 검색 / 지역별 / 좋아요순 정렬
- 관광지 상세 보기
  - 기본 정보 확인
  - 리뷰 작성 / 수정 / 삭제
  - 좋아요 등록 / 해제
  - 즐겨찾기 등록 / 해제
- 마이페이지
  - 내가 작성한 리뷰 관리
  - 내 즐겨찾기 목록 조회
  - 내 좋아요 목록 조회

### 🔑 관리자 기능
- 관광지 등록
- 관광지 수정
- 관광지 삭제

---

## 🛠 기술 스택

- **Language**: Java 17+
- **Database**: MySQL (JDBC 연동)
- **IDE**: IntelliJ IDEA
- **구조**: MVC 패턴  
  - View: `TravelView`
  - Controller: 각 도메인별 컨트롤러
  - Service: 비즈니스 로직
  - DAO: 데이터베이스 접근
  - DTO: 엔티티

---

## 🗂️ 프로젝트 구조

```
2025-Mlp-Travel-nfo-app/
├─ travel_app/
│  └─ src/
│     └─ com/
│        └─ multi/
│           └─ travelapp/
│              ├─ view/
│              │  ├─ TravelView.java
│              │  └─ …
│              ├─ controller/
│              │  ├─ BookMarkController.java
│              │  ├─ LikesController.java
│              │  ├─ MemberController.java
│              │  ├─ ReviewController.java
│              │  ├─ TouristSpotController.java
│              │  └─ …
│              ├─ service/
│              │  ├─ BookMarkService.java
│              │  ├─ LikesService.java
│              │  ├─ MemberService.java
│              │  ├─ ReviewService.java
│              │  ├─ TouristSpotService.java
│              │  └─ …
│              ├─ dao/
│              │  ├─ BookMarkDao.java
│              │  ├─ LikesDao.java
│              │  ├─ MemberDao.java
│              │  ├─ ReviewDao.java
│              │  ├─ TouristSpotDao.java
│              │  └─ …
│              ├─ common/
│              │  ├─ Session.java
│              │  └─ …
│              └─ dto/
│                 ├─ BookMarkDto.java
│                 ├─ LikesDto.java
│                 ├─ MemberDto.java
│                 ├─ ReviewDto.java
│                 ├─ TouristSpotDto.java
│                 └─ SignInDto.java
├─ travel_import/
│  ├─ gradle/
│  │  └─ wrapper/
│  ├─ src/
│  │  └─ main/
│  ├─ build.gradle
│  ├─ gradlew
│  ├─ gradlew.bat
│  └─ settings.gradle
└─ README.md

> ⚠️ 프로젝트 안에 또 프로젝트가 포함되어 있는 구조라, IDE에서 `src` 폴더를 **소스 루트(Source Root)**로 지정해야 합니다.

---

```


## ⚙️ 실행 방법

### 1. 저장소 클론
```bash
git clone https://github.com/yksoo00/2025-Mlp-Travel-nfo-app.git
cd 2025-Mlp-Travel-nfo-app
```

### 2. Gradle 실행

### 3. IDE에서 소스 루트 설정
``` 
	•	IntelliJ IDEA:
	1.	File > Project Structure > Modules 메뉴 이동
	2.	src 폴더를 Sources 로 지정
	3.	JDK 버전(예: Java 17) 확인
```
### 4. DB 연결 정보 수정
```
String url = "jdbc:mysql://localhost:3306/travel_db";
String username = "root";
String password = "비밀번호";
```
