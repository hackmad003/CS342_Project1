import java.util.ArrayList;
import java.util.Iterator;

public class MyHashMap<T> implements Iterable<T> 
{

    private ArrayList<GenericQueue<T>> map;

    //makes an arraylist map  with 10 slots, all set to null, and creates the first key value pair
    public MyHashMap(String key, T value) 
    {
        map = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            map.add(null);
        }
        put(key, value);
    }


    /*
    Adds a key-value pair to the hash map.
    If the bucket already contains a GenericQueue 
    (collision), the value is appended 
    to the existing queue with the associated hash code.
    */
    public void put(String key, T value) 
    {
        int hashCode = key.hashCode();
        int hashValue = Math.abs(hashCode) % 10;

        GenericQueue<T> queue = map.get(hashValue);

        if (queue == null) 
        {
            queue = new GenericQueue<>(value);
            queue.getHead().code = hashCode;
            map.set(hashValue, queue);
        } 
        else 
        {
            queue.add(value, hashCode);
        }
    }




    //if the given key exists in the HashMap, return true if yes and false if no.
    public boolean contains(String key) 
    {
    int hashCode = key.hashCode();
    int hashValue = Math.abs(hashCode) % 10;

    GenericQueue<T> queue = map.get(hashValue);

    if (queue == null) 
    {
        return false;
    }

    GenericList.Node<T> current = queue.getHead();

    while (current != null) 
    {
        if (current.code == hashCode) 
        {
            return true;
        }
        current = current.next;
    }

    return false;
    }


    // returns the value at the given key, or null if the key does not exist in the map.
    public T get(String key) 
    {
        int hashCode = key.hashCode();
        int hashValue = Math.abs(hashCode) % 10;

        GenericQueue<T> queue = map.get(hashValue);

        if (queue == null) 
        {
            return null;
        }

        GenericList.Node<T> current = queue.getHead();

        while (current != null) 
        {
            if (current.code == hashCode) 
            {
                return current.data;
            }
            current = current.next;
        }

        return null;
    }

    // Returns the total number of key-value mappings in the map.
    public int size() 
    {
        int total = 0;

        for (int i = 0; i < map.size(); i++) 
        {
            GenericQueue<T> queue = map.get(i);

            if (queue != null) 
            {
                total += queue.getLength();
            }
        }

        return total;
    }

    // Returns true if the map contains no key-value mappings, false otherwise.
    public boolean isEmpty() 
    {
        for (int i = 0; i < map.size(); i++) 
        {
            GenericQueue<T> queue = map.get(i);

            if (queue != null && queue.getLength() > 0) 
            {
                return false;
            }
        }

        return true;
    }


    // Replaces the value for existing key
    public T replace(String key, T value) 
    {
        int hashCode = key.hashCode();
        int hashValue = Math.abs(hashCode) % 10;

        GenericQueue<T> queue = map.get(hashValue);

        if (queue == null) 
        {
            return null;
        }

        GenericList.Node<T> current = queue.getHead();

        while (current != null) 
        {
            if (current.code == hashCode) 
            {
                T oldData = current.data;
                current.data = value;
                return oldData;
            }
            current = current.next;
        }

        return null;
    }




    //Returns all values stored in hashmap, will go through each generic queue
    @Override
    public Iterator<T> iterator() 
    {
        return new HMIterator<>(map);
    }
    
}
