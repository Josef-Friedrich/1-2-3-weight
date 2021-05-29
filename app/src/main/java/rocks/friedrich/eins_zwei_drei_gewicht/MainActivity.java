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
    double kalorien;
    double fett;
    double portion;

    TextView punkteTextAnsicht;
    TextView punkteProPortionTextAnsicht;

    double berechnePunkte() {
        if (fett != 0 && kalorien != 0) {
            return kalorien  / 60 + fett / 9;
        }
        return 0;
    }

    double berechnePunkteProPortion(double punkte) {
        if (punkte != 0 && portion != 0) {
            return punkte * portion / 100;
        }
        return 0;
    }

    void aktualisiereErgebnisTextAnsicht() {
        double punkte = berechnePunkte();
        if (punkte > 0) {
            punkteTextAnsicht.setText(rundePunkte(punkte));
        } else {
            punkteTextAnsicht.setText("Punkte");
        }

        double punkteProPortion = berechnePunkteProPortion(punkte);
        if (punkteProPortion > 0) {
            punkteProPortionTextAnsicht.setText(rundePunkte(punkteProPortion));
        } else {
            punkteProPortionTextAnsicht.setText("Punkte pro Portion");
        }
    }

    String rundePunkte(double zahl) {
        DecimalFormat df=new DecimalFormat("0.0");
        return df.format(zahl);
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

        punkteTextAnsicht = (TextView) findViewById(R.id.punkte);
        punkteProPortionTextAnsicht = (TextView) findViewById(R.id.punkteProPortion);

        EditText kalorienTextFeld = (EditText) findViewById(R.id.kalorien);
        kalorienTextFeld.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        kalorienTextFeld.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                kalorien = konvertiereEingabe(s);
                aktualisiereErgebnisTextAnsicht();
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

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


    }
}