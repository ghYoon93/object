package me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성;

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
