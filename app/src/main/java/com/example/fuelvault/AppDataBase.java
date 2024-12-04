package com.example.fuelvault;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AppDataBase extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "CarFuelDetails";
    public static final int DATABASE_VERSION= 1;


    public static final String TABLE_NAME = "CFD";
    public static final String COLUMN_ID = "id";


    public AppDataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
