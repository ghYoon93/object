package me.yghee.ch05_responsibility_assignment.code;

public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition ( int sequence ) {
        this.sequence = sequence;
    }

    public boolean isSatisfiedBy(Screening screening) {
        return sequence == screening.getSequence();
    }
}
