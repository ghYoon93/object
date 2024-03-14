package me.yghee.ch06_message_interface.s04_명령_쿼리_분리_원칙;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class RecurringSchedule {
    private String subject;
    private DayOfWeek dayOfWeek;
    private LocalTime from;
    private Duration duration;

    public RecurringSchedule ( String subject, DayOfWeek dayOfWeek, LocalTime form, Duration duration ) {
        this.subject = subject;
        this.dayOfWeek = dayOfWeek;
        this.from = form;
        this.duration = duration;
    }

    public String getSubject () {
        return subject;
    }

    public DayOfWeek getDayOfWeek () {
        return dayOfWeek;
    }

    public LocalTime getFrom () {
        return from;
    }

    public Duration getDuration () {
        return duration;
    }
}
