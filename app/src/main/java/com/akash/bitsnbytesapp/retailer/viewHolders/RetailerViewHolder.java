package com.akash.bitsnbytesapp.retailer.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.akash.bitsnbytesapp.R;


public class RetailerViewHolder extends RecyclerView.ViewHolder {
    public TextView shopName;

    public RetailerViewHolder(View itemView) {
        super(itemView);
        shopName = itemView.findViewById(R.id.viewText);

    }
}
