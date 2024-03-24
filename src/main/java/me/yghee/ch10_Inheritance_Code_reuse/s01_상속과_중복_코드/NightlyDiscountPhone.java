package me.yghee.ch10_Inheritance_Code_reuse.s01_상속과_중복_코드;

import me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.Money;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * 심야 할인 요금제
 */
public class NightlyDiscountPhone extends Phone {
    private static final int LATE_NIGHT_HOUR = 22;
    private Money nightlyAmount;

    public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration seconds ) {
        super( regularAmount, seconds );
        this.nightlyAmount = nightlyAmount;
    }

    @Override
    public Money calculateFee() {

        // 부모 클래스의 calculateFee 호출
        Money result = super.calculateFee();

        Money nightlyFee = Money.ZERO;
        for ( Call call : getCalls() ) {
            if ( call.getFrom().getHour() >= LATE_NIGHT_HOUR ) {
                nightlyFee = nightlyFee.plus(
                        getAmount().minus( nightlyAmount ).times( ( double ) (call.getDuration().getSeconds() / getSeconds().getSeconds()) )
                );
            }
        }

        return result.minus( nightlyFee );
    }
}
