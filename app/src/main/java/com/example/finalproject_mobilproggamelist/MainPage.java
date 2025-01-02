package com.example.finalproject_mobilproggamelist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainPage extends AppCompatActivity {

    private GameDataBase database;
    private ListView gameListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        gameListView = findViewById(R.id.lstOyunList);

        database = GameDataBase.getInstance(MainPage.this);

        //
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(new Runnable() {
            @Override
            public void run() {
                database.gameDao().deleteAllGames();
                kayıtEkle(); // yeni kayıtları ekleme

                // veritabanından verileri alma
                List<GameDB> gameList = database.gameDao().getAllGames();
                runOnUiThread(() -> {
                    // arayüzde listeyi gösterme
                    GameAdapter adapter = new GameAdapter(MainPage.this, gameList);
                    gameListView.setAdapter(adapter);
                });
            }
        });


        // listviewdeki bir içeriğe tıklandığında
        gameListView.setOnItemClickListener((parent, view, position, id) -> {
            // tıklanan verinin bilgilerini al
            GameDB clickedGame = (GameDB) parent.getItemAtPosition(position);

            // içeriğe ait verileri tutarak yeni sayfaya git
            Intent intent = new Intent(MainPage.this, GameDetailActivity.class);
            intent.putExtra("oyunisim", clickedGame.getOyunisim());
            intent.putExtra("oyunaciklama", clickedGame.getOyunaciklama());
            intent.putExtra("oyunvideolinki", clickedGame.getOyunvideolinki());
            startActivity(intent);
        });
    }



    // veritabanı kayıtları
    @SuppressLint("ResourceType")
    private void kayıtEkle() {

            GameDB game1 = new GameDB();
            game1.setOyunisim("The Last Of Us: Part I");
            game1.setOyunfiyat("1500₺");
            game1.setOyunaciklama("The Last of Us, aksiyon-macera video oyunu serisidir. Seri, Cordyceps cinsindeki mutasyona uğramış bir mantarın bulaştırdığı verem sonucu yamyam insanların istila ettiği kıyamet sonrası Amerika Birleşik Devletleri'nde geçiyor. Hikaye, salgın sırasında kızını kaybeden kaçakçı Joel, enfeksiyona karşı bağışıklık kazanan genç bir kız olan Ellie ve milisleri ile dini bir tarikat arasındaki çatışmaya karışan asker Abby de dahil olmak üzere birkaç kurtulanın hikayesini konu almaktadır. Serideki oyunlarda, oyuncunun ateşli silahlar ve gizlilik kullanarak düşmanlara ve yamyam yaratıklara karşı savaştığı üçüncü şahıs bakış açısı bulunur.");
            game1.setOyunvideolinki("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/usjYrOaIsAs?si=acBQxjKdeOPEn8Hj\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>");
            game1.setOyunLogosu(R.drawable.lastofusp1);

            GameDB game2 = new GameDB();
            game2.setOyunisim("Uncharted 4");
            game2.setOyunfiyat("1200₺");
            game2.setOyunaciklama("Uncharted 4: Bir Hırsızın Sonu, Naughty Dog tarafından geliştirilen, Sony Computer Entertainment tarafından ise yayımcılığı yapılan; hikâye odaklı, üçüncü şahıs nişancı ve aksiyon-macera video oyunudur. PlayStation 4'e özel olarak geliştirilmiştir. 2021 yılında ise oyunun Microsoft Windows platformuna geleceği açıklanmıştır. Oyun yakın zaman içerisinde Uncharted: Legacy of Thieves Collection adı altında 2022 yılında çıkmak üzere Uncharted: Kayıp Miras ile birlikte dijital platformlarda yerini aldı.");
            game2.setOyunvideolinki("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/jQ9FQoi1xyA?si=5MxQDqlV1WnoZjLB\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>");
            game2.setOyunLogosu(R.drawable.uncharted4);

            GameDB game3 = new GameDB();
            game3.setOyunisim("Detroit Become Human");
            game3.setOyunfiyat("300₺");
            game3.setOyunaciklama("Oyun Kara, Connor ve Markus adlı üç androidin hikâyesine odaklanmaktadır. Hizmet androidi olan Kara evrilip bilinç sahibi olarak efendisinin android kızıyla birlikte kaçmış, bakıcı android Markus kendisini androidlerin özgürlük mücadelesine adamış, Connor ise bilinç sahibi androidleri avlamak üzere görevlendirilmiştir. Bir noktadan sonra hikâyeleri kesişen karakterlerin her birinin kendi konuşma ağacı vardır, karakterlerin yaşama ihtimali oyuncunun kendi seçimlerine bağlıdır.");
            game3.setOyunvideolinki("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/QD1pbWCJcKQ?si=DNMRCltKgWnc9VKn\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>");
            game3.setOyunLogosu(R.drawable.detroit);

            database.gameDao().insertGame(game1);
            database.gameDao().insertGame(game2);
            database.gameDao().insertGame(game3);
    }
}
