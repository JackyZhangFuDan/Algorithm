package algorithm.tree.bitree.searchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import algorithm.tree.bitree.BiTreeNode;
import algorithm.tree.bitree.visit.MidOrderVisit;
/*
 * 构造以一组有序节点为叶子节点的最佳查找树，Hu-Tucker算法
 * 给定一组节点，他们已经排好序，他们的使用频率也给定，能不能构造一颗以他们为叶子节点的查找树，使得达到这些外部结点的总代价最小（也就是最小加权路径最小），并且叶子节点顺序不变
 * 1. Hu-Tuker算法与Huffman算法目标是不一样的，前者要构造的是一颗查找树；而后者没有这个要求。
 * 2. Hu-Tuker算法构造的查找树相对于一般查找树是一个特殊情况：这棵树的内部节点查找概率是0，永远不会搜索它们。我们当然也可以用一般的查找树构建方法造Hu-Tucker能构造出的查找树
 * 
 * 思路：三个阶段：
 * 1， 构造
 * 1）相邻的，中间无原始结点的两个节点，他们 2）频率(或权重)加和最小，3)节点下标越小越好。用这两个节点构造新树，去替代两个节点中靠前的那一个，并删除靠后的那一个
 * 2， 标记
 * 在构造阶段构造起了一棵树，各个原始节点都在某一个层上，那么我们记录下他们的层号
 * 3， 建造
 * 原始结点有了层号以后，找相邻且层号最大的两个元素，构成新的节点，新节点层号等于那两个元素的层号减一，在序列中用新节点取代那两个相邻的老元素
 * 
 * 具体参考施伯乐和蔡子经的《数据结构》一书
 */
public class HuTuckerBestSearchTree {
	
	private BiTreeNode<Character> root;//最终形成的二叉树根节点；
	private List<Entry> originalList;//最初给定的数据
	
	public void construct(List<Entry> list){
		if(list == null || list.isEmpty()) {
			return;
		}
		
		//保存一份original list，它是排过序的，后序有用
		this.originalList = new ArrayList<Entry>();
		for(Entry e:list) {
			this.originalList.add(e);
		}
		//第一步：构造
		this.step1(list);
		//构造后list中应该只有一个元素，否则出错
		if(list == null || list.size() != 1) {
			return;
		}
		//第二步，标记
		this.step2(list.get(0));
		//第三步：建造，结果在orginalList里面
		this.step3();
		if(this.originalList.size() != 1) {
			System.out.println("Some thing wrong, the tree isn'cat constructed correctly");
		}
		
		//为了美观，把结果转化为一颗标准二叉树
		this.root = this.makeBiTree(this.originalList.get(0));
	}
	
	private void step1(List<Entry> list){
		
		while(list.size() > 1){
			int selectedIndex1 = -1 ;
			int selectedIndex2 = -1 ;
			
			int i = 0;
			while(i < list.size() - 1){
				if(selectedIndex1 < 0){
					selectedIndex1 = i;
					selectedIndex2 = i + 1;
				}
				int j = i + 1;
				boolean done = false;
				while(!done){
					if(list.get(i).frequency + list.get(j).frequency < list.get(selectedIndex1).frequency + list.get(selectedIndex2).frequency ){
						selectedIndex1 = i;
						selectedIndex2 = j;
					}
					if(!list.get(j).isNotOriginal || j == list.size() - 1){
						done = true;
					}
					j++;
				};
				
				i = j - 1;
			}
			
			Entry newE = new Entry();
			newE.data = '*';
			newE.frequency = list.get(selectedIndex1).frequency + list.get(selectedIndex2).frequency;
			newE.isNotOriginal = true;
			newE.left = list.get(selectedIndex1);
			newE.right = list.get(selectedIndex2);
			
			list.remove(selectedIndex2);
			list.remove(selectedIndex1);
			list.add(selectedIndex1, newE);
		}
		
	}
	
	private void step2(Entry root) {
		
		Entry r = root;
		int level = 0;
		LinkedList<Entry> queue = new LinkedList<Entry>();
		queue.offer(r);
		
		//为元素打上层号
		while(!queue.isEmpty()) {
			int length = queue.size();
			for(int i = 0 ; i < length ; i++) {
				Entry e = queue.poll();
				e.level = level;
				if(e.left != null)
					queue.offer(e.left);
				if(e.right != null) {
					queue.offer(e.right);
				}
				
			}
			level++;
		}
	}
	
	private void step3() {
		
		while(this.originalList.size() > 1) {
			int selectedIndex1 = -1;
			int selectedIndex2 = -1;
			int maxLevel = -1;
			
			for(int i = 0 ; i < this.originalList.size() - 1; i++) {
				if(this.originalList.get(i).level == this.originalList.get(i + 1).level && this.originalList.get(i).level > maxLevel) {
					selectedIndex1 = i;
					selectedIndex2 = i + 1;
					maxLevel = this.originalList.get(i).level;
				}
			}
			
			Entry newE = new Entry();
			newE.data = '*';
			newE.isNotOriginal = true;
			newE.left = this.originalList.get(selectedIndex1);
			newE.right = this.originalList.get(selectedIndex2);
			newE.level = maxLevel - 1;
			
			this.originalList.remove(selectedIndex2);
			this.originalList.remove(selectedIndex1);
			this.originalList.add(selectedIndex1, newE);
		}
	}
	
	private BiTreeNode<Character> makeBiTree(Entry r) {
		if(r == null) return null;
		BiTreeNode<Character> root = new BiTreeNode<Character>();
		root.setData(r.data);
		root.setLeftNode(this.makeBiTree(r.left));
		root.setRightNode(this.makeBiTree(r.right));
		return root;
	}
	
	public static void main(String[] args) {
		List<Entry> list = new ArrayList<Entry>();
		
		Entry e = new Entry();
		e.data = 'a';
		e.frequency = 23;
		list.add(e);
		
		e = new Entry();
		e.data = 'b';
		e.frequency = 18;
		list.add(e);
		
		e = new Entry();
		e.data = 'c';
		e.frequency = 2;
		list.add(e);
		
		e = new Entry();
		e.data = 'd';
		e.frequency = 16;
		list.add(e);
		
		e = new Entry();
		e.data = 'e';
		e.frequency = 15;
		list.add(e);
		
		e = new Entry();
		e.data = 'f';
		e.frequency = 8;
		list.add(e);
		
		HuTuckerBestSearchTree htbs = new HuTuckerBestSearchTree();
		htbs.construct(list);
		
		System.out.println("Mid order visiting the Bi-Tree constructed by Hu-Tucker algorithm:");
		MidOrderVisit mov = new MidOrderVisit();
		mov.visit(htbs.root);
	}
	
	/*
	 * 用于给出输入信息的数据结构，内部设置了一些在后续处理时需要用到的属性，纯属为了方便
	 */
	public static class Entry{
		public char data;
		public int frequency;
		
		public int level;
		public boolean isNotOriginal;
		
		public Entry left;
		public Entry right;
	}
}
