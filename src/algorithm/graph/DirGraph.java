package algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class DirGraph extends Graph {

	public DirGraph(Integer[] nodes, int[][] connectionMatrix) {
		super(nodes, connectionMatrix);
	}
	
	public DirGraph(Integer[] nodes, Map<Integer, ArrayList<GraphEdge>> connections) {
		super(nodes,connections);
	}
	
	/*
	 * 求有向图中一个顶点到图中所有其它顶点的最短距离
	 * 思路：特别像求连通无向图的最小代价生成树的Prim算法。把顶点分成两组，一组是已经求出v到它们最短路径的集合S1，初始时它是v；一组是还没有求得的，设为S2。然后
	 * 做n-1次循环，每次都取这样的路径：开始节点在S1中，结束节点在S2中，且由v到S2节点的路径最短的那一个。然后把S2中取的那个顶点加入S1。
	 * 注意加入后要调整计算v到S2中个顶点的新距离，因为新顶点加入S1可能造成更短路径
	 */
	public void minCostRouteDijkstra(int start) {
		int[] dist = new int[this.nodes.length];
		int[] pre = new int[this.nodes.length];
		int[] visitedFlags = new int[this.nodes.length];
		
		for(int i = 0; i < this.nodes.length; i++){
			pre[i] = -1;
			visitedFlags[i] = 0;
			dist[i] = this.connectionMatrix[start][i];
			if(dist[i] < 9999) {
				pre[i] = start;
			}
		}
		visitedFlags[start] = 1;
		
		for(int i = 0; i < this.nodes.length; i++) {
			
			int minCost = 9999;
			int candidate = -1;
			for(int j = 0; j < this.nodes.length; j++) {
				if(visitedFlags[j] == 0 && dist[j] < minCost) {
					minCost = dist[j];
					candidate = j;
				}
			}
			
			if(candidate == -1) {
				continue;
			}
			
			visitedFlags[candidate] = 1;
			System.out.println("select " + candidate);
			
			for(int j = 0; j < this.nodes.length; j++) {
				if(visitedFlags[j] == 0 ) {
					if( this.connectionMatrix[candidate][j] + minCost < dist[j]) {
						dist[j] = this.connectionMatrix[candidate][j] + minCost;
						pre[j] = candidate;
					}
				}
			}
		}
		
		//打印边
		System.out.println("Used Edges:");
		for(int i = 0; i < pre.length; i++) {
			System.out.println(pre[i] + "->" + i);
		}
	}
	
	/*
	 * 求有向图任意两个顶点之间的最短路径
	 * 实际上这个只要调用Dijkstra算法n次就可以实现了，但Floyd找到了一个更简洁的方式，二者之间时间复杂度是一致的
	 */
	public void minCostRouteFloyd() {
		int[][] a = new int[this.nodes.length][this.nodes.length];
		int[][] path = new int[this.nodes.length][this.nodes.length];
		
		for(int i = 0; i < this.nodes.length; i++) {
			for(int j = 0; j< this.nodes.length; j++) {
				a[i][j] = this.connectionMatrix[i][j];
				path[i][j] = -1;
			}
		}

		for(int i = 0; i < this.nodes.length; i++) {
			for(int j = 0; j< this.nodes.length; j++) {
				for(int k = 0; k < this.nodes.length; k++) {
					if(a[i][j] > a[i][k] + a[k][j]) {
						a[i][j] = a[i][k] + a[k][j];
						path[i][j] = k;
					}
				}
			}
		}
		
		this.printAllPathForFloyd(path, a);
	}
	
	private void printPathForFloyd(int[][] path, int i, int j) {	
		int k = path[i][j];
		if(k==-1) {
			return;
		}
		this.printPathForFloyd(path, i, k);
		System.out.print(k+"->");
		this.printPathForFloyd(path, k, j);
	}
	
	private void printAllPathForFloyd(int[][]path, int[][] cost) {
		for(int i = 0; i < this.nodes.length; i++) {
			for(int j = 0; j < this.nodes.length; j++) {
				if(i != j) {
					if(cost[i][j] == 9999) {
						System.out.print(i + "->" + j + " has no path");
					}else {
						System.out.print(i + "->");
						this.printPathForFloyd(path, i, j);
						System.out.print("->" + j);
					}
				}
				System.out.println();
			}
		}
	}
	
	/*
	 * 拓扑排序 - 非自反的半序序列排序
	 * 对于无有向回路的有向图，通过拓扑排序可以把所有顶点都输出，但如果有回路，那么回路部分是没有办法输出的
	 * 排序方法：很简单，每次都输出入度为0的顶点，直到没有
	 * 
	 * AOV网络是用顶点代表活动，边代表活动之间的先后顺序的网络。如果AOV网络没有回路那么它是可行的。
	 * 拓扑排序可以用于判别AOV-网络的可行性，以及排出活动的执行次序
	 */
	public void topolSort() {
		int[] inCount = new int[this.nodes.length];
		Stack<Integer> nodesOfZeroIn = new Stack<Integer>();
		
		for(Map.Entry<Integer, ArrayList<GraphEdge>> e: this.connections.entrySet()) {
			ArrayList<GraphEdge> list = e.getValue();
			for(GraphEdge node : list) {
				inCount[node.targetNode] = inCount[node.targetNode] + 1;
			}
		}
		
		for(int i = 0 ; i < inCount.length; i++) {
			if(inCount[i] == 0) {
				nodesOfZeroIn.push(i);
			}
		}
		
		while(!nodesOfZeroIn.isEmpty()) {
			int node = nodesOfZeroIn.pop();
			System.out.println(node);
			ArrayList<GraphEdge> connectedNodes = this.connections.get(node);
			if(connectedNodes ==null || connectedNodes.isEmpty()) {
				continue;
			}
			for(GraphEdge connectedNode : connectedNodes) {
				inCount[connectedNode.targetNode]--;
				if(inCount[connectedNode.targetNode] == 0) {
					nodesOfZeroIn.push(connectedNode.targetNode);
				}
			}
		}
	}
	
	/*
	 * 确定AOE网络的关键路径
	 * AOE网络是指顶点代表事件，边代表活动的网络，如果AOE网络有唯一的入度为0的顶点（起点）和唯一的出度为0的顶点（终点），那么可以确定它的关键路径
	 * AOV网络的重点在于表示活动先后顺序，而AOE网络的重点是表示活动的耗时
	 * 思路：找到各个事件（或活动）的最早发生时间和最晚发生时间，二者相同的事件是关键路径上的事件。先通过Topol排序类似的过程确定各个事件的最早发生时间；然后在“倒序”确定各个节点的最晚发生时间
	 */
	public void keyPath() {
		int[] eventEarlist = new int[this.nodes.length];
		int[] eventLastest = new int[this.nodes.length];
		int[] topol = new int[this.nodes.length];
		
		//首先利用topol排序一样的方法计算时间最早发生时间，topol排序结果也顺便计算出来了
		int[] inCount = new int[this.nodes.length];
		Stack<Integer> nodesOfZeroIn = new Stack<Integer>();
		
		for(Map.Entry<Integer, ArrayList<GraphEdge>> e: this.connections.entrySet()) {
			ArrayList<GraphEdge> list = e.getValue();
			for(GraphEdge node : list) {
				inCount[node.targetNode] = inCount[node.targetNode] + 1;
			}
		}
		
		for(int i = 0 ; i < inCount.length; i++) {
			if(inCount[i] == 0) {
				nodesOfZeroIn.push(i);
			}
		}
		
		int index = 0;
		while(!nodesOfZeroIn.isEmpty()) {
			int node = nodesOfZeroIn.pop();
			topol[index++] = node;
			ArrayList<GraphEdge> connectedNodes = this.connections.get(node);
			if(connectedNodes ==null || connectedNodes.isEmpty()) {
				continue;
			}
			for(GraphEdge connectedNode : connectedNodes) {
				inCount[connectedNode.targetNode]--;
				if(inCount[connectedNode.targetNode] == 0) {
					nodesOfZeroIn.push(connectedNode.targetNode);
				}
				if(eventEarlist[node] + connectedNode.edgeValue > eventEarlist[connectedNode.targetNode]) {
					eventEarlist[connectedNode.targetNode] = eventEarlist[node] + connectedNode.edgeValue;
				}
			}
		}
		
		//然后将拓扑排序的结果逆序一下，后面会用到,
		//当然也可以不用这样物理逆转，而是在循环时下标从大到小，但为了突出这一事项，这里做一下
		int i1 = 0;
		int j1 = this.nodes.length-1;
		while(i1 < j1) {
			int temp = topol[i1];
			topol[i1] = topol[j1];
			topol[j1] = temp;
			i1++;
			j1--;
		}
		
		//接下来计算各个事件的最晚发生时间，这是按照事件的逆序计算的，从终点开始
		for(int i = 0; i < this.nodes.length; i++) {
			eventLastest[i] = eventEarlist[topol[0]]; //各个事件的最晚开始时间都设置为终止事件的最早开始时间（也是它的最晚开始时间）
		};
		for(int i = 1; i < topol.length; i++) {
			int node = topol[i];
			ArrayList<GraphEdge> connectedNodes = this.connections.get(node);
			for(GraphEdge connectedNode : connectedNodes) {
				if(eventLastest[connectedNode.targetNode] - connectedNode.edgeValue < eventLastest[node]  ) {
					eventLastest[node] = eventLastest[connectedNode.targetNode] - connectedNode.edgeValue;
				}
			}
		}
		
		//找出关键路径上的边
		//一条边（一个活动）<i,j>,它的最早开始时间是eventEarliest[i],最晚开始时间是eventLastest[j] 减去 边长
		//如果最早等于最晚，那么这条边在关键路径上
		for(Map.Entry<Integer, ArrayList<GraphEdge>> edge: this.connections.entrySet()) {
			int startEvent = edge.getKey();
			ArrayList<GraphEdge> activities = edge.getValue();
			if(activities == null || activities.isEmpty()) {
				continue;
			}
			for(GraphEdge activity : activities) {
				int endEvent = activity.targetNode;
				if(eventEarlist[startEvent] == eventLastest[endEvent] - activity.edgeValue) {
					System.out.println(startEvent + "-" + endEvent);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		//确定一个顶点与其它所有顶点的最短路径，Dijkstra算法
		Integer[] nodes = {1,2,3,4,5,6,7};
		int[][] edges = new int[7][7];
		
		for(int i = 0; i < 7; i++) {
			for(int j = 0; j < 7; j++) {
				if(i==j) {
					edges[i][j] = 0;
				}else
					edges[i][j] = 9999;
			}
		}
		
		edges[0][1] = 13;
		edges[0][2] = 8;
		edges[0][4] = 30;
		edges[0][6] = 32;
		edges[1][5] = 9;
		edges[1][6] = 7;
		edges[2][3] = 5;
		edges[3][4] = 6;
		edges[4][5] = 2;
		edges[5][6] = 17;
		
		DirGraph dg = new DirGraph(nodes, edges);
		System.out.println("Node 0 to others' short path:");
		dg.minCostRouteDijkstra(0);
		
		//计算任意两个顶点之间的最短路径，Floyd算法
		Integer[] n = {1,2,3};
		int[][] e = new int[3][3];
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(i==j) {
					e[i][j] = 0;
				}else
					e[i][j] = 9999;
			}
		}
		
		e[0][1] = 10;
		e[0][2] = 5;
		e[1][0] = 4;
		e[2][1] = 2;
		
		DirGraph dgFloyd = new DirGraph(n,e);
		System.out.println("All short pathes between nodes:");
		dgFloyd.minCostRouteFloyd();
		
		//拓扑排序测试程序
		Integer[] n2 = {1,2,3,4,5,6,7};
		Map<Integer,ArrayList<GraphEdge>> connections = new HashMap<Integer, ArrayList<GraphEdge>>();
		
		ArrayList<GraphEdge> connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(1));
		connection.add(new GraphEdge(2));
		connections.put(0, connection);
		
		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(3));
		connection.add(new GraphEdge(4));
		connection.add(new GraphEdge(5));
		connections.put(1, connection);
		
		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(4));
		connection.add(new GraphEdge(6));
		connections.put(2, connection);
		
		connection = new ArrayList<GraphEdge>();
		connections.put(3, connection);
		
		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(6));
		connections.put(4, connection);
		
		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(6));
		connections.put(5, connection);
		
		connection = new ArrayList<GraphEdge>();
		connections.put(6, connection);
		
		DirGraph topol = new DirGraph(n2,connections);
		System.out.println("Topol Sort:");
		topol.topolSort();
		
		//关键路径测试程序
		Integer[] n3 = {1,2,3,4,5,6,7,8,9};
		connections = new HashMap<Integer, ArrayList<GraphEdge>>();
		
		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(1,5));
		connection.add(new GraphEdge(2,7));
		connections.put(0, connection);

		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(3,3));
		connections.put(1, connection);

		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(3,6));
		connection.add(new GraphEdge(4,3));
		connections.put(2, connection);
		
		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(4,4));
		connection.add(new GraphEdge(5,4));
		connection.add(new GraphEdge(6,4));
		connections.put(3, connection);

		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(6,2));
		connection.add(new GraphEdge(7,5));
		connections.put(4, connection);

		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(8,4));
		connections.put(5, connection);

		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(8,5));
		connections.put(6, connection);

		connection = new ArrayList<GraphEdge>();
		connection.add(new GraphEdge(8,2));
		connections.put(7, connection);
		
		DirGraph keyPathGraph = new DirGraph(n3,connections);
		System.out.println("Key path: ");
		keyPathGraph.keyPath();
	}
}
