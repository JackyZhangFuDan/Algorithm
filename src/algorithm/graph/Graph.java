package algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Graph {
	
	protected Integer[] nodes;
	protected Map<Integer, ArrayList<Integer>> connections = new HashMap<Integer,ArrayList<Integer>>();
	
	public abstract void depthFirstVisit();
	public abstract void widthFirstVisit();
}
