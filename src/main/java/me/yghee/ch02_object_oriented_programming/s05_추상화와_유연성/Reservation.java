package me.yghee.ch02_object_oriented_programming.s05_추상화와_유연성;

public class Reservation {
    private Customer customer; // 고객
    private Screening screening; // 상영 정보
    private Money fee; // 예매 요금
    private int audienceCount; // 인원 수

    public Reservation ( Customer customer, Screening screening, Money fee, int audienceCount ) {
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }
}
