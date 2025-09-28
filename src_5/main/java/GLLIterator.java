import java.util.Iterator;
import java.util.NoSuchElementException;


public class GLLIterator<E> implements Iterator<E>{
	
	
    private GenericList.Node<E> currentNode;
    
    public GLLIterator(GenericList.Node<E> head) {
    	this.currentNode = head;
    }
    
    @Override
    public boolean hasNext() {
    	return currentNode != null; 
    }

    
    @Override
    public E next() {
    	if(!hasNext()) {
    		throw new NoSuchElementException("No more elements in the linked list to iterate over");
    	}
    	
    	E data = currentNode.data;
    	

    	currentNode = currentNode.next;
    	
    	return data;
    }
    
    
}
