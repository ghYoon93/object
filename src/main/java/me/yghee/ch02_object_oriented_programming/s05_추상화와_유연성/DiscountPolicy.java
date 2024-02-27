package me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DiscountPolicy {
    private List<DiscountCondition> conditions = new ArrayList<>(); // 할인 조건들

    public DiscountPolicy( DiscountCondition... conditions) {
        this.conditions = Arrays.asList( conditions );
    }

    /**
     * 할인조건을 판단하여 할인 요금을 반환한다.
     * @param screening
     * @return
     */
    public Money calculateDiscountAmount( Screening screening) {
        for( DiscountCondition each : conditions) {
            if ( each.isSatisfiedBy(screening)) {
                return getDiscountAmount(screening);
            }
        }
        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount( Screening screening);
}
