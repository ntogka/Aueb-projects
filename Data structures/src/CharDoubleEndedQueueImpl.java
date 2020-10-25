import java.io.PrintStream;
import java.util.NoSuchElementException;

//The class CharDoubleEndedQueueImpl implements the methods of the corresponding interface which represents a character deque.
public class CharDoubleEndedQueueImpl implements CharDoubleEndedQueue {
    private ListNode header;    //Front of the list (deque)
    private ListNode tail;      //Back of the list (deque)
    private int size;           //Size of the list (deque)

    public CharDoubleEndedQueueImpl() {
        header = tail = null;
        size = 0;
    }

    //Checks whether the list (deque) is empty
    public boolean isEmpty() {
        return size == 0;
    }

    //Adds a character at the front of the list (deque)
    public void addFirst(char item) {
        ListNode tempNode = new ListNode(item);
        if(header != null)
        {
            tempNode.next = header;
            header.previous = tempNode;
        }
        else
        {
            tail = tempNode;
        }
        header = tempNode;
        size++;
    }

    //Removes a character from the front of the list (deque). Throwa NoSuchElementException if the queue is empty.
    public char removeFirst() throws NoSuchElementException
    {
        if(isEmpty())
        {
            throw new NoSuchElementException("The queue is empty! Nothing to remove!");
        }
        ListNode tempNode = header;
        char removedFirstChar = tempNode.item;
        header = tempNode.next;
        if(header != null)
        {
            header.previous = null;
        }
        else
        {
            tail = null;
        }
        tempNode.item = ' ';
        tempNode.previous = tempNode.next = null;
        size--;
        return removedFirstChar;
    }

    //Adds a character at the back of the list (deque)
    public void addLast(char item)
    {
        ListNode tempNode = new ListNode(item);
        if (tail != null)
        {
            tempNode.previous = tail;
            tail.next = tempNode;
        }
        else
        {
            header = tempNode;
        }
        tail = tempNode;
        size++;
    }

    //Removes a character from the back of the list (deque). Throws NoSuchElementException if the queue is empty.
    public char removeLast() throws NoSuchElementException
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("The queue is empty! Nothing to remove!");
        }
        ListNode tempNode = tail;
        char removedLastChar = tempNode.item;
        tail = tempNode.previous;
        if(tail != null)
        {
            tail.next = null;
        }
        else
        {
            header = null;
        }
        tempNode.item = ' ';
        tempNode.previous = tempNode.next = null;
        size--;
        return removedLastChar;
    }

    //Returns without removing the first character of the list (deque)
    public char getFirst()
    {
        return header.item;
    }

    //Returns without removing the last character of the list (deque)
    public char getLast()
    {
        return tail.item;
    }

    //Printing the characters of the list (deque) starting from the front to the print stream given as an argument (for example, System.out)
    public void printQueue(PrintStream stream)
    {
        ListNode headerNode = header;
        while (headerNode != null) {
            stream.print("DEQUE: "+headerNode.item);
            stream.println();   //Prints a line to the stream
            stream.flush(); //Flushes the output stream and forces any buffered output bytes to be written out. It is done to improve the I/O performance!
            headerNode = headerNode.next;
        }
    }

    //Returns the size of the list (deque)
    public int size()
    {
        return size;
    }
}
