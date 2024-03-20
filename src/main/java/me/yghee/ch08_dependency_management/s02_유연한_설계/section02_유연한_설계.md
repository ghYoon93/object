## 의존성과 결합도
- 의존성은 객체들의 협력을 가능하게 만드는 매개체라는 관점에서는 바람직한 것이다.
- 의존성이 과하면 문제가 될 수도 있다.
- 의존성이 무조건 문제는 아니다. 문제는 의존성의 정도다.
  - 구체적인 클래스에 의존하면 다른 문맥에서 재사용할 수 없다.
- 바람직한 의존성은 **재사용성**과 관련이 있다.
  - 즉 컨텍스트에 독립적인 의존성이다.
  - 바람직한 의존성은 결합도가 약하다고 말한다.

## 지식이 결합을 낳는다
- 결합도의 정도는 한 요소가 자신이 의존하고 있는 다른 요소에 대해 알고 있는 정보의 양으로 결정된다.
- 결합도를 느슨하게 유지하려면 협력하는 대상에 대해 더 적게 알아야 한다.
- 추상화가 이 목적을 달성할 수 있는 가장 효과적인 방법이다.

## 추상화에 의존하라
- 추상화
  - 어떤 양상, 세부사항, 구조를 좀 더 명확하게 이해하기 위해 특정 절차나 물체를 의도적으로 생략하거나 감춤으로써 복잡도를 극복하는 방법이다.
- 추상화와 결합도의 관점에서 의존 대상 구분
  - 구체 클래스 의존성(concrete class dependency)
  - 추상 클래스 의존성(abstract class dependency)
  - 인터페이스 의존성(interface dependency)
- 의존하는 대상이 더 추상적일수록 결합도는 더 낮아진다.

## 명시적인 의존성
- 클래스 안에서 구체 클래스에 대한 모든 의존성을 제거해야하지만 런타임 시 의존성을 해결할 방법이 필요하다.
  - 명시적인 의존성: 퍼블릭 인터페이스를 통해 의존하는 대상을 명시하는 것
- 의존성은 명시적으로 표현돼야 한다. 의존성을 구현 내부에 숨겨두지 마라.
- 
## new는 해롭다
- new가 해로운 이유
  - new 연산자를 사용하기 위해서는 구체 클래스의 이름을 직접 기술해야 한다. 따라서 new를 사용하는 클라이언트는 추상화가 아닌 구체 클래스에 의존할 수 밖에 없기 때문에 결합도가 높아지낟.
  - new 연산자는 생성하려는 구체 클래스뿐만 아니라 어떤 인자를 이용해 클래스의 생성자를 호출해야 하는지도 알아야 한다. 따라서 new를 사용하면 클라이언트가 알아야 하는 지식의 양이 늘어나기 때문에 결합도가 높아진다.
- 메시지에 대한 의존성 외의 모든 다른 의존성은 결합도를 높이는 불필요한 의존성이다.
- 해결 방법
  - 인스턴스를 생성하는 로직과 생성된 인스턴스를 사용하는 로직을 분리하는 것이다.
  - 외부로부터 이미 생성된 인스턴스를 전달받아야 한다.
- 누가 인스턴스를 생성하는가?
  - 클라이언트가 처리한다.
- 생성의 책임은 클라이언트에 있다.
- 설계를 유연하게 만드는 방법
  - 사용과 생성의 책임 분리
  - 명시적 의존성
  - 추상클래스에 의존

## 가끔은 생성해도 무방하다
- 협력하는 기본 객체를 설정하고 싶은 경우
- 대부분 A 인스턴스와 협력하고 가끔씩만 B 인스턴스와 협력하는 경우
  - 기본 객체를 생성하는 생성자를 추가하고 이 생성자에서 의존하는 대상의 인스턴스를 인자로 받는 생성자를 체이닝하는 것
  - 다양한 컨텍스트에서 유연하게 사용될 수 있는 여지를 제공
