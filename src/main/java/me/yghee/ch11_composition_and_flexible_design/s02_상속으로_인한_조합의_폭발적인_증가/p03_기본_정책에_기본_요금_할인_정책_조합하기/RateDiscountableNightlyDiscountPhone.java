package me.yghee.ch11_composition_and_flexible_design.s02_상속으로_인한_조합의_폭발적인_증가.p03_기본_정책에_기본_요금_할인_정책_조합하기;

import me.yghee.ch11_composition_and_flexible_design.s02_상속으로_인한_조합의_폭발적인_증가.p02_기본_정책에_세금_정책_조합하기.Money;
import me.yghee.ch11_composition_and_flexible_design.s02_상속으로_인한_조합의_폭발적인_증가.p02_기본_정책에_세금_정책_조합하기.NightlyDiscountPhone;

import java.time.Duration;

public class RateDiscountableNightlyDiscountPhone extends NightlyDiscountPhone {

    private Money discountAmount;
    public RateDiscountableNightlyDiscountPhone ( Money nightlyAmount, Money regularAmount, Duration seconds, Money discountAmount ) {
        super( nightlyAmount, regularAmount, seconds );
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money afterCalculated ( Money fee ) {
        return fee.minus( discountAmount );
    }
}
