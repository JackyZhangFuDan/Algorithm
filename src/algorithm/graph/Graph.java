package algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public abstract class Graph {
	
	//图的顶点集合，不过在用的过程中作用不大，边的信息足以支撑这些算法了
	protected Integer[] nodes;
	
	//下面两个用于表示边的属性二选一，看是要用链接表还是邻接矩阵表示边
	protected Map<Integer, ArrayList<GraphEdge>> connections = new HashMap<Integer,ArrayList<GraphEdge>>();
	protected int[][] connectionMatrix = null;
	
	public Graph(Integer[] nodes, int[][] connectionMatrix) {
		this.nodes = nodes;
		this.connectionMatrix = connectionMatrix;
	}
	
	public Graph(Integer[] nodes, Map<Integer, ArrayList<GraphEdge>> connections) {
		this.nodes = nodes;
		this.connections = connections;
	}
	
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
				
				List<GraphEdge> iConnections = this.connections.get(iNode);
				if(iConnections == null || iConnections.isEmpty()) {
					continue;
				}
				for(GraphEdge cNode : iConnections) {
					if(visitedNodeFlags[cNode.targetNode] == 0) {//广度优先时没有这个判断，那时所有邻接节点都进队，在打印时检验是否访问过
						stack.push(cNode.targetNode);
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
				
				List<GraphEdge> iConnections = this.connections.get(iNode);
				if(iConnections == null || iConnections.isEmpty()) {
					continue;
				}
				for(GraphEdge cNode : iConnections) {
						queue.offer(cNode.targetNode);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Integer[] nodes = {1,2,3,4,5,6,7,8,9};
		Map<Integer,ArrayList<GraphEdge>> connections = new HashMap<Integer, ArrayList<GraphEdge>>();
		
		ArrayList<GraphEdge> connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(2));
		connection.add(new GraphEdge(3));
		connection.add(new GraphEdge(4));
		connection.add(new GraphEdge(5));
		connections.put(1, connection);
		
		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(1));
		connection.add(new GraphEdge(4));
		connection.add(new GraphEdge(5));
		connections.put(2, connection);
		
		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(1));
		connection.add(new GraphEdge(6));
		connection.add(new GraphEdge(7));
		connections.put(3, connection);
		
		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(1));
		connection.add(new GraphEdge(2));
		connection.add(new GraphEdge(5));
		connections.put(4, connection);
		
		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(1));
		connection.add(new GraphEdge(2));
		connection.add(new GraphEdge(4));
		connection.add(new GraphEdge(8));
		connections.put(5, connection);

		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(3));
		connection.add(new GraphEdge(7));
		connection.add(new GraphEdge(9));
		connections.put(6, connection);

		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(3));
		connection.add(new GraphEdge(6));
		connections.put(7, connection);

		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(5));
		connections.put(8, connection);

		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(6));
		connections.put(9, connection);
		
		Graph ndg = new NonDirGraph(nodes,connections);
		System.out.println("Depth first visit the graph:");
		ndg.depthFirstVisit();
		System.out.println("Width first visit the graph:");
		ndg.widthFirstVisit();
	}
	
	/*
	 * 在用链接表存储图的边时所用，链接表的一个元素对应的数据结构
	 */
	public static class GraphEdge{
		public Integer targetNode; //目标顶点
		public Integer edgeValue; //边的权重，例如长度
		
		public GraphEdge() {
			
		}
		
		public GraphEdge(Integer tn) {
			this.targetNode = tn;
		}
		
		public GraphEdge(Integer tn, Integer value) {
			this.targetNode = tn;
			this.edgeValue = value;
		}
	}
}
