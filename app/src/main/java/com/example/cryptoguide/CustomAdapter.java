package com.example.cryptoguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
  private  ArrayList<Crypto> arrayList;
  private Context context;
  private static DecimalFormat  df = new DecimalFormat("#.##");

    public CustomAdapter(ArrayList<Crypto> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public void filterList(ArrayList<Crypto> filteredList)
    {
        arrayList = filteredList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom,parent,false);
        return new CustomAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position) {
    Crypto crypto = arrayList.get(position);
  holder.textView.setText(crypto.getName());
  holder.symbol.setText(crypto.getSign());
  holder.rate.setText("$ "+df.format(crypto.getPrice()));
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
              private TextView textView;
                private TextView symbol;
                private TextView rate;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            textView= (TextView)itemView.findViewById(R.id.textView);
            symbol = (TextView) itemView.findViewById((R.id.textView2));
            rate = (TextView) itemView.findViewById(R.id.textView4);

        }
    }
}
