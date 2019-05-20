package search;

import java.util.ArrayList;
import java.util.List;

/*
 * 这里被搜索的数组必须是排好序的，而且元素不重复。这是二分法的前提
 */
public class Binary extends SearchBase {
	
	public Binary(Character[] array) {
		super(array);
	}
	
	@Override
	public List<Integer> search(char target) {
		if(this.array == null || this.array.length == 0) {
			return null;
		}
		
		List<Integer> result = new ArrayList<Integer>();
		
		int start = 0;
		int end   = this.array.length - 1;
		while(end >= start) {
			int middle = (start + end) / 2;
			if(this.array[middle] == target ) {
				result.add(middle);
				break;
			}else if(this.array[middle] < target) {
				start = middle+1;
			}else {
				end = middle-1;
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Binary b = new Binary(new Character[] {'a','b','c','d','h','k','x','y'} ) ;
		b.printResult(b.search('y'));
	}

	

}
