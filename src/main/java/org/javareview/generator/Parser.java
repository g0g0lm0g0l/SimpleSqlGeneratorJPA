package org.javareview.generator;

import org.javareview.service.PersonRepositoryImpl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    //TODO: implement parser for className

    public static void parse(String functionName) {
        List<String> permittedMethods = new ArrayList<>();
        Method[] methods = PersonRepositoryImpl.class.getDeclaredMethods();

        System.out.println("[INFO]: method names ==>");
        Arrays.stream(methods).forEach(method -> {
            permittedMethods.add(method.getName());
            System.out.println(method.getName());
        });

        if (!permittedMethods.contains(functionName)) {
            System.err.printf("[ERROR]: parsing error %s\n", Parser.class.getName());
            System.exit(1);
        }
        System.out.printf("method %s passed parse()\n", functionName);
    }
}
