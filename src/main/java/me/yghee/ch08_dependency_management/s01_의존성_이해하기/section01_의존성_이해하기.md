## 변경과 의존성
- 객체가 협력하기 위해 다른 객체를 필요로 할 때 두 객체 사이에 의존성이 존재한다.
- 실행 시점: 의존하는 객체가 정상적으로 동작하기 위해서는 실행 시에 의존 대상 객체가 반드시 존재해야 한다.
- 구현 시점: 의존 대상 객체가 변경될 경우 의존하는 객체도 함께 변경된다.
- `PeriodCondition`은 `Screening`에 의존하며 `Screening`으로 향하는 점선 화살표로 표시한다.
- `PeriodCondition`이 가지는 의존성
  - `DiscountCondtion`: 구현에 의한 의존성
  - `DayOfWeek`: 인스턴수 변수 의존성
  - `LocalTime`: 인스턴수 변수 의존성
  - `Screening`: 메서드 인자 의존성

## 의존성 전이
- **의존성 전이(transitive dependency)**
  - 의존 대상 객체의 의존성이 의존하는 객체에도 전파될 가능성이 있는 것
  - 의존성의 종류
    - 직접 의존성(direct dependency)
    - 간접 의존성(indirect dependency)

## 런타임 의존성과 컴파일타임 의존성
- 런타임 의존성
  - 객체 사이
- 컴파일타임 의존성
  - 클래스 사이
- 클래스는 자신과 협력할 객체의 구체적인 클래스에 대해 알아서는 안 된다.
- 유연하고 재사용 가능한 설계를 창조하기 위해서는 동일한 소스코드 구조를 가지고 다양한 실행 구조를 만들 수 있어야 한다.

## 컨텍스트 독립성
- 클래스가 특정한 문맥에 강하게 결합될수록 다른 문맥에서 사용하기는 더 어려워진다.
- 컨텍스트 독립성
  - 클래스가 사용될 특정한 문맥에 대해 최소한의 가정만으로 이뤄져 있다면 다른 문맥에서 재사용하기가 더 수월해진다.

## 의존성 해결하기
- 컴파일타임 의존성을 실행 컨텍스트에 맞는 적절한 런타임 의존성으로 교체하는 것.
  - 객체를 생성하는 시점에 생성자를 통해 의존성 해결
    - 의존 대상이 필수일 경우
  - 객체 생성 후 setter 메서드를 통해 의존성 해결
    - 의존 대상을 변경할 수 있는 가능성을 열어 놓고 싶은 경우
    - 보통 생성자 방식과 setter 방식을 혼합해서 사용한다.
  - 메서드 실행 시 인자를 이용해 의존성 해결
    - 항상 의존할 필요까지 없고 일시적으로 알아도 무방할 경우
