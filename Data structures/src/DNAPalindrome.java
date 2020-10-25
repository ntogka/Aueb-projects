import java.util.*;

/*The class DNAPalindrome is a program-client in which the client gives a DNA Sequence and then the program checks whether it is a Watson-Crick complemented palindrome.
  This means that when the its complementary DNA is written it should be read the same as the initial.
  For example the DNA Sequence "AAAACGTTTT" is converted to "TTTTGCAAAA" (complementary DNA). They are read in the same way! (from the beginning to the end and vice versa)*/
public class DNAPalindrome {

    //The method validDNASequence checks whether the user-inputted DNA sequence is valid, i.e the sequence contains ONLY the letters A,T,G,C or the " "
    public static boolean validDNASequence(String sequence) {
        for (int i = 0; i < sequence.length(); i++) {
            if ((sequence.matches("[ATCG]*")) || (sequence.equals(" "))) {  //If the sequence contains ONLY the letters A,T,G,C or is equal to the empty string return true
                return true;
            }
        }
        return false;
    }

    /*The method complementDNA replaces every nucleobase with its complement. To be more specific:
        Adenine (A) = Thymine (T) (and vice versa)
        Guanine (G) = Cytosine (C) (and vice versa)
      However there is another "complement" and it is the " " ONLY on the beginning of the sequence and it is
      the only item on the sequence because it is a Watson-Crick complement DNA sequence!
      In order for this to work, the space button must be pressed once and then enter, or else it won't work!*/
    public static void complementDNA(CharDoubleEndedQueueImpl deque){
        int counter = 0;
        while(counter < deque.size()){
            if (deque.getFirst() == 'A'){   //If the first character of the DNA Sequence is the letter A (G,C,T,' ') remove it from the front of the deque
                deque.removeFirst();        //and add its complement to the back of the deque
                deque.addLast('T');
            }
            else if(deque.getFirst() == 'T'){
                deque.removeFirst();
                deque.addLast('A');
            }
            else if(deque.getFirst() == 'G'){
                deque.removeFirst();
                deque.addLast('C');
            }
            else if(deque.getFirst() == 'C'){
                deque.removeFirst();
                deque.addLast('G');
            }
            else{
                deque.removeFirst();
                deque.addLast(' ');
            }
            counter += 1;
        }
    }

    //The method isDNAPalindrome checks whether the DNA Sequence is a Watson-Crick complemented palindrome
    public static boolean isDNAPalindrome(CharDoubleEndedQueue deque, String initial_DNASequence){
        int counter = 0;
        for (int i=0; i<initial_DNASequence.length(); i++) {
            if (initial_DNASequence.charAt(i) == deque.getLast()) {     //If the character on the initial DNA sequence is equal to the last character of the
                deque.removeLast();                                     // converted DNA Sequence remove it from the deque and increase the counter by 1
                counter += 1;
            }
        }
        if (counter == initial_DNASequence.length()) {  //If the counter is equal to the length of the string representing the initial DNA Sequence return true
            return true;                                //It means that the DNA Sequence is indeed a Watson-Crick complemented palindrome!
        }
        else {
            return false;
        }
    }

    public static void main(String[] args){
        CharDoubleEndedQueueImpl deque = new CharDoubleEndedQueueImpl();
        Scanner input = new Scanner(System.in);
        String DNA_sequence;

        System.out.println("This program checks whether a given DNA sequence is a Watson-Crick complemented palindrome\n");
        System.out.print("Give a DNA sequence: ");
        DNA_sequence = input.nextLine();

        if (validDNASequence(DNA_sequence)) {       //If the DNA sequence is valid put each character at the back of the deque (doing that way, the DNA Sequence will be in order)
            for (int i = 0; i < DNA_sequence.length(); i++) {
                deque.addLast(DNA_sequence.charAt(i));
            }
            complementDNA(deque);   //Converts the DNA Sequence to its complement
            //If the DNA sequence is (not) a Watson-Crick complemented palindrome then the corresponding message
            if(isDNAPalindrome(deque,DNA_sequence)){
                System.out.println("\nThe DNA sequence "+DNA_sequence+" is a Watson-Crick complemented palindrome!");
            }
            else{
                System.out.println("\nThe DNA sequence "+DNA_sequence+" is not a Watson-Crick complemented palindrome!");
            }
        }
        else{
            System.out.println(DNA_sequence+" is not a DNA Sequence!");
        }
    }
}
