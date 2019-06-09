package algorithm.tree.trie;

public class TrieTree {
	
	private TrieTreeBranchNode root = null;
	
	public TrieTreeNode find(char[] information) {
		TrieTreeNode node = root;
		int index = 0;
		
		while(node != null && !node.isInformation()) {
			TrieTreeBranchNode nodeTemp = (TrieTreeBranchNode) node;
			
			//这个if特别容易忽略
			if(index == information.length) {
				node = nodeTemp.get('0');
			}else {
				Character c = information[index];
				node = nodeTemp.get(c);
			}
			index++;
		}
		
		if(node != null && node.isInformation()) {
			String info = String.valueOf(((TrieTreeInfoNode) node).getInformation());
			String target = String.valueOf(information);
			if(info.equals(target)) {
				return node;
			}else {
				return null;
			}
		}
		
		return null;
	}
	
	public boolean add(Character[] information) {
		TrieTreeBranchNode parent = null;
		TrieTreeNode node = root;
		int index = 0;
		
		while(node != null && !node.isInformation() && index <= information.length) {
			TrieTreeBranchNode nodeTemp = (TrieTreeBranchNode) node;
			if(index == information.length) {
				node = nodeTemp.get('0');
			}else {
				node = nodeTemp.get(information[index]);
			}
			
			parent = nodeTemp;
			index++;
		}
		
		if(node == null) { //
			if(parent == null) {
				
			}else {
				TrieTreeInfoNode iNode = new TrieTreeInfoNode();
				iNode.setInformation(information);
				if(index == information.length) {
					parent.add('0', iNode);
				}else {
					parent.add(information[--index], iNode);
				}
			}
		}else if(node.isInformation()) {
			TrieTreeInfoNode nodeInfo = (TrieTreeInfoNode) node;
			Character[] existedInformation = nodeInfo.getInformation();
			if(String.valueOf(existedInformation).equals(String.valueOf(information))){
				return false;
			}
			
			
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
