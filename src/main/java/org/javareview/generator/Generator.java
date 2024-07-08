package org.javareview.generator;

import org.javareview.generator.utils.SQLCreator;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class Generator {

    // {class: className, method: methodName}
    private static Map<String, String> getNameOfCallingClasAndMethod() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        System.out.println("[INFO]: Elements in stackTrace: ");
        for (StackTraceElement element : stackTrace) {
            System.out.println(element);
        }

        if (stackTrace.length < 3) { //check this statement
            System.err.println("[ERROR]: There are not enough elements in the array to safely access");
            System.exit(1);
        }

        Map<String, String> classAndMethod = new HashMap<>();
        classAndMethod.put("class", getCleanClassName(stackTrace[3].getClassName()));
        classAndMethod.put("method", stackTrace[3].getMethodName());

        return classAndMethod;
    }

    public static String generate(Connection connection) {
        Map<String, String> methodNameAndClass = getNameOfCallingClasAndMethod();
        if (methodNameAndClass.size() < 2) {
            System.err.println("[ERROR]: Problem with the size of the hashmap that contains the className and methodName");
            System.exit(1);
        }

        for (Map.Entry<String, String> nameAndClass : methodNameAndClass.entrySet()) {
            System.out.println(nameAndClass.getKey() + " * " + nameAndClass.getValue());
        }

        String methodName = methodNameAndClass.get("method");
        Parser.parse(methodName);
        System.out.print("[INFO]: Analysis passed\n");
        System.out.println("method name: " + methodName);
        SQLCreator sqlCreator = new SQLCreator(connection);
        return sqlCreator.createSql(methodName);
    }


    /*
        Expected this patron "org.javareview.service.PersonRepositoryImpl"
                              -----------------------^    ^--------------
     */
    private static String getCleanClassName(String dirtyClassName) {
        System.out.println(dirtyClassName);
        int startIndex = dirtyClassName.lastIndexOf(".") + 1;
        int endIndex = dirtyClassName.indexOf("Repository");
        return dirtyClassName.substring(startIndex, endIndex);
    }

}
