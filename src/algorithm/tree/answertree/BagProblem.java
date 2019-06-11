package algorithm.tree.answertree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/*
 * 解答树是一种暴力求解方法，原则上是用穷举法求解。
 * 背包问题：有一个背包，n种货物，背包容量有限，货物轻重与价值各不相同，求背包最多可以装多少价值的货物
 * 如果不要求给出装法，那么相对简单, 这里我们实现时保留了一份最佳装填法，如果是全部的话在此基础上改进也不是很难
 * 思路：先把货物按照单位价值从大到小排个序，然后从第一个一直考虑到最后一个：每个货物都有装和不装两个不同的决定，那么就衍生出“树”中当前节点的两个分支，穷尽了所有可能也就求出了最大值。
 * 这里可以利用剩余价值估算函数避免掉大量的必定失败的探测，所以只是理论上的穷举，现实执行时有很可能节省大量计算。
 */
public class BagProblem {
	private List<Good> goods = null;
	private List<Entry> maxValueBagContent = new ArrayList<Entry>();
	
	public BagProblem(List<Good> goods) {
		for(Good good : goods) {
			good.price = ((double)good.value) / good.weight;
		}
		
		this.goods = goods;
		this.sortGoodsAccordingToPrice();
	}
	
	public void answer(int bagCapacity) {
		if(goods == null || goods.size() == 0 || bagCapacity <= 0) {
			return;
		}
		
		Stack<Entry> stack = new Stack<Entry>();
		Entry entry = new Entry();
		entry.totalValue = 0;
		entry.totalWeight = 0;
		entry.elementCount = 1;
		stack.push(entry);
		if(bagCapacity <= this.goods.get(0).weight) {
			entry = new Entry();
			entry.totalWeight = this.goods.get(0).weight;
			entry.totalValue = this.goods.get(0).value;
			entry.elementCount = 1;
			stack.push(entry);
		}
		
		int maxValue = 0;
		int goodsCount = this.goods.size();
		
		while(!stack.isEmpty()) {
			boolean exit = false;
			entry = stack.pop();
			while(entry.elementCount < 0) {
				if(stack.isEmpty()) {
					exit = true;
					break;
				}
				entry = stack.pop();
			}
			
			if(exit) {
				break;
			}
			
			if(entry.elementCount == goodsCount) {
				if(entry.totalValue > maxValue) {
					maxValue = entry.totalValue;
					
					entry.elementCount = -entry.elementCount;
					stack.push(entry);
					this.copyBagContent(stack);
				}
			}else {
				entry.elementCount = -entry.elementCount;
				stack.push(entry);
				
				Entry entry2 = new Entry();
				entry2.totalValue  = entry.totalValue  ;
				entry2.totalWeight = entry.totalWeight ;
				entry2.elementCount = -entry.elementCount + 1;
				stack.push(entry2);
				
				Good good = this.goods.get(-entry.elementCount);
				if(good.weight + entry.totalWeight <= bagCapacity) {
					if(this.estimateMaxValue(entry.totalWeight + good.weight, entry.totalValue+good.value, bagCapacity, -entry.elementCount + 1) > maxValue) {
						entry2 = new Entry();
						entry2.totalValue  = entry.totalValue  + this.goods.get(-entry.elementCount).value;
						entry2.totalWeight = entry.totalWeight + this.goods.get(-entry.elementCount).weight;
						entry2.elementCount = -entry.elementCount + 1;
						stack.push(entry2);
					}
				}else {
					if(entry.totalValue > maxValue) {
						maxValue = entry.totalValue;
						this.copyBagContent(stack);
					}
				}
				
			}
		}
		
		System.out.println("The found max value is: "+maxValue);
		this.printFillPath();
	}
	
	/*
	 * 一个估计函数，判断加入后序货物是不是无论如何都不会超过现已得到的最大值了
	 * 它会极大地减少不必要的探测
	 */
	private double estimateMaxValue(int currentWeight, int currentValue, int bagCapacity, int consideredElementsCount) {
		if(consideredElementsCount == this.goods.size()) return 0;
		
		Good good = this.goods.get(consideredElementsCount);
		
		if(good.weight + currentWeight < bagCapacity) {
			return good.value + this.estimateMaxValue(currentWeight + good.weight, currentValue + good.value, bagCapacity, consideredElementsCount + 1);
		}else if(good.weight + currentWeight == bagCapacity){
			return good.value + currentValue;
		}else {
			return currentValue + good.value * (good.weight + currentWeight - bagCapacity) / good.weight;
		}
	}
	
	/*
	 * 给输入的货物按照单位价值从大到小排序，这样的话就不要求输入的货物是已排序的。
	 * 不是解答树算法核心，纯粹为了输入方便。
	 */
	private void sortGoodsAccordingToPrice() {
		for(int i = 0; i < this.goods.size() - 1; i++) {
			Good maxPriceGood = this.goods.get(i);
			for(int j = i+1; j < this.goods.size(); j++) {
				Good good = this.goods.get(j);
				if(good.price > maxPriceGood.price) {
					Good tempGood = good;
					this.goods.remove(j);
					this.goods.add(j, maxPriceGood);
					maxPriceGood = tempGood;
				}
			}
			this.goods.remove(i);
			this.goods.add(i, maxPriceGood);
		}
	}
	
	/*
	 * 记录一下当前得到的背包最大价值装填法
	 */
	private void copyBagContent(Stack<Entry> stack) {
		this.maxValueBagContent.clear();
		for(Entry entry: stack) {
			if(entry.elementCount < 0) {
				Entry e = new Entry();
				e.elementCount = - entry.elementCount;
				e.totalValue = entry.totalValue;
				e.totalWeight = entry.totalWeight;
				this.maxValueBagContent.add(entry);
			}
		}
	}
	
	/*
	 * 
	 */
	public void printFillPath() {
		if(this.maxValueBagContent == null || this.maxValueBagContent.isEmpty()) {
			return;
		}
		
		Entry e1 = this.maxValueBagContent.get(0);
		System.out.println("Good 1 (Value: " + (e1.totalValue) + "; Weight: " + (e1.totalWeight) + ")");
		
		for(int i = 1; i < this.maxValueBagContent.size(); i++) {
			Entry e2 = this.maxValueBagContent.get(i);
			System.out.println("Good "+(i+1)+" (Value: " + (e2.totalValue - e1.totalValue) + "; Weight: " + (e2.totalWeight - e1.totalWeight) + ")");
			e1 = e2;
		}
	}
	
	public static void main(String[] args) {
		List<Good> goods = new ArrayList<Good>();
		
		Good g = new Good();
		g.weight = 8;
		g.value = 8;
		goods.add(g);
		
		g = new Good();
		g.weight = 16;
		g.value = 14;
		goods.add(g);
		
		g = new Good();
		g.weight = 12;
		g.value = 7;
		goods.add(g);
		
		g = new Good();
		g.weight = 17;
		g.value = 11;
		goods.add(g);
		
		g = new Good();
		g.weight = 21;
		g.value = 16;
		goods.add(g);
		BagProblem bp = new BagProblem(goods);
		bp.answer(37);
		
	}
	
	public static class Entry{
		public int totalWeight;
		public int totalValue;
		public int elementCount;
	}
	
	public static class Good{
		public int weight;
		public int value;
		public double price;
	}
}
