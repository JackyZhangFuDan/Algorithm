package algorithm.leetcode.threenumbersum;

public class Solution {
	
	private int[] numbers;
	
	public Solution(int[] num) {
		this.numbers = num;
	}
	
	public void solve() {
		
		//把原始数组分成正数和负数两个子数组
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
		
		//用选择排序，把两个子数组排好顺序；
		for(int i = 0 ; i < pCount; i++) {
			int v = pNums[i];
			for(int j = i + 1; j < pCount; j++) {
				if(v > pNums[j]) {
					int tmp = v;
					v = pNums[j];
					pNums[j] = tmp;
				}
			}
			pNums[i] = v;
		}
		for(int i = 0; i < nCount; i++) {
			int v = nNums[i];
			for(int j = i+1; j < nCount; j++) {
				if(v > nNums[j]) {
					int tmp = v;
					v = nNums[j];
					nNums[j] = tmp;
				}
			}
			nNums[i] = v;
		}
		
		//最后，我们针对这个两个子数组做嵌套循环
		int previousValueP = 0;
		for(int i = 0; i < pCount; i++) {
			
			//如果当前数字和之前一个数字一样，那么可直接跳过这个数字，因为，在处理前面那个相同数字的时候，我们会有比处理当前数字更多的选择，
			//所以处理当前数字只能带来已经得到的结果
			if(previousValueP == pNums[i]) {
				continue;
			}
			
			int p = pNums[i];
			previousValueP = p;
			
			int previousValueN = 0;
			for(int j = 0; j < nCount; j++) {
				//类似正数情况，
				//如果当前数字和之前一个数字一样，那么可直接跳过这个数字，因为，在处理前面那个相同数字的时候，我们会有比处理当前数字更多的选择，
				//所以处理当前数字只能带来已经得到的结果
				if(previousValueN == nNums[j]) {
					continue;
				}
				
				int n = nNums[j];
				previousValueN = n;
				
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
	
	//一种更好的解法，时间复杂度n*n
	public void solve2() {
		//sort the firstly
		for(int i = 0; i < this.numbers.length; i++) {
			int v = this.numbers[i];
			for(int j = i + 1; j < this.numbers.length; j++) {
				if(this.numbers[j] < v) {
					int tmp = v;
					v = this.numbers[j];
					this.numbers[j] = tmp;
				}
			}
			this.numbers[i] = v;
		}
		
		//外层循环从0 到 n-2； 
		int previousV = 0;
		for(int i = 0; i < this.numbers.length - 2; i++) {
			int v1 = this.numbers[i];
			
			if(v1 > 0) return;
			
			if(i != 0 && previousV == v1) {
				continue;
			}
			
			previousV = v1;
			
			int idx1 = i + 1;
			int idx2 = this.numbers.length - 1;
			
			//内层循环每次都是 n-i
			while(idx1 < idx2) {
				if(this.numbers[idx1] + this.numbers[idx2] > -v1) {
					idx2--;
				}else if(this.numbers[idx1] + this.numbers[idx2] < -v1) {
					idx1++;
				}else {
					System.out.println(v1+","+this.numbers[idx1]+","+this.numbers[idx2]);
					do {
						idx1++;
					}while(idx1 < idx2 && this.numbers[idx1] == this.numbers[idx1-1]);
					do {
						idx2--;
					}while(idx1 < idx2 && this.numbers[idx2] == this.numbers[idx2+1]);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
		nums = new int[] {0,0,0,0};
		Solution s = new Solution( nums );
		
		s.solve2();
	}
}
