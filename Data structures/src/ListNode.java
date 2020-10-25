//Creating the class that represents a list node (either it is singly-linked or doubly-linked). It is configured later depending the type of the list!
public class ListNode {

    public char item;
    public ListNode previous;
    public ListNode next;

    public ListNode(char item) {
        this.item = item;
        previous = null;
        next = null;
    }
}