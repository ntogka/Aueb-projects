public class DQ_util {
	
	public static int firstOccurence(int array[], int L, int H, int x, int N){
		if (H < L){
			return -1;
		}
		else{
			int mid_element = L + (H - L)/2;
			if((mid_element == 0 || x > array[mid_element-1]) && array[mid_element] == x){
				return mid_element;
			}
			else if(x > array[mid_element]){
				return firstOccurence(array, mid_element + 1, H, x, N);
			}
			else{
				return firstOccurence(array, L, mid_element - 1, x, N);
			}
		}
	}
	
	public static int lastOccurence(int array[], int L, int H, int x, int N) {
        if (H < L){
			return -1;
		}
		else{
            int mid_element =  L + (H - L) / 2;
            if ((mid_element == N - 1 || x < array[mid_element + 1]) && array[mid_element] == x) {
                return mid_element;
            }
			else if (x < array[mid_element]) {
                return lastOccurence(array, L, mid_element - 1, x, N);
            } 
			else {
                return lastOccurence(array, mid_element + 1, H, x, N);
            }
        }
    }
}