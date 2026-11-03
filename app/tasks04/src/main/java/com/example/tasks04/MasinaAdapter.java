package com.example.tasks04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class MasinaAdapter extends BaseAdapter {
    private List<Masina> masini=null;
    private Context ctx;
    private int resursaLayout;

    public MasinaAdapter(List<Masina> masini, Context ctx, int resursaLayout) {
        this.masini = masini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return masini.size();
    }

    @Override
    public Object getItem(int i) {
        return masini.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(resursaLayout, parent, false);
        Masina m = (Masina) getItem(i);
        ((TextView) v.findViewById(R.id.marcaTV)).setText(m.getMarca());
        ((TextView) v.findViewById(R.id.pretTV)).setText("Pret: " + m.getPret() + " EUR");
        ((TextView) v.findViewById(R.id.categorieTV))
                .setText(m.getCategorie() + (m.isEsteNoua() ? " • noua" : " • second-hand"));
        ((CheckBox) v.findViewById(R.id.electricaCB)).setChecked(m.isElectrica());
        return v;
    }
}
