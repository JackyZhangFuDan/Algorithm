package algorithm.leetcode.removelinknode;

import java.util.Stack;

import algorithm.leetcode.addtwonum.ListNode;

/*
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：
给定一个链表: 1->2->3->4->5, 和 n = 2.
当删除了倒数第二个节点后，链表变为 1->2->3->5.

说明：
给定的 n 保证是有效的。

进阶：
你能尝试使用一趟扫描实现吗？

 */
public class Solution {

	private ListNode head = null;
	
	public Solution(ListNode head){
		this.head = head;
	}

	public ListNode solve(int target){
		Stack<ListNode> stack = new Stack<ListNode>();
		ListNode node = this.head;
		while(node != null){
			stack.push(node);
			node = node.next;
		}
		int count = target-1;
		ListNode nextNode = null;
		while(count > 0){
			nextNode = stack.pop();
			count--;
		}
		ListNode removedNode = stack.pop();
		if(removedNode == head){
			return nextNode;
		}else{
			ListNode previousNode = stack.pop();
			previousNode.next = nextNode;
			return this.head;
		}
	}
	
	/*
	 * 这是官方解法，用两个指针，它们之间相隔target-1个节点，然后同步移动直到后一个节点到底
	 * 注意当要删除的是头节点的时候，p2是空
	 */
	public ListNode solve2(int target) {
		
		ListNode p1 = this.head;
		ListNode p2 = this.head;
		do {
			p2 = p2.next;
			target--;
		}while(target > 0);
		
		while(p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next;
		}
		
		if(p1 == this.head) {
			this.head = this.head.next;
		}else {
			p1.next = p1.next;
		}
		return this.head;
	}
	
	public void printList() {
		ListNode node = this.head;
		while(node != null) {
			System.out.print(node.val+"->");
			node = node.next;
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		
		ListNode node = new ListNode(2);
		head.next = node;

		node.next = new ListNode(3);
		node = node.next;

		node.next = new ListNode(4);
		node = node.next;

		node.next = new ListNode(5);
		node = node.next;
		
		
		Solution s = new Solution(head);
		s.printList();
		s.solve2(5);
		s.printList();
		
	}
	
	public static class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	}

}