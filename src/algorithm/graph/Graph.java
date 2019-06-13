package algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public abstract class Graph {
	
	protected Integer[] nodes;
	protected Map<Integer, ArrayList<Integer>> connections = new HashMap<Integer,ArrayList<Integer>>();
	
	/*
	 * 深度优先遍历算法对无向图和有向图都有效
	 */
	public void depthFirstVisit() {
		if(this.nodes == null || this.nodes.length == 0) {
			return;
		}
		
		Stack<Integer> stack = new Stack<Integer>();
		int[] visitedNodeFlags = new int[this.nodes.length+1];
		
		for(int i = 0; i < this.nodes.length; i++) {
			Integer iNode = this.nodes[i];
			if(visitedNodeFlags[iNode] == 1) {
				continue;
			}
			stack.clear();
			stack.push(iNode);
			
			while(!stack.isEmpty()) {
				iNode = stack.pop();
				
				System.out.println(iNode);
				visitedNodeFlags[iNode] = 1;
				
				List<Integer> iConnections = this.connections.get(iNode);
				if(iConnections == null || iConnections.isEmpty()) {
					continue;
				}
				for(Integer cNode : iConnections) {
					if(visitedNodeFlags[cNode] == 0) {//广度优先时没有这个判断，那时所有邻接节点都进队，在打印时检验是否访问过
						stack.push(cNode);
						break; //这个也很关键，只进栈一个
					}
				}
			}
		}
		
	}
	
	/*
	 * 广度优先遍历法对有向图和无向图都有效
	 */
	public void widthFirstVisit() {
		if(this.nodes == null || this.nodes.length == 0) {
			return;
		}
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		int[] visitedNodeFlags = new int[this.nodes.length+1];
		
		for(int i = 0; i < this.nodes.length; i++) {
			Integer iNode = this.nodes[i];
			if(visitedNodeFlags[iNode] == 1) {
				continue;
			}
			queue.offer(iNode);
			
			while(!queue.isEmpty()) {
				iNode = queue.poll();
				if(visitedNodeFlags[iNode] == 1) { //深度优先时没有这个控制，那时直接打印，而是在进栈时检验有没有访问过
					continue;
				}
				System.out.println(iNode);
				visitedNodeFlags[iNode] = 1;
				
				List<Integer> iConnections = this.connections.get(iNode);
				if(iConnections == null || iConnections.isEmpty()) {
					continue;
				}
				for(Integer cNode : iConnections) {
						queue.offer(cNode);
				}
			}
		}
	}
}
