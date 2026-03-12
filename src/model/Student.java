package model;

public class Student {

    private static int idCounter = 1000;

    private String name;

    private int id;

    private int age;

    public Student (String name, int age){
        this.name = name;
        this.id = ++idCounter;
        this.age = age;
    }

    public static int getTotalStudentsCreated() {
        return idCounter - 1000;
    }

    @Override
    public String toString(){
        return "Name: " + name + " Age: " + age + " ID: " + id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
