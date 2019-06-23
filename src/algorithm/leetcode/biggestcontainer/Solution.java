package algorithm.leetcode.biggestcontainer;

public class Solution {
	
	private int[] y = null;
	
	public Solution(int[] high) {
		y = high;
	}
	
	/*
	 * 第一种方法：暴力地由左侧开始找，两层循环。
	 * 一个小加速：直接跳过外层循环比前一个获得最大容量时的左侧边矮的那些左侧边
	 */
	public int maxContainer() {
		int max = 0;
		int maxi = 0;
		for(int i = 0; i < this.y.length; i++) {
			
			int linei = this.y[i];
			if(linei <= maxi) {
				continue;
			}
			
			for(int j = i+1; j < this.y.length; j++) {
				int linej = this.y[j];
				int line = 0;
				if(linei < linej) {
					line = linei;
				}else {
					line = linej;
				}
				if (max < line * (j-i)) {
					max = line * (j-i);
					maxi = linei;
				}
			}
		}
		
		return max;
	}
	
	/*
	 * 由方法1的加速方法得到想法：左右两侧可以都向中间移动，
	 */
	public int maxContainer2() {
		
		int lineLeftIdx = 0;
		int lineLeft = 0;
		int lineRightIdx = this.y.length - 1;
		int lineRight = 0;
		int max = 0;
		
		while(lineLeftIdx < lineRightIdx) {
			int left = this.y[lineLeftIdx];
			int right = this.y[lineRightIdx];
			
			if(left <= lineLeft) {
				lineLeftIdx++;
				continue;
			}else if(right <= lineRight) {
				lineRightIdx--;
				continue;
			}
			
			int high = 0;
			if(left <= right) {
				high = left;
			}else {
				high = right;
			}
			
			if(high * (lineRightIdx - lineLeftIdx) > max) {
				max = high * (lineRightIdx - lineLeftIdx);
			}
			
			if(left <= right) {
				lineLeftIdx++;
			}else {
				lineRightIdx--;
			}
		}
		return max;
	}
	
	/*
	 * copy 一个别人的精简解法，思想和maxContainer2一致
	 */
	public int maxContainer3() {
		 int max = 0;
		 for(int i = 0, j = this.y.length - 1; i < j ; ){
			 int minHeight = this.y[i] < this.y[j] ? this.y[i ++] : this.y[j --];
			 max = Math.max(max, (j - i + 1) * minHeight);
		 }
		 return max;
	}
	
	public static void main(String[] args) {
		int[] y = {1,8,6,2,5,4,8,3,7};
		Solution s = new Solution(y);
		System.out.print("Max volume: " + s.maxContainer3());
	}

}
