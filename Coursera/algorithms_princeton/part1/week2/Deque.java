public class Deque<Item> implements Iterable<Item> {

    private int size = 0;
    private Node<Item> first = null;
    private Node<Item> last  = null;

    // Node entity in the linked list structure
    private class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> previous;

        public Node(Item item) {
            this.item = item;
            this.next = null;
            this.previous = null;
        }
    }

    public Deque()                           // construct an empty deque
    {

    }
    public boolean isEmpty()                 // is the deque empty?
    {
        return this.size == 0;
    }
    public int size()                        // return the number of items on the deque
    {
        return this.size;
    }
    public void addFirst(Item item)          // insert the item at the front
    {
        if (item == null) throw new NullPointerException();

        Node<Item> newNode = new Node<Item>(item);
        newNode.next = this.first;
        newNode.previous = null;

        if (this.first != null) this.first.previous = newNode;
        if (this.isEmpty()) this.last = newNode;
        this.first = newNode;

        this.size++;
    }
    public void addLast(Item item)           // insert the item at the end
    {
        if (item == null) throw new java.lang.NullPointerException();

        Node<Item> newNode = new Node<Item>(item);
        newNode.previous = this.last;
        newNode.next = null;

        if (this.last != null) this.last.next = newNode;
        if (this.isEmpty()) this.first = newNode;
        this.last = newNode;

        this.size++;
    }
    public Item removeFirst()                // delete and return the item at the front
    {
        if (this.isEmpty()) throw new java.util.NoSuchElementException();

        Node<Item> oldFirst = this.first;
        this.first = oldFirst.next;

        this.size--;

        return oldFirst.item;
    }
    public Item removeLast()                 // delete and return the item at the end
    {
        if (this.isEmpty()) throw new java.util.NoSuchElementException();

        Node<Item> oldLast = this.last;
        this.last = oldLast.previous;

        this.size--;

        return oldLast.item;
    }
    // Iterable implementation
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return null;
    }

    public static void main(String[] args)   // unit testing
    {
        Deque<String>d = new Deque<String>();

        d.addLast("Be");
        d.addLast("Or");
        d.addLast("Not");
        d.addLast("To");
        d.addLast("Be");
        d.addFirst("To");
        d.addLast("...");

        int initSize = d.size();
        for (int i = 0; i < initSize; i++)
        {
            String s = d.removeLast();
            StdOut.print(s + " ");
        }

    }
}
