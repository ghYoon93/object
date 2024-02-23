package me.yghee.ch02_object_oriented_programming.s04_할인_정책_구성하기;

import java.time.Duration;

public class Movie {
    private String title; // 제목
    private Duration runningTime; // 상영시간
    private Money fee; // 기본 요금

    private DiscountPolicy discountPolicy; // 할인 정책


    public Movie ( String title, Duration runningTime, Money fee, DiscountPolicy discountPolicy ) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee() {
        return fee;
    }

    /**
     * discountPolicy로부터 할인 받은 금액을 차감한 요금
     * @param screening
     * @return
     */
    public Money calculateMovieFee( Screening screening ) {
        // 할인 정책을 판단하지 않고 discountPolicy에게 메시지를 전송한다.
        return fee.minus( discountPolicy.calculateDiscountAmount(screening) );
    }
}
