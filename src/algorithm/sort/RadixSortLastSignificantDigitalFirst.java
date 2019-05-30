package algorithm.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 思路： 先按照最低位把各项分队，然后从代表小的那个队开始把分了队的元素收集起来，
 * 依次完成从低位到高位的分队和收集工作，排序完成
 * 基数排序都是稳定的
 */
public class RadixSortLastSignificantDigitalFirst {
	
	public List<Character[]> sort(List<Character[]> source, int radixLength) {
		
		List<Character[]> source2 = source;
		for(int i = radixLength-1 ; i >= 0; i--) {
			Map<Character, List<Character[]>> links = new HashMap<Character, List<Character[]> >();
			for(int j = 0; j < source2.size(); j++) {
				if(source2.get(j)[i] == ' ') {
					List<Character[]> emptyLink = links.get(' ');
					if(emptyLink == null) {
						emptyLink = new ArrayList<Character[]>();
						links.put(' ', emptyLink);
					}
					emptyLink.add(source2.get(j));
				}else {
					Character c = source2.get(j)[i];
					List<Character[]> theList = links.get(c);
					if(theList == null) {
						theList = new ArrayList<Character[]>();
						links.put(c, theList);
					}
					theList.add(source2.get(j));
				}
			}
			
			List<Character[]> tempList = new ArrayList<Character[]>();
			List<Character[]> theList = links.get(' ');
			if(theList != null && !theList.isEmpty()) {
				tempList.addAll(theList);
			}
			for(int k = 0; k < 26; k++) {
				Character c = (char) ((int)'a' + k);
				theList = links.get(c);
				if(theList != null && !theList.isEmpty()) {
					tempList.addAll(theList);
				}
			}
			
			source2 = tempList;
		}
		
		return source2;
	}
	
	public static void main(String[] args) {
		ArrayList<Character[]> source = new ArrayList<Character[]>();
		source.add(new Character[]{'b','a','h'});
		source.add(new Character[]{' ','z','t'});
		source.add(new Character[]{'a','h','b'});
		source.add(new Character[]{'w','q','y'});
		source.add(new Character[]{'x','t','z'});
		source.add(new Character[]{' ',' ','h'});
		RadixSortLastSignificantDigitalFirst rs = new RadixSortLastSignificantDigitalFirst();
		List<Character[]> result = rs.sort(source, 3);
		for(Character[] item : result) {
			for(Character c : item) {
				System.out.print(c);
			}
			System.out.println();
		}
	}
}
