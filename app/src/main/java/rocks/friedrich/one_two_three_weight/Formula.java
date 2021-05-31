package rocks.friedrich.one_two_three_weight;

import android.text.Editable;

import java.text.DecimalFormat;

public class Formula {
    private static double calories = 0;
    private static double fat = 0;
    private static double portion = 0;

    private static double convertToDouble(Editable input) {
        try {
            return Double.parseDouble(input.toString());
        } catch (Exception e) {
            return -1;
        }
    }

    public static void set(Editable input, String valueName) {
        if (input != null && input.length() > 0) {
            double value = convertToDouble(input);
            if (value > -1) {
                switch (valueName) {
                    case "calories":
                        calories = value;
                        break;
                    case "fat":
                        fat = value;
                        break;
                    case "portion":
                        portion = value;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid value name: allowed names are calories, fat, portion");
                }
            }
        }
    }

    public static double calculatePoints() {
        return calories / 60 + fat / 9;
    }

    public static double calculatePointsPerPortion(double points) {
        return points * portion / 100;
    }

    public static String round(double number) {
        DecimalFormat df=new DecimalFormat("0.0");
        return df.format(number);
    }

    public static void reset() {
        calories = 0;
        fat = 0;
        portion = 0;
    }
}
