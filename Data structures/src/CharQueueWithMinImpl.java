import java.io.PrintStream;
import java.util.NoSuchElementException;

/* -> The class CharQueueWithMinImpl inherits the class CharQueueImpl so the methods peek(),size(),isEmpty(),printQueue(stream), don't need to be implemented here!
      However, put(item) and get() must be implemented because of the 2 queues we have and their different functions they have.
      For example we use put(item) for the FIFO Queue to insert an item on the queue, and addFirst(item) to insert am item on the front of the deque.
   -> The main idea is that the queue is our basic data structure is the queue (CharQueue) and the deque (CharDoubleEndedQueue) is the 'supplemental' data structure
      to hold the minimum character (only ONE character (item) should be shown on the deque). */
public class CharQueueWithMinImpl extends CharQueueImpl implements CharQueueWithMin {

    CharDoubleEndedQueueImpl deque = new CharDoubleEndedQueueImpl();

    public CharQueueWithMinImpl(){
        super();
    }

    //The method put(item) inserts an item on the queue and/or on the deque depending on the situation
    public void put(char item){
        if (deque.isEmpty()){     //If the deque is empty insert the character on both the queue and deque
            super.put(item);
            deque.addFirst(item);
        }
        else{
            super.put(item);    //Initially we insert the character on the queue
            if (item < deque.getLast()){    //If the character we want to insert on the deque is lexicographically smaller than the one that is on the deque
                deque.removeLast();         //we remove it and insert the desired character
                deque.addFirst(item);
            }
        }
    }

   public char get() throws NoSuchElementException {
       if (isEmpty()) {
           throw new NoSuchElementException("The queue is empty! Nothing to remove!");
       }
       char returnedCharacter = super.get();    //This variable stores the oldest character that was removed from the queue
       if (returnedCharacter == deque.getLast()) {    //If this character equals with the last character on the deque then remove it from the back of the deque
           deque.removeLast();                        //(The same character is removed from both the queue and deque!)
           ListNode node = super.header;    //Creating a node equal to the header of the CharQueue
           deque.addFirst(header.item);     //Add the character stored in the header on the front of the deque
           while (node != null) {
               if (node.item < deque.getLast()) {    //As long as the node is not null if the character stored on the node is lexicographically smaller
                   deque.removeLast();              //than one on the back of the deque, remove it from there and insert the node's character on the front of the deque
                   deque.addFirst(node.item);
               }
               node = node.next;    //Move to the next node
           }
       }
       return returnedCharacter;    //Return the character that was removed from the queue (and the deque if the the second if-statement was true!)
   }

    /*The method printQueue prints the elements of the queue and deque in thw way they were implemeneted correspondingly!
      It is not needed because it already inherits from the CharQueue class, but because we want to see how the queue and the deque
      look we've implemented it here! The printing sequence will look like this:
            QUEUE: (characters)
            DEQUE: (characters, it should only display one!)
    */
    public void printQueue(PrintStream stream){
        super.printQueue(stream);
        stream.println();
        deque.printQueue(stream);
        stream.println();
        stream.flush();
    }

    //The minimum character is the first element of the deque! There should only be 1 element on the deque which holds the minimum character!
    public char min(){
        return deque.getFirst();
    }

    public static void main (String[] args){
        CharQueueWithMinImpl CQWI = new CharQueueWithMinImpl();

        CQWI.put('L');
        CQWI.printQueue(System.out);
        CQWI.put('R');
        CQWI.printQueue(System.out);
        CQWI.put('F');
        CQWI.printQueue(System.out);
        CQWI.put('A');
        CQWI.printQueue(System.out);
        CQWI.put('M');
        CQWI.printQueue(System.out);
        CQWI.put('D');
        CQWI.printQueue(System.out);
        CQWI.get();
        CQWI.printQueue(System.out);
        CQWI.get();
        CQWI.printQueue(System.out);
        CQWI.get();
        CQWI.printQueue(System.out);
        CQWI.get();
        CQWI.printQueue(System.out);

        System.out.println("Minimum character: "+CQWI.min());
    }
}
