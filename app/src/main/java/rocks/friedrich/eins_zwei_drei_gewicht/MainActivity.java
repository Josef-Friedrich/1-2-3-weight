package rocks.friedrich.eins_zwei_drei_gewicht;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    double fett;
    double portion;
    double kalorien;

    TextView ergebnisTextAnsicht;

    double berechnePunkte() {
        if (fett != 0 && kalorien != 0) {
            return kalorien  / 60 + fett / 9;
        }
        return 0;
    }

    void aktualisiereErgebnisTextAnsicht() {
        if (ergebnisTextAnsicht != null) {
            double punkte = berechnePunkte();
            if (punkte > 0) {
                ergebnisTextAnsicht.setText(Double.toString(punkte));
            } else {
                ergebnisTextAnsicht.setText("");
            }
        }
    }

    double konvertiereEingabe(Editable s) {
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

        ergebnisTextAnsicht = (TextView) findViewById(R.id.ergebnis);

        EditText fettTextFeld = (EditText) findViewById(R.id.fett);
        fettTextFeld.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                fett = konvertiereEingabe(s);
                aktualisiereErgebnisTextAnsicht();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        EditText portionTextFeld = (EditText) findViewById(R.id.portion);
        portionTextFeld.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                portion = konvertiereEingabe(s);
                aktualisiereErgebnisTextAnsicht();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        EditText kalorienTextFeld = (EditText) findViewById(R.id.kalorien);
        kalorienTextFeld.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                kalorien = konvertiereEingabe(s);
                aktualisiereErgebnisTextAnsicht();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
    }
}