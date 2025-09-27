import java.util.ArrayList;
import java.util.Iterator;

public class MyHashMap<T> implements Iterable<T> {

    private ArrayList<GenericQueue<T>> map;

    public MyHashMap(String key, T value) {
        map = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            map.add(null);
        }
        put(key, value);
    }

    public void put(String key, T value) {
        int hashCode = key.hashCode();
        int hashValue = Math.abs(hashCode % 10);

        GenericQueue<T> queue = map.get(hashValue);

        if (queue == null) {
            queue = new GenericQueue<>(key, value, hashCode);
            map.set(hashValue, queue);
        } else {
            queue.add(key, value, hashCode);
        }
    }

    public boolean contains(String key) {
        int hashCode = key.hashCode();
        int hashValue = Math.abs(hashCode % 10);

        GenericQueue<T> queue = map.get(hashValue);

        if (queue == null) return false;

        GenericList.Node<T> current = queue.getHead();

        while (current != null) {
            if (current instanceof GenericQueue.QueueNode) {
                GenericQueue.QueueNode node = (GenericQueue.QueueNode) current;
                if (node.code == hashCode && key.equals(node.key)) {
                    return true;
                }
            }
            current = current.next;
        }

        return false;
    }

    public T get(String key) {
        int hashCode = key.hashCode();
        int hashValue = Math.abs(hashCode % 10);

        GenericQueue<T> queue = map.get(hashValue);

        if (queue == null) return null;

        GenericList.Node<T> current = queue.getHead();

        while (current != null) {
            if (current instanceof GenericQueue.QueueNode) {
                GenericQueue.QueueNode node = (GenericQueue.QueueNode) current;
                if (node.code == hashCode && key.equals(node.key)) {
                    return (T) node.data;
                }
            }
            current = current.next;
        }

        return null;
    }

    public int size() {
        int total = 0;
        for (GenericQueue<T> queue : map) {
            if (queue != null) {
                total += queue.getLength();
            }
        }
        return total;
    }

    public boolean isEmpty() {
        for (GenericQueue<T> queue : map) {
            if (queue != null && queue.getLength() > 0) {
                return false;
            }
        }
        return true;
    }

    public T replace(String key, T value) {
        int hashCode = key.hashCode();
        int hashValue = Math.abs(hashCode % 10);

        GenericQueue<T> queue = map.get(hashValue);
        if (queue == null) return null;

        GenericList.Node<T> current = queue.getHead();

        while (current != null) {
            if (current instanceof GenericQueue.QueueNode) {
                GenericQueue.QueueNode node = (GenericQueue.QueueNode) current;
                if (node.code == hashCode && key.equals(node.key)) {
                    T oldData = (T) node.data;
                    node.data = value;
                    return oldData;
                }
            }
            current = current.next;
        }

        return null;
    }

    @Override
    public Iterator<T> iterator() {
        return new HMIterator<>(map);
    }
}

