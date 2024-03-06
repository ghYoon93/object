package me.yghee.ch04_design_quality_and_tradeoffs.s01_데이터_중심의_영화_예매_시스템.code;

import java.time.LocalDateTime;

public class Screening {

    // 데이터 준비
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    // 캡슐화
    public Movie getMovie () {
        return movie;
    }

    public void setMovie ( Movie movie ) {
        this.movie = movie;
    }

    public LocalDateTime getWhenScreened () {
        return whenScreened;
    }

    public void setWhenScreened ( LocalDateTime whenScreened ) {
        this.whenScreened = whenScreened;
    }

    public int getSequence () {
        return sequence;
    }

    public void setSequence ( int sequence ) {
        this.sequence = sequence;
    }
}
