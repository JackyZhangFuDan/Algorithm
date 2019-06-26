package algorithm.leetcode.fournumbersum;

/*
给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

注意：
答案中不可以包含重复的四元组。

示例：
给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
满足要求的四元组集合为：
[
  [-1,  0, 0, 1],
  [-2, -1, 1, 2],
  [-2,  0, 0, 2]
]
*/
public class Solution{
	private int[] numbers = null;
	
	public Solution(int[] noms){
		this.numbers = noms;
	}

	public void solve(int target){
		//sort the numbers
		for(int I = 0; I < this.numbers.length; I++){
			int v = this.numbers[I];
			for(int j = I + 1; j < this.numbers.length; j++){
				if(this.numbers[j] < v){
					int tmp = v;
					v = this.numbers[j];
					this.numbers[j] = tmp;
				}
				this.numbers[I] = v;
			}
		}
		
		//add one additional outter loop comparing with 3 numbers sum problem
		Integer previousValue1 = null;
		for(int I = 0; I < this.numbers.length - 3; I++){
			int v1 = this.numbers[I];
			if(previousValue1 != null && previousValue1 == v1) continue; 
			previousValue1 = v1;
			
			Integer previousValue2 = null;
			for(int j = I + 1; j < this.numbers.length - 2; j++){
				int v2 = this.numbers[j];
				if(previousValue2 != null && previousValue2 == v2) continue; 
				previousValue2 = v2;
				
				if(v1 + v2 > target) continue;

				int idx1 = j + 1;
				int idx2 = this.numbers.length - 1;
				while(idx1 < idx2){
					int v3 = this.numbers[idx1];
					int v4 = this.numbers[idx2];
					if(v1 + v2 + v3 + v4 > target){
						idx2--;	
						//注意，这个while其实可有可无，不会影响正确性
						while(idx2 > idx1 && idx2 < this.numbers.length - 1 && this.numbers[idx2] == this.numbers[idx2+1]){
							idx2--;
						}
					}else if(v1 + v2 + v3 + v4 < target){
						idx1++;
						//注意，这个while其实可有可无，不会影响正确性
						while(idx2 > idx1 && this.numbers[idx1] == this.numbers[idx1-1]){
							idx1++;
						};
					}else{
						System.out.println(v1 + "," +v2 + "," + v3 + "," + v4);
						idx1++;
						idx2--;
						while(idx2 > idx1 && this.numbers[idx1] == this.numbers[idx1-1]){
							idx1++;
						};
						while(idx2 > idx1 && idx2 < this.numbers.length - 1 && this.numbers[idx2] == this.numbers[idx2+1]){
							idx2--;
						}
					}
					
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 0, -1, 0, -2, 2}; 
		Solution s = new Solution(nums);
		s.solve(0);
	}

}