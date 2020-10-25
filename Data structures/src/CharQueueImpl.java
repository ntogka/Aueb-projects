import java.io.PrintStream;
import java.util.NoSuchElementException;

//The class CharQueueImpl implements the methods of the corresponding interface which represents a character queue.
public class CharQueueImpl implements CharQueue {

    protected ListNode header;  //The front of the list (queue)
    protected ListNode tail;    //The back of the list (queue)
    private int size;           //Size of the list (queue)

    public CharQueueImpl(){
        super();
        header = tail = null;
        size = 0;
    }

    //Checks if the queue is empty
    public boolean isEmpty(){
        return size==0;
    }

    //Inserts a single character to the queue
    public void put(char item){
        ListNode tempNode = tail;
        tail = new ListNode(item);
        if(isEmpty()){
            header = tail;
        }
        else{
            tempNode.next = tail;
        }
        size++;
        System.out.println("Putting the character: "+tail.item);    //OPTIONAL, it just makes the program more user-friendly!
    }

    //Removes the oldest character that was put on the queue. Throws NoSuchElementException if the queue is empty.
    public char get() throws NoSuchElementException{
        if (isEmpty()){
            throw new NoSuchElementException("The queue is empty! Nothing to remove!");
        }
        char removedCharacter = header.item;
        System.out.println("Removing the character: "+header.item);     //OPTIONAL, it just makes the program more user-friendly!
        if (header == tail){
            header = tail = null;
        }
        else
        {
            header = header.next;
        }
        size--;
        return removedCharacter;

    }

    //Returns without removing the oldest item that was put on the queue
    public char peek() throws NoSuchElementException{
        if (isEmpty()){
            throw new NoSuchElementException("The queue is empty! Nothing to remove!");
        }
        return header.item;
    }

    //Prints the elements of the queue starting from the oldest to the stream given as an argument (for example, System.out)
    public void printQueue(PrintStream stream){
        stream.print("QUEUE: ");
        for(ListNode headerNode = header; headerNode != null; headerNode = headerNode.next){ //could be done with a while-loop as well
            stream.print(headerNode.item);
            stream.flush();
        }
    }

    //Returns the size of the queue
    public int size(){
        return size;
    }
}
