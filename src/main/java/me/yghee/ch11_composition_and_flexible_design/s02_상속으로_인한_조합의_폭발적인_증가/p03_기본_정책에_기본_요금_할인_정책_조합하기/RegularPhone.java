package me.yghee.ch11_composition_and_flexible_design.s02_상속으로_인한_조합의_폭발적인_증가.p03_기본_정책에_기본_요금_할인_정책_조합하기;

import java.time.Duration;

public class RegularPhone extends Phone {

    private Money amount;
    private Duration seconds;

    public RegularPhone ( Money amount, Duration seconds ) {
        this.amount = amount;
        this.seconds = seconds;
    }

    @Override
    protected Money calculateCallFee ( Call call ) {
        return amount.times(call.getDuration().getSeconds() / seconds.getSeconds() );
    }
}
