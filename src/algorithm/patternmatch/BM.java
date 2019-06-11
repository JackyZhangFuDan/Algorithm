package algorithm.patternmatch;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BM {
	
	private Character[] target;
	private Character[] pattern;
	
	private Map<Integer, Integer> suffixPreviousPosition = new HashMap<Integer, Integer>();
	private Map<String, Integer> elementPreviousPosition = new HashMap<String, Integer>();
	
	public BM(Character[] t, Character[] p){
		this.target = t;
		this.pattern = p;
	}
	
	/*
	 * 构造一张坏字符位移速查表，它只根据pattern就可以构建出来
	 */
	private void calculatgeElementPreviousPosition(){
		for(int i = this.pattern.length - 1; i >= 0; i--){
			for(int j = i - 1; j >= 0; j --){
				Character c = this.pattern[j];
				if(c == this.pattern[i]){
					continue;
				}
				String key = i + "-" + c; //注意key的内容，因为position受字符以及起始位置的影响，所以我们把它们都做到key里面
				if(!this.elementPreviousPosition.containsKey(key)){
					this.elementPreviousPosition.put(i+"-"+c, j);
				}
				
			}
		}
	}
	
	/*
	 * 这里计算一下Pattern中各个后缀在前面重复的位置，当然是要最后那次
	 * 目的是在“好后缀”场景下使用这个信息
	 */
	private void calculateSuffixPreviousPosition(){
		for(Integer i = this.pattern.length - 1; i > 0; i--){
			Integer lengthOfSuffix = this.pattern.length - i;
			for(Integer j = i - 1; j >= lengthOfSuffix - 1; j--){
				boolean allMatch = true;
				for(int k = 0; k < lengthOfSuffix; k++){
					if(this.pattern[j-k] != this.pattern[this.pattern.length - 1 - k]){
						allMatch = false;
						break;
					}
				}
				if(allMatch){
					this.suffixPreviousPosition.put(i, j - lengthOfSuffix + 1);
					break;
				}
			}
		}
		
		System.out.println("Calculated suffix last occurrence positions:");
		Iterator<Integer> keyIt = this.suffixPreviousPosition.keySet().iterator();
		while(keyIt.hasNext()){
			int suffixIdx = keyIt.next();
			int occurrencePosition = this.suffixPreviousPosition.get(suffixIdx);
			for(int i = suffixIdx; i < this.pattern.length; i++){
				System.out.print(this.pattern[i]);
			}
			System.out.print(" : " + occurrencePosition + "\t\n");
			
		}
	}
	
	public void doMatch(){
		System.out.println("Pattern matching started...");
		if(this.target.length < this.pattern.length){
			System.out.println("no match");
			return;
		}
		
		this.calculateSuffixPreviousPosition();
		this.calculatgeElementPreviousPosition();
		
		//start代表target数组的下标，随着比较的进行，这个start会逐渐增大-代表向target右端移动
		int start = this.pattern.length - 1;
		System.out.println("Compare from " + (start) + " character of source string");
		
		boolean matched = false;
		
		while(!matched && start < this.target.length){
			int i=this.pattern.length-1;
			for(; i >= 0; i--){
				if(this.pattern[i] != this.target[start - (this.pattern.length - 1 - i)]){

					int shiftDis = 0;
					
					//按照坏字符策略计算右移距离
					int nskip = 0;
					String key = i+"-"+this.target[start - (this.pattern.length - i -1)];
					if(this.elementPreviousPosition.containsKey(key)){
						nskip = i - this.elementPreviousPosition.get(key);
					}else{
						nskip = i + 1;
					}
					
					//按照好后缀策略计算右移距离
					if(i < this.pattern.length-1){
						int suffixIndex = i+1;
						while(
								suffixIndex < this.pattern.length && 
								!this.suffixPreviousPosition.containsKey(suffixIndex)
						){
							suffixIndex++;
						}
						if(suffixIndex < this.pattern.length){
							shiftDis = suffixIndex - this.suffixPreviousPosition.get(suffixIndex);
						}else{
							shiftDis = suffixIndex;
						}
					}
					
					//选取右移多的那个距离
					if(shiftDis < nskip){
						shiftDis = nskip; 
					}
					
					start = start + shiftDis;
					System.out.println("Compare from character at index " + (start) + " of source string");
					
					break;
				}
				
			};
			
			if(i < 0){
				matched = true;
			}
		};
		
		if(matched){
			System.out.println("Matched success, start index: " + (start-this.pattern.length+1));
		}else{
			System.out.println("NO match detected.");
		}
		
		System.out.println("Pattern matching end");
	}
	
	public static void main(String[] args) {
		//fail case
		BM b = new BM(
				new Character[]{'a','m','n','l','m','n','l','t','t','n','l','p','d','z','m','n','l','m','l','c','e','g','h','p','d','z','m','n','l','m','l','b'},
				new Character[]{'p','d','z','m','n','l','m','n'}
		);
		b.doMatch();
		
		//success case
		b = new BM(
				new Character[]{'a','m','n','l','m','n','l','t','t','n','l','p','d','z','m','n','l','m','l','c','e','g','h','p','d','z','m','n','l','m','n','b'},
				new Character[]{'p','d','z','m','n','l','m','n'}
		);
		b.doMatch();
	}

}
