package com.example.tasks04;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GalerieActivity extends AppCompatActivity {

    private List<ImagineMasina> lista = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_galerie);

        ListView lv = findViewById(R.id.lvGalerie);
        ViewCompat.setOnApplyWindowInsetsListener(lv, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<ImagineMasina> lista = new ArrayList<>();

        lista.add(new ImagineMasina(
                "https://commons.wikimedia.org/wiki/Special:FilePath/2013_Porsche_911_Carrera_4S_(991)_(9626546987).jpg?width=400",
                "Porsche 911",
                "https://en.wikipedia.org/wiki/Porsche_911"));

        // Tesla
        lista.add(new ImagineMasina(
                "https://commons.wikimedia.org/wiki/Special:FilePath/Tesla_Model_S_-_rot.jpg?width=400",
                "Tesla Model S",
                "https://en.wikipedia.org/wiki/Tesla_Model_S"));

// BMW
        lista.add(new ImagineMasina(
                "https://commons.wikimedia.org/wiki/Special:FilePath/BMW_M3_Competition.jpg?width=400",
                "BMW M3",
                "https://en.wikipedia.org/wiki/BMW_M3"));

// Golf
        lista.add(new ImagineMasina(
                "https://commons.wikimedia.org/wiki/Special:FilePath/Volkswagen_Golf_GTi_1.jpg?width=400",
                "Volkswagen Golf",
                "https://en.wikipedia.org/wiki/Volkswagen_Golf"));

// Mustang
        lista.add(new ImagineMasina(
                "https://commons.wikimedia.org/wiki/Special:FilePath/Ford_Mustang_VII.jpg?width=400",
                "Ford Mustang",
                "https://en.wikipedia.org/wiki/Ford_Mustang"));

        lv.setAdapter(new ImagineMasinaAdapter(this, lista, R.layout.item_imagine));

        lv.setOnItemClickListener((parent, view, i, id) -> {
            Intent it = new Intent(GalerieActivity.this, WebViewActivity.class);
            it.putExtra("link", lista.get(i).getUrlPagina());
            startActivity(it);
        });
    }
}