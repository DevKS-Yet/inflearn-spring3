## 2022-02-16

###서블릿과 JSP의 한계
- 서블릿으로 개발할 때는 뷰 화면을 위한 HTML을 만드는 작업이 자바 코드에 섞여서 지저분하고 복잡했음.
- JSP를 사용하면서 나름 깔끔해졌지만 비즈니스 로직이 같이 있으면 로직이 노출된다.

### MVC 패턴의 등장
- 비즈니스 로직은 서블릿처럼 다른 곳에서 처리하고 HTML은 뷰 화면에 집중할 수 있도록 개발한 것!

### MVC 패턴 - 개요
- JSP나 서블릿에 비즈니스 로직과 뷰 렌더링까지 처리하여 너무 많은 역할이 부여됨
- UI와 비즈니스 로직의 일부를 수정할 경우 대부분 서로에게 영향을 주지 않음으로 각각 나누어 두는 것이 좋다. 변경의 라이프 사이클이 다른 것을 따로 유지

#### MVC 설명
- 컨트롤러 : 비즈니스 로직
- 모델 : 데이터
- 뷰 : 로직
- MVC1 : `뷰`-`컨트롤러`-`모델`-`컨트롤러`-`뷰` (;요런 느낌으로!)
- MVC2 : `뷰`-`컨트롤러`-`비즈니스 로직`-`모델`-`컨트롤러`-`뷰`  
  국비학원에서 한 경우에는 `Repository`가 아니라 `ServiceImpl`로 한 느낌

#### 알면 좋은 것?
- WEB-INF 안에 있는 jsp들은 주소창에 절대경로를 적어도 찾아지지 않음. 항상 컨트롤러를 거쳐서만 갈 수 있음
- 요즘엔 JSP는 사장되고 있으면 타임리프나 다른 템플릿을 사용하는 추세

### MVC 패턴의 한계
- 포워드 중복
- ViewPath 중복(prefix:`WEB-INF/views/`, suffix:`.jsp`)
- 항상 request, response를 사용하지 않지만 매번 둘다 받아옴
- 공통 처리가 어려움
- 해당 단점을 보완하기 위해서 프론트 컨트롤러 패턴을 도입하게 됨

## 2022-02-17

### 프론트 컨트롤러 도입 - v1
- 요청 -> `Front Controller` -> `mapping` -> `Controller` -> `JSP`
- 요청이 들어올 때 공통적으로 실행되는 메서드 제거

### View 분리 - v2
- 요청 -> `Front Controller` -> `mapping` -> `Controller` -> `Front Controller` -> `MyView` -> `JSP`
- Dispatcher 부분을 따로 클래스로 만들어서 해당 공통 메서드 제거

## 2022-02-20

#### 실전에서는 Map을 사용하지 않는다고 했으니 Map을 concurrentMap과 AtomicLong으로 바꾸는 연습
- 일차적으로 `MemberRepository2`에선 `Map`과 `Long`만 `ConcurrentHashMap`과 `AtomicLong`으로 변경
- 일차적으로 생각드는 것은 `ConcurrentHashMap`을 사용했을 때 제네릭도 Atomic으로 변경해야하는가? -> 일단은 변경하지 않음. 왜냐면 `ConcurrentHashMap`이 벌써 Serializable를 받고있기 때문에 필요없다고 판단
- 두 번째로는 AtomicLong을 사용할거라고 하면 `Member` 클래스에 있는 `Long id`도 바꿔야하는가? - 안바꿈. 왜냐면 멤버라는 객체를 만들때 수행되는 작업이지만 여기서는 저장을 눌렀을 시에 수행되게끔 되어있어서 최대한 기본 코드를 따라감. 하지만 객체 안에 만들어두어도 괜찮지 않을까 생각은 듬

## 2022-02-21

### Model 추가 - v3
- 요청 -> `Front Controller` -> `mapping` -> `Controller` -> `Front Controller` -> `viewResolver` -> `Front Controller` -> `MyView`
- 서블릿 종속성 제거(request, response)
- 뷰 이름 중복 제거(WEB-INF/views)

## 2022-02-24

### 단순하고 실용적인 컨트롤러 - v4
- `ModelView` 객체를 생성하고 반환하는 점 제거
- **좋은 프레임워크는 아키텍쳐도 중요하지만, 개발자가 단순하고 편리하게 사용할 수 있어야한다.**
- 만든 사람이 힘들면 사용하는 사람이 편하다!!!!

## 2022-02-25

### 유연한 컨트롤러1 - v5
- 만약 어떤 개발자는 `ControllerV3`를 사용하고 싶고 다른 개발자는 `ControllerV4`로 개발을 하고 싶다면??
- 어댑터 패턴을 사용하면 해당 문제점을 해결 할 수 있음
- 어댑터 패턴이란 두개가 서로 안맞을 때 사이에 하나는 껴둠으로 변경 가능
- 요청 -> `Front Controller` -> `mapping` -> `handler adapter list` -> `handler adapter` -> `handler(controller)` -> `Front Controller` -> `viewResolver` -> `Front Controller` -> `MyView`

## 2022-02-26

### 정리
- v1 : 기존 구조를 최대한 유지하면서 프론트 컨트롤러를 도입
- v2 : 단순 반복되는 뷰 로직 분리
- v3 : 서블릿 종속성 및 뷰 이름 중복 제거
- v4 : v3와 거의 비슷하지만 ModelView를 직접 생성 및 반환하지 않도록 인터페이스 제공
- v5 : 어댑터 패턴 도입하여 프레임워크를 유연하고 확장성 있게 설계