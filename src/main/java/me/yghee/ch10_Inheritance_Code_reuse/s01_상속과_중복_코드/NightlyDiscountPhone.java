package me.yghee.ch10_Inheritance_Code_reuse.s01_상속과_중복_코드;

import me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.Money;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * 심야 할인 요금제
 */
public class NightlyDiscountPhone {
    private static final int LATE_NIGHT_HOUR = 22;
    private Money nightlyAmount;

    private Money regularAmount;
    private Duration seconds;
    private List<Call> calls = new ArrayList<>();

    private double taxRate;

    public NightlyDiscountPhone ( Money nightlyAmount, Money regularAmount, Duration seconds, double taxRate ) {
        this.nightlyAmount = nightlyAmount;
        this.regularAmount = regularAmount;
        this.seconds = seconds;
        this.taxRate = taxRate;
    }

    public Money calculateFee() {
        Money result = Money.ZERO;

        for ( Call call : calls ) {
            if ( call.getFrom().getHour() >= LATE_NIGHT_HOUR ) {
                result = result.plus(
                        nightlyAmount.times( ( double ) (call.getDuration().getSeconds() / seconds.getSeconds()) )
                );
            }
            else {
                result = result.plus(
                        regularAmount.times( ( double ) (call.getDuration().getSeconds() / seconds.getSeconds()) )
                );
            }
        }

        return result.minus( result.times( taxRate ) );
    }
}
