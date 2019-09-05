package com.hanet.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.hanet.myapplication.db.helper.SQLiteAssetHelper;
import com.hanet.myapplication.model.Car;

@Database(entities = {Car.class}, version = 5, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "car.db";
    private static final int VERSION = 1;
    private static AppDatabase instance;

    public static AppDatabase getAppDatabase(Context context) {
        if (instance == null) {
            //copyDB(context, DB_NAME, null, null, VERSION);
            instance = Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
//                    .allowMainThreadQueries()
//                    .addMigrations(new Migration(1, 2) {
//                        @Override
//                        public void migrate(@NonNull SupportSQLiteDatabase database) {
//                            Log.w("DB", "migrate from version 1 to 2 ");
//                        }
//                    })
                    .build();
        }
        return instance;
    }

    private static void copyDB(Context context, String dbName, String path, SQLiteDatabase.CursorFactory factory, int version) {
        new SQLiteAssetHelper(context, dbName, path, factory, version).getWritableDatabase().close();
    }

    public static void destroyInstance() {
        instance = null;
    }


    public abstract CarDao carDao();

}
