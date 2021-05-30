package rocks.friedrich.eins_zwei_drei_gewicht;

import android.text.Editable;
import android.text.TextWatcher;

public class TextWatcherAdapter implements TextWatcher {

    private String valueName;
    private MainActivity mainActivity;

    public TextWatcherAdapter(String valueName, MainActivity mainActivity) {
        this.valueName = valueName;
        this.mainActivity = mainActivity;
    }

    public void afterTextChanged(Editable s) {
        Formula.set(s, valueName);
        mainActivity.updateTextViews();
    }

    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    public void onTextChanged(CharSequence s, int start, int before, int count) {}
}
