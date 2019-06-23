package algorithm.leetcode.toromanumber;

public class Solution {
	
	public String integerToRomaNumber(int num) {
		String result = "";
		
		int mCount = num / 1000;
		while(mCount > 0) {
			result = result + "M";
			mCount--;
		}
		num = num % 1000;
		
		if(num >= 900) {
			result = result + "CM";
		}else if(num >= 500 && num <= 599) {
			result = result + "D";
		}else if(num >= 400 && num <= 499) {
			result = result + "CD";
		}else {
			int cCount = num / 100;
			if(cCount >= 5) {
				result = result + "D";
				cCount = cCount - 5;
			}
			while(cCount > 0) {
				result = result + "C";
			}
		}
		num = num % 100;
		
		if(num >= 90) {
			result = result + "XC";
		}else if(num >= 50 && num <= 59) {
			result = result + "L";
		}else if(num >= 40 && num <= 49) {
			result = result + "XL";
		}else {
			int sCount = num / 10;
			if(sCount >= 5) {
				result = result + "L";
				sCount = sCount - 5;
			}
			while(sCount > 0) {
				result = result + "X";
			}
		}
		num = num % 10;
		
		switch(num) {
		case 9:
			result = result + "IX";
			break;
		case 8:
			result = result + "VIII";
			break;
		case 7:
			result = result + "VII";
			break;
		case 6:
			result = result + "VI";
			break;
		case 5:
			result = result + "V";
			break;
		case 4:
			result = result + "IV";
			break;
		case 3:
			result = result + "III";
		case 2:
			result = result + "II";
			break;
		case 1:
			result = result + "I";
		}
		
		return result;
	}
	
	/*
	 * Copy一个别人的巧妙解法
	 */
	public String integerToRomaNumber2(int num) {
		String result = "";
		int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
		String[] representations = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
		for(int i = 0; i < values.length; i++) {
			while(num >= values[i]) {
				result = result + representations[i];
				num = num - values[i];
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		Solution s = new Solution();
		int i = 1994;
		System.out.println("Convert "+i+" to Roma: " + s.integerToRomaNumber(i));

		System.out.println("Convert "+i+" to Roma by method2: " + s.integerToRomaNumber2(i));
	}

}
