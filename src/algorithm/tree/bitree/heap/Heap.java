package algorithm.tree.bitree.heap;

/*
 * 堆是一颗拟满二叉树，可以为空，不空时任何子树的根节点都是不小于其子结点的
 */
public class Heap {
	private int[] data;
	
	public Heap(int[] data) {
		this.data = data;
	}
	
	/*
	 * 这是一个对堆进行再调整的函数，用于构建初始堆和最后把堆排好序的过程
	 * index是子树的根节点，这棵树的左右子树都是堆，但这个根可能破坏了整棵树的堆性，所以要把根调整到合适的位置使得整棵树成为堆。
	 * maxIndexPlusOne是指用于存放堆的数组的最大下标值
	 */
	private void reOrg(int index, int maxIndexPlusOne) {
		if(index < 0) {
			return;
		}
		
		int j = index;
		while(2*j + 1 <= maxIndexPlusOne - 1) {
			int k = 2*j+1;
			
			if(2*j + 2 <= maxIndexPlusOne -1 && this.data[2*j+1] < this.data[2*j+2]) {
				k = 2*j + 2;
			}
			
			if(this.data[k] > this.data[j]) {
				int temp = this.data[j];
				this.data[j] = this.data[k];
				this.data[k] = temp;
				j = k;
			}else {
				break;
			}
		}
	}
	
	/*
	 * 把一个数组data转化为堆
	 */
	public void constructHeap() {

		int i = 1;
		int index = (this.data.length - 2) / 2;
		
		while(index >= 0) {
			this.reOrg(index,this.data.length);
			
			index = (this.data.length - 2) / 2 - i++;
		}
		
	}
	
	/*
	 * 假设data已经成为一个堆，那么我们把data变成一个从小到大排好序的数组
	 */
	public void sort() {
		for(int i = this.data.length - 1; i >= 0 ; i--) {
			int temp = this.data[0];
			this.data[0] = this.data[i];
			this.data[i] = temp;
			this.reOrg(0,i);
		}
	}
	
	public void printData() {
		for(int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}
	}
	
	public static void main(String[] args) {
		int[] data = {46,26,22,68,48,42,36,84,66};
		Heap h = new Heap(data);
		
		h.constructHeap();
		System.out.println("The constructed heap: ");
		h.printData();
		
		h.sort();
		System.out.println("The array after sorted by heap:");
		h.printData();

	}

}
