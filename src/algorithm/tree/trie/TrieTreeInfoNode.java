package algorithm.tree.trie;

public class TrieTreeInfoNode implements TrieTreeNode {
	private char[] info;
	
	@Override
	public boolean isInformation() {
		return true;
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
