package me.yghee.ch11_composition_and_flexible_design.s03_합성_관계로_변경하기;

public class RateDiscountablePolicy extends AdditionalRatePolicy {

    private Money discountAmount;

    public RateDiscountablePolicy ( RatePolicy next, Money discountAmount ) {
        super( next );
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money afterCalculated ( Money fee ) {
        return fee.minus( discountAmount );
    }
}
