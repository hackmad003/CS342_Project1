/*
 test cases needed from GQ and HM classes
 as well as all three iterators.
 tests are split between two files
 minimum one test per method
 test forEach loop and constructors for GQ and HM
 test descendingIterator
 
 /////////////////
 /// IMPORTANT ///
 /////////////////
 Document import org.junit.jupiter.api. all import Annotations 
 
 
 */



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


///////////////////////////////////////////////////////
/////////// GENERIC QUEUE CLASS ///////////////////////
///////////////////////////////////////////////////////
public class HMTest {
	
	//Java Generics
	//One HashMap Instace = One Data Type
	//When you creat a HashMap instance, the generic type is locked in at creation time.
	
	
	private MyHashMap<String> stringHashMap;
	private MyHashMap<Integer> intHashMap;
	
	@BeforeEach
	void setUp() {
		// Initialize HashMaps with fresh list of test values before each test
		stringHashMap = new MyHashMap<>("testKey", "testVal");
		intHashMap = new MyHashMap<>("testKey", 101);
	}
	
	////////////////////////
    // CONSTRUCTOR TESTS ///
    ////////////////////////
	
	@Test
	void testHMConstructorString() {
		MyHashMap<String> map = new MyHashMap<>("Ahmad","651397101");
		
		assertEquals(1, map.size(), "Constructor should create map with size = 1");
		assertFalse(map.isEmpty(), "Constructor create a non-empty map");
		assertTrue(map.contains("Ahmad"), "Constructor should add the initial key");
		assertEquals("651397101", map.get("Ahmad"), "Constructor should store the inital value");
	}
	
	@Test
	void testHMConstructorInteger() {
		MyHashMap<Integer> map = new MyHashMap<>("Ahmad", 651397101);
		assertEquals(1, map.size());
		assertEquals(Integer.valueOf(95), map.get("Ahmad"));
		
		MyHashMap<Double> doubleMap = new MyHashMap<>("PI", 3.14159);
		assertEquals(Double.valueOf(3.14159), doubleMap.get("PI"));
	}
	
    ///////////////////////
    // PUT METHOD TESTS  //
    ///////////////////////
	
	@Test
	void testPutMethod() {
		MyHashMap<String> map = new MyHashMap<>("firstKey", "val_1");
		
		map.put("secondKey","val_2");
		map.put("thirdKey","val_3");
		
		assertEquals(3, map.size(), "Put should increase size");
		assertTrue(map.contains("secondKey"), "Put should add new keys");
		assertTrue(map.contains("thirdKey"), "Put should add new keys");
		assertEquals("val_2", map.get("secondKey"), "Put should store correct values");
		assertEquals("val_3", map.get("thirdKey"), "Put should store associated values together");
	}
	
	@Test
	void testPutCollision() {
		MyHashMap<String> map = new MyHashMap<>("key1","val_1");
		
		for(int i = 2; i <= 10; i++) {
			map.put("key" + i, "val_" + i);
		}
		
		assertEquals(10, map.size(), "Put should handle multiple items");
		
		for(int i = 1; i <= 10; i++) {
			assertTrue(map.contains("key" + i), "All keys should be accessible after collisions");
			assertEquals("val_" + i, map.get("key_" + i), "All values should be correct after collisions");
		}	
	}
	
    ///////////////////////
    // GET METHOD TESTS ///
    ///////////////////////
	
	@Test
	void testGetMethod() {
		MyHashMap<String> map = new MyHashMap<>("Ahmad", "651397101");
		map.put("Michael", "911");
		map.put("Patrick", "101");
		
		assertEquals("651397101", map.get("Ahmad"), "Get should return associated value for Ahmad");
		assertEquals("911", map.get("Michael"), "Get should return associated value for Michael");
		assertEquals("101", map.get("Patrick"), "Get should return associated value for Patrick");
		
		assertNull(map.get("Bob"), "Get should return null for non-existent key");
		assertNull(map.get(""), "Get should return null for empty string");	
	}
	
	
    ///////////////////////////
    // CONTAINS METHOD TESTS //
    ///////////////////////////
	
