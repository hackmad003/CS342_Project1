
public class GenericQueue<T> extends GenericList<T> {

    private Node<T> tail;
   
    public GenericQueue(T value) { 
    	GenericList.Node<T> node = new GenericList.Node<>(value, 0);
        setHead(node); 
        tail = node; 
        setLength(1);
    }

    @Override
    //add to the back of the list
    public void add(T data) { 
        add(data, 0);
    }


    public void add(T data, int code){
        Node<T> newNode = new Node<>(data, code); 
        if (getHead() == null) { 
            setHead(newNode);
            tail = newNode;
        } else { 
            tail.next = newNode;
            tail = newNode; 
        }
        setLength(getLength() + 1); 
    }
    

    
    @Override
    //returns the value of and deletes the last node in the list
    public T delete() {
    	//CASE 1: EMPTY LIST
    	if(getHead() == null) return null; 
    	//CASE 2: SINGLE NODE
    	if(getHead().next == null){  
    		T value = getHead().data; 
    		setHead(null); 
    		tail = null;
    		setLength(0); 
    		return value; 
    	}
    	//CASE 3: MULTIPE NODES
    	Node<T> current = getHead();  
    	while(current.next.next != null) { 
    		
    		current = current.next;
    	}
    	T value = current.next.data; 
    	current.next = null; 
    	tail = current; 
    	setLength(getLength() -1); 
    	return value; 
    }

    // Adds to the tail of the queue with data using add()
    public void enqueue(T data){ 
        add(data, 0);
    }

    // Adds to the tail of the queue with data/code using add()
    public void enqueue(T data, int code){ 
        add(data, code);
    }

    // Deletes from the back of the list using delete()
    public T dequeue(){ 
        return delete();
    }
}
