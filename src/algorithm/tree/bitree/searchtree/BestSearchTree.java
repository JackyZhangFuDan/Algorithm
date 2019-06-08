package algorithm.tree.bitree.searchtree;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import algorithm.tree.bitree.BiTreeNode;
import algorithm.tree.bitree.visit.MidOrderVisit;

public class BestSearchTree {
	
	private BiTreeNode<Integer> root;
	
	public void construct(int[] array,int[] successFrequence,int[] failFrequence) {
		int len = array.length;
		
		Integer[] a = (Integer[]) Array.newInstance(Integer.class, len + 1);
		for(int i = 0; i < array.length; i++) {
			a[i+1] = array[i];
		}
		
		Integer[] sf = (Integer[]) Array.newInstance(Integer.class, len + 1);
		for(int i = 0; i< len; i++) {
			sf[i+1] = successFrequence[i]; 
		}

		Map<String, TreeInfo> informationMap = new HashMap<String, TreeInfo>();
		
		for(int interval = 0; interval <= len; interval++) {
			for(int i = 0 ; i <= len - interval; i++) {
				TreeInfo ti = new TreeInfo();
				//启动时的初始值设置
				if(interval == 0) {
					ti.rootIndex = 0;
					ti.weight = failFrequence[i];
					ti.cost = 0;
					informationMap.put(i+"-"+i, ti);
					continue;
				}
				//计算weight
				TreeInfo ti2 = informationMap.get(i + "-" + (i+interval-1) );
				ti.weight = sf[i+interval] + failFrequence[i+interval] + ti2.weight;
				
				//计算cost，进而计算出树根和左右子树，比较麻烦
				ti.cost = -1;
				for(int k = i+1; k <= i+interval; k++) {
					//k代表假设的树根，它是不是能成为最终树根那要看它的左右子树cost之和是否最小
					int currentCost = ti.weight + informationMap.get(i + "-" + (k-1)).cost + informationMap.get(k + "-" + (i+interval)).cost;
					if(ti.cost < 0 || currentCost < ti.cost) {
						ti.cost = currentCost;
						ti.rootIndex = k;
						ti.leftSubTree  = informationMap.get(i + "-" + (k-1));
						ti.rightSubTree = informationMap.get(k + "-" + (i+interval));
					}
				}
				informationMap.put(i + "-" + (i+interval), ti);
			}
		}
		
		this.root = this.makeStandardBiTree(informationMap.get("0-"+len), a);
	}
	
	/*
	 * 其实对应于“0-n”的TreeNode实例已经是一棵树了，但为了更美观，基于它构建一棵以标准节点类型为节点的二叉树
	 * 简单遍历copy一下就ok
	 */
	private BiTreeNode<Integer> makeStandardBiTree(TreeInfo r, Integer[] array) {
		if(r == null || r.rootIndex == 0) {
			return null;
		}
		BiTreeNode<Integer> root = new BiTreeNode<Integer>();
		root.setData(array[r.rootIndex]);
		root.setLeftNode(this.makeStandardBiTree(r.leftSubTree, array));
		root.setRightNode(this.makeStandardBiTree(r.rightSubTree, array));
		return root;
	}
	
	public static void main(String[] args) {
		BestSearchTree bst = new BestSearchTree();
		int[] array = {1,2,3,4};
		int[] successFrequence = {3,3,1,1};
		int[] failFrequence = {2,3,1,1,1};
		bst.construct(array, successFrequence, failFrequence);
		
		System.out.println("Mid order visit the created tree:");
		MidOrderVisit mov = new MidOrderVisit();
		mov.visit(bst.root);
	}
	
	private class TreeInfo{
		public Integer rootIndex;
		
		public Integer weight;
		public Integer cost;
		
		public TreeInfo rightSubTree;
		public TreeInfo leftSubTree;
	}
}