	@Test
	void testContainsMethod() {
		MyHashMap<String> map = new MyHashMap<>("Micro", "Soft");
		map.put("Linked","In");
		map.put("Annoying","Orange");
		
		assertTrue(map.contains("Micro"), "Contains should return true for existing key");
		assertTrue(map.contains("Linked"), "Contains should return true for existing key");
		assertTrue(map.contains("Annoying"), "Contains should return true for existing key");
		
		assertFalse(map.contains("Red"), "Contains should return false for non-exisitent key");
		assertFalse(map.contains("Blue"), "Contains should return false for non-exisitent key");
		assertFalse(map.contains("Green"), "Contains should return false for non-exisitent key");
	}
	
	
    ////////////////////////
    // SIZE METHOD TESTS ///
    ////////////////////////
	
	@Test
	void testSizeMethod() {
		assertEquals(1, stringHashMap.size(), "Initial size should be 1");
		
		stringHashMap.put("key_2", "val_2");
		assertEquals(2, stringHashMap.size(), "Size should be 2 after adding one element");
		
		stringHashMap.put("key_3","val_3");
		stringHashMap.put("key_4", "val_4");
		assertEquals(4, stringHashMap.size(), "Size should be 4 after adding 3 elements");
	}
	
	
    //////////////////////////
    // ISEMPTY METHOD TESTS //
    //////////////////////////
    
	@Test
	void testIsEmptyMethod() {
		
		assertFalse(stringHashMap.isEmpty(), "Map with initial element should not be empty");
		stringHashMap.put("key_2", "val_2");
		assertFalse(stringHashMap.isEmpty(), "Map with multiple elements should not be empty");
	}
	
	
    //////////////////////////////////
    // REPLACE METHOD TESTS
    //////////////////////////////////
    @Test
    void testReplaceMethod() {
    	MyHashMap<String> map = new MyHashMap<>("Ahmad", "101");
    	map.put("Pat", "911");
    	
    	String oldVal = map.replace("Ahmad", "651397101");
    	assertEquals("101", oldVal, "Replace should return old value");
    	assertEquals("651397101", map.get("Ahmad"), "Replace should update the value");
    	assertEquals(2, map.size(), "Replace should not change size");
    	
    	String oldPatVal = map.replace("Pat", "6789");
    	assertEquals("911", oldPatVal, "Replace should return old value");
    	assertEquals("6789", map.get("Pat"), "Replace should update Pat's value");
    	
    	String nonExistResult = map.replace("Charlie", "1234");
    	assertNull(nonExistResult, "Replace should return null for non existent key");
    	assertFalse(map.contains("Charile"), "Replace should not add new keys");
    	assertEquals(2, map.size(), "Replace should not change size for no existent key");
    	
    }
	
	
    ///////////////////////////
    // ITERATOR METHOD TESTS //
    ///////////////////////////
    
    @Test
    void testIteratorMethod() {
    	MyHashMap<String> map = new MyHashMap<>("1st", "val_1");
    	map.put("2nd", "val_2");
    	map.put("3rd", "val_3");
    	
    	Iterator<String> iterator = map.iterator();
    	assertNotNull(iterator, "Iterator should not be null");
    	
    	assertTrue(iterator.hasNext(), "Iterator should have next elements");
    	
    	List<String> values = new ArrayList<>(); //why a List
    	while(iterator.hasNext()) {
    		values.add(iterator.next());
    	}
    	
    	assertEquals(3, values.size(), "Iterator should return all 3 values");
    	assertTrue(values.contains("val_1"), "Iterator should include val_1");
    	assertTrue(values.contains("val_2"), "Iterator should include val_2");
    	assertTrue(values.contains("val_3"), "Iterator should include val_3");
    	
    	assertFalse(iterator.hasNext(), "Iterator should be exhausted after traversal");
    }
    
    @Test
    void testIteratorNextThrowsException() {
    	MyHashMap<String> map = new MyHashMap<>("1st", "val");
    	Iterator<String> iterator = map.iterator();
    	
    	assertTrue(iterator.hasNext());
    	assertEquals("val", iterator.next());
    	
    	assertFalse(iterator.hasNext());
    	
    	//can you explain this line v v v v
    	assertThrows(NoSuchElementException.class, () -> { iterator.next(); }, "Iterator.next() should throw NoSuchElementException when no more elements");
    }
	
    
    //////////////////////////
    // FOR-EACH LOOP TESTS ///
    //////////////////////////
	
