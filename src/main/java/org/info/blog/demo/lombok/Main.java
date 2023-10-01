package org.info.blog.demo.lombok;

public class Main {

    public static void main(String[] args) {
        BuilderTest builderTest = BuilderTest
                .builder()
                .name("test")
                .build();
        System.out.print(builderTest);

        WithLombBuilder withLombBuilder = WithLombBuilder
                .aza()

                .iggy();
        System.out.println(withLombBuilder);


        int [] test= {};
        test[0]=1;
        


    }
}
