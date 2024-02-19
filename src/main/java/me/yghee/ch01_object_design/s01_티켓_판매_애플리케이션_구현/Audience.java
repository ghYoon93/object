package me.yghee.ch01_object_design.s01_티켓_판매_애플리케이션_구현;

/**
 * 관람객. 소지품을 보관하기 위해 가방을 소지할 수 있다.
 */
public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    public Bag getBag() {
        return bag;
    }
}
