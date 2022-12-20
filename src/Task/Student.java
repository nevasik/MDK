package Task;

import java.util.Objects;

public class Student {
    private final String student; // имя/прозвище студента
    private final String group; // группа студента
    private final String discipline; // предмет/дисциплина студента
    private final double average; // ср.балл студента

    public Student(String student, String group, String discipline, double average) { // базовый конструктор класса
        this.student = student; // иницилизация полей объекта класса Student
        this.group = group; // иницилизация полей объекта класса Student
        this.discipline = discipline; // иницилизация полей объекта класса Student
        this.average = average; // иницилизация полей объекта класса Student
    }

    public String getStudent() { // обычный геттер для класса, нужен что бы обращаться к полю в других классах
        return student;
    }

    public String getGroup() { // обычный геттер для класса, нужен что бы обращаться к полю в других классах
        return group;
    }

    public String getDiscipline() { // обычный геттер для класса, нужен что бы обращаться к полю в других классах
        return discipline;
    }

    public double getAverage() { // обычный геттер для класса, нужен что бы обращаться к полю в других классах
        return average;
    }
    @Override
    public boolean equals(Object o) { // переопределили метод из под главного класса Object, родителя всех классов метод equals, что бы различались наши студенты
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getStudent().equals(student.getStudent()); // делаем сравнение объектов по имени/прозвищу студента
    }

    @Override
    public int hashCode() { // этот метод мы тоже переопределили как и equals, по hashCode сравниваются объекты. У каждого объекта свой hashCode
        return Objects.hash(getStudent());
    }
}
