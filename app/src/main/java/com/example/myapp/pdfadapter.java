package com.example.myapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class pdfadapter extends RecyclerView.Adapter<pdfadapter.holder> {

    private final List<pdfModel> list;

    public pdfadapter(List<pdfModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.pdflist,parent,false);
        return new holder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final holder holder, int position) {
        final pdfModel data=list.get(position);
        holder.textView.setText(data.getName());
        holder.textView2.setText(data.getCategory());

        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(holder.itemView.getContext(),ELpdf.class);
            intent.putExtra("pdfname",data.getName());
            intent.putExtra("pdfurl",data.getUrl());
            intent.putExtra("category",data.getCategory());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.itemView.getContext().startActivity(intent);

        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    static class holder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView,textView2;

        public holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageView);
            textView=itemView.findViewById(R.id.textView);
            textView2=itemView.findViewById(R.id.textView2);

        }
    }
}
