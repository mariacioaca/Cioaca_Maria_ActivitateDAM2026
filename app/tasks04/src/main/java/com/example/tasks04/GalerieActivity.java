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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lvGalerie), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView lv = findViewById(R.id.lvGalerie);

        List<String> linkuriImagini=new ArrayList<>();
        linkuriImagini.add("https://thumb.wikimedia.org/wikipedia/commons/thumb/c/c6/2013_Porsche_911_Carrera_4S_%28991%29_%289626546987%29.jpg/1920px-2013_Porsche_911_Carrera_4S_%28991%29_%289626546987%29.jpg?utm_source=commons.wikimedia.org&utm_campaign=index&utm_content=thumbnail&_=20131024163505");
        linkuriImagini.add("https://encrypted-tbn0.gstatic.com/images?");
        linkuriImagini.add("https://encrypted-tbn0.gstatic.com/images?");
        linkuriImagini.add("https://encrypted-tbn0.gstatic.com/images?");
        linkuriImagini.add("https://encrypted-tbn0.gstatic.com/images?");


        Executor executor= Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
        List<Bitmap> imagini = new ArrayList<>();
        executor.execute(new Runnable(){
            @Override
            public void run(){
                for(String link: linkuriImagini){
                    HttpURLConnection con=null;
                    try{
                        URL url=new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is =con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                        is.close();
                        con.disconnect();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        imagini.add(null);
                    }
                }
            }

        });
        handler.post(new Runnable(){
            @Override
            public void run(){
                lista = new ArrayList<>();
               // lista.add();

            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(GalerieActivity.this, WebViewActivity.class);
                it.putExtra("link", lista.get(i).getUrlImagine());
                startActivity(it);
            }
        });
    }
}