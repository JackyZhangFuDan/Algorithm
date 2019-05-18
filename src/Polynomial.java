import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Polynomial {
	
	private String midExpression = null;
	private Map<Character, Integer> order;
	public static String endStr = "|";
	
	public Polynomial(String expression) {
		this.midExpression = expression;
		order = new HashMap<Character, Integer>();
		order.put('+',1);
		order.put('-',1);
		order.put('*',2);
		order.put('/',2);
		order.put('^',3);
		order.put('(',0);
	}
	
	/*
	 * 多项式的中缀转后缀
	 * 中缀：（a+b)*c^d^e
	 * 后缀：ab+cde^^*
	 */
	public String toPostExpression() {
		
		String result = "";
		
		Stack<Character> stk = new Stack<Character>();
		char topChar;
		char[] expressionCharArray = this.midExpression.toCharArray();
		for(int i = 0; i < expressionCharArray.length; i++) {
			char c = expressionCharArray[i];
			
			switch(c) {
			case '(':
				stk.add(c);
				break;
				
			case ')':
				topChar = stk.pop();
				while (topChar != '(') {
					result = result + topChar;
					topChar = stk.pop();
				}
				break;
				
			case '+':
			case '-':
			case '*':
			case '/':
			case '^':
				if(stk.isEmpty()) {
					stk.add(c);
				}else {
					topChar = stk.peek();
					while(
							(this.order.get(topChar) > this.order.get(c)) || 
							(this.order.get(topChar) == this.order.get(c) && c != '^')
					) {
						result = result + stk.pop();
						if(stk.isEmpty()) {
							break;
						}
						topChar = stk.peek();
					}
					stk.add(c);
				}
				break;
				
			default: //云算数
				result = result + c;
			}
		}
		
		while(!stk.isEmpty()) {
			result = result + stk.pop();
		}
		return result;
	}
	
	public static void main(String[] args) {
		Polynomial p = new Polynomial("(a+b*c)^d^(e*f/g)-h*i");
		System.out.println(p.toPostExpression());
	}
}
