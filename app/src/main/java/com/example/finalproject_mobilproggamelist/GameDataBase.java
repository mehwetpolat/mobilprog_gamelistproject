package com.example.finalproject_mobilproggamelist;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {GameDB.class}, version = 2)
public abstract class GameDataBase extends RoomDatabase {

    private static GameDataBase instance;

    public abstract GameDAO gameDao();

    public static synchronized GameDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            GameDataBase.class, "game_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
