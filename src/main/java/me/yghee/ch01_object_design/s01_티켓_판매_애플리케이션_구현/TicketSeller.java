package me.yghee.ch01_object_design.s01_티켓_판매_애플리케이션_구현;

/**
 * 판매원
 * 매표소에서 초대장을 티켓으로 교환해 주거나 티켓을 판매하는 역할 수행
 */
public class TicketSeller {
    private TicketOffice ticketOffice;

     public TicketSeller(TicketOffice ticketOffice) {
         this.ticketOffice = ticketOffice;
     }

     public TicketOffice getTicketOffice() {
         return ticketOffice;
     }
}
