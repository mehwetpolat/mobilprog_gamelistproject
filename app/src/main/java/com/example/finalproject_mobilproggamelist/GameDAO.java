package com.example.finalproject_mobilproggamelist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GameDAO {

    @Insert
    void insertGame(GameDB game);

    // tüm kayıtları getirme
    @Query("SELECT * FROM games")
    List<GameDB> getAllGames();

    // kayıtları silme
    @Query("DELETE FROM games")
    void deleteAllGames();
}