package rocks.friedrich.one_two_three_weight;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    EditText caloriesEditText;
    EditText fatEditText;
    EditText portionEditText;

    TextView pointsTextView;
    TextView pointsPerPortionTextView;

    public void updateTextViews() {
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

    public void resetForNewCalculation() {
        caloriesEditText.getText().clear();
        fatEditText.getText().clear();
        portionEditText.getText().clear();
        Formula.reset();
        updateTextViews();
        focusFirstEditText();
    }

    public void focusFirstEditText() {
        caloriesEditText.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pointsTextView = findViewById(R.id.points_text_view);
        pointsPerPortionTextView = findViewById(R.id.points_per_portion_text_view);

        caloriesEditText = findViewById(R.id.calories_edit_text);
        focusFirstEditText();

        caloriesEditText.addTextChangedListener(new TextWatcherAdapter("calories", this));

        fatEditText = findViewById(R.id.fat_edit_text);
        fatEditText.addTextChangedListener(new TextWatcherAdapter("fat", this));

        portionEditText = findViewById(R.id.portion_edit_text);
        portionEditText.addTextChangedListener(new TextWatcherAdapter("portion", this));
        FloatingActionButton resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(view -> resetForNewCalculation());
    }
}