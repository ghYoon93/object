package me.yghee.ch04_design_quality_and_tradeoffs.s01_데이터_중심의_영화_예매_시스템.code;

import java.time.Duration;

public class Movie {
    // 데이터 준비
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    // 접근자(accessor)와 수정자(mutator) 추가

    public MovieType getMovieType () {
        return movieType;
    }

    public void setMovieType ( MovieType movieType ) {
        this.movieType = movieType;
    }

    public Money getFee () {
        return fee;
    }

    public void setFee ( Money fee ) {
        this.fee = fee;
    }

    public List<DiscountCondition> getDiscountConditions () {
        return discountConditions;
    }

    public void setDiscountConditions ( List<DiscountCondition> discountConditions ) {
        this.discountConditions = discountConditions;
    }

    public Money getDiscountAmount () {
        return discountAmount;
    }

    public void setDiscountAmount ( Money discountAmount ) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercent () {
        return discountPercent;
    }

    public void setDiscountPercent ( double discountPercent ) {
        this.discountPercent = discountPercent;
    }
}
