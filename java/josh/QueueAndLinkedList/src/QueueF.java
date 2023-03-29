public class QueueF <T> {
    private int maxQSize = 100;
    private int front = -1;
    private int rear = -1;
    private int size = 0;
    private Object[] que;

    public QueueF(){
        que = new Object[maxQSize];
    }
    public QueueF(int n){
        if(n > 0){
            maxQSize = n;
            que = new Object[maxQSize];
        }
    }
    public void clear(){
        front = -1;
        rear = -1;
    }
    public void clear(Object[]que){
        front = -1;
        rear = -1;
    }
    public boolean isEmpty(){
        if(front == -1)
            return true;
        else
            return false;
    }
    public boolean isFull(){
        if((rear + 1)%maxQSize == front)
            return true;
        else
            return false;
    }
    public int enqueue(T el){
        if(isFull())
            return -999;
        if(isEmpty())
            front = 0;
        rear = (rear + 1)%maxQSize;
        que[rear] = el;
        size++;
        return 1;
    }
    public T dequeue(){
        if(isEmpty())
            return null;
        T el = (T)que[front];
        if(front == rear)
            clear();
        else
            front = (front + 1)% maxQSize;
        size --;
        return el;
    }
    public T peek(){
        if(isEmpty())
            return null;
        else
            return (T)que[front];
    }
    public int size(){
        return size;
    }

}