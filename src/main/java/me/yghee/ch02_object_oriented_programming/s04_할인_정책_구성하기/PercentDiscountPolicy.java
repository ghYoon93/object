package me.yghee.ch02_object_oriented_programming.s04_할인_정책_구성하기;

public class PercentDiscountPolicy extends DiscountPolicy {
    private double percent; // 할인 비율

    public PercentDiscountPolicy ( double percent, DiscountCondition... conditions ) {
        super( conditions );
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount ( Screening screening ) {
        return screening.getMovieFee().times(percent);
    }
}
