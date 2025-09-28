import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.ArrayList;



public class GQTest {

    private GenericQueue<Integer> queue;
    private GenericQueue<String> stringQueue;

    
    @Test
    public void testNodeConstructor() {
        GenericQueue.Node<String> node = new GenericQueue.Node<>("hello", 101);

        assertNotNull(node, "Node constructor should create a non-null object");
        assertEquals("hello", node.data, "Node data should be initialized correctly");
        assertEquals(101, node.code, "Node code should be initialized correctly");
        assertNull(node.next, "New Node's next reference should be null by default");
    }

    
    @Test
    public void testNodeConstructorWithoutCode() {
        GenericQueue.Node<Integer> node = new GenericQueue.Node<>(99);

        assertNotNull(node, "Node constructor without code should create a non-null object");
        assertEquals(99, node.data, "Node data should be initialized correctly");
        assertEquals(0, node.code, "Node code should default to 0 if not provided");
        assertNull(node.next, "New Node's next reference should be null by default");
    }

    
    
    @BeforeEach
    public void setUp() { //runs before each individual method test
        queue = new GenericQueue<>(1); // fresh queue with firstNode = 1
        stringQueue = new GenericQueue<>("a"); // fresh queue with firstNode = a

    }
    
    @Test
    void testPrint() {
    	stringQueue.dequeue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.print();
    }
    
    @Test
    void testDumpList() {
        GenericQueue<String> queue = new GenericQueue<>("first");
        queue.add("second");
        queue.add("third");
        
        ArrayList<String> dumped = queue.dumpList(); 
        
        assertEquals(3, dumped.size());
        assertEquals("first", dumped.get(0));
        assertEquals("second", dumped.get(1));
        assertEquals("third", dumped.get(2));
    }
    
    @Test
    void testGet() {
        stringQueue.add("x");
        stringQueue.add("y");
        stringQueue.add("z");

        assertEquals("x", stringQueue.get(1), "get(1) should return 'x'");
        assertEquals("y", stringQueue.get(2), "get(2) should return 'y'");
        assertEquals("z", stringQueue.get(3), "get(3) should return 'z'");
    }

    @Test
    void testSet() {
        queue.add(20);
        queue.add(30);
        queue.set(1, 200);  // replace index 1

        assertEquals(200, queue.get(1), "set(1, 200) should replace element at index 1");
    }



    @Test
    public void testConstructor() {
    	 assertNotNull(queue, "Constructor should create a non-null queue");
         assertEquals(1, queue.getLength(), "Constructor should set length = 1");
         assertEquals(1, queue.getHead().data, "Constructor should initialize head with value 1");
    }

    @Test
    public void testAdd() {
        queue.add(2); //testing adding a node with value 2 to the back of the list
        assertEquals(2, queue.getLength(), "After add(), length should be 2");
        assertEquals(1, queue.getHead().data, "Head should remain 1 after add()");
        assertEquals(2, queue.getHead().next.data, "Second node should be 2 after add()");
    }

    @Test
    public void testAddWithCode() {
        queue.add(5, 21); //testing adding with data/code
        assertEquals(2, queue.getLength(), "After add(data, code), length should be 2");
        assertEquals(5, queue.getHead().next.data, "Second node data should be 5");
        assertEquals(21, queue.getHead().next.code, "Second node code should be 21");
    }
    
    
    @Test
    public void testEnqueue() {
        queue.enqueue(3); 
        assertEquals(2, queue.getLength(), "After enqueue, length should be 2");
        assertEquals(1, queue.getHead().data, "Head should remain 1 after enqueue");
        assertEquals(3, queue.getHead().next.data, "Second node should be 3 after enqueue");
    }
    
    @Test
    public void testEnqueueWithCode() {
        queue.enqueue(7, 144); 
        assertEquals(2, queue.getLength(), "After enqueue(data, code), length should be 2");
        assertEquals(7, queue.getHead().next.data, "Second node data should be 7");
        assertEquals(144, queue.getHead().next.code, "Second node code should be 144");
    }

    @Test
    public void testDelete() {
        queue.add(2); //add 2 to back of list
        Integer deleted = queue.delete(); //delete 2 from back of list
        assertEquals(2, deleted, "delete() should return the last element (2)");
        assertEquals(1, queue.getLength(), "After delete, length should return to 1");
    }
    
    @Test
    public void testDequeue() {
        queue.enqueue(4); 
        Integer val = queue.dequeue();
        assertEquals(4, val, "dequeue() should return the enqueued value (4)");
        assertEquals(1, queue.getLength(), "After dequeue, length should be 1");
        assertEquals(1, queue.getHead().data, "Head should still be 1 after dequeue");
    }
    
    @Test
    public void testIterator() {
        queue.enqueue(2); //adding elements 
        queue.enqueue(3);

        Iterator<Integer> it = queue.iterator(); //creates instance of iterator for queue
        assertTrue(it.hasNext(), "Iterator should have next at start");
        assertEquals(1, it.next(), "First value from iterator should be 1");
        assertEquals(2, it.next(), "Second value from iterator should be 2");
        assertEquals(3, it.next(), "Third value from iterator should be 3");
        assertFalse(it.hasNext(), "Iterator should have no more elements");
    }

    @Test
    public void testDescendingIterator() {
        queue.enqueue(2); //adding elements
        queue.enqueue(3);

        Iterator<Integer> it = queue.descendingIterator(); //creates instance
        assertTrue(it.hasNext(), "Descending iterator should have next at start");
        assertEquals(3, it.next(), "First descending value should be 3");
        assertEquals(2, it.next(), "Second descending value should be 2");
        assertEquals(1, it.next(), "Third descending value should be 1");
        assertFalse(it.hasNext(), "Descending iterator should have no more elements");
    }

    @Test
    public void testForEachLoop() {
        queue.enqueue(2); //adding elements
        queue.enqueue(3);

        int sum = 0;
        for (int val : queue) { //for-each loop uses iterator() automatically
            sum += val; 
        }
        assertEquals(6, sum, "for-each loop should sum values 1+2+3 = 6");
    }

    @Test
    public void testDeleteSingleElement() {
        Integer deleted = queue.delete();
        assertEquals(1, deleted, "delete() should return the only element (1)");
        assertEquals(0, queue.getLength(), "Length should be 0 after deleting the only element");
        assertNull(queue.getHead(), "Head should be null after deleting the only element");
    }
    
    
}
