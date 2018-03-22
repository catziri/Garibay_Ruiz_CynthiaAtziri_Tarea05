package com.iteso.pdm18_scrollabletabs.tools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.pdm18_scrollabletabs.R;
import com.iteso.pdm18_scrollabletabs.beans.ItemProduct;
import com.iteso.pdm18_scrollabletabs.database.DataBaseHandler;
import com.iteso.pdm18_scrollabletabs.database.ItemProductControl;

import java.util.ArrayList;

public class ElectronicsFragment extends Fragment {
    public ElectronicsFragment() {

    }

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ItemProductControl itemProductControl=new ItemProductControl();
    DataBaseHandler dh;
    ArrayList<ItemProduct> mDataSet;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        RecyclerView recyclerView = (RecyclerView)
                view.findViewById(R.id.fragment_recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        mDataSet = new ArrayList<ItemProduct>();
        dh=DataBaseHandler.getInstance(getContext());
        mDataSet=itemProductControl.getItemProductsByCategory(dh,0);
        mAdapter = new AdapterProduct(getActivity(), mDataSet);
        recyclerView.setAdapter(mAdapter);
        itemProductControl=null;

        return view;
    }
}
