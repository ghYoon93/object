package me.yghee.ch04_design_quality_and_tradeoffs.code;

/**
 * 데이터 클래스들을 조합해서 영화 예매 절차를 구현하는 클래스
 */
public class ReservationAgency {
    public Reservation reserv(Screening screening, Customer customer, int audienceCount) {
        Movie movie = screening.getMovie();

        //
        boolean discountable = false;
        for ( DiscountCondition condition : movie.getDiscountConditions() ) {
            if (condition.getType() == DiscountConditionType.PERIOD ) {
                discountable = screening.getWhenScreened().getDayOfWeek().equals( condition.getDayOfWeek() ) &&
                        condition.getStartTime().compareTo( screening.getWhenScreened().toLocalTime() ) <= 0 &&
                        condition.getEndTime().compareTo( screening.getWhenScreened().toLocalTime() ) >= 0;
            }
            else {
                discountable = condition.getSequence() == screening.getSequence();
            }

            if ( discountable ) {
                break;
            }
        }

        // 적절한 할인 정책에 따라 예매 요금을 계산하는 if 문
        Money fee;
        if (discountable) {
            Money discountAmount = Money.ZERO;
            switch ( movie.getMovieType() ) {
                case AMOUNT_DISCOUNT:
                    discountAmount = movie.getDiscountAmount();
                    break;
                case PERCENT_DISCOUNT:
                    discountAmount = movie.getFee().times(movie.getDiscountPercent());
                    break;
                case NONE_DISCOUNT:
                    discountAmount = Money.ZERO;
                    break;
            }
            fee = movie.getFee().minus( discountAmount );
        }
        else {
            fee = movie.getFee();
        }

        return new Reservation( customer, screening, fee, audienceCount );
    }
}
