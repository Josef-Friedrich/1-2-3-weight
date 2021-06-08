package rocks.friedrich.one_two_three_weight;

import android.text.Editable;

import java.text.DecimalFormat;

public class Formula {
    private static double calories = 0;
    private static double fat = 0;
    private static double portion = 0;

    private static double convertToDouble(Editable input) {
        try {
            // To allow commas as a separator for floating point numbers https://stackoverflow.com/a/6280607
            return Double.parseDouble(input.toString().replace(',', '.'));
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
        return roundToDouble(calories / 60 + fat / 9);
    }

    public static double calculatePointsPerPortion(double points) {
        return roundToDouble(points * portion / 100);
    }

    public static double roundToDouble(double input) {
        return (double) Math.round(input * 10) / 10;
    }

    public static String roundToString(double number) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(number);
    }

    public static void reset() {
        calories = 0;
        fat = 0;
        portion = 0;
    }
}
