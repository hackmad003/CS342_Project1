public class GenericQueue<T> extends GenericList<T> {

    
    private Node<T> tail;

    //initalize list
    public GenericQueue(T initialValue) {
        Node<T> firstNode = new Node<>(initialValue);
        setHead(firstNode);
        this.tail = firstNode;
        setLength(1);
    }

    
    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    //add element end of list, adjust head/tail
    @Override
    public void add(T data) {
        Node<T> newNode = new Node<>(data);

        if (getHead() == null) 
        {
            setHead(newNode);
            tail = newNode;
        } 
        else 
        {
            tail.next = newNode;
            tail = newNode;
        }
        setLength(getLength() + 1);
    }

    //overloaded with the secondary data code
    public void add(T data, int code) 
    {
        Node<T> newNode = new Node<>(data);
        newNode.code = code;

        if (getHead() == null) 
        {
            setHead(newNode);
            tail = newNode;
        } 
        else 
        {
            tail.next = newNode;
            tail = newNode;
        }
        setLength(getLength() + 1);
    }

    //remove last element from list adjust head/tail
    @Override
    public T delete() {
        Node<T> head = getHead();
        if (head == null) return null;

        T data = head.data;
        setHead(head.next);
        setLength(getLength() - 1);

        if (getHead() == null) {
            tail = null;
        }

        return data;
    }

 
    public void enqueue(T data) 
    {
        add(data);
    }


    public T dequeue() 
    {
        return delete();
    }
}
