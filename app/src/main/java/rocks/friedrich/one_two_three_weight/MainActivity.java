package rocks.friedrich.one_two_three_weight;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    EditText caloriesEditText;
    EditText fatEditText;
    EditText portionEditText;

    TextView pointsTextView;
    TextView pointsPerPortionTextView;

    ImageView indicatorImage;

    Formula formula = new Formula();

    public void updateTextViews() {
        double points = formula.calculatePoints();
        if (points > 0) {
            pointsTextView.setText(formula.roundToString(points));
            portionEditText.setEnabled(true);
        } else {
            pointsTextView.setText(R.string.points);
            portionEditText.setEnabled(false);
        }

        double pointsPerPortion = formula.calculatePointsPerPortion(points);

        if (pointsPerPortion > 0) {
            pointsPerPortionTextView.setText(formula.roundToString(pointsPerPortion));
        } else {
            pointsPerPortionTextView.setText(R.string.points_per_portion);
        }

        if (pointsPerPortion > 0) {
            updateIndicatorImage(pointsPerPortion);
        } else {
            updateIndicatorImage(points);
        }
    }

    public void updateIndicatorImage(double points) {
        int image;
        int color;

        if (points <= 4) {
            image = R.drawable.slim;
            color = R.color.green_500;
        } else if (points > 4 && points < 8) {
            image = R.drawable.medium;
            color = R.color.orange_500;
        } else {
            image = R.drawable.fat;
            color = R.color.red_500;
        }

        indicatorImage.setImageResource(image);

        indicatorImage.setColorFilter(ContextCompat.getColor(MainActivity.this,
                color));
    }

    public void resetForNewCalculation() {
        caloriesEditText.getText().clear();
        fatEditText.getText().clear();
        portionEditText.getText().clear();
        formula.reset();
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

        indicatorImage = findViewById(R.id.indicator_image);

        caloriesEditText = findViewById(R.id.calories_edit_text);
        focusFirstEditText();

        caloriesEditText.addTextChangedListener(new TextWatcherAdapter("calories", this, formula));

        fatEditText = findViewById(R.id.fat_edit_text);
        fatEditText.addTextChangedListener(new TextWatcherAdapter("fat", this, formula));

        portionEditText = findViewById(R.id.portion_edit_text);
        portionEditText.addTextChangedListener(new TextWatcherAdapter("portion", this, formula));
        portionEditText.setEnabled(false);
        FloatingActionButton resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(view -> resetForNewCalculation());
    }
}