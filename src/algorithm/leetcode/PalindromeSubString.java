package algorithm.leetcode;

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
