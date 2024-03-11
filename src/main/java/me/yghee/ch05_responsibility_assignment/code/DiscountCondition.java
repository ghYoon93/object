package me.yghee.ch05_responsibility_assignment.code;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {
    // 2. 책임을 수행하는 데 필요한 인스턴스 변수
    private DiscountConditionType type;
    private int sequence;
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    // 1. 메시지를 처리할 수 있는 메서드 구현
    public boolean isSatisfiedBy( Screening screening ) {
        if ( type == DiscountConditionType.PERIOD ) {
            return isSatisfiedByPeriod( screening );
        }

        return isSatisfiedBySequence( screening );
    }

    private boolean isSatisfiedByPeriod( Screening screening ) {
        return dayOfWeek.equals( screening.getWhenScreened().getDayOfWeek() ) &&
                startTime.compareTo( screening.getWhenScreened().toLocalTime() ) <= 0 &&
                endTime.isAfter( screening.getWhenScreened().toLocalTime() );
    }

    private boolean isSatisfiedBySequence( Screening screening ) {
        return sequence == screening.getSequence();
    }
}
