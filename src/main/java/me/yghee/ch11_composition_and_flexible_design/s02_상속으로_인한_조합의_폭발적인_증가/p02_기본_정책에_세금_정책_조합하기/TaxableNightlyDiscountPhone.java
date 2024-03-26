package me.yghee.ch11_composition_and_flexible_design.s02_상속으로_인한_조합의_폭발적인_증가.p02_기본_정책에_세금_정책_조합하기;

import java.time.Duration;

public class TaxableNightlyDiscountPhone extends NightlyDiscountPhone {

    private double taxRate;
    public TaxableNightlyDiscountPhone ( Money nightlyAmount, Money regularAmount, Duration seconds ) {
        super( nightlyAmount, regularAmount, seconds );
    }

    @Override
    protected Money afterCalculated ( Money fee ) {
        return fee.plus( fee.times( taxRate ) );
    }
}
