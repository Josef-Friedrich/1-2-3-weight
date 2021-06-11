package rocks.friedrich.one_two_three_weight;

import android.text.Editable;

import java.text.DecimalFormat;

public class Formula {
    private double calories;
    private double fat;
    private double portion;

    public Formula (double calories, double fat, double portion) {
        this.calories = calories;
        this.fat = fat;
        this.portion = portion;
    }

    public Formula () {
        this.calories = -1;
        this.fat = -1;
        this.portion = -1;
    }

    private double convertToDouble(Editable input) {
        try {
            // To allow commas as a separator for floating point numbers https://stackoverflow.com/a/6280607
            return Double.parseDouble(input.toString().replace(',', '.'));
        } catch (Exception e) {
            return -1;
        }
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCalories() {
        return calories;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getFat() {
        return fat;
    }

    public void setPortion(double portion) {
        this.portion = portion;
    }

    public double getPortion() {
        return portion;
    }

    public void set(Editable input, String valueName) {
        double value;
        if (input != null && input.length() > 0) {
            value = convertToDouble(input);
        } else {
            value = -1;
        }

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

    public double calculatePoints() {
        double points = 0;
        if (calories > -1) {
            points = calories / 60;
        }

        if (fat > -1) {
            points = points + fat / 9;
        }

        points = roundToDouble(points);
        // Show always a points number greater than zero if there is an input
        if (points == 0 && (calories > -1 || fat > -1)) {
            return 0.1;
        }
        return points;
    }

    public double calculatePointsPerPortion() {
        return calculatePointsPerPortion(calculatePoints());
    }

    public double calculatePointsPerPortion(double points) {
        if (points == 0) {
            return 0;
        }
        double result = roundToDouble(points * portion / 100);

        if (result == 0 && portion > -1) {
            return 0.1;
        }
        return result;
    }

    public double roundToDouble(double input) {
        return (double) Math.round(input * 10) / 10;
    }

    public String roundToString(double number) {
        DecimalFormat df = new DecimalFormat("0.0");
        return df.format(number);
    }

    public void reset() {
        calories = -1;
        fat = -1;
        portion = -1;
    }
}
