import java.util.ArrayList;
import java.util.Iterator;

/*
navigates each GenericQueue to each
ArrayList and iterates through their nodes from head to tail.
*/

public class HMIterator<T> implements Iterator<T> 
{

    private ArrayList<GenericQueue<T>> map;
    private int currentIndex;
    private GenericList.Node<T> currentNode;

    public HMIterator(ArrayList<GenericQueue<T>> map) 
    {
        this.map = map;
        this.currentIndex = 0;
        this.currentNode = null;
        advanceToNext();
    }

    //traverse
    private void advanceToNext() {
        if (currentNode != null && currentNode.next != null) 
        {
            currentNode = currentNode.next;
            return;
        }

        currentNode = null;
        while (currentIndex < map.size()) 
        {
            GenericQueue<T> queue = map.get(currentIndex++);
            if (queue != null && queue.getHead() != null) 
            {
                currentNode = queue.getHead();
                break;
            }
        }
    }


    //Checks if there is another value in hash
    @Override
    public boolean hasNext() 
    {
        return currentNode != null;
    }

 
    //Returns the current value
    @Override
    public T next() 
    {
        T data = currentNode.data;
        advanceToNext();
        return data;
    }
}