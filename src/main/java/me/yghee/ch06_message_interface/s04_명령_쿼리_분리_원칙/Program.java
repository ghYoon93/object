package me.yghee.ch06_message_interface.s04_명령_쿼리_분리_원칙;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Program {

    public static void main ( String[] args ) {
        RecurringSchedule schedule = new RecurringSchedule( "회의", DayOfWeek.WEDNESDAY, LocalTime.of( 10, 30 ), Duration.ofMinutes( 30 ) );
        Event meeting = new Event( "회의", LocalDateTime.of( 2019, 5, 8, 10, 30), Duration.ofMinutes( 30 ) );

        assert meeting.isSatisfied(schedule) == true;

        Event meeting2 = new Event( "회의", LocalDateTime.of( 2019, 5, 9, 10, 30), Duration.ofMinutes( 30 ) );

        assert meeting2.isSatisfied(schedule) == false;
        assert meeting2.isSatisfied(schedule) == true;
        if ( !meeting2.isSatisfied( schedule ) ) {
            meeting2.reschedule( schedule );
        }
    }
}
