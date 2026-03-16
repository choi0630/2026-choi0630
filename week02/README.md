# Week 02 — Kotlin 콘솔 실습: Smart Task & Note Manager (구조화 + 컬렉션)

이번 주는 Week01 메뉴 프로그램을 확장하여, **Smart Task & Note 콘솔 앱**을 만들며
Kotlin의 핵심 문법을 “서비스 형태”로 구조화합니다.

- 수업 형태: Android Studio에서 **콘솔 프로젝트**로 진행
- 제출: **실습 과제 제출 있음**

---

## 링크 안내
- 강의자료(PDF): https://ecampus.konkuk.ac.kr
- 과제 제출(Classroom): <CLASSROOM_URL>

> 이 저장소는 읽기 전용입니다.  
> 과제는 GitHub Classroom에서 생성된 개인 저장소에 작성 후 `push`로 제출합니다.

---

## 학습 목표
- `data class`, `enum class`로 데이터 모델을 만든다.
- `MutableList` 기반으로 CRUD(추가/조회/검색/토글/삭제)를 구현한다.
- 로직을 `data` / `service` / `ui` / `util` 로 분리한다.
- 고차함수(`filter`, `sortedBy`, `forEachIndexed`)를 사용해 데이터 처리를 한다.
- 확장함수로 입력 처리 로직을 재사용 가능하게 만든다.

---

## 이번 주 프로젝트 구조(권장)
아래 패키지를 아래처럼 구성합니다.
ui/ - 콘솔 UI(main)만 담당
data/model - data class, enum
data/repo - Repository 클래스
service/ - 비즈니스 로직
util/ - 입력 처리/시간 유틸


### 필수 동작
- [ ] 항목 추가: `type(TASK/NOTE)`, `title`, (선택) `priority(P1/P2/P3)`
- [ ] 목록 출력: 완료 항목 포함/제외 선택
- [ ] 검색: 키워드 부분 문자열 검색(대소문자 무시)
- [ ] 완료 토글: id를 입력 받아 done을 true/false 전환
- [ ] 삭제: id를 입력 받아 삭제
- [ ] 입력 방어: 숫자 변환 실패/범위 밖 처리(프로그램이 죽지 않기)

---

## 실습 과제(제출용)
이번 주 과제는 **미완성 메뉴를 구현**하여 기능을 완성하는 것입니다.

### 채점 기준
- [ ] 프로그램이 예외 없이 실행됨
- [ ] 1~6번 기능이 모두 동작함(최소 5개 데이터로 테스트)
- [ ] 패키지 구조를 유지하고(main에 로직을 과도하게 넣지 않음)

### 제출 방법
1) GitHub Classroom 과제 링크로 개인 저장소 생성
2) clone 프로젝트 생성 후 구현
3) `git add .` → `git commit -m "Week02 complete"` → `git push`
4) 프로젝트 내부에 README 파일을 생성하여, 아래 내용을 포함하여 보고서로 제출할 것
   - 아래의 체크리스트 포함
   - 메뉴별 실행결과 이미지 또는 gif로 포함
---

## 체크리스트
- [ ] 숫자 아닌 입력을 넣어도 죽지 않는다.
- [ ] 추가 → 목록 → 검색 → 토글 → 삭제가 모두 정상 동작한다.
- [ ] 존재하지 않는 id 처리(“해당 id 없음”)가 있다.
- [ ] 코드가 패키지별로 분리되어 있다.

---

## 다음 주 예고(Week 03)
Jetpack Compose로 UI를 구성하고, 이번 주에 만든 도메인/서비스 구조를
State(상태)와 연결하여 “화면에서 데이터가 바뀌는 앱”으로 확장하는 수업을 진행합니다.

