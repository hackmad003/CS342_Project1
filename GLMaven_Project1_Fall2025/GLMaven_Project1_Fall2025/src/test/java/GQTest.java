import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GQTest {

    @Test
    public void testLengthTracking() {
        GenericQueue<String> queue = new GenericQueue<>("a");
        queue.delete(); 

        assertEquals(0, queue.getLength());

        queue.enqueue("b");
        queue.enqueue("c");
        assertEquals(2, queue.getLength());

        queue.dequeue();
        assertEquals(1, queue.getLength());
    }


    @Test
    public void testAddWithCode() {
        GenericQueue<String> queue = new GenericQueue<>("a");
        queue.delete();
        queue.add("b", 42);
        GenericList.Node<String> head = queue.getHead();
        assertNotNull(head);
        assertEquals("b", head.data);
        assertEquals(42, head.code);
    }

    @Test
    public void testDumpList() {
        GenericQueue<String> queue = new GenericQueue<>("a");
        queue.delete();

        queue.enqueue("a");
        queue.enqueue("b");

        ArrayList<String> dumped = queue.dumpList();
        assertEquals(2, dumped.size());
        assertEquals("a", dumped.get(0));
        assertEquals("b", dumped.get(1));
    }

    @Test
    public void testAddAndDelete() {
        GenericQueue<String> queue = new GenericQueue<>("a");
        queue.delete();

        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");

        assertEquals("c", queue.delete());
        assertEquals("b", queue.delete());
        assertEquals("a", queue.delete());
        assertNull(queue.delete());
    }

    @Test
    public void testDescendingIterator() {
        GenericQueue<String> queue = new GenericQueue<>("a");
        queue.delete();

        queue.enqueue("a");
        queue.enqueue("b");
        queue.enqueue("c");

        Iterator<String> reverseIter = queue.descendingIterator();

        assertTrue(reverseIter.hasNext());
        assertEquals("c", reverseIter.next());
        assertEquals("b", reverseIter.next());
        assertEquals("a", reverseIter.next());
        assertFalse(reverseIter.hasNext());
    }

    @Test
    public void testGetValidAndInvalid() {
        GenericQueue<String> queue = new GenericQueue<>("a");

        queue.enqueue("b");
        queue.enqueue("c");

        assertEquals("a", queue.get(0));
        assertEquals("b", queue.get(1));
        assertEquals("c", queue.get(2));

        assertNull(queue.get(-1));
        assertNull(queue.get(3));
    }

    @Test
    public void testSetValidAndInvalid() {
        GenericQueue<String> queue = new GenericQueue<>("a");
        queue.enqueue("b");
        queue.enqueue("c");

        String old = queue.set(1, "c");
        assertEquals("b", old);
        assertEquals("c", queue.get(1));

        assertNull(queue.set(5, "a"));
    }

    @Test
    public void testIteratorForward() {
        GenericQueue<String> queue = new GenericQueue<>("a");
        queue.enqueue("b");
        queue.enqueue("c");

        Iterator<String> iter = queue.iterator();

        assertTrue(iter.hasNext());
        assertEquals("a", iter.next());
        assertEquals("b", iter.next());
        assertEquals("c", iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testDescendingIteratorEmpty() {
        GenericQueue<String> emptyQueue = new GenericQueue<>("a");
        emptyQueue.delete();

        Iterator<String> reverseIter = emptyQueue.descendingIterator();
        assertFalse(reverseIter.hasNext());
    }
}
