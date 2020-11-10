package java3.lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BatchTesting {

    public static void start(String className) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        start(Class.forName(className));
    }

    public static void start(Class<?> testClass) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("Now testing " + testClass.getName());

        // here the magic begins!

        Method[] methods = testClass.getDeclaredMethods();
        // check BeforeSuite

        Method before = null;
        Method after = null;
        List<TestingMethod> sortedMethods = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (before == null) {
                    before = method;
                } else {
                    throw new RuntimeException("@BeforeSuite annotation must be once presented!");
                }
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                if (after == null) {
                    after = method;
                } else {
                    throw new RuntimeException("@AfterSuite annotation must be once presented!");
                }
            } else if (method.isAnnotationPresent(Test.class)) {
                Test testAnnotation = method.getAnnotation(Test.class);
                sortedMethods.add(new TestingMethod(testAnnotation.priority(), method));
            }
        }

        sortedMethods.sort(Comparator.comparingInt(o -> o.priority));

        Object instance = testClass.getConstructor().newInstance();
        if (before != null) {
            System.out.println("Now testing @BeforeSuite: " + before.getName());
            before.invoke(instance);
        }

        for (TestingMethod testingMethod : sortedMethods) {
            System.out.println("Now testing @Test: " + testingMethod.method.getName() + "; priority: " + testingMethod.priority);
            testingMethod.method.invoke(instance);
        }

        if (after != null) {
            System.out.println("Now testing @AfterSuite: " + after.getName());
            after.invoke(instance);
        }

    }

    static class TestingMethod {
        int priority;
        Method method;

        public TestingMethod(int priority, Method method) {
            this.priority = priority;
            this.method = method;
        }
    }
}
