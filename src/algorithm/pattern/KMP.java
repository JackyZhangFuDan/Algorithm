package algorithm.pattern;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class KMP {
	
	private Map<Integer, Integer> failLinkIdx = new HashMap<Integer, Integer>();

	private Character[] target;
	private Character[] pattern;
	
	public KMP(Character[] t, Character[] p){
		this.target = t;
		this.pattern = p;
	}
	
	/*
	 * 计算失败链接值，pattern[0]的失败链接值设置为-1，其它的在此基础上计算
	 */
	private void calculateFailLinkIdx(){
		
		this.failLinkIdx.put(0, -1);
		
		for(Integer i=1; i < this.pattern.length; i++){
			int preElementFailLink = this.failLinkIdx.get(i-1);
			if(preElementFailLink >= 0 && this.pattern[preElementFailLink] != this.pattern[i-1]){
				preElementFailLink = this.failLinkIdx.get(preElementFailLink);
			}
			this.failLinkIdx.put(i, preElementFailLink+1);
		}
		
		System.out.println("Calculated fail link index:");
		Iterator<Integer> keyIt = this.failLinkIdx.keySet().iterator();
		while(keyIt.hasNext()){
			int idx = keyIt.next();
			int faiLinkIdx = this.failLinkIdx.get(idx);
			System.out.println("index: " + idx + ", fail link index: "+faiLinkIdx);
		}
	}
	
	/*
	 * 自己做的，啰嗦
	 */
	public void doMatch(){
		this.calculateFailLinkIdx();
		
		int start = 0;
		boolean matched = false;
	
		for(int i = 0; i < this.pattern.length && start < this.target.length;){
			if(this.pattern[i] != this.target[start]){
				int failLink = this.failLinkIdx.get(i);
				if(failLink < 0){
					start = start + 1;
					i = 0;
				}else{
					i = failLink;
				}
			}else{
				start = start + 1;
				i = i + 1;
				if(i == this.pattern.length){
					matched = true;
					break;
				}
			}
		}
		
		if(matched){
			System.out.println("matched success, index at target string: " + (start - this.pattern.length));
		}else{
			System.out.println("match fail");
		}
	}
	
	/*
	 * 教材上的写法，精辟
	 */
	public void doMatchBook(){
		this.calculateFailLinkIdx();
		
		int i = 0;
		int j = 0;
		while(i < this.target.length){
			while(j != -1 && this.pattern[j] != this.target[i]){
				j = this.failLinkIdx.get(j);
			}
			if(j == this.pattern.length - 1){
				System.out.println("Match success, index at target string: " + (i-this.pattern.length+1));
				return;
			}
			j++;
			i++;
		}
		
	}
	
	public static void main(String[] args) {
		//fail case
		KMP b = new KMP(
				new Character[]{'a','m','n','l','m','n','l','t','t','n','l','p','d','z','m','n','l','m','l','c','e','g','h','p','d','z','m','n','l','m','l','b'},
				new Character[]{'a','b','a','b','a','b'}
		);
		b.doMatch();
		
		//success case
		b = new KMP(
				new Character[]{'a','m','n','l','m','n','l','t','t','n','l','p','d','z','m','n','l','m','l','c','e','g','h','p','d','z','m','n','l','m','n','b'},
				new Character[]{'p','d','z','m','n','l','m','n'}
		);
		b.doMatch();
		b.doMatchBook();
		
	}

}
