package com.example.edulog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edulog.R;
import com.example.edulog.activities.ProfileActivity;
import com.example.edulog.models.CountryData;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{
    ArrayList<CountryData> countryData;
    AlertDialog alertDialog;
    Context context;

    public CountryAdapter(Context profileActivity, ArrayList<CountryData> data, AlertDialog alertDialog) {
        this.countryData = data;
        this.context = profileActivity;
        this.alertDialog = alertDialog;}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.country.setText(countryData.get(position).getCountryName());
        holder.country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ProfileActivity)context).setCountryId(countryData.get(position).getCountryId());
                ((ProfileActivity)context).showCountry(countryData.get(position).countryName);
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(countryData.size() != 0){
            return countryData.size();
        }else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView country;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            country=itemView.findViewById(R.id.country);
        }
    }
}
