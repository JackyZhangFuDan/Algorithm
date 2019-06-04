package algorithm.tree.visit;

import java.util.Stack;

import algorithm.tree.TreeCompositeNode;
import algorithm.tree.TreeNode;

/*
 * 前序遍历：先打印当前节点，然后把其子结点入栈，右子节点先入，左子结点最后入
 */
public class PreOrderVisit implements Visit {

	@Override
	public <T> void visit(TreeNode<T> root) {
		if(root == null) {
			return ;
		}
		
		Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();
		stack.push(root);
		
		while(!stack.isEmpty()) {
			TreeNode<T> currentNode = stack.pop();
			System.out.println(currentNode.getData());
			if(!currentNode.isLeaf()) {
				TreeCompositeNode<T> cNode = (TreeCompositeNode<T>) currentNode;
				for(int i = cNode.childrenCount() - 1; i >= 0; i--) {
					if(cNode.getChild(i) != null) {
						stack.push(cNode.getChild(i));
					}
				}
			}
		};
	}
	
	public static void main(String[] args) {
		TreeNode<Integer> cn = Visit.createSampleData();
		PreOrderVisit pov = new PreOrderVisit();
		pov.visit(cn);
	}
}
