package me.yghee.ch05_responsibility_assignment.code;

import java.time.DayOfWeek;
import java.time.LocalTime;

public interface DiscountCondition {
    boolean isSatisfiedBy( Screening screening );
}
