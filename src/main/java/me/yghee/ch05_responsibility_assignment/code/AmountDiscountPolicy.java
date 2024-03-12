package me.yghee.ch05_responsibility_assignment.code;

import me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.DiscountCondition;
import me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.DiscountPolicy;
import me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.Money;
import me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.Screening;

public class AmountDiscountPolicy extends DiscountPolicy {
    private me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.Money discountAmount; // 할인 요금

    public AmountDiscountPolicy ( me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성.Money discountAmount, DiscountCondition... conditions ) {
        super( conditions );
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount ( Screening screening ) {
        return discountAmount;
    }
}
