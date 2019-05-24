package algorithm.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * 用拉链法解决Hash存储时候的冲�?问题，实现基于它的�?�入，删除和查找
 * 选�?�一个简�?�的Hash函数：除以13�?�余数，并�?�设数组元素为�?写字�?
 */
public class HashSearchLinkedAddress extends SearchBase {
	private int arraySize = 20;
	LinkedList[] linkHeads = new LinkedList[13];
	
	public HashSearchLinkedAddress(Character[] array) {
		super(array);
		for(int i = 0; i < array.length; i++) {
			this.insert(array[i]);
		}
	}

	@Override
	public List<Integer> search(char target) {
		
		List<Integer> result = new ArrayList<Integer>();
		int position = this.hashMethod(target);
		
		if(linkHeads[position] == null) {
			System.out.println("Can't find the element "+target+" in array, delete is cancelled");
			return null;
		}
		
		Integer idx = linkHeads[position].indexOf(target);
		if(idx < 0) {
			return null;
		}else {
			result.add(position);
		}
		
		return result;
	}
	
	public boolean insert(Character c) {
		int position = this.hashMethod(c);
		if(linkHeads[position] == null) {
			linkHeads[position] = new LinkedList<Character>();	
		};
		linkHeads[position].addLast(c);
		
		System.out.println("Inserted " + c + " into index: " + position);
		return true;
	}
	
	public boolean delete(Character c) {
		int position = this.hashMethod(c);
		
		if(linkHeads[position] == null) {
			System.out.println("Can't find the element "+c+" in array, delete is cancelled");
			return false;
		}
		
		int deleteIdx = linkHeads[position].indexOf(c);
		if(deleteIdx < 0) {
			System.out.println("Can't find the element "+c+" in array, delete is cancelled");
			return false;
		}
		
		System.out.println("The element is found, link index " + position +", #"+deleteIdx+" in the link list. Delete it.");
		linkHeads[position].remove(deleteIdx);
		return true;
	}
	
	private int hashMethod(Character c) {
		int diff = c - 'a';
		return diff % 13;
	}
	
	public static void main(String[] args) {
		HashSearchLinkedAddress h = new HashSearchLinkedAddress(new Character[] {'a','d','g','c','k'});
		h.insert('j');
		h.insert('n');
		h.insert('b');
		h.insert('e');
		h.insert('f');
		h.insert('h');
		h.insert('i');
		h.insert('l');
		h.insert('m');
		h.insert('o');
		h.insert('p');
		h.insert('q');
		h.insert('r');
		h.insert('s');
		h.insert('t');
		h.insert('u');
		h.insert('v');
		h.insert('w');
		h.insert('x');
		h.insert('y');
		h.insert('z');
		
		h.insert('a');
		
		h.delete('n');
		
		h.insert('u');
		
		h.printResult(h.search('z'));
		
		h.printResult(h.search('t'));
	}

}
