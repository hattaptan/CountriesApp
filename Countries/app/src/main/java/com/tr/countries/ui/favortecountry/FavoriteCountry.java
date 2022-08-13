package com.tr.countries.ui.favortecountry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.tr.countries.R;

import java.util.ArrayList;
import java.util.List;

import com.tr.countries.network.CountryService;
import com.tr.countries.ui.home.MainActivity;
import com.tr.countries.ui.home.RecyclerViewAdapter;

public class FavoriteCountry extends AppCompatActivity{
    ImageView imageView2;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    public CountryService countryService;
    ArrayList<String> getLikedCountry;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_country);


        recyclerView = findViewById(R.id.recyclerView);
        imageView2=findViewById(R.id.imageView2);
        String countryNameLiked = getIntent().getStringExtra("LikedC");
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnMainActivity();
            }
        });


    }




    public void returnMainActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }



}
