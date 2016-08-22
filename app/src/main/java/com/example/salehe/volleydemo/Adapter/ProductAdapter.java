package com.example.salehe.volleydemo.Adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.salehe.volleydemo.R;
import com.example.salehe.volleydemo.pojo.Product;

import java.util.ArrayList;

/**
 * Created by Salehe on 8/22/2016.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<Product> arrayList = new ArrayList<Product>();
    Context context;

    public ProductAdapter(Context context) {
        this.context = context;
        notifyItemRangeChanged(0,arrayList.size());
    }

    public void setProductList(ArrayList<Product> arrayList){
        this.arrayList = arrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name, status, price,volleyError;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvName);
            status = (TextView) itemView.findViewById(R.id.tvStatus);
            price = (TextView) itemView.findViewById(R.id.tvPrice);
            volleyError = (TextView) itemView.findViewById(R.id.volleyError);
        }
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Product information = arrayList.get(position);
        holder.name.setText(information.getName());
        holder.price.setText(information.getPrice());
        holder.status.setText(information.getStatus());

    }

    @Override
    public int getItemCount() {
        return this.arrayList.size();

    }
}
