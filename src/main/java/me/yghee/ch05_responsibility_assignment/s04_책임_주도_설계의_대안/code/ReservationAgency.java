package me.yghee.ch05_responsibility_assignment.s04_책임_주도_설계의_대안.code;

import me.yghee.ch05_responsibility_assignment.code.*;

/**
 * 데이터 클래스들을 조합해서 영화 예매 절차를 구현하는 클래스
 */
public class ReservationAgency {
    public Reservation reserv( Screening screening, Customer customer, int audienceCount) {
        boolean discountable = checkDiscountable(screening);

        Money fee = calculateFee( screening, discountable, audienceCount );

        return createReservation( screening, customer, audienceCount, fee );
    }

    private Money calculateFee ( Screening screening, boolean discountable, int audienceCount ) {
        // 적절한 할인 정책에 따라 예매 요금을 계산하는 if 문
        Money fee;
        // 할인 정책이 추가되면 변경이 발생
        if ( discountable ) {
            return screening.getMovie().getFee()
                    .minus( calculateDiscountedFee( screening.getMovie() ) )
                    .times( audienceCount );
        }
        return screening.getMovie().getFee().times( audienceCount );
    }

    private boolean checkDiscountable( Screening screening ) {
        return screening.getMovie().getDiscountConditions().stream()
                .anyMatch( condition -> condition.isDiscountable( screening ) )
    }



    private Money calculateDiscountedFee( Movie movie ) {
        switch ( movie.getMovieType() ) {
            case AMOUNT_DISCOUNT:
                return calculateAmountDiscountedFee( movie );
            case PERCENT_DISCOUNT:
                return calculatePercentDiscountedFee( movie );
            case NONE_DISCOUNT:
                return calculateNoneDiscountedFee( movie );
        }
        throw new IllegalArgumentException();
    }

    private Money calculateAmountDiscountedFee( Movie movie ) {
        return movie.getDiscountAmount();
    }

    private Money calculatePercentDiscountedFee( Movie movie ) {
        return movie.getFee().times( movie.getDiscountPercent() );
    }

    private Money calculateNoneDiscountedFee( Movie movie ) {
        return Money.ZERO;
    }

    private Reservation createReservation( Screening screening,
                                           Customer customer, int audienceCount, Money fee ) {
        return new Reservation( customer, screening, fee, audienceCount );
    }
}
