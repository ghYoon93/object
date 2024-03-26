package me.yghee.ch11_composition_and_flexible_design.s01_상속을_합성으로_변경하기;

import java.util.EmptyStackException;
import java.util.Vector;

public class Stack <E> {
    private Vector<E> elements = new Vector<>();
    public E push(E item) {
        elements.addElement( item );
        return item;
    }

    public E pop() {
        if (elements.isEmpty()) {
            throw new EmptyStackException();
        }
        return elements.remove( elements.size() - 1 );
    }
}
