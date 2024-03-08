package me.yghee.ch04_design_quality_and_tradeoffs.code;

import java.time.LocalDateTime;

public class Screening {

    // 데이터 준비
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    // 캡슐화
    public Movie getMovie () {
        return movie;
    }

    public void setMovie ( Movie movie ) {
        this.movie = movie;
    }

    public LocalDateTime getWhenScreened () {
        return whenScreened;
    }

    public void setWhenScreened ( LocalDateTime whenScreened ) {
        this.whenScreened = whenScreened;
    }

    public int getSequence () {
        return sequence;
    }

    public void setSequence ( int sequence ) {
        this.sequence = sequence;
    }

    public Money calculateFee( int audienceCount ) {
        switch ( movie.getMovieType() ) {
            case AMOUNT_DISCOUNT:
                if ( movie.isDiscountable( whenScreened, sequence ) ) {
                    return movie.calculateAmountDiscountedFee().times( audienceCount );
                }
                break;
            case PERCENT_DISCOUNT:
                if ( movie.isDiscountable( whenScreened, sequence ) ) {
                    return movie.calculatePercentDiscountedFee().times( audienceCount );
                }
            case NONE_DISCOUNT:
                return movie.calculateNoneDiscountedFee().times( audienceCount );
        }

        return movie.calculateNoneDiscountedFee().times( audienceCount );
    }
}
