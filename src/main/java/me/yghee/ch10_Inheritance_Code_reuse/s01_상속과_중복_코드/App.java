package me.yghee.ch10_Inheritance_Code_reuse.s01_상속과_중복_코드;

import me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.Money;

import java.time.Duration;
import java.time.LocalDateTime;

public class App {

    public static void main ( String[] args ) {
        Phone phone = new Phone( Money.wons( 5L ), Duration.ofSeconds( 10 ) );
        phone.call( new Call( LocalDateTime.of( 2018, 1, 1, 12, 10, 0 ),
                LocalDateTime.of( 2018, 1, 1, 12, 11, 0 ) ) );
        phone.call( new Call( LocalDateTime.of( 2018, 1, 2, 12, 10, 0 ),
                LocalDateTime.of( 2018, 1, 2, 12, 11, 0 ) ) );

        phone.calculateFee();
    }
}
