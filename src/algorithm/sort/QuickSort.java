package algorithm.sort;

/*
 * 思路：先为一个元素找到它在排好序后的数组中的位置，然后以它为中间点，把数组一分为二，各自进行同样的排序操作
 * 为一个元素找位置的过程是一个由数组两头轮换寻找的过程： 从左边找比它大的；从右边找比它小的。找到之后向左（或右）半边交换
 */
public class QuickSort extends SortBase {

	public QuickSort(String s) {
		super(s);
	}

	@Override
	public void sort(){
		this.doSort(0, this.source.length - 1);
	}
	
	private void doSort(int start, int end){
		if(start >= end || start < 0 || end < 0){
			return;
		}
		
		int i = start;
		int j = end;
		char key = this.source[i];
		int direction = 0; //search in right
		
		int keyPosition = i;
		
		while(j < i ){
			if(direction == 0){
				while( j > i && this.source[j] >=key ) j--;
			}else{
				while( j > i && this.source[i] <= key ) i++;
			}
			
			if(j > i){ //找到了用于交换的位置
				if(direction == 0){
					this.source[i] = this.source[j];
					direction = 1;
				}else{
					this.source[j] = this.source[i];
					direction = 0;
				}
			}else{ //没有找到可用于交换位置，那么也就是说key的位置可以被确定了
				if(direction == 0){
					this.source[i] = key;
					keyPosition = i;
				}else{
					this.source[j] = key;
					keyPosition = j;
				}
				this.doSort(start, keyPosition-1);
				this.doSort(keyPosition-1, end);
			}
		}
	}
	
	public static void main(String[] args) {
		QuickSort qs = new QuickSort("tazbctmkghzzxxyynpqqp");
		qs.sort();
		qs.printResult();
	}

}
