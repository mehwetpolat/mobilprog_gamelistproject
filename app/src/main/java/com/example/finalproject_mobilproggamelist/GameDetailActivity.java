package com.example.finalproject_mobilproggamelist;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameDetailActivity extends AppCompatActivity {

    private TextView oyunAcıklaması;
    private String videoLink;

    private TextView oyununIsmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);

        // Views
        oyunAcıklaması = findViewById(R.id.gameDescription);

        oyununIsmi = findViewById(R.id.txtOyunisim);


        // intentten gelen veriyi al
        String isimOyun = getIntent().getStringExtra("oyunisim");
        String oyunAcıklamametni = getIntent().getStringExtra("oyunaciklama");

        oyununIsmi.setText(isimOyun);
        videoLink = getIntent().getStringExtra("oyunvideolinki");

        // açıklamayı ekranda göster
        oyunAcıklaması.setText(oyunAcıklamametni);


        // video oynatım
        WebView oyunVideosu = findViewById(R.id.webOyunvideo);
        oyunVideosu.loadData(videoLink, "text/html", "utfs-8");

        oyunVideosu.getSettings().setJavaScriptEnabled(true);
        oyunVideosu.setWebChromeClient(new WebChromeClient());
    }




}
