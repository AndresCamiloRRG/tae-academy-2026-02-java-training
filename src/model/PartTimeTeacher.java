package model;

public class PartTimeTeacher extends Teacher{

    private double hoursPerWeek;

    public PartTimeTeacher(String name, double baseSalary, double hoursPerWeek){
        super(name, baseSalary);
        this.hoursPerWeek = hoursPerWeek;
    }

    @Override
    public double calculateSalary() {
        return baseSalary*hoursPerWeek;
    }

    @Override
    public String toString(){
        return "Name: " + name + " Type: Part-Time " + " Salary: " + calculateSalary();
    }

    public double getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(double hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }
}
