import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class HMTest {

    @Test
    public void testPutAndGet() {
        MyHashMap<String> map = new MyHashMap<>("key1", "a");
        assertEquals("a", map.get("key1"));

        map.put("key2", "b");
        map.put("key3", "c");

        assertEquals("b", map.get("key2"));
        assertEquals("c", map.get("key3"));

        assertNull(map.get("not_in_map"));
    }

    @Test
    public void testContains() {
        MyHashMap<Integer> map = new MyHashMap<>("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);

        assertTrue(map.contains("key1"));
        assertTrue(map.contains("key2"));
        assertTrue(map.contains("key3"));
        assertFalse(map.contains("key4"));
    }

    @Test
    public void testSizeAndIsEmpty() {
        MyHashMap<String> map = new MyHashMap<>("key1", "a");
        assertFalse(map.isEmpty());
        assertEquals(1, map.size());

        map.put("key2", "b");
        map.put("key3", "c");
        assertEquals(3, map.size());

        MyHashMap<String> emptyMap = new MyHashMap<>("key1", "a");
        emptyMap.replace("key1", null);

        assertFalse(emptyMap.isEmpty());
    }

    @Test
    public void testReplace() {
        MyHashMap<String> map = new MyHashMap<>("key1", "a");
        map.put("key2", "b");

        String oldVal = map.replace("key1", "c");
        assertEquals("a", oldVal);
        assertEquals("c", map.get("key1"));

        assertNull(map.replace("key4", "anything"));
    }

    @Test
    public void testPutAllowsDuplicates() {
        MyHashMap<String> map = new MyHashMap<>("key1", "a");
        map.put("key1", "b");

      
        assertEquals("a", map.get("key1"));

      
        assertEquals(2, map.size());

        assertTrue(map.contains("key1"));
    }

    @Test
    public void testIterator() {
        MyHashMap<String> map = new MyHashMap<>("key1", "a");
        map.put("key2", "b");
        map.put("key3", "c");

        Iterator<String> iter = map.iterator();

        int count = 0;
        boolean foundA = false, foundB = false, foundC = false;

        while (iter.hasNext()) {
            String val = iter.next();
            count++;
            if (val.equals("a")) foundA = true;
            if (val.equals("b")) foundB = true;
            if (val.equals("c")) foundC = true;
        }

        assertEquals(3, count);
        assertTrue(foundA && foundB && foundC);
    }

    @Test
    public void testCollisionHandling() {
        
        MyHashMap<String> map = new MyHashMap<>("key1", "a");
        map.put("key2", "b");

        assertEquals("a", map.get("key1"));
        assertEquals("b", map.get("key2"));
        assertTrue(map.contains("key1"));
        assertTrue(map.contains("key2"));

        assertEquals(2, map.size());
    }
}
