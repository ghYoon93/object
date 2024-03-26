package me.yghee.ch11_composition_and_flexible_design.s02_상속으로_인한_조합의_폭발적인_증가.p01_상속을_이용해서_기본_정책_구현하기;

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
