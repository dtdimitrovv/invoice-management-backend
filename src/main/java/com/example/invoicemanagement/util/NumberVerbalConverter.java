package com.example.invoicemanagement.util;

import java.util.ArrayList;
import java.util.List;

public final class NumberVerbalConverter {
    public enum Currency {
        BGN, EUR
    }

    private static final int MALE = 1;
    private static final int FEMALE = 0;

    private static final String[][][] numberLiterals = new String[3][10][10];

    static {
        initNumberLiterals();
    }

    private NumberVerbalConverter() {
    }

    private static void initNumberLiterals() {
        numberLiterals[0][1][0] = "сто";
        numberLiterals[0][2][0] = "двеста";
        numberLiterals[0][3][0] = "триста";
        numberLiterals[0][4][0] = "четиристотин";
        numberLiterals[0][5][0] = "петстотин";
        numberLiterals[0][6][0] = "шестотин";
        numberLiterals[0][7][0] = "седемстотин";
        numberLiterals[0][8][0] = "осемстотин";
        numberLiterals[0][9][0] = "деветстотин";

        numberLiterals[1][1][0] = "десет";
        numberLiterals[1][1][1] = "единадесет";
        numberLiterals[1][1][2] = "дванадесет";
        numberLiterals[1][1][3] = "тринадесет";
        numberLiterals[1][1][4] = "четиринадесет";
        numberLiterals[1][1][5] = "петнадесет";
        numberLiterals[1][1][6] = "шестнадесет";
        numberLiterals[1][1][7] = "седемнадесет";
        numberLiterals[1][1][8] = "осемнадесет";
        numberLiterals[1][1][9] = "деветнадесет";

        numberLiterals[1][2][0] = "двадесет";
        numberLiterals[1][3][0] = "тридесет";
        numberLiterals[1][4][0] = "четиридесет";
        numberLiterals[1][5][0] = "петдесет";
        numberLiterals[1][6][0] = "шестдесет";
        numberLiterals[1][7][0] = "седемдесет";
        numberLiterals[1][8][0] = "осемдесет";
        numberLiterals[1][9][0] = "деветдесет";

        numberLiterals[2][1][FEMALE] = "една";
        numberLiterals[2][1][MALE] = "един";
        numberLiterals[2][2][FEMALE] = "две";
        numberLiterals[2][2][MALE] = "два";

        String[] digits = {"три", "четири", "пет", "шест", "седем", "осем", "девет"};
        for (int i = 3; i <= 9; i++) {
            numberLiterals[2][i][0] = digits[i - 3];
        }
    }

    public static String toWords(double inputNumber, Currency currency) {
        String number = String.valueOf(inputNumber);
        if (number.isEmpty()) {
            throw new IllegalArgumentException("Invalid number");
        }

        String[] parts = number.split("\\.");
        String intPart = parts[0];
        String fracPart = (parts.length > 1 ? (parts[1] + "00").substring(0, 2) : "00");

        if (intPart.length() > 9) {
            throw new IllegalArgumentException("Invalid number format");
        }

        String result;

        if (intPart.matches("^0{1,9}$")) {
            result = "нула";
        } else {
            List<String> triads = splitIntoTriads(intPart);
            int count = triads.size();
            result = "";

            if (count == 3) {
                result += convertTriad(triads.get(0)) + (triads.get(0).equals("1") ? " милион " : " милиона ");
                result += convertTriad(triads.get(1), FEMALE, true);
                if (!triads.get(1).equals("1") && !triads.get(1).equals("001") && !triads.get(1).equals("000")) {
                    result += " хиляди ";
                }
                if (needsAnd(triads.get(1), triads.get(2))) result += " и ";
                result += convertTriad(triads.get(2));
            } else if (count == 2) {
                result += convertTriad(triads.get(0), FEMALE, true);
                if (!triads.get(0).equals("1") && !triads.get(0).equals("001") && !triads.get(0).equals("000")) {
                    result += " хиляди ";
                }
                if (needsAnd(triads.get(0), triads.get(1))) result += " и ";
                result += convertTriad(triads.get(1));
            } else {
                result += convertTriad(triads.get(0));
            }
        }

        boolean isPlural = !intPart.endsWith("1") || intPart.endsWith("11");
        boolean isFractionPlural = !fracPart.endsWith("1") || fracPart.endsWith("11");

        if (currency == Currency.BGN) {
            result += " " + (isPlural ? "лева" : "лев");
        } else {
            result += " евро";
        }

        if (!fracPart.equals("00")) {
            result += " и " + convertTriad(fracPart, FEMALE);
            if (currency == Currency.BGN) {
                result += " " + (isFractionPlural ? "стотинки" : "стотинка");
            } else {
                result += " " + (isFractionPlural ? "евроцента" : "евроцент");
            }
        }

        return result.replaceAll("\\s{2,}", " ").trim();
    }

    private static String convertTriad(String triad) {
        return convertTriad(triad, MALE, false);
    }

    private static String convertTriad(String triad, int gender) {
        return convertTriad(triad, gender, false);
    }

    private static String convertTriad(String triad, int gender, boolean isThousands) {
        triad = String.format("%3s", triad).replace(' ', '0');
        int first = Character.getNumericValue(triad.charAt(0));
        int second = Character.getNumericValue(triad.charAt(1));
        int third = Character.getNumericValue(triad.charAt(2));

        if (isThousands && triad.equals("001")) return "хиляда ";

        StringBuilder result = new StringBuilder();

        if (first != 0) {
            result.append(numberLiterals[0][first][0]).append(" ");
            if ((second <= 1 || third == 0) && second != third) {
                result.append("и ");
            }
        }

        if (second != 0) {
            if (second == 1) return result.append(numberLiterals[1][second][third]).toString();
            result.append(numberLiterals[1][second][0]);
            if (third != 0) result.append(" и ");
        }

        if (third != 0) {
            if (third == 1 || third == 2) {
                result.append(numberLiterals[2][third][gender]);
            } else {
                result.append(numberLiterals[2][third][0]);
            }
        }

        return result.toString().trim();
    }

    private static List<String> splitIntoTriads(String num) {
        List<String> triads = new ArrayList<>();
        int rem = num.length() % 3;
        if (rem != 0) {
            triads.add(num.substring(0, rem));
            num = num.substring(rem);
        }
        for (int i = 0; i < num.length(); i += 3) {
            triads.add(num.substring(i, i + 3));
        }
        return triads;
    }

    private static boolean needsAnd(String prev, String current) {
        int val;
        try {
            val = Integer.parseInt(current);
        } catch (NumberFormatException e) {
            return false;
        }
        return (val >= 1 && val < 10) ||
                current.matches("^0[1-9]0$") ||
                current.matches("^01[1-9]$") ||
                prev.matches("^[1-9]00$");
    }
}
