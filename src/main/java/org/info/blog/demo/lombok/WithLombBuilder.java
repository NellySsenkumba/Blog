package org.info.blog.demo.lombok;

import lombok.Builder;
import org.springframework.lang.NonNull;

@Builder(buildMethodName = "iggy",builderMethodName = "aza")
public class WithLombBuilder {
    @org.springframework.lang.NonNull
    private String name;
}
