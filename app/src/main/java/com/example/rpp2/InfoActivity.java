package com.example.rpp2;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rpp2.adapters.MyListAdapter;
import com.example.rpp2.adapters.MyPageAdapter;

import java.util.ArrayList;
import java.util.logging.Logger;

public class InfoActivity extends AppCompatActivity {
    private boolean doubleTap = false;
    private ListView listView;
    private ViewPager viewPager;
    static public ArrayList<Technology> technologies;
    private static int currPage = 0;
    private static boolean pagActive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);
        listView = findViewById(R.id.listView);
        viewPager = findViewById(R.id.viewPager);
        ScreenActivity.infoShown=true;
        if (pagActive) {
            getSupportActionBar().setTitle(technologies.get(currPage).getName());
            viewPager.setCurrentItem(currPage);
            viewPager.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            getSupportActionBar().setTitle("Technology list");
        }
        final MyPageAdapter customPageAdapter = new MyPageAdapter(technologies, this);
        final ListAdapter adapter = new MyListAdapter(this, technologies);
        viewPager.setAdapter(customPageAdapter);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currPage = position;
                viewPager.setCurrentItem(currPage);
                getSupportActionBar().setTitle(technologies.get(currPage).getName());
                listView.setVisibility(View.GONE);
                viewPager.setVisibility(View.VISIBLE);
                pagActive = true;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                getSupportActionBar().setTitle(technologies.get(i).getName());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (!listView.isShown()) {
            listView.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.GONE);
            getSupportActionBar().setTitle("Technology list");
            pagActive = false;
        } else {
            if (doubleTap) {
                finishAffinity();
                ScreenActivity.infoShown = false;
            } else {
                doubleTap = true;
                Toast.makeText(this, "Please tap BACK again to exit", Toast.LENGTH_SHORT).show();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            doubleTap = false;
                        } catch (InterruptedException ex) {
                            Logger.getGlobal().info(ex.toString());
                        }
                    }
                });
                thread.start();
            }
        }
    }
}
