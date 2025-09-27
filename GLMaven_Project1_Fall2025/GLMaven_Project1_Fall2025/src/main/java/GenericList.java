import java.util.Iterator;
import java.util.ArrayList;
import java.util.Stack;

public abstract class GenericList<T> implements Iterable<T> 
{

    private Node<T> head;
    private int length;

    // Data is primary info of any type code is extra int data
    public static class Node<T> 
    {
        T data;
        int code;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.code = 0;
            this.next = null;
        }

        public Node(T data, int code) 
        {
            this.data = data;
            this.code = code;
            this.next = null;
        }

    }

    // Abstract methods
    public abstract void add(T data);
    public abstract T delete();

    // Constructor, getters, setters, length print
    public Node<T> getHead() { return head; }
    public void setHead(Node<T> head) { this.head = head; }
    public int getLength() { return length; }
    public void setLength(int length) { this.length = length; }

    public void print() {
        if (head == null) 
        {
            System.out.println("Empty List");
            return;
        }
        Node<T> current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public ArrayList<T> dumpList() 
    {
        ArrayList<T> list = new ArrayList<>();
        Node<T> current = head;

        while (current != null) 
        {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    public T get(int index) 
    {
        if (index < 0 || index >= length) return null;
        Node<T> current = head;

        for (int i = 0; i < index; i++) 
        {
            current = current.next;
        }

        return current.data;
    }

    public T set(int index, T element) 
    {
        if (index < 0 || index >= length) return null;
        Node<T> current = head;

        for (int i = 0; i < index; i++) 
        {
            current = current.next;
        }
        T oldData = current.data;
        current.data = element;
        return oldData;
    }

    //head to tail
    @Override
    public Iterator<T> iterator() 
    {
        return new GLLIterator<>(head);
    }

   //tail to head 
   public Iterator<T> descendingIterator() 
   {
        return new ReverseGLLIterator<>(head);
   }

}