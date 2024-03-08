첫 번째 설계에서 발생했던 대부분의 문제는 두 번째 설계에서도 여전히 발생하는 이유는 무엇일까?
## 캡슐화 위반
`DiscountCondition`
- isDiscountable 인터페이스의 dayOfWeek, time으로 객체 내부의 인스턴스 변수를 노출
- isDiscountable 인터페이스의 sequence도 또한 인스턴스 변수를 노출
- getType으로 할인조건 노출

`Movie`
- 할인정책 노출 : calculate**AmountDiscount**edFee, calculate**PercentDiscount**edFee, calculate**NoneDiscount**edFee
- 할인정책이 추가될수록 인터페이스도 늘어난다.

## 높은 결합도
- `Movie`와 `DiscountCondition` 사이의 결합도의 문제점?
- `DiscountCondition`의 기간 할인 조건의 명칭이 `PERIOD`에서 다른 값으로 변경된다면 `Movie`의 수정 발생
- `DiscountCondition`의 종류가 추가되거나 삭제된다면 `Movie₩ 안의 `if ~ else` 구문 수정 발생
- 각 `DiscountCondition`의 만족 여부를 판단하는 데 필요한 정보가 변경되면 `Movie`와 `Screening`의 수정 발생


## 낮은 응집도
- 설계의 응집도가 낮다는 증거 -> 하나의 변경을 수용하기 위해 코드의 여러 곳을 동시에 변경해야 한다.
- 데이터 중심의 설계는 어떤 이유로 캡슐화 위반, 높은 결합도, 낮은 응집도 문제를 유발할까?