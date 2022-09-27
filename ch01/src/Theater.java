public class Theater {
		private TicketSeller ticketSeller;
		
		public Theater( TicketSeller ticketSeller ) {
				this.ticketSeller = ticketSeller;
		}
		
		// 관람객과 판매원이 소극장의 통제를 받는 수동적인 존재
		// 소극장이 매표소의 티켓과 현금에 접금
		// 소극장이 관람객의 가방에 접근
		// 가방에서 티켓과 돈을 꺼내는 일이나 매표소에서 티켓을 주고 돈을 받는 등의 행위가 모두 소극장에 의해 이루어짐
		// Audience 와 TicketSeller 를 변경할 경우 Theater 도 함께 변경이 필요
		public void enter( Audience audience ) {
				if ( audience.getBag().hasInvitation() ) {
						Ticket ticket = ticketSeller.getTicketOffice().getTicket();
						audience.getBag().setTicket( ticket );
				} else {
						Ticket ticket = ticketSeller.getTicketOffice().getTicket();
						audience.getBag().minusAmount( ticket.getFee() );
						ticketSeller.getTicketOffice().plusAmount( ticket.getFee() );
						audience.getBag().setTicket( ticket );
				}
		}
}
