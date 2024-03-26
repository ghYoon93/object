package me.yghee.ch11_composition_and_flexible_design.s03_합성_관계로_변경하기;

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
