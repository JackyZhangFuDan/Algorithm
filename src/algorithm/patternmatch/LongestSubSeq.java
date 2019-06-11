package algorithm.patternmatch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
题目：给定两个字符串s1和s2，找出s1和s2的最大共有�?串。
共有�?串是指一个字符串z，它既是s1的�?串�?�是s2的�?串，并且没有其它这样的�?串比它更长
*/

public class LongestSubSeq {

	public static void main(String[] args) {
		LongestSubSeq lss = new LongestSubSeq();
		List<String> result = lss.getLongestSubSeq("ABCABCDEACHHHHHD", "HIGKABCDEHABCDHHHHH");
		if(result == null) {
			System.out.println("wrong input");
		}else {
			Iterator<String> it = result.iterator();
			while(it.hasNext()) {
				System.out.println(it.next());
			}
		}
	}
	
	private List<String> getLongestSubSeq(String s1, String s2) {
		if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return null;
		}
		
		ArrayList<String> results = new ArrayList<String>();
		int resultLen = 0;

		for (int i = 0 ; i < s1.length(); i++) {
			if(!s2.contains(s1.substring(0, 1))) {
				continue;
			}
			
			int delta = 0;
			String subStr = s1.substring(i, i + delta);
			
			while(i+ delta <= s1.length()-1 && s2.contains(subStr)) {
				if(resultLen < subStr.length()) {
					results.clear();
					results.add(subStr);
					resultLen = subStr.length();
				}else if (resultLen == subStr.length()) {
					results.add(subStr);
				}
				delta++;
				subStr = s1.substring(i, i + delta);
			}
		}
		return results;
	}
	
	

}
