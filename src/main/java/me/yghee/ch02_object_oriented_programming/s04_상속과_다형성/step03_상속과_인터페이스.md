상속의 목적은 메서드나 인스턴스 변수를 재사용하는 것이 아니다.
상속은 부모 클래스가 제공하는 모든 인터페이스를 자식 클래스가 물려받을 수 있기 때문에 가치있다.

인터페이스는 객체가 이해할 수 있는 메시지의 목록을 정의한다. 
상속을 통해 **자식 클래스는 자신의 인터페이스에 부모 클래스의 인터페이스를 포함하게 된다.**

결과적으로 자식 클래스는 부모 클래스가 수신할 수 있게 때문에 외부 객체는 자식 클래스를 부모 클래스와 동일한 타입으로 간주할 수 있다.

```java
public class Movie {
    public Money calculateMovieFee(Screening screening) {
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }
}
```
- `Movie` 는 자신과 협력하는 객체가 어떤 클래스의 인스턴스인지가 중요한 것이 아니라 calculateDiscountAmount 메시지를 수신할 수 있다는 사실이 중요하다.
- 다시 말해 `Movie`는 협력 객체가 `calculateDiscountAmount`라는 메시지를 이해할 수만 있다면 그 객체가 어떤 클래스의 인스턴스인지는 상관하지 않는다는 것이다.

**업캐스팅(upcasting)** : 자식 클래스가 부모 클래스를 대신하는 것
