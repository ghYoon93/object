package me.yghee.ch04_design_quality_and_tradeoffs.code;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

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

    public Money calculateAmountDiscountedFee() {
        if (movieType != MovieType.AMOUNT_DISCOUNT ) {
            throw new IllegalArgumentException();
        }

        return fee.minus( discountAmount );
    }

    public Money calculatePercentDiscountedFee() {
        if ( movieType != MovieType.PERCENT_DISCOUNT ) {
            throw new IllegalArgumentException();
        }

        return fee.minus( fee.times( discountPercent ) );
    }

    public Money calculateNoneDiscountedFee() {
        if ( movieType != MovieType.NONE_DISCOUNT ) {
            throw new IllegalArgumentException();
        }

        return fee;
    }

    public boolean isDiscountable( LocalDateTime whenScreened, int sequence ) {
        for ( DiscountCondition discountCondition : discountConditions ) {
            if ( discountCondition.getType() == DiscountConditionType.PERIOD ) {
                if ( discountCondition.isDiscountable( whenScreened.getDayOfWeek(), whenScreened.toLocalTime() ) ) {
                    return true;
                }
            }
            else {
                if ( discountCondition.isDiscountable( sequence ) ) {
                    return true;
                }
            }
        }

        return false;
    }
}
