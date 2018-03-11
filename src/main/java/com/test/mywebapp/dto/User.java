package com.test.mywebapp.dto;

public class User {
    private Long id;
    private String nameFirst;
    private String nameLast;
    private int age;
    private int salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{" +
                "nameFirst='" + nameFirst + '\'' +
                ", nameLast='" + nameLast + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
