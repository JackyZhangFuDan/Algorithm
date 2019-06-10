package algorithm.tree.trie;

import java.util.LinkedList;

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
	
	public boolean add(char[] information) {
		TrieTreeBranchNode parent = null;
		TrieTreeNode node = root;
		int index = 0;
		
		while(node != null && !node.isInformation()) {
			TrieTreeBranchNode nodeTemp = (TrieTreeBranchNode) node;
			if(index == information.length) {
				node = nodeTemp.get('0');
			}else {
				node = nodeTemp.get(information[index]);
			}
			
			parent = nodeTemp;
			index++;
		}
		
		TrieTreeInfoNode iNode = new TrieTreeInfoNode();
		iNode.setInformation(information);
		
		if(node == null) { //
			if(parent == null) {
				root = new TrieTreeBranchNode();
				root.add(information[0], iNode);
			}else {
				if(index == information.length) {
					parent.add('0', iNode);
				}else {
					parent.add(information[--index], iNode);
				}
			}
		}else if(node.isInformation()) {
			TrieTreeInfoNode existedNodeInfo = (TrieTreeInfoNode) node;
			char[] existedInformation = existedNodeInfo.getInformation();
	
			//二者相等，证明已经存在
			if(String.valueOf(existedInformation).equals(String.valueOf(information))){
				return false;
			}
			
			//如下情况已经被相等的情况涵盖
			//if(index == information.length){
			//	return true;
			//}
			index--;
			while(index < information.length && index < existedInformation.length && information[index] == existedInformation[index]){
				TrieTreeBranchNode nodeBranch = new TrieTreeBranchNode();
				parent.add(information[index], nodeBranch);
				
				char[] branchInfo = new char[index+1];
				for(int i = 0; i <= index; i++){
					branchInfo[i] = information[i];
				}
				nodeBranch.setInformation(branchInfo);
				
				parent = nodeBranch;
				index++;
			}
			
			if(index == information.length){
				parent.add('0', iNode);
				parent.add(existedInformation[index], existedNodeInfo);
			}else if(index == existedInformation.length){
				parent.add('0', existedNodeInfo);
				parent.add(information[index], iNode);
			}else{
				parent.add(existedInformation[index], existedNodeInfo);
				parent.add(information[index], iNode);
			}
		}
		return true;
	}
	
	/*
	 * 打印trie当前形态
	 */
	public void printTree(){
		LinkedList<TrieTreeNode> queue = new LinkedList<TrieTreeNode>();
		queue.offer(this.root);
		while(!queue.isEmpty()){
			int len = queue.size();
			for(int i = 0; i < len; i++){
				TrieTreeNode node = queue.poll();
				String text ;
				if(node.isInformation()){
					text = "I-Node: " + String.valueOf(node.getInformation()) + "; ";
					System.out.print(text);
				}else{
					if(node.getInformation() == null){
						text = "B-Node: no information ; ";
					}else{
						text = "B-Node: " + String.valueOf(node.getInformation()) + "; ";
					}
					System.out.print(text);
					queue.addAll(((TrieTreeBranchNode) node).getSubNodes());
				}
			};
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		TrieTree tt = new TrieTree();
		
		tt.add("computer".toCharArray());
		tt.add("heap".toCharArray());
		tt.add("head".toCharArray());
		tt.add("headache".toCharArray());
		
		tt.printTree();
	}

}
