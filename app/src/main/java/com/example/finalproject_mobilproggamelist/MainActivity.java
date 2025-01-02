package com.example.finalproject_mobilproggamelist;


import static android.content.Context.CONNECTIVITY_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ImageView imgLogoyun = findViewById(R.id.imgLogo);

        // logo döndürme animasyonu
        Animation dondurme = AnimationUtils.loadAnimation(MainActivity.this, R.anim.logodondurme);
        imgLogoyun.startAnimation(dondurme);



        // Asenkron kontrol işlemlerim
        ExecutorService es = Executors.newFixedThreadPool(2);

        // internet bağlantı kontrolü
        es.execute(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // internet baglantısı kontrolü
                        if (!isInternetAvailable()) {
                            Toast.makeText(MainActivity.this, "İnternet bağlantısı yok", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "İnternet bağlantısı mevcut.", Toast.LENGTH_SHORT).show();

                            // internet varsa uygulamaya giris yapar
                            new Handler().postDelayed(() -> {
                                Intent intent = new Intent(MainActivity.this, MainPage.class);
                                startActivity(intent);
                                finish();
                            }, 5200); // 5.2sn bekletme
                        }
                    }
                });
            }
        });


        GameDataBase db = Room.databaseBuilder(MainActivity.this, GameDataBase.class, "my_database").build();
        // veritabanı bağlantı kontrolü
        es.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    GameDAO gmDao = db.gameDao();
                    gmDao.getAllGames();

                    // eriştiğinde
                    runOnUiThread(() ->
                            Toast.makeText(MainActivity.this, "Veritabanına erişildi.", Toast.LENGTH_SHORT).show()
                    );
                } catch (Exception e) {

                    runOnUiThread(() ->
                            Toast.makeText(MainActivity.this, "Veritabanına erişilemedi: " + e.getMessage(), Toast.LENGTH_LONG).show()
                    );
                }
            }
        });
    }



    // internet baglantısı kontrol metodu
    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }
}