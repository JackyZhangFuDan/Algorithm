package algorithm.tree.answertree;

import java.util.Stack;

/*
 * 有n*n的棋盘，要求放置n个皇后在棋盘上，并且这些皇后之间不冲突。两个皇后在如下情况下冲突：它们处在同一行，或同一列或同一斜线上。
 */
public class NQueensProblem {
	
	private int n;
	
	public NQueensProblem(int size){
		this.n = size;
	}
	
	public void answer(){
		
		int solutionCount = 0;
		Stack<Position> stack = new Stack<Position>();
		
		Position pos = null;
		for(int i = 0; i < n; i++){
			pos = new Position();
			pos.row = 0;
			pos.col = i;
			pos.isOnPath = false;
			stack.push(pos);
		}
		
		while(!stack.isEmpty()){
			pos = stack.pop();
			while(pos.isOnPath && !stack.isEmpty()){
				pos = stack.pop();
			}
			
			if(pos.isOnPath && stack.isEmpty()){
				break;
			}
			
			if( !this.canPutHere(pos, stack) ){
				continue;
			}
			
			if(pos.row == n - 1){
				pos.isOnPath = true;
				stack.push(pos);
				this.printResult(solutionCount++,stack);
				stack.pop();
				continue;
			}else{
				pos.isOnPath = true;
				stack.push(pos);
				int nextRow = pos.row + 1;
				for(int j = 0; j < n ; j++){
					pos = new Position();
					pos.row = nextRow;
					pos.col = j;
					pos.isOnPath = false;
					stack.push(pos);
				}
			}
		}
	}
	
	/*
	 * 判断是否可以在一个位置放置一个皇后而不与已经放置的皇后们冲突
	 * 这里有意思的是|i-k| == |j-l|这个判断条件，(i,j)和(k,l)是两个皇后的位置
	 */
	public boolean canPutHere(Position targetPosition, Stack<Position> stack){
		Position pos = null;
		
		for(int i = 0 ; i < stack.size(); i++){
			pos = stack.get(i);
			if(pos.isOnPath){
				if(targetPosition.col == pos.col || Math.abs(targetPosition.col - pos.col ) == Math.abs(targetPosition.row - pos.row)){
					return false;
				}
			}else{
				continue;
			}
		}
		
		return true;
	}
	
	/*
	 * 打印结果
	 */
	public void printResult(int solutionCount,Stack<Position> stack){
		Position pos = null;
		System.out.println(solutionCount + "th solution is found:");
		for(int i = 0 ; i < stack.size(); i++){
			pos = stack.get(i);
			if(pos.isOnPath){
				System.out.println("Row: " + pos.row + " Column: " + pos.col);
			}
		}
	}
	
	public static void main(String[] args) {
		NQueensProblem nqp = new NQueensProblem(8);
		nqp.answer();
	}
	
	public static class Position{
		public int row;
		public int col;
		
		public boolean isOnPath;
	}

}
