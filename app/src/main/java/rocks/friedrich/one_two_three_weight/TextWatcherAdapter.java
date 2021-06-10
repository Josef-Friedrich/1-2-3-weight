package rocks.friedrich.one_two_three_weight;

import android.text.Editable;
import android.text.TextWatcher;

public class TextWatcherAdapter implements TextWatcher {

    private String valueName;
    private MainActivity mainActivity;
    private Formula formula;

    public TextWatcherAdapter(String valueName, MainActivity mainActivity, Formula formula) {
        this.valueName = valueName;
        this.mainActivity = mainActivity;
        this.formula = formula;
    }

    public void afterTextChanged(Editable s) {
        formula.set(s, valueName);
        mainActivity.updateTextViews();
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }
}
