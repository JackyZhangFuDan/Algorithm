package algorithm.tree.bitree.visit;

import java.util.Stack;

import algorithm.tree.bitree.BiTreeNode;

/*
 * 二叉树的前序遍历
 * 思路，把根节点入栈，然后对栈中元素循环直至为空：
 * 拿出栈顶节点，打印；如果栈顶节点有子结点，那么把它们从右到左的顺序进栈
 */
public class PreOrderVisit {
	
	public <T> void visit(BiTreeNode<T> root) {
		if(root == null) {
			return;
		}
		
		Stack<BiTreeNode<T>> stack = new Stack<BiTreeNode<T>>();
		stack.push(root);
		
		while(!stack.isEmpty()) {
			BiTreeNode<T> node = stack.pop();
			System.out.println(node);
			
			if(node.getRightNode() != null) {
				stack.push(node.getRightNode());
			}
			if(node.getLeftNode() != null) {
				stack.push(node.getLeftNode());
			}
		}
	}
	
	public static void main(String[] args) {
		/*
				A
		a				b
					c		d
								e
		*/
		BiTreeNode<Character> root = new BiTreeNode<Character>();
		root.setData('A');
		
		BiTreeNode<Character> node = new BiTreeNode<Character>();
		node.setData('a');
		root.setLeftNode(node);
		
		node = new BiTreeNode<Character>();
		node.setData('b');
		root.setRightNode(node);
		
		BiTreeNode<Character> node2 = new BiTreeNode<Character>();
		node2.setData('c');
		node.setLeftNode(node2);
		
		node2 = new BiTreeNode<Character>();
		node2.setData('d');
		node.setRightNode(node2);
		
		node = new BiTreeNode<Character>();
		node.setData('e');
		node2.setRightNode(node);
		
		PreOrderVisit pov = new PreOrderVisit();
		pov.visit(root);
	}

}
