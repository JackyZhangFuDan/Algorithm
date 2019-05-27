package algorithm.sort;

/*
 * 思路：做几次操作，每次操作让相邻的1，2，4，8.。。n/2个元素为一组，每组都是排好序的，然后两辆合并排序
 */
public class MergeSort extends SortBase {
	
	public MergeSort(String s) {
		super(s);
	}

	@Override
	public void sort() {
		char[] result = new char[this.source.length];
		
		int length = 1;
		
		while( length < this.source.length) {
			int times = this.source.length/(2*length);
			if((this.source.length % (2*length)) != 0) {
				times = times + 1;
			}
			
			int resultIdx = 0;
			
			for(int i = 0; i < times; i++) {
				//待合并的第一个小数组
				int start1 = i*2*length;
				int end1 = start1 + length - 1;
			
				//只有第一个数组，没有第二个了，那么简单copy它到结果中去
				if(end1 >= this.source.length - 1) {
					while(start1 <= this.source.length -1) {
						result[resultIdx++] = this.source[start1++];
					}
					continue; 
				}
				
				//待合并的第二个小数组，注意上面处理了没有第二个部分情况。
				int start2 = start1 + length;
				int end2 = start2 + length - 1;
				if(end2 >= this.source.length) {
					end2 = this.source.length-1;
				}
				
				//开始合并
				while(start1 <= end1 && start2 <= end2) {
					if(this.source[start1] <= this.source[start2]) {
						result[resultIdx++] = this.source[start1++];
					}else {
						result[resultIdx++] = this.source[start2++];
					}
				}
				while(start1 <= end1) {
					result[resultIdx++] = this.source[start1++];
				}
				while(start2 <= end2) {
					result[resultIdx++] = this.source[start2++];
				}
			}
			
			this.source = result.clone();
			length = 2 * length;
		}
	}
	
	public static void main(String[] args) {
		MergeSort ms = new MergeSort("eoioircxcxlrioas0");
		ms.sort();
		ms.printResult();
	}

}
