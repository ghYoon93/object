package me.yghee.ch01_object_design.s01_티켓_판매_애플리케이션_구현;

public class Bag {

    private Long amount; // 현금
    private Invitation invitation; // 초대장
    private Ticket ticket; // 티켓

    /**
     * 이벤트에 당첨되지 않은 관람객
     * @param amount 소유한 현금
     */
    public Bag(Long amount) {
        this(null, amount);
    }

    /**
     * 이벤트에 당첨된 관람객
     * @param invitation 초대장
     * @param amount 소유한 현금
     */
    public Bag(Invitation invitation, Long amount) {
        this.invitation = invitation;
        this.amount = amount;
    }

    /**
     * 티켓 소유 여부 판단
     * @return 티켓이 있으면 true, 없으면 false
     */
    public boolean hasTicket() {
        return ticket != null;
    }

    /**
     * 초대장 소유 여부 판단
     * @return 초대장이 있으면 true, 없으면 false
     */
    public boolean hasInvitation() {
        return invitation != null;
    }

    /**
     * 초대장을 티켓으로 교환
     * @param ticket - 티켓
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    /**
     * 현금을 감소시킨다.
     * @param amount 감소시킬 현금량
     */
    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    /**
     * 현금을 증가시킨다.
     * @param amount 증가시킬 현금량
     */
    public void plusAmount(Long amount) {
        this.amount += amount;
    }

}
