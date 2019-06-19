package algorithm.leetcode.addtwonum;

/*
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-two-numbers
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
*/

public class Solution {
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		
        if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }
        
        int move = 0;
        ListNode resultHead = null;
        while(l1 != null && l2 != null){
            int sum = move + l1.val + l2.val;
            l1.val = sum % 10;
            move = sum / 10;
            if(resultHead == null){
                resultHead = l1;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        
        //find the last node of result nodes;
        ListNode endNode = resultHead;
        while(endNode.next != null) endNode = endNode.next;
        
        //find head of reminding nodes;
        ListNode leftNode = null;
        if(l1 == null){
            leftNode = l2;
        }else{
            leftNode = l1;
        }
        
        while(leftNode != null){
            int sum = move + leftNode.val;
            leftNode.val = sum % 10;
            move = sum / 10;
            endNode.next = leftNode;
            endNode = endNode.next;
            leftNode = leftNode.next;
        }
        
        if(move > 0) {
        	endNode.next = new ListNode(move);
        }
        
        return resultHead;
    }
	
	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(5);
		
		ListNode l2 = new ListNode(3);
		l2.next = new ListNode(5);
		l2.next.next = new ListNode(6);
		l2.next.next.next = new ListNode(6);
		
		Solution s = new Solution();
		l1 = s.addTwoNumbers(l1, l2);
		
		while(l1 != null) {
			System.out.print(l1.val);
			l1 = l1.next;
		}
	}

}
