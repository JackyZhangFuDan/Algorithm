package algorithm.tree.bitree.searchtree;

import java.util.ArrayList;
import java.util.List;

import algorithm.tree.bitree.BiTreeNode;
import algorithm.tree.bitree.searchtree.HuffmanTree.Entry;
/*
 * 构造以一组有序节点为叶子节点的最佳查找树，Hu-Tucker算法
 * 假设给定一组节点，他们已经排好序，他们的使用频率也给定，能不能构造一颗以他们为叶子节点的查找树，使得达到这些外部结点的总代价最小（也就是最小加权路径最小），并且叶子节点顺序不变
 * 其实Hu-Tuker算法构造的树和Huffman算法构造的树唯一的区别是原始序列的顺序有没有被保持
 * 
 * 思路：三个阶段：
 * 1， 构造
 * 1）相邻的，中间无原始结点的两个节点，他们 2）频率(或权重)加和最小，3)节点下标越小越好。用这两个节点构造新树，去替代两个节点中靠前的那一个，并删除靠后的那一个
 * 2， 标记
 * 在构造阶段构造起了一棵树，各个原始节点都在某一个层上，那么我们记录下他们的层号
 * 3， 建造
 * 原始结点有了层号以后，找相邻且层号最大的两个元素，构成新的节点，新节点层号等于那两个元素的层号减一，用新节点取代这两个老元素
 */
public class HuTuckerBestSerchTree {
	private BiTreeNode<Character> root;
	
	public void construct(List<Entry> list){
		this.step1(list);
	}
	
	private void step1(List<Entry> list){
		
		while(list.size() > 1){
			int selectedIndex1 = -1 ;
			int selectedIndex2 = -1 ;
			
			int i = 0;
			while(i < list.size() - 1){
				if(selectedIndex1 < 0){
					selectedIndex1 = i;
					i++;
					continue;
				}else if(selectedIndex2 < 0){
					selectedIndex2 = i;
					continue;
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
			
			if(selectedIndex2 < 0){
				break;
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
		
		System.out.println("done");
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
		
		HuTuckerBestSerchTree htbs = new HuTuckerBestSerchTree();
		htbs.construct(list);
	}
	
	public static class Entry{
		public char data;
		public int frequency;
		
		public int level;
		public boolean isNotOriginal;
		
		public Entry left;
		public Entry right;
	}
}
