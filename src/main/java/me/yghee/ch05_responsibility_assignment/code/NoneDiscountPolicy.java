package me.yghee.ch05_responsibility_assignment.code;

import me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.DiscountPolicy;
import me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.Money;
import me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.Screening;

public class NoneDiscountPolicy extends DiscountPolicy {
    @Override
    protected me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.Money getDiscountAmount ( Screening screening ) {
        return Money.ZERO;
    }
}
