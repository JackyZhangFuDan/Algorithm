package algorithm.tree.trie;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * 一个Trie结构，实现了插入，删除，查找操作
 * 为了处理方便，我们假设空Trie的状态时只有一个根节点，它有27个指针的Branch节点，都指向空。这使得根节点与其它Branch节点不太一样：其它节点都必须有两个及以上子结点，否则要被移除的。
 */
public class TrieTree {
	
	private TrieTreeBranchNode root = new TrieTreeBranchNode();
	
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
			if(parent == null) { //其实由于我们对Trie初始状态的假设，这里永远都不会是空
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
	
	public boolean remove(char[] information) {
		if(information == null || information.length == 0) {
			return false;
		}
		
		TrieTreeNode node = root;
		Stack<TrieTreeBranchNode> parents = new Stack<TrieTreeBranchNode>();
		int index = 0;
		while(node != null && !node.isInformation()) {
			TrieTreeBranchNode nodeTemp = (TrieTreeBranchNode) node;
			if(index == information.length) {
				node = nodeTemp.get('0');
			}else {
				node = nodeTemp.get(information[index]);
			}
			
			parents.push( nodeTemp);
			index++;
		}
		
		if(node == null) {
			return false;
		}
		
		if(! String.valueOf(node.getInformation()).equals(String.valueOf(information))) {
			return false;
		}
		
		Character c = this.calculateLinkedCharacter(parents.peek(), node);
		parents.peek().add(c,null);
		
		TrieTreeNode theOnlyInfoNode = null;
		List<TrieTreeNode> children = parents.peek().getSubNodes();
		
		if(children.size() == 1 && children.get(0).isInformation()) {
			theOnlyInfoNode = children.get(0);
			c = this.calculateLinkedCharacter(parents.peek(), theOnlyInfoNode);
		}else {
			return true;
		}
		
		while(!parents.isEmpty()) {
			parents.peek().add(c, null);
			children = parents.peek().getSubNodes();
			
			if(children.size() >=1) {
				break;
			}else {
				if(parents.peek().getInformation() == null) {
					break;
				}else {
					TrieTreeBranchNode parent = parents.pop();
					c = this.calculateLinkedCharacter(parents.peek(), parent);
				}
			}
		}
		
		TrieTreeBranchNode parent = parents.pop();
		parent.add(c, theOnlyInfoNode);
		
		return true;
	}
	
	private Character calculateLinkedCharacter(TrieTreeBranchNode parent, TrieTreeNode child) {
		String s1 = String.valueOf(child.getInformation());
		String s2 = "";
		if(parent.getInformation() != null) {
			s2 = String.valueOf(parent.getInformation());
		}
		
		if(s1.equals(s2)) {
			return '0';
		}else {
			return s1.toCharArray()[s2.length()];
		}
	}
	
	/*
	 * 打印trie当前形态
	 */
	public void printTree(){
		LinkedList<TrieTreeNode> queue = new LinkedList<TrieTreeNode>();
		queue.offer(this.root);
		int level = 0;
		
		while(!queue.isEmpty()){
			System.out.print("Nodes of level " + level + ": ");
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
			level++;
		}
	}
	
	public static void main(String[] args) {
		TrieTree tt = new TrieTree();
		
		tt.add("computer".toCharArray());
		tt.add("heap".toCharArray());
		tt.add("head".toCharArray());
		tt.add("headache".toCharArray());
		
		System.out.println("After construction:");
		tt.printTree();
		
		tt.remove("headache".toCharArray());
		System.out.println("\nAfter deleting headache:");
		tt.printTree();
		
		tt.remove("heap".toCharArray());
		System.out.println("\nAfter deleting heap:");
		tt.printTree();
		
		tt.remove("computer".toCharArray());
		System.out.println("\nAfter deleting computer:");
		tt.printTree();
		
		tt.remove("head".toCharArray());
		System.out.println("\nAfter deleting head:");
		tt.printTree();
		
		System.out.println("\nAfter adding hello:");
		tt.add("hello".toCharArray());
		tt.printTree();
	}

}
