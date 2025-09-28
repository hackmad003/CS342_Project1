import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;

/*
 * 
 * HMIterator - HashMap Iterator
 * 
 * This class provides logic for iterating through a MyHashMap data structure.
 * It implements the Java Iterator interface to traverse all values stored 
 * in the hash map, regardless of which bucket they're stored in. 
 * 
 * The iterator traverses buckets sequentially (0-9) and within each bucket,
 * it traverses the linked list from head to tail.
 * 
 * @param <V> the generic type of values stored in the HashMap 
 * why choose V ? it doesn't matter which letter I choose correct?
 * */



public class HMIterator<V> implements Iterator<V> {
	
    /////////////////////////
    // INSTANCE VARIABLES ///
    /////////////////////////
    
	/*
	 * Reference to the ArrayList of GenericQueues that forms the hash map structure.
	 * Each index represents a bucket that may contain a GenericQueure of collided values.
	 * 
	 * */
	private ArrayList<GenericQueue<V>> hashMapBuckets;
	
	/*
	 * Index of the current bucket being examined (0-9 for a 10-bucket hash map).
	 * Each index represents a bucket that may contain a GenericQueue of collided values.
	 * What do you mean collided values? I understand each bucket may contain a GQ but whats collided values?
	 * 
	 * */
	private int currentBucketIndex;
	
	/*
	 * Reference to the current node within the current bucket's linked list. 
	 * Points to the next node whose data will be returned by next().
	 * When null, we need to find the next non-empty bucket.
	 * 
	 * */
	private GenericQueue.Node<V> currentNode;
	
	
    ///////////////////
    // CONSTRUCTOR ///
    ///////////////////
    
	/*
	 * Constructor that initializes the iterator with a reference to the hash map's bucket array.
	 * 
	 * @param buckets The ArrayList of GenericQueues representing the hash maps' internal structure.
	 * 					Each element can be null (empty buckets) or a GenericQueue (bucket with data)
	 * 
	 * 
	 * */
	public HMIterator(ArrayList<GenericQueue<V>> buckets) {
		this.hashMapBuckets = buckets; // Store reference to the hash map's internal structure
		this.currentBucketIndex = 0; // Start searching from the first bucket (index 0)
		this.currentNode = null; // Will be set when we find the first non-empty bucket
		
		// Find the first non-empty bucket and position ourselves at its first node
		findNextValidNode();
	}
	
	
    /////////////////////
    // HELPER METHODS ///
    /////////////////////
	
	/*
	 * 
	 * Finds the next valid node to iterate over across all buckets.
	 * 
	 * This method handles the complex logic of moving between buckets and nodes;
	 * 1. If we're in the middle of a linked list, move to the next node
	 * 2. If we've finished a linked list, find the next non-empty bucket
	 * 3. If no more buckets have data, set currentNode to null (iteration complete)
	 * 
	 * */
	
	private void findNextValidNode() {
		//CASE 1: If we're currently in a linked list and there's a next node, use it
		if(currentNode != null && currentNode.next != null) {
			currentNode = currentNode.next; // Move to next node in current bucket's list
			return; //Found our next node, we're done
		}
		
		//CAdSE 2: Current bucket's list is exhausted, search for next non-empty bucket
		// Start from currentBucketIndex and look for a bucket with data
		for(int bucketIndex = currentBucketIndex; bucketIndex < hashMapBuckets.size(); bucketIndex++) {
			GenericQueue<V> bucket = hashMapBuckets.get(bucketIndex); //Get bucket at this index
			
			//Check if this bucket exists and has at least one node
			if(bucket != null && bucket.getHead() != null) {
				//Found a non-empty bucket!
				currentBucketIndex = bucketIndex + 1; //Set next search to start from next bucket
				currentNode = bucket.getHead(); // Position at first node of this bucket
				return; // Found our next node, we're done
			}
		}
		
		//CASE 3: No more buckets with data found
		currentNode = null; // Signal that iteration is complete 	
	}
	
	
	
	
	
	
	
	
	//////////////////////////////////
	// ITERATOR INTERFACE METHODS ///
	//////////////////////////////////
	
	/**
	* Checks if there are more elements to iterate over in the hash map.
	* 
	* This method determines whether the iteration has more elements by
	* checking if we have a valid current node to process.
	* 
	* @return true if there are more elements to iterate over, false otherwise
	*/
	@Override
	public boolean hasNext() {
	return currentNode != null; // True if we have a valid node positioned for next()
	}
	
	/**
	* Returns the next element in the hash map iteration and advances the iterator.
	* 
	* This method retrieves the data from the current node, advances the iterator
	* to the next valid node (possibly in a different bucket), and returns the data.
	* 
	* The order of iteration depends on:
	* 1. Hash function (which bucket keys map to)
	* 2. Insertion order within each bucket's linked list
	* 3. Bucket traversal order (0 through 9)
	* 
	* @return The data stored in the current node
	* @throws NoSuchElementException if there are no more elements to iterate over
	*/
	@Override
	public V next() {
	// Check if there are more elements before proceeding
	if (!hasNext()) {
	throw new NoSuchElementException("No more elements in the hash map to iterate over");
	}
	
	// Save the data from the current node before advancing
	V data = currentNode.data;
	
	// Find the next valid node for future calls to next()
	// This handles moving within linked lists and between buckets
	findNextValidNode();
	
	// Return the data from the node we just processed
	return data;
	}
	
	/**
	* The remove() and forEachRemaining() methods are optional
	* in the Iterator interface and are not implemented as per requirements.
	* Calling these methods will throw UnsupportedOperationException.
	*/
	
	
	
	
	
	
	
	
}
