import java.util.*;

public class Quicksort_util{
	
	private Random random = new Random();
	
	public void quicksort(int[] array, int L, int H){
		if(H <= L) return;
		int pivot = partition(array, L, H);
		quicksort(array, L, pivot-1);
		quicksort(array, pivot+1, H);
	}
	
	private int partition(int[] array, int L, int H){
		int index = L + random.nextInt(H - L);
		swap(array, index, L);
		int pivot = array[L];
		int i = L + 1;
		while(i <= H){
			if(array[i] < pivot){
				swap(array, L++, i++);
			}
			else if(array[i] == pivot){
				i++;
			}
			else{
				swap(array, i, H--);
			}
		}
		return i-1;
	}
	
	private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}