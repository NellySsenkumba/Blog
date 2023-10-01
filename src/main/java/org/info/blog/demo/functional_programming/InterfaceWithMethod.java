package org.info.blog.demo.functional_programming;

import java.util.function.Function;

public interface InterfaceWithMethod extends Function<String, String> {
    static String logicMethod() {
        return "test";
    }

    static InterfaceWithMethod returnLumbda(){
        return name -> name.toUpperCase();
    }

    default String methodWithBodyInInterface() {
        return "This is new ";
    }

}

