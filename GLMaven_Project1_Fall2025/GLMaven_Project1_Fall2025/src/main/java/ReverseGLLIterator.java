import java.util.Iterator;
import java.util.Stack;
import java.util.NoSuchElementException;

public class ReverseGLLIterator<T> implements Iterator<T> {

    private Stack<T> stack;

    public ReverseGLLIterator(GenericList.Node<T> head) {
        stack = new Stack<>();
        GenericList.Node<T> current = head;
        while (current != null) {
            stack.push(current.data);
            current = current.next;
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public T next() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.pop();
    }
}
