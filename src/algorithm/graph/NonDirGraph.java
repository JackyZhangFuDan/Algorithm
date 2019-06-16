package algorithm.graph;

import java.util.ArrayList;
import java.util.Map;

/*
 * 无向图的各种操作：深度优先遍历；广度优先遍历。它们的实现都在父类Graph里
 */
public class NonDirGraph extends Graph {
	
	public NonDirGraph(Integer[] nodes, int[][] connectionMatrix) {
		super(nodes, connectionMatrix);
	}
	
	public NonDirGraph(Integer[] nodes, Map<Integer, ArrayList<GraphEdge>> connections) {
		super(nodes,connections);
	}
	
	/*
	 * 用Prim算法来生成连通无向图的最小代价生成树
	 * 思路：设置两个数组：
	 * 数组1 - closetNodes，元素值代表离当前节点（该数组下标表示）最近（代价最小）的节点；算法结束后它直接给出了所选取的边；
	 * 数组2 - lowcost，用于存储所有节点（该数组下标表示）与已被树包含的节点集合之间的边的最小代价；
	 */
	public void minCostTreeByPrim() {
		int[] closestNodes = new int[this.nodes.length];
		int[] lowcost = new int[this.nodes.length];

		System.out.print("selected nodes: 0");
		for(int i = 1; i < this.nodes.length; i++) {
				closestNodes[i] = 0;
				lowcost[i] = this.connectionMatrix[0][i];
		}
		
		for(int i = 1; i< this.nodes.length; i++) {
			int minCost = 9999;
			int minCostNode = -1;
			for(int j = 0; j < this.nodes.length; j++) {
				if(lowcost[j] != 0 && lowcost[j] < minCost) {
					minCost = lowcost[j];
					minCostNode = j;
				}
			}
			
			System.out.print("->"+minCostNode);
			lowcost[minCostNode] = 0;
			
			for(int j = 0; j < this.nodes.length; j++) {
				if(lowcost[j] != 0 && lowcost[j] > this.connectionMatrix[minCostNode][j]) {
					lowcost[j] = this.connectionMatrix[minCostNode][j];
					closestNodes[j] = minCostNode;
				}
			}
		}
		
		//打印选取的边
		System.out.println("\nselected edges:");
		for(int i = 0; i < this.nodes.length; i++) {
			System.out.println(closestNodes[i] + "-" + i);
		}
	}
	
	/*
	 * 用Kruskal算法为连通无向图生成最小代价生成树
	 * 思路：每次选取代价最小的一个边，对于有n个节点的连通无向图只要选取前n-1条代价最小边就完成了。
	 */
	private void minCostTreeByKruskal() {
		
		for(int i = 0; i < this.nodes.length-1; i++) {
			int minCost = 9999;
			int k1 = -1;
			int k2 = -1;
			for(int j = 0; j < this.nodes.length; j ++) {
				for(int k = 0; k < this.nodes.length; k++) {
					if(this.connectionMatrix[j][k] == 0) {
						continue;
					}
					if(this.connectionMatrix[j][k] < minCost) {
						minCost = this.connectionMatrix[j][k];
						k1 = j;
						k2 = k;
					}
				}
			}
			
			System.out.println(k1 + ":" + k2);

			for(int j = 0; j < this.nodes.length; j ++) {
				if(this.connectionMatrix[k1][j] != 0) {
					continue;
				}
				for(int k = 0; k < this.nodes.length; k++) {
					if(this.connectionMatrix[k2][k] != 0) {
						continue;
					}
					this.connectionMatrix[j][k] = 0;
					this.connectionMatrix[k][j] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Integer[] nodes = {1,2,3,4,5,6};
		int[][] edges = new int[6][6];
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(i==j) {
					edges[i][j] = 0;
				}else
					edges[i][j] = 9999;
			}
		}
		
		edges[0][1] = 6;
		edges[1][0] = 6;
		edges[0][2] = 1;
		edges[2][0] = 1;
		edges[0][3] = 5;
		edges[3][0] = 5;
		edges[1][2] = 5;
		edges[2][1] = 5;
		edges[1][4] = 3;
		edges[4][1] = 3;
		edges[2][3] = 5;
		edges[3][2] = 5;
		edges[2][4] = 6;
		edges[4][2] = 6;
		edges[2][5] = 4;
		edges[5][2] = 4;
		edges[3][5] = 2;
		edges[5][3] = 2;
		edges[4][5] = 6;
		edges[5][4] = 6;
		
		NonDirGraph ndg = new NonDirGraph(nodes, edges);
		System.out.println("Generate low cost tree by Prim:");
		ndg.minCostTreeByPrim();
		
		System.out.println("Generate low cost tree by Kruskal:");
		ndg.minCostTreeByKruskal();
	}
}
