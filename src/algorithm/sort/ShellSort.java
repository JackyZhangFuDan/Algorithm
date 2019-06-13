package algorithm.sort;

import algorithm.search.SearchBase;

/*
 * 思路：先给出一个分组方案，是一个整数数组，元素从大到小递减，保证最后一个元素是1；每个分组规则对应一轮操作。
 * 在一轮操作中，我们以本轮分组规则为下标间隔把待排序数组分组，每组都进行插入排序。
 * 这里要特别注意：实现各组插入排序时我们并不是先把一个组的插入排序完成再去完成下一个分组，
 * 而是轮流做各个组的一次插入，这样会为算法带来简便
 * Shell排序不稳定
 */
public class ShellSort extends SortBase{
	private int[] groupSize;
	
	public ShellSort(int[] groupSize, String s) {
		super(s);
		if(groupSize[groupSize.length-1] != 1) {
			System.out.println("The last group size must be 1.");
			return;
		}
		this.groupSize = groupSize;
	}
	
	@Override
	public void sort() {
		for(int i = 0; i < this.groupSize.length; i++) {
			int gSize = this.groupSize[i];
			for(int j = gSize; j < this.source.length; j++) {
				char currentChar = this.source[j];
				int k;
				for(k = j - gSize; k >= 0 && this.source[k] > currentChar; k = k - gSize) {
					this.source[k+gSize] = this.source[k];
				}
				this.source[k+gSize] = currentChar;
			}
		}
	}
	
	public static void main(String[] args) {
		ShellSort bs = new ShellSort(new int[] {4,3,1},"eoioircxcxlrioas");
		bs.sort();
		bs.printResult();

	}

}
