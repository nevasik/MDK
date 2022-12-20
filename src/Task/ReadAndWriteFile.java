package Task;
import java.io.*;
import java.util.*;

/**
 * @author Dima Poplaukhin
 * @version 1.1
 * @since 1.0
 * <string>Это класс ReadAndWriteFile, тут будет происходить решение задачи на считывание, сортировку, обработку данных, запись в файл</string>
 */
public class ReadAndWriteFile {

    /**
     * Стартовая точка программы.
     * @param args тут происходит выполнение основой программы
     * @throws IOException при неудачном выполнении программы, может выбросить IOException(исключение)
     */

    public static void main(String[] args) throws IOException {
        File fileIn = new File("in"); // создаём объект File и в аргументе класса пишем название файла "in"
        readWriteFiles(fileIn);
    }

    /**
     * <string>Происходит запись из файла, сохранение значений в объект класса, сохранений в список, сортировка данных и запись в файл</string>
     * @param file это файловое значение, эта функция не возрващает ничего, и принимает аргумент метода File
     * @throws IOException при неудачном считывании может опять выбрасить IOException(исключение)
     */
    private static void readWriteFiles(File file) throws IOException {
        Scanner scanner = new Scanner(file);          // создали Scanner что бы произвести считывание файла
        final List<Student> records = new ArrayList<>(); // обьявили коллекцию, которая принимает объекты класса Student

        while (scanner.hasNextLine()) {                 // цикл, который считывает файл по-строчно
            String[] fields = scanner.nextLine().split(";"); // делаем split, и указываем в аргументе метода, через что разделяются значения в файле

            OptionalDouble averageGradeDouble = Arrays.stream(fields[3].split(" ")) // в строке берём последнее значение где оценки студента, переводим в число и находим сред.знач.
                    .mapToInt(Integer::parseInt)
                    .average();

            double averageGrade = averageGradeDouble.isPresent() ? averageGradeDouble.getAsDouble() : 0; // проверяем на пустоту(true-ср.знач; false - 0)

            Student student = new Student(fields[0], fields[1], fields[2], averageGrade); // создаём объект класса Student, разложили по полям значения их файла

            records.add(student); // добавляем в нашу коллекцию объект класса Student, у которого значения полей - это значения строки
        }

        Comparator<Student> averageComparator = Comparator.comparingDouble(Student::getAverage).reversed(); // создаём Comparator, что бы сделать сортировку по полям названия группы и по фамилиям студентов

        List<Student> finalRecords = records.stream().sorted(
                Comparator.comparing(Student::getGroup)
                        .thenComparing(Student::getStudent)
                        .thenComparing(averageComparator))
                        .distinct() // удаляем дубликаты
                        .toList(); // возводим в коллекцию List

        FileWriter fileWriter = new FileWriter("Out"); // Создали FileWriter, что бы записать в файл Out предметы, где больше ср.бал, отсортированные значения каждого студента

        for(Student student : finalRecords) { // итерируемся по отсортированной коллекции List
            fileWriter.write(student.getGroup() + " " // записываем в файл группу, студента, дисциплину(любимый предмет студента), и в конце добавили Сепаратор, что бы наши значения не слиплись в одну строку
                    + student.getStudent() + " "
                    + student.getDiscipline()
                    + System.lineSeparator());
        }

        fileWriter.close(); // закрываем потоки, что бы исполняемый файл не кушал лишние/ненужные ресурсы
    }
}
