package hashset;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class MyHashSet<T>  implements Iterable<T>{
    private LinkedList<T>[] hashset;
    private int capacity = 16;
    private double loadFactor = 0.75;
    private int size;

    public MyHashSet() {
        hashset = new LinkedList[capacity];
    }

    public boolean add(T element) {
        if (size > capacity * loadFactor) {
            rebalance();
        }
        int index = getIndex(element);
        if (hashset[index] == null) {
            hashset[index]= new LinkedList<>();

        }
        if (hashset[index].contains(element)) {
            return false;
        }
        hashset[index].add(element);
        size++;
        return true;
    }

    public boolean contains(T element) {
        int index = getIndex(element);
        if (hashset[index] == null) {
            return false;
        }
        return hashset[index].contains(element);
    }

    public boolean remove(T element) {
        int index = getIndex(element);
        if (hashset[index] == null || !hashset[index].contains(element)) {
            return false;
        }
        if (hashset[index].remove(element)) {
            if (hashset[index].isEmpty()) {
                hashset[index] = null;
            }
            size--;
            return true;
        }
        return false;
    }
    private void rebalance() {
        capacity *= 2;
        LinkedList<T>[] newHashSet = new LinkedList[capacity];
        for (int i = 0; i < hashset.length; i++) {
            if (hashset[i] != null) {
                for (T e : hashset[i]) {
                    int index = getIndex(e);
                    if (newHashSet[index] == null) {
                        newHashSet[index] = new LinkedList<>();
                    }
                    newHashSet[index].add(e);
                }
            }
        }
        hashset = newHashSet;
    }
    private int getIndex(T element) {
        int hashcode = element.hashCode();
        hashcode = hashcode > 0 ? hashcode : -hashcode;
        return hashcode % capacity;
    }

    @Override
    public Iterator<T> iterator() {
        return new DeepIterator();
    }

    private class DeepIterator implements Iterator<T>{
        Iterator<LinkedList<T>> listIterator = Arrays.stream(hashset)
                .filter(obj -> obj!=null && !obj.isEmpty())
                .iterator();
        Iterator<T> valueIterator;
        T elementOfNext;
        boolean isNext = false;

        public DeepIterator() {
            if (listIterator.hasNext()) {
                valueIterator = listIterator.next().iterator();
            }
        }

        @Override
        public boolean hasNext() {
            if (listIterator.hasNext()||valueIterator.hasNext()) {
                if (valueIterator.hasNext()) {
                    return true;
                }
                valueIterator= listIterator.next().iterator();
                return true;

            }
            return false;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            isNext = true;
            elementOfNext = valueIterator.next();
            return elementOfNext;
        }

        @Override
        public void remove() {
            if (!isNext) {
                throw new IllegalStateException();
            }
            MyHashSet.this.remove(elementOfNext);
            isNext = false;
        }
    }

}
