package algorithm.tree;

public interface Visit {
	public <T> void visit(TreeNode<T> root);
	
	public static TreeNode<Integer> createSampleData(){
		/*
				           28
			  	 29                       30
		  31                         32        33       34
		35  36
		 */
		
		TreeCompositeNode<Integer> cn = new TreeCompositeNode<Integer>();
		cn.setData(28);
		TreeCompositeNode<Integer> cn1 = new TreeCompositeNode<Integer>();
		cn1.setData(29);
		cn.addChild(cn1);
		TreeCompositeNode<Integer> cn11 = new TreeCompositeNode<Integer>();
		cn11.setData(31);
		cn1.addChild(cn11);
		TreeLeafNode<Integer> cn111 = new TreeLeafNode<Integer>();
		cn111.setData(35);
		cn11.addChild(cn111);
		TreeLeafNode<Integer> cn112 = new TreeLeafNode<Integer>();
		cn112.setData(36);
		cn11.addChild(cn112);
		TreeCompositeNode<Integer> cn2 = new TreeCompositeNode<Integer>();
		cn2.setData(30);
		cn.addChild(cn2);
		TreeLeafNode<Integer> cn21 = new TreeLeafNode<Integer>();
		cn21.setData(32);
		cn2.addChild(cn21);
		TreeLeafNode<Integer> cn22 = new TreeLeafNode<Integer>();
		cn22.setData(33);
		cn2.addChild(cn22);
		TreeLeafNode<Integer> cn23 = new TreeLeafNode<Integer>();
		cn23.setData(34);
		cn2.addChild(cn23);
		
		return cn;
	}
}
