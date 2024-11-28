package deque;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class ArrayDeque<T> {

    public T[] items;
    public int next_front;
    public int next_back;
    public int size;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        next_front = 0;
        next_back = 1;
    }


    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[Math.floorMod(next_front, items.length)] = x;
        next_front -= 1;
        size += 1;
    }


    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[Math.floorMod(next_back, items.length)] = x;
        next_back += 1;
        size += 1;
    }


    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int front = next_front + 1;
        while (front < next_back) {
            returnList.add(items[Math.floorMod(front, items.length)]);
            front += 1;
        }
        return returnList;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }


    public T removeFirst() {
        if (this.size == 0) {
            return null;
        } else {
            int front = next_front + 1;
            next_front += 1;
            size -= 1;
            if (items.length >= 16 && (double) size / items.length < 0.25){
                resize(items.length / 2);
            }
            return this.items[Math.floorMod(front, items.length)];
        }
    }


    public T removeLast() {
        if (this.size == 0) {
            return null;
        } else {
            int back = next_back - 1;
            next_back = next_back - 1;
            size -= 1;
            return this.items[Math.floorMod(back, items.length)];
        }
    }


    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        } else {
            int front = next_front + 1;
            return items[Math.floorMod(index + front, items.length)];
        }
    }


    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    private void resize(int capcacity) {
        T[] new_items = (T[]) new Object[capcacity];
        int front = next_front + 1;
        for (int i = 0; i < size; i++){
            new_items[i] = items[Math.floorMod(front + i, items.length)];
        }
        next_back = size;
        next_front = -1;
        items = new_items;
    }

}

