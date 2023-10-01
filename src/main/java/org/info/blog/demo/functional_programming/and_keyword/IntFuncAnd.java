package org.info.blog.demo.functional_programming.and_keyword;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.function.Function;

public interface IntFuncAnd extends Function<Customer, String> {
    static IntFuncAnd nameToUpper() {
        return customer -> customer.getName().toUpperCase();
    }

    static IntFuncAnd ageToString() {
        return customer -> String.valueOf(customer.getAge());
    }


    default IntFuncAnd and(IntFuncAnd other) {
        return customer -> this.apply(customer);
    }
}
