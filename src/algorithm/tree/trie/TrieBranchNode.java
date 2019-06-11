package algorithm.tree.trie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrieBranchNode implements TrieNode {
	private char[] info;
	private Map<Character, TrieNode> index;
	
	@Override
	public boolean isInformation() {
		return false;
	}
	
	public TrieBranchNode() {
		this.index = new HashMap<Character, TrieNode>();
		this.index.put('0', null);
		this.index.put('a', null);
		this.index.put('b', null);
		this.index.put('c', null);
		this.index.put('d', null);
		this.index.put('e', null);
		this.index.put('f', null);
		this.index.put('g', null);
		this.index.put('h', null);
		this.index.put('i', null);
		this.index.put('j', null);
		this.index.put('k', null);
		this.index.put('l', null);
		this.index.put('m', null);
		this.index.put('n', null);
		this.index.put('o', null);
		this.index.put('p', null);
		this.index.put('q', null);
		this.index.put('r', null);
		this.index.put('s', null);
		this.index.put('t', null);
		this.index.put('u', null);
		this.index.put('v', null);
		this.index.put('w', null);
		this.index.put('x', null);
		this.index.put('y', null);
		this.index.put('z', null);
		
		Set<Character> keys = this.index.keySet();
		
	};

	public boolean add(Character ch, TrieNode node) {
		this.index.put(ch,node);
		return true;
	}
	
	public TrieNode get(Character ch) {
		return this.index.get(ch);
	}
	
	public List<TrieNode> getSubNodes(){
		List<TrieNode> result = new ArrayList<TrieNode>();
		for(Map.Entry<Character, TrieNode> entry: this.index.entrySet()){
			if(entry.getValue() != null){
				result.add(entry.getValue());
			}
		}
		return result;
	}
	
	@Override
	public void setInformation(char[] info) {
		if(info == null || info.length == 0) return;
		
		this.info = new char[info.length];
		for(int i = 0; i < info.length; i++){
			this.info[i] = info[i];
		}
	}
	
	@Override
	public char[] getInformation() {
		return info;
	}
}
