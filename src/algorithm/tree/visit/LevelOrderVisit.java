package algorithm.tree.visit;

import java.util.LinkedList;

import algorithm.tree.TreeCompositeNode;
import algorithm.tree.TreeNode;

/*
 * 层序遍历：先打印根节点，然后把它的子结点全部入队；然后打印队列中前部同层次的节点，并把它们的子结点入列
 */
public class LevelOrderVisit implements Visit {

	@Override
	public <T> void visit(TreeNode<T> root) {
		if(root == null) {
			return;
		}
		
		LinkedList<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
		queue.offer(root);
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			while(size > 0){
				TreeNode<T> currentNode = queue.pop();
				System.out.println(currentNode.getData());
				if(!currentNode.isLeaf()) {
					TreeCompositeNode<T> cNode = (TreeCompositeNode<T>) currentNode;
					for(int i = 0 ; i < cNode.childrenCount() ; i++) {
						TreeNode<T> nd = cNode.getChild(i);
						queue.add(nd);
					}
				}
				size --;
			}
		}
	}

	public static void main(String[] args) {
		TreeNode<Integer> cn = Visit.createSampleData();
		LevelOrderVisit pov = new LevelOrderVisit();
		pov.visit(cn);

	}

	
}
