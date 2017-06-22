package com.demo.json.entity;

/**
 * Created by theking on 2017-03-23.
 */
public class Student {

    private int id;

    private int age;

    private String name;

    public Student(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){

        return "Person [id=" + id + ", age=" + age + ", name=" + name + "]";

    }

}
