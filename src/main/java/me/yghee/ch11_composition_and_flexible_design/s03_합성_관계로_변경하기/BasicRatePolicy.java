package me.yghee.ch11_composition_and_flexible_design.s03_합성_관계로_변경하기;

public abstract class BasicRatePolicy implements RatePolicy {
    @Override
    public Money calculate ( Phone phone ) {
        Money result = Money.ZERO;

        for( Call call : phone.getCalls() ) {
            result.plus( calculateCallFee( call ) );
        }

        return result;
    }

    protected abstract Money calculateCallFee( Call call );
}
