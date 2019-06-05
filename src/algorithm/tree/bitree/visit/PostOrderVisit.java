package algorithm.tree.bitree.visit;

import java.util.Stack;

import algorithm.tree.bitree.BiTreeNode;

/*
 * 二叉树后序遍历
 * 思路：先把根节点放入栈，然后对栈中节点做如下循环直至栈空：
 * 1.如果栈顶节点有子结点，那么把子结点从右向左进栈；
 * 2. 如果栈顶节点没有子结点，栈顶节点出栈，打印；
 * 2.1 如果它的层号比新的栈顶结点大，说明新栈顶节点所有子结点打印完毕，那么栈顶节点可以出栈，打印它。。。循环直至找到新栈顶节点层号等于刚刚出栈的栈顶或栈空
 * 
 * 由于我的树节点上不带有层次号，所以用另外一个栈来存放节点栈中节点的层号
 */
public class PostOrderVisit {
	
	public <T> void visit(BiTreeNode<T> root) {
		if(root == null) {
			return;
		}
		
		Stack<BiTreeNode<T>> stack = new Stack<BiTreeNode<T>>();
		Stack<Integer> levelStack = new Stack<Integer>();
		stack.push(root);
		levelStack.push(1);
		
		while(!stack.isEmpty()) {
			BiTreeNode<T> topNode = stack.peek();
			Integer topNodeLevel = levelStack.peek();
			
			if(!topNode.isLeaf()) {
				if(topNode.getRightNode() != null) {
					stack.push(topNode.getRightNode());
					levelStack.push(topNodeLevel + 1);
				}
				if(topNode.getLeftNode() != null) {
					stack.push(topNode.getLeftNode());
					levelStack.push(topNodeLevel + 1);
				}
			}else {
				topNode = stack.pop();
				topNodeLevel = levelStack.pop();
				System.out.println(topNode);
				
				if(stack.isEmpty()) break;
				
				BiTreeNode<T> newTopNode = stack.peek();
				Integer newTopNodeLevel = levelStack.peek();
				while(topNodeLevel > newTopNodeLevel) {
					topNode = stack.pop();
					topNodeLevel = levelStack.pop();
					System.out.println(topNode);
					
					if(stack.isEmpty()) break;
					
					newTopNode = stack.peek();
					newTopNodeLevel = levelStack.peek();
				}
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
		
		PostOrderVisit pov = new PostOrderVisit();
		pov.visit(root);

	}

}
