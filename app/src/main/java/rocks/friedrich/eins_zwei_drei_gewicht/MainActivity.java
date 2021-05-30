package rocks.friedrich.eins_zwei_drei_gewicht;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    double calories;
    double fat;
    double portion;

    TextView pointsTextView;
    TextView pointsPerPortionTextView;

    double calculatePoints() {
        if (fat != 0 && calories != 0) {
            return calories / 60 + fat / 9;
        }
        return 0;
    }

    double calculatePointsPerPortion(double points) {
        if (points != 0 && portion != 0) {
            return points * portion / 100;
        }
        return 0;
    }

    void updateTextViews() {
        double points = calculatePoints();
        if (points > 0) {
            pointsTextView.setText(roundPoints(points));
        } else {
            pointsTextView.setText(R.string.points);
        }

        double pointsPerPortion = calculatePointsPerPortion(points);
        if (pointsPerPortion > 0) {
            pointsPerPortionTextView.setText(roundPoints(pointsPerPortion));
        } else {
            pointsPerPortionTextView.setText(R.string.points_per_portion);
        }
    }

    String roundPoints(double number) {
        DecimalFormat df=new DecimalFormat("0.0");
        return df.format(number);
    }

    double convertInput(Editable s) {
        if (s != null && s.length() > 0) {
            try {
                return Double.parseDouble(s.toString());
            } catch(Exception e) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointsTextView = findViewById(R.id.points_text_view);
        pointsPerPortionTextView = findViewById(R.id.points_per_portion_text_view);

        EditText caloriesEditText = findViewById(R.id.calories_edit_text);
        caloriesEditText.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        caloriesEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                calories = convertInput(s);
                updateTextViews();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        EditText fatEditText = findViewById(R.id.fat_edit_text);
        fatEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                fat = convertInput(s);
                updateTextViews();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        EditText portionEditText = findViewById(R.id.portion_edit_text);
        portionEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                portion = convertInput(s);
                updateTextViews();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


    }
}