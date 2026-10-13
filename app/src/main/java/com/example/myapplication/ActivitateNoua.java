package com.example.myapplication;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivitateNoua extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_noua);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    void openActivity(){
    Intent intent=new Intent(getApplicationContext(),ActivitateRezultat.class);
    startActivity(intent);
    }
    public static class MainActivity {
    }

    public void deschideActivitate(View view){
        Intent it=new Intent(this, ActivitateRezultat.class);
        Bundle bundle=new Bundle();
        bundle.putInt("numar1",6);
        bundle.putInt("numar2",8);
        bundle.putString("mesaj", "Mesaj transmis din prima activitate");
        it.putExtras(bundle);
        startActivityForResult(it,37);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller) {
        super.onActivityResult(requestCode, resultCode, data, caller);
        if(requestCode==37){
            if(resultCode==RESULT_OK) {
                String mesaj = data.getStringExtra("mesaj");
                int suma = data.getIntExtra("suma", 0);
                Toast.makeText(this,mesaj+" "+suma,Toast.LENGTH_SHORT).show();
            }
        }
    }
}