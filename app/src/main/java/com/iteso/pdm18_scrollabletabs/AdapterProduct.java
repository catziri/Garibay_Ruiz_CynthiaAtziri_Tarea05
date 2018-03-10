package com.iteso.pdm18_scrollabletabs;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;

import java.util.ArrayList;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder>{

    private ArrayList<ItemProduct> mDataSet;
    private Context context;

    public AdapterProduct(Context context, ArrayList<ItemProduct> myDataSet) {
        mDataSet = myDataSet;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public Button mDetail;
        public Button mShare;
        public TextView mTitle;
        public TextView mStore;
        public TextView mLocation;
        public TextView mPhone;
        public ImageView mProductImage;
        public ImageView mProductThumbnail;
        public RelativeLayout mEventLayout;

        public ViewHolder(View v){
            super(v);
            mEventLayout = (RelativeLayout) v.findViewById(R.id.item_product_layout);
            mDetail = (Button)v.findViewById(R.id.item_product_detail);
            mShare = (Button)v.findViewById(R.id.item_product_share);
            mTitle = (TextView)v.findViewById(R.id.item_product_title);
            mStore = (TextView)v.findViewById(R.id.item_product_store);
            mLocation = (TextView)v.findViewById(R.id.item_product_location);
            mPhone = (TextView)v.findViewById(R.id.item_product_phone);
            mProductImage = (ImageView) v.findViewById(R.id.item_product_image);
            mProductThumbnail = (ImageView) v.findViewById(R.id.item_product_thumbnail);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitle.setText(mDataSet.get(position).getTitle());
        holder.mStore.setText(mDataSet.get(position).getStore());
        holder.mLocation.setText(mDataSet.get(position).getLocation());
        holder.mPhone.setText(mDataSet.get(position).getPhone());
        switch(mDataSet.get(position).getImage()){
            case 0:
                holder.mProductImage.setImageResource(R.drawable.mac); break;
            case 1:
                holder.mProductImage.setImageResource(R.drawable.alienware); break;
            case 2:
                holder.mProductImage.setImageResource(R.drawable.mac); break;
        }

        holder.mPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + mDataSet.get(position).getPhone()));
                context.startActivity(intent);
            }
        });

        holder.mEventLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemProduct iit=new ItemProduct();
                iit.setTitle(mDataSet.get(position).getTitle());
                iit.setStore(mDataSet.get(position).getStore());
                iit.setLocation(mDataSet.get(position).getLocation());
                iit.setPhone(mDataSet.get(position).getPhone());
                iit.setCode(mDataSet.get(position).getCode());
                iit.setImage(mDataSet.get(position).getImage());
                Intent intent = new Intent(context, ActivityProduct.class);
                intent.putExtra("ITEM", iit);
                ((ActivityMain) context).startActivityForResult(intent, mDataSet.get(position).getCode());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
