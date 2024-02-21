package me.yghee.ch02_object_oriented_programming.s03_할인_요금_구하기;

public interface DiscountCondition {
    boolean isSatisfiedBy(Screening screening);
}
