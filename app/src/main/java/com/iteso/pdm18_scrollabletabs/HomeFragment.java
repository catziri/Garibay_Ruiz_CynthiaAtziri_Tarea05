package com.iteso.pdm18_scrollabletabs;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.v4.app.Fragment;

import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    public HomeFragment() {

    }

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        RecyclerView recyclerView = (RecyclerView)
                view.findViewById(R.id.fragment_recycler_view);
        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // Use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        ArrayList<ItemProduct> mDataSet = new ArrayList<ItemProduct>();
        mDataSet = new ArrayList<ItemProduct>();
        mDataSet.add(new ItemProduct("Mac", "BestBuy", "Zapopan","3310156716","Llevate esta.....", 0, 0));
        mDataSet.add(new ItemProduct("Alienware", "BestBuy", "Guadalajara","33110156787","Llevate esta...", 1, 1));
        mDataSet.add(new ItemProduct("Lanix", "BestBuy", "Tlaquepaque","3310157681","Llevate esta.....", 0, 2));

        mAdapter = new AdapterProduct(getActivity(), mDataSet);
        recyclerView.setAdapter(mAdapter);
        return view;
    }
}
