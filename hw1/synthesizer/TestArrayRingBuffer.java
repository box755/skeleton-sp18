package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testEnqueueDequeue() {
        ArrayRingBuffer<Integer> buffer = new ArrayRingBuffer<>(5);

        // 初始狀態測試
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
        assertEquals(0, buffer.fillCount());

        // 添加元素
        buffer.enqueue(1);
        buffer.enqueue(2);
        buffer.enqueue(3);

        assertFalse(buffer.isEmpty());
        assertEquals(3, buffer.fillCount());

        // 查看首元素
        assertEquals((Integer) 1, buffer.peek());

        // 移除元素
        assertEquals((Integer) 1, buffer.dequeue());
        assertEquals((Integer) 2, buffer.peek());
        assertEquals(2, buffer.fillCount());

        buffer.dequeue();
        buffer.dequeue();
        assertTrue(buffer.isEmpty());
    }

    @Test
    public void testDequeueFromEmptyBuffer() {
        ArrayRingBuffer<Integer> buffer = new ArrayRingBuffer<>(3);
        buffer.dequeue(); // 應拋出異常
    }

    @Test
    public void testEnqueueToFullBuffer() {
        ArrayRingBuffer<Integer> buffer = new ArrayRingBuffer<>(2);
        buffer.enqueue(1);
        buffer.enqueue(2);
        buffer.enqueue(3); // 應拋出異常
    }

    @Test
    public void testWrapAround() {
        ArrayRingBuffer<Integer> buffer = new ArrayRingBuffer<>(3);

        buffer.enqueue(1);
        buffer.enqueue(2);
        buffer.enqueue(3);
        assertTrue(buffer.isFull());

        assertEquals((Integer) 1, buffer.dequeue());
        buffer.enqueue(4);

        // 確認回繞後的正確性
        assertEquals((Integer) 2, buffer.dequeue());
        assertEquals((Integer) 3, buffer.dequeue());
        assertEquals((Integer) 4, buffer.dequeue());
        assertTrue(buffer.isEmpty());
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> buffer = new ArrayRingBuffer<>(4);
        buffer.enqueue(1);
        buffer.enqueue(2);
        buffer.enqueue(3);
        buffer.enqueue(4);


        buffer.dequeue();

        buffer.dequeue();

        for(int x : buffer){
            System.out.println(x);
        }



    }
    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
