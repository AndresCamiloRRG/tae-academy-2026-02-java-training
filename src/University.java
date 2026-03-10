import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Teacher> teachers;
    private List<Student> students;
    private List<UniversityClass> classes;

    public University() {
        this.teachers = new ArrayList<>();
        this.students = new ArrayList<>();
        this.classes = new ArrayList<>();
    }

    public List<UniversityClass> getClassesByStudentId(int studentId) {
        List<UniversityClass> studentClasses = new ArrayList<>();

        for (UniversityClass universityClass : classes) {
            for (Student student : universityClass.getStudents()) {
                if (student.getId() == studentId) {
                    studentClasses.add(universityClass);
                    // Un estudiante puede estar solo una vez en una clase así que saltamos a la siguiente clase
                    break;
                }
            }
        }
        return studentClasses;
    }

    public Student findStudentById(int id) {
        for (Student student : students){
            if (student.getId() == id){
                return student;
            }
        }
        return null;
    }

    public Teacher findTeacherByName(String name) {
        for (Teacher teacher : teachers){
            if (teacher.getName().equals(name)){
                return teacher;
            }
        }
        return null;
    }

    public void addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void addClass(UniversityClass academicClass) {
        this.classes.add(academicClass);
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<UniversityClass> getClasses() {
        return classes;
    }
}