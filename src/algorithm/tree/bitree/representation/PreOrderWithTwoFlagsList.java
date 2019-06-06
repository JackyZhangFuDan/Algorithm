package algorithm.tree.bitree.representation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import algorithm.tree.bitree.BiTreeNode;
import algorithm.tree.bitree.visit.PreOrderVisit;

/*
 * 把线性存储的二叉树转化成标准二叉树
 * 线性存储方式：目标二叉树的所有节点以前序方式依次存放在一个列表中，列表项除了数据位外，还包含指示有无左节点的flag和指示是否有无有节点的flag
 * 思路：关键点是如果列表中有一项说自己没有左节点，那么这一项下面的一项一定是列表中前面的指示自己有右子节点的项的右节点，并且这个项是符合条件的节点中离当前项最近的那一个
 * 处理时，假设列表有n项，那么做n次循环顺次处理列表中的项，每次循环先把当前项的值放入之前开好的一个树节点中，然后新开一个树节点作为下次循环承载数据所用，
 * 之后根据上面规则决定出下个项是当前项的左节点还是之前某个节点的右节点，根据决定结果把新开树节点作为某已知节点的左或右子结点
 */
public class PreOrderWithTwoFlagsList {
	
	public BiTreeNode<Character> toStandardTree(List<Entry> preOrderNodes) {
		BiTreeNode<Character> root = null;
		if(preOrderNodes == null || preOrderNodes.isEmpty()) {
			return root;
		}
		
		root = new BiTreeNode<Character>();
		
		BiTreeNode<Character> currentNode = root;
		Stack<BiTreeNode<Character>> stack = new Stack<BiTreeNode<Character>>();
		
		for(int i = 0; i < preOrderNodes.size(); i++) {
			Entry entry = preOrderNodes.get(i);
			currentNode.setData(entry.data);
			
			if(entry.hasRightNode) {
				stack.push(currentNode);
			}

			BiTreeNode<Character> nextNode = new BiTreeNode<Character>();
			if(entry.hasLeftNode) {
				currentNode.setLeftNode(nextNode);
			}else{
				if(stack.isEmpty()) {
					continue;
				}
				BiTreeNode<Character> tempNode = stack.pop();
				tempNode.setRightNode(nextNode);
			}
			currentNode = nextNode;
		}
		
		currentNode.setData(preOrderNodes.get(preOrderNodes.size()-1).data);
		
		return root;
	}
	
	public static void main(String[] args) {
		List<Entry> preOrderList = new ArrayList<Entry>();
		
		Entry entry = new Entry();
		entry.data = 'A';
		entry.hasLeftNode = true;
		entry.hasRightNode = true;
		preOrderList.add(entry);
		
		entry = new Entry();
		entry.data = 'B';
		entry.hasLeftNode = false;
		entry.hasRightNode = true;
		preOrderList.add(entry);
		
		entry = new Entry();
		entry.data = 'D';
		entry.hasLeftNode = true;
		entry.hasRightNode = false;
		preOrderList.add(entry);
		
		entry = new Entry();
		entry.data = 'F';
		entry.hasLeftNode = false;
		entry.hasRightNode = false;
		preOrderList.add(entry);
		
		entry = new Entry();
		entry.data = 'C';
		entry.hasLeftNode = true;
		entry.hasRightNode = false;
		preOrderList.add(entry);
		
		entry = new Entry();
		entry.data = 'E';
		entry.hasLeftNode = true;
		entry.hasRightNode = false;
		preOrderList.add(entry);
		
		entry = new Entry();
		entry.data = 'G';
		entry.hasLeftNode = false;
		entry.hasRightNode = true;
		preOrderList.add(entry);
		
		entry = new Entry();
		entry.data = 'H';
		entry.hasLeftNode = false;
		entry.hasRightNode = false;
		preOrderList.add(entry);
		
		PreOrderWithTwoFlagsList fwtfl = new PreOrderWithTwoFlagsList();
		BiTreeNode root = fwtfl.toStandardTree(preOrderList);
		
		System.out.println("Pre order node list of the got standard tree:");
		PreOrderVisit pov = new PreOrderVisit();
		pov.visit(root);
	}
	
	private static class Entry{
		public char data;
		public boolean hasLeftNode;
		public boolean hasRightNode;
	}
}
