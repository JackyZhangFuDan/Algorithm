package algorithm.sort;

/*
 * �?�入排�?是�?稳定的排�?算法
 */
public class InsertSort {
	
	private Character[] source;
	private Character[] sorted;
	
	public InsertSort(Character[] s) {
		this.source = s;
	}
	
	public void printResult() {
		for(Character c : this.sorted) {
			System.out.println(c);
		}
	}
	
	/*
	 * 自己的写法，需�?�?�一个数组，�?显啰嗦
	 */
	public void sort() {
		this.sorted = new Character[this.source.length];
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
	
	/*
	 * 书中给出的算法，很精炼
	 */
	public void sortBook() {
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
	
	public static void main(String[] args) {
		InsertSort is = new InsertSort(new Character[] {'d','c','b','a','e','z','p'});
		is.sortBook();
		is.printResult();
	}

}
