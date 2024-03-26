package me.yghee.ch11_composition_and_flexible_design.s03_합성_관계로_변경하기;

public abstract class AdditionalRatePolicy implements RatePolicy {

    private RatePolicy next;

    public AdditionalRatePolicy ( RatePolicy next ) {
        this.next = next;
    }

    @Override
    public Money calculate ( Phone phone ) {

        Money fee = next.calculate( phone );
        return afterCalculated( fee );
    }

    abstract protected Money afterCalculated( Money fee );
}
