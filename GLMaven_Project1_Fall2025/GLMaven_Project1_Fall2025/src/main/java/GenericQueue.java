public class GenericQueue<T> extends GenericList<T> {

    private QueueNode tail;

    public class QueueNode extends GenericList.Node<T> {
        String key;

        public QueueNode(String key, T data, int code) {
            super(data, code);
            this.key = key;
        }
    }

   
    public GenericQueue() {
        setHead(null);
        tail = null;
        setLength(0);
    }

   
    public GenericQueue(T data) {
        QueueNode node = new QueueNode(null, data, 0);
        setHead(node);
        tail = node;
        setLength(1);
    }

    
    public GenericQueue(T data, int code) {
        QueueNode firstNode = new QueueNode(null, data, code);
        setHead(firstNode);
        tail = firstNode;
        setLength(1);
    }

   
    public GenericQueue(String key, T data) {
        QueueNode firstNode = new QueueNode(key, data, 0);
        setHead(firstNode);
        tail = firstNode;
        setLength(1);
    }

    
    public GenericQueue(String key, T data, int code) {
        QueueNode firstNode = new QueueNode(key, data, code);
        setHead(firstNode);
        tail = firstNode;
        setLength(1);
    }

    @Override
    public void add(T data) {
        add(null, data, 0); 
    }

    public void add(String key, T data, int code) {
        QueueNode newNode = new QueueNode(key, data, code);

        if (getHead() == null) {
            setHead(newNode);
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        setLength(getLength() + 1);
    }

    public void add(T data, int code) {
    add(null, data, code);
    }

    @Override
    public T delete() {
        if (getHead() == null) return null;

        if (getHead().next == null) {
            T data = getHead().data;
            setHead(null);
            tail = null;
            setLength(0);
            return data;
        }

        QueueNode current = (QueueNode) getHead();
        while (current.next != tail) {
            current = (QueueNode) current.next;
        }

        T data = tail.data;
        current.next = null;
        tail = current;
        setLength(getLength() - 1);
        return data;
    }

    public void enqueue(String key, T data, int code) {
        add(key, data, code);
    }

    public void enqueue(T data) {
    add(null, data, 0);
    }

    
    public void enqueue(T data, int code) {
        add(null, data, code);
    }

    
    public void enqueue(String key, T data) {
        add(key, data, 0);
    }


    public T dequeue() {
        return delete();
    }
}
