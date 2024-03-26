package me.yghee.ch11_composition_and_flexible_design.s03_합성_관계로_변경하기;

import java.time.Duration;

public class Main {

    public static void main ( String[] args ) {
        // 일반 요금제에 세금 정책 조합
        Phone phoneRegularTax = new Phone(
                new TaxablePolicy( new RegularPolicy(),
                        0.05 )
        );

        // 일반 요금제에 기본 요금 할인 정책을 조합한 결과에 세금 정책 조합
        Phone phoneRegularRateTax = new Phone(
                new RateDiscountablePolicy(
                        new TaxablePolicy(
                                new RegularPolicy(),
                                0.05
                        ),
                        Money.wons( 1000 )
                )
        );

        // 심야 요금제에 기본 요금 할인 정책을 조합한 결과에 세금 정책 조합
        Phone nightlyPhoneRegularRateTax = new Phone(
                new RateDiscountablePolicy(
                        new TaxablePolicy(
                                new NightlyDiscountPolicy(
                                        Money.wons( 2000 ),
                                        Money.wons( 3000 ), Duration.ofSeconds( 10 )
                                ),
                                0.05
                        ),
                        Money.wons( 1000 )
                )
        );
    }
}
