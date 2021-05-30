package rocks.friedrich.eins_zwei_drei_gewicht;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView pointsTextView;
    TextView pointsPerPortionTextView;

    void updateTextViews() {
        double points = Formula.calculatePoints();
        if (points > 0) {
            pointsTextView.setText(Formula.round(points));
        } else {
            pointsTextView.setText(R.string.points);
        }

        double pointsPerPortion = Formula.calculatePointsPerPortion(points);
        if (pointsPerPortion > 0) {
            pointsPerPortionTextView.setText(Formula.round(pointsPerPortion));
        } else {
            pointsPerPortionTextView.setText(R.string.points_per_portion);
        }
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
                Formula.set(s, "calories");
                updateTextViews();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        EditText fatEditText = findViewById(R.id.fat_edit_text);
        fatEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Formula.set(s, "fat");
                updateTextViews();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        EditText portionEditText = findViewById(R.id.portion_edit_text);
        portionEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Formula.set(s, "portion");
                updateTextViews();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


    }
}