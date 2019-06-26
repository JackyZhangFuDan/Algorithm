package algorithm.leetcode.threenumbersumclosest;

/*
Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
Example:
Given array nums = [-1, 2, 1, -4], and target = 1.
The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
*/
public class Solution {
	
	private int[] numbers;
	
	public Solution(int[] nums){
		this.numbers = nums;
	}
	
	public void solve(int target){
		
		//sort
		for(int i = 0; i < this.numbers.length; i++){
			int v = this.numbers[i];
			for(int j = i + 1; j < this.numbers.length; j++){
				if(this.numbers[j] < v){
					int tmp = v;
					v = this.numbers[j];
					this.numbers[j] = tmp;
				}
			}
			this.numbers[i] = v;
		}
		
		//find the solution
		Integer result = null;
		for(int i = 0; i < this.numbers.length - 2; i++ ){
			int v = this.numbers[i];
			
			boolean finished = false;
			int idx1 = i + 1;
			int idx2 = this.numbers.length - 1;
			while(!finished && idx1 < idx2){
				if(result == null || Math.abs(this.numbers[idx1] + this.numbers[idx2] + v - target) < Math.abs(result - target)){
					result = this.numbers[idx1] + this.numbers[idx2] + v;
				}
				if(this.numbers[idx1] + this.numbers[idx2] + v < target){
					idx1++;
				}else if(this.numbers[idx1] + this.numbers[idx2] + v > target){
					idx2--;
				}else{
					result = 0;
					finished = true;
				}
			}
			
			if(finished){
				break;
			}
		}
		
		//print result
		System.out.println("Result is : " + result);
	}
	
	public static void main(String[] args){
		int[] num = {-1,2,1,-4};
		Solution s = new Solution(num);
		
		s.solve(1);
	}
}
