package com.iteso.pdm18_scrollabletabs;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;

public class ActivityProduct extends AppCompatActivity {
    public EditText Title;
    public EditText Store;
    public EditText Location;
    public EditText Phone;
    public ImageView Image;
    Button save, cancel;
    ItemProduct itemProduct,i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Title = (EditText) findViewById(R.id.title);
        Store = (EditText) findViewById(R.id.store);
        Location = (EditText) findViewById(R.id.location);
        Phone = (EditText) findViewById(R.id.phone);
        Image = (ImageView) findViewById(R.id.image);
        save=(Button)findViewById(R.id.activity_detail_save);
        cancel=(Button)findViewById(R.id.activity_detail_cancel);

        if(getIntent().getExtras()!=null){
            itemProduct=getIntent().getParcelableExtra("ITEM");
            if(itemProduct!=null){
                Title.setText(itemProduct.getTitle());
                Store.setText(itemProduct.getStore());
                Location.setText(itemProduct.getLocation());
                Phone.setText(itemProduct.getPhone());

                switch(itemProduct.getImage()){
                    case 0:
                        Image.setImageResource(R.drawable.mac); break;
                    case 1:
                        Image.setImageResource(R.drawable.alienware); break;
                    case 2:
                        Image.setImageResource(R.drawable.mac); break;
                }
            }
        }
        save.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
          i=new ItemProduct();
                i.setTitle(Title.getText().toString());
                i.setStore(Store.getText().toString());
                i.setLocation(Location.getText().toString());
                i.setPhone(Phone.getText().toString());
                i.setImage(itemProduct.getImage());
                i.setCode(itemProduct.getCode());

                Intent intent = new Intent();
                intent.putExtra("ITEM", i);
                setResult(Activity.RESULT_OK, intent);
                finish();
        }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
