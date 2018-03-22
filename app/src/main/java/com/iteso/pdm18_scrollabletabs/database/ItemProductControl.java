package com.iteso.pdm18_scrollabletabs.database;

import android.content.ClipData;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.City;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;

import java.util.ArrayList;

/**
 * Created by cgaribay on 20/03/2018.
 */

public class ItemProductControl {
    public void addItemProduct(ItemProduct st, DataBaseHandler dh) {
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_PRODUCT_ID , st.getCode());
        values.put(DataBaseHandler.KEY_PRODUCT_TITLE, st.getTitle());
        values.put(DataBaseHandler.KEY_PRODUCT_IMAGE , st.getImage());
        values.put(DataBaseHandler.KEY_PRODUCT_CATEGORY, st.getCategory().getId());
        // Inserting Row
        db.insert(DataBaseHandler.TABLE_PRODUCT, null, values);
        ContentValues values1 = new ContentValues();
        values1.put(DataBaseHandler.KEY_SPRODUCT_IDS, st.getStore().getId());
        values1.put(DataBaseHandler.KEY_SPRODUCT_IDP, st.getCode());
        db.insert(DataBaseHandler.TABLE_STORE_PRODUCT, null, values1);
        // Closing database connection
        try {db.close();} catch (Exception e) {}
        db = null; values = null;
    }


    public ArrayList<ItemProduct> getItemProductsByCategory(DataBaseHandler dh,int
            idCategory){
        ArrayList<ItemProduct> products=new ArrayList<ItemProduct>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String selectQuery = "SELECT P." + DataBaseHandler.KEY_PRODUCT_ID + ","
                + " P." + DataBaseHandler.KEY_PRODUCT_TITLE + ","
                + " P." + DataBaseHandler.KEY_PRODUCT_IMAGE+ ","
                + " P." + DataBaseHandler.KEY_PRODUCT_CATEGORY +","
                + " CA." + DataBaseHandler.KEY_CATEGORY_NAME +","
                + " ST." + DataBaseHandler.KEY_SPRODUCT_IDS +","
                + " S." + DataBaseHandler.KEY_STORE_NAME +","
                + " S." + DataBaseHandler.KEY_STORE_PHONE +","
                + " S." + DataBaseHandler.KEY_STORE_CITY +","
                + " S." + DataBaseHandler.KEY_STORE_THUMBNAIL +","
                + " S." + DataBaseHandler.KEY_STORE_LAT +","
                + " S." + DataBaseHandler.KEY_STORE_LNG +","
                + " CI." + DataBaseHandler.KEY_CITY_NAME
                + " FROM "
                + DataBaseHandler.TABLE_PRODUCT + " P "
                +" INNER JOIN "
                + DataBaseHandler.TABLE_CATEGORY + " CA ON P."+
                DataBaseHandler.KEY_PRODUCT_CATEGORY  + " = " + " CA. "+DataBaseHandler.KEY_CATEGORY_ID  +
                " INNER JOIN "+ DataBaseHandler.TABLE_STORE_PRODUCT + " ST ON P. "
                +DataBaseHandler.KEY_PRODUCT_ID  + " = " + "ST. "+DataBaseHandler.KEY_SPRODUCT_IDP
                +" INNER JOIN " + DataBaseHandler.TABLE_STORE + " S ON S."
                +DataBaseHandler.KEY_STORE_ID+ " = " + "ST. "+DataBaseHandler.KEY_SPRODUCT_IDS
                + " INNER JOIN "+ DataBaseHandler.TABLE_CITY + " CI ON S."+DataBaseHandler.KEY_STORE_CITY + " = " + " CI. "+DataBaseHandler.KEY_CITY_ID
                +  "  WHERE P."+ DataBaseHandler.KEY_PRODUCT_CATEGORY + " = " + idCategory;

        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                ItemProduct product=new ItemProduct();
                product.setCode(cursor.getInt(0));
                product.setTitle(cursor.getString(1));
                product.setImage(cursor.getInt(2));
                Category category=new Category();
                category.setId(cursor.getInt(3));
                category.setName(cursor.getString(4));
                product.setCategory(category);
                Store store=new Store();
                store.setId(cursor.getInt(5));
                store.setName(cursor.getString(6));
                store.setPhone(cursor.getString(7));
                product.setStore(store);
                City city=new City();
                city.setId(cursor.getInt(8));
                store.setCity(city);
                store.setThumbnail(cursor.getInt(9));
                store.setLatitude(cursor.getDouble(10));
                store.setLongitude(cursor.getDouble(11));
                city.setName(cursor.getString(12));

                products.add(product);
            }while(cursor.moveToNext());
        }
        try {cursor.close();db.close();
        } catch (Exception e) {}
        db = null;
        cursor = null;
        // return store
        return products;
    }
}
