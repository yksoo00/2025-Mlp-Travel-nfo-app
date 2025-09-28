# 2025-Mlp-Travel-nfo-app
20250 멀티캠퍼스 스켈레톤 프로젝트 관광지
# Code Convention

## ☑️ 코드 컨벤션
fdasfsdfdsfad
<aside>
<img src="https://cdn-icons-png.flaticon.com/512/7350/7350737.png" alt="https://cdn-icons-png.flaticon.com/512/7350/7350737.png" width="40px" /> **문자열을 처리할 때는 쌍따옴표를 사용하도록 합니다.**

</aside>

<aside>
🐫 **함수명, 변수명은 카멜케이스로 작성합니다.**

</aside>

<aside>
🔠 생성자 함수명의 맨 앞글자는 대문자로 합니다.

```jsx
function Person(){}
```

</aside>

<aside>
☝ **가독성을 위해 한 줄에 하나의 문장만 작성합니다.**

</aside>

<aside>
<img src="https://cdn-icons-png.flaticon.com/512/3602/3602241.png" alt="https://cdn-icons-png.flaticon.com/512/3602/3602241.png" width="40px" /> **주석은 설명하려는 구문에 맞춰 들여쓰기 합니다.**

```jsx
// Good
function someFunction() {
  ...

  // statement에 관한 주석
  statements
}
```

</aside>

<aside>
<img src="https://cdn-icons-png.flaticon.com/512/3978/3978575.png" alt="https://cdn-icons-png.flaticon.com/512/3978/3978575.png" width="40px" /> **연산자 사이에는 공백을 추가하여 가독성을 높입니다.**

```jsx
a+b+c+d // bad
a + b + c + d // good
```

</aside>

<aside>
☝ **콤마 다음에 값이 올 경우 공백을 추가하여 가독성을 높입니다.**

```jsx
var arr = [1,2,3,4]; //bad
var arr = [1, 2, 3, 4]; //good
```

</aside>

---

### ☑️ 코드 컨벤션이 필요한 이유

- 팀원끼리 코드를 공유하기 때문에 일관성있는 코드를 작성하면 서로 이해하기 쉽다.
- 나중에 입사 지원 시 프로젝트를 하며 코드 컨벤션을 만들어 진행했다고 하면 협업 면에서 유리하게 작용할 수 있다.

### 참고

