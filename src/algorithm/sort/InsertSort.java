package algorithm.sort;

/*
 * 思路：数组头部是排好序的区域，后部是未排序区域，进行n次操作，每一次操作之后，前部排好序的区域扩大一个元素；后部未排序的区域缩小一个元素。
 * 每次操作时，把未排序部分的第一个元素拿来，把它插入到排好序的区域的合适位置
 * 插入排序是稳定的
 */
public class InsertSort extends SortBase  {
	
	private char[] sorted;
	
	public InsertSort(String s) {
		super(s);
	}
	
	public void printResult() {
		for(Character c : this.sorted) {
			System.out.println(c);
		}
	}
	
	/*
	 * 书中给出的算法，很精炼
	 */
	@Override
	public void sort() {
		int i, j;
		for(i = 1; i < this.source.length; i++) {
			char currentChar = this.source[i];
			for(j = i - 1; j >= 0; j--) {
				if(this.source[j] > currentChar) {
					this.source[j+1] = this.source[j];
				}else {
					break;
				}
			}
			this.source[j+1] = currentChar;
		}
		this.sorted = this.source;
	}
	
	/*
	 * 自己的写法，需�?�?�一个数组，�?显啰嗦
	 */
	public void sortSelf() {
		this.sorted = new char[this.source.length];
		this.sorted[0] = this.source[0];
		
		for(int i = 1 ; i < this.source.length; i++) {
			char currentChar = this.source[i];
			int j = 0;
			for(j = 0; j < i; j++) {
				if(this.sorted[j] > currentChar) {
					break;
				}
			}
			
			for(int k = i-1; k >= j; k--) {
				this.sorted[k+1] = this.sorted[k];
			}
			
			this.sorted[j] = currentChar;
		}
	}
	
	public static void main(String[] args) {
		InsertSort is = new InsertSort("dcbaezp");
		is.sort();
		is.printResult();
	}

}
