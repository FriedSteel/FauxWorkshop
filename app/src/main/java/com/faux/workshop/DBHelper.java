package com.faux.workshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "workshoplist.db";
    private static int DB_VERSION = 1;
    private static String DB_TABLE = "workshopTable";

    private static String COL_ID = "id";
    private static String COL_NAME = "name";
    private static String COL_COMPANY = "cname";
    private static String COL_DURATION = "duration";
    private static String COL_LOCATION = "location";
    private static String COL_FEE = "fee";
    private static String COL_APPLIED = "applied";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + "(" +
                COL_ID + " INTEGER PRIMARY KEY," +
                COL_NAME + " TEXT," +
                COL_COMPANY + " TEXT," +
                COL_DURATION + " TEXT," +
                COL_LOCATION + " TEXT," +
                COL_FEE + " INTEGER," +
                COL_APPLIED + " TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }

    public void addDataModel(Model model) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, model.getName());
        contentValues.put(COL_COMPANY, model.getCompany());
        contentValues.put(COL_DURATION, model.getDuration());
        contentValues.put(COL_LOCATION, model.getLocation());
        contentValues.put(COL_FEE, model.getFee());
        contentValues.put(COL_APPLIED, model.getApplied());

        if (!checkDuplicateDataModel(contentValues)) {
            db.insert(DB_TABLE, null, contentValues);
        }
        db.close();
    }

    private boolean checkDuplicateDataModel(ContentValues contentValues) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE, null, COL_NAME + " = ?",
                new String[]{
                        String.valueOf(contentValues.get(COL_NAME))},
                null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();

            return true;
        } else {
            return false;
        }
    }

    public List<Model> getSummaryDataModel(){
        List<Model> modelList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DB_TABLE + " WHERE " +
                COL_APPLIED + " = 'no'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Model model = new Model();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setName(String.valueOf(cursor.getString(1)));
                model.setCompany(String.valueOf(cursor.getString(2)));
                model.setDuration(String.valueOf(cursor.getString(3)));
                model.setLocation(String.valueOf(cursor.getString(4)));
                model.setFee(Integer.parseInt(cursor.getString(5)));
                model.setApplied(String.valueOf(cursor.getString(6)));

                modelList.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return modelList;
    }

    public List<Model> getSummaryDataModelWhere() {
        List<Model> modelList = new ArrayList<>();

        String selectWhereQuery = "SELECT * FROM " + DB_TABLE + " WHERE " +
                COL_APPLIED + " = 'yes'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectWhereQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Model model = new Model();
                model.setId(Integer.parseInt(cursor.getString(0)));
                model.setName(String.valueOf(cursor.getString(1)));
                model.setCompany(String.valueOf(cursor.getString(2)));
                model.setDuration(String.valueOf(cursor.getString(3)));
                model.setLocation(String.valueOf(cursor.getString(4)));
                model.setFee(Integer.parseInt(cursor.getString(5)));
                model.setApplied(String.valueOf(cursor.getString(6)));

                modelList.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return modelList;
    }

    public void updateDataModel(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_APPLIED, "yes");

        db.update(DB_TABLE, contentValues, COL_ID + " = " + id, null);
        db.close();
    }
}
