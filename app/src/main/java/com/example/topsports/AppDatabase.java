package com.example.topsports;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Sportiv.class, ProfileUser.class}, version = 7 ,exportSchema = false)
public abstract
class AppDatabase  extends RoomDatabase {


    private final static String DB_NAME = "sportivi.db";
    private static AppDatabase instanta;

    public static AppDatabase getInstance(Context context){
        if(instanta == null){
            instanta = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instanta;
    }
    public abstract SportivDao sportivDao();
}
