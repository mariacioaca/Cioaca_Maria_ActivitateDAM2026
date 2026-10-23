package com.example.tasks04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AdaugaMasinaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_masina2);

        Switch switchNoua = findViewById(R.id.switchNoua);

        EditText etMarca = findViewById(R.id.etMarca);
        EditText etPret = findViewById(R.id.etPret);
        CheckBox cbElectrica = findViewById(R.id.cbElectrica);
        Spinner spCategorie = findViewById(R.id.spCategorie);
        Button btnSalveaza = findViewById(R.id.btnSalveaza);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.categorii, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategorie.setAdapter(adapter);

        btnSalveaza.setOnClickListener(v -> {
            String pretText = etPret.getText().toString();
            if (pretText.isEmpty()) {
                Toast.makeText(this, "Introdu pretul", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean esteNoua = switchNoua.isChecked();

            Masina masina = new Masina(
                    etMarca.getText().toString(),
                    Double.parseDouble(pretText),
                    cbElectrica.isChecked(),
                    spCategorie.getSelectedItem().toString(),
                    switchNoua.isChecked());


            Intent intent = new Intent();
            intent.putExtra("masina", masina);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

}