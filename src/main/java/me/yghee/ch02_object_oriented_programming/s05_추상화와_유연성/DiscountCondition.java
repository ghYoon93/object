package me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성;

public interface DiscountCondition {
    boolean isSatisfiedBy( Screening screening );
}
