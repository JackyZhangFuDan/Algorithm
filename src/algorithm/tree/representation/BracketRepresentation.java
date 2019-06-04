package algorithm.tree.representation;

import java.util.Stack;

import algorithm.tree.TreeCompositeNode;
import algorithm.tree.TreeLeafNode;
import algorithm.tree.TreeNode;
import algorithm.tree.visit.PreOrderVisit;

public class BracketRepresentation {

	public TreeNode toStandardTree(String expression) {
		if(expression == null || expression.trim().isEmpty()) {
			return null;
		}
		
		char[] characters = expression.toCharArray();
		TreeNode<Character> root = null;
		
		Stack<TreeNode<Character>> stack = new Stack<TreeNode<Character>>();
		
		char previousChar = characters[0];
		TreeCompositeNode parent ;
		
		for(int i = 1; i < characters.length; i++) {
			char currentChar = characters[i];
			TreeNode<Character> node = null;
			
			switch(currentChar) {
			case '(':
				node = new TreeCompositeNode<Character>();
				node.setData(previousChar);
				if(!stack.isEmpty()) {
					parent = (TreeCompositeNode) stack.peek();
					parent.addChild(node);
				}
				stack.push(node);
				break;
			case ',':
				if(previousChar == ' ') break;
				node = new TreeLeafNode<Character>();
				node.setData(previousChar);
				parent = (TreeCompositeNode) stack.peek();
				parent.addChild(node);
				break;
			case ')':
				node = new TreeLeafNode<Character>();
				node.setData(previousChar);
				parent = (TreeCompositeNode) stack.pop();
				parent.addChild(node);
				previousChar = ' ';//一个特殊字符，考虑测试数据中h后的第一个逗号情况
				root = parent;
				break;
			default:
				previousChar = currentChar;
			}
		}
		
		if(root == null) {//括号表达式只有一个字符：例如 ‘A'，代表根，那么它是不会被上面循环处理到的
			root = new TreeLeafNode<Character>();
			root.setData(previousChar);
		}
		
		return root;
	}
	
	public static void main(String[] args) {
		BracketRepresentation br = new BracketRepresentation();
		TreeNode<Character> root = br.toStandardTree("A(b,c(e,g,h),d(x,y(p,q),z)");
		PreOrderVisit pov = new PreOrderVisit();
		pov.visit(root);
	}

}
