package algorithm.tree.trie;

public class TrieTreeInfoNode implements TrieTreeNode {
	private char[] info;
	
	@Override
	public boolean isInformation() {
		return true;
	}
	
	public void setInformation(String info) {
		if(info == null || info.length() == 0) {
			return;
		}
		this.info = info.toCharArray();
	}
	
	public void setInformation(Character[] info) {
		this.info = String.valueOf(info).toCharArray();
	}
	
	public Character[] getInformation() {
		if(this.info == null || this.info.length <=0) {
			return null;
		}
		
		Character[] result = new Character[this.info.length];
		for(int i = 0; i < this.info.length; i++) {
			result[i] = this.info[i];
		}
		return result;
	}
}
