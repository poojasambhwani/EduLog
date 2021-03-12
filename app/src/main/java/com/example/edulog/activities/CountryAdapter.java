package com.example.edulog.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edulog.R;
import com.example.edulog.models.CountryData;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{
    ArrayList<CountryData> countryData;
    AlertDialog alertDialog;

    public CountryAdapter(ProfileActivity profileActivity, ArrayList<CountryData> data, AlertDialog alertDialog) {
        this.countryData = data;
        this.alertDialog = alertDialog;}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.et_country.setText(countryData.get(position).getCountryName());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private EditText et_country;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            et_country=itemView.findViewById(R.id.et_country);
        }
    }
}
