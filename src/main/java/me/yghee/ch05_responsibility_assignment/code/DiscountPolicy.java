package me.yghee.ch05_responsibility_assignment.code;

import java.util.Arrays;
import java.util.List;

public abstract class DiscountPolicy {
    private List<DiscountCondition> conditions;

    public DiscountPolicy ( DiscountCondition... conditions ) {
        this.conditions = Arrays.asList( conditions );
    }

    public Money calculateDiscountAmount( Screening screening ) {
        for ( DiscountCondition condition : conditions ) {
            if ( condition.isSatisfiedBy( screening ) ) {
                return getDiscountAmount( screening );
            }
        }

        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount( Screening screening );
}
