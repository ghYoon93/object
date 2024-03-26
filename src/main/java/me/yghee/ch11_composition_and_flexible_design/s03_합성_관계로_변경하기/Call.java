package me.yghee.ch11_composition_and_flexible_design.s03_합성_관계로_변경하기;

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