    @Test
    void testForEachLoop() {
    	MyHashMap<String> map = new MyHashMap<>("Ahmad", "Awaidah");
    	map.put("John", "Cena");
    	map.put("John", "Doe");
    	
    	List<String> collectedVals = new ArrayList<>();
    	
    	for(String val : map) {
    		collectedVals.add(val);
    	}
    	
    	assertEquals(3, collectedVals.size(), "ForEach should collect all three vals");
    	assertTrue(collectedVals.contains("Awaidah"), "ForEach should include Awaidah");
    	assertTrue(collectedVals.contains("Cena"), "ForEach should include Cena");
    	
    	int totalLength = 0;
    	for(String val : map) {
    		totalLength += val.length();
    	}
    	
    	assertTrue(totalLength > 0, "ForEach should allow operations on values");
    }
	
	@Test
	void testForEachLoopWithEmptyBuckets() {
		MyHashMap<Integer> map = new MyHashMap<>("BrainRot", 67);

		List<Integer> vals = new ArrayList<>();
		for(Integer val : map) {
			vals.add(val);
		}
		
		assertEquals(1, vals.size(), "ForEach should work with sparse HashMap");
		assertEquals(Integer.valueOf(67), vals.get(0), "ForEach should return associated value");
	}
	
	
    //////////////////////
    // EDGE CASE TESTS ///
    //////////////////////
    
	@Test
	void testNullKey() {
		assertThrows(NullPointerException.class, () -> { new MyHashMap<>(null, "val"); }, "Constructor should handle null key");
	}
	
	
	// key used to generate the hashCode
	// val becomes the data (the T value you want to store)
	@Test
	void testNullValue() {
		
		MyHashMap<String> map = new MyHashMap<>("key_1", null);
		
		assertTrue(map.contains("key_1"), "Should contain key will null value");
		assertNull(map.get("key_1"), "Should return null for null value");
		assertEquals(1, map.size(), "Size should be 1 even with null value");
		
		map.put("key_2", null);
		assertEquals(2, map.size(), "Should handle multiple null values");
		assertNull(map.get("key_2"), "Should retrieve null value correctly");
	}
	
	@Test
	void testHashCollisions() {
		MyHashMap<String> map = new MyHashMap<>("key_1", "val_1");
		
		for(int i = 1; i <= 20; i++) {
			map.put("key_" + i, "val_" + i);
		}
		
		assertEquals(21, map.size(), "all elements should be stored despite potential collisions");
		
		assertEquals("val_1", map.get("key_1"), "Original element should be accessible");
		for(int i = 1; i <= 20; i++) {
			assertTrue(map.contains("key_" + i), "All keys should be accessible after many insertions");
			assertEquals("value" + i, map.get("key_" + i), "All values should be correct after many insertions");
		}
	}
	
	
    ////////////////////////////////////
    // HASHMAP IMPLEMENTATION TESTS ////
    ////////////////////////////////////
    
	@Test
	void testMyHashMapDataStructure() {
		
		MyHashMap<Double> studentGrades = new MyHashMap<>("Ahmad Awaidah (aawai)", 99.99);
		
		studentGrades.put("Pat Gonger (gongerP)", 88.88);
		studentGrades.put("Michael Swims (swimsM)", 77.77);
		studentGrades.put("Danny Lab (labD)", 66.66);
		
		assertEquals(4, studentGrades.size());
		assertFalse(studentGrades.isEmpty());
		
		Double oldGrade = studentGrades.replace("Michael Swims (swimsM)", 55.55);
		assertEquals(77.77, oldGrade);
		assertEquals(55.55, studentGrades.get("Michael Swims (swimsM)"));
		
		List<Double> allStudentGrades = new ArrayList<>();
		for(Double grade : studentGrades) {
			allStudentGrades.add(grade);
		}
		
		assertEquals(4, allStudentGrades.size());
		assertTrue(allStudentGrades.contains(99.99));  //Ahmad
		assertTrue(allStudentGrades.contains(88.88));  //Pat
		assertTrue(allStudentGrades.contains(55.55));  //Michael
		assertTrue(allStudentGrades.contains(66.66));  //Danny
	}
}

//EOF
