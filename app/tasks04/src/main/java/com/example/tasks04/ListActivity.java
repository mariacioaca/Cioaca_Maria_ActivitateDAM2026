package com.example.tasks04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ArrayList<Masina> masini;
    private ArrayAdapter<Masina> adapter;
    private ListView lvMasini;
    private TextView tvInstructiuni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lvMasini = findViewById(R.id.lvMasini);
        tvInstructiuni = findViewById(R.id.tvInstructiuni);

        Intent intent = getIntent();

        @SuppressWarnings("unchecked")
        ArrayList<Masina> listaPrimita =
                (ArrayList<Masina>) intent.getSerializableExtra("masini");

        if (listaPrimita != null) {
            masini = listaPrimita;
        } else {
            masini = new ArrayList<>();
        }

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                masini
        );

        lvMasini.setAdapter(adapter);

        actualizeazaInstructiuni();

        // Cerinta 3
        lvMasini.setOnItemClickListener((parent, view, position, id) -> {
            Masina masina = masini.get(position);

            Toast.makeText(
                    this,
                    masina.toString(),
                    Toast.LENGTH_LONG
            ).show();
        });

        // Cerinta 4
        lvMasini.setOnItemLongClickListener(
                (parent, view, position, id) -> {

                    Masina masinaStearsa = masini.remove(position);

                    adapter.notifyDataSetChanged();

                    actualizeazaInstructiuni();

                    Toast.makeText(
                            this,
                            "Mașina a fost ștearsă!",
                            Toast.LENGTH_SHORT
                    ).show();

                    return true;
                }
        );
    }

    private void actualizeazaInstructiuni() {
        tvInstructiuni.setText(
                "Număr de mașini: " + masini.size()
                        + "\nApasă pentru detalii. Ține apăsat pentru ștergere."
        );
    }

    @Override
    public void onBackPressed() {
        Intent rezultat = new Intent();
        rezultat.putExtra("masini", masini);

        setResult(RESULT_OK, rezultat);

        super.onBackPressed();
    }
}