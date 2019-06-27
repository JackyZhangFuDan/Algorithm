package algorithm.leetcode.bracketgeneration;


public class Solution {
	
	/*
	 * 解答里面给的一个方法，回赎法，很清楚
	 */
	public void solve(int n) {
		this.back("", 0, 0, n);
	}
	
	private void back(String currentResult, int leftCount, int rightCount, int n) {
		if( leftCount + rightCount == 2*n) {
			System.out.println(currentResult);
			return;
		}
		
		if(leftCount < n) {
			this.back(currentResult+"(", leftCount+1, rightCount, n);
		}
		if(rightCount < leftCount) {
			this.back(currentResult+")", leftCount, rightCount+1, n);
		}
	}
	
	public static void main(String[] args) {
		Solution s = new Solution();
		s.solve(3);
	}

}
