- 좋은 인터페이스
  - 최소한의 인터페이스
  - 추상적인 인터페이스
- 최소주의를 따르면서도 추상적인 인터페이스를 설계할 수 있는 가장 좋은 방법
  - 책임 주도 설계 방법
- 퍼블릭 인터페이스의 품질에 영향을 미치는 원칙과 기법
  - 디미터 법칙
  - 묻지 말고 시켜라
  - 의도를 드러내는 인터페이스
  - 명령-쿼리 분리


## 디미터 법칙
```java
public class ReservationAgency {
    public Reservation reserve( Screening screening, Customer customer, int audienceCount ) {
        Movie movie = screening.getMovie();
        
        boolean discountable = false;
        for( DiscountCondition condition : movie.getDiscountConditions() ) {
            if ( condition.getType() == DiscountConditionType.PERIOD ) {
                discountable = screening.getWhenScreened().getDayOfWeek().equals( condition.getDayOfWeek() ) &&
                        condition.getStartTime().compareTo( screening.getWhenScreened().toLocalTime() ) <= 0 &&
                        condition.getEndTime().compareTo( screening.getWhenScreened().toLocaTime() ) >= 0;
            }
            else {
                discountable = condition.getSequence() == screening.getSequence();
            }
            
            if ( discountable ) {
                break;
            }
            ...
        }
    }
}
```
- 위 코드는 `ReservationAgency`와 인자로 전달된 `Screening` 사이의 결합도가 너무 높다.
- **디미터 법칙(Law of Demeter)**: 협력하는 객체의 내부 구조에 대한 결합으로 인해 발생하는 설계 문제를 해결하기 위해 제안된 원칙
  - 객체의 내부 구조에 강하게 결합되지 않도록 협력 경로를 제한하라는 것.
  - 오직 하나의 도트만 사용하라
- 클래스가 특정한 조건을 만족하는 대상에게만 메시지를 전송하도록 프로그래밍해야 한다.
  - 모든 클래스 C와 C에 구현된 모든 메서드 M에 대해서, M이 메시지를 전송할 수 있는 모든 객체는 다음에 서술된 클래스의 인스턴스여야 한다.
  - 이 때 M에 의해 생성된 객체나 M이 호출하는 메서드에 의해 생성된 객체, 전역 변수로 선언된 객체는 모두 M의 인자로 간주한다.
    - M의 인자로 전달된 클래스(C 자체를 포함)
    - C의 인스턴스 변수의 클래스
- 클래스 내부의 메서드가 아래 조건을 만족하는 인스턴스에만 메시지를 전송하도록 프로그래밍 해야 한다.
  - this 객체
  - 메서드의 매개변수
  - this의 속성
  - this의 속성인 컬렉션의 요소
  - 메서드 내에서 생성된 지역 객체

`ReservationAgency`
```java
public class ReservationAgency {
    public Reservation reserv(Screening screening, Customer customer, int audienceCount ) {
        Money fee = screening.calculateFee( audienceCount );
        return new Reservation(customer, screening, fee, audienceCount );
    }
}
```
- 위 코드에서 `Screening` 인스턴스에게만 메시지를 전송한다.

- 디미터 법칙과 캡슐화
  - 디미터 법칙은 캡슐화를 다른 관점에서 표현한 것.
  - 클래스를 캡슐화하기 위해 따라야하는 구체적인 지침을 제공

- 디미터 법칙을 위반하는 코드의 전형적인 모습
  - `screening.getMovie().getDiscountConditions();`
  - 이와 같은 코드를 **기차 충돌(train wreck)** 라 부른다.
  - 메시지 전송자가 메시지 수신자의 내부 구현에 강하게 결합된다.
  - `screening.calculateFee( audienceCount );`
  - 하지만 무비판적으로 디미터 법칙을 수용하면 퍼블릭 인터페이스 관점에서 객체의 응집도가 낮아질 수도 있다.
  - **원칙의 함정**

## 묻지 말고 시켜라
- 메시지 전송자는 메시지 수신자의 상태를 기반으로 결정을 내린 후 메시지 수신자의 상태를 바꿔서는 안된다.
- 내부 상태를 이용해 어떤 결정을 내리는 로직이 객체 외부에 존재하면 행딩이 객체 외부로 누수된 것.
- 상태를 묻는 오퍼레이션을 행동을 요청하는 오퍼레이션으로 대체함으로써 인터페이스를 향상시켜라
- 객체는 자신이 내부적으로 보유하고 있는 정보나 메시지 전송의 결과로 얻게 되는 정보만 사용해서 의사결정을 내려야한다.
- 훌륭한 인터페이스는 객체가 어떻게 작업을 수행하는지를 노출해서는 안 된다.
  - 인터페이스는 객체가 어떻게 하는지가 아니라 무엇을 하는지를 서술해야 한다.

## 의도를 드러내는 인터페이스
- 메서드를 명명하는 두 가지 방법
  - 메서드가 작업을 어떻게 수행하는지를 나타내도록
    - 메서드에 대해 제대로 커뮤니케이션하지 못한다.
    - 메서드 수준에서 캡슐화 위반
  - '어떻게'가 아니라 '무엇'을 하는지를 드러내도록
