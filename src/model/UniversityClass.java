package model;

import java.util.ArrayList;

public class UniversityClass {

    private String name;

    private String classroom;

    private ArrayList<Student> students;

    private Teacher teacher;

    public UniversityClass(String name, String classroom, Teacher teacher) {
        this.name = name;
        this.classroom = classroom;
        this.teacher = teacher;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public String toString(){

        String studentsOut = "";

        for (Student student: students){
            studentsOut = studentsOut + "\n     - " + student.toString();
        }

        return "\nClass: " + name + "\nClassroom: " + classroom + "\nTeacher: " + teacher.getName() + "\nStudents" + studentsOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}