class MyCircularQueue {
    int[] queue = null;
    int head;
    int tail;
    int length;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.queue = new int[k];
        this.head = 0;
        this.tail = 0;
        this.length = k;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if((this.tail+1)%this.length != this.head){
            int pos = this.tail++ % this.length;
            this.queue[pos] = value;
            return true;
        }
        return false;
        
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(this.head == this.tail){
            return false;
        }else{
            this.head ++;
            return true;
        }
        
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return this.queue[head];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        return this.queue[tail];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return this.head == this.tail;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return (this.tail+1)%this.length == this.head;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
class Test{
    public static void main(String[] args){
        MyCircularQueue obj = new MyCircularQueue(3);
        boolean param_1 = obj.enQueue(1);
        boolean param_2 = obj.enQueue(2);
        boolean param_3 = obj.enQueue(3);
        boolean param_4 = obj.enQueue(4);
        boolean param_5 = obj.deQueue();
        int param_6 = obj.Front();
        int param_7 = obj.Rear();
        boolean param_8 = obj.isEmpty();
        boolean param_9 = obj.isFull();
    }
}