package controller;

import model.*;

import java.util.List;
import java.util.Scanner;

public class UniversityController {
    private University university;
    private Scanner scanner;

    public UniversityController() {
        this.university = new University();
        this.scanner = new Scanner(System.in);
        initializeData();
    }

    public void start() {
        int option = 0;
        System.out.println("Welcome to Globant University, Population of: " + Student.getTotalStudentsCreated() + " students");

        while (option != 7) {
            printMenu();
            try {
                option = Integer.parseInt(scanner.nextLine());
                action(option);
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n********** MAIN MENU **********");
        System.out.println("1. Print all professors");
        System.out.println("2. Print all classes");
        System.out.println("3. Create a new student");
        System.out.println("4. Create a new class");
        System.out.println("5. List classes for a student");
        System.out.println("6. Print all students");
        System.out.println("7. Exit");
        System.out.print("Select an option: ");
    }

    private void action(int option) {
        switch (option) {
            case 1:
                printProfessors();
                break;
            case 2:
                printClassesSubMenu();
                break;
            case 3:
                createNewStudent();
                break;
            case 4:
                createNewClass();
                break;
            case 5:
                listStudentClasses();
                break;
            case 6:
                printStudents();
                break;
            case 7:
                System.out.println("Goodbye");
                break;
            default:
                System.out.println("Option not valid.");
                break;

        }
    }

    private void printProfessors() {
        System.out.println("\n--- Professor List ---");
        for (Teacher teacher : university.getTeachers()) {
            System.out.println(teacher.toString());
        }
    }

    private void printClassesSubMenu() {
        List<UniversityClass> classes = university.getClasses();
        System.out.println("\n--- Current Classes ---");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". " + classes.get(i).getName());
        }

        System.out.print("Select a class number for details (or 0 to return): ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice > 0 && choice <= classes.size()) {
            UniversityClass selected = classes.get(choice - 1);
            System.out.println(selected.toString());
        }
    }

    private void createNewStudent() {
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Student Age: ");
        int age = Integer.parseInt(scanner.nextLine());

        Student newStudent = new Student(name, age);
        university.addStudent(newStudent);

        System.out.println("Available Classes:");
        List<UniversityClass> classes = university.getClasses();
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". " + classes.get(i).getName());
        }
        System.out.print("Add to class number: ");
        int classChoice = Integer.parseInt(scanner.nextLine());
        classes.get(classChoice - 1).addStudent(newStudent);

        System.out.println("Student created and added successfully!");
    }

    private void createNewClass() {
        System.out.println("\n--- Create New Class ---");
        System.out.print("Enter Class Name: ");
        String className = scanner.nextLine();
        System.out.print("Enter Classroom: ");
        String classroom = scanner.nextLine();

        System.out.println("\nSelect a Teacher:");
        List<Teacher> teachers = university.getTeachers();
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println((i + 1) + ". " + teachers.get(i).getName());
        }
        System.out.print("Enter teacher number: ");
        int teacherIdx = Integer.parseInt(scanner.nextLine()) - 1;
        Teacher selectedTeacher = teachers.get(teacherIdx);

        UniversityClass newClass = new UniversityClass(className, classroom, selectedTeacher);

        boolean addingStudents = true;
        while (addingStudents) {
            System.out.println("\nSelect a Student to add:");
            List<Student> students = university.getStudents();
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i+1) + ")" + students.get(i).toString());
            }
            System.out.print("Enter student number (or 0 to finish): ");
            int studentIdx = Integer.parseInt(scanner.nextLine()) - 1;

            if (studentIdx == -1) {
                addingStudents = false;
            } else if (studentIdx >= 0 && studentIdx < students.size()) {
                if (!newClass.getStudents().contains(students.get(studentIdx))){
                    newClass.addStudent(students.get(studentIdx));
                }
                System.out.println("Student added!");
            } else {
                System.out.println("Invalid selection.");
            }
        }

        university.addClass(newClass);
        System.out.println("\nClass '" + className + "' created successfully with " +
                newClass.getStudents().size() + " students.");
    }

    private void listStudentClasses() {
        System.out.print("Enter Student ID to search: ");
        int id = Integer.parseInt(scanner.nextLine());
        List<UniversityClass> results = university.getClassesByStudentId(id);

        if (results.isEmpty()) {
            System.out.println("No classes found for this student.");
        } else {
            System.out.println("Student is enrolled in:");
            for (UniversityClass c : results) {
                System.out.println("- " + c.getName());
            }
        }
    }

    private void printStudents() {
        System.out.println("\n--- Students List ---");
        for (Student student : university.getStudents()) {
            System.out.println(student.toString());
        }
        System.out.println("\nFor a total of: " + Student.getTotalStudentsCreated() + " students");
    }

    private void initializeData() {
        // 1. Teachers
        FullTimeTeacher ft1 = new FullTimeTeacher("Dr. Alan Turing", 3000, 10);
        FullTimeTeacher ft2 = new FullTimeTeacher("Dr. Ada Lovelace", 3200, 8);
        PartTimeTeacher pt1 = new PartTimeTeacher("Prof. Grace Hopper", 50, 20);
        PartTimeTeacher pt2 = new PartTimeTeacher("Prof. John von Neumann", 55, 15);
        university.addTeacher(ft1); university.addTeacher(ft2);
        university.addTeacher(pt1); university.addTeacher(pt2);

        // 2. Students
        university.addStudent(new Student("Alice", 20));
        university.addStudent(new Student("Bob", 21));
        university.addStudent(new Student("Charlie", 22));
        university.addStudent(new Student("Diana", 19));
        university.addStudent(new Student("Ethan", 20));
        university.addStudent(new Student("Fiona", 21));

        // 3. Classes
        UniversityClass c1 = new UniversityClass("Intro to CS", "Room 101", ft1);
        c1.addStudent(university.getStudents().get(0));
        c1.addStudent(university.getStudents().get(1));

        UniversityClass c2 = new UniversityClass("Mathematics", "Room 202", ft2);
        c2.addStudent(university.getStudents().get(2));

        UniversityClass c3 = new UniversityClass("COBOL Legacy", "Lab A", pt1);
        c3.addStudent(university.getStudents().get(3));
        c3.addStudent(university.getStudents().get(4));

        UniversityClass c4 = new UniversityClass("Game Theory", "Room 303", pt2);
        c4.addStudent(university.getStudents().get(5));

        university.addClass(c1);
        university.addClass(c2);
        university.addClass(c3);
        university.addClass(c4);
    }
}