package Task;
import java.io.*;
import java.util.*;

/**
Студент Группа Дисциплина Оценки
Структура входного файла in.txt
Тигра;Г1;Физика; 3 3 5
Винни_Пух;Г2;Пчеловодство;5 5 5
Винни_Пух;Г2;Русский язык;3 3
КроликГ1;Русский язык; 5 5 5 4
Тигра;Г1;Химия; 3 4 5

Сформировать список студентов с любимыми дисциплинами, исходя из среднего балла (гарантируется, что для каждого студента только одна любимая дисциплина).
Список упорядочить по названию группы и по фамилии студента
        Структура выходного файла out.txt
        Г1 Кролик Русский язык
        Г1 Тигра Химия
        Г2 Винни_Пух Пчеловодство
 **/
public class ReadAndWriteFile {

    public static void main(String[] args) throws IOException {
        File fileIn = new File("in");
        Scanner scanner = new Scanner(fileIn);
        final List<Student> records = new ArrayList<>();
        while (scanner.hasNextLine()){
            String[] fields = scanner.nextLine().split(";");
            OptionalDouble averageGradeDouble = Arrays.stream(fields[3].split(" "))
                    .mapToInt(Integer::parseInt)
                    .average();
            double averageGrade = averageGradeDouble.isPresent() ? averageGradeDouble.getAsDouble() : 0;

            Student student = new Student(fields[0], fields[1], fields[2], averageGrade);
            records.add(student);
        }

        Comparator<Student> averageComparator = Comparator.comparingDouble(Student::getAverage).reversed();
        List<Student> finalRecords = records.stream().sorted(
                        Comparator.comparing(Student::getGroup)
                                .thenComparing(Student::getStudent)
                                .thenComparing(averageComparator))
                .distinct()
                .toList();

        FileWriter fileWriter = new FileWriter("Out");
        for(Student student : finalRecords) {
            fileWriter.write(student.getGroup() + " "
                    + student.getStudent() + " "
                    + student.getDiscipline()
                    + System.lineSeparator());
        }
        fileWriter.close();
    }

}
