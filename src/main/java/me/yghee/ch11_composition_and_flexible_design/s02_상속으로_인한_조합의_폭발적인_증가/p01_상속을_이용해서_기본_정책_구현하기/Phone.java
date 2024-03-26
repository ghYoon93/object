package me.yghee.ch11_composition_and_flexible_design.s02_상속으로_인한_조합의_폭발적인_증가.p01_상속을_이용해서_기본_정책_구현하기;

import java.util.ArrayList;
import java.util.List;

public abstract class Phone {
    private List<Call> calls = new ArrayList<>();

    public Money calculateFee() {
        Money result = Money.ZERO;
        for ( Call call : calls ) {
            result = result.plus( calculateCallFee( call ) );
        }

        return result;
    }

    abstract protected Money calculateCallFee( Call call );
}
