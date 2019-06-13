package algorithm.tree.bitree.thread;

public class ThreadTreeNode {
	public char data;
	public ThreadTreeNode leftNode;
	public ThreadTreeNode rightNode;
	public boolean leftIsChild; //标志位，左子结点指针是否还是指向真正子结点？即使leftNode是null它也可能是true
	public boolean rightIsChild;
	
	@Override
	public String toString() {
		return String.valueOf(data);
	}
}
