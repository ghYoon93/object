package me.yghee.ch04_design_quality_and_tradeoffs.s01_데이터_중심의_영화_예매_시스템.code;

public class Reservation {
    // 데이터 준비
    private Customer customer;
    private
    Screening screening;
    private Money fee;
    private int audienceCount;

    public Reservation ( Customer customer, Screening screening, Money fee, int audienceCount ) {
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }

    //캡슐화
    public Customer getCustomer () {
        return customer;
    }

    public void setCustomer ( Customer customer ) {
        this.customer = customer;
    }

    public Screening getScreening () {
        return screening;
    }

    public void setScreening ( Screening screening ) {
        this.screening = screening;
    }

    public Money getFee () {
        return fee;
    }

    public int getAudienceCount () {
        return audienceCount;
    }

    public void setAudienceCount ( int audienceCount ) {
        this.audienceCount = audienceCount;
    }


    public void setFee ( Money fee ) {
        this.fee = fee;
    }
}
