public class FullTimeTeacher extends Teacher{

    private int experienceYears;

    public FullTimeTeacher(String name, double baseSalary, int experienceYears){
        super(name, baseSalary);
        this.experienceYears = experienceYears;
    }

    @Override
    public double calculateSalary() {
        return baseSalary*experienceYears*1.1;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }
}
