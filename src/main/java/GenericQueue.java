/*
 * 
 * 
 * 
 * */
///////////////////////////////////////////////////////
/////////// GENERIC QUEUE CLASS ///////////////////////
///////////////////////////////////////////////////////
public class GenericQueue<T> extends GenericList<T> {

    // This is a traditional reference to the tail of the list.
    private Node<T> tail;

    
    ////////////////
    // CONSTRUCTOR//
    ////////////////
    /*
     * The constructors for this class will take one parameter. That parameter will be a value
	 * that will go in the first node of the list encapsulated by each instance of the class. Each
	 * constructor should initialize the linked list head, with the value passed in by the
	 * constructor and set the head and tail data members
     * 
     * */
    public GenericQueue(T value) { // Constructor that creates a queue with one initial node containing the provided value
        Node<T> node = new Node<>(value, 0); // Create a new Node object with the provided data and default code of 0
        //Default hash code (0) - will be overwritten by HashMap when needed
        //The actual data to store in this node (String, Integer, etc.)
        setHead(node); // Set the head pointer to point to our new node (makes it the first node in the linked list)
        tail = node; // Set the tail pointer to also point to our new node (since there's only one node, it's both first and last)
        setLength(1); // Set the length counter to 1 since we now have exactly one node in the queue
    }

    
    ////////////////
    // ADD(T DATA)//
    ////////////////
    // The method add(T data), GenericQueue will add to the back of the list
    public void add(T data) { 
        add(data, 0);
    }

    
    //////////////////////////
    // ADD(T DATA, INT CODE)//
    //////////////////////////
    // It will do the same thing as add(T data) but also set the code data member
    // to the value passed in
    @Override
    public void add(T data, int code){
        Node<T> newNode = new Node<>(data, code); //Create a new Node with data & code
        if (getHead() == null) { //if list is empty set both head/tail to new Node
            setHead(newNode);
            tail = newNode;
        } else { //else link current tail to the newNode
            tail.next = newNode;
            tail = newNode; //update tail to point to newNode
        }
        setLength(getLength() + 1); //Increment length of list
    }
    

    ///////////////
    /// DELETE ////
    ///////////////
    //It will return the value of and delete the last node in the list.
    @Override
    public T delete() {
    	//CASE 1: EMPTY LIST
    	if(getHead() == null) return null; //if list is empty return null
    	//CASE 2: SINGLE NODE
    	if(getHead().next == null){ //if there is only one node 
    		T value = getHead().data; //save data from one node
    		setHead(null); //clear list by setting head/tail to null
    		tail = null;
    		setLength(0); //reset length to 0
    		return value; //return the value deleted
    	}
    	//CASE 3: MULTIPE NODES
    	Node<T> current = getHead(); //Else start from head to find 2nd to last node 
    	while(current.next.next != null) { //traverse until current.next.next is null
    		//meaning current is the second to last node
    		current = current.next;
    	}
    	T value = current.next.data; //save the data from the last node
    	current.next = null; //remove last node by setting it to null
    	tail = current; //update tail to point to new last node
    	setLength(getLength() -1); //decrease length of the list 
    	return value; //return the value of the deleted node	
    }

    ////////////////////////
    /// ENQUEUE(T DATA) ////
    ////////////////////////
    public void enqueue(T data){ // Adds to the tail of the queue with data using add()
        add(data, 0);
    }

    //////////////////////////////
    /// ENQUEUE(T DATA, CODE) ////
    //////////////////////////////
    public void enqueue(T data, int code){ // Adds to the tail of the queue with data/code using add()
        add(data, code);
    }

    ////////////////
    /// DEQUEUE ////
    ////////////////
    public T dequeue(){ // Deletes from the back of the list using delete()
        return delete();
    }
}
