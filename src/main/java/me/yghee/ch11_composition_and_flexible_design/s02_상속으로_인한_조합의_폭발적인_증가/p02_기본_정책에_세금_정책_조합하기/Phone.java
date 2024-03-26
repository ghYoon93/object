package me.yghee.ch11_composition_and_flexible_design.s02_상속으로_인한_조합의_폭발적인_증가.p02_기본_정책에_세금_정책_조합하기;

import java.util.ArrayList;
import java.util.List;

public abstract class Phone {
    private List<Call> calls = new ArrayList<>();

    public Money calculateFee() {
        Money result = Money.ZERO;
        for ( Call call : calls ) {
            result = result.plus( calculateCallFee( call ) );
        }

        return afterCalculated( result );
    }

    protected Money afterCalculated( Money fee ) {
        return fee;
    };
    abstract protected Money calculateCallFee( Call call );
}
