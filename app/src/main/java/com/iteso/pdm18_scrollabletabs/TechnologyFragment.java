package com.iteso.pdm18_scrollabletabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import java.util.ArrayList;
import java.util.Iterator;

public class TechnologyFragment extends Fragment {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ItemProduct> mDataSet;

    public TechnologyFragment() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        RecyclerView recyclerView = (RecyclerView)
                view.findViewById(R.id.fragment_recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        mDataSet = new ArrayList<ItemProduct>();
        mDataSet.add(new ItemProduct("Mac", "BestBuy", "Zapopan","3310156716","Llevate esta.....", 0, 0));
        mDataSet.add(new ItemProduct("Alienware", "BestBuy", "Guadalajara","33110156787","Llevate esta...", 1, 1));
        mDataSet.add(new ItemProduct("Lanix", "BestBuy", "Tlaquepaque","3310157681","Llevate esta.....", 0, 2));

        mAdapter = new AdapterProduct(getActivity(), mDataSet);
        recyclerView.setAdapter(mAdapter);
        return view;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        ItemProduct itemProduct = data.getParcelableExtra("ITEM");
        Iterator<ItemProduct> iterator = mDataSet.iterator();
        int position = 0;
        while(iterator.hasNext()){
            ItemProduct item = iterator.next();
            if(item.getCode() == itemProduct.getCode()){
                mDataSet.set(position, itemProduct);
                break;
            }
            position++;
        }
        mAdapter.notifyDataSetChanged();
    }
}
