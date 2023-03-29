public class SLLNode<T> {
    private T info;
    private SLLNode<T> next;

    public SLLNode(T info) {
        this.info = info;
        this.next = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public SLLNode<T> getNext() {
        return next;
    }

    public void setNext(SLLNode<T> next) {
        this.next = next;
    }
}