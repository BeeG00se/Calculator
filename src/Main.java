import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение, состоящее из двух целых чисел от 0 до 10, знака операции и знака равно (напр. 2+2=): ");

        Scanner scanner = new Scanner(System.in);

        String str = scanner.nextLine();
        String res = calc(str);
        System.out.println(res);
    }

    public static String calc(String input) throws Exception {
        //разделим input на массив строк
        // a,b = 0,2 индексы массива
        String[] words = input.split(" ");
        if (words.length != 3) {
            throw new Exception("Не то количество циферок");
        }
        String a = words[0];
        char ch = words[1].charAt(0);
        String b = words[2];
        boolean flagA = false;
        boolean flagB = false;
        for (Test v : Test.values()) {
            if (v.getDivnijVostok().equals(a)) {
                flagA = true;
            }
            if (v.getDivnijVostok().equals(b)) {
                flagB = true;
            }

        }
//    flag = true, значит будем иметь дело с римскими обозначениями

        int first;
        int second;
        if (flagA && flagB) {
            first = Test.fromString(a).getArabskayaNoch();
            second = Test.fromString(b).getArabskayaNoch();

            if (first < 0 || first > 10 || second < 0 || second >10) {
                throw new Exception("Цифра должна быть в диапазоне от 0 до 10");
            }
            int res = result(first, ch, second);

            if(res <= 0) {
                throw new Exception("Это грустно, но у римлян нет отрицательных чисел :("); //т.к. в римской системе нет отрицательных чисел
            }
            String rim = toRoman(res);
            return "Ваш ответ: " + rim + ". Вы великолепны!";
        } else if (flagA != flagB) {
            throw new Exception("Вы нехороший человек, больше так не делайте..."); //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)

        } else {
            first = Integer.valueOf(a);
            second = Integer.valueOf(b);

            if (first < 0 || first > 10 || second < 0 || second >10) {
                throw new Exception("Цифра должна быть в диапазоне от 0 до 10");
            }
            int res = result(first, ch, second);
            return "Ваш ответ: " + res + ". Вы великолепны!";
        }
    }

    public static String toRoman(int number) {
        List<Test> romanNumerals = Test.getReverseSortedValues();

        int i = 0;
        String sb = new String();

        while ((number > 0) && (i < romanNumerals.size())) {
            Test currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getArabskayaNoch() <= number) {
                sb = sb + currentSymbol.name();
                number -= currentSymbol.getArabskayaNoch();
            } else {
                i++;
            }
        }
        return sb;
    }

    public static int result(int a, char ch, int b) throws Exception {
        int sum = 0;

        if (ch == '+') {
            sum = a + b;
        } else if (ch == '-') {
            sum = a - b;
        } else if (ch == '*') {
            sum = a * b;
        } else if (ch == '/') {
            sum = a / b;
        } else {
            throw new Exception("Карты говорят, что сегодня не ваш день");
        }

//        System.out.println("Ваш ответ: " + sum);
        return sum;
    }

}