package algorithm.sort;

/*
 * 思路：进行n-1次操作，每次操作后，数组前部排好序的部分都会增长一个元素，而数组后部未排好续的部分减少一个元素
 * 每次操作都从数组后部未排序的部分选出最小的那一个，放入未排序区域的首元素，而首元素被交换到最小元素位置（这是不稳定的源泉）。之后未排序部分首元素被纳入排好序部分。
 * 选测排序和插入排序策略类似。
 * 选择排序是不稳定的
 */
public class SelectSort  extends SortBase {
	
	public SelectSort(String s){
		super(s);
	}
	
	@Override
	public void sort(){
		for(int i = 0; i < this.source.length - 1; i++){ //注意，n个元素的数组，n-1次选择就够了
			int rangeStart = i;
			int smallestInRange = rangeStart;
			for(int j = rangeStart; j < this.source.length; j++){
				if(this.source[smallestInRange] > this.source[j]){
					smallestInRange = j;
				}
			}
			char temp = this.source[rangeStart];
			this.source[rangeStart] = this.source[smallestInRange];
			this.source[smallestInRange] = temp;
		}
	}
	
	public static void main(String[] args) {
		SelectSort qs = new SelectSort("tazbctmkghzzxxyynpqqp");
		qs.sort();
		qs.printResult();
	}

}
