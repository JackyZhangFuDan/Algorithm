package algorithm.search;

import java.util.ArrayList;
import java.util.List;

/*
 * 用开�?寻�?�法解决Hash表的冲�?问题，实现它的�?�入，删除和查找
 * Hash函数选�?�一个简�?�的�?�余数算法: 除以13�?�余数；并�?�设数组内容为�?写字�?
 */
public class HashSearchOpenAddress extends SearchBase {
	private Character deleteChar = '1';
	private Character emptyChar = '0';
	private int arraySize = 20;
	
	public HashSearchOpenAddress(Character[] array) {
		super(null);
		
		this.array = new Character[this.arraySize];
		for(int i = 0; i < this.arraySize; i++) {
			this.array[i] = this.emptyChar;
		}
		for(int i = 0; i < this.arraySize && i < array.length; i++) {
			Character c = array[i];
			this.insert(c);
		}
	}
	
	@Override
	public List<Integer> search(char target) {
		List<Integer> result = new ArrayList<Integer>();
		
		int position = this.hashMethod(target);
		int count = 1;
		while(count < this.arraySize && this.array[position] != this.emptyChar ) {
			if(this.array[position] == target) {
				result.add(position);
				break;
			}
			position = this.nextHashPosition(position);
			count++;
		}
		
		if(result.size() == 0) {
			return null;
		}else
			return result;
	}
	
	public void insert(Character c) {
		int position = this.hashMethod(c);
		int count = 1;
		while(count < this.arraySize && this.array[position] != this.emptyChar && this.array[position] != this.deleteChar) {
			if(this.array[position] == c) {
				System.out.println("The element already in the array, insert cancel");
				return;
			}
			position = this.nextHashPosition(position);
			count++;
		}
		
		if(this.array[position] != this.emptyChar && this.array[position] != this.deleteChar) {
			System.out.println("The array is full, can't insert more");
			return;
		}
		
		System.out.println("Inserted " + c + " into index: " + position);
		this.array[position] = c;
	}
	
	public void delete(Character c) {
		int position = this.hashMethod(c);
		int count = 1;
		while(count < this.arraySize && 
				this.array[position] != c && 
				this.array[position] != this.emptyChar && 
				this.array[position] != this.deleteChar) {
			
			position = this.nextHashPosition(position);
			count++;
		}
		
		if(this.array[position] == c) {
			System.out.println("The element is found, index " + position +", delete it.");
			this.array[position] = this.deleteChar;
		}else {
			System.out.println("Can't find the element "+c+" in array, delete is cancelled");
		}
		return;
	}
	
	private int hashMethod(Character c) {
		int diff = c - 'a';
		return diff % 13;
	}
	
	private int nextHashPosition(int position) {
		return (position + 1) % this.arraySize;
	}
	
	public static void main(String[] args) {
		HashSearchOpenAddress h = new HashSearchOpenAddress(new Character[] {'a','d','g','c','k'});
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
