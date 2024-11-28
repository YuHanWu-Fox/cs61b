package deque;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LinkedListDeque<T>  {

    private class Node {
        public Node before;
        public T item;
        public Node next;
        public Node(Node b, T i, Node n) {
            before = b;
            item = i;
            next = n;
        }
    }

    private Node sentinal;
    private int size;


    public LinkedListDeque() {
        sentinal = new Node(null, null, null);
        sentinal.before = sentinal;
        sentinal.next = sentinal;
        size = 0;
    }


    public void addFirst(T x) {
        sentinal.next = new Node(sentinal, x, sentinal.next);
        sentinal.next.next.before = sentinal.next;
        size += 1;
    }


    public void addLast(T x) {
        sentinal.before = new Node(sentinal.before, x, sentinal);
        sentinal.before.before.next = sentinal.before;
        size += 1;
    }


    public List<T> toList() {
        List<T> lst = new ArrayList<>();
        Node current = sentinal.next;
        while (current.item != null){
            lst.add(current.item);
            current = current.next;
        }
        return lst;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }


    public T removeFirst() {
        if (this.size == 0){
            return null;
        } else {
            T returnitem = sentinal.next.item;
            sentinal.next = sentinal.next.next;
            sentinal.next.before = sentinal;
            this.size -=1;
            return returnitem;
        }
    }


    public T removeLast() {
        if (this.size == 0){
            return null;
        } else {
            T returnitem = sentinal.before.item;
            sentinal.before = sentinal.before.before;
            sentinal.before.next=sentinal;
            this.size -=1;
            return returnitem;
        }
    }


    public T get(int index) {
        if (index <0 || index > this.size - 1) {
            return null;
        } else {
            Node help = sentinal.next;
            while (index != 0) {
                help = help.next;
                index -=1;
            }
            return help.item;
        }
    }


    public T getRecursive(int index) {
        if (index <0 || index>this.size - 1) {
            return null;
        } else {
            return helpergetRecursive(index, sentinal.next);
        }
    }

    private T helpergetRecursive(int index, Node x){
        if (index == 0){
            return x.item;
        } else {
            return helpergetRecursive(index - 1, x.next);
        }
    }

    public void printDeque() {
        List<T> returnlist = toList();
        System.out.println(
                returnlist.stream()
                        .map(Object::toString)
                        .collect(Collectors.joining(" "))
        );
    }
}
