package com.example.tasks04;

import android.os.Bundle;

import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;



public class AdaugaMasinaAIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_masina_aiactivity);

        EditText etMarcaAI = findViewById(R.id.etMarcaAI);
        EditText etPretAI = findViewById(R.id.etPretAI);
        CheckBox cbElectricaAI = findViewById(R.id.cbElectricaAI);
        Spinner spCategorieAI = findViewById(R.id.spCategorieAI);
        SwitchCompat switchNouaAI =
                findViewById(R.id.switchNouaAI);
        Button btnSalveazaAI = findViewById(R.id.btnSalveazaAI);

        String[] categorii = {
                "Sedan", "SUV", "Hatchback", "Coupe", "Break"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categorii
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spCategorieAI.setAdapter(adapter);

        btnSalveazaAI.setOnClickListener(v -> {

            String marca = etMarcaAI.getText().toString().trim();
            String pretText = etPretAI.getText().toString().trim();

            if (marca.isEmpty() || pretText.isEmpty()) {
                Toast.makeText(this,
                        "Completeaza marca si pretul",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            double pret;

            try {
                pret = Double.parseDouble(pretText);
            } catch (NumberFormatException e) {
                Toast.makeText(this,
                        "Pret invalid",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            Masina masina = new Masina(
                    marca,
                    pret,
                    cbElectricaAI.isChecked(),
                    spCategorieAI.getSelectedItem().toString(),
                    switchNouaAI.isChecked()
            );

            Intent intent = new Intent();
            intent.putExtra("masina", masina);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}