import java.io.*;
import java.util.*;

public class Sorter {

    static Map<String, Integer> map = new HashMap<>();
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        while (true) {
            System.out.println("Введите номер алгоритма сортировки" + "\n" +
                    "1. По алфавиту" + "\n" +
                    "2. По количеству символов в строке." + "\n" +
                    "3. По слову в строке заданному аргументом программы в виде порядкового номера (Убедитель что введен верный аргумент)");

            Scanner sc = new Scanner(System.in);
            int sortNumber = sc.nextInt();
if (sortNumber==4){
    return;
}

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader("test.txt"));
                 BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("testResult.txt"))) {

                reedAndAddForSort(bufferedReader);

                sort(args, sortNumber);

                writeToResultFile(bufferedWriter);
            }
        }
    }

    private static void writeToResultFile(BufferedWriter bufferedWriter) {
        list.forEach(s -> {
            try {
                StringBuilder sb = new StringBuilder(s);
                sb.append(" ").append(map.get(s)).append("\n");
                bufferedWriter.write(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void sort(String[] args, int sortNumber) {
        if (sortNumber == 1) {
            Collections.sort(list, (a, b) -> a.toLowerCase().compareTo(b.toLowerCase()));
        }
        if (sortNumber == 2) {
            Collections.sort(list, (a, b) -> a.length() - (b.length()));
        }
        if (sortNumber == 3) {
            if (args.length == 1) {
                int wordPosition = Integer.parseInt(args[0]);
                try {
                    Collections.sort(list, (a, b) -> {
                        String[] aString = a.split(" ");
                        String[] bString = b.split(" ");
                        return aString[wordPosition-1].toLowerCase().compareTo(bString[wordPosition-1].toLowerCase());
                    });
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("слова под номером "+wordPosition+" нет в одной из строк. Введите меньшее число");
                }
            } else {
                System.out.println("Неверно введен аргумент. Введите ровно 1 аргумент");
            }
        }
    }

    private static void reedAndAddForSort(BufferedReader bufferedReader) throws IOException {
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            map.merge(line, 1, (oldVal, newVal) -> oldVal + newVal);
            list.add(line);
        }
    }
}
