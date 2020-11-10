package java3.lesson7;

public class StringTests {

    public static String testValue;

    @BeforeSuite
    public static void before() {
        testValue = "Test value";
        System.out.println(testValue);
    }

    @BeforeSuite
    public static void beforeAgain() {
        testValue = "null value";
        System.out.println(testValue);
    }

    @AfterSuite
    public static void after() {
        testValue = "New test value";
        System.out.println(testValue);
    }

    @Test(priority = 1)
    public void secondFirstTest() {
        System.out.println("Second First test");
    }

    @Test(priority = 1)
    public void firstTest() {
        System.out.println("First test");
    }

    @Test(priority = 10)
    public void lastTest() {
        System.out.println("Last test");
    }

    @Test(priority = 5)
    public void test5() {
        System.out.println("Test 5");
    }

    @Test(priority = 3)
    public void test3() {
        System.out.println("Test 3");
    }

    @Test(priority = 1)
    public void test1() {
        System.out.println("Test 1");
    }

}
