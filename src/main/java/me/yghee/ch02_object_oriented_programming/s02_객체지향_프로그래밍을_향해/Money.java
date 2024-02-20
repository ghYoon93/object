package me.yghee.ch02_object_oriented_programming.s02_객체지향_프로그래밍을_향해;

import java.math.BigDecimal;

public class Money {
    private static final Money ZERO = Money.wons(0);

    private final BigDecimal amount;

    public static Money wons(Long amount) {
        return new Money(BigDecimal.valueOf( amount ));
    }

    public static Money wons(Double amount) {
        return new Money(BigDecimal.valueOf( amount ));
    }

    Money(BigDecimal amount) {
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
