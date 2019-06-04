package algorithm.tree.visit;

import java.util.Stack;

import algorithm.tree.TreeCompositeNode;
import algorithm.tree.TreeNode;

/*
 * 遍历树：先子结点从左到右，再访问自己。对应于二叉树的后序遍历
 * 思路：构造两个栈，第一个栈用于存放结点，第二个栈用于存放第一个栈中各个结点的层数
 * 初始时栈中只有一个root结点；后续进栈规则：如果栈顶结点有子结点那么依次从右到左进栈
 */
public class PostOrderVisit implements Visit {

	@Override
	public <T> void visit(TreeNode<T> root) {
		if(root == null){
			return;
		}
		
		Stack<TreeNode<T>> nodeStack = new Stack<TreeNode<T>>();
		Stack<Integer> nodeLevelStack = new Stack<Integer>();
		
		nodeStack.push(root);
		nodeLevelStack.push(1);
		int lastVisitedNodeLevel = 1;
		
		while(!nodeStack.isEmpty()){
			
			int topNodeLevel = nodeLevelStack.peek();
			TreeNode<T> topNode = null;
			
			//上一次遍历的结点是当前栈顶结点的子节点，根据进栈规则那么说明栈顶结点子节点访问完毕，访问栈顶并出栈
			if(lastVisitedNodeLevel != topNodeLevel){
				topNode = nodeStack.pop();
				System.out.println(topNode.getData());
				lastVisitedNodeLevel = nodeLevelStack.pop();
				continue;
			}

			topNode = nodeStack.peek();
			
			if(topNode.isLeaf()){
				//栈顶结点是叶子结点
				topNode = nodeStack.pop();
				System.out.println(topNode.getData());
				lastVisitedNodeLevel = nodeLevelStack.pop();
				continue;
			}else{
				//栈顶结点是非叶子结点
				TreeCompositeNode<T> topNode2 = (TreeCompositeNode<T>) topNode;
				for(int i = topNode2.childrenCount() - 1; i >= 0; i--){
					nodeStack.push(topNode2.getChild(i));
					nodeLevelStack.push(topNodeLevel + 1);
				}
				lastVisitedNodeLevel = nodeLevelStack.peek();
			}
		}
	}
	
	public static void main(String[] args) {
		PostOrderVisit pov = new PostOrderVisit();
		pov.visit(Visit.createSampleData());
	}

}