```java
public class Movie {
    private DiscountPolicy discountPolicy;
    
    public Movie(String title, Duration runningTime, Money fee) {
        this(title, runningTime, fee, new AmountDiscountPolicy(...));
    }
    
    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        ...
        this.discountPolicy = discountPolicy;
    }
}
```
메서드 오버로딩
```java
public class Movie {
    public Money calculateMovieFee(Screening screening) {
        return calculateMovieFee( screening, new AmountDiscountPolicy(...) );
    }
    
    public Money calculateMovieFee(Screening screening, DiscountPolicy discountPolicy) {
        return fee.minus(discountPolicy.calcualteDiscountAmount(screening));
    }
}
```
- 트레이드오프로 구체클래스를 사용하여 결합도를 높히더라도 클래스의 사용성을 높일 수 있다.
- 종종 모든 결합도가 모이는 새로운 클래스를 추가함으로써 사용성과 유연성이라는 두 마리 토끼를 잡을 수 있는 경우도 있다.

## 표준 클래스에 대한 의존은 해롭지 않다.
- 변경될 확률이 거의 없는 표준 클래스는 의존성이 문제가 되지 않는다.
- 의존성에 의한 영향이 적은 경우에도 추상화에 의존하고 의존성을 명시적으로 드러내는 것은 좋은 설계 습관이다.

## 컨텍스트 확장하기
- 다른 컨텍스트에서 `Movie`를 확장해서 재사용하는 두 가지 예
  - 할인 혜택을 제공하지 않는 영화
  - 다수의 할인 정책을 중복해서 적용하는 영화

### 할인 혜택을 제공하지 않는 영화
```java
public class Movie {
    public Movie(String title, Duration runningTime, Money fee) {
        this(title, runningTime, fee, null);
    }
    public Movie(String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    public Money calculateMovieFee(Screening screening) {
        if (discountPolicy == null ) {
            return fee;
        }
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }

```
- 위 코드는 할인 정책이 없는 예외 경우를 처리하기 위해 내부 코드를 직접 수정해야 핬다.
- 어떤 경우든 코드 내부를 직접 수정하는 것은 버그의 바랭 가능성을 높이는 것이다.

- 해결 방법
  - 할인 정책이 존재하지 않는다는 사실을 할인 정책의 한 종류로 간주하는 것이다.
```java
public class NoneDiscountPolicy extends DiscountPolicy {
    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
```

### 다수의 할인 정책을 중복해서 적용하는 영화
- 가장 간단하게 구현할 수 있는 방법은 DiscountPolicy의 인스턴스들로 구성된 List를 인스턴스 변수로 갖게 하는 것이다.
  - 중복 할인 정책을 구현하기 위해 기존의 할인 정책의 협력 방식과는 다른 예외 케이스를 추가하게 만든다.

- 해결 방법
  - 중복 할인 정책을 할인 정책의 한 가지로 간주하는 것(OverlappedDiscountPolicy를 DiscountPolicy의 자식 클래스로 만들면 기존 협력 방식을 수정하지 않고도 여러 개의 할인 정책을 적용할 수 있다.)

```java
import me.yghee.ch05_responsibility_assignment.code.Screening;

import java.util.ArrayList;

public class OverlappedDiscountPolicy extends DiscountPolicy {
    private List<DiscountPolicy> discountPolicies = new ArrayList<>();

    public OverlappedDiscountPolicy ( DiscountPolicy... discountPolicies ) {
        this.discountPolicies = Arrays.asList( discountPolicies );
    }

    @Override
    protected Money getDiscountAmount ( Screening screening ) {
        Money result = Money.ZERO;
        for(DiscountPolicy each : discountPolicies) {
            result = result.plus(each.calculateDiscountAmount(screening));
        }
        
        return result;
    }
}
```

## 조합 가능한 행동
- 필요한 컨텍스트에서 `Movie`를 재사용할 수 있었던 이유
  - 코드를 직접 수정하지 않고도 협력 대상인 `DiscountPolicy` 인스턴스를 교체할 수 있었기 때문이다.
- 어떤 객체와 협력하느냐에 따라 객체의 행동이 달라지는 것은 유연하고 재사용 가능한 설계가 가진 특징이다.
- 유연하고 재사용 가능한 설계는 객체가 어떻게(how) 하는지를 장황하게 나열하지 않고도 객체들의 조합을 통해 무엇(what)을 하는지를 표현하는 클래스들로 구성된다.
  - 선언적으로 객체의 행동을 정의할 수 있다.
- 유연하고 재사용 가능한 설계는 작은 객체들의 행동을 조합함으로써 새로운 행동을 이끌어낼 수 있는 설계다.
- 객체 네트워크의 행위에 대한 선언적인 정의
  - 객체 구성을 관리할 목적으로 작성하는 코드 
