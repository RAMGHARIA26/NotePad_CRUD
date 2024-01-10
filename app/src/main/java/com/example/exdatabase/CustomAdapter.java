package com.example.exdatabase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList userList;
    ArrayList addressList;
    ArrayList phoneNumberList;
    ArrayList id;
    Activity activity;
    public CustomAdapter(Activity activity, Context context, ArrayList userList, ArrayList addressList, ArrayList phoneNumberList, ArrayList id) {
        this.context = context;
        this.activity = activity;
        this.userList = userList;
        this.phoneNumberList = phoneNumberList;
        this.addressList = addressList;
        this.id = id;

        Log.d("CustomAdapter", "id size: " + id.size());
        Log.d("CustomAdapter", "userList size: " + userList.size());
        Log.d("CustomAdapter", "addressList size: " + addressList.size());
        Log.d("CustomAdapter", "phoneNumberList size: " + phoneNumberList.size());
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.recyleview_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        holder.id.setText(String.valueOf(id.get(position)));
        holder.userName.setText(String.valueOf(userList.get(position)));
        holder.address.setText(String.valueOf(addressList.get(position)));
        holder.phoneNum.setText(String.valueOf(phoneNumberList.get(position)));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id",String.valueOf(id.get(position)));
                intent.putExtra("name",String.valueOf(userList.get(position)));
                intent.putExtra("address",String.valueOf(addressList.get(position)));
                intent.putExtra("number",String.valueOf(phoneNumberList.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,userName,address,phoneNum;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.idText);
            userName = itemView.findViewById(R.id.userText);
            address = itemView.findViewById(R.id.addressText);
            phoneNum = itemView.findViewById(R.id.phoneNumText);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
