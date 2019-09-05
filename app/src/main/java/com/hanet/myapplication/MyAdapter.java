package com.hanet.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.hanet.myapplication.model.Car;

public class MyAdapter extends PagedListAdapter<Car, MyAdapter.MyViewHolder> {


    protected MyAdapter() {
        super(Car.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_car, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Car car = getItem(position);
        if (car != null) holder.bindTo(car);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name, id;

        public MyViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
        }

        public void bindTo(Car car) {
            id.setText(String.valueOf(car.getId()));
            name.setText(car.getName());
        }
    }
}
