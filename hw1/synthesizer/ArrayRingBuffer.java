package synthesizer;// TODO: Make sure to make this class a part of the synthesizer package
// package <package name>;
import synthesizer.AbstractBoundedQueue;
import synthesizer.BoundedQueue;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> implements Iterable<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.rb = (T[]) new Object[capacity];
        this.capacity = capacity;
        this.fillCount = 0;
        this.first = 0;
        this.last = 0;
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if(isFull()){
            throw new RuntimeException("Trying to enqueue a full queue.");
        }
        rb[last] = x;

        last = (last + 1) % capacity;
        fillCount++;
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if(isEmpty()){
            throw new RuntimeException("Trying to dequeue a empty queue.");
        }
        T item = rb[first];
        rb[first] = null;

        first = (first + 1) % capacity;
        fillCount--;
        return item;
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if(isEmpty()){
            throw new RuntimeException("Trying to peek empty queue.");
        }
        return rb[first];
        // TODO: Return the first item. None of your instance variables should change.
    }

    public int capacity(){
        return this.capacity;
    }

    public int fillCount(){
        return this.fillCount;
    }
    // TODO: When you get to part 5, implement the needed code to support iteration.

    public Iterator<T> iterator(){
        return new Iterator<T>(){
            private int currPos = first;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < fillCount;
            }

            public T next(){
                T item = rb[currPos];
                currPos = (currPos + 1) % capacity;
                count++;
                return item;
            }
        };
    }
}
