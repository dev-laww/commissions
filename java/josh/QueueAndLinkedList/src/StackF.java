public class StackF<T> {
    private int maxStkSize = 100;
    private int top = -1;
    private Object[] stk;
    private T el;


    public StackF() {
        this.stk = new Object[maxStkSize];
    }

    public StackF(int n) {
        if (n > 0) maxStkSize = n;
        this.stk = new Object[maxStkSize];
    }

    public Object push(T el) {
        if (isFull())
            return -999;

        top = top + 1;
        this.stk[top] = el;
        return this.stk[top];
    }

    public T pop() {
        if (isEmpty())
            return null;

        this.el = (T) this.stk[top];
        top = top - 1;
        return this.el;

    }

    public T peek() {
        if (isEmpty()) return null;

        return (T) stk[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxStkSize;
    }

    public void clear(Object[] stk) {
        this.top = -1;
    }

}