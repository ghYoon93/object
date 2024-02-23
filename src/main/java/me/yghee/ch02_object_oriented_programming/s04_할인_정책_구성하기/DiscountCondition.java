package me.yghee.ch02_object_oriented_programming.s04_할인_정책_구성하기;

public interface DiscountCondition {
    boolean isSatisfiedBy( Screening screening);
}
