import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;




public class ReverseGLLIterator<E> implements Iterator<E> {
	
    private ArrayList<E> reverseDataList;
    
	private int currentIndex;
	   

	public ReverseGLLIterator(GenericList.Node<E> head) {
		this.reverseDataList = new ArrayList<>(); 
		this.currentIndex = 0; 
		
		collectDataFromLinkedList(head);
		
		reverseDataOrder();
	}
	
	
	private void collectDataFromLinkedList(GenericList.Node<E> head) {
		GenericList.Node<E> current = head; 
		
		while(current != null) {
			reverseDataList.add(current.data); 
			current = current.next; 
		}		
	}
	

	private void reverseDataOrder() {
		ArrayList<E> originalOrder = new ArrayList<>(reverseDataList); 
		reverseDataList.clear(); 
		
		for(int i = originalOrder.size() - 1; i >= 0; i--) {
			reverseDataList.add(originalOrder.get(i));
		}
	}
	

	@Override
	public boolean hasNext() {
		return currentIndex < reverseDataList.size(); 
	}
	

	@Override
	public E next() {
		if(!hasNext()) {
			throw new NoSuchElementException("No more elements in reverse iteration");
		}
		
		E data = reverseDataList.get(currentIndex);
		
		currentIndex++;
		
		return data;	
	}
	
}
