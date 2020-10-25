import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class mainApp{
	
	public static void main(String[] args) throws IOException{
		File F = new File("1.1-sm.txt");
		List<Integer> list = Utilities.convertFileSequenceToList(F);
		Scanner input = new Scanner(System.in);
		
		int N = list.size();
		int[] tempArray = new int[N];
		for(int i=0; i<N; i++){
			tempArray[i] = list.get(i);
		}
		System.out.print("Enter a number: ");
        int x = input.nextInt();
		System.out.println("First Occurence of "+x+": "+DQ_util.firstOccurence(tempArray, 0, N-1, x, N));
		System.out.println("Last Occurence of "+x+": "+DQ_util.lastOccurence(tempArray, 0, N-1, x, N));
	}
}