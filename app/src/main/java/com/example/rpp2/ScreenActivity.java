package com.example.rpp2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.example.rpp2.retrofit.RetroFitHandler;
import com.example.rpp2.retrofit.JsonRequest;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScreenActivity extends AppCompatActivity {
    private JsonRequest jsonRequest = RetroFitHandler.InitRetroFitHandler().create(JsonRequest.class);
    public static boolean infoShown = false;
    private Context context=this;
    private int permission=0;

    class SplashEvent extends Thread {
        private Context context;

        SplashEvent(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            try {
                sleep(2000);
            } catch (Exception ex) {
                Logger.getGlobal().info(ex.toString());
            }
            if (!infoShown) {
                Intent intent = new Intent(context, InfoActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.screen_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        Call<List<Technology>> call = jsonRequest.getTechologys();
        call.enqueue(new Callback<List<Technology>>() {
            @Override
            public void onResponse(Call<List<Technology>> call, Response<List<Technology>> response) {
                if (response.body() != null) {
                    List<Technology> technologies = response.body();
                    InfoActivity.technologies = new ArrayList<>();
                    InfoActivity.technologies.addAll(technologies);
                    InfoActivity.technologies.remove(0);

                    File root = android.os.Environment.getExternalStorageDirectory();
                    File dir = new File(root.getAbsolutePath() + "/download");
                    dir.mkdirs();

                    File file = new File(dir, "JsonObject.txt");
                    try {
                        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(ScreenActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, permission);
                            if(permission ==PackageManager.PERMISSION_GRANTED) {
                                Log.e("Permisiton","GRANTED");
                            } else {
                                Log.e("Permisiton","NOT GRANTED");
                            }
                        } else {
                            FileOutputStream fOut = openFileOutput("JsonObject.txt", Context.MODE_APPEND);
                            fOut.write(new Gson().toJson(technologies).getBytes());
                            fOut.close();
                            Log.e("WRITE", "Worked");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("ERROR", "ERROR");
                    }
                    Log.e("Is File exsist",Boolean.toString( file.exists()));
                }
            }

            @Override
            public void onFailure(Call<List<Technology>> call, Throwable t) {

            }
        });
        new SplashEvent(this).start();
    }
}
