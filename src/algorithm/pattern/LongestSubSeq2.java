package algorithm.pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LongestSubSeq2 {
	
	/*
	题目：找两个字符串的最大共有�?�?列。
	字符串S1和字符串S2共有�?�?列Z是指：Z中所有字符都在S1与S2中出现，并且Z的字符顺�?与它们出现在S1和S2中时先�?�顺�?一致，但Z�?必是S1和S2的连续�?串。
	找出最大共有�?列就是找出最长的Z 
	 */
	private int recursiveCount = 0;
	
	public static void main(String[] args) {
		LongestSubSeq2 ins = new LongestSubSeq2();
		List<String> longest = ins.getLongestSubSeq("aq12we34r5q5w6er", "a1f23j4q5w5keruq6wder");
		Iterator<String> it = longest.iterator();
		System.out.println("recursive call count for method 1: " + ins.recursiveCount);
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		ins.recursiveCount = 0;
		longest = ins.getLongestSubSeq2("aq12we34r5q5w6er", "a1f23j4q5w5keruq6wder");
		it = longest.iterator();
		System.out.println("recursive call count for method 2: " + ins.recursiveCount);
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	/*
	方法1: 效率差
	关键：
	- 如果S1的最�?�一个字符和S2的最�?�一个字符相等，那么结果就是最�?�一个字符+去除最�?�一个字符的S1和S2的最大共有�?列
	- 如果S1的最�?�一个字符和S2的最�?�一个字符�?相等，那么就过就是longest（S1，S2去除最�?�一个字符）与longest（S1去除最�?�一个字符，S2）中长的那个；
	用递归�?�以解决.
	为什么从最�?�一个字符的相等开始？因为如果最�?�字符相等，那它必定是最长共有�?列的最�?�一个，这点很关键
	*/
	private List<String> getLongestSubSeq(String s1, String s2){
		List<String> results = new ArrayList<String>();
		if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return results;
		}
		
		this.recursiveCount++;
		
		List<String> subResult = null;
		if(s1.charAt(s1.length() - 1) == s2.charAt(s2.length() - 1)) {
			subResult = this.getLongestSubSeq(s1.substring(0, s1.length()-1), s2.substring(0,s2.length()-1));
			
		}else {
			List<String> subResults1 = this.getLongestSubSeq(s1, s2.substring(0, s2.length()-1));
			List<String> subResults2 = this.getLongestSubSeq(s1.substring(0, s1.length()-1), s2);
			if(subResults1.size() == 0) {
				subResult = subResults2;
			}else if(subResults2.size() == 0) {
				subResult = subResults1;
			}else {
				if(subResults1.get(0).length() > subResults2.get(0).length()) {
					subResult = subResults1;
				}else if(subResults1.get(0).length() < subResults2.get(0).length()) {
					subResult = subResults2;
				}else {
					subResult = subResults1;
					subResult.addAll(subResults2);
				}
			}
		}
		
		String subfix = "";
		if(s1.charAt(s1.length() - 1) == s2.charAt(s2.length() - 1)) {
			subfix = s1.substring(s1.length()-1);
		}
		
		if(subResult.size() == 0) {
			results.add(subfix);
		}else {
			Iterator<String> it = subResult.iterator();
			while(it.hasNext()) {
				String subStr = it.next() + subfix;
				results.add(subStr);
			}
		}
		
		return results;
	}
	
	/*
	方法二：效率高
	在方法一的基础上，我们会�?�现字�?列longest（S1去除最�?�一个字符，S2去除最�?�一个字符）会被�?�?计算，�?�常耗时，那么�?��?�?�以把计算结果记下�?��?��?？
	 */
	private Map<String, List<String>> cachedResult = new HashMap();
	private List<String> getLongestSubSeq2(String s1, String s2){
		List<String> results = new ArrayList<String>();
		if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return results;
		}
		
		List<String> resultFromCache = this.cachedResult.get((s1.length() - 1) + "+" + (s2.length()-1));
		if(resultFromCache != null) {
			return resultFromCache;
		}
		
		this.recursiveCount++;
		
		List<String> subResult = null;
		if(s1.charAt(s1.length() - 1) == s2.charAt(s2.length() - 1)) {
			subResult = this.getLongestSubSeq2(s1.substring(0, s1.length()-1), s2.substring(0,s2.length()-1));
			
		}else {
			List<String> subResults1 = this.getLongestSubSeq2(s1, s2.substring(0, s2.length()-1));
			List<String> subResults2 = this.getLongestSubSeq2(s1.substring(0, s1.length()-1), s2);
			if(subResults1.size() == 0) {
				subResult = subResults2;
			}else if(subResults2.size() == 0) {
				subResult = subResults1;
			}else {
				if(subResults1.get(0).length() > subResults2.get(0).length()) {
					subResult = subResults1;
				}else if(subResults1.get(0).length() < subResults2.get(0).length()) {
					subResult = subResults2;
				}else {
					subResult = subResults1;
					subResult.addAll(subResults2);
				}
			}
		}
		
		String subfix = "";
		if(s1.charAt(s1.length() - 1) == s2.charAt(s2.length() - 1)) {
			subfix = s1.substring(s1.length()-1);
		}
		
		if(subResult.size() == 0) {
			results.add(subfix);
		}else {
			Iterator<String> it = subResult.iterator();
			while(it.hasNext()) {
				String subStr = it.next() + subfix;
				results.add(subStr);
			}
		}
		this.cachedResult.put((s1.length() -1) + "+" + (s2.length()-1) , results);
		return results;
	}
}
