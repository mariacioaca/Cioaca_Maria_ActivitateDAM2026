package com.example.tasks04;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class ImagineMasinaAdapter extends BaseAdapter {

private ArrayList<ImagineMasina> imagini;
private Context context;
private int resursaLayout;
    public ImagineMasinaAdapter(Context context, ArrayList<ImagineMasina> imagini, int resursaLayout) {
        this.context = context;
        this.imagini = imagini;
        this.resursaLayout=resursaLayout;
    }

    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int position) {
        return imagini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(resursaLayout, parent, false);
        ImagineMasina m = (ImagineMasina) getItem(i);
        ((TextView) v.findViewById(R.id.tvDescriere)).setText(m.getDescriere());
        ImageView img = v.findViewById(R.id.imgMasina);
        String urlImagine = m.getUrlImagine();
        img.setTag(urlImagine);
        new Thread(() -> {
            try {
                URL url = new URL(urlImagine);
                InputStream input = url.openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(input);
                input.close();
                img.post(() -> {
                    if (urlImagine.equals(img.getTag())) {
                        img.setImageBitmap(bitmap);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        return v;
    }
}

