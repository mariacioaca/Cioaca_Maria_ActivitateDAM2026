package com.example.tasks04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ArrayList<Masina> masini=null;
    private MasinaAdapter adapter=null;
    private ListView lvMasini;
    private TextView tvInstructiuni;
    private final ActivityResultLauncher<Intent> editLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    Masina m = IntentCompat.getParcelableExtra(data, "masina", Masina.class);
                    int poz = data.getIntExtra("pozitie", -1);
                    if (m != null && poz >= 0 && poz < masini.size()) {
                        masini.set(poz, m);
                        adapter.notifyDataSetChanged();
                        trimiteRezultat();
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lvMasini = findViewById(R.id.lvMasini);
        tvInstructiuni = findViewById(R.id.tvInstructiuni);

        ArrayList<Masina> primita =
                IntentCompat.getParcelableArrayListExtra(getIntent(), "masini", Masina.class);
        masini = primita != null ? primita : new ArrayList<>();

        adapter = new MasinaAdapter(masini, this, R.layout.masina);
        lvMasini.setAdapter(adapter);

        actualizeazaInstructiuni();
        trimiteRezultat();

        // click = modificare
        lvMasini.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(this, AdaugaMasinaActivity.class);
            i.putExtra("masina", masini.get(position));
            i.putExtra("pozitie", position);
            editLauncher.launch(i);
        });

        // long click = stergere
        lvMasini.setOnItemLongClickListener((parent, view, position, id) -> {
            masini.remove(position);
            adapter.notifyDataSetChanged();
            actualizeazaInstructiuni();
            trimiteRezultat();

            Toast.makeText(this, "Mașina a fost ștearsă!", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
    private void trimiteRezultat() {
        Intent r = new Intent();
        r.putParcelableArrayListExtra("masini", masini);
        setResult(RESULT_OK, r);
    }
    private void actualizeazaInstructiuni() {
        tvInstructiuni.setText(
                "Număr de mașini: " + masini.size()
                        + "\nApasă pentru modificare"
        );
    }


}