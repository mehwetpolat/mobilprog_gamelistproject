package com.example.finalproject_mobilproggamelist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "games")
public class GameDB {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String oyunisim;
    private String oyunfiyat;
    private String oyunaciklama;
    private String oyunvideolinki;

    private int oyunLogosu;



    public int getOyunLogosu() {
        return oyunLogosu;
    }

    public void setOyunLogosu(int oyunLogosu) {
        this.oyunLogosu = oyunLogosu;
    }

    // Getter ve Setter'lar
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOyunisim() {
        return oyunisim;
    }

    public void setOyunisim(String oyunisim) {
        this.oyunisim = oyunisim;
    }

    public String getOyunfiyat() {
        return oyunfiyat;
    }

    public void setOyunfiyat(String oyunfiyat) {
        this.oyunfiyat = oyunfiyat;
    }

    public String getOyunaciklama() {
        return oyunaciklama;
    }

    public void setOyunaciklama(String oyunaciklama) {
        this.oyunaciklama = oyunaciklama;
    }

    public String getOyunvideolinki() {
        return oyunvideolinki;
    }

    public void setOyunvideolinki(String oyunvideolinki) {
        this.oyunvideolinki = oyunvideolinki;
    }
}
