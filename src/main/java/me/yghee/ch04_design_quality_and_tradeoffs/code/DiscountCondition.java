package me.yghee.ch04_design_quality_and_tradeoffs.code;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DiscountCondition {

    // 데이터 준비
    private DiscountConditionType type;

    // 순번 조건
    private int sequence;

    // 기간 조건
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    // 캡슐화
    public DiscountConditionType getType () {
        return type;
    }

    public void setType ( DiscountConditionType type ) {
        this.type = type;
    }

    public DayOfWeek getDayOfWeek () {
        return dayOfWeek;
    }

    public void setDayOfWeek ( DayOfWeek dayOfWeek ) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getStartTime () {
        return startTime;
    }

    public void setStartTime ( LocalTime startTime ) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime () {
        return endTime;
    }

    public void setEndTime ( LocalTime endTime ) {
        this.endTime = endTime;
    }

    public int getSequence () {
        return sequence;
    }

    public void setSequence ( int sequence ) {
        this.sequence = sequence;
    }

    public boolean isDiscountable( DayOfWeek dayOfWeek, LocalTime time ) {
        if ( type != DiscountConditionType.PERIOD) {
            throw new IllegalArgumentException();
        }

        return this.dayOfWeek.equals( dayOfWeek )
                && ! this.startTime.isAfter( time )
                && ! this.endTime.isBefore( time );
    }

    public boolean isDiscountable( int sequence ) {
        if ( type != DiscountConditionType.SEQUENCE ) {
            throw new IllegalArgumentException();
        }

        return this.sequence == sequence;
    }
}
