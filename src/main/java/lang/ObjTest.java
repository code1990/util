package lang;

//定义一个公共的类 类名为ObjTest
public class ObjTest {

    //定义类的成员变量 是类的属性 是类的行为
    private int age;
    private String name;

    //定义一个无参数的构造方法 方法名与类名相同  构造方法是创建对象的方法
    public ObjTest(){

    }
    //有参数的构造方法
    public ObjTest(int age){

    }
    //无返回值的方法 被static修饰的方法也叫做类方法
    public static void getInfo(){
        System.out.println("age");
    }
    //有参数的静态方法
    public static int getAge(){
        return 1;
    }
    //常量
    private static final double PI = 3.14;

    //无返回值的一般方法
    public void getInfomation(){
        System.out.println("age+name");
    }
    //有返回值的一般方法
    public String getName(){
        return "Jim Green";
    }

    //main方法是程序的入口 在需要测试的时候使用
    public static void main(String[] args) {
        //新建一个对象 对象名称为t 对象类型为ObjTest 调用的是无参数构造方法
        ObjTest t = new ObjTest();
        //新建一个对象 调用的是有参数构造方法
        ObjTest t2 = new ObjTest(12);

        //调用类方法即 static修饰的方法 类名.方法名
        ObjTest.getInfo();
        //调用一般方法  对象名.方法名
        t.getInfomation();
    }
}
