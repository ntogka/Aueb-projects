import java.io.File;
import java.io.IOException;
import java.util.*;

public class mainApp{
	
	public static void main(String[] args) throws IOException{
		File F = new File("1.2-sm.txt");
		List<Integer> list = Utilities.convertFileSequenceToList(F);
		Scanner input = new Scanner(System.in);
		Quicksort_util QU = new Quicksort_util();
		Random random = new Random();
		
		int N = list.size();
		int[] tempArray = new int[N];
		for(int i=0; i<N; i++){
			tempArray[i] = list.get(i);
		}
		System.out.println("BEFORE");
		for(int i=0; i<tempArray.length; i++){
			System.out.print(tempArray[i]+" ");
		}
		QU.quicksort(tempArray, 0, N-1);
		System.out.println("\nAFTER");
		for(int i=0; i<tempArray.length; i++){
			System.out.print(tempArray[i]+" ");
		}
	}
}