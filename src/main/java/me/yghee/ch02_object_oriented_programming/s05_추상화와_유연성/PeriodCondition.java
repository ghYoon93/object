package me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class PeriodCondition implements DiscountCondition {
    private DayOfWeek dayOfWeek; // 요일
    private LocalTime startTime; // 시작 시간
    private LocalTime endTime; // 종료 시간

    public PeriodCondition ( DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime ) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * 요일이 같아야 하며 시작 시간이 할인 시작 시간과 종료 시간 사이여야한다.
     * @param screening
     * @return
     */
    @Override
    public boolean isSatisfiedBy ( Screening screening ) {
        return screening.getStartTime().getDayOfWeek().equals( dayOfWeek )&&
                startTime.compareTo( screening.getStartTime().toLocalTime() ) <= 0 &&
                endTime.compareTo( screening.getStartTime().toLocalTime() ) >= 0;
    }
}
