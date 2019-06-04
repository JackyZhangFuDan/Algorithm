package algorithm.tree.representation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import algorithm.tree.TreeCompositeNode;
import algorithm.tree.TreeLeafNode;
import algorithm.tree.TreeNode;

public class PreorderWithLevelRepresentation {
	
	public TreeNode toStandardTree(List<DataWithLevel> dataList) {
		
		if(dataList == null || dataList.isEmpty()) {
			return null;
		}
		
		TreeNode<Integer> root = null;
		Stack<TreeNode<Integer>> parentStack = new Stack<TreeNode<Integer>>();
		Stack<Integer> parentLevelStack = new Stack<Integer>();
		
		if(dataList.size() == 1) {
			root = new TreeLeafNode<Integer>();
			root.setData(dataList.get(0).data);
			return root;
		}else {
			root = new TreeCompositeNode<Integer>();
			root.setData(dataList.get(0).data);
			parentStack.add(root);
			parentLevelStack.add(dataList.get(0).level);
		}
		
		for(int i = 1; i < dataList.size(); i++) {
			TreeNode<Integer> tempNd = null;
			if(i == dataList.size() - 1 || dataList.get(i + 1).level <= dataList.get(i).level) {
				tempNd = new TreeLeafNode<Integer>();
			}else{
				tempNd = new TreeCompositeNode<Integer>();
			}
			
			tempNd.setData(dataList.get(i).data);
			while(parentLevelStack.peek() >= dataList.get(i).level) {
				parentLevelStack.pop();
				parentStack.pop();
			}
			TreeCompositeNode<Integer> parent = (TreeCompositeNode<Integer>) parentStack.peek();
			parent.addChild(tempNd);
			
			if(i < dataList.size() - 1 && dataList.get(i + 1).level > dataList.get(i).level) {
				parentStack.push(tempNd);
				parentLevelStack.push(dataList.get(i).level);
			}
			
		}
		
		return root;
	}
	
	public static void main(String[] args) {
		List<DataWithLevel> preorderWithLevel = new ArrayList<DataWithLevel>();
		DataWithLevel data = new DataWithLevel();
		
		data.data = 1;
		data.level = 1;
		preorderWithLevel.add(data);
		
		data = new DataWithLevel();
		data.data = 2;
		data.level = 2;
		preorderWithLevel.add(data);
		
		data = new DataWithLevel();
		data.data = 3;
		data.level = 2;
		preorderWithLevel.add(data);
		
		data = new DataWithLevel();
		data.data = 4;
		data.level = 3;
		preorderWithLevel.add(data);
		
		data = new DataWithLevel();
		data.data = 5;
		data.level = 2;
		preorderWithLevel.add(data);
		
		data = new DataWithLevel();
		data.data = 6;
		data.level = 3;
		preorderWithLevel.add(data);
		
		PreorderWithLevelRepresentation ins = new PreorderWithLevelRepresentation();
		TreeNode<Integer> root = ins.toStandardTree(preorderWithLevel);
	}
	
	public static class DataWithLevel{
		public Integer data;
		public Integer level;
	}
}
