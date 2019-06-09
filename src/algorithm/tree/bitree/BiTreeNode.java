package algorithm.tree.bitree;

public class BiTreeNode<T> {
	
	private T data;
	private BiTreeNode<T> leftNode;
	private BiTreeNode<T> rightNode;
	
	private int weight; //it is used in some scenarios, e.g. huffman tree
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public boolean isLeaf() {
		return (this.leftNode == null && this.rightNode == null);
	}
	
	
	public BiTreeNode<T> getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(BiTreeNode<T> leftNode) {
		this.leftNode = leftNode;
	}

	public BiTreeNode<T> getRightNode() {
		return rightNode;
	}

	public void setRightNode(BiTreeNode<T> rightNode) {
		this.rightNode = rightNode;
	}

	public String toString() {
		return data.toString();
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
