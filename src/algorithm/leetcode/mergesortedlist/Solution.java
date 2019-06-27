package algorithm.leetcode.mergesortedlist;

/*
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:
输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
*/

public class Solution {
	
	public ListNode sort(ListNode[] heads) {
		
		int listCount = heads.length;
		
		while(listCount > 1) {
			for(int i = 0; i < listCount/2; i++) {
				int idx1 = i * 2;
				int idx2 = i * 2 + 1;
				
				ListNode head1 = heads[idx1];
				ListNode head2 = heads[idx2];
				ListNode pHead1 = null;
				
				while(head1 != null || head2 != null) {
					if(head1 != null && head2 != null) {
						if(head1.val < head2.val) {
							pHead1 = head1;
							head1 = head1.next;
						}else {
							ListNode tmp = head2;
							head2 = head2.next;
							tmp.next = head1;
							if(pHead1 != null) {
								pHead1.next = tmp;
							}else {
								heads[idx1] = tmp;
							}
							pHead1 = tmp;
						}
					}else if(head2 == null) {
						head1 = null;
					}else if(head1 == null) {
						pHead1.next = head2;
						head2 = null;
					}
				}
				
				heads[i] = heads[2*i];
			}
			
			if(listCount % 2 == 0) {
				listCount = listCount / 2;
			}else {
				heads[listCount/2] = heads[listCount-1];
				listCount = listCount / 2 + 1;
			}
		}
		
		return heads[0];
	}
	
	public static void main(String[] args) {
		ListNode[] heads = new ListNode[3];
		
		ListNode node = new ListNode(1);
		heads[0] = node;
		node.next = new ListNode(4);
		node = node.next;
		node.next = new ListNode(5);
		
		node = new ListNode(1);
		heads[1] = node;
		node.next = new ListNode(3);
		node = node.next;
		node.next = new ListNode(4);
		
		node = new ListNode(2);
		heads[2] = node;
		node.next = new ListNode(6);
		
		Solution s = new Solution();
		node = s.sort(heads);
		
		while(node != null) {
			System.out.println(node.val);
			node = node.next;
		}
	}
	
	public static class ListNode{
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
}
