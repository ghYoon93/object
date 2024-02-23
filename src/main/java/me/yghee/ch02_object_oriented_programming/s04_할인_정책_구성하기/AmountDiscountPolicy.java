package me.yghee.ch02_object_oriented_programming.s04_할인_정책_구성하기;

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
