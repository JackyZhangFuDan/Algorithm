package algorithm.tree.bitree.thread;

public class ThreadTree {
	
	private ThreadTreeNode root;
	private ThreadTreeNode headOfMidOrder;
	
	public void visit() {
		ThreadTreeNode node = headOfMidOrder;
		while(node != null) {
			System.out.println(node);
			node = this.findSuccNodeOfMiddleOrder(node);
		}
	}
	/*
	 * 找到给定节点按前序的前一个节点。
	 * 思路：如果它没有左子结点那么左子结点存放的指针就指向中序的前序节点；如果它有左子节点，那就找它左子结点的右树枝的最后一个节点，就是结果
	 */
	public ThreadTreeNode findPreNodeOfMiddleOrder(ThreadTreeNode currentNode) {
		if(currentNode == null) {
			return null;
		}
		
		if(!currentNode.leftIsChild) {
			return currentNode.leftNode;
		}
		
		ThreadTreeNode node = currentNode.leftNode;
		if(node == null) {//只有可能是头节点
			return node;
		}
		while(node.rightNode != null && node.rightIsChild) {
			node = node.rightNode;
		}
		
		return node;
	}
	
	public ThreadTreeNode findSuccNodeOfMiddleOrder(ThreadTreeNode currentNode) {
		if(currentNode == null) {
			return null;
		}
		
		if(!currentNode.rightIsChild) {
			return currentNode.rightNode;
		}
		
		ThreadTreeNode node = currentNode.rightNode;
		if(node == null) {//只可能是中序最后的节点
			return node;
		}
		while(node.leftNode!= null && node.leftIsChild) {
			node = node.leftNode;
		}
		
		return node;
	}
	/*
	 * 把一个数据插入穿线树，使它位于给定节点在线上的前一个位置，也就是说中序的前一个
	 * 思路：如果给定节点没有左节点（包含两种情况：头节点和无左子结点的非头节点），那么直接作为给定节点左子结点；
	 * 如果给定节点有左子结点，那么只好去找给定节点的线上前序节点，找到后把新节点作为找到节点的右子节点
	 */
	public void InsertAsPreOfMiddleOrder(ThreadTreeNode node, char data) {
		if(node == null) {
			return;
		}
		
		ThreadTreeNode toBeInsertNode = new ThreadTreeNode();
		toBeInsertNode.data = data;
		
		if(!node.leftIsChild || node.leftNode == null) {
			toBeInsertNode.leftNode = node.leftNode;
			toBeInsertNode.leftIsChild = node.leftIsChild;
			toBeInsertNode.rightNode = node;
			toBeInsertNode.rightIsChild = false;
			
			node.leftNode = toBeInsertNode;
			node.leftIsChild = true;
			
			if(toBeInsertNode.leftNode == null) {
				this.headOfMidOrder = toBeInsertNode;
			}
		}else {
			ThreadTreeNode preNode = this.findPreNodeOfMiddleOrder(node);
			
			toBeInsertNode.rightNode = preNode.rightNode;
			toBeInsertNode.rightIsChild = preNode.rightIsChild;
			toBeInsertNode.leftNode = preNode;
			toBeInsertNode.leftIsChild = false;
			
			preNode.rightNode = toBeInsertNode;
			preNode.rightIsChild = true;
		}
	}
	
	public void InsertAsSuccOfMiddleOrder(ThreadTreeNode node, char data) {
		if(node == null) {
			return;
		}
		
		ThreadTreeNode toBeInsertNode = new ThreadTreeNode();
		toBeInsertNode.data = data;
		
		if(!node.rightIsChild || node.rightNode == null) {
			toBeInsertNode.rightNode = node.rightNode;
			toBeInsertNode.rightIsChild = node.rightIsChild;
			toBeInsertNode.leftNode = node;
			toBeInsertNode.leftIsChild = false;
			
			node.rightNode = toBeInsertNode;
			node.rightIsChild = true;
		}else {
			ThreadTreeNode succNode = this.findSuccNodeOfMiddleOrder(node);
			toBeInsertNode.leftNode = succNode.leftNode;
			toBeInsertNode.leftIsChild = succNode.leftIsChild;
			toBeInsertNode.rightNode = succNode;
			toBeInsertNode.rightIsChild = false;
			
			succNode.leftNode = toBeInsertNode;
			succNode.leftIsChild = true;
		}
	}
	
	/*
	 * 把一个数组转变为一颗穿线排序树，之后中序遍历该树就得到排好序的序列了
	 * 穿线排序树就是穿线树+节点是按照中序排好序了的
	 * 思路：对每个数组元素，先找到它在穿线排序中的插入位置，然后插入
	 */
	public static ThreadTree constructSortTree(char[] array) {
		if(array == null || array.length == 0) {
			return null;
		}
		
		ThreadTree result = new ThreadTree();
		ThreadTreeNode node = new ThreadTreeNode();
		node.data = array[0];
		node.leftIsChild = true;
		node.leftNode = null;
		node.rightIsChild = true;
		node.rightNode = null;
		result.root = node;
		
		for(int i = 1; i < array.length; i++) {
			char data = array[i];
			ThreadTreeNode toBeInsertNode = new ThreadTreeNode();
			toBeInsertNode.data = data;
			
			node = result.root;
			while(1==1) {
				if(node.data <= data) {
					if(node.rightIsChild && node.rightNode !=null ) {
						node = node.rightNode;
					}else {
						break;
					}
				}else if(node.data > data) {
					if(node.leftIsChild && node.leftNode != null) {
						node = node.leftNode;
					}else {
						break;
					}
				}
			}
			
			if(node.data <= data) {
				toBeInsertNode.rightNode = node.rightNode;
				toBeInsertNode.rightIsChild = node.rightIsChild;
				toBeInsertNode.leftNode = node;
				toBeInsertNode.leftIsChild = false;
				
				node.rightNode = toBeInsertNode;
				node.rightIsChild = true;
			}else {
				toBeInsertNode.leftNode = node.leftNode;
				toBeInsertNode.leftIsChild = node.leftIsChild;
				toBeInsertNode.rightNode = node;
				toBeInsertNode.rightIsChild = false;
				
				if(toBeInsertNode.leftNode == null) {
					result.headOfMidOrder = toBeInsertNode;
				}
				
				node.leftNode = toBeInsertNode;
				node.leftIsChild = true;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		
		char[] data = {'E','B','H','C','F','A','D','G'};
		System.out.println("All nodes of the original tree: ");
		for(int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
		
		System.out.println("Middle order visit, after sorted by thread sort tree: ");
		ThreadTree tree = ThreadTree.constructSortTree(data);
		tree.visit();
		
	}
}
