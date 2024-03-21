- **의존성 주입(Dependency Injection)**
  - 사용하는 객체가 아닌 외부의 독립적인 객체가 인스턴스를 생성한 후 이를 전달해서 의존성을 해결하는 방법
  - 의존성은 퍼블릭 인터페이스에 명시적으로 드러내는 것이 좋다.
    - 컴파일타임 의존성과 런타임 의존성에 차이를 둘 수 있다.
    - 생성자 주입(constructor injection): 객체 생성 시점
    - setter 주입(setter injection): 생성 후 주입
    - 메서드 주입(method inject): 메서드 실행 시 인자를 이용하여 주입
- 인터페이스 주입(setter 주입의 변형)
```java
public interface DiscountPolicyInjectable {
    public void inject(DiscountPolicy discountPolicy);
}
```
## 숨겨진 의존성은 나쁘다
- **SERVICE LOCATOR** 패턴
  - 의존성을 해결할 객체들을 보관하는 일종의 저장소
  - 객체가 직접 SERVICE LOCATOR에게 의존성을 해결해줄 것을 요청한다.
  - 의존성을 숨긴다는 단점이 있다.
```java
public class Movie {
    ...
    private DiscountPolicy discountPolicy;
    
    public Movie(String title, Duration runningTime, Money fee) {
        this.title = title;
        ...
        this.discountPolicy = ServiceLocator.discountPolicy();
    }
}
```

- 숨겨진 의존성은 의존성의 대상을 설정하는 시점과 의존성이 해결되는 시점을 멀리 떨어ㄷ트려 놓는다.