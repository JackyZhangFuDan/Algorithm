package algorithm.leetcode.threenumbersum;

public class Solution {
	
	private int[] numbers;
	
	public Solution(int[] num) {
		this.numbers = num;
	}
	
	public void solve() {
		int pCount = 0;
		int nCount = 0;
		int zCount = 0;
		
		for(int i : this.numbers) {
			if(i > 0) {
				pCount++;
			}else if(i < 0) {
				nCount++;
			}else {
				zCount++;
			}
		}
		
		int[] pNums = new int[pCount];
		int[] nNums = new int[nCount];
		int pIdx = 0;
		int nIdx = 0;
		
		for(int i : this.numbers) {
			if(i > 0) {
				pNums[pIdx++] = i;
			}else if(i < 0) {
				nNums[nIdx++] = i;
			}
		}
		
		for(int i = 0; i < pCount; i++) {
			int p = pNums[i];
			
			boolean continueTrue = false;
			for(int j = 0; j < i; j++) {
				if(p == pNums[j]) {
					continueTrue = true;
				}
			}
			
			if(continueTrue) {
				continue;
			}
			
			for(int j = 0; j < nCount; j++) {
				int n = nNums[j];
				if(p+n == 0 ) {
					if(zCount > 0) {
						System.out.println(p + ",0," + n);
					}else {
						continue;
					}
				}else if(p+n < 0) {
					for(int k = i + 1; k < pCount; k++) {
						if(pNums[k] == -(p+n)) {
							System.out.println(p+","+pNums[k]+","+n);
						}
					}
				}else if(p+n > 0) {
					for(int k = j + 1; k < nCount; k++) {
						if(nNums[k] == -(p+n)) {
							System.out.println(p+","+nNums[k]+","+n);
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
		Solution s = new Solution( nums );
		
		s.solve();
	}
}
