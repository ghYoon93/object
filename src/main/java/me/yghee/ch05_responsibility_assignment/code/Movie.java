package me.yghee.ch05_responsibility_assignment.code;

import java.time.Duration;
import java.util.List;

public class Movie {
    // 2. 책임을 수행하는 데 필요한 인스턴스 변수
    private String title;
    private Duration runningTime;
    private Money fee;
    private List<DiscountCondition> discountConditions;

    private MovieType movieType;
    private Money discountAmount;
    private double discountPercent;

    // 1. 메시지를 처리할 수 있는 메서드 구현
    public Money calculateMovieFee( Screening screening ) {
        if ( isDiscountable( screening) ) {
            return fee.minus( calculateDiscountAmount() );
        }

        return fee;
    }

    private boolean isDiscountable( Screening screening ) {
        return discountConditions.stream()
                .anyMatch( condition -> condition.isSatisfiedBy( screening ) );
    }

    private Money calculateDiscountAmount() {
        switch ( movieType ) {
            case AMOUNT_DISCOUNT:
                return calculateAmountDiscountAmount();
            case PERCENT_DISCOUNT:
                return calculatePercentDiscountAmount();
            case NONE_DISCOUNT:
                return calculateNoneDiscountAmount();
        }

        throw new IllegalStateException();
    }

    private Money calculateAmountDiscountAmount() {
        return discountAmount;
    }

    private Money calculatePercentDiscountAmount() {
        return fee.times( discountPercent );
    }

    private Money calculateNoneDiscountAmount() {
        return Money.ZERO;
    }
}
