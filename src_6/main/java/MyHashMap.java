import java.util.ArrayList;
import java.util.Iterator;

/*

 * 
 * 
 put("Ahmad", "1234")
     ↑         ↑
   key_1      val (T data)
     ↓         ↓
hashCode()   Node.data
     ↓         ↓
  Node.code  "1234"
 * 
 * 
 * 
 * 
 * */

public class MyHashMap<T> implements Iterable<T> {

	
	
	//////////////////////////////////////////
	//PRIVATE & GLOBAL INSTANCES & VARIABLES//
	//////////////////////////////////////////
	private ArrayList<GenericQueue<T>> map; 
	private int size; 
	
	
	
    ///////////////
    //CONSTRUCTOR//
    ///////////////
	public MyHashMap(String key, T value) {
		map = new ArrayList<>(10); 
		for(int i = 0; i < 10; i++) {
			map.add(null); 
		}
		put(key, value); 
	}
	
	
	
	/////////////
	/////PUT/////
	/////////////
	// This method will take a key value pair and do the following:
	// Create a hash code and hash value using the key passed into the method.
	public void put(String key, T value) {
		int hashCode = key.hashCode(); 
		int bucketIndex = Math.abs(hashCode) % 10; 

		GenericQueue<T> bucket = map.get(bucketIndex); 
		
		if(bucket == null) { 
			bucket = new GenericQueue<>(value); 
			bucket.getHead().code = hashCode; 
			map.set(bucketIndex, bucket); 
		} else { 
			bucket.add(value, hashCode); 
		}
		size++; 
	}
	
	
	
	/////////////
	///CONTAINS//
	/////////////
	// Check and see if the given key exists in the HashMap
	// and return true if yes and false if no 
	public boolean contains(String key){ 
		int hashCode = key.hashCode(); 
		int bucketIndex = Math.abs(hashCode) % 10; 
		GenericQueue<T> bucket = map.get(bucketIndex); 
	    if(bucket == null) { 
	        return false; 
	    }
		GenericQueue.Node<T> current = bucket.getHead(); 
		while(current != null) { 
			if(current.code == hashCode) { 
				return true; 
			}
	        current = current.next; 
		}
	    return false; 	
	}
	
	
	
	////////////
	/////GET////
	////////////
	//will return the value at the given key or return null if it does not exist
	public T get(String key){
		int hashCode = key.hashCode(); 
		int bucketIndex = Math.abs(hashCode) % 10; 
		GenericQueue<T> bucket = map.get(bucketIndex); 
		if(bucket == null) { 
			return null;
		}
		GenericQueue.Node<T> current = bucket.getHead(); 
		while(current != null) { 
			if(current.code == hashCode) { 
				return current.data; 
			}
	        current = current.next; 
		}
	    return null; 
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
	            T oldValue = current.data; // Save the current value before replacing it to return it
	            current.data = value; // Replace the old value with the new value in this node
	            return oldValue; // Return the old value that was replaced 
			}
			current = current.next;
		}
		return null;
	}
	
	
	
	////////////////
	///ITERATORS/////
	////////////////

	//head to tail
	public Iterator<T> iterator(){  
		return new HMIterator<>(map); 
	}
	
	
}
