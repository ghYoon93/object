package me.yghee.ch11_composition_and_flexible_design.s03_합성_관계로_변경하기;

import java.time.Duration;

public class NightlyDiscountPhone extends Phone {

    private static final int LATE_NIGHT_HOUR = 22;

    private Money nightlyAmount;
    private Money regularAmount;
    private Duration seconds;

    public NightlyDiscountPhone ( Money nightlyAmount, Money regularAmount, Duration seconds ) {
        this.nightlyAmount = nightlyAmount;
        this.regularAmount = regularAmount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee ( Call call ) {
        if ( call.getFrom().getHour() >= LATE_NIGHT_HOUR ) {
            return nightlyAmount.times( call.getDuration().getSeconds() / seconds.getSeconds() );
        }

        return regularAmount.times( call.getDuration().getSeconds() / seconds.getSeconds() );
    }
}
