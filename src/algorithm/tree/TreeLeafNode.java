package algorithm.tree;

public class TreeLeafNode<T> implements TreeNode<T> {
	private T data;
	
	@Override
	public T getData() {
		return this.data;
	}

	@Override
	public void setData(T data) {
		this.data = data;
	}

	@Override
	public boolean isLeaf() {
		return true;
	}

}
