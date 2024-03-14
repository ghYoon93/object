- 디미터 법칙과 묻지말고 시켜라 스타일은 절대적인 법칙이 아니다.
  - 소프트웨어 설계에 법칙이란 존재하지 않는다.
  - 원칙은 예외가 넘쳐난다.
- 원칙이 현재 상황에 부적합하다고 판단된다면 과감하게 원칙을 무시하라.
  - 원칙을 아는 것보다 더 중요한 것
    - 현재 상황에 원칙이 유용한지 판단할 수 있는 능력을 기르는 것
      - 트레이드 오프 능력

## 디미터 법칙은 하나의 도트(.)를 강제하는 규칙이 아니다.
- `IntStream.of(1, 15, 20, 3, 9).filter(x -> x > 10 ).distinct().count();`
  - 각 메서드들은 `IntStream`의 인스턴스를 또 다른 `IntStream`의 인스턴스로 변환한다.
    - 객체의 내부 구조가 외부로 노출되는 경우가 아니다.

## 결합도와 응집도의 충돌
- 어떤 객체의 상태를 물어본 후 반환된 상태를 기반으로 결정을 내리고 그 결정에 따라 객체의 상태를 변경하는 코드는 묻지 말고 시켜라 스타일로 변경해야한다.
- 뭄지 말고 시켜라와 디미터 법칙을 준수하는 것이 항상 긍정적인 결과로만 귀결되는 것은 아니다.
  - 맹목적으로 위임 메서드를 추가하면 퍼블릭 인터페이스 안에 어울리지 않는 오퍼레이션들이 공존하게 된다.
  - 상관 없는 책임들 발생
        - 응집도가 낮아진다.

```java
public class PeriodCondition implements  DiscountCondition {
    public boolean isSatisfiedBy( Screening screening ) {
        return screening.getStartTime().getDayOfWeek().equals(dayOfWeek) &&
      ...
    }
}
```

```java
public class Screening {
    public boolea isDiscountable( DayOfWeek, dayOfWeek, ...) {
      ...
    }
}
```
- 할인 여부 판단 로직을 Screening으로 옮기고 PeriodCondition이 이 메서드를 호출하도록 변경
  - 묻지 말고 시켜라 스타일을 준수
  - 하지만 `Screening`이 기간에 따른 할인 조건을 판단하는 책임을 떠안음
- `Screening`의 본질적인 책임
  - 영화를 예매하는 것
- `Screening`의 응집도를 높이고 `Screening`과 `PeriodCondition` 사이의 결합도를 낮추는 것이 더 좋은 방법

- 객체에게 시키는 것이 항상 가능한 것은 아니다.
  - 가끔은 물어야 한다.
- 자료 구조는 내부를 노출해야 하므로 디미터 법칙을 적용할 필요가 없다.