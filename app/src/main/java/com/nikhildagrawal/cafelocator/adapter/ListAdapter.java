package com.nikhildagrawal.cafelocator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nikhildagrawal.cafelocator.R;
import com.nikhildagrawal.cafelocator.models.Cafes;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    ArrayList<Cafes> list = new ArrayList<>();
    Context mContext;

    public ListAdapter(Context context , ArrayList<Cafes> list){
        mContext = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mTextViewName.setText(list.get(position).getCafeName());
        holder.mTextViewRating.setText(String.valueOf(list.get(position).getRating()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mTextViewName;
        TextView mTextViewRating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.tv_list);
            mTextViewRating = itemView.findViewById(R.id.tv_rating);
        }
    }
}
