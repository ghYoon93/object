package me.yghee.ch05_responsibility_assignment.code;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Screening {
    // 2. 책임을 수행하는 데 필요한 인스턴스 변수
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    // 1. 메시지를 처리할 수 있는 메서드 구현
    public Reservation reserv( Customer customer, int audienceCount ) {
        // 3. 영화 전체 예매 요금 계산 후 Reservation을 생성하고 반환
        return new Reservation( customer, this, calculateFee(audienceCount ), audienceCount );
    }

    private Money calculateFee( int audienceCount ) {
        // 3. movie에게 가격을 메시지 전송
        return movie.calculateMovieFee( this ).times( audienceCount );
    }

    public LocalDateTime getWhenScreened () {

        return whenScreened;
    }

    public int getSequence () {
        return sequence;
    }
}
