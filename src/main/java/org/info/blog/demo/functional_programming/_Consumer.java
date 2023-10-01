package org.info.blog.demo.functional_programming;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

public class _Consumer {
    public static void main(String[] args) {
        List<Person> persons = List.of(
                new Person("Joe", 21),
                new Person("Faith", 20),
                new Person("Jerry", 16)
        );


        List<Student> student = persons.stream().map(person -> new Student(person.name)).collect(Collectors.toList());

//        persons
        student.forEach((student1) -> System.out.println(student1.test));
        System.out.println("Below age");

        persons.stream()
                .filter((person -> person.age < 18))

                .forEach(System.out::println);

    }
}

@AllArgsConstructor
class Person {
    String name;
    int age;
}

@AllArgsConstructor
class Student {
    String test;
}