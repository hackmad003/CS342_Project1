import java.util.Iterator;

public class GLLIterator<T> implements Iterator<T> {

    private GenericList.Node<T> current;

    public GLLIterator(GenericList.Node<T> head) 
    {
        this.current = head;
    }

    //true if there is another element
    @Override
    public boolean hasNext() 
    {
        return current != null;
    }

    //returns current data and advances
    @Override
    public T next() 
    {
        if (current == null) 
        {
            return null;
        }
        T data = current.data;
        current = current.next;
        return data;
    }
}