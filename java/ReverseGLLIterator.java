import java.util.Iterator;

public class ReverseGLLIterator<T> implements Iterator<T> 
{

    private GenericList.Node<T> current;

    public ReverseGLLIterator(GenericList.Node<T> tail) {
        this.current = tail;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        T data = current.data;
        current = current.prev;
        return data;
    }
}