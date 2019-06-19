package algorithm.leetcode;
/*
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

示例 2：
输入: "cbbd"
输出: "bb"

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-palindromic-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PalindromeSubString {
	
	private char[] target;
	
	public PalindromeSubString(String t){
		this.target = t.toCharArray();
	}
	
	public void printLongestPalindromeSubString(){
		String result = "";
		
		int leftCursor = 0;
		
		while(leftCursor < this.target.length -1){
			char currentStartChar = this.target[leftCursor];
			//找以currentStratChar为首的回文
			int i = leftCursor;
			int j = this.target.length - 1;
			while(i != j && this.target[j] != currentStartChar){
				j--;
				if(j-i+1 < result.length()){
					break;
				}
			}
			
			if(i == j || j - i + 1 < result.length()){ 
				leftCursor++;
				continue;
			}else{
				int rightCursor = j;
				while(i <= j && this.target[i] == this.target[j]){
					i++;
					j--;
				}
				if(i>=j){//找到了一个回文串
					for(int k = leftCursor; k <= rightCursor; k++){
						result = result + this.target[k];
					}
				}
			}
			
			leftCursor++;
		}
		
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		PalindromeSubString pss = new PalindromeSubString("Htabcddcbaabcddcbaeffe"); 
		pss.printLongestPalindromeSubString();
	}

}
