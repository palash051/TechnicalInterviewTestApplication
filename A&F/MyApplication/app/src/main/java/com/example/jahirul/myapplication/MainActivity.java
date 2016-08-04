package com.example.jahirul.myapplication;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements JsonParserService.IApplicationDataUpdater{

    RecyclerView rvApplicationList;
    JsonParserService jsonParserService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_view);

        rvApplicationList= (RecyclerView) findViewById(R.id.rvApplicationList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvApplicationList.setLayoutManager(mLayoutManager);
        rvApplicationList.setHasFixedSize(true);
        rvApplicationList.setItemAnimator(new DefaultItemAnimator());
        rvApplicationList.addItemDecoration(new DividerItemDecoration(this));
        rvApplicationList.setAdapter(new ProductCartAdapter(this, new ArrayList<Product>()));
        jsonParserService=new JsonParserService(this);
        jsonParserService.loadApplicationList();

    }

    @Override
    public void showApplicationData(ArrayList<Product> applicationEntities) {
        rvApplicationList.setAdapter(new ProductCartAdapter(this, applicationEntities));
    }
}
