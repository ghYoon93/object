package me.yghee.ch02_object_oriented_programming.s03_할인_요금_구하기;

public class SequenceCondition implements DiscountCondition {
    private int sequence; // 순번

    public SequenceCondition ( int sequence ) {
        this.sequence = sequence;
    }

    @Override
    public boolean isSatisfiedBy ( Screening screening ) {
        return screening.isSequence( sequence );
    }
}
