package me.yghee.ch01_object_design.s03_설계_개선;

/**
 * 소극장
 * 관람객을 맞이한다.
 */
public class Theater {
    private TicketSeller ticketSeller;

    public Theater( TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter( Audience audience) {
        ticketSeller.sellTo( audience );
    }
}
