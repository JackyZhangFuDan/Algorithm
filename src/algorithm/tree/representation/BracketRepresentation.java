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
		if(characters.length == 1) {
			root = new TreeLeafNode<Character>();
			root.setData(characters[0]);
			return root;
		}else {
			root = new TreeCompositeNode<Character>();
			root.setData(characters[0]);
		}
		
		Stack<TreeNode<Character>> stack = new Stack<TreeNode<Character>>();
		TreeNode<Character> currentNode = root ;
		
		for(int i = 1; i < characters.length; i++) {
			char currentChar = characters[i];
			TreeNode<Character> node = null;
			
			switch(currentChar) {
			case '(':
				stack.push(currentNode);
				break;
			case ',':
				TreeCompositeNode<Character> parent = (TreeCompositeNode) stack.peek();
				parent.addChild(currentNode);
				break;
			case ')':
				parent = (TreeCompositeNode) stack.pop();
				parent.addChild(currentNode);
				currentNode = parent;
				break;
			default:
				if(i == characters.length - 1 || characters[i+1] != '(') {
					currentNode = new TreeLeafNode<Character>();
				}else {
					currentNode = new TreeCompositeNode<Character>();
				}
				currentNode.setData(characters[i]);
				break;
			}
		}
		
		return root;
	}
	
	public static void main(String[] args) {
		BracketRepresentation br = new BracketRepresentation();
		String expression = "A(b,c(e,g,h),d(x,y(p,q),z))";
		System.out.println("Bracket expression: " );
		System.out.println(expression);
		
		TreeNode<Character> root = br.toStandardTree(expression);
		PreOrderVisit pov = new PreOrderVisit();
		
		System.out.println("Pre ordered printed nodes of the constructed tree:");
		pov.visit(root);
	}
}
