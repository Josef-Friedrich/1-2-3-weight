package rocks.friedrich.one_two_three_weight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
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

    double points = 0;
    double oldPoints = 0;
    double pointsPerPortion = 0;
    double oldPointsPerPortion = 0;
    TextView pointsTextView;
    TextView pointsPerPortionTextView;

    ImageView indicatorImage;
    ValueAnimator valueAnimator;
    Formula formula = new Formula();

    private void animatePoints(TextView textView, double oldPoints, double newPoints) {
        if (valueAnimator != null) {
            valueAnimator.end();
        }

        if ((Math.abs(newPoints - oldPoints) > 1)) {
            // Fix glitch with negative start values by points per portion
            if (oldPoints < 0) oldPoints = 0f;
            valueAnimator = ValueAnimator.ofFloat((float) oldPoints, (float) newPoints);
            valueAnimator.setDuration(500);
            valueAnimator.addUpdateListener(a -> {
                float current = Float.parseFloat(a.getAnimatedValue().toString());
                textView.setText(String.format("%.1f", current));
            });

            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    updateIndicatorImage();
                }
            });

            valueAnimator.start();
        } else {
            textView.setText(formula.roundToString(newPoints));
            updateIndicatorImage();
        }
    }

    public void updateTextViews() {
        points = formula.calculatePoints();
        if (points > 0) {
            animatePoints(pointsTextView, oldPoints, points);
            portionEditText.setEnabled(true);
        } else {
            pointsTextView.setText(R.string.points);
            portionEditText.setEnabled(false);
        }

        oldPoints = points;

        pointsPerPortion = formula.calculatePointsPerPortion(points);

        if (pointsPerPortion > 0) {
            animatePoints(pointsPerPortionTextView, oldPointsPerPortion, pointsPerPortion);
        } else {
            pointsPerPortionTextView.setText(R.string.points_per_portion);
        }
        oldPointsPerPortion = pointsPerPortion;
    }

    public void updateIndicatorImage() {
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
            image = R.drawable.ic_slim;
            color = R.color.green_500;
        } else if (points > 4 && points < 8) {
            image = R.drawable.ic_medium;
            color = R.color.orange_500;
        } else {
            image = R.drawable.ic_fat;
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