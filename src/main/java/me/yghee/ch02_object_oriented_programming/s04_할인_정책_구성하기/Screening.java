package me.yghee.ch02_object_oriented_programming.s04_할인_정책_구성하기;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie; // movie - 영화
    private int sequence; // 순번
    private LocalDateTime whenScreened; // 상영 시작 시간

    public Screening ( Movie movie, int sequence, LocalDateTime whenScreened ) {
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    /**
     * 상영 시작 시간 반환
     * @return
     */
    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    /**
     * 순번의 일치 여부 검사
     * @param sequence 검사할 순번
     * @return
     */
    public boolean isSequence(int sequence) {
        return this.sequence == sequence;
    }

    /**
     * 영화 예매
     * 고객은(Customer)은 인원수(audienceCount)만큼 상영(Screening)을 예약(reserve)할 수 있다.
     * @param customer 예매자
     * @param audienceCount 인원 수
     * @return
     */
    public Reservation reserve( Customer customer, int audienceCount) {
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    /**
     * 1인 당 영화 요금을 계산하여 인원 수만큼 곱한다.
     * 상영(Screening)은 인원수(audienceCount)만큼 금액을 계산할 수 있다.
     * @param audienceCount
     * @return 총 예매 금액
     */
    private Money calculateFee( int audienceCount) {
        return movie.calculateMovieFee(this).times( ( double ) audienceCount );
    }

    public Money getMovieFee () {
        return movie.getFee();
    }
}
