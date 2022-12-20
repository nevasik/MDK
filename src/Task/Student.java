package Task;

import java.util.Objects;

public class Student {
    private final String student;
    private final String group;
    private final String discipline;
    private final double average;

    public Student(String student, String group, String discipline, double average) {
        this.student = student;
        this.group = group;
        this.discipline = discipline;
        this.average = average;
    }

    public String getStudent() {
        return student;
    }

    public String getGroup() {
        return group;
    }

    public String getDiscipline() {
        return discipline;
    }

    public double getAverage() {
        return average;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getStudent().equals(student.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudent());
    }
}
