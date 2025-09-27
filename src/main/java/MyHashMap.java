import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * you will use the java.util data structure ArrayList<T> in conjunction with 
 * the GenericQueue class you've already created to accomplish this
 * 
 * BIG O Notation is a mathematical way to describe efficiency of an algorithm
 * specifically how its performance scales as the input size grows
 * 
 * TIME COMPLEXITY: is the actual measure of how long an algorithm 
 * takes to run based on input size. 
 * 
 * BIG O is the notation we use to express that time complexity. 
 * 
 * BEST 
 * O(log n) , O(1)
 * O(n)
 * O(n log n)
 * O(n^2)
 * O(2^n)
 * O(n!) 
 * WORST
 * 
 * Key-Value Mapping is like an entry in a dictionary:
 * Key: the identifier (what I search with)
 * Value: the data (what I want to store/retrieve)
 * 
 * 
 put("Alice", "555-1234")
     ↑         ↑
   key_1      val (T data)
     ↓         ↓
hashCode()   Node.data
     ↓         ↓
  Node.code  "555-1234"
 * 
 * 
 * 
 * 
 * */

//////////////////////////////////////////////////////
/////////// MY HASH MAP CLASS ////////////////////////
//////////////////////////////////////////////////////

public class MyHashMap<T> implements Iterable<T> {

	
	
	//////////////////////////////////////////
	//PRIVATE & GLOBAL INSTANCES & VARIABLES//
	//////////////////////////////////////////
	private ArrayList<GenericQueue<T>> map; //An ArrayList of GenericQueues called map
	private int size; //Number of key-value mappings
	
	
	
    ///////////////
    //CONSTRUCTOR//
    ///////////////
	public MyHashMap(String key, T value) {
		map = new ArrayList<>(10); //Initialize ArrayList map to 10 buckets lined up
		for(int i = 0; i < 10; i++) {
			map.add(null); //Initialize with null 
		}
		put(key, value); //Add the first key/value pair using put
	}
	
	
	
	/////////////
	/////PUT/////
	/////////////
	// This method will take a key value pair and do the following:
	// Create a hash code and hash value using the key passed into the method.
	public void put(String key, T value) {
		int hashCode = key.hashCode(); //Generate hash code from key (will store in Node.code field)
		int bucketIndex = Math.abs(hashCode) % 10; //Handles negative with abs(), then %10 gives bucket index 0-9

		GenericQueue<T> bucket = map.get(bucketIndex); //Get the queue at this bucket index
		
		if(bucket == null) { // Check if the bucket is empty (no GenericQueue exists at this index yet)
			bucket = new GenericQueue<>(value); // Create a new GenericQueue with the value as the first node's data
			bucket.getHead().code = hashCode; //Store the hashCode in the Node.code field
			map.set(bucketIndex, bucket); // Store the new GenericQueue in the ArrayList at the calculated bucket index
		} else { // Bucket is not null, meaning a GenericQueue already exists at this index (collision case)
			bucket.add(value, hashCode); //collision detected, add a newNode to queue
		}
		size++; // Increment the total count of key-value pairs stored in the HashMap
	}
	
	
	
	/////////////
	///CONTAINS//
	/////////////
	// Check and see if the given key exists in the HashMap
	// and return true if yes and false if no 
	public boolean contains(String key){ 
		int hashCode = key.hashCode(); //Generate Hash Code from key for O(1) search time complexity
		int bucketIndex = Math.abs(hashCode) % 10; //Handles negative with abs(), then %10 gives bucket index 0-9
		GenericQueue<T> bucket = map.get(bucketIndex); //Get the queue at this bucket index
	    if(bucket == null) { // Check if bucket is empty (no GenericQueue exists at this index)
	        return false; // Key cannot exist if bucket is empty, so return false immediately
	    }
		GenericQueue.Node<T> current = bucket.getHead(); // Start searching from the first node in the linked list
		while(current != null) { // Traverse through all nodes in the linked list until we reach the end
			if(current.code == hashCode) { // Compare the node's stored hash code with the key's hash code
				return true; // Found a matching hash code, meaning the key exists in the HashMap
			}
	        current = current.next; // Move to the next node in the linked list to continue searching
		}
	    return false; // Finished searching entire linked list without finding the key, so it doesn't exist		
	}
	
	
	
