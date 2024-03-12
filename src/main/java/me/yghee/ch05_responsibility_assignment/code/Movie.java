package me.yghee.ch05_responsibility_assignment.code;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public abstract class Movie {
    // 2. 책임을 수행하는 데 필요한 인스턴스 변수
    private String title;
    private Duration runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    public Movie ( String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy ) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    protected Money getFee() {
        return fee;
    }

    // 1. 메시지를 처리할 수 있는 메서드 구현
    public Money calculateMovieFee( Screening screening ) {

        return fee.minus( discountPolicy.calculateDiscountAmount( screening ) );

    }
}
