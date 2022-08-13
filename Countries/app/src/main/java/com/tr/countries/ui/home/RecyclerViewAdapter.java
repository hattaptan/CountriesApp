package com.tr.countries.ui.home;

import android.app.admin.SystemUpdatePolicy;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tr.countries.R;
import com.tr.countries.data.Date;
import com.tr.countries.di.IntentKey;
import com.tr.countries.ui.countrydetail.CountryDetail;
import com.tr.countries.ui.favortecountry.FavoriteCountry;

import java.util.ArrayList;
/*
*
* RecycleView Class
*
* */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder> {


    private ArrayList<Date> countryList;//country list
    Context context;
    ArrayList<String> CountryArray= new ArrayList<String>();
    AdapterListener adapterListener;

    public RecyclerViewAdapter(ArrayList<Date> countryList) {
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);


        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {

        holder.binding(countryList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName;
        ToggleButton toggleButton;
        public RowHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.countryName);
            toggleButton = itemView.findViewById(R.id.likedCountryBTN);
        }
        public void binding(Date countryDom,Integer position){
             textName.setText(countryDom.name);
             itemView.setOnClickListener(new View.OnClickListener(){
                 @Override
                 public void onClick(View v) {
                     adapterListener.itemClick(countryList.get(position).code);
                 }
             });
             toggleButton.setOnClickListener(new View.OnClickListener(){
                 @Override
                 public void onClick(View v) {
                    // adapterListener.addToFavorite(countryList.get(position));
                    String favCountry = countryList.get(position).name;
                     System.out.println("FAVORITE COUNTRY : " + favCountry);
                 }
             });
        }
    }
}
