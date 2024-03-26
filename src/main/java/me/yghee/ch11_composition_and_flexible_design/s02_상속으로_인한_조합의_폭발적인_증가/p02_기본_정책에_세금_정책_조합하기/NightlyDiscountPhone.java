package me.yghee.ch11_composition_and_flexible_design.s02_상속으로_인한_조합의_폭발적인_증가.p02_기본_정책에_세금_정책_조합하기;

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

    @Override
    protected Money afterCalculated ( Money fee ) {
        return fee;
    }
}
