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


## 상속


## 합성