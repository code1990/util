import org.junit.Test;
/*表示导入util下面所有的类 不建议此方式 需要什么类导入什么类*/
import java.util.*;
/*导入静态成员方法*/
import static java.lang.Math.max;
/*导入静态成员变量*/
import static java.lang.System.out;
public class Demo11 {

    @Test
    public void testPackage(){
        /*本地时间*/
        java.util.Date date1 = new java.util.Date();
        /*数据库服务器时间*/
        java.sql.Date date2 = new java.sql.Date(123);
    }

    @Test
    /*定义为final的方法不能被重写。*/
    public final void  testFinal(){
        /*由final定义的变量为常量,不能被修改*/
        final Double PI = 3.14;
        /*定义为final的类不能被继承。*/
        final class FinalClass{

        }
    }

    @Test
    public void testInnerClass(){
        /*成员内部类*/

        /*局部内部类 在方法中或者任意作用域中定义类*/
        class InnerClass2 implements OutInterface{
            InnerClass2(){

            }
        }
        /*匿名内部类*/

        /*静态内部类*/

        /*内部类的继承*/
    }

    /*成员内部类*/
    class innerClass{
        innerClass(){

        }
    }
    private int x;
    /*成员内部类向上转型为接口 隐藏内部类的实现过程*/
    private class PrivateInnerClass implements OutInterface{
        private int x=9;
        PrivateInnerClass(){

        }
        /*使用this关键字获取内部类与外部类的引用*/
        public void test(int x){
            x++;/*调用的是形参*/
            this.x++;/*内部类调用变量x*/
            Demo11.this.x++;/*调用外部类的变量x*/
        }
    }

    public innerClass doit(){
        /*返回一个匿名内部类*/
        return new innerClass(){};
    }

    /*静态内部类*/
    static class StaticInnerClass{

    }

}
interface OutInterface{

}
/*内部类的继承*/
class ClassC extends ClassA.ClassB {
    public ClassC(ClassA a){
        /*必须在构造方法中使用a.super*/
        a.super();
    }

}
class ClassA{
    class ClassB{

    }
}