package me.yghee.ch05_responsibility_assignment.s04_책임_주도_설계의_대안.code;


import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {

    private DiscountConditionType type;
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public boolean isDiscountable( Screening screening ) {
        if ( type == DiscountConditionType.PERIOD ) {
            return isSatisfiedByPeriod( screening );
        }
        return isSatisfiedBySequence( screening );
    }

    private boolean isSatisfiedByPeriod( Screening screening ) {
        return screening.getWhenScreened().getDayOfWeek().equals( dayOfWeek ) &&
                startTime.compareTo( screening.getWhenScreened().toLocalTime() ) <= 0 &&
                endTime.compareTo( screening.getWhenScreened().toLocalTime() ) >= 0;
    }

    private boolean isSatisfiedBySequence( Screening screening ) {
        return sequence == screening.getSequence();
    }
}
