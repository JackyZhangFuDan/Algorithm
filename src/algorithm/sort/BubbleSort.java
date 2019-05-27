package algorithm.sort;

/*
 * 思路：最多做n-1次操作，每次操作都让最大的字符移到数组的头部
 * 在每次移动时，记录一下最后一个发生位置变动的元素下标，这个元素以前都是排好序的了
 * 冒泡排序是稳定的
 */
public class BubbleSort extends SortBase {
	
	public BubbleSort(String s) {
		super(s);
	}

	@Override
	public void sort() {
		
		int end = 0;
		while(end < this.source.length - 1)
		{
			int lastMovedIdx = this.source.length;
			for(int i = this.source.length - 1; i > end; i--) {
				if(this.source[i] > this.source[i-1]) {
					char c = this.source[i-1];
					this.source[i-1] = this.source[i];
					this.source[i] = c;
					lastMovedIdx = i;
				}
			}
			end = lastMovedIdx;
		}
	}
	
	public static void main(String[] args) {
		BubbleSort bs = new BubbleSort("eoioircxcxlrioas");
		bs.sort();
		bs.printResult();
	}

}
