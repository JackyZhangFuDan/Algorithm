package algorithm.sort;

public abstract class SortBase {
	protected char[] source;
	
	public abstract void sort();
	
	public SortBase(String s){
		this.source = s.toCharArray();
	}
	
	public void printResult(){
		System.out.println("result is:");
		for(int i = 0; i < this.source.length ; i++){
			System.out.print(this.source[i]);
		}
	}
}
