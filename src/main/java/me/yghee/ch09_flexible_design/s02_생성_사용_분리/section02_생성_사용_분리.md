- 결합도가 높아질수록 개방-폐쇄 원칙을 따르는 구조를 설계하기가 어려워진다.
- 알아야 하는 지식이 많으면 결합도도 높아진다.
- 객체 생성에 대한 지식은 과도한 결합도를 초래하는 경향이 있다.
- 객체 생성은 피할 수는 없다. 어딘가에서는 반드시 객체를 생성해야 한다.
- **생성과 사용 분리(separating use from creation)**
  - 객체와 관련된 두 가지 책임(생성과 사용)을 서로 다른 객체로 분리해야 한다.
  - 객체를 생성하는 클래스와 사용하는 클래스가 있어야한다.
  - 가장 보편적인 방법은 객체를 생성할 책임을 클라이언트로 옮기는 것이다.
```java
public class Client {
    public Money getAvatarFee() {
        Movie avatar = new Movie("아바타",
                Duration.ofMinutes(120),
                Money.wons(10000),
                new AmountDiscountPolicy(...));
        return avatar.getFee();
    }
}
```


## FACTORY 추가하기
- 생성과 사용을 분리하기 위해 객체 생성에 특화된 객체
```java
public class Factory {
    public Movie createAvatarMovie() {
        return new Movie("아바타",
                Duration.ofMinutes(120),
                Money.wons(10000),
                new AmountDiscountPolicy(...));
    }
}
```
```java
public class Client {
    private Factory factory;
    
    public Client(Factory factory) {
        this.factory = factory;
    }
    
    public Money getAvatarFee() {
        Movie avatar = factory.createAvatarMovie();
        return avatar.getFee();
    }
}
```
- Client는 오직 사용과 관련된 책임만 지고 생성과 관련된 어떤 지식도 가지지 않을 수 있다.

## 순수한 가공물에게 책임 할당하기
- 도메인 모델은 **INFORMATION EXPERT**를 찾기 위해 참조할 수 있는 일차적인 재료
- 어떤 책임을 할당하고 싶다면 제일 먼저 도메인 모델 안의 개념 중에서 적절한 후보가 존재하는지 찾아봐야 한다.
- 시스템을 객체로 분해하는 두 가지 방식
  - **표현적 분해(representational decomposition)**
  - **행위적 분해(behavioral decomposition)**
- 표현적 분해
  - 도메인에 존재하는 사물 또는 개념을 표현하는 객체들을 이용해 시스템을 분해하는 것
  - 표현적 분해는 객체지향 설계를 위한 가장 기본적인 접근법
  - 실제로 동작하는 애플리케이션은 데이터베이스 접근을 위한 객체와 같이 도메인 개념들을 초월하는 기계적인 개념들을 필요할 수 있다.
- 행위적 분해
  - **PURE FABRICATION(순수한 가공물)**
  - 책임을 할당하기 위해 창조되는 도메인과 무관한 인공적인 객체
  - 행동을 책임질 마땅한 도메인 개념이 존재하지 않을 때 책임을 할당하는 객체
  - 특정한 행동을 표현하는 것이 일반적
- 객체지향 애플리케이션의 대부분은 실제 도메인에서 발견할 수 없는 순수한 인공물로 가득 차 있다.
- 도메인 개념을 표현하는 객체와 순수하게 창조된 가공의 객체들이 모여 자신의 역할과 책임을 다하고 조화롭게 협력하는 애플리케이션을 설계하는 것이 목표
- 먼저 도메인의 본질적인 개념을 표현하는 추상화를 이용해 애플리케이션 구축하기 시작하라.
  - 도메인 개념이 만족스럽지 못하다면 주저하지 말고 인공적인 객체를 창조하라.

