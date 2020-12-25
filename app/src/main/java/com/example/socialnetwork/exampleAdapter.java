package com.example.socialnetwork;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class exampleAdapter extends RecyclerView.Adapter <exampleAdapter.ExampleViewHolder>{
    private ArrayList<Example_list>mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int item);
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        private final TextView mTextView1;

        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.AdapterTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }


    public exampleAdapter(ArrayList<Example_list> exampleLists){
    mExampleList = exampleLists;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Example_list currentItem = mExampleList.get(position);
        holder.mTextView1.setText(currentItem.getMtext1());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
