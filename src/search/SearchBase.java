package search;

import java.util.List;

public abstract class SearchBase {
	protected Character[] array;
	
	public SearchBase(Character[] array) {
		this.array = array;
	}
	
	
	public abstract List<Integer> search(char target);
	
	public void printResult(List<Integer> result) {
		if(result == null) {
			System.out.println("empty result, search fail.");
			return;
		}
		
		System.out.println("searching result: ");
		for(int r : result){
			System.out.println(r);
		}
	}
}
