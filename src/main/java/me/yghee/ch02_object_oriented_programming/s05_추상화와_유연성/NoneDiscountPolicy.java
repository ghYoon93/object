package me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성;

public class NoneDiscountPolicy extends DiscountPolicy {
    @Override
    protected Money getDiscountAmount ( Screening screening ) {
        return Money.ZERO;
    }
}
