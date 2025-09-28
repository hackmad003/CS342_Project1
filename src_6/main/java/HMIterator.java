import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;


public class HMIterator<V> implements Iterator<V> {
	

	private ArrayList<GenericQueue<V>> hashMapBuckets;
	private int currentBucketIndex;
	private GenericQueue.Node<V> currentNode;
	

	public HMIterator(ArrayList<GenericQueue<V>> buckets) {
		this.hashMapBuckets = buckets;
		this.currentBucketIndex = 0; 
		this.currentNode = null; 
		
		findNextValidNode();
	}
	
	
	
	private void findNextValidNode() {
		//CASE 1: If we're currently in a linked list and there's a next node, use it
		if(currentNode != null && currentNode.next != null) {
			currentNode = currentNode.next;
			return; 
		}
		
		//CASE 2: Current bucket's list is exhausted, search for next non-empty bucket
		for(int bucketIndex = currentBucketIndex; bucketIndex < hashMapBuckets.size(); bucketIndex++) {
			GenericQueue<V> bucket = hashMapBuckets.get(bucketIndex);
			
			if(bucket != null && bucket.getHead() != null) {
				currentBucketIndex = bucketIndex + 1;
				currentNode = bucket.getHead(); 
				return; 
			}
		}
		
		//CASE 3: No more buckets with data found
		currentNode = null; 	
	}
	
	

	@Override
	public boolean hasNext() {
		return currentNode != null; 
	}
	

	@Override
	public V next() {
		if (!hasNext()) {
			throw new NoSuchElementException("No more elements in the hash map to iterate over");
		}
		
		V data = currentNode.data;
		
		findNextValidNode();
		
		return data;
	}
	


	
	
}
