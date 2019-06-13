package algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/*
 * 无向图的各种操作：深度优先遍历；广度优先遍历。它们的实现都在父类Graph里
 */
public class NonDirGraph extends Graph {
	
	public NonDirGraph(Integer[] nodes, Map<Integer,ArrayList<Integer>> connections) {
		this.nodes = nodes;
		this.connections = connections;
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
