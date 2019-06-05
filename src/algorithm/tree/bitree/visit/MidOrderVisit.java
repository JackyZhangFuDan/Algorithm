package algorithm.tree.bitree.visit;

import java.util.Stack;

import algorithm.tree.bitree.BiTreeNode;

/*
 * 二叉树中序遍历
 * 思路：先把从树根出发的最左边树枝放入栈（这条树枝包含了根自己），然后对栈中节点做如下循环直至为空：
 * 取出栈顶，直接打印；看栈顶是否有右子节点，有，那么重复对根节点的处理方式：把最左边一条树枝入栈（包含了自身）
 */
public class MidOrderVisit {
	
	public <T> void visit(BiTreeNode<T> root) {
		if(root == null) {
			return;
		}
		
		Stack<BiTreeNode<T>> stack = new Stack<BiTreeNode<T>>();
		BiTreeNode<T> currentNode = root;
		
		while(currentNode.getLeftNode() != null) {
			stack.push(currentNode);
			currentNode = currentNode.getLeftNode();
		}
		stack.add(currentNode);
		
		while(!stack.isEmpty()) {
			currentNode = stack.pop();
			System.out.println(currentNode);
			
			if(currentNode.getRightNode() != null) {
				currentNode = currentNode.getRightNode();
				while(currentNode.getLeftNode() != null) {
					stack.push(currentNode);
					currentNode = currentNode.getLeftNode();
				}
				stack.push(currentNode);
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
		
		MidOrderVisit mov = new MidOrderVisit();
		mov.visit(root);
	}

}
