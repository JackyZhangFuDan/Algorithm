package algorithm.tree;

import java.util.List;

public class TreeCompositeNode<T> implements TreeNode<T> {

	private T data;
	private List<TreeNode<T>> childs;
	
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
		return false;
	}
	
	public TreeNode<T> getChild(int index){
		if(this.childs == null) {
			return null;
		}
		
		if(index >= this.childs.size()) {
			return null;
		}
		
		return this.childs.get(index);
	}
	
	public boolean removeChild(int index) {
		if(this.childs == null) {
			return false;
		}
		
		if(index >= this.childs.size()) {
			return false;
		}
		
		this.childs.remove(index);
		return true;
	}

}
