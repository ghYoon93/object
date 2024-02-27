package me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class Section05 {

    public static void main ( String[] args ) {
        Movie avatar = new Movie( "아바타",
                Duration.ofMinutes( 120 ),
                Money.wons( 10000L ),
                new AmountDiscountPolicy( Money.wons( 800L ),
                        new SequenceCondition( 1 ),
                        new SequenceCondition( 10 ),
                        new PeriodCondition( DayOfWeek.MONDAY, LocalTime.of( 10, 0 ), LocalTime.of( 11, 59 ) ),
                        new PeriodCondition( DayOfWeek.THURSDAY, LocalTime.of( 10, 0 ), LocalTime.of( 20, 59 ) )
                        )
                );

        Movie titanic = new Movie( "타이타닉",
                Duration.ofMinutes( 180 ),
                Money.wons( 11000L ),
                new AmountDiscountPolicy( Money.wons( 800L ),
                        new PeriodCondition( DayOfWeek.TUESDAY, LocalTime.of( 14, 0 ), LocalTime.of( 16, 59 ) ),
                        new SequenceCondition( 2 ),
                        new PeriodCondition( DayOfWeek.THURSDAY, LocalTime.of( 10, 0 ), LocalTime.of( 13, 59 ) )
                )
        );

        Movie starWars = new Movie( "스타워즈",
                Duration.ofMinutes( 210 ),
                Money.wons( 10000L ),
                new NoneDiscountPolicy() );
    }
}
