## 정보 은닉과 모듈
- 시스엠 변경을 관리하는 기본적인 전략
  - 함께 변경되는 부분을 하나의 구현 단위로 묶고 퍼블릭 인터페이스를 통해서만 접근하도록 만드는 것이다.
  - 기능을 기반으로 시스템을 분해하는 것이 아니라 변경의 방향에 맞춰 시스템을 분해하는 것이다.
- 정보 은닉
  - 시스템을 모듈 단위로 분해하기 위한 기본원리
  - 시스템에서 자주 변경되는 부분을 상대적으로 덜 변경되는 안정적인 인터페이스 뒤로 감춰야 한다.
- 어려운 설계 결정이나 변화할 것 같은 설계 결정들의 목록을 사용해 설계를 시작하라

```
module Employees
  $employees = ["직원A", ...]
  $basePays = [400, ...]
  $hourlys = [false, ...]
  $timeCards = [0, ...]
  
  def Employees.calculatePay(name, taxRate)
  def Employees.hourly?(name)
  def Employees.calculateHourlyPayFor(name, taxRate)
  def Employees.calculatePayFor(name, taxRate)
  def Employees.sumOfBasePays()
```

## 모듈의 장점과 한계
- 장점
  - 모듈 내부의 변수가 변경되더라도 모듈 내부에만 영향을 미친다
  - 비즈니스 로직과 사용자 인터페이스에 대한 관심사를 분리한다.
  - 전역 변수와 전역 함수를 제거함으로써 네임스페이스 오염(namespace pollution)을 방지한다.
- 모듈은 기능이 아니라 변경의 정도에 따라 시스템을 분해하게 한다.
  - 높은 응집도, 낮은 결합도 유지
- 한계
  - 인스턴스 개념을 제공하지 않는다.
