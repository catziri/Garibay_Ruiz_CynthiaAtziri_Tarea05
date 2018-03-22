package com.iteso.pdm18_scrollabletabs.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.Store;

import java.util.ArrayList;

/**
 * Created by cgaribay on 20/03/2018.
 */

public class CategoryControl {
    public ArrayList<Category> getCategories(DataBaseHandler dh){
        ArrayList<Category> categories=new ArrayList<Category>();
        String selectQuery = "SELECT C." + DataBaseHandler.KEY_CATEGORY_ID + ","
                + "C." + DataBaseHandler.KEY_CATEGORY_NAME
                + " FROM "
                + DataBaseHandler.TABLE_CATEGORY + " C ";
        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Category category=new Category();
                category.setId(cursor.getInt(0));
                category.setName(cursor.getString(1));
                categories.add(category);
            }while(cursor.moveToNext());
        }
        try {cursor.close();db.close();
        } catch (Exception e) {}
        db = null;
        cursor = null;
        return categories;
    }
}
