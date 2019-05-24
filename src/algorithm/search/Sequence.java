package algorithm.search;

import java.util.ArrayList;
import java.util.List;

public class Sequence extends SearchBase {
	
	
	public Sequence(Character[] array) {
		super(array);
	}
	
	@Override
	public List<Integer> search(char target){
		if(this.array == null || this.array.length ==0) {
			return null;
		}
		
		List<Integer> result = new ArrayList<Integer>();
		for(int i = 0; i < this.array.length; i++) {
			if(this.array[i] == target) {
				result.add(i);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Character[] source = {'a','b','e','g','h','e'};
		Sequence s = new Sequence(source);
		List<Integer> result = s.search('e');
		
		s.printResult(result);
	}
}
