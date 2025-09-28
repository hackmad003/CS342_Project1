import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * 
 * GLL - Generic Singly Linked List Iterator 
 * 
 * this class provides logic for iterating through
 * a generic linked list from head to tail. It implements 
 * Java Iterator interface to provide standard iteration 
 * functionality.
 * 
 * @param <E> the generic type of data stored in the linked list nodes
 * 
 * 
 * 
 * 
 * */


public class GLLIterator<E> implements Iterator<E>{
	
	
    /////////////////////////
    // INSTANCE VARIABLES ///
    /////////////////////////
    
	/*
	 * Reference to the current node in the iteration
	 * process.
	 * Points to the next node to be returned by next()
	 * when null iteration is complete
	 * */
    private GenericList.Node<E> currentNode;
    
    ///////////////////
    // CONSTRUCTOR  ///
    ///////////////////
	
    /*
     * Constructor that initializes the iterator with the head of a linked list.
     * 
     * @param head The first node of the linked list to iterate through.
     * 			   Can be null if the list is empty.
     * 
     * */
    public GLLIterator(GenericList.Node<E> head) {
    	this.currentNode = head;
    }
    
    //////////////////////////////////
    // ITERATOR INTERFACE METHODS  ///
    //////////////////////////////////
    
	/*
	 * Checks if there are more elements to iterate over.
	 * this method determines whether the iteration has more
	 * elements by checking if the current node reference is not 
	 * null.
	 * 
	 * @return true if there are more elements to iterate over,
	 * false otherwise.
	 * 
	 * */
    @Override
    public boolean hasNext() {
    	return currentNode != null; // True if we have a valid node to process next
    }
	
    /*
     * 
     * Returns the next element in the iteration and advances the iterator.
     * 
     * This method retrieves the data from the current node, advances the 
     *  iterator to the next node in the linked list, and returns the data.
     *  
     *  @return The data stored in the current node 
     *  @throws NoSuchElementException if there are no more elements to iterate over
     * 
     * */
    
    @Override
    public E next() {
    	//check if there are more elements before proceeding 
    	if(!hasNext()) {
    		throw new NoSuchElementException("No more elements in the linked list to iterate over");
    	}
    	
    	// Save the data from the current node before advancing 
    	E data = currentNode.data;
    	
    	// Advance to the next node in the linked list
    	// This will be null if we've reached the end of the list
    	currentNode = currentNode.next;
    	
    	// Return the data from the node we just processed
    	return data;
    }
    
    
    /*
     * The remove() and forEachRemaining() methods are optional 
     * in the Iterator interface and are not implemented as per requirements. 
     * Calling these methods will throw UnsupportedOperationException.
     * 
     * 
     * */
    
    
    
    
}
