package com.example.edulog.adapters;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edulog.R;
import com.example.edulog.activities.ProfileActivity;
import com.example.edulog.models.StateData;

import java.util.ArrayList;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder>{
    AlertDialog alertDialog;
    ArrayList<StateData> stateData;

    public StateAdapter(ProfileActivity profileActivity, ArrayList<StateData> statesData, AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
        this.stateData = statesData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.country_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.et_state.setText(stateData.get(position).getStateName());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private EditText et_state;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            et_state=itemView.findViewById(R.id.et_state);
        }
    }
}
