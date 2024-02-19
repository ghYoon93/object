package me.yghee.ch01_object_design.s01_티켓_판매_애플리케이션_구현;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 매표소. 초대장을 티켓으로 교환하거나 현금으로 티켓을 구매하는 곳.
 *
 */
public class TicketOffice {
    private Long amount; // 판매 금액
    private List<Ticket> tickets = new ArrayList<>(); // 교환해줄 티켓 목록

    public TicketOffice(Long amount, Ticket ... tickets ) {
        this.amount = amount;
        this.tickets.addAll( Arrays.asList(tickets));
    }

    /**
     * 티켓 판매
     * @return 판매할 티켓
     */
    public Ticket getTicket() {
        return tickets.remove( 0 );
    }

    /**
     * 현금 차감
     * @param amount 차감할 현금
     */
    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    /**
     * 현금 추가
     * @param amount 추가할 현금
     */
    public void plusAmount(Long amount) {
        this.amount += amount;
    }

}
