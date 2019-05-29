package algorithm.sort;

import java.util.ArrayList;
import java.util.List;

/*
 * 假设待排序数组的每一个元素都是0～9数字构成的字符串（用数组表示），字符串数组长度一样，如果高位没有数字则该位为空字符
 * 思路：先按照高位排序，然后把高位相等的元素放一块进行递归排序
 * Java里面比较繁琐的是没有指针，所以空间浪费会有一些
 */
public class RadixSort {
	
	public List<Character[]> sort(List<Character[]> source, int radixLength, int currentPosition){
		List<Character[]> result = null;
		
		if(radixLength <= currentPosition) {
			return source;
		}
		
		int comparePosition = currentPosition - 1;
		
		//按当前位进行排序，由小到大
		for(int i = 0; i < source.size(); i++) {
			int valueAtPosition = 0;
			if(source.get(i)[comparePosition] != ' ') {
				valueAtPosition = Integer.valueOf(source.get(i)[comparePosition].toString());
			}
			for(int j = i + 1; j < source.size(); j++) {
				int vap = 0;
				if(source.get(j)[comparePosition] != ' ') {
					vap = Integer.valueOf(source.get(j)[comparePosition].toString());
				}
				if(valueAtPosition > vap) {
					Character[] tempi = source.get(i).clone();
					Character[] tempj = source.get(j).clone();
					source.remove(i);
					source.add(i, tempj);
					source.remove(j);
					source.add(j, tempi);
					valueAtPosition = vap;
				}
			}
		}
		
		//当前位一致的放一组，进行递归排序
		Character cap = source.get(0)[comparePosition];
		List<Character[]> temp = new ArrayList<Character[]>();
		temp.add(source.get(0));
		
		for(int i = 1; i < source.size(); i++) {
			if(cap == source.get(i)[comparePosition]) {
				temp.add(source.get(i));
			}else {
				List<Character[]> r = this.sort(temp, radixLength, currentPosition+1);
				if(result == null) {
					result = r;
				}else {
					result.addAll(r);
				}
				cap = source.get(i)[comparePosition];
				temp = new ArrayList<Character[]>();
				temp.add(source.get(i));
			}
		}
		
		List<Character[]> r = this.sort(temp, radixLength,currentPosition+1);
		if(result == null) {
			result = r;
		}else {
			result.addAll(r);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		ArrayList<Character[]> source = new ArrayList<Character[]>();
		source.add(new Character[]{'2','8','3'});
		source.add(new Character[]{' ','9','3'});
		source.add(new Character[]{'1','8','2'});
		source.add(new Character[]{'9','6','3'});
		source.add(new Character[]{'2','8','0'});
		source.add(new Character[]{' ',' ','9'});
		RadixSort rs = new RadixSort();
		List<Character[]> result = rs.sort(source, 3, 1);
		for(Character[] item : result) {
			for(Character c : item) {
				System.out.print(c);
			}
			System.out.println();
		}
	}

}
