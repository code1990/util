import org.junit.*;

import static org.junit.Assert.assertEquals;

/*@Test： 	标识一条测试用例。 (A) (expected=XXEception.class)   (B) (timeout=xxx)
@Ignore:  	忽略的测试用例。
@Before:  	每一个测试方法之前运行。
@After :  	每一个测试方法之后运行。
@BefreClass 	 所有测试开始之前运行。
@AfterClass  	所有测试结果之后运行。
@assertArrayEquals(expecteds, actuals) 	查看两个数组是否相等。
@assertEquals(expected, actual) 	查看两个对象是否相等。类似于字符串比较使用的equals()方法。
@assertNotEquals(first, second) 	查看两个对象是否不相等。
@assertNull(object) 	查看对象是否为空。
@assertNotNull(object) 	查看对象是否不为空。
@assertSame(expected, actual) 	查看两个对象的引用是否相等。类似于使用“==”比较两个对象。
@assertNotSame(unexpected, actual) 	查看两个对象的引用是否不相等。类似于使用“!=”比较两个对象。
@assertTrue(condition) 	查看运行结果是否为true。
@assertFalse(condition) 	查看运行结果是否为false。
@assertThat(actual, matcher) 	查看实际值是否满足指定的条件。
@fail() 	让测试失败。
*/
public class JunitDemo {

    /*@Test 用来注释一个普通的方法为一条测试用例。*/
    @Test
    public void myFirstTest() {
        /*assertEquals() 方法用于断言两个值是否相关。*/
        assertEquals(2+2, 4);
        assertEquals(2+2, 3);
    }

    @Test
    public void testAdd() {
       int result = add(2,2);
        assertEquals(result, 4);
    }

    /**
     * 计算并返回两个数相除的结果
     */
    public int division(int a, int b){
        return a / b;
    }
    /**
     * 计算并返回两个参数的和
     */
    public int add(int x ,int y){
        return x + y;
    }
    //验证超时
    /*1、在 testAdd() 用例中设置 timeout=100 , 说明的用例的运行时间不能超过 100 毫秒，
    但故意在用例添加 sleep() 方法休眠 101 毫秒，所以会导致用例失败。*/
    @Test(timeout=100)
    public void testAdd1() throws InterruptedException {
        Thread.sleep(101);
        add(1, 1);
    }

    //验证抛出异常
    /*2、在 Java 中被除数不能为0，所以 8/0 会报 ArithmeticException 异常,
    在 @Test 中设置 expected=ArithmeticException.class ，说明抛该异常符合预期。*/
    @Test(expected=ArithmeticException.class)
    public void testDivision() {
        division(8, 0);
    }

    // 跳过该条用例
    /*3、@Ignore 表来标识该用例跳过，不管用例运行成功还是失败。*/
    @Ignore
    @Test
    public void testAdd2() {

        int result = add(2,2);
        assertEquals(result, 5);
    }

    //在当前测试类开始时运行。
    @BeforeClass
    public static void beforeClass(){
        System.out.println("-------------------beforeClass");
    }

    //在当前测试类结束时运行。
    @AfterClass
    public static void afterClass(){
        System.out.println("-------------------afterClass");
    }

    //每个测试方法运行之前运行
    @Before
    public void before(){
        System.out.println("=====before");
    }

    //每个测试方法运行之后运行
    @After
    public void after(){
        System.out.println("=====after");
    }

    @Test
    public void testAdd11() {
        int result=add(5,3);
        assertEquals(8,result);
        System.out.println("test Run testadd1");
    }

    @Test
    public void testAdd22() {
        int result=add(15,13);
        assertEquals(28,result);
        System.out.println("test Run testadd2");
    }
}
