package rocks.friedrich.one_two_three_weight;

import android.text.Editable;
import android.text.SpannableStringBuilder;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormulaTest {

    @Test
    public void setGetCalories() {
        Formula f = new Formula();
        f.setCalories(120);
        assertEquals(f.getCalories(), 120, 0);
    }
// TODO Use mocking to mock .length
// @Test
//    public void methodSet() {
//        Formula f = new Formula();
//
//        f.set((Editable) new SpannableStringBuilder("120"), "calories");
//        assertEquals(f.getCalories(), 120, 0);
//
//        f.set(new SpannableStringBuilder("5"), "fat");
//        assertEquals(f.getFat(), 5, 0);
//
//        f.set(new SpannableStringBuilder("200"), "portion");
//        assertEquals(f.getPortion(), 200, 0);
//
//        assertEquals(f.calculatePoints(), 2.6, 0);
//        assertEquals(f.calculatePointsPerPortion(), 5.2, 0);
//    }

    @Test
    public void setGetFat() {
        Formula f = new Formula();
        f.setFat(5);
        assertEquals(f.getFat(), 5, 0);
    }

    @Test
    public void setGetPortion() {
        Formula f = new Formula();
        f.setPortion(200);
        assertEquals(f.getPortion(), 200, 0);
    }

    @Test
    public void methodCalculatePointsPerPortion() {
        Formula f = new Formula(120, 5, 200);
        assertEquals(f.calculatePointsPerPortion(), 5.2, 0);
    }
}