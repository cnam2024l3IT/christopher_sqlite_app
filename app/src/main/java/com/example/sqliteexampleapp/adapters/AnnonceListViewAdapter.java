package com.example.sqliteexampleapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sqliteexampleapp.R;
import com.example.sqliteexampleapp.models.Annonce;

import java.util.ArrayList;
import java.util.Locale;

public class AnnonceListViewAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<Annonce> annonces;

    public AnnonceListViewAdapter(Context context, ArrayList<Annonce> annonces) {
        this.context = context;
        this.annonces = annonces;
        this.inflater = (LayoutInflater.from(context));
    }
    @Override
    public int getCount() {
        return annonces.size();
    }

    @Override
    public Annonce getItem(int position) {
        return annonces.get(position);
    }

    @Override
    public long getItemId(int position) {
        return annonces.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_annonce_list_view, null);
        TextView titleView = convertView.findViewById(R.id.annonceTitleItemListView);
        TextView priceView = convertView.findViewById(R.id.annoncePriceItemListView);
        Annonce annonce = getItem(position);
        titleView.setText(annonce.getTitle());
        priceView.setText(String.format(Locale.getDefault(), "%d", annonce.getPrice()));
        return convertView;
    }
}
