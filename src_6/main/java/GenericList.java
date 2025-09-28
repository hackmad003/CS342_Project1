import java.util.Iterator; 
import java.util.ArrayList; 


public abstract class GenericList<T> implements Iterable<T> {
	

	private Node<T> head;
	private int length;

	protected static class Node<T>{
		
		T data;  
		int code; 
		Node<T> next;
	    
        Node(T data) {
            this.data = data;
            this.next = null;
        }	    
	    
		Node(T data, int code){
			this.data = data;
			this.code = code;
			this.next = null; 
		}
		
	}
	

	// Prints the items of the list, one value per line. 
	// If the list is empty, print “Empty List”.
	public void print(){
		if(length == 0){
			System.out.println("Empty List");
			return;
		}
		Node<T> current = head;
		while(current != null) {
			System.out.println(current.data);
			current = current.next;
		}
		
	}
	
	//Abstract Methods
	public abstract void add(T data);
	public abstract void add(T data, int code);
	public abstract T delete();
	
	//Stores all values currently in the list into a ArrayList and returns ArrayList
	public ArrayList<T>dumpList(){
		ArrayList<T> list = new ArrayList<>();
	    Node<T> current = head;
	    while (current != null) {
	        list.add(current.data);
	        current = current.next;
	    }
	    return list;
	}
	
	//Returns value at the index of the argument passed in  
	public T get(int index){
		//check for out of bounds index and return null if thats the case
		if(index < 0 || index >= length) {
			return null;
		}
		
		Node<T> current = head;
		int i = 0;
		
		//walk through the list until we reach the index
		while(i < index){ 
			current = current.next;
			i++;
		}
		
		//return the data at that index
		return current.data;
	}
	
	
	//Replace the element at index argument passed in 
	//with element argument argument passed in. 
	//finally return old element
	public T set(int index, T element){
		if(index < 0 || index >= length){
			return null;
		}
		
		Node<T> current = head;
		int i = 0;
		
		while(i < index){
			current = current.next;
			i++;
		}
		
		T oldElement = current.data;
		current.data = element; 
		
		return oldElement;
		
	}
	
    // Getters/Setters
    public Node<T> getHead() { return head; }
    public void setHead(Node<T> head) { this.head = head; }
    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }
	
    //head to tail
	public Iterator<T> iterator(){
		return new GLLIterator<>(head);
	}

	//tail to head
	public Iterator<T> descendingIterator(){
		return new ReverseGLLIterator<>(head);
	}
	
	
}
