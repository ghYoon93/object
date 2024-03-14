package me.yghee.ch06_message_interface.s04_명령_쿼리_분리_원칙;

import java.time.Duration;
import java.time.LocalDateTime;

public class Event {
    private String subject;
    private LocalDateTime from;
    private Duration duration;

    public Event ( String subject, LocalDateTime from, Duration duration ) {
        this.subject = subject;
        this.from = from;
        this.duration = duration;
    }

    // 명령과 쿼리의 두 가지 역할을 동시에 수행하고 있다.
    public boolean isSatisfied ( RecurringSchedule schedule ) {
        if ( from.getDayOfWeek() != schedule.getDayOfWeek() ||
        !from.toLocalTime().equals( schedule.getFrom() ) ||
        !duration.equals( schedule.getDuration() ) ) {
            return false;
        }

        return true;
    }

    public void reschedule( RecurringSchedule schedule ) {
        from = LocalDateTime.of( from.toLocalDate().plusDays( daysDistance( schedule ) ),
                schedule.getFrom() );
        duration = schedule.getDuration();
    }

    private long daysDistance( RecurringSchedule schedule ) {
        return schedule.getDayOfWeek().getValue() - from.getDayOfWeek().getValue();
    }
}
