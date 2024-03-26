package me.yghee.ch11_composition_and_flexible_design.s01_상속을_합성으로_변경하기;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class InstrumentedHashSet<E> implements Set<E>{
    private int addCount = 0;
    private Set<E> set;

    public InstrumentedHashSet(Set<E> set) {
        this.set = set;
    }

    @Override
    public int size () {
        return set.size();
    }

    @Override
    public boolean isEmpty () {
        return set.isEmpty();
    }

    @Override
    public boolean contains ( Object o ) {
        return set.contains( o );
    }

    @Override
    public Iterator<E> iterator () {
        return set.iterator();
    }

    @Override
    public Object[] toArray () {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray ( T[] a ) {
        return set.toArray( a );
    }

    @Override
    public boolean add( E e) {
        addCount++;
        return set.add(e);
    }

    @Override
    public boolean remove ( Object o ) {
        return set.remove( o );
    }

    @Override
    public boolean containsAll ( Collection<?> c ) {
        return set.containsAll( c );
    }

    @Override
    public boolean addAll( Collection<? extends E> c) {
        addCount += c.size();
        return set.addAll( c );
    }

    @Override
    public boolean retainAll ( Collection<?> c ) {
        return set.retainAll( c );
    }

    @Override
    public boolean removeAll ( Collection<?> c ) {
        return set.remove( c );
    }

    @Override
    public void clear () {
        set.clear();
    }

    public int getAddCount() {
        return addCount;
    }
}
