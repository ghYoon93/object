package me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성;

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
