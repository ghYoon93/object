package me.yghee.ch10_Inheritance_Code_reuse.s01_상속과_중복_코드;

import me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.Money;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Phone {
    private static final int LATE_NIGHT_HOUR = 22;
    enum PhoneType { REGULAR, NIGHTLY }

    private PhoneType type;
    private Money amount;
    private Money regularAmount;
    private Money nightlyAmount;
    private Duration seconds;
    private List<Call> calls = new ArrayList<>();

    public Phone ( Money amount, Duration seconds ) {
        this(PhoneType.REGULAR, amount, Money.ZERO, Money.ZERO, seconds );
    }

    public Phone(Money nightlyAmount, Money regularAmount, Duration seconds) {
        this(PhoneType.NIGHTLY, Money.ZERO, nightlyAmount, regularAmount, seconds);
    }

    public Phone( PhoneType type, Money amount, Money nightlyAmount, Money regularAmount, Duration seconds ) {
        this.type = type;
        this.amount = amount;
        this.regularAmount = regularAmount;
        this.nightlyAmount = nightlyAmount;
        this.seconds = seconds;
    }

    public Money calculateFee() {
        Money result = Money.ZERO;
        for ( Call call : calls ) {
            if ( type == PhoneType.REGULAR ) {
                result = result.plus(
                        amount.times( ( double ) (call.getDuration().getSeconds() / seconds.getSeconds()) )
                );
            }
            else {
                regularAmount.times( ( double ) (call.getDuration().getSeconds() / seconds.getSeconds()) );
            }
        }

        return result;
    }

    public List<Call> getCalls() {
        return calls;
    }

    public Money getAmount() {
        return amount;
    }

    public Duration getSeconds() {
        return seconds;
    }
}
