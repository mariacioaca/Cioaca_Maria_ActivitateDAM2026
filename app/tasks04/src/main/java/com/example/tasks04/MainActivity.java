package com.example.tasks04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView tvRezultat;
    private ArrayList<Masina> masini = new ArrayList<>();
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK
                        && result.getData() != null) {
                    Intent data = result.getData();
                    Masina masina = (Masina)
                            data.getSerializableExtra("masina");
                    if (masina != null) {
                        masini.add(masina);
                        tvRezultat.setText(masina.toString());
                        Toast.makeText(this, masina.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });
    private final ActivityResultLauncher<Intent> listaLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == RESULT_OK
                                && result.getData() != null) {

                            @SuppressWarnings("unchecked")
                            ArrayList<Masina> masiniActualizate = (ArrayList<Masina>) result.getData().getSerializableExtra("masini");

                            if (masiniActualizate != null) {
                                masini = masiniActualizate;
                            }
                            tvRezultat.setText("Număr de mașini: " + masini.size());
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvRezultat = findViewById(R.id.tvRezultat);
    }
    public void culegereDate(View view) {
        Intent intent = new Intent(this, AdaugaMasinaActivity.class);
        launcher.launch(intent);
    }
    public void culegereDateAI(View view) {
        Intent intent = new Intent(this, AdaugaMasinaAIActivity.class);
        launcher.launch(intent);
    }
    public void afisareMasini(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);

        intent.putExtra("masini", masini);
        listaLauncher.launch(intent);
    }
}
