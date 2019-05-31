package com.example.rpp2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rpp2.R;
import com.example.rpp2.Technology;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyListAdapter extends ArrayAdapter<Technology> {
    public MyListAdapter(Context context, ArrayList<Technology> technologies) {
        super(context, 0, technologies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Technology technology = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        final ImageView techImage = convertView.findViewById(R.id.listImage);
        final TextView techName = convertView.findViewById(R.id.listText);
        Picasso.get().load(technology.getImageUrl()).into(techImage, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                techImage.setImageResource(R.drawable.not_found);
            }
        });
        techName.setText(technology.getName());
        return convertView;
    }
}
