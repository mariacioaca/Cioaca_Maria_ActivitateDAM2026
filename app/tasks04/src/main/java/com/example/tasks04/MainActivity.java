package com.example.tasks04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView tvRezultat;
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Masina m = (Masina) result.getData().getSerializableExtra("masina");
                    if (m != null) {
                        tvRezultat.setText(m.toString());
                        Toast.makeText(this, m.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            });

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
}
