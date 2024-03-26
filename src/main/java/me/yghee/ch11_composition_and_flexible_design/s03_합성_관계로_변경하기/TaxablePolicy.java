package me.yghee.ch11_composition_and_flexible_design.s03_합성_관계로_변경하기;

public class TaxablePolicy extends AdditionalRatePolicy {

    private double taxRatio;

    public TaxablePolicy ( RatePolicy next, double taxRatio ) {
        super( next );
        this.taxRatio = taxRatio;
    }

    @Override
    protected Money afterCalculated ( Money fee ) {
        return fee.plus( fee.times( taxRatio ) );
    }
}
