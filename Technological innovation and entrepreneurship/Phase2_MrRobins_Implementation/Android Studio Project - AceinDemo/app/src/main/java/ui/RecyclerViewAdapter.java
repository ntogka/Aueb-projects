package com.example.georgiosmoschovis.aceindemo.ui;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.georgiosmoschovis.aceindemo.R;

/**
 * This class implements a Recycler View Adapter.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
        public ImageView image;
        public TextView name;
        public TextView description;

        //Constructor matching super (class RecyclerView.ViewHolder)
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.INSURANCE_image);
            name = (TextView) itemView.findViewById(R.id.INSURANCE_name);
            description = (TextView) itemView.findViewById(R.id.INSURANCE_price);
        }
    }

    private List<Preview_DATA> listData = new ArrayList<Preview_DATA>();

    public RecyclerViewAdapter(ArrayList<Preview_DATA> listData) {
        this.listData = listData;
    }
    ItemSelectionListener<Preview_DATA> itemSelectionListener;


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item,parent,false);
        //itemView.setOnClickListener(mOnClickListener);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        final Preview_DATA dataAtPosition = listData.get(position);

        holder.image.setImageResource(listData.get(position).getImageId());
        holder.name.setText(listData.get(position).getName());
        holder.description.setText(listData.get(position).getDescription());
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // notify the Activity of the selected book
                if (itemSelectionListener != null) {
                    itemSelectionListener.onItemSelected(dataAtPosition);
                }
            }
        };

        holder.image.setOnClickListener(listener);
        holder.name.setOnClickListener(listener);
        holder.description.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void setItemSelectionListener(ItemSelectionListener<Preview_DATA> itemSelectionListener) {
        this.itemSelectionListener = itemSelectionListener;
    }
}
