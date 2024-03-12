package me.yghee.ch05_responsibility_assignment.code;

import java.time.Duration;

public class NoneDiscountMovie extends Movie {
    public NoneDiscountMovie ( String title, Duration runningTime, Money fee ) {
        super( title, runningTime, fee );
    }

    @Override
    protected Money calculateDiscountAmount () {
        return Money.ZERO;
    }
}
