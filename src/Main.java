import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение - два целых операнда и один оператор через пробел (например, 3 + 5 или II * III):");
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        boolean romeNum;
        romeNum = input.contains("I") || input.contains("V") || input.contains("X");
        String[] pieces = input.split(" ");
        if (pieces.length != 3) {
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (romeNum) {
            return romeNumbers(pieces);
        } else {
            return arabicNumbers(pieces);
        }
    }

    static String arabicNumbers(String[] pieces) throws Exception {
        int firstOperand = Integer.parseInt(pieces[0]);
        int secondOperand = Integer.parseInt(pieces[2]);
        String operator = pieces[1];
        boolean a = (firstOperand >= 1 && firstOperand <= 10) && (secondOperand >= 1 && secondOperand <= 10);
        if (!a) {
            throw new Exception("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно" +
                    "и должен принимать на вход числа от 1 до 10 включительно, не более");
        }
        int result = choosingAnOperator(firstOperand, secondOperand, operator);
        return String.valueOf(result);
    }

    static String romeNumbers(String[] pieces) throws Exception {
        String[] romeOperands = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String firstOperand = pieces[0];
        String secondOperand = pieces[2];
        String operator = pieces[1];
        int k1 = 0;
        int k2 = 0;
        for (int i = 0; i < romeOperands.length; i++) {
            if (romeOperands[i].equals(firstOperand)) {
                k1 = i + 1;
            }
            if (romeOperands[i].equals(secondOperand)) {
                k2 = i + 1;
            }
        }
        boolean a = (k1 >= 1 && k1 <= 10) && (k2 >= 1 && k2 <= 10);
        if (!a) {
            throw new Exception("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно" +
                    " и должен принимать на вход числа от 1 до 10 включительно, не более");
        }
        int result = choosingAnOperator(k1, k2, operator);
        String resultRome = null;
        String[] romanNumerals = {
                "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        int k = 0;
        for (int i = 0; i < romanNumerals.length; i++) {
            if (i + 1 == result) {
                resultRome = romanNumerals[i];
                k += 1;
            }
        }
        if (k == 0) {
            throw new Exception("Римские числа могут быть только >= 1");
        }
        return resultRome;
    }

    static int choosingAnOperator(int firstOperand, int secondOperand, String operator) throws Exception {
        return switch (operator) {
            case "+" -> firstOperand + secondOperand;
            case "-" -> firstOperand - secondOperand;
            case "*" -> firstOperand * secondOperand;
            case "/" -> firstOperand / secondOperand;
            default -> throw new Exception("Неверная операция");
        };
    }
}