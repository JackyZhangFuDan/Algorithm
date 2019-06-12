package algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/*
 * 无向图的各种操作：深度优先遍历；广度优先遍历
 */
public class NonDirGraph extends Graph {
	
	public NonDirGraph(Integer[] nodes, Map<Integer,ArrayList<Integer>> connections) {
		this.nodes = nodes;
		this.connections = connections;
	}
	
	@Override
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

	@Override
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
	
	public static void main(String[] args) {
		Integer[] nodes = {1,2,3,4,5,6,7,8,9};
		Map<Integer,ArrayList<Integer>> connections = new HashMap<Integer, ArrayList<Integer>>();
		
		ArrayList<Integer> connection = new ArrayList<Integer>();
		connection.add(2);
		connection.add(3);
		connection.add(4);
		connection.add(5);
		connections.put(1, connection);
		
		connection = new ArrayList<Integer>();
		connection.add(1);
		connection.add(4);
		connection.add(5);
		connections.put(2, connection);
		
		connection = new ArrayList<Integer>();
		connection.add(1);
		connection.add(6);
		connection.add(7);
		connections.put(3, connection);
		
		connection = new ArrayList<Integer>();
		connection.add(1);
		connection.add(2);
		connection.add(5);
		connections.put(4, connection);
		
		connection = new ArrayList<Integer>();
		connection.add(1);
		connection.add(2);
		connection.add(4);
		connection.add(8);
		connections.put(5, connection);

		connection = new ArrayList<Integer>();
		connection.add(3);
		connection.add(7);
		connection.add(9);
		connections.put(6, connection);

		connection = new ArrayList<Integer>();
		connection.add(3);
		connection.add(6);
		connections.put(7, connection);

		connection = new ArrayList<Integer>();
		connection.add(5);
		connections.put(8, connection);

		connection = new ArrayList<Integer>();
		connection.add(6);
		connections.put(9, connection);
		
		NonDirGraph ndg = new NonDirGraph(nodes,connections);
		System.out.println("Depth first visit the graph:");
		ndg.depthFirstVisit();
		System.out.println("Width first visit the graph:");
		ndg.widthFirstVisit();
	}
}
