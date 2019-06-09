package algorithm.tree.trie;

import java.util.HashMap;
import java.util.Map;

public class TrieTreeBranchNode implements TrieTreeNode {
	
	private Map<Character, TrieTreeNode> index;
	
	@Override
	public boolean isInformation() {
		return false;
	}
	
	public TrieTreeBranchNode() {
		this.index = new HashMap<Character, TrieTreeNode>();
		this.index.compute('0', null);
		this.index.compute('a', null);
		this.index.compute('b', null);
		this.index.compute('c', null);
		this.index.compute('d', null);
		this.index.compute('e', null);
		this.index.compute('f', null);
		this.index.compute('g', null);
		this.index.compute('h', null);
		this.index.compute('i', null);
		this.index.compute('j', null);
		this.index.compute('k', null);
		this.index.compute('l', null);
		this.index.compute('m', null);
		this.index.compute('n', null);
		this.index.compute('o', null);
		this.index.compute('p', null);
		this.index.compute('q', null);
		this.index.compute('r', null);
		this.index.compute('s', null);
		this.index.compute('t', null);
		this.index.compute('u', null);
		this.index.compute('v', null);
		this.index.compute('w', null);
		this.index.compute('x', null);
		this.index.compute('y', null);
		this.index.compute('z', null);
		
	};

	public boolean add(Character ch, TrieTreeNode node) {
		if(this.index.get(ch) != null) {
			return false;
		}
		this.index.put(ch,node);
		return true;
	}
	
	public TrieTreeNode get(Character ch) {
		return this.index.get(ch);
	}
}
