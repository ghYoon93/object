package me.yghee.ch11_composition_and_flexible_design.s03_합성_관계로_변경하기;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Phone {
    private RatePolicy ratePolicy;
    private List<Call> calls = new ArrayList<>();

    public Phone ( RatePolicy ratePolicy ) {
        this.ratePolicy = ratePolicy;
    }

    public Money calculateFee() {
        return ratePolicy.calculate( this );
    }

    public List<Call> getCalls() {
        return Collections.unmodifiableList( calls );
    }
}
