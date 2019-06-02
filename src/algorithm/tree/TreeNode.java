package algorithm.tree;

public interface TreeNode<T> {

	public T getData(); 
	public void setData(T data);
	
	public boolean isLeaf();
}
