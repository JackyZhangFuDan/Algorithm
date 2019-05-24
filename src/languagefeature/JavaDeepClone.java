package languagefeature;
class Professor1  implements Cloneable   
{    
     String name;    
     int age;    
     Professor1(String name,int age)    
     {    
        this.name=name;    
        this.age=age;    
     } 
     public Object clone()    
     {    
         Object o=null;    
        try    
         {    
             o=super.clone();    
         }    
        catch(CloneNotSupportedException e)    
         {    
             System.out.println(e.toString());    
         }    
        return o;    
     }    
}    

class Student1 implements Cloneable    
{    
     String name;// 常�?对象。    
     int age;    
     Professor1 p;// 学生1和学生2的引用值都是一样的。    
     Student1(String name,int age,Professor1 p)    
     {    
        this.name=name;    
        this.age=age;    
        this.p=p;    
     }    
    public Object clone()    
     {    
         Student1 o=null;    
        try    
         {    
             o=(Student1)super.clone();    
         }    
        catch(CloneNotSupportedException e)    
         {    
             System.out.println(e.toString());    
         }
        o.p = (Professor1)p.clone();
        return o;    
     }    
}    

public class JavaDeepClone {

	public static void main(String[] args)    
    {    
      Professor1 p=new Professor1("wangwu",50);    
      Student1 s1=new Student1("zhangsan",18,p);    
      Student1 s2=(Student1)s1.clone();    
      s2.p.name="lisi";    
      s2.p.age=30;    
      System.out.println("name="+s1.p.name+","+"age="+s1.p.age);//学生1的教授�?为lisi,age为30。    
    }   
}
