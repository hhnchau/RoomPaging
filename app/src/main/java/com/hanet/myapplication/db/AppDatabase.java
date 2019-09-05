package com.hanet.myapplication.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hanet.myapplication.model.Car;

@Database(entities = {Car.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public static AppDatabase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "car.db")
                    //.openHelperFactory(new AssetSQLiteOpenHelperFactory())
                    //.allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }


    public abstract CarDao carDao();

}
