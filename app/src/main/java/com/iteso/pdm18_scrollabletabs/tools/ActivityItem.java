package com.iteso.pdm18_scrollabletabs.tools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.iteso.pdm18_scrollabletabs.R;
import com.iteso.pdm18_scrollabletabs.beans.Category;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.beans.Store;
import com.iteso.pdm18_scrollabletabs.database.CategoryControl;
import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm18_scrollabletabs.database.ItemProductControl;
import com.iteso.pdm18_scrollabletabs.database.StoreControl;

import java.util.ArrayList;

public class ActivityItem extends AppCompatActivity {
    Spinner image, category, store;
    EditText title;
    Button save;
    DataBaseHandler dataBaseHandler;
    StoreControl storeControl;
    ItemProductControl itemProductControl;
    CategoryControl categoryControl;
    ArrayAdapter<Store> storesAdapter;
    ArrayAdapter<Category> categoriesAdapter;
    ArrayAdapter<String> imagesAdapter;
    ArrayList<String>myimages=new ArrayList<>();
    int imageSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        dataBaseHandler = DataBaseHandler.getInstance(getApplicationContext());
        storeControl = new StoreControl();
        itemProductControl = new ItemProductControl();
        categoryControl = new CategoryControl();

        image=findViewById(R.id.activity_item_image);
        category=findViewById(R.id.activity_item_category);
        store=findViewById(R.id.activity_item_store);
        save=findViewById(R.id.activity_item_save);

        myimages.add("Mac"); myimages.add("Alienware");
        imagesAdapter=new ArrayAdapter<String>(this,R.layout.activity_item,myimages);
        image.setAdapter(imagesAdapter);


        ArrayList<Category> categoriesList = categoryControl.getCategories(dataBaseHandler);
        categoriesAdapter=new ArrayAdapter<Category>(this,R.layout.activity_item,categoriesList);
        categoriesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        category.setAdapter(categoriesAdapter);

        ArrayList<Store> storesList = storeControl.getStores(dataBaseHandler);
        storesAdapter=new ArrayAdapter<Store>(this,R.layout.activity_item,storesList);
        storesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        store.setAdapter(storesAdapter);

        image.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageSelected = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemProduct itemProduct=new ItemProduct();
                itemProduct.setStore((Store)store.getSelectedItem());
                itemProduct.setCategory((Category)category.getSelectedItem());
                itemProduct.setTitle(title.getText().toString());
                itemProduct.setImage(imageSelected);
                itemProduct.setDescription("hk");
                itemProductControl.addItemProduct(itemProduct,dataBaseHandler);
                finish();
            }
        });

    }
}
