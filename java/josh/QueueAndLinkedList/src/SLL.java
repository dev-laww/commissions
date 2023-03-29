public class SLL<T extends Comparable<T>> {
    private SLLNode<T> head;

    public SLL() {
        head = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addToHead(T info) {
        head = new SLLNode<>(info);
    }

    public void addToTail(T info) {
        if (isEmpty()) {
            addToHead(info);
            return;
        }

        SLLNode<T> temp = head;

        while (temp.getNext() != null)
            temp = temp.getNext();

        temp.setNext(new SLLNode<>(info));

    }

    public T deleteFromHead() {
        if (isEmpty())
            return null;

        T info = head.getInfo();

        if (head.getNext() == null)
            head = null;
        else
            head = head.getNext();

        return info;
    }

    public T deleteFromTail() {
        if (isEmpty())
            return null;

        T info;

        if (head.getNext() == null) {
            info = head.getInfo();
            head = null;
        } else {
            SLLNode<T> temp = head;
            while (temp.getNext().getNext() != null)
                temp = temp.getNext();
            info = temp.getNext().getInfo();
            temp.setNext(null);
        }
        return info;
    }

    public void printAll() {
        SLLNode<T> temp = head;

        while (temp != null) {
            System.out.print(temp.getInfo() + " ");
            temp = temp.getNext();
        }

        System.out.println();
    }

    public void insertAsc(T info) {
        if (isEmpty() || info.compareTo(head.getInfo()) <= 0) {
            addToHead(info);
            return;
        }

        SLLNode<T> prev = head;
        SLLNode<T> curr = head.getNext();

        while (curr != null && info.compareTo(curr.getInfo()) > 0) {
            prev = curr;
            curr = curr.getNext();
        }

        prev.setNext(new SLLNode<>(info));
        prev.getNext().setNext(curr);
    }
}