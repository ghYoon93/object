## 추상화의 힘
- 할인 정책
  - 금액 할인 정책
  - 비율 할인 정책
- 할인 조건
  - 순번 조건
  - 기간 조건

할인 정책과 할인 조건이 더 추상적인 이유는 인터페이스에 초점을 맞추기 때문이다.
추상화는 세부사항에 억눌리지 않고 상위 개념만으로도 도메인의 중요한 개념을 설명할 수 있게 한다.

- 추상화를 이용한 설계는 필요에 따라 표현의 수준을 조정하는 것을 가능하게 해준다.
- 추상화를 이용해 상위 정책을 기술한다는 것은 기본적인 애플리케이션의 협력 흐름을 기술한다는 것을 의미한다.
- 디자인 패턴이나 프레임워크 모두 추상화를 이용해 상위 정책을 정의하는 객체지향의 매커니즘을 활용하고 있다.
- 추상화를 이용해 상위 정책을 표현하면 기존 구조를 수정하지 않고도 새로운 기능을 쉽게 추가하고 확장할 수 있다.

## 유연한 설계
```java
public class Movie {
    public Money calculateMovieFee(Screening screening) {
        if ( discountPolicy == null ) {
            return fee;
        }
        
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}
```

할인 정책이 적용돼 있지 않은 영화는 어떻게 할 것인가?
위 코드를 보면 조건문으로 할인 정책이 없으면 요금 그대로를 반환하고 있다. 약간의 예외 상황으로 취급하고 있는 느낌이 있어서 일관성 있던 협력 방식이 무너진다.

책임의 위치를 결정하기 위해 조건문을 사용하는 것은 협력의 설계 측면에서 대부분의 경우 좋지 않은 선택이다.
> 항상 예외 케이스를 최소화하고 일관성을 유지할 수 있는 방법을 선택하라.

```java
public class NoneDiscountPolicy extends DiscountPolicy {
  @Override
  protected Money getDiscountAmount ( Screening screening ) {
      return Money.ZERO;
  }
}
```

```java
Movie starWars = new Movie( "스타워즈", Duration.ofMinutes(210),
        Money.wons(10000),
        new NoneDiscountPolicy());
```

코드의 구조는 추상화를 중심으로 설계하자. 그러면 유연하고 확장 가능한 설계를 만들 수 있다.
추상화가 유연한 설계를 가능하게 하는 이유는 설계가 구체적인 상황에 결합되는 것을 방지하기 때문이다.

**`Movie`는 특정한 할인 정책에 묶이지 않는다. 학인 정책을 구현한 클래스가 `DiscountPolicy`를 상속 받고 있다면 어떤 클래스와도 협력이 가능한다.**

## 추상 클래스와 인터페이스 트레이드오프
`NoneDiscountPolicy` 클래스를 만들었지만 `DiscountPolicy`에서 할인 조건이 없을 경우 `getDiscountAmount()` 를 호출하지 않는다.
그래서 `NoneDiscountPolicy` 의 `getDiscountAmount()` 를 호출할 일이 없다.

`DiscountPolicy` 클래스를 인터페이스로 변경
```java
public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
```

기존 `DiscountPolicy`를 `DefaultDiscountPolicy`로 변경하고 인터페이스를 구현하도록 수정
```java
public abstract class DefaultDiscountPolicy implements DiscountPolicy {
  ...
}
```

`NoneDiscountPolicy`와 `DiscountPolicy` 의 개념적인 혼란과 결합을 제거하기 위해 `NoneDiscountPolicy`가 `DiscountPolicy` 인터페이스를 구현하도록 변경하자.

```java
public class NoneDiscountPolicy implements DiscountPolicy {
    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
```
위와 같이 인터페이스를 사용하도록 변경한 설계가 이상적이지만 현실적으로는 `NoneDiscountPolicy`만을 위해 인터페이스를 추가하는 것이 과하다고 느낄 수도 있다.

변경 전의 `NoneDiscountPolicy`도 할인 금액이 0원이라는 사실을 효과적으로 전달한다.

구현과 관련된 모든 것들이 트레이드오프의 대상이 될 수 있다.
작성하는 모든 코드에서는 합당한 이유가 있어야 한다.
**트레이드오프를 통해 얻어진 결론과 그렇지 않은 결론 사이의 차이는 크다. 고민하고 또 고민하여 트레이드오프하자.**

## 코드 재사용
상속은 코드를 자새용하기 위해 널리 사용되는 방법이지만 가장 좋은 방법인 것은 아니다.
합성은 다른 객체의 인스턴스를 자신의 인스턴스 변수로 포함해서 재사용하는 방법을 말한다.
코드 재사용을 위해서 상속보다는 **합성(composition)**이 더 좋은 방법이라는 이야기가 있다.

`Movie`가 `DiscountPolicy` 의 코드를 재사용하는 방이 바로 합성이다.
이 설계를 상속을 사용하도록 변경할 수도 있다.
`Movie`를 직접 상속받아 `AmountDiscountMovie`와 `PercentDiscountMovie` 라는 두 개의 클래스를 추가하면 합성을 사용한 기존 방법과 기능적인 관점에서 완벽히 동일하다.

상속 대신 합성을 선호하는 이유가 무엇일까?

## 상속
**상속**은 객체지향에서 코드를 재사용하기 위해 널리 사용되는 기법이다.
두 가지 관점에서 설계에 안 좋은 영향을 미친다. 하나는 상속이 캡슐화를 위반한다는 것이고, 다른 하나는 설계를 유연하지 못하게 만드는 것이다.

**상속의 가장 큰 문제점은 캡슐화를 위반한다는 것이다.**
상속을 이용하기 위해서는 부모 클래스의 내부 구조를 잘 알고 있어야 한다.

`AmountDiscountMovie`와 `PercentDiscountMovie` 를 구현하는 개발자는 부모 클래스인 `Movie`의 `calculateMovieFee` 메서드 안에서 추상 메서드인 `getDiscountAmount` 메서드를 호출한다는 사실을 알고 있어야한다.

캡슐화의 약화는 자식 클래스가 부모 클래스에 강하게 결합되도록 만든다.

상속의 두 번째 단점은 설계가 유연하지 않다는 것이다. 상속은 부모 클래스와 자식 클래스 사이의 관계를 컴파일 시점에 결정한다. 따라서 실행 시점에 객체의 종류를 변경하는 것이 불가능하다.

실행 시점에 금액 할인 정책인 영화를 비율 할인 정책으로 변경하려면 `AmountDiscountMovie`의 인스턴스를 `PercentDiscountMovie` 로 변경해아한다.
하지만 대부분의 언어는 이미 생성된 객체의 클래스를 변경하는 기능을 지원하지 않고 그나마 최선으로는 `PercentDiscountMovie`의 인스턴스를 생성한 후 `AmountDiscountMovie`의 상태를 복사하는 것뿐이다.

반면 인스턴스 변수로 연결한 기존 방법을 사용하면 실행 시점에 할인 정책을 간단하게 변경할 수 있다.
```java
public class Movie {
    private DiscountPolicy discountPolicy;
    
    public void changeDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}
```

```java
Movie avatar = new Movie("아바타",
        Duration.ofMinutes(120),
        Money.wons(10000),
        new AmountDiscountPolicy(Money.wons(800), ...));

avatar.changeDiscountPolicy(new PercentPolicy(0.1, ...));
```
`Movie`가 `DiscountPolicy` 의 코드를 재사용하는 이 방법은 너무나도 유용하기 때문에 특별한 이름으로 불린다.

## 합성