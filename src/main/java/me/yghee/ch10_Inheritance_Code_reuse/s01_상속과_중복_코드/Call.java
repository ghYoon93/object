package me.yghee.ch10_Inheritance_Code_reuse.s01_상속과_중복_코드;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 개별 통화 기간을 저장
 * 통화 시작 시간과 통화 종료 시간 포함
 */
public class Call {
    private LocalDateTime from;
    private LocalDateTime to;

    public Call( LocalDateTime from, LocalDateTime to ) {
        this.from = from;
        this.to = to;
    }

    public Duration getDuration() {
        return Duration.between( from, to );
    }

    public LocalDateTime getFrom() {
        return from;
    }
}
