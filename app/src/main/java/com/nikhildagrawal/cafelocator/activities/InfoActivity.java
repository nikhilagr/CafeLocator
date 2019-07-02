package com.nikhildagrawal.cafelocator.activities;

import android.os.Bundle;

import com.nikhildagrawal.cafelocator.R;
import com.nikhildagrawal.cafelocator.adapter.ListAdapter;
import com.nikhildagrawal.cafelocator.models.Cafes;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InfoActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ListAdapter mAdapter;
    ArrayList<Cafes> list;
    RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafe);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Cafes");


        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        list = GetAsyncData.resultList;
        mAdapter = new ListAdapter(this,list);
        recyclerView.setAdapter(mAdapter);

       
    }
}
