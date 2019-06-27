package algorithm.leetcode.bracketgeneration;

import java.util.Stack;

import algorithm.tree.answertree.BagProblem;
import algorithm.tree.answertree.BagProblem.Good;

/*
 * 试着用解答树的一般方法来解答背包问题
 * 背包问题：有一个背包，n种货物，背包容量有限，货物轻重与价值各不相同，求背包最多可以装多少价值的货物
 */
public class SolutionForBagProblem {
	private Good[] goods = null;
	
	public SolutionForBagProblem(Good[] goods) {
		this.goods = goods;
	}
	
	public void solve(int bagCapacity) {
		//sort the goods according to price
		for(int i = 0 ; i < goods.length; i++) {
			Good goodI = this.goods[i];
			double price = goodI.value / goodI.weight;
			
			for(int j = i + 1; j < goods.length; j++) {
				if(goods[j].value / goods[j].weight > price) {
					Good tmp = this.goods[j];
					this.goods[j] = goodI;
					goodI = tmp;
				}
			}
			this.goods[i] = goodI;
		}
		
		Stack<Node> stack = new Stack<Node>();
		Stack<Integer> visitFlag = new Stack<Integer>();
		
		Node node = new Node();
		node.currentValue = 0;
		node.goodsCount   = -1;
		node.totalWeight  = 0;
		
		stack.push(node);
		visitFlag.push(0);
		
		int maxValue = 0;
		
		while(!stack.isEmpty()) {
			node = stack.peek();
			int vFlag = visitFlag.peek();
			boolean needPop = false;
			
			if(vFlag == 0) {
				int goodIdx = node.goodsCount + 1;
				if(goodIdx == this.goods.length) {
					if(node.currentValue > maxValue) {
						maxValue = node.currentValue;
					}
					needPop = true;
				}else {
					if(bagCapacity - node.totalWeight >= this.goods[goodIdx].weight) {
						Node node2 = new Node();
						node2.goodsCount = goodIdx;
						node2.currentValue = node.currentValue + this.goods[goodIdx].value;
						node2.totalWeight  = node.totalWeight + this.goods[goodIdx].weight;
						stack.push(node2);
						visitFlag.pop();
						visitFlag.push(1);
						visitFlag.push(0);
					}else {
						Node node2 = new Node();
						node2.goodsCount = goodIdx;
						node2.currentValue = node.currentValue ;
						node2.totalWeight  = node.totalWeight ;
						stack.push(node2);
						visitFlag.pop();
						visitFlag.push(2);
						visitFlag.push(0);
					}
				}
			}else if(vFlag == 1) {				
				Node node2 = new Node();
				node2.goodsCount = node.goodsCount + 1;
				node2.currentValue = node.currentValue ;
				node2.totalWeight  = node.totalWeight ;
				stack.push(node2);
				visitFlag.pop();
				visitFlag.push(2);
				visitFlag.push(0);
			}else if(vFlag == 2) {
				needPop = true;
			}
			
			if(needPop) {
				stack.pop();
				visitFlag.pop();
			}
		}
		
		System.out.println("The max value is: " + maxValue);
	}
	
	public static void main(String[] args) {
		Good[] goods = new Good[5];
		
		Good g = new Good();
		g.weight = 8;
		g.value = 8;
		goods[0] = g;
		
		g = new Good();
		g.weight = 16;
		g.value = 14;
		goods[1] = g;
		
		g = new Good();
		g.weight = 12;
		g.value = 7;
		goods[2] = g;
		
		g = new Good();
		g.weight = 17;
		g.value = 11;
		goods[3] = g;
		
		g = new Good();
		g.weight = 21;
		g.value = 16;
		goods[4] = g;
		
		SolutionForBagProblem bp = new SolutionForBagProblem(goods);
		bp.solve(37);
	}
	
	public static class Node{
		int currentValue;
		int goodsCount = 0;
		int totalWeight = 0;
	}
	
	public static class Good{
		int value = 0;
		int weight = 0;
	}

}