- 무엇을 하는지를 드러내도록 메서드의 이름을 짓는다는 것
  - 객체가 협력 안에서 수행해야 하는 책임
- **의도를 드러내는 선택자(Intention Revealing Selector)**
  - 무엇을 하느냐에 따라 메서드의 이름을 짓는 패턴
- **의도를 드러내는 인터페이스(Intention Revealing Interface)**
  - 구현과 관련된 모든 정보를 캡슐화하고 객체의 퍼블릭 인터페이스에는 협력과 관련된 의도만을 표현해야 한다는 것

## 함께 모으기

### 디미터 법칙
디미터 법칙을 위반하는 티켓 판매 도메인
```java
public class Theater {
    private TicketSeller ticketSeller;
    
    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }
    
    public void enter(Audience audience) {
        if ( audience.getBack().hasInvitation() ) {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        }
        else {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().minusAmount(ticket.getFee());
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }
}
```

- 기차 충돌 스타일 발생
  - `audience.getBag().minusAmount(title.getFee());`
- 디미터 법칙을 위반하는 설계는 **인터페이스와 구현의 분리 원칙**을 위반
- 프로그램에 노출되는 객체 사이의 관계가 많아질수록 결합도가 높아진다.

`Theater` 은 `TicketOffice` 가 `getTicket` 메시지를 수신할 수 있으며, `Ticket` 인스턴스가 `getFee` 메시지를 이해할 수 있다는 사실도 알고 있어야 한다.
```java
Ticket ticket = ticketSeller getTicketOffice().getTicket();
audience.getBag().minusAmount(ticket.getFee());
```

### 묻지 말고 시켜라
- `TicketSeller`와 `Audience`는 묻지 말고 시켜라 스타일을 따르는 퍼블릭 인터페이스를 가져야 한다.
  - `Theater`가 `TicketSeller`에게 자신이 원하는 일을 시키도록 수정하자

```java
public class TicketSeller {
    public void setTicket(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            Ticket ticket = ticketOffice.getTicket();
            audience.getBag().setTicket(ticket);
        }
        else {
            Ticket ticket = ticketOffice.getTicket();
            audience.getBag().minusAmount(ticket.getFee());
            ticketOffice.plusAmount(ticket.getFee());
            audience.getBag().setTicket(ticket);
        }
    }
}
```

수정 후
```java
public class Theater {
    public void enter(Audience audience) {
        ticketSeller.setTicket(audience);
    }
}
```

- `Audience`에게 `setTicket` 메서드를 추가하고 스스토 티켓을 가지도록 수정
```java
public class Audience {
    public Long setTicket(Ticket ticket) {
        if (bag.hasInvitation()) {
            bag.setTicket(ticket);
            return 0L;
        }
        else {
            bag.setTicket(ticket);
            bag.minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }
}
```
- 디미터 법칙 스타일
  - 자율적인 객체로 구성된 유연한 협력을 얻게 된다.
  - 구현이 객체의 퍼블릭 인터페이스에 노출되지 않는다.
    - 겹합도가 낮아진다.
  - 책임이 잘못된 곳에 할당될 가능성이 낮아진다.
    - 응집도가 높아진다.
- 인터페이스가 클라이언트의 의도를 올바르게 반영했는지를 확인해야 한다.

### 인터페이스에 의도를 드러내자
- 현재의 인터페이스는 클라이언트의 의도를 명확하게 드러내지 못한다.
  - `TicketSeller#setTicket`
  - `Audience#setTicket`
  - `Bag#setTicket`
- `Theater`가 `TicketSeller`에게 `setTicket` 메시지를 전송해서 얻고 싶었던 결과는 무엇일까?
  - `Audience` 에게 티켓을 판매하는 것. `sellTo`
- `TicketSeller` 가 `Audience`에게 `setTicket` 메시지를 전송하는 이유
  - `Audience`가 티켓을 사도록 만드는 것. `buy`
  - `Audience`가 `Bag` 에게 메시지를 전송하면서 의도한 것
    - 티켓을 보관하도록 만드는 것 `hold`

```java
public class TicketSeller {
    public void sellTo(Audience audience);
}

public class Audience {
    public Long buy(Ticket ticket) {}
}

public class Bag {
    public Long hold(Ticket ticket);
}
```

- 디미터 법칙
  - 객체 간의 협력을 설계할 때 캡슐화를 위반하는 메시지가 인터페이스에 포함되지 않도록 제한.
- 묻지 말고 시켜라 원칙
  - 디미터 법칙을 준수하는 협력을 만들기 위한 스타일 제시
- 의도를 드러내는 인터페이스 원칙
  - 객체의 퍼블릭 인터페이스에 어떤 이름이 드러나야 하는지에 대한 지침을 제공함으로써 코드의 목정을 명확하게 커뮤니케이션할 수 있게 해준다.
  - 