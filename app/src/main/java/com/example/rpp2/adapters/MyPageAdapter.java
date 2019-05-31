package com.example.rpp2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rpp2.InfoActivity;
import com.example.rpp2.R;
import com.example.rpp2.Technology;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyPageAdapter extends PagerAdapter {
    private ArrayList<Technology> technologies;
    private LayoutInflater layoutInflater;


    public MyPageAdapter(ArrayList<Technology> technologies, Context context) {
        this.technologies = technologies;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return InfoActivity.technologies.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.page_item, container, false);
        final ImageView techImage = view.findViewById(R.id.pageImage);
        final TextView techText = view.findViewById(R.id.pageText);
        if (technologies.get(position).getHelpText()!=null) {
            techText.setText(technologies.get(position).getHelpText());
        } else {
            techText.setText("No description");
        }
        Picasso.get().load(InfoActivity.technologies.get(position).getImageUrl()).into(techImage, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                techImage.setImageResource(R.drawable.not_found);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}