import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

/*
 * 
 * ReverseGLLIterator - Reverse Generic Linked List Iterator
 * 
 * This class provides logic for iterating through a generic linked list
 * from tail to head (in reverse order). It implements the Java Iterator
 * interface to provide standard iteration functionality in reverse
 * 
 * Note: since singly linked lists dont have backwards pointers, this 
 * implementation collects all nodes first, then iterates in reverse.
 * 
 * @param <T> The generic type of data stored in the linked list nodes
 * 
 * */



public class ReverseGLLIterator<T> implements Iterator<T> {
	
    /////////////////////////
    // INSTANCE VARIABLES ///
    /////////////////////////
    
	/*
	 * List containing all the data elements from the linked list in reverse order. 
	 * This allows us to iterate backwards through a singly-linked list 
	 * 
	 * 
	 * */
    private List<T> reverseDataList;
    
    /*
     * Index tracking the current position in the reverse iteration
     * Points to the next element to be returned by next()
     * 
     * */
	private int currentIndex;
	
	
    ///////////////////
    // CONSTRUCTOR ///
    ///////////////////
    
    
	/*
	 * Constructor that initializes the reverse iterator with the head of a linked list
	 * 
	 * This constructor traverses the entire linked list once to collect all data
	 * elements, then reverse the order to enable reverse iteration.
	 * 
	 * @param head The first node of the linked list to iterate through in reverse.
	 * 				Can be null if the list is empty
	 * 
	 * */
	
	public ReverseGLLIterator(GenericList.Node<T> head) {
		this.reverseDataList = new ArrayList<>(); //Initializes the list to store data
		this.currentIndex = 0; // Start at the beginning of our reverse list
		
		// Collect all data from the linked list (head to tail)
		collectDataFromLinkedList(head);
		
		// Reverse the collected data to enable tail-to-head iteration
		reverseDataOrder();
	}
	
	
    /////////////////////
    // HELPER METHODS ///
    /////////////////////
    
	/*
	 * Traverse the linked list from head to tail and collects all data elements
	 * 
	 * This method walks through the entire linked list once, adding each node's
	 * data to our internal list in the order encountered (head to tail)
	 * 
	 * */

	private void collectDataFromLinkedList(GenericList.Node<T> head) {
		GenericList.Node<T> current = head; // Start at the head
		
		//Traverse the entire linked list
		while(current != null) {
			reverseDataList.add(current.data); // Add this node's data to our collection
			current = current.next; // Move to the next node
		}		
	}
	
	/*
	 * Reverses the order of collected data elements. 
	 * 
	 * This method reverses our collected data list so that when we iterate 
	 * through it normally (index 0 to end), we're actually going from 
	 * tail to head of the original linked list
	 * 
	 * 
	 * */
	private void reverseDataOrder() {
		List<T> originalOrder = new ArrayList<>(reverseDataList); // Copy original order
		reverseDataList.clear(); //clear the list
		
		//Add elements back in reverse order (from end to beginning)
		for(int i = originalOrder.size() - 1; i >= 0; i--) {
			reverseDataList.add(originalOrder.get(i));
		}
	}
	
    //////////////////////////////////
    // ITERATOR INTERFACE METHODS ///
    //////////////////////////////////
    
    /*
     * Checks if there are more elements to iterate over in reverse.
     * 
     * This method determines whether the reverse iteration has more elements
     * by checking if the current index is within the bounds of our reverse data list.
     * 
     * @return true if there are more elements to iterate over, false otherwise
     * 
     * 
     * */
	
	@Override
	public boolean hasNext() {
		return currentIndex < reverseDataList.size(); //True if we haven't reached the end
	}
	
	/*
	 * Returns the next element in the reverse iteration and advances the iterator
	 * 
	 *  This method retrieves the data at the current index from our reverse list, 
	 *  advances the index, and returns the data. This effectively iterates through
	 *  the original linked list from tail to head.
	 * 
	 * @return The data from the next node in reverse order
	 * @throws NoSuchElementException if there are no more elements to iterate over
	 *
	 */
	@Override
	public E next() {
		// Check if there are more elements before proceeding 
		if(!hasNext()) {
			throw new NoSuchElementException("No more elements in reverse iteration");
		}
		
		//Get the data at the current index (this is the "next" element in reverse order)
		E data = reverseDataList.get(currentIndex);
		
		//Advance to the next index for future calls
		currentIndex++;
		
		//Return the data we just retrieved
		return data;	
	}
	
	/*
	 * The remove() and forEachRemaining methods are optional 
	 * Calling these methods will throw UnsupportedOperationException
	 * 
	 * */
	
	
	
	
	
	
}
