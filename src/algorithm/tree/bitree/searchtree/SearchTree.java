package algorithm.tree.bitree.searchtree;

import algorithm.tree.bitree.BiTreeNode;
import algorithm.tree.bitree.visit.MidOrderVisit;

public class SearchTree {
	
	private BiTreeNode<Integer> root;
	
	public BiTreeNode<Integer> search(Integer target) {
		BiTreeNode<Integer> node = root;
		
		while(node != null && !node.getData().equals(target)) {
			
			if( target < node.getData() ) {
				node = node.getLeftNode();
			}else {
				node = node.getRightNode();
			}
		}
		
		return node;
	}
	
	/*
	 * 在查找树中插入一个元素，如果该元素的值在树种已经存在，那么返回false；否则插入并返回true
	 */
	public boolean insert(Integer value) {
		BiTreeNode<Integer> node = root;
		BiTreeNode<Integer> parent = null;
		
		while(node != null && !node.getData().equals(value)) {
			parent = node;
			if(value < node.getData()) {
				node = node.getLeftNode();
			}else {
				node = node.getRightNode();
			}
		}
		
		//如果找到了，那么不必插入，查找树中不允许有重复元素
		if(node != null) {
			return false;
		}
		
		//如果查找树为空，那么直接把新节点作为根节点
		if(parent == null) {
			root = new BiTreeNode<Integer>();
			root.setData(value);
			return true;
		}
		
		node = new BiTreeNode<Integer>();
		node.setData(value);
		
		if(parent.getData() > value) {
			parent.setLeftNode(node);
		}else {
			parent.setRightNode(node);
		}
		
		return true;
	}
	/*
	 * 从查找树中删除一个元素。如果该值不存在那么返回false。如果有该值，那么试图把它的左子节点或右子节点作为它的替代者
	 * 思路：在树中查找删除的目标是基础，要记录该值在树种的节点以及该节点的父节点。
	 */
	public boolean delete(Integer value) {
		BiTreeNode<Integer> node = root;
		BiTreeNode<Integer> parent = null;
		
		while(node != null && !node.getData().equals(value)) {
			parent = node;
			if(value < node.getData()) {
				node = node.getLeftNode();
			}else {
				node = node.getRightNode();
			}
		}
		
		if(node == null) {
			return false;
		}
		
		if(node.getLeftNode() == null && node.getRightNode() != null) { //只有右子节点
			if(parent != null) {
				if(parent.getLeftNode() == node) {
					parent.setLeftNode(node.getRightNode());
				}else {
					parent.setRightNode(node.getRightNode());
				}
			}else {
				//parent是空，说明要删除的是根节点
				this.root = node.getRightNode();
			}
		}else if(node.getLeftNode() != null && node.getRightNode() == null) { //只有左子结点
			if(parent != null) {
				if(parent.getLeftNode() == node) {
					parent.setLeftNode(node.getLeftNode());
				}else {
					parent.setRightNode(node.getLeftNode());
				}
			}else {
				//parent是空，说明要删除的是根节点
				this.root = node.getLeftNode();
			}
		}else {//既有左子结点又有右子节点
			BiTreeNode<Integer> left = node.getLeftNode();
			BiTreeNode<Integer> right = node.getRightNode();
			
			if(parent != null) {
				if(parent.getLeftNode() == node) {
					parent.setLeftNode(left);
				}else {
					parent.setRightNode(left);
				}
			}else {
				this.root = left;
			}
			
			while(left.getRightNode() != null) {
				left = left.getRightNode();
			}
			
			left.setRightNode(right);
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		/*
				2
		1				8
					3		20
								30
		*/
		
		SearchTree tree = new SearchTree();
		tree.root = new BiTreeNode<Integer>();
		tree.root.setData(2);
		
		BiTreeNode<Integer> node = new BiTreeNode<Integer>();
		node.setData(1);
		tree.root.setLeftNode(node);
		
		node = new BiTreeNode<Integer>();
		node.setData(8);
		tree.root.setRightNode(node);
		
		BiTreeNode<Integer> node2 = new BiTreeNode<Integer>();
		node2.setData(3);
		node.setLeftNode(node2);
		
		node2 = new BiTreeNode<Integer>();
		node2.setData(20);
		node.setRightNode(node2);
		
		node = new BiTreeNode<Integer>();
		node.setData(30);
		node2.setRightNode(node);
		
		System.out.println("Middle order visit the original tree: ");
		MidOrderVisit mov = new MidOrderVisit();
		mov.visit(tree.root);
		
		if(tree.insert(6)) {
			System.out.println("Middle order visit the tree after inserting an element: ");
			mov = new MidOrderVisit();
			mov.visit(tree.root);
		}else{
			System.out.println("Insert element to search tree fail.");
		};
		
		if(tree.delete(2)) {
			System.out.println("Middle order visit the tree after deleting an element: ");
			mov = new MidOrderVisit();
			mov.visit(tree.root);
		}else {
			System.out.println("Delete element from search tree fail.");
		}
		
	}
}
