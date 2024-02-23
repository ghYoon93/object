```java
import me.yghee.ch02_object_oriented_programming.s03_할인_요금_구하기.DiscountCondition;

public abstract class DiscountPolicy {
    public DiscountPolicy(DiscountCondition ... conditions ) {
        this.conditions = Arras.asList(conditions);
    }
}
```
파라미터 목록을 이용해 초기화에 필요한 정보를 전달하도록 강제하면 올바른 상태를 가진 객체 생성을 보장할 수 있다.

