package com.example.edulog.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edulog.R;
import com.example.edulog.activities.ProfileActivity;
import com.example.edulog.models.CityData;

import java.util.ArrayList;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{
    ArrayList<CityData> ctData;
    AlertDialog alertDialog;
    Context context;

    public CityAdapter(Context profileActivity, ArrayList<CityData> cityData, AlertDialog alertDialog) {
        this.ctData = cityData;
        this.alertDialog = alertDialog;
        this.context = profileActivity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.et_city.setText(ctData.get(position).getCityName());
        holder.et_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ProfileActivity)context).setCityId(ctData.get(position).getCityId());
                ((ProfileActivity)context).showCity(ctData.get(position).getCityName());
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(ctData.size() != 0){
            return ctData.size();
        }else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private EditText et_city;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            et_city=itemView.findViewById(R.id.et_city);
        }
    }
}

