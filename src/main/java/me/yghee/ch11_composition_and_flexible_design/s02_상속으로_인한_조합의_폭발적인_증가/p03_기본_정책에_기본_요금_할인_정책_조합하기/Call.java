package me.yghee.ch11_composition_and_flexible_design.s02_상속으로_인한_조합의_폭발적인_증가.p03_기본_정책에_기본_요금_할인_정책_조합하기;

import java.time.Duration;
import java.time.LocalDateTime;

public class Call {
    private LocalDateTime from;
    private LocalDateTime to;

    public Call(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }

    public Duration getDuration() {
        return Duration.between(from, to);
    }

    public LocalDateTime getFrom() {
        return from;
    }
}