[코딩컨벤션](https://ui.toast.com/fe-guide/ko_CODING-CONVENTION)
# 주석규칙

/**

- @name : SOO HYUN
- @function:함수명 - 함수기능
- @param : 변수명, 변수명

*/

```java
/**
* @package : 패키지명
* @name : 클래스명
* @create-date: 2022.07.08
* @author : 작성자명
* @version : 1.0.0
* 
* @update-date : 2022.07.10
* @update-author : 000
* @update-description : asdfasdf
*/
class 

/**
* @method:함수명 - 함수기능
* @param : 변수명 - 변수기능, 변수명 - 변수기능
* @create-date: 2022.07.08
* @author : 작성자명
* @version : 1.0.0
* 
* @update-date : 2022.07.10
* @update-author : 000
* @update-description : asdfasdf
*/
method
```

```jsx
/**
*  @name : SOO HYUN
*  @function:함수명 - 함수기능
*  @param : 변수명 - 변수기능, 변수명 - 변수기능
*  @create-date : 2022.07.08
*  @update-date : 2022.07.08
*  @update-dscrib : asdda
*   
*/
function asdf(var asdf, var asdf)
```


# 깃허브 CLI PR 시나리오

1. task 시작 하기 전에 issue에 등록 (커밋 컨벤션 참고해서 말머리와 Labels 선택)

[![관광지 프로젝트 스크린샷](https://prod-files-secure.s3.us-west-2.amazonaws.com/5a3cad5b-edbc-4aee-a0ad-b8dd5d9c4c4d/d946b671-a907-4325-96f9-cd1220b3f9e1/Untitled.png)](https://prod-files-secure.s3.us-west-2.amazonaws.com/5a3cad5b-edbc-4aee-a0ad-b8dd5d9c4c4d/d946b671-a907-4325-96f9-cd1220b3f9e1/Untitled.png)
2. issue 번호대로 브랜치 생성 (기능 n= issue 번호 : Feat/#n, Chore/#n) 

![브랜치 생성 화면](https://prod-files-secure.s3.us-west-2.amazonaws.com/5a3cad5b-edbc-4aee-a0ad-b8dd5d9c4c4d/3434bbbe-18e2-474a-b840-a2c632e9428b/Untitled.png)

3. 커밋 컨벤션 참고해서 커밋 메세지 작성 후 푸시  
예) Chore : [Readme.md](http://readme.md/) 수정

![커밋 및 푸시 화면](https://prod-files-secure.s3.us-west-2.amazonaws.com/5a3cad5b-edbc-4aee-a0ad-b8dd5d9c4c4d/206e0144-1fe1-4d86-aac7-d477daad61f8/%E1%84%89%E1%85%B3%E1%84%8F%E1%85%B3%E1%84%85%E1%85%B5%E1%86%AB%E1%84%89%E1%85%A3%E1%86%BA_2023-07-08_%E1%84%8B%E1%85%A9%E1%84%92%E1%85%AE_8.14.43.png)

> 푸시 할 때는 feature 브랜치가 아닌 자신이 issue 번호 붙여 생성한 브랜치로 할 것!  

[명령어 참고](https://developer0809.tistory.com/30)

![푸시 화면](https://prod-files-secure.s3.us-west-2.amazonaws.com/5a3cad5b-edbc-4aee-a0ad-b8dd5d9c4c4d/f0fab478-4310-4182-ae7c-f2248d3dc226/Untitled.png)

4. feature 브랜치에 Pull Request 날리기 (Assignees, Labels 맞는거 고르기)

> 이후 코드 수정 후 push하기 전에 git pull 먼저 하기

[GitHub PR 방법 - YouTube](https://www.youtube.com/watch?v=Z9dvM7qgN9s)  
[Git PR 보내는 방법 - Inpa Tistory](https://inpa.tistory.com/entry/GIT-⚡️-깃헙-PRPull-Request-보내는-방법-folk-issue)  
[Git First Pull Request Story](https://wayhome25.github.io/git/2017/07/08/git-first-pull-request-story/)


# Git 브랜치 전략

저희 팀은 임

## Branch 전략

- **master** : 배포용 브랜치 → 배포를 진행하는 브랜치
- **develop** : 다음 배포 버전을 개발하는 브랜치
- **feature** : 기능을 개발하는 브랜치 → 평소 개발할 때 사용

feature에 머지 완료된 브랜치는 또 사용할 수도 있는 브랜치를 제외하고는 삭제 부탁드립니다!

### Branch 이름

각 기능 개발은 이슈생성 후에 진행해주시면 됩니다!

`BE/#이슈넘버` or `FE/#이슈넘버`

ex) `BE/#30` or `FE/#30`

Fear/#30

- 참고
    
    [우린 Git-flow를 사용하고 있어요 | 우아한형제들 기술블로그](https://techblog.woowahan.com/2553/)


# 깃 충돌 방지

develop에 누가 코드를 merge 했든 안 했든 앞으로 이런 습관 들이면 왠만하면 충돌업슴

- 코드 수정 및 구현을 한다
- 내 코드가 잘 굴러가는지 테스트한다
- 잘 된다? 그럼 이제 깃허브에 코드를 올려야겠쥥?
- git status
- 지금 내가 수정 한 코드들의 폴더들 확인하는 명령어
- 혹시 .env와 같은 올리면 안 되는 코드들이 있을 수 있으니 먼저 확인하는 습관 들일 것
- github에 올라가면 처치 곤란쓰~~
- git branch
- 현재 내가 체크아웃된 브랜치 확인 명령어
- 브랜치 앞에 별표시 되어 있음
- git add .
- git coimmit -m “feat : 어쩌구”
- git switch develop
- git pull
- 커밋까지만 하고 먼저 develop(메인 디폴트 브랜치)로 이동한 다음에 Pull해서 최신사항으로 만들어 놓을 것
- git switch 작업브랜치
- git merge develop
- 이 때 충돌 발생하면 잡는거
- git push
- github 돌아가서 pull request ㄱㄱㄱ
