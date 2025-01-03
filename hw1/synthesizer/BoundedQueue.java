package synthesizer;

public interface BoundedQueue<T> {
    /**
     * Returns the capacity of the queue.
     * @return the maximum number of items the queue can hold.
     */
    int capacity();

    /**
     * Returns the number of items currently in the queue.
     * @return the number of items in the queue.
     */
    int fillCount();

    /**
     * Adds an item to the tail of the queue.
     * @param x the item to be added.
     */
    void enqueue(T x);

    /**
     * Removes and returns the item at the head of the queue.
     * @return the item at the head of the queue.
     */
    T dequeue();

    /**
     * Returns (but does not remove) the item at the head of the queue.
     * @return the item at the head of the queue.
     */
    T peek();

    /**
     * Checks if the queue is empty.
     * @return true if the queue is empty; false otherwise.
     */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /**
     * Checks if the queue is full.
     * @return true if the queue is full; false otherwise.
     */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
