package algorithm.tree.bitree.visit;

/*
 * 逆转指针法前序，中序，后续遍历二叉树，无栈，无递归
 * 思路：先参考一下前面利用栈来后续遍历二叉树的算法，这里的思路是显示的创建一个栈，而是利用正在遍历的树枝替代这个栈。
 * 树的遍历有三个重要过程：1）向左分叉直至没有左节点；2）有右结点则把右子结点拿过来再做1）；3）没有子结点或者子结点都访问过了，那么回述
 * 由于java没有goto语句的实现，所以额外带来了复杂性，这里我们用三个子函数分别对应上面三个过程，为了避免无限循环调用，我们用‘allDone’来标志遍历完毕。
 */
public class ReversePointerVisit {
	
	private TreeNode root;
	private TreeNode parentNode;
	private TreeNode currentNode;
	private int visitMode; //1: pre; 2: mid; 3: post
	
	private boolean allDone = false;
	
	public ReversePointerVisit(TreeNode root,int visitMode){
		this.root = root;
		this.visitMode = visitMode;
	}
	
	public void visit(){
		
		if(this.root == null){
			return;
		}
		
		parentNode = null;
		currentNode = this.root;
		
		this.goLeft();
		
	}
	/*
	 * 调用这个函数，开始检验当前结点是否有左子节点，有的话顺着左子节点一直向下，直到当前结点没有左子节点
	 * 这个函数执行完后当前结点一定是没有左子结点了
	 */
	private void goLeft(){
		if(allDone){
			return;
		}
		
		if(this.visitMode == 1){
			System.out.println(currentNode);
		}
		
		if(currentNode.leftNode != null){
			TreeNode temp = currentNode.leftNode;
			currentNode.leftNode = parentNode;
			currentNode.whichChild = Direction.LEFT;
			parentNode = currentNode;
			currentNode = temp;
			
			this.goLeft();
		}
		this.goRight();
	}
	
	/*
	 * 调到这个函数时，当前结点的没有左子节点或者左子节点已经被访问完毕，那么我们看当前结点有没有右子结点，有的把右子结点拿来做goLeft处理
	 * 当这个函数执行结束后，当前结点一定是既没有左子节点也没有右子结点，那么就可以调用最后一个函数了
	 */
	private void goRight(){
		if(allDone){
			return;
		}
		
		if(this.visitMode == 2){
			System.out.println(currentNode);
		}
		
		if(currentNode.rightNode != null){
			TreeNode temp = currentNode.rightNode;
			currentNode.rightNode = parentNode;
			currentNode.whichChild = Direction.RIGHT;
			parentNode = currentNode;
			currentNode = temp;
			this.goLeft();
		}
		this.noLeftAndRight();
	}
	
	/*
	 * 调用到这个函数的时候，当前结点一定是叶子结点或者所有子结点都访问过了，那么我们回述上去：把parent（栈顶）结点作为当前结点
	 * 回述上去的时候有个问题：是回到goRight还是当前这个函数noLeftAndRight，这个决定于当前结点是栈顶结点的左子结点还是右子结点
	 * 左子结点的话要回到goRight，因为栈顶结点的右子结点还没访问呢；
	 * 右子结点的话要回到当前函数，因为栈顶结点的所有子结点都访问过了，当前结点相当于叶子了
	 */
	private void noLeftAndRight(){
		
		if(!allDone && this.visitMode == 3){
			System.out.println(currentNode);
		}
		
		if(parentNode == null) {
			allDone = true;
		}
		
		if(allDone){
			return;
		}
		
		if(parentNode.whichChild == Direction.LEFT){
			TreeNode temp = parentNode.leftNode; //parent's parent
			parentNode.leftNode = currentNode;
			parentNode.whichChild = null;
			currentNode = parentNode;
			parentNode = temp;
			
			this.goRight();
		}else if(parentNode.whichChild == Direction.RIGHT){
			TreeNode temp = parentNode.rightNode;
			parentNode.rightNode = currentNode;
			parentNode.whichChild = null;
			currentNode = parentNode;
			parentNode = temp;
			
			this.noLeftAndRight();
		}
	}
	
	public static void main(String[] args) {
		/*
				A
		a				b
					c		d
								e
		*/
		TreeNode n = new TreeNode();
		n.data = 'A';
		
		TreeNode n1 = new TreeNode();
		n1.data = 'a';
		n.leftNode = n1;
		
		n1 = new TreeNode();
		n1.data = 'b';
		n.rightNode = n1;
		
		TreeNode n2 = new TreeNode();
		n2.data = 'c';
		n1.leftNode = n2;
		
		n2 = new TreeNode();
		n2.data = 'd';
		n1.rightNode = n2;
		
		n1 = new TreeNode();
		n1.data = 'e';
		n2.rightNode = n1;
		
		System.out.println("Pre Order:");
		ReversePointerVisit rpv = new ReversePointerVisit(n, 1);
		rpv.visit();
		
		System.out.println("Middle Order:");
		rpv = new ReversePointerVisit(n, 2);
		rpv.visit();
		
		System.out.println("Post Order:");
		rpv = new ReversePointerVisit(n, 3);
		rpv.visit();

	}
	
	public static class TreeNode{
		public char data;
		public Direction whichChild;
		public TreeNode leftNode;
		public TreeNode rightNode;
		
		@Override
		public String toString(){
			return Character.toString(data);
		}
	}
	
	public enum Direction{
		LEFT, RIGHT;
	}
}
