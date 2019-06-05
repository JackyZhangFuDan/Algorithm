package algorithm.tree.bitree.visit;

import java.util.LinkedList;

import algorithm.tree.bitree.BiTreeNode;

/*
 * 二叉树的层序遍历
 * 思路：先把根节点进队，然后对队列中节点做如下循环直至队列为空：
 * 看一下当前队列多长，设为n，然后对队列做n次如下操作：队首出队，打印；拿出其所有子结点以从右向左的顺序进队。
 */
public class LevelOrderVisit {
	
	public <T> void visit(BiTreeNode<T> root){
		if(root == null) {
			return;
		}
		
		LinkedList<BiTreeNode<T>> queue = new LinkedList<BiTreeNode<T>>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			int length = queue.size();
			for(int i = 0; i < length; i++) {
				BiTreeNode<T> head = queue.poll();
				System.out.println(head);
				if(head.getLeftNode() != null) {
					queue.offer(head.getLeftNode());
				}
				if(head.getRightNode() != null) {
					queue.offer(head.getRightNode());
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
		
		LevelOrderVisit lov = new LevelOrderVisit();
		lov.visit(root);

	}

}
