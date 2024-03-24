- 상속으로 인한 피해를 최소화할 수 있는 방법.
  - 추상화

# 추상화에 의존하자
- 강한 결합을 해결하는 가장 일반적인 방법
  - 자식 클래스가 부모 클래스의 구현이 아닌 추상화에 의존하도록 만드는 것.
- 코드 중복을 제거하기 위해 상속을 도입할 때 따르는 두 가지 우너칙
  - 두 메서드가 유사하게 보인다면 차이점을 메서드로 추출하라. 메서드 추출을 통해 두 메서드를 동일한 형태로 보이도록 만들 수 있다.
  - 부모 클래스의 코드를 하위로 내리지 말고 자식 클래스의 코드를 상위로 올려라. 부모 클래스의 구체적인 메서드를 자식 클래스로 내리는 것보다 자식 클래스의 추상적인 메서드를 부모 클래스로 올리는 것이 재사용성과 응집도 측면에서 더 뛰어난 결과를 얻을 수 있다.

# 차이를 메서드로 추출하라
- 변하는 것으로부터 변하지 않는 것을 분리하라.
- 변하는 부분을 찾고 이를 캡슐화하라

중복 코드를 가진 Phone

```java
import java.util.ArrayList;

public class Phone {
    private Money amount;
    private Duration seconds;
    private List<call> calls = new ArrayList<>();
    
    public Phone(Money amount, Duration seconds) {
        this.amount = amount;
        this.seconds = seconds;
    }
    
    public Money calculateFee() {
        Money result = Money.ZERO;
        
        for(Call call : calls ) {
            result = result.plus(amount.times(call.getDuration().getSeconds() / seconds.getSeconds()));
        }
        
        return result;
    }
}
```

중복 코드를 가진 `NightlyDiscountPhone`
```java
public class NightlyDiscountPhone {
    private static final int LATE_NIGHT_HOUR = 22;
    private Money nightlyAmount;
    private Money regularAmount;
    private Duration seconds;
    private List<call> calls = new ArrayList<>();

    public NightlyDiscountPhone ( Money nightlyAmount, Money regularAmount, Duration seconds ) {
        this.nightlyAmount = nightlyAmount;
        this.regularAmount = regularAmount;
        this.seconds = seconds;
    }

    public Money calculateFee () {
        Money result = Money.ZERO;

        for ( Call call : calls ) {
            if ( call.getFrom().getHour() >= LATE_NIGHT_HOUR ){
                result = result.plus( nightlyAmount.times( call.getDuration().getSeconds() / seconds.getSeconds() ) );
            }
            else {
                result = result.plus( regularAmount.times( call.getDuration().getSeconds() / seconds.getSeconds() ) );
            }
            return result;
        }
    }
}
```
- 두 클래스의 메서드에서 다른 부분
  - calculateFee의 for 문 안에 구현된 요금 계산 로직이 서로 다르다.

```java
public class Phone {
    ...
    public Money calculateFee() {
        Money result = Money.ZERO;
        
        for( Call call : calls) {
            result = result.plus(calculateCallFee(call));
        }
        return result;
    }
    
    private Money calculateCallFee(Call call) {
        return amount.times(call.getDuration().getSeconds() / seconds.getSeconds());
    }
}
```
```java
public class NightlyDiscountPhone {
    ...
    public Money calculateFee() {
        Money result = Money.ZERO;

        for( Call call : calls) {
            result = result.plus(calculateCallFee(call));
        }
        return result;
    }

    private Money calculateCallFee(Call call) {
        if ( call.getFrom().getHour() >= LATE_NIGHT_HOUR ){
            return nightlyAmount.times( call.getDuration().getSeconds() / seconds.getSeconds() );
        }
        else {
            return regularAmount.times( call.getDuration().getSeconds() / seconds.getSeconds() );
        }
    }
}
```

# 중복 코드를 부모 클래스로 올려라

```java
import java.util.ArrayList;

public abstract class AbstractPhone {
    private List<Call> calls = new ArrayList<>();
    public Money calculateFee() {
        Money result = Money.ZERO;
        
        for(Call call : calls) {
            result = result.plus( calculateCallFee(call));
        }
        return result;
    }
    
    abstract protected Money calculateCallFee(Call call);
}

public class Phone extends AbstractPhone {
}

public class NightlyDiscountPhone extends AbstractPhone {
}
```

# 추상화가 핵심이다.
- 공통 코드를 이동시킨 후에 각 클래스는 서로 다른 변경의 이유를 가진다.
- `Phone`과 `NightlyDiscountPhone`은 오직 `AbstractPhone₩의 추상화에만 의존한다.
- 상속 계층이 코드를 진화시키는 데 걸림돌이 된다면 추상화를 찾아내고 상속 계층 안의 클래스들이 그 추상화에 의존하도록 코드를 리팩터링하라.

# 의도를 드러내는 이름 선택하기
- `AbstractPhone`은 `Phone`으로 `Phone`은 `RegularPhone`으로 변경하여 의도를 드러내자.

# 세금 추가하기
- 클래스라는 도구는 메서드뿐만 아니라 인스턴스 변수도 함께 포함한다.
- 클래스 사이의 상속은 자식 클래스가 부모 클래스가 구현한 행동뿐만 아니라 인스턴스 변수에 대해서도 결합되게 만든다.
- 책임을 잘 분리하더라도 인스턴스 변수의 추가는 종종 상속 계층 전반에 걸친 변경을 유발한다.
- 객체 생성 로직에 대한 변경을 막는 것보다 핵심 로직의 중복을 막는게 더 중요하다.
- 행동을 변경하기 위해 인스턴스 변수를 추가하더라도 상속 계층 전체에 걸쳐 부작용이 퍼지지 않게 막는 것이 목표다.
- 

