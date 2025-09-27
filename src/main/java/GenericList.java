import java.util.Iterator; 
import java.util.ArrayList; 

/*
 * 
 * T DATA will contain the data being stored in the list 
 * CODE will be an optional field that may be used or not 
 * NEXT will be the reference to the next node in the list
 * 
 * The T is a placeholder for any data type
 * 
 * 
 * GenericList<T> Parent Class
 * |
 * | Extends
 * |
 * V
 * GenericQueue<T> Child Class
 *
 *
 * MyHashMap<T> Independent
 * 
 * 
 ***GenericList<T> PARENT provides basic linked list functionality**
 * - Manages Head Node
 * - Tracks Length
 * - Print()
 * - Abstract add()
 * - Abstract delete()
 * - DumpList()
 * - Get()
 * - Set()
 * - GetLength()
 * - SetLength()
 * - GetHead()
 * - SetHead()
 * - DescendingIterator()
 * 
 ***GenericQueue<T> CHILD specializes GenericList for unique queue behavior
 * - INHERITS FROM GenericList
 * - IMPLEMENTS ABSTRACT 
 * - add()
 * - delete()
 * 
 ***MyHashMap<T> INDEPENDENT hash table implementation using GenericQueue for collision management
 * - ArrayList of GenericQueue
 * - Size tracks total key-value mappings
 * 
 * */


//////////////////////////////////////////////////////
/////////// GENERIC LIST CLASS ///////////////////////
//////////////////////////////////////////////////////
public abstract class GenericList<T> implements Iterable<T> {
	
	///////////////////////////
	//// PRIVATE DATA FIELDS //
	///////////////////////////
	private Node<T> head;
	private int length;

	////////////////////////////////
	/////GENERIC NODE INNER CLASS //
	////////////////////////////////
	protected static class Node<T>{
		
		T data; //T DATA will contain the data being stored in the list 
		int code; //CODE will be an optional field that may be used or not
		Node<T> next; //NEXT will be the reference to the next node in the list

	    /////////////////
	    // CONSTRUCTOR //
	    /////////////////
		Node(T data, int code){
			this.data = data;
			this.code = code;
			this.next = null; 
		}
		
	}
	
	////////////////
	// ITERATOR ////
	////////////////
	@Override //OVERRIDING METHOD ANNOTATION
	public Iterator<T> iterator() { 
		return new GenericListIterator(); 
	}
	
	///////////////////////////////////
	///ITERATOR PRIVATE INNER CLASS////
	///////////////////////////////////
	private class GenericListIterator implements Iterator<T>{ 
		private Node<T> current = head;
		
        @Override
        public boolean hasNext() { //REQUIRED Method
            return current != null;
        }
		
		@Override 
		public T next() { //REQUIRED Method
			T data = current.data;
			current = current.next;
			return data;
		}
	}

	
	////////////
	///PRINT////
	////////////
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
	
	////////////////////
	//ABSTRACT METHODS//
	////////////////////
	public abstract void add(T data, int code);
	public abstract T delete();
	
	
	/////////////
	///DUMPLIST//
	/////////////
	// This method stores and returns all values currently in
	// the list into an ArrayList and returns it. 
	public ArrayList<T>dumpList(){
		ArrayList<T> list = new ArrayList<>();
	    Node<T> current = head;
	    while (current != null) {
	        list.add(current.data);
	        current = current.next;
	    }
	    return list;
	}
	
	
	/////////
	///GET///
	/////////
	// returns the value at the specified index or 
	//null if the index is out of bounds.
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
	
	
	
	////////////
	////SET/////
	////////////
	// replace the element at specified position in the list
	// with the specified element and return the element
	// previously at the specified position.
	// Return null if index is out of bounds
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
		
		T oldValue = current.data;
		current.data = element; 
		
		return oldValue;
		
	}
	
	////////////////////////
	/// GETTERS & SETTERS //
	////////////////////////
	public int getLength(){
		return length;
	}
	
	public void setLength(int length){
		this.length = length;
	}
	
	public Node<T> getHead(){
		return head;
	}
	
	public void setHead(Node<T> head){
		this.head = head;
	}
	
	///////////////////////////
	///DESCENDING ITERATOR/////
	///////////////////////////
	// returns an iterator over the elements of the
	// list in reverse order( tail to head)
	public Iterator<T> descendingIterator(){
		ArrayList<T> list = dumpList();
		
		return new Iterator<T>() {
			private int index = list.size() - 1;
			
			@Override
			public boolean hasNext(){ //REQURIED
				return index >= 0;
			}
			
			@Override
			public T next() { //REQURIED
				return list.get(index--);
			}
			
		};
	}
	
	
}
