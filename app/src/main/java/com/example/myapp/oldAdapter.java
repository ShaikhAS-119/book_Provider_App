package com.example.myapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class oldAdapter extends RecyclerView.Adapter<oldAdapter.mainholder>{
    List<bookdetailpro> listdata;

    public oldAdapter(List<bookdetailpro> listdata) {
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public mainholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.listrefrence,parent,false);
        return new mainholder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final mainholder holder, int position) {
       final bookdetailpro data=listdata.get(position);
        holder.bookName.setText(data.getBookName());
        holder.writerName.setText(data.getWriterName());
        // holder.writerName.setText(data.getPrice());
        holder.price.setText(data.getPrice());
        Glide.with(holder.imageView.getContext()).load(data.getUrl()).into(holder.imageView);

        holder.itemView.setOnClickListener(view -> {
            Intent intent=new Intent(holder.itemView.getContext(),ELoldbook.class);
            intent.putExtra("contact",data.getContact());
            intent.putExtra("price",data.getPrice());
            intent.putExtra("pages",data.getPages());
            intent.putExtra("bookname",data.getBookName());
            intent.putExtra("writer",data.getWriterName());
            intent.putExtra("publisher",data.getPublisherName());
            intent.putExtra("Url",data.getUrl());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            holder.itemView.getContext().startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return listdata.size();
    }

    static class mainholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView bookName,writerName,price;
        public mainholder(@NonNull View itemView) {
            super(itemView);
                imageView=itemView.findViewById(R.id.imageView);
                bookName=itemView.findViewById(R.id.bookName);
                writerName=itemView.findViewById(R.id.writerName);
                price=itemView.findViewById(R.id.price);

        }
    }

}


