package languagefeature;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;

//这样�?�的�?�??是对象以�?�对象内部所有引用到的对象都是�?�串行化的，�?�则，就需�?仔细考察那些�?�?�串行化的对象�?��?�设�?transient，从而将之排除在�?制过程之外。
class Teacher implements Serializable{
	String name;
	int age;
	Teacher(String name,int age){
		this.name=name;
		this.age=age;
	}
}

class Student2 implements Serializable{
	String name;//常�?对象
	int age;
	Teacher t;//学生1和学生2的引用值都是一样的。
	
	Student2(String name,int age,Teacher t){
		this.name=name;
		this.age=age;
		this.t=t;
	}
	
	public Object deepClone() throws IOException,OptionalDataException,ClassNotFoundException{//将对象写到�?里
		ByteArrayOutputStream bo=new ByteArrayOutputStream();
		ObjectOutputStream oo=new ObjectOutputStream(bo);
		oo.writeObject(this);//从�?里读出�?�
		ByteArrayInputStream bi=new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi=new ObjectInputStream(bi);
		return(oi.readObject());
	}

}

public class JavaDeepCloneBySerialize {
	public static void main(String[] args){ 
		Teacher t=new Teacher("tangliang",30);
		Student2 s1=new Student2("zhangsan",18,t);
		Student2 s2 = null;
		try {
			s2 = (Student2)s1.deepClone();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return;
		}
		
		s2.t.name="tony";
		s2.t.age=40;
		System.out.println("name="+s1.t.name+","+"age="+s1.t.age);//学生1的�?师�?改�?�
		}

}
