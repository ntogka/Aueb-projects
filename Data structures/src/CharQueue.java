import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Defines the methods for a FIFO queue that handles characters
 */
public interface CharQueue {

    /**
     * @return true if the queue is empty
     */
    public boolean isEmpty();

    /**
     * insert a single character to the queue
     */
    public void put(char item);

    /**
     * remove and return the oldest item of the queue
     * @return oldest item of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public char get() throws NoSuchElementException;

    /**
     * return without removing the oldest item of the queue
     * @return oldest item of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public char peek() throws NoSuchElementException;


    /**
     * print the elements of the queue, starting from the oldest
     * item, to the print stream given as argument. For example, to
     * print the elements to the
     * standard output, pass System.out as parameter. E.g.,
     * printQueue(System.out);
     */
    public void printQueue(PrintStream stream);

    /**
     * return the size of the queue, 0 if it is empty
     * @return number of elements in the queue
     */
    public int size();
}