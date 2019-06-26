package algorithm.leetcode.lettercombinationofphonenumber;

import java.util.ArrayList;
import java.util.List;

/*
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 */
public class Solution{
	private char[] charMap = {'-','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		
	public List<String> solve(String s){
		
		List<String> result = new ArrayList<String>();
		if(s == null || s.isEmpty()) return result;
		
		char c = s.charAt(0);
		int num = Integer.parseInt(c+"");
		
		int startIdx = -1;
		int endIdx = -1;
		
		switch (num){
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
				startIdx = (num-2) * 3 + 1;
				endIdx = startIdx + 2;
				break;
			case 7:
				startIdx = 16;
				endIdx = 19;
				break;
			case 8:
				startIdx = 20;
				endIdx = 22;
				break;
			case 9:
				startIdx = 23;
				endIdx = 26;
				break;
		}
		
		List<String> subResult = null;
		if(s.length() > 1){
			subResult = this.solve(s.substring(1));
		}
		
		if(startIdx < 0){
			return subResult;
		}
		
		for(int i = startIdx; i <= endIdx; i++){
			c = this.charMap[i];
			if(subResult != null && !subResult.isEmpty()){
				for(String ss : subResult){
					result.add(c + ss);
				}
			}else{
				result.add(c+"");
			}
		}
		
		return result;
	}
	
	public static void main(String[] args){
		Solution s = new Solution();
		List<String> result = s.solve("2173");
		for(String r : result){
			System.out.println(r);
		};
	}
}
