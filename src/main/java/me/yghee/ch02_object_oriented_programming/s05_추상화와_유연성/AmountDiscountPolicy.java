package me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성;

public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountAmount; // 할인 요금

    public AmountDiscountPolicy ( Money discountAmount, DiscountCondition... conditions ) {
        super( conditions );
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount ( Screening screening ) {
        return discountAmount;
    }
}
