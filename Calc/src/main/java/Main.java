import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int number1, number2;
    static char operation;
    static int result;
    private static String[] romans = {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static void main(String[] args) throws Exception {
        Boolean isWorking = true;
        while (isWorking) {
            System.out.println("Input:");
            String userInput = scanner.nextLine();
            System.out.println(calc(userInput));
            System.out.println("Для продолжить вычисления нажмите Y, для выхода - любую кнопку");
            if (!scanner.nextLine().equalsIgnoreCase("y")) {
                isWorking = false;
            }
        }
    }

    public static String calc(String userInput) throws Exception {
        String resultString = null;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < userInput.length(); i++) {

            if (userInput.charAt(i) == '+' || userInput.charAt(i) == '-' || userInput.charAt(i) == '*' || userInput.charAt(i) == '/') {
                stringBuilder.append(userInput.charAt(i));
            }
        }
        if (stringBuilder.toString().length() == 0) {
            throw new Exception("Арифметический оператор не найден!");
        }
        if (stringBuilder.toString().length() > 1) {
            throw new Exception("Арифметический оператор должен быть 1!");
        }
        operation = stringBuilder.charAt(0);
        String[] blacks = userInput.split("[+-/*]");
        String stable00 = blacks[0];
        String stable01 = blacks[1];
        String string03 = stable01.trim();
        int count = 0;
        try {
            number1 = Integer.parseInt(stable00);
            count++;
            number2 = Integer.parseInt(string03);
            result = calculated(number1, number2, operation);
            System.out.println("Output:");
            resultString = String.valueOf(result);
        } catch (Exception e) {
            if (count == 1) {
                throw new Exception("Введены различные системы исчисления");
            }
            number1 = romanToNumber(stable00);
            number2 = romanToNumber(string03);
            if (number1 < 0 && number2 < 0) {
                result = 0;
            } else {
                result = calculated(number1, number2, operation);
                if (result < 1) {
                    throw new Exception("Результатом должно быть положительное число");
                }
                System.out.println("Output:");
                String resultRoman = convertNumToRoman(result);

                resultString = resultRoman;
            }
        }
        return resultString;
    }


    private static String convertNumToRoman(int numArabian) {
        final String s = romans[numArabian];
        return s;
    }


    private static int romanToNumber(String roman) throws Exception {
        int count = -1;

        for (int i = 0; i < romans.length; i++) {
            if (roman.equals(romans[i])) {
                count = i;
            }

        }
        if (count > 10) {
            throw new Exception("Введено число больше 10!!");
        } else if (count == -1) {
            throw new Exception("Не верный формат данных");
        }
        try {
            if (roman.equals("I")) {
                return 1;
            } else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Введены различные системы исчисления");
        }
        return -1;
    }

    public static int calculated(int num1, int num2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                try {
                    result = num1 / num2;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Only integer non-zero parameters allowed");

                    break;
                }
                break;
            default:
                throw new IllegalArgumentException("Не верный знак операции");
        }
        return result;
    }
}