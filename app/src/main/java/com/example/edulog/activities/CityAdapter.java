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
import com.example.edulog.models.CityData;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{
    ArrayList<CityData> ctData;
    AlertDialog alertDialog;

    public CityAdapter(ProfileActivity profileActivity, ArrayList<CityData> cityData, AlertDialog alertDialog) {
        this.ctData = cityData;
        this.alertDialog = alertDialog;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CityData cityDataList= ctData.get(position);
        holder.et_city.setText(cityDataList.getCityName());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private EditText et_city;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            et_city=itemView.findViewById(R.id.et_city);
        }
    }
}

