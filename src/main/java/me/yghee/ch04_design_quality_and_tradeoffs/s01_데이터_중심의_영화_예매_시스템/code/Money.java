package me.yghee.ch04_design_quality_and_tradeoffs.s01_데이터_중심의_영화_예매_시스템.code;

import java.math.BigDecimal;

public class Money {
    public static final Money ZERO = Money.wons(0L);

    private final BigDecimal amount;

    public static Money wons(Long amount) {
        return new Money(BigDecimal.valueOf( amount ));
    }

    public static Money wons(Double amount) {
        return new Money(BigDecimal.valueOf( amount ));
    }

    Money ( BigDecimal amount) {
        this.amount = amount;
    }

    public Money plus(Money amount) {
        return new Money( this.amount.add( amount.amount ) );
    }

    public Money minus(Money amount) {
        return new Money( this.amount.subtract( amount.amount ) );
    }

    public Money times(Double percent) {
        return new Money( this.amount.multiply( BigDecimal.valueOf( percent ) ) );
    }

    public boolean isLessThan(Money other) {
        return amount.compareTo( other.amount ) < 0;
    }

    public boolean isGreaterThanOrEqual(Money other) {
        return amount.compareTo( other.amount ) >= 0;
    }
}
