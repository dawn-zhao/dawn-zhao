package com.dawn.zhao.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyLinkedList<T> implements List<T> {


    int size;

    private Item<T> first;

    private Item<T> last;

    private Item<T> node(int index){
        if(index > (size >> 1)) {
            Item<T> item = last;
            for (int i = size - 1; i > index; i--) {
                item = item.prev;
            }
            return item;
        } else {
            Item<T> item = first;
            for (int i = 0; i < index; i++) {
                item = item.next;
            }
            return item;
        }
    }

    private void linkFirst(T item){
        Item<T> f = first;
        Item<T> t = new Item<>(item, null, f);
        first = t;
        if(f == null){
            last = t;
        } else {
            f.prev = t;
        }
        size++;
    }

    private void linkLast(T item){
        Item<T> l = last;
        Item<T> t = new Item<>(item, l, null);
        last = t;
        if(l == null) {
            first = t;
        } else {
            l.next = t;
        }
        size++;
    }

    public void linkBefore(Item<T> before, T element){
        Item<T> p = before.prev;
        Item<T> item = new Item<>(element, p, before);
        before.prev = item;
        if(null == p) {
            first = item;
        } else {
            p.next = item;
        }
        size++;
    }

    class Item<T> {
        T value;
        Item<T> prev;
        Item<T> next;

        public Item() {
        }

        public Item(T value, Item<T> prev, Item<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public void add(int index, T element) {
        if (index == size){
            linkLast(element);
        } else if(index == 0){
            linkFirst(element);
        } else{
            linkBefore(node(index), element);
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

}