	////////////
	/////GET////
	////////////
	//will return the value at the given key or return null if it does not exist
	public T get(String key){
		int hashCode = key.hashCode(); //Generate Hash Code from key for O(1) search time complexity
		int bucketIndex = Math.abs(hashCode) % 10; //Handles negative with abs(), then %10 gives bucket index 0-9
		GenericQueue<T> bucket = map.get(bucketIndex); //Get the queue at this bucket index
		if(bucket == null) { // Check if bucket is empty (no GenericQueue exists at this index)
			return null;
		}
		GenericQueue.Node<T> current = bucket.getHead(); // Start searching from the first node in the linked list
		while(current != null) { // Traverse through all nodes in the linked list until we reach the end
			if(current.code == hashCode) { // Compare the node's stored hash code with the key's hash code
				return current.data; // Found matching hash code. Return the value (T data) stored in this node
			}
	        current = current.next; // Move to the next node in the linked list to continue searching
		}
	    return null; // Searched entire linked list without finding the key, so return null (key not found)
	}

	
	
	////////////
	////SIZE////
	////////////
	//returns the number of key-value mappings in the map
	public int size() { 
		return size;
	}
	
	/////////////////
	/////IS_EMPTY////
	////////////////
	//returns true if this map contains no key-value mappings.
	public boolean isEmpty() { 
		return size == 0;
	}
	
	

	////////////////
	////REPLACE/////
	////////////////
	//replaces the entry for the specified key only if it is currently mapped to some value
	public T replace(String key, T value){	
		int hashCode = key.hashCode();
		int bucketIndex = Math.abs(hashCode) % 10; 
		GenericQueue<T> bucket = map.get(bucketIndex);
		if(bucket == null) {
			return null;
		}
		GenericQueue.Node<T> current = bucket.getHead();
		while(current != null) {
			if(current.code == hashCode) {
	            T oldValue = current.data; // Save the current value before replacing it (this is what we'll return)
	            current.data = value; // Replace the old value with the new value in this node
	            return oldValue; // Return the old value that was replaced 
			}
			current = current.next;
		}
		return null;
	}
	
	
	
	////////////////
	///ITERATOR/////
	////////////////
	/*
	 * 
	 * This iterator allows you to traverse through 
	 * all values in your HashMap regardless of 
	 * which bucket they're in. It enables for-each loops
	 * 
	 * 
	 * */
	@Override
	public Iterator<T> iterator(){  //REQUIRED by Iterable interface enables for each loops
		return new MyHashMapIterator(); 
	}
	
	
	
	///////////////////////////////////
	///ITERATOR PRIVATE INNER CLASS////
	///////////////////////////////////
	private class MyHashMapIterator implements Iterator<T>{ 
		
	    private int bucketIndex = 0; // Tracks which bucket (0-9) we're currently in inside the ArrayList
		private GenericQueue.Node<T> currentNode = null; // Points to the current node we're positioned at 
		
		public MyHashMapIterator() { // Constructor called when iterator is created
	        findNextNode(); // Immediately find the first node to start iteration
		}
		
	    // Helper method that finds the next node to iterate over
		private void findNextNode() {
	        // CASE 1: If we're in the middle of a linked list, move to next node in same bucket
			if(currentNode != null && currentNode.next != null) {
				currentNode = currentNode.next;
				return;
			}
	        // CASE 2: End of current bucket's linked list, search for next non-empty bucket
			for(int i = bucketIndex; i < map.size(); i++) {
				GenericQueue<T> bucket = map.get(i);
				if(bucket != null && bucket.getHead() != null) {
					bucketIndex = i + 1; // Set bucketIndex to next bucket for future searches
					currentNode = bucket.getHead(); // Position at the first node of this bucket
					return;
				}
			}
			
	        // CASE 3: No more buckets with data found
			currentNode = null;
		}
		
		
	    @Override
	    public boolean hasNext() { // Required by Iterator interface - checks if more elements exist
	        return currentNode != null; // True if we have a current node, false if we've reached the end
	    }
		
	    @Override
	    public T next() { // Required by Iterator interface - returns next element and advances iterator
	        
	        if(!hasNext()) { // Check if there are more elements
	            throw new NoSuchElementException(); // Standard Java behavior - throw exception if no more elements
	        }
	        
	        T data = currentNode.data; // Get the data from current node before moving
	        findNextNode(); // Advance to next node for future next() calls
	        return data; // Return the data we retrieved
	    }
	}

}
