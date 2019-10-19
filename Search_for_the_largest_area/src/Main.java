import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String readFileName = inputFileName();         //ввод имени файла с данными
        String writeFileName = inputFileName();         //ввод имени файла, в который будет записан результат

        HashMap<Triangle, Double> trianglesData = readFile(readFileName);   //Треугольники с площадью

        if (trianglesData.size() != 0) {
            writeToFile(writeFileName, trianglesData);       //запись результата в файл
        }
    }

    //Считывание данных с файла
    public static HashMap<Triangle, Double> readFile(String fileName) {
        HashMap<Triangle, Double> triangleData = new HashMap<>();                       //Треугольники с площадью
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();       //считываем строку
                String[] arrayXYZ = workWithLine(line);        //записываем координаты в массив

                if (isLineValid(arrayXYZ)) {                     //проверка данных на верность
                    Point A = new Point(Integer.parseInt(arrayXYZ[0]), Integer.parseInt(arrayXYZ[1]));  //запись координат
                    Point B = new Point(Integer.parseInt(arrayXYZ[2]), Integer.parseInt(arrayXYZ[3]));
                    Point C = new Point(Integer.parseInt(arrayXYZ[4]), Integer.parseInt(arrayXYZ[5]));
                    if (checkPointsForTriangleCreating(A, B, C)) {      //проверка на существование треугольника
                        Triangle triangle = new Triangle(A, B, C);
                        triangleData.put(triangle, triangle.area());    //добавляем треугольник с площадью
                    }
                } else {
                    continue;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
        }
        return triangleData;
    }

    //Проверка на существования треугольника
    private static boolean checkPointsForTriangleCreating(Point A, Point B, Point C) {
        if (A.x == B.x && A.x == C.x) {
            return false;
        } else if (A.y == B.y && A.y == C.y) {
            return false;
        } else {
            return true;
        }
    }

    //Ввод имени файла
    public static String inputFileName() {
        boolean check = false;
        String fileName = "";
        do {                                                                                    //проверка наличия файла
            try {
                System.out.println("Введите имя файла или путь к файлу: ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                fileName = reader.readLine();                                                   //ввод имени файла
                FileReader fileReader = new FileReader(fileName);
                fileReader.close();
                check = true;
            } catch (FileNotFoundException e) {
                System.out.println("Файл не найден:( Попробуйте снова!");
            } catch (IOException e) {

            }
        } while (!check);
        return fileName;
    }

    //Запись координат треугольников с max площадью в файл
    public static void writeToFile(String fileName2, HashMap<Triangle, Double> triangleData) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName2));
            double max = Collections.max(triangleData.values());                            //находим max площадь
            for (Map.Entry<Triangle, Double> entry : triangleData.entrySet()) {
                if (entry.getValue() == max) {                                              //ищем треугольники с max площадью
                    String coordinates = entry.getKey().A.x + " " + entry.getKey().A.y + " " + entry.getKey().B.x + " " +
                            entry.getKey().B.y + " " + entry.getKey().C.x + " " + entry.getKey().C.y;
                    bufferedWriter.write(coordinates + '\n');         //записываем координаты треугольников с max площадью в файл
                }
            }
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Корректировка строки
    public static String[] workWithLine(String line) {
        String rightLine = line.trim().replaceAll("\\s{2,}", " ");    //избавляемся от лишних пробелов
        String[] arrayXYZ = rightLine.split(" ");                                //записываем координаты в массив
        return arrayXYZ;
    }

    //Проверка на правильность данных в строке
    public static boolean isLineValid(String[] arrayXYZ) {
        if (arrayXYZ.length != 6) {
            return false;
        } else {
            try {
                for (String s : arrayXYZ) {
                    Integer.parseInt(s);
                }
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }
}
