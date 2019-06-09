package algorithm.tree.bitree.searchtree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import algorithm.tree.bitree.BiTreeNode;

/*
 * 构造具有最小加权外部路径长度的二叉树 （注意构造结果不是查找树）
 * 给定一个结点序列和他们的权重，构造一棵树，用这些结点作为叶子节点，并且到达这些叶子节点的加权路径最短
 * 思路：先把原始节点序列放在一个列表里，然后找出两个加权最小的节点，让他们构成新结点，新节点的权重是二者之和，放到《注意》列表尾部，并从列表中移除那两个结点，
 * 当序列中只有一个元素时候整个过程结束。如果出现多组这样的结点，选区比较靠序列前部的节点
 * 
 * 在下面的实现过程中我们为了方便，开始循环前把初始列表中元素的权重都变负，并且新加入节点权重也取负值，当权重为负数的节点变为新节点的子节点时再把他们的权重变正，
 * 这样在循环中只考虑权重为负数的节点，从而避免新建列表的繁琐
 */
public class HuffmanTree {
	private BiTreeNode<Character> root;
	
	public void construct(List<Entry> list){
		if(list == null || list.isEmpty()){
			return;
		}
		
		//初始节点权重变负
		for(Entry e : list){
			e.weight = -e.weight;
		}
		
		boolean done = false;
		
		while(!done){
			Iterator<Entry> listIt = list.iterator();
			int minW1 = 9999;
			Entry e1 = null;
			int minW2 = 9999;
			Entry e2 = null;
			
			//找权重最小的两个
			while(listIt.hasNext()){
				Entry e = listIt.next();
				if(e.weight < 0 && -e.weight < minW1){
					minW2 = minW1;
					e2 = e1;
					minW1 = -e.weight;
					e1 = e;
				}else if(e.weight < 0 && -e.weight < minW2){
					minW2 = -e.weight;
					e2 = e;
				}
			}
			
			//如果只能找到一个节点，那说明树已经形成，结束
			if(e2 == null){
				done = true;
				continue;
			}
			
			Entry newE = new Entry();
			newE.data = '*';
			newE.weight = e1.weight + e2.weight;
			newE.left = e1;
			newE.right = e2;
			list.add(newE);
			
			//现在e1和e2已经是新节点的子节点了，把他们的权重恢复
			e1.weight = -e1.weight;
			e2.weight = -e2.weight;
		}
		
		//为了美观，我们把刚刚结算的结果建造成标准二叉树
		Entry r = null;
		for(Entry e:list){
			if(e.weight < 0){
				r = e;
			}
		}
		this.root = this.makeBiTree(r);
	}
	
	private BiTreeNode makeBiTree(Entry r){
		if (r == null) return null;
		
		BiTreeNode<Character> root = new BiTreeNode<Character>();
		root.setData(r.data);
		root.setWeight(Math.abs(r.weight));
		root.setLeftNode(this.makeBiTree(r.left));
		root.setRightNode(this.makeBiTree(r.right));
		return root;
	}
	
	public static void main(String[] args) {
		List<Entry> list = new ArrayList<Entry>();
		
		Entry e = new Entry();
		e.data = 'a';
		e.weight = 10;
		list.add(e);
		
		e = new Entry();
		e.data = 'b';
		e.weight = 5;
		list.add(e);
		
		e = new Entry();
		e.data = 'c';
		e.weight = 20;
		list.add(e);
		
		e = new Entry();
		e.data = 'd';
		e.weight = 10;
		list.add(e);
		
		e = new Entry();
		e.data = 'e';
		e.weight = 18;
		list.add(e);
		
		HuffmanTree ht = new HuffmanTree();
		ht.construct(list);
		
	}
	
	public static class Entry{
		public char data;
		public int weight ;
		
		public Entry left;
		public Entry right;
	}

}
