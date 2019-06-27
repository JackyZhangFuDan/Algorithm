package algorithm.leetcode.bracketgeneration;

import java.util.Stack;

/*
 * 这里我试图用解答树的方式来解决这个问题。
 * 并且形成解答树的一般解法
 */
public class Solution2 {
	
	public void solve(int n) {
		Stack<Node> stack = new Stack<Node>();
		Stack<Integer> visitFlag = new Stack<Integer>();
		
		Node node = new Node();
		node.value = '(';
		node.existedL = 1;
		
		stack.push(node);
		visitFlag.push(0);
		
		while(!stack.isEmpty()) {
			node = stack.peek();
			int vFlag = visitFlag.peek();
			boolean needPop = false;
			
			if(vFlag == 0) {
				if(node.existedL == n && node.existedR == n) {
					this.printStack(stack);
					needPop = true;
				}else if(node.existedL < n) {
					Node node2 = new Node();
					node2.value = '(';
					node2.existedL = node.existedL + 1;
					node2.existedR = node.existedR;
					visitFlag.pop();
					visitFlag.push(1);
					visitFlag.push(0);
				}else if(node.existedL > node.existedR ) {
					Node node2 = new Node();
					node2.value = ')';
					node2.existedL = node.existedL;
					node2.existedR = node.existedR + 1;
					visitFlag.pop();
					visitFlag.push(2);
					visitFlag.push(0);
				}
			}else if(vFlag == 1) {
				if(node.existedL < node.existedR) {
					Node node2 = new Node();
					node2.value = ')';
					node2.existedL = node.existedL;
					node2.existedR = node.existedR + 1;
					visitFlag.pop();
					visitFlag.push(2);
					visitFlag.push(0);
				}
			}else if(vFlag ==2) {
				needPop = true;
			}
			
			if(needPop) {
				stack.pop();
				visitFlag.pop();
			}
		}
	}
	
	private void printStack(Stack<Node> s) {
		for(Node node : s) {
			System.out.println("One Result is: ");
			System.out.print(node.value);
		}
	}
	
	public static void main(String[] args) {
		Solution s = new Solution();
		s.solve(4);
	}
	
	public static class Node{
		char value = ' ';
		int existedL = 0;
		int existedR = 0;
	}
}
