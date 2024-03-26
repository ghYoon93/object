package me.yghee.ch11_composition_and_flexible_design.s03_합성_관계로_변경하기;

import java.time.Duration;

public class RegularPolicy extends BasicRatePolicy {
    private Money amount;
    private Duration seconds;
    @Override
    protected Money calculateCallFee ( Call call ) {
        return amount.times( call.getDuration().getSeconds() / seconds.getSeconds() );
    }
}
