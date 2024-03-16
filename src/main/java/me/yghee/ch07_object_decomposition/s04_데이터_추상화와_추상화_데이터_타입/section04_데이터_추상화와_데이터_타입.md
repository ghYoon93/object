## 추상 데이터 타입
- 타입
  - 변수에 저장할 수 있는 내용물의 종류와 변수에 적용될 수 있는 연산의 가짓수
- 데이터 추상화
  - 프로시저 추상화를 보완하기위해 제안된 개념
- 직원에 대한 추상 데이터 타입을 설계하려면 어떤 데이터를 감추기 위해 직원이라는 데이터 추상화가 필요한가?
  - 직원의 이름
  - 기본급
  - 아르바이트 직원 여부
```
Employee = Struct.new(:name, :basePay, :hourly, :timeCard) do
End
```

- 외부에서 인자로 전달받던 직원의 이름은 이제 `calculatePy` 오퍼레이션의 인자로 받을 필요가 없다.
```
Employy = Struct.new(:name, :basePay, :hourly, :timeCard) do
  def calculatePay(taxRate)
    if (hourly) then
      return calculateHourlyPay(taxRate)
    end
    return calculateSalariedPay(taxRate)
  end

private
  def calculateHourlyPay(taxRate)
    return (basePay * timeCard) - (basePay * timeCard) * taxRate
  end
  
  def calculateSalariedPay(taxRate)
    return basePay - (basePay * taxRate)
  end
end
```

- 추상 데이터 타입은 사람들이 세상을 바라보는 방식에 좀 더 근접해지도록 추상화 수준을 향상시킨다.
- 추상 데이터 타입 정의를 기반으로 객체를 생성하는 것은 가능하지만 여전히 데이터와 기능을 분리해서 바라본다는 점에 주의하라.
