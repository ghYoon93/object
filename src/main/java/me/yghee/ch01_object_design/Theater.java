package me.yghee.ch01_object_design;

/**
 * 소극장
 * 관람객을 맞이한다.
 */
public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter(Audience audience) {
        // 관람객의 가방에 초대장이 있으면 판매원이 매표소에서 티켓을 꺼내고,
        // 소극장은 관람객은 가방에 티켓을 넣는다.
        if (audience.getBag().hasInvitation()) {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket( ticket );
        }
        // 초대장이 없으면 판매원이 매표소에서 티켓을 꺼내고
        // 소극장은 관람객의 가방에서 티켓 요금만큼 현금을 빼낸 뒤,
        // 관람객의 가방에 티켓을 넣는다.
        else {
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();;
            audience.getBag().minusAmount( ticket.getFee() );
            audience.getBag().setTicket( ticket );
        }
    }
}
