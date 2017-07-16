package com.example.gautamnarayanan.t3;

import android.content.Context;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    RecyclerView Gview;
    RecyclerView.LayoutManager Gmanager;
    Bundle extras;
    ArrayList<PokeObject> values;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        Gview = (RecyclerView)findViewById(R.id.Gview);
        Gmanager=new LinearLayoutManager(this);
        extras=getIntent().getExtras();
        values= extras.getParcelableArrayList("pokemon");
        Gview.setLayoutManager(Gmanager);
        CustomAdapter gdapter = new CustomAdapter(this,values);
        Gview.setAdapter(gdapter);
    }
}
