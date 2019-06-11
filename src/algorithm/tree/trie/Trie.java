package algorithm.tree.trie;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
 * Trie结构，包含了查找，删除和加入操作
 * 
 */
public class Trie {
	
	private TrieNode root = null;
	
	public boolean find(char[] information){
		
		if(information == null || information.length == 0){
			return false;
		}
		
		TrieNode node = this.root;
		Character c = null;
		int index = 0;
		
		while(node != null && !node.isInformation()){
			if(index == information.length){
				c = '0';
			}else{
				c = information[index];
			}
			
			node = ((TrieBranchNode) node).get(c);
			index++;
		}
		
		if(node == null){
			return false;
		}
		
		if( String.valueOf(information).equals(String.valueOf(node.getInformation()))){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean add(char[] information){
		if(information == null || information.length ==0){
			return false;
		}
		
		//定位插入点
		TrieBranchNode parent = null;
		TrieNode node = root;
		int index = 0;
		Character c = null;
		
		while(node != null && !node.isInformation()){	
			if(index == information.length){
				c = '0';
			}else{
				c = information[index];
			}
			
			parent = (TrieBranchNode)node;
			node = ((TrieBranchNode)node).get(c);
			index++;
		}
		
		TrieInfoNode newNode = new TrieInfoNode();
		newNode.setInformation(information);
		
		//开始插入新结点
		if(node == null){  //直接找到了插入点
			if(parent == null){	
				this.root = newNode;
			}else{
				parent.add(c, newNode);
			}
			return true;
		}else{  //找到了一个信息结点，这时候就涉及Branch结点的创建
			if( String.valueOf(information).equals( String.valueOf(node.getInformation()))){
				return false;
			}else{
				if(parent == null){
					TrieBranchNode newBranchNode = new TrieBranchNode();
					newBranchNode.add(information[0], newNode);
					newBranchNode.add(this.root.getInformation()[0], this.root);
					this.root = newBranchNode;
				}else{
					/*
					 * 下面这部分最搞脑子，思路要清晰
					 * 比较待插入结点和被找到的信息结点，它们之间每个没有Branch结点的共同字符都应该创建一个Branch
					*/
					TrieInfoNode existedNodeInfo = (TrieInfoNode) node;
					char[] existedInformation = existedNodeInfo.getInformation();
					index--;
					while(index < information.length && index < existedInformation.length && information[index] == existedInformation[index]){
						TrieBranchNode newBranch = new TrieBranchNode();
						parent.add(information[index], newBranch);
						
						char[] branchInfo = new char[index+1];
						for(int i = 0; i <= index; i++){
							branchInfo[i] = information[i];
						}
						newBranch.setInformation(branchInfo);
						
						parent = newBranch;
						index++;
					}
					
					if(index == information.length){
						parent.add('0', newNode);
						parent.add(existedInformation[index], existedNodeInfo);
					}else if(index == existedInformation.length){
						parent.add('0', existedNodeInfo);
						parent.add(information[index], newNode);
					}else{
						parent.add(existedInformation[index], existedNodeInfo);
						parent.add(information[index], newNode);
					}
				}
			}
			return true;
		}
	}
	
	public boolean remove(char[] information){
		if(information == null || information.length == 0){
			return false;
		}
		
		//首先，我们定位被删除结点在Trie内的位置，这很像查找
		Stack<TrieBranchNode> parents = new Stack<TrieBranchNode>();
		TrieNode node = this.root;
		int index = 0;
		Character c = null;
		
		while(node != null && !node.isInformation()){
			TrieBranchNode nodeB = (TrieBranchNode) node;
			if(index == information.length){
				c = '0';
			}else{
				c = information[index];
			}
			
			node = nodeB.get(c);
			parents.push(nodeB);
			index++;
		}
		
		if(node == null || !String.valueOf(node.getInformation()).equals(String.valueOf(information))){
			return false;
		}
		
		if(parents.isEmpty()){
			this.root = null;
			return true;
		}
		
		//下面我们找出被删信息结点的兄弟信息结点 - 如果没有兄弟信息结点（那必有Branch兄弟结点）那删除就结束了
		TrieBranchNode parent = null;
		TrieNode theLeftChild = null;
		
		parent = parents.pop();
		parent.add(c, null);
		if(parent.getInformation() != null){
			c = parent.getInformation()[parent.getInformation().length-1];
		}
		
		List<TrieNode> children = parent.getSubNodes();
		if(children.size() > 1 || !children.get(0).isInformation()){
			return true;
		}
		theLeftChild = children.get(0);
		
		//被删结点右兄弟信息结点，并且这个兄弟信息结点已经是父Branch结点的唯一结点了，那么就需要考虑删除长辈Branch结点的可能性了
		while(!parents.isEmpty()){
			parent = parents.peek();
			parent.add(c, null);
			
			children = parent.getSubNodes();
			if(children.size() > 0){
				break;
			}
			
			parents.pop();
			if(parent.getInformation() == null){
				//the root node is poped, so nothing to do, the loop will end
			}else{
				c = parent.getInformation()[parent.getInformation().length-1];
			}
		}
		
		if(parents.isEmpty()){
			this.root = theLeftChild;
			return true;
		}
		
		parent = parents.pop();
		parent.add(c, theLeftChild);
		
		return true ;
	}
	
	/*
	 * 打印trie当前形态
	 */
	public void printTree(){
		
		if(this.root == null){
			System.out.println("Empty trie");
			return;
		}
		
		LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
		queue.offer(this.root);
		int level = 0;
		
		while(!queue.isEmpty()){
			System.out.print("Nodes of level " + level + ": ");
			int len = queue.size();
			for(int i = 0; i < len; i++){
				TrieNode node = queue.poll();
				String text ;
				if(node.isInformation()){
					text = "I-Node(" + String.valueOf(node.getInformation()) + "); ";
					System.out.print(text);
				}else{
					if(node.getInformation() == null){
						text = "B-Node(no information); ";
					}else{
						text = "B-Node(" + String.valueOf(node.getInformation()) + "); ";
					}
					System.out.print(text);
					queue.addAll(((TrieBranchNode) node).getSubNodes());
				}
			};
			System.out.println();
			level++;
		}
	}
	
	public static void main(String[] args) {
		Trie tt = new Trie();
		
		tt.add("computer".toCharArray());
		System.out.println("After add computer:");
		tt.printTree();
		
		tt.add("heap".toCharArray());
		System.out.println("\nAfter add heap:");
		tt.printTree();
		
		tt.add("head".toCharArray());
		System.out.println("\nAfter add head:");
		tt.printTree();
		
		tt.add("headache".toCharArray());
		System.out.println("\nAfter add headache:");
		tt.printTree();
		
		if(tt.find("he".toCharArray())){
			System.out.println("\nword 'he' is find successully");
		}else{
			System.out.println("\nword 'he' isn't found");
		}
		
		if(tt.find("head".toCharArray())){
			System.out.println("\nword 'head' is find successully");
		}else{
			System.out.println("\nword 'head' isn't found");
		}
		
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
