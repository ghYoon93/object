## 클래스는 추상 데이터 타입인가?
- 클래스와 추상 데이터 타입 모두 데이터 추상화를 기반으로 시스템을 분해한다.
- 명확한 의미에서 추상 데이터 타입과 클래스는 동일하지 않다.
  - 클래스는 상속과 다형성을 지원하는 데 비해 추상 데이터 타입은 지원하지 못한다.
- 클래스는 절차를 추상화한 것
- 추상 데이터 타입은 타입을 추상화한 것
- 타입 추상화
  - 하나의 대표적인 타입이 다수의 세부적인 타입을 감추는 것.
  - 객체지향은 타입을 기준으로 오퍼레이션을 묶는다.
- 동일한 메시지에 대해 서로 다르게 반응한다.
  - 다형성
- 객체지향은 절차 추상화(procedural abstraction)이다.

## 추상 데이터 타입엣거 클래스로 변경하기
- 클래스를 이용한 급여 관리 시스템 구현
Employee (정규 직원과 아르바이트 직원 타입이 공통적으로 가져야 하는 속성과 메서드 시그니처만 정의하고 있는 불완전한 구현체)
```
class Employee
  attr_reader :name, :basePay
  
  def initialize(name, basePay)
    @name = name
    @basePay = basePay
  end
  
  def calculatePay(taxRate)
    raise NotImplementedError
  end
  
  def monthlyBasePay()
    raise NotImplementedError
  end
```

SalariedEmployee
```
class SalariedEmployee < Employee
  def initialize(name, basePay)
    super(name, basePay)
  end
  
  def calculatePay(taxRate)
    return basePay - (basePay * taxRate)
  end
  
  def monthlyBasePay()
    return basePay
  end
end  
```

HourlyEmployee
```
class HourlyEmployee < Employee
  attr_reader :timeCard
  def initialize(name, basePay, timeCard)
    super(name, basePay)
    @timeCard = timeCard
  end
  
  def calculatePay(taxRate)
    return (basePay * timeCard) - (basePay * timeCard) * taxRate
  end
  
  def monthlyBasePay()
    return 0
  end
end 
```

객체를 고려하지 않고 monthlyBasePay 메시지를 전송
```
def sumOfBasePays()
  result = 0
  for each in $employees
    result += each.monthlyBasePay()
   end
  puts(result)
end 
```

## 변경을 기준으로 선택하라
- 클래스를 구현 단위로 사용한다는 것이 객체지향 프로그래밍을 한다는 것을 의미하지는 않는다.
  - 타입을 기준으로 절차를 추상화하지 않았다면 그것은 객체지향 분해가 아니다.
- 클래스가 추상 데이터 타입의 개념을 따르는지를 확인할 수 있는 가장 간단한 방법
  - 클래스 내부에 인스턴스의 타입(정규직, 아르바이트)을 표현하는 변수가 있는지를 살펴보는 것이다.
    - hourly라는 인스턴스 변수에 직원의 유형을 저장한다.
- 객체지향에서 타입 변수를 이용한 조건문
  - 다형성으로 대체
  - 클라리언트가 객체의 타입을 확인한 후 적절한 메서드를 호출하는 것이 아니라 객체가 메시지를 처리할 적절한 메서드를 선택한다.
- **개방-폐쇄 원칙(Open-Closed Principle, OCP)**
  - 기존 코드에 아무런 영향도 미치지 않고 새로운 객체 유형과 행위를 추가할 수 있는 객체지향의 특성
- 추상 데이터 타입은 모든 경우에 최악의 선택인가?
  - 설계에 요구되는 변경의 압력이 '타입 추가'에 관한 것인지, 아니면 '오퍼레이션 추가'에 관한 것인지에 따라 달라진다.
  - 새로운 타입 추가
    - 객체지향의 클래스 구조
  - 새로운 오퍼레이션 추가
    - 추상 데이터 타입

## 협력이 중요하다
- 객체지향에서 종요한 것은 역할, 책임, 협력이다.
- 협력이라는 문맥을 고려하지 않고 객체를 고립시킨 채 오퍼레이션의 구현 방식을 타입별로 분배하는 것은 올바른 접근법이 아니다.
- 참여할 협력 결정 -> 책임을 수행하기 위해 필요한 객체 
- 타입 계층과 다형성은 협력이라는 문맥 안에서 책임을 수행하는 방법에 관해 고민한 결과물이어야 하며 그 자체가 목적이 되어서는 안 된다.
