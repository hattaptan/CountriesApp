package com.tr.countries.ui.home;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.tr.countries.data.Countries;
import com.tr.countries.R;
import com.tr.countries.data.Date;
import com.tr.countries.data.FavoriteCounty;
import com.tr.countries.di.IntentKey;
import com.tr.countries.di.MyActivity;
import com.tr.countries.ui.countrydetail.CountryDetail;
import com.tr.countries.ui.favortecountry.FavoriteCountry;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends MyActivity implements AdapterListener{
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    public static ToggleButton toggle;
    ImageView img;
    ToggleButton likedCountryBTN;
    ImageButton homeBackBtn;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        toggle= (ToggleButton) findViewById(R.id.toggleBtn);
        likedCountryBTN = (ToggleButton) findViewById(R.id.likedCountryBTN);
        getCountryInfo();
        homeBackBtn= findViewById(R.id.homeBackBtn);





        homeBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Are you sure to exit")
                        //.setIcon(R.drawable.)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                finish();
                                System.exit(0);
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create().show();
            }
        });





    }



    private void setAdapter(ArrayList<Date> dt){
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter = new RecyclerViewAdapter(dt);
        recyclerViewAdapter.adapterListener = this;
        recyclerView.setAdapter(recyclerViewAdapter);

    }


    public void getCountryInfo() {
        Call<Countries> call = countryService.getCountryData();
        call.enqueue(new Callback<Countries>() {
            @Override
            public void onResponse(Call<Countries> call, Response<Countries> response) {
                if (response.isSuccessful()) {
                    Countries responseList;
                    responseList = response.body();
                    ArrayList<Date> dt = new ArrayList<>(responseList.data);
                    setAdapter(dt);
                }
            }
            @Override
            public void onFailure(Call<Countries> call, Throwable t) {

                t.printStackTrace();
                System.out.println("onFailure Hatası");
            }
        });


    }

    public void ActivityFavoryteCountry(){
        Intent intent = new Intent(this, FavoriteCountry.class);

        startActivity(intent);
    }
    private void startIntent(String countryCode){
        Toast.makeText(this, "DETAIL", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, CountryDetail.class);
        intent.putExtra(IntentKey.COUNTRY_NAME.name(),countryCode);
        startActivity(intent);

    }

    @Override
    public void addToFavorite(Date date) {
        Toast.makeText(this, "AddToFavorite", Toast.LENGTH_SHORT).show();
        FavoriteCounty favoriteCounty = new FavoriteCounty(date.code,date.name,date.wikiDataID);
        countryRepo.insert(favoriteCounty);
        List<FavoriteCounty> dates = countryRepo.getAll();

    }

    @Override
    public void itemClick(String rowName) {
        startIntent(rowName);
    }



    public void FavoritePageToggleBtn(View view) {
        Intent intent = new Intent(this, FavoriteCountry.class);
         startActivity(intent);

    }

   /* public void LikedCountry(View view) {
    } */
}